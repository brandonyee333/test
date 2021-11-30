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
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.ActivitiesAssetTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Collections;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Matthew Kong
 */
@ElasticsearchIndex(
	name = "activities", resourcePath = "activities.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@RepositoryResource(
	repositoryClass = AssetRepository.class,
	resourcePath = "osbasahfaroinfo/assets.json"
)
public class ActivitiesAssetTransformationJSONArrayFunctionTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testApplyAscSort() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new ActivitiesAssetTransformationJSONArrayFunction(
				_assetDog, _dataSourceDog);

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
			new ActivitiesAssetTransformationJSONArrayFunction(
				_assetDog, _dataSourceDog);

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

	@Autowired
	private AssetDog _assetDog;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}