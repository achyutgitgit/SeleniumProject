package seleniumproject.utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverWaits {

	WebDriver driver;
	
	public DriverWaits(WebDriver driver)
	{
		
		this.driver = driver;
	}
	
	
	
//	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	public void WaitUntilVisible(By visibleEle)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//	wait.until(ExpectedConditions.visibilityOf(visibleEle));
	wait.until(ExpectedConditions.visibilityOfElementLocated(visibleEle));
	}
	
	public void WaitUntilDisappear(By invisibleEle)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//	wait.until(ExpectedConditions.invisibilityOf(invisibleEle));
	wait.until(ExpectedConditions.invisibilityOfElementLocated(invisibleEle));
	}
	
	public void WaitUntilDisappeaByEle(WebElement invisibleEle)
	{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	wait.until(ExpectedConditions.invisibilityOf(invisibleEle));
	}
	
	public void WaitClickable(By clickable)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(clickable));
	}
	
	public void WaitUntilVisibleEle(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
}
