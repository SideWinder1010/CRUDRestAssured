package com.qa.furniture.Util;


import java.io.File;

import javax.swing.text.html.HTML;

import org.apache.commons.io.FileUtils;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


/**
 * @author vaishali
 *
 */
public class ReportManagerAPI {

	private static ExtentReports INSTANCE = null;

	private ReportManagerAPI() {

	}

	public synchronized static ExtentReports getReporter(String filePath, boolean replaceExisting) {
		if (INSTANCE == null) {
			INSTANCE = new ExtentReports(filePath, replaceExisting);
			
		}
		return INSTANCE;
	}

	
}