package com.qa.furniture.test;

import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.furniture.Base.BaseClass;
import com.qa.furniture.dto.ProductListDTO;
import com.qa.furniture.dto.PutListDTO;
import com.qa.furniture.resources.XLSWorker;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

/**
 * Swagger URL Put product with body functionality test
 * 
 * @author vaishali
 *
 */
public class PutProduct extends BaseClass {

	private static BaseClass bc = new BaseClass();
	private static ExtentTest extentReportLogger = null;
	private static String testCategory = "Furniture API";

	/**
	 * Before setup method
	 */
	@BeforeMethod
	public void setUp() {
		FurniturePutTest FurniturePut = new FurniturePutTest();
		this.extentReportLogger = BaseClass.extentReportLogger;

	}

	/**
	 * Data provider for testcase
	 * 
	 * @return
	 * @throws Exception
	 */
	@DataProvider
	public Object[][] getProductData() throws Exception {

		Object data[][] = XLSWorker.getTestData("PutValueBody");
		return data;
	}

	/**
	 * Test case to put the product details
	 * 
	 * @param putValue
	 * @param availability
	 * @param color
	 * @param description
	 * @param discount
	 * @param materialDescription
	 * @param name
	 * @param price
	 * @param updateOn
	 * @param warranty
	 */
	@Test(priority = 1, dataProvider = "getProductData")
	public void putProductTest(String putValue, String availability, String color, String description, String discount,
			String materialDescription, String name, String price, String updateOn, String warranty) {

		try {

			Reporter.log("Post Value API Test begins", true);
			RequestSpecification httpRequest = RestAssured.given();

			JSONObject requestParams = new JSONObject();

			Double discountDouble = Double.parseDouble(discount);

			Double warrnDouble = Double.parseDouble(warranty);

			Double priceDouble = Double.parseDouble(price);

			PutListDTO putdto = new PutListDTO(availability, color, description, warranty, discountDouble,
					materialDescription, name, priceDouble, updateOn, warrnDouble);

			httpRequest.header("Content-Type", "application/json");

			httpRequest.body(putdto);

			// Response Object

			JSONObject json = new JSONObject();

			float f = Float.valueOf(putValue.trim()).floatValue();
			int pID = (int) f;

			String appender = "/rest/api/product/" + pID;

			Response response = httpRequest.request(Method.PUT, appender);
			String responseBody = response.getBody().asString();
			System.out.println("***********Response Body************" + responseBody);
			int statusCode = response.getStatusCode();

			System.out.println("response code" + statusCode);

			Assert.assertEquals(statusCode, 200);

			if (statusCode == 200) {
				extentReportLogger.log(LogStatus.PASS, "***The test case has passed**");
				BaseClass.reportTestCaseStatus(extentReportLogger, "Put Method", true, responseBody);
			} else {
				extentReportLogger.log(LogStatus.FAIL, "The testcase has failed ===>>");
				BaseClass.reportTestCaseStatus(extentReportLogger, "Put Method", false, responseBody);
			}

		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}

	}
}
