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
import com.liferay.osb.asah.common.elasticsearch.converter.FilterStringToQueryBuilderConverter;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.rest.response.TransformationGetResponse;
import com.liferay.osb.asah.common.rest.response.function.TermsAggregationTransformationJSONArrayFunction;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.StringUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregation;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.CompositeValuesSourceBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.TermsValuesSourceBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.Cardinality;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

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
	public long countAssetKeywords(String keywords) {
		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.cardinality(
						"keywordsCount"
					).field(
						"keywords.keyword"
					));

				if (StringUtils.isNotBlank(keywords)) {
					searchSourceBuilder.query(
						QueryBuilders.regexpQuery(
							"keywords.keyword",
							FilterStringToQueryBuilderConverter.
								buildIgnoreCaseRegExp(
									StringUtil.unquote(keywords))));
				}

				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (isEmpty(aggregations)) {
			return 0;
		}

		Cardinality cardinality = aggregations.get("keywordsCount");

		return cardinality.getValue();
	}

	@Override
	public long countByAssetTypeAndCanonicalURLIn(
		String assetType, Collection<String> canonicalUrls) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("assetType", assetType)
			).filter(
				QueryBuilders.termsQuery("canonicalUrl", canonicalUrls)
			));
	}

	@Override
	public long countByAssetTypeAndFilterStringAndKeywords(
		@Nullable String assetType, FilterHelper filterHelper,
		@Nullable String keyword) {

		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(),
			_buildQueryBuilder(assetType, filterHelper, keyword));
	}

	@Override
	public long countByFilterString(FilterHelper filterHelper) {
		return _faroInfoElasticsearchInvoker.count(
			getCollectionName(), _buildQueryBuilder(filterHelper));
	}

	@Override
	public List<Asset> findByAssetTypeAndAssetKeywordNotNull(
		Long assetId, String assetType, int size) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(), SortBuilderUtil.fieldSort("id"),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("assetType", assetType)
				).filter(
					QueryBuilders.existsQuery("keywords.keyword")
				).filter(
					QueryBuilders.rangeQuery(
						"id"
					).gt(
						assetId
					)
				),
				size));
	}

	@Override
	public List<Asset> findByAssetTypeAndCanonicalURLIn(
		String assetType, Collection<String> canonicalUrls, Pageable pageable) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				(int)pageable.getOffset(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("assetType", assetType)
				).filter(
					QueryBuilders.termsQuery("canonicalUrl", canonicalUrls)
				),
				pageable.getPageSize()));
	}

	@Override
	public List<Asset> findByAssetTypeAndFilterString(
		@Nullable String assetType, FilterHelper filterHelper) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				_buildQueryBuilder(assetType, filterHelper, null)));
	}

	@Override
	public List<Asset> findByAssetTypeAndFilterStringAndKeywords(
		@Nullable String assetType, FilterHelper filterHelper, String keywords,
		Pageable pageable) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				(int)pageable.getOffset(),
				_buildQueryBuilder(assetType, filterHelper, keywords),
				pageable.getPageSize()));
	}

	@Override
	public List<Asset> findByChannelIds(
		List<Long> channelIds, Pageable pageable) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				(int)pageable.getOffset(),
				QueryBuilders.termsQuery(
					"channelIds", ListUtil.map(channelIds, String::valueOf)),
				pageable.getPageSize()));
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
		FilterHelper filterHelper, Pageable pageable) {

		return toList(
			_faroInfoElasticsearchInvoker.get(
				getCollectionName(),
				getFieldSortBuilders(
					getSortFieldNameConversionMap(), pageable.getSort()),
				(int)pageable.getOffset(), _buildQueryBuilder(filterHelper),
				pageable.getPageSize()));
	}

	@Override
	public List<String> findDataSourceAssetPKByKeyword(String keyword) {
		List<String> dataSourceAssetPKs = new ArrayList<>();

		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"dataSourceAssetPKs"
					).field(
						"dataSourceAssetPK"
					).order(
						BucketOrder.key(true)
					).size(
						Integer.MAX_VALUE
					));
				searchSourceBuilder.query(
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery("assetType", "Page")
					).filter(
						QueryBuilders.termQuery("keywords.keyword", keyword)
					));
				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (isEmpty(aggregations)) {
			return dataSourceAssetPKs;
		}

		Terms terms = aggregations.get("dataSourceAssetPKs");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			dataSourceAssetPKs.add(bucket.getKeyAsString());
		}

		return dataSourceAssetPKs;
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

		if (isEmpty(aggregations)) {
			return keywords;
		}

		Terms terms = aggregations.get("keywords");

		for (Terms.Bucket bucket : terms.getBuckets()) {
			keywords.add(bucket.getKeyAsString());
		}

		return keywords;
	}

	@Override
	public List<String> getAssetKeywords(String keywords, Pageable pageable) {
		SearchResponse searchResponse = _faroInfoElasticsearchInvoker.search(
			getCollectionName(),
			searchSourceBuilder -> {
				searchSourceBuilder.aggregation(
					AggregationBuilders.terms(
						"keywords/keyword"
					).field(
						"keywords.keyword"
					).size(
						Integer.MAX_VALUE
					).subAggregation(
						PipelineAggregatorBuilders.bucketSort(
							"groupBySort",
							_getFieldSortBuilders(pageable.getSort())
						).from(
							Math.max(0, (int)pageable.getOffset())
						).size(
							pageable.getPageSize()
						)
					));

				if (StringUtils.isNotBlank(keywords)) {
					searchSourceBuilder.query(
						BoolQueryBuilderUtil.filter(
							QueryBuilders.wildcardQuery(
								"keywords.keyword", "*" + keywords + "*")));
				}

				searchSourceBuilder.size(0);
			});

		Aggregations aggregations = searchResponse.getAggregations();

		if (isEmpty(aggregations)) {
			return Collections.emptyList();
		}

		Terms terms = aggregations.get("keywords/keyword");

		List<? extends Terms.Bucket> buckets = terms.getBuckets();

		Stream<? extends Terms.Bucket> stream = buckets.stream();

		return stream.map(
			Terms.Bucket::getKeyAsString
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public List<Transformation> getAssetTransformations(
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
	public Map<String, Set<String>> getByAssetTypeAndChannelIdAndDatasourceId(
		String assetType, @Nullable Long channelId,
		@Nullable Long dataSourceId) {

		Map<String, Set<String>> assets = new HashMap<>();

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		TermsValuesSourceBuilder assetIdTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("assetIds");

		assetIdTermsValuesSourceBuilder.field("id");

		TermsValuesSourceBuilder keywordTermsValuesSourceBuilder =
			new TermsValuesSourceBuilder("keywords/keyword");

		keywordTermsValuesSourceBuilder.field("keywords.keyword");

		CompositeAggregationBuilder compositeAggregationBuilder =
			AggregationBuilders.composite(
				"composite",
				new ArrayList<CompositeValuesSourceBuilder<?>>() {
					{
						add(assetIdTermsValuesSourceBuilder);
						add(keywordTermsValuesSourceBuilder);
					}
				}
			).size(
				10000
			);

		searchSourceBuilder.aggregation(compositeAggregationBuilder);

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("assetType", "Page")
		).filter(
			QueryBuilders.existsQuery("keywords.keyword")
		);

		if (channelId != null) {
			BoolQueryBuilderUtil.filterTerm(
				boolQueryBuilder, "channelIds", String.valueOf(channelId));
		}

		if (dataSourceId != null) {
			BoolQueryBuilderUtil.filterTerm(
				boolQueryBuilder, "dataSourceId", String.valueOf(dataSourceId));
		}

		searchSourceBuilder.query(boolQueryBuilder);

		searchSourceBuilder.size(0);

		while (true) {
			SearchResponse searchResponse =
				_faroInfoElasticsearchInvoker.search(
					"assets", searchSourceBuilder);

			Aggregations aggregations = searchResponse.getAggregations();

			if (isEmpty(aggregations)) {
				return assets;
			}

			CompositeAggregation compositeAggregation = aggregations.get(
				"composite");

			List<? extends CompositeAggregation.Bucket> buckets =
				compositeAggregation.getBuckets();

			if (buckets.isEmpty()) {
				return assets;
			}

			for (CompositeAggregation.Bucket bucket : buckets) {
				Map<String, Object> keys = bucket.getKey();

				String keyword = (String)keys.get("keywords/keyword");

				Set<String> assetIds = assets.getOrDefault(
					keyword, new HashSet<>());

				assetIds.add((String)keys.get("assetIds"));

				assets.put(keyword, assetIds);
			}

			compositeAggregationBuilder.aggregateAfter(
				compositeAggregation.afterKey());
		}
	}

	@Override
	protected String getCollectionName() {
		return "assets";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	private QueryBuilder _buildQueryBuilder(FilterHelper filterHelper) {
		if (StringUtils.isEmpty(filterHelper.getFilterString())) {
			return QueryBuilders.matchAllQuery();
		}

		return filterHelper.getQueryBuilder();
	}

	private QueryBuilder _buildQueryBuilder(
		String assetType, FilterHelper filterHelper, String keywords) {

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

		if (StringUtils.isNotEmpty(filterHelper.getFilterString())) {
			boolQueryBuilder.filter(filterHelper.getQueryBuilder());
		}

		return boolQueryBuilder;
	}

	private List<FieldSortBuilder> _getFieldSortBuilders(Sort sort) {
		Stream<Sort.Order> stream = sort.stream();

		return stream.map(
			order -> SortBuilderUtil.fieldSort(
				order.getProperty(),
				order.isAscending() ? SortOrder.ASC : SortOrder.DESC)
		).collect(
			Collectors.toList()
		);
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

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}