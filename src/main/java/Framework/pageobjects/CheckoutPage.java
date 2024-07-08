package Framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Framework.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent
	{
	//Creating constructor for driver
	   WebDriver driver;
		
		public  CheckoutPage(WebDriver driver)
		 	
		{
			super(driver);
			this.driver= driver;
			PageFactory.initElements(driver, this);
		}
		
		//pagefactory for selecting country(typein name)
		@FindBy(xpath="//input[@placeholder='Select Country']")
		WebElement country;
		
		//pagefactory for selectingCountry
		@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
		WebElement SelectingCountry;
				
		//page factory for placeorder/submit
		@FindBy(xpath="//a[normalize-space()='Place Order']")
		WebElement PlaceOrder;
		
		By results= By.cssSelector(".ta-results");
		
		public void selectCounty(String countryName) throws InterruptedException
		{
			Actions a = new Actions(driver);
			a.sendKeys(country,countryName).build().perform();
			
			//wait
			waitForElementToappear(By.cssSelector(".ta-results"));
			SelectingCountry.click();
		}
		public ConfirmationPage PlaceOrder()
		{
			PlaceOrder.click();
			return new ConfirmationPage(driver);
		}

		
	}


