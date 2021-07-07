package com.pages;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utility.BrowserUtilityPOM;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class DemoExtentReports{
	
	WebDriver driver;
	ExtentSparkReporter sparkReporter;
	ExtentReports extent;
	ExtentTest logger;
	
	
	@BeforeTest
	  public void startReport() {
		
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
  public void testGoogle() {
	  
	  logger=extent.createTest("Test Method- Open Google");
	  logger.log(Status.INFO, MarkupHelper.createLabel("Trying to open Google in chrome", ExtentColor.BLUE));
	  driver.get("https://www.google.com/");
	  Assert.assertEquals(driver.getTitle(),"Google");
  }
  
  @Test(priority=2)
  public void testMSN() {
	  
	  logger=extent.createTest("Test Method- Open MSN");
	  logger.log(Status.INFO, MarkupHelper.createLabel("Trying to open MSN in chrome", ExtentColor.GREEN));
	  driver.get("https://www.msn.com/en-in");
	  Assert.assertEquals(driver.getTitle(),"MSN India | Breaking News, Entertainment, Latest Videos, Outlook");
  }
  
  @Test(priority=3)
  public void testYahoo() {
	  
	  logger=extent.createTest("Test Method- Open Yahoo");
	  logger.log(Status.INFO, MarkupHelper.createLabel("Trying to open Yahoo in chrome", ExtentColor.ORANGE));
	  driver.get("https://in.yahoo.com/");
	  Assert.assertEquals(driver.getTitle(),"Yahoo India | News, Finance, Cricket, Lifestyle and Entertainment");
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
			
			TakesScreenshot snapShot = (TakesScreenshot) driver;
			File source = snapShot.getScreenshotAs(OutputType.FILE);
			String imagePath = System.getProperty("user.dir") + "/extent-reports/snapshots_" + result.getName() + ".png";
			FileUtils.copyFile(source, new File(imagePath));
			
			// Embedding the screenshot image with the report
			logger.addScreenCaptureFromPath(imagePath,result.getName());
		}
	}

  
  @AfterTest
  public void endReport() {
	  BrowserUtilityPOM.closeApp(driver);
	  extent.flush(); // Save the results to the report
  }

}
