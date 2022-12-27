package com.qa.opencart.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;

public class AccountsTestPage extends BaseTest {
	
	Map<String,String> actProductInfoMap;

	@BeforeClass
	public void accountsPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void accountsPageTitleTest() {
		String actualTitle = accPage.getAccountsPageTitle();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accountsPageUrlTest() {
		String actualUrl = accPage.getAccountsPageUrl();
		System.out.println(actualUrl);
		Assert.assertTrue(actualUrl.contains(Constants.ACCOUNTS_PAGE_URL_FRACTION));
	}

	@Test
	public void accountspageHeaderTest() {
		Assert.assertTrue(accPage.isHeaderLogoExist());

	}

	@Test
	public void accountPageSectionListTest() {
		List<String> actAccountPageList = accPage.getAccountPageSectionList();
		System.out.println(actAccountPageList);
		Assert.assertEquals(actAccountPageList, Constants.ACCOUNTS_PAGE_EXPECTED_LIST);

	}

	@Test(enabled = false)
	public void logoutLinkTest() {
		Assert.assertTrue(accPage.isAcountPageLogoutExist());
	}

	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] { { "Macbook" }, { "Samsung" }, { "Apple" }

		};

	}

	@Test(dataProvider = "getSearchKey")
	public void searchTest(String searchKey) {
		searchResPage = accPage.doSearch(searchKey);
		Assert.assertTrue(searchResPage.getSearchResultsCount() > 0);
	}
	
	// verifying product names
	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] { { "Macbook" , "MacBook Pro"},
			{ "Samsung" , "Samsung SyncMaster 941BW" },
			{ "Apple" ,"Apple Cinema 30\""}

		};

	}
	

	@Test(dataProvider = "getProductName")
	public void doSelectProductTest(String searchKey, String productName) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		String productHeader = productInfoPage.getProductHeader();
		System.out.println(productHeader);
		Assert.assertEquals(productHeader, productName);
	}
	
	// verifying product images 
	@DataProvider
	public Object[][] productImages() {
		return new Object[][] { { "Macbook" , "MacBook Pro",4},
			{ "Samsung" , "Samsung SyncMaster 941BW",1 },
			

		};

	}
	@Test(dataProvider = "productImages")
	public void doVerifyProductImagesTest(String searchKey, String productName, int count) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(),count);
		
	}
	
	@DataProvider
	public Object[][] productInfo() {
		return new Object[][] { { "name", "MacBook Pro"},
			{ "Brand","Apple" },
			{ "Availability","In Stock" },
			{ "price","$2,000.00" },
			

		};

	}
	
	@Test(dataProvider = "productInfo")
	public void doVerifyProductInfoTest(String key, String value) {
		searchResPage = accPage.doSearch("MacBook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		Map<String,String> actProductInfoMap=productInfoPage.getProductInformation();
		softAssert.assertEquals(actProductInfoMap.get(key),(value));
		softAssert.assertEquals(actProductInfoMap.get(key),(value));
		softAssert.assertEquals(actProductInfoMap.get(key),(value));
		softAssert.assertEquals(actProductInfoMap.get(key),(value));
		softAssert.assertAll();
	}
	
	@Test
	// Test to verify innerText
	public void productInnerTextDescTest() {
		
		searchResPage = accPage.doSearch("MacBook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		softAssert.assertEquals( productInfoPage.getProductInnerText(),Constants.DESC_LATEST_INTEL);
		softAssert.assertEquals( productInfoPage.getProductInnerText(),Constants.DESC_CONNECT);
		softAssert.assertEquals( productInfoPage.getProductInnerText(),Constants.DESC_LEADING_GRAPHICS);
		softAssert.assertEquals( productInfoPage.getProductInnerText(),Constants.DESC_NEXT_GEN);
		
	}
	
	
	
	
	
	@DataProvider
	public Object[][] productQuantity() {
		return new Object[][] { { "Macbook" , "MacBook Pro","2"},
	

		};
	}
	@Test (dataProvider = "productQuantity")
	public void addQuantityTest(String searchKey,String productName,String quantity) {
		searchResPage = accPage.doSearch(searchKey);
		productInfoPage = searchResPage.selectProduct(productName);
		productInfoPage.addProductQuantity().sendKeys(quantity);
		String actualMsg =productInfoPage.addToCart();
		softAssert.assertTrue(actualMsg.contains(Constants.PRODUCT_SUCCESS));
		String cartText= productInfoPage.getCartItemText();
		System.out.println(cartText);
		softAssert.assertTrue(cartText.contains(quantity + " item(s)"));
		
		
		}
	@Test 
	public void clickOnBagTest() {
		shoppingBagPage =  productInfoPage.clickOnShoppingCart();
		Assert.assertTrue(shoppingBagPage.shoppingCartLinkExist());
		
		
		}
//------------------------------------------------------------------------------
	//builder pattern 
	@Test(enabled = false)
	public void addtoCartTest() {
		searchResPage = accPage.doSearch("MacBook");
		productInfoPage = searchResPage.selectProduct("MacBook Pro");
		productInfoPage.enterQuantity("2").clickOnAddtoCart();
	}
//--------------------------------------------------------------------------	
	
	
	
	@Test(enabled = false)
	public void zLogoutTest() {
		Assert.assertEquals(accPage.clickOnLogout().getLogoutSuccessMessage(), Constants.LOGOUT_PAGE_TEXT);
	}
	

}
