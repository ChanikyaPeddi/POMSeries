package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 10101 - this epic is for login functionality of open cart application")
@Story("Story - 10212 - design login page with various features")
public class LoginPageTest extends BaseTest {

	@Description("Login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginpageTitleTest() {
		String actualTitle = loginPage.getPageTitle();
		System.out.println("Login page title is " + actualTitle);
		Assert.assertEquals(actualTitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Description("Login page url test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginpageUrlTest() {
		String actualUrl = loginPage.getPageUrl();
		System.out.println("Login page title is " + actualUrl);
		Assert.assertTrue(actualUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}

	@Description("Login page forgot password test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdExist());
	}

	@Description("Login page register link test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void registerLinkExistTest() {
		Assert.assertTrue(loginPage.registerExist());
	}

	@Description("Login page Login test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void loginTest() {
		Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim())
				.isAcountPageLogoutExist());
	}
}
