package com.ecom.testCases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ecom.pageObjects.CartPage;
import com.ecom.pageObjects.CheckOutPage;
import com.ecom.pageObjects.LoginPage;
import com.ecom.pageObjects.ProductPage;
import com.ecom.pageObjects.WishlistPage;

public class TestCases2 extends BaseClass {
	
	LoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;
	WishlistPage wishlistPage;
	CheckOutPage checkoutPage;
	
	
	
	@Test
	public void testDay5TestCase5() throws InterruptedException {
		
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		wishlistPage = new WishlistPage(driver);
		SoftAssert sa = new SoftAssert();

		String productName = "LG LCD";// SAMSUNG LCD;
		String expTitle = "TV";
		String expMsg = "Your Wishlist has been shared.";

		loginPage.loginInToAccount("king01@gmail.com", "King@143");

		productPage.tvSection();
		String actTile = productPage.getPageTitle();
		sa.assertEquals(actTile, expTitle);

		List<WebElement> productList = productPage.getProductNames();
		List<WebElement> wishlistBtn = productPage.addWishList();
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getText().equalsIgnoreCase(productName)) {
				wishlistBtn.get(i).click();
			}
		}

		wishlistPage.shareProduct("king01@gmail.com", "Checkout this product.....:)");
		String actMsg = wishlistPage.getOutputMsg();

		sa.assertEquals(actMsg, expMsg);
		sa.assertAll();
		Thread.sleep(5000);
	}

	@Test(dependsOnMethods = "testDay5TestCase5")
	public void testDay6TestCase6() throws InterruptedException {

		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		wishlistPage = new WishlistPage(driver);
		cartPage = new CartPage(driver);
		checkoutPage = new CheckOutPage(driver);

		SoftAssert sa = new SoftAssert();

		String expPagetilte1 = "MY WISHLIST";
		String expPagetilte2 = "SHOPPING CART";
		String expPageTitle3 = "CHECKOUT";
		String paymentMethod = "Check / Money order";
		String expProductName = "LG LCD";

		loginPage.loginInToAccount("king01@gmail.com", "King@143");
		loginPage.goToWishlist();
		String actpageTitle = wishlistPage.getTitle();
		wishlistPage.goToCart();
		String actPageTitle2 = cartPage.getTitle();
		String productPrice = cartPage.getTotalPrice();
		cartPage.goToCheckOut();
		String actPageTitle3 = checkoutPage.getTitle();
		checkoutPage.address();

		Thread.sleep(3000);
		String flatPrice = checkoutPage.getFlatPrice();
		checkoutPage.paymentMethod(paymentMethod);
		String actProductName = checkoutPage.getProductName();
		String productTotalPrice = checkoutPage.getTotalPrice();
		checkoutPage.placeOrder();

		double productCost = getParasedInput(productPrice);
		double flatCost = getParasedInput(flatPrice);
		double expPrice = productCost + flatCost;
		double actTotalPrice = getParasedInput(productTotalPrice);


		sa.assertEquals(actpageTitle, expPagetilte1);
		sa.assertEquals(actPageTitle2, expPagetilte2);
		sa.assertEquals(actPageTitle3, expPageTitle3);
		sa.assertEquals(actProductName, expProductName);
		sa.assertEquals(actTotalPrice, expPrice);

		sa.assertAll();

	}
	
	@Test
	public void test7() throws InterruptedException {
		
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		checkoutPage = new CheckOutPage(driver);
		
		String expProductName="SONY XPERIA";
		String paymentMethod = "Check / Money order";
		String expPagetilte = "SHOPPING CART";
		String expPageTitle2 = "CHECKOUT";
		
		loginPage.loginInToAccount("king01@gmail.com", "King@143");
		productPage.mobileSection();
		List<WebElement> productsList = productPage.getProductNames();
		
		for(int i=0;i<productsList.size();i++) {
			if(productsList.get(i).getText().equalsIgnoreCase(expProductName)) {
				productPage.addToCart().get(i).click();
				break;
			}
		}
		

		String actTitle = cartPage.getTitle();
		cartPage.goToCheckOut();
		String actPageTitle2 = checkoutPage.getTitle();
		checkoutPage.address();
		Thread.sleep(3000);
		String flatPrice = checkoutPage.getFlatPrice();
		checkoutPage.paymentMethod(paymentMethod);
		String actProductName = checkoutPage.getProductName();
		checkoutPage.placeOrder();

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actTitle, expPagetilte);
		sa.assertEquals(actPageTitle2, expPageTitle2);
		sa.assertEquals(actProductName, expProductName);
		sa.assertAll();
		
	}

}
