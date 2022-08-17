package com.mystore.testcases;




import java.io.IOException;
import java.util.logging.Logger;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mystore.pageobject.AccountCreationDetailsPage;
import com.mystore.pageobject.IndexPage;
import com.mystore.pageobject.MyAccount;
import com.mystore.pageobject.RegisteredUserNameVerifyPage;
import com.mystore.utilities.ReadExcelFile;

public class TC_AccountPageTestDatadrivenTesting extends BaseClass{
	
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
	
	@Test(dataProvider = "LoginDataProvider")
	public void verifylogin(String userEmail, String userPwd, String expectedUsername) throws IOException{
		
		IndexPage indexPage = new IndexPage(driver);
		indexPage.clickOnSignIn();
		
		MyAccount myAcc = new MyAccount(driver);
		
		myAcc.enterEmailAddress(userEmail);
		logger.info("Email id entered");
		myAcc.enterPassword(userPwd);
		logger.info("Password entered");
		myAcc.clickSignIn();
		logger.info("Clicked on Login Button");
		// verify username displaying on My Account page
        RegisteredUserNameVerifyPage rnv = new RegisteredUserNameVerifyPage(driver);		
		String vusername=	rnv.getUsername();
		
		if(vusername.equals(expectedUsername))
		{
			logger.info("VerifyLogin - Passed");
			Assert.assertTrue(true);
			rnv.clickOnLogoutBtn();
			logger.info("Clicked on Logout button successfully.");
		}
		else
		{
			logger.info("VerifyLogin - Failed");
			captureScreenShot(driver,"VerifyLogin");
			Assert.assertTrue(false);

		}
		
		logger.info("***************TestCase Verify login ends*****************");		
		
	}
	
	@DataProvider(name = "LoginDataProvider")
	public String[][] LoginDataProvider()
	{
		//System.out.println(System.getProperty("user.dir"));
		String fileName = System.getProperty("user.dir") + "\\TestData\\MyStoreTestData.xlsx";


		int ttlRows = ReadExcelFile.getRowCount(fileName, "LoginTestData");
		int ttlColumns = ReadExcelFile.getColCount(fileName, "LoginTestData");
	

		String data[][]=new String[ttlRows-1][ttlColumns];

		for(int i=1;i<ttlRows;i++)//rows =1,2
		{
			for(int j=0;j<ttlColumns;j++)//col=0, 1,2
			{

				data[i-1][j]=ReadExcelFile.getCellValue(fileName,"LoginTestData", i,j);
			}

		}
		return data;
	}

}
