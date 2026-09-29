package seleniumproject.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumproject.utils.DriverWaits;

public class LoginPage extends DriverWaits{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement user = driver.findElement(By.id("userEmail")).sendKeys("webauto@gmail.com");
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	//driver.findElement(By.id("userPassword")).sendKeys("Password@123");
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	//driver.findElement(By.className("login-btn")).click();
	@FindBy(className="login-btn")
	WebElement LoginButton;
	
//	div[aria-label='Incorrect email or password.']
	@FindBy(css ="[class*='toast-message']")
	WebElement incorrectLoginText;
	
	
	
	public void LoadWebsite()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	public ProductsPage login(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		LoginButton.click();
		
		ProductsPage productsPage= new ProductsPage(driver);
		return productsPage;
	}
	
	public String IncorrectLoginText(String email, String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		LoginButton.click();
		WaitUntilVisibleEle(incorrectLoginText);
		String text = incorrectLoginText.getText();
		return text;
	}

}