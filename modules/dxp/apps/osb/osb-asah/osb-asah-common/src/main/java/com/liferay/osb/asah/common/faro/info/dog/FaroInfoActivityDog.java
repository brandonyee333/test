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

package com.liferay.osb.asah.common.faro.info.dog;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.Individual;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.UserSession;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregation;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class FaroInfoActivityDog extends BaseFaroInfoDog {

	public void addActivity(JSONArray activityJSONArray) {
		elasticsearchInvoker.add("activities", activityJSONArray);

		List<Long> referencedAssetIds = _segmentDog.getReferencedAssetIds();

		JSONArray contextJSONArray = new JSONArray();

		for (int i = 0; i < activityJSONArray.length(); i++) {
			JSONObject activityJSONObject = activityJSONArray.getJSONObject(i);

			JSONObject objectJSONObject = activityJSONObject.getJSONObject(
				"object");

			if (!referencedAssetIds.contains(objectJSONObject.getLong("id"))) {
				continue;
			}

			contextJSONArray.put(
				JSONUtil.put(
					"dateModified", DateUtil.newDateString()
				).put(
					"filter",
					"contains(filter, '" +
						activityJSONObject.getString("activityKey") + "')"
				).put(
					"individualJSONObject",
					elasticsearchInvoker.get(
						"individuals", activityJSONObject.getString("ownerId"))
				));
		}

		_asahTaskDog.scheduleAsahTask(
			"UpdateDynamicMembershipsNanite", contextJSONArray);
	}

	public JSONObject addActivity(JSONObject activityJSONObject) {
		activityJSONObject = elasticsearchInvoker.add(
			"activities", activityJSONObject);

		List<Long> referencedAssetIds = _segmentDog.getReferencedAssetIds();

		JSONObject objectJSONObject = activityJSONObject.getJSONObject(
			"object");

		if (!referencedAssetIds.contains(objectJSONObject.getLong("id"))) {
			return activityJSONObject;
		}

		_asahTaskDog.scheduleAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"dateModified", DateUtil.newDateString()
			).put(
				"filter",
				"contains(filter, '" +
					activityJSONObject.getString("activityKey") + "')"
			).put(
				"individualJSONObject",
				elasticsearchInvoker.get(
					"individuals", activityJSONObject.getString("ownerId"))
			));

		return activityJSONObject;
	}

	public void deleteActivies(Set<Long> channelIds) {
		elasticsearchInvoker.delete(
			"activities",
			QueryBuilders.termsQuery(
				"channelId", SetUtil.map(channelIds, String::valueOf)));
	}

	public JSONObject fetchLatestActivityJSONObject(
		Long channelId, Long individualId) {

		JSONArray activitiesJSONArray = new JSONArray(
			elasticsearchInvoker.get(
				"activities",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						BoolQueryBuilderUtil.filter(
							getEventsQueryBuilder(String.valueOf(individualId))
						).filter(
							QueryBuilders.termQuery(
								"channelId", String.valueOf(channelId))
						));
					searchSourceBuilder.size(1);
					searchSourceBuilder.sort(
						SortBuilderUtil.fieldSort("endTime", SortOrder.DESC));
				}));

		if (activitiesJSONArray.length() == 0) {
			return null;
		}

		return activitiesJSONArray.getJSONObject(0);
	}

	public JSONObject fetchLatestActivityJSONObject(String sessionId) {
		return elasticsearchInvoker.fetch(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("eventContext")
			).filter(
				QueryBuilders.termQuery("sessionId", sessionId)
			),
			SortBuilderUtil.fieldSort("id", SortOrder.DESC), null,
			"eventContext");
	}

	public JSONObject fetchLatestFormViewedActivity(
		Date eventDate, String userId) {

		JSONArray formViewedActivityJSONArray = new JSONArray(
			elasticsearchInvoker.get(
				"activities",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.rangeQuery(
								"endTime"
							).lt(
								DateUtil.toUTCString(eventDate)
							)
						).filter(
							QueryBuilders.termQuery("eventId", "formViewed")
						).filter(
							QueryBuilders.termQuery("userId", userId)
						));

					searchSourceBuilder.size(1);
					searchSourceBuilder.sort(
						SortBuilderUtil.fieldSort("endTime", SortOrder.DESC));
				}));

		if (formViewedActivityJSONArray.length() == 0) {
			return null;
		}

		return formViewedActivityJSONArray.getJSONObject(0);
	}

	public QueryBuilder getEventsQueryBuilder(String ownerId) {
		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("ownerId", ownerId));

		BoolQueryBuilder eventsBoolQueryBuilder = QueryBuilders.boolQuery();

		for (Map.Entry<String, String[]> entry : _eventIds.entrySet()) {
			eventsBoolQueryBuilder.should(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("applicationId", entry.getKey())
				).filter(
					QueryBuilders.termsQuery("eventId", entry.getValue())
				));
		}

		return boolQueryBuilder.filter(eventsBoolQueryBuilder);
	}

	public String getFirstDayDateString() {
		JSONArray activitiesJSONArray = new JSONArray(
			elasticsearchInvoker.get(
				"activities",
				searchSourceBuilder -> {
					searchSourceBuilder.size(1);
					searchSourceBuilder.sort(SortBuilderUtil.fieldSort("day"));
				}));

		if (activitiesJSONArray.length() == 0) {
			return null;
		}

		JSONObject activityJSONObject = activitiesJSONArray.getJSONObject(0);

		return activityJSONObject.getString("day");
	}

	public long getIndividualPageViews(String dayDateString, Long ownerId) {
		return elasticsearchInvoker.count(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Page")
			).filter(
				QueryBuilders.termQuery("day", dayDateString)
			).filter(
				QueryBuilders.termQuery("eventId", "pageViewed")
			).filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			));
	}

	public List<String> getOwnerIds(
		boolean checkEqualityOnly, int minDocCount, QueryBuilder queryBuilder,
		int value) {

		SearchResponse searchResponse = elasticsearchInvoker.search(
			"activities",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"ownerIds"
					).field(
						"ownerId"
					).minDocCount(
						minDocCount
					).order(
						BucketOrder.compound(
							BucketOrder.count(checkEqualityOnly),
							BucketOrder.key(true))
					).size(
						Integer.MAX_VALUE
					));

				searchSourceBuilder.query(queryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		Terms terms = aggregations.get("ownerIds");

		List<String> ownerIds = new ArrayList<>();

		for (Terms.Bucket bucket : terms.getBuckets()) {
			if (checkEqualityOnly && (bucket.getDocCount() > value)) {
				break;
			}

			ownerIds.add(bucket.getKeyAsString());
		}

		return ownerIds;
	}

	public List<String> getOwnerIds(QueryBuilder queryBuilder) {
		List<String> ownerIds = new ArrayList<>();

		SearchResponse searchResponse = elasticsearchInvoker.search(
			"activities",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"ownerIds"
					).field(
						"ownerId"
					).size(
						Integer.MAX_VALUE
					));

				searchSourceBuilder.query(queryBuilder);
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (aggregations == null) {
			return ownerIds;
		}

		List<Aggregation> aggregationList = aggregations.asList();

		if (aggregationList.isEmpty()) {
			return ownerIds;
		}

		Terms terms = aggregations.get("ownerIds");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			ownerIds.add(bucket.getKeyAsString());
		}

		return ownerIds;
	}

	public Set<Long> getOwnerIds(String dayDateString) {
		Set<Long> ownerIds = new HashSet<>();

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		TermsValuesSourceBuilder termsValuesSourceBuilder =
			new TermsValuesSourceBuilder("ownerIds");

		termsValuesSourceBuilder.field("ownerId");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite", Collections.singletonList(termsValuesSourceBuilder)
			).size(
				10000
			);

		searchSourceBuilder.aggregation(compositeAggregationBuilder);

		searchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Page")
			).filter(
				QueryBuilders.termQuery("day", dayDateString)
			).filter(
				QueryBuilders.termQuery("eventId", "pageViewed")
			));

		searchSourceBuilder.size(0);

		while (true) {
			SearchResponse searchResponse = elasticsearchInvoker.search(
				"activities", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				break;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Map<String, Object> keys = bucket.getKey();

				ownerIds.add(Long.valueOf((String)keys.get("ownerIds")));
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		return ownerIds;
	}

	public long getTotalKeywordViews(String dayDateString, List<String> urls) {
		return elasticsearchInvoker.count(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Page")
			).filter(
				QueryBuilders.termQuery("day", dayDateString)
			).filter(
				QueryBuilders.termQuery("eventId", "pageViewed")
			).filter(
				QueryBuilders.termsQuery("object.dataSourceAssetPK", urls)
			));
	}

	public long getTotalPageViews(String dayDateString) {
		return elasticsearchInvoker.count(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Page")
			).filter(
				QueryBuilders.termQuery("day", dayDateString)
			).filter(
				QueryBuilders.termQuery("eventId", "pageViewed")
			));
	}

	public Map<String, Long> getURLPageViewsMap(
		String endDayDateString, Long ownerId, String startDayDateString) {

		Map<String, Long> urlsPageViewsMap = new HashMap<>();

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		TermsValuesSourceBuilder termsValuesSourceBuilder =
			new TermsValuesSourceBuilder("urls");

		termsValuesSourceBuilder.field("object.dataSourceAssetPK");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite",
				Collections.singletonList(termsValuesSourceBuilder));

		compositeAggregationBuilder.size(10000);

		searchSourceBuilder.aggregation(compositeAggregationBuilder);

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("applicationId", "Page")
		).filter(
			QueryBuilders.termQuery("eventId", "pageViewed")
		);

		if (startDayDateString != null) {
			boolQueryBuilder.filter(
				QueryBuilders.rangeQuery(
					"day"
				).gte(
					startDayDateString
				).lte(
					endDayDateString
				));
		}
		else {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("day", endDayDateString));
		}

		BoolQueryBuilderUtil.filterTerm(
			boolQueryBuilder, "ownerId", String.valueOf(ownerId));

		searchSourceBuilder.query(boolQueryBuilder);

		searchSourceBuilder.size(0);

		while (true) {
			SearchResponse searchResponse = elasticsearchInvoker.search(
				"activities", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				break;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Map<String, Object> keys = bucket.getKey();

				urlsPageViewsMap.put(
					(String)keys.get("urls"), bucket.getDocCount());
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		return urlsPageViewsMap;
	}

	public boolean isActivity(String applicationId, String eventId) {
		String[] applicationEventIds = _eventIds.get(applicationId);

		if (applicationEventIds != null) {
			for (String applicationEventId : applicationEventIds) {
				if (applicationEventId.equals(eventId)) {
					return true;
				}
			}
		}

		return false;
	}

	public void updateOwnerId(Individual individual, String userId) {
		elasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery("userId", userId), true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.ownerId = params.ownerId",
				Collections.singletonMap(
					"ownerId", String.valueOf(individual.getId()))),
			"activities");

		JSONArray activitiesJSONArray = elasticsearchInvoker.get(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery(
					"object.id",
					ListUtil.map(
						_segmentDog.getReferencedAssetIds(), String::valueOf))
			).filter(
				QueryBuilders.termQuery("userId", userId)
			));

		for (int i = 0; i < activitiesJSONArray.length(); i++) {
			JSONObject activityJSONObject = activitiesJSONArray.getJSONObject(
				i);

			_asahTaskDog.scheduleAsahTask(
				"UpdateDynamicMembershipsNanite",
				JSONUtil.put(
					"dateModified", DateUtil.newDateString()
				).put(
					"filter",
					"contains(filter, " +
						activityJSONObject.getString("activityKey") + ")"
				).put(
					"individualJSONObject",
					_objectMapper.convertValue(individual, JSONObject.class)
				));
		}
	}

	public void updateSessionId(UserSession userSession) {
		Script script = new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			"ctx._source.sessionId = params.sessionId",
			Collections.singletonMap("sessionId", userSession.getId()));

		elasticsearchInvoker.updateByQueryWithRetry(
			BoolQueryBuilderUtil.filter(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceId", userSession.getDataSourceId())
				).filter(
					QueryBuilders.rangeQuery(
						"endTime"
					).gte(
						DateUtil.toUTCString(userSession.getFirstEventDate())
					).lte(
						DateUtil.toUTCString(userSession.getLastEventDate())
					)
				).filter(
					QueryBuilders.termQuery("userId", userSession.getUserId())
				)
			).mustNot(
				QueryBuilders.existsQuery("sessionId")
			),
			true, script, "activities");
	}

	private static final Map<String, String[]> _eventIds =
		new HashMap<String, String[]>() {
			{
				put("Blog", new String[] {"commentPosted"});
				put(
					"Document",
					new String[] {"documentDownloaded", "documentPreviewed"});
				put("Form", new String[] {"formSubmitted", "formViewed"});
				put("Page", new String[] {"pageViewed"});
			}
		};

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SegmentDog _segmentDog;

}