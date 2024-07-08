package SeleniumFramework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Framework.resources.ExtentReportNG;


public class Listners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent= ExtentReportNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest= new ThreadLocal<ExtentTest>();//This helps to riun test in parallel for each test having its own concurrency thread
	
	
	@Override
	public void onTestStart(ITestResult result)
	{
		//provide test case name here
		 test = extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);//unique thread id is assigned here(ErrorValidationTest-->test)
	}
	
	@Override
	public void onTestSuccess(ITestResult result)
	{
		//provide test case name here
		extentTest.get().log(Status.PASS, "This test is passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result)
	{	
		//log status
		extentTest.get().log(Status.FAIL, "This test is failed");
		
		//
		//log what is the error message of the failed to test
		extentTest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		//taking screenshot for the failed test and attaching to the report
		String filePath = null;
		try 
		{
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName() );
		
		}
	@Override
	public void onTestSkipped(ITestResult result)
	{
		
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		
	}
	@Override
	public void onStart(ITestContext  context)
	{
		
	}
	@Override
	public void onFinish(ITestContext  context)
	{
		//code to generate final report
		extent.flush();
	}
	
	
	
	
	
	

}
