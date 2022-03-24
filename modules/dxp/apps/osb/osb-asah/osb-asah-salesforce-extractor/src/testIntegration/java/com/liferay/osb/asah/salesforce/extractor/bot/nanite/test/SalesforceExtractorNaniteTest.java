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

import com.liferay.osb.asah.common.dog.RunLogDog;
import com.liferay.osb.asah.common.dog.SalesforceEntityDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.AsahMarkerRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
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
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringExtension;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author Rachael Koestartyo
 */
@ExtendWith(OSBAsahSpringExtension.class)
@SpringBootTest(
	classes = {
		OSBAsahSalesforceExtractorSpringBootApplication.class,
		SalesforceExtractorNaniteTest.
			SalesforceExtractorNaniteTestConfiguration.class
	}
)
public class SalesforceExtractorNaniteTest
	implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_elasticsearchIndexManager.clearIndices();

		_elasticsearchIndexManager.checkIndices();

		Assumptions.assumeTrue(
			StringUtils.isNotEmpty(
				_salesforceExtractorConfiguration.
					getSalesforceAuthEndpoint()) &&
			StringUtils.isNotEmpty(
				_salesforceExtractorConfiguration.getSalesforcePassword()) &&
			StringUtils.isNotEmpty(
				_salesforceExtractorConfiguration.getSalesforceUserName()));

		_dataSourceRepository.save(
			FaroInfoTestUtil.buildSalesforceDataSource(
				_salesforceExtractorConfiguration.getSalesforceUserName(),
				_salesforceExtractorConfiguration.getSalesforcePassword(),
				_salesforceExtractorConfiguration.getSalesforceURL()));
	}

	@AfterEach
	public void tearDown() {
		_asahMarkerRepository.deleteAll();
		_dataSourceRepository.deleteAll();
		_salesforceEntityDog.deleteSalesforceEntities(1L);
		_salesforceRawElasticsearchInvoker.delete(
			"audit-events", QueryBuilders.matchAllQuery());

		_elasticsearchIndexManager.clearIndices();
	}

	@Test
	public void test() throws Exception {
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
		public RunLogDog runLogDog() {
			return new RunLogDog();
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

	private void _testAddField() {
		Iterable<AsahMarker> iterable = _asahMarkerRepository.findAll();

		Iterator<AsahMarker> iterator = iterable.iterator();

		AsahMarker asahMarker = iterator.next();

		JSONObject context = asahMarker.getContextJSONObject();

		JSONObject tablesJSONObject = (JSONObject)context.get("tables");

		JSONObject tableJSONObject = tablesJSONObject.getJSONObject("Account");

		JSONObject fieldsJSONObject = new JSONObject(
			tableJSONObject.getString("fields"));

		fieldsJSONObject.remove("Name");

		tableJSONObject.put("fields", fieldsJSONObject.toString());

		_asahMarkerRepository.save(asahMarker);

		List<SalesforceEntity> salesforceEntities =
			_salesforceEntityDog.getSalesforceEntities(
				1L, 0, 100, SalesforceEntity.Type.ACCOUNT);

		for (SalesforceEntity salesforceEntity : salesforceEntities) {
			JSONObject jsonObject = salesforceEntity.getFieldsJSONObject();

			jsonObject.remove("Name");
		}

		_salesforceEntityDog.saveSalesforceEntities(salesforceEntities);

		salesforceEntities = _salesforceEntityDog.getSalesforceEntities(
			1L, 0, 100, SalesforceEntity.Type.ACCOUNT);

		for (SalesforceEntity salesforceEntity : salesforceEntities) {
			JSONObject jsonObject = salesforceEntity.getFieldsJSONObject();

			Assertions.assertNotNull(jsonObject.opt("Name"));
		}
	}

	private void _testAddSObject() throws Exception {
		Iterable<AsahMarker> iterable = _asahMarkerRepository.findAll();

		Iterator<AsahMarker> iterator = iterable.iterator();

		AsahMarker asahMarker = iterator.next();

		JSONObject context = asahMarker.getContextJSONObject();

		context.put("tables", new JSONObject());

		_asahMarkerRepository.save(asahMarker);

		_salesforceEntityDog.deleteSalesforceEntities(1L);

		_salesforceExtractorNanite.run();

		List<SalesforceEntity> salesforceEntities =
			_salesforceEntityDog.getSalesforceEntities(
				1L, 0, 100, SalesforceEntity.Type.ACCOUNT);

		Assertions.assertFalse(salesforceEntities.isEmpty());
	}

	private void _testAuditEventsTable() {
		JSONArray auditEventsJSONArray = _salesforceRawElasticsearchInvoker.get(
			"audit-events");

		Assertions.assertNotNull(auditEventsJSONArray);

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

		Assertions.assertTrue(hasDelete && hasUpdate);
	}

	private void _testDeleteField() throws Exception {
		Iterable<AsahMarker> iterable = _asahMarkerRepository.findAll();

		Iterator<AsahMarker> iterator = iterable.iterator();

		AsahMarker asahMarker = iterator.next();

		JSONObject context = asahMarker.getContextJSONObject();

		context.put("tables", new JSONObject());

		JSONObject tablesJSONObject = context.getJSONObject("tables");

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

		_asahMarkerRepository.save(asahMarker);

		List<SalesforceEntity> salesforceEntities =
			_salesforceEntityDog.getSalesforceEntities(
				1L, 0, 100, SalesforceEntity.Type.ACCOUNT);

		for (SalesforceEntity salesforceEntity : salesforceEntities) {
			JSONObject curTableJSONObject =
				salesforceEntity.getFieldsJSONObject();

			curTableJSONObject.put("OSBAsahTest__c", "test");
		}

		_salesforceEntityDog.saveSalesforceEntities(salesforceEntities);

		_salesforceExtractorNanite.run();

		salesforceEntities = _salesforceEntityDog.getSalesforceEntities(
			1L, 0, 100, SalesforceEntity.Type.ACCOUNT);

		for (SalesforceEntity salesforceEntity : salesforceEntities) {
			JSONObject curTableJSONObject =
				salesforceEntity.getFieldsJSONObject();

			Assertions.assertNull(curTableJSONObject.opt("OSBAsahTest__c"));
		}
	}

	private void _testDeleteSObject() throws Exception {
		Iterable<AsahMarker> iterable = _asahMarkerRepository.findAll();

		Iterator<AsahMarker> iterator = iterable.iterator();

		AsahMarker asahMarker = iterator.next();

		JSONObject context = asahMarker.getContextJSONObject();

		context.put("tables", new JSONObject());

		JSONObject tablesJSONObject = context.getJSONObject("tables");

		JSONObject tableJSONObject = tablesJSONObject.getJSONObject("Account");

		tablesJSONObject.put("OSBAsahTest__c", tableJSONObject);

		_asahMarkerRepository.save(asahMarker);

		_salesforceExtractorNanite.run();

		List<SalesforceEntity> salesforceEntities =
			_salesforceEntityDog.getSalesforceEntities(
				1L, 0, 100, SalesforceEntity.Type.ACCOUNT);

		Assertions.assertEquals(0, salesforceEntities.size());
	}

	private void _testPopulateNewTables() throws Exception {
		_salesforceExtractorNanite.run();

		Iterable<AsahMarker> iterable = _asahMarkerRepository.findAll();

		Iterator<AsahMarker> iterator = iterable.iterator();

		AsahMarker asahMarker = iterator.next();

		JSONObject context = asahMarker.getContextJSONObject();

		JSONObject tablesJSONObject = (JSONObject)context.get("tables");

		JSONObject tableJSONObject = tablesJSONObject.getJSONObject("Account");

		Assertions.assertNotNull(tableJSONObject);

		List<SalesforceEntity> salesforceEntities =
			_salesforceEntityDog.getSalesforceEntities(
				1L, 0, 100, SalesforceEntity.Type.ACCOUNT);

		Assertions.assertFalse(salesforceEntities.isEmpty());
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

			List<SalesforceEntity> salesforceEntities =
				_salesforceEntityDog.getSalesforceEntities(
					1L, 0, 100, SalesforceEntity.Type.ACCOUNT);

			for (SalesforceEntity salesforceEntity : salesforceEntities) {
				if (Objects.equals(salesforceEntity.getId(), salesforceKey)) {
					added = true;

					break;
				}
			}

			if (added) {
				break;
			}

			Thread.sleep(20000);
		}

		Assertions.assertTrue(added);

		List<DeleteResult> deleteResults =
			_salesforcePartnerClientInvoker.deleteSObjects(
				_salesforceExtractorConfiguration,
				new String[] {salesforceKey});

		DeleteResult deleteResult = deleteResults.get(0);

		Thread.sleep(60000);

		boolean deleted = false;

		for (int i = 0; i < 3; i++) {
			_salesforceExtractorNanite.run();

			List<SalesforceEntity> salesforceEntities =
				_salesforceEntityDog.getSalesforceEntities(
					1L, 0, 100, SalesforceEntity.Type.ACCOUNT);

			boolean exists = false;

			for (SalesforceEntity salesforceEntity : salesforceEntities) {
				if (Objects.equals(
						salesforceEntity.getId(), deleteResult.getId())) {

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

		Assertions.assertTrue(deleted);
	}

	private static final SalesforceExtractorConfiguration
		_salesforceExtractorConfiguration =
			SalesforceExtractorTestUtil.getSalesforceExtractorConfiguration();

	@Autowired
	private AsahMarkerRepository _asahMarkerRepository;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private SalesforceEntityDog _salesforceEntityDog;

	@Autowired
	private SalesforceExtractorNanite _salesforceExtractorNanite;

	@Autowired
	private SalesforcePartnerClientInvoker _salesforcePartnerClientInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}