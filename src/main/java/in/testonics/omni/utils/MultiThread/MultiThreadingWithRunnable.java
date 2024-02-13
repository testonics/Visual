/*
    Multithreading is a Java feature that allows concurrent execution of two or more parts of a program for maximum utilization of CPU. Each part of such program is called a thread. So, threads are light-weight processes within a process.

    Threads can be created by using two mechanisms :
    1. Extending the Thread class
    2. Implementing the Runnable Interface

    Thread Class vs Runnable Interface
    1. If we extend the Thread class, our class cannot extend any other class because Java doesn’t support multiple inheritance. But, if we implement the Runnable interface, our class can still extend other base classes.
    2.We can achieve basic functionality of a thread by extending Thread class because it provides some inbuilt methods like yield(), interrupt() etc. that are not available in Runnable interface.
    3.Using runnable will give you an object that can be shared amongst multiple threads.
     */

package in.testonics.omni.utils.MultiThread;

import in.testonics.omni.frameworks.Omni;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MultiThreadingWithRunnable implements Runnable {
    private static Map<String, String> links = new ConcurrentHashMap<>();
    private static String BASE_URL = "";
    private static String xpathToURLs = "//*[@href]";

    public MultiThreadingWithRunnable() {
    }

    public MultiThreadingWithRunnable(String url) {
        BASE_URL = url;
    }

    public void run() {
        // Displaying the thread that is running
        System.out.println("Thread " + Thread.currentThread().getId() + " is running");
        getListOfURLs();
    }

    public static void getListOfURLs() {
        Omni omni = new Omni();
        omni.setFramework("selenium");
        omni.setHeadlessMode(true);
        omni.setDriver();
        WebDriver driver = (WebDriver) omni.getDriver();
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get(BASE_URL);
        List<WebElement> listOfLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathToURLs)));
        for (WebElement webElement : listOfLinks) {
            String url = webElement.getAttribute("href");
            links.put(url, "alive");
        }
        driver.quit();
    }

    public Map<String, String> getURLs() {
        return links;
    }
}
