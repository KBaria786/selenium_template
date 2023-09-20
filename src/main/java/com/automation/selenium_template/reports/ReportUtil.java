package com.automation.selenium_template.reports;

public interface ReportUtil {
	
	public void report(ReportStatus reportStatus, String stepDescription, String message, String screenShotPath);
	public void report(ReportStatus reportStatus, String stepDescription, String message, String screenShotPath, Exception e);
	
}
