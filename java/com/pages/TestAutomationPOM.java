package com.pages;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utility.BrowserUtilityPOM;

public class TestAutomationPOM {
	WebDriver driver;
	ExtentSparkReporter sparkReporter;
	ExtentReports extent;
	ExtentTest logger;
	
	HomePage hp;
	LogInPage lp;
	RegisterPage rp;
	
	@BeforeTest
	public void setUp()
	{
		 driver=BrowserUtilityPOM.createBrowser("chrome");
		 BrowserUtilityPOM.OpenApp(driver, "https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm", 60);
		 
		 String reportPath=System.getProperty("user.dir") + "/extent-reports/" + 
					new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss-ms").format(new Date()) + ".html";
							
					sparkReporter = new ExtentSparkReporter(reportPath);
					sparkReporter.config().setDocumentTitle("Sample Test  Report");
					sparkReporter.config().setReportName("Extent Report");
					sparkReporter.config().setTheme(Theme.DARK);
					
					extent= new ExtentReports();
					extent.attachReporter(sparkReporter);
					extent.setSystemInfo("Project", "ExtentReportValidation");
					extent.setSystemInfo("Location", "NewYork");
					extent.setSystemInfo("TesterId", "William Willson");
		  
	}
	@Test(priority=1)
	  public void testSignUp() throws InterruptedException {
		
		logger=extent.createTest("Test Method- Open and SignUp to TestMeApp");
		logger.log(Status.INFO, MarkupHelper.createLabel("User trying to open and SignUp to TestMeApp in chrome", ExtentColor.BLUE));
		  
		hp=new HomePage(driver);
		Assert.assertEquals(hp.fetchPageTitle(), "Home");
		hp.navigateToRegisterPage();
		Thread.sleep(2000);
		Assert.assertEquals(hp.fetchPageTitle(), "Sign Up");
	}
	@Test(priority=2)
	  public void testRegister() throws InterruptedException {
		
		logger=extent.createTest("Test Method- Register to TestMeApp");
		logger.log(Status.INFO, MarkupHelper.createLabel("Trying to register to TestMeApp", ExtentColor.GREEN));
		  
		rp=new RegisterPage(driver);
		Thread.sleep(2000);
		rp.userDetails("Arora2027","Mansi","Arora");
		rp.userPwdTxtBox("pass1234","pass1234");
		rp.userGender();
		Thread.sleep(2000);
		rp.userContactDetails("abc@gmail.com", "6787564598");
		rp.userOtherDetails("04/05/1992","NewYork123NY FirstFloor");
		
		rp.userSeqQues();
		rp.userSeqAns("Red");
		Thread.sleep(3000);
		rp.registerButton();
		Assert.assertEquals(rp.userRegisteredSuccess(), "User Registered Succesfully!!! Please login");
	}
	
  @Test(priority=3)
  public void testLogIn() throws InterruptedException {
	  
	  logger=extent.createTest("Test Method- User Logs In with registered credentials");
	  logger.log(Status.INFO, MarkupHelper.createLabel("Logs in with registered credentials", ExtentColor.ORANGE));
	  
	  lp=new LogInPage(driver);
	  Assert.assertEquals(lp.fetchPageTitle(), "Login");
	  lp.doLogin("Arora2027", "pass1234");
	  Thread.sleep(2000);
  }
  @Test(priority=4)
  public void testLogOut() throws InterruptedException {
	  
	  logger=extent.createTest("Test Method- User Logs out");
	  logger.log(Status.INFO, MarkupHelper.createLabel("Logs Out from the TestMeApp", ExtentColor.TEAL));
	  
	  Assert.assertEquals(hp.fetchPageTitle(), "Home");
	  hp=new HomePage(driver);
	  Thread.sleep(2000);
	  hp.navigateToAppHomePage();
	  Thread.sleep(2000);
	  Assert.assertEquals(hp.fetchPageTitle(), "Home");
  }
  
  @AfterMethod
  public void getResults(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.SUCCESS)
			logger.log(Status.PASS, MarkupHelper.createLabel("Test Passed:" + result.getName(), ExtentColor.GREEN));
		
		else if(result.getStatus() == ITestResult.SKIP)
			logger.log(Status.SKIP, MarkupHelper.createLabel("Test Skipped:" + result.getName(), ExtentColor.ORANGE));
		
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.log(Status.FAIL, MarkupHelper.createLabel("Test Failed:" + result.getName(), ExtentColor.RED));
		}
		
		TakesScreenshot snapShot = (TakesScreenshot) driver;
		File source = snapShot.getScreenshotAs(OutputType.FILE);
		String imagePath = System.getProperty("user.dir") + "/extent-reports/snapshots_" + result.getName() + ".png";
		FileUtils.copyFile(source, new File(imagePath));
		
		// Embedding the screenshot image with the report
		logger.addScreenCaptureFromPath(imagePath,result.getName());
	}
  
  @AfterTest
  public void tearDown()
  {
	  BrowserUtilityPOM.closeApp(driver);
	  extent.flush();
  }
}
