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

package com.liferay.osb.asah.upgrade.v3_1_4.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.faro.FaroInfoTestUtil;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_1_4.FieldMappingUpgradeStep;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 */
public class FieldMappingUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@AfterEach
	public void tearDown() throws Exception {
		_dataSourceRepository.deleteAll();

		_elasticsearchInvoker.delete(
			"field-mappings", QueryBuilders.matchAllQuery());
	}

	@Test
	public void testUpgrade() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		DataSource dataSource = FaroInfoTestUtil.buildLiferayDataSource();

		dataSource.setId(351238757269547426L);

		_dataSourceRepository.save(dataSource);

		_elasticsearchInvoker.add(
			"field-mappings",
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/field_mappings.json", this))));

		_fieldMappingUpgradeStep.upgrade("");

		JSONArray jsonArray = _elasticsearchInvoker.get("field-mappings");

		Assertions.assertEquals(2, jsonArray.length());

		JSONObject jsonObject = _elasticsearchInvoker.fetch(
			"field-mappings", "366588394714972833");

		JSONAssert.assertEquals(
			jsonObject.getJSONObject("dataSourceFieldNames"),
			JSONUtil.put("351238757269547426", "age"), true);

		jsonObject = _elasticsearchInvoker.fetch(
			"field-mappings", "366573390725218943");

		JSONAssert.assertEquals(
			jsonObject.getJSONObject("dataSourceFieldNames"), new JSONObject(),
			true);

		Assertions.assertFalse(
			_elasticsearchInvoker.exists(
				"field-mappings", "366573389382719637"));
	}

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private FieldMappingUpgradeStep _fieldMappingUpgradeStep;

}