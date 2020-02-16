package com.qa.furniture.test;

import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.furniture.Base.BaseClass;
import com.qa.furniture.resources.XLSWorker;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

/**
 * Swagger URL Put product functionality test
 * 
 * @author vaishali
 *
 */
public class FurniturePutTest extends BaseClass {

	private static BaseClass bc = new BaseClass();
	private static ExtentTest extentReportLogger = null;
	private static String testCategory = "Furniture API";

	@BeforeMethod
	public void setUp() {
		FurniturePutTest FurniturePut = new FurniturePutTest();
		RestAssured.basePath = "/rest/api/product";
		this.extentReportLogger = BaseClass.extentReportLogger;

	}

	@DataProvider
	public Object[][] getProductData() throws Exception {

		Object data[][] = XLSWorker.getTestData("PutValue");
		return data;
	}

	@Test(priority = 1, dataProvider = "getProductData")
	public void putTest(String putValue) {
		try {
			Reporter.log("Put Value API Test begins", true);
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			JSONObject json = new JSONObject();

			float f = Float.valueOf(putValue.trim()).floatValue();
			int pID = (int) f;

			json.put("id", pID);
			request.body(json.toJSONString());
			// String url = bc.URLs();
			// String newURL = url+"/rest/api/product/"+pID;
			Response response = request.put("/" + pID);

			String responseBody = response.getBody().asString();
			int statusCode = response.getStatusCode();

			System.out.println("response code" + statusCode);

			Assert.assertEquals(statusCode, 200);

			if (statusCode == 200) {
				extentReportLogger.log(LogStatus.PASS, "***The test case has passed**");
				BaseClass.reportTestCaseStatus(extentReportLogger, "Put Test Method", true, responseBody);
			} else {
				extentReportLogger.log(LogStatus.FAIL, "The testcase has failed ===>>");
				BaseClass.reportTestCaseStatus(extentReportLogger, "Put Test Method", false, responseBody);
			}

		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

	}
}
