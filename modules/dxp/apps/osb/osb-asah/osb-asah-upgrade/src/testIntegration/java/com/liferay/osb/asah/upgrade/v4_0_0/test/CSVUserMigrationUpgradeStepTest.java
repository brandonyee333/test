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

package com.liferay.osb.asah.upgrade.v4_0_0.test;

import com.liferay.osb.asah.common.entity.BQCSVUser;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.repository.BQCSVUserRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.v4_0_0.CSVUserMigrationUpgradeStep;

import java.util.List;

import org.apache.commons.collections4.IterableUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leilany Ulisses
 */
public class CSVUserMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_bqCSVUserRepository.deleteAll();

		_dataSourceRepository.deleteAll();

		DataSource dataSource = new DataSource("Liferay Brazil");

		dataSource.setCredentialType("Token Authentication");

		dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource.setId(337984445922213329L);
		dataSource.setIsNew(Boolean.TRUE);
		dataSource.setProviderType("LIFERAY");

		_dataSourceRepository.save(dataSource);

		_elasticsearchIndexManager.delete(
			"test_osbasahfaroinfo_csv-individuals");

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies/csv_individuals_index_configuration.json", this),
			"test_osbasahfaroinfo_csv-individuals");

		_elasticsearchIndexManager.addAlias(
			"test_osbasahfaroinfo_csv-individuals_alias",
			"test_osbasahfaroinfo_csv-individuals");
	}

	@AfterEach
	public void tearDown() throws Exception {
		_elasticsearchIndexManager.delete(
			"test_osbasahfaroinfo_csv-individuals");

		_bqCSVUserRepository.deleteAll();
		_dataSourceRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/csv_individuals.json", this)));

		Assertions.assertFalse(jsonArray.isEmpty());

		_elasticsearchInvoker.add("csv-individuals", jsonArray);

		_csvUserMigrationUpgradeStep.upgrade("");

		List<BQCSVUser> bqCSVUsers = IterableUtils.toList(
			_bqCSVUserRepository.findAll());

		Assertions.assertEquals(1, bqCSVUsers.size());

		BQCSVUser bqCSVUser = bqCSVUsers.get(0);

		Assertions.assertEquals(
			337984445922213329L, bqCSVUser.getDataSourceId());

		JSONArray fieldsJSONArray = bqCSVUser.getFieldsJSONArray();

		Assertions.assertEquals(1, fieldsJSONArray.length());

		JSONObject fieldJSONObject = fieldsJSONArray.getJSONObject(0);

		Assertions.assertEquals("name", fieldJSONObject.getString("name"));
		Assertions.assertEquals("Guest", fieldJSONObject.getString("value"));
	}

	@Autowired
	private BQCSVUserRepository _bqCSVUserRepository;

	@Autowired
	private CSVUserMigrationUpgradeStep _csvUserMigrationUpgradeStep;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}