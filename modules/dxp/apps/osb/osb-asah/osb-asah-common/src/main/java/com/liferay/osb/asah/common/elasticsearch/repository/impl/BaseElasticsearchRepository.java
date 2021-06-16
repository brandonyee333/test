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
import com.liferay.osb.asah.common.elasticsearch.impl.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseElasticsearchRepository<T extends Persistable<ID>, ID>
	implements CrudRepository<T, ID> {

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
	public Iterable<T> findAll() {
		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		return toList(elasticsearchInvoker.get(getCollectionName()));
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

		JSONObject jsonObject = null;

		if ((entity.getId() != null) &&
			elasticsearchInvoker.exists(
				getCollectionName(), String.valueOf(entity.getId()))) {

			jsonObject = elasticsearchInvoker.update(
				getCollectionName(), toJSONObject(entity));
		}
		else {
			jsonObject = elasticsearchInvoker.add(
				getCollectionName(), toJSONObject(entity));
		}

		return (S)toEntity(jsonObject);
	}

	@Override
	public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
		List<S> list = new ArrayList<>();

		JSONArray jsonArray = new JSONArray();

		ElasticsearchInvoker elasticsearchInvoker = getElasticsearchInvoker();

		entities.forEach(
			entity -> {
				JSONObject jsonObject = null;

				if ((entity.getId() != null) &&
					elasticsearchInvoker.exists(
						getCollectionName(), String.valueOf(entity.getId()))) {

					jsonObject = elasticsearchInvoker.update(
						getCollectionName(), toJSONObject(entity));
				}
				else {
					jsonObject = toJSONObject(entity);

					String id = jsonObject.optString(
						"id", _timeOrderedUuidGenerator.generateId());

					jsonObject.put("id", id);

					jsonArray.put(jsonObject);
				}

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
		SearchSourceBuilder searchSourceBuilder, Pageable pageable) {

		searchSourceBuilder.from(
			pageable.getPageNumber() * pageable.getPageSize());
		searchSourceBuilder.size(pageable.getPageSize());

		Stream.of(
			pageable.getSort()
		).flatMap(
			Sort::stream
		).forEach(
			sort -> {
				SortOrder sortOrder = SortOrder.ASC;

				if (sort.isDescending()) {
					sortOrder = SortOrder.DESC;
				}

				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort(sort.getProperty(), sortOrder));
			}
		);
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

	protected static final int ELASTICSEARCH_MAX_SIZE = 10000;

	@Autowired
	protected ObjectMapper objectMapper;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}