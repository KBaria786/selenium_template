package com.automation.selenium_template.reports;

import java.io.File;

public class ReportingUtil {

	private static ReportingProvider provider;
	
	public static void setProvider(ReportingProvider provider) {
		ReportingUtil.provider = provider;
	}
	
	public static void logInfo(String message) {
		checkProvider();
		provider.logInfo(message);
	}
	
	public static void logInfo(String message, File screenshotFile) {
		checkProvider();
		provider.logInfo(message, screenshotFile);
	}
	
	public static void logException(String messsage, Exception e) {
		checkProvider();
		provider.logException(messsage, e);
	}
	
	public static void logException(String messsage, Exception e, File screenshotFile) {
		checkProvider();
		provider.logException(messsage, e, screenshotFile);
	}
	
	private static void checkProvider() {
		if(provider == null) {
			throw new ReportingProviderNotSetException();
		}
	}
	
}
