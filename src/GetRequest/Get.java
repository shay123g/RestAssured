package GetRequest;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.fail;

public class Get
{
    String url, city, key;
    RequestSpecification request;
    Response response;

    @BeforeClass
    public void Init()
    {
        url = "http://api.openweathermap.org/data/2.5/weather";
        city = "Jerusalem";
        key = "e05c9a6c9dc4ef7a9e9a48f6ef216988";
        RestAssured.baseURI = url;
        request = RestAssured.given();
    }

    @Test
    public void Test01_jsonValidation()
    {
        try
        {
            response = request.get("?q=" + city + "&APPID=" + key + "&units=metric");
            DoPrints();
            assertEquals(response.getContentType().toLowerCase().substring(12,16), "json");
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
            assertEquals(response.getStatusCode(), 200);
        } catch (AssertionError e)
        {
            System.out.println("Status code not 200 OK");
            fail();
        }


    }

    public void DoPrints()
    {
        System.out.println(response.getBody().toString());
        System.out.println("**************************************************************");
        System.out.println(response.getStatusLine());
        System.out.println("**************************************************************");
        System.out.println(response.getHeader("Date"));
        System.out.println("**************************************************************");
    }
}
