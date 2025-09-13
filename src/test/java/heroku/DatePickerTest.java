package heroku;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerTest extends BaseTest {
    private WebDriverWait wait;

    @BeforeMethod
    public void openBrowser(){
        driver.get("https://www.vietnamairlines.com/vn/vi/home");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("cookie-agree")).click();
    }

    @Test
    public void verifyDepartureDate_Dynamic(){
        //1. Calculate target date
        LocalDate today = LocalDate.now();
        System.out.println("Today: " + today);

        LocalDate targetDate = today.plusWeeks(15);
        System.out.println("Target date: " + targetDate);

        int targetYear = targetDate.getYear();
        int targetMonth = targetDate.getMonthValue() - 1;
        int targetDay = targetDate.getDayOfMonth();

        //2. Open date picker
        wait.until(ExpectedConditions.elementToBeClickable(By.id("roundtrip-date-depart"))).click();

        //3. Build xpath locator of target date
        String xpathTarget = """
        ((//table[@class='ui-datepicker-calendar'])[1] | (//table[@class='ui-datepicker-calendar'])[2])
        //td[@data-handler='selectDay' and @data-year='%d'and @data-month='%d']
        //a[normalize-space()='%d']
        """.formatted(targetYear, targetMonth, targetDay);

        System.out.println(xpathTarget);
        By nextBtn = By.xpath("//a[@class='ui-datepicker-next ui-corner-all']");

        boolean isFound = false;
        //Loop through each month to find target date (max 24 times -> mean 2 years)
        for (int i = 0; i < 24; i++){
            var matches = driver.findElements(By.xpath(xpathTarget));
            System.out.println(matches);

            if(!matches.isEmpty()){
                wait.until(ExpectedConditions.elementToBeClickable(matches.getFirst())).click();
                isFound = true;
                break;
            } else {
                wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();
                wait.until(ExpectedConditions.
                        presenceOfAllElementsLocatedBy
                                (By.xpath("//table[@class='ui-datepicker-calendar']")));
            }
        }

        if(!isFound){
            throw new NoSuchElementException("Not found target date " + " on date picker");
        }

        //Assertion
        String expected = targetDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Expected: " + expected);

        String actual = driver.findElement(By.id("roundtrip-date-depart")).getAttribute("value");
        System.out.println("Actual: " + actual);

        Assert.assertEquals(actual, expected);
    }
}
