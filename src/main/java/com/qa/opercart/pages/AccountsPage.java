package com.qa.opercart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By headerLogo = By.xpath("//img[@title='naveenopencart']");
	private By sectionHeaders = By.xpath("//div[@id='content']/h2");
	private By logoutLink = By.linkText("Logout");
	private By searchBtn = By.cssSelector("input[placeholder='Search']");
	private By searchIcon = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {

		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getAccountsPageTitle() {
		return eleUtil.waitForTitleIs(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIMEOUT);
	}

	public String getAccountsPageUrl() {
		return eleUtil.waitForUrlContains(Constants.ACCOUNTS_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
	}

	public boolean isHeaderLogoExist() {
		return eleUtil.doIsDisplayed(headerLogo);
	}

	public List<String> getAccountPageSectionList() {

		List<WebElement> secHeadersList = eleUtil.getElements(sectionHeaders);
		List<String> secValList = new ArrayList<String>();
		System.out.println(secHeadersList.size());
		for (WebElement e : secHeadersList) {
			String sectionHeadersText = e.getText();
			secValList.add(sectionHeadersText);
			System.out.println(sectionHeadersText);

		}
		return secValList;

	}

	public boolean isAccountsPageSearchExist() {
		return eleUtil.doIsDisplayed(searchBtn);
	}

	public boolean isAcountPageLogoutExist() {

		return eleUtil.waitForElementPresence(logoutLink, Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();
	}
	// to click on logout Link
	public LoginPage clickOnLogout() {
		if (isAcountPageLogoutExist()) {
			eleUtil.doClick(logoutLink);
		}
		return new LoginPage(driver);
	}
	// to search for items
	public SearchResultsPage doSearch(String searchKey) {
		if(isAccountsPageSearchExist()) {
			eleUtil.doSendKeys(searchBtn, searchKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultsPage(driver);
		}
		return null;
	}
	
	
	

}
