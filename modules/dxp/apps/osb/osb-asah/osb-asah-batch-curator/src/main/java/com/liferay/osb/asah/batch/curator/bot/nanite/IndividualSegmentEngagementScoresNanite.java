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
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoEngagementDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualSegmentDog;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class IndividualSegmentEngagementScoresNanite
	extends BaseEngagementScoresNanite {

	@Override
	public boolean isLogRunEnabled() {
		return true;
	}

	@Override
	public void run(String dayDateString) throws Exception {
		run(Collections.emptyMap(), "individual-segments", dayDateString, null);

		_faroInfoEngagementDog.clearCache();
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void process(
			Map<String, Object> context, String dayDateString,
			JSONObject individualSegmentJSONObject)
		throws Exception {

		String individualSegmentId = individualSegmentJSONObject.getString(
			"id");

		double engagementScore = _computeEngagementScore(
			dayDateString, individualSegmentJSONObject);

		if ((engagementScore == 0) || Double.isInfinite(engagementScore)) {
			_faroInfoEngagementDog.deleteScore(
				dayDateString, individualSegmentId, "individual-segment");
		}
		else {
			_faroInfoEngagementDog.saveEngagement(
				dayDateString, null, null, null, individualSegmentId,
				"individual-segment", engagementScore);
		}

		JSONObject osbAsahMarkerJSONObject = getOSBAsahMarkerJSONObject();

		String lastSuccessfulDayDateString = osbAsahMarkerJSONObject.optString(
			"lastSuccessfulDay", null);

		if (lastSuccessfulDayDateString != null) {
			int deltaDays = DateUtil.getDeltaDays(
				lastSuccessfulDayDateString, dayDateString);

			if (deltaDays >= 0) {
				_faroInfoIndividualSegmentDog.updateIndividualSegment(
					individualSegmentId,
					JSONUtil.put(
						"activeIndividualCount",
						_getActiveIndividualCount(
							dayDateString, individualSegmentId)
					).put(
						"engagementScore", engagementScore
					));
			}
		}
	}

	private double _computeEngagementScore(
			String dayDateString, JSONObject individualSegmentJSONObject)
		throws Exception {

		String individualSegmentId = individualSegmentJSONObject.getString(
			"id");

		SearchResponse searchResponse = faroInfoElasticsearchInvoker.search(
			"engagements",
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.sum(
						"totalScore"
					).field(
						"score"
					));
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("dateRecorded", dayDateString)
					).filter(
						QueryBuilders.termQuery(
							"individualSegmentIds", individualSegmentId)
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (aggregations == null) {
			return 0;
		}

		Sum sum = aggregations.get("totalScore");

		double totalScore = sum.getValue();

		if (totalScore == 0) {
			return 0;
		}

		int membershipCount = _getMembershipCount(
			dayDateString, individualSegmentId);

		if (membershipCount == 0) {
			return 0;
		}

		return totalScore / membershipCount;
	}

	private long _getActiveIndividualCount(
		String dayDateString, String individualSegmentId) {

		return faroInfoElasticsearchInvoker.count(
			"engagements",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dateRecorded", dayDateString)
			).filter(
				QueryBuilders.termQuery(
					"individualSegmentIds", individualSegmentId)
			).filter(
				QueryBuilders.termQuery("ownerType", "individual")
			));
	}

	private int _getMembershipCount(
			String dayDateString, String individualSegmentId)
		throws Exception {

		String endOfDayDateString = DateUtil.newEndOfDayDateString(
			dayDateString);

		JSONArray membershipChangesJSONArray = new JSONArray(
			faroInfoElasticsearchInvoker.get(
				"membership-changes",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.rangeQuery(
								"dateChanged"
							).lte(
								endOfDayDateString
							)
						).filter(
							QueryBuilders.termQuery(
								"individualSegmentId", individualSegmentId)
						));
					searchSourceBuilder.size(1);
					searchSourceBuilder.sort("id", SortOrder.DESC);
				}));

		if (membershipChangesJSONArray.length() == 0) {
			return 0;
		}

		JSONObject membershipChangeJSONObject =
			membershipChangesJSONArray.getJSONObject(0);

		return membershipChangeJSONObject.getInt("individualsCount");
	}

	private static final Log _log = LogFactory.getLog(
		IndividualSegmentEngagementScoresNanite.class);

	@Autowired
	private FaroInfoEngagementDog _faroInfoEngagementDog;

	@Autowired
	private FaroInfoIndividualSegmentDog _faroInfoIndividualSegmentDog;

}