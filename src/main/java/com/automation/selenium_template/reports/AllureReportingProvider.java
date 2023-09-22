package com.automation.selenium_template.reports;

import java.io.File;
import java.io.FileInputStream;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

public class AllureReportingProvider implements ReportingProvider {

	@Override
	public void log(ReportStatus reportStatus, String message) {
		Allure.step(message, getStatus(reportStatus));
	}
	
	@Override
	public void log(ReportStatus reportStatus, String message, File ScreenshotFile) {
		Allure.step(message, getStatus(reportStatus));
	}
	
	@Override
	public void logInfo(String message) {
		Allure.step(message);
	}
	
	@Override
	public void logInfo(String message, File screenshotFile) {
		Allure.step(message, () -> Allure.addAttachment(message, new FileInputStream(screenshotFile)));
	}

	@Override
	public void logException(String message, Exception e) {
		Allure.step(message, () -> {throw e;});
	}

	@Override
	public void logException(String message, Exception e, File ScreenshotFile) {
		Allure.step(message, () -> {
			Allure.addAttachment(message, new FileInputStream(ScreenshotFile));
			throw e;
		});
	}
	
	private Status getStatus(ReportStatus reportStatus) {
		switch (reportStatus) { 
	     	case DEBUG:
     		case INFO:
     		case PASS: return Status.PASSED;
     		
     		case WARNINIG:
     		case FATAL:
	     	case ERROR: return Status.BROKEN;
	     	
	     	case FAIL: return Status.FAILED; 
     		
     		case SKIP: return Status.SKIPPED;
     		
  			default: throw new IllegalStateException(); 
	    }
	}

}
