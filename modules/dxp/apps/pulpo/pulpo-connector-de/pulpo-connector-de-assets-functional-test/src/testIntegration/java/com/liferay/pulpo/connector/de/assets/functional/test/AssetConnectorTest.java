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

package com.liferay.pulpo.connector.de.assets.functional.test;

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

import java.util.Optional;
import java.util.UUID;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author Ruben Pulido
 */
@CucumberOptions(
	features = "features/assets_end_to_end.feature",
	plugin = {"com.cucumber.listener.ExtentCucumberFormatter", "pretty"}
)
@RunAsClient
@RunWith(CukeSpace.class)
public class AssetConnectorTest {

	@Drone
	public static WebDriver browser;

	@BeforeClass
	public static void setUpClass() throws IOException {
		FunctionalTestUtil.createReports();
		_assetCount = 0L;
	}

	@AfterClass
	public static void tearDownClass() throws Throwable {
		_deleteAssetByTitle(_title);
	}

	@When("^I click in the Add new element")
	public void clickAddNewElement() throws Exception {
		FunctionalTestLocatorsHelper.clickElement(
			browser, By.xpath("//span[contains(.,' Add New ')]"));
	}

	@When("^I click in the (.+) button$")
	public void clickInButton(String text) throws Exception {
		if (text.equals("Publish")) {

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

	@Given("^I enter (.+) as Web Content Title")
	public void enterWebContentTitle(String text) {
		_setTitle(text);

		String locator =
			"//input[@id='_com_liferay_journal_web_portlet_JournalPortlet_" +
				"title']";

		FunctionalTestLocatorsHelper.waitForElementToBeVisible(
			browser, By.xpath(locator));

		FunctionalTestLocatorsHelper.clickElement(browser, By.xpath(locator));

		WebElement element = FunctionalTestLocatorsHelper.getElement(
			browser, By.xpath(locator));

		element.sendKeys(_title);
	}

	@And("^I click in the Basic Web Content element$")
	public void iClickInTheBasicWebContentElement() throws Throwable {
		String locator = "//a[contains(.,'Basic Web Content')]";

		FunctionalTestLocatorsHelper.clickElement(browser, By.xpath(locator));
	}

	@And("^I click in the Content element$")
	public void iClickInTheContentElement() throws Throwable {
		String locator =
			"//a[@data-qa-id='appGroup' and " +
				"@href='#panel-manage-site_administration_content']";

		FunctionalTestLocatorsHelper.clickElement(browser, By.xpath(locator));
	}

	@And("^I click in the Liferay DXP element$")
	public void iClickInTheLiferayDXPElement() throws Throwable {
		String locator = "//span[contains(@class,'site-name truncate-text')]";

		FunctionalTestLocatorsHelper.clickElement(browser, By.xpath(locator));
	}

	@And("^I click in the Menu element$")
	public void iClickInTheMenuElement() throws Throwable {
		String productMenuLocator = "//a[@data-qa-id='productMenu']";

		String contentElementLocator =
			"//a[@data-qa-id='appGroup' and " +
				"@href='#panel-manage-site_administration_content']";

		_retryClickingInElement(productMenuLocator, contentElementLocator);
	}

	@And("^I click in the plus element$")
	public void iClickInThePlusElement() throws Throwable {
		String locator = "//button[@data-qa-id='addButton']";

		FunctionalTestUtil.scroll(browser);

		FunctionalTestLocatorsHelper.waitForElementToBeVisible(
			browser, By.xpath(locator));

		FunctionalTestLocatorsHelper.clickElement(browser, By.xpath(locator));
	}

	@And("^I click in the Web Content element$")
	public void iClickInTheWebContentElement() throws Throwable {
		String webContentLocator = "//a[@data-title='Web Content']";

		String addButonLocator = "//button[@data-qa-id='addButton']";

		_retryClickingInElement(webContentLocator, addButonLocator);
	}

	@And("^I wait for (\\d+) seconds for the asset to be processed$")
	public void iWaitForSecondsForTheAssetToBeProcessed(int seconds)
		throws Throwable {

		Thread.sleep(seconds * 1000);
	}

	@Given("^I am in the (.+) url$")
	public void navigateInClientWindowToURL(String url) {
		browser.get(url);
	}

	@After
	public void tearDown(Scenario scenario) throws Throwable {
		FunctionalTestUtil.checkJavascriptErrors(browser);

		FunctionalTestUtil.takeScreenshot(scenario, browser);
	}

	@Then(
		"^The asset with the entered title can be found through the assets " +
			"Service$"
	)
	public void theAssetCanBeFoundByAnAdministratorInTheBackend()
		throws Throwable {

		String lastAssetUrl = _assetEngineUrl + "/asset/last/" + _INSTANCE_ID;

		String lastAsset = FunctionalTestUtil.getJsonFromURL(lastAssetUrl);

		System.out.println("Last asset in backend: " + lastAsset);

		Assert.assertTrue(lastAsset.contains(_title));
	}

	@Then(
		"^The asset containing the entered title is not visible in the list " +
			"of assets on the client$"
	)
	public void theAssetContainingIsNotVisibleInTheListOfAssetsOnTheClient()
		throws Throwable {

		FunctionalTestLocatorsHelper.waitForElementNotToBeVisible(
			browser, By.xpath(String.format("//a[contains(.,'%s')]", _title)));
	}

	@Then(
		"^The asset containing the entered title is not visible in the list " +
			"of assets on the site"
	)
	public void theAssetContainingIsNotVisibleInTheListOfAssetsOnTheSite()
		throws Throwable {

		FunctionalTestLocatorsHelper.waitForElementNotToBeVisible(
			browser, By.xpath(String.format("//p[contains(.,'%s')]", _title)));
	}

	@Then(
		"^The asset containing the entered title is visible in the list of " +
			"assets on the client$"
	)
	public void theAssetContainingIsVisibleInTheListOfAssetsOnTheClient()
		throws Throwable {

		FunctionalTestLocatorsHelper.waitForElementToBePresent(
			browser, By.xpath(String.format("//a[contains(.,'%s')]", _title)));
	}

	@And(
		"^There are at least (\\d+) more assets and at most (\\d+) more " +
			"assets? in the backend with the entered title$"
	)
	public void thereAreMoreAssetsInTheBackend(
			long minAssetsIncrement, long maxAssetsIncrement)
		throws Throwable {

		long assetCountNow = _getNumberOfAssetsFromElasticSearch(_title);

		String message = String.format(
			"Assets before: %s. Assets now: %s", _assetCount, assetCountNow);

		System.out.println(message);

		Assert.assertTrue(
			message, assetCountNow >= (_assetCount + minAssetsIncrement) &&
			assetCountNow <= (_assetCount + maxAssetsIncrement));
	}

	@And("^There is no asset with the same title$")
	public void thereIsNoAssetWithTitle() throws Throwable {
		Assert.assertNull(
			"The asset with title " + _title + " is still in index",
			_getAssetIdByTitle(_title));
	}

	protected static String elasticSearchUrl = System.getenv(
		"ELASTIC_SEARCH_URL");

	private static void _deleteAssetByTitle(String title) throws Throwable {
		String id = _getAssetIdByTitle(title);

		if (id == null) {
			return;
		}

		String deleteAssetUrl =
			elasticSearchUrl + "/" + _assetIndexName + "/asset/" + id;

		System.out.println("Delete asset url: " + deleteAssetUrl);

		String response = FunctionalTestUtil.deleteJsonFromURL(
			deleteAssetUrl, Optional.empty(), Optional.empty());

		System.out.println("Delete asset response: " + response);

		Thread.sleep(2000);
	}

	private static String _getAssetIdByTitle(String title) throws IOException {
		String assetSearchIdUrl = String.format(
			"%s/%s/asset/_search?q=title.en_US:'%s'&filter_path=hits.hits._id",
			AssetConnectorTest.elasticSearchUrl,
			AssetConnectorTest._assetIndexName, title);

		System.out.println("Asset search id url: " + assetSearchIdUrl);

		String hits = FunctionalTestUtil.getJsonFromURL(assetSearchIdUrl);

		System.out.println("Asset search id response: " + hits);

		if (hits.equals("{}")) {
			System.out.println("Asset with title " + title + " not found");

			return null;
		}

		// Expected response: {"hits":{"hits":[{"_id":"1"}]}}

		return hits.split("_id\":\"")[1].split("\"}")[0].trim();
	}

	private static long _getNumberOfAssets() throws IOException {
		String assetCountUrl = _assetEngineUrl + "/asset/count/" + _INSTANCE_ID;

		return Long.parseLong(FunctionalTestUtil.getJsonFromURL(assetCountUrl));
	}

	private static long _getNumberOfAssetsFromElasticSearch(String title)
		throws IOException {

		String assetCountUrl = String.format(
			"%s/%s/asset/_search?pretty=true&q=title.en_US:'%s'&filter_path=" +
				"hits.total",
			AssetConnectorTest.elasticSearchUrl,
			AssetConnectorTest._assetIndexName, title);

		try {
			System.out.println("Asset count url: " + assetCountUrl);

			String hitsTotal = FunctionalTestUtil.getJsonFromURL(assetCountUrl);

			System.out.println("Asset count response: " + hitsTotal);

			// Expected response: {"hits":{"total":1}}

			hitsTotal = hitsTotal.split(":")[2].split("}")[0].trim();

			return Long.parseLong(hitsTotal);
		}
		catch (RuntimeException re) {
			if (re.getMessage().equals("Failed : HTTP error code : 404")) {
				System.out.println("Asset index does not exist yet.");

				return 0L;
			}
			else {
				throw re;
			}
		}
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

	private void _setTitle(String text) {
		String uuid = String.valueOf(UUID.randomUUID());

		_title = text + uuid.replace("-", "");
	}

	private static final int _INSTANCE_ID = 20116;

	private static Long _assetCount;
	private static final String _assetEngineEnvironmentUniquename =
		System.getenv("PULPO_ASSET_CONNECTOR_ENVIRONMENT_UNIQUENAME");
	private static final String _assetEngineUrl = System.getenv(
		"ASSET_ENGINE_URL");
	private static final String _assetIndexName =
		"asset_" + _assetEngineEnvironmentUniquename;
	private static String _title;

}