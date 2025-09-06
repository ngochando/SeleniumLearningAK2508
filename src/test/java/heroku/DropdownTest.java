package heroku;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DropdownTest extends BaseTest {
    @BeforeMethod
    public void openBrowser(){
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void verifySelectOptionSuccessfully(){
        /**
         * <select id="dropdown">
         *     <option value="" disabled="disabled">Please select an option</option>
         *     <option value="1">Option 1</option>
         *     <option value="2">Option 2</option>
         * </select>
         */
        WebElement select = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(select);
        dropdown.selectByVisibleText("Option 1");

        /**
         * <select id="dropdown">
         *     <option value="" disabled="disabled" selected="selected">Please select an option</option>
         *     <option value="1">Option 1</option>
         *     <option value="2">Option 2</option>
         * </select>
         */
        boolean isSelected = driver.findElement(By.xpath("//option[.='Option 1']")).isSelected();

        Assert.assertTrue(isSelected);
    }

    @Test
    void verifySelectMultipleOptionsSuccessfully(){
        driver.get("https://output.jsbin.com/osebed/2");

        Select dropdown = new Select(driver.findElement(By.id("fruits")));
        dropdown.selectByVisibleText("Banana");
        dropdown.selectByVisibleText("Apple");

        Assert.assertTrue(driver.findElement(By.xpath("//option[.='Banana']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//option[.='Apple']")).isSelected());
    }
}
