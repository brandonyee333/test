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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

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

	private Void _upgradeUserSession(JSONObject userSessionJSONObject) {
		JSONObject lastActivityJSONObject = _faroInfoElasticsearchInvoker.fetch(
			"activities",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.existsQuery("eventContext")
			).filter(
				QueryBuilders.termQuery(
					"sessionId", userSessionJSONObject.getString("id"))
			),
			SortBuilderUtil.fieldSort("id", SortOrder.DESC), null,
			"eventContext");

		if (lastActivityJSONObject == null) {
			return null;
		}

		JSONObject eventContextJSONObject =
			lastActivityJSONObject.getJSONObject("eventContext");

		userSessionJSONObject.put(
			"contentLanguageId",
			eventContextJSONObject.optString("contentLanguageId", ""));
		userSessionJSONObject.put(
			"languageId", eventContextJSONObject.optString("languageId", ""));
		userSessionJSONObject.put(
			"timezoneOffset",
			eventContextJSONObject.optString("timezoneOffset", ""));

		_cerebroInfoElasticsearchInvoker.update(
			"user-sessions", userSessionJSONObject);

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
					"languageId", JSONUtil.put("type", "keyword")
				).put(
					"timezoneOffset", JSONUtil.put("type", "keyword")
				)
			).toString(),
			"user-sessions", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
	}

	private void _upgradeUserSessionsJSONObjects() throws Exception {
		JSONArrayIterator.of(
			"user-sessions", _cerebroInfoElasticsearchInvoker,
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

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}