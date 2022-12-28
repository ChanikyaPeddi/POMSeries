package com.qa.opercart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// creating  Private By locator : also called as Object repository
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By LoginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");
	private By accountLogoutSucessMsg= By.cssSelector("div#content h1");
	private By demoLocator = By.xpath("DEmo");
	
	// public constructor to initialize driver
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// page actions
	public String getPageTitle() {
		return eleUtil.waitForTitleIs(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIMEOUT);
	}

	public String getPageUrl() {
		return eleUtil.waitForUrlContains(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
	}

	public AccountsPage doLogin(String userName, String pswd) {
		System.out.println("login credentials are : " + userName + " & " + pswd);
		eleUtil.waitForElementVisible(email, Constants.DEFAULT_ELEMENT_TIMEOUT).sendKeys(userName);
		eleUtil.doSendKeys(password, pswd);
		eleUtil.doClick(LoginBtn);
		return new AccountsPage(driver);

	}

	public boolean isForgotPwdExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}

	public boolean registerExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}
	public String getLogoutSuccessMessage() {
		return eleUtil.doGetText(accountLogoutSucessMsg);
	}
	
	public RegisterPage clickOnRegisterLink() {
		if(registerExist()) {
			eleUtil.doClick(registerLink);
		}else {
			System.out.println("register link is not present");
		}
		return new RegisterPage(driver);
	}

}
