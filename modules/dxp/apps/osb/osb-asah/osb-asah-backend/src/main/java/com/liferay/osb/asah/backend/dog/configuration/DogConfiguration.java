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
import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.MetricType;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author Marcellus Tavares
 */
public interface DogConfiguration {

	public <T extends AssetMetric> AssetResolver<T> getAssetResolver();

	public AssetType getAssetType();

	public String getCollection();

	public default MetricResolver getMetricResolver(MetricType metricType) {
		Set<MetricResolver> metricResolvers = getMetricResolvers(
			Collections.singleton(metricType.getName()));

		Stream<MetricResolver> stream = metricResolvers.stream();

		Optional<MetricResolver> metricResolverOptional = stream.filter(
			metricResolver -> metricType.equals(metricResolver.getMetricType())
		).findAny();

		return metricResolverOptional.orElseThrow(
			() -> new IllegalStateException(
				"There is no metric resolver defined the metric type " +
					metricType));
	}

	public Set<MetricResolver> getMetricResolvers(Set<String> selectedMetrics);

	public Function<String, MetricType> getMetricTypeResolverFunction();

	public QueryBuilder getQueryBuilder();

}