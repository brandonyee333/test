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
import com.liferay.osb.asah.backend.model.BlogMetric;
import com.liferay.osb.asah.backend.model.BlogMetricType;
import com.liferay.osb.asah.backend.model.MetricType;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;

import org.springframework.context.annotation.Configuration;

/**
 * @author Marcellus Tavares
 */
@Configuration
public class BlogDogConfiguration extends BaseDogConfiguration {

	@Override
	public AssetResolver<BlogMetric> getAssetResolver() {
		AssetResolver.Builder<BlogMetric> builder = AssetResolver.builder(
			"assetId");

		builder.searchableFieldNames("assetId", "title");
		builder.supplier(BlogMetric::new);

		return builder.build();
	}

	@Override
	public AssetType getAssetType() {
		return AssetType.BLOG;
	}

	@Override
	public String getCollection() {
		return "blogs";
	}

	@Override
	public Set<MetricResolver> getMetricResolvers(Set<String> selectedMetrics) {
		Set<MetricResolver> metricResolvers = new HashSet<>();

		if (selectedMetrics.contains(BlogMetricType.CLICKS.getName())) {
			metricResolvers.add(_buildClicksMetricResolver());
		}

		if (selectedMetrics.contains(BlogMetricType.COMMENTS.getName())) {
			metricResolvers.add(_buildCommentsMetricResolver());
		}

		if (selectedMetrics.contains(BlogMetricType.READING_TIME.getName())) {
			metricResolvers.add(_buildReadingTimeMetricResolver());
		}

		if (selectedMetrics.contains(BlogMetricType.RATINGS.getName())) {
			metricResolvers.add(_buildRatingsMetricResolver());
		}

		if (selectedMetrics.contains(BlogMetricType.VIEWS.getName())) {
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
		return BlogMetricType::of;
	}

	private MetricResolver _buildClicksMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			BlogMetricType.CLICKS);

		builder.setterBiConsumer(BlogMetric::setClicksMetric);

		return builder.build();
	}

	private MetricResolver _buildCommentsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			BlogMetricType.COMMENTS);

		builder.setterBiConsumer(BlogMetric::setCommentsMetric);

		return builder.build();
	}

	private MetricResolver _buildRatingsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			BlogMetricType.RATINGS);

		createRatingsWeightedAvgAggregations(
			builder, BlogMetricType.RATINGS.getAggregationName(), "ratings");

		builder.mapperFunction(
			aggregations -> getBucketScriptAggregationValue(
				aggregations, BlogMetricType.RATINGS));
		builder.setterBiConsumer(BlogMetric::setRatingsMetric);

		return builder.build();
	}

	private MetricResolver _buildReadingTimeMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			BlogMetricType.READING_TIME);

		builder.aggregate(
			_createAvgAggregationBuilder(BlogMetricType.READING_TIME));
		builder.mapperFunction(
			aggregations -> getAvgAggregationValue(
				aggregations, BlogMetricType.READING_TIME));
		builder.setterBiConsumer(BlogMetric::setReadingTimeMetric);

		return builder.build();
	}

	private MetricResolver _buildViewsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			BlogMetricType.VIEWS);

		builder.bucketOrderTermsAggregationBuilderConsumer(
			createBucketOrderTermsAggregationBuilderConsumer(
				BlogMetricType.VIEWS));
		builder.setterBiConsumer(BlogMetric::setViewsMetric);

		return builder.build();
	}

	private AvgAggregationBuilder _createAvgAggregationBuilder(
		MetricType metricType) {

		AvgAggregationBuilder avgAggregationBuilder = AggregationBuilders.avg(
			metricType.getAggregationName());

		avgAggregationBuilder.field(metricType.getFieldName());

		return avgAggregationBuilder;
	}

}