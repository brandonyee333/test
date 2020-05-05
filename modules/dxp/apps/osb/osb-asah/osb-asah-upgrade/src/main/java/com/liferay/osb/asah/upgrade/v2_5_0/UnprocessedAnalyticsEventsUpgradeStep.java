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

package com.liferay.osb.asah.upgrade.v2_5_0;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class UnprocessedAnalyticsEventsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		String oldestUnprocessedAnalyticsEventId = null;

		JSONObject sessionNaniteOSBAsahMarkersJSONObject =
			_cerebroInfoElasticsearchInvoker.fetch(
				"OSBAsahMarkers", "SessionNanite");

		if (sessionNaniteOSBAsahMarkersJSONObject != null) {
			String lastSuccessfulAnalyticsEventId =
				sessionNaniteOSBAsahMarkersJSONObject.optString(
					"lastSuccessfulAnalyticsEventId", null);

			if (lastSuccessfulAnalyticsEventId != null) {
				oldestUnprocessedAnalyticsEventId =
					lastSuccessfulAnalyticsEventId;
			}
		}

		JSONObject activitiesNaniteOSBAsahMarkerJSONObject =
			_faroInfoElasticsearchInvoker.fetch(
				"OSBAsahMarkers", "ActivitiesNanite");

		if (activitiesNaniteOSBAsahMarkerJSONObject != null) {
			String lastSuccessfulAnalyticsEventId =
				activitiesNaniteOSBAsahMarkerJSONObject.optString(
					"lastSuccessfulAnalyticsEventId", null);

			if ((lastSuccessfulAnalyticsEventId != null) &&
				((oldestUnprocessedAnalyticsEventId == null) ||
				 (oldestUnprocessedAnalyticsEventId.compareTo(
					 lastSuccessfulAnalyticsEventId) > 0))) {

				oldestUnprocessedAnalyticsEventId =
					lastSuccessfulAnalyticsEventId;
			}
		}

		if (oldestUnprocessedAnalyticsEventId == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Skipping upgrade because there are no " +
						"lastSuccessfulAnalyticsEventIds");
			}

			return;
		}

		QueryBuilder queryBuilder = QueryBuilders.rangeQuery(
			"id"
		).gt(
			oldestUnprocessedAnalyticsEventId
		);

		if (_log.isInfoEnabled()) {
			long count = _cerebroRawElasticsearchInvoker.count(
				"analytics-events", queryBuilder);

			_log.info(
				String.format(
					"Oldest unprocessed analytics event ID: %s. Found %d " +
						"events to process.",
					oldestUnprocessedAnalyticsEventId, count));
		}

		Map<String, String> channelIds = _getChannelIds();

		if (channelIds.isEmpty()) {
			if (_log.isInfoEnabled()) {
				_log.info("Skipping upgrade because there are no channels");
			}

			return;
		}

		_cerebroRawElasticsearchInvoker.updateByQueryWithRetry(
			queryBuilder, false,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				ScriptUtil.loadScriptSource(
					getClass(), "update-channel-id-script.painless"),
				Collections.singletonMap("channelIds", channelIds)),
			"analytics-events");
	}

	private Map<String, String> _getChannelIds() {
		Map<String, String> channelIds = new HashMap<>();

		JSONArray jsonArray = _faroInfoElasticsearchInvoker.get(
			"data-sources", QueryBuilders.existsQuery("channelId"));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			channelIds.put(
				jsonObject.getString("id"), jsonObject.getString("channelId"));
		}

		return channelIds;
	}

	@PostConstruct
	private void _init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
	}

	private static final Log _log = LogFactory.getLog(
		UnprocessedAnalyticsEventsUpgradeStep.class);

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}