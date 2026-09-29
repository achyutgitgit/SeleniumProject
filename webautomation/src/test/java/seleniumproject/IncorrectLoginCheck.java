package seleniumproject;


import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import seleniumproject.pageobjects.LoginPage;
import seleniumproject.pageobjects.ProductsPage;

public class IncorrectLoginCheck extends BaseTest {
	
	@Test
	public void IncorrectLogin()
	{
		//loginPage.LoadWebsite();
		String incorrectText = loginPage.IncorrectLoginText("email@gmail.com", "password");
		Assert.assertEquals(incorrectText, "ncorrect email or password.");
	}

}
