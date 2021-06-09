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
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.SalesforceEntityRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregation;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository
public class ElasticsearchSalesforceEntityRepositoryImpl
	implements SalesforceEntityRepository {

	@Override
	public long count() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long countByDataSourceIdAndType(
		Long dataSourceId, SalesforceEntity.Type type) {

		return _salesforceRawElasticsearchInvoker.count(
			_getCollectionName(type),
			QueryBuilders.termQuery(
				"dataSourceId", String.valueOf(dataSourceId)));
	}

	@Override
	public void delete(SalesforceEntity salesforceEntity) {
		_salesforceRawElasticsearchInvoker.delete(
			_getCollectionName(salesforceEntity.getType()),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dataSourceId",
					String.valueOf(salesforceEntity.getDataSourceId()))
			).filter(
				QueryBuilders.termQuery("id", salesforceEntity.getId())
			));
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll(
		Iterable<? extends SalesforceEntity> salesforceEntities) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteByDataSourceId(Long dataSourceId) {
		QueryBuilder queryBuilder = QueryBuilders.termQuery(
			"dataSourceId", String.valueOf(dataSourceId));

		_salesforceRawElasticsearchInvoker.delete("Account", queryBuilder);
		_salesforceRawElasticsearchInvoker.delete("Contact", queryBuilder);
		_salesforceRawElasticsearchInvoker.delete("Lead", queryBuilder);
		_salesforceRawElasticsearchInvoker.delete("individuals", queryBuilder);
	}

	@Override
	public void deleteById(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean existsByDataSourceIdAndIdAndType(
		Long dataSourceId, String id, SalesforceEntity.Type type) {

		return _salesforceRawElasticsearchInvoker.exists(
			_getCollectionName(type),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))
			).filter(
				QueryBuilders.termQuery("id", id)
			));
	}

	@Override
	public boolean existsById(String s) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<SalesforceEntity> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<SalesforceEntity> findAllById(Iterable<String> strings) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<SalesforceEntity> findByDataSourceIdAndFieldKeyEqualsAndType(
		Long dataSourceId, String fieldKey, String fieldValue,
		SalesforceEntity.Type type) {

		return toList(
			_salesforceRawElasticsearchInvoker.get(
				_getCollectionName(type),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceId", String.valueOf(dataSourceId))
				).filter(
					QueryBuilders.termQuery("fields." + fieldKey, fieldValue)
				)));
	}

	@Override
	public List<String>
		findByDataSourceIdAndFieldKeyEqualsAndTypeGroupByFieldKey(
			Long dataSourceId, String fieldKey, String fieldValue,
			SalesforceEntity.Type type, String groupByFieldKey) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		TermsValuesSourceBuilder termsValuesSourceBuilder =
			new TermsValuesSourceBuilder("terms");

		termsValuesSourceBuilder.field("fields." + groupByFieldKey);

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite", Collections.singletonList(termsValuesSourceBuilder)
			).size(
				10000
			);

		searchSourceBuilder.aggregation(compositeAggregationBuilder);

		searchSourceBuilder.query(
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))
			).filter(
				QueryBuilders.termQuery("fields." + fieldKey, fieldValue)
			));

		searchSourceBuilder.size(0);

		List<String> groupByFieldValues = new ArrayList<>();

		while (true) {
			SearchResponse searchResponse =
				_salesforceRawElasticsearchInvoker.search(
					_getCollectionName(type), searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				break;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Map<String, Object> keys = bucket.getKey();

				groupByFieldValues.add(String.valueOf(keys.get("terms")));
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}

		return groupByFieldValues;
	}

	@Override
	public Optional<SalesforceEntity> findByDataSourceIdAndIdAndType(
		Long dataSourceId, String id, SalesforceEntity.Type type) {

		JSONObject jsonObject = _salesforceRawElasticsearchInvoker.fetch(
			_getCollectionName(type),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))
			).filter(
				QueryBuilders.termQuery("id", id)
			));

		return Optional.ofNullable(
			jsonObject
		).map(
			this::_toEntity
		);
	}

	@Override
	public List<SalesforceEntity> findByDataSourceIdAndType(
		Long dataSourceId, SalesforceEntity.Type type, Pageable pageable) {

		return toList(
			new JSONArray(
				_salesforceRawElasticsearchInvoker.get(
					_getCollectionName(type),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							QueryUtil.buildSearchQueryBuilder(
								"dataSourceId", String.valueOf(dataSourceId)));

						searchSourceBuilder.from(
							pageable.getPageNumber() * pageable.getPageSize());
						searchSourceBuilder.size(pageable.getPageSize());
					})));
	}

	@Override
	public Optional<SalesforceEntity> findById(String id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <S extends SalesforceEntity> S save(S salesforceEntity) {
		JSONObject jsonObject = null;

		if ((salesforceEntity.getId() != null) &&
			_salesforceRawElasticsearchInvoker.exists(
				_getCollectionName(salesforceEntity.getType()),
				String.valueOf(salesforceEntity.getId()))) {

			jsonObject = _salesforceRawElasticsearchInvoker.update(
				_getCollectionName(salesforceEntity.getType()),
				_toJSONObject(salesforceEntity));
		}
		else {
			jsonObject = _salesforceRawElasticsearchInvoker.add(
				_getCollectionName(salesforceEntity.getType()),
				_toJSONObject(salesforceEntity));
		}

		return (S)_toEntity(jsonObject);
	}

	@Override
	public <S extends SalesforceEntity> Iterable<S> saveAll(
		Iterable<S> salesforceEntities) {

		Stream<S> stream = StreamSupport.stream(
			salesforceEntities.spliterator(), false);

		return stream.map(
			this::save
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public void updateSalesforceEntityFields(
		Long dataSourceId, JSONObject fieldsJSONObject, String id,
		SalesforceEntity.Type type) {

		Optional<SalesforceEntity> salesforceEntityOptional =
			findByDataSourceIdAndIdAndType(dataSourceId, id, type);

		SalesforceEntity salesforceEntity = salesforceEntityOptional.orElse(
			null);

		if (salesforceEntity == null) {
			return;
		}

		salesforceEntity.setFieldsJSONObject(fieldsJSONObject);

		save(salesforceEntity);
	}

	protected List<SalesforceEntity> toList(JSONArray jsonArray) {
		Stream<Object> stream = JSONUtil.toObjectStream(jsonArray);

		return stream.map(
			object -> _toEntity((JSONObject)object)
		).collect(
			Collectors.toList()
		);
	}

	private String _getCollectionName(SalesforceEntity.Type type) {
		return type.getValue();
	}

	private SalesforceEntity _toEntity(JSONObject jsonObject) {
		return _objectMapper.convertValue(jsonObject, SalesforceEntity.class);
	}

	private JSONObject _toJSONObject(SalesforceEntity salesforceEntity) {
		return _objectMapper.convertValue(salesforceEntity, JSONObject.class);
	}

	@Autowired
	private ObjectMapper _objectMapper;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}