package com.automation.selenium_template.reports;

public class ReportUtil {

    private static ReportingProvider reportingProvider;


    // getters and setters

    public static ReportingProvider getReportingProvider() {
        return reportingProvider;
    }

    public static void setReportingProvider(ReportingProvider reportingProvider) {
        ReportUtil.reportingProvider = reportingProvider;
    }


    // reporting methods

    public static void reportStepSuccess(String stepDescription, String details) {
        checkReportingProvider();
        reportingProvider.reportStepSuccess(stepDescription, details);
    }
    public static void reportStepSuccess(String stepDescription, String details, byte[] screenshot) {
        checkReportingProvider();
        reportingProvider.reportStepSuccess(stepDescription, details, screenshot);
    }
    public static void reportStepFailure(String stepDescription, String details) {
        checkReportingProvider();
        reportingProvider.reportStepFailure(stepDescription, details);
    }
    public static void reportStepFailure(String stepDescription, String details, byte[] screenshot) {
        checkReportingProvider();
        reportingProvider.reportStepFailure(stepDescription, details, screenshot);
    }
    public static void reportStepFailure(String stepDescription, String details, Throwable exception) {
        checkReportingProvider();
        reportingProvider.reportStepFailure(stepDescription, details, exception);
    }
    public static void reportStepFailure(String stepDescription, String details, Throwable exception, byte[] screenshot) {
        checkReportingProvider();
        reportingProvider.reportStepFailure(stepDescription, details, exception, screenshot);
    }

    private static void checkReportingProvider() {
        if(reportingProvider == null) {
            throw new IllegalStateException("No implementation of ReportingProvider has been set. use setReportingProvider to set an implementation.");
        }
    }

}
