package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(linkText="SignIn")
	private WebElement signInLink;
	
	@FindBy(linkText="SignUp")
	private WebElement signUpLink;
	
	@FindBy(linkText="SignOut")
	private WebElement signOutLink;
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public String fetchPageTitle()
	{
		return driver.getTitle();
	}
    public void navigateToLoginPage()
    {
    	signInLink.click();
    }
    public void navigateToRegisterPage()
    {
    	signUpLink.click();
    }
    public void navigateToAppHomePage()
    {
    	signOutLink.click();
    }
}
