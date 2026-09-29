package seleniumproject;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseTest;
import seleniumproject.pageobjects.ProductsPage;
import seleniumproject.pageobjects.CartPage;
import seleniumproject.pageobjects.CheckoutPage;
import seleniumproject.pageobjects.ConfirmationPage;
import seleniumproject.pageobjects.LoginPage;
import seleniumproject.pageobjects.OrdersHistoryPage;
import seleniumproject.utils.DriverWaits;

public class FullProject extends BaseTest{
	String productToBuy;
	 @Test(dataProvider = "getData")
	 public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	 {
		productToBuy = input.get("product");
		ProductsPage productsPage = loginPage.login(input.get("email"), input.get("password"));
		
	//	Assert.assertEquals(productsPage.LoginSuccessPopup().getText(), "Login Successfully");
	
		productsPage.AddToCart(productToBuy);
		CartPage cartPage = productsPage.clickOnCart();
		
		
	    boolean check = cartPage.cartItemCheck(productToBuy);
		Assert.assertTrue(check);
		CheckoutPage checkoutPage = cartPage.checkout();
		
		
		checkoutPage.chooseCountry();
		ConfirmationPage confirmationPage = checkoutPage.ClickSubmit();
		

		String actualText = confirmationPage.ConfirmText();
		Assert.assertEquals(actualText, "THANKYOU FOR THE ORDER.");
		
	 }
	 
	 
	 
	 @Test(dependsOnMethods = {"SubmitOrder"})
	 public void VerifyOrder()
	 {
		 productToBuy = "IPHONE 13 PRO";
		 ProductsPage productsPage = loginPage.login("webauto@gmail.com", "Password@123");
		 OrdersHistoryPage ordersHistoryPage = new OrdersHistoryPage(driver);
		 boolean result = ordersHistoryPage.OrdersItemCheck(productToBuy);
		 Assert.assertTrue(result);
	 }




	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "\\src\\test\\java\\Data\\TestData.json");
 		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
		
		
		
	// sending through hashmap
		/*	HashMap<String,String> map = new HashMap<String,String>();
			map.put("email", "webauto@gmail.com");
			map.put("password", "Password@123");
			map.put("product", "IPHONE 13 PRO");
			
			HashMap<String,String> map1 = new HashMap<String,String>();
			map1.put("email", "webauto@gmail.com");
			map1.put("password", "Password@123");
			map1.put("product", "ZARA COAT 3");
			
			return new Object[][] {{map}, {map1}};
			
	//directly sending data 
			return new String[][]{
				{"webauto@gmail.com", "Password@123", "IPHONE 13 PRO"}, 
				{"webauto@gmail.com", "Password@123", "ZARA COAT 3"}
			};
		*/		
	
}