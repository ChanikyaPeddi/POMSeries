package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.Constants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterTest extends BaseTest {

	@BeforeClass
	public void registerPageSetup() {
		registerPage = loginPage.clickOnRegisterLink();

	}
	@DataProvider
	public Object[][] getRegUserData() {
		
			Object[][] regUserData =ExcelUtil.getUserData(Constants.REGISTER_SHEET_NAME);
			return regUserData;
		
	}
	public String randomEmail() {
		Random random = new Random();
		String email ="automation" +random.nextInt(1000)+java.time.LocalDate.now() +"@gmail.com";
		System.out.println(email);
		return email;
	}
	
	@Test(dataProvider = "getRegUserData")
	public void registerUserTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {

		Assert.assertTrue(registerPage.registerUser( firstName,  lastName,  randomEmail(),  telephone,  password,
				 subscribe));

	}
}
