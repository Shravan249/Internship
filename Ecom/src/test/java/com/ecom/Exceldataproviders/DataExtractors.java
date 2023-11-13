package com.ecom.Exceldataproviders;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.ecom.utilites.ExcelDataExtracter;
import com.ecom.utilites.TestUtilities;

public class DataExtractors extends TestUtilities {

	ExcelDataExtracter excelData;

	@DataProvider(name = "testCase2Data")
	public Object[][] getTestCase2Data() throws IOException, Exception {

		excelData = new ExcelDataExtracter();
		Object[][] data = excelData.getExcelData(getValues("excelPath1"), "Sheet1");
		return data;
	}

	@DataProvider(name = "testCase3Data")
	public Object[][] getTestCase3Data() throws IOException, Exception {

		excelData = new ExcelDataExtracter();
		Object[][] data = excelData.getExcelData(getValues("excelPath1"), "Sheet2");
		return data;
	}

	@DataProvider(name = "testCase4Data")
	public Object[][] getTestCase4Data() throws IOException, Exception {

		excelData = new ExcelDataExtracter();
		Object[][] data = excelData.getExcelData(getValues("excelPath1"), "Sheet3");
		return data;
	}

	@DataProvider(name = "testCase5Data")
	public Object[][] getTestCase5Data() throws IOException, Exception {

		excelData = new ExcelDataExtracter();
		Object[][] data = excelData.getExcelData(getValues("excelPath2"), "Sheet1");
		return data;
	}

	@DataProvider(name = "testCase7Data")
	public Object[][] getTestCase7Data() throws IOException, Exception {

		excelData = new ExcelDataExtracter();
		Object[][] data = excelData.getExcelData(getValues("excelPath2"), "Sheet2");
		return data;
	}
}
