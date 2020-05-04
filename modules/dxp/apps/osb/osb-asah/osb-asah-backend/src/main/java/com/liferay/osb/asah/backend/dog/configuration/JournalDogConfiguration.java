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
import com.liferay.osb.asah.backend.model.JournalMetric;
import com.liferay.osb.asah.backend.model.JournalMetricType;
import com.liferay.osb.asah.backend.model.MetricType;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.springframework.context.annotation.Configuration;

/**
 * @author Marcellus Tavares
 */
@Configuration
public class JournalDogConfiguration extends BaseDogConfiguration {

	@Override
	public AssetResolver<JournalMetric> getAssetResolver() {
		AssetResolver.Builder<JournalMetric> builder = AssetResolver.builder(
			"assetId");

		builder.searchableFieldNames("assetId", "title");
		builder.supplier(JournalMetric::new);

		return builder.build();
	}

	@Override
	public AssetType getAssetType() {
		return AssetType.JOURNAL;
	}

	@Override
	public String getCollection() {
		return "journals";
	}

	@Override
	public Set<MetricResolver> getMetricResolvers(Set<String> selectedMetrics) {
		Set<MetricResolver> metricResolvers = new HashSet<>();

		if (selectedMetrics.contains(JournalMetricType.VIEWS.getName())) {
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
		return JournalMetricType::of;
	}

	private MetricResolver _buildViewsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			JournalMetricType.VIEWS);

		builder.bucketOrderTermsAggregationBuilderConsumer(
			createBucketOrderTermsAggregationBuilderConsumer(
				JournalMetricType.VIEWS));
		builder.setterBiConsumer(JournalMetric::setViewsMetric);

		return builder.build();
	}

}