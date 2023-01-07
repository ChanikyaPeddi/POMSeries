package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productDataMap;

	// creating Private By locator : also called as Object repository
	private By productHeaderName = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("div.col-sm-8 img");
	private By productQuantity = By.cssSelector("input#input-quantity");
	private By addToCartBtn = By.cssSelector("button#button-cart");
	private By productSuccess = By.xpath("//div[@class=\"alert alert-success alert-dismissible\"]");
	private By shoppingCartBtn = By
			.xpath("//div[@class='alert alert-success alert-dismissible']/a[text()='shopping cart']");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By cart = By.cssSelector("span#cart-total");

	// public constructor to initialize driver
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getProductHeader() {
		return eleUtil.waitForElementVisible(productHeaderName, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();

	}

	public int getProductImagesCount() {

		return eleUtil.waitForElementsVisible(productImages, Constants.DEFAULT_ELEMENT_TIMEOUT).size();
	}
	// HashMap to store meta and price data
	public Map<String, String> getProductInformation() {

		productDataMap = new HashMap<String, String>();
		productDataMap.put("name", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		productDataMap.forEach((k,v) -> System.out.println(k +":" +v));
		return productDataMap;

	}

	private void getProductMetaData() {
		// meta data
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		System.out.println("Total product meta data size :" + metaDataList.size());
		for (WebElement e : metaDataList) {

			String meta[] = e.getText().split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productDataMap.put(metaKey, metaValue);
		}
	}

	private void getProductPriceData() {
		// price data
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		System.out.println("Total product price size :" +priceList.size());
		String price = priceList.get(0).getText().trim();
		String exTaxPrice = priceList.get(1).getText().trim();
		productDataMap.put("price", price);
		productDataMap.put("extaxprice", exTaxPrice);
	}
	
	public String getProductInnerText() {
		JavascriptExecutor js =(JavascriptExecutor)driver;
		String innerTextDesc = js.executeScript("return document.documentElement.innerText").toString();
		System.out.println(innerTextDesc);
		return innerTextDesc;
	}
	

	public WebElement addProductQuantity() {

		eleUtil.getElement(productQuantity).clear();
		return eleUtil.waitForElementPresence(productQuantity, Constants.DEFAULT_ELEMENT_TIMEOUT);
	}

	public String addToCart() {
		eleUtil.doClick(addToCartBtn);
		return eleUtil.waitForElementPresence(productSuccess, Constants.DEFAULT_ELEMENT_TIMEOUT).getText();

	}
//------------------------------------------------------	
	/**
	 * this is a builder pattern method 
	 * @return 
	 * @return productInfo page 
	 */
	
	public ProductInfoPage enterQuantity(String qnty) {
		eleUtil.doSendKeys(productQuantity, qnty);
		return this;
		
	}
	
	public void clickOnAddtoCart() {
		eleUtil.doClick(addToCartBtn);
	}
	
//-----------------------------------------------------
	public boolean clickOnShoppingCartExist() {
		return eleUtil.waitForElementPresence(shoppingCartBtn, Constants.DEFAULT_ELEMENT_TIMEOUT).isDisplayed();

	}
	
	public String getCartItemText() {
		return eleUtil.doGetText(cart);
	}

	public ShoppingBagPage clickOnShoppingCart() {
		if (clickOnShoppingCartExist()) {
			eleUtil.doClick(shoppingCartBtn);
		}
		return new ShoppingBagPage(driver);
	}
}
