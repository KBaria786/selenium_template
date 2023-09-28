package com.automation.selenium_template.reports;

public interface ReportingProvider {

	public void reportStepSuccess(String stepDescription, String details);
	public void reportStepSuccess(String stepDescription, String details, byte[] screenshot);
	public void reportStepFailure(String stepDescription, String details);
	public void reportStepFailure(String stepDescription, String details, byte[] screenshot);
	public void reportStepFailure(String stepDescription, String details, Throwable exception);
	public void reportStepFailure(String stepDescription, String details, Throwable exception, byte[] screenshot);
	
}
