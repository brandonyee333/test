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

import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ObjectMapperUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.lucene.search.TotalHits;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONObject;

/**
 * @author Marcellus Tavares
 */
public class ElasticsearchRepository<T> {

	public ElasticsearchRepository(
		String collectionName, ElasticsearchInvoker elasticsearchInvoker,
		Class<T> modelClass) {

		_collectionName = collectionName;
		_elasticsearchInvoker = elasticsearchInvoker;
		_modelClass = modelClass;
	}

	public T add(T model) {
		return _toModel(
			_elasticsearchInvoker.add(_collectionName, _toJSONObject(model)));
	}

	public boolean delete(String id) {
		return _elasticsearchInvoker.delete(_collectionName, id);
	}

	public BulkByScrollResponse deleteByQuery(QueryBuilder queryBuilder) {
		return _elasticsearchInvoker.deleteByQuery(
			queryBuilder, true, _collectionName);
	}

	public boolean exists(QueryBuilder queryBuilder) {
		return _elasticsearchInvoker.exists(_collectionName, queryBuilder);
	}

	public T fetchFirst(Consumer<SearchSourceBuilder> consumer) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		consumer.accept(searchSourceBuilder);

		searchSourceBuilder.size(1);

		ResultBag<T> resultBag = _search(searchSourceBuilder);

		if (resultBag.getTotal() == 0) {
			return null;
		}

		List<T> results = resultBag.getResults();

		return results.get(0);
	}

	public T get(String id) {
		return _toModel(_elasticsearchInvoker.get(_collectionName, id));
	}

	public List<T> list(Consumer<SearchSourceBuilder> consumer) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		consumer.accept(searchSourceBuilder);

		ResultBag<T> resultBag = _search(searchSourceBuilder);

		return resultBag.getResults();
	}

	public ResultBag<T> search(Consumer<SearchSourceBuilder> consumer) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		consumer.accept(searchSourceBuilder);

		return _search(searchSourceBuilder);
	}

	public ResultBag<T> search(
		QueryBuilder queryBuilder, int size, Sort sort, int start) {

		return search(
			searchSourceBuilder -> {
				searchSourceBuilder.from(start);
				searchSourceBuilder.query(queryBuilder);
				searchSourceBuilder.size(size);
				searchSourceBuilder.sort(SortBuilderUtil.fieldSort(sort));
			});
	}

	public T update(String id, T model) {
		return _toModel(
			_elasticsearchInvoker.update(
				_collectionName, id, _toJSONObject(model)));
	}

	private ResultBag<T> _search(SearchSourceBuilder searchSourceBuilder) {
		SearchResponse searchResponse = _elasticsearchInvoker.search(
			_collectionName, searchSourceBuilder);

		SearchHits searchHits = searchResponse.getHits();

		List<T> results = new ArrayList<>();

		for (SearchHit searchHit : searchHits) {
			results.add(
				ObjectMapperUtil.convertValue(
					searchHit.getSourceAsMap(), _modelClass));
		}

		TotalHits totalHits = searchHits.getTotalHits();

		return new ResultBag<>(results, totalHits.value);
	}

	private JSONObject _toJSONObject(T model) {
		return ObjectMapperUtil.convertValue(model, JSONObject.class);
	}

	private T _toModel(JSONObject jsonObject) {
		return ObjectMapperUtil.convertValue(jsonObject, _modelClass);
	}

	private final String _collectionName;
	private final ElasticsearchInvoker _elasticsearchInvoker;
	private final Class<T> _modelClass;

}