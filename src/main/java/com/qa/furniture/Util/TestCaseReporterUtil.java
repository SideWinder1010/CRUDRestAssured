package com.qa.furniture.Util;

import java.io.File;

import org.apache.commons.io.FileUtils;


//import com.mindtree.sdet.ExtentReportListener.ReportManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestCaseReporterUtil {

	public TestCaseReporterUtil() {
	}

	/**
	 * This method is used to report the test cases to the ExtentReport for
	 * ExtentTest logging.
	 * 
	 * @param driver
	 * @param logger
	 * @param methodName
	 * @param testStatus
	 */
	public void reportTestCaseStatus(ExtentTest logger, String methodName,
			boolean testStatus,String response) {

		
		
		try {
			if (testStatus) {
				String passMessage = "Verified '" + methodName + "'. Test case PASSED." + " With Response:"+response;

				logger.log(LogStatus.PASS, passMessage);
			} else {
				String failMessage = "Verified '" + methodName + "'. Test case FAILED." + " With Response:"+response;
				logger.log(LogStatus.FAIL, failMessage);
			}
		} catch (Exception e) {
			System.out.println("Error closing the Test Suite in @AfterSuite method \n" + e.getMessage());
			e.printStackTrace();
		}
	}
}
