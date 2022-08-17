package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AccountCreationDetailsPage {
	
	WebDriver ldriver;
	
	public AccountCreationDetailsPage(WebDriver rdriver){
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);		
	}
	
	@FindBy(id="id_gender1")
	WebElement titleMRs;
	
	@FindBy(id="customer_firstname")
	WebElement firstName;
	
	@FindBy(id="customer_lastname")
	WebElement lastName;
	
	@FindBy(id="passwd")
	WebElement password;	
	
	@FindBy(id="firstname")
	WebElement AddFirstName;
	
	@FindBy(id="lastname")
	WebElement AddLastName;
	
	@FindBy(id="address1")
	WebElement address;
	
	@FindBy(id="city")
	WebElement cityname;
	
	@FindBy(id="id_state")
	WebElement stateName;
	
	@FindBy(id="postcode")
	WebElement zipCode;
	
	@FindBy(id="id_country")
	WebElement countryName;
	
	@FindBy(id="phone_mobile")
	WebElement phoneNumber;
	
	@FindBy(id="alias")
	WebElement alias;
	
	@FindBy(id="submitAccount")
	WebElement registerBtn;
	
	public void selectTitle(){
		titleMRs.click();
	}
	
	public void enterFirstName(String fname){
		firstName.sendKeys(fname);
	}
	
	public void enterLastName(String lname){
		lastName.sendKeys(lname);
	}
	
	public void enterPassword(String pass){
		password.sendKeys(pass);
	}
	
	public void enterAddFname(String Adfname){
		AddFirstName.sendKeys(Adfname);
	}
	
	public void enterAddLname(String Adlname){
		AddLastName.sendKeys(Adlname);
	}
	public void enterAddress(String Entaddress){
		address.sendKeys(Entaddress);
	}
	
	public void enterCityNamecity(String city){
		cityname.sendKeys(city);
	}
	
	public void selectState(String text){
		Select obj= new Select(stateName);
		obj.selectByVisibleText(text);
	}
	
	public void enterZip(String zip){
		zipCode.sendKeys(zip);
	}
	
	public void selectCountry(String text){
		Select obj1= new Select(countryName);
		obj1.selectByVisibleText(text);
	}
	
	public void enterphoneNumber(String phone){
		phoneNumber.sendKeys(phone);
	}
	
	public void enterAliasName(String aliasName){
		alias.sendKeys(aliasName);
	}
	
	public void clickonRegBtn(){
		
		registerBtn.click();
	}
	
	
	
	

}
