package mobile;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import java.net.URL;

public class ios {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
    DesiredCapabilities caps = new DesiredCapabilities();

    public void setCapabilities(){
        caps.setCapability("deviceName", "iPhone.*");
        caps.setCapability("appiumVersion", "1.17.1");
        caps.setCapability("platformVersion", "14.8");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("noReset", "true");
        caps.setCapability("idleTimeout", "90");
        caps.setCapability("newCommandTimeout", "90");
        caps.setCapability("name", "IOS Test");
        caps.setCapability("app","storage:0318d16b-6966-46b7-9e99-d83ad11b6ee3");

        caps.setCapability("waitForQuietness", "false");
        caps.setCapability("waitForQuiescence", "false");
        caps.setCapability("wdaEventloopIdleDelay", "7");
        caps.setCapability("eventLoopIdleDelaySec", "4");
    }

    public void setDriver() {

        //Sauncelabs details
        String user = "";
        String accessKey = "";
        String server = "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

        setCapabilities();
        String url = "https://" + user + ":" + accessKey + server;

        RemoteWebDriver iosDriver = null;
        try {
            iosDriver = new IOSDriver(new URL(url), caps);
        }catch (Exception exception){
            Assert.fail("Exception Occurred while setting up android driver");
        }
        driver.set(iosDriver);
    }

    public RemoteWebDriver getDriver(){
        return driver.get();
    }
}
