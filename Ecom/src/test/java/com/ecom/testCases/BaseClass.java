package com.ecom.testCases;

import java.io.IOException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentTest;
import com.ecom.utilites.AbstractComponent;
import com.ecom.utilites.ListenersFile;
import com.ecom.utilites.TestUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends TestUtilities {

	public WebDriver driver;
	private String[] args = { "--remote-allow-origins=*", "--incognito", "--start-maximized" };

	
	@BeforeMethod
	public void openWebSite() throws IOException {

		if (getValues("browser").equalsIgnoreCase("Chrome")) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments(args);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

		}

		else if (getValues("browser").equalsIgnoreCase("firefox")) {

			FirefoxOptions options = new FirefoxOptions();
			options.addArguments(args);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(options);
		}

		else if (getValues("browser").equalsIgnoreCase("edge")) {

			EdgeOptions options = new EdgeOptions();
			options.addArguments(args);
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
		}

		driver.get(getValues("baseURL"));

	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
