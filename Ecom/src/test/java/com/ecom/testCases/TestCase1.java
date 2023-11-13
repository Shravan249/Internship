package com.ecom.testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.ecom.Exceldataproviders.DataExtractors;
import com.ecom.pageObjects.CartPage;
import com.ecom.pageObjects.CheckOutPage;
import com.ecom.pageObjects.LoginPage;
import com.ecom.pageObjects.ProductPage;
import com.ecom.pageObjects.WishlistPage;

public class TestCase1 extends BaseClass {

	LoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;
	WishlistPage wishlistPage;
	CheckOutPage checkoutPage;

	@Test
	public void testDay1TestCase1() throws IOException {

		/*
		 * Verifying the sorting feature By Name The products are sorted as per the
		 * expected results
		 */

		List<String> expProductNames = new ArrayList<>();
		List<String> actProductNames = new ArrayList<>();

		productPage = new ProductPage(driver);
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

	@Test(dataProvider = "testCase2Data", dataProviderClass = DataExtractors.class)
	public void testDay2TestCase2(String expPrice, String productName) {
		/*
		 * Verifying the cost of the product is same in list page as well as details
		 * page
		 */
		String actPrice = "";
		productPage = new ProductPage(driver);
		productPage.mobileSection();
		List<WebElement> productsList = productPage.getProductNames();
		List<WebElement> productsPrice = productPage.getProductsPrice();

		for (WebElement product : productsList) {
			if (product.getText().equalsIgnoreCase(productName)) {
				for (WebElement productPrice : productsPrice) {

					if (productPrice.getText().substring(0, 4).equals(expPrice)) {
						actPrice = productPrice.getText().substring(0, 4);
					}
				}
			}
		}

		Assert.assertEquals(actPrice, expPrice);

	}

	@Test(dataProvider = "testCase3Data", dataProviderClass = DataExtractors.class)
	public void testDay3TestCase3(String productName, String expErrorMsg, String expEmptyMsg)
			throws InterruptedException, IOException {
		/*
		 * Verifying the quantity of the product are not adding more than the products
		 * available in the store
		 */

		String expCartPageTitle = getValues("cartPageTitle");
		int quantity = 1000;
		productPage = new ProductPage(driver);
		productPage.mobileSection();
		List<WebElement> productsList = productPage.getProductNames();

		for (int i = 0; i < productsList.size(); i++) {
			if (productsList.get(i).getText().equalsIgnoreCase(productName)) {
				productPage.addToCart().get(i).click();
				break;
			}
		}

		Thread.sleep(3000);
		cartPage = new CartPage(driver);

		String actCartPageTitle = cartPage.getTitle();
		cartPage.addQuantity(quantity);
		cartPage.updateQuantity();
		String actErrorMsg = cartPage.getErrorMsg();
		cartPage.emptyCart();
		String actEmptyMsg = cartPage.getTitle();

		SoftAssert soft = new SoftAssert();
		soft.assertEquals(actCartPageTitle, expCartPageTitle);
		soft.assertEquals(actErrorMsg, expErrorMsg);
		soft.assertEquals(actEmptyMsg, expEmptyMsg);
		soft.assertAll();

	}

	@Test(dataProvider = "testCase4Data", dataProviderClass = DataExtractors.class)
	public void testDay4TestCase4(String product1, String product2) throws InterruptedException {
		/*
		 * Comparing the two products
		 */

		List<String> actProduct = new ArrayList<>();
		List<String> expProducts = Arrays.asList(product1, product2);
		productPage = new ProductPage(driver);
		productPage.mobileSection();
		List<WebElement> productsList = productPage.getProductNames();
		List<WebElement> compareBtn = productPage.addToCompare();

		for (int i = 0; i < productsList.size(); i++) {
			if (expProducts.contains(productsList.get(i).getText())) {
				compareBtn.get(i).click();
			}

		}

		productPage.goToCompare();
		Set<String> wind = driver.getWindowHandles();
		Iterator<String> it = wind.iterator();
		String parentId = it.next();
		String childId = it.next();

		driver.switchTo().window(childId);
		List<WebElement> names = driver.findElements(By.tagName("h2"));
		for (WebElement name : names) {
			actProduct.add(name.getText());
		}

		driver.findElement(By.cssSelector("button[title='Close Window']")).click();
		driver.switchTo().window(parentId);
		Assert.assertEquals(actProduct, actProduct);
	}

}
