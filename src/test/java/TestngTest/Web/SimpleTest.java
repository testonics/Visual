package TestngTest.Web;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.openqa.selenium.support.locators.RelativeLocator.with;


import web.Browser;

public class SimpleTest {

    @Test
    public void launchURL() {
        //Creating an object of ChromeDriver
        WebDriver driver = Browser.getWebDriver();

        driver.get("https://www.google.com");

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();
        System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
    }

}
