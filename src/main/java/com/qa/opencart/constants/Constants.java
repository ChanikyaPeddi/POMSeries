package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public final static String LOGIN_PAGE_TITLE ="Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	
	public final static String ACCOUNTS_PAGE_TITLE ="My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION = "route=account/account";
	public static final List<String> ACCOUNTS_PAGE_EXPECTED_LIST = Arrays.asList("My Account",
			"My Orders",
			"My Affiliate Account",
			"Newsletter");
	
	public static final String SHOPPINGCART_PAGE_TITLE = "Shopping Cart"; 
	public static final String LOGOUT_PAGE_TEXT="Account Logout";
	
	public static final String PRODUCT_SUCCESS = "Success: You have added ";
	
	
	public static final int DEFAULT_TIMEOUT = 5;
	public static final int  DEFAULT_ELEMENT_TIMEOUT= 10;
	public static final int DEFAULT_POLLING_TIME = 500;
	public static final String SHOPPINGCART_PAGE_URL_FRACTION = "opencart/index.php?route=checkout/cart";
	
	// Product info page COnstants
	public final static String DESC_LATEST_INTEL = "Latest Intel mobile architecture";
	public final static String DESC_LEADING_GRAPHICS = "NVIDIA GeForce 8600M GTe";
	public final static String DESC_CONNECT = "Connect to high-bandwidth peripherals with FireWire 800 and DVI.";
	public final static String DESC_NEXT_GEN = "Featuring 802.11n wireless technology";
	
	// Register page COnstants
	public final static String REGISTER_SUCCESS_MESSAGE = "Congratulations! Your new account has been successfully created!";
	public static final String REGISTER_SHEET_NAME = "userRegister";
	
	

	
	
	
}
