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
		JSONArray groupJSONArray = _getJSONArray("groups");
		JSONArray organizationJSONArray = _getJSONArray("organizations");
		JSONArray roleJSONArray = _getJSONArray("roles");
		JSONArray teamJSONArray = _getJSONArray("teams");
		JSONArray userJSONArray = _getJSONArray("users");
		JSONArray userGroupJSONArray = _getJSONArray("user_groups");

		_dxpRawElasticsearchInvoker.add(
			DXPEntity.Type.GROUP.getCollectionName(), groupJSONArray);
		_dxpRawElasticsearchInvoker.add(
			DXPEntity.Type.ORGANIZATION.getCollectionName(),
			organizationJSONArray);
		_dxpRawElasticsearchInvoker.add(
			DXPEntity.Type.ROLE.getCollectionName(), roleJSONArray);
		_dxpRawElasticsearchInvoker.add(
			DXPEntity.Type.TEAM.getCollectionName(), teamJSONArray);
		_dxpRawElasticsearchInvoker.add(
			DXPEntity.Type.USER.getCollectionName(), userJSONArray);
		_dxpRawElasticsearchInvoker.add(
			DXPEntity.Type.USER_GROUP.getCollectionName(), userGroupJSONArray);

		_dxpEntityMigrationUpgradeStep.upgrade("");

		_assertDXPEntities(groupJSONArray, DXPEntity.Type.GROUP);
		_assertDXPEntities(organizationJSONArray, DXPEntity.Type.ORGANIZATION);
		_assertDXPEntities(roleJSONArray, DXPEntity.Type.ROLE);
		_assertDXPEntities(teamJSONArray, DXPEntity.Type.TEAM);
		_assertDXPEntities(userJSONArray, DXPEntity.Type.USER);
		_assertDXPEntities(userGroupJSONArray, DXPEntity.Type.USER_GROUP);
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

	private JSONArray _getJSONArray(String collectionName) throws Exception {
		return new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					String.format(
						"dependencies/osbasahdxpraw/%s.json", collectionName),
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

	@Autowired
	private ObjectMapper _objectMapper;

}