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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.Repository;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseElasticsearchRepository<T extends Persistable<ID>, ID>
	implements Repository<T, ID> {

	@Override
	public long count() {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		return elasticsearchInvoker.count(
			getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public void delete(T entity) {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		elasticsearchInvoker.delete(
			getCollectionName(), String.valueOf(entity.getId()));
	}

	@Override
	public void deleteAll() {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		elasticsearchInvoker.delete(
			getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		Stream<? extends T> stream = StreamSupport.stream(
			entities.spliterator(), false);

		elasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"id",
				stream.map(
					T::getId
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				)),
			true, getCollectionName());
	}

	@Override
	public void deleteById(ID id) {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		elasticsearchInvoker.delete(getCollectionName(), id.toString());
	}

	@Override
	public boolean existsById(ID id) {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		return elasticsearchInvoker.exists(getCollectionName(), id.toString());
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		return PageableExecutionUtils.getPage(
			toList(
				elasticsearchInvoker.get(
					getCollectionName(),
					getFieldSortBuilders(
						getSortFieldNameConversionMap(), pageable.getSort()),
					getFrom(pageable), pageable.getPageSize())),
			pageable, () -> count());
	}

	@Override
	public Iterable<T> findAll(Sort sort) {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		return toList(
			elasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(getSortFieldNameConversionMap(), sort)));
	}

	@Override
	public Iterable<T> findAllById(Iterable<ID> ids) {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		Stream<ID> stream = StreamSupport.stream(ids.spliterator(), false);

		return toList(
			elasticsearchInvoker.get(
				getCollectionName(),
				QueryBuilders.termsQuery(
					"id",
					stream.map(
						String::valueOf
					).collect(
						Collectors.toList()
					))));
	}

	@Override
	public Optional<T> findById(ID id) {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		return Optional.ofNullable(
			elasticsearchInvoker.fetch(getCollectionName(), id.toString())
		).map(
			this::toEntity
		);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <S extends T> S save(S entity) {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		JSONObject jsonObject = toJSONObject(entity);

		String id = jsonObject.optString(
			"id", timeOrderedUuidGenerator.generateId());

		jsonObject.put("id", id);

		return (S)toEntity(
			elasticsearchInvoker.add(getCollectionName(), jsonObject));
	}

	@Override
	public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
		List<S> list = new ArrayList<>();

		JSONArray jsonArray = new JSONArray();

		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		entities.forEach(
			entity -> {
				JSONObject jsonObject = toJSONObject(entity);

				String id = jsonObject.optString(
					"id", timeOrderedUuidGenerator.generateId());

				jsonObject.put("id", id);

				jsonArray.put(jsonObject);

				list.add((S)toEntity(jsonObject));
			});

		elasticsearchInvoker.add(getCollectionName(), jsonArray);

		return list;
	}

	protected Optional<T> findFirst(Consumer<SearchSourceBuilder> consumer) {
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

		consumer.accept(searchSourceBuilder);

		searchSourceBuilder.size(1);

		List<T> result = search(searchSourceBuilder);

		Stream<T> stream = result.stream();

		return stream.findFirst();
	}

	protected abstract String getCollectionName();

	protected abstract ElasticsearchInvoker getElasticsearchInvoker();

	protected List<FieldSortBuilder> getFieldSortBuilders(
		Map<String, String> fieldNames, Sort sort) {

		List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();

		if ((sort == null) || sort.isUnsorted()) {
			fieldSortBuilders.add(SortBuilderUtil.fieldSort("id"));

			return fieldSortBuilders;
		}

		if (fieldNames == null) {
			fieldNames = Collections.emptyMap();
		}

		for (Sort.Order order : sort.toList()) {
			String fieldName = order.getProperty();

			if (fieldNames.containsKey(fieldName)) {
				fieldName = fieldNames.get(fieldName);
			}

			fieldName = fieldName.replace('/', '.');

			SortOrder sortOrder = SortOrder.ASC;

			if (order.isDescending()) {
				sortOrder = SortOrder.DESC;
			}

			fieldSortBuilders.add(
				SortBuilderUtil.fieldSort(fieldName, sortOrder));
		}

		return fieldSortBuilders;
	}

	protected List<FieldSortBuilder> getFieldSortBuilders(Sort sort) {
		return getFieldSortBuilders(getSortFieldNameConversionMap(), sort);
	}

	protected int getFrom(Pageable pageable) {
		return (int)pageable.getOffset();
	}

	protected Map<String, String> getSortFieldNameConversionMap() {
		return Collections.emptyMap();
	}

	protected boolean isEmpty(Aggregations aggregations) {
		if (aggregations == null) {
			return true;
		}

		List<Aggregation> aggregationsList = aggregations.asList();

		return aggregationsList.isEmpty();
	}

	protected List<T> search(SearchSourceBuilder searchSourceBuilder) {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		SearchResponse searchResponse = elasticsearchInvoker.search(
			getCollectionName(), searchSourceBuilder);

		JSONArray jsonArray = new JSONArray();

		for (SearchHit searchHit : searchResponse.getHits()) {
			jsonArray.put(searchHit.getSourceAsMap());
		}

		return toList(jsonArray);
	}

	protected void setSearchSourceBuilderPage(
		Pageable pageable, SearchSourceBuilder searchSourceBuilder) {

		searchSourceBuilder.from(getFrom(pageable));
		searchSourceBuilder.size(pageable.getPageSize());

		for (FieldSortBuilder fieldSortBuilder :
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort())) {

			searchSourceBuilder.sort(fieldSortBuilder);
		}
	}

	@SuppressWarnings("unchecked")
	protected T toEntity(JSONObject jsonObject) {
		Class<?>[] typeArgumentClasses =
			GenericTypeResolver.resolveTypeArguments(
				getClass(), BaseElasticsearchRepository.class);

		if (typeArgumentClasses == null) {
			return null;
		}

		Class<T> entityClass = (Class<T>)typeArgumentClasses[0];

		return objectMapper.convertValue(jsonObject, entityClass);
	}

	protected JSONObject toJSONObject(T entity) {
		return objectMapper.convertValue(entity, JSONObject.class);
	}

	protected List<T> toList(JSONArray jsonArray) {
		Stream<Object> stream = JSONUtil.toObjectStream(jsonArray);

		return stream.map(
			object -> toEntity((JSONObject)object)
		).collect(
			Collectors.toList()
		);
	}

	@Autowired
	protected ObjectMapper objectMapper;

	protected final TimeOrderedUuidGenerator timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}