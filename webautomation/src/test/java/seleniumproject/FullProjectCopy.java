package seleniumproject;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FullProjectCopy {
	public static void main(String[] args)
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("webauto@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@123");
		driver.findElement(By.className("login-btn")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement success =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Successfully')]")));
		Assert.assertEquals(success.getText(), "Login Successfully");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);
		wait.until(ExpectedConditions.invisibilityOf(success));
		prod.findElement(By.cssSelector(".w-10.rounded")).click();
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean check = cartproducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase("IPHONE 13 PRO"));
		Assert.assertTrue(check);
		
		driver.findElement(By.cssSelector(".subtotal .btn-primary")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder*='Country'] ")), "india").build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.cssSelector(".ta-item:last-of-type")).click();
		
		WebElement submit = driver.findElement(By.xpath("//a[contains(@class, 'submit')]"));
		a.scrollToElement(submit).build().perform();
		wait.until(ExpectedConditions.visibilityOf(submit));
		submit.click();
		
		String confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(confirmation, "THANKYOU FOR THE ORDER.");
		
		driver.quit();
	}

}
