package com.ecom.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ecom.utilites.AbstractComponent;

public class LoginPage extends AbstractComponent{

	WebDriver driver;

	public LoginPage(WebDriver driver) {
        super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a[class*='skip-account']")
	private WebElement accountBtn;

	@FindBy(css = "a[title='My Account']")
	private WebElement signBtn;

	@FindBy(css = "input[name='login[username]']")
	private WebElement emailInput;

	@FindBy(css = "input[name='login[password]']")
	private WebElement passwordInput;

	@FindBy(css = "button[title='Login']")
	private WebElement loginBtn;
	
	@FindBy(css  = "a[title*='My Wishlist']")
	private WebElement wishlistBtn;
	
	@FindBy(tagName = "h1")
	private WebElement title;

	

	public void loginInToAccount(String email, String password) {
		accountBtn.click();
		signBtn.click();
		emailInput.sendKeys(email);
		passwordInput.sendKeys(password);
		loginBtn.click();
	}
	
	public void goToWishlist() {
		accountBtn.click();
		wishlistBtn.click();
	}
}
