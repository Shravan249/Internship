package com.ecom.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ecom.utilites.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
        super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(tagName = "h1")
	private WebElement title;

	@FindBy(css = "button[onclick='billing.save()']")
	private WebElement continueBtn;

	@FindBy(css = "div[id='checkout-shipping-method-load'] dt")
	private WebElement flatRate;

	@FindBy(xpath = "//div[@id='checkout-shipping-method-load']//label//span")
	private WebElement flatPrice;

	@FindBy(css = "button[onclick='shippingMethod.save()']")
	private WebElement continBtn;

	@FindBy(css = "button[onclick='payment.save()']")
	private WebElement contBtn;

	@FindBy(css = "strong span[class='price']")
	private WebElement totalPrice;

	@FindBy(css = "button[title='Place Order']")
	private WebElement orderBtn;
	
	@FindBy(css = "h3[class='product-name']")
	private WebElement productName;


	public String getTitle() {
		return title.getText();
	}

	public void address() {
		continueBtn.click();
	}

	public String getFlatPrice() {
		waitforWebElement(continBtn);
		String price = flatPrice.getText();
		continBtn.click();
		return price;
	}

	public void paymentMethod(String title) {
		waitforWebElement(contBtn);
		driver.findElement(By.cssSelector("input[title='" + title + "']")).click();
		contBtn.click();
	}
	
	public String getProductName() {
		waitforWebElement(productName);
		return productName.getText();
	}
	
	public String getTotalPrice() {
		waitforWebElement(totalPrice);
		return totalPrice.getText();
	}
	
	public void placeOrder() {
		waitforWebElement(orderBtn);
		orderBtn.click();
	}

}
