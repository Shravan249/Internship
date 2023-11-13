package com.ecom.utilites;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ecom.testCases.BaseClass;

public class ExtentRepo extends BaseClass{

	public static ExtentReports getReport() {
		String path=getReportsPath();
		ExtentSparkReporter  reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("OS", System.getProperty("os.version"));
		extent.setSystemInfo("Java version", System.getProperty("java.version"));
	    
		return extent;
	}
	
	
	public static String getReportsPath() {
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss");
		String timestamp=dateFormat.format(new Date());
		String reportFile="./reports/index "+timestamp+".html";
		return reportFile;
		
	}
	

	
}
