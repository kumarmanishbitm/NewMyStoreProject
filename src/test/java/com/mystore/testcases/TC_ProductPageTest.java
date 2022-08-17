package com.mystore.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.mystore.pageobject.IndexPage;
import com.mystore.pageobject.MyAccount;
import com.mystore.pageobject.OrderAddressPage;
import com.mystore.pageobject.OrderConfirmationPage;
import com.mystore.pageobject.OrderPaymentPage;
import com.mystore.pageobject.OrderShippingPage;
import com.mystore.pageobject.OrderSummaryPage;
import com.mystore.pageobject.ProductPage;
import com.mystore.pageobject.RegisteredUserNameVerifyPage;
import com.mystore.pageobject.SearchResultPage;




public class TC_ProductPageTest  extends BaseClass  {

	@Test(enabled=false)
	public void VerifySearchProduct() throws IOException
	{
		String searchKey = "T-shirts";
		logger.info("\n***************TestCase Search Product started*****************"); 

		//Sign in 
		IndexPage indexPage = new IndexPage(driver);
		indexPage.clickOnSignIn();


		//Enter account details- email and password
		MyAccount myAcc = new MyAccount(driver);
		myAcc.enterEmailAddress(emailAddress);

		logger.info("User Email and Password entered.");
		myAcc.enterPassword(password);

		myAcc.clickSignIn();
		logger.info("Sign In link clicked");

		//Enter searchkey in search box
		RegisteredUserNameVerifyPage rnv = new RegisteredUserNameVerifyPage(driver);
		rnv.EnterDataInSearchBox(searchKey);
		rnv.ClickOnSearchButton();

		// Get Name of Searched Product
		SearchResultPage resultPg = new SearchResultPage(driver);

		String SearchResultProductname=resultPg.getSearchResultProductName();


		//Verify that correct Product is displaying after search

		if(SearchResultProductname.contains(searchKey))
		{
			logger.info("Search Product testcase - Passed"); 
			Assert.assertTrue(true);

			rnv.clickOnLogoutBtn();

		}
		else
		{
			logger.info("Search Product testcase - Failed");
			captureScreenShot(driver,"VerifySearchProduct");
			Assert.assertTrue(false);

		} 

		logger.info("***************TestCase Search Product ends*****************"); 

	}


	@Test
	public void VerifyBuyProduct() throws IOException, InterruptedException
	{

		logger.info("\n***************TestCase Buy Product started*****************"); 

		/*	driver.get(url);
		logger.info("Url opened");*/

		//Sign in 
		IndexPage indexPage = new IndexPage(driver);
		indexPage.clickOnSignIn();


		//Enter account details- email and password
		MyAccount myAcc = new MyAccount(driver);
		myAcc.enterEmailAddress(emailAddress);

		logger.info("User Email and Password entered.");
		myAcc.enterPassword(password);

		myAcc.clickSignIn();
		logger.info("Sign In link clicked");

		RegisteredUserNameVerifyPage rnv = new RegisteredUserNameVerifyPage(driver);
		rnv.EnterDataInSearchBox("T-shirts");
		logger.info("T-shirt entered in search box");

		rnv.ClickOnSearchButton();
		logger.info("clicked on search button");
		Thread.sleep(2000);
		// Scroll page
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,300)");
				
		SearchResultPage searchResultPg = new SearchResultPage(driver);
		searchResultPg.overOnTshirtProduct();
		Thread.sleep(2000);
		searchResultPg.ClickOnMoreLink();
		logger.info("Clicked on more button");

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//

		ProductPage prodPg = new ProductPage(driver);
		prodPg.setQuantity("2");
		logger.info("quantity 2 entereed");

		prodPg.setSize("M");
		logger.info("size M entered");

		prodPg.clickOnAddToCart();
		logger.info("Clicked on add to cart");

		prodPg.clickOnProceedToCheckOut();
		logger.info("Clicked on proceed to checkout on product page");


		OrderSummaryPage orderSumPg = new OrderSummaryPage(driver);
		orderSumPg.cickOnProceedToCheckout(); 
		logger.info("Clicked on proceed to checkout on order summary page");

		OrderAddressPage orderAddPg = new OrderAddressPage(driver);
		orderAddPg.cickOnProceedToCheckout();
		logger.info("Clicked on proceed to checkout on order address page");

		OrderShippingPage orderShippingPg = new OrderShippingPage(driver);
		orderShippingPg.selectTermsOfServices();
		logger.info("selecged term of service check box");

		orderShippingPg.cickOnProceedToCheckout();
		logger.info("Clicked on proceed to checkout on order shipping page");

		OrderPaymentPage orderPaymentPg = new OrderPaymentPage(driver);
		logger.info(orderPaymentPg.getPageTitle());

		orderPaymentPg.clickOnPayByCheque();
		logger.info("Clicked on pay by cheque");

		OrderConfirmationPage orderConfirmPg = new OrderConfirmationPage(driver);
		orderConfirmPg.cickOnConfirmOrder();

		logger.info("Clicked on confirmed order");

		String sucessMsg = orderConfirmPg.getOrderSucessMessage();

		//	Assert.assertEquals(sucessMsg, "Your order on My Store is complete.");

		if(sucessMsg.equals("Your order on My Store is complete."))
		{
			logger.info("VerifyBuyProduct - Passed"); 
			Assert.assertTrue(true);

			orderConfirmPg.clickOnSignOut();

		}
		else
		{
			logger.info("VerifyBuyProduct - Failed");
			captureScreenShot(driver,"VerifyBuyProduct");
			Assert.assertTrue(false);

		} 

		logger.info("***************TestCase BuyProduct ends*****************"); 

	}


//	@Test
//	public void verifyAddToWishlistWithoutLogin() throws IOException
//	{
//		logger.info("\n***************TestCase verifyAddToWishlistWithoutLogin started*****************"); 
//
//		indexPage indexPage = new indexPage(driver);
//		indexPage.clickOnTShirtMenu();
//
//		ProductPage prodPage = new ProductPage(driver);
//		prodPage.mouseOverOnTshirtProduct();
//
//		prodPage.clickOnAddToWishList();
//
//		String actualAlertMsg = prodPage.getTextOfAlertForWishList();
//		String expectedAlertMsg = "You must be logged in to manage your wishlist.";
//
//		if(actualAlertMsg.equals(expectedAlertMsg))
//		{
//			logger.info("verifyAddToWishlistWithoutLogin - passed"); 
//			Assert.assertTrue(true);
//		}
//		else
//		{
//			logger.info("verifyAddToWishlistWithoutLogin - failed"); 
//			captureScreenShot(driver,"verifyAddToWishlistWithoutLogin");
//			Assert.assertTrue(false);
//		}
//
//	}
}
