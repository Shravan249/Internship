package com.ecom.testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	WebDriver driver;
	private String[] args = { "--remote-allow-origins=*", "--incognito", "--start-maximized" };

	@BeforeClass
	public void openWebSite() throws IOException {

		if(getValues("browser").equalsIgnoreCase("Chrome")) {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments(args);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		
		}
		
		else if(getValues("browser").equalsIgnoreCase("firefox")) {
			
		}
		
		else if(getValues("browser").equalsIgnoreCase("edge")) {
			
			EdgeOptions options=new EdgeOptions();
			options.addArguments(args);
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver(options);
		}
		
		driver.get(getValues("baseURL"));
	}
	
	
	public String getValues(String key) throws IOException {
		FileInputStream fis=new FileInputStream(new File("./Configuration/Config.properties"));
		Properties prop=new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}

}
