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

package com.liferay.osb.asah.upgrade.v2_7_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.messaging.Channel;
import com.liferay.osb.asah.common.messaging.MessageBus;
import com.liferay.osb.asah.common.model.AnalyticsEvent;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class UnprocessedAnalyticsEventsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		_upgradeAnalyticsEvents(
			Channel.ANALYTICS_EVENTS_ACTIVITY, _faroInfoElasticsearchInvoker,
			_buildActivityQueryBuilder(), "ActivitiesNanite");
		_upgradeAnalyticsEvents(
			Channel.ANALYTICS_EVENTS_BLOG, _cerebroInfoElasticsearchInvoker,
			_buildBlogQueryBuilder(), "BlogClickNanite",
			"BlogSocialShareNanite", "BlogTrafficSourceNanite");
		_upgradeAnalyticsEvents(
			Channel.ANALYTICS_EVENTS_JOURNAL, _cerebroInfoElasticsearchInvoker,
			_buildJournalClickQueryBuilder(), "JournalClickNanite");
		_upgradeAnalyticsEvents(
			Channel.ANALYTICS_EVENTS_SESSION, _cerebroInfoElasticsearchInvoker,
			null, "SessionNanite");
	}

	private QueryBuilder _buildActivityQueryBuilder() {
		return QueryBuilders.termsQuery(
			"applicationId", "Blog", "Comment", "Document", "Form", "Page",
			"WebContent");
	}

	private QueryBuilder _buildBlogQueryBuilder() {
		return BoolQueryBuilderUtil.should(
			_getBlogClickQueryBuilder()
		).should(
			_getBlogSocialShareQueryBuilder()
		).should(
			_getBlogTrafficSourceQueryBuilder()
		);
	}

	private QueryBuilder _buildJournalClickQueryBuilder() {
		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("applicationId", "WebContent")
		).filter(
			QueryBuilders.termQuery("eventId", "webContentClicked")
		);
	}

	private void _cacheChannelIds() {
		JSONArray jsonArray = _faroInfoElasticsearchInvoker.get(
			"data-sources", QueryBuilders.existsQuery("channelId"));

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			_channelIds.put(
				jsonObject.getString("id"), jsonObject.getString("channelId"));
		}
	}

	private QueryBuilder _getBlogClickQueryBuilder() {
		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("applicationId", "Blog")
		).filter(
			QueryBuilders.termQuery("eventId", "blogClicked")
		);
	}

	private QueryBuilder _getBlogSocialShareQueryBuilder() {
		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("applicationId", "SocialBookmarks")
		).filter(
			QueryBuilders.termQuery("eventId", "shared")
		).filter(
			QueryBuilders.termQuery(
				"eventProperties.className",
				"com.liferay.blogs.model.BlogsEntry")
		);
	}

	private QueryBuilder _getBlogTrafficSourceQueryBuilder() {
		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("applicationId", "Blog")
		).filter(
			QueryBuilders.termQuery("eventId", "blogViewed")
		).filter(
			QueryBuilders.existsQuery("context.referrer")
		);
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
		Channel destinationChannel, ElasticsearchInvoker elasticsearchInvoker,
		QueryBuilder queryBuilder, String... osbAsahMarkerIds) {

		JSONObject osbAsahMarkerJSONObject = null;

		if (osbAsahMarkerIds.length == 1) {
			osbAsahMarkerJSONObject = elasticsearchInvoker.fetch(
				"OSBAsahMarkers", osbAsahMarkerIds[0]);
		}
		else {
			JSONArray jsonArray = new JSONArray(
				elasticsearchInvoker.get(
					"OSBAsahMarkers",
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							QueryBuilders.termsQuery("id", osbAsahMarkerIds));
						searchSourceBuilder.size(1);
						searchSourceBuilder.sort(
							SortBuilderUtil.fieldSort(
								"lastSuccessfulAnalyticsEventId",
								SortOrder.ASC));
					}));

			if (jsonArray.length() == 1) {
				osbAsahMarkerJSONObject = jsonArray.getJSONObject(0);
			}
		}

		if (osbAsahMarkerJSONObject == null) {
			if (_log.isInfoEnabled()) {
				_log.info(
					"Skipping upgrade because there is no OSBAsahMarker with " +
						"ID " + Arrays.toString(osbAsahMarkerIds));
			}

			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"Starting upgrade for OSBAsahMarker with ID " +
					osbAsahMarkerJSONObject.getString("id"));
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

			if (queryBuilder != null) {
				boolQueryBuilder.filter(queryBuilder);
			}

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
						osbAsahMarkerJSONObject.getString("id"),
					e);

				return;
			}

			if (analyticsEvents.isEmpty()) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"There are no more stale analytics events for the " +
							"OSBAsahMarker with ID " +
								osbAsahMarkerJSONObject.getString("id"));
				}

				break;
			}

			for (AnalyticsEvent analyticsEvent : analyticsEvents) {
				if (StringUtils.isEmpty(analyticsEvent.getChannelId())) {
					analyticsEvent.setChannelId(
						_channelIds.get(analyticsEvent.getDataSourceId()));
				}

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

			elasticsearchInvoker.update(
				"OSBAsahMarkers", osbAsahMarkerJSONObject);

			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Upgraded %s analytics events for OSBAsahMarker with " +
							"ID %s, updating lastSuccessfulAnalyticsEventId " +
								"to %s",
						analyticsEvents.size(),
						osbAsahMarkerJSONObject.getString("id"),
						lastAnalyticsEvent.getId()));
			}
		}

		for (String osbAsahMarkerId : osbAsahMarkerIds) {
			elasticsearchInvoker.delete("OSBAsahMarkers", osbAsahMarkerId);
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Successfully completed analytics events upgrade for " +
						"OSBAsahMarker with ID %s ",
					Arrays.toString(osbAsahMarkerIds)));
		}
	}

	private static final Log _log = LogFactory.getLog(
		UnprocessedAnalyticsEventsUpgradeStep.class);

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;
	private final Map<String, String> _channelIds = new HashMap<>();

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private MessageBus _messageBus;

}