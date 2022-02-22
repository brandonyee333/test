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
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.repository.AsahMarkerRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_2_0.AsahMarkerMigrationUpgradeStep;

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
public class AsahMarkerMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_asahMarkerRepository.deleteAll();

		_setupIndex("osbasahcerebroinfo");
		_setupIndex("osbasahdxpraw");
		_setupIndex("osbasahfaroinfo");
		_setupIndex("osbasahsalesforceraw");
	}

	@AfterEach
	public void tearDown() throws Exception {
		_elasticsearchIndexManager.delete(
			"test_osbasahcerebroinfo_osbasahmarkers");
		_elasticsearchIndexManager.delete("test_osbasahdxpraw_osbasahmarkers");
		_elasticsearchIndexManager.delete(
			"test_osbasahfaroinfo_osbasahmarkers");
		_elasticsearchIndexManager.delete(
			"test_osbasahsalesforceraw_osbasahmarkers");

		_asahMarkerRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray cerebroInfoJSONArray = _getJSONArray("osbasahcerebroinfo");
		JSONArray dxpRawJSONArray = _getJSONArray("osbasahdxpraw");

		JSONArray faroInfoJSONArray = _getJSONArray("osbasahfaroinfo");
		JSONArray salesForceRawJSONArray = _getJSONArray(
			"osbasahsalesforceraw");

		_cerebroInfoElasticsearchInvoker.add(
			"OSBAsahMarkers", cerebroInfoJSONArray);
		_dxpRawElasticsearchInvoker.add("OSBAsahMarkers", dxpRawJSONArray);
		_faroInfoElasticsearchInvoker.add("OSBAsahMarkers", faroInfoJSONArray);
		_salesforceRawElasticsearchInvoker.add(
			"OSBAsahMarkers", salesForceRawJSONArray);

		JSONArray jsonArray = new JSONArray();

		for (Object cerebroInfoObject : cerebroInfoJSONArray) {
			jsonArray.put(cerebroInfoObject);
		}

		for (Object dxpRawObject : dxpRawJSONArray) {
			jsonArray.put(dxpRawObject);
		}

		for (Object faroInfoObject : faroInfoJSONArray) {
			jsonArray.put(faroInfoObject);
		}

		for (Object salesForceRawObject : salesForceRawJSONArray) {
			jsonArray.put(salesForceRawObject);
		}

		_asahMarkerMigrationUpgradeStep.upgrade("");

		List<Object> jsonObjectList = jsonArray.toList();

		Stream<Object> stream = jsonObjectList.stream();

		Assertions.assertEquals(
			stream.map(
				object -> _objectMapper.convertValue(object, AsahMarker.class)
			).sorted(
				Comparator.comparing(AsahMarker::getId)
			).collect(
				Collectors.toList()
			),
			_asahMarkerRepository.findAll());
	}

	private JSONArray _getJSONArray(String sourceName) throws Exception {
		return new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					String.format(
						"dependencies/%s/osbasahmarkers.json", sourceName),
					this)));
	}

	private void _setupIndex(String indexSource) throws Exception {
		String indexName = String.format("test_%s_osbasahmarkers", indexSource);

		_elasticsearchIndexManager.delete(indexName);

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				String.format(
					"dependencies/%s/osbasahmarkers_index_configuration.json",
					indexSource),
				this),
			indexName);

		_elasticsearchIndexManager.addAlias(
			String.format("%s_alias", indexName), indexName);
	}

	@Autowired
	private AsahMarkerMigrationUpgradeStep _asahMarkerMigrationUpgradeStep;

	@Autowired
	private AsahMarkerRepository _asahMarkerRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}