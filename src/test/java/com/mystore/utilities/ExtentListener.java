package com.mystore.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListener implements ITestListener{
	
	
	ExtentSparkReporter htmlReporter;
	ExtentReports reportes;
	ExtentTest test;
	
	// Create method for Report configuration
	public void configureReport(){
		ReadConfig readConfig = new ReadConfig();
		String timestamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		String reportName = "NewMyStoreProj" + timestamp + ".html";
//	htmlReporter = new ExtentSparkReporter("ExtentReportDemo2.html");
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//Reports//" + reportName);
	reportes = new ExtentReports();
	reportes.attachReporter(htmlReporter);
	
	// Add environments details
	reportes.setSystemInfo("Machine", "testpc1");
	reportes.setSystemInfo("OS", "Windows 11");
	reportes.setSystemInfo("user1", "test1");
	reportes.setSystemInfo("Browser", "chrome");

			// Configuration to change look and feel
			htmlReporter.config().setDocumentTitle("Extent Report Demo");
			htmlReporter.config().setReportName("Test Report");
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
			
	}
	
	// We want to execute configureReport method before all class so we will call this method in onStart()
	
	
	// Invoke before execution of any test cases only once
	public void onStart(ITestContext Result){
		configureReport();
		System.out.println("On start method invoke ...........");
	}
	
	// Invoke after execution of all TCs only once
	public void onFinish(ITestContext Result){		
		System.out.println("On finish method invoke ...........");
		reportes.flush();
	}
	
	// When TC failed then this will invoke
	public void onTestFailure(ITestResult Result){		
		System.out.println("Name of the test method failed : " +Result.getName());
		test= reportes.createTest(Result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the Failed Test Case is : " +Result.getName(), ExtentColor.RED));
		
		String screenShotPath = System.getProperty("user.dir") + "\\Screenshorts\\" + Result.getName() + ".png";
		
		File screenShotFile = new File(screenShotPath);
		
		if(screenShotFile.exists())
		{
			test.fail("Captured Screenshot is below:" + test.addScreenCaptureFromPath(screenShotPath));
			
		}
	}
	
	// When TC skipped then this will invoke
	public void onTestSkipped(ITestResult Result){
		
		System.out.println("Name of the test method skipped : " +Result.getName());
		test= reportes.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of the Skipped Test Case is : " +Result.getName(), ExtentColor.YELLOW));
	}
	
	// When TC started then this will invoke for all test cases e.g. 5
	public void onTestStart(ITestResult Result){
		
		System.out.println("Name of the test method Started : " +Result.getName());
	}
	// When TC Passed then this will invoke 
	public void onTestSuccess(ITestResult Result){
		
		System.out.println("Name of the test method success  : " +Result.getName());
		test= reportes.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Name of the Passed Test Case is : " +Result.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult Result){
		
		// Not implemented only addressed. 
	}

}
