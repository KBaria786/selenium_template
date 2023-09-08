package com.automation.selenium_template.reports;

public abstract class ReportUtil {

	public abstract void log(ReportStatus reportStatus, String stepDescription, String message, String screenshotPath);
	
	public abstract void log(ReportStatus reportStatus, String stepDescription, String message, Exception exception, String screenShotPath);
	
}
