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

package com.liferay.osb.asah.upgrade.v3_0_0;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.AnalyticsEventStorageDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class CustomEventUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		for (SearchHit[] searchHits = _readBatch(); searchHits.length > 0;
			 searchHits = _readBatch()) {

			_writeBatch(searchHits);
		}

		_clearScroll();
	}

	private void _clearScroll() {
		if (_scrollId == null) {
			return;
		}

		try {
			_faroInfoElasticsearchInvoker.clearScroll(_scrollId);
		}
		catch (Exception exception) {
			_log.error("Unable to clear scroll ID " + _scrollId, exception);
		}
	}

	private Map<String, String> _getEventContext(
		JSONObject activityJSONObject) {

		JSONObject eventContextJSONObject = activityJSONObject.optJSONObject(
			"eventContext");

		if (eventContextJSONObject != null) {
			return _toSafeMap(eventContextJSONObject.toMap());
		}

		Map<String, String> eventContext = new HashMap<>();

		JSONObject objectJSONObject = activityJSONObject.getJSONObject(
			"object");

		eventContext.put(
			"canonicalUrl", objectJSONObject.getString("canonicalUrl"));
		eventContext.put("url", objectJSONObject.getString("url"));

		if (Objects.equals(
				activityJSONObject.getString("applicationId"), "Page")) {

			eventContext.put("title", objectJSONObject.getString("name"));
		}

		return eventContext;
	}

	private SearchHit[] _readBatch() {
		SearchResponse searchResponse = null;

		if (_scrollId == null) {
			searchResponse = _faroInfoElasticsearchInvoker.searchScroll(
				"activities",
				searchSourceBuilder -> {
					searchSourceBuilder.trackTotalHits(false);
					searchSourceBuilder.size(500);
					searchSourceBuilder.sort("id", SortOrder.ASC);
				},
				120);
		}
		else {
			searchResponse = _faroInfoElasticsearchInvoker.searchScroll(
				_scrollId, 120);
		}

		_scrollId = searchResponse.getScrollId();

		SearchHits searchHits = searchResponse.getHits();

		return searchHits.getHits();
	}

	private Map<String, String> _toSafeMap(Map<String, Object> jsonObjectMap) {
		Map<String, String> map = new HashMap<>();

		for (Map.Entry<String, Object> entry : jsonObjectMap.entrySet()) {
			map.put(entry.getKey(), StringUtil.get(entry.getValue()));
		}

		return map;
	}

	private void _upgradeActivity(JSONObject activityJSONObject) {
		AnalyticsEvent analyticsEvent = new AnalyticsEvent();

		analyticsEvent.setApplicationId(
			activityJSONObject.getString("applicationId"));
		analyticsEvent.setChannelId(activityJSONObject.getString("channelId"));
		analyticsEvent.setContext(_getEventContext(activityJSONObject));
		analyticsEvent.setCreateDate(
			DateUtil.toUTCDate(activityJSONObject.getString("endTime")));
		analyticsEvent.setDataSourceId(
			activityJSONObject.getString("dataSourceId"));
		analyticsEvent.setEventDate(
			DateUtil.toUTCDate(activityJSONObject.getString("endTime")));

		JSONObject eventPropertiesJSONObject = activityJSONObject.getJSONObject(
			"eventProperties");

		analyticsEvent.setEventProperties(
			_toSafeMap(eventPropertiesJSONObject.toMap()));

		analyticsEvent.setId(activityJSONObject.getString("id"));
		analyticsEvent.setEventId(activityJSONObject.getString("eventId"));
		analyticsEvent.setIndividualId(activityJSONObject.getString("ownerId"));
		analyticsEvent.setUserId(activityJSONObject.getString("userId"));

		_analyticsEventStorageDog.store(analyticsEvent);
	}

	private void _writeBatch(SearchHit[] searchHits) {
		Stream<SearchHit> stream = Arrays.stream(searchHits);

		stream.map(
			SearchHit::getSourceAsString
		).forEach(
			sourceString -> _upgradeActivity(new JSONObject(sourceString))
		);
	}

	private static final Log _log = LogFactory.getLog(
		CustomEventUpgradeStep.class);

	@Autowired
	private AnalyticsEventStorageDog _analyticsEventStorageDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private String _scrollId;

}