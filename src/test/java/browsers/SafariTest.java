package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.Test;

public class SafariTest {
    @Test
    public void SafariTest(){
        WebDriver driver = new SafariDriver();
        driver.get("https://www.apple.com");
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
