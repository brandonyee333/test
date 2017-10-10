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

import com.cucumber.listener.ExtentCucumberFormatter;

import cucumber.api.Scenario;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.URL;

import java.nio.charset.StandardCharsets;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;

import org.junit.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * @author Julio Camarero
 */
public class FunctionalTestUtil {

	public static void checkJavascriptErrors(WebDriver webDriver) {
		final List<JavaScriptError> javaScriptErrors =
			filterIgnorableJavascriptErrors(
				JavaScriptError.readErrors(webDriver));

		Assert.assertTrue(
			printErrorMessage(javaScriptErrors), javaScriptErrors.isEmpty());
	}

	public static void createReports() {
		File reportFile = new File("build/reports/tests/cucumber/index.html");

		ExtentCucumberFormatter.initiateExtentCucumberFormatter(
			reportFile, false);
	}

	public static String deleteJsonFromURL(
			String urlString, Optional<String> userName,
			Optional<String> password)
		throws IOException {

		return jsonFromURL("DELETE", urlString, userName, password);
	}

	public static String getJsonFromURL(String urlString) throws IOException {
		return getJsonFromURL(urlString, Optional.empty(), Optional.empty());
	}

	public static String getJsonFromURL(
			String urlString, Optional<String> userName,
			Optional<String> password)
		throws IOException {

		return jsonFromURL("GET", urlString, userName, password);
	}

	public static String jsonFromURL(
			String method, String urlString, Optional<String> userName,
			Optional<String> password)
		throws IOException {

		return jsonFromURL(method, urlString, userName, password, null);
	}

	public static String jsonFromURL(
			String method, String urlString, Optional<String> userName,
			Optional<String> password, String body)
		throws IOException {

		URL url = new URL(urlString);

		HttpURLConnection connection = (HttpURLConnection)url.openConnection();

		_addAuthenticationHeaderIfNeeded(userName, password, connection);

		connection.setRequestMethod(method);
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");

		if (body != null) {
			connection.setDoOutput(true);

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
				connection.getOutputStream());

			outputStreamWriter.write(body);
			outputStreamWriter.close();
		}

		if (connection.getResponseCode() != 200) {
			throw new RuntimeException(
				"Failed : HTTP error code : " + connection.getResponseCode());
		}

		BufferedReader br = new BufferedReader(
			new InputStreamReader(
				connection.getInputStream(), StandardCharsets.UTF_8));

		StringBuilder stringBuilder = new StringBuilder();

		String line;

		try {
			while ((line = br.readLine()) != null) {
				stringBuilder.append(line);
			}
		}
		finally {
			br.close();
		}

		connection.disconnect();

		return stringBuilder.toString();
	}

	public static String postJsonFromURL(
			String urlString, Optional<String> userName,
			Optional<String> password, String body)
		throws IOException {

		return jsonFromURL("POST", urlString, userName, password, body);
	}

	public static void scroll(WebDriver browser) {
		JavascriptExecutor jse = (JavascriptExecutor)browser;

		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void scroll(WebDriver browser, int height) {
		JavascriptExecutor jse = (JavascriptExecutor)browser;

		jse.executeScript("window.scrollBy(0," + height + ")");
	}

	public static void takeScreenshot(Scenario scenario, WebDriver webDriver) {
		if (scenario.isFailed()) {
			final TakesScreenshot takesScreenshot = (TakesScreenshot)webDriver;

			final byte[] screenshot = takesScreenshot.getScreenshotAs(
				OutputType.BYTES);

			scenario.embed(screenshot, "image/png");
		}
	}

	protected static List<JavaScriptError> filterIgnorableJavascriptErrors(
		List<JavaScriptError> javaScriptErrors) {

		Stream<JavaScriptError> stream = javaScriptErrors.stream();

		return stream.filter(
			error -> !isIgnorable(error)
		).collect(
			Collectors.toList()
		);
	}

	protected static boolean isIgnorable(JavaScriptError javaScriptError) {
		String log = javaScriptError.getErrorMessage();

		if (log.startsWith(
				"mutating the [[Prototype]] of an object will cause your " +
					"code to run very slowly")) {

			return true;
		}

		if (log.startsWith("NetworkError: A network error occurred.")) {
			return true;
		}

		return false;
	}

	protected static String printErrorMessage(
		List<JavaScriptError> javaScriptErrors) {

		StringBuilder sb = new StringBuilder(2 + 3 * javaScriptErrors.size());

		sb.append(javaScriptErrors.size());
		sb.append(" Javascript Errors detected: \n");

		for (JavaScriptError javaScriptError : javaScriptErrors) {
			sb.append(" * ");
			sb.append(javaScriptError.toString());
			sb.append("\n");
		}

		return sb.toString();
	}

	private static void _addAuthenticationHeaderIfNeeded(
		Optional<String> userName, Optional<String> password,
		HttpURLConnection connection) {

		if (userName.isPresent() && password.isPresent()) {
			String authenticationString = userName.get() + ":" + password.get();

			byte[] authenticationBytes = authenticationString.getBytes(
				StandardCharsets.UTF_8);

			String authenticationEncodedString =
				Base64.getEncoder().encodeToString(authenticationBytes);

			connection.setRequestProperty(
				"Authorization", "Basic " + authenticationEncodedString);
		}
	}

}