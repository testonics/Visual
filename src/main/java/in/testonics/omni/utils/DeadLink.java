package in.testonics.omni.utils;

import in.testonics.omni.frameworks.Omni;
import in.testonics.omni.utils.MultiThread.MultiThreadingWithRunnable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DeadLink {

    private int numberOfThreads = 1;
    private static Map<String,String> links = new ConcurrentHashMap<>();
    private static Map<String,String> linksChecked = new ConcurrentHashMap<>();
    private String BASE_URL_CHECK= "";
    private int urlSizeToCheck = 1;
    private String BASE_URL = "";
    private static final String xpathToURLs = "//*[@href]";

    public void setNumberOfThreads(int numberOfThreads){
        this.numberOfThreads = numberOfThreads;
    }

    public void setBaseURL(String baseURL){this.BASE_URL = baseURL;}

    public void setBaseURLCheck(String baseURLCheck){
        this.BASE_URL_CHECK = baseURLCheck;
    }

    public void setUrlSizeToCheck(int urlSizeToCheck){
        this.urlSizeToCheck = urlSizeToCheck;
    }

    Omni omni = new Omni();

    public void findDeadLinks() throws InterruptedException {
        omni.setFramework("selenium");
        omni.setHeadlessMode(true);
        omni.setDriver();
        List<Thread> threads = new ArrayList<>();

        getListOfURLs();
        links = setBaseURLCheck(links);
        links = setUrlSizeCheck(links);

        for (int i = 0; i < numberOfThreads; i++) {
            String link = links.keySet().iterator().next();
            //Ignoring the link if it's already checked
            if (linksChecked.get(link) != null){
                continue;
            }
            Thread thread = new Thread(new MultiThreadingWithRunnable(link));
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads){
            thread.join();
        }

        links = new MultiThreadingWithRunnable().getURLs();
        for (Map.Entry mapElement : links.entrySet()) {
            String link = mapElement.getKey().toString();
            linksChecked.put(link,links.get(link));
        }
        System.out.println("List Of URLs : " + linksChecked);
    }

    private Map<String,String> setUrlSizeCheck(Map<String,String> links){
        Map<String,String> urls = new ConcurrentHashMap<>();
        for (Map.Entry mapElement : links.entrySet()) {
            String link= mapElement.getKey().toString();
            link = link.replace("http://","");
            link = link.replace("https://","");
            if (link.split("/").length>urlSizeToCheck){
                urls.put(mapElement.getKey().toString(),"alive");
            }else{
                linksChecked.put(mapElement.getKey().toString(),"Ignored: Based on URL Size Check");
            }
        }
        return urls;
    }

    private Map<String,String> setBaseURLCheck(Map<String,String> links) {
        Map<String, String> urls = new ConcurrentHashMap<>();
        for (Map.Entry mapElement : links.entrySet()) {
            String link = mapElement.getKey().toString();
            if (!"".equals(BASE_URL_CHECK) && link.contains(BASE_URL_CHECK)) {
                urls.put(link, "alive");
            }else {
                linksChecked.put(mapElement.getKey().toString(),"Ignored: Based on Base URL Check");
            }
        }
        return urls;
    }

    private void getListOfURLs(){
        WebDriver driver = (WebDriver) omni.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);
        List<WebElement> listOfLinks = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathToURLs)));
        for (WebElement webElement: listOfLinks){
            String url = webElement.getAttribute("href");
            links.put(url,"alive");
        }
        driver.quit();
    }

}
