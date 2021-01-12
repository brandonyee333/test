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

package com.liferay.osb.asah.backend.graphql.schema.test;

import com.liferay.osb.asah.backend.graphql.schema.PreferenceMutationDataFetcher;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.Preference;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingEnvironmentBuilder;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Matthew Kong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class PreferenceMutationDataFetcherTest {

	@Test(expected = RuntimeException.class)
	public void testAddInvalidKeyPreference() {
		_preferenceMutationDataFetcher.get(
			_getDataFetchingEnvironment(
				"dummy-key", String.valueOf(7 * DateUtil.MONTH)));
	}

	@Test(expected = RuntimeException.class)
	public void testAddInvalidValuePreference1() {
		_preferenceMutationDataFetcher.get(
			_getDataFetchingEnvironment(
				"data-retention-period", String.valueOf(14 * DateUtil.MONTH)));
	}

	@Test(expected = RuntimeException.class)
	public void testAddInvalidValuePreference2() {
		_preferenceMutationDataFetcher.get(
			_getDataFetchingEnvironment("data-retention-period", "0"));
	}

	@Test
	public void testAddPreference() {
		String value = String.valueOf(7 * DateUtil.MONTH);

		Preference preference = _preferenceMutationDataFetcher.get(
			_getDataFetchingEnvironment("data-retention-period", value));

		Assert.assertEquals(preference.getValue(), value);
	}

	private DataFetchingEnvironment _getDataFetchingEnvironment(
		String key, String value) {

		DataFetchingEnvironmentBuilder dataFetchingEnvironmentBuilder =
			DataFetchingEnvironmentBuilder.newDataFetchingEnvironment();

		Map<String, Object> arguments = new HashMap<>();

		arguments.put("key", key);
		arguments.put("value", value);

		dataFetchingEnvironmentBuilder.arguments(arguments);

		return dataFetchingEnvironmentBuilder.build();
	}

	@Autowired
	private PreferenceMutationDataFetcher _preferenceMutationDataFetcher;

}