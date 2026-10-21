package cucumber;

import java.io.IOException;

import org.testng.Assert;

import Base.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumproject.pageobjects.CartPage;
import seleniumproject.pageobjects.CheckoutPage;
import seleniumproject.pageobjects.ConfirmationPage;
import seleniumproject.pageobjects.ProductsPage;

public class StepDefinitions extends BaseTest {
	
	ProductsPage productsPage;
	CartPage cartPage;
	ConfirmationPage confirmationPage;

	@Given("Go to application login page")
	public void Go_to_application_login_page() throws IOException
	{
		loginPage = LaunchApplication();
	}
	
	@Given("^Enter an valid username (.+) and password (.+)$")
	public void Enter_an_valid_username_and_password(String username, String password)
	{
		productsPage = loginPage.login(username, password);
	}
	
	@When("^Add the product (.+) to cart$")
	public void Add_the_product_to_cart(String productname) throws InterruptedException
	{
		productsPage.AddToCart(productname);
		cartPage = productsPage.clickOnCart();
	    boolean check = cartPage.cartItemCheck(productname);
		Assert.assertTrue(check);
	}
	
	@And("^Checkout the details for (.+) and submit the order$")
	public void Checkout_the_details_for_productName_and_submit_the_order(String productname)
	{
		CheckoutPage checkoutPage = cartPage.checkout();
		checkoutPage.chooseCountry();
		confirmationPage = checkoutPage.ClickSubmit();
	}
	
	@Then("Check for the message {string} in confirmation page")
	public void Check_message_in_confirmationPage(String string)
	{
		String actualText = confirmationPage.ConfirmText();
		Assert.assertEquals(actualText, (string));
		driver.close();
	}
	
}
