package com.automation.selenium_template.reports;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;

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

        AllureLifecycle allureLifecycle = Allure.getLifecycle();

        StepResult stepResult = new StepResult();

        allureLifecycle.startStep(details, stepResult);
        allureLifecycle.updateStep(step -> {
            step.setStatus(Status.BROKEN);
            Allure.addAttachment(stepDescription, new ByteArrayInputStream(screenshot));
        });
        allureLifecycle.stopStep(details);
    }

    @Override
    public void reportStepFailure(String stepDescription, String details, Throwable exception) {
        details = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, details) : details;

        AllureLifecycle allureLifecycle = Allure.getLifecycle();

        StepResult stepResult = new StepResult();

        allureLifecycle.startStep(details, stepResult);
        String finalDetails = details;
        allureLifecycle.updateStep(step -> {
            step.setStatus(Status.BROKEN);
            Allure.step(finalDetails, () -> {throw(exception);});
        });
        allureLifecycle.stopStep(details);
    }

    @Override
    public void reportStepFailure(String stepDescription, String details, Throwable exception, byte[] screenshot) {
        details = StringUtils.isNotBlank(stepDescription) ? String.format("%s: %s", stepDescription, details) : details;

        AllureLifecycle allureLifecycle = Allure.getLifecycle();

        StepResult stepResult = new StepResult();

        allureLifecycle.startStep(details, stepResult);
        String finalDetails = details;
        allureLifecycle.updateStep(step -> {
            step.setStatus(Status.BROKEN);
            Allure.addAttachment(stepDescription, new ByteArrayInputStream(screenshot));
            Allure.step(finalDetails, () -> {throw(exception);});
        });
        allureLifecycle.stopStep(details);
    }


}
