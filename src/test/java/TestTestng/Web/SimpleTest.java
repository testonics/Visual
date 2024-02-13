package TestTestng.Web;

import in.testonics.omni.frameworks.Omni;
import org.testng.annotations.Test;

public class SimpleTest {

    in.testonics.omni.frameworks.Omni omni = new Omni();

    @Test
    public void launchURL() {
        //Creating an object of ChromeDriver
//        WebDriver driver = (WebDriver) omni.getDriver();
//
//        driver.get("https://www.google.com");
//
//        WebElement element = driver.findElement(By.name("q"));
//        element.sendKeys("Cheese!");
//        element.submit();
//        System.out.println("Page title is: " + driver.getTitle());
//        driver.quit();
    }

}
