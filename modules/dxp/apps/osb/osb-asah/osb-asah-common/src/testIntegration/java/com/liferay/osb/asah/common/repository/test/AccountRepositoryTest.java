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

import com.liferay.osb.asah.common.entity.Account;
import com.liferay.osb.asah.common.entity.DataSourceIndividual;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Rachael Koestartyo
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@Import(JDBCTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class AccountRepositoryTest extends BaseAccountRepositoryTestCase {

	@Override
	@Test
	public void testGetAccountDistributions() throws Exception {
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
						"/accounts_2.json",
					AccountRepositoryTest.class)));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			Account account = _objectMapper.convertValue(
				jsonObject, Account.class);

			account.setIsNew(true);

			accountRepository.save(account);

			JSONObject organizationJSONObject = jsonObject.getJSONObject(
				"organization");

			Set<String> keys = organizationJSONObject.keySet();

			Iterator<String> iterator = keys.iterator();

			while (iterator.hasNext()) {
				String key = iterator.next();

				JSONArray fieldJSONArray = organizationJSONObject.getJSONArray(
					key);

				fieldRepository.save(
					_objectMapper.convertValue(
						fieldJSONArray.getJSONObject(0), Field.class));
			}
		}

		jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/" + WeDeployDataService.OSB_ASAH_FARO_INFO +
						"/individual_segments.json",
					AccountRepositoryTest.class)));

		for (int i = 0; i < jsonArray.length(); i++) {
			Segment segment = _objectMapper.convertValue(
				jsonArray.getJSONObject(i), Segment.class);

			segment.setIsNew(true);

			segmentRepository.save(segment);
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

			Long individualId = individual.getId();

			individual.setIsNew(true);

			Set<DataSourceIndividual> dataSourceIndividuals = new HashSet<>();

			JSONArray dataSourceAccountPKsJSONArray = jsonObject.optJSONArray(
				"dataSourceAccountPKs");

			for (int j = 0; j < dataSourceAccountPKsJSONArray.length(); j++) {
				JSONObject dataSourceAccountPKJSONObject =
					dataSourceAccountPKsJSONArray.optJSONObject(j);

				if (dataSourceAccountPKJSONObject == null) {
					continue;
				}

				DataSourceIndividual dataSourceIndividual =
					new DataSourceIndividual();

				dataSourceIndividual.setAccountPKs(
					JSONUtil.toStringSet(
						dataSourceAccountPKJSONObject.getJSONArray(
							"accountPKs")));
				dataSourceIndividual.setDataSourceId(
					Long.valueOf(
						dataSourceAccountPKJSONObject.getString(
							"dataSourceId")));
				dataSourceIndividual.setIndividualId(individualId);

				dataSourceIndividuals.add(dataSourceIndividual);
			}

			individual.setDataSourceIndividuals(dataSourceIndividuals);

			individualRepository.save(individual);
		}

		super.testGetAccountDistributions();
	}

	@Autowired
	private ObjectMapper _objectMapper;

}