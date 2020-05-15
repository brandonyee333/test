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
import com.liferay.osb.asah.backend.model.FormMetric;
import com.liferay.osb.asah.backend.model.FormMetricType;
import com.liferay.osb.asah.backend.model.MetricType;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;

import org.springframework.context.annotation.Configuration;

/**
 * @author Marcellus Tavares
 */
@Configuration
public class FormDogConfiguration extends BaseDogConfiguration {

	@Override
	public AssetResolver<FormMetric> getAssetResolver() {
		AssetResolver.Builder<FormMetric> builder = AssetResolver.builder(
			"assetId");

		builder.searchableFieldNames("assetId", "title");
		builder.supplier(FormMetric::new);

		return builder.build();
	}

	@Override
	public AssetType getAssetType() {
		return AssetType.FORM;
	}

	@Override
	public String getCollection() {
		return "forms";
	}

	@Override
	public Set<MetricResolver> getMetricResolvers(Set<String> selectedMetrics) {
		Set<MetricResolver> metricResolvers = new HashSet<>();

		if (selectedMetrics.contains(FormMetricType.ABANDONMENTS.getName())) {
			metricResolvers.add(_buildAbandonmentsMetricResolver());
		}

		if (selectedMetrics.contains(
				FormMetricType.COMPLETION_TIME.getName())) {

			metricResolvers.add(_buildCompletionTimeMetricResolver());
		}

		if (selectedMetrics.contains(FormMetricType.SUBMISSIONS.getName())) {
			metricResolvers.add(_buildSubmissionsMetricResolver());
		}

		if (selectedMetrics.contains(FormMetricType.VIEWS.getName())) {
			metricResolvers.add(_buildViewsMetricResolver());
		}

		if (selectedMetrics.contains("defaultMetric") ||
			metricResolvers.isEmpty()) {

			metricResolvers.add(_buildSubmissionsMetricResolver());
		}

		return metricResolvers;
	}

	@Override
	public Function<String, MetricType> getMetricTypeResolverFunction() {
		return FormMetricType::of;
	}

	private MetricResolver _buildAbandonmentsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			FormMetricType.ABANDONMENTS);

		_createAbandonmentsAggregations(builder);

		builder.mapperFunction(
			aggregations -> getBucketScriptAggregationValue(
				aggregations, FormMetricType.ABANDONMENTS));
		builder.setterBiConsumer(FormMetric::setAbandonmentsMetric);

		return builder.build();
	}

	private MetricResolver _buildCompletionTimeMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			FormMetricType.COMPLETION_TIME);

		_createCompletionTimeAggregations(builder);

		builder.mapperFunction(
			aggregations -> getBucketScriptAggregationValue(
				aggregations, FormMetricType.COMPLETION_TIME));
		builder.setterBiConsumer(FormMetric::setCompletionTimeMetric);

		return builder.build();
	}

	private MetricResolver _buildSubmissionsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			FormMetricType.SUBMISSIONS);

		builder.bucketOrderTermsAggregationBuilderConsumer(
			createBucketOrderTermsAggregationBuilderConsumer(
				FormMetricType.SUBMISSIONS));
		builder.setterBiConsumer(FormMetric::setSubmissionsMetric);

		return builder.build();
	}

	private MetricResolver _buildViewsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			FormMetricType.VIEWS);

		builder.setterBiConsumer(FormMetric::setViewsMetric);

		return builder.build();
	}

	private void _createAbandonmentsAggregations(
		MetricResolver.Builder builder) {

		builder.aggregate(
			_createAggregationBuilder(
				FormMetricType.ABANDONMENTS,
				FormMetricType.ABANDONMENTS.getAggregationName() + "_field"));
		builder.aggregate(_createAggregationBuilder(FormMetricType.VIEWS));
		builder.aggregate(
			createDivisionPipelineAggregationBuilder(
				FormMetricType.ABANDONMENTS, FormMetricType.VIEWS));
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

		sumAggregationBuilder.field(metricType.getAggregationName());

		return sumAggregationBuilder;
	}

	private void _createCompletionTimeAggregations(
		MetricResolver.Builder builder) {

		builder.aggregate(
			_createAggregationBuilder(
				FormMetricType.COMPLETION_TIME,
				FormMetricType.COMPLETION_TIME.getAggregationName() +
					"_field"));
		builder.aggregate(
			_createAggregationBuilder(FormMetricType.SUBMISSIONS));
		builder.aggregate(
			createDivisionPipelineAggregationBuilder(
				FormMetricType.COMPLETION_TIME, FormMetricType.SUBMISSIONS));
	}

}