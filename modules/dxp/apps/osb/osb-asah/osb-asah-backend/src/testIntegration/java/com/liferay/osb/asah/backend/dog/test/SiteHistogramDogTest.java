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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dog.SiteHistogramDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.HistogramMetric;
import com.liferay.osb.asah.backend.model.HistogramMetricBag;
import com.liferay.osb.asah.backend.model.SiteMetricType;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringExtension;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marcos Martins
 */
@ExtendWith(OSBAsahSpringExtension.class)
public class SiteHistogramDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@SQLResource(resourcePath = "test_bq_events_site_histogram.sql")
	@Test
	public void testGetHistogramMetricBag1() {
		Assertions.assertArrayEquals(
			new double[] {0, 1, 0, 0, 1, 0, 3},
			_getActualValues(
				_siteHistogramDog.getHistogramMetricBag(
					_getSearchQueryContext(),
					SiteMetricType.ANONYMOUS_VISITORS)),
			0);
		Assertions.assertArrayEquals(
			new double[] {0, 0, 1, 1, 1, 0, 0},
			_getPreviousValues(
				_siteHistogramDog.getHistogramMetricBag(
					_getSearchQueryContext(),
					SiteMetricType.ANONYMOUS_VISITORS)),
			0);
		Assertions.assertArrayEquals(
			new double[] {0, 1, 0, 1, 1, 0, 1},
			_getActualValues(
				_siteHistogramDog.getHistogramMetricBag(
					_getSearchQueryContext(), SiteMetricType.KNOWN_VISITORS)),
			0);
		Assertions.assertArrayEquals(
			new double[] {0, 0, 1, 0, 1, 0, 0},
			_getPreviousValues(
				_siteHistogramDog.getHistogramMetricBag(
					_getSearchQueryContext(), SiteMetricType.KNOWN_VISITORS)),
			0);
		Assertions.assertArrayEquals(
			new double[] {0, 2, 0, 1, 2, 0, 4},
			_getActualValues(
				_siteHistogramDog.getHistogramMetricBag(
					_getSearchQueryContext(), SiteMetricType.VISITORS)),
			0);
		Assertions.assertArrayEquals(
			new double[] {0, 0, 2, 1, 2, 0, 0},
			_getPreviousValues(
				_siteHistogramDog.getHistogramMetricBag(
					_getSearchQueryContext(), SiteMetricType.VISITORS)),
			0);
	}

	@SQLResource(resourcePath = "test_bq_events_site_histogram_1.sql")
	@Test
	public void testGetHistogramMetricBag2() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		searchQueryContext.setInterval(Interval.HOUR.getKey());
		searchQueryContext.setTimeRange(TimeRange.LAST_24_HOURS);

		Assertions.assertArrayEquals(
			new double[] {
				3600000.0, 0, 0, 0, 0, 0, 0, 0, 7200000.0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 3600000.0, 0, 0, 180000.0
			},
			_getActualValues(
				_siteHistogramDog.getHistogramMetricBag(
					searchQueryContext, SiteMetricType.SESSION_DURATION)),
			1);
		Assertions.assertArrayEquals(
			new double[] {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 7200000.0, 0, 0, 0, 3600000.0, 0, 0,
				0, 0, 0, 0, 0, 3600000.0, 0, 0
			},
			_getPreviousValues(
				_siteHistogramDog.getHistogramMetricBag(
					searchQueryContext, SiteMetricType.SESSION_DURATION)),
			1);
	}

	@SQLResource(resourcePath = "test_bq_events_site_histogram.sql")
	@Test
	public void testGetHistogramMetricBag3() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		Assertions.assertArrayEquals(
			new double[] {0, 1, 0, 1, 1, 0, 1},
			_getActualValues(
				_siteHistogramDog.getHistogramMetricBag(
					searchQueryContext, SiteMetricType.SESSIONS_PER_VISITOR)),
			1);
		Assertions.assertArrayEquals(
			new double[] {0, 0, 1, 1, 1, 0, 0},
			_getPreviousValues(
				_siteHistogramDog.getHistogramMetricBag(
					searchQueryContext, SiteMetricType.SESSIONS_PER_VISITOR)),
			1);
	}

	@SQLResource(resourcePath = "test_bq_events_site_histogram_1.sql")
	@Test
	public void testGetHistogramMetricBag4() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		searchQueryContext.setInterval(Interval.HOUR.getKey());
		searchQueryContext.setTimeRange(TimeRange.LAST_24_HOURS);

		Assertions.assertArrayEquals(
			new double[] {
				1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
				0, 0, 0
			},
			_getActualValues(
				_siteHistogramDog.getHistogramMetricBag(
					searchQueryContext, SiteMetricType.BOUNCE_RATE)),
			1);
		Assertions.assertArrayEquals(
			new double[] {
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0
			},
			_getPreviousValues(
				_siteHistogramDog.getHistogramMetricBag(
					searchQueryContext, SiteMetricType.BOUNCE_RATE)),
			1);
	}

	private double[] _getActualValues(HistogramMetricBag histogramMetricBag) {
		return _getValue(HistogramMetric::getValue, histogramMetricBag);
	}

	private double[] _getPreviousValues(HistogramMetricBag histogramMetricBag) {
		return _getValue(HistogramMetric::getPreviousValue, histogramMetricBag);
	}

	private SearchQueryContext _getSearchQueryContext() {
		SearchQueryContext searchQueryContext = new SearchQueryContext();

		searchQueryContext.setChannelId("1");
		searchQueryContext.setIncludePrevious(Boolean.TRUE);
		searchQueryContext.setInterval(Interval.DAY.getKey());
		searchQueryContext.setTimeRange(TimeRange.LAST_7_DAYS);

		return searchQueryContext;
	}

	private double[] _getValue(
		Function<HistogramMetric, Double> function,
		HistogramMetricBag histogramMetricBag) {

		List<HistogramMetric> histogramMetrics =
			histogramMetricBag.getMetrics();

		double[] actualValues = new double[histogramMetrics.size()];

		for (int i = 0; i < histogramMetrics.size(); i++) {
			HistogramMetric histogramMetric = histogramMetrics.get(i);

			actualValues[i] = function.apply(histogramMetric);
		}

		return actualValues;
	}

	@Autowired
	private SiteHistogramDog _siteHistogramDog;

}