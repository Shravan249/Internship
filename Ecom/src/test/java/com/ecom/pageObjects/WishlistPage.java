package com.ecom.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecom.utilites.AbstractComponent;

public class WishlistPage extends AbstractComponent {

	WebDriver driver;
	
	public WishlistPage(WebDriver driver) {
	     super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = "button[title='Share Wishlist']")
	private WebElement sharelist;
	
	@FindBy(id = "email_address")
	private WebElement emailAddress;
	
	@FindBy(id = "message")
	private WebElement message;
	
	@FindBy(css = "button[title='Share Wishlist']")
	private WebElement shareBtn;
	
	@FindBy(css = "li[class='success-msg']")
	private WebElement successMsg;
	
	@FindBy(css = "button[title='Add to Cart']")
	private WebElement cartBtn;
	
	@FindBy(tagName = "h1")
	private WebElement title;
	
	
	public String getTitle() {
		return title.getText();
	}
	
	public void shareProduct(String email, String msg) {
		sharelist.click();
		emailAddress.sendKeys(email);
		message.sendKeys(msg);
		shareBtn.click();
	}
	
	
	public String getOutputMsg() {
		return successMsg.getText();
	}
	
	
	public void goToCart() {
		cartBtn.click();
	}
}
