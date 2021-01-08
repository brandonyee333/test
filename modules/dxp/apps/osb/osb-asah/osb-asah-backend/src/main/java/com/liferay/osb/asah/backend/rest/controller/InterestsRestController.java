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

package com.liferay.osb.asah.backend.rest.controller;

import com.liferay.osb.asah.backend.rest.response.InterestsHistogramTransformationJSONArrayFunction;
import com.liferay.osb.asah.backend.rest.response.embedded.InterestsEmbeddedJSONObjectCreator;
import com.liferay.osb.asah.backend.rest.response.embedded.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.findbugs.SuppressFBWarnings;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;

import org.json.JSONObject;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 * @author David Bhasme
 */
@RequestMapping("/interests")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.InterestsRestController"
)
@SuppressFBWarnings("NM_SAME_SIMPLE_NAME_AS_SUPERCLASS")
public class InterestsRestController
	extends com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.
				InterestsRestController {

	@GetMapping("/{id}")
	public String getInterest(
			@PathVariable String id,
			@RequestParam(required = false) String expand)
		throws Exception {

		if (StringUtils.isEmpty(expand)) {
			return toItemGetResponse("interests", id);
		}

		return toItemGetResponse(
			"interests",
			new InterestsEmbeddedJSONObjectCreator(
				faroInfoElasticsearchInvoker, expand),
			id);
	}

	@GetMapping("/keywords")
	public String getInterestKeywords(
			@RequestParam(required = false) String name,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			"assets", page, null, size, null, new String[] {"_key", "asc"},
			new TermsAggregationTransformationJSONArrayFunction(
				name, "keywords.keyword",
				MultiBucketsAggregation.Bucket::getKeyAsString),
			"interest-keywords");
	}

	@Cacheable("getInterests")
	@GetMapping(params = "!apply")
	public String getInterests(
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(required = false) String expand,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		return toCollectionGetResponse(
			"interests",
			new InterestsEmbeddedJSONObjectCreator(
				faroInfoElasticsearchInvoker, expand),
			page,
			_getInterestThresholdQueryBuilder(
				FilterStringToQueryBuilderConverter.convert(filterString)),
			size, sorts);
	}

	@Cacheable("getInterestTransformations")
	@GetMapping(params = "apply")
	public String getInterestTransformations(
			@RequestParam String apply,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString);

		return toTransformationGetResponse(
			apply, "interests", page, queryBuilder, size, "dateRecorded",
			new InterestsHistogramTransformationJSONArrayFunction(true),
			"interest-transformations");
	}

	private QueryBuilder _getInterestThresholdQueryBuilder(
		QueryBuilder queryBuilder) {

		JSONObject osbAsahMarkerJSONObject = faroInfoElasticsearchInvoker.fetch(
			"OSBAsahMarkers", "InterestThresholdScoreNanite");

		if ((osbAsahMarkerJSONObject == null) ||
			!osbAsahMarkerJSONObject.has("score")) {

			return queryBuilder;
		}

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.rangeQuery(
				"score"
			).gte(
				osbAsahMarkerJSONObject.getDouble("score")
			));

		if (queryBuilder == null) {
			return boolQueryBuilder;
		}

		return boolQueryBuilder.filter(queryBuilder);
	}

}