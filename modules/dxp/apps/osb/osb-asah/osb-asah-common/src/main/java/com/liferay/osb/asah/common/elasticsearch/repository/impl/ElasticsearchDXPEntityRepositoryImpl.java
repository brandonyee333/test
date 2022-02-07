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
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.util.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcos Martins
 */
@Repository
public class ElasticsearchDXPEntityRepositoryImpl
	implements DXPEntityRepository {

	@Override
	public long count() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long countByDataSourceIdsAndKeywordsAndType(
		List<Long> dataSourceIds, @Nullable String keywords,
		DXPEntity.Type type) {

		return _dxpRawElasticsearchInvoker.count(
			type.getCollectionName(),
			_createQueryBuilder(
				type.getCollectionName(), dataSourceIds, keywords));
	}

	@Override
	public void delete(DXPEntity dxpEntity) {
		DXPEntity.Type type = dxpEntity.getType();

		_dxpRawElasticsearchInvoker.delete(
			type.getCollectionName(), String.valueOf(dxpEntity.getId()));
	}

	@Override
	public void deleteAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteAll(Iterable<? extends DXPEntity> dxpEntities) {
		dxpEntities.forEach(this::delete);
	}

	@Override
	public void deleteByFieldNameAndFieldValueAndType(
		String fieldName, String fieldValue, DXPEntity.Type type) {

		_dxpRawElasticsearchInvoker.delete(
			type.getCollectionName(),
			QueryBuilders.termQuery(fieldName, fieldValue));
	}

	@Override
	public void deleteById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteByType(DXPEntity.Type type) {
		_dxpRawElasticsearchInvoker.delete(
			type.getCollectionName(), QueryBuilders.matchAllQuery());
	}

	@Override
	public boolean existsById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<DXPEntity> findAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Page<DXPEntity> findAll(Pageable pageable) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<DXPEntity> findAll(
		org.springframework.data.domain.Sort sort) {

		throw new UnsupportedOperationException();
	}

	@Override
	public Iterable<DXPEntity> findAllById(Iterable<Long> ids) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<DXPEntity> findByAfterAndFieldsAndType(
		@Nullable Long after, Map<String, Object> fields, int size,
		DXPEntity.Type type) {

		SearchResponse searchResponse = _dxpRawElasticsearchInvoker.search(
			type.getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.query(_createQueryBuilder(fields));

				if (after != null) {
					searchSourceBuilder.searchAfter(new Long[] {after});
				}

				searchSourceBuilder.size(size);

				searchSourceBuilder.sort(
					SortBuilderUtil.fieldSort("id", SortOrder.ASC));
			});

		return _toList(searchResponse.getHits());
	}

	@Override
	public List<DXPEntity> findByFieldsAndType(
		Map<String, Object> fields, DXPEntity.Type type) {

		return _toList(
			_dxpRawElasticsearchInvoker.get(
				type.getCollectionName(), _createQueryBuilder(fields)));
	}

	@Override
	public Optional<DXPEntity> findById(Long id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<DXPEntity> findByMembershipClassNameAndMembershipId(
		String membershipClassName, Long membershipId) {

		return _toList(
			_dxpRawElasticsearchInvoker.get(
				"users",
				QueryBuilders.termQuery(
					"fields.memberships." + membershipClassName,
					membershipId)));
	}

	@Override
	@SuppressWarnings("unchecked")
	public <S extends DXPEntity> S save(S dxpEntity) {
		DXPEntity.Type type = dxpEntity.getType();

		JSONObject jsonObject = objectMapper.convertValue(
			dxpEntity, JSONObject.class);

		String id = jsonObject.optString(
			"id", _timeOrderedUuidGenerator.generateId());

		jsonObject.put("id", id);

		return (S)objectMapper.convertValue(
			_dxpRawElasticsearchInvoker.upsert(
				type.getCollectionName(), jsonObject),
			dxpEntity.getClass());
	}

	@Override
	public <S extends DXPEntity> Iterable<S> saveAll(Iterable<S> dxpEntities) {
		Stream<S> stream = StreamSupport.stream(
			dxpEntities.spliterator(), false);

		return stream.map(
			this::save
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public List<DXPEntity> searchByDataSourceIdsAndKeywordsAndType(
		List<Long> dataSourceIds, @Nullable String keywords,
		DXPEntity.Type type, Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(type.getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_dxpRawElasticsearchInvoker);
			collectionGetResponse.setPage(pageable.getPageNumber());
			collectionGetResponse.setQueryBuilder(
				_createQueryBuilder(
					type.getCollectionName(), dataSourceIds, keywords));
			collectionGetResponse.setSize(pageable.getPageSize());

			List<String> sorts = new ArrayList<>();

			for (Sort.Order order : pageable.getSort()) {
				if (StringUtils.equals(order.getProperty(), "name")) {
					if (StringUtils.equals(type.getCollectionName(), "users")) {
						sorts.add("fields.firstName");

						sorts.add(_getOrderDirection(order));

						sorts.add("fields.lastName");
					}
					else {
						sorts.add("fields.name");
					}
				}
				else {
					sorts.add(order.getProperty());
				}

				sorts.add(_getOrderDirection(order));
			}

			collectionGetResponse.setSorts(sorts.toArray(new String[0]));

			JSONObject jsonObject = new JSONObject(
				collectionGetResponse.respond());

			JSONObject embeddedJSONObject = jsonObject.getJSONObject(
				"_embedded");

			return _toList(
				embeddedJSONObject.getJSONArray(type.getCollectionName()));
		}
		catch (Exception exception) {
			return Collections.emptyList();
		}
	}

	@Autowired
	protected ObjectMapper objectMapper;

	private BoolQueryBuilder _addDataSourceIdsBoolQueryTerm(
		BoolQueryBuilder boolQueryBuilder, List<Long> dataSourceIds) {

		if (dataSourceIds.isEmpty()) {
			return boolQueryBuilder;
		}

		BoolQueryBuilder dataSourceBoolQueryBuilder = QueryBuilders.boolQuery();

		for (Long dataSourceId : dataSourceIds) {
			dataSourceBoolQueryBuilder.should(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId)));
		}

		return BoolQueryBuilderUtil.filter(
			boolQueryBuilder
		).filter(
			dataSourceBoolQueryBuilder
		);
	}

	private BoolQueryBuilder _createQueryBuilder(Map<String, Object> fields) {
		BoolQueryBuilder boolQueryBuilder = null;

		Set<Map.Entry<String, Object>> entrySet = fields.entrySet();

		for (Map.Entry<String, Object> entry : entrySet) {
			TermsQueryBuilder termsQueryBuilder = null;

			if (entry.getValue() instanceof Collection) {
				termsQueryBuilder = QueryBuilders.termsQuery(
					entry.getKey(), (Collection<?>)entry.getValue());
			}
			else {
				termsQueryBuilder = QueryBuilders.termsQuery(
					entry.getKey(),
					Collections.singletonList(entry.getValue()));
			}

			if (boolQueryBuilder == null) {
				boolQueryBuilder = BoolQueryBuilderUtil.filter(
					termsQueryBuilder);
			}
			else {
				boolQueryBuilder.filter(termsQueryBuilder);
			}
		}

		return boolQueryBuilder;
	}

	private QueryBuilder _createQueryBuilder(
		String collectionName, List<Long> dataSourceIds, String keywords) {

		if (dataSourceIds == null) {
			return _createQueryBuilder(collectionName, keywords);
		}

		if (collectionName.equals("groups") || collectionName.equals("teams")) {
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

			if (StringUtils.isNotBlank(keywords)) {
				boolQueryBuilder = BoolQueryBuilderUtil.filter(
					QueryUtil.buildSearchQueryBuilder("fields.name", keywords));
			}

			return _addDataSourceIdsBoolQueryTerm(
				boolQueryBuilder, dataSourceIds);
		}

		if (collectionName.equals("users")) {
			BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

			if (StringUtils.isNotBlank(keywords)) {
				boolQueryBuilder = BoolQueryBuilderUtil.should(
					QueryUtil.buildSearchQueryBuilder(
						"fields.firstName", keywords)
				).should(
					QueryUtil.buildSearchQueryBuilder(
						"fields.lastName", keywords)
				);
			}

			return _addDataSourceIdsBoolQueryTerm(
				boolQueryBuilder, dataSourceIds);
		}

		return _createQueryBuilder(collectionName, keywords);
	}

	private QueryBuilder _createQueryBuilder(
		String collectionName, String keywords) {

		if (collectionName.equals("users")) {
			return BoolQueryBuilderUtil.should(
				QueryUtil.buildSearchQueryBuilder("fields.firstName", keywords)
			).should(
				QueryUtil.buildSearchQueryBuilder("fields.lastName", keywords)
			);
		}

		return QueryUtil.buildSearchQueryBuilder("fields.name", keywords);
	}

	private String _getOrderDirection(Sort.Order order) {
		if (order.isAscending()) {
			return "asc";
		}

		return "desc";
	}

	private DXPEntity _toDXPEntity(JSONObject jsonObject) {
		return objectMapper.convertValue(jsonObject, DXPEntity.class);
	}

	private List<DXPEntity> _toList(JSONArray jsonArray) {
		Stream<Object> stream = JSONUtil.toObjectStream(jsonArray);

		return stream.map(
			object -> _toDXPEntity((JSONObject)object)
		).collect(
			Collectors.toList()
		);
	}

	private List<DXPEntity> _toList(SearchHits searchHits) {
		Stream<SearchHit> stream = Arrays.stream(searchHits.getHits());

		return stream.map(
			searchHit -> {
				try {
					return objectMapper.readValue(
						searchHit.getSourceAsString(), DXPEntity.class);
				}
				catch (IOException ioException) {
					throw new RuntimeException(
						"Unable to process search request", ioException);
				}
			}
		).collect(
			Collectors.toList()
		);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	private final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}