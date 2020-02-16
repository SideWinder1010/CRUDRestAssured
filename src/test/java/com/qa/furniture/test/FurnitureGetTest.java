package com.qa.furniture.test;

import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.furniture.Base.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

/**
 * Swagger URL Get functionality test
 * 
 * @author vaishali
 *
 */
public class FurnitureGetTest extends BaseClass {

	private static ExtentTest extentReportLogger = null;
	private static String testCategory = "Furniture API";
	private static String appender = null;
	/**
	 * Before Setup Method
	 */
	@BeforeMethod
	public void setUp() {

		appender = "/rest/api/category/all";

		this.extentReportLogger = BaseClass.extentReportLogger;
		// extentReportLogger.assignCategory(testCategory);

	}

	/**
	 * Test case for GET
	 */
	@Test
	public void getProductTest() {

		try {
			Reporter.log("Get Value API Test begins", true);
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.request(Method.GET,appender);

			String responseBody = response.getBody().asString();
			System.out.println(responseBody);
			
			int statusCode = response.getStatusCode();

			if (statusCode == 200) {
				extentReportLogger.log(LogStatus.PASS, "***The test case has passed**");
				BaseClass.reportTestCaseStatus(extentReportLogger, "Get Method", true, responseBody);
			} else {
				extentReportLogger.log(LogStatus.FAIL, "The testcase has failed ===>>");
				BaseClass.reportTestCaseStatus(extentReportLogger, "Get Method", false, responseBody);
			}

		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

	}

}
