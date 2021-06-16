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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.dog.SegmentDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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

	public String fetchLatestPageViewActivityId(String userId) {
		JSONArray mostRecentPageViewActivitiesJSONArray = new JSONArray(
			elasticsearchInvoker.get(
				"activities",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Page")
						).filter(
							QueryBuilders.termQuery("eventId", "pageViewed")
						).filter(
							QueryBuilders.termQuery("userId", userId)
						));
					searchSourceBuilder.size(1);
					searchSourceBuilder.sort(
						SortBuilderUtil.fieldSort("startTime", SortOrder.DESC));
				}));

		if (mostRecentPageViewActivitiesJSONArray.length() == 0) {
			return null;
		}

		JSONObject mostRecentPageViewActivityJSONObject =
			mostRecentPageViewActivitiesJSONArray.getJSONObject(0);

		JSONObject eventPropertiesJSONObject =
			mostRecentPageViewActivityJSONObject.getJSONObject(
				"eventProperties");

		return eventPropertiesJSONObject.optString("pageViewActivityId", null);
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

	public boolean isActivity(String applicationId, String eventId) {
		String[] applicationEventIds = _eventIds.get(applicationId);

		if (applicationEventIds != null) {
			List<String> applicationEventIdsList = Arrays.asList(
				applicationEventIds);

			return applicationEventIdsList.contains(eventId);
		}

		return false;
	}

	public void updateOwnerId(
			JSONObject activityJSONObject, JSONObject individualJSONObject)
		throws Exception {

		elasticsearchInvoker.update(
			"activities", activityJSONObject.getString("id"),
			JSONUtil.put("ownerId", individualJSONObject.getString("id")));

		List<Long> referencedAssetIds = _segmentDog.getReferencedAssetIds();

		JSONObject objectJSONObject = activityJSONObject.getJSONObject(
			"object");

		if (!referencedAssetIds.contains(objectJSONObject.getLong("id"))) {
			return;
		}

		_asahTaskDog.scheduleAsahTask(
			"UpdateDynamicMembershipsNanite",
			JSONUtil.put(
				"dateModified", DateUtil.newDateString()
			).put(
				"filter",
				"contains(filter, " +
					activityJSONObject.getString("activityKey") + ")"
			).put(
				"individualJSONObject", individualJSONObject
			));
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
	private SegmentDog _segmentDog;

}