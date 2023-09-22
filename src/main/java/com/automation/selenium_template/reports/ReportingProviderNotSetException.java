package com.automation.selenium_template.reports;

public class ReportingProviderNotSetException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ReportingProviderNotSetException() {
		super("Reporting provider has not been set. Please set a valid reporting provider using ReportingUtil.setProvider().");
	}
	
	

}
