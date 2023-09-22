package com.ecom.testCases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCase {
	private String[] args = { "--remote-allow-origins=*", "--incognito", "--start-maximized" };
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments(args);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://live.techpanda.org/index.php");
	}
	
	
	@Test
	public void test1() {
		
		List<String> expProducts=new ArrayList<>();
		List<String> actProducts=new ArrayList<>();
		
		
		driver.findElement(By.xpath("//a[normalize-space()='Mobile']")).click();
		List<WebElement> productNames= driver.findElements(By.tagName("h2"));
		for(WebElement productName :productNames ) {
			
			expProducts.add(productName.getText());
		}
		Collections.sort(expProducts);
		
		
		WebElement sortType=driver.findElement(By.cssSelector("select[title='Sort By']"));
		selection(sortType,"Name");
		
		
		List<WebElement> actProductNames= driver.findElements(By.tagName("h2"));
		
		for( WebElement ele: actProductNames) {
			
			actProducts.add(ele.getText());
		}
		
		
		
		Assert.assertEquals(actProducts, expProducts);
		
	}
	
	public void testDay2TestCase2() 
	{
		//.price-box
		
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		
		driver.quit();
	}
	
	public void selection(WebElement ele, String str) {
		
		Select sel = new Select(ele);

		List<WebElement> options = sel.getOptions();
		for (WebElement opt : options) {

			if (opt.getText().contains(str)) {

				opt.click();
				break;
			}
		}
	}
}
