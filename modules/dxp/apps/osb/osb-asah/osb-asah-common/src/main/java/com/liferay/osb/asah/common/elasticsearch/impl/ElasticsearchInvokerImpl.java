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

package com.liferay.osb.asah.common.elasticsearch.impl;

import com.liferay.osb.asah.common.elasticsearch.ClientUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchAliases;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.HitsUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.ResourceNotFoundException;
import org.elasticsearch.action.admin.indices.refresh.RefreshAction;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequestBuilder;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.search.MultiSearchRequestBuilder;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.IndexNotFoundException;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.BulkByScrollTask;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.collapse.CollapseBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 * @author André Miranda
 */
public class ElasticsearchInvokerImpl implements ElasticsearchInvoker {

	public ElasticsearchInvokerImpl(
		Client client, ElasticsearchAliases elasticsearchAliases,
		WeDeployDataService weDeployDataService) {

		_client = client;
		_elasticsearchAliases = elasticsearchAliases;
		_weDeployDataService = weDeployDataService;
	}

	@Override
	public boolean add(String collectionName, JSONArray jsonArray) {
		if ((jsonArray == null) || (jsonArray.length() == 0)) {
			return false;
		}

		BulkRequestBuilder bulkRequestBuilder = _client.prepareBulk();

		bulkRequestBuilder.setRefreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		String indexAlias = _elasticsearchAliases.check(collectionName);

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			jsonObject = new JSONObject(jsonObject.toString());

			String id = null;

			if (jsonObject.has("id") && (jsonObject.get("id") != null)) {
				id = String.valueOf(jsonObject.get("id"));
			}
			else {
				id = _timeOrderedUuidGenerator.generateId();
			}

			IndexRequestBuilder indexRequestBuilder = _client.prepareIndex(
				indexAlias, collectionName, id);

			jsonObject.put("id", id);

			indexRequestBuilder.setSource(
				jsonObject.toString(), XContentType.JSON);

			bulkRequestBuilder.add(indexRequestBuilder);
		}

		ClientUtil.waitForConnection(_client);

		BulkResponse bulkResponse = bulkRequestBuilder.get();

		if (bulkResponse.hasFailures()) {
			_log.error(bulkResponse.buildFailureMessage());

			return false;
		}

		return true;
	}

	@Override
	public boolean add(String collectionName, JSONArray jsonArray, int delta) {
		List<Object> list = jsonArray.toList();

		for (int i = 0; i < jsonArray.length(); i += delta) {
			int end = Math.min(i + delta, jsonArray.length());

			if (!add(collectionName, new JSONArray(list.subList(i, end)))) {
				return false;
			}
		}

		return true;
	}

	@Override
	public JSONObject add(String collectionName, JSONObject jsonObject) {
		return add(collectionName, jsonObject, true);
	}

	@Override
	public JSONObject add(
		String collectionName, JSONObject jsonObject, boolean refresh) {

		if (jsonObject == null) {
			return null;
		}

		jsonObject = new JSONObject(jsonObject.toString());

		String id = jsonObject.optString(
			"id", _timeOrderedUuidGenerator.generateId());

		jsonObject.put("id", id);

		IndexRequestBuilder indexRequestBuilder = _client.prepareIndex(
			_elasticsearchAliases.check(collectionName), collectionName, id);

		if (refresh) {
			indexRequestBuilder.setRefreshPolicy(
				WriteRequest.RefreshPolicy.IMMEDIATE);
		}

		indexRequestBuilder.setSource(jsonObject.toString(), XContentType.JSON);

		ClientUtil.waitForConnection(_client);

		indexRequestBuilder.get();

		return jsonObject;
	}

	@Override
	public long count(String collectionName, QueryBuilder queryBuilder) {
		SearchRequestBuilder searchRequestBuilder = _prepareSearch(
			_elasticsearchAliases.get(collectionName));

		searchRequestBuilder.setQuery(queryBuilder);
		searchRequestBuilder.setSize(0);
		searchRequestBuilder.setTrackTotalHits(true);

		ClientUtil.waitForConnection(_client);

		SearchResponse searchResponse = searchRequestBuilder.get();

		return HitsUtil.getTotalHitsCount(searchResponse.getHits());
	}

	@Override
	public ElasticsearchBulkRequestBuilder
		createElasticsearchBulkRequestBuilder() {

		return ElasticsearchBulkRequestBuilder.create(
			_client, _elasticsearchAliases,
			ElasticsearchIndexUtil.getIndexNamespace(_weDeployDataService));
	}

	@Override
	public void delete(String collectionName, QueryBuilder queryBuilder) {
		try {
			DeleteByQueryRequestBuilder deleteByQueryRequestBuilder =
				new DeleteByQueryRequestBuilder(
					_client, DeleteByQueryAction.INSTANCE);

			deleteByQueryRequestBuilder = deleteByQueryRequestBuilder.filter(
				queryBuilder);

			deleteByQueryRequestBuilder.refresh(true);
			deleteByQueryRequestBuilder.source(
				_elasticsearchAliases.get(collectionName));

			ClientUtil.waitForConnection(_client);

			deleteByQueryRequestBuilder.get();
		}
		catch (IndexNotFoundException indexNotFoundException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Index does not exist for collection " + collectionName);
			}
		}
	}

	@Override
	public boolean delete(String collectionName, String id) {
		DeleteRequestBuilder deleteRequestBuilder = _client.prepareDelete(
			_elasticsearchAliases.get(collectionName), collectionName, id);

		deleteRequestBuilder.setRefreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);

		ClientUtil.waitForConnection(_client);

		DeleteResponse deleteResponse = deleteRequestBuilder.get();

		RestStatus restStatus = deleteResponse.status();

		if (restStatus == RestStatus.OK) {
			return true;
		}

		return false;
	}

	@Override
	public BulkByScrollResponse deleteByQuery(
		QueryBuilder queryBuilder, boolean refresh, String... collectionNames) {

		DeleteByQueryRequestBuilder deleteByQueryRequestBuilder =
			new DeleteByQueryRequestBuilder(
				_client, DeleteByQueryAction.INSTANCE);

		deleteByQueryRequestBuilder.abortOnVersionConflict(false);
		deleteByQueryRequestBuilder.filter(queryBuilder);
		deleteByQueryRequestBuilder.refresh(refresh);

		List<String> indices = _getExistingIndices(
			queryBuilder, collectionNames);

		if (indices.isEmpty()) {
			return new BulkByScrollResponse(
				TimeValue.ZERO,
				new BulkByScrollTask.Status(
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, TimeValue.ZERO, 0F, null,
					TimeValue.ZERO),
				Collections.emptyList(), Collections.emptyList(), false);
		}

		deleteByQueryRequestBuilder.source(indices.toArray(new String[0]));

		ClientUtil.waitForConnection(_client);

		return deleteByQueryRequestBuilder.get();
	}

	@Override
	public boolean exists(String collectionName, QueryBuilder queryBuilder) {
		if (count(collectionName, queryBuilder) > 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean exists(String collectionName, String id) {
		try {
			GetRequestBuilder getRequestBuilder = _client.prepareGet(
				_elasticsearchAliases.get(collectionName), null, id);

			getRequestBuilder.setFetchSource(false);
			getRequestBuilder.setStoredFields("_none_");

			ClientUtil.waitForConnection(_client);

			GetResponse getResponse = getRequestBuilder.get();

			return getResponse.isExists();
		}
		catch (IndexNotFoundException indexNotFoundException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Index does not exist for collection " + collectionName);
			}

			return false;
		}
	}

	@Override
	public JSONObject fetch(String collectionName, QueryBuilder queryBuilder) {
		return fetch(collectionName, queryBuilder, null, null, null);
	}

	@Override
	public JSONObject fetch(
		String collectionName, QueryBuilder queryBuilder,
		SortBuilder<?> sortBuilder, String sourceExclude,
		String sourceInclude) {

		SearchRequestBuilder searchRequestBuilder = _prepareSearch(
			_elasticsearchAliases.get(collectionName));

		if (sortBuilder != null) {
			searchRequestBuilder.addSort(sortBuilder);
		}

		searchRequestBuilder.setFetchSource(sourceInclude, sourceExclude);
		searchRequestBuilder.setQuery(queryBuilder);
		searchRequestBuilder.setSize(1);

		ClientUtil.waitForConnection(_client);

		SearchResponse searchResponse = searchRequestBuilder.get();

		SearchHits searchHits = searchResponse.getHits();

		if (!HitsUtil.hasHits(searchHits)) {
			return null;
		}

		SearchHit searchHit = searchHits.getAt(0);

		return new JSONObject(searchHit.getSourceAsString());
	}

	@Override
	public JSONObject fetch(String collectionName, String id) {
		try {
			GetRequestBuilder getRequestBuilder = _client.prepareGet(
				_elasticsearchAliases.get(collectionName), null, id);

			ClientUtil.waitForConnection(_client);

			GetResponse getResponse = getRequestBuilder.get();

			if (!getResponse.isExists()) {
				return null;
			}

			return new JSONObject(getResponse.getSourceAsString());
		}
		catch (IndexNotFoundException indexNotFoundException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Index does not exist for collection " + collectionName);
			}

			return null;
		}
	}

	@Override
	public JSONArray get(
		CollapseBuilder collapseBuilder, String collectionName,
		List<FieldSortBuilder> fieldSortBuilders, QueryBuilder queryBuilder) {

		return _getAll(
			collapseBuilder, collectionName, null, queryBuilder,
			fieldSortBuilders.toArray(new FieldSortBuilder[0]));
	}

	@Override
	public JSONArray get(String collectionName) {
		return _getAll(
			null, collectionName, null, null, SortBuilderUtil.fieldSort("id"));
	}

	@Override
	public JSONArray get(
		String collectionName, FieldSortBuilder fieldSortBuilder, int size) {

		return _get(collectionName, null, null, null, size, fieldSortBuilder);
	}

	public JSONArray get(
		String collectionName, FieldSortBuilder fieldSortBuilder,
		QueryBuilder queryBuilder, int size) {

		return _get(
			collectionName, null, null, queryBuilder, size, fieldSortBuilder);
	}

	@Override
	public JSONArray get(
		String collectionName, FieldSortBuilder fieldSortBuilder,
		String[] includes, QueryBuilder queryBuilder, int size) {

		return _get(
			collectionName, null, includes, queryBuilder, size,
			fieldSortBuilder);
	}

	@Override
	public JSONArray get(
		String collectionName, int from, QueryBuilder queryBuilder, int size) {

		return _get(
			collectionName, from, null, queryBuilder, size,
			SortBuilderUtil.fieldSort("id"));
	}

	@Override
	public JSONArray get(
		String collectionName, List<FieldSortBuilder> fieldSortBuilders) {

		return _getAll(
			null, collectionName, null, null,
			fieldSortBuilders.toArray(new FieldSortBuilder[0]));
	}

	@Override
	public JSONArray get(
		String collectionName, List<FieldSortBuilder> fieldSortBuilders,
		int from, int size) {

		return _get(
			collectionName, from, null, null, size,
			fieldSortBuilders.toArray(new FieldSortBuilder[0]));
	}

	@Override
	public JSONArray get(
		String collectionName, List<FieldSortBuilder> fieldSortBuilders,
		int from, QueryBuilder queryBuilder, int size) {

		return _get(
			collectionName, from, null, queryBuilder, size,
			fieldSortBuilders.toArray(new FieldSortBuilder[0]));
	}

	@Override
	public JSONArray get(
		String collectionName, List<FieldSortBuilder> fieldSortBuilders,
		QueryBuilder queryBuilder) {

		return _getAll(
			null, collectionName, null, queryBuilder,
			fieldSortBuilders.toArray(new FieldSortBuilder[0]));
	}

	@Override
	public JSONArray get(
		String collectionName, List<FieldSortBuilder> fieldSortBuilders,
		String[] includes) {

		return _getAll(
			null, collectionName, includes, null,
			fieldSortBuilders.toArray(new FieldSortBuilder[0]));
	}

	@Override
	public JSONArray get(
		String collectionName, List<FieldSortBuilder> fieldSortBuilders,
		String[] includes, QueryBuilder queryBuilder) {

		return _getAll(
			null, collectionName, includes, queryBuilder,
			fieldSortBuilders.toArray(new FieldSortBuilder[0]));
	}

	@Override
	public JSONArray get(String collectionName, QueryBuilder queryBuilder) {
		return _getAll(
			null, collectionName, null, queryBuilder,
			SortBuilderUtil.fieldSort("id"));
	}

	@Override
	public JSONArray get(
		String collectionName, QueryBuilder queryBuilder, int size) {

		return _get(
			collectionName, null, null, queryBuilder, size,
			SortBuilderUtil.fieldSort("id"));
	}

	@Override
	public JSONObject get(String collectionName, String id) {
		JSONObject jsonObject = fetch(collectionName, id);

		if (jsonObject == null) {
			throw new ResourceNotFoundException(
				"Unable to get entry in collection " + collectionName +
					" with ID " + id);
		}

		return jsonObject;
	}

	@Override
	public JSONArray get(
		String collectionName, String[] includes, QueryBuilder queryBuilder) {

		return _getAll(
			null, collectionName, includes, queryBuilder,
			SortBuilderUtil.fieldSort("id"));
	}

	@Override
	public JSONArray get(
		String collectionName, String[] includes, QueryBuilder queryBuilder,
		int size) {

		return _get(
			collectionName, null, includes, queryBuilder, size,
			SortBuilderUtil.fieldSort("id"));
	}

	@Override
	public RefreshResponse refresh() {
		RefreshRequestBuilder refreshRequestBuilder = new RefreshRequestBuilder(
			_client, RefreshAction.INSTANCE);

		ClientUtil.waitForConnection(_client);

		return refreshRequestBuilder.get();
	}

	@Override
	public JSONObject replace(String collectionName, JSONObject jsonObject) {
		return replace(collectionName, jsonObject, true);
	}

	@Override
	public JSONObject replace(
		String collectionName, JSONObject jsonObject, boolean refresh) {

		IndexRequestBuilder indexRequestBuilder = _client.prepareIndex(
			_elasticsearchAliases.get(collectionName), collectionName,
			String.valueOf(jsonObject.get("id")));

		if (refresh) {
			indexRequestBuilder.setRefreshPolicy(
				WriteRequest.RefreshPolicy.IMMEDIATE);
		}

		indexRequestBuilder.setSource(jsonObject.toString(), XContentType.JSON);

		ClientUtil.waitForConnection(_client);

		indexRequestBuilder.get();

		return jsonObject;
	}

	@Override
	public void save(String collectionName, JSONArray jsonArray) {
		save(collectionName, jsonArray, true);
	}

	@Override
	public void save(
		String collectionName, JSONArray jsonArray, boolean refresh) {

		for (int j = 0; j < jsonArray.length(); j++) {
			save(collectionName, jsonArray.getJSONObject(j), refresh);
		}
	}

	@Override
	public JSONObject save(String collectionName, JSONObject jsonObject) {
		return save(collectionName, jsonObject, true);
	}

	@Override
	public JSONObject save(
		String collectionName, JSONObject jsonObject, boolean refresh) {

		Object id = jsonObject.opt("id");

		if (id != null) {
			JSONObject existingJSONObject = fetch(
				collectionName, String.valueOf(id));

			if (existingJSONObject != null) {
				return replace(collectionName, jsonObject, refresh);
			}
		}

		return add(collectionName, jsonObject, refresh);
	}

	@Override
	public SearchResponse search(
		String collectionName, Consumer<SearchSourceBuilder> consumer) {

		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		consumer.accept(searchSourceBuilder);

		return search(collectionName, searchSourceBuilder);
	}

	@Override
	public SearchResponse search(
		String collectionName, SearchSourceBuilder searchSourceBuilder) {

		if (searchSourceBuilder.size() == -1) {
			throw new IllegalArgumentException("Size must be defined");
		}

		SearchRequestBuilder searchRequestBuilder = _prepareSearch(
			_elasticsearchAliases.get(collectionName));

		searchSourceBuilder.trackTotalHits(true);

		searchRequestBuilder = searchRequestBuilder.setSource(
			searchSourceBuilder);

		ClientUtil.waitForConnection(_client);

		return searchRequestBuilder.get();
	}

	@Override
	public JSONObject update(String collectionName, JSONObject jsonObject) {
		return update(
			collectionName, String.valueOf(jsonObject.get("id")), jsonObject);
	}

	@Override
	public JSONObject update(
		String collectionName, String id, JSONObject jsonObject) {

		return _update(
			jsonObject,
			_client.prepareUpdate(
				_elasticsearchAliases.get(collectionName), collectionName, id));
	}

	@Override
	public JSONObject update(String collectionName, String id, Script script) {
		UpdateRequestBuilder updateRequestBuilder = _client.prepareUpdate(
			_elasticsearchAliases.get(collectionName), collectionName, id);

		updateRequestBuilder.setFetchSource(true);
		updateRequestBuilder.setRefreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);
		updateRequestBuilder.setRetryOnConflict(5);
		updateRequestBuilder.setScript(script);

		ClientUtil.waitForConnection(_client);

		UpdateResponse updateResponse = updateRequestBuilder.get();

		GetResult getResult = updateResponse.getGetResult();

		return new JSONObject(getResult.getSource());
	}

	@Override
	public BulkByScrollResponse updateByQuery(
		QueryBuilder queryBuilder, boolean refresh, Script script,
		String... collectionNames) {

		return _updateByQuery(queryBuilder, refresh, script, collectionNames);
	}

	@Override
	public boolean updateByQueryWithRetry(
		QueryBuilder queryBuilder, boolean refresh, Script script,
		String... collectionNames) {

		int retry = 0;

		while (true) {
			BulkByScrollResponse bulkByScrollResponse = _updateByQuery(
				queryBuilder, refresh, script, collectionNames);

			List<BulkItemResponse.Failure> bulkFailures =
				bulkByScrollResponse.getBulkFailures();

			if ((bulkByScrollResponse.getVersionConflicts() == 0) &&
				bulkFailures.isEmpty()) {

				return true;
			}

			if (++retry >= 10) {
				_log.error("Unable to execute updateByQuery");

				return false;
			}

			try {
				Thread.sleep(5000);
			}
			catch (InterruptedException interruptedException) {
				Thread thread = Thread.currentThread();

				thread.interrupt();
			}
		}
	}

	@Override
	public JSONObject upsert(String collectionName, JSONObject jsonObject) {
		UpdateRequestBuilder updateRequestBuilder = _client.prepareUpdate(
			_elasticsearchAliases.check(collectionName), collectionName,
			String.valueOf(jsonObject.get("id")));

		updateRequestBuilder.setDoc(jsonObject.toString(), XContentType.JSON);
		updateRequestBuilder.setFetchSource(true);
		updateRequestBuilder.setDocAsUpsert(true);
		updateRequestBuilder.setRefreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);
		updateRequestBuilder.setRetryOnConflict(5);

		ClientUtil.waitForConnection(_client);

		UpdateResponse updateResponse = updateRequestBuilder.get();

		GetResult getResult = updateResponse.getGetResult();

		return new JSONObject(getResult.getSource());
	}

	private JSONArray _get(
		String collectionName, Integer from, String[] includes,
		QueryBuilder queryBuilder, int size,
		FieldSortBuilder... fieldSortBuilders) {

		JSONArray jsonArray = new JSONArray();

		SearchRequestBuilder searchRequestBuilder = _prepareSearch(
			_elasticsearchAliases.get(collectionName));

		for (FieldSortBuilder fieldSortBuilder : fieldSortBuilders) {
			searchRequestBuilder.addSort(fieldSortBuilder);
		}

		if (from != null) {
			searchRequestBuilder.setFrom(from);
		}

		if (includes != null) {
			searchRequestBuilder.setFetchSource(includes, null);
		}

		if (queryBuilder != null) {
			searchRequestBuilder.setQuery(queryBuilder);
		}

		searchRequestBuilder.setSize(size);

		ClientUtil.waitForConnection(_client);

		SearchResponse searchResponse = searchRequestBuilder.get();

		SearchHits searchHits = searchResponse.getHits();

		SearchHit[] hits = searchHits.getHits();

		for (SearchHit searchHit : hits) {
			jsonArray.put(new JSONObject(searchHit.getSourceAsString()));
		}

		return jsonArray;
	}

	private JSONArray _getAll(
		CollapseBuilder collapseBuilder, String collectionName,
		String[] includes, QueryBuilder queryBuilder,
		FieldSortBuilder... fieldSortBuilders) {

		JSONArray jsonArray = new JSONArray();

		SearchRequestBuilder searchRequestBuilder = _prepareSearch(
			_elasticsearchAliases.get(collectionName));

		for (FieldSortBuilder fieldSortBuilder : fieldSortBuilders) {
			searchRequestBuilder.addSort(fieldSortBuilder);
		}

		if (collapseBuilder != null) {
			searchRequestBuilder.setCollapse(collapseBuilder);
		}

		if (includes != null) {
			searchRequestBuilder.setFetchSource(includes, null);
		}

		if (queryBuilder != null) {
			searchRequestBuilder.setQuery(queryBuilder);
		}

		searchRequestBuilder.setSize(10000);

		ClientUtil.waitForConnection(_client);

		Object[] fieldValues = null;

		while (true) {
			if (fieldValues != null) {
				searchRequestBuilder.searchAfter(fieldValues);
			}

			SearchResponse searchResponse = searchRequestBuilder.get();

			SearchHits searchHits = searchResponse.getHits();

			SearchHit[] hits = searchHits.getHits();

			for (SearchHit searchHit : hits) {
				jsonArray.put(new JSONObject(searchHit.getSourceAsString()));
			}

			if (hits.length < 10000) {
				break;
			}

			SearchHit searchHit = hits[hits.length - 1];

			fieldValues = searchHit.getSortValues();
		}

		return jsonArray;
	}

	private List<String> _getExistingIndices(
		QueryBuilder queryBuilder, String... collectionNames) {

		List<String> indices = new ArrayList<>();

		MultiSearchRequestBuilder multiSearchRequestBuilder =
			_client.prepareMultiSearch();

		for (String collectionName : collectionNames) {
			SearchRequestBuilder searchRequestBuilder = _prepareSearch(
				_elasticsearchAliases.get(collectionName));

			searchRequestBuilder.setQuery(queryBuilder);
			searchRequestBuilder.setSize(0);

			multiSearchRequestBuilder.add(searchRequestBuilder);
		}

		ClientUtil.waitForConnection(_client);

		MultiSearchResponse multiSearchResponse =
			multiSearchRequestBuilder.get();

		MultiSearchResponse.Item[] multiSearchResponseItems =
			multiSearchResponse.getResponses();

		for (int i = 0; i < multiSearchResponseItems.length; i++) {
			MultiSearchResponse.Item multiSearchResponseItem =
				multiSearchResponseItems[i];

			SearchResponse searchResponse =
				multiSearchResponseItem.getResponse();

			if (searchResponse == null) {
				continue;
			}

			SearchHits searchHits = searchResponse.getHits();

			if (HitsUtil.hasHits(searchHits)) {
				indices.add(_elasticsearchAliases.get(collectionNames[i]));
			}
		}

		return indices;
	}

	private SearchRequestBuilder _prepareSearch(String indexName) {
		SearchRequestBuilder searchRequestBuilder = _client.prepareSearch(
			indexName);

		searchRequestBuilder.setIndicesOptions(
			IndicesOptions.lenientExpandOpen());

		return searchRequestBuilder;
	}

	private JSONObject _update(
		JSONObject jsonObject, UpdateRequestBuilder updateRequestBuilder) {

		updateRequestBuilder.setDoc(jsonObject.toString(), XContentType.JSON);
		updateRequestBuilder.setFetchSource(true);
		updateRequestBuilder.setRefreshPolicy(
			WriteRequest.RefreshPolicy.IMMEDIATE);
		updateRequestBuilder.setRetryOnConflict(5);

		ClientUtil.waitForConnection(_client);

		UpdateResponse updateResponse = updateRequestBuilder.get();

		GetResult getResult = updateResponse.getGetResult();

		return new JSONObject(getResult.getSource());
	}

	private BulkByScrollResponse _updateByQuery(
		QueryBuilder queryBuilder, boolean refresh, Script script,
		String... collectionNames) {

		List<String> indices = _getExistingIndices(
			queryBuilder, collectionNames);

		if (indices.isEmpty()) {
			return new BulkByScrollResponse(
				TimeValue.ZERO,
				new BulkByScrollTask.Status(
					0, 0, 0, 0, 0, 0, 0, 0, 0, 0, TimeValue.ZERO, 0F, null,
					TimeValue.ZERO),
				Collections.emptyList(), Collections.emptyList(), false);
		}

		UpdateByQueryRequestBuilder updateByQueryRequestBuilder =
			new UpdateByQueryRequestBuilder(
				_client, UpdateByQueryAction.INSTANCE);

		updateByQueryRequestBuilder.abortOnVersionConflict(false);
		updateByQueryRequestBuilder.filter(queryBuilder);
		updateByQueryRequestBuilder.refresh(refresh);
		updateByQueryRequestBuilder.script(script);
		updateByQueryRequestBuilder.source(indices.toArray(new String[0]));

		UpdateByQueryRequest updateByQueryRequest =
			updateByQueryRequestBuilder.request();

		updateByQueryRequest.setBatchSize(10000);

		ClientUtil.waitForConnection(_client);

		return updateByQueryRequestBuilder.get();
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchInvokerImpl.class);

	private final Client _client;
	private final ElasticsearchAliases _elasticsearchAliases;
	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();
	private final WeDeployDataService _weDeployDataService;

}