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

package com.liferay.osb.asah.backend.repository.test;

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.model.AssetMetric;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.model.Tuple2;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahRepositoryTestExecutionListener;
import com.liferay.osb.asah.test.util.spring.OSBAsahSQLTestExecutionListener;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mockito;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestExecutionListeners;

/**
 * @author Marcellus Tavares
 */
@TestExecutionListeners(
	mergeMode = TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS,
	value = {
		OSBAsahRepositoryTestExecutionListener.class,
		OSBAsahSQLTestExecutionListener.class
	}
)
public abstract class BaseAssetMetricRepositoryTestCase<T extends AssetMetric>
	implements OSBAsahBackendSpringTestContext {

	@BeforeEach
	public void setUp() {
		Mockito.when(
			_timeZoneDog.getTimeZoneId()
		).thenReturn(
			"UTC"
		);

		Mockito.when(
			_timeZoneDog.getZoneId()
		).thenReturn(
			ZoneOffset.UTC
		);

		TimeZoneDogUtil.setTimeZoneDog(_timeZoneDog);
	}

	@AfterEach
	public void tearDown() {
		TimeZoneDogUtil.setTimeZoneDog(null);
	}

	protected void assertAssetMetrics(
		Double[] expectedMetricValues, List<T> metrics,
		Function<T, Metric> mapperFunction) {

		Assertions.assertEquals(
			expectedMetricValues.length, metrics.size(), metrics.toString());

		Stream<T> stream = metrics.stream();

		MatcherAssert.assertThat(
			new Double[] {7D, 6D},
			Matchers.arrayContainingInAnyOrder(
				stream.map(
					mapperFunction
				).map(
					Metric::getValue
				).toArray()));
	}

	protected void assertGetCanonicalUrls(TimeRange timeRange) {
		AssetMetricRepository<T> assetMetricRepository =
			getAssetMetricRepository();

		List<String> canonicalUrls = assetMetricRepository.getCanonicalUrls(
			"e131fabc", 1L, PageRequest.of(0, 10), timeRange);

		Stream<String> stream = canonicalUrls.stream();

		MatcherAssert.assertThat(
			new String[] {
				"https://www.beryl.com/products/commercial/irrigation",
				"https://www.beryl.com/delivery",
				"https://www.beryl.com/products/commercial/irrigation/FF-2100"
			},
			Matchers.arrayContainingInAnyOrder(stream.toArray()));
	}

	protected void assertGetIndividualsCount(
		MetricType metricType, TimeRange timeRange) {

		AssetMetricRepository<T> assetMetricRepository =
			getAssetMetricRepository();

		Assertions.assertEquals(
			1,
			assetMetricRepository.getIndividualsCount(
				"e131fabc", 1L, false, metricType, timeRange),
			0);
		Assertions.assertEquals(
			2,
			assetMetricRepository.getIndividualsCount(
				"e131fabc", 1L, true, metricType, timeRange),
			0);
		Assertions.assertEquals(
			4,
			assetMetricRepository.getIndividualsCount(
				"e131fabc", 1L, null, metricType, timeRange),
			0);
	}

	protected void assertGetSegmentedIndividualsCount(
		MetricType metricType, TimeRange timeRange) {

		AssetMetricRepository<T> assetMetricRepository =
			getAssetMetricRepository();

		Assertions.assertEquals(
			1,
			assetMetricRepository.getSegmentedIndividualsCount(
				"e131fabc", 1L, metricType, timeRange),
			0);
		Assertions.assertEquals(
			3,
			assetMetricRepository.getNonsegmentedIndividualsCount(
				"e131fabc", 1L, metricType, timeRange),
			0);
	}

	protected void assertGetSegmentMetrics(
		MetricType metricType, TimeRange timeRange) {

		AssetMetricRepository<T> assetMetricRepository =
			getAssetMetricRepository();

		assertMetrics(
			Arrays.asList(
				new Tuple2("B", 2D), new Tuple2("A", 1D), new Tuple2("C", 1D)),
			assetMetricRepository.getSegmentMetrics(
				"e131fabc", 1L, metricType, timeRange));
	}

	protected void assertHistogramMetrics(
		Set<Double> expectedMetricValues,
		List<HistogramMetric> histogramMetrics) {

		Assertions.assertEquals(
			expectedMetricValues.size(), histogramMetrics.size(),
			histogramMetrics.toString());
		Assertions.assertEquals(
			expectedMetricValues,
			SetUtil.map(histogramMetrics, HistogramMetric::getValue));
	}

	protected void assertHistogramMetricsDifferentTimezone(
		String assetId, Long channelId, MetricType metricType,
		int timeZoneDifference, String timeZoneId) {

		AssetMetricRepository<T> assetMetricRepository =
			getAssetMetricRepository();

		List<LocalDateTime> localDateTimes = _getLocalDateTimes(
			assetMetricRepository.getHistogramMetrics(
				assetId, null, channelId, Interval.HOUR, metricType,
				TimeRange.LAST_24_HOURS));

		Mockito.when(
			_timeZoneDog.getTimeZoneId()
		).thenReturn(
			timeZoneId
		);

		Mockito.when(
			_timeZoneDog.getZoneId()
		).thenReturn(
			ZoneId.of(timeZoneId)
		);

		List<LocalDateTime> shiftedLocalDateTimes = _getLocalDateTimes(
			assetMetricRepository.getHistogramMetrics(
				assetId, null, channelId, Interval.HOUR, metricType,
				TimeRange.LAST_24_HOURS));

		Assertions.assertEquals(
			localDateTimes.size(), shiftedLocalDateTimes.size(),
			shiftedLocalDateTimes.toString());

		for (int i = 0; i < localDateTimes.size(); i++) {

			// Time zone ID to UTC

			Duration duration = Duration.between(
				localDateTimes.get(i), shiftedLocalDateTimes.get(i));

			Assertions.assertEquals(timeZoneDifference, duration.toHours());
		}
	}

	protected void assertMetrics(
		List<Tuple2<String, Double>> expectedMetrics, List<Metric> metrics) {

		Assertions.assertEquals(
			expectedMetrics.size(), metrics.size(), metrics.toString());

		for (int i = 0; i < expectedMetrics.size(); i++) {
			Tuple2<String, Double> expectedMetricTuple2 = expectedMetrics.get(
				i);

			Metric metric = metrics.get(i);

			Assertions.assertEquals(
				expectedMetricTuple2.getT1(), metric.getValueKey());
			Assertions.assertEquals(
				expectedMetricTuple2.getT2(), metric.getValue(), 0);
		}
	}

	protected abstract AssetMetricRepository<T> getAssetMetricRepository();

	private List<LocalDateTime> _getLocalDateTimes(
		List<HistogramMetric> histogramMetrics) {

		Stream<HistogramMetric> stream = histogramMetrics.stream();

		return stream.map(
			HistogramMetric::getKey
		).map(
			LocalDateTime::parse
		).collect(
			Collectors.toList()
		);
	}

	@MockBean
	private TimeZoneDog _timeZoneDog;

}