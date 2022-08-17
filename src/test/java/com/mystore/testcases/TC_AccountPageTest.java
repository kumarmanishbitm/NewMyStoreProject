package com.mystore.testcases;
import java.io.IOException;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.mystore.pageobject.AccountCreationDetailsPage;
import com.mystore.pageobject.IndexPage;
import com.mystore.pageobject.MyAccount;
import com.mystore.pageobject.RegisteredUserNameVerifyPage;

public class TC_AccountPageTest extends BaseClass{
	
	@Test(enabled=false)
	public void verifyRegistrationAndLogin(){
		
		IndexPage indexPage = new IndexPage(driver);
		indexPage.clickOnSignIn();
		
		MyAccount myAcc = new MyAccount(driver);
		myAcc.enterCreateEmailAddress("testacc101@gmail.com");
		myAcc.clickSubmitCreate();
		
		logger.info("Clicked on Create an account button successfully.");
		
		AccountCreationDetailsPage acd =  new AccountCreationDetailsPage(driver);
		
		acd.selectTitle();
		acd.enterFirstName("Mk");
		acd.enterLastName("kumar");
		acd.enterPassword("mk_123");
		acd.enterAddFname("AddMK");
		acd.enterAddLname("AddKumar");
		acd.enterAddress("18/8 park street");
		acd.enterCityNamecity("Delhi");
		acd.selectState("Alabama");
		acd.enterZip("00000");
		acd.selectCountry("United States");
		acd.enterphoneNumber("9878967656");
		acd.enterAliasName("Home");
		logger.info("Entered all required details for Registration.");
		
		acd.clickonRegBtn();
		
		RegisteredUserNameVerifyPage rnv = new RegisteredUserNameVerifyPage(driver);
		
		String username=	rnv.getUsername();
		
		Assert.assertEquals(username, "Mk kumar");
		
		logger.info("Registration Success");
		
	}
	
	@Test
	public void verifylogin() throws IOException{
		
		IndexPage indexPage = new IndexPage(driver);
		indexPage.clickOnSignIn();
		
		MyAccount myAcc = new MyAccount(driver);
		
		myAcc.enterEmailAddress("testacc101@gmail.com");
		logger.info("Email id entered");
		myAcc.enterPassword("mk_123");
		logger.info("Password entered");
		myAcc.clickSignIn();
		logger.info("Clicked on Login Button");
		// verify username displaying on My Account page
        RegisteredUserNameVerifyPage rnv = new RegisteredUserNameVerifyPage(driver);		
		String vusername=	rnv.getUsername();
		
		if(vusername.equals("Mk kumar"))
		{
			logger.info("VerifyLogin - Passed");
			Assert.assertTrue(true);
		}
		else
		{
			logger.info("VerifyLogin - Failed");
			captureScreenShot(driver,"VerifyLogin");
			Assert.assertTrue(false);

		}
	}	
		@Test
		public void VerifySignOut() throws IOException 
		{

			logger.info("***************TestCase Verify Sign out starts*****************"); 

			IndexPage indexPage = new IndexPage(driver);

			indexPage.clickOnSignIn();
			logger.info("Clicked on sign in link");

			MyAccount myAcc = new MyAccount(driver);

			myAcc.enterEmailAddress("cs923@gmail.com");
			logger.info("Entered email address");

			myAcc.enterPassword("cs923");
			logger.info("Entered password");

			myAcc.clickSignIn();
			logger.info("Clicked on sign in link..");


			RegisteredUserNameVerifyPage rnv = new RegisteredUserNameVerifyPage(driver);
			rnv.clickOnLogoutBtn();

			if(indexPage.getPageTitle().equals("Login - My Store"))
			{
				logger.info("VerifySignOut - Passed");
				Assert.assertTrue(true);
			}

			else
			{
				logger.info("VerifySignOut - Failed");
				captureScreenShot(driver,"VerifySignOut");
				Assert.assertTrue(false);
			}

		
			logger.info("***************TestCase Verify Sign out ends*****************"); 

		}
	
	}
