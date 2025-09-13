package heroku;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HoverTest extends BaseTest {
    @BeforeMethod
    public void openBrowser(){
        driver.get("https://the-internet.herokuapp.com/hovers");
    }

    @Test
    void verifyContentDisplayedWhenHoverAvatar() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement avatar1 = driver.findElement(By.xpath("//div[@class='example']/div[@class='figure'][1]"));

        actions.moveToElement(avatar1).perform();

        String caption = driver.findElement(By.xpath("//div[@class='example']/div[@class='figure'][1]/div[@class='figcaption']/h5")).getText();
        Assert.assertEquals(caption,"name: user1");
        driver.quit();
    }
}
