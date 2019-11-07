/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.pulpo.connector.de.functional.test.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Julio Camarero
 */
public class FunctionalTestLocatorsHelper {

	public static void clickElement(WebDriver webDriver, By locator) {
		waitForElementToBeClickable(webDriver, locator);

		WebElement webElement = webDriver.findElement(locator);

		webElement.click();
	}

	public static WebElement fetchElement(
		WebDriver webDriver, By containerLocator, By elementLocator) {

		List<WebElement> elements = fetchElements(
			webDriver, containerLocator, elementLocator);

		if (!elements.isEmpty()) {
			return elements.get(0);
		}

		return null;
	}

	public static List<WebElement> fetchElements(
		WebDriver webDriver, By containerLocator, By elementLocator) {

		WebElement container = getElement(webDriver, containerLocator);

		return container.findElements(elementLocator);
	}

	public static WebElement getClickableElement(
		WebDriver webDriver, By locator) {

		waitForElementToBeClickable(webDriver, locator);

		return webDriver.findElement(locator);
	}

	public static WebElement getElement(WebDriver webDriver, By locator) {
		waitForElementToBePresent(webDriver, locator);

		return webDriver.findElement(locator);
	}

	public static void waitForElementNotToBeVisible(
		WebDriver webDriver, By locator) {

		WebDriverWait webDriverWait = new WebDriverWait(
			webDriver, _DEFAULT_TIME_OUT_IN_SECONDS);

		webDriverWait.until(
			ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public static void waitForElementToBeClickable(
		WebDriver webDriver, By locator) {

		WebDriverWait webDriverWait = new WebDriverWait(
			webDriver, _DEFAULT_TIME_OUT_IN_SECONDS);

		webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void waitForElementToBePresent(
		WebDriver webDriver, By locator) {

		WebDriverWait webDriverWait = new WebDriverWait(
			webDriver, _DEFAULT_TIME_OUT_IN_SECONDS);

		webDriverWait.until(
			ExpectedConditions.presenceOfElementLocated(locator));
	}

	public static void waitForElementToBeVisible(
		WebDriver webDriver, By locator) {

		WebDriverWait webDriverWait = new WebDriverWait(
			webDriver, _DEFAULT_TIME_OUT_IN_SECONDS);

		webDriverWait.until(
			ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitFortextToBePresentInElementLocated(
		WebDriver webDriver, String text, By locator) {

		WebDriverWait webDriverWait = new WebDriverWait(
			webDriver, _DEFAULT_TIME_OUT_IN_SECONDS);

		webDriverWait.until(
			ExpectedConditions.textToBePresentInElementLocated(locator, text));
	}

	private static final long _DEFAULT_TIME_OUT_IN_SECONDS = 30;

}