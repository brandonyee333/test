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

package com.liferay.osb.asah.backend.rest.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.rest.controller.DataSourcesRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.batch.curator.bot.nanite.DeleteDataSourcesNanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dto.DataSourceDTO;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.faro.backend.http.DataSourceHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;
import com.liferay.petra.string.StringPool;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Rachael Koestartyo
 * @author Vishal Reddy
 */
@ContextConfiguration(
	classes = {
		DataSourceHttpTestConfiguration.class,
		OSBAsahBackendSpringBootApplication.class
	}
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class DataSourcesRestControllerTest {

	@Before
	public void setUp() {
		_mock();
	}

	@Test
	public void testDeleteDataSource() throws Exception {
		JSONObject dataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSourceDTO.class)));

		JSONObject accountJSONObject = _faroInfoElasticsearchInvoker.add(
			"accounts",
			FaroInfoTestUtil.buildAccountJSONObject(dataSourceJSONObject));

		_faroInfoElasticsearchInvoker.add(
			"individual-segments",
			FaroInfoTestUtil.buildAccountIndividualSegmentJSONObject(
				accountJSONObject));

		JSONObject individualJSONObject = _faroInfoElasticsearchInvoker.add(
			"individuals",
			FaroInfoTestUtil.buildIndividualJSONObject(dataSourceJSONObject));

		JSONObject activityGroupJSONObject = _faroInfoElasticsearchInvoker.add(
			"activity-groups",
			FaroInfoTestUtil.buildActivityGroupJSONObject(
				dataSourceJSONObject.getString("id"), individualJSONObject));

		JSONObject assetJSONObject = _faroInfoElasticsearchInvoker.add(
			"assets",
			FaroInfoTestUtil.buildAssetJSONObject(
				"Page", dataSourceJSONObject.getString("id")));

		JSONObject activityJSONObject = _faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				activityGroupJSONObject, assetJSONObject, "pageViewed",
				new String[0]));

		_dataSourcesRestController.deleteDataSource(
			dataSourceJSONObject.getLong("id"));

		JSONObject updateDataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.getDataSource(
				dataSourceJSONObject.getLong("id")));

		Assert.assertTrue(updateDataSourceJSONObject.has("deletionDate"));
		Assert.assertEquals(
			"IN_PROGRESS_DELETING",
			updateDataSourceJSONObject.getString("state"));

		_runDeleteDataSourcesNanite(updateDataSourceJSONObject);

		Assert.assertFalse(
			_faroInfoElasticsearchInvoker.exists(
				"accounts", accountJSONObject.getString("id")));
		Assert.assertTrue(
			_faroInfoElasticsearchInvoker.exists(
				"activities", activityJSONObject.getString("id")));
		Assert.assertTrue(
			_faroInfoElasticsearchInvoker.exists(
				"activity-groups", activityGroupJSONObject.getString("id")));
		Assert.assertTrue(
			_faroInfoElasticsearchInvoker.exists(
				"assets", assetJSONObject.getString("id")));
		Assert.assertFalse(
			_faroInfoElasticsearchInvoker.exists(
				"data-sources", dataSourceJSONObject.getString("id")));
		Assert.assertFalse(
			_faroInfoElasticsearchInvoker.exists(
				"individual-segments",
				QueryBuilders.termQuery(
					"name", "Account: " + accountJSONObject.getString("id"))));
		Assert.assertFalse(
			_faroInfoElasticsearchInvoker.exists(
				"individuals",
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId",
						dataSourceJSONObject.getString("id")),
					ScoreMode.None)));
	}

	@Test
	public void testDuplicateDataSourceName() throws Exception {
		JSONObject dataSourceJSONObject =
			FaroInfoTestUtil.buildLiferayDataSourceJSONObject(
				"Liferay", RandomTestUtil.randomURL());

		for (int i = 0; i < 4; i++) {
			_dataSourcesRestController.postDataSource(
				_objectMapper.convertValue(
					dataSourceJSONObject, DataSourceDTO.class));
		}

		JSONObject responseJSONObject = _objectMapper.convertValue(
			_dataSourcesRestController.getDataSourceDTOsPageDTO(
				null, 0, 10, null),
			JSONObject.class);

		JSONArray dataSourcesJSONArray = (JSONArray)responseJSONObject.query(
			"/_embedded/data-sources");

		Assert.assertEquals(4, dataSourcesJSONArray.length());

		Set<String> dataSourceNames = new HashSet<>();

		for (int i = 0; i < dataSourcesJSONArray.length(); i++) {
			dataSourceJSONObject = dataSourcesJSONArray.getJSONObject(i);

			dataSourceNames.add(dataSourceJSONObject.getString("name"));
		}

		Assert.assertTrue(dataSourceNames.contains("Liferay"));
		Assert.assertTrue(dataSourceNames.contains("Liferay (1)"));
		Assert.assertTrue(dataSourceNames.contains("Liferay (2)"));
		Assert.assertTrue(dataSourceNames.contains("Liferay (3)"));
	}

	@Test
	public void testGetCSVDataSourceProgress() throws Exception {
		JSONObject csvDataSourceJSONObject = _faroInfoElasticsearchInvoker.add(
			"data-sources", FaroInfoTestUtil.buildCSVDataSourceJSONObject());

		// CSV individuals nanite is null

		Assert.assertEquals(
			"{}",
			_dataSourcesRestController.getProgress(
				csvDataSourceJSONObject.getLong("id")));

		// CSV individuals nanite started

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", csvDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "CSVIndividualsNanite"
			).put(
				"processedOperations", 1
			).put(
				"reprocess", false
			).put(
				"status", "STARTED"
			).put(
				"totalOperations", 1
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				csvDataSourceJSONObject.getLong("id")));

		Assert.assertEquals(
			1, progressJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", progressJSONObject.getString("status"));

		// CSV individuals nanite failed

		String dateLogged = DateUtil.newDateString();

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", csvDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "CSVIndividualsNanite"
			).put(
				"processedOperations", 1
			).put(
				"reprocess", false
			).put(
				"status", "FAILED"
			).put(
				"totalOperations", 1
			));

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateRecorded", dateLogged
			).put(
				"status", "FAILED"
			),
			new JSONObject(
				_dataSourcesRestController.getProgress(
					csvDataSourceJSONObject.getLong("id"))),
			true);
	}

	@Test
	public void testGetDataSources() throws Exception {
		_dataSourcesRestController.postDataSource(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
				DataSourceDTO.class));

		JSONObject dataSourcesJSONObject = _objectMapper.convertValue(
			_dataSourcesRestController.getDataSourceDTOsPageDTO(
				null, 0, 20, null),
			JSONObject.class);

		JSONObject embeddedJSONObject = dataSourcesJSONObject.getJSONObject(
			"_embedded");

		JSONArray dataSourcesJSONArray = embeddedJSONObject.getJSONArray(
			"data-sources");

		Assert.assertEquals(1, dataSourcesJSONArray.length());

		JSONObject dataSourceJSONObject = dataSourcesJSONArray.getJSONObject(0);

		Assert.assertFalse(
			dataSourceJSONObject.has("faroBackendSecuritySignature"));
	}

	@Test
	public void testGetSalesforceAccountsFields() throws Exception {
		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		JSONArray jsonArray = new JSONArray(
			_dataSourcesRestController.getSalesforceAccountsFields(
				String.valueOf(salesforceDataSource.getId()), 3, 0));

		Assert.assertEquals(3, jsonArray.length());
	}

	@Test
	public void testGetSalesforceDataSourceAccountsProgress() throws Exception {
		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor nanite is null

		Assert.assertEquals(
			JSONUtil.put(
				"accounts", new JSONObject()
			).put(
				"individuals", new JSONObject()
			).toString(),
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		// Salesforce accounts nanite is null

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject accountsJSONObject = progressJSONObject.getJSONObject(
			"accounts");

		Assert.assertEquals(
			1, accountsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", accountsJSONObject.getString("status"));
		Assert.assertEquals(2, accountsJSONObject.getInt("totalOperations"));

		// Salesforce accounts nanite is not null and date logged is earlier

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.addDays(dateLogged, -1)
			).put(
				"naniteClassName", "SalesforceAccountsNanite"
			).put(
				"reprocess", false
			).put(
				"status", "FAILED"
			).put(
				"totalOperations", 1
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		accountsJSONObject = progressJSONObject.getJSONObject("accounts");

		Assert.assertEquals(
			1, accountsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", accountsJSONObject.getString("status"));
		Assert.assertEquals(2, accountsJSONObject.getInt("totalOperations"));

		// Salesforce accounts nanite is not null and date logged is after

		JSONObject runLogsJSONObject = _faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.addDays(dateLogged, 1)
			).put(
				"naniteClassName", "SalesforceAccountsNanite"
			).put(
				"reprocess", false
			).put(
				"status", "FAILED"
			).put(
				"totalOperations", 1
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		accountsJSONObject = progressJSONObject.getJSONObject("accounts");

		Assert.assertEquals(
			DateUtil.addDays(dateLogged, 1),
			accountsJSONObject.getString("dateRecorded"));
		Assert.assertEquals("FAILED", accountsJSONObject.getString("status"));

		_faroInfoElasticsearchInvoker.delete(
			"run-logs", runLogsJSONObject.getString("id"));

		// Salesforce accounts nanite started

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "SalesforceAccountsNanite"
			).put(
				"reprocess", false
			).put(
				"status", "STARTED"
			).put(
				"totalOperations", 1
			));

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.put(
				"osbAsahDataSourceId",
				String.valueOf(salesforceDataSource.getId())
			).put(
				"typeName", "Account"
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		accountsJSONObject = progressJSONObject.getJSONObject("accounts");

		Assert.assertEquals(
			1, accountsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", accountsJSONObject.getString("status"));
		Assert.assertEquals(2, accountsJSONObject.getInt("totalOperations"));

		// Salesforce extractor nanite failed

		dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "FAILED"
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		accountsJSONObject = progressJSONObject.getJSONObject("accounts");

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateRecorded", dateLogged
			).put(
				"status", "FAILED"
			),
			accountsJSONObject, true);

		// Salesforce extractor nanite started and initial run is true

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"initialAccountRun", true
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "STARTED"
			).put(
				"totalAccountOperations", 1
			));

		_salesforceRawElasticsearchInvoker.add(
			"Account",
			JSONUtil.put(
				"Name", "Test Account"
			).put(
				"osbAsahDataSourceId",
				String.valueOf(salesforceDataSource.getId())
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		accountsJSONObject = progressJSONObject.getJSONObject("accounts");

		Assert.assertEquals(
			1, accountsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", accountsJSONObject.getString("status"));
		Assert.assertEquals(2, accountsJSONObject.getInt("totalOperations"));

		// Salesforce extractor nanite started and initial run is false

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"initialAccountRun", false
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "STARTED"
			).put(
				"totalAccountOperations", 1
			));

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.put(
				"osbAsahDataSourceId",
				String.valueOf(salesforceDataSource.getId())
			).put(
				"typeName", "Account"
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		accountsJSONObject = progressJSONObject.getJSONObject("accounts");

		Assert.assertEquals(
			2, accountsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", accountsJSONObject.getString("status"));
		Assert.assertEquals(2, accountsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress1()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor nanite is null

		Assert.assertEquals(
			JSONUtil.put(
				"accounts", new JSONObject()
			).put(
				"individuals", new JSONObject()
			).toString(),
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress2()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor nanite failed

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "FAILED"
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateRecorded", dateLogged
			).put(
				"status", "FAILED"
			),
			progressJSONObject.getJSONObject("individuals"), true);
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress3()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor nanite started and initial run is true

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"initialContactRun", true
			).put(
				"initialLeadRun", true
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "STARTED"
			).put(
				"totalContactOperations", 1
			).put(
				"totalLeadOperations", 1
			));

		_salesforceRawElasticsearchInvoker.add(
			"Contact",
			JSONUtil.put(
				"Name", "Test Contact"
			).put(
				"osbAsahDataSourceId",
				String.valueOf(salesforceDataSource.getId())
			));

		_salesforceRawElasticsearchInvoker.add(
			"Lead",
			JSONUtil.put(
				"Name", "Test Lead"
			).put(
				"osbAsahDataSourceId",
				String.valueOf(salesforceDataSource.getId())
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assert.assertEquals(
			2, individualsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assert.assertEquals(6, individualsJSONObject.getInt("totalOperations"));

		// Salesforce extractor nanite started and initial run is false

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"initialContactRun", false
			).put(
				"initialLeadRun", false
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "STARTED"
			).put(
				"totalContactOperations", 1
			).put(
				"totalLeadOperations", 1
			));

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.put(
				"osbAsahDataSourceId",
				String.valueOf(salesforceDataSource.getId())
			).put(
				"typeName", "Contact"
			));

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.put(
				"osbAsahDataSourceId",
				String.valueOf(salesforceDataSource.getId())
			).put(
				"typeName", "Lead"
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		individualsJSONObject = progressJSONObject.getJSONObject("individuals");

		Assert.assertEquals(
			2, individualsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assert.assertEquals(6, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress4()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor nanite in progress and salesforce extractor
		// individuals nanite is null

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assert.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assert.assertEquals(
			1, individualsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assert.assertEquals(3, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress5()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor individuals nanite started

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"naniteClassName", "SalesforceExtractorIndividualsNanite"
			).put(
				"status", "STARTED"
			).put(
				"totalOperations", 1
			));

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.put(
				"osbAsahDataSourceId",
				String.valueOf(salesforceDataSource.getId())
			).put(
				"typeName", "Contact"
			));

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.put(
				"osbAsahDataSourceId",
				String.valueOf(salesforceDataSource.getId())
			).put(
				"typeName", "Lead"
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assert.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assert.assertEquals(
			0, individualsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assert.assertEquals(3, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress6()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor individuals nanite not started and date logged
		// is earlier

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.addDays(dateLogged, -1)
			).put(
				"naniteClassName", "SalesforceExtractorIndividualsNanite"
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 1
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assert.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assert.assertEquals(
			1, individualsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assert.assertEquals(3, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress7()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor individuals nanite failed and date logged
		// is later

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.addHours(dateLogged, -1)
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceExtractorIndividualsNanite"
			).put(
				"status", "FAILED"
			).put(
				"totalOperations", 1
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateRecorded", dateLogged
			).put(
				"status", "FAILED"
			),
			individualsJSONObject, true);
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress8()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.addHours(dateLogged, -1)
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceExtractorIndividualsNanite"
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 1
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assert.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assert.assertEquals(
			2, individualsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assert.assertEquals(3, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress9()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce individuals nanite started

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.addHours(dateLogged, -1)
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceExtractorIndividualsNanite"
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 1
			));

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.newDayDateString()
			).put(
				"naniteClassName", "SalesforceIndividualsNanite"
			).put(
				"status", "STARTED"
			).put(
				"totalOperations", 2
			));

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.put(
				"osbAsahDataSourceId",
				String.valueOf(salesforceDataSource.getId())
			).put(
				"typeName", "individuals"
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assert.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assert.assertEquals(
			5, individualsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assert.assertEquals(6, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress10()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce individuals nanite not started and and date logged is
		// earlier

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.addHours(dateLogged, -1)
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.addHours(dateLogged, 1)
			).put(
				"naniteClassName", "SalesforceExtractorIndividualsNanite"
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 1
			));

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceIndividualsNanite"
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 2
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assert.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assert.assertEquals(
			2, individualsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assert.assertEquals(3, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress11()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce individuals nanite not started and and date logged is
		// later

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.addHours(dateLogged, -1)
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceExtractorIndividualsNanite"
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 1
			));

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"dateLogged", DateUtil.addHours(dateLogged, 2)
			).put(
				"naniteClassName", "SalesforceIndividualsNanite"
			).put(
				"status", "IN_PROGRESS"
			).put(
				"totalOperations", 2
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assert.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
	}

	@Test
	public void testGetSalesforceUsersFields() throws Exception {
		DataSource salesforceDataSource = _dataSourceDog.addDataSource(
			FaroInfoTestUtil.buildSalesforceDataSource());

		JSONArray jsonArray = new JSONArray(
			_dataSourcesRestController.getSalesforceUsersFields(
				String.valueOf(salesforceDataSource.getId()), 3, 0));

		Assert.assertEquals(3, jsonArray.length());
	}

	@Test
	public void testPutDataSource() throws Exception {
		JSONObject dataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				_objectMapper.convertValue(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject(),
					DataSourceDTO.class)));

		dataSourceJSONObject.put(
			"provider",
			JSONUtil.put(
				"analyticsConfiguration",
				JSONUtil.put(
					"enableAllSites", true
				).put(
					"lastSyncTime", DateUtil.newDateString()
				).put(
					"sites", new JSONArray()
				)
			).put(
				"contactsConfiguration",
				JSONUtil.put(
					"enableAllContacts", true
				).put(
					"lastSuccessfulAuditEventTime", DateUtil.newDateString()
				).put(
					"lastSyncTime", DateUtil.newDateString()
				).put(
					"organizations", new JSONArray()
				).put(
					"userGroups", new JSONArray()
				)
			).put(
				"type", "LIFERAY"
			));

		JSONObject newDataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.putDataSource(
				dataSourceJSONObject.getString("id"),
				_objectMapper.convertValue(
					dataSourceJSONObject, DataSourceDTO.class)));

		JSONObject providerJSONObject = newDataSourceJSONObject.getJSONObject(
			"provider");

		JSONObject analyticsConfigurationJSONObject =
			providerJSONObject.getJSONObject("analyticsConfiguration");

		Assert.assertFalse(
			analyticsConfigurationJSONObject.has("lastSyncTime"));

		JSONObject contactsConfigurationJSONObject =
			providerJSONObject.getJSONObject("contactsConfiguration");

		Assert.assertFalse(
			contactsConfigurationJSONObject.has(
				"lastSuccessfulAuditEventTime"));
		Assert.assertFalse(contactsConfigurationJSONObject.has("lastSyncTime"));
	}

	private void _mock() {
		Mockito.when(
			_cacheManager.getCache(Mockito.anyString())
		).thenReturn(
			_cache
		);

		Mockito.when(
			_cacheManager.getCacheNames()
		).thenReturn(
			Collections.singleton(StringPool.BLANK)
		);

		Mockito.when(
			_salesforceExtractorConfigurationDog.getState(
				Mockito.any(DataSource.class))
		).thenReturn(
			"CREDENTIALS_VALID"
		);
	}

	private void _runDeleteDataSourcesNanite(JSONObject dataSourceJSONObject)
		throws Exception {

		DeleteDataSourcesNanite deleteDataSourcesNanite =
			new DeleteDataSourcesNanite();

		_autowireCapableBeanFactory.autowireBeanProperties(
			deleteDataSourcesNanite, AutowireCapableBeanFactory.AUTOWIRE_NO,
			false);

		Class<?> clazz = deleteDataSourcesNanite.getClass();

		_autowireCapableBeanFactory.initializeBean(
			deleteDataSourcesNanite, clazz.getName());

		deleteDataSourcesNanite.run(dataSourceJSONObject);
	}

	@Autowired
	private AutowireCapableBeanFactory _autowireCapableBeanFactory;

	@Mock
	private Cache _cache;

	@MockBean
	private CacheManager _cacheManager;

	@MockBean
	private ChannelHttp _channelHttp;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private DataSourcesRestController _dataSourcesRestController;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	@Mock
	private SalesforceExtractorConfigurationDog
		_salesforceExtractorConfigurationDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}