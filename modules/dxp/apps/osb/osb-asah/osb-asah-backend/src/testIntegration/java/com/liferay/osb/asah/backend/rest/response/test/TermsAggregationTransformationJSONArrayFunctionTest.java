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

import com.liferay.osb.asah.backend.rest.response.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

import org.junit.Assert;
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
public class TermsAggregationTransformationJSONArrayFunctionTest {

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testApply() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new TermsAggregationTransformationJSONArrayFunction(
				null, "demographics.worksFor.value", null);

		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/expected_terms.json", this))),
			transformationJSONArrayFunction.apply(
				"individuals", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null, null),
			true);
		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/expected_terms_sorted.json", this))),
			transformationJSONArrayFunction.apply(
				"individuals", null, _elasticsearchInvoker, 0, 20,
				new ArrayList<Pair<String, SortOrder>>() {
					{
						add(Pair.of("_count", SortOrder.ASC));
						add(Pair.of("_key", SortOrder.DESC));
					}
				},
				null, null),
			true);
	}

	@Test
	public void testApplyNoCollection() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new TermsAggregationTransformationJSONArrayFunction(
				null, "demographics.worksFor.value", null);

		JSONArray jsonArray = transformationJSONArrayFunction.apply(
			"individuals", null, _elasticsearchInvoker, 0, 20,
			Collections.emptyList(), null, null);

		Assert.assertEquals(0, jsonArray.length());
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testApplyWithFilter() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new TermsAggregationTransformationJSONArrayFunction(
				"-", "demographics.worksFor.value", null);

		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/expected_terms_filtered_0.json", this))),
			transformationJSONArrayFunction.apply(
				"individuals", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null, null),
			true);
		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/expected_terms_filtered_1.json", this))),
			transformationJSONArrayFunction.apply(
				"individuals", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null,
				QueryBuilders.rangeQuery(
					"demographics.birthDate.value"
				).gt(
					"1970-01-01T00:00:00.000Z"
				)),
			true);
		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/expected_terms_filtered_sorted.json",
						this))),
			transformationJSONArrayFunction.apply(
				"individuals", null, _elasticsearchInvoker, 0, 20,
				new ArrayList<Pair<String, SortOrder>>() {
					{
						add(Pair.of("_key", SortOrder.DESC));
					}
				},
				null, null),
			true);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testApplyWithGroupByString() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new TermsAggregationTransformationJSONArrayFunction(
				"groupby((demographics/worksFor/value))/contains((-))", null);

		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/expected_terms_filtered_0.json", this))),
			transformationJSONArrayFunction.apply(
				"individuals", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null, null),
			true);
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testApplyWithResponseFormatter() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new TermsAggregationTransformationJSONArrayFunction(
				null, "demographics.worksFor.value",
				bucket -> JSONUtil.put(
					"count", bucket.getDocCount()
				).put(
					"name", bucket.getKeyAsString()
				));

		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/expected_terms_formatted.json", this))),
			transformationJSONArrayFunction.apply(
				"individuals", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null, null),
			true);
		JSONAssert.assertEquals(
			new JSONArray(
				TestExecutionListenerUtil.replaceVariables(
					ResourceUtil.readResourceToString(
						"dependencies/expected_terms_formatted_sorted.json",
						this))),
			transformationJSONArrayFunction.apply(
				"individuals", null, _elasticsearchInvoker, 0, 20,
				new ArrayList<Pair<String, SortOrder>>() {
					{
						add(Pair.of("_count", SortOrder.ASC));
						add(Pair.of("_key", SortOrder.DESC));
					}
				},
				null, null),
			true);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}