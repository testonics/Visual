package TestJUnit;

import in.testonics.omni.frameworks.Omni;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class WinCalculatorTest {

    Omni omni = new Omni();
    private WindowsDriver driver;

    @Test(description = "Demonstration of Mouse Actions using ActionChains")
    public void test_mouse_interactions() throws InterruptedException, MalformedURLException {

        omni.setFramework("windows");
        omni.setApp("Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
        omni.setDriver();
        driver = (WindowsDriver) omni.getDriver();

        /* Click on the Toggle Menu button */
        omni.clickWithAction(driver.findElementByAccessibilityId("TogglePaneButton"));
        /* Blocking Sleep - Used only for demo, Explicit Sleep is preferred */
        Thread.sleep(3000);

        /* Click on the Scientific item in the Menu */
        omni.clickWithAction(driver.findElementByAccessibilityId("Standard"));
    }

    @Test(description = "Demonstration of Button click", priority = 1)
    public void button_click_interactions() throws InterruptedException {
        /* Option 1: Click on Button - 1 by identifying it using name property */
        /* driver.findElement(By.name("One")).click(); */

        /* Option 2: Click on Button - 1 by identifying it using AccessibilityID property */
        omni.click(driver.findElementByAccessibilityId("num1Button"));

        /* Use the Name property for locating the buttons and performing click operation */
        omni.click(driver.findElement(By.name("Plus")));
        omni.click(driver.findElement(By.name("Nine")));
        omni.click(driver.findElement(By.name("Equals")));

        /* The result should be 10, assert if the result pane does not show 10 */
        String resultantText = "10";
        String resultsElementText = omni.getText(driver.findElementByAccessibilityId("CalculatorResults")).replace("Display is", "").trim();

        /* Assert if the result is not 10 */
        Assert.assertEquals(resultantText, resultsElementText);

        omni.close();
    }

}