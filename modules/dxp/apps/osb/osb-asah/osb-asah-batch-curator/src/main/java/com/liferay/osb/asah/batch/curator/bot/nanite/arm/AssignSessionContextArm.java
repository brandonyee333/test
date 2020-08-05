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

package com.liferay.osb.asah.batch.curator.bot.nanite.arm;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class AssignSessionContextArm {

	public void execute() throws Exception {
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_cerebroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		JSONArrayIterator.of(
			"user-sessions", _cerebroInfoElasticsearchInvoker,
			userSessionJSONObject -> {
				_updateUserSession(
					elasticsearchBulkRequestBuilder, userSessionJSONObject);

				return elasticsearchBulkRequestBuilder;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.existsQuery("canonicalUrls")
			).filter(
				QueryBuilders.existsQuery("channelId")
			).filter(
				QueryBuilders.existsQuery("dataSourceId")
			).mustNot(
				QueryBuilders.existsQuery("deviceType")
			).filter(
				QueryBuilders.existsQuery("firstEventDate")
			).filter(
				QueryBuilders.existsQuery("id")
			).filter(
				QueryBuilders.existsQuery("lastEventDate")
			).mustNot(
				QueryBuilders.existsQuery("referrers")
			).mustNot(
				QueryBuilders.existsQuery("urls")
			).filter(
				QueryBuilders.existsQuery("userId")
			)
		).setSourceIncludes(
			new String[] {
				"channelId", "dataSourceId", "firstEventDate", "id",
				"lastEventDate", "userId"
			}
		).iterate();
	}

	private String _getDeviceType(JSONObject userSessionJSONObject) {
		JSONObject analyticsEventJSONObject =
			_cerebroRawElasticsearchInvoker.fetch(
				"analytics-events",
				BoolQueryBuilderUtil.filter(
					_getQueryBuilder(userSessionJSONObject)
				).filter(
					QueryBuilders.existsQuery("context.deviceType")
				));

		if (analyticsEventJSONObject == null) {
			return null;
		}

		JSONObject contextJSONObject = analyticsEventJSONObject.getJSONObject(
			"context");

		return contextJSONObject.getString("deviceType");
	}

	private QueryBuilder _getQueryBuilder(JSONObject userSessionJSONObject) {
		return BoolQueryBuilderUtil.filter(
			BoolQueryBuilderUtil.should(
				QueryBuilders.termQuery(
					"channelId", userSessionJSONObject.getString("channelId"))
			).should(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.existsQuery("channelId"))
			)
		).filter(
			QueryBuilders.termQuery(
				"dataSourceId", userSessionJSONObject.getString("dataSourceId"))
		).filter(
			QueryBuilders.termQuery(
				"userId", userSessionJSONObject.getString("userId"))
		).filter(
			QueryBuilders.rangeQuery(
				"eventDate"
			).gte(
				userSessionJSONObject.getString("firstEventDate")
			).lte(
				userSessionJSONObject.getString("lastEventDate")
			)
		);
	}

	@PostConstruct
	private void _init() {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_cerebroRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroRaw();
	}

	private void _updateUserSession(
			ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder,
			JSONObject userSessionJSONObject)
		throws Exception {

		Set<String> canonicalUrls = new HashSet<>();
		Set<String> referrers = new HashSet<>();
		Set<String> urls = new HashSet<>();

		JSONArrayIterator.of(
			"analytics-events", _cerebroRawElasticsearchInvoker,
			analyticsEventsJSONObject -> {
				JSONObject contextJSONObject =
					analyticsEventsJSONObject.optJSONObject("context");

				if (contextJSONObject != null) {
					String canonicalUrl = contextJSONObject.optString(
						"canonicalUrl");

					if (!StringUtil.isNull(canonicalUrl)) {
						canonicalUrls.add(canonicalUrl);
					}

					String referrer = contextJSONObject.optString("referrer");

					if (!StringUtil.isNull(referrer)) {
						referrers.add(referrer);
					}

					String url = contextJSONObject.optString("url");

					if (!StringUtil.isNull(url)) {
						urls.add(url);
					}
				}

				return null;
			}
		).setQueryBuilder(
			_getQueryBuilder(userSessionJSONObject)
		).iterate();

		elasticsearchBulkRequestBuilder.update(
			"user-sessions",
			JSONUtil.put(
				"canonicalUrls", canonicalUrls
			).put(
				"deviceType", _getDeviceType(userSessionJSONObject)
			).put(
				"id", userSessionJSONObject.getString("id")
			).put(
				"referrers", referrers
			).put(
				"urls", urls
			));
	}

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;
	private ElasticsearchInvoker _cerebroRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}