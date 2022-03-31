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
import com.liferay.osb.asah.common.entity.RunLog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.repository.RunLogRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_2_0.RunLogMigrationUpgradeStep;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leilany Ulisses
 */
public class RunLogMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_runLogRepository.deleteAll();
		_dataSourceRepository.deleteAll();

		DataSource dataSource = new DataSource();

		dataSource.setId(342837044336786766L);
		dataSource.setIsNew(Boolean.TRUE);

		_dataSourceRepository.save(dataSource);

		_setupIndex("osbasahdxpraw");
		_setupIndex("osbasahfaroinfo");
		_setupIndex("osbasahsalesforceraw");
	}

	@AfterEach
	public void tearDown() {
		_elasticsearchIndexManager.delete("test_osbasahdxpraw_run-logs");
		_elasticsearchIndexManager.delete("test_osbasahfaroinfo_run-logs");
		_elasticsearchIndexManager.delete("test_osbasahsalesforceraw_run-logs");

		_runLogRepository.deleteAll();
		_dataSourceRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray dxpRawJSONArray = _getJSONArray("osbasahdxpraw");

		JSONArray faroInfoJSONArray = _getJSONArray("osbasahfaroinfo");
		JSONArray salesForceRawJSONArray = _getJSONArray(
			"osbasahsalesforceraw");

		_dxpRawElasticsearchInvoker.add("run-logs", dxpRawJSONArray);
		_faroInfoElasticsearchInvoker.add("run-logs", faroInfoJSONArray);
		_salesforceRawElasticsearchInvoker.add(
			"run-logs", salesForceRawJSONArray);

		JSONArray jsonArray = new JSONArray();

		for (Object dxpRawObject : dxpRawJSONArray) {
			jsonArray.put(dxpRawObject);
		}

		for (Object faroInfoObject : faroInfoJSONArray) {
			jsonArray.put(faroInfoObject);
		}

		for (Object salesForceRawObject : salesForceRawJSONArray) {
			jsonArray.put(salesForceRawObject);
		}

		_runLogMigrationUpgradeStep.upgrade("");

		Assertions.assertEquals(
			JSONUtil.toList(
				jsonArray,
				jsonObject -> _objectMapper.convertValue(
					jsonObject, RunLog.class)),
			_runLogRepository.findAll());
	}

	private JSONArray _getJSONArray(String sourceName) throws Exception {
		return new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					String.format("dependencies/%s/run_logs.json", sourceName),
					this)));
	}

	private void _setupIndex(String indexSource) throws Exception {
		String indexName = String.format("test_%s_run-logs", indexSource);

		_elasticsearchIndexManager.delete(indexName);

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				String.format(
					"dependencies/%s/run_logs_index_configuration.json",
					indexSource),
				this),
			indexName);

		_elasticsearchIndexManager.addAlias(
			String.format("%s_alias", indexName), indexName);
	}

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private RunLogMigrationUpgradeStep _runLogMigrationUpgradeStep;

	@Autowired
	private RunLogRepository _runLogRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}