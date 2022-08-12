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

import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import org.json.JSONArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Rachael Koestartyo
 */
@Disabled
@Import(JDBCTestConfiguration.class)
public class IndividualRepositoryTest extends BaseIndividualRepositoryTestCase {

	@Override
	@Test
	public void testGetIndividualDistributions() throws Exception {
		setUpDataSources();

		// TODO Add BQFieldMapping from field_mappings.json

		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/" + WeDeployDataService.OSB_ASAH_FARO_INFO +
						"/individuals.json",
					IndividualRepositoryTest.class)));

		for (int i = 0; i < jsonArray.length(); i++) {
			Individual individual = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), Individual.class);

			individual.setIsNew(true);

			individualRepository.save(individual);
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