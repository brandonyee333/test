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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_2_0.DXPEntityMigrationUpgradeStep;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcos Martins
 */
public class DXPEntityMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		DataSource dataSource1 = new DataSource("Liferay Brazil");

		dataSource1.setCredentialType("Token Authentication");

		dataSource1.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource1.setId(405057430327289648L);
		dataSource1.setIsNew(true);
		dataSource1.setProviderType("LIFERAY");

		DataSource dataSource2 = new DataSource("Liferay Diamond Bar");

		dataSource2.setCredentialType("Token Authentication");

		dataSource2.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource2.setId(405057430327289649L);
		dataSource2.setIsNew(true);
		dataSource2.setProviderType("LIFERAY");

		_dataSourceRepository.saveAll(Arrays.asList(dataSource1, dataSource2));
	}

	@AfterEach
	public void tearDown() throws Exception {
		_dxpRawElasticsearchInvoker.delete(
			DXPEntity.Type.GROUP.getCollectionName(),
			QueryBuilders.matchAllQuery());
		_dxpRawElasticsearchInvoker.delete(
			DXPEntity.Type.ORGANIZATION.getCollectionName(),
			QueryBuilders.matchAllQuery());
		_dxpRawElasticsearchInvoker.delete(
			DXPEntity.Type.ROLE.getCollectionName(),
			QueryBuilders.matchAllQuery());
		_dxpRawElasticsearchInvoker.delete(
			DXPEntity.Type.TEAM.getCollectionName(),
			QueryBuilders.matchAllQuery());
		_dxpRawElasticsearchInvoker.delete(
			DXPEntity.Type.USER.getCollectionName(),
			QueryBuilders.matchAllQuery());
		_dxpRawElasticsearchInvoker.delete(
			DXPEntity.Type.USER_GROUP.getCollectionName(),
			QueryBuilders.matchAllQuery());
		_faroInfoElasticsearchInvoker.delete(
			"field-mappings", QueryBuilders.matchAllQuery());

		_dxpEntityRepository.deleteByType(DXPEntity.Type.EXPANDO_COLUMN);
		_dxpEntityRepository.deleteByType(DXPEntity.Type.GROUP);
		_dxpEntityRepository.deleteByType(DXPEntity.Type.ORGANIZATION);
		_dxpEntityRepository.deleteByType(DXPEntity.Type.ROLE);
		_dxpEntityRepository.deleteByType(DXPEntity.Type.TEAM);
		_dxpEntityRepository.deleteByType(DXPEntity.Type.USER);
		_dxpEntityRepository.deleteByType(DXPEntity.Type.USER_GROUP);

		_dataSourceRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray fieldMappingsJSONArray = _getJSONArray(
			"field_mappings",
			WeDeployDataService.OSB_ASAH_FARO_INFO.toString());
		JSONArray groupsJSONArray = _getJSONArray(
			"groups", WeDeployDataService.OSB_ASAH_DXP_RAW.toString());
		JSONArray organizationsJSONArray = _getJSONArray(
			"organizations", WeDeployDataService.OSB_ASAH_DXP_RAW.toString());
		JSONArray rolesJSONArray = _getJSONArray(
			"roles", WeDeployDataService.OSB_ASAH_DXP_RAW.toString());
		JSONArray teamsJSONArray = _getJSONArray(
			"teams", WeDeployDataService.OSB_ASAH_DXP_RAW.toString());
		JSONArray usersJSONArray = _getJSONArray(
			"users", WeDeployDataService.OSB_ASAH_DXP_RAW.toString());
		JSONArray userGroupsJSONArray = _getJSONArray(
			"user_groups", WeDeployDataService.OSB_ASAH_DXP_RAW.toString());

		_faroInfoElasticsearchInvoker.add(
			"field-mappings", fieldMappingsJSONArray);
		_dxpRawElasticsearchInvoker.add(
			DXPEntity.Type.GROUP.getCollectionName(), groupsJSONArray);
		_dxpRawElasticsearchInvoker.add(
			DXPEntity.Type.ORGANIZATION.getCollectionName(),
			organizationsJSONArray);
		_dxpRawElasticsearchInvoker.add(
			DXPEntity.Type.ROLE.getCollectionName(), rolesJSONArray);
		_dxpRawElasticsearchInvoker.add(
			DXPEntity.Type.TEAM.getCollectionName(), teamsJSONArray);
		_dxpRawElasticsearchInvoker.add(
			DXPEntity.Type.USER.getCollectionName(), usersJSONArray);
		_dxpRawElasticsearchInvoker.add(
			DXPEntity.Type.USER_GROUP.getCollectionName(), userGroupsJSONArray);

		_dxpEntityMigrationUpgradeStep.upgrade("");

		_assertDXPEntities(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/expected_expando_columns.json", this))),
			DXPEntity.Type.EXPANDO_COLUMN);
		_assertDXPEntities(groupsJSONArray, DXPEntity.Type.GROUP);
		_assertDXPEntities(organizationsJSONArray, DXPEntity.Type.ORGANIZATION);
		_assertDXPEntities(rolesJSONArray, DXPEntity.Type.ROLE);
		_assertDXPEntities(teamsJSONArray, DXPEntity.Type.TEAM);
		_assertDXPEntities(usersJSONArray, DXPEntity.Type.USER);
		_assertDXPEntities(userGroupsJSONArray, DXPEntity.Type.USER_GROUP);
	}

	private void _assertDXPEntities(JSONArray jsonArray, DXPEntity.Type type) {
		List<Object> jsonObjectList = jsonArray.toList();

		Stream<Object> stream = jsonObjectList.stream();

		Assertions.assertEquals(
			stream.map(
				object -> _objectMapper.convertValue(object, DXPEntity.class)
			).sorted(
				Comparator.comparing(DXPEntity::getId)
			).collect(
				Collectors.toList()
			),
			_dxpEntityRepository.findByFieldsAndType(
				Collections.emptyMap(), type));
	}

	private JSONArray _getJSONArray(
			String collectionName, String weDeployDataServiceName)
		throws Exception {

		return new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					String.format(
						"dependencies/%s/%s.json", weDeployDataServiceName,
						collectionName),
					this)));
	}

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private DXPEntityMigrationUpgradeStep _dxpEntityMigrationUpgradeStep;

	@Autowired
	private DXPEntityRepository _dxpEntityRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

}