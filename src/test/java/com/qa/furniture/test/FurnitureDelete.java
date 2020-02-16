package com.qa.furniture.test;

import static org.testng.Assert.assertFalse;

import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import com.qa.furniture.Base.BaseClass;
import com.qa.furniture.Util.*;
import com.qa.furniture.resources.XLSWorker;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * Swagger URL Delete functionality test
 * 
 * @author vaishali
 *
 */
public class FurnitureDelete extends BaseClass {
	
	private static BaseClass bc= new BaseClass();
	private static ExtentTest extentReportLogger = null;
	private static String testCategory = "Furniture API";
	
	/**
	 * Before Method for setup
	 */
	@BeforeMethod
	public void setUp(){
		
		this.extentReportLogger = BaseClass.extentReportLogger;
	}
	
	@DataProvider
	public Object[][] getProductData() throws Exception {

		Object data[][] = XLSWorker.getTestData("DeleteProductId");
		return data;
	}
	/**
	 * Test to delete the product and assert the success
	 */
	@Test(priority = 1, dataProvider = "getProductData")
	public void deleteTest(String deleteValue)
	{
		try{
			
		Reporter.log("Delete Value API Test begins",true);
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.header("Content-Type","application/json");	
		
		float f = Float.valueOf(deleteValue.trim()).floatValue();
		int pID = (int) f;
		
		String appender = "/rest/api/product/"+pID;
		
		System.out.println("Here");
					
		Response response = httpRequest.request(Method.DELETE,appender);
	
		boolean responseStatus = response.then().extract().path("success");
		
		//System.out.println("************************Response************"+response.getBody().prettyPrint());
	
		String responseBody = response.getBody().prettyPrint().toString();
		
		
		if(responseStatus)
		{
			extentReportLogger.log(LogStatus.PASS, "***The test case has passed**");
			BaseClass.reportTestCaseStatus(extentReportLogger, "Delete Method", true,responseBody);
		} else {
			extentReportLogger.log(LogStatus.FAIL, "The testcase has failed ===>>");
			BaseClass.reportTestCaseStatus(extentReportLogger, "Delete Method", false,responseBody);
		}
		Assert.assertEquals(responseStatus, true);
	}
	catch(NullPointerException ex) {
		ex.printStackTrace();
		Reporter.log("ID does not exist");
	}
	
	}

}
