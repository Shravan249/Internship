package com.ecom.testCases;

import java.io.IOException;
import java.util.Objects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import com.ecom.utilites.TestUtilities;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDriver extends TestUtilities {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static String[] args = { "--remote-allow-origins=*", "--incognito", "--start-maximized" };

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void setDriver(WebDriver dr) {
		driver.set(dr);
	}

	public static void unload() {
		driver.remove();
	}

	public static void setUp() throws IOException {

		WebDriver driver = null;
		if (Objects.isNull(getDriver())) {
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

			setDriver(driver);
			getDriver().get(getValues("baseURL"));

		}
	}

	public static void tearDown() {
		
		if (Objects.nonNull(getDriver())) {
			getDriver().close();
			unload();
		}
	}
}
