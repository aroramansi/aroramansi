package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
WebDriver driver;
	
	@FindBy(id="userName")
	private WebElement userTxtBox;
	
	@FindBy(id="password")
	private WebElement passwordTxtBox;
	
	@FindBy(name="Login")
	private WebElement logInButton;
	 
	public LogInPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public String fetchPageTitle()
	{
		return driver.getTitle();
	}
	public void doLogin(String uname, String pwd)
	{
		userTxtBox.clear();
		userTxtBox.sendKeys(uname);
		
		passwordTxtBox.clear();
		passwordTxtBox.sendKeys(pwd);
		
		logInButton.click();
	}
}
