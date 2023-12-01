package com.ecom.testCases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ecom.utilites.TestUtilities;

public class BaseClass extends TestUtilities{


	@BeforeMethod
	public void openWebSite() throws IOException {

		TestDriver.setUp();
	}

	@AfterMethod
	public void tearDown() {

		TestDriver.tearDown();

	}

}
