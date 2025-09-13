package heroku;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NestedFrameTest extends BaseTest {
    @BeforeMethod
    public void openBrowser(){
        driver.get("https://the-internet.herokuapp.com/nested_frames");
    }

    @Test
    public void verifyNestedFrameContent(){
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-left");
        String content = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(content.contains("LEFT"));

        driver.switchTo().parentFrame(); //frame-top
        driver.switchTo().frame("frame-middle");
        content = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(content.contains("MIDDLE"));

        driver.switchTo().parentFrame(); //frame-top
        driver.switchTo().frame("frame-right");
        content = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(content.contains("RIGHT"));

        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-bottom");
        content = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(content.contains("BOTTOM"));

        driver.quit();
    }
}
