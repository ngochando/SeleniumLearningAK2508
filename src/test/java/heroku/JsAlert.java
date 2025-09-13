package heroku;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JsAlert extends BaseTest {
    @BeforeMethod
    public void openBrowser(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    @Test
    public void acceptJSConfirmPopup(){
        driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();

        driver.switchTo().alert().accept();
        Assert.assertTrue(driver.findElement(By.id("result")).getText().contains("You clicked: Ok"));

        driver.quit();
    }

    @Test
    public void cancelJSConfirmPopup(){
        driver.findElement(By.xpath("//button[.='Click for JS Confirm']")).click();

        driver.switchTo().alert().dismiss();
        Assert.assertTrue(driver.findElement(By.id("result")).getText().contains("You clicked: Cancel"));
    }

    @Test
    void fillJSPromptPopup(){
        driver.findElement(By.xpath("//button[.='Click for JS Prompt']")).click();

        driver.switchTo().alert().sendKeys("hello");
        driver.switchTo().alert().accept();
        Assert.assertTrue(driver.findElement(By.id("result")).getText().contains("You entered: hello"));
    }
}
