package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.opencart.exception.FrameworkExceptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	// ThreadLocal is a java Class with has two methods set and get. By using these
	// two methods it creates a local copy of Objects
	// inorder to avoid Deadlock scenarios
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize WebDriver on the basis of given browser
	 * name
	 * 
	 * @param browserName
	 * @return it returns the driver
	 */
	public WebDriver init_driver(Properties prop) {
		optionsManager = new OptionsManager(prop);
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser is : " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}

		else {
			System.out.println("Please provide correct browser... your requested browser is : " + browserName);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}

	/**
	 * This is used to get the local copy of driver
	 * 
	 * @return WebDriver
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialize config properties file
	 * 
	 * @return Properties file
	 */
	public Properties init_prop() {

		prop = new Properties();
		FileInputStream ip = null;
		
		// from eclipse
		/*
		 * try { ip = new
		 * FileInputStream("./src/test/resources/config/config.properties");
		 * 
		 * prop.load(ip); } catch (FileNotFoundException e) {
		 * 
		 * e.printStackTrace(); } catch (IOException e) {
		 * 
		 * e.printStackTrace(); }
		 */

		// mnv clean install -Denv="qa";
		String envName = System.getProperty("env");
		System.out.println("Tests are running on " + envName + " Environment");

		if (envName == null) {
			System.out.println("No env is given... hence running tests on QA enviroment");
			try {
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			try {
				switch (envName) {
				case "qa":
					System.out.println("running on qa env");
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					System.out.println("running on stage env");
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					System.out.println("running on wat env");
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;

				default:
					System.out.println("Please pass the right env..." + envName);
					throw new FrameworkExceptions(" No Env is found Exception");
					//break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}

	public String getScreenShot() {

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = "./" + "screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;

	}

}
