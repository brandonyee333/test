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
import com.liferay.osb.asah.common.entity.Preference;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.PreferenceRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_2_0.PreferenceMigrationUpgradeStep;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leilany Ulisses
 */
public class PreferenceMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_preferenceRepository.deleteAll();

		_elasticsearchIndexManager.delete("test_osbasahfaroinfo_preferences");

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				String.format(
					"dependencies/%s/preferences_index_configuration.json",
					"osbasahfaroinfo"),
				this),
			"test_osbasahfaroinfo_preferences");

		_elasticsearchIndexManager.addAlias(
			"test_osbasahfaroinfo_preferences_alias",
			"test_osbasahfaroinfo_preferences");
	}

	@AfterEach
	public void tearDown() throws Exception {
		_elasticsearchIndexManager.delete("test_osbasahfaroinfo_preferences");

		_preferenceRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray jsonArray = new JSONArray(
			ResourceUtil.readResourceToString(
				"dependencies/osbasahfaroinfo/preferences.json", this));

		_faroInfoElasticsearchInvoker.add("preferences", jsonArray);

		_preferenceMigrationUpgradeStep.upgrade("");

		List<Preference> actualPreferences = JSONUtil.toList(
			jsonArray,
			jsonObject -> _objectMapper.convertValue(
				jsonObject, Preference.class));

		Collections.sort(
			actualPreferences, Comparator.comparing(Preference::getId));

		Assertions.assertEquals(
			actualPreferences, _preferenceRepository.findAll());
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private PreferenceMigrationUpgradeStep _preferenceMigrationUpgradeStep;

	@Autowired
	private PreferenceRepository _preferenceRepository;

}