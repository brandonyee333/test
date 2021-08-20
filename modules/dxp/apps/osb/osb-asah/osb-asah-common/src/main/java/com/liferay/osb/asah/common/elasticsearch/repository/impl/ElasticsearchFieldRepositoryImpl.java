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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.Field;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.FieldRepository;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.TopHits;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@Repository
public class ElasticsearchFieldRepositoryImpl
	extends BaseElasticsearchRepository<Field, Long>
	implements FieldRepository {

	@Override
	public long countFields(@Nullable String filterString) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			FilterStringToQueryBuilderConverter.convert(filterString));
	}

	@Override
	public void deleteByDataSourceId(Long dataSourceId) {
		_faroInfoElasticsearchInvoker.delete(
			getCollectionName(),
			QueryBuilders.termQuery(
				"dataSourceId", String.valueOf(dataSourceId)));
	}

	@Override
	public void deleteByOwnerIdAndOwnerType(Long ownerId, String ownerType) {
		_faroInfoElasticsearchInvoker.delete(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			).filter(
				QueryBuilders.termQuery("ownerType", "individual")
			));
	}

	@Override
	public boolean existsByDataSourceId(Long dataSourceId) {
		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(),
			QueryBuilders.termQuery(
				"dataSourceId", String.valueOf(dataSourceId)));
	}

	@Override
	public boolean existsByDataSourceIdAndNameAndOwnerId(
		Long dataSourceId, String name, Long ownerId) {

		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))
			).filter(
				QueryBuilders.termQuery("name", name)
			).filter(
				QueryBuilders.termQuery("ownerId", String.valueOf(ownerId))
			));
	}

	@Override
	public boolean existsByNameAndOwnerId(String name, Long ownerId) {
		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("name", name)
			).filter(
				QueryBuilders.termQuery("ownerId", ownerId)
			));
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

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(), boolQueryBuilder));
	}

	@Override
	public List<Field> findByContextAndDataSourceIdAndOwnerIdAndOwnerType(
		String context, Long dataSourceId, Long ownerId, String ownerType) {

		return findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
			context, dataSourceId, null, ownerId, ownerType);
	}

	@Override
	public List<Field>
		findByContextAndDataSourceIdNotAndNameNotInAndOwnerIdAndOwnerType(
			String context, Long dataSourceId, List<String> names, Long ownerId,
			String ownerType) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("context", context)
				).filter(
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.termQuery(
							"dataSourceId", String.valueOf(dataSourceId)))
				).filter(
					BoolQueryBuilderUtil.mustNot(
						QueryBuilders.termsQuery("name", names))
				).filter(
					QueryBuilders.termQuery("ownerId", String.valueOf(ownerId))
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
	public List<Field> findByContextAndOwnerIdAndOwnerType(
		String context, Long ownerId, String ownerType) {

		return findByContextAndDataSourceIdAndNameAndOwnerIdAndOwnerType(
			context, null, null, ownerId, ownerType);
	}

	@Override
	public List<Field> findByContextAndOwnerIdGroupByMaxModifiedDateAndName(
		String context, Long ownerId) {

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
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
						QueryBuilders.termQuery(
							"ownerId", String.valueOf(ownerId))
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (aggregations == null) {
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

		return toList(jsonArray);
	}

	@Override
	public Field findByDataSourceIdAndNameAndOwnerId(
		Long dataSourceId, String name, Long ownerId) {

		return toEntity(
			_faroInfoElasticsearchInvoker.fetch(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceId", String.valueOf(dataSourceId))
				).filter(
					QueryBuilders.termQuery("name", name)
				).filter(
					QueryBuilders.termQuery("ownerId", String.valueOf(ownerId))
				)));
	}

	@Override
	public List<Field> findByFieldTypeAndOwnerTypeAndValueIn(
		String fieldType, String ownerType, List<String> values) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("fieldType", fieldType)
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				).filter(
					QueryBuilders.termsQuery("value", values)
				)));
	}

	@Override
	public List<Transformation> getFieldTransformations(
		String apply, @Nullable String filterString, Pageable pageable) {

		TransformationGetResponse transformationGetResponse =
			new TransformationGetResponse();

		transformationGetResponse.setCollectionName(getCollectionName());
		transformationGetResponse.setElasticsearchInvoker(
			_faroInfoElasticsearchInvoker);
		transformationGetResponse.setPage(pageable.getPageNumber());

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString);

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
	public List<Field> searchFields(
		@Nullable String filterString, Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setPage(pageable.getPageNumber());

			QueryBuilder queryBuilder =
				FilterStringToQueryBuilderConverter.convert(filterString);

			if (queryBuilder != null) {
				collectionGetResponse.setQueryBuilder(queryBuilder);
			}

			collectionGetResponse.setSize(pageable.getPageSize());
			collectionGetResponse.setSorts(_getSorts(pageable.getSort()));

			JSONObject jsonObject = new JSONObject(
				collectionGetResponse.respond());

			JSONObject embeddedJSONObject = jsonObject.getJSONObject(
				"_embedded");

			return toList(embeddedJSONObject.getJSONArray(getCollectionName()));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}
	}

	@Override
	protected String getCollectionName() {
		return "fields";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
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

	private static final Log _log = LogFactory.getLog(
		ElasticsearchFieldRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}