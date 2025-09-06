package heroku;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static utlis.Browser.check;
import static utlis.Browser.uncheck;

public class CheckboxTest extends BaseTest {
    @BeforeMethod
    public void openBrowser(){
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test(priority = 0)
    public void verifyTheCheckboxesShouldChecked() {
        WebElement checkbox1 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));

        check(checkbox1);
        Assert.assertTrue(checkbox1.isSelected(), "Checkbox 1 should be checked");

        check(checkbox2);
        Assert.assertTrue(checkbox2.isSelected(), "Checkbox 2 should be checked");
    }

    @Test(priority = 1)
    public void verifyTheCheckboxesShouldUnchecked(){
        WebElement checkbox1 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));

        uncheck(checkbox1);
        Assert.assertFalse(checkbox1.isSelected(), "Checkbox 1 should be unchecked");

        uncheck(checkbox2);
        Assert.assertFalse(checkbox2.isSelected(), "Checkbox 2 should be unchecked");
    }
}
