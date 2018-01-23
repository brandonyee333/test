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

package com.liferay.pulpo.connector.de.contacts.functional.test;

import com.liferay.pulpo.connector.de.functional.test.util.FunctionalTestLocatorsHelper;
import com.liferay.pulpo.connector.de.functional.test.util.FunctionalTestUtil;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import cucumber.runtime.arquillian.CukeSpace;

import java.io.IOException;

import java.net.URL;
import java.net.URLEncoder;

import java.util.Optional;
import java.util.UUID;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Eduardo Garcia
 * @author Ruben Pulido
 */
@CucumberOptions(
	features = "features/contacts_end_to_end.feature",
	plugin = {"com.cucumber.listener.ExtentCucumberFormatter", "pretty"}
)
@Ignore
@RunAsClient
@RunWith(CukeSpace.class)
public class ContactConnectorTest {

	@Drone
	public static WebDriver browser;

	@BeforeClass
	public static void setUpClass() throws IOException {
		_contactCount = 0L;

		Assume.assumeNotNull(_CONTACTS_ENGINE_URL);
		Assume.assumeNotNull(_CONTACTS_PROVIDER_ID);
		Assume.assumeNotNull(_PROJECT_ID);

		FunctionalTestUtil.createReports();
	}

	@AfterClass
	public static void tearDownClass() throws Throwable {
		_deleteProjectContacts();
	}

	@When("^I click in the (.+) button$")
	public void clickInButton(String text) throws Exception {
		if (text.equals("Save")) {

			// To prevent Chat bar to cover the button and make it unclickable

			FunctionalTestUtil.scroll(browser);
		}

		FunctionalTestLocatorsHelper.clickElement(
			browser,
			By.xpath(String.format("//button[contains(.,' %s ')]", text)));
	}

	@When("^I click in the (.+) link$")
	public void clickInLink(String buttonText) throws Exception {
		FunctionalTestLocatorsHelper.clickElement(
			browser, By.xpath("//a[contains(., '" + buttonText + "')]"));
	}

	@Given("^I enter (.+) as email")
	public void enterTextInEmailField(String text) {
		String locator =
			"//input[@name='_com_liferay_login_web_portlet_" +
				"LoginPortlet_login']";

		FunctionalTestLocatorsHelper.waitForElementToBeVisible(
			browser, By.xpath(locator));

		WebElement element = FunctionalTestLocatorsHelper.getElement(
			browser, By.xpath(locator));

		element.sendKeys(text);
	}

	@Given("^I enter (.+) as password")
	public void enterTextInPasswordField(String text) {
		String locator = "//input[contains(@name,'password')]";

		FunctionalTestLocatorsHelper.waitForElementToBeVisible(
			browser, By.xpath(locator));

		WebElement element = FunctionalTestLocatorsHelper.getElement(
			browser, By.xpath(locator));

		element.sendKeys(text);
	}

	@Given("^I enter (.+) as Email Address")
	public void enterUserEmailAddress(String text) {
		_email = _getRandomText(text);

		String locator =
			"//input[@id='_com_liferay_users_admin_web_portlet_" +
				"UsersAdminPortlet_emailAddress']";

		FunctionalTestLocatorsHelper.waitForElementToBeVisible(
			browser, By.xpath(locator));

		FunctionalTestLocatorsHelper.clickElement(browser, By.xpath(locator));

		WebElement element = FunctionalTestLocatorsHelper.getElement(
			browser, By.xpath(locator));

		element.sendKeys(_email);
	}

	@Given("^I enter (.+) as First Name")
	public void enterUserFirstName(String text) {
		FunctionalTestUtil.scroll(browser, 250);

		String locator =
			"//input[@id='_com_liferay_users_admin_web_portlet_" +
				"UsersAdminPortlet_firstName']";

		FunctionalTestLocatorsHelper.waitForElementToBeVisible(
			browser, By.xpath(locator));

		FunctionalTestLocatorsHelper.clickElement(browser, By.xpath(locator));

		WebElement element = FunctionalTestLocatorsHelper.getElement(
			browser, By.xpath(locator));

		element.sendKeys(_getRandomText(text));
	}

	@Given("^I enter (.+) as Last Name")
	public void enterUserLastName(String text) {
		FunctionalTestUtil.scroll(browser, 250);

		String locator =
			"//input[@id='_com_liferay_users_admin_web_portlet_" +
				"UsersAdminPortlet_lastName']";

		FunctionalTestLocatorsHelper.waitForElementToBeVisible(
			browser, By.xpath(locator));

		FunctionalTestLocatorsHelper.clickElement(browser, By.xpath(locator));

		WebElement element = FunctionalTestLocatorsHelper.getElement(
			browser, By.xpath(locator));

		element.sendKeys(_getRandomText(text));
	}

	@Given("^I enter (.+) as Screen Name")
	public void enterUserScreenName(String text) {
		_screenName = _getRandomText(text);

		String locator =
			"//input[@id='_com_liferay_users_admin_web_portlet_" +
				"UsersAdminPortlet_screenName']";

		FunctionalTestLocatorsHelper.waitForElementToBeVisible(
			browser, By.xpath(locator));

		FunctionalTestLocatorsHelper.clickElement(browser, By.xpath(locator));

		WebElement element = FunctionalTestLocatorsHelper.getElement(
			browser, By.xpath(locator));

		element.sendKeys(_screenName);
	}

	@And("^I click in the Control Panel element$")
	public void iClickInTheControlPanelElement() throws Throwable {
		String controlPanelLocator =
			"//a[@data-qa-id='productMenuControlPanelCategory']";

		String usersPanelLocator =
			"//a[@data-qa-id='appGroup' and " +
				"@href='#panel-manage-control_panel_users']";

		_retryClickingInElement(controlPanelLocator, usersPanelLocator);
	}

	@And("^I click in the Liferay DXP element$")
	public void iClickInTheLiferayDXPElement() throws Throwable {
		String locator = "//span[contains(@class,'site-name truncate-text')]";

		FunctionalTestLocatorsHelper.clickElement(browser, By.xpath(locator));
	}

	@And("^I click in the Menu element$")
	public void iClickInTheMenuElement() throws Throwable {
		String controlPanelLocator =
			"//a[@data-qa-id='productMenuControlPanelCategory']";

		try {
			FunctionalTestLocatorsHelper.waitForElementToBeVisible(
				browser, By.xpath(controlPanelLocator));

			return;
		}
		catch (TimeoutException te) {
			String productMenuLocator = "//a[@data-qa-id='productMenu']";

			_retryClickingInElement(productMenuLocator, controlPanelLocator);
		}
	}

	@And("^I click in the plus element$")
	public void iClickInThePlusElement() throws Throwable {
		String locator = "//a[@data-qa-id='addButton']";

		FunctionalTestUtil.scroll(browser);

		FunctionalTestLocatorsHelper.waitForElementToBeVisible(
			browser, By.xpath(locator));

		FunctionalTestLocatorsHelper.clickElement(browser, By.xpath(locator));
	}

	@And("^I click in the Users and Organizations element$")
	public void iClickInTheUsersAndOrganizationsElement() throws Throwable {
		String usersAndOrganizationsLocator =
			"//a[@data-title='Users and Organizations']";

		String addButonLocator = "//a[@data-qa-id='addButton']";

		_retryClickingInElement(usersAndOrganizationsLocator, addButonLocator);
	}

	@And("^I click in the Users element$")
	public void iClickInTheUsersElement() throws Throwable {
		String usersPanelLocator =
			"//a[@data-qa-id='appGroup' and " +
				"@href='#panel-manage-control_panel_users']";

		String usersAndOrganizationsElementLocator =
			"//a[@data-title='Users and Organizations']";

		_retryClickingInElement(
			usersPanelLocator, usersAndOrganizationsElementLocator);
	}

	@And("^I wait for (\\d+) seconds for the contact to be processed$")
	public void iWaitForSecondsForTheContactToBeProcessed(int seconds)
		throws Throwable {

		Thread.sleep(seconds * 1000);
	}

	@Given("^I am in the Liferay main page$")
	public void navigateInClientWindowToURL() {
		browser.get(_url.toExternalForm());

		Assert.assertEquals("Welcome - Liferay DXP", browser.getTitle());
	}

	@After
	public void tearDown(Scenario scenario) throws Throwable {
		FunctionalTestUtil.checkJavascriptErrors(browser);

		FunctionalTestUtil.takeScreenshot(scenario, browser);
	}

	@Then(
		"^The contact containing the entered Screen Name is visible in the " +
			"list of contacts on the client$"
	)
	public void theContactContainingIsVisibleInTheListOfContactsOnTheClient()
		throws Throwable {

		FunctionalTestLocatorsHelper.waitForElementToBePresent(
			browser,
			By.xpath(String.format("//a[contains(.,'%s')]", _screenName)));
	}

	@And(
		"^There are at least (\\d+) more contacts and at most (\\d+) more " +
			"contacts? in the contacts engine with the entered email$"
	)
	public void thereAreMoreContactsInTheBackend(
			long minContactsIncrement, long maxContactsIncrement)
		throws Throwable {

		long contactCountNow = _countContactsByEmail(_email);

		String message = String.format(
			"Contacts before: %s. Contacts now: %s", _contactCount,
			contactCountNow);

		System.out.println(message);

		Assert.assertTrue(
			message,
			contactCountNow >= (_contactCount + minContactsIncrement) &&
			contactCountNow <= (_contactCount + maxContactsIncrement));
	}

	private static long _countContactsByEmail(String email) throws IOException {
		String wrapperQuery = String.format(
			_CONTACTS_PROVIDER_ENTRY_EMAIL_QUERY, email);

		String queryString =
			"wrapperQuery=" + URLEncoder.encode(wrapperQuery, "UTF-8");

		String contactSearchCountUrl = String.format(
			"%s/%s/%s/search_count?%s", _CONTACTS_ENGINE_URL, _PROJECT_ID,
			_CONTACTS_PROVIDER_ENTRY_SERVICE_PATH, queryString);

		try {
			System.out.println("Contact count url: " + contactSearchCountUrl);

			String hitsTotal = FunctionalTestUtil.postJsonFromURL(
				contactSearchCountUrl, Optional.empty(), Optional.empty(),
				null);

			System.out.println("Contact count response: " + hitsTotal);

			return Long.parseLong(hitsTotal);
		}
		catch (RuntimeException re) {
			if (re.getMessage().equals("Failed : HTTP error code : 404")) {
				System.out.println("Contact index does not exist yet.");

				return 0L;
			}
			else {
				throw re;
			}
		}
	}

	private static void _deleteProjectContacts() throws Throwable {
		String deleteContactUrl = String.format(
			"%s/%s/%s/delete_all", _CONTACTS_ENGINE_URL, _PROJECT_ID,
			_CONTACTS_ENTRY_SERVICE_PATH);

		System.out.println("Delete contact url: " + deleteContactUrl);

		String response = FunctionalTestUtil.deleteJsonFromURL(
			deleteContactUrl, Optional.empty(), Optional.empty());

		System.out.println("Delete contact response: " + response);

		Thread.sleep(2000);
	}

	private String _getRandomText(String text) {
		String uuid = String.valueOf(UUID.randomUUID());

		return uuid.replace("-", "") + text;
	}

	private void _retryClickingInElement(
			String locatorToClick, String locatorToWaitFor)
		throws Throwable {

		int maxNumberOfTries = 3;

		int currentTry = 1;

		boolean elementToWaitForVisible = false;

		while (!elementToWaitForVisible && (currentTry <= maxNumberOfTries)) {
			try {
				FunctionalTestLocatorsHelper.clickElement(
					browser, By.xpath(locatorToClick));

				FunctionalTestLocatorsHelper.waitForElementToBeVisible(
					browser, By.xpath(locatorToWaitFor));

				elementToWaitForVisible = true;
			}
			catch (TimeoutException te) {
				System.out.println(
					String.format(
						"Timeout waiting for element %s. Try: %s",
						locatorToWaitFor, currentTry));

				if (currentTry == maxNumberOfTries) {
					throw te;
				}

				currentTry++;
			}
		}
	}

	private static final String _CONTACTS_ENGINE_URL = System.getenv(
		"PULPO_TEST_CONTACT_ENGINE_URL");

	private static final String _CONTACTS_ENTRY_SERVICE_PATH = "contacts_entry";

	private static final String _CONTACTS_PROVIDER_ENTRY_EMAIL_QUERY =
		"{\"query\": {\"match_phrase\": " +
			"{\"propertiesMap.User#emailAddress.value\" : \"%s" + "\"}}}";

	private static final String _CONTACTS_PROVIDER_ENTRY_SERVICE_PATH =
		"contacts_provider_entry";

	private static final String _CONTACTS_PROVIDER_ID = System.getenv(
		"PULPO_CONTACTS_PROVIDER_ID");

	private static final String _PROJECT_ID = System.getenv("PULPO_PROJECT_ID");

	private static Long _contactCount;
	private static String _email;
	private static String _screenName;

	@ArquillianResource
	private URL _url;

}