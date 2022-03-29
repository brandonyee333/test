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

package com.liferay.osb.asah.upgrade.v3_2_0.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.SalesforceEntityRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_2_0.SalesforceEntityMigrationUpgradeStep;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Robson Pastor
 */
public class SalesforceEntityMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_salesforceEntityRepository.deleteAll();
		_dataSourceRepository.deleteAll();

		DataSource dataSource = new DataSource("Liferay Brazil");

		dataSource.setCredentialType("Token Authentication");

		dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource.setId(342837044336786766L);
		dataSource.setIsNew(true);
		dataSource.setProviderType("SALESFORCE");

		_dataSourceRepository.save(dataSource);

		_setupIndex("account");
		_setupIndex("contact");
		_setupIndex("individuals");
		_setupIndex("lead");
	}

	@AfterEach
	public void tearDown() throws Exception {
		_elasticsearchIndexManager.delete("test_osbasahsalesforceraw_account");
		_elasticsearchIndexManager.delete("test_osbasahsalesforceraw_contact");
		_elasticsearchIndexManager.delete(
			"test_osbasahsalesforceraw_individuals");
		_elasticsearchIndexManager.delete("test_osbasahsalesforceraw_lead");

		_salesforceEntityRepository.deleteAll();
		_dataSourceRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray accountJSONArray = _getJSONArray("account");
		JSONArray contactJSONArray = _getJSONArray("contact");
		JSONArray individualJSONArray = _getJSONArray("individuals");
		JSONArray leadJSONArray = _getJSONArray("lead");

		_salesforceRawElasticsearchInvoker.add(
			SalesforceEntity.Type.ACCOUNT.getValue(), accountJSONArray);
		_salesforceRawElasticsearchInvoker.add(
			SalesforceEntity.Type.CONTACT.getValue(), contactJSONArray);
		_salesforceRawElasticsearchInvoker.add(
			SalesforceEntity.Type.INDIVIDUAL.getValue(), individualJSONArray);
		_salesforceRawElasticsearchInvoker.add(
			SalesforceEntity.Type.LEAD.getValue(), leadJSONArray);

		JSONArray jsonArray = new JSONArray();

		for (Object accountObject : accountJSONArray) {
			jsonArray.put(accountObject);
		}

		for (Object contactObject : contactJSONArray) {
			jsonArray.put(contactObject);
		}

		for (Object individualObject : individualJSONArray) {
			jsonArray.put(individualObject);
		}

		for (Object leadObject : leadJSONArray) {
			jsonArray.put(leadObject);
		}

		Assertions.assertEquals(5, jsonArray.length());

		_salesforceEntityMigrationUpgradeStep.upgrade("");

		List<Object> jsonObjectList = jsonArray.toList();

		Stream<Object> stream = jsonObjectList.stream();

		Assertions.assertEquals(
			stream.map(
				object -> _objectMapper.convertValue(
					object, SalesforceEntity.class)
			).sorted(
				Comparator.comparing(SalesforceEntity::getId)
			).collect(
				Collectors.toList()
			),
			_salesforceEntityRepository.findAll());
	}

	private JSONArray _getJSONArray(String collectionName) throws Exception {
		return new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					String.format(
						"dependencies/osbasahsalesforceraw/%s.json",
						collectionName),
					this)));
	}

	private void _setupIndex(String collectionName) throws Exception {
		String indexName = String.format(
			"test_osbasahsalesforceraw_%s", collectionName);

		_elasticsearchIndexManager.delete(indexName);

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				String.format(
					"dependencies/osbasahsalesforceraw" +
						"/%s_index_configuration.json",
					collectionName),
				this),
			indexName);

		_elasticsearchIndexManager.addAlias(
			String.format("%s_alias", indexName), indexName);
	}

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SalesforceEntityMigrationUpgradeStep
		_salesforceEntityMigrationUpgradeStep;

	@Autowired
	private SalesforceEntityRepository _salesforceEntityRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}