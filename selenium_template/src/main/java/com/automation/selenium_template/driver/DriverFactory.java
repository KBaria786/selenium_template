package com.automation.selenium_template.driver;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.automation.selenium_template.util.PropertyReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class);
	
	private static WebDriver webDriver;
	
	private static Properties driverConfigProperties;
	
	public static WebDriver getDriver() {
		driverConfigProperties = PropertyReader.getProperties("driver-config.properties");
		switch(driverConfigProperties.getProperty("browser")) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				webDriver = new ChromeDriver();
				logger.info("chrome driver setup");
			case "edge":
				WebDriverManager.edgedriver().setup();
				webDriver = new EdgeDriver();
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				webDriver = new FirefoxDriver();
		}
		return webDriver;
	}
	
}
