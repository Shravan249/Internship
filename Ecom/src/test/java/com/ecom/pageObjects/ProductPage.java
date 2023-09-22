package com.ecom.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

	WebDriver driver;
	public ProductPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[normalize-space()='Mobile']")
	private WebElement listPage;
	
	@FindBy(tagName = "h2")
	private List<WebElement> productNames;
	
	
	@FindBy(css = "select[title='Sort By']")
	private WebElement sortType;
	
	@FindBy(className = "price-box")
	private List<WebElement> priceList;
	
	@FindBy(css = "button[title='Add to Cart']")
	private WebElement cartBtn;
	
	

	
	public void mobileSection() {
		listPage.click();
	}
	
	public List<WebElement> getProductNames() {
		
		return productNames;
	}
	
	public List<WebElement> getProductsPrice() {
		
		return priceList;
	}
	
	public void addToCart() {
		
		 cartBtn.click();
	}
	
	
	
	
	public void getSorting(String sortBy) {

		Select sel = new Select(sortType);

		List<WebElement> options = sel.getOptions();
		for (WebElement opt : options) {

			if (opt.getText().contains(sortBy)) {

				opt.click();
				break;
			}
		}
	}
}
