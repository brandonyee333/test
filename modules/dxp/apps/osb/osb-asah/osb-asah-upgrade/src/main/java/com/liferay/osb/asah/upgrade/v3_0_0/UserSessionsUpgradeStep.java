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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoActivityDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class UserSessionsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_upgradeUserSessionsIndexMapping();

		_upgradeUserSessionsJSONObjects();
	}

	private Void _upgradeUserSession(JSONArray userSessionJSONArray) {
		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			_cerebroInfoElasticsearchInvoker.
				createElasticsearchBulkRequestBuilder();

		for (int i = 0; i < userSessionJSONArray.length(); i++) {
			JSONObject userSessionJSONObject =
				userSessionJSONArray.getJSONObject(i);

			JSONObject latestActivityJSONObject =
				_faroInfoActivityDog.fetchLatestActivityJSONObject(
					userSessionJSONObject.getString("id"));

			if (latestActivityJSONObject == null) {
				return null;
			}

			JSONObject eventContextJSONObject =
				latestActivityJSONObject.getJSONObject("eventContext");

			userSessionJSONObject.put(
				"contentLanguageId",
				eventContextJSONObject.optString("contentLanguageId", ""));
			userSessionJSONObject.put(
				"devicePixelRatio",
				eventContextJSONObject.optString("devicePixelRatio", ""));
			userSessionJSONObject.put(
				"languageId",
				eventContextJSONObject.optString("languageId", ""));
			userSessionJSONObject.put(
				"screenHeight",
				eventContextJSONObject.optString("screenHeight", ""));
			userSessionJSONObject.put(
				"screenWidth",
				eventContextJSONObject.optString("screenWidth", ""));
			userSessionJSONObject.put(
				"timezoneOffset",
				eventContextJSONObject.optString("timezoneOffset", ""));
			userSessionJSONObject.put(
				"userAgent", eventContextJSONObject.optString("userAgent", ""));

			elasticsearchBulkRequestBuilder.update(
				"user-sessions", userSessionJSONObject);
		}

		if (elasticsearchBulkRequestBuilder.hasActions()) {
			elasticsearchBulkRequestBuilder.get();
		}

		return null;
	}

	private void _upgradeUserSessionsIndexMapping() {
		_elasticsearchIndexManager.updateMapping(
			"user-sessions",
			JSONUtil.put(
				"properties",
				JSONUtil.put(
					"contentLanguageId", JSONUtil.put("type", "keyword")
				).put(
					"devicePixelRatio", JSONUtil.put("type", "keyword")
				).put(
					"finalized", JSONUtil.put("type", "boolean")
				).put(
					"languageId", JSONUtil.put("type", "keyword")
				).put(
					"modifiedDate", JSONUtil.put("type", "date")
				).put(
					"screenHeight", JSONUtil.put("type", "keyword")
				).put(
					"screenWidth", JSONUtil.put("type", "keyword")
				).put(
					"timezoneOffset", JSONUtil.put("type", "keyword")
				).put(
					"userAgent", JSONUtil.put("type", "keyword")
				)
			).toString(),
			"user-sessions", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
	}

	private void _upgradeUserSessionsJSONObjects() throws Exception {
		JSONArrayIterator.of(
			"user-sessions", _cerebroInfoElasticsearchInvoker, null
		).setProcessJSONArrayUnsafeFunction(
			this::_upgradeUserSession
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.existsQuery("contentLanguageId"))
		).iterate();
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private FaroInfoActivityDog _faroInfoActivityDog;

}