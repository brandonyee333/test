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

package com.liferay.osb.asah.backend.dog.resolver;

import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.MetricType;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.aggregations.metrics.sum.SumAggregationBuilder;

/**
 * @author Marcellus Tavares
 */
public class MetricResolver {

	public static Builder builder(MetricType metricType) {
		Objects.requireNonNull(metricType, "Metric type is null");

		return new Builder(metricType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MetricResolver)) {
			return false;
		}

		MetricResolver metricResolver = (MetricResolver)obj;

		if (!Objects.equals(
				_aggregationBuilders, metricResolver._aggregationBuilders)) {

			return false;
		}

		if (!Objects.equals(
				_pipelineAggregationBuilders,
				metricResolver._pipelineAggregationBuilders)) {

			return false;
		}

		return true;
	}

	public Set<AggregationBuilder> getAggregationBuilders() {
		if (_aggregationBuilders.isEmpty()) {
			_aggregationBuilders.add(
				_createDefaultAggregationBuilder(_metricType));
		}

		return Collections.unmodifiableSet(_aggregationBuilders);
	}

	public Optional<Consumer<TermsAggregationBuilder>>
		getBucketOrderTermsAggregationBuilderConsumerOptional() {

		return Optional.ofNullable(_bucketOrderTermsAggregationBuilderConsumer);
	}

	public Function<Aggregations, Double> getMapperFunction() {
		return Optional.ofNullable(
			_mapperFunction
		).orElseGet(
			() -> aggregations -> _getSumAggregationValue(
				aggregations, _metricType)
		);
	}

	public MetricType getMetricType() {
		return _metricType;
	}

	public Set<PipelineAggregationBuilder> getPipelineAggregationBuilders() {
		return Collections.unmodifiableSet(_pipelineAggregationBuilders);
	}

	public <T> BiConsumer<T, Metric> getSetterBiConsumer() {
		return (BiConsumer<T, Metric>)_setterBiConsumer;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_aggregationBuilders, _bucketOrderTermsAggregationBuilderConsumer,
			_mapperFunction, _metricType, _pipelineAggregationBuilders,
			_setterBiConsumer);
	}

	public static final class Builder {

		public Builder aggregate(AggregationBuilder... aggregationBuilders) {
			Collections.addAll(
				_metricResolver._aggregationBuilders, aggregationBuilders);

			return this;
		}

		public Builder aggregate(
			PipelineAggregationBuilder... pipelineAggregationBuilders) {

			Collections.addAll(
				_metricResolver._pipelineAggregationBuilders,
				pipelineAggregationBuilders);

			return this;
		}

		public <T> Builder bucketOrderTermsAggregationBuilderConsumer(
			Consumer<TermsAggregationBuilder>
				bucketOrderTermsAggregationBuilderConsumer) {

			_metricResolver._bucketOrderTermsAggregationBuilderConsumer =
				bucketOrderTermsAggregationBuilderConsumer;

			return this;
		}

		public MetricResolver build() {
			return _metricResolver;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof Builder)) {
				return false;
			}

			Builder builder = (Builder)obj;

			if (Objects.equals(_metricResolver, builder._metricResolver)) {
				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_metricResolver);
		}

		public Builder mapperFunction(
			Function<Aggregations, Double> mapperFunction) {

			_metricResolver._mapperFunction = mapperFunction;

			return this;
		}

		public <T> Builder setterBiConsumer(
			BiConsumer<T, Metric> setterBiConsumer) {

			_metricResolver._setterBiConsumer = setterBiConsumer;

			return this;
		}

		private Builder(MetricType metricType) {
			_metricResolver = new MetricResolver(metricType);
		}

		private final MetricResolver _metricResolver;

	}

	private MetricResolver(MetricType metricType) {
		_metricType = metricType;
	}

	private AggregationBuilder _createDefaultAggregationBuilder(
		MetricType metricType) {

		SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(
			metricType.getAggregationName());

		sumAggregationBuilder.field(metricType.getFieldName());

		return sumAggregationBuilder;
	}

	private Double _getSumAggregationValue(
		Aggregations aggregations, MetricType metricType) {

		Sum sum = aggregations.get(metricType.getAggregationName());

		return sum.getValue();
	}

	private Set<AggregationBuilder> _aggregationBuilders = new HashSet<>();
	private Consumer<TermsAggregationBuilder>
		_bucketOrderTermsAggregationBuilderConsumer;
	private Function<Aggregations, Double> _mapperFunction;
	private final MetricType _metricType;
	private Set<PipelineAggregationBuilder> _pipelineAggregationBuilders =
		new HashSet<>();
	private BiConsumer<?, Metric> _setterBiConsumer;

}