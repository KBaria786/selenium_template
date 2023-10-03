package com.automation.selenium_template;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestRunner {
	
	private ExtentReports extentReports = new ExtentReports();
	
	@BeforeMethod
	public void beforeMethod() {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("C:/Users/10692688/Desktop/Workspace/Selenium/selenium-template/reports/sample_report.html");
		extentReports.attachReporter(extentSparkReporter);
	}

	@Test
	public void sampleTest() throws IOException {
		ExtentTest test = extentReports.createTest("sample test");
		String stepDescription = "Click on element";
		String message = "click operation successful on element";
		NoSuchElementException exception = new NoSuchElementException("element not found");
		String screenShotPath = "C:/Users/10692688/Pictures/ss.png";
		Media screenShotMedia = MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build();
		
		test.info(String.format("<b>%s</b><br>%s", stepDescription, message));
		test.info(String.format("<b>%s</b><br>%s", stepDescription, message));
		test.log(Status.WARNING, String.format("<b>%s</b><br>%s", stepDescription, exception.getMessage()), null, screenShotMedia);
		test.log(Status.WARNING, exception);
	}
	
	@AfterMethod
	public void afterMethod() {
		extentReports.flush();
	}
	
}
