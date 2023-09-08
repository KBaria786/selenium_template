package com.automation.selenium_template.reports;

public enum ReportStatus {
	DEBUG,
	ERROR,
	FAIL,
	FATAL,
	INFO,
	PASS,
	SKIP,
	WARNINIG;

	@Override
	public String toString() {
		switch (this) { 
	      case DEBUG: return "DEBUG"; 
	      case ERROR: return "ERROR"; 
	      case FAIL: return "FAIL"; 
	      case FATAL: return "FATAL"; 
	      case INFO : return "INFO";
	      case PASS: return "PASS"; 
	      case SKIP: return "SKIP"; 
	      case WARNINIG : return "WARNINIG";
	      default: throw new IllegalStateException(); 
	    } 
	}
	
	
}
