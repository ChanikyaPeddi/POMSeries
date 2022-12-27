package com.qa.opercart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telePhone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPaswword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0']");
	private By privacyPolicyBtn = By.xpath("//div[@class='pull-right']/input[@value='1']");
	private By continueBtn = By.xpath("//div[@class='pull-right']/input[@value='Continue']");
	private By registerSuccess = By.xpath("//div[@id='content']/p[text()='Congratulations! Your new account has been successfully created!']");
	private By registerLink = By.linkText("Register");
	private By logoutLink = By.linkText("Logout");
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public Boolean registerUser(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {

		WebElement firstnameEle = eleUtil.waitForElementVisible(this.firstName, Constants.DEFAULT_ELEMENT_TIMEOUT);
		firstnameEle.clear();
		firstnameEle.sendKeys(firstName);
		
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telePhone, telephone);
		
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPaswword, password);

		if (subscribe.equalsIgnoreCase("Yes")) {

			eleUtil.doClick(this.subscribeYes);
		} else {
			eleUtil.doClick(this.subscribeNo);
		}

		eleUtil.doClick(this.privacyPolicyBtn);
		eleUtil.doClick(this.continueBtn);
		
		String successMsg =eleUtil.waitForElementVisible(registerSuccess, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();
		System.out.println(successMsg);
		
		if(successMsg.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}else {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
		}
		return false;
		

	}

}
