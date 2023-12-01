package com.ecom.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.ecom.testCases.TestDriver;

public class TestUtilities {

	public static String getValues(String key) throws IOException {
		FileInputStream fis = new FileInputStream(new File("./Configuration/Config.properties"));
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}

	public double getParasedInput(String input) {
		double value;
		if (input.contains(",")) {
			value = Double.parseDouble(input.substring(1).replaceAll(",", ""));
			return value;
		}

		else {
			value = Double.parseDouble(input.substring(1).replaceAll(",", ""));
			return value;
		}
	}

	public  String getScreenshot(String testName) throws IOException {
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
		String timestamp=dateFormat.format(new Date());
		String path="/Ecom/screenshot/" + testName + timestamp + ".png";
		File ts = ((TakesScreenshot) TestDriver.getDriver()).getScreenshotAs(OutputType.FILE);
		File destfile = new File(path);
		FileUtils.copyFile(ts, destfile);
		return path;
	}
}
