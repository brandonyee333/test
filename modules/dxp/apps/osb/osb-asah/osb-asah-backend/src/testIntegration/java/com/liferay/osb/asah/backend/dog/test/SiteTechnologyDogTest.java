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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dog.SiteTechnologyDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.SiteMetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Regisson Cesar
 */
public class SiteTechnologyDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@SQLResource(resourcePath = "test_bq_sessions_site_technology.sql")
	@Test
	public void testBrowserMetricsLast7Days() {
		List<Metric> browserMetrics = _siteTechnologyDog.getBrowserMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_7_DAYS));

		DogTestUtil.assertMetric(1, browserMetrics, "Chrome");
		DogTestUtil.assertMetric(1, browserMetrics, "Firefox");
	}

	@SQLResource(resourcePath = "test_bq_sessions_site_technology.sql")
	@Test
	public void testBrowserMetricsLast24Hours() {
		List<Metric> browserMetrics = _siteTechnologyDog.getBrowserMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_24_HOURS));

		DogTestUtil.assertMetric(2, browserMetrics, "Chrome");
		DogTestUtil.assertMetric(1, browserMetrics, "Firefox");
	}

	@SQLResource(resourcePath = "test_bq_sessions_site_technology.sql")
	@Test
	public void testBrowserMetricsLast30Days() {
		List<Metric> browserMetrics = _siteTechnologyDog.getBrowserMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_30_DAYS));

		DogTestUtil.assertMetric(2, browserMetrics, "Chrome");
		DogTestUtil.assertMetric(2, browserMetrics, "Firefox");
	}

	@SQLResource(resourcePath = "test_bq_sessions_site_technology.sql")
	@Test
	public void testDeviceMetricsLast24Hours() {
		List<Metric> browserMetrics = _siteTechnologyDog.getDeviceMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_24_HOURS));

		DogTestUtil.assertMetric(3, browserMetrics, "Desktop");
		DogTestUtil.assertMetric(2, browserMetrics, "Desktop", "Linux");
		DogTestUtil.assertMetric(1, browserMetrics, "Desktop", "Win10");
	}

	@SQLResource(resourcePath = "test_bq_sessions_site_technology.sql")
	@Test
	public void testDeviceMetricsLast30Days() {
		List<Metric> browserMetrics = _siteTechnologyDog.getDeviceMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_30_DAYS));

		DogTestUtil.assertMetric(1, browserMetrics, "Desktop");
		DogTestUtil.assertMetric(1, browserMetrics, "Desktop", "Linux");
		DogTestUtil.assertMetric(3, browserMetrics, "Phone");
		DogTestUtil.assertMetric(1, browserMetrics, "Phone", "Android");
		DogTestUtil.assertMetric(2, browserMetrics, "Phone", "IOS");
	}

	private SearchQueryContext _getSearchQueryContext(TimeRange timeRange) {
		SearchQueryContext searchQueryContext = new SearchQueryContext();

		searchQueryContext.setChannelId("1");
		searchQueryContext.setIncludePrevious(true);
		searchQueryContext.setTimeRange(timeRange);

		return searchQueryContext;
	}

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private SiteTechnologyDog _siteTechnologyDog;

}