package seleniumproject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css=".cartSection h3")
	List<WebElement> cartproducts;
	
//	driver.findElement(By.cssSelector(".subtotal .btn-primary")).click();
	@FindBy(css=".subtotal .btn-primary")
	WebElement checkoutButton;
	
	
	
	public boolean cartItemCheck(String productToBuy)
	{
		boolean check = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productToBuy));
		return check;
	}
	
	public CheckoutPage checkout()
	{
		checkoutButton.click();
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
	
}
