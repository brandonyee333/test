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
import com.liferay.osb.asah.backend.model.TrendClassification;
import com.liferay.osb.asah.backend.test.util.BaseBeanTestCase;
import com.liferay.osb.asah.test.util.util.AssertTestUtil;

import java.util.function.BiConsumer;
import java.util.function.Function;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.pipeline.PipelineAggregatorBuilders;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Inácio Nery
 */
public class MetricResolverBuilderTest
	extends BaseBeanTestCase<MetricResolver.Builder> {

	@Override
	@Test
	public void testEqualsAndHashCode() {
		EqualsVerifier<?> equalsVerifier = EqualsVerifier.forClass(
			MetricResolver.Builder.class);

		equalsVerifier.suppress(
			Warning.INHERITED_DIRECTLY_FROM_OBJECT, Warning.NONFINAL_FIELDS,
			Warning.STRICT_INHERITANCE);

		equalsVerifier.withPrefabValues(
			AggregationBuilder.class, AggregationBuilders.terms("field1"),
			AggregationBuilders.terms("field2"));

		equalsVerifier.verify();
	}

	@Test
	public void testGetAggregations1() {
		MetricResolver.Builder builder = newInstance();

		MetricResolver metricResolver = builder.build();

		AssertTestUtil.assertSize(1, metricResolver.getAggregationBuilders());
	}

	@Test
	public void testGetAggregations2() {
		MetricResolver.Builder builder = newInstance();

		builder.aggregate(AggregationBuilders.terms(""));

		MetricResolver metricResolver = builder.build();

		AssertTestUtil.assertSize(1, metricResolver.getAggregationBuilders());
	}

	@Test
	public void testGetMapperFunction1() {
		MetricResolver.Builder builder = newInstance();

		MetricResolver metricResolver = builder.build();

		Assert.assertNotNull(metricResolver.getMapperFunction());
	}

	@Test
	public void testGetMapperFunction2() {
		MetricResolver.Builder builder = newInstance();

		Function<Aggregations, Double> mapperFunction = a -> null;

		builder.mapperFunction(mapperFunction);

		MetricResolver metricResolver = builder.build();

		Assert.assertEquals(mapperFunction, metricResolver.getMapperFunction());
	}

	@Test
	public void testGetMetricType() {
		MetricResolver.Builder builder = newInstance();

		MetricResolver metricResolver = builder.build();

		Assert.assertEquals(
			TestMetricType.TEST, metricResolver.getMetricType());
	}

	@Test
	public void testGetPipelineAggregations1() {
		MetricResolver.Builder builder = newInstance();

		MetricResolver metricResolver = builder.build();

		AssertTestUtil.assertSize(
			0, metricResolver.getPipelineAggregationBuilders());
	}

	@Test
	public void testGetPipelineAggregations2() {
		MetricResolver.Builder builder = newInstance();

		builder.aggregate(PipelineAggregatorBuilders.avgBucket("", ""));

		MetricResolver metricResolver = builder.build();

		AssertTestUtil.assertSize(1, metricResolver.getAggregationBuilders());
	}

	@Test
	public void testGetSetter() {
		BiConsumer<Object, Metric> setterBiConsumer = (a, b) -> {
		};

		MetricResolver.Builder builder = newInstance();

		builder.setterBiConsumer(setterBiConsumer);

		MetricResolver metricResolver = builder.build();

		Assert.assertEquals(
			setterBiConsumer, metricResolver.getSetterBiConsumer());
	}

	public enum TestMetricType implements MetricType {

		TEST("test", "test", TrendClassification.Order.ASC);

		@Override
		public String getAggregationName() {
			return _aggregationName;
		}

		@Override
		public String getFieldName() {
			return _fieldName;
		}

		@Override
		public String getName() {
			return _name;
		}

		@Override
		public TrendClassification.Order getTrendClassificationOrder() {
			return _order;
		}

		private TestMetricType(
			String fieldName, String name, TrendClassification.Order order) {

			_aggregationName = fieldName;
			_fieldName = fieldName;
			_name = name;
			_order = order;
		}

		private final String _aggregationName;
		private final String _fieldName;
		private final String _name;
		private final TrendClassification.Order _order;

	}

	@Override
	protected MetricResolver.Builder newInstance() {
		return MetricResolver.builder(TestMetricType.TEST);
	}

}