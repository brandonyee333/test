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
import com.liferay.osb.asah.backend.model.DocumentLibraryMetric;
import com.liferay.osb.asah.backend.model.DocumentLibraryMetricType;
import com.liferay.osb.asah.backend.model.MetricType;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.springframework.context.annotation.Configuration;

/**
 * @author Marcellus Tavares
 */
@Configuration
public class DocumentLibraryDogConfiguration extends BaseDogConfiguration {

	@Override
	public AssetResolver<DocumentLibraryMetric> getAssetResolver() {
		AssetResolver.Builder<DocumentLibraryMetric> builder =
			AssetResolver.builder("assetId");

		builder.searchableFieldNames("assetId", "title");
		builder.supplier(DocumentLibraryMetric::new);

		return builder.build();
	}

	@Override
	public AssetType getAssetType() {
		return AssetType.DOCUMENT;
	}

	@Override
	public String getCollection() {
		return "document-libraries";
	}

	@Override
	public Set<MetricResolver> getMetricResolvers(Set<String> selectedMetrics) {
		Set<MetricResolver> metricResolvers = new HashSet<>();

		if (selectedMetrics.contains(
				DocumentLibraryMetricType.COMMENTS.getName())) {

			metricResolvers.add(_buildCommentsMetricResolver());
		}

		if (selectedMetrics.contains(
				DocumentLibraryMetricType.DOWNLOADS.getName())) {

			metricResolvers.add(_buildDownloadsMetricResolver());
		}

		if (selectedMetrics.contains(
				DocumentLibraryMetricType.PREVIEWS.getName())) {

			metricResolvers.add(_buildPreviewsTimeMetricResolver());
		}

		if (selectedMetrics.contains(
				DocumentLibraryMetricType.RATINGS.getName())) {

			metricResolvers.add(_buildRatingsMetricResolver());
		}

		if (selectedMetrics.contains("defaultMetric") ||
			metricResolvers.isEmpty()) {

			metricResolvers.add(_buildDownloadsMetricResolver());
		}

		return metricResolvers;
	}

	@Override
	public Function<String, MetricType> getMetricTypeResolverFunction() {
		return DocumentLibraryMetricType::of;
	}

	private MetricResolver _buildCommentsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			DocumentLibraryMetricType.COMMENTS);

		builder.setterBiConsumer(DocumentLibraryMetric::setCommentsMetric);

		return builder.build();
	}

	private MetricResolver _buildDownloadsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			DocumentLibraryMetricType.DOWNLOADS);

		builder.bucketOrderTermsAggregationBuilderConsumer(
			createBucketOrderTermsAggregationBuilderConsumer(
				DocumentLibraryMetricType.DOWNLOADS));
		builder.setterBiConsumer(DocumentLibraryMetric::setDownloadsMetric);

		return builder.build();
	}

	private MetricResolver _buildPreviewsTimeMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			DocumentLibraryMetricType.PREVIEWS);

		builder.setterBiConsumer(DocumentLibraryMetric::setPreviewsMetric);

		return builder.build();
	}

	private MetricResolver _buildRatingsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			DocumentLibraryMetricType.RATINGS);

		createRatingsWeightedAvgAggregations(
			builder, DocumentLibraryMetricType.RATINGS.getAggregationName(),
			"ratings");

		builder.mapperFunction(
			aggregations -> getBucketScriptAggregationValue(
				aggregations, DocumentLibraryMetricType.RATINGS));
		builder.setterBiConsumer(DocumentLibraryMetric::setRatingsMetric);

		return builder.build();
	}

}