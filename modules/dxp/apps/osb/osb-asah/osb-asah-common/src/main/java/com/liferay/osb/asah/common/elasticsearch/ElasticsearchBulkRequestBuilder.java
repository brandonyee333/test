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

package com.liferay.osb.asah.common.elasticsearch;

import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy;

import com.liferay.osb.asah.common.elasticsearch.impl.TimeOrderedUuidGenerator;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.script.Script;

import org.json.JSONObject;

/**
 * @author Rachael Koestartyo
 */
public class ElasticsearchBulkRequestBuilder {

	public static ElasticsearchBulkRequestBuilder create(
		Map<String, String> aliases, Client client, String indexNamespace) {

		return new ElasticsearchBulkRequestBuilder(
			aliases, client, indexNamespace);
	}

	public ElasticsearchBulkRequestBuilder add(
		String collectionName, JSONObject jsonObject) {

		addByIndexName(
			collectionName, getIndexAlias(collectionName), jsonObject);

		return this;
	}

	public ElasticsearchBulkRequestBuilder addByIndexName(
		String collectionName, String indexName, JSONObject jsonObject) {

		String id = jsonObject.optString(
			"id", _timeOrderedUuidGenerator.generateId());

		IndexRequestBuilder indexRequestBuilder = _client.prepareIndex(
			indexName, collectionName, id);

		jsonObject.put("id", id);

		indexRequestBuilder.setSource(jsonObject.toString(), XContentType.JSON);

		_bulkRequestBuilder.add(indexRequestBuilder);

		return this;
	}

	public ElasticsearchBulkRequestBuilder delete(
		String collectionName, String id) {

		DeleteRequestBuilder deleteRequestBuilder = _client.prepareDelete(
			getIndexAlias(collectionName), collectionName, id);

		_bulkRequestBuilder.add(deleteRequestBuilder);

		return this;
	}

	public BulkResponse get() {
		for (int i = _MAX_RETRIES; i >= 0; i--) {
			ClientUtil.waitForConnection(_client);

			BulkResponse bulkResponse = _bulkRequestBuilder.get();

			if (!bulkResponse.hasFailures()) {
				_reset();

				return bulkResponse;
			}

			_log.error(
				"Unable to process bulk operation " +
					bulkResponse.buildFailureMessage());

			if (i == 0) {
				_reset();

				return bulkResponse;
			}

			if (_log.isInfoEnabled()) {
				_log.info("Retrying...");
			}
		}

		_reset();

		return null;
	}

	public String getIndexAlias(String collectionName) {
		return _aliases.getOrDefault(
			ElasticsearchIndexUtil.getIndexName(
				collectionName, _indexNamespace),
			_indexNamespace + "_" + collectionName.toLowerCase());
	}

	public boolean hasActions() {
		if (_bulkRequestBuilder.numberOfActions() > 0) {
			return true;
		}

		return false;
	}

	public ElasticsearchBulkRequestBuilder refreshPolicy(
		RefreshPolicy refreshPolicy) {

		_refreshPolicy = refreshPolicy;

		_bulkRequestBuilder.setRefreshPolicy(refreshPolicy);

		return this;
	}

	public ElasticsearchBulkRequestBuilder replace(
		String collectionName, JSONObject jsonObject) {

		IndexRequestBuilder indexRequestBuilder = _client.prepareIndex(
			getIndexAlias(collectionName), collectionName,
			jsonObject.getString("id"));

		indexRequestBuilder.setSource(jsonObject.toString(), XContentType.JSON);

		_bulkRequestBuilder.add(indexRequestBuilder);

		return this;
	}

	public ElasticsearchBulkRequestBuilder update(
		String collectionName, JSONObject jsonObject) {

		return updateByIndexName(
			collectionName, getIndexAlias(collectionName), jsonObject);
	}

	public ElasticsearchBulkRequestBuilder update(
		String collectionName, String id, Script script) {

		UpdateRequestBuilder updateRequestBuilder = _client.prepareUpdate(
			getIndexAlias(collectionName), collectionName, id);

		updateRequestBuilder.setFetchSource(false);
		updateRequestBuilder.setRetryOnConflict(5);
		updateRequestBuilder.setScript(script);

		_bulkRequestBuilder.add(updateRequestBuilder);

		return this;
	}

	public ElasticsearchBulkRequestBuilder updateByIndexName(
		String collectionName, String indexName, JSONObject jsonObject) {

		UpdateRequestBuilder updateRequestBuilder = _client.prepareUpdate(
			indexName, collectionName, jsonObject.getString("id"));

		updateRequestBuilder.setDoc(jsonObject.toString(), XContentType.JSON);
		updateRequestBuilder.setFetchSource(false);

		_bulkRequestBuilder.add(updateRequestBuilder);

		return this;
	}

	private ElasticsearchBulkRequestBuilder(
		Map<String, String> aliases, Client client, String indexNamespace) {

		_aliases = aliases;
		_client = client;
		_indexNamespace = indexNamespace;

		_reset();
	}

	private void _reset() {
		_bulkRequestBuilder = _client.prepareBulk();

		_bulkRequestBuilder.setRefreshPolicy(_refreshPolicy);
	}

	private static final int _MAX_RETRIES = 3;

	private static final Log _log = LogFactory.getLog(
		ElasticsearchBulkRequestBuilder.class);

	private final Map<String, String> _aliases;
	private BulkRequestBuilder _bulkRequestBuilder;
	private final Client _client;
	private final String _indexNamespace;
	private WriteRequest.RefreshPolicy _refreshPolicy =
		WriteRequest.RefreshPolicy.NONE;
	private TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}