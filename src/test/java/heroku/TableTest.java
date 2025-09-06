package heroku;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.classfile.attribute.SyntheticAttribute;
import java.util.Comparator;
import java.util.List;

public class TableTest extends BaseTest {
    @BeforeMethod
    public void openBrowser(){
        driver.get("https://the-internet.herokuapp.com/tables");
    }

    @Test
    public void verifyLargestDuePerson(){
        List<WebElement> cells = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td"));
        // Due Column //table[@id='table1']/tbody/tr/td[4]
        List<Double> dueValue = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]"))
                .stream()
                .map(WebElement::getText)
                .map(d ->Double.parseDouble(d.replace("$","")))
                .toList();
        Double maxDue = dueValue.stream().max(Comparator.naturalOrder()).get();
        int maxDueRowIndex = dueValue.indexOf(maxDue)+1;

        System.out.println("Max due: " + maxDue);

        String maxDuePersonFirstNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[2]",maxDueRowIndex);
        String maxDuePersonLastNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[1]",maxDueRowIndex);
        String firstName = driver.findElement(By.xpath(maxDuePersonFirstNameLocator)).getText();
        String lastName = driver.findElement(By.xpath(maxDuePersonLastNameLocator)).getText();

        System.out.println(firstName);
        System.out.println(lastName);

        Assert.assertEquals(String.format("%s %s",firstName,lastName), "Jason Doe");
    }

    @Test
    public void verifySmallestDuePerson(){
        List<WebElement> cells = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td"));
        // Due Column //table[@id='table1']/tbody/tr/td[4]
        List<Double> dueValue = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]"))
                .stream()
                .map(WebElement::getText)
                .map(d ->Double.parseDouble(d.replace("$","")))
                .toList();
        Double minDue = dueValue.stream().min(Comparator.naturalOrder()).get();
        int minDueRowIndex = dueValue.indexOf(minDue)+1;

        System.out.println("Min due: " + minDue);

        String minDuePersonFirstNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[2]",minDueRowIndex);
        String minDuePersonLastNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[1]",minDueRowIndex);
        String firstName = driver.findElement(By.xpath(minDuePersonFirstNameLocator)).getText();
        String lastName = driver.findElement(By.xpath(minDuePersonLastNameLocator)).getText();

        System.out.println(firstName);
        System.out.println(lastName);

        Assert.assertEquals(String.format("%s %s",firstName,lastName), "John Smith");
    }
}
