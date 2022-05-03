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
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.entity.DataSourceFieldMapping;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
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
public class ElasticsearchFieldMappingRepositoryImpl
	extends BaseElasticsearchRepository<FieldMapping, Long>
	implements FieldMappingRepository {

	@Override
	public long countByFieldNameAndOwnerType(
		String fieldName, String ownerType) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("fieldName", fieldName)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	@Override
	public long countFieldMappings(FilterHelper filterHelper) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(), filterHelper.getQueryBuilder());
	}

	@Override
	public long countIndividualFieldMappings(@Nullable String name) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(), _getQueryBuilder(name));
	}

	@Override
	public boolean existsByContextAndFieldNameAndOwnerType(
		String context, String fieldName, String ownerType) {

		return _faroInfoElasticsearchInvoker.exists(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", context)
			).filter(
				QueryBuilders.termQuery("fieldName", fieldName)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));
	}

	@Override
	public List<FieldMapping>
		findByContextAndDataSourceIdAndFieldNameAndOwnerType(
			String context, Long dataSourceId, String fieldName,
			String ownerType) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("context", context)
				).filter(
					QueryBuilders.existsQuery(
						"dataSourceFieldNames." + dataSourceId)
				).filter(
					QueryBuilders.termQuery("fieldName", fieldName)
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				)));
	}

	@Override
	public List<FieldMapping> findByContextAndDataSourceIdAndOwnerType(
		String context, @Nullable Long dataSourceId, String ownerType) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("context", context)
		).filter(
			QueryBuilders.termQuery("ownerType", ownerType)
		);

		if (dataSourceId != null) {
			boolQueryBuilder = boolQueryBuilder.filter(
				QueryBuilders.existsQuery(
					"dataSourceFieldNames." + dataSourceId));
		}

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				Arrays.asList(
					SortBuilderUtil.fieldSort("displayName"),
					SortBuilderUtil.fieldSort("dateModified", SortOrder.DESC)),
				boolQueryBuilder));
	}

	@Override
	public List<FieldMapping> findByContextAndDisplayNameAndOwnerType(
		String context, String displayName, String ownerType) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("context", context)
		).filter(
			QueryBuilders.termQuery("displayName", displayName)
		).filter(
			QueryBuilders.termQuery("ownerType", ownerType)
		);

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(), boolQueryBuilder));
	}

	@Override
	public Optional<FieldMapping> findByContextAndFieldNameAndOwnerType(
		String context, String fieldName, String ownerType) {

		JSONObject fieldMappingJSONObject = _faroInfoElasticsearchInvoker.fetch(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("context", context)
			).filter(
				QueryBuilders.termQuery("fieldName", fieldName)
			).filter(
				QueryBuilders.termQuery("ownerType", ownerType)
			));

		return Optional.ofNullable(toEntity(fieldMappingJSONObject));
	}

	@Override
	public List<FieldMapping>
		findByDataSourceFieldNameAndDataSourceIdAndOwnerType(
			String dataSourceFieldName, Long dataSourceId, String ownerType) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"dataSourceFieldNames." + dataSourceId,
						dataSourceFieldName)
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				)));
	}

	@Override
	public List<FieldMapping> findByDataSourceId(Long dataSourceId) {
		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				QueryBuilders.existsQuery(
					"dataSourceFieldNames." + dataSourceId)));
	}

	@Override
	public List<String> findFieldNameByContextAndFieldTypeAndOwnerType(
		String context, String fieldType, String ownerType) {

		List<String> fieldNames = new ArrayList<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"fieldNames"
					).field(
						"fieldName"
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("context", context)
					).filter(
						QueryBuilders.termQuery("fieldType", fieldType)
					).filter(
						QueryBuilders.termQuery("ownerType", ownerType)
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (isEmpty(aggregations)) {
			return fieldNames;
		}

		Terms terms = aggregations.get("fieldNames");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			fieldNames.add(
				String.format(
					"demographics.%s.value.searchable",
					bucket.getKeyAsString()));
		}

		return fieldNames;
	}

	@Override
	public List<Transformation> getFieldMappingTransformations(
		String apply, FilterHelper filterHelper, Pageable pageable) {

		TransformationGetResponse transformationGetResponse =
			new TransformationGetResponse();

		transformationGetResponse.setCollectionName(getCollectionName());
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
			"field-mapping-transformations");

		JSONObject jsonObject = null;

		try {
			jsonObject = new JSONObject(transformationGetResponse.respond());
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}

		JSONObject embeddedJSONObject = jsonObject.getJSONObject("_embedded");

		JSONArray fieldMappingTransformationsJSONArray =
			embeddedJSONObject.getJSONArray("field-mapping-transformations");

		Stream<Object> stream = JSONUtil.toObjectStream(
			fieldMappingTransformationsJSONArray);

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
	public List<FieldMapping> searchFieldMappings(
		FilterHelper filterHelper, Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(getCollectionName());
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

			return toList(embeddedJSONObject.getJSONArray(getCollectionName()));
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}
	}

	@Override
	public List<FieldMapping> searchIndividualFieldMappings(
		@Nullable String name, Pageable pageable) {

		try {
			CollectionGetResponse collectionGetResponse =
				new CollectionGetResponse();

			collectionGetResponse.setCollectionName(getCollectionName());
			collectionGetResponse.setElasticsearchInvoker(
				_faroInfoElasticsearchInvoker);
			collectionGetResponse.setPage(pageable.getPageNumber());
			collectionGetResponse.setQueryBuilder(_getQueryBuilder(name));
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
		return "field-mappings";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@Override
	protected JSONObject toJSONObject(FieldMapping fieldMapping) {
		JSONObject jsonObject = super.toJSONObject(fieldMapping);

		Set<DataSourceFieldMapping> dataSourceFieldMappings =
			fieldMapping.getDataSourceFieldMappings();

		JSONObject dataSourceFieldNamesJSONObject = jsonObject.optJSONObject(
			"dataSourceFieldNames");

		if (CollectionUtils.isNotEmpty(dataSourceFieldMappings)) {
			if (dataSourceFieldNamesJSONObject == null) {
				dataSourceFieldNamesJSONObject = new JSONObject();
			}

			for (DataSourceFieldMapping dataSourceFieldMapping :
					dataSourceFieldMappings) {

				dataSourceFieldNamesJSONObject.put(
					String.valueOf(dataSourceFieldMapping.getDataSourceId()),
					dataSourceFieldMapping.getFieldName());
			}

			jsonObject.put(
				"dataSourceFieldNames", dataSourceFieldNamesJSONObject);
		}

		if ((fieldMapping.getId() != null) &&
			_faroInfoElasticsearchInvoker.exists(
				getCollectionName(), String.valueOf(fieldMapping.getId()))) {

			JSONObject existingFieldMappingJSONObject =
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(), String.valueOf(fieldMapping.getId()));

			existingFieldMappingJSONObject.put(
				"dataSourceFieldNames",
				jsonObject.getJSONObject("dataSourceFieldNames"));

			fieldMapping = objectMapper.convertValue(
				jsonObject, FieldMapping.class);

			FieldMapping existingFieldMapping = objectMapper.convertValue(
				existingFieldMappingJSONObject, FieldMapping.class);

			BeanUtils.copyProperties(fieldMapping, existingFieldMapping);

			jsonObject = objectMapper.convertValue(
				existingFieldMapping, JSONObject.class);
		}

		return jsonObject;
	}

	private QueryBuilder _getQueryBuilder(String name) {
		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.existsQuery("dataSourceFieldNames")
		).filter(
			QueryBuilders.termQuery("ownerType", "individual")
		);

		if (StringUtils.isNotBlank(name)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.queryStringQuery(
						String.format(
							"%s:*%s*", "fieldName",
							QueryUtil.escapeKeywords(name)))
				).should(
					QueryBuilders.matchQuery(
						"fieldName", name
					).fuzziness(
						Fuzziness.AUTO
					)
				));
		}

		return boolQueryBuilder;
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
		ElasticsearchFieldMappingRepositoryImpl.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}