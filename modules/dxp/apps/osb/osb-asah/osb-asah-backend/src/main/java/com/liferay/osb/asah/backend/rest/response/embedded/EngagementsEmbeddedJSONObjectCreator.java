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

package com.liferay.osb.asah.backend.rest.response.embedded;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.embedded.BaseEmbeddedJSONObjectCreator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Bowerman
 */
public class EngagementsEmbeddedJSONObjectCreator
	extends BaseEmbeddedJSONObjectCreator {

	public EngagementsEmbeddedJSONObjectCreator(
		ElasticsearchInvoker elasticsearchInvoker, String expand) {

		_elasticsearchInvoker = elasticsearchInvoker;
		_expand = expand;
	}

	@Override
	public JSONObject create(String id) throws Exception {
		if (StringUtils.isEmpty(_expand)) {
			return null;
		}

		int days = 0;

		Set<String> expandParts = new HashSet<>(
			Arrays.asList(_expand.split(",")));

		if (expandParts.contains("engagement-aggregation-last-30-days")) {
			days = 30;
		}
		else if (expandParts.contains("engagement-aggregation-last-60-days")) {
			days = 60;
		}
		else if (expandParts.contains("engagement-aggregation-last-90-days")) {
			days = 90;
		}
		else if (_log.isWarnEnabled()) {
			_log.warn("Invalid expand: " + _expand);

			return null;
		}

		String endDateDayString = DateUtil.newDayDateString();

		JSONObject engagementJSONObject = _elasticsearchInvoker.get(
			"engagements", id);

		return JSONUtil.put(
			"engagement-aggregation-last-" + days + "-days",
			_getEngagementAggregationJSONArray(
				endDateDayString, engagementJSONObject.getString("ownerId"),
				engagementJSONObject.getString("ownerType"),
				DateUtil.addDays(endDateDayString, 1 - days)));
	}

	private JSONArray _getEngagementAggregationJSONArray(
			String endDayDateString, String ownerId, String ownerType,
			String startDayDateString)
		throws Exception {

		JSONArray engagementAggregationJSONArray = new JSONArray();

		JSONArray engagementsJSONArray = _elasticsearchInvoker.get(
			"engagements",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"dateRecorded"
				).gte(
					startDayDateString
				)
			).filter(
				QueryBuilders.rangeQuery(
					"dateRecorded"
				).lte(
					endDayDateString
				)
			).filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));

		Map<String, Double> scores = new HashMap<>();

		for (int i = 0; i < engagementsJSONArray.length(); i++) {
			JSONObject engagementJSONObject =
				engagementsJSONArray.getJSONObject(i);

			scores.put(
				engagementJSONObject.getString("dateRecorded"),
				engagementJSONObject.getDouble("score"));
		}

		String currentDayDateString = startDayDateString;

		while (currentDayDateString.compareTo(endDayDateString) <= 0) {
			Double score = scores.get(currentDayDateString);

			if (score != null) {
				engagementAggregationJSONArray.put(
					JSONUtil.put(
						"intervalInitDate", currentDayDateString
					).put(
						"scoreAvg", score
					).put(
						"totalElements", 1
					));
			}
			else {
				engagementAggregationJSONArray.put(
					JSONUtil.put(
						"intervalInitDate", currentDayDateString
					).put(
						"scoreAvg", 0.0
					).put(
						"totalElements", 0
					));
			}

			currentDayDateString = DateUtil.addDays(currentDayDateString, 1);
		}

		return engagementAggregationJSONArray;
	}

	private static final Log _log = LogFactory.getLog(
		EngagementsEmbeddedJSONObjectCreator.class);

	private final ElasticsearchInvoker _elasticsearchInvoker;
	private final String _expand;

}