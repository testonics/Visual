package in.testonics.omni.frameworks;

import in.testonics.omni.Interface.Omni;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;

public class Selenium implements Omni {

    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String Url = "";
    private static String browser = "CHROME";
    private static boolean headlessMode = true;

    @Override
    public void setNavigationUrl(String Url){
        this.Url = Url;
        System.out.println("URL to be navigated : " + this.Url);
    }

    @Override
    public void setHeadlessMode(boolean headlessMode){
        this.headlessMode = headlessMode;
    }

    @Override
    public void setBrowser(String browser){
        this.browser = browser.toUpperCase();
    }

    @Override
    public void setDriver(){
        //Setting up this is not required anymore with Webdrivermanager
        //	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver_108.exe");
        WebDriver webDriver;
        System.out.println("Browser To be Launched : " + browser);
        switch (browser) {
            case "CHROME":
                // Set up the wWebDriverManager for chrome driver
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headlessMode){
                    chromeOptions.addArguments("--headless");
                }
                webDriver = new ChromeDriver(chromeOptions);
                break;
            case "EDGE":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headlessMode){
//                    edgeOptions.addArguments("--headless");
                }
                webDriver = new EdgeDriver(edgeOptions);
                break;
            default:
                throw new RuntimeException("Unrecognized system property 'browser': " + browser);
        }

        webDriver.manage().window().maximize();

        //Deleting all the cookies
        webDriver.manage().deleteAllCookies();
        driver.set(webDriver);
    }

    public WebDriver getDriver(){
        return driver.get();
    }

    @Override
    public void closeDriver() {
        driver.get().close();
        driver.get().quit();
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
            Thread.sleep(2000);
        }catch (Exception e){
            System.out.println("Exception while killing Chromedriver process "  + e);
        }
    }

    public void navigate(){
        driver.get().get(Url);
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
            return driver.get().findElement(By.xpath((String) element));
        else
            return (WebElement) element;
    }
}
