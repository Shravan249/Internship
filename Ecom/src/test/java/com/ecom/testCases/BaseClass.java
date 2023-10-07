package com.ecom.testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

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

		}

		else if (getValues("browser").equalsIgnoreCase("edge")) {

			EdgeOptions options = new EdgeOptions();
			options.addArguments(args);
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
		}

		driver.get(getValues("baseURL"));
	}

	public String getValues(String key) throws IOException {
		FileInputStream fis = new FileInputStream(new File("./Configuration/Config.properties"));
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}

	public double getParasedInput(String input) {
		double value;
		if (input.contains(",")) {
			value = Double.parseDouble(input.substring(1).replaceAll(",", ""));
			return value;
		}

		else {
			value = Double.parseDouble(input.substring(1).replaceAll(",", ""));
			return value;
		}
	}

	public  String getScreenshot(String testName, WebDriver driver) throws IOException {
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
		String timestamp=dateFormat.format(new Date());
		String path="./screenshot/" + testName + timestamp + ".png";
		File ts = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destfile = new File(path);
		FileUtils.copyFile(ts, destfile);
		return path;
	}


	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

}
