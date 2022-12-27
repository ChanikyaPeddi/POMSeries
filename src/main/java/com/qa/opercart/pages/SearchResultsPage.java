package com.qa.opercart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchHeader = By.cssSelector("div#content h1");
	private By searchResults = By.cssSelector("div.product-layout.product-grid");
	private By selectProductName;
	

	// public constructor to initialize driver
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}
	
	public int getSearchResultsCount() {
		return eleUtil.waitForElementsVisible(searchResults, Constants.DEFAULT_ELEMENT_TIMEOUT).size();
	}
	

	public ProductInfoPage selectProduct(String productName) {
		selectProductName = By.linkText(productName);
		eleUtil.doClick(selectProductName);
		return new ProductInfoPage(driver);
		
	}
	
	
	
}
