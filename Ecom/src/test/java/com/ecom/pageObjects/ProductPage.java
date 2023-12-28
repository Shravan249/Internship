package com.ecom.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.ecom.utilites.AbstractComponent;

public class ProductPage extends AbstractComponent {

	WebDriver driver;
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[normalize-space()='Mobile']")
	private WebElement mobileListPage;
	
	@FindBy(xpath = "//a[normalize-space()='TV']")
	private WebElement tvListPage;
	
	@FindBy(tagName = "h2")
	private List<WebElement> productNames;
	
	
	@FindBy(css = "select[title='Sort By']")
	private WebElement sortType;
	
	@FindBy(className = "price-box")
	private List<WebElement> priceList;
	
	@FindBy(css = "button[title='Add to Cart']")
	private List<WebElement> cartBtn;
	
	@FindBy(linkText  =  "Add to Compare")
	private List<WebElement> compareBtns;
	
	@FindBy(linkText = "Add to Wishlist")
	private List<WebElement> wishlistBtn;
	
	@FindBy(css="button[title='Compare']")
	private WebElement compareBtn;
	
	@FindBy(tagName = "h1")
	private WebElement title;
	
	
	
	public void mobileSection() {
		mobileListPage.click();
	}
	
	public void tvSection() {
		tvListPage.click();
	}
	
	public String getPageTitle() {
		return title.getText();
	}

	
	public List<WebElement> getProductNames() {
		
		return productNames;
	}
	
	public List<WebElement> getProductsPrice() {
		
		return priceList;
	}
	
	public List<WebElement> addToCart() {
		
		 return cartBtn;
	}
	
	public List<WebElement> addToCompare() {
	
		return compareBtns;
		}
	
	public List<WebElement> addWishList() {
		return wishlistBtn;
	}
	
	public void goToCompare() {
		compareBtn.click();
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
