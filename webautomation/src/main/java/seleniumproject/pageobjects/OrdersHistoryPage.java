package seleniumproject.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersHistoryPage {
	
	WebDriver driver;
	public OrdersHistoryPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css="[routerlink='/dashboard/myorders']")
	WebElement OrdersIcon;
	
	@FindBy(xpath="//tr//td[2]")
	List<WebElement> OrdersList;
	
	
	public List<WebElement> OrdersList()
	{
		return OrdersList;
	}
	
	public Boolean OrdersItemCheck(String productToBuy)
	{
		OrdersIcon.click();
		Boolean prod = OrdersList.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productToBuy));
		return prod;
	}

}
