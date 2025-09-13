package heroku;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class TableTest extends BaseTest {
    // === CONSTANTS ===
    private static final String EXPECTED_MAX_DUE_PERSON = "Jason Doe";
    private static final String EXPECTED_MIN_DUE_PERSON = "John Smith";
    private static final List<String> EXPECTED_MIN_DUE_PERSONS = List.of("John Smith", "Tim Conway");
    private static final List<String> EXPECTED_MAX_DUE_PERSONS = List.of("", "");

    @BeforeMethod
    public void openBrowser(){
        driver.get("https://the-internet.herokuapp.com/tables");
    }

    @Test(enabled=false)
    public void verifyLargestDuePerson(){
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
        System.out.println(String.format("%s %s",firstName,lastName));

        Assert.assertEquals(String.format("%s %s",firstName,lastName), EXPECTED_MAX_DUE_PERSON);
    }

    @Test(enabled=false)
    public void verifySmallestDuePerson(){
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
        System.out.println(String.format("%s %s",firstName,lastName));

        Assert.assertEquals(String.format("%s %s",firstName,lastName), EXPECTED_MIN_DUE_PERSON);
    }

    @Test(enabled=false)
    public void verifyAllLargestDuePerson(){
        List<Double> dueValue = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]"))
                .stream()
                .map(WebElement::getText)
                .map(d -> Double.parseDouble(d.replace("$", "")))
                .toList();
        Double maxValue = dueValue.stream().max(Comparator.naturalOrder()).get();

        List<Integer> maxIndexes = IntStream.range(0, dueValue.size())
                .filter(i -> dueValue.get(i).equals(maxValue))
                .boxed()
                .toList();
        if(maxIndexes.size() == 1){
            int row = maxIndexes.getFirst() + 1;
            String maxDuePersonFirstNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[2]",row);
            String maxDuePersonLastNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[1]",row);
            String firstName = driver.findElement(By.xpath(maxDuePersonFirstNameLocator)).getText();
            String lastName = driver.findElement(By.xpath(maxDuePersonLastNameLocator)).getText();
            System.out.println("Max Due person: " + String.format("%s %s",firstName,lastName));

            Assert.assertEquals(String.format("%s %s", firstName, lastName), EXPECTED_MAX_DUE_PERSON);
        } else {
            List<String> actualNames = new ArrayList<>();
            for(int row : maxIndexes){
                int xpathRow = row + 1;
                String maxDuePersonFirstNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[2]",xpathRow);
                String maxDuePersonLastNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[1]",xpathRow);
                String firstName = driver.findElement(By.xpath(maxDuePersonFirstNameLocator)).getText();
                String lastName = driver.findElement(By.xpath(maxDuePersonLastNameLocator)).getText();
                actualNames.add(String.format("%s %s", firstName, lastName));
            }

            Assert.assertEquals(actualNames, EXPECTED_MAX_DUE_PERSONS);
            System.out.println("Max Due persons list: " + actualNames);
        }
    }


    @Test(enabled=false)
    public void verifyAllSmallestDuePerson(){
        List<Double> dueValue = driver.findElements(By.xpath("//table[@id='table1']/tbody/tr/td[4]"))
                .stream()
                .map(WebElement::getText)
                .map(d -> Double.parseDouble(d.replace("$", "")))
                .toList();
        Double minValue = dueValue.stream().min(Comparator.naturalOrder()).get();

        List<Integer> minIndexes = IntStream.range(0, dueValue.size())
                .filter(i -> dueValue.get(i).equals(minValue))
                .boxed()
                .toList();
        if(minIndexes.size() == 1){
            int row = minIndexes.getFirst() + 1;
            String minDuePersonFirstNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[2]", row);
            String minDuePersonLasttNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[1]", row);
            String firstName = driver.findElement(By.xpath(minDuePersonFirstNameLocator)).getText();
            String lastName = driver.findElement(By.xpath(minDuePersonLasttNameLocator)).getText();
            System.out.println("Min Due person: " + String.format("%s %s",firstName,lastName));

            Assert.assertEquals(String.format("%s %s", firstName, lastName), EXPECTED_MIN_DUE_PERSON);
        } else {
            List<String> actualNames = new ArrayList<>();
            for(int row : minIndexes){
                int xpathRow = row + 1;
                String minDuePersonFirstNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[2]", xpathRow);
                String minDuePersonLasttNameLocator = String.format("//table[@id='table1']/tbody/tr[%d]/td[1]", xpathRow);
                String firstName = driver.findElement(By.xpath(minDuePersonFirstNameLocator)).getText();
                String lastName = driver.findElement(By.xpath(minDuePersonLasttNameLocator)).getText();
                String name = String.format("%s %s", firstName, lastName);
                actualNames.add(name);
            }

            Assert.assertEquals(actualNames, EXPECTED_MIN_DUE_PERSONS);
            System.out.println("Min Due persons list: " + actualNames);
        }
    }

    List<Person> table1 = new ArrayList<>();
    public void getTable1(){
        driver.findElements(By.xpath("//table[@id='table1']/tbody/tr"))
                .forEach(row -> {
                    String firstName = row.findElement(By.xpath("./td[2]")).getText();
                    String lastName = row.findElement(By.xpath("./td[2]")).getText();
                    String due = row.findElement(By.xpath("./td[4]]")).getText();

                    table1.add(new Person(firstName, lastName, due));
                });
    }

    @Test
    public void verifyLargestDuePerson_1(){

    }
}
