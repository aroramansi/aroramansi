package Utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CaptureScreenshotsUtility {
	
	public static void captureScreenshot(WebDriver driver, String name)
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String path=System.getProperty("user.dir")+ "/screenshots" 
		+ new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + "_" +name +".png";
		
		File dest =new File(path);
		
		try
		{
			FileUtils.copyFile(src, dest);
		}
		catch(IOException e)
		{
			System.out.println("File Not Found!!!");
		}
	}
	
	public static void uploadFile(String path)
	{
		StringSelection stringSelection=new StringSelection(path);
		Clipboard clipboard= Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		Robot robot=null;
		try {
			robot=new Robot();
		}
		catch(AWTException e)
		{
			e.printStackTrace();
		}
		robot.delay(300);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(200);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
	}
}
