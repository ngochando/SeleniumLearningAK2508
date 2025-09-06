package heroku;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HyperLinkTest extends BaseTest {
    @BeforeMethod
    public void openBrowser(){
        driver.get("https://the-internet.herokuapp.com/status_codes");
    }
    /**
     * Open browser
     *
     * Navigate to https://the-internet.herokuapp.com/status_codes
     *
     * Click on "200"
     *
     * Then "200 status code" page appear
     *
     * Click on "go here"
     *
     * Click on "301"
     *
     * Then "301 status code" page appear
     *
     * Click on "go here"
     *
     * Click on "404"
     *
     * Then "404 status code" page appear
     *
     * Click on "go here"
     *
     * Click on "500"
     *
     * Then "500 status code" page appear
     *
     * Click on "go here"
     */

    @Test
    public void verifyNavigateHyperLinksSuccessfully(){
        WebElement link200 = driver.findElement(By.linkText("200"));
        WebElement link301 = driver.findElement(By.linkText("301"));
        WebElement link404 = driver.findElement(By.linkText("404"));
        WebElement link500 = driver.findElement(By.linkText("500"));

        link200.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/200");

        driver.findElement(By.linkText("here")).click();

        link301.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/301");

        driver.findElement(By.linkText("here")).click();

        link404.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/404");

        driver.findElement(By.linkText("here")).click();

        link500.click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/status_codes/500");

        driver.findElement(By.linkText("here")).click();




    }
}
