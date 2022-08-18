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

package com.liferay.osb.asah.common.rest.response.function.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author Leslie Wong
 */
public class TermsAggregationTransformationJSONArrayFunctionTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@RepositoryResource(
		repositoryClass = BQIndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals.json"
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

		Assertions.assertEquals(0, jsonArray.length());
	}

	@RepositoryResource(
		repositoryClass = BQIndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals.json"
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

	@RepositoryResource(
		repositoryClass = BQIndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals.json"
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

	@RepositoryResource(
		repositoryClass = BQIndividualRepository.class,
		resourcePath = "osbasahfaroinfo/individuals.json"
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