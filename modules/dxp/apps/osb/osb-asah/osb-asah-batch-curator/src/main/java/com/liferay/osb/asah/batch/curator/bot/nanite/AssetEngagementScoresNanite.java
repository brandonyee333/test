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
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoEngagementDog;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class AssetEngagementScoresNanite extends BaseEngagementScoresNanite {

	public static final int DAYS = 10;

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(String dayDateString) throws Exception {
		run(Collections.emptyMap(), "assets", dayDateString, null);
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void process(
			Map<String, Object> context, String dayDateString,
			JSONObject assetJSONObject)
		throws Exception {

		double engagementScore = _computeEngagementScore(
			assetJSONObject, dayDateString);

		if ((engagementScore == 0) || Double.isInfinite(engagementScore)) {
			return;
		}

		_faroInfoEngagementDog.saveAssetEngagement(
			dayDateString, null, null, null, assetJSONObject.getString("id"),
			engagementScore);
	}

	private double _computeEngagementScore(
			JSONObject assetJSONObject, String dayDateString)
		throws Exception {

		double interactionScore = _computeInteractionScore(
			assetJSONObject, dayDateString);
		double viewScore = _computeViewScore(assetJSONObject, dayDateString);

		return ((4 * interactionScore) + viewScore) / 5;
	}

	private double _computeInteractionScore(
			JSONObject assetJSONObject, String dayDateString)
		throws Exception {

		String assetType = assetJSONObject.getString("assetType");

		if (assetType.equals("Form")) {
			return _computeSubmitInteractionScore(
				assetJSONObject, dayDateString);
		}

		return _computeViewInteractionScore(assetJSONObject, dayDateString);
	}

	private double _computeSubmitInteractionScore(
			JSONObject assetJSONObject, String dayDateString)
		throws Exception {

		long formSubmittedCount = _getFormSubmittedCount(
			assetJSONObject, dayDateString);

		if (formSubmittedCount == 0) {
			return 0;
		}

		long formViewedCount = _getFormViewedCount(
			assetJSONObject, dayDateString);

		if (formViewedCount == 0) {
			return 0;
		}

		return (double)formSubmittedCount / formViewedCount;
	}

	private double _computeViewInteractionScore(
			JSONObject assetJSONObject, String dayDateString)
		throws Exception {

		String startDayDateString = DateUtil.addDays(dayDateString, -DAYS);

		JSONArray scrollingActivitiesJSONArray = new JSONArray(
			faroInfoElasticsearchInvoker.get(
				"activities",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Page")
						).filter(
							QueryBuilders.rangeQuery(
								"day"
							).gt(
								startDayDateString
							).lte(
								dayDateString
							)
						).filter(
							QueryBuilders.termQuery(
								"eventId", "pageDepthReached")
						).filter(
							QueryBuilders.termQuery(
								"object.id", assetJSONObject.getString("id"))
						));
					searchSourceBuilder.sort(
						SortBuilderUtil.fieldSort(
							"eventProperties.pageViewActivityId"));
				}));

		scrollingActivitiesJSONArray = filterMaxActivitiesJSONArray(
			scrollingActivitiesJSONArray,
			new ScrollingActivityJSONObjectComparator());

		if (scrollingActivitiesJSONArray.length() == 0) {
			return 0;
		}

		double score = 0;

		for (int i = 0; i < scrollingActivitiesJSONArray.length(); i++) {
			JSONObject scrollingActivityJSONObject =
				scrollingActivitiesJSONArray.getJSONObject(i);

			double scrollDepthScore = computeScrollDepthScore(
				scrollingActivityJSONObject);
			double scrollTimeScore = computeScrollTimeScore(
				scrollingActivityJSONObject);

			score += scrollDepthScore * scrollTimeScore;
		}

		return score / scrollingActivitiesJSONArray.length();
	}

	private double _computeViewScore(
			JSONObject assetJSONObject, String dayDateString)
		throws Exception {

		String assetType = assetJSONObject.getString("assetType");

		String viewEventIdName = _viewEventIdNames.get(assetType);

		if (viewEventIdName == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Returning a view score of 0 for unknown asset type " +
						assetType + " with id " +
							assetJSONObject.getString("id"));
			}

			return 0;
		}

		long viewCount = faroInfoElasticsearchInvoker.count(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Form")
			).filter(
				QueryBuilders.rangeQuery(
					"day"
				).gt(
					DateUtil.addDays(dayDateString, -DAYS)
				).lte(
					dayDateString
				)
			).filter(
				QueryBuilders.termQuery("eventId", viewEventIdName)
			).filter(
				QueryBuilders.termQuery(
					"object.id", assetJSONObject.getString("id"))
			));

		return normalizeScore(viewCount, _UNNORMALIZED_AVERAGE_SCORE_VIEW);
	}

	private long _getFormSubmittedCount(
			JSONObject assetJSONObject, String dayDateString)
		throws Exception {

		return faroInfoElasticsearchInvoker.count(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Form")
			).filter(
				QueryBuilders.rangeQuery(
					"day"
				).gt(
					DateUtil.addDays(dayDateString, -DAYS)
				).lte(
					dayDateString
				)
			).filter(
				QueryBuilders.termQuery("eventId", "formSubmitted")
			).filter(
				QueryBuilders.termQuery(
					"object.id", assetJSONObject.getString("id"))
			));
	}

	private long _getFormViewedCount(
			JSONObject assetJSONObject, String dayDateString)
		throws Exception {

		return faroInfoElasticsearchInvoker.count(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("applicationId", "Form")
			).filter(
				QueryBuilders.rangeQuery(
					"day"
				).gt(
					DateUtil.addDays(dayDateString, -DAYS)
				).lte(
					dayDateString
				)
			).filter(
				QueryBuilders.termQuery("eventId", "formViewed")
			).filter(
				QueryBuilders.termQuery(
					"object.id", assetJSONObject.getString("id"))
			));
	}

	private static final int _UNNORMALIZED_AVERAGE_SCORE_VIEW = 20;

	private static final Log _log = LogFactory.getLog(
		AssetEngagementScoresNanite.class);

	private static final Map<String, String> _viewEventIdNames =
		new HashMap<String, String>() {
			{
				put("Blog", "blogViewed");
				put("Document", "documentPreviewed");
				put("Form", "formViewed");
				put("Page", "pageViewed");
				put("WebContent", "webContentViewed");
			}
		};

	@Autowired
	private FaroInfoEngagementDog _faroInfoEngagementDog;

}