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

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.Matchers;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.mockito.Mockito;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseAssetMetricRepositoryTestCase<T extends AssetMetric> {

	public void assertAssetMetrics(
		Double[] expectedMetricValues, List<T> metrics,
		Function<T, Metric> mapperFunction) {

		Assert.assertEquals(
			metrics.toString(), expectedMetricValues.length, metrics.size());

		Stream<T> stream = metrics.stream();

		Assert.assertThat(
			new Double[] {7D, 6D},
			Matchers.arrayContainingInAnyOrder(
				stream.map(
					mapperFunction
				).map(
					Metric::getValue
				).toArray()));
	}

	public void assertHistogramMetrics(
		Set<Double> expectedMetricValues,
		List<HistogramMetric> histogramMetrics) {

		Assert.assertEquals(
			histogramMetrics.toString(), expectedMetricValues.size(),
			histogramMetrics.size());
		Assert.assertEquals(
			expectedMetricValues,
			SetUtil.map(histogramMetrics, HistogramMetric::getValue));
	}

	public void assertHistogramMetricsDifferentTimezone(
		String assetId, Long channelId, MetricType metricType,
		int timeZoneDifference, String timeZoneId) {

		AssetMetricRepository<T> assetMetricRepository =
			getAssetMetricRepository();

		List<LocalDateTime> localDateTimes = _getLocalDateTimes(
			assetMetricRepository.getHistogramMetrics(
				assetId, channelId, Interval.HOUR, metricType,
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
				assetId, channelId, Interval.HOUR, metricType,
				TimeRange.LAST_24_HOURS));

		Assert.assertEquals(
			shiftedLocalDateTimes.toString(), localDateTimes.size(),
			shiftedLocalDateTimes.size());

		for (int i = 0; i < localDateTimes.size(); i++) {

			// Time zone ID to UTC

			Duration duration = Duration.between(
				localDateTimes.get(i), shiftedLocalDateTimes.get(i));

			Assert.assertEquals(timeZoneDifference, duration.toHours());
		}
	}

	public void assertMetrics(
		List<Tuple2<String, Double>> expectedMetrics, List<Metric> metrics) {

		Assert.assertEquals(
			metrics.toString(), expectedMetrics.size(), metrics.size());

		for (int i = 0; i < expectedMetrics.size(); i++) {
			Tuple2<String, Double> expectedMetricTuple2 = expectedMetrics.get(
				i);

			Metric metric = metrics.get(i);

			Assert.assertEquals(
				expectedMetricTuple2.getT1(), metric.getValueKey());
			Assert.assertEquals(
				expectedMetricTuple2.getT2(), metric.getValue(), 0);
		}
	}

	@Before
	public void setUp() {
		Mockito.when(
			_timeZoneDog.getTimeZoneId()
		).thenReturn(
			"UTC"
		);

		Mockito.when(
			_timeZoneDog.getZoneId()
		).thenReturn(
			ZoneId.of("UTC")
		);

		TimeZoneDogUtil.setTimeZoneDog(_timeZoneDog);
	}

	@After
	public void tearDown() {
		TimeZoneDogUtil.setTimeZoneDog(null);
	}

	protected void assertGetCanonicalUrls(TimeRange timeRange) {
		AssetMetricRepository<T> assetMetricRepository =
			getAssetMetricRepository();

		List<String> canonicalUrls = assetMetricRepository.getCanonicalUrls(
			"e131fabc", 1L, PageRequest.of(0, 10), timeRange);

		Stream<String> stream = canonicalUrls.stream();

		Assert.assertThat(
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

		Assert.assertEquals(
			1,
			assetMetricRepository.getIndividualsCount(
				"e131fabc", 1L, false, metricType, timeRange),
			0);

		Assert.assertEquals(
			2,
			getAssetMetricRepository().getIndividualsCount(
				"e131fabc", 1L, true, metricType, timeRange),
			0);

		Assert.assertEquals(
			4,
			getAssetMetricRepository().getIndividualsCount(
				"e131fabc", 1L, null, metricType, timeRange),
			0);
	}

	protected void assertGetSegmentedCount(
		MetricType metricType, TimeRange timeRange) {

		AssetMetricRepository<T> assetMetricRepository =
			getAssetMetricRepository();

		Assert.assertEquals(
			1,
			assetMetricRepository.getSegmentedCount(
				"e131fabc", 1L, true, metricType, timeRange),
			0);

		Assert.assertEquals(
			3,
			assetMetricRepository.getSegmentedCount(
				"e131fabc", 1L, false, metricType, timeRange),
			0);

		Assert.assertEquals(
			4,
			assetMetricRepository.getSegmentedCount(
				"e131fabc", 1L, null, metricType, timeRange),
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

	protected abstract AssetMetricRepository<T> getAssetMetricRepository();

	private List<LocalDateTime> _getLocalDateTimes(
		List<HistogramMetric> histogramMetrics) {

		Stream<HistogramMetric> stream = histogramMetrics.stream();

		return stream.map(
			HistogramMetric::getKey
		).map(
			LocalDateTime::parse
		).sorted(
		).collect(
			Collectors.toList()
		);
	}

	@MockBean
	private TimeZoneDog _timeZoneDog;

}