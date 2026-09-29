package seleniumproject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumproject.utils.DriverWaits;

public class ProductsPage extends DriverWaits{

		WebDriver driver;
		
		public ProductsPage(WebDriver driver)
		{
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}

		
		@FindBy(css=".mb-3")
		List<WebElement> products;
		
	//	WebElement success =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Successfully')]")));
        @FindBy(xpath="//*[contains(text(), 'Successfully')]")
        WebElement successLoginEle;
        
		@FindBy(css="[routerlink*='cart']")
		WebElement CartIcon;
		
		@FindBy(css=".ng-animating")
		WebElement Loading;
 
        
		By productsBy = By.cssSelector(".mb-3");
		By productName =  By.cssSelector("b");
		By addToCartButton = By.cssSelector(".w-10.rounded");
		By successLogin = By.cssSelector(".toast-bottom-right");
		By addedMessage = By.cssSelector(".toast-container");
		By cartIconAppear = By.cssSelector("[routerlink*='cart']");
		
        public WebElement LoginSuccessPopup()
		{
        	WaitUntilVisibleEle(successLoginEle);
			return successLoginEle;
		}
		
		
		
		public List<WebElement> ProductsList()
		{
			WaitUntilVisible(productsBy);
			return products;
		}
		
		public WebElement productCheck(String productToBuy)
		{
			WebElement prod = ProductsList().stream().filter(product->
			product.findElement(productName).getText().equals(productToBuy)).findFirst().orElse(null);
			return prod;
		}
		
		public void AddToCart(String productToBuy)
		{
	//		WaitUntilDisappear(success);
			WebElement prod = productCheck(productToBuy);
			WaitUntilDisappear(successLogin);
	//		WaitClickable(addToCartButton);
			prod.findElement(addToCartButton).click();
			
		}
		
		public CartPage clickOnCart() throws InterruptedException
		{
			WaitUntilVisible(addedMessage);
			WaitClickable(cartIconAppear);
			CartIcon.click();
			Thread.sleep(2000);
	//		WaitUntilDisappeaByEle(Loading);   // This is taking more time to dissappear intenally
			
			CartPage cartPage = new CartPage(driver);
			return cartPage;
		}
}	

