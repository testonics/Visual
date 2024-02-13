package in.testonics.omni.frameworks;

import in.testonics.omni.Interface.Omni;
import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WinApp implements Omni {

    private static WindowsDriver driver = null;
    private static Actions action;
    public String appPath = "";

    public static void startWinAppDriver() {
        try {
            Desktop desktop = Desktop.getDesktop();
            File file = new File("C:\\Program Files\\Windows Application Driver\\WinAppDriver.exe");;

            if (!file.exists()){
                file = new File("C:\\Program Files (x86)\\Windows Application Driver\\WinAppDriver.exe");
            }

            /* Check if there is support for Desktop or not */
            if(!Desktop.isDesktopSupported()) {
                System.out.println("not supported");
                return;
            }
            if (file.exists()) {
                System.out.println("Open WinAppDriver.exe\n");
                desktop.open(file);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Encountered Exception\n");
            throw new RuntimeException(e);
        }
    }

    public static void stopWinAppDriver() {
        try {
            ProcessBuilder processBuilder =new ProcessBuilder("taskkill ","/f","/IM","WinAppDriver.exe");
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void setApp(String appPath){
        this.appPath = appPath;
    }

    public void setDriver() {
        DesiredCapabilities capability = new DesiredCapabilities();

        capability.setCapability("ms:experimental-webdriver", true);
        capability.setCapability("app", appPath);
        capability.setCapability("platformName", "Windows");
        capability.setCapability("deviceName", "Windows10Machine");

        /* Start WinAppDriver.exe so that it can start listening to incoming requests */
        startWinAppDriver();

        try{
            Thread.sleep(2000);
            driver = new WindowsDriver(new URL("http://127.0.0.1:4723/"), capability);
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            System.out.println("Exception Occurred : " + e);
        }

    }

    public WebDriver getDriver(){
        return driver;
    }

    public void closeDriver() {
        if (driver != null) {
            stopWinAppDriver();
            driver.quit();
        }
    }

    public Actions getAction(){
        if (action == null){
        /* create an object for the Actions class and pass the driver argument */
            action = new Actions(driver);
        }
        return action;
    }

    @Override
    public void setNavigationUrl(String url) {
        System.out.println("Not Applicable");
    }

    @Override
    public void setHeadlessMode(boolean headlessMode) {
        System.out.println("Not Applicable");
    }

    @Override
    public void setBrowser(String browser) {
        System.out.println("Not Applicable");
    }

    @Override
    public void click(Object element) {
        WebElement webElement =getWebElement(element);
        webElement.click();
    }

    public void clickWithAction(Object element) {
        WebElement webElement =getWebElement(element);
        getAction().click(webElement);
        getAction().perform();
    }

    @Override
    public void enter(Object element, String value) {
        WebElement webElement =getWebElement(element);
        webElement.sendKeys(value);
    }

    @Override
    public void select(Object element, Object value) {

    }

    public String getText(Object element){
        return getWebElement(element).getText();
    }

    WebElement getWebElement(Object element){
        if (element instanceof String)
            return driver.findElement(By.xpath((String) element));
        else
            return (WebElement) element;
    }
}