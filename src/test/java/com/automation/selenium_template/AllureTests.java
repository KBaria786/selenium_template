package com.automation.selenium_template;

import org.testng.annotations.Test;

import io.qameta.allure.Allure;

public class AllureTests {

	@Test(description = "something")
	public void test() throws Exception {
		Exception e = new RuntimeException("Some exception");
		Allure.step("Step one");
		Allure.step("Step two", () -> {
			Allure.addAttachment("Step two", "screenshot");
		});
		Allure.step("Test over", () -> {
			Allure.step("Exception occured", () -> {throw e;});
		});
	}
}
