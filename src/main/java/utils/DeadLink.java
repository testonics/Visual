package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MultiThread.MultiThreadingWithRunnable;
import web.Browser;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeadLink {

    private static final int numberOfThreads = 5;
    private static Map<String,String> links = new ConcurrentHashMap<>();
    private static final String BASE_URL_CHECK= "google.com";
    private static final int urlSizeToCheck = 2;
    private static final String BASE_URL = "https://www.google.com";
    private static final String xpathToURLs = "//*[@href and not(contains(@style,'none')) and not(contains(@type,'hidden'))]";

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();

        getListOfURLs();
        links = setBaseURLCheck(links);
        links = setUrlSizeCheck(links);

        for (int i = 0; i < numberOfThreads; i++) {
            String link = links.keySet().iterator().next();
            Thread thread = new Thread(new MultiThreadingWithRunnable(link));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads){
            thread.join();
        }

        System.out.println("List Of URLs : " + new MultiThreadingWithRunnable().getURLs());
    }


    public static Map<String,String> setUrlSizeCheck(Map<String,String> links){
        Map<String,String> urls = new ConcurrentHashMap<>();
        for (Map.Entry mapElement : links.entrySet()) {
            String link= mapElement.getKey().toString();
            link = link.replace("http://","");
            link = link.replace("https://","");
            if (link.split("/").length>urlSizeToCheck){
                urls.put(mapElement.getKey().toString(),"alive");
            }
        }
        return urls;
    }

    public static Map<String,String> setBaseURLCheck(Map<String,String> links) {
        Map<String, String> urls = new ConcurrentHashMap<>();
        for (Map.Entry mapElement : links.entrySet()) {
            String link = mapElement.getKey().toString();
            if (!"".equals(BASE_URL_CHECK) && link.contains(BASE_URL_CHECK)) {
                urls.put(link, "alive");
            }
        }
        return urls;
    }

    public static void getListOfURLs(){
        WebDriver driver = Browser.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);
        List<WebElement> listOfLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathToURLs)));
        for (WebElement webElement: listOfLinks){
            String url = webElement.getAttribute("href");
            links.put(url,"alive");
        }
        driver.quit();
    }

//    public void convertMapToCSV() throws IOException {
//        Files.createFile(Paths.get(""));
//    }
}
