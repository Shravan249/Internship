package com.ecom.utilites;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.ecom.testCases.BaseClass;

public class ListenersFile extends BaseClass implements ITestListener {

	static ExtentTest test;
	ExtentReports extent = ExtentRepo.getReport();
	ThreadLocal<ExtentTest> safe = new ThreadLocal<ExtentTest>(); // Used when test run parallel

	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		safe.set(test);
	}

	public void onTestSuccess(ITestResult result) {

		safe.get().log(Status.PASS, result.getTestName());

	}

	public void onTestFailure(ITestResult result) {

		safe.get().fail(result.getThrowable());
		String filepath = null;

		try {

			filepath = getScreenshot(result.getMethod().getMethodName());
			safe.get().addScreenCaptureFromPath(filepath, result.getMethod().getMethodName());

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {

		safe.get().log(Status.SKIP, result.getTestName());
	}

	public void onFinish(ITestContext context) {

		extent.flush();
	}

	public static ExtentTest getLogger() {
         return test;
	}

}