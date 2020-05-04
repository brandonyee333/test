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
import com.liferay.osb.asah.backend.model.IndividualMetric;
import com.liferay.osb.asah.backend.model.IndividualMetricType;
import com.liferay.osb.asah.backend.model.MetricType;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import org.springframework.context.annotation.Configuration;

/**
 * @author Matthew Kong
 */
@Configuration
public class IndividualMetricDogConfiguration extends BaseDogConfiguration {

	@Override
	public AssetResolver<IndividualMetric> getAssetResolver() {
		AssetResolver.Builder<IndividualMetric> builder = AssetResolver.builder(
			"individualId");

		builder.supplier(IndividualMetric::new);

		return builder.build();
	}

	@Override
	public AssetType getAssetType() {
		return AssetType.INDIVIDUAL_METRIC;
	}

	@Override
	public String getCollection() {
		return "individuals";
	}

	@Override
	public Set<MetricResolver> getMetricResolvers(Set<String> selectedMetrics) {
		Set<MetricResolver> metricResolvers = new HashSet<>();

		if (selectedMetrics.contains(
				IndividualMetricType.ANONYMOUS_INDIVIDUALS.getName())) {

			metricResolvers.add(_buildAnonymousIndividualsMetricResolver());
		}

		if (selectedMetrics.contains(
				IndividualMetricType.KNOWN_INDIVIDUALS.getName())) {

			metricResolvers.add(_buildKnownIndividualsMetricResolver());
		}

		if (selectedMetrics.contains(
				IndividualMetricType.TOTAL_INDIVIDUALS.getName())) {

			metricResolvers.add(_buildTotalIndividualsMetricResolver());
		}

		if (selectedMetrics.contains("defaultMetric") ||
			metricResolvers.isEmpty()) {

			metricResolvers.add(_buildKnownIndividualsMetricResolver());
		}

		return metricResolvers;
	}

	@Override
	public Function<String, MetricType> getMetricTypeResolverFunction() {
		return IndividualMetricType::of;
	}

	private MetricResolver _buildAnonymousIndividualsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			IndividualMetricType.ANONYMOUS_INDIVIDUALS);

		builder.setterBiConsumer(
			IndividualMetric::setAnonymousIndividualsMetric);

		return builder.build();
	}

	private MetricResolver _buildKnownIndividualsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			IndividualMetricType.KNOWN_INDIVIDUALS);

		builder.setterBiConsumer(IndividualMetric::setKnownIndividualsMetric);

		return builder.build();
	}

	private MetricResolver _buildTotalIndividualsMetricResolver() {
		MetricResolver.Builder builder = MetricResolver.builder(
			IndividualMetricType.TOTAL_INDIVIDUALS);

		builder.setterBiConsumer(IndividualMetric::setTotalIndividualsMetric);

		return builder.build();
	}

}