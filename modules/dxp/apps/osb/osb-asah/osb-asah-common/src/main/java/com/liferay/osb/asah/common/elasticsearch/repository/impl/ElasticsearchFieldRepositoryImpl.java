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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.TopHits;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@Repository
public class ElasticsearchFieldRepositoryImpl implements FieldRepository {

	@Override
	public long count() {
		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public long countFields(FilterHelper filterHelper) {
		return _faroInfoElasticsearchInvoker.count(
			_getCollectionName(), filterHelper.getQueryBuilder());
	}

	@Override
	public void delete(Field field) {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(), String.valueOf(field.getId()));
	}

	@Override
	public void deleteAll() {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public void deleteAll(Iterable<? extends Field> fields) {
		Stream<? extends Field> stream = StreamSupport.stream(
			fields.spliterator(), false);

		_faroInfoElasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery(
				"id",
				stream.map(
					Field::getId
				).map(
					String::valueOf
				).collect(
					Collectors.toList()
				)),
			true, _getCollectionName());
	}

	@Override
	public void deleteByContextAndDataSourceIdAndNameAndOwnerIdInAndOwnerType(
		String context, Long dataSourceId, String name, List<Long> ownerIds,
		String ownerType) {

		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", context)
			).filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))
			).filter(
				QueryBuilders.termQuery("name", name)
			).filter(
				QueryBuilders.termsQuery(
					"ownerId", ListUtil.map(ownerIds, String::valueOf))
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	@Override
	public void deleteByContextAndOwnerId(String context, Long ownerId) {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", context)
			).filter(
				QueryBuilders.termQuery("ownerId", String.valueOf(ownerId))
			));
	}

	@Override
	public void deleteByDataSourceId(Long dataSourceId) {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(),
			QueryBuilders.termQuery(
				"dataSourceId", String.valueOf(dataSourceId)));
	}

	@Override
	public void deleteById(Long id) {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(), id.toString());
	}

	@Override
	public void deleteByOwnerIdAndOwnerType(Long ownerId, String ownerType) {
		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("ownerId", String.valueOf(ownerId))
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	@Override
	public void deleteByOwnerIdInAndOwnerType(
		List<Long> ownerIds, String ownerType) {

		_faroInfoElasticsearchInvoker.delete(
			_getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery(
					"ownerId", ListUtil.map(ownerIds, String::valueOf))
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	@Override
	public boolean existsById(Long id) {
		return _faroInfoElasticsearchInvoker.exists(
			_getCollectionName(), id.toString());
	}

	@Override
	public boolean existsByNameAndOwnerId(String name, Long ownerId) {
		return _faroInfoElasticsearchInvoker.exists(
			_getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("name", name)
			).filter(
				QueryBuilders.termQuery("ownerId", String.valueOf(ownerId))
			));
	}

	@Override
	public Iterable<Field> findAll() {
		return findAll(Sort.by("id"));
	}

	@Override
	public Page<Field> findAll(Pageable pageable) {
		return PageableExecutionUtils.getPage(
			_toFields(
				_faroInfoElasticsearchInvoker.get(
					_getCollectionName(),
					_getFieldSortBuilders(pageable.getSort()),
					_getFrom(pageable), pageable.getPageSize())),
			pageable, () -> count());
	}

	@Override
	public Iterable<Field> findAll(Sort sort) {
		return _toFields(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), _getFieldSortBuilders(sort)));
	}

	@Override
	public Iterable<Field> findAllById(Iterable<Long> ids) {
		Stream<Long> stream = StreamSupport.stream(ids.spliterator(), false);

		return _toFields(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(),
				QueryBuilders.termsQuery(
					"id",
					stream.map(
						String::valueOf
					).collect(
						Collectors.toList()
					))));
	}

	@Override
	public List<Field>
		findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
			String context, @Nullable Long dataSourceId, @Nullable String name,
			@Nullable Long ownerId, String ownerType) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("context", context)
		).filter(
			QueryBuilders.termQuery("ownerType", ownerType)
		);

		if (dataSourceId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId)));
		}

		if (name != null) {
			boolQueryBuilder.filter(QueryBuilders.termQuery("name", name));
		}

		if (ownerId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("ownerId", String.valueOf(ownerId)));
		}

		return _toFields(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(), boolQueryBuilder));
	}

	@Override
	public List<Field> findByContextAndDataSourceIdAndOwnerIdAndOwnerType(
		String context, Long dataSourceId, Long ownerId, String ownerType) {

		return findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
			context, dataSourceId, null, ownerId, ownerType);
	}

	@Override
	public List<Field> findByContextAndDataSourceIdAndOwnerIdInAndOwnerType(
		String context, Long dataSourceId, List<Long> ownerIds,
		String ownerType) {

		return _toFields(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("context", context)
				).filter(
					QueryBuilders.termQuery(
						"dataSourceId", String.valueOf(dataSourceId))
				).filter(
					QueryBuilders.termsQuery(
						"ownerId", ListUtil.map(ownerIds, String::valueOf))
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				)));
	}

	@Override
	public List<Field> findByContextAndNameAndOwnerIdAndOwnerType(
		String context, String name, Long ownerId, String ownerType) {

		return findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
			context, null, name, ownerId, ownerType);
	}

	@Override
	public List<Field> findByContextAndNameAndOwnerIdInAndOwnerType(
		String context, String name, List<Long> ownerIds, String ownerType) {

		return _toFields(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("context", context)
				).filter(
					QueryBuilders.termQuery("name", name)
				).filter(
					QueryBuilders.termsQuery(
						"ownerId", ListUtil.map(ownerIds, String::valueOf))
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				)));
	}

	@Override
	public List<Field> findByContextAndOwnerIdAndOwnerType(
		String context, Long ownerId, String ownerType) {

		return findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
			context, null, null, ownerId, ownerType);
	}

	@Override
	public List<Field> findByContextAndOwnerIdGroupByMaxModifiedDateAndName(
		String context, Long ownerId) {

		return findByContextAndOwnerIdInGroupByMaxModifiedDateAndName(
			context, Arrays.asList(ownerId));
	}

	@Override
	public List<Field> findByContextAndOwnerIdInGroupByMaxModifiedDateAndName(
		String context, List<Long> ownerIds) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			_getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"names"
					).field(
						"name"
					).size(
						Integer.MAX_VALUE
					).subAggregation(
						AggregationBuilders.topHits(
							"maxDateModified"
						).sort(
							SortBuilderUtil.fieldSort(
								"dateModified", SortOrder.DESC)
						).size(
							1
						)
					));

				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("context", context)
					).filter(
						QueryBuilders.termsQuery(
							"ownerId", ListUtil.map(ownerIds, String::valueOf))
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (_isEmpty(aggregations)) {
			return Collections.emptyList();
		}

		Terms nameTerms = aggregations.get("names");

		JSONArray jsonArray = new JSONArray();

		for (Terms.Bucket bucket : nameTerms.getBuckets()) {
			Aggregations maxDateModifiedAggregations = bucket.getAggregations();

			TopHits topHits = maxDateModifiedAggregations.get(
				"maxDateModified");

			SearchHits searchHits = topHits.getHits();

			SearchHit searchHit = searchHits.getAt(0);

			jsonArray.put(searchHit.getSourceAsMap());
		}

		return _toFields(jsonArray);
	}

	@Override
	public List<Field> findByFieldTypeAndOwnerTypeAndValueIn(
		String fieldType, String ownerType, List<String> values) {

		return _toFields(
			_faroInfoElasticsearchInvoker.get(
				_getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("fieldType", fieldType)
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				).filter(
					QueryBuilders.termsQuery("value", values)
				)));
	}

	@Override
	public Optional<Field> findById(Long id) {
		return Optional.ofNullable(
			_faroInfoElasticsearchInvoker.fetch(
				_getCollectionName(), id.toString())
		).map(
			this::_toField
		);
	}

	@Override
	public List<Transformation> getFieldTransformations(
		String apply, FilterHelper filterHelper, Pageable pageable) {

		TransformationGetResponse transformationGetResponse =
			new TransformationGetResponse();

		transformationGetResponse.setCollectionName(_getCollectionName());
		transformationGetResponse.setElasticsearchInvoker(
			_faroInfoElasticsearchInvoker);
		transformationGetResponse.setPage(pageable.getPageNumber());

		QueryBuilder queryBuilder = filterHelper.getQueryBuilder();

		if (queryBuilder != null) {
			transformationGetResponse.setQueryBuilder(queryBuilder);
		}

		transformationGetResponse.setSize(pageable.getPageSize());
		transformationGetResponse.setSorts(
			new HashMap<String, String>() {
				{
					put("terms", "_key");
					put("totalElements", "_count");
				}
			},
			_getSorts(pageable.getSort()));
		transformationGetResponse.setTransformationJSONArrayFunction(
			new TermsAggregationTransformationJSONArrayFunction(apply, null));
		transformationGetResponse.setTransformationName(
			"field-transformations");

		JSONObject jsonObject = null;

		try {
			jsonObject = new JSONObject(transformationGetResponse.respond());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}

		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		JSONArray fieldTransformationsJSONArray =
			embeddedJSONObject.getJSONArray("field-transformations");

		Stream<Object> stream = JSONUtil.toObjectStream(
			fieldTransformationsJSONArray);

		return stream.map(
			object -> {
				JSONObject curJSONObject = (JSONObject)object;

				return new Transformation(
					JSONUtil.toMap(curJSONObject.getJSONObject("terms")),
					curJSONObject.getInt("totalElements"));
			}
		).collect(
			Collectors.toList()
		);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <S extends Field> S save(S field) {
		JSONObject jsonObject = _toJSONObject(field);

		String id = jsonObject.optString(
			"id", _timeOrderedUuidGenerator.generateId());

		jsonObject.put("id", id);

		return (S)_toField(
			_faroInfoElasticsearchInvoker.add(
				_getCollectionName(), jsonObject));
	}

	@Override
	public <S extends Field> Iterable<S> saveAll(Iterable<S> fields) {
		List<S> list = new ArrayList<>();

		JSONArray jsonArray = new JSONArray();

		fields.forEach(
			field -> {
				JSONObject jsonObject = _toJSONObject(field);

				String id = jsonObject.optString(
					"id", _timeOrderedUuidGenerator.generateId());

				jsonObject.put("id", id);

				jsonArray.put(jsonObject);

				list.add((S)_toField(jsonObject));
			});

		_faroInfoElasticsearchInvoker.add(_getCollectionName(), jsonArray);

		return list;
	}

	@Override
	public List<Field> searchFields(
		FilterHelper filterHelper, Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(_getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setPage(pageable.getPageNumber());

			QueryBuilder queryBuilder = filterHelper.getQueryBuilder();

			if (queryBuilder != null) {
				collectionGetResponse.setQueryBuilder(queryBuilder);
			}

			collectionGetResponse.setSize(pageable.getPageSize());
			collectionGetResponse.setSorts(_getSorts(pageable.getSort()));

			JSONObject jsonObject = new JSONObject(
				collectionGetResponse.respond());

			JSONObject embeddedJSONObject = jsonObject.getJSONObject(
				"_embedded");

			return _toFields(
				embeddedJSONObject.getJSONArray(_getCollectionName()));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}
	}

	@Override
	public void updateDataSourceNameByDataSourceId(
		Long dataSourceId, String dataSourceName) {

		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery(
				"dataSourceId", String.valueOf(dataSourceId)),
			true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.dataSourceName = params.dataSourceName",
				Collections.singletonMap("dataSourceName", dataSourceName)),
			_getCollectionName());
	}

	private String _getCollectionName() {
		return "fields";
	}

	private List<FieldSortBuilder> _getFieldSortBuilders(Sort sort) {
		List<FieldSortBuilder> fieldSortBuilders = new ArrayList<>();

		if ((sort == null) || sort.isUnsorted()) {
			fieldSortBuilders.add(SortBuilderUtil.fieldSort("id"));

			return fieldSortBuilders;
		}

		for (Sort.Order order : sort.toList()) {
			String fieldName = order.getProperty();

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

	private int _getFrom(Pageable pageable) {
		return (int)pageable.getOffset();
	}

	private String[] _getSorts(Sort sort) {
		if (sort == null) {
			return null;
		}

		List<String> sorts = new LinkedList<>();

		for (Sort.Order order : sort) {
			sorts.add(order.getProperty());

			if (order.isAscending()) {
				sorts.add("asc");
			}
			else {
				sorts.add("desc");
			}
		}

		return sorts.toArray(new String[0]);
	}

	private boolean _isEmpty(Aggregations aggregations) {
		if (aggregations == null) {
			return true;
		}

		List<Aggregation> aggregationsList = aggregations.asList();

		return aggregationsList.isEmpty();
	}

	private Field _toField(JSONObject jsonObject) {
		return _objectMapper.convertValue(jsonObject, Field.class);
	}

	private List<Field> _toFields(JSONArray jsonArray) {
		Stream<Object> stream = JSONUtil.toObjectStream(jsonArray);

		return stream.map(
			object -> _toField((JSONObject)object)
		).collect(
			Collectors.toList()
		);
	}

	private JSONObject _toJSONObject(Field field) {
		return _objectMapper.convertValue(field, JSONObject.class);
	}

	private static final Log _log = LogFactory.getLog(
		ElasticsearchFieldRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}