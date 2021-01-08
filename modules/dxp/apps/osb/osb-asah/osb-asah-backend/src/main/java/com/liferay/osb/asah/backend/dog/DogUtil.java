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

package com.liferay.osb.asah.backend.dog;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.liferay.osb.asah.backend.dog.configuration.DogConfiguration;
import com.liferay.osb.asah.backend.dog.resolver.AssetResolver;
import com.liferay.osb.asah.backend.dog.resolver.MetricResolver;
import com.liferay.osb.asah.backend.model.AssetId;
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.logging.Log;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.NumericMetricsAggregation;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;

/**
 * @author Marcellus Tavares
 */
public class DogUtil {

	public static void addTermsAggregationBuilderOrder(
		MetricResolver metricResolver,
		TermsAggregationBuilder termsAggregationBuilder) {

		Optional<Consumer<TermsAggregationBuilder>>
			bucketOrderTermsAggregationBuilderConsumerOptional =
				metricResolver.
					getBucketOrderTermsAggregationBuilderConsumerOptional();

		bucketOrderTermsAggregationBuilderConsumerOptional.ifPresent(
			termsAggregationBuilderConsumer ->
				termsAggregationBuilderConsumer.accept(
					termsAggregationBuilder));
	}

	public static void addTermsAggregationBuilderSubaggregations(
		MetricResolver metricResolver,
		TermsAggregationBuilder termsAggregationBuilder) {

		Set<AggregationBuilder> aggregationBuilders =
			metricResolver.getAggregationBuilders();

		aggregationBuilders.forEach(termsAggregationBuilder::subAggregation);

		Set<PipelineAggregationBuilder> pipelineAggregationBuilders =
			metricResolver.getPipelineAggregationBuilders();

		pipelineAggregationBuilders.forEach(
			termsAggregationBuilder::subAggregation);
	}

	public static SearchSourceBuilder buildSearchSourceBuilder(
		FieldSortBuilder fieldSortBuilder, QueryBuilder keywordsQueryBuilder,
		int size, int start) {

		if (fieldSortBuilder != null) {
			return buildSearchSourceBuilder(
				Collections.singletonList(fieldSortBuilder),
				keywordsQueryBuilder, size, start);
		}

		return buildSearchSourceBuilder(
			Collections.emptyList(), keywordsQueryBuilder, size, start);
	}

	public static SearchSourceBuilder buildSearchSourceBuilder(
		List<FieldSortBuilder> fieldSortBuilders, QueryBuilder queryBuilder,
		int size, int start) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.from(start);
		searchSourceBuilder.query(queryBuilder);
		searchSourceBuilder.size(size);

		for (FieldSortBuilder fieldSortBuilder : fieldSortBuilders) {
			searchSourceBuilder.sort(fieldSortBuilder);
		}

		return searchSourceBuilder;
	}

	public static SearchSourceBuilder buildSearchSourceBuilder(
		String[] fetchSourceExcludes, QueryBuilder queryBuilder,
		Object[] searchAfter, int size, SortBuilder<?> sortBuilder) {

		SearchSourceBuilder searchSourceBuilder =
			SearchSourceBuilder.searchSource();

		searchSourceBuilder.fetchSource(null, fetchSourceExcludes);

		if (queryBuilder != null) {
			searchSourceBuilder.query(queryBuilder);
		}

		if (searchAfter != null) {
			searchSourceBuilder.searchAfter(searchAfter);
		}

		searchSourceBuilder.size(size);

		if (sortBuilder == null) {
			searchSourceBuilder.sort(SortBuilderUtil.fieldSort("id"));
		}
		else {
			searchSourceBuilder.sort(sortBuilder);
		}

		return searchSourceBuilder;
	}

	public static <T> ResultBag<T> createResultBag(
		Class<T> modelClass, SearchHits searchHits) {

		return createResultBag(
			_createDefaultSearchHitModelMapperFunction(modelClass), searchHits);
	}

	public static <T> ResultBag<T> createResultBag(
		Function<SearchHit, T> searchHitModelMapperFunction,
		SearchHits searchHits) {

		ResultBag<T> resultBag = new ResultBag<>();

		List<T> models = new ArrayList<>();

		for (SearchHit searchHit : searchHits) {
			models.add(searchHitModelMapperFunction.apply(searchHit));
		}

		resultBag.setResults(models);
		resultBag.setTotal(searchHits.getTotalHits());

		return resultBag;
	}

	public static Optional<AssetId> getAssetIdOptional(
		String assetId, DogConfiguration dogConfiguration) {

		AssetResolver<?> assetResolver = dogConfiguration.getAssetResolver();

		if (assetResolver == null) {
			return Optional.empty();
		}

		return Optional.ofNullable(
			assetId
		).map(
			assetResolver::getAssetId
		);
	}

	public static long getCardinalityAsLong(Cardinality cardinality) {
		if (_isValid(cardinality)) {
			return cardinality.getValue();
		}

		return 0L;
	}

	public static Double getSingleValue(
		NumericMetricsAggregation.SingleValue singleValue) {

		if (_isValid(singleValue)) {
			return singleValue.value();
		}

		return 0D;
	}

	public static boolean isEmpty(Aggregations aggregations) {
		if (aggregations == null) {
			return true;
		}

		List<Aggregation> aggregationList = aggregations.asList();

		if (aggregationList.isEmpty()) {
			return true;
		}

		return false;
	}

	public static void logSearchResponseErrors(
		Log log, SearchResponse searchResponse) {

		for (ShardSearchFailure shardSearchFailure :
				searchResponse.getShardFailures()) {

			log.error(shardSearchFailure.getCause());
		}
	}

	private static <T> Function<SearchHit, T>
		_createDefaultSearchHitModelMapperFunction(Class<T> modelClass) {

		return (SearchHit searchHit) -> {
			try {
				return _objectMapper.readValue(
					searchHit.getSourceAsString(), modelClass);
			}
			catch (IOException ioe) {
				throw new RuntimeException(
					"Unable to process search request", ioe);
			}
		};
	}

	private static boolean _isValid(
		NumericMetricsAggregation.SingleValue singleValue) {

		if ((singleValue == null) || Double.isNaN(singleValue.value()) ||
			Double.isInfinite(singleValue.value())) {

			return false;
		}

		return true;
	}

	private static final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new JavaTimeModule());
			registerModule(new Jdk8Module());
			registerModule(new JsonOrgModule());
		}
	};

}