package Framework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{

	//Creating constructor for driver
	   WebDriver driver;
		
		public  LandingPage(WebDriver driver)
		 	
		{
			//Sending driver from child(LandingPage) to parent(AbstractComponent) using super 
			super(driver);
			this.driver= driver;
			PageFactory.initElements(driver, this);
		}
		
		//using PageFactory for userEmail
		
				@FindBy(id="userEmail")
				WebElement userEmails;
				
			//using PageFactory userPassword
				
				@FindBy(id="userPassword")
				WebElement password;
				
			//using PageFactory for login
						
				@FindBy(id="login")
				WebElement loginbutton;
				
				@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
				//@FindBy(xpath="//div[@role='alert']")
				//@FindBy(css="[class*='toast-error']")
				WebElement errorMessage;
				
		public void goTo()
		{
			driver.get("https://rahulshettyacademy.com/client");
		}
		//implementing ActionMetods for page object created 
		public ProductCatalogue loginApplication(String email,String userPassword)
		{
			userEmails.sendKeys(email);
			password.sendKeys(userPassword);
			loginbutton.click();
			ProductCatalogue productCatalogue = new ProductCatalogue(driver);
			return productCatalogue;
		}
		public String getErrorMessage() throws InterruptedException
		{
			waitForWebElementToappear(errorMessage);
			return errorMessage.getText();
			
		}
				
	}


