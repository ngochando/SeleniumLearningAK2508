package heroku;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DragAndDropTest extends BaseTest {
    @BeforeMethod
    public void openBrowser(){
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
    }

    @Test
    public void verifyMoveAToBSuccessfully(){
        Actions actions =new Actions(driver);
        WebElement a = driver.findElement(By.id("column-a"));
        WebElement b = driver.findElement(By.id("column-b"));

        Assert.assertEquals(a.getText(),"A");
        Assert.assertEquals(b.getText(),"B");

        actions.dragAndDrop(a,b).perform();

        Assert.assertEquals(a.getText(),"B");
        Assert.assertEquals(b.getText(),"A");

    }
}
