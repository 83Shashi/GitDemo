package SeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Framework.pageobjects.LandingPage;

public class BaseTest
{
	
	public WebDriver driver;
	public LandingPage landingpage;
	

	public WebDriver initializedriver() throws IOException
	{
		//properties class
		
	Properties prop = new Properties();
	FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Framework//resources//GlobalData.properties");
	prop.load(fis);
	
	//java ternary operator to decide on which browser to run the application
	String browserName =System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
	//prop.getProperty("browser");
	
	if(browserName.equalsIgnoreCase("chrome"))
			{
			 driver = new ChromeDriver();
			}
	else if (browserName.equalsIgnoreCase("fireforx"))

		{
		 driver = new FirefoxDriver();
		}
	else if (browserName.equalsIgnoreCase("edge"))
		{
		 driver = new EdgeDriver();
		}
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.manage().window().maximize(); 
	return driver;
}
	public List<HashMap<String, String>> getJasonDataToMap(String filePath) throws IOException
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//convert string to hashMap using dependency jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>()
				{});
		return data;
	}
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source= ts.getScreenshotAs(OutputType.FILE);
		File destfile = new File(System.getProperty("user.dir")+ "//reports" + testCaseName + ".png");
		FileUtils.copyFile(source, destfile);
		return System.getProperty("user.dir")+ "//reports" + testCaseName + ".png";
		
	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializedriver();
		landingpage = new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.quit();
	}
}
