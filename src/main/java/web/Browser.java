package web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class Browser {
	
	public static WebDriver getWebDriver() {

		WebDriver driver;

		//Setting up this is not required anymore with Webdrivermanager
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\drivers\\chromedriver_108.exe");

		String browser = System.getProperty("browser");
		if (browser == null) browser = "edge";

		System.out.println("Browser To be Launched : " + browser);
		switch (browser) {
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "chrome":
				// Set up the wWebDriverManager for chrome driver
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			default:
				throw new RuntimeException("Unrecognized system property 'browser': " + browser);
		}

		driver.manage().window().maximize();

		//Deleting all the cookies
		driver.manage().deleteAllCookies();

		//Specifiying pageLoadTimeout and Implicit wait
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return driver;
	}
}
