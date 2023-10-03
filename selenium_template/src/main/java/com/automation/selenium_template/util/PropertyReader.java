package com.automation.selenium_template.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyReader {
	
	private static final Logger logger = LoggerFactory.getLogger(PropertyReader.class);
	
	private static Properties properties;

	public static Properties getProperties(String fileName) {
		try {
			properties = new Properties();
			properties.load(PropertyReader.class.getClassLoader().getResourceAsStream(fileName));
			//logging
			logger.info("Successfully got properties file");
			return properties;
		} catch (IOException e) {
			//logging
			logger.error("Exception occurred while getting properties file", e);
		}
		return null;
	}
	
}
