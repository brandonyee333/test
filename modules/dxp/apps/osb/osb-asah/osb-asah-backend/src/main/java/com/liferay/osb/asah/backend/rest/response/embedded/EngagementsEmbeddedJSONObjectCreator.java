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
import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.rest.response.embedded.BaseEmbeddedJSONObjectCreator;

import java.time.LocalDateTime;

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

		LocalDateTime endDayLocalDateTime = DateUtil.newDayLocalDateTime(
			TimeZoneDogUtil.getZoneId());

		JSONObject engagementJSONObject = _elasticsearchInvoker.get(
			"engagements", id);

		return JSONUtil.put(
			"engagement-aggregation-last-" + days + "-days",
			_getEngagementAggregationJSONArray(
				endDayLocalDateTime, engagementJSONObject.getString("ownerId"),
				engagementJSONObject.getString("ownerType"),
				endDayLocalDateTime.plusDays(1 - days)));
	}

	private JSONArray _getEngagementAggregationJSONArray(
			LocalDateTime endDayLocalDateTime, String ownerId, String ownerType,
			LocalDateTime startDayLocalDateTime)
		throws Exception {

		JSONArray engagementAggregationJSONArray = new JSONArray();

		JSONArray engagementsJSONArray = _elasticsearchInvoker.get(
			"engagements",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"dateRecorded"
				).gte(
					startDayLocalDateTime.toString()
				).lte(
					endDayLocalDateTime.toString()
				).timeZone(
					TimeZoneDogUtil.getTimeZoneId()
				)
			).filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));

		Map<LocalDateTime, Double> scores = new HashMap<>();

		for (int i = 0; i < engagementsJSONArray.length(); i++) {
			JSONObject engagementJSONObject =
				engagementsJSONArray.getJSONObject(i);

			scores.put(
				DateUtil.toLocalDateTime(
					DateUtil.toUTCDate(
						engagementJSONObject.getString("dateRecorded")),
					TimeZoneDogUtil.getZoneId()),
				engagementJSONObject.getDouble("score"));
		}

		LocalDateTime currentDayLocalDateTime = startDayLocalDateTime;

		while (currentDayLocalDateTime.compareTo(endDayLocalDateTime) <= 0) {
			Double score = scores.get(currentDayLocalDateTime);

			if (score != null) {
				engagementAggregationJSONArray.put(
					JSONUtil.put(
						"intervalInitDate", currentDayLocalDateTime.toString()
					).put(
						"scoreAvg", score
					).put(
						"totalElements", 1
					));
			}
			else {
				engagementAggregationJSONArray.put(
					JSONUtil.put(
						"intervalInitDate", currentDayLocalDateTime.toString()
					).put(
						"scoreAvg", 0.0
					).put(
						"totalElements", 0
					));
			}

			currentDayLocalDateTime = currentDayLocalDateTime.plusDays(1);
		}

		return engagementAggregationJSONArray;
	}

	private static final Log _log = LogFactory.getLog(
		EngagementsEmbeddedJSONObjectCreator.class);

	private final ElasticsearchInvoker _elasticsearchInvoker;
	private final String _expand;

}