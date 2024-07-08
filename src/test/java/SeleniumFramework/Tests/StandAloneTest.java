package SeleniumFramework.Tests;

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

import Framework.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
				

	String ProductName = "ZARA COAT 3";
	ChromeDriver driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	driver.get("https://rahulshettyacademy.com/client");
	driver.findElement(By.id("userEmail")).sendKeys("shashikhmouli@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("@Samkumar1418");
	driver.findElement(By.id("login")).click();
	driver.manage().window().maximize();  
	
	//code to wait till the page is loaded
	WebDriverWait wait =  new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".mb-3")));
	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	
	//Java streams to scan for products and to hit addToCart
	WebElement prod =  products.stream().filter(product-> 
	product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	//code to wait for the loader completes its action till the item is added to cart and hit addToCart
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
	//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	
	//validate the if the selected product is added in the cart ant hit checkout button
	List<WebElement> cartProducts = driver.findElements(By.xpath("//h3[normalize-space()='ZARA COAT 3']"));
	//cartProducts.stream().filter(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProductName));
	Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProductName));
	Assert.assertTrue(match);
	driver.findElement(By.xpath("//button[.='Checkout']")).click();
	
	//using actions class to choose a field from the dropdown list
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "Ind").build().perform();
	
	//wait
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
	driver.findElement(By.xpath("//a[normalize-space()='Place Order']")).click();
	String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
			
	
	
	
	
	

	}

}
