package StepDef;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import in.testonics.omni.frameworks.Omni;
import org.openqa.selenium.WebDriver;

public class Stepdefs {

    Omni omni = new Omni();
    private static WebDriver driver;

    @Given("^Launch the browser$")
    public void launch() {
        omni.setFramework("selenium");
        omni.setHeadlessMode(true);
        omni.setDriver();
        driver = (WebDriver) omni.getDriver();
    }

    @Given("^Search on Google With Playwright$")
    public void SearchWithPlaywright() throws Throwable {
        omni.setFramework("playwright");
        omni.setHeadlessMode(true);
        omni.setDriver();
        omni.setNavigationUrl("http://www.google.com");
        omni.navigate();
        omni.enter("//*[@name='q']","Cheese!");
        omni.close();
    }

    @Given("^Navigate to the URL$")
    public void navigate() {
        omni.setNavigationUrl("http://www.google.com");
        omni.navigate();
    }

    @When("^Search On Google$")
    public void searchOnGoogle() throws Throwable {
        omni.enter("//*[@name='q']","Cheese!");
        omni.close();
    }

    @Given("^Execute Rest API$")
    public void executeRestAPI(){
        //ToDo
    }

    @Given("^launch mobile android app$")
    public void launchMobileApp() throws InterruptedException {
        omni.setFramework("android");
        omni.setApp("cbbdfe53-e55e-4dcf-a7b1-ba402e243b8d");
        omni.setDriver();
        omni.click("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.RelativeLayout");
        omni.click("//*[@content-desc='ranking']/android.widget.TextView");
        omni.click("//*[@content-desc='Categories']/android.widget.TextView");
        omni.close();
    }



    //    @When("^Test selenium 4 features$")
//    public void selenium4Features() throws Throwable {
    //Relative Locators
//        String relativeElementText = driver.findElement(with(By.tagName("div"))
//                .below(By.name("q")).toRightOf(By.linkText("हिन्दी"))).getText();
//        System.out.println("Relative Locator Text : " + relativeElementText);

//        omni.close();
    //Switching and opening new window or tab
    //        driver.switchTo().newWindow(WindowType.WINDOW);
    //        driver.switchTo().newWindow(WindowType.TAB);
//    }

//    @When("^Collect Performance Metrics$")
//    public void collectPerformanceMetrics() throws Throwable {
//
//        //Working with Chrome Dev Tool
//        DevTools devTools = ((ChromeDriver) driver).getDevTools();
//        devTools.createSession();
//        devTools.send(Performance.enable(Optional.empty()));
//        List<Metric> metricList = devTools.send(Performance.getMetrics());
//
//        omni.navigate();
//        omni.close();
//
//        for(Metric m : metricList) {
//            System.out.println(m.getName() + " = " + m.getValue());
//        }
//    }
//
//    @When("^Override Device Mode$")
//    public void overideDeviceMode() throws Throwable {
//
//        //Working with Chrome Dev Tool
//        driver = (WebDriver) omni.getDriver();
//        DevTools devTools = ((ChromeDriver) driver).getDevTools();
//        devTools.createSession();
//        // iPhone 11 Pro dimensions
//        devTools.send(Emulation.setDeviceMetricsOverride(375,
//                812,
//                50,
//                true,
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty(),
//                Optional.empty()));
//
//        omni.setNavigationUrl("https://selenium.dev/");
//        omni.navigate();
//        omni.close();
//    }
//
//    @When("^Listen Call Logs$")
//    public void listenCallLogs() throws Throwable {
//        //Working with Chrome Dev Tool
//        DevTools devTools = ((ChromeDriver) driver).getDevTools();
//        devTools.createSession();
//        devTools.send(Log.enable());
//        devTools.addListener(Log.entryAdded(),
//                logEntry -> {
//                    System.out.println("log: "+logEntry.getText());
//                    System.out.println("level: "+logEntry.getLevel());
//                });
//
//        // Check the terminal output for the browser console messages.
//        omni.setNavigationUrl("http://the-internet.herokuapp.com/broken_images");
//        omni.navigate();
//        omni.close();
//    }


}
