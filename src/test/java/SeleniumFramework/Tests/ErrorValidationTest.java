package SeleniumFramework.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import Framework.pageobjects.CartPage;
import Framework.pageobjects.CheckoutPage;
import Framework.pageobjects.ConfirmationPage;
import Framework.pageobjects.LandingPage;
import Framework.pageobjects.ProductCatalogue;
import SeleniumFramework.TestComponents.BaseTest;
import SeleniumFramework.TestComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTest 
{

	@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException 
	{
	//String productName = "ZARA COAT 3";
	//ProductCatalogue productCatalogue =
	landingpage.loginApplication("shashikhmouli@gmail.com", "Samkumar1418");
	landingpage.getErrorMessage();
	Assert.assertEquals("Incorrect email ", landingpage.getErrorMessage());
	}
	
	@Test
		public void productErrorValidation() throws IOException, InterruptedException 
		{
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingpage.loginApplication("shashikhmouli@gmail.com", "@Samkumar1418");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage =  productCatalogue.goToCArtPage();
		Boolean match = cartPage.verifyProductDosplay("ZARA COAT 3");
		Assert.assertTrue(match);
				
		}

	}


