package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opercart.factory.DriverFactory;
import com.qa.opercart.pages.AccountsPage;
import com.qa.opercart.pages.LoginPage;
import com.qa.opercart.pages.ProductInfoPage;
import com.qa.opercart.pages.RegisterPage;
import com.qa.opercart.pages.SearchResultsPage;
import com.qa.opercart.pages.ShoppingBagPage;

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
