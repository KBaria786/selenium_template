package com.automation.selenium_template.reports;

import org.apache.commons.lang3.StringUtils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

public class AllureReportsUtil implements ReportUtil {

	@Override
	public void report(ReportStatus reportStatus, String stepDescription, String message, String screenShotPath) {
		Status status = getStatus(reportStatus);
		message = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, message) : message;
		Allure.step(message, status);
	}

	@Override
	public void report(ReportStatus reportStatus, String stepDescription, String message, String screenShotPath,
			Exception e) {
		message = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, message) : message;
		Allure.step(message, () -> {throw e;});
		
	}
	
	private Status getStatus(ReportStatus reportStatus) {
		switch (reportStatus) {
			case DEBUG:
			case INFO:
		    case PASS: return Status.PASSED;
		    
		    case WARNINIG:
		    case ERROR: return Status.BROKEN;
		    case FAIL:
		    case FATAL: return Status.FAILED;
		    
		    case SKIP: return Status.SKIPPED;
		    default: return Status.PASSED;
		}
	}

}
