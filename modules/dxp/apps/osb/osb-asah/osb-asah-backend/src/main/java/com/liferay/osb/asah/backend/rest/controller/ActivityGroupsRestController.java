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

import com.liferay.osb.asah.backend.rest.response.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoActivitiesFilterStringConverterHelper;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.TopHits;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vishal Reddy
 */
@RequestMapping("/activity-groups")
@RestController
public class ActivityGroupsRestController extends BaseRestController {

	@GetMapping("/{id}")
	public String getActivityGroup(@PathVariable String id) throws Exception {
		JSONObject activityGroupJSONObject = new JSONObject(
			toItemGetResponse("activity-groups", id));

		return activityGroupJSONObject.toString();
	}

	@GetMapping(params = "!apply")
	public String getActivityGroups(
			@RequestParam(required = false) String expand,
			@RequestParam(name = "expandFilter", required = false)
				String expandFilterString,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		JSONObject responseJSONObject = new JSONObject(
			toCollectionGetResponse(
				"activity-groups", null, page,
				FilterStringToQueryBuilderConverter.convert(
					filterString,
					_faroInfoActivitiesFilterStringConverterHelper),
				size, sorts));

		if (!StringUtils.isEmpty(expand)) {
			JSONObject embeddedJSONObject = responseJSONObject.getJSONObject(
				"_embedded");

			JSONArray activityGroupsJSONArray = embeddedJSONObject.getJSONArray(
				"activity-groups");

			if (activityGroupsJSONArray.length() == 0) {
				return responseJSONObject.toString();
			}

			_addExpansion(activityGroupsJSONArray, expand, expandFilterString);
		}

		return responseJSONObject.toString();
	}

	@GetMapping(params = "apply")
	public String getActivityGroupTransformations(
			@RequestParam String apply,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size)
		throws Exception {

		return toTransformationGetResponse(
			"activity-groups", page,
			FilterStringToQueryBuilderConverter.convert(
				filterString, _faroInfoActivitiesFilterStringConverterHelper),
			size, null, null,
			new TermsAggregationTransformationJSONArrayFunction(apply, null),
			"activity-group-transformations");
	}

	private void _addExpansion(
			JSONArray activityGroupsJSONArray, String expand,
			String filterString)
		throws Exception {

		Set<String> expandParts = new HashSet<>(
			Arrays.asList(expand.split(",")));

		boolean expandActivities = expandParts.contains("activities");
		boolean expandActivitiesCount = expandParts.contains(
			"activities-count");

		if (!expandActivities && !expandActivitiesCount) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unknown expand: " + expand);
			}

			return;
		}

		JSONObject activityGroupsJSONObject = new JSONObject();

		for (int i = 0; i < activityGroupsJSONArray.length(); i++) {
			JSONObject activityGroupJSONObject =
				activityGroupsJSONArray.getJSONObject(i);

			activityGroupsJSONObject.put(
				activityGroupJSONObject.getString("id"),
				activityGroupJSONObject);
		}

		QueryBuilder queryBuilder = _getExpandQueryBuilder(
			activityGroupsJSONObject.keySet(), filterString);

		SearchResponse searchResponse = faroInfoElasticsearchInvoker.search(
			"activities",
			searchSourceBuilder -> {
				AggregationBuilder aggregationBuilder =
					AggregationBuilders.terms(
						"groupIds"
					).field(
						"groupId"
					).order(
						BucketOrder.compound(
							BucketOrder.count(false), BucketOrder.key(true))
					).size(
						activityGroupsJSONArray.length()
					);

				if (expandActivities) {
					aggregationBuilder.subAggregation(
						AggregationBuilders.topHits(
							"activities"
						).size(
							100
						).sort(
							"id"
						));
				}

				if (expandActivitiesCount) {
					aggregationBuilder.subAggregation(
						AggregationBuilders.terms(
							"eventIds"
						).field(
							"eventId"
						).order(
							BucketOrder.compound(
								BucketOrder.count(false), BucketOrder.key(true))
						).size(
							Integer.MAX_VALUE
						));
				}

				searchSourceBuilder.aggregation(aggregationBuilder);

				searchSourceBuilder.query(queryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Terms groupIdTerms = aggregations.get("groupIds");

		for (Terms.Bucket groupIdBucket : groupIdTerms.getBuckets()) {
			JSONObject activityGroupJSONObject =
				(JSONObject)activityGroupsJSONObject.remove(
					groupIdBucket.getKeyAsString());

			JSONObject embeddedJSONObject = new JSONObject();

			Aggregations groupIdAggregations = groupIdBucket.getAggregations();

			if (expandActivities) {
				TopHits topHits = groupIdAggregations.get("activities");

				SearchHits searchHits = topHits.getHits();

				Stream<SearchHit> stream = Arrays.stream(searchHits.getHits());

				embeddedJSONObject.put(
					topHits.getName(),
					new JSONArray(
						stream.map(
							hit -> new JSONObject(hit.getSourceAsString())
						).collect(
							Collectors.toList()
						)));
			}

			if (expandActivitiesCount) {
				JSONObject eventIdCountsJSONObject = new JSONObject();

				Terms eventIdTerms = groupIdAggregations.get("eventIds");

				for (Terms.Bucket eventIdBucket : eventIdTerms.getBuckets()) {
					eventIdCountsJSONObject.put(
						eventIdBucket.getKeyAsString(),
						eventIdBucket.getDocCount());
				}

				embeddedJSONObject.put(
					"activities-count", eventIdCountsJSONObject);
			}

			activityGroupJSONObject.put("_embedded", embeddedJSONObject);
		}

		for (String groupId : activityGroupsJSONObject.keySet()) {
			JSONObject activityGroupJSONObject =
				activityGroupsJSONObject.getJSONObject(groupId);

			JSONObject embeddedJSONObject = new JSONObject();

			if (expandActivities) {
				embeddedJSONObject.put("activities", new JSONArray());
			}

			if (expandActivitiesCount) {
				embeddedJSONObject.put("activities-count", new JSONObject());
			}

			activityGroupJSONObject.put("_embedded", embeddedJSONObject);
		}
	}

	private QueryBuilder _getExpandQueryBuilder(
			Collection<String> activityGroupIds, String filterString)
		throws Exception {

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString, _faroInfoActivitiesFilterStringConverterHelper);

		if (queryBuilder != null) {
			return BoolQueryBuilderUtil.filter(
				queryBuilder
			).filter(
				QueryBuilders.termsQuery("groupId", activityGroupIds)
			);
		}

		return QueryBuilders.termsQuery("groupId", activityGroupIds);
	}

	private static final Log _log = LogFactory.getLog(
		ActivityGroupsRestController.class);

	@Autowired
	private FaroInfoActivitiesFilterStringConverterHelper
		_faroInfoActivitiesFilterStringConverterHelper;

}