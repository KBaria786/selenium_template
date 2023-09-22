package com.automation.selenium_template;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.automation.selenium_template.driver.DriverControllerV3;
import com.automation.selenium_template.reports.AllureReportingProvider;
import com.automation.selenium_template.reports.ReportingUtil;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;

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
		
		Allure.step("Test over", () -> {
			Allure.step("Exception occured", () -> {throw e;});
		});
	}
	
	@Test
	public void driverControllerTest() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		DriverControllerV3 dc = new DriverControllerV3(driver);
		ReportingUtil.setProvider(new AllureReportingProvider());
		dc.get("Launch google", "https://google.com");
		dc.waitForInvisibilityOfElement("step description", "name~q", Duration.ofSeconds(3));
	}
	
}
