package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;
import com.qa.opencart.pages.ShoppingBagPage;

public class BaseTest {
	public DriverFactory df;
	public Properties prop;
	
	public WebDriver driver;	
	
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected SearchResultsPage  searchResPage;
	protected ProductInfoPage productInfoPage;
	protected ShoppingBagPage shoppingBagPage;
	protected RegisterPage registerPage;
	
	public SoftAssert softAssert;
	
	@BeforeTest
	public void setUp() {
		df =new DriverFactory();
		prop =df.init_prop();
		driver =df.init_driver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
}
