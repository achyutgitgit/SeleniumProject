package seleniumproject.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumproject.utils.DriverWaits;

public class CheckoutPage extends DriverWaits{
	
	WebDriver driver;
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="[placeholder*='Country']")
	WebElement searchCountry;
	
	@FindBy(css= ".ta-item:last-of-type")
	WebElement selectCountry;
	
//	WebElement submit = driver.findElement(By.xpath("//a[contains(@class, 'submit')]"));
	@FindBy(xpath="//a[contains(@class, 'submit')]")
	WebElement submit;
	
	
	By countryList = By.cssSelector(".ta-results"); 
	
	Actions a;
	public void chooseCountry()
	{
	a = new Actions(driver);
	a.sendKeys(searchCountry, "india").build().perform();
	WaitUntilVisible(countryList);
	selectCountry.click();
	}
	
	public ConfirmationPage ClickSubmit()
	{
		a.scrollToElement(submit).build().perform();
		WaitUntilVisibleEle(submit);
		submit.click();
		
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
		

}
