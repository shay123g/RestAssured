package HTMLResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.fail;


public class Ebay
{
Document doc;
Element logo,dropDown;
    @BeforeClass
    public void InitVariables() throws IOException
    {
        System.setProperty("webdriver.chrome.driver", "C://Automation//libs//chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        doc = Jsoup.connect("https://www.ebay.com/").get();
        logo=doc.getElementById("gh-logo");
        dropDown=doc.getElementById("gh-cat");
    }
    @Test
    public void Test01_Dimensions()
    {
        try
        {
            assertEquals("250",logo.attr("width"));
            assertEquals("200",logo.attr("height"));
        }
        catch (AssertionError e)
        {
            System.out.println("Logo Dimensions mismatch. see details "+e.getMessage());
            fail();
        }
    }

    @Test
    public void Test02_Dropdown()
    {
        try
        {
            assertEquals("All Categories",dropDown.text());
        }
        catch (AssertionError e)
        {
            System.out.println("dropdown text mismatch. see details "+e.getMessage());
            fail();
        }
    }
}
