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
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.DataSourceFieldMapping;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.FieldMappingRepository;
import com.liferay.osb.asah.common.rest.response.CollectionGetResponse;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Rachael Koestartyo
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
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
	public long countFieldMappings(@Nullable String filterString) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			FilterStringToQueryBuilderConverter.convert(filterString));
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
		String context, Long dataSourceId, String ownerType) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("context", context)
				).filter(
					QueryBuilders.existsQuery(
						"dataSourceFieldNames." + dataSourceId)
				).filter(
					QueryBuilders.termQuery("ownerType", ownerType)
				)));
	}

	@Override
	public Optional<FieldMapping>
		findByContextAndDisplayNameAndDisplayTypeAndFieldTypeAndOwnerType(
			String context, String displayName, String displayType,
			@Nullable String fieldType, String ownerType) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("context", context)
		).filter(
			QueryBuilders.termQuery("displayName", displayName)
		).filter(
			QueryBuilders.termQuery("ownerType", ownerType)
		);

		if (StringUtils.isNotEmpty(displayType)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("displayType", displayType));
		}

		if (StringUtils.isNotEmpty(fieldType)) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("fieldType", fieldType));
		}

		JSONObject fieldMappingJSONObject = _faroInfoElasticsearchInvoker.fetch(
			getCollectionName(), boolQueryBuilder);

		return Optional.ofNullable(toEntity(fieldMappingJSONObject));
	}

	@Override
	public Optional<FieldMapping> findByContextAndDisplayNameAndOwnerType(
		String context, String displayName, String ownerType) {

		return findByContextAndDisplayNameAndDisplayTypeAndFieldTypeAndOwnerType(
			context, displayName, null, null, ownerType);
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
	public List<Transformation> getFieldMappingTransformations(
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
	public <S extends FieldMapping> S save(S fieldMapping) {
		JSONObject fieldMappingJSONObject = objectMapper.convertValue(
			fieldMapping, JSONObject.class);

		Set<DataSourceFieldMapping> dataSourceFieldMappings =
			fieldMapping.getDataSourceFieldMappings();

		JSONObject dataSourceFieldNamesJSONObject =
			fieldMappingJSONObject.optJSONObject("dataSourceFieldNames");

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

			fieldMappingJSONObject.put(
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
				fieldMappingJSONObject.getJSONObject("dataSourceFieldNames"));

			fieldMapping = (S)objectMapper.convertValue(
				fieldMappingJSONObject, FieldMapping.class);
			FieldMapping existingFieldMapping = objectMapper.convertValue(
				existingFieldMappingJSONObject, FieldMapping.class);

			BeanUtils.copyProperties(fieldMapping, existingFieldMapping);

			fieldMappingJSONObject = _faroInfoElasticsearchInvoker.replace(
				getCollectionName(),
				objectMapper.convertValue(
					existingFieldMapping, JSONObject.class));
		}
		else {
			fieldMappingJSONObject = _faroInfoElasticsearchInvoker.add(
				getCollectionName(), fieldMappingJSONObject);
		}

		return (S)toEntity(fieldMappingJSONObject);
	}

	@Override
	public List<FieldMapping> searchFieldMappings(
		String filterString, Pageable pageable) {

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