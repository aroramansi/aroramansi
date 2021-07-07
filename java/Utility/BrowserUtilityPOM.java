package Utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserUtilityPOM {
	static WebDriver driver;
	
	public static WebDriver createBrowser(String browsername)
	{
		if(browsername.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\mansi.e.arora\\Documents\\Drivers\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browsername.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver","\\src\\test\\resources\\Drivers\\geckodriver.exe");
				driver=new FirefoxDriver();
				driver.manage().window().maximize();
			}
		else if(browsername.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver","\\src\\test\\resources\\Drivers\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			driver.manage().window().maximize();
		}
		else
		{
			System.out.println("Browser not configured yet!!!");
		}
		return driver;
	}
	
	public static void OpenApp(WebDriver driver, String URL, int waitTimeInSeconds)
	{
		driver.manage().timeouts().implicitlyWait(waitTimeInSeconds, TimeUnit.SECONDS);
		driver.get(URL);
	}
	
	public static void closeApp(WebDriver driver)
	{
		driver.quit();
	}
}

