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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.elasticsearch.ScriptUtil;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoChannelDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.ReindexHelper;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

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
		_updateChannelIdScriptSource = ScriptUtil.loadScriptSource(
			getClass(), "update-channel-id-script.painless");

		_addMappingField(
			"assets", "channelIds", WeDeployDataService.OSB_ASAH_FARO_INFO);
		_addMappingField(
			"analytics-events", WeDeployDataService.OSB_ASAH_CEREBRO_RAW);
		_addMappingField(
			"data-sources", WeDeployDataService.OSB_ASAH_FARO_INFO);
		_addMappingField(
			"individual-segments", WeDeployDataService.OSB_ASAH_FARO_INFO);
		_addMappingField(
			"individuals", "channelIds",
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		Map<String, String> channelIds = new HashMap<>();

		JSONArrayIterator.of(
			"data-sources", _faroInfoElasticsearchInvoker,
			dataSourceJSONObject -> {
				JSONObject channelJSONObject = _createChannel(
					dataSourceJSONObject);

				String channelId = channelJSONObject.getString("id");

				dataSourceJSONObject.put("channelId", channelId);

				_faroInfoElasticsearchInvoker.update(
					"data-sources", dataSourceJSONObject);

				channelIds.put(dataSourceJSONObject.getString("id"), channelId);

				return null;
			}
		).setQueryBuilder(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.existsQuery("channelId")
			).filter(
				QueryBuilders.termQuery("provider.type", "LIFERAY")
			)
		).setStopOnExceptions(
			true
		).iterate();

		Map<String, Object> params = Collections.singletonMap(
			"channelIds", channelIds);

		_reindex(
			params, _updateChannelIdScriptSource,
			WeDeployDataService.OSB_ASAH_CEREBRO_INFO, version,
			_CEREBRO_INFO_COLLECTION_NAMES);

		_reindex(
			params, _updateChannelIdScriptSource,
			WeDeployDataService.OSB_ASAH_FARO_INFO, version,
			_FARO_INFO_COLLECTION_NAMES);

		_reindex(
			params,
			ScriptUtil.loadScriptSource(
				getClass(), "update-asset-channel-ids-script.painless"),
			WeDeployDataService.OSB_ASAH_FARO_INFO, version, "assets");

		_reindex(
			params,
			ScriptUtil.loadScriptSource(
				getClass(), "update-individual-channel-ids-script.painless"),
			WeDeployDataService.OSB_ASAH_FARO_INFO, version, "individuals");
	}

	private void _addMappingField(
		String indexName, String propertyName,
		WeDeployDataService weDeployDataService) {

		_elasticsearchIndexManager.updateMapping(
			_elasticsearchIndexManager.getIndexName(
				indexName, weDeployDataService),
			JSONUtil.put(
				"properties",
				JSONUtil.put(propertyName, JSONUtil.put("type", "keyword"))
			).toString(),
			indexName);
	}

	private void _addMappingField(
		String indexName, WeDeployDataService weDeployDataService) {

		_addMappingField(indexName, "channelId", weDeployDataService);
	}

	private JSONObject _createChannel(JSONObject dataSourceJSONObject) {
		String dataSourceId = dataSourceJSONObject.getString("id");
		String dataSourceName = dataSourceJSONObject.getString("name");

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Creating channel for data source: %s - %s", dataSourceId,
					dataSourceName));
		}

		return _faroInfoChannelDog.addChannel(
			Collections.singletonList(
				JSONUtil.put(
					"groupIds", Collections.emptyList()
				).put(
					"id", dataSourceId
				)),
			dataSourceName, true);
	}

	private void _reindex(
			Map<String, Object> params, String script,
			WeDeployDataService weDeployDataService, String version,
			String... collectionNames)
		throws Exception {

		for (String collectionName : collectionNames) {
			String indexAlias = _reindexHelper.getIndexAlias(
				collectionName, weDeployDataService);

			if (_log.isInfoEnabled()) {
				_log.info("Index alias: " + indexAlias);
			}

			String baseIndexName = _reindexHelper.getBaseIndexName(
				collectionName, weDeployDataService);

			String newIndexName = _reindexHelper.getNewIndexName(
				baseIndexName, version);

			if (_elasticsearchIndexManager.aliasExists(indexAlias) &&
				_elasticsearchIndexManager.exists(newIndexName) &&
				Objects.equals(
					_elasticsearchIndexManager.getIndexName(indexAlias),
					newIndexName)) {

				if (_log.isInfoEnabled()) {
					_log.info(
						"Skipped reindexing as " + newIndexName +
							" exists and has the expected alias");
				}

				continue;
			}

			newIndexName = _reindexHelper.createIndex(
				indexAlias,
				_elasticsearchIndexManager.readIndexConfiguration(
					collectionName, weDeployDataService),
				baseIndexName, version);

			if (_log.isInfoEnabled()) {
				_log.info("New index created: " + newIndexName);
			}

			String oldIndexName = _elasticsearchIndexManager.getIndexName(
				indexAlias);

			if (_elasticsearchIndexManager.exists(oldIndexName)) {
				if (_log.isInfoEnabled()) {
					_log.info("Old index name: " + oldIndexName);
				}

				_reindexHelper.reindex(
					newIndexName, params, script, oldIndexName);

				_reindexHelper.refreshIndex(newIndexName);

				_reindexHelper.reassignAlias(
					indexAlias, newIndexName, oldIndexName);

				_reindexHelper.deleteIndex(oldIndexName);
			}
			else {
				_reindexHelper.reassignAlias(indexAlias, newIndexName, null);
			}

			if (_log.isInfoEnabled()) {
				_log.info("Finished reindexing " + collectionName);
			}
		}
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
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	@Autowired
	private FaroInfoChannelDog _faroInfoChannelDog;

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ReindexHelper _reindexHelper;

	private String _updateChannelIdScriptSource;

}