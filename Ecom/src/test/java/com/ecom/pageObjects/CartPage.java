package com.ecom.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecom.utilites.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Edit")
	private WebElement editBtn;
	
	@FindBy(id="qty")
	private WebElement quantity;
	
	@FindBy(css="button[title='Update Cart']")
	private WebElement updateBtn;
	
	@FindBy(css="button[title='Empty Cart']")
	private WebElement emptyCartBtn;
	
	@FindBy(css="li[class='error-msg']")
	private WebElement errorMsg;
	
	@FindBy(tagName = "h1")
	private WebElement title;
	
	
	@FindBy(css = "strong span[class='price']")
	private WebElement totalPrice;
	
	@FindBy(css = "button[title='Proceed to Checkout']")
	private WebElement proceedBtn;
	
	
	public void addQuantity(int num) {
		
	    editBtn.click();
	    quantity.clear();
		quantity.sendKeys(Integer.toString(num));
		
	}
	
	public void updateQuantity() {
		updateBtn.click();
	}
	
	public String getErrorMsg() {
		
		return errorMsg.getText();
	}
	
	public void emptyCart() {
		
		emptyCartBtn.click();
	}
	
	public String getTitle() {
		
		return title.getText();
	}
	
	public String getTotalPrice() {
		
		return totalPrice.getText();
	}
	
	public void goToCheckOut() {
		
		proceedBtn.click();
	}
	
	
}
