package com.ecom.utilites;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentRepo {

	public static ExtentReports getReport() {
		String path=getReportsPath();
		ExtentSparkReporter  reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Shravan Kumar");
		
		return extent;
	}
	
	
	public static String getReportsPath() {
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyy.MM.dd_HH.mm.ss");
		String timestamp=dateFormat.format(new Date());
		String reportFile="./reports/index "+timestamp+".html";
		return reportFile;
		
	}
	
}
