package com.automation.selenium_template.reports;

import java.io.ByteArrayInputStream;

import org.apache.commons.lang3.StringUtils;

import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

public class AllureReportingProvider implements ReportingProvider {


    @Override
    public void reportStepSuccess(String stepDescription, String details) {
        details = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, details) : details;
        Allure.step(details, Status.PASSED);
    }

    @Override
    public void reportStepSuccess(String stepDescription, String details, byte[] screenshot) {
        details = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, details) : details;
        Allure.step(details, () -> Allure.addAttachment(stepDescription, new ByteArrayInputStream(screenshot)));
    }

    @Override
    public void reportStepFailure(String stepDescription, String details) {
        details = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, details) : details;
        Allure.step(details, Status.BROKEN);
    }

    @Override
    public void reportStepFailure(String stepDescription, String details, byte[] screenshot) {
    	details = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, details) : details;
        Allure.step(details);
    }

    @Override
    public void reportStepFailure(String stepDescription, String details, Throwable exception) {
        details = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, details) : details;
        Allure.step(details, () -> {
        	throw exception.getCause() != null ? exception.getCause() : exception;
        });
    }

    @Override
    public void reportStepFailure(String stepDescription, String details, Throwable exception, byte[] screenshot) {
    	details = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, details) : details;
        Allure.step(details, () -> {
        	Allure.addAttachment("", new ByteArrayInputStream(screenshot));
        	throw exception.getCause() != null ? exception.getCause() : exception;
        });
    }


}
