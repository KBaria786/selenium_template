package com.automation.selenium_template;

import org.testng.annotations.Test;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

public class AllureTests {

	@Test(description = "sample test")
	public void sampleTest() throws Exception {
		Exception e = new RuntimeException("Some exception");
		Allure.step("Step one");
		
		Allure.step("Step two", () -> {
			Allure.addAttachment("Step two", "screenshot");
		});
		
		Allure.step("Step 4", () -> {
			Allure.step("Step 4", Status.FAILED);
			Allure.addAttachment("", "Attachment");
		});
		
		Allure.step("Step 5", Status.SKIPPED);
		
		StepResult step6 = new StepResult();
		step6.setName("step6");
		AllureLifecycle allure = Allure.getLifecycle();
		
		allure.startStep(step6.getName(), step6);
		allure.updateStep(step -> {
			step.setStatus(Status.FAILED);
			Allure.addAttachment("life cycle", "life cycle");
		});
		allure.stopStep(step6.getName());
		
		Allure.step("Test over", () -> {
			Allure.step("Exception occured", () -> {throw e;});
		});
	}
	
}
