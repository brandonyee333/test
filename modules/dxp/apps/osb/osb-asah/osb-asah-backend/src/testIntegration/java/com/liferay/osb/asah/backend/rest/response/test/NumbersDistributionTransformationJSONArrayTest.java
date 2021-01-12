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

package com.liferay.osb.asah.backend.rest.response.test;

import com.liferay.osb.asah.backend.rest.response.NumbersDistributionTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import java.util.Collections;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class NumbersDistributionTransformationJSONArrayTest {

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testApply() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new NumbersDistributionTransformationJSONArrayFunction();

		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/expected_numbers_distribution.json",
						this))),
			transformationJSONArrayFunction.apply(
				"individuals", null, _elasticsearchInvoker, 0, 8,
				Collections.emptyList(), "demographics.age.value", null),
			true);
		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies" +
							"/expected_numbers_distribution_same_max_min.json",
						this))),
			transformationJSONArrayFunction.apply(
				"individuals", null, _elasticsearchInvoker, 0, 8,
				Collections.emptyList(), "demographics.age.value",
				QueryBuilders.rangeQuery(
					"demographics.age.value"
				).gt(
					50
				)),
			true);
		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/expected_numbers_distribution_1_bin.json",
						this))),
			transformationJSONArrayFunction.apply(
				"individuals", null, _elasticsearchInvoker, 0, 1,
				Collections.emptyList(), "demographics.age.value", null),
			true);
		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies" +
							"/expected_numbers_distribution_decimal.json",
						this))),
			transformationJSONArrayFunction.apply(
				"individuals", null, _elasticsearchInvoker, 0, 5,
				Collections.emptyList(), "demographics.score.value", null),
			true);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}