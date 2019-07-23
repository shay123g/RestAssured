package GetRequest;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.fail;

public class Get
{
    String url, city1,city2, key;
    RequestSpecification request;
    Response response1,response2;
    JsonPath jp;
    WebDriver driver;

    @BeforeClass
    public void Init()
    {
        System.setProperty("webdriver.chrome.driver", "C:/Automation/libs/chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        driver = new ChromeDriver();
        url = "http://api.openweathermap.org/data/2.5/weather";
        city1 = "Haifa";
        city2="Jerusalem";
        key = "e05c9a6c9dc4ef7a9e9a48f6ef216988";
        RestAssured.baseURI = url;
        request = RestAssured.given();
        response1 = request.get("?q=" + city1 + "&APPID=" + key + "&units=metric");
        response2 = request.get("?q=" + city2 + "&APPID=" + key + "&units=metric");
        jp=response1.jsonPath();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void Test01_jsonValidation()
    {
        try
        {
            DoPrints();
            assertEquals(response1.getContentType().toLowerCase().substring(12,16), "json");
        } catch (AssertionError e)
        {
            System.out.println("content type is not json");
            fail();
        }
    }

    @Test
    public void Test02_StatusCodeValidation()
    {
        try
        {
            assertEquals(response1.getStatusCode(), 200);
        } catch (AssertionError e)
        {
            System.out.println("Status code not 200 OK");
            fail();
        }
    }

        @Test
        public void Test03_HaifaILValid()
        {
            try
            {
                assertEquals("IL",jp.get("sys.country").toString());

            } catch (AssertionError e)
            {
                System.out.println("Country is not IL as expected");
                fail();
            }



        }

    @Test
    public void Test04_Humidity()
    {
        try
        {

            Integer HumidityAPI=jp.get("main.humidity");
            driver.get("https://openweathermap.org/");
            driver.findElement(By.xpath("//input[@placeholder='Your city name']")).sendKeys("Haifa");
            driver.findElement(By.xpath("//button[@class='btn btn-orange']")).click();
            driver.findElement(By.xpath("//table/tbody/tr/td[2]/b/a")).click();
            String HumidityWeb=driver.findElement(By.xpath("//div[@id=\"weather-widget\"]/table/tbody/tr[4]/td[2]")).getText();
            HumidityWeb=HumidityWeb.substring(0,2);
            System.out.println("Web="+HumidityWeb);
            System.out.println("API="+HumidityAPI);
            assertEquals(HumidityWeb,HumidityAPI.toString());


        } catch (AssertionError e)
        {
            System.out.println("the Humidity from server and from client is not the same");
            System.out.println(e.getMessage());
            fail();
        }



    }


    @AfterClass
public void after()
{
    driver.quit();
}
    public void DoPrints()
    {
        System.out.println(response1.getBody().toString());
        System.out.println("**************************************************************");
        System.out.println(response1.getStatusLine());
        System.out.println("**************************************************************");
        System.out.println(response1.getHeader("Date"));
        System.out.println("**************************************************************");
    }

}
