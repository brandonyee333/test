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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		ElasticsearchInvoker cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		JSONArray osbAsahMarkersJSONArray = cerebroInfoElasticsearchInvoker.get(
			"OSBAsahMarkers");

		String oldestAnalyticsEventId = null;

		for (int i = 0; i < osbAsahMarkersJSONArray.length(); i++) {
			JSONObject osbAsahMarkersJSONObject =
				osbAsahMarkersJSONArray.getJSONObject(i);

			String id = osbAsahMarkersJSONObject.getString("id");

			if (!_STREAM_CURATOR_NANITES.contains(id)) {
				continue;
			}

			String lastSuccessfulAnalyticsEventId =
				osbAsahMarkersJSONObject.optString(
					"lastSuccessfulAnalyticsEventId", null);

			if (lastSuccessfulAnalyticsEventId == null) {
				continue;
			}

			if (oldestAnalyticsEventId == null) {
				oldestAnalyticsEventId = lastSuccessfulAnalyticsEventId;

				continue;
			}

			if (oldestAnalyticsEventId.compareTo(
					lastSuccessfulAnalyticsEventId) > 0) {

				oldestAnalyticsEventId = lastSuccessfulAnalyticsEventId;
			}
		}

		if (oldestAnalyticsEventId == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"No lastSuccessfulAnalyticsEventId found." +
						"Skipping Upgrade");
			}

			return;
		}

		ElasticsearchInvoker cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();

		QueryBuilder queryBuilder = QueryBuilders.rangeQuery(
			"id"
		).gt(
			oldestAnalyticsEventId
		);

		if (_log.isInfoEnabled()) {
			long count = cerebroRawElasticsearchInvoker.count(
				"analytics-events", queryBuilder);

			_log.info(
				String.format(
					"Oldest unprocess analytics event ID: %s. Found %d " +
						"events to process.",
					oldestAnalyticsEventId, count));
		}

		ElasticsearchInvoker faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		JSONArray dataSourcesJSONArray = faroInfoElasticsearchInvoker.get(
			"data-sources",
			QueryBuilders.termQuery("provider.type", "LIFERAY"));

		Map<String, String> channelIds = new HashMap<>();

		for (int i = 0; i < dataSourcesJSONArray.length(); i++) {
			JSONObject dataSourceJSONObject =
				dataSourcesJSONArray.getJSONObject(i);

			channelIds.put(
				dataSourceJSONObject.getString("id"),
				dataSourceJSONObject.getString("channelId"));
		}

		cerebroRawElasticsearchInvoker.updateByQueryWithRetry(
			queryBuilder, true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				ScriptUtil.loadScriptSource(
					getClass(), "update-channel-id-script.painless"),
				Collections.singletonMap("channelIds", channelIds)),
			"analytics-events");
	}

	private static final List<String> _STREAM_CURATOR_NANITES =
		new ArrayList<String>() {
			{
				add("BlogClickNanite");
				add("BlogNanite");
				add("BlogSocialShareNanite");
				add("BlogTrafficSourceNanite");
				add("CustomAssetDashboardNanite");
				add("CustomAssetNanite");
				add("DocumentLibraryNanite");
				add("FormNanite");
				add("IndividualNanite");
				add("JournalClickNanite");
				add("JournalNanite");
				add("PageNanite");
				add("PageReferrerNanite");
				add("SessionFinalizerNanite");
				add("SessionNanite");
			}
		};

	private static final Log _log = LogFactory.getLog(
		UnprocessedAnalyticsEventsUpgradeStep.class);

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}