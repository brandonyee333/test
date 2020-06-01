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

import java.util.function.Consumer;

import org.elasticsearch.ResourceNotFoundException;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 * @author André Miranda
 */
public interface ElasticsearchInvoker {

	public boolean add(String collectionName, JSONArray jsonArray);

	public boolean add(String collectionName, JSONArray jsonArray, int delta);

	public JSONObject add(String collectionName, JSONObject jsonObject);

	public boolean clearScroll(String scrollId) throws Exception;

	public long count(String collectionName, QueryBuilder queryBuilder);

	public ElasticsearchBulkRequestBuilder
		createElasticsearchBulkRequestBuilder();

	public boolean delete(String collectionName, JSONObject jsonObject);

	public void delete(String collectionName, QueryBuilder queryBuilder);

	public boolean delete(String collectionName, String id);

	public BulkByScrollResponse deleteByQuery(
		QueryBuilder queryBuilder, boolean refresh, String... collectionNames);

	public boolean exists(String collectionName, QueryBuilder queryBuilder);

	public boolean exists(String collectionName, String id);

	public JSONObject fetch(String collectionName, QueryBuilder queryBuilder);

	public JSONObject fetch(
		String collectionName, QueryBuilder queryBuilder,
		SortBuilder<?> sortBuilder, String sourceExclude, String sourceInclude);

	public JSONObject fetch(
		String collectionName, QueryBuilder queryBuilder, String sourceExclude,
		String sourceInclude);

	public JSONObject fetch(String collectionName, String id);

	public JSONArray get(String collectionName);

	public String get(
		String collectionName, Consumer<SearchSourceBuilder> consumer);

	public JSONArray get(String collectionName, QueryBuilder queryBuilder);

	public JSONObject get(String collectionName, String id)
		throws ResourceNotFoundException;

	public String getIndexAlias(String collectionName);

	public RefreshResponse refresh();

	public JSONObject replace(String collectionName, JSONObject jsonObject);

	public void save(String collectionName, JSONArray jsonArray);

	public JSONObject save(String collectionName, JSONObject jsonObject);

	public SearchResponse search(
		String collectionName, Consumer<SearchSourceBuilder> consumer);

	public SearchResponse search(
		String collectionName, SearchSourceBuilder searchSourceBuilder);

	public SearchResponse searchScroll(
		String collectionName, Consumer<SearchSourceBuilder> consumer,
		long keepAliveInSeconds);

	public SearchResponse searchScroll(String scrollId, long timeValueSeconds);

	public JSONObject update(String collectionName, JSONObject jsonObject);

	public JSONObject update(
		String collectionName, String id, JSONObject jsonObject);

	public JSONObject update(String collectionName, String id, Script script);

	public BulkByScrollResponse updateByQuery(
		QueryBuilder queryBuilder, boolean refresh, Script script,
		String... collectionNames);

	public boolean updateByQueryWithRetry(
		QueryBuilder queryBuilder, boolean refresh, Script script,
		String... collectionNames);

	public JSONObject upsert(
		String collectionName, String id, JSONObject jsonObject, Script script);

}