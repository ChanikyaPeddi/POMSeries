package com.qa.opencart.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ElementUtil;

public class ShoppingBagPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By shoppingCartBtnHeader = By.xpath("//div[@id='checkout-cart']//a[text()='Shopping Cart']");
	private By productquantity = By.xpath("(//div[@class= 'table-responsive'] //input[@class='form-control'])[1]");

	public ShoppingBagPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getPageTitle() {
		return eleUtil.waitForTitleIs(Constants.SHOPPINGCART_PAGE_TITLE, Constants.DEFAULT_TIMEOUT);
	}

	public String getPageUrl() {
		return eleUtil.waitForUrlContains(Constants.SHOPPINGCART_PAGE_URL_FRACTION, Constants.DEFAULT_TIMEOUT);
	}

	public boolean shoppingCartLinkExist() {
		
	return	eleUtil.waitForElementPresence(shoppingCartBtnHeader,Constants.DEFAULT_ELEMENT_TIMEOUT ).isDisplayed();
	
	}
	public void verifyQuantity() {
		WebElement quan = eleUtil.getElement(productquantity);
		System.out.println(quan.getAttribute("value"));
	}
	
	
}
