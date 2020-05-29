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

import com.liferay.osb.asah.backend.dog.configuration.DogConfiguration;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryHelper;
import com.liferay.osb.asah.backend.dog.resolver.AssetResolver;
import com.liferay.osb.asah.backend.dog.resolver.MetricResolver;
import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.MetricType;
import com.liferay.osb.asah.backend.model.URL;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.petra.string.CharPool;

import graphql.language.Field;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.cardinality.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.pipeline.bucketsort.BucketSortPipelineAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 * @author André Miranda
 */
@Component
public class MetricDog {

	@Autowired
	public MetricDog(List<DogConfiguration> dogConfigurations) {
		_dogConfigurationBag = new DogConfigurationBag(dogConfigurations);
	}

	public <T extends AssetMetric> T getAssetMetric(
		Set<String> fieldNames, SearchQueryContext searchQueryContext) {

		return getAssetMetric(
			fieldNames, searchQueryContext, Collections.emptySet());
	}

	public <T extends AssetMetric> T getAssetMetric(
		Set<String> fieldNames, SearchQueryContext searchQueryContext,
		Set<String> selectedMetrics) {

		boolean includePrevious = false;

		if (fieldNames.contains("previousValue") ||
			fieldNames.contains("trend")) {

			includePrevious = true;
		}

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		AssetResolver<T> assetResolver = dogConfiguration.getAssetResolver();

		Supplier<T> supplier = assetResolver.getSupplier();

		T assetMetric = supplier.get();

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollection(),
			_buildAssetMetricSearchSourceBuilder(
				dogConfiguration, includePrevious, searchQueryContext,
				selectedMetrics));

		if (DogUtil.isEmpty(aggregations)) {
			return assetMetric;
		}

		String assetId = searchQueryContext.getAssetId();

		if (assetId != null) {
			assetMetric.setAssetId(assetId);
		}

		URL url = searchQueryContext.getURL();

		if (!url.equals(URL.any())) {
			assetMetric.setURLs(Collections.singletonList(url.getURL()));
		}

		Set<MetricResolver> metricResolvers =
			dogConfiguration.getMetricResolvers(selectedMetrics);

		Range range = null;

		if (includePrevious) {
			range = aggregations.get("period_ranges");
		}
		else {
			range = aggregations.get("ranges");
		}

		_setURLs(assetMetric, includePrevious, range);

		for (MetricResolver metricResolver : metricResolvers) {
			Metric metric = _getMetric(
				includePrevious, metricResolver.getMapperFunction(),
				metricResolver.getMetricType(), range);

			Optional.ofNullable(
				metricResolver.getSetterBiConsumer()
			).ifPresent(
				setter -> setter.accept(assetMetric, metric)
			);
		}

		return assetMetric;
	}

	public <T extends AssetMetric> List<T> getAssetMetrics(
		FieldSortBuilder fieldSortBuilder,
		SearchQueryContext searchQueryContext, Set<String> selectedMetrics,
		int size, int start) {

		return _getAssetMetrics(
			Collections.emptySet(), getAssetMetricsCount(searchQueryContext),
			fieldSortBuilder, searchQueryContext, selectedMetrics, size, start);
	}

	public <T extends AssetMetric> List<T> getAssetMetrics(
		int count, FieldSortBuilder fieldSortBuilder,
		SearchQueryContext searchQueryContext, Set<String> selectedMetrics,
		int size, int start) {

		return _getAssetMetrics(
			Collections.emptySet(), count, fieldSortBuilder, searchQueryContext,
			selectedMetrics, size, start);
	}

	public <T extends AssetMetric> List<T> getAssetMetrics(
		Set<String> assetIds, FieldSortBuilder fieldSortBuilder,
		SearchQueryContext searchQueryContext, Set<String> selectedMetrics,
		int size, int start) {

		return _getAssetMetrics(
			assetIds, assetIds.size(), fieldSortBuilder, searchQueryContext,
			selectedMetrics, size, start);
	}

	public int getAssetMetricsCount(SearchQueryContext searchQueryContext) {
		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollection(),
			_buildAssetMetricsCountSearchSourceBuilder(searchQueryContext));

		if (DogUtil.isEmpty(aggregations)) {
			return 0;
		}

		Range range = aggregations.get("ranges");

		List<? extends Range.Bucket> rangeBuckets = range.getBuckets();

		if (rangeBuckets.isEmpty()) {
			return 0;
		}

		Range.Bucket rangeBucket = rangeBuckets.get(0);

		Aggregations bucketAggregations = rangeBucket.getAggregations();

		AssetType assetType = searchQueryContext.getAssetType();

		Cardinality cardinality = bucketAggregations.get(assetType.name());

		return (int)cardinality.getValue();
	}

	private SearchSourceBuilder _buildAssetMetricsCountSearchSourceBuilder(
		SearchQueryContext searchQueryContext) {

		AssetType assetType = searchQueryContext.getAssetType();

		CardinalityAggregationBuilder cardinalityAggregationBuilder =
			AggregationBuilders.cardinality(assetType.name());

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(assetType);

		AssetResolver<AssetMetric> assetResolver =
			dogConfiguration.getAssetResolver();

		cardinalityAggregationBuilder.script(
			_createPrimaryKeyAggregationScript(
				assetResolver.getAssetIdFieldName()));

		cardinalityAggregationBuilder.precisionThreshold(1000);

		return _searchQueryHelper.createRangeSearchSourceBuilder(
			cardinalityAggregationBuilder, Optional.empty(),
			Collections.emptySet(), assetResolver,
			dogConfiguration.getQueryBuilder(), searchQueryContext);
	}

	private SearchSourceBuilder _buildAssetMetricSearchSourceBuilder(
		DogConfiguration dogConfiguration, boolean includePrevious,
		SearchQueryContext searchQueryContext, Set<String> selectedMetrics) {

		Set<MetricResolver> metricResolvers =
			dogConfiguration.getMetricResolvers(selectedMetrics);

		AssetResolver<AssetMetric> assetResolver =
			dogConfiguration.getAssetResolver();

		Set<AggregationBuilder> aggregationBuilders = _getAggregationBuilders(
			metricResolvers);

		TermsAggregationBuilder urlAggregationBuilder =
			AggregationBuilders.terms("urls");

		urlAggregationBuilder.field(
			_searchQueryHelper.getURLFieldName(
				searchQueryContext.getAssetType()));
		urlAggregationBuilder.size(Integer.MAX_VALUE);

		aggregationBuilders.add(urlAggregationBuilder);

		if (includePrevious) {
			return _searchQueryHelper.createPeriodRangeSearchSourceBuilder(
				aggregationBuilders,
				Optional.of(
					assetResolver.getAssetId(searchQueryContext.getAssetId())),
				_getPipelineAggregationBuilders(metricResolvers),
				dogConfiguration.getQueryBuilder(), searchQueryContext);
		}

		return _searchQueryHelper.createRangeSearchSourceBuilder(
			aggregationBuilders,
			Optional.of(
				assetResolver.getAssetId(searchQueryContext.getAssetId())),
			_getPipelineAggregationBuilders(metricResolvers),
			dogConfiguration.getQueryBuilder(), searchQueryContext);
	}

	private SearchSourceBuilder _buildAssetMetricsSearchSourceBuilder(
		Set<String> assetIds, int count, DogConfiguration dogConfiguration,
		FieldSortBuilder fieldSortBuilder,
		SearchQueryContext searchQueryContext, Set<String> selectedMetrics,
		int size, int start) {

		AssetResolver<AssetMetric> assetResolver =
			dogConfiguration.getAssetResolver();

		TermsAggregationBuilder termsAggregationBuilder =
			_createTermsAggregationBuilder(assetResolver, count);

		Set<AggregationBuilder> aggregationBuilders = _getAggregationBuilders(
			dogConfiguration.getMetricResolvers(selectedMetrics));

		aggregationBuilders.forEach(termsAggregationBuilder::subAggregation);

		Set<PipelineAggregationBuilder> pipelineAggregationBuilders =
			_getPipelineAggregationBuilders(
				dogConfiguration.getMetricResolvers(selectedMetrics));

		pipelineAggregationBuilders.forEach(
			termsAggregationBuilder::subAggregation);

		termsAggregationBuilder.subAggregation(
			_createBucketSortPipelineAggregationBuilder(
				fieldSortBuilder, size, start, assetResolver.getSupplier()));

		return _searchQueryHelper.createRangeSearchSourceBuilder(
			termsAggregationBuilder, Optional.empty(), assetIds, assetResolver,
			dogConfiguration.getQueryBuilder(), searchQueryContext);
	}

	private BucketSortPipelineAggregationBuilder
		_createBucketSortPipelineAggregationBuilder(
			FieldSortBuilder fieldSortBuilder, int size, int start,
			Supplier<AssetMetric> supplier) {

		if (fieldSortBuilder == null) {
			fieldSortBuilder = _createDefaultFieldSortBuilder(supplier.get());
		}

		BucketSortPipelineAggregationBuilder
			bucketSortPipelineAggregationBuilder =
				PipelineAggregatorBuilders.bucketSort(
					"bucket_sort_agg",
					Collections.singletonList(fieldSortBuilder));

		bucketSortPipelineAggregationBuilder.from(start);
		bucketSortPipelineAggregationBuilder.size(size);

		return bucketSortPipelineAggregationBuilder;
	}

	private FieldSortBuilder _createDefaultFieldSortBuilder(
		AssetMetric assetMetric) {

		Metric defaultMetric = assetMetric.getDefaultMetric();

		MetricType metricType = defaultMetric.getMetricType();

		return SortBuilderUtil.fieldSort(
			metricType.getAggregationName(), SortOrder.DESC);
	}

	private Script _createPrimaryKeyAggregationScript(String assetIdFieldName) {
		return new Script(
			Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
			"doc[params.assetIdFieldName].value + '@' + doc['title'].value + " +
				"'@' + doc['dataSourceId'].value",
			new HashMap() {
				{
					put("assetIdFieldName", assetIdFieldName);
				}
			});
	}

	private TermsAggregationBuilder _createTermsAggregationBuilder(
		AssetResolver<AssetMetric> assetResolver, int size) {

		TermsAggregationBuilder termsAggregationBuilder =
			AggregationBuilders.terms("terms");

		termsAggregationBuilder.script(
			_createPrimaryKeyAggregationScript(
				assetResolver.getAssetIdFieldName()));

		if (size > 0) {
			termsAggregationBuilder.size(size);
		}

		return termsAggregationBuilder;
	}

	private Set<AggregationBuilder> _getAggregationBuilders(
		Set<MetricResolver> metricResolvers) {

		Stream<MetricResolver> stream = metricResolvers.stream();

		return stream.flatMap(
			this::_getAggregationsBuildersStream
		).collect(
			Collectors.toSet()
		);
	}

	private Stream<AggregationBuilder> _getAggregationsBuildersStream(
		MetricResolver metricResolver) {

		Set<AggregationBuilder> aggregationBuilders =
			metricResolver.getAggregationBuilders();

		return aggregationBuilders.stream();
	}

	private <T extends AssetMetric> List<T> _getAssetMetrics(
		Set<String> assetIds, int count, FieldSortBuilder fieldSortBuilder,
		SearchQueryContext searchQueryContext, Set<String> selectedMetrics,
		int size, int start) {

		if (count == 0) {
			return Collections.emptyList();
		}

		DogConfiguration dogConfiguration =
			_dogConfigurationBag.getDogConfiguration(
				searchQueryContext.getAssetType());

		Aggregations aggregations = _dataDog.queryAggregations(
			dogConfiguration.getCollection(),
			_buildAssetMetricsSearchSourceBuilder(
				assetIds, count, dogConfiguration, fieldSortBuilder,
				searchQueryContext, selectedMetrics, size, start));

		if (DogUtil.isEmpty(aggregations)) {
			return Collections.emptyList();
		}

		List<T> assetMetrics = new ArrayList<>();

		AssetResolver<T> assetResolver = dogConfiguration.getAssetResolver();

		Terms terms = _getTermsAggregationResult(aggregations);

		for (Terms.Bucket termsBucket : terms.getBuckets()) {
			Supplier<T> supplier = assetResolver.getSupplier();

			T assetMetric = supplier.get();

			BiConsumer<T, String> assetIdSetterBiConsumer =
				assetResolver.getSetterBiConsumer();

			String[] keys = StringUtils.splitPreserveAllTokens(
				termsBucket.getKeyAsString(), CharPool.AT);

			assetIdSetterBiConsumer.accept(assetMetric, keys[0]);

			assetMetric.setAssetTitle(keys[1]);
			assetMetric.setDataSourceId(keys[2]);

			_setModelMetrics(
				termsBucket.getAggregations(), assetMetric,
				dogConfiguration.getMetricResolvers(selectedMetrics));

			assetMetrics.add(assetMetric);
		}

		return assetMetrics;
	}

	private Metric _getMetric(
		boolean includePrevious, Function<Aggregations, Double> metricResolver,
		MetricType metricType, Range range) {

		if (range == null) {
			return new Metric(metricType);
		}

		Metric metric = new Metric(metricType);

		List<? extends Range.Bucket> rangeBuckets = range.getBuckets();

		if (rangeBuckets.isEmpty()) {
			return metric;
		}

		if (includePrevious) {
			Range.Bucket previousBucket = rangeBuckets.get(0);

			if (previousBucket != null) {
				metric.setPreviousValue(
					metricResolver.apply(previousBucket.getAggregations()));
			}

			Range.Bucket currentBucket = rangeBuckets.get(1);

			metric.setValue(
				metricResolver.apply(currentBucket.getAggregations()));
		}
		else {
			Range.Bucket currentBucket = rangeBuckets.get(0);

			metric.setValue(
				metricResolver.apply(currentBucket.getAggregations()));
		}

		return metric;
	}

	private Set<PipelineAggregationBuilder> _getPipelineAggregationBuilders(
		Set<MetricResolver> metricResolvers) {

		Stream<MetricResolver> stream = metricResolvers.stream();

		return stream.flatMap(
			this::_getPipelineAggregationBuildersStream
		).collect(
			Collectors.toSet()
		);
	}

	private Stream<PipelineAggregationBuilder>
		_getPipelineAggregationBuildersStream(MetricResolver metricResolver) {

		Set<PipelineAggregationBuilder> pipelineAggregationBuilders =
			metricResolver.getPipelineAggregationBuilders();

		return pipelineAggregationBuilders.stream();
	}

	private Terms _getTermsAggregationResult(Aggregations aggregations) {
		Range range = aggregations.get("ranges");

		List<? extends Range.Bucket> rangeBuckets = range.getBuckets();

		Range.Bucket rangeBucket = rangeBuckets.get(0);

		Aggregations bucketAggregations = rangeBucket.getAggregations();

		return bucketAggregations.get("terms");
	}

	private void _setModelMetrics(
		Aggregations aggregations, AssetMetric assetMetric,
		Set<MetricResolver> metricResolvers) {

		for (MetricResolver metricResolver : metricResolvers) {
			Metric metric = new Metric(metricResolver.getMetricType());

			Function<Aggregations, Double> mapperFunction =
				metricResolver.getMapperFunction();

			metric.setValue(mapperFunction.apply(aggregations));

			Optional.ofNullable(
				metricResolver.getSetterBiConsumer()
			).ifPresent(
				metricSetter -> metricSetter.accept(assetMetric, metric)
			);
		}
	}

	private void _setURLs(
		AssetMetric assetMetric, boolean includePrevious, Range range) {

		List<? extends Range.Bucket> rangeBuckets = range.getBuckets();

		if (rangeBuckets.isEmpty()) {
			return;
		}

		Range.Bucket rangeBucket = null;

		if (includePrevious) {
			rangeBucket = rangeBuckets.get(1);
		}
		else {
			rangeBucket = rangeBuckets.get(0);
		}

		Aggregations bucketAggregations = rangeBucket.getAggregations();

		Terms terms = bucketAggregations.get("urls");

		List<? extends Terms.Bucket> termsBuckets = terms.getBuckets();

		if (termsBuckets.isEmpty()) {
			assetMetric.setURLs(Collections.emptyList());

			return;
		}

		Stream<? extends Terms.Bucket> stream = termsBuckets.stream();

		List<String> urls = stream.map(
			Terms.Bucket::getKeyAsString
		).collect(
			Collectors.toList()
		);

		assetMetric.setURLs(urls);
	}

	@Autowired
	private DataDog _dataDog;

	private final DogConfigurationBag _dogConfigurationBag;

	@Autowired
	private SearchQueryHelper _searchQueryHelper;

}