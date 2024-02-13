package in.testonics.omni.frameworks;

import in.testonics.omni.Interface.Omni;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URL;

public class Android implements Omni {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    MutableCapabilities caps = new MutableCapabilities();
    public String appIDOrName = "";

    @Override
    public void setNavigationUrl(String url){
        System.out.println("Not Applicable");
    }

    @Override
    public void setHeadlessMode(boolean headlessMode){
        System.out.println("Not Applicable");
    }

    @Override
    public void setBrowser(String browser){
        System.out.println("Not Applicable");
    }

    public void setCapabilities(){
        caps.setCapability("appiumVersion", "1.17.1");
        caps.setCapability("platformVersion", "10");
        caps.setCapability("deviceName", "Samsung.*");
        caps.setCapability("platformName", "android");
        caps.setCapability("noReset", "true");
        caps.setCapability("idleTimeout", "90");
        caps.setCapability("newCommandTimeout", "90");
        caps.setCapability("name", "android test");
        caps.setCapability("app","storage:" + appIDOrName);
    }

    public void setApp(String appIDOrName){
        this.appIDOrName = appIDOrName;
    }

    @Override
    public void setDriver() {
        //Saucelabs details
        String user = "";
        String accessKey = "";
        String server = "@ondemand.us-west-1.saucelabs.com:443/wd/hub";

        setCapabilities();
        String url = "https://" + user + ":" + accessKey + server;

        RemoteWebDriver androidDriver = null;
        try {
            androidDriver = new AndroidDriver(new URL(url), caps);
        }catch (Exception exception){
            System.out.println("Exception Occurred while setting up android driver\n" + exception);
        }
        driver.set(androidDriver);
    }

    public RemoteWebDriver getDriver(){
        return driver.get();
    }

    @Override
    public void closeDriver(){
        getDriver().quit();
    }

    @Override
    public void click(Object element) {
        WebElement webElement = getWebElement(element);
        webElement.click();
    }

    @Override
    public void enter(Object element, String value) {
        WebElement webElement = getWebElement(element);
        webElement.sendKeys(value);
        webElement.submit();
    }

    @Override
    public void select(Object element, Object value) {
        WebElement webElement = getWebElement(element);
        Select dropDown = new Select(webElement);
        if (value instanceof String) {
            dropDown.selectByValue((String) value);
        }else{
            dropDown.selectByIndex((Integer) value);
        }
    }

    public WebElement getWebElement(Object element){
        if (element instanceof String)
            return getDriver().findElement(By.xpath((String) element));
        else
            return (WebElement) element;
    }
}
