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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.FilterStringConverterHelper;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import org.apache.commons.lang3.StringUtils;

import org.hamcrest.Matchers;

import org.json.JSONArray;

import org.junit.Assert;
import org.junit.Before;

/**
 * @author Vishal Reddy
 */
public abstract class BaseFaroInfoFilterStringConverterHelperTestCase {

	public abstract FilterStringConverterHelper
		getFilterStringConverterHelper();

	@Before
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

		Assert.assertThat(
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

			Assert.fail();
		}
		catch (Exception e) {
			if (expectedExceptionClass == null) {
				return;
			}

			Throwable throwable = e;

			while (throwable.getCause() != null) {
				throwable = throwable.getCause();
			}

			Class<?> clazz = throwable.getClass();

			Assert.assertEquals(
				"Expected innermost throwable to be of type " +
					expectedExceptionClass.getName() + ", but was " +
						clazz.getName(),
				clazz, expectedExceptionClass);

			if (expectedMessage == null) {
				return;
			}

			Assert.assertEquals(expectedMessage, throwable.getMessage());
		}
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	protected ElasticsearchInvoker faroInfoElasticsearchInvoker;

	private static final String[] _COLLECTION_NAMES = {
		"accounts", "activities", "data-sources", "field-mappings", "fields",
		"individual-segments", "individuals", "interests", "membership-changes",
		"memberships"
	};

}