package location;

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


public class LocationPage {
    private WebDriver driver;

    public LocationPage(WebDriver driver) {
        this.driver = driver;

        if (!driver.getTitle().contains("localhost:8080/pg6100_exam/home.faces")) {
            throw new IllegalStateException("This is not http://localhost:8080/pg6100_exam/home.faces: " + driver.getCurrentUrl());
        }
    }

    public String getTitle() {
        WebElement resultPageHeadLine = driver.findElement(By.tagName("title"));
        return resultPageHeadLine.getText();
    }
}