package com.ecom.testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ecom.pageObjects.CartPage;
import com.ecom.pageObjects.ProductPage;

public class TestCase1 extends BaseClass {

	ProductPage productPage;
	CartPage cartPage;

	 @Test(priority = 1)
	public void testDay1TestCase1() throws IOException {

		List<String> expProductNames = new ArrayList<>();
		List<String> actProductNames = new ArrayList<>();

		productPage= new ProductPage(driver);
		productPage.mobileSection();
		List<WebElement> beforeNames = productPage.getProductNames();

		for (WebElement ele : beforeNames) {
			expProductNames.add(ele.getText());
		}
		Collections.sort(expProductNames);

		productPage.getSorting("Name");

		List<WebElement> afterSortingNames = productPage.getProductNames();

		for (WebElement ele : afterSortingNames) {
			actProductNames.add(ele.getText());
		}

		Assert.assertEquals(actProductNames, expProductNames);

	}

	@Test
	public void testDay2TestCase2() {
		String expPrice = "$100";
		String actPrice = null;
		String productName = "SONY XPERIA";
		
		productPage = new ProductPage(driver);
		productPage.mobileSection();
		List<WebElement> productsList = productPage.getProductNames();
		List<WebElement> productsPrice = productPage.getProductsPrice();

		for (WebElement product : productsList) {
			if (product.getText().equalsIgnoreCase(productName)) {
				for (WebElement productPrice : productsPrice) {
					
					if(productPrice.getText().substring(0, 4).equals(expPrice)) {
						actPrice =productPrice.getText().substring(0, 4);
					}
				}
			}
		}

		Assert.assertEquals(actPrice, expPrice);

	}
	
	@Test
	public void testDay3TestCase3() throws InterruptedException {
		
		String productName="SONY XPERIA";
		String expTitle="SHOPPING CART";
		int numberOfQuantity=1000;
		String expErrorMsg="The maximum quantity allowed for purchase is 500.";
		String expEmptyMsg="SHOPPING CART IS EMPTY";
		
		
		productPage = new ProductPage(driver);
		productPage.mobileSection();
		List<WebElement> productsList = productPage.getProductNames();
		
		for (WebElement product : productsList) {
			if (product.getText().equalsIgnoreCase(productName)) {
				productPage.addToCart();
				break;
			}
		}
		
		Thread.sleep(3000);
		cartPage=new CartPage(driver);
		
		String actTitle=cartPage.getTitle();
		cartPage.addQuantity(numberOfQuantity);
		cartPage.updateQuantity();
		String actErrorMsg=cartPage.getErrorMsg();
		cartPage.emptyCart();
		String actEmptyMsg=cartPage.getTitle();
		
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actTitle, expTitle);
		soft.assertEquals(actErrorMsg,expErrorMsg );
		soft.assertEquals(actEmptyMsg, expEmptyMsg);
		soft.assertAll();
		
		
		
		
	}
	
	
}
