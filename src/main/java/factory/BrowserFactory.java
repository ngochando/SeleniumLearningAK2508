package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {
   public static WebDriver getDriver(String browserName){
       WebDriver driver;

       switch (browserName.toLowerCase()) {
           case "chrome":
               WebDriverManager.chromedriver().setup();
               driver = new ChromeDriver();
               break;

           case "firefox":
               WebDriverManager.firefoxdriver().setup();
               driver = new FirefoxDriver();
               break;

           case "safari":
               WebDriverManager.safaridriver().setup();
               driver = new SafariDriver();
               break;

           case "chrome-headless":
               WebDriverManager.chromedriver().setup();
               ChromeOptions options = new ChromeOptions();
               options.addArguments("--headless");
               driver = new ChromeDriver(options);
               break;

           default:
               throw new IllegalArgumentException(
                       "Browser name isn't valid! " + browserName
               );
       }

       return driver;
   }


}
