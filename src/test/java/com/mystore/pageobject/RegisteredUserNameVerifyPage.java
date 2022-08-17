package com.mystore.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisteredUserNameVerifyPage {
	
WebDriver ldriver;
	
	public RegisteredUserNameVerifyPage(WebDriver rdriver){
		ldriver = rdriver;
		
		PageFactory.initElements(rdriver, this);		
	}
	
	@FindBy(xpath="//a[@title='View my customer account']")
	WebElement username;
	
	@FindBy(xpath="//a[@title='Log me out']")
	WebElement logoutBtn;
	
	@FindBy(name ="search_query")
	WebElement searchBox;

	@FindBy(name ="submit_search")
	WebElement submit_search;

	@FindBy(linkText = "Women")
	WebElement WomenMenu;

	@FindBy(linkText="T-shirts")
	WebElement TShirtMenu;
	
	public String getUsername(){
		
		String text = username.getText();
		
		return text;
	}
	
	public void clickOnLogoutBtn(){
		
		logoutBtn.click(); 
	}
	
	public void EnterDataInSearchBox(String searchKey)
	{
		searchBox.sendKeys(searchKey);
	}

	public void ClickOnSearchButton()
	{
		submit_search.click();

	}

	
	public void MouseOverTShirtMenu()
	{
		Actions actions=new Actions(ldriver);
		actions.moveToElement(WomenMenu).moveToElement(TShirtMenu).click().perform();
	}

}
