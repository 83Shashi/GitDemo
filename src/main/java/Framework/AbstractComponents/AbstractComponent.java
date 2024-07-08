package Framework.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Framework.pageobjects.CartPage;
import Framework.pageobjects.orderPage;

//This is the place common and reusable code is written
public class AbstractComponent {
		
		WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css=".btn.btn-custom[routerlink='/dashboard/myorders']")
	WebElement OrderHeader;
	
	
	
	public void waitForElementToappear(By findby) throws InterruptedException
	
	{
		//Thread.sleep(1000);
		WebDriverWait wait =  new WebDriverWait(driver,Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findby));

	}
	public void waitForWebElementToappear(WebElement  findby) throws InterruptedException
	
	{
		//Thread.sleep(800);
		WebDriverWait wait =  new WebDriverWait(driver,Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOf(findby));

	}
	public CartPage  goToCArtPage()
	{
		//driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();(written page factory model for this above)
		cart.click();
		CartPage cartPage =  new CartPage(driver);
		return cartPage;
		
	}
	public orderPage  goToOrdersPage()
	{
		//driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();(written page factory model for this above)
		OrderHeader.click();
		orderPage orderpage =  new orderPage(driver);
		return orderpage;
		
	}
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException
	{
		//Thread.sleep(1000);
		WebDriverWait wait =  new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}

}
