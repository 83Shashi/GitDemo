package Framework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Framework.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

	//Creating constructor for driver
	   WebDriver driver;
		
		public  ProductCatalogue(WebDriver driver)
		 	
		{
			super(driver);
			this.driver= driver;
			PageFactory.initElements(driver, this);
		}
		
					
	//using PageFactory for products
//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		@FindBy(css=".mb-3")
		List<WebElement> products;
		
		@FindBy(css=".ng-animating")
		WebElement spinner;
		
		By products1= By.cssSelector(".mb-3");
		By addToCart = By.cssSelector(".card-body button:last-of-type");
		By toastMessage= By.cssSelector("#toast-container");
		
	//Actions method for product	
		
		public List<WebElement> getProductList() throws InterruptedException
		{
			waitForElementToappear(products1);
			return products;
		}
		
		public WebElement getProductsByName(String productName) throws InterruptedException
		{
			WebElement prod =  getProductList().stream().filter(product-> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
			return prod;
		}
		
		public void addProductToCart(String productName) throws InterruptedException
		{
			WebElement prod = getProductsByName(productName);
			prod.findElement(addToCart).click();
			waitForElementToappear(toastMessage);
			waitForElementToDisappear(spinner);
		}

	
		
	
		
	}


