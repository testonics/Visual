package mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import java.net.URL;

public class android {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    MutableCapabilities caps = new MutableCapabilities();

    public void setCapabilities(){
        caps.setCapability("appiumVersion", "1.17.1");
        caps.setCapability("platformVersion", "10");
        caps.setCapability("deviceName", "Samsung.*");
        caps.setCapability("platformName", "android");
        caps.setCapability("noReset", "true");
        caps.setCapability("idleTimeout", "90");
        caps.setCapability("newCommandTimeout", "90");
        caps.setCapability("name", "android test");
        caps.setCapability("app","storage:410cd900-647f-4629-a41d-f61b1be92d12");
    }

    public void setDriver() {

        //Sauncelabs details
        String user = "";
        String accessKey = "";
        String server = "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

        setCapabilities();
        String url = "https://" + user + ":" + accessKey + server;

        RemoteWebDriver androidDriver = null;
        try {
            androidDriver = new AndroidDriver<>(new URL(url), caps);
        }catch (Exception exception){
            Assert.fail("Exception Occurred while setting up android driver\n" + exception);
        }
        driver.set(androidDriver);
    }

    public RemoteWebDriver getDriver(){
        return driver.get();
    }
}
