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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.sort.SortOrder;

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

		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();

		JSONArray osbAsahMarkersJSONArray = cerebroInfoElasticsearchInvoker.get(
			"OSBAsahMarkers");

		String oldestAnalyticsEventId = null;

		Set<String> validOSBAsahMarkerIds = _streamCuratorNanites.keySet();

		for (int i = 0; i < osbAsahMarkersJSONArray.length(); i++) {
			JSONObject osbAsahMarkersJSONObject =
				osbAsahMarkersJSONArray.getJSONObject(i);

			String id = osbAsahMarkersJSONObject.getString("id");

			if (!validOSBAsahMarkerIds.contains(id)) {
				continue;
			}

			String lastSuccessfulAnalyticsEventId =
				osbAsahMarkersJSONObject.optString(
					"lastSuccessfulAnalyticsEventId", null);

			if (lastSuccessfulAnalyticsEventId == null) {
				continue;
			}

			String nextAnalyticsEventId = _getNextAnalyticsEventId(
				lastSuccessfulAnalyticsEventId, _streamCuratorNanites.get(id));

			if (nextAnalyticsEventId == null) {
				continue;
			}

			if (oldestAnalyticsEventId == null) {
				oldestAnalyticsEventId = nextAnalyticsEventId;

				continue;
			}

			if (oldestAnalyticsEventId.compareTo(nextAnalyticsEventId) > 0) {
				oldestAnalyticsEventId = nextAnalyticsEventId;
			}
		}

		if (oldestAnalyticsEventId == null) {
			if (_log.isInfoEnabled()) {
				_log.info("Unable to find lastSuccessfulAnalyticsEventIds");
			}

			return;
		}

		QueryBuilder queryBuilder = QueryBuilders.rangeQuery(
			"id"
		).gte(
			oldestAnalyticsEventId
		);

		if (_log.isInfoEnabled()) {
			long count = _cerebroRawElasticsearchInvoker.count(
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

		_cerebroRawElasticsearchInvoker.updateByQueryWithRetry(
			queryBuilder, true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				ScriptUtil.loadScriptSource(
					getClass(), "update-channel-id-script.painless"),
				Collections.singletonMap("channelIds", channelIds)),
			"analytics-events");
	}

	private String _getNextAnalyticsEventId(
		String lastSuccessfulAnalyticsEventId, QueryBuilder queryBuilder) {

		if (queryBuilder == null) {
			return lastSuccessfulAnalyticsEventId;
		}

		JSONArray analyticsEventsJSONArray = new JSONArray(
			_cerebroRawElasticsearchInvoker.get(
				"analytics-events",
				searchSourceBuilder -> {
					searchSourceBuilder.query(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.rangeQuery(
								"id"
							).gt(
								lastSuccessfulAnalyticsEventId
							)
						).filter(
							queryBuilder
						));
					searchSourceBuilder.size(1);
					searchSourceBuilder.sort("id", SortOrder.ASC);
				}));

		if (analyticsEventsJSONArray.length() == 0) {
			return null;
		}

		JSONObject analyticsEventJSONObject =
			analyticsEventsJSONArray.getJSONObject(0);

		return analyticsEventJSONObject.getString("id");
	}

	private static final Log _log = LogFactory.getLog(
		UnprocessedAnalyticsEventsUpgradeStep.class);

	private static final Map<String, QueryBuilder> _streamCuratorNanites =
		new HashMap<String, QueryBuilder>() {
			{
				put(
					"BlogClickNanite",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("applicationId", "Blog")
					).filter(
						QueryBuilders.termQuery("eventId", "blogClicked")
					));
				put(
					"BlogNanite",
					BoolQueryBuilderUtil.should(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Blog"))
					).should(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Comment")
						).filter(
							QueryBuilders.termQuery("eventId", "posted")
						).filter(
							QueryBuilders.termQuery(
								"eventProperties.className",
								"com.liferay.blogs.model.BlogsEntry")
						)
					).should(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Ratings")
						).filter(
							QueryBuilders.termQuery("eventId", "VOTE")
						).filter(
							QueryBuilders.termQuery(
								"eventProperties.className",
								"com.liferay.blogs.model.BlogsEntry")
						).should(
							BoolQueryBuilderUtil.mustNot(
								QueryBuilders.existsQuery(
									"eventProperties.ratingType"))
						).should(
							QueryBuilders.termQuery(
								"eventProperties.ratingType", "stars")
						)
					));
				put(
					"BlogSocialShareNanite",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("applicationId", "shared")
					).filter(
						QueryBuilders.termQuery("eventId", "SocialBookmarks")
					).filter(
						QueryBuilders.termQuery(
							"eventProperties.className",
							"com.liferay.blogs.model.BlogsEntry")
					));
				put(
					"BlogTrafficSourceNanite",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("applicationId", "Blog")
					).filter(
						QueryBuilders.termQuery("eventId", "blogViewed")
					).mustNot(
						QueryBuilders.termQuery("context.referrer", "")
					));
				put("CustomAssetDashboardNanite", null);
				put(
					"CustomAssetNanite",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("applicationId", "Custom")
					).filter(
						QueryBuilders.regexpQuery(
							"eventProperties.assetId", ".+")
					));
				put(
					"DocumentLibraryNanite",
					BoolQueryBuilderUtil.should(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Comment")
						).filter(
							QueryBuilders.termQuery("eventId", "posted")
						).filter(
							QueryBuilders.termQuery(
								"eventProperties.className",
								"com.liferay.document.library.kernel.model." +
									"DLFileEntry")
						)
					).should(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Document")
						).filter(
							QueryBuilders.termQuery(
								"eventId", "documentDownloaded")
						)
					).should(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Document")
						).filter(
							QueryBuilders.termQuery(
								"eventId", "documentPreviewed")
						)
					).should(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Ratings")
						).filter(
							QueryBuilders.termQuery("eventId", "VOTE")
						).filter(
							QueryBuilders.termQuery(
								"eventProperties.className",
								"com.liferay.document.library.kernel.model." +
									"DLFileEntry")
						).should(
							BoolQueryBuilderUtil.mustNot(
								QueryBuilders.existsQuery(
									"eventProperties.ratingType"))
						).should(
							QueryBuilders.termQuery(
								"eventProperties.ratingType", "stars")
						)
					));
				put(
					"FormNanite",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("applicationId", "Form")
					).filter(
						BoolQueryBuilderUtil.should(
							QueryBuilders.termQuery("eventId", "fieldBlurred")
						).should(
							QueryBuilders.termQuery("eventId", "fieldFocused")
						).should(
							QueryBuilders.termQuery("eventId", "formSubmitted")
						).should(
							QueryBuilders.termQuery("eventId", "formViewed")
						).should(
							QueryBuilders.termQuery("eventId", "pageViewed")
						)
					));
				put(
					"JournalClickNanite",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("applicationId", "WebContent")
					).filter(
						QueryBuilders.termQuery("eventId", "webContentClicked")
					));
				put(
					"JournalNanite",
					BoolQueryBuilderUtil.should(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Ratings")
						).filter(
							QueryBuilders.termQuery("eventId", "VOTE")
						).filter(
							QueryBuilders.termQuery(
								"eventProperties.className",
								"com.liferay.journal.model.JournalArticle")
						).should(
							BoolQueryBuilderUtil.mustNot(
								QueryBuilders.existsQuery(
									"eventProperties.ratingType"))
						).should(
							QueryBuilders.termQuery(
								"eventProperties.ratingType", "stars")
						)
					).should(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery(
								"applicationId", "WebContent")
						).filter(
							QueryBuilders.termQuery(
								"eventId", "webContentViewed")
						)
					));
				put(
					"PageNanite",
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.termsQuery(
							"eventId", "blogViewed", "formViewed", "pageLoaded",
							"pageUnloaded", "webContentViewed")
					).mustNot(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.termQuery("applicationId", "Form")
						).filter(
							QueryBuilders.termQuery("eventId", "pageViewed")
						).filter(
							QueryBuilders.termQuery("eventProperties.page", 0)
						)
					));
				put(
					"PageReferrerNanite",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("applicationId", "Page")
					).filter(
						QueryBuilders.termQuery("eventId", "pageViewed")
					));
				put("SessionFinalizerNanite", null);
				put("SessionNanite", null);
			}
		};

	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}