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

package com.liferay.osb.asah.extractor.rest.controller;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.extractor.processor.AnalyticsEventsChannels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcellus Tavares
 */
@RequestMapping(produces = "application/json")
@RestController
public class UpgradeRestController {

	@PostMapping("/upgrade")
	public void upgrade() {
		_upgradeAnalyticsEvents(Channel.ANALYTICS_EVENTS_BLOG, "BlogNanite");
		_upgradeAnalyticsEvents(
			Channel.ANALYTICS_EVENTS_CUSTOM_ASSET,
			"CustomAssetDashboardNanite");
		_upgradeAnalyticsEvents(
			Channel.ANALYTICS_EVENTS_DOCUMENT, "DocumentLibraryNanite");
		_upgradeAnalyticsEvents(Channel.ANALYTICS_EVENTS_FORM, "FormNanite");
		_upgradeAnalyticsEvents(
			Channel.ANALYTICS_EVENTS_JOURNAL, "JournalNanite");
		_upgradeAnalyticsEvents(Channel.ANALYTICS_EVENTS_PAGE, "PageNanite");
	}

	private void _cacheChannelIds() {

		// Only data sources created before 2.5.0 contain channel ID

		JSONArray jsonArray = _faroInfoElasticsearchInvoker.get(
			"data-sources", QueryBuilders.existsQuery("channelId"));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_channelIds.put(
				jsonObject.getString("id"), jsonObject.getString("channelId"));
		}
	}

	@PostConstruct
	private void _init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		_cacheChannelIds();
	}

	private void _upgradeAnalyticsEvents(
		Channel destinationChannel, String osbAsahMakerId) {

		JSONObject osbAsahMarkerJSONObject =
			_cerebroInfoElasticsearchInvoker.fetch(
				"OSBAsahMarkers", osbAsahMakerId);

		if (osbAsahMarkerJSONObject == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Skipping upgrade because there is no OSBAsahMarker with " +
						"ID " + osbAsahMakerId);
			}

			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"Starting upgrade for OSBAsahMarker with ID " + osbAsahMakerId);
		}

		while (true) {
			String lastSuccessfulAnalyticsEventId =
				osbAsahMarkerJSONObject.optString(
					"lastSuccessfulAnalyticsEventId", null);

			if (lastSuccessfulAnalyticsEventId == null) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Skipping upgrade because " +
							"lastSuccessfulAnalyticsEventId is null");
				}

				break;
			}

			BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
				QueryBuilders.rangeQuery(
					"id"
				).gt(
					lastSuccessfulAnalyticsEventId
				));

			String analyticsEventsJSON = _cerebroRawElasticsearchInvoker.get(
				"analytics-events",
				searchSourceBuilder -> {
					searchSourceBuilder.query(boolQueryBuilder);
					searchSourceBuilder.size(100);
					searchSourceBuilder.sort("id");
				});

			List<AnalyticsEvent> analyticsEvents = null;

			try {
				analyticsEvents = AnalyticsEvent.toAnalyticsEvents(
					analyticsEventsJSON);
			}
			catch (Exception e) {
				_log.error(
					"Aborting upgrade process for OSBAsahMarker with ID " +
						osbAsahMakerId,
					e);

				return;
			}

			if (analyticsEvents.isEmpty()) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"There are no more stale analytics events for the " +
							"OSBAsahMarker with ID " + osbAsahMakerId);
				}

				break;
			}

			for (AnalyticsEvent analyticsEvent : analyticsEvents) {
				List<Channel> channels = _analyticsEventsChannels.getChannels(
					analyticsEvent);

				if (!channels.contains(destinationChannel)) {
					continue;
				}

				analyticsEvent.setChannelId(
					_channelIds.get(analyticsEvent.getDataSourceId()));

				if (_log.isDebugEnabled()) {
					_log.debug(
						String.format(
							"Sending analytics event message %s to channel %s",
							analyticsEvent.toJSON(), destinationChannel));
				}

				_messageBus.sendMessage(
					destinationChannel, analyticsEvent.toJSON());
			}

			AnalyticsEvent lastAnalyticsEvent = analyticsEvents.get(
				analyticsEvents.size() - 1);

			osbAsahMarkerJSONObject.put(
				"lastSuccessfulAnalyticsEventId", lastAnalyticsEvent.getId());

			_cerebroInfoElasticsearchInvoker.update(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Upgraded %s analytics events for OSBAsahMarker with " +
							"ID %s, updating lastSuccessfulAnalyticsEventId " +
								"to %s",
						analyticsEvents.size(), osbAsahMakerId,
						lastAnalyticsEvent.getId()));
			}
		}

		_cerebroInfoElasticsearchInvoker.delete(
			"OSBAsahMarkers", osbAsahMakerId);

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Successfully completed analytics events upgrade for " +
						"OSBAsahMarker with ID %s ",
					osbAsahMakerId));
		}
	}

	private static final Log _log = LogFactory.getLog(
		UpgradeRestController.class);

	@Autowired
	private AnalyticsEventsChannels _analyticsEventsChannels;

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;
	private final Map<String, String> _channelIds = new HashMap<>();

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private MessageBus _messageBus;

}