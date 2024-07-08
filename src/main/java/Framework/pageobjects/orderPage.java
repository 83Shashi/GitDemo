package Framework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Framework.AbstractComponents.AbstractComponent;

public class orderPage extends AbstractComponent{

//	
//	@FindBy(xpath="//h3[normalize-space()='ZARA COAT 3']")
//	private List<WebElement> cartProducts;
//	
	
	@FindBy(xpath="//button[.='Checkout']")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	private List<WebElement> cartProducts;
	
	//Creating constructor for driver
	   WebDriver driver;
		
		public  orderPage(WebDriver driver)
		 	
		{
			super(driver);
			this.driver= driver;
			PageFactory.initElements(driver, this);
		}
		
		public boolean verifyOrdersDisplay(String productName)
		{
			Boolean match = cartProducts.stream().anyMatch(cartProducts-> cartProducts.getText().equalsIgnoreCase(productName));
			return match;
		}
		
		
		
	}


