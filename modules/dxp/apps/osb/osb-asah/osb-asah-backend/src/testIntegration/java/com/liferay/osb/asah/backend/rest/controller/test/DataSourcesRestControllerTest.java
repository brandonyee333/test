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

import com.liferay.osb.asah.backend.dto.DataSourceDTO;
import com.liferay.osb.asah.backend.rest.controller.DataSourcesRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.batch.curator.bot.nanite.DeleteDataSourcesNanite;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AccountDog;
import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.dog.IndividualDog;
import com.liferay.osb.asah.common.dog.SalesforceAuditEventDog;
import com.liferay.osb.asah.common.dog.SalesforceEntityDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.ActivityGroup;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.RunLog;
import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AccountRepository;
import com.liferay.osb.asah.common.repository.ActivityGroupRepository;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.RunLogRepository;
import com.liferay.osb.asah.common.repository.SalesforceAuditEventRepository;
import com.liferay.osb.asah.common.salesforce.extractor.dog.SalesforceExtractorConfigurationDog;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.faro.backend.http.DataSourceHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahElasticsearchTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringExtension;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * @author Rachael Koestartyo
 * @author Vishal Reddy
 */
@ExtendWith(OSBAsahSpringExtension.class)
@SpringBootTest(
	classes = {
		DataSourceHttpTestConfiguration.class,
		OSBAsahBackendSpringBootApplication.class
	}
)
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.REPLACE_DEFAULTS,
	value = {
		DependencyInjectionTestExecutionListener.class,
		MockitoTestExecutionListener.class,
		OSBAsahElasticsearchTestExecutionListener.class,
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class
	}
)
public class DataSourcesRestControllerTest {

	@BeforeEach
	public void setUp() {
		_mock();
	}

	@Test
	public void testDeleteDataSource() throws Exception {
		DataSource dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		JSONObject dataSourceJSONObject = new JSONObject(
			_dataSourcesRestController.postDataSource(
				_objectMapper.convertValue(dataSource, DataSourceDTO.class)));

		dataSource.setId(Long.valueOf(dataSourceJSONObject.getString("id")));

		Account account = FaroInfoTestUtil.buildAccount(dataSource);

		account = _accountDog.addAccount(
			String.valueOf(account.getId()),
			_objectMapper.convertValue(account, JSONObject.class), dataSource);

		Individual individual = FaroInfoTestUtil.buildIndividual(dataSource);

		individual = _individualDog.addIndividual(individual, false);

		ActivityGroup activityGroup = _activityGroupDog.addActivityGroup(
			FaroInfoTestUtil.buildActivityGroup(
				dataSource.getId(), individual));

		Asset asset = _assetRepository.save(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildAssetJSONObject(
					"Page", dataSource.getId()),
				Asset.class));

		JSONObject activityJSONObject = _faroInfoElasticsearchInvoker.add(
			"activities",
			FaroInfoTestUtil.buildActivityJSONObject(
				_objectMapper.convertValue(activityGroup, JSONObject.class),
				_objectMapper.convertValue(asset, JSONObject.class),
				"pageViewed", new String[0]));

		_dataSourcesRestController.deleteDataSource(
			dataSourceJSONObject.getLong("id"));

		JSONObject updateDataSourceJSONObject = _objectMapper.convertValue(
			_dataSourcesRestController.getDataSourceDTO(
				dataSourceJSONObject.getLong("id")),
			JSONObject.class);

		Assertions.assertTrue(updateDataSourceJSONObject.has("deletionDate"));
		Assertions.assertEquals(
			"IN_PROGRESS_DELETING",
			updateDataSourceJSONObject.getString("state"));

		_runDeleteDataSourcesNanite(updateDataSourceJSONObject);

		Assertions.assertFalse(_accountRepository.existsById(account.getId()));
		Assertions.assertTrue(
			_faroInfoElasticsearchInvoker.exists(
				"activities", activityJSONObject.getString("id")));

		Long activityGroupId = activityGroup.getId();

		if (activityGroupId == null) {
			activityGroupId = 0L;
		}

		Assertions.assertTrue(
			_activityGroupRepository.existsById(activityGroupId));

		Assertions.assertTrue(_assetRepository.existsById(asset.getId()));
		Assertions.assertFalse(
			_faroInfoElasticsearchInvoker.exists(
				"data-sources", dataSourceJSONObject.getString("id")));
		Assertions.assertFalse(
			_faroInfoElasticsearchInvoker.exists(
				"individual-segments",
				QueryBuilders.termQuery(
					"name", "Account: " + account.getId())));
		Assertions.assertFalse(
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
	public void testDuplicateDataSourceName() {
		DataSource dataSource = FaroInfoTestUtil.buildLiferayDataSource(
			"Liferay", RandomTestUtil.randomURL());

		for (int i = 0; i < 4; i++) {
			_dataSourcesRestController.postDataSource(
				_objectMapper.convertValue(dataSource, DataSourceDTO.class));
		}

		JSONObject responseJSONObject = _objectMapper.convertValue(
			_dataSourcesRestController.getDataSourceDTOPageDTO(
				null, 0, 10, null),
			JSONObject.class);

		JSONArray dataSourcesJSONArray = (JSONArray)responseJSONObject.query(
			"/_embedded/data-sources");

		Assertions.assertEquals(4, dataSourcesJSONArray.length());

		Set<String> dataSourceNames = new HashSet<>();

		for (int i = 0; i < dataSourcesJSONArray.length(); i++) {
			JSONObject dataSourceJSONObject =
				dataSourcesJSONArray.getJSONObject(i);

			dataSourceNames.add(dataSourceJSONObject.getString("name"));
		}

		Assertions.assertTrue(dataSourceNames.contains("Liferay"));
		Assertions.assertTrue(dataSourceNames.contains("Liferay (1)"));
		Assertions.assertTrue(dataSourceNames.contains("Liferay (2)"));
		Assertions.assertTrue(dataSourceNames.contains("Liferay (3)"));
	}

	@Test
	public void testGetCSVDataSourceProgress() {
		DataSource csvDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildCSVDataSource());

		// CSV individuals nanite is null

		Assertions.assertEquals(
			"{}",
			_dataSourcesRestController.getProgress(csvDataSource.getId()));

		// CSV individuals nanite started

		RunLog runLog1 = new RunLog();

		runLog1.setContextJSONObject(
			JSONUtil.put(
				"processedOperations", 1
			).put(
				"reprocess", false
			).put(
				"totalOperations", 1
			));
		runLog1.setDataSourceId(csvDataSource.getId());
		runLog1.setDateLogged(DateUtil.newDate());
		runLog1.setNaniteClassName("CSVIndividualsNanite");
		runLog1.setStatus("STARTED");

		_runLogRepository.save(runLog1);

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(csvDataSource.getId()));

		Assertions.assertEquals(
			1, progressJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", progressJSONObject.getString("status"));

		// CSV individuals nanite failed

		Date loggedDate = DateUtil.newDate();

		RunLog runLog2 = new RunLog();

		runLog2.setContextJSONObject(
			JSONUtil.put(
				"processedOperations", 1
			).put(
				"reprocess", false
			).put(
				"totalOperations", 1
			));
		runLog2.setDataSourceId(csvDataSource.getId());
		runLog2.setDateLogged(loggedDate);
		runLog2.setNaniteClassName("CSVIndividualsNanite");
		runLog2.setStatus("FAILED");

		_runLogRepository.save(runLog2);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateRecorded", DateUtil.toUTCString(loggedDate)
			).put(
				"status", "FAILED"
			),
			new JSONObject(
				_dataSourcesRestController.getProgress(csvDataSource.getId())),
			true);
	}

	@Test
	public void testGetDataSources() {
		_dataSourcesRestController.postDataSource(
			_objectMapper.convertValue(
				FaroInfoTestUtil.buildLiferayDataSource(),
				DataSourceDTO.class));

		JSONObject dataSourcesJSONObject = _objectMapper.convertValue(
			_dataSourcesRestController.getDataSourceDTOPageDTO(
				null, 0, 20, null),
			JSONObject.class);

		JSONObject embeddedJSONObject = dataSourcesJSONObject.getJSONObject(
			"_embedded");

		JSONArray dataSourcesJSONArray = embeddedJSONObject.getJSONArray(
			"data-sources");

		Assertions.assertEquals(1, dataSourcesJSONArray.length());

		JSONObject dataSourceJSONObject = dataSourcesJSONArray.getJSONObject(0);

		Assertions.assertFalse(
			dataSourceJSONObject.has("faroBackendSecuritySignature"));
	}

	@Test
	public void testGetSalesforceAccountsFields() throws Exception {
		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		JSONArray jsonArray = new JSONArray(
			_dataSourcesRestController.getSalesforceAccountsFields(
				salesforceDataSource.getId(), 3, 0));

		Assertions.assertEquals(3, jsonArray.length());
	}

	@Test
	public void testGetSalesforceDataSourceAccountsProgress() {
		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor nanite is null

		Assertions.assertEquals(
			JSONUtil.put(
				"accounts", new JSONObject()
			).put(
				"individuals", new JSONObject()
			).toString(),
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		// Salesforce accounts nanite is null

		Date loggedDate = DateUtil.newDate();

		RunLog runLog1 = new RunLog();

		runLog1.setDataSourceId(salesforceDataSource.getId());
		runLog1.setDateLogged(loggedDate);
		runLog1.setNaniteClassName("SalesforceExtractorNanite");
		runLog1.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog1);

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject accountsJSONObject = progressJSONObject.getJSONObject(
			"accounts");

		Assertions.assertEquals(
			1, accountsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", accountsJSONObject.getString("status"));
		Assertions.assertEquals(
			2, accountsJSONObject.getInt("totalOperations"));

		// Salesforce accounts nanite is not null and date logged is earlier

		RunLog runLog2 = new RunLog();

		runLog2.setContextJSONObject(
			JSONUtil.put(
				"reprocess", false
			).put(
				"totalOperations", 1
			));
		runLog2.setDataSourceId(salesforceDataSource.getId());
		runLog2.setDateLogged(DateUtil.addDays(loggedDate, -1));
		runLog2.setNaniteClassName("SalesforceAccountsNanite");
		runLog2.setStatus("FAILED");

		_runLogRepository.save(runLog2);

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		accountsJSONObject = progressJSONObject.getJSONObject("accounts");

		Assertions.assertEquals(
			1, accountsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", accountsJSONObject.getString("status"));
		Assertions.assertEquals(
			2, accountsJSONObject.getInt("totalOperations"));

		// Salesforce accounts nanite is not null and date logged is after

		RunLog runLog3 = new RunLog();

		runLog3.setContextJSONObject(
			JSONUtil.put(
				"reprocess", false
			).put(
				"totalOperations", 1
			));
		runLog3.setDataSourceId(salesforceDataSource.getId());
		runLog3.setDateLogged(DateUtil.addDays(loggedDate, 1));
		runLog3.setNaniteClassName("SalesforceAccountsNanite");
		runLog3.setStatus("FAILED");

		runLog3 = _runLogRepository.save(runLog3);

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		accountsJSONObject = progressJSONObject.getJSONObject("accounts");

		Assertions.assertEquals(
			DateUtil.addDays(loggedDate, 1),
			DateUtil.toUTCDate(accountsJSONObject.getString("dateRecorded")));
		Assertions.assertEquals(
			"FAILED", accountsJSONObject.getString("status"));

		_runLogRepository.delete(runLog3);

		// Salesforce accounts nanite started

		RunLog runLog4 = new RunLog();

		runLog4.setContextJSONObject(
			JSONUtil.put(
				"reprocess", false
			).put(
				"totalOperations", 1
			));
		runLog4.setDataSourceId(salesforceDataSource.getId());
		runLog4.setDateLogged(DateUtil.newDate());
		runLog4.setNaniteClassName("SalesforceAccountsNanite");
		runLog4.setStatus("STARTED");

		_runLogRepository.save(runLog4);

		SalesforceAuditEvent salesforceAuditEvent1 = new SalesforceAuditEvent();

		salesforceAuditEvent1.setDataSourceId(salesforceDataSource.getId());
		salesforceAuditEvent1.setEntityTypeName("Account");

		_salesforceAuditEventRepository.save(salesforceAuditEvent1);

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		accountsJSONObject = progressJSONObject.getJSONObject("accounts");

		Assertions.assertEquals(
			1, accountsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", accountsJSONObject.getString("status"));
		Assertions.assertEquals(
			2, accountsJSONObject.getInt("totalOperations"));

		// Salesforce extractor nanite failed

		Date newLoggedDate = DateUtil.newDate();

		RunLog runLog5 = new RunLog();

		runLog5.setDataSourceId(salesforceDataSource.getId());
		runLog5.setDateLogged(newLoggedDate);
		runLog5.setNaniteClassName("SalesforceExtractorNanite");
		runLog5.setStatus("FAILED");

		_runLogRepository.save(runLog5);

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		accountsJSONObject = progressJSONObject.getJSONObject("accounts");

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateRecorded", DateUtil.toUTCString(newLoggedDate)
			).put(
				"status", "FAILED"
			),
			accountsJSONObject, true);

		// Salesforce extractor nanite started and initial run is true

		RunLog runLog6 = new RunLog();

		runLog6.setContextJSONObject(
			JSONUtil.put(
				"initialAccountRun", true
			).put(
				"totalAccountOperations", 1
			));
		runLog6.setDataSourceId(salesforceDataSource.getId());
		runLog6.setDateLogged(DateUtil.newDate());
		runLog6.setNaniteClassName("SalesforceExtractorNanite");
		runLog6.setStatus("STARTED");

		_runLogRepository.save(runLog6);

		SalesforceEntity salesforceEntity = new SalesforceEntity();

		salesforceEntity.setDataSourceId(salesforceDataSource.getId());
		salesforceEntity.setFieldsJSONObject(
			JSONUtil.put("Name", "Test Account"));
		salesforceEntity.setId("1");
		salesforceEntity.setType(SalesforceEntity.Type.ACCOUNT);

		_salesforceEntityDog.saveSalesforceEntity(salesforceEntity);

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		accountsJSONObject = progressJSONObject.getJSONObject("accounts");

		Assertions.assertEquals(
			1, accountsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", accountsJSONObject.getString("status"));
		Assertions.assertEquals(
			2, accountsJSONObject.getInt("totalOperations"));

		// Salesforce extractor nanite started and initial run is false

		RunLog runLog7 = new RunLog();

		runLog7.setContextJSONObject(
			JSONUtil.put(
				"initialAccountRun", false
			).put(
				"totalAccountOperations", 1
			));
		runLog7.setDataSourceId(salesforceDataSource.getId());
		runLog7.setDateLogged(DateUtil.newDate());
		runLog7.setNaniteClassName("SalesforceExtractorNanite");
		runLog7.setStatus("STARTED");

		_runLogRepository.save(runLog7);

		SalesforceAuditEvent salesforceAuditEvent2 = new SalesforceAuditEvent();

		salesforceAuditEvent2.setDataSourceId(salesforceDataSource.getId());
		salesforceAuditEvent2.setEntityTypeName("Account");

		_salesforceAuditEventRepository.save(salesforceAuditEvent2);

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		accountsJSONObject = progressJSONObject.getJSONObject("accounts");

		Assertions.assertEquals(
			2, accountsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", accountsJSONObject.getString("status"));
		Assertions.assertEquals(
			2, accountsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress1() {
		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor nanite is null

		Assertions.assertEquals(
			JSONUtil.put(
				"accounts", new JSONObject()
			).put(
				"individuals", new JSONObject()
			).toString(),
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress2() {
		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor nanite failed

		Date loggedDate = DateUtil.newDate();

		RunLog runLog = new RunLog();

		runLog.setDataSourceId(salesforceDataSource.getId());
		runLog.setDateLogged(loggedDate);
		runLog.setNaniteClassName("SalesforceExtractorNanite");
		runLog.setStatus("FAILED");

		_runLogRepository.save(runLog);

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateRecorded", DateUtil.toUTCString(loggedDate)
			).put(
				"status", "FAILED"
			),
			progressJSONObject.getJSONObject("individuals"), true);
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress3() {
		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor nanite started and initial run is true

		Date loggedDate = DateUtil.newDate();

		RunLog runLog1 = new RunLog();

		runLog1.setContextJSONObject(
			JSONUtil.put(
				"initialContactRun", true
			).put(
				"initialLeadRun", true
			).put(
				"totalContactOperations", 1
			).put(
				"totalLeadOperations", 1
			));
		runLog1.setDataSourceId(salesforceDataSource.getId());
		runLog1.setDateLogged(loggedDate);
		runLog1.setNaniteClassName("SalesforceExtractorNanite");
		runLog1.setStatus("STARTED");

		_runLogRepository.save(runLog1);

		SalesforceEntity contactSalesforceEntity = new SalesforceEntity();

		contactSalesforceEntity.setDataSourceId(salesforceDataSource.getId());
		contactSalesforceEntity.setFieldsJSONObject(
			JSONUtil.put("Name", "Test Contact"));
		contactSalesforceEntity.setId("2");
		contactSalesforceEntity.setType(SalesforceEntity.Type.CONTACT);

		_salesforceEntityDog.saveSalesforceEntity(contactSalesforceEntity);

		SalesforceEntity leadSalesforceEntity = new SalesforceEntity();

		leadSalesforceEntity.setDataSourceId(salesforceDataSource.getId());
		leadSalesforceEntity.setFieldsJSONObject(
			JSONUtil.put("Name", "Test Lead"));
		leadSalesforceEntity.setId("3");
		leadSalesforceEntity.setType(SalesforceEntity.Type.LEAD);

		_salesforceEntityDog.saveSalesforceEntity(leadSalesforceEntity);

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assertions.assertEquals(
			2, individualsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assertions.assertEquals(
			6, individualsJSONObject.getInt("totalOperations"));

		// Salesforce extractor nanite started and initial run is false

		RunLog runLog2 = new RunLog();

		runLog2.setContextJSONObject(
			JSONUtil.put(
				"initialContactRun", true
			).put(
				"initialLeadRun", true
			).put(
				"totalContactOperations", 1
			).put(
				"totalLeadOperations", 1
			));
		runLog2.setDataSourceId(salesforceDataSource.getId());
		runLog2.setDateLogged(loggedDate);
		runLog2.setNaniteClassName("SalesforceExtractorNanite");
		runLog2.setStatus("STARTED");

		_runLogRepository.save(runLog2);

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"typeName", "Contact"
			));

		_salesforceRawElasticsearchInvoker.add(
			"audit-events",
			JSONUtil.put(
				"dataSourceId", String.valueOf(salesforceDataSource.getId())
			).put(
				"typeName", "Lead"
			));

		progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		individualsJSONObject = progressJSONObject.getJSONObject("individuals");

		Assertions.assertEquals(
			2, individualsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assertions.assertEquals(
			6, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress4() {
		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor nanite in progress and salesforce extractor
		// individuals nanite is null

		RunLog runLog = new RunLog();

		runLog.setDataSourceId(salesforceDataSource.getId());
		runLog.setDateLogged(DateUtil.newDate());
		runLog.setNaniteClassName("SalesforceExtractorNanite");
		runLog.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog);

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assertions.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assertions.assertEquals(
			1, individualsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assertions.assertEquals(
			3, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress5() {
		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor individuals nanite started

		RunLog runLog1 = new RunLog();

		runLog1.setDataSourceId(salesforceDataSource.getId());
		runLog1.setDateLogged(DateUtil.newDate());
		runLog1.setNaniteClassName("SalesforceExtractorNanite");
		runLog1.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog1);

		RunLog runLog2 = new RunLog();

		runLog2.setContextJSONObject(JSONUtil.put("totalOperations", 1));
		runLog2.setDataSourceId(salesforceDataSource.getId());
		runLog2.setNaniteClassName("SalesforceExtractorIndividualsNanite");
		runLog2.setStatus("STARTED");

		_runLogRepository.save(runLog2);

		SalesforceAuditEvent salesforceAuditEvent1 = new SalesforceAuditEvent();

		salesforceAuditEvent1.setDataSourceId(salesforceDataSource.getId());
		salesforceAuditEvent1.setEntityTypeName("Contact");

		_salesforceAuditEventRepository.save(salesforceAuditEvent1);

		SalesforceAuditEvent salesforceAuditEvent2 = new SalesforceAuditEvent();

		salesforceAuditEvent2.setDataSourceId(salesforceDataSource.getId());
		salesforceAuditEvent2.setEntityTypeName("Lead");

		_salesforceAuditEventRepository.save(salesforceAuditEvent2);

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assertions.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assertions.assertEquals(
			0, individualsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assertions.assertEquals(
			3, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress6() {
		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor individuals nanite not started and date logged
		// is earlier

		Date loggedDate = DateUtil.newDate();

		RunLog runLog1 = new RunLog();

		runLog1.setDataSourceId(salesforceDataSource.getId());
		runLog1.setDateLogged(loggedDate);
		runLog1.setNaniteClassName("SalesforceExtractorNanite");
		runLog1.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog1);

		RunLog runLog2 = new RunLog();

		runLog2.setContextJSONObject(JSONUtil.put("totalOperations", 1));
		runLog2.setDataSourceId(salesforceDataSource.getId());
		runLog2.setDateLogged(DateUtil.addDays(loggedDate, -1));
		runLog2.setNaniteClassName("SalesforceExtractorIndividualsNanite");
		runLog2.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog2);

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assertions.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assertions.assertEquals(
			1, individualsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assertions.assertEquals(
			3, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress7() {
		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce extractor individuals nanite failed and date logged
		// is later

		Date loggedDate = DateUtil.newDate();

		RunLog runLog1 = new RunLog();

		runLog1.setDataSourceId(salesforceDataSource.getId());
		runLog1.setDateLogged(
			DateUtil.toUTCDate(
				DateUtil.addHours(DateUtil.toUTCString(loggedDate), -1)));
		runLog1.setNaniteClassName("SalesforceExtractorNanite");
		runLog1.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog1);

		RunLog runLog2 = new RunLog();

		runLog2.setContextJSONObject(JSONUtil.put("totalOperations", 1));
		runLog2.setDataSourceId(salesforceDataSource.getId());
		runLog2.setDateLogged(loggedDate);
		runLog2.setNaniteClassName("SalesforceExtractorNanite");
		runLog2.setStatus("FAILED");

		_runLogRepository.save(runLog2);

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		JSONAssert.assertEquals(
			JSONUtil.put(
				"dateRecorded", DateUtil.toUTCString(loggedDate)
			).put(
				"status", "FAILED"
			),
			individualsJSONObject, true);
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress8() {
		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		Date loggedDate = DateUtil.newDate();

		RunLog runLog1 = new RunLog();

		runLog1.setDataSourceId(salesforceDataSource.getId());
		runLog1.setDateLogged(
			DateUtil.toUTCDate(
				DateUtil.addHours(DateUtil.toUTCString(loggedDate), -1)));
		runLog1.setNaniteClassName("SalesforceExtractorNanite");
		runLog1.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog1);

		RunLog runLog2 = new RunLog();

		runLog2.setContextJSONObject(JSONUtil.put("totalOperations", 1));
		runLog2.setDataSourceId(salesforceDataSource.getId());
		runLog2.setDateLogged(loggedDate);
		runLog2.setNaniteClassName("SalesforceExtractorIndividualsNanite");
		runLog2.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog2);

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assertions.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assertions.assertEquals(
			2, individualsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assertions.assertEquals(
			3, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress9() {
		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce individuals nanite started

		Date loggedDate = DateUtil.newDate();

		RunLog runLog1 = new RunLog();

		runLog1.setDataSourceId(salesforceDataSource.getId());
		runLog1.setDateLogged(
			DateUtil.toUTCDate(
				DateUtil.addHours(DateUtil.toUTCString(loggedDate), -1)));
		runLog1.setNaniteClassName("SalesforceExtractorNanite");
		runLog1.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog1);

		RunLog runLog2 = new RunLog();

		runLog2.setContextJSONObject(JSONUtil.put("totalOperations", 1));
		runLog2.setDataSourceId(salesforceDataSource.getId());
		runLog2.setDateLogged(loggedDate);
		runLog2.setNaniteClassName("SalesforceExtractorIndividualsNanite");
		runLog2.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog2);

		RunLog runLog3 = new RunLog();

		runLog3.setContextJSONObject(JSONUtil.put("totalOperations", 2));
		runLog3.setDataSourceId(salesforceDataSource.getId());
		runLog3.setDateLogged(
			DateUtil.toUTCDate(DateUtil.toUTCString(loggedDate)));
		runLog3.setNaniteClassName("SalesforceIndividualsNanite");
		runLog3.setStatus("STARTED");

		_runLogRepository.save(runLog3);

		SalesforceAuditEvent salesforceAuditEvent = new SalesforceAuditEvent();

		salesforceAuditEvent.setDataSourceId(salesforceDataSource.getId());
		salesforceAuditEvent.setEntityTypeName("individuals");

		_salesforceAuditEventRepository.save(salesforceAuditEvent);

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assertions.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assertions.assertEquals(
			5, individualsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assertions.assertEquals(
			6, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress10()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce individuals nanite not started and and date logged is
		// earlier

		Date loggedDate = DateUtil.newDate();

		RunLog runLog1 = new RunLog();

		runLog1.setDataSourceId(salesforceDataSource.getId());
		runLog1.setDateLogged(
			DateUtil.toUTCDate(
				DateUtil.addHours(DateUtil.toUTCString(loggedDate), -1)));
		runLog1.setNaniteClassName("SalesforceExtractorNanite");
		runLog1.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog1);

		RunLog runLog2 = new RunLog();

		runLog2.setContextJSONObject(JSONUtil.put("totalOperations", 1));
		runLog2.setDataSourceId(salesforceDataSource.getId());
		runLog2.setDateLogged(
			DateUtil.toUTCDate(
				DateUtil.addHours(DateUtil.toUTCString(loggedDate), 1)));
		runLog2.setNaniteClassName("SalesforceExtractorIndividualsNanite");
		runLog2.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog2);

		RunLog runLog3 = new RunLog();

		runLog3.setContextJSONObject(JSONUtil.put("totalOperations", 2));
		runLog3.setDataSourceId(salesforceDataSource.getId());
		runLog3.setDateLogged(loggedDate);
		runLog3.setNaniteClassName("SalesforceIndividualsNanite");
		runLog3.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog3);

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assertions.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assertions.assertEquals(
			2, individualsJSONObject.getInt("processedOperations"));
		Assertions.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
		Assertions.assertEquals(
			3, individualsJSONObject.getInt("totalOperations"));
	}

	@Test
	public void testGetSalesforceDataSourceIndividualsProgress11()
		throws Exception {

		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		// Salesforce individuals nanite not started and and date logged is
		// later

		Date loggedDate = DateUtil.newDate();

		RunLog runLog1 = new RunLog();

		runLog1.setDataSourceId(salesforceDataSource.getId());
		runLog1.setDateLogged(
			DateUtil.toUTCDate(
				DateUtil.addHours(DateUtil.toUTCString(loggedDate), -1)));
		runLog1.setNaniteClassName("SalesforceExtractorNanite");
		runLog1.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog1);

		RunLog runLog2 = new RunLog();

		runLog2.setContextJSONObject(JSONUtil.put("totalOperations", 1));
		runLog2.setDataSourceId(salesforceDataSource.getId());
		runLog2.setDateLogged(loggedDate);
		runLog2.setNaniteClassName("SalesforceExtractorIndividualsNanite");
		runLog2.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog2);

		RunLog runLog3 = new RunLog();

		runLog3.setContextJSONObject(JSONUtil.put("totalOperations", 2));
		runLog3.setDataSourceId(salesforceDataSource.getId());
		runLog3.setDateLogged(
			DateUtil.toUTCDate(
				DateUtil.addHours(DateUtil.toUTCString(loggedDate), 2)));
		runLog3.setNaniteClassName("SalesforceIndividualsNanite");
		runLog3.setStatus("IN_PROGRESS");

		_runLogRepository.save(runLog3);

		JSONObject progressJSONObject = new JSONObject(
			_dataSourcesRestController.getProgress(
				salesforceDataSource.getId()));

		JSONObject individualsJSONObject = progressJSONObject.getJSONObject(
			"individuals");

		Assertions.assertTrue(individualsJSONObject.has("dateRecorded"));
		Assertions.assertEquals(
			"IN_PROGRESS", individualsJSONObject.getString("status"));
	}

	@Test
	public void testGetSalesforceUsersFields() throws Exception {
		DataSource salesforceDataSource = _dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource());

		JSONArray jsonArray = new JSONArray(
			_dataSourcesRestController.getSalesforceUsersFields(
				salesforceDataSource.getId(), 3, 0));

		Assertions.assertEquals(3, jsonArray.length());
	}

	private void _mock() {
		Mockito.when(
			_salesforceExtractorConfigurationDog.getState(
				ArgumentMatchers.any(DataSource.class))
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
	private AccountDog _accountDog;

	@Autowired
	private AccountRepository _accountRepository;

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@Autowired
	private ActivityGroupRepository _activityGroupRepository;

	@Autowired
	private AssetRepository _assetRepository;

	@Autowired
	private AutowireCapableBeanFactory _autowireCapableBeanFactory;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private DataSourcesRestController _dataSourcesRestController;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private IndividualDog _individualDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private RunLogRepository _runLogRepository;

	@Autowired
	private SalesforceAuditEventDog _salesforceAuditEventDog;

	@Autowired
	private SalesforceAuditEventRepository _salesforceAuditEventRepository;

	@Autowired
	private SalesforceEntityDog _salesforceEntityDog;

	@Mock
	private SalesforceExtractorConfigurationDog
		_salesforceExtractorConfigurationDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}