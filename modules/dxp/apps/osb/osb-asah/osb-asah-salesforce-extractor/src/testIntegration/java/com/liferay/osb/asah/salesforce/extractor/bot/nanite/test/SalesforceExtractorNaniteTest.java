/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.salesforce.extractor.bot.nanite.test;

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.run.logger.RunLogger;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.salesforce.extractor.bot.SalesforceConfigurableBot;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.SalesforceExtractorNanite;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.test.util.SalesforceExtractorTestUtil;
import com.liferay.osb.asah.salesforce.extractor.client.SalesforceBulkClientInvoker;
import com.liferay.osb.asah.salesforce.extractor.client.SalesforcePartnerClientInvoker;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;
import com.liferay.osb.asah.salesforce.extractor.oauth2.SalesforceOAuth2Client;
import com.liferay.osb.asah.salesforce.extractor.spring.OSBAsahSalesforceExtractorSpringBootApplication;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author Rachael Koestartyo
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		OSBAsahSalesforceExtractorSpringBootApplication.class,
		SalesforceExtractorNaniteTest.
			SalesforceExtractorNaniteTestConfiguration.class
	}
)
public class SalesforceExtractorNaniteTest {

	@Before
	public void setUp() {
		_elasticsearchIndexManager.clearIndices();

		_elasticsearchIndexManager.checkIndices();

		_setUpConfigurationManager();
	}

	@After
	public void tearDown() throws Exception {
		_dataSourceDog.deleteDataSources();
		_salesforceRawElasticsearchInvoker.delete(
			"Account", QueryBuilders.matchAllQuery());
		_salesforceRawElasticsearchInvoker.delete(
			"OSBAsahMarkers", QueryBuilders.matchAllQuery());
		_salesforceRawElasticsearchInvoker.delete(
			"audit-events", QueryBuilders.matchAllQuery());

		_elasticsearchIndexManager.clearIndices();
	}

	@Test
	public void test() throws Exception {
		Assume.assumeTrue(
			StringUtils.isNotEmpty(
				_salesforceExtractorConfiguration.
					getSalesforceAuthEndpoint()) &&
			StringUtils.isNotEmpty(
				_salesforceExtractorConfiguration.getSalesforcePassword()) &&
			StringUtils.isNotEmpty(
				_salesforceExtractorConfiguration.getSalesforceUserName()));

		_testPopulateNewTables();

		_testAddField();
		_testAddSObject();
		_testDeleteField();
		_testDeleteSObject();
		_testSyncExistingTable();

		_testAuditEventsTable();
	}

	@TestConfiguration
	public static class SalesforceExtractorNaniteTestConfiguration {

		@Bean
		public RunLogger runLogger() {
			return new RunLogger();
		}

		@Bean
		public SalesforceBulkClientInvoker salesforceBulkClientInvoker() {
			return new SalesforceBulkClientInvoker();
		}

		@Bean
		@Primary
		public SalesforceConfigurableBot salesforceConfigurableBot() {
			SalesforceConfigurableBot salesforceConfigurableBot =
				new SalesforceConfigurableBot();

			salesforceConfigurableBot.setTableNames(new String[] {"Account"});

			return salesforceConfigurableBot;
		}

		@Bean
		public SalesforceExtractorNanite salesforceExtractorNanite() {
			return new SalesforceExtractorNanite(
				_salesforceExtractorConfiguration, new String[] {"Account"});
		}

		@Bean
		public SalesforceOAuth2Client salesforceOAuth2Client() {
			return new SalesforceOAuth2Client();
		}

		@Bean
		public SalesforcePartnerClientInvoker salesforcePartnerClientInvoker() {
			return new SalesforcePartnerClientInvoker();
		}

	}

	private String[] _getIndexAliases(String... collectionNames) {
		Stream<String> stream = Arrays.stream(collectionNames);

		return stream.map(
			collectionName -> ElasticsearchIndexUtil.getIndexAlias(
				collectionName, WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
		).toArray(
			String[]::new
		);
	}

	private void _setUpConfigurationManager() {
		_dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource(
				_salesforceExtractorConfiguration.getSalesforceUserName(),
				_salesforceExtractorConfiguration.getSalesforcePassword(),
				_salesforceExtractorConfiguration.getSalesforceURL()));
	}

	private void _testAddField() {
		JSONArray osbAsahMarkerJSONArray =
			_salesforceRawElasticsearchInvoker.get("OSBAsahMarkers");

		JSONObject osbAsahMarkerJSONObject =
			osbAsahMarkerJSONArray.getJSONObject(0);

		JSONObject tablesJSONObject = osbAsahMarkerJSONObject.getJSONObject(
			"tables");

		JSONObject tableJSONObject = tablesJSONObject.getJSONObject("Account");

		JSONObject fieldsJSONObject = new JSONObject(
			tableJSONObject.getString("fields"));

		fieldsJSONObject.remove("Name");

		tableJSONObject.put("fields", fieldsJSONObject.toString());

		_salesforceRawElasticsearchInvoker.update(
			"OSBAsahMarkers", osbAsahMarkerJSONObject);

		JSONArray tableJSONArray = _salesforceRawElasticsearchInvoker.get(
			"Account");

		for (int i = 0; i < tableJSONArray.length(); i++) {
			JSONObject curTableJSONObject = tableJSONArray.getJSONObject(i);

			curTableJSONObject.remove("Name");

			_salesforceRawElasticsearchInvoker.update(
				"Account", curTableJSONObject);
		}

		tableJSONArray = _salesforceRawElasticsearchInvoker.get("Account");

		for (int i = 0; i < tableJSONArray.length(); i++) {
			JSONObject curTableJSONObject = tableJSONArray.getJSONObject(i);

			Assert.assertNotNull(curTableJSONObject.opt("Name"));
		}
	}

	private void _testAddSObject() throws Exception {
		JSONArray osbAsahMarkerJSONArray =
			_salesforceRawElasticsearchInvoker.get("OSBAsahMarkers");

		JSONObject osbAsahMarkerJSONObject =
			osbAsahMarkerJSONArray.getJSONObject(0);

		osbAsahMarkerJSONObject.put("tables", new JSONObject());

		_salesforceRawElasticsearchInvoker.update(
			"OSBAsahMarkers", osbAsahMarkerJSONObject);

		_salesforceRawElasticsearchInvoker.delete(
			"Account", QueryBuilders.matchAllQuery());

		_elasticsearchIndexManager.clear(_getIndexAliases("Account"));

		_salesforceExtractorNanite.run();

		Assert.assertNotNull(_salesforceRawElasticsearchInvoker.get("Account"));
	}

	private void _testAuditEventsTable() {
		JSONArray auditEventsJSONArray = _salesforceRawElasticsearchInvoker.get(
			"audit-events");

		Assert.assertNotNull(auditEventsJSONArray);

		boolean hasDelete = false;
		boolean hasUpdate = false;

		for (int i = 0; i < auditEventsJSONArray.length(); i++) {
			if (hasDelete && hasUpdate) {
				break;
			}

			JSONObject auditEventJSONObject =
				auditEventsJSONArray.getJSONObject(i);

			String eventType = auditEventJSONObject.getString("eventType");

			if (Objects.equals(eventType, "DELETE")) {
				hasDelete = true;
			}
			else if (Objects.equals(eventType, "UPDATE")) {
				hasUpdate = true;
			}
		}

		Assert.assertTrue(hasDelete && hasUpdate);
	}

	private void _testDeleteField() throws Exception {
		JSONArray osbAsahMarkerJSONArray =
			_salesforceRawElasticsearchInvoker.get("OSBAsahMarkers");

		JSONObject osbAsahMarkerJSONObject =
			osbAsahMarkerJSONArray.getJSONObject(0);

		JSONObject tablesJSONObject = osbAsahMarkerJSONObject.getJSONObject(
			"tables");

		JSONObject tableJSONObject = tablesJSONObject.getJSONObject("Account");

		JSONObject fieldsJSONObject = new JSONObject(
			tableJSONObject.getString("fields"));

		fieldsJSONObject.put(
			"OSBAsahTest__c",
			JSONUtil.put(
				"required", false
			).put(
				"type", "string"
			));

		tableJSONObject.put("fields", fieldsJSONObject.toString());

		_salesforceRawElasticsearchInvoker.update(
			"OSBAsahMarkers", osbAsahMarkerJSONObject);

		JSONArray tableJSONArray = _salesforceRawElasticsearchInvoker.get(
			"Account");

		for (int i = 0; i < tableJSONArray.length(); i++) {
			JSONObject curTableJSONObject = tableJSONArray.getJSONObject(i);

			curTableJSONObject.put("OSBAsahTest__c", "test");
		}

		_salesforceRawElasticsearchInvoker.save("Account", tableJSONArray);

		_salesforceExtractorNanite.run();

		tableJSONArray = _salesforceRawElasticsearchInvoker.get("Account");

		for (int i = 0; i < tableJSONArray.length(); i++) {
			JSONObject curTableJSONObject = tableJSONArray.getJSONObject(i);

			Assert.assertNull(curTableJSONObject.opt("OSBAsahTest__c"));
		}
	}

	private void _testDeleteSObject() throws Exception {
		JSONArray osbAsahMarkerJSONArray =
			_salesforceRawElasticsearchInvoker.get("OSBAsahMarkers");

		JSONObject osbAsahMarkerJSONObject =
			osbAsahMarkerJSONArray.getJSONObject(0);

		JSONObject tablesJSONObject = osbAsahMarkerJSONObject.getJSONObject(
			"tables");

		JSONObject tableJSONObject = tablesJSONObject.getJSONObject("Account");

		tablesJSONObject.put("OSBAsahTest__c", tableJSONObject);

		_salesforceRawElasticsearchInvoker.update(
			"OSBAsahMarkers", osbAsahMarkerJSONObject);

		JSONArray tableJSONArray = _salesforceRawElasticsearchInvoker.get(
			"Account");

		_salesforceRawElasticsearchInvoker.add(
			"OSBAsahTest__c", tableJSONArray);

		_salesforceExtractorNanite.run();

		JSONArray jsonArray = _salesforceRawElasticsearchInvoker.get(
			"OSBAsahTest__c");

		Assert.assertEquals(0, jsonArray.length());
	}

	private void _testPopulateNewTables() throws Exception {
		_salesforceExtractorNanite.run();

		JSONArray osbAsahMarkerJSONArray =
			_salesforceRawElasticsearchInvoker.get("OSBAsahMarkers");

		JSONObject osbAsahMarkerJSONObject =
			osbAsahMarkerJSONArray.getJSONObject(0);

		JSONObject tablesJSONObject = osbAsahMarkerJSONObject.getJSONObject(
			"tables");

		JSONObject tableJSONObject = tablesJSONObject.getJSONObject("Account");

		Assert.assertNotNull(tableJSONObject);

		JSONArray tableJSONArray = _salesforceRawElasticsearchInvoker.get(
			"Account");

		Assert.assertTrue(tableJSONArray.length() > 0);
	}

	private void _testSyncExistingTable() throws Exception {
		SObject sObject = new SObject("Account");

		sObject.setField("Name", "OSB Asah Test");

		List<SaveResult> saveResults =
			_salesforcePartnerClientInvoker.createSObjects(
				_salesforceExtractorConfiguration, new SObject[] {sObject});

		SaveResult saveResult = saveResults.get(0);

		String salesforceKey = saveResult.getId();

		Thread.sleep(60000);

		boolean added = false;

		for (int i = 0; i < 3; i++) {
			_salesforceExtractorNanite.run();

			JSONArray jsonArray = _salesforceRawElasticsearchInvoker.get(
				"Account");

			for (int j = 0; j < jsonArray.length(); j++) {
				JSONObject jsonObject = jsonArray.getJSONObject(j);

				if (Objects.equals(jsonObject.getString("id"), salesforceKey)) {
					added = true;

					break;
				}
			}

			if (added) {
				break;
			}

			Thread.sleep(20000);
		}

		Assert.assertTrue(added);

		List<DeleteResult> deleteResults =
			_salesforcePartnerClientInvoker.deleteSObjects(
				_salesforceExtractorConfiguration,
				new String[] {salesforceKey});

		DeleteResult deleteResult = deleteResults.get(0);

		Thread.sleep(60000);

		boolean deleted = false;

		for (int i = 0; i < 3; i++) {
			_salesforceExtractorNanite.run();

			JSONArray jsonArray = _salesforceRawElasticsearchInvoker.get(
				"Account");

			boolean exists = false;

			for (int j = 0; j < jsonArray.length(); j++) {
				JSONObject jsonObject = jsonArray.getJSONObject(j);

				if (Objects.equals(
						jsonObject.getString("id"), deleteResult.getId())) {

					exists = true;

					break;
				}
			}

			deleted = !exists;

			if (deleted) {
				break;
			}

			Thread.sleep(20000);
		}

		Assert.assertTrue(deleted);
	}

	private static final SalesforceExtractorConfiguration
		_salesforceExtractorConfiguration =
			SalesforceExtractorTestUtil.getSalesforceExtractorConfiguration();

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private SalesforceExtractorNanite _salesforceExtractorNanite;

	@Autowired
	private SalesforcePartnerClientInvoker _salesforcePartnerClientInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}