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

package com.liferay.osb.asah.common.repository.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Rachael Koestartyo
 */
@Import(JDBCTestConfiguration.class)
public class IndividualRepositoryTest extends BaseIndividualRepositoryTestCase {

	@Override
	@Test
	public void testGetIndividualDistributions() throws Exception {
		setUpDataSources();

		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/" + WeDeployDataService.OSB_ASAH_FARO_INFO +
						"/field_mappings.json",
					AccountRepositoryTest.class)));

		for (int i = 0; i < jsonArray.length(); i++) {
			FieldMapping fieldMapping = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), FieldMapping.class);

			fieldMapping.setIsNew(true);

			fieldMappingRepository.save(fieldMapping);
		}

		jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/" + WeDeployDataService.OSB_ASAH_FARO_INFO +
						"/individuals.json",
					AccountRepositoryTest.class)));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Individual individual = _objectMapper.convertValue(
				jsonObject, Individual.class);

			individual.setIsNew(true);

			individualRepository.save(individual);

			JSONObject demographicsJSONObject = jsonObject.optJSONObject(
				"demographics");

			if (demographicsJSONObject == null) {
				continue;
			}

			Set<String> keys = demographicsJSONObject.keySet();

			Iterator<String> iterator = keys.iterator();

			while (iterator.hasNext()) {
				String key = iterator.next();

				JSONArray fieldJSONArray = demographicsJSONObject.getJSONArray(
					key);

				fieldRepository.save(
					_objectMapper.convertValue(
						fieldJSONArray.getJSONObject(0), Field.class));
			}
		}

		super.testGetIndividualDistributions();
	}

	@Override
	@Test
	public void testUpdateDataSourceNameByDataSourceId() {
		Assertions.assertThrows(
			UnsupportedOperationException.class,
			super::testUpdateDataSourceNameByDataSourceId);
	}

	@Autowired
	private ObjectMapper _objectMapper;

}