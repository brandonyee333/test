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

import com.liferay.osb.asah.backend.rest.controller.DataSourcesRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.batch.curator.bot.nanite.DeleteDataSourcesNanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dxp.extractor.dog.DXPExtractorConfigurationDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.http.ChannelHttp;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
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

		_dxpRawElasticsearchInvoker = _elasticsearchInvokerFactory.forDXPRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
		_salesforceRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forSalesforceRaw();
	}

	@Test
	public void testDeleteDataSource() throws Exception {
		JSONObject dataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				String.valueOf(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject())));

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
				new String[] {"pageLoadTime", "1000"}));

		_dataSourcesRestController.deleteDataSource(
			dataSourceJSONObject.getString("id"));

		JSONObject updateDataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.getDataSource(
				dataSourceJSONObject.getString("id")));

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

		String dataSourceJSON = dataSourceJSONObject.toString();

		for (int i = 0; i < 4; i++) {
			_dataSourcesRestController.postDataSource(dataSourceJSON);
		}

		JSONObject responseJSONObject = new JSONObject(
			_dataSourcesRestController.getDataSources(null, 0, 10, null));

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
				csvDataSourceJSONObject.getString("id")));

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
				csvDataSourceJSONObject.getString("id")));

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
					csvDataSourceJSONObject.getString("id"))),
			true);
	}

	@Test
	public void testGetDataSources() throws Exception {
		_dataSourcesRestController.postDataSource(
			String.valueOf(
				FaroInfoTestUtil.buildLiferayDataSourceJSONObject()));

		JSONObject dataSourcesJSONObject = new JSONObject(
			_dataSourcesRestController.getDataSources(null, 0, 20, null));

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
	public void testGetDataSourceTransformations() throws Exception {
		_dataSourcesRestController.postDataSource(
			String.valueOf(
				FaroInfoTestUtil.buildLiferayDataSourceJSONObject()));

		JSONObject dataSourceTransformationsJSONObject = new JSONObject(
			_dataSourcesRestController.getDataSourceTransformations(
				"groupby((provider/type))/contains(('LIFERAY'))", null, 0, 10));

		JSONObject embeddedJSONObject =
			dataSourceTransformationsJSONObject.getJSONObject("_embedded");

		JSONArray dataSourceTransformationsJSONArray =
			embeddedJSONObject.getJSONArray("data-source-transformations");

		Assert.assertEquals(1, dataSourceTransformationsJSONArray.length());
	}

	@Test
	public void testGetDXPGroups() throws Exception {
		JSONObject liferayDataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				String.valueOf(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject())));

		JSONObject jsonObject = new JSONObject(
			_dataSourcesRestController.getDXPGroups(
				liferayDataSourceJSONObject.getString("id"), 1, null, 0, true,
				0));

		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		JSONArray groupsJSONArray = embeddedJSONObject.getJSONArray("groups");

		Assert.assertEquals(1, groupsJSONArray.length());

		JSONObject groupJSONObject = groupsJSONArray.getJSONObject(0);

		Assert.assertEquals("20126", groupJSONObject.getString("groupId"));

		jsonObject = new JSONObject(
			_dataSourcesRestController.getDXPGroups(
				liferayDataSourceJSONObject.getString("id"), "{}"));

		embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		groupsJSONArray = embeddedJSONObject.getJSONArray("groups");

		Assert.assertEquals(1, groupsJSONArray.length());

		groupJSONObject = groupsJSONArray.getJSONObject(0);

		Assert.assertEquals("20126", groupJSONObject.getString("groupId"));
	}

	@Test
	public void testGetDXPOrganizations() throws Exception {
		JSONObject liferayDataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				String.valueOf(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject())));

		JSONObject jsonObject = new JSONObject(
			_dataSourcesRestController.getDXPOrganizations(
				liferayDataSourceJSONObject.getString("id"), 1, null, 0, 0));

		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		JSONArray organizationsJSONArray = embeddedJSONObject.getJSONArray(
			"organizations");

		Assert.assertEquals(1, organizationsJSONArray.length());

		JSONObject organizationJSONObject =
			organizationsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			"20127", organizationJSONObject.getString("organizationId"));

		jsonObject = new JSONObject(
			_dataSourcesRestController.getDXPOrganizations(
				liferayDataSourceJSONObject.getString("id"), null));

		embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		organizationsJSONArray = embeddedJSONObject.getJSONArray(
			"organizations");

		Assert.assertEquals(1, organizationsJSONArray.length());

		organizationJSONObject = organizationsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			"20127", organizationJSONObject.getString("organizationId"));
	}

	@Test
	public void testGetDXPUserGroups() throws Exception {
		JSONObject liferayDataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				String.valueOf(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject())));

		JSONObject jsonObject = new JSONObject(
			_dataSourcesRestController.getDXPUserGroups(
				liferayDataSourceJSONObject.getString("id"), 1, null, 0));

		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		JSONArray userGroupsJSONArray = embeddedJSONObject.getJSONArray(
			"userGroups");

		Assert.assertEquals(1, userGroupsJSONArray.length());

		JSONObject userGroupJSONObject = userGroupsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			"33213", userGroupJSONObject.getString("userGroupId"));

		jsonObject = new JSONObject(
			_dataSourcesRestController.getDXPUserGroups(
				liferayDataSourceJSONObject.getString("id"), null));

		embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		userGroupsJSONArray = embeddedJSONObject.getJSONArray("userGroups");

		Assert.assertEquals(1, userGroupsJSONArray.length());

		userGroupJSONObject = userGroupsJSONArray.getJSONObject(0);

		Assert.assertEquals(
			"33213", userGroupJSONObject.getString("userGroupId"));
	}

	@Test
	public void testGetDXPUsersFields() throws Exception {
		JSONObject liferayDataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				String.valueOf(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject())));

		JSONArray jsonArray = new JSONArray(
			_dataSourcesRestController.getDXPUsersFields(
				liferayDataSourceJSONObject.getString("id"), 3, 0));

		Assert.assertEquals(3, jsonArray.length());
	}

	@Test
	public void testGetDXPUsersTotal() throws Exception {
		JSONObject liferayDataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				String.valueOf(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject())));

		Assert.assertEquals(
			"1",
			_dataSourcesRestController.getDXPUsersTotal(
				liferayDataSourceJSONObject.getString("id"), null));
	}

	@Test
	public void testGetLiferayDataSourceProgress() throws Exception {
		JSONObject liferayDataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				String.valueOf(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject())));

		// DXP extractor nanite is null

		Assert.assertEquals(
			"{}",
			_dataSourcesRestController.getProgress(
				liferayDataSourceJSONObject.getString("id")));

		// DXP individuals nanite is null

		String dateLogged = DateUtil.newDateString();

		_dxpRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", liferayDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "DXPExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				liferayDataSourceJSONObject.getString("id")));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assert.assertEquals(
			1, individualsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assert.assertEquals(2, individualsJSONObject.getInt("totalOperations"));

		// DXP individuals nanite is not null and date logged is earlier

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", liferayDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.addDays(dateLogged, -1)
			).put(
				"naniteClassName", "DXPIndividualsNanite"
			).put(
				"reprocess", false
			).put(
				"status", "FAILED"
			).put(
				"totalOperations", 1
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				liferayDataSourceJSONObject.getString("id")));

		individualsJSONObject = progressJSONObject.getJSONObject("individuals");

		Assert.assertEquals(
			1, individualsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assert.assertEquals(2, individualsJSONObject.getInt("totalOperations"));

		// DXP individuals nanite is not null and date logged is after

		JSONObject runLogsJSONObject = _faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", liferayDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.addDays(dateLogged, 1)
			).put(
				"naniteClassName", "DXPIndividualsNanite"
			).put(
				"reprocess", false
			).put(
				"status", "FAILED"
			).put(
				"totalOperations", 1
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				liferayDataSourceJSONObject.getString("id")));

		individualsJSONObject = progressJSONObject.getJSONObject("individuals");

		Assert.assertEquals(
			DateUtil.addDays(dateLogged, 1),
			individualsJSONObject.getString("dateRecorded"));
		Assert.assertEquals(
			"FAILED", individualsJSONObject.getString("status"));

		_faroInfoElasticsearchInvoker.delete(
			"run-logs", runLogsJSONObject.getString("id"));

		// DXP individuals nanite started and reprocess is false

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", liferayDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "DXPIndividualsNanite"
			).put(
				"reprocess", false
			).put(
				"status", "STARTED"
			).put(
				"totalOperations", 1
			));

		_dxpRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", liferayDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "DXPExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				liferayDataSourceJSONObject.getString("id")));

		individualsJSONObject = progressJSONObject.getJSONObject("individuals");

		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assert.assertEquals(2, individualsJSONObject.getInt("totalOperations"));

		// DXP individuals nanite started and reprocess is true

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", liferayDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "DXPIndividualsNanite"
			).put(
				"processedOperations", 1
			).put(
				"reprocess", true
			).put(
				"status", "STARTED"
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				liferayDataSourceJSONObject.getString("id")));

		Assert.assertEquals(
			"IN_PROGRESS", progressJSONObject.getString("status"));

		// DXP extractor nanite failed

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", liferayDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "DXPIndividualsNanite"
			).put(
				"processedOperations", 1
			).put(
				"reprocess", false
			).put(
				"status", "STARTED"
			));

		_dxpRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", liferayDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "DXPExtractorNanite"
			).put(
				"status", "FAILED"
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				liferayDataSourceJSONObject.getString("id")));

		individualsJSONObject = progressJSONObject.getJSONObject("individuals");

		Assert.assertEquals(
			"FAILED", individualsJSONObject.getString("status"));

		// DXP Extractor nanite started and initial run is true

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", liferayDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "DXPIndividualsNanite"
			).put(
				"processedOperations", 1
			).put(
				"reprocess", false
			).put(
				"status", "STARTED"
			));

		_dxpRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", liferayDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"initialRun", true
			).put(
				"naniteClassName", "DXPExtractorNanite"
			).put(
				"status", "STARTED"
			).put(
				"totalOperations", 1
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				liferayDataSourceJSONObject.getString("id")));

		individualsJSONObject = progressJSONObject.getJSONObject("individuals");

		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assert.assertEquals(2, individualsJSONObject.getInt("totalOperations"));

		// DXP Extractor nanite started and initial run is false

		_faroInfoElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", liferayDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "DXPIndividualsNanite"
			).put(
				"processedOperations", 1
			).put(
				"reprocess", false
			).put(
				"status", "STARTED"
			));

		_dxpRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", liferayDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"initialRun", false
			).put(
				"naniteClassName", "DXPExtractorNanite"
			).put(
				"status", "STARTED"
			).put(
				"totalOperations", 1
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				liferayDataSourceJSONObject.getString("id")));

		individualsJSONObject = progressJSONObject.getJSONObject("individuals");

		Assert.assertEquals(
			1, individualsJSONObject.getInt("processedOperations"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
	}

	@Test
	public void testGetSalesforceAccountsFields() throws Exception {
		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		JSONArray jsonArray = new JSONArray(
			_dataSourcesRestController.getSalesforceAccountsFields(
				salesforceDataSourceJSONObject.getString("id"), 3, 0));

		Assert.assertEquals(3, jsonArray.length());
	}

	@Test
	public void testGetSalesforceDataSourceAccountsProgress() throws Exception {
		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		// Salesforce extractor nanite is null

		Assert.assertEquals(
			JSONUtil.put(
				"accounts", new JSONObject()
			).put(
				"individuals", new JSONObject()
			).toString(),
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));

		// Salesforce accounts nanite is null

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));

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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")));

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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")));

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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")
			).put(
				"typeName", "Account"
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));

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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "FAILED"
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));

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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));

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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")
			).put(
				"typeName", "Account"
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));

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

		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		// Salesforce extractor nanite is null

		Assert.assertEquals(
			JSONUtil.put(
				"accounts", new JSONObject()
			).put(
				"individuals", new JSONObject()
			).toString(),
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress2()
		throws Exception {

		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		// Salesforce extractor nanite failed

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", dateLogged
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "FAILED"
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));

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

		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		// Salesforce extractor nanite started and initial run is true

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")
			));

		_salesforceRawElasticsearchInvoker.add(
			"Lead",
			JSONUtil.put(
				"Name", "Test Lead"
			).put(
				"osbAsahDataSourceId",
				salesforceDataSourceJSONObject.getString("id")
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));

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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")
			).put(
				"typeName", "Contact"
			));

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.put(
				"osbAsahDataSourceId",
				salesforceDataSourceJSONObject.getString("id")
			).put(
				"typeName", "Lead"
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));

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

		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		// Salesforce extractor nanite in progress and salesforce extractor
		// individuals nanite is null

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", "SalesforceExtractorNanite"
			).put(
				"status", "IN_PROGRESS"
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));

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

		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		// Salesforce extractor individuals nanite started

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")
			).put(
				"typeName", "Contact"
			));

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.put(
				"osbAsahDataSourceId",
				salesforceDataSourceJSONObject.getString("id")
			).put(
				"typeName", "Lead"
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));

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

		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		// Salesforce extractor individuals nanite not started and date logged
		// is earlier

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")));

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

		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		// Salesforce extractor individuals nanite failed and date logged
		// is later

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")));

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

		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")));

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

		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		// Salesforce individuals nanite started

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")
			).put(
				"typeName", "individuals"
			));

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSourceJSONObject.getString("id")));

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

		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		// Salesforce individuals nanite not started and and date logged is
		// earlier

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")));

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

		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		// Salesforce individuals nanite not started and and date logged is
		// later

		String dateLogged = DateUtil.newDateString();

		_salesforceRawElasticsearchInvoker.add(
			"run-logs",
			JSONUtil.put(
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				"dataSourceId", salesforceDataSourceJSONObject.getString("id")
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
				salesforceDataSourceJSONObject.getString("id")));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assert.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assert.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
	}

	@Test
	public void testGetSalesforceUsersFields() throws Exception {
		JSONObject salesforceDataSourceJSONObject =
			_faroInfoElasticsearchInvoker.add(
				"data-sources",
				FaroInfoTestUtil.buildSalesforceDataSourceJSONObject());

		JSONArray jsonArray = new JSONArray(
			_dataSourcesRestController.getSalesforceUsersFields(
				salesforceDataSourceJSONObject.getString("id"), 3, 0));

		Assert.assertEquals(3, jsonArray.length());
	}

	@Test
	public void testPutDataSource() throws Exception {
		JSONObject dataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				String.valueOf(
					FaroInfoTestUtil.buildLiferayDataSourceJSONObject())));

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
				dataSourceJSONObject.toString()));

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
			_dxpExtractorConfigurationDog.getState(
				Mockito.any(JSONObject.class))
		).thenReturn(
			"CREDENTIALS_VALID"
		);

		Mockito.when(
			_salesforceExtractorConfigurationDog.getState(
				Mockito.any(JSONObject.class))
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
	private DataSourcesRestController _dataSourcesRestController;

	@Mock
	private DXPExtractorConfigurationDog _dxpExtractorConfigurationDog;

	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Mock
	private SalesforceExtractorConfigurationDog
		_salesforceExtractorConfigurationDog;

	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}