package com.qa.furniture.Base;

import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.qa.furniture.Util.ReportManagerAPI;
import com.qa.furniture.Util.TestCaseReporterUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.restassured.RestAssured;

public class BaseClass {
	
	public static ExtentReports reporter = null;
	public static ExtentTest extentReportLogger = null;	
	public static String reportFile = "";

	public static final TestCaseReporterUtil testReporter = new TestCaseReporterUtil();
	
	//URLs for the functions
	
	@BeforeClass
	 public void beforeClass(ITestContext context) {
	        RestAssured.baseURI = context.getCurrentXmlTest().getParameter("BaseURI");
	       
	        Reporter.log("BaseURI Set", true);

}
	@BeforeMethod(alwaysRun = true)
	public void driverSetUp(Method method) throws Exception {
		reportFile = "./test-resources/testreports/testReporter.html";
		reporter = ReportManagerAPI.getReporter(reportFile, true);
		extentReportLogger = reporter.startTest("TestCase" + " - " + method.getName());
	}
	/**
	 * AfterClass method for driver cleanup and flush the logs into ExtentReport
	 * 
	 * @throws Exception
	 */
	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		reporter.flush();
	}
	

	/***
	 * This is reporter method, to put the logs into ExtentReport.
	 */
	public static void reportTestCaseStatus(ExtentTest logger, String methodName,
			boolean testStatus,String response) {
		testReporter.reportTestCaseStatus(logger, methodName, testStatus,response);
	}

}
