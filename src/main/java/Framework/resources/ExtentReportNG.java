package Framework.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject()
	{
		//create on object of class ExtentReports, ExtentSparkReporter each
		
				//setting report path to store/save our report
				String path = System.getProperty("user.dir")+"\\reports\\index.html";
				
				//create an object reporter for class ExtentSparkReporter class as below( this is helper class to create some configurations).
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				reporter.config().setReportName("Web Automation Results");
				reporter.config().setDocumentTitle("Report Test Results");
				
				//create on object of class ExtentReports
				ExtentReports extent = new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "Mouli");
				return extent;
			
	}

}
