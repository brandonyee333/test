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

package com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.converter.helper.FilterStringConverterHelper;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import org.apache.commons.lang3.StringUtils;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.json.JSONArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author Vishal Reddy
 */
public abstract class BaseFaroInfoFilterStringConverterHelperTestCase
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	public abstract FilterStringConverterHelper
		getFilterStringConverterHelper();

	@BeforeEach
	public void setUp() throws Exception {
		for (String collectionName : _COLLECTION_NAMES) {
			faroInfoElasticsearchInvoker.add(
				collectionName,
				new JSONArray(
					TestExecutionListenerUtil.replaceVariables(
						ResourceUtil.readResourceToString(
							"dependencies/osbasahfaroinfo/" +
								StringUtils.replace(collectionName, "-", "_") +
									".json",
							this))));
		}
	}

	protected void testFilterString(
			String collection, String filterString,
			String... expectedIndividualIds)
		throws Exception {

		MatcherAssert.assertThat(
			expectedIndividualIds,
			Matchers.arrayContainingInAnyOrder(
				JSONUtil.toStringArray(
					faroInfoElasticsearchInvoker.get(
						collection,
						FilterStringToQueryBuilderConverter.convert(
							filterString, getFilterStringConverterHelper())),
					"id")));
	}

	protected void testFilterStringThrowsException(
		Class<? extends Throwable> expectedExceptionClass,
		String expectedMessage, String filterString) {

		try {
			FilterStringToQueryBuilderConverter.convert(
				filterString, getFilterStringConverterHelper());

			Assertions.fail();
		}
		catch (Exception exception) {
			if (expectedExceptionClass == null) {
				return;
			}

			Throwable throwable = exception;

			while (throwable.getCause() != null) {
				throwable = throwable.getCause();
			}

			Class<?> clazz = throwable.getClass();

			Assertions.assertEquals(
				clazz, expectedExceptionClass,
				"Expected innermost throwable to be of type " +
					expectedExceptionClass.getName() + ", but was " +
						clazz.getName());

			if (expectedMessage == null) {
				return;
			}

			Assertions.assertEquals(expectedMessage, throwable.getMessage());
		}
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	protected ElasticsearchInvoker faroInfoElasticsearchInvoker;

	private static final String[] _COLLECTION_NAMES = {
		"accounts", "activities", "bq-membership-changes", "bq-memberships",
		"data-sources", "field-mappings", "fields", "individual-segments",
		"individuals", "interests"
	};

}