package StepDef;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import mobile.android;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v109.emulation.Emulation;
import org.openqa.selenium.devtools.v109.log.Log;
import org.openqa.selenium.devtools.v109.performance.Performance;
import org.openqa.selenium.devtools.v109.performance.model.Metric;
import org.openqa.selenium.remote.RemoteWebDriver;
import web.Browser;

import java.util.List;
import java.util.Optional;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Stepdefs {

    WebDriver driver;

    @Given("^Login and Search Google$")
    public void loginAndSearch() throws Throwable {
        //Creating an object of ChromeDriver
        driver = Browser.getWebDriver();

        driver.get("https://www.google.com");
    }


    @When("^Search On Google$")
    public void searchOnGoogle() throws Throwable {

        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Cheese!");
        element.submit();
        System.out.println("Page title is: " + driver.getTitle());
        driver.quit();
    }


    @When("^Test selenium 4 features$")
    public void selenium4Features() throws Throwable {

        //Relative Locators
        String relativeElementText = driver.findElement(with(By.tagName("div"))
                .below(By.name("q")).toRightOf(By.linkText("हिन्दी"))).getText();
        System.out.println("Relative Locator Text : " + relativeElementText);

        //Switching and opening new window or tab
        //        driver.switchTo().newWindow(WindowType.WINDOW);
        //        driver.switchTo().newWindow(WindowType.TAB);
    }

    @When("^Collect Performance Metrics$")
    public void collectPerformanceMetrics() throws Throwable {

        //Working with Chrome Dev Tool
        driver = Browser.getWebDriver();
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Performance.enable(Optional.empty()));
        List<Metric> metricList = devTools.send(Performance.getMetrics());

        driver.get("https://google.com");
        driver.quit();

        for(Metric m : metricList) {
            System.out.println(m.getName() + " = " + m.getValue());
        }
    }

    @When("^Override Device Mode$")
    public void overideDeviceMode() throws Throwable {

        //Working with Chrome Dev Tool
        driver = Browser.getWebDriver();
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        // iPhone 11 Pro dimensions
        devTools.send(Emulation.setDeviceMetricsOverride(375,
                812,
                50,
                true,
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()));
        driver.get("https://selenium.dev/");
        driver.quit();
    }


    @When("^Listen Call Logs$")
    public void listenCallLogs() throws Throwable {

        //Working with Chrome Dev Tool
        driver = Browser.getWebDriver();
        DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
        devTools.send(Log.enable());
        devTools.addListener(Log.entryAdded(),
                logEntry -> {
                    System.out.println("log: "+logEntry.getText());
                    System.out.println("level: "+logEntry.getLevel());
                });
        driver.get("http://the-internet.herokuapp.com/broken_images");
        // Check the terminal output for the browser console messages.
        driver.quit();

    }

    @Given("^Execute Rest API$")
    public void executeRestAPI(){
        //ToDo
    }

    @Given("^launch mobile app$")
    public void launchMobileApp(){
        android android = new android();
        android.setDriver();
        RemoteWebDriver driver = android.getDriver();
        //        System.out.println("Driver Title : " + driver.getTitle());
    }

}
