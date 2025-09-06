package heroku;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @BeforeMethod
    public void openBrowser(){
        driver.get("https://the-internet.herokuapp.com/login");
    }
    /*
    TC01: Form Authentication : Login successful with valid credentials

    Open browser

    Navigate to https://the-internet.herokuapp.com/login

    Fill in username with tomsmith

    Fill in the password with SuperSecretPassword!

    Click on Login button

    And the home page is appear
     */
    @Test
    public void successfullyWithValidCredential(){
        //Fill in the username with tomsmith
        /**
         * Tagname: input
         * Attribute:
         *  +type=text
         *  +name=username
         *  +id=username
         * Text: n/a
         */
        driver.findElement(By.id("username")).sendKeys("tomsmith");

        //Fill in the password with SuperSecretPassword!
        /**
         * Tagname: input
         * Attribute:
         *  +type=password
         *  +name=password
         *  +id=password
         * Text: n/a
         */
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        //Click on Login button
        /**
         * Tagname: button
         * Attribute:
         *  +class=radius
         *  +type=submit
         * Text: n/a
         */
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //And the home page is appear
        System.out.println("Current URL: " + driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure");

        String successMessage = driver.findElement(By.className("success")).getText();
        System.out.println(successMessage);

        Assert.assertTrue(successMessage.contains("You logged into a secure area!"));

        driver.quit();

    }
}
