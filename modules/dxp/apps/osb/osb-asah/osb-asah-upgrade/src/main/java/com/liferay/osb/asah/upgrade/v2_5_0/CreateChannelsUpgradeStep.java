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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoChannelDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.search.join.ScoreMode;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class CreateChannelsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
		_faroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forFaroInfo();
		_updateChannelIdsScriptSource = ScriptUtil.loadScriptSource(
			getClass(), "update-channel-ids-script.painless");

		JSONArrayIterator.of(
			"data-sources", _faroInfoElasticsearchInvoker, this::_createChannel
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.existsQuery("channelId")
			).filter(
				QueryBuilders.termQuery("provider.type", "LIFERAY")
			)
		).setStopOnExceptions(
			true
		).iterate();
	}

	private JSONObject _createChannel(JSONObject dataSourceJSONObject)
		throws Exception {

		String dataSourceId = dataSourceJSONObject.getString("id");
		String dataSourceName = dataSourceJSONObject.getString("name");

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Creating channel for data source: %s - %s", dataSourceId,
					dataSourceName));
		}

		JSONObject channelJSONObject = _faroInfoChannelDog.addChannel(
			Collections.singletonList(
				JSONUtil.put(
					"groupIds", Collections.emptyList()
				).put(
					"id", dataSourceId
				)),
			dataSourceName, true);

		_updateIndices(channelJSONObject.getString("id"), dataSourceId);

		_updateDataSource(
			channelJSONObject.getString("id"), dataSourceJSONObject);

		return channelJSONObject;
	}

	private void _updateChannelIds(
			String channelId, String collectionName, QueryBuilder queryBuilder)
		throws Exception {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.mustNot(
			QueryBuilders.termQuery("channelIds", channelId)
		).filter(
			queryBuilder
		);

		Script script = new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			_updateChannelIdsScriptSource,
			Collections.singletonMap("channelId", channelId));

		_updateCollections(
			boolQueryBuilder, new String[] {collectionName},
			_faroInfoElasticsearchInvoker, script);
	}

	private void _updateCollections(
			BoolQueryBuilder boolQueryBuilder, String[] collectionNames,
			ElasticsearchInvoker elasticsearchInvoker, Script script)
		throws Exception {

		ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
			elasticsearchInvoker.createElasticsearchBulkRequestBuilder();

		for (String collectionName : collectionNames) {
			JSONArrayIterator.of(
				collectionName, elasticsearchInvoker,
				jsonObject -> elasticsearchBulkRequestBuilder.update(
					collectionName, jsonObject.getString("id"), script)
			).setQueryBuilder(
				boolQueryBuilder
			).setSourceIncludes(
				"id"
			).iterate();
		}
	}

	private void _updateDataSource(
		String channelId, JSONObject dataSourceJSONObject) {

		dataSourceJSONObject.put("channelId", channelId);

		_faroInfoElasticsearchInvoker.update(
			"data-sources", dataSourceJSONObject);
	}

	private void _updateIndices(String channelId, String dataSourceId)
		throws Exception {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.mustNot(
			QueryBuilders.existsQuery("channelId")
		).filter(
			QueryBuilders.termQuery("dataSourceId", dataSourceId)
		);

		Script script = new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			"ctx._source.channelId = params.channelId;",
			Collections.singletonMap("channelId", channelId));

		_updateCollections(
			boolQueryBuilder, _CEREBRO_INFO_COLLECTION_NAMES,
			_cerebroInfoElasticsearchInvoker, script);
		_updateCollections(
			boolQueryBuilder, _FARO_INFO_COLLECTION_NAMES,
			_faroInfoElasticsearchInvoker, script);

		_updateChannelIds(
			channelId, "assets",
			QueryBuilders.termQuery("dataSourceId", dataSourceId));
		_updateChannelIds(
			channelId, "individuals",
			QueryBuilders.nestedQuery(
				"dataSourceIndividualPKs",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceIndividualPKs.dataSourceId", dataSourceId)),
				ScoreMode.None));
	}

	private static final String[] _CEREBRO_INFO_COLLECTION_NAMES = {
		"blog-clicks", "blog-social-shares", "blog-traffic-sources", "blogs",
		"custom-assets", "custom-asset-dashboards", "document-libraries",
		"forms", "journal-clicks", "journals", "page-referrers", "pages",
		"user-sessions"
	};

	private static final String[] _FARO_INFO_COLLECTION_NAMES = {
		"activities", "activity-groups", "experiments"
	};

	private static final Log _log = LogFactory.getLog(
		CreateChannelsUpgradeStep.class);

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private FaroInfoChannelDog _faroInfoChannelDog;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;
	private String _updateChannelIdsScriptSource;

}