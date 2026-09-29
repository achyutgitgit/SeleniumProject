package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import seleniumproject.pageobjects.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	public LoginPage loginPage;
	
	public WebDriver InitilizeDriver() throws IOException
	{
	//	WebDriver driver = new ChromeDriver();
	//	driver.manage().window().maximize();
	//	driver.get("https://rahulshettyacademy.com/client");
	//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\global.properties");
		prop.load(fis);
		//String browserName = prop.getProperty("browser");
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1920,1080));
		}
		
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
		
		}
		
	
	
	@BeforeMethod
	public LoginPage LaunchApplication() throws IOException
	{
		InitilizeDriver();
		loginPage = new LoginPage(driver);
		loginPage.LoadWebsite();
		return loginPage;
	}
	
	@AfterMethod
	public void ClosingBrowser()
	{
		driver.quit();
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException
	{
		//READ JSON TO STRING
		String jsonContent = FileUtils.readFileToString(new File(FilePath) ,StandardCharsets.UTF_8);
		
		//String to HashMap using jackson DataBind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	}
	

}
