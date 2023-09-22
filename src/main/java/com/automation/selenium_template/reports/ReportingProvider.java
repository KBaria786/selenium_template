package com.automation.selenium_template.reports;

import java.io.File;

public interface ReportingProvider {

	public void log(ReportStatus reportStatus, String message);
	public void log(ReportStatus reportStatus, String message, File ScreenshotFile);
	public void logInfo(String message);
	public void logInfo(String message, File screenshotFile);
	public void logException(String message, Exception e);
	public void logException(String message, Exception e, File ScreenshotFile);
	
}
