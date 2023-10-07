package com.ecom.utilites;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent  {

	
	WebDriver driver;
	Duration time=Duration.ofSeconds(3);
	public WebDriverWait wait;

	
	public AbstractComponent(WebDriver driver) {
		this .driver=driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, time);
	}

	//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
	 
	public void waitToVisible(By findBy) {
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(findBy)));
	}
	
	public void waitforWebElement(WebElement ele) {
		
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	
	public void waitToInvisible(WebElement  ele) throws InterruptedException {
		
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
}
	