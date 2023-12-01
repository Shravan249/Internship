package com.ecom.testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ecom.Exceldataproviders.DataExtractors;
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
	
	
	
	@Test(dataProvider = "testCase5Data",dataProviderClass = DataExtractors.class)
	public void testDay5TestCase5(String productName, String message1, String email, String message2 ) throws InterruptedException, IOException {
		
		/*
		 Verifying the functionality of the share wishlist to other people using email
		 */

		loginPage = new LoginPage(TestDriver.getDriver());
		productPage = new ProductPage(TestDriver.getDriver());
		wishlistPage = new WishlistPage(TestDriver.getDriver());
		SoftAssert sa = new SoftAssert();

		String expTitle = getValues("TvPageTitle");

		loginPage.loginInToAccount(getValues("userName"), getValues("password"));
		
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

		wishlistPage.shareProduct(email, message2);
		String actMsg = wishlistPage.getOutputMsg();

		sa.assertEquals(actMsg, message1);
		sa.assertAll();
		Thread.sleep(5000);
	}

	@Test(dependsOnMethods = "testDay5TestCase5")
	public void testDay6TestCase6() throws InterruptedException, IOException {
        /* 
         Verifying the user able to purchase the product from wishlist
         */
		
		loginPage = new LoginPage(TestDriver.getDriver());
		productPage = new ProductPage(TestDriver.getDriver());
		wishlistPage = new WishlistPage(TestDriver.getDriver());
		cartPage = new CartPage(TestDriver.getDriver());
		checkoutPage = new CheckOutPage(TestDriver.getDriver());

		SoftAssert sa = new SoftAssert();

		String expWishPagetilte = getValues("wishPageTitle");
		String expCartPagetilte = getValues("cartPageTitle");
		String expCheckoutPageTitle = getValues("checkoutPageTitle");
		String paymentMethod = "Check / Money order";
		String expProductName = "LG LCD";

		loginPage.loginInToAccount(getValues("userName"), getValues("password"));
		loginPage.goToWishlist();
		String actWishPageTitle = wishlistPage.getTitle();
		wishlistPage.goToCart();
		String actCartPageTitle = cartPage.getTitle();
		String productPrice = cartPage.getTotalPrice();
		cartPage.goToCheckOut();
		String actCheckoutPageTitle = checkoutPage.getTitle();
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


		sa.assertEquals(actWishPageTitle, expWishPagetilte);
		sa.assertEquals(actCartPageTitle, expCartPagetilte);
		sa.assertEquals(actCheckoutPageTitle, expCheckoutPageTitle);
		sa.assertEquals(actProductName, expProductName);
		sa.assertEquals(actTotalPrice, expPrice);

		sa.assertAll();

	}
	
	@Test(dataProvider = "testCase7Data",dataProviderClass = DataExtractors.class)
	public void test7(String productName, String paymentMethod) throws InterruptedException, IOException {
		
		/* 
		 Verifying the user able to purchase the product 
		  */
		
		loginPage = new LoginPage(TestDriver.getDriver());
		productPage = new ProductPage(TestDriver.getDriver());
		cartPage = new CartPage(TestDriver.getDriver());
		checkoutPage = new CheckOutPage(TestDriver.getDriver());
		
		String expCartPagetilte = getValues("cartPageTitle");
		String expCheckoutPageTitle = getValues("checkoutPageTitle");
		
		loginPage.loginInToAccount(getValues("userName"), getValues("password"));
		productPage.mobileSection();
		List<WebElement> productsList = productPage.getProductNames();
		for(int i=0;i<productsList.size();i++) {
			if(productsList.get(i).getText().equalsIgnoreCase(productName)) {
				productPage.addToCart().get(i).click();
				break;
			}
		}
		

		String actCartPageTitle = cartPage.getTitle();
		cartPage.goToCheckOut();
		String actCheckouPagePageTitle = checkoutPage.getTitle();
		checkoutPage.address();
		Thread.sleep(3000);
		String flatPrice=checkoutPage.getFlatPrice();
		checkoutPage.paymentMethod(paymentMethod);
		String actProductName = checkoutPage.getProductName();
		checkoutPage.placeOrder();

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actCartPageTitle, expCartPagetilte);
		sa.assertEquals(actCheckouPagePageTitle, expCheckoutPageTitle);
		sa.assertEquals(actProductName, productName);
		sa.assertAll();
		
	}

}
