package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RegisterPage {
     WebDriver driver;

     @FindBy(linkText="New User?Register Here")
     private WebElement registerLink;
     
     @FindBy(id="err")
     private WebElement statusElement;
     
     @FindBy(id="userName")
     private WebElement userName;
     
     @FindBy(id="firstName")
     private WebElement firstName;
     
     @FindBy(id="lastName")
     private WebElement lastName;
     
     @FindBy(id="password")
     private WebElement userPassword;
     
     @FindBy(id="pass_confirmation")
     private WebElement userPasswordConfirmation;
     
     @FindBy(xpath="//input[@value='Female']")
     private WebElement gender;
     
     @FindBy(id="emailAddress")
     private WebElement emailAddress;
     
     @FindBy(id="mobileNumber")
     private WebElement mobileNumber;
     
     @FindBy(id="dob")
     private WebElement dateOfBirth;
     
     @FindBy(id="address")
     private WebElement userAddress;
     
     @FindBy(xpath="//select[@name='securityQuestion']")
     private WebElement secureQuestion;
     
     @FindBy(id="answer")
     private WebElement color;
     
     @FindBy(xpath="//input[@value='Register']")
     private WebElement registerButton;
     
     @FindBy(xpath="//div[@id='errormsg'][4]")
     private WebElement successMessage;
     
     public RegisterPage(WebDriver driver)
 	{
 		this.driver=driver;
 		PageFactory.initElements(driver, this);
 	}
     public void navigateToRegistrationPage()
     {
    	 registerLink.click();
     }
     public String fetchPageTitle()
 	{
 		return driver.getTitle();
 	}
     
    public void userDetails(String uname, String fname, String lname) 
    {
		Assert.assertEquals(statusElement.getText(),"New");
		
    	userName.clear();
    	userName.sendKeys(uname);
    	
    	firstName.clear();
    	firstName.sendKeys(fname);
    	
    	WebDriverWait wait = new WebDriverWait(driver,120);  
		wait.until(ExpectedConditions.textToBePresentInElement(statusElement,"Available"));
		
    	lastName.clear();
    	lastName.sendKeys(lname);
    }
    public void userPwdTxtBox(String pwd, String cpwd)
    {
    	userPassword.clear();
    	userPassword.sendKeys(pwd);
    	
    	userPasswordConfirmation.clear();
    	userPasswordConfirmation.sendKeys(cpwd);
    }
    public void userGender()
    {
    	gender.click();
    }
    public void userContactDetails(String email,String contact)
    {
    	emailAddress.clear();
    	emailAddress.sendKeys(email);
    	
    	mobileNumber.clear();
    	mobileNumber.sendKeys(contact);
    }
    public void userOtherDetails(String dob,String address)
    	{
    	  dateOfBirth.clear();
    	  dateOfBirth.sendKeys(dob);
    	
    	  userAddress.clear();
    	  userAddress.sendKeys(address);
    	
    	}
    	
    public void userSeqQues()
    {
    	secureQuestion.click();
    	Select question = new Select(secureQuestion);
    	question.selectByVisibleText("What is your favour color?");

    }
    public void userSeqAns(String favcolor)
    {

    	color.sendKeys(favcolor);
    }
    public void registerButton()
    {
    	registerButton.click();
    }
    public String userRegisteredSuccess()
    {
    	String msg="User Registered Succesfully!!! Please login";
    	return msg;
    }
    	
    }

