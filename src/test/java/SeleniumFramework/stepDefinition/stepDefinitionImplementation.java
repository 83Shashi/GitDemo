package SeleniumFramework.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Framework.pageobjects.CartPage;
import Framework.pageobjects.CheckoutPage;
import Framework.pageobjects.ConfirmationPage;
import Framework.pageobjects.LandingPage;
import Framework.pageobjects.ProductCatalogue;
import SeleniumFramework.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class stepDefinitionImplementation extends BaseTest{
	
	public LandingPage  landingpage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	
	{
		landingpage = launchApplication();
		//code
	}
	
		
	//the keywords <name> and <password> is replaced with (.+) where this indicates to accept anything from name and password: call values dynamically

	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password)
	{
		 productCatalogue = landingpage.loginApplication(username, password);
	}
	

	 @When("^I add product (.+) to cart$")
	 public void I_add_product_to_cart(String productName) throws InterruptedException
	   {
		 List<WebElement> product = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
	   }
	 
	 
	 //@And can also be in the place in @When here
	 @When("^checkout product (.+)  and submit the order$")
	 public void checkout_submit_order(String  productName) throws InterruptedException
	 {
		    CartPage cartPage =  productCatalogue.goToCArtPage();
			Boolean match = cartPage.verifyProductDosplay(productName);
			cartPage.goToCheckout();
			Assert.assertTrue(match);
			CheckoutPage checkoutPage = new CheckoutPage(driver);
			checkoutPage.selectCounty("Ind");
			confirmationPage =checkoutPage.PlaceOrder();
	 }
	 
	 
	 //For hardcoded data like below {string} is used
	// Then "THANKYOU FOR THE ORDER." is displayed on the confirmation page
	 @Then("{string} message is displayed on the confirmation page")
	 public void message_displayed_on_confirmationPage(String string)
	 {
		 String confirmMessage = confirmationPage.verifyConfirmationMessage();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		 driver.close();
	 }
	 
	 @Then("{string} message is diaplayed")
	 public void error_message_is_displayed(String string) throws Throwable
	 {
		 Assert.assertEquals(string,landingpage.getErrorMessage());
		 driver.close();
	 }
	 
	 
}
