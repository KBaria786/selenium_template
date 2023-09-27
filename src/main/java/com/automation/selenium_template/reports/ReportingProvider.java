package com.automation.selenium_template.reports;

import java.io.File;

public interface ReportingProvider {

	public void reportStepSuccess(String stepDescription, String details, byte[] screenshot);
	public void reportStepFailure(String stepDescription, String details, byte[] screenshot);
	public void reportStepFailure(String stepDescription, String details, Throwable exception, byte[] screenshot);
	
}
