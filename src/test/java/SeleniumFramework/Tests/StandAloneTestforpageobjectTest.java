package SeleniumFramework.Tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.pageobjects.CartPage;
import Framework.pageobjects.CheckoutPage;
import Framework.pageobjects.ConfirmationPage;
import Framework.pageobjects.LandingPage;
import Framework.pageobjects.ProductCatalogue;
import Framework.pageobjects.orderPage;
import SeleniumFramework.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestforpageobjectTest extends BaseTest
{
	//String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void StandAloneTestforpageobject(HashMap<String,String> input) throws IOException, InterruptedException 
	{
	
	ProductCatalogue productCatalogue = landingpage.loginApplication(input.get("email"),input.get("password"));
	List<WebElement> product = productCatalogue.getProductList();
	productCatalogue.addProductToCart(input.get("productName"));
	CartPage cartPage =  productCatalogue.goToCArtPage();
	Boolean match = cartPage.verifyProductDosplay(input.get("productName"));
	cartPage.goToCheckout();
	Assert.assertTrue(match);
	CheckoutPage checkoutPage = new CheckoutPage(driver);
	checkoutPage.selectCounty("Ind");
	ConfirmationPage confirmationPage =checkoutPage.PlaceOrder();
	String confirmMessage = confirmationPage.verifyConfirmationMessage();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
		
	@Test(dependsOnMethods = {"StandAloneTestforpageobject"})
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingpage.loginApplication("shashikhmouli@gmail.com", "@Samkumar1418");
		orderPage orderpage = productCatalogue.goToOrdersPage();
		//Assert.assertTrue(orderpage.verifyOrdersDisplay(productName));
	}
	

	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJasonDataToMap(System.getProperty("user.dir")+"//src//test//java//SeleniumFramework//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	 }
}


//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"shashikhmouli@gmail.com","@Samkumar1418","ZARA COAT 3"},{"Shankar@gmail.com","Samkumar@1814","ADIDAS ORIGINAL"}};
//	}
//			
//		
//	}
//@DataProvider
//public Object[][] getData() throws IOException
//{
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "shashikhmouli@gmail.com");
//	map.put("password", "@Samkumar1418");
//	map.put("productName", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "Shankar@gmail.com");
//	map1.put("password", "Samkumar@1814");
//	map1.put("productName", "ADIDAS ORIGINAL");

