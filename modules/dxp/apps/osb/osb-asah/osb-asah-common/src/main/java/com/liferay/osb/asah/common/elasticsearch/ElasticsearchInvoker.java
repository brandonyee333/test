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

import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import java.util.List;
import java.util.function.Consumer;

import org.elasticsearch.ResourceNotFoundException;
import org.elasticsearch.action.admin.indices.refresh.RefreshResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.script.Script;
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
public interface ElasticsearchInvoker {

	public boolean add(String collectionName, JSONArray jsonArray);

	public boolean add(String collectionName, JSONArray jsonArray, int delta);

	public JSONObject add(String collectionName, JSONObject jsonObject);

	public JSONObject add(
		String collectionName, JSONObject jsonObject, boolean refresh);

	public long count(String collectionName, QueryBuilder queryBuilder);

	public ElasticsearchBulkRequestBuilder
		createElasticsearchBulkRequestBuilder();

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

	public JSONObject fetch(String collectionName, String id);

	public JSONArray get(
		CollapseBuilder collapseBuilder, String collectionName,
		List<FieldSortBuilder> fieldSortBuilders, QueryBuilder queryBuilder);

	public JSONArray get(String collectionName);

	public JSONArray get(
		String collectionName, FieldSortBuilder fieldSortBuilder, int size);

	public JSONArray get(
		String collectionName, FieldSortBuilder fieldSortBuilder,
		QueryBuilder queryBuilder, int size);

	public JSONArray get(
		String collectionName, FieldSortBuilder fieldSortBuilder,
		String[] includes, QueryBuilder queryBuilder, int size);

	public JSONArray get(
		String collectionName, int from, QueryBuilder queryBuilder, int size);

	public JSONArray get(
		String collectionName, List<FieldSortBuilder> fieldSortBuilders);

	public JSONArray get(
		String collectionName, List<FieldSortBuilder> fieldSortBuilders,
		int from, int size);

	public JSONArray get(
		String collectionName, List<FieldSortBuilder> fieldSortBuilders,
		int from, QueryBuilder queryBuilder, int size);

	public JSONArray get(
		String collectionName, List<FieldSortBuilder> fieldSortBuilders,
		QueryBuilder queryBuilder);

	public JSONArray get(
		String collectionName, List<FieldSortBuilder> fieldSortBuilders,
		String[] includes);

	public JSONArray get(
		String collectionName, List<FieldSortBuilder> fieldSortBuilders,
		String[] includes, QueryBuilder queryBuilder);

	public JSONArray get(String collectionName, QueryBuilder queryBuilder);

	public JSONArray get(
		String collectionName, QueryBuilder queryBuilder, int size);

	public JSONObject get(String collectionName, String id)
		throws ResourceNotFoundException;

	public JSONArray get(
		String collectionName, String[] includes, QueryBuilder queryBuilder);

	public JSONArray get(
		String collectionName, String[] includes, QueryBuilder queryBuilder,
		int size);

	public RefreshResponse refresh();

	public JSONObject replace(String collectionName, JSONObject jsonObject);

	public JSONObject replace(
		String collectionName, JSONObject jsonObject, boolean refresh);

	public void save(String collectionName, JSONArray jsonArray);

	public void save(
		String collectionName, JSONArray jsonArray, boolean refresh);

	public JSONObject save(String collectionName, JSONObject jsonObject);

	public JSONObject save(
		String collectionName, JSONObject jsonObject, boolean refresh);

	public SearchResponse search(
		String collectionName, Consumer<SearchSourceBuilder> consumer);

	public SearchResponse search(
		String collectionName, SearchSourceBuilder searchSourceBuilder);

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

	public JSONObject upsert(String collectionName, JSONObject jsonObject);

	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface Autowired {

		public boolean cacheable() default false;

		public WeDeployDataService value();

	}

}