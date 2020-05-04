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

package com.liferay.osb.asah.backend.dog.configuration;

import com.liferay.osb.asah.backend.dog.resolver.AssetResolver;
import com.liferay.osb.asah.backend.dog.resolver.MetricResolver;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.CustomAssetMetric;
import com.liferay.osb.asah.backend.model.CustomAssetMetricType;
import com.liferay.osb.asah.backend.model.MetricType;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.avg.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;

import org.springframework.context.annotation.Configuration;

/**
 * @author Marcellus Tavares
 */
@Configuration
public class CustomAssetDogConfiguration extends BaseDogConfiguration {

	@Override
	public AssetResolver<CustomAssetMetric> getAssetResolver() {
		AssetResolver.Builder<CustomAssetMetric> builder =
			AssetResolver.builder("assetPrimaryKey");

		builder.searchableFieldNames("assetId", "title");
		builder.supplier(CustomAssetMetric::new);

		return builder.build();
	}

	@Override
	public AssetType getAssetType() {
		return AssetType.CUSTOM;
	}

	@Override
	public String getCollection() {
		return "custom-assets";
	}

	@Override
	public Set<MetricResolver> getMetricResolvers(Set<String> selectedMetrics) {
		Set<MetricResolver> metricResolvers = new HashSet<>();

		if (selectedMetrics.contains(
				CustomAssetMetricType.ABANDONMENTS.getName())) {

			metricResolvers.add(_buildAbandonmentsMetricResolver());
		}

		if (selectedMetrics.contains(CustomAssetMetricType.CLICKS.getName())) {
			metricResolvers.add(_buildClicksMetricResolver());
		}

		if (selectedMetrics.contains(
				CustomAssetMetricType.COMPLETION_TIME.getName())) {

			metricResolvers.add(_buildCompletionTimeMetricResolver());
		}

		if (selectedMetrics.contains(
				CustomAssetMetricType.DOWNLOADS.getName())) {

			metricResolvers.add(_buildDownloadsMetricResolver());
		}

		if (selectedMetrics.contains(
				CustomAssetMetricType.READING_TIME.getName())) {

			metricResolvers.add(_buildReadingTimeMetricResolver());
		}

		if (selectedMetrics.contains(
				CustomAssetMetricType.SUBMISSIONS.getName())) {

			metricResolvers.add(_buildSubmissionsMetricResolver());
		}

		if (selectedMetrics.contains(CustomAssetMetricType.VIEWS.getName())) {
			metricResolvers.add(_buildViewsMetricResolver());
		}

		if (selectedMetrics.contains("defaultMetric") ||
			metricResolvers.isEmpty()) {

			metricResolvers.add(_buildViewsMetricResolver());
		}

		return metricResolvers;
	}

	@Override
	public Function<String, MetricType> getMetricTypeResolverFunction() {
		return CustomAssetMetricType::of;
	}

	private MetricResolver _buildAbandonmentsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			CustomAssetMetricType.ABANDONMENTS);

		_createAbandonmentsAggregations(builder);

		builder.mapperFunction(
			aggregations -> getBucketScriptAggregationValue(
				aggregations, CustomAssetMetricType.ABANDONMENTS));
		builder.setterBiConsumer(CustomAssetMetric::setAbandonmentsMetric);

		return builder.build();
	}

	private MetricResolver _buildClicksMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			CustomAssetMetricType.CLICKS);

		builder.setterBiConsumer(CustomAssetMetric::setClicksMetric);

		return builder.build();
	}

	private MetricResolver _buildCompletionTimeMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			CustomAssetMetricType.COMPLETION_TIME);

		_createCompletionTimeAggregations(builder);

		builder.mapperFunction(
			aggregations -> getBucketScriptAggregationValue(
				aggregations, CustomAssetMetricType.COMPLETION_TIME));
		builder.setterBiConsumer(CustomAssetMetric::setCompletionTimeMetric);

		return builder.build();
	}

	private MetricResolver _buildDownloadsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			CustomAssetMetricType.DOWNLOADS);

		builder.setterBiConsumer(CustomAssetMetric::setDownloadsMetric);

		return builder.build();
	}

	private MetricResolver _buildReadingTimeMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			CustomAssetMetricType.READING_TIME);

		builder.aggregate(
			_createAvgAggregationBuilder(CustomAssetMetricType.READING_TIME));
		builder.mapperFunction(
			aggregations -> getAvgAggregationValue(
				aggregations, CustomAssetMetricType.READING_TIME));
		builder.setterBiConsumer(CustomAssetMetric::setReadingTimeMetric);

		return builder.build();
	}

	private MetricResolver _buildSubmissionsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			CustomAssetMetricType.SUBMISSIONS);

		builder.setterBiConsumer(CustomAssetMetric::setSubmissionsMetric);

		return builder.build();
	}

	private MetricResolver _buildViewsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			CustomAssetMetricType.VIEWS);

		builder.setterBiConsumer(CustomAssetMetric::setViewsMetric);

		return builder.build();
	}

	private void _createAbandonmentsAggregations(
		MetricResolver.Builder builder) {

		builder.aggregate(
			_createAggregationBuilder(
				CustomAssetMetricType.ABANDONMENTS,
				CustomAssetMetricType.ABANDONMENTS.getAggregationName() +
					"_field"));
		builder.aggregate(
			_createAggregationBuilder(CustomAssetMetricType.VIEWS));
		builder.aggregate(
			createDivisionPipelineAggregationBuilder(
				CustomAssetMetricType.ABANDONMENTS,
				CustomAssetMetricType.VIEWS));
	}

	private AggregationBuilder _createAggregationBuilder(
		MetricType metricType) {

		return _createAggregationBuilder(
			metricType, metricType.getAggregationName());
	}

	private AggregationBuilder _createAggregationBuilder(
		MetricType metricType, String name) {

		SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(
			name);

		sumAggregationBuilder.field(metricType.getFieldName());

		return sumAggregationBuilder;
	}

	private AvgAggregationBuilder _createAvgAggregationBuilder(
		MetricType metricType) {

		AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg(
			metricType.getAggregationName());

		avgAggregationBuilder.field(metricType.getFieldName());

		return avgAggregationBuilder;
	}

	private void _createCompletionTimeAggregations(
		MetricResolver.Builder builder) {

		builder.aggregate(
			_createAggregationBuilder(
				CustomAssetMetricType.COMPLETION_TIME,
				CustomAssetMetricType.COMPLETION_TIME.getAggregationName() +
					"_field"));
		builder.aggregate(
			_createAggregationBuilder(CustomAssetMetricType.SUBMISSIONS));
		builder.aggregate(
			createDivisionPipelineAggregationBuilder(
				CustomAssetMetricType.COMPLETION_TIME,
				CustomAssetMetricType.SUBMISSIONS));
	}

}