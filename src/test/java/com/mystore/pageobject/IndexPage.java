package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IndexPage {
	
	// create object of WebDriver
	WebDriver ldriver; // local driver
	
	//Constructor
	public IndexPage(WebDriver rdriver){
		ldriver = rdriver; // Remote driver
		
		PageFactory.initElements(rdriver, this);
	}
	
	// identify webelements
	@FindBy(linkText="Sign in")
	WebElement signIn;
	
	// Identify action on webelement
	public void clickOnSignIn(){
		signIn.click();
	}
	
	public String getPageTitle()
	{
		return(ldriver.getTitle());
	}

}
