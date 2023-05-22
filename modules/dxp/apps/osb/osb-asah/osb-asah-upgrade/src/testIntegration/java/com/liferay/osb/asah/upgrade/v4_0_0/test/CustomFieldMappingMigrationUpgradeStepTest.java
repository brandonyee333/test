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

import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.v4_0_0.CustomFieldMappingMigrationUpgradeStep;

import java.util.Collections;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 */
public class CustomFieldMappingMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		DataSource dataSource = new DataSource("Liferay Brazil");

		dataSource.setCredentialType("Token Authentication");

		dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		dataSource.setId(445755420866214963L);
		dataSource.setIsNew(true);
		dataSource.setProviderType("LIFERAY");

		_dataSourceRepository.save(dataSource);

		_elasticsearchIndexManager.delete(
			"test_osbasahfaroinfo_osbasahmarkers");

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies/osbasahfaroinfo" +
					"/osbasahmarkers_index_configuration.json",
				this),
			"test_osbasahfaroinfo_osbasahmarkers");

		_elasticsearchIndexManager.addAlias(
			"test_osbasahfaroinfo_osbasahmarkers_alias",
			"test_osbasahfaroinfo_osbasahmarkers");

		_elasticsearchIndexManager.delete(
			"test_osbasahfaroinfo_field-mappings");

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies/field_mappings_index_configuration.json", this),
			"test_osbasahfaroinfo_field-mappings");

		_elasticsearchIndexManager.addAlias(
			"test_osbasahfaroinfo_field-mappings_alias",
			"test_osbasahfaroinfo_field-mappings");
	}

	@AfterEach
	public void tearDown() throws Exception {
		_faroInfoElasticsearchInvoker.delete(
			"field-mappings", QueryBuilders.matchAllQuery());
		_faroInfoElasticsearchInvoker.delete(
			"OSBAsahMarkers", QueryBuilders.matchAllQuery());

		_dxpEntityRepository.deleteByType(DXPEntity.Type.EXPANDO_COLUMN);

		_dataSourceRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray customFieldMappingsJSONArray = new JSONArray(
			ResourceUtil.readResourceToString(
				"dependencies/osbasahfaroinfo/custom_field_mappings.json",
				this));

		_faroInfoElasticsearchInvoker.add(
			"field-mappings", customFieldMappingsJSONArray);

		_customFieldMappingMigrationUpgradeStep.upgrade("");

		List<DXPEntity> dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			Collections.emptyMap(), DXPEntity.Type.EXPANDO_COLUMN);

		Assertions.assertEquals(17L, dxpEntities.size());

		dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			Collections.singletonMap(
				"fields.name", "cedula_ciudadania-Integer"),
			DXPEntity.Type.EXPANDO_COLUMN);

		Assertions.assertEquals(1L, dxpEntities.size());

		DXPEntity dxpEntity = dxpEntities.get(0);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"className", "com.liferay.portal.kernel.model.User"
			).put(
				"columnId", "cedula_ciudadania-Integer"
			).put(
				"dataType", "Integer"
			).put(
				"displayType", ""
			).put(
				"name", "cedula_ciudadania-Integer"
			),
			dxpEntity.getFieldsJSONObject(), false);

		dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			Collections.singletonMap(
				"fields.name", "tipo_notificacion-Decimal"),
			DXPEntity.Type.EXPANDO_COLUMN);

		Assertions.assertEquals(1L, dxpEntities.size());

		dxpEntity = dxpEntities.get(0);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"className", "com.liferay.portal.kernel.model.Organization"
			).put(
				"columnId", "tipo_notificacion-Decimal"
			).put(
				"dataType", "Decimal"
			).put(
				"displayType", "input-field"
			).put(
				"name", "tipo_notificacion-Decimal"
			),
			dxpEntity.getFieldsJSONObject(), false);

		dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			Collections.singletonMap("fields.name", "estado_colaborador-Text"),
			DXPEntity.Type.EXPANDO_COLUMN);

		Assertions.assertEquals(1L, dxpEntities.size());

		dxpEntity = dxpEntities.get(0);

		JSONAssert.assertEquals(
			JSONUtil.put(
				"className", "com.liferay.portal.kernel.model.User"
			).put(
				"columnId", "estado_colaborador-Text"
			).put(
				"dataType", "Text"
			).put(
				"displayType", "boolean"
			).put(
				"name", "estado_colaborador-Text"
			),
			dxpEntity.getFieldsJSONObject(), false);
	}

	@Autowired
	private CustomFieldMappingMigrationUpgradeStep
		_customFieldMappingMigrationUpgradeStep;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

	@Autowired
	private DXPEntityRepository _dxpEntityRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}