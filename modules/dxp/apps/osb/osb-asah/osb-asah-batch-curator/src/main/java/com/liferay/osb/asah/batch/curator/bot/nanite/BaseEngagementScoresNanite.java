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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;

import java.io.Serializable;

import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Bowerman
 */
public abstract class BaseEngagementScoresNanite extends BaseScoresNanite {

	protected double computeScrollDepthScore(
		JSONObject scrollingActivityJSONObject) {

		JSONObject eventPropertiesJSONObject =
			scrollingActivityJSONObject.getJSONObject("eventProperties");

		int depth = Math.max(eventPropertiesJSONObject.getInt("depth"), 0);

		return (double)depth / _MAX_SCROLL_DEPTH;
	}

	protected double computeScrollTimeScore(
			JSONObject scrollingActivityJSONObject)
		throws Exception {

		long scrollTime = getScrollTime(scrollingActivityJSONObject);

		JSONObject eventPropertiesJSONObject =
			scrollingActivityJSONObject.getJSONObject("eventProperties");

		int depth = eventPropertiesJSONObject.getInt("depth");

		double maxScrollTime =
			_AVERAGE_PRESCROLL_PAGE_VIEW_TIME +
				_AVERAGE_SCROLL_TIME_PER_DEPTH * depth;

		return Math.min(Math.max(scrollTime / maxScrollTime, 0), 1);
	}

	protected double computeViewDurationScore(
		JSONObject timingActivityJSONObject) {

		JSONObject eventPropertiesJSONObject =
			timingActivityJSONObject.getJSONObject("eventProperties");

		long viewDuration = Math.max(
			eventPropertiesJSONObject.getLong("viewDuration"), 0);

		return normalizeScore(viewDuration, _AVERAGE_VIEW_DURATION);
	}

	protected JSONArray filterMaxActivitiesJSONArray(
		JSONArray activitiesJSONArray, Comparator<JSONObject> comparator) {

		JSONArray maxActivitiesJSONArray = new JSONArray();

		String currentPageViewActivityId = null;
		JSONObject maxActivityJSONObject = null;

		for (int i = 0; i < activitiesJSONArray.length(); i++) {
			JSONObject activityJSONObject = activitiesJSONArray.getJSONObject(
				i);

			JSONObject eventPropertiesJSONObject =
				activityJSONObject.getJSONObject("eventProperties");

			String pageViewActivityId = eventPropertiesJSONObject.optString(
				"pageViewActivityId", null);

			if (pageViewActivityId == null) {
				continue;
			}

			if (!pageViewActivityId.equals(currentPageViewActivityId)) {
				currentPageViewActivityId = pageViewActivityId;

				if (maxActivityJSONObject != null) {
					maxActivitiesJSONArray.put(maxActivityJSONObject);
				}

				maxActivityJSONObject = activityJSONObject;
			}
			else {
				int value = comparator.compare(
					activityJSONObject, maxActivityJSONObject);

				if (value > 0) {
					maxActivityJSONObject = activityJSONObject;
				}
			}
		}

		if (maxActivityJSONObject != null) {
			maxActivitiesJSONArray.put(maxActivityJSONObject);
		}

		return maxActivitiesJSONArray;
	}

	protected long getScrollTime(JSONObject scrollingActivityJSONObject)
		throws Exception {

		JSONObject eventPropertiesJSONObject =
			scrollingActivityJSONObject.getJSONObject("eventProperties");

		String pageViewActivityId = eventPropertiesJSONObject.optString(
			"pageViewActivityId", null);

		if (pageViewActivityId == null) {
			return 0;
		}

		JSONObject pageViewActivityJSONObject =
			faroInfoElasticsearchInvoker.fetch(
				"activities",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("applicationId", "Page")
				).filter(
					QueryBuilders.termQuery("eventId", "pageViewed")
				).filter(
					QueryBuilders.termQuery(
						"eventProperties.pageViewActivityId",
						pageViewActivityId)
				));

		if (pageViewActivityJSONObject == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get page view activity " + pageViewActivityId);
			}

			return 0;
		}

		return DateUtil.getDeltaMilliseconds(
			pageViewActivityJSONObject.getString("startTime"),
			scrollingActivityJSONObject.getString("endTime"));
	}

	protected double normalizeScore(double score, double averageScore) {
		return score /
			Math.sqrt(3 * averageScore * averageScore + score * score);
	}

	protected static class ScrollingActivityJSONObjectComparator
		implements Comparator<JSONObject>, Serializable {

		@Override
		public int compare(
			JSONObject scrollingActivityJSONObject1,
			JSONObject scrollingActivityJSONObject2) {

			JSONObject eventPropertiesJSONObject1 =
				scrollingActivityJSONObject1.getJSONObject("eventProperties");
			JSONObject eventPropertiesJSONObject2 =
				scrollingActivityJSONObject2.getJSONObject("eventProperties");

			int depth1 = eventPropertiesJSONObject1.getInt("depth");
			int depth2 = eventPropertiesJSONObject2.getInt("depth");

			if (depth1 > depth2) {
				return 1;
			}
			else if (depth1 < depth2) {
				return -1;
			}

			String endTimeString1 = scrollingActivityJSONObject1.getString(
				"endTime");
			String endTimeString2 = scrollingActivityJSONObject2.getString(
				"endTime");

			return endTimeString1.compareTo(endTimeString2);
		}

	}

	protected static class TimingActivityJSONObjectComparator
		implements Comparator<JSONObject>, Serializable {

		@Override
		public int compare(
			JSONObject scrollingActivityJSONObject1,
			JSONObject scrollingActivityJSONObject2) {

			JSONObject eventPropertiesJSONObject1 =
				scrollingActivityJSONObject1.getJSONObject("eventProperties");
			JSONObject eventPropertiesJSONObject2 =
				scrollingActivityJSONObject2.getJSONObject("eventProperties");

			long viewDuration1 = eventPropertiesJSONObject1.getLong(
				"viewDuration");
			long viewDuration2 = eventPropertiesJSONObject2.getLong(
				"viewDuration");

			if (viewDuration1 > viewDuration2) {
				return 1;
			}
			else if (viewDuration1 < viewDuration2) {
				return -1;
			}

			String endTimeString1 = scrollingActivityJSONObject1.getString(
				"endTime");
			String endTimeString2 = scrollingActivityJSONObject2.getString(
				"endTime");

			return endTimeString1.compareTo(endTimeString2);
		}

	}

	private static final int _AVERAGE_PRESCROLL_PAGE_VIEW_TIME = 10000;

	private static final int _AVERAGE_SCROLL_TIME_PER_DEPTH = 200;

	private static final int _AVERAGE_VIEW_DURATION = 300000;

	private static final int _MAX_SCROLL_DEPTH = 100;

	private static final Log _log = LogFactory.getLog(
		BaseEngagementScoresNanite.class);

}