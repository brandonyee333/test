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

import com.liferay.osb.asah.backend.rest.controller.util.FilterUtil;
import com.liferay.osb.asah.common.dog.ActivityGroupDog;
import com.liferay.osb.asah.common.dto.ActivityGroupDTO;
import com.liferay.osb.asah.common.dto.PageDTO;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoActivitiesFilterStringConverterHelper;
import com.liferay.osb.asah.common.model.ActivityGroup;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.data.domain.Page;
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
	public ActivityGroupDTO getActivityGroup(@PathVariable Long id) {
		return new ActivityGroupDTO(_activityGroupDog.getActivityGroup(id));
	}

	@GetMapping
	public PageDTO<ActivityGroupDTO> getActivityGroups(
			@RequestParam(required = false) String expand,
			@RequestParam(name = "expandFilter", required = false)
				String expandFilterString,
			@RequestParam(name = "filter", required = false)
				String filterString,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "20") int size,
			@RequestParam(name = "sort", required = false) String[] sorts)
		throws Exception {

		Page<ActivityGroup> activityGroups =
			_activityGroupDog.searchActivityGroups(
				FilterUtil.getLong(filterString, "channelId"),
				FilterUtil.getEndDate(filterString, "day"),
				FilterUtil.getStartDate(filterString, "day"),
				FilterUtil.getLong(filterString, "ownerId"), page, size, sorts);

		if (!StringUtils.isEmpty(expand)) {
			if (activityGroups.isEmpty()) {
				return _toPageDTO(activityGroups);
			}

			Map<String, ActivityGroupDTO> activityGroupDTOs = Stream.of(
				activityGroups.getContent()
			).flatMap(
				List::stream
			).map(
				ActivityGroupDTO::new
			).collect(
				LinkedHashMap::new,
				(map, activityGroupDTO) -> map.put(
					activityGroupDTO.getId(), activityGroupDTO),
				Map::putAll
			);

			_addExpansion(activityGroupDTOs, expand, expandFilterString);

			return _toPageDTO(
				activityGroups,
				new ActivityGroupDTO(activityGroupDTOs.values()));
		}

		return _toPageDTO(activityGroups);
	}

	private void _addExpansion(
			Map<String, ActivityGroupDTO> activityGroupDTOs, String expand,
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

		QueryBuilder queryBuilder = _getExpandQueryBuilder(
			activityGroupDTOs.keySet(), filterString);

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
						activityGroupDTOs.size()
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

		for (Map.Entry<String, ActivityGroupDTO> activityGroupDTOEntry :
				activityGroupDTOs.entrySet()) {

			Map<String, Object> embedded = new HashMap<>();

			Terms.Bucket groupIdBucket = groupIdTerms.getBucketByKey(
				activityGroupDTOEntry.getKey());

			if (groupIdBucket != null) {
				Aggregations groupIdAggregations =
					groupIdBucket.getAggregations();

				if (expandActivities) {
					TopHits topHits = groupIdAggregations.get("activities");

					SearchHits searchHits = topHits.getHits();

					Stream<SearchHit> stream = Arrays.stream(
						searchHits.getHits());

					embedded.put(
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

					for (Terms.Bucket eventIdBucket :
							eventIdTerms.getBuckets()) {

						eventIdCountsJSONObject.put(
							eventIdBucket.getKeyAsString(),
							eventIdBucket.getDocCount());
					}

					embedded.put("activities-count", eventIdCountsJSONObject);
				}
			}
			else {
				if (expandActivities) {
					embedded.put("activities", new JSONArray());
				}

				if (expandActivitiesCount) {
					embedded.put("activities-count", new JSONObject());
				}
			}

			ActivityGroupDTO activityGroupDTO =
				activityGroupDTOEntry.getValue();

			activityGroupDTO.setEmbedded(embedded);
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

	private PageDTO<ActivityGroupDTO> _toPageDTO(
		Page<ActivityGroup> activityGroups) {

		return _toPageDTO(
			activityGroups, new ActivityGroupDTO(activityGroups.getContent()));
	}

	private PageDTO<ActivityGroupDTO> _toPageDTO(
		Page<ActivityGroup> activityGroups, ActivityGroupDTO activityGroupDTO) {

		return new PageDTO<>(
			"_embedded", activityGroupDTO, activityGroups.getNumber(),
			activityGroups.getSize(), activityGroups.getTotalElements(),
			activityGroups.getTotalPages());
	}

	private static final Log _log = LogFactory.getLog(
		ActivityGroupsRestController.class);

	@Autowired
	private ActivityGroupDog _activityGroupDog;

	@Autowired
	private FaroInfoActivitiesFilterStringConverterHelper
		_faroInfoActivitiesFilterStringConverterHelper;

}