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

import com.liferay.osb.asah.backend.rest.response.VisitedPagesTransformationJSONArrayFunction;
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

import org.junit.Assert;
import org.junit.Before;
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
public class VisitedPagesTransformationJSONArrayFunctionTest {

	@Before
	public void setUp() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "visited-pages", resourcePath = "visited-pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testApply() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(null, null, true);

		JSONAssert.assertEquals(
			new JSONArray(
				ResourceUtil.readResourceToString(
					"dependencies/expected_visited_pages.json", this)),
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null, null),
			false);
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "visited-pages", resourcePath = "visited-pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testApplyNoAssets() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(null, null, false);

		JSONArray transformationJSONArray =
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null, null);

		Assert.assertEquals(0, transformationJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "visited-pages", resourcePath = "visited-pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testApplyNoOSBAsahMarker() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(null, null, true);

		JSONArray transformationJSONArray =
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null, null);

		Assert.assertEquals(0, transformationJSONArray.length());

		transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(null, null, false);

		JSONAssert.assertEquals(
			new JSONArray(
				ResourceUtil.readResourceToString(
					"dependencies/expected_visited_pages_no_osbasahmarker.json",
					this)),
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null, null),
			true);
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testApplyNoVisitedPages() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(null, null, true);

		JSONArray transformationJSONArray =
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null, null);

		Assert.assertEquals(0, transformationJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual-segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "memberships", resourcePath = "memberships.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "visited-pages", resourcePath = "visited-pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testApplyWithOwner() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(
				"386700291682772906", "account", true);

		JSONAssert.assertEquals(
			new JSONArray(
				ResourceUtil.readResourceToString(
					"dependencies/expected_visited_pages_account.json", this)),
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 0, 20,
				Collections.singletonList(
					Pair.of("uniqueVisitsCount", SortOrder.DESC)),
				"url", null),
			true);

		transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(
				"386700289195644932", "individual-segment", true);

		JSONAssert.assertEquals(
			new JSONArray(
				ResourceUtil.readResourceToString(
					"dependencies/expected_visited_pages_segment.json", this)),
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 0, 20,
				Collections.singletonList(
					Pair.of("uniqueVisitsCount", SortOrder.DESC)),
				"url", null),
			true);
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "visited-pages", resourcePath = "visited-pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testApplyWithPagination() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(null, null, true);

		JSONAssert.assertEquals(
			new JSONArray(
				ResourceUtil.readResourceToString(
					"dependencies/expected_visited_pages_paginated.json",
					this)),
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 1, 2,
				Collections.singletonList(
					Pair.of("uniqueVisitsCount", SortOrder.DESC)),
				null, null),
			true);

		JSONArray transformationJSONArray =
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 3, 2,
				Collections.emptyList(), null, null);

		Assert.assertEquals(0, transformationJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "OSBAsahMarkers", resourcePath = "osbasahmarkers.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "assets", resourcePath = "assets.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "visited-pages", resourcePath = "visited-pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testApplyWithSort() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(null, null, true);

		JSONAssert.assertEquals(
			new JSONArray(
				ResourceUtil.readResourceToString(
					"dependencies/expected_visited_pages_sorted.json", this)),
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 0, 20,
				Collections.singletonList(
					Pair.of("uniqueVisitsCount", SortOrder.DESC)),
				"url", null),
			true);
	}

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}