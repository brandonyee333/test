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

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.QueryUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository
public class ElasticsearchAssetRepositoryImpl
	extends BaseElasticsearchRepository<Asset, Long>
	implements AssetRepository {

	@Override
	public long countByAssetTypeAndAssetKeywordNotNull(String assetType) {
		return _faroInfoElasticsearchInvoker.count(
			"assets",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("assetType", assetType)
			).filter(
				QueryBuilders.existsQuery("keywords.keyword")
			));
	}

	@Override
	public long countByAssetTypeAndFilterStringAndKeywords(
		@Nullable String assetType, @Nullable String filterString,
		@Nullable String keyword) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_buildQueryBuilder(assetType, filterString, keyword));
	}

	@Override
	public long countByFilterString(@Nullable String filterString) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(), _buildQueryBuilder(filterString));
	}

	@Override
	public List<Asset> findByAssetTypeAndAssetKeywordNotNull(
		String assetType, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					"assets",
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							BoolQueryBuilderUtil.filter(
								QueryBuilders.termQuery("assetType", assetType)
							).filter(
								QueryBuilders.existsQuery("keywords.keyword")
							));

						if (pageable != null) {
							setSearchSourceBuilderPage(
								searchSourceBuilder, pageable);
						}
					})));
	}

	@Override
	public List<Asset> findByAssetTypeAndFilterStringAndKeywords(
		@Nullable String assetType, @Nullable String filterString,
		@Nullable String keywords, @Nullable Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							_buildQueryBuilder(
								assetType, filterString, keywords));

						if (pageable != null) {
							setSearchSourceBuilderPage(
								searchSourceBuilder, pageable);
						}
					})));
	}

	@Override
	public Optional<Asset> findByDataSourceAssetPKAndDataSourceId(
		String dataSourceAssetPK, Long dataSourceId) {

		JSONObject jsonObject = _faroInfoElasticsearchInvoker.fetch(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("dataSourceAssetPK", dataSourceAssetPK)
			).filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId))
			));

		return Optional.ofNullable(
			jsonObject
		).map(
			this::toEntity
		);
	}

	@Override
	public List<Asset> findByFilterString(
		@Nullable String filterString, Pageable pageable) {

		return toList(
			new JSONArray(
				_faroInfoElasticsearchInvoker.get(
					getCollectionName(),
					searchSourceBuilder -> {
						searchSourceBuilder.query(
							_buildQueryBuilder(filterString));

						setSearchSourceBuilderPage(
							searchSourceBuilder, pageable);
					})));
	}

	@Override
	public List<String> findKeywordByAssetType(String assetType) {
		List<String> keywords = new ArrayList<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"keywords"
					).field(
						"keywords.keyword.keyword"
					).order(
						BucketOrder.key(true)
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("assetType", assetType)));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (aggregations == null) {
			return keywords;
		}

		List<Aggregation> aggregationList = aggregations.asList();

		if (aggregationList.isEmpty()) {
			return keywords;
		}

		Terms terms = aggregations.get("keywords");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			keywords.add(bucket.getKeyAsString());
		}

		return keywords;
	}

	@Override
	public List<Transformation> getAssetTransformations(
		String apply, @Nullable String filterString, Pageable pageable) {

		TransformationGetResponse transformationGetResponse =
			new TransformationGetResponse();

		transformationGetResponse.setCollectionName(getCollectionName());
		transformationGetResponse.setElasticsearchInvoker(
			_faroInfoElasticsearchInvoker);
		transformationGetResponse.setPage(pageable.getPageNumber());

		QueryBuilder queryBuilder = FilterStringToQueryBuilderConverter.convert(
			filterString, _assetFilterStringConverterHelper);

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
			"asset-transformations");

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
			embeddedJSONObject.getJSONArray("asset-transformations");

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
	protected String getCollectionName() {
		return "assets";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private QueryBuilder _buildQueryBuilder(String filterString) {
		if (StringUtils.isEmpty(filterString)) {
			return QueryBuilders.matchAllQuery();
		}

		return FilterStringToQueryBuilderConverter.convert(
			filterString, _assetFilterStringConverterHelper);
	}

	private QueryBuilder _buildQueryBuilder(
		String assetType, String filterString, String keywords) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("assetType", assetType));

		if (StringUtils.isNotBlank(keywords)) {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.should(
					QueryBuilders.wildcardQuery(
						"canonicalUrl", "*" + keywords + "*")
				).should(
					QueryBuilders.queryStringQuery(
						String.format(
							"name:*%1$s* OR description:*%1$s*",
							QueryUtil.escapeKeywords(keywords)))
				).should(
					QueryBuilders.multiMatchQuery(
						keywords, "name", "description"
					).fuzziness(
						Fuzziness.AUTO
					)
				).should(
					QueryBuilders.wildcardQuery("url", "*" + keywords + "*")
				));
		}

		if (StringUtils.isNotEmpty(filterString)) {
			boolQueryBuilder.filter(
				FilterStringToQueryBuilderConverter.convert(
					filterString, _assetFilterStringConverterHelper));
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
		ElasticsearchAssetRepositoryImpl.class);

	private final AssetFilterStringConverterHelper
		_assetFilterStringConverterHelper =
			new AssetFilterStringConverterHelper();

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	private static class AssetFilterStringConverterHelper
		extends DefaultFilterStringConverterHelper {

		@Override
		public QueryBuilder getCustomFunctionQueryBuilder(
			List<String> arguments, String customFunctionName,
			boolean negated) {

			if (!customFunctionName.equalsIgnoreCase("similarTo")) {
				return null;
			}

			Map<String, String> fieldNames = getFieldNameConversionMap();

			String fieldName = toFieldName(
				fieldNames.getOrDefault(arguments.get(0), arguments.get(0)));

			QueryBuilder queryBuilder = QueryBuilders.regexpQuery(
				fieldName,
				StringUtil.unquoteAndDecodeInnerQuotes(arguments.get(1)));

			if (negated) {
				return BoolQueryBuilderUtil.mustNot(queryBuilder);
			}

			return queryBuilder;
		}

		@Override
		public Map<String, String> getFieldNameConversionMap() {
			return new HashMap<String, String>() {
				{
					put("title", "name");
				}
			};
		}

	}

}