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

import com.liferay.osb.asah.backend.rest.response.ActivitiesAssetTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.Collections;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Matthew Kong
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@ElasticsearchIndex(
	name = "activities", resourcePath = "activities.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@ElasticsearchIndex(
	name = "assets", resourcePath = "assets.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class ActivitiesAssetTransformationJSONArrayFunctionTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@Test
	public void testApplyAscSort() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new ActivitiesAssetTransformationJSONArrayFunction();

		JSONAssert.assertEquals(
			new JSONArray(
				ResourceUtil.readResourceToString(
					"dependencies/expected_asset_activities_asc_sort.json",
					this)),
			transformationJSONArrayFunction.apply(
				"activities", null, _elasticsearchInvoker, 0, 20,
				Collections.singletonList(Pair.of("count", SortOrder.ASC)),
				null, null),
			true);
	}

	@Test
	public void testApplyDescSort() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new ActivitiesAssetTransformationJSONArrayFunction();

		JSONAssert.assertEquals(
			new JSONArray(
				ResourceUtil.readResourceToString(
					"dependencies/expected_asset_activities_desc_sort.json",
					this)),
			transformationJSONArrayFunction.apply(
				"activities", null, _elasticsearchInvoker, 0, 20,
				Collections.singletonList(Pair.of("count", SortOrder.DESC)),
				null, null),
			true);
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}