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

package com.liferay.osb.asah.backend.dog.title;

import com.liferay.osb.asah.backend.dog.DataDog;
import com.liferay.osb.asah.backend.dog.DogConfigurationBag;
import com.liferay.osb.asah.backend.dog.DogUtil;
import com.liferay.osb.asah.backend.dog.configuration.DogConfiguration;
import com.liferay.osb.asah.backend.dog.resolver.AssetResolver;
import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.petra.string.StringPool;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lino Alves
 */
@Component
public class TitleDog {

	@Autowired
	public TitleDog(List<DogConfiguration> dogConfigurations) {
		_dogConfigurationBag = new DogConfigurationBag(dogConfigurations);
	}

	public Map<String, String> getTitle(
		AssetType assetType, Set<String> assetIds) {

		return getTitle(assetIds, assetType, null);
	}

	public Map<String, String> getTitle(
		Set<String> assetIds, AssetType assetType, String keywords) {

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(assetType);

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollection(),
			_buildSearchSourceBuilder(assetIds, assetType, keywords));

		if (DogUtil.isEmpty(aggregations)) {
			return Collections.emptyMap();
		}

		Terms terms = aggregations.get("assetId");

		Map<String, String> resultMap = new HashMap<>();

		for (Terms.Bucket termsBucket : terms.getBuckets()) {
			String assetId = termsBucket.getKeyAsString();

			Aggregations bucketAggregations = termsBucket.getAggregations();

			String title = _getTitle(bucketAggregations.get("title"));

			resultMap.put(assetId, title);
		}

		return resultMap;
	}

	private SearchSourceBuilder _buildSearchSourceBuilder(
		Set<String> assetIds, AssetType assetType, String keywords) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(assetType);

		AssetResolver<AssetMetric> assetResolver =
			dogConfiguration.getAssetResolver();

		searchSourceBuilder.aggregation(
			_createAssetIdFieldNameAggregationBuilder(
				assetResolver.getAssetIdFieldName()));
		searchSourceBuilder.query(
			_createQueryBuilder(assetIds, assetResolver, keywords));

		searchSourceBuilder.size(0);

		return searchSourceBuilder;
	}

	private AggregationBuilder _createAssetIdFieldNameAggregationBuilder(
		String assetIdFieldName) {

		TermsAggregationBuilder termsAggregationBuilder =
			AggregationBuilders.terms("assetId");

		termsAggregationBuilder.field(assetIdFieldName);
		termsAggregationBuilder.size(Integer.MAX_VALUE);
		termsAggregationBuilder.subAggregation(
			_createTitleAggregationBuilder());

		return termsAggregationBuilder;
	}

	private QueryBuilder _createAssetIdsQueryBuilder(
		Set<String> assetIds, AssetResolver<AssetMetric> assetResolver) {

		BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

		assetIds.forEach(
			assetId -> boolQueryBuilder.should(
				QueryBuilders.termQuery(
					assetResolver.getAssetIdFieldName(), assetId)));

		return boolQueryBuilder;
	}

	private QueryBuilder _createQueryBuilder(
		AssetResolver<AssetMetric> assetResolver, String keywords) {

		if ((keywords == null) || (keywords.length() < 3)) {
			return QueryBuilders.boolQuery();
		}

		MultiMatchQueryBuilder multiMatchQueryBuilder =
			QueryBuilders.multiMatchQuery(keywords);

		Set<String> searchableFieldNames =
			assetResolver.getSearchableFieldNames();

		searchableFieldNames.forEach(multiMatchQueryBuilder::field);

		return multiMatchQueryBuilder;
	}

	private QueryBuilder _createQueryBuilder(
		Set<String> assetIds, AssetResolver<AssetMetric> assetResolver,
		String keywords) {

		return BoolQueryBuilderUtil.filter(
			_createAssetIdsQueryBuilder(assetIds, assetResolver)
		).filter(
			_createQueryBuilder(assetResolver, keywords)
		);
	}

	private AggregationBuilder _createTitleAggregationBuilder() {
		TermsAggregationBuilder termsAggregationBuilder =
			AggregationBuilders.terms("title");

		termsAggregationBuilder.field("title");
		termsAggregationBuilder.order(
			BucketOrder.aggregation("modifiedDate", false));
		termsAggregationBuilder.size(1);

		MaxAggregationBuilder maxAggregationBuilder = AggregationBuilders.max(
			"modifiedDate");

		maxAggregationBuilder.field("modifiedDate");

		termsAggregationBuilder.subAggregation(maxAggregationBuilder);

		return termsAggregationBuilder;
	}

	private String _getTitle(StringTerms terms) {
		List<StringTerms.Bucket> buckets = terms.getBuckets();

		if (buckets.isEmpty()) {
			return StringPool.BLANK;
		}

		StringTerms.Bucket bucket = buckets.get(0);

		return bucket.getKeyAsString();
	}

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

}