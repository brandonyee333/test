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
public class InterestsEmbeddedJSONObjectCreator
	extends BaseEmbeddedJSONObjectCreator {

	public InterestsEmbeddedJSONObjectCreator(
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

		if (expandParts.contains("interest-aggregation-last-30-days")) {
			days = 30;
		}
		else if (expandParts.contains("interest-aggregation-last-60-days")) {
			days = 60;
		}
		else if (expandParts.contains("interest-aggregation-last-90-days")) {
			days = 90;
		}
		else if (!expandParts.contains("pages-visited")) {
			if (_log.isWarnEnabled()) {
				_log.warn("Invalid expand: " + _expand);
			}

			return null;
		}

		JSONObject embeddedJSONObject = new JSONObject();

		JSONObject interestJSONObject = _elasticsearchInvoker.get(
			"interests", id);

		if (days > 0) {
			String endDateDayString = DateUtil.newDayDateString();

			embeddedJSONObject.put(
				"interest-aggregation-last-" + days + "-days",
				_getInterestAggregationJSONArray(
					endDateDayString, interestJSONObject.getString("name"),
					interestJSONObject.getString("ownerId"),
					interestJSONObject.getString("ownerType"),
					DateUtil.addDays(endDateDayString, 1 - days)));
		}

		if (expandParts.contains("pages-visited")) {
			embeddedJSONObject.put(
				"pages-visited", _getVisitedPagesJSONArray(interestJSONObject));
		}

		return embeddedJSONObject;
	}

	private JSONArray _getInterestAggregationJSONArray(
			String endDayDateString, String name, String ownerId,
			String ownerType, String startDayDateString)
		throws Exception {

		JSONArray interestAggregationJSONArray = new JSONArray();

		JSONArray interestsJSONArray = _elasticsearchInvoker.get(
			"interests",
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
				QueryBuilders.termQuery("name", name)
			).filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));

		Map<String, JSONObject> interestJSONObjects = new HashMap<>();

		for (int i = 0; i < interestsJSONArray.length(); i++) {
			JSONObject interestJSONObject = interestsJSONArray.getJSONObject(i);

			interestJSONObjects.put(
				interestJSONObject.getString("dateRecorded"),
				interestJSONObject);
		}

		String currentDayDateString = startDayDateString;

		while (currentDayDateString.compareTo(endDayDateString) <= 0) {
			JSONObject interestJSONObject = interestJSONObjects.get(
				currentDayDateString);

			if (interestJSONObject != null) {
				interestAggregationJSONArray.put(
					JSONUtil.put(
						"intervalInitDate", currentDayDateString
					).put(
						"scoreAvg", interestJSONObject.getDouble("score")
					).put(
						"totalElements", 1
					).put(
						"viewsSum", interestJSONObject.getInt("views")
					));
			}
			else {
				interestAggregationJSONArray.put(
					JSONUtil.put(
						"intervalInitDate", currentDayDateString
					).put(
						"scoreAvg", 0.0
					).put(
						"totalElements", 0
					).put(
						"viewsSum", 0
					));
			}

			currentDayDateString = DateUtil.addDays(currentDayDateString, 1);
		}

		return interestAggregationJSONArray;
	}

	private JSONArray _getVisitedPagesJSONArray(JSONObject interestJSONObject) {
		return _elasticsearchInvoker.get(
			"visited-pages",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"day", interestJSONObject.getString("dateRecorded"))
			).filter(
				QueryBuilders.termQuery(
					"interestName", interestJSONObject.getString("name"))
			).filter(
				QueryBuilders.termQuery(
					"ownerId", interestJSONObject.getString("ownerId"))
			).filter(
				QueryBuilders.termQuery(
					"ownerType", interestJSONObject.getString("ownerType"))
			));
	}

	private static final Log _log = LogFactory.getLog(
		InterestsEmbeddedJSONObjectCreator.class);

	private final ElasticsearchInvoker _elasticsearchInvoker;
	private final String _expand;

}