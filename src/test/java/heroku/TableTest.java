package heroku;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.classfile.attribute.SyntheticAttribute;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class TableTest extends BaseTest {
    @BeforeMethod
    public void openBrowser(){
        driver.get("https://the-internet.herokuapp.com/tables");
    }

    @Test
    public void verifyLargestDuePerson(){
        List<WebElement> cells = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td"));
        List<Double> dueValue = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]"))
                .stream()
                .map(WebElement::getText)
                .map(d ->Double.parseDouble(d.replace("$","")))
                .toList();
        Double maxDue = dueValue.stream().max(Comparator.naturalOrder()).get();
        int maxDueRowIndex = dueValue.indexOf(maxDue)+1;
        String maxDuePersonFirstNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[2]",maxDueRowIndex);
        String maxDuePersonLastNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[1]",maxDueRowIndex);
        String firstName = driver.findElement(By.xpath(maxDuePersonFirstNameLocator)).getText();
        String lastName = driver.findElement(By.xpath(maxDuePersonLastNameLocator)).getText();

        Assert.assertEquals(String.format("%s %s",firstName,lastName), "Jason Doe");
    }

    @Test
    public void verifySmallestDuePerson(){
        List<WebElement> cells = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td"));
        List<Double> dueValue = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]"))
                .stream()
                .map(WebElement::getText)
                .map(d ->Double.parseDouble(d.replace("$","")))
                .toList();
        Double minDue = dueValue.stream().min(Comparator.naturalOrder()).get();
        int minDueRowIndex = dueValue.indexOf(minDue)+1;
        String minDuePersonFirstNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[2]",minDueRowIndex);
        String minDuePersonLastNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[1]",minDueRowIndex);
        String firstName = driver.findElement(By.xpath(minDuePersonFirstNameLocator)).getText();
        String lastName = driver.findElement(By.xpath(minDuePersonLastNameLocator)).getText();

        Assert.assertEquals(String.format("%s %s",firstName,lastName), "John Smith");
    }

    @Test
    public void verifyAllLargestDuePerson(){
        List<Double> dueValue = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]"))
                .stream()
                .map(WebElement::getText)
                .map(d -> Double.parseDouble(d.replace("$", "")))
                .toList();
        Double maxValue = dueValue.stream().max(Comparator.naturalOrder()).get();
        List<Integer> maxIndexs = IntStream.range(0, dueValue.size())
                .filter(i -> dueValue.get(i).equals(maxValue))
                .boxed()
                .toList();
        if(maxIndexs.size() == 1){
            int row = maxIndexs.get(0) + 1;
            String maxDuePersonFirstNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[2]",row);
            String maxDuePersonLastNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[1]",row);
            String firstName = driver.findElement(By.xpath(maxDuePersonFirstNameLocator)).getText();
            String lastName = driver.findElement(By.xpath(maxDuePersonLastNameLocator)).getText();

            Assert.assertEquals(String.format("%s %s", firstName, lastName), "Jason Doe");
        } else {
            List<String> actualsNames = new ArrayList<>();
            for(int row : maxIndexs){
                int xpathRow = row + 1;
                String maxDuePersonFirstNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[2]",xpathRow);
                String maxDuePersonLastNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[1]",xpathRow);
                String firstName = driver.findElement(By.xpath(maxDuePersonFirstNameLocator)).getText();
                String lastName = driver.findElement(By.xpath(maxDuePersonLastNameLocator)).getText();
                actualsNames.add(String.format("%s %s", firstName, lastName));
            }
            List<String> expetctNames = List.of("", "");
            Assert.assertEquals(actualsNames, expetctNames);
        }
    }


    @Test
    public void verifyAllSmallestDuePerson(){
        List<Double> dueValue = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]"))
                .stream()
                .map(WebElement::getText)
                .map(d -> Double.parseDouble(d.replace("$", "")))
                .toList();
        Double minValue = dueValue.stream().min(Comparator.naturalOrder()).get();
        List<Integer> minIndexs = IntStream.range(0, dueValue.size())
                .filter(i -> dueValue.get(i).equals(minValue))
                .boxed()
                .toList();
        if(minIndexs.size() == 1){
            int row = minIndexs.get(0) + 1;
            String minDuePersonFirstNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[2]", row);
            String minDuePersonLasttNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[1]", row);
            String firstName = driver.findElement(By.xpath(minDuePersonFirstNameLocator)).getText();
            String lastName = driver.findElement(By.xpath(minDuePersonLasttNameLocator)).getText();

            Assert.assertEquals(String.format("%s %s", firstName, lastName), "");
        } else {
            List<String> actualNames = new ArrayList<>();
            for(int row : minIndexs){
                int xpathRow = row + 1;
                String minDuePersonFirstNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[2]", xpathRow);
                String minDuePersonLasttNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[1]", xpathRow);
                String firstName = driver.findElement(By.xpath(minDuePersonFirstNameLocator)).getText();
                String lastName = driver.findElement(By.xpath(minDuePersonLasttNameLocator)).getText();
                String name = String.format("%s %s", firstName, lastName);
                actualNames.add(name);
            }
            List<String> expectedNames = List.of("John Smith", "Tim Conway");
            Assert.assertEquals(actualNames, expectedNames);
        }
    }
}
