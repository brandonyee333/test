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

import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.date.dog.util.TimeZoneDogUtil;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.MetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.util.SetUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.mockito.Mockito;

import org.springframework.boot.test.mock.mockito.MockBean;

/**
 * @author Marcellus Tavares
 */
public abstract class BaseAssetMetricRepositoryTestCase {

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

		AssetMetricRepository assetMetricRepository =
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

	protected abstract AssetMetricRepository getAssetMetricRepository();

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