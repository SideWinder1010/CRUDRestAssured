package com.qa.furniture.test;

import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.furniture.Base.BaseClass;
import com.qa.furniture.dto.ProductListDTO;
import com.qa.furniture.resources.XLSWorker;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import junit.framework.AssertionFailedError;

public class FurniturePostTest extends BaseClass {

	private static ExtentTest extentReportLogger = null;
	private static String testCategory = "Furniture API";
	String appender = null;

	@BeforeMethod
	public void setUp(){
		this.extentReportLogger = BaseClass.extentReportLogger;
		appender = "/rest/api/product/";
	}
	
	@DataProvider
	public Object[][] getProductData() throws Exception {
	
		Object data[][] = XLSWorker.getTestData("DataSheet");
		return data;
	}
	
	
	@Test(priority = 1, dataProvider = "getProductData")
	public void postProductTest(String availability, String categoryId, String color,String createdOn,String description,String discount, String materialDescription, String name, String price, String updateOn, String warranty ) 
	{

		try
		{
		
		Reporter.log("Post Value API Test begins",true);
		RequestSpecification httpRequest = RestAssured.given();
		
		JSONObject requestParams = new JSONObject();
		
	
		Double catIdDouble = Double.parseDouble(categoryId);
		Reporter.log(catIdDouble.toString());
		Double discDouble = Double.parseDouble(discount);
		Reporter.log(discount.toString());
		Double warrnDouble = Double.parseDouble(warranty);
		
		Double priceDouble = Double.parseDouble(price);
		
		ProductListDTO pdto =new ProductListDTO(availability,catIdDouble,color, createdOn, description, discDouble,  materialDescription,  name,priceDouble,  updateOn,warrnDouble);		
		
		httpRequest.header("Content-Type","application/json");		
		
		httpRequest.body(pdto);
		
		//Response Object
		
		Response response = httpRequest.request(Method.POST,appender);		
		
		//httpRequest.log(LogDetail.BODY);
		httpRequest.given().log().toString();
		
		String responseBody = response.getBody().asString();
	
		Reporter.log(responseBody);
		//Status code validation
		int statusCode=response.getStatusCode();
		Reporter.log("Status code is"+statusCode);
		Assert.assertEquals(statusCode, 201);
		
		if(statusCode == 201)
		{
			extentReportLogger.log(LogStatus.PASS, "***The test case has passed**");
			BaseClass.reportTestCaseStatus(extentReportLogger, "Post Method", true,responseBody);
		} else {
			extentReportLogger.log(LogStatus.FAIL, "The testcase has failed ===>>");
			BaseClass.reportTestCaseStatus(extentReportLogger, "Post Method", false,responseBody);
		}
		
		}
		catch(NullPointerException ex) {
			ex.printStackTrace();
		}
	
	}

}
