package com.automation.selenium_template.driver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public interface DriverController {
	
	/**
	 * Wait for the element to be visible and clickable on the DOM for default explicit wait duration
	 * 
	 * @param byLocator the By locator for the web element
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeClickable(By ByLocator);
	
	/**
	 * Wait for the element to be visible and clickable on the DOM for the specified explicit wait duration
	 * 
	 * @param byLocator the By locator for the web element
	 * @param explicitWaitDuration the explicit wait duration 
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeClickable(By byLocator, Duration explicitWaitDuration);

	/**
	 * Wait for the element to be visible and clickable on the DOM for default explicit wait duration
	 * 
	 * @param webElement the web element
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeClickable(WebElement webElement);
	
	/**
	 * Wait for the element to be visible and clickable on the DOM for the specified explicit wait duration
	 * 
	 * @param byLocator the By locator for the web element
	 * @param explicitWaitDuration the explicit wait duration 
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeClickable(WebElement webElement, Duration explicitWaitDuration);
	
	/**
	 * Wait for the element to be visible on the DOM for default explicit wait duration
	 * 
	 * @param byLocator the By locator for the web element
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeVisible(By byLocator);
	
	/**
	 * Wait for the element to be visible on the DOM for the specified explicit wait duration
	 * 
	 * @param byLocator the By locator for the web element
	 * @param explicitWaitDuration the explicit wait duration 
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeVisible(By byLocator, Duration explicitWaitDuration);
	
	/**
	 * Wait for the element to be visible on the DOM for default explicit wait duration
	 * 
	 * @param webElement the web element
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeVisible(WebElement webElement);
	
	/**
	 * Wait for the element to be visible on the DOM for the specified explicit wait duration
	 * 
	 * @param webElement the web element
	 * @param explicitWaitDuration the explicit wait duration 
	 * @return true if the web element is visible, otherwise false
	 */
	public boolean waitForElementToBeVisible(WebElement webElement, Duration explicitWaitDuration);
	
	/**
	 * Find the first web element using the given By locator
	 * 
	 * @param byLocator the By locator for the web element
	 * @return the web element once it is located
	 */
	public WebElement findElement(By byLocator);
	
	/**
	 * Find the first select element using the given By locator
	 * 
	 * @param byLocator the By locator for the web element
	 * @return the web element once it is located
	 */
	public Select findSelectElement(By byLocator);
	
	/**
	 * Sets the web driver implicit wait duration to zero seconds
	 */
	public void nullifyImplicitWait();
	
	/**
	 * Sets the web driver implicit wait duration to default implicit wait duration
	 */
	public void setDefaultImplicitWait();
	
	/**
	 * Sets the web driver implicit wait duration to specified duration
	 * 
	 * @param duration the duration to wait
	 */
	public void setImplicitWait(Duration duration);
}
