package selenium;

/**
 * Created by ng20 on 27.05.2016.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertTrue;


public class LocationPageTest {
    private WebDriver driver;

    @Before
    public void setUp() throws Exception{
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void verifyPageisFound() throws Exception{

        String baseUrl = "http://localhost:8080/pg6100_exam/home.faces";
        driver.get(baseUrl);

        WebElement resultPageHeadLine = driver.findElement(By.tagName("title"));
        assertTrue(resultPageHeadLine.getText().contains("Home page"));
    }
}