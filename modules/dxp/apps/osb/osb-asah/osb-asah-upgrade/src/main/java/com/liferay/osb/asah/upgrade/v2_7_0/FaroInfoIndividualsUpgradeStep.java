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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Rachael Koestartyo
 */
@Component
public class FaroInfoIndividualsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();

		JSONArray channelsJSONArray = elasticsearchInvoker.get("channels");

		Map<String, List<String>> dataSourceIds = new HashMap<>();

		for (int i = 0; i < channelsJSONArray.length(); i++) {
			JSONObject channelJSONObject = channelsJSONArray.getJSONObject(i);

			JSONArray jsonArray = channelJSONObject.optJSONArray("dataSources");

			if (jsonArray != null) {
				dataSourceIds.put(
					channelJSONObject.getString("id"),
					JSONUtil.toStringList(jsonArray, "id"));
			}
		}

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		elasticsearchBulkRequestBuilder.refreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		String script = ScriptUtil.loadScriptSource(
			getClass(), "individuals-channel-id-removal-script.painless");

		for (String channelId :
				JSONUtil.toStringList(channelsJSONArray, "id")) {

			JSONArrayIterator.of(
				"individuals", elasticsearchInvoker,
				individualJSONObject -> elasticsearchBulkRequestBuilder.update(
					"individuals", individualJSONObject.getString("id"),
					new Script(
						Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
						script,
						Collections.singletonMap("channelId", channelId)))
			).setQueryBuilder(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.nestedQuery(
						"activitiesCounts",
						QueryBuilders.termQuery(
							"activitiesCounts.channelId", channelId),
						ScoreMode.None)
				).filter(
					_buildFilterQueryBuilder(
						channelId,
						dataSourceIds.getOrDefault(
							channelId, Collections.emptyList()))
				)
			).iterate();
		}
	}

	private QueryBuilder _buildFilterQueryBuilder(
		String channelId, List<String> dataSourceIds) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.existsQuery("demographics.email")
		).filter(
			QueryBuilders.termQuery("channelIds", channelId)
		);

		if (!dataSourceIds.isEmpty()) {
			boolQueryBuilder.filter(
				QueryBuilders.nestedQuery(
					"dataSourceIndividualPKs",
					QueryBuilders.termsQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceIds),
					ScoreMode.None));
		}

		return boolQueryBuilder;
	}

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}