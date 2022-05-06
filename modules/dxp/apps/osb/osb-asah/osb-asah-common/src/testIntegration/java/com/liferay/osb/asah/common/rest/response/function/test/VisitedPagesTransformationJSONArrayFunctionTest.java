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
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.AssetDog;
import com.liferay.osb.asah.common.dog.MembershipDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.repository.AsahMarkerRepository;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.BQMembershipRepository;
import com.liferay.osb.asah.common.rest.response.TransformationJSONArrayFunction;
import com.liferay.osb.asah.common.rest.response.function.VisitedPagesTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Collections;

import org.apache.commons.lang3.tuple.Pair;

import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Leslie Wong
 */
public class VisitedPagesTransformationJSONArrayFunctionTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@ElasticsearchIndex(
		name = "visited-pages", resourcePath = "visited_pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahfaroinfo/osbasahmarkers.json"
	)
	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
	)
	@Test
	public void testApply() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(
				_asahMarkerDog, _assetDog, _membershipDog, null, null,
				_segmentDog, true);

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
		name = "visited-pages", resourcePath = "visited_pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahfaroinfo/osbasahmarkers.json"
	)
	@Test
	public void testApplyNoAssets() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(
				_asahMarkerDog, _assetDog, _membershipDog, null, null,
				_segmentDog, false);

		JSONArray transformationJSONArray =
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null, null);

		Assertions.assertEquals(0, transformationJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "visited-pages", resourcePath = "visited_pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
	)
	@Test
	public void testApplyNoOSBAsahMarker() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(
				_asahMarkerDog, _assetDog, _membershipDog, null, null,
				_segmentDog, true);

		JSONArray transformationJSONArray =
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null, null);

		Assertions.assertEquals(0, transformationJSONArray.length());

		transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(
				_asahMarkerDog, _assetDog, _membershipDog, null, null,
				_segmentDog, false);

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

	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahfaroinfo/osbasahmarkers.json"
	)
	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
	)
	@Test
	public void testApplyNoVisitedPages() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(
				_asahMarkerDog, _assetDog, _membershipDog, null, null,
				_segmentDog, true);

		JSONArray transformationJSONArray =
			transformationJSONArrayFunction.apply(
				"visited-pages", null, _elasticsearchInvoker, 0, 20,
				Collections.emptyList(), null, null);

		Assertions.assertEquals(0, transformationJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "individual-segments", resourcePath = "individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "visited-pages", resourcePath = "visited_pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahfaroinfo/osbasahmarkers.json"
	)
	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
	)
	@RepositoryResource(
		repositoryClass = BQMembershipRepository.class,
		resourcePath = "osbasahfaroinfo/bq_memberships.json"
	)
	@Test
	public void testApplyWithOwner() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(
				_asahMarkerDog, _assetDog, _membershipDog, 386700291682772906L,
				"account", _segmentDog, true);

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
				_asahMarkerDog, _assetDog, _membershipDog, 386700289195644932L,
				"individual-segment", _segmentDog, true);

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
		name = "visited-pages", resourcePath = "visited_pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahfaroinfo/osbasahmarkers.json"
	)
	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
	)
	@Test
	public void testApplyWithPagination() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(
				_asahMarkerDog, _assetDog, _membershipDog, null, null,
				_segmentDog, true);

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

		Assertions.assertEquals(0, transformationJSONArray.length());
	}

	@ElasticsearchIndex(
		name = "visited-pages", resourcePath = "visited_pages.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = AsahMarkerRepository.class,
		resourcePath = "osbasahfaroinfo/osbasahmarkers.json"
	)
	@RepositoryResource(
		repositoryClass = AssetRepository.class,
		resourcePath = "osbasahfaroinfo/assets.json"
	)
	@Test
	public void testApplyWithSort() throws Exception {
		TransformationJSONArrayFunction transformationJSONArrayFunction =
			new VisitedPagesTransformationJSONArrayFunction(
				_asahMarkerDog, _assetDog, _membershipDog, null, null,
				_segmentDog, true);

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

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private AssetDog _assetDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private MembershipDog _membershipDog;

	@Autowired
	private SegmentDog _segmentDog;

}