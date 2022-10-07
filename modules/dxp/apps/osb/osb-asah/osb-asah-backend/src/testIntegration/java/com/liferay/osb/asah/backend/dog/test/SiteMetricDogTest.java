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
import com.liferay.osb.asah.backend.dog.SiteMetricDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.HeatMapMetric;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.SiteMetric;
import com.liferay.osb.asah.backend.model.SiteMetricType;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.time.LocalDate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;

/**
 * @author Marcos Martins
 * @author Regisson Cesar
 */
@BootstrapWith(SpringBootTestContextBootstrapper.class)
public class SiteMetricDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@SQLResource(resourcePath = "test_bq_sessions_site_technology.sql")
	@Test
	public void testBrowserMetricsLast7Days() {
		List<Metric> browserMetrics = _siteMetricDog.getBrowserMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_7_DAYS));

		DogTestUtil.assertMetric(1, browserMetrics, "Chrome");
		DogTestUtil.assertMetric(1, browserMetrics, "Firefox");
	}

	@SQLResource(resourcePath = "test_bq_sessions_site_technology.sql")
	@Test
	public void testBrowserMetricsLast24Hours() {
		List<Metric> browserMetrics = _siteMetricDog.getBrowserMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_24_HOURS));

		DogTestUtil.assertMetric(2, browserMetrics, "Chrome");
		DogTestUtil.assertMetric(1, browserMetrics, "Firefox");
	}

	@SQLResource(resourcePath = "test_bq_sessions_site_technology.sql")
	@Test
	public void testBrowserMetricsLast30Days() {
		List<Metric> browserMetrics = _siteMetricDog.getBrowserMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_30_DAYS));

		DogTestUtil.assertMetric(2, browserMetrics, "Chrome");
		DogTestUtil.assertMetric(2, browserMetrics, "Firefox");
	}

	@SQLResource(resourcePath = "test_bq_sessions_site_technology.sql")
	@Test
	public void testDeviceMetricsLast24Hours() {
		List<Metric> browserMetrics = _siteMetricDog.getDeviceMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_24_HOURS));

		DogTestUtil.assertMetric(3, browserMetrics, "Desktop");
		DogTestUtil.assertMetric(2, browserMetrics, "Desktop", "Linux");
		DogTestUtil.assertMetric(1, browserMetrics, "Desktop", "Win10");
	}

	@SQLResource(resourcePath = "test_bq_sessions_site_technology.sql")
	@Test
	public void testDeviceMetricsLast30Days() {
		List<Metric> browserMetrics = _siteMetricDog.getDeviceMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_30_DAYS));

		DogTestUtil.assertMetric(1, browserMetrics, "Desktop");
		DogTestUtil.assertMetric(1, browserMetrics, "Desktop", "Linux");
		DogTestUtil.assertMetric(3, browserMetrics, "Phone");
		DogTestUtil.assertMetric(1, browserMetrics, "Phone", "Android");
		DogTestUtil.assertMetric(2, browserMetrics, "Phone", "IOS");
	}

	@SQLResource(resourcePath = "test_bq_sessions_site_geolocation.sql")
	@Test
	public void testGeolocationMetrics1() {
		List<Metric> geolocationMetrics = _siteMetricDog.getGeolocationMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_24_HOURS));

		Assertions.assertEquals(
			2, geolocationMetrics.size(), geolocationMetrics.toString());

		DogTestUtil.assertMetric(2, geolocationMetrics, "Brazil");
		DogTestUtil.assertMetric(1, geolocationMetrics, "Spain");
	}

	@SQLResource(resourcePath = "test_bq_sessions_site_geolocation.sql")
	@Test
	public void testGeolocationMetrics2() {
		List<Metric> geolocationMetrics = _siteMetricDog.getGeolocationMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_7_DAYS));

		Assertions.assertEquals(
			2, geolocationMetrics.size(), geolocationMetrics.toString());

		DogTestUtil.assertMetric(1, geolocationMetrics, "Brazil");
		DogTestUtil.assertMetric(1, geolocationMetrics, "Spain");
	}

	@SQLResource(resourcePath = "test_bq_sessions_site_geolocation.sql")
	@Test
	public void testGeolocationMetrics3() {
		List<Metric> geolocationMetrics = _siteMetricDog.getGeolocationMetrics(
			SiteMetricType.SESSIONS,
			_getSearchQueryContext(TimeRange.LAST_30_DAYS));

		Assertions.assertEquals(
			2, geolocationMetrics.size(), geolocationMetrics.toString());

		DogTestUtil.assertMetric(2, geolocationMetrics, "Brazil");
		DogTestUtil.assertMetric(2, geolocationMetrics, "Spain");
	}

	@SQLResource(resourcePath = "test_bq_events.sql")
	@Test
	public void testGetSiteMetric() {
		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			_getSearchQueryContext());

		Metric anonymousVisitorsMetric =
			siteMetric.getAnonymousVisitorsMetric();

		Assertions.assertEquals(0, anonymousVisitorsMetric.getPreviousValue());
		Assertions.assertEquals(1, anonymousVisitorsMetric.getValue());

		Metric knownVisitorsMetric = siteMetric.getKnownVisitorsMetric();

		Assertions.assertEquals(1, knownVisitorsMetric.getPreviousValue());
		Assertions.assertEquals(1, knownVisitorsMetric.getValue());

		Metric visitorsMetric = siteMetric.getVisitorsMetric();

		Assertions.assertEquals(1, visitorsMetric.getPreviousValue());
		Assertions.assertEquals(2, visitorsMetric.getValue());
	}

	@SQLResource(resourcePath = "test_bq_events_1.sql")
	@Test
	public void testGetSiteMetricBounceRateLast7Days() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		searchQueryContext.setIncludePrevious(false);
		searchQueryContext.setTimeRange(TimeRange.LAST_7_DAYS);

		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			searchQueryContext);

		Metric bounceRateMetric = siteMetric.getBounceRateMetric();

		Assertions.assertEquals(null, bounceRateMetric.getPreviousValue());
		Assertions.assertEquals(0.5, bounceRateMetric.getValue());
	}

	@SQLResource(resourcePath = "test_bq_events_1.sql")
	@Test
	public void testGetSiteMetricBounceRateLast7DaysIncludePrevious() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		searchQueryContext.setIncludePrevious(true);
		searchQueryContext.setTimeRange(TimeRange.LAST_7_DAYS);

		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			searchQueryContext);

		Metric bounceRateMetric = siteMetric.getBounceRateMetric();

		Assertions.assertEquals(1, bounceRateMetric.getPreviousValue());
		Assertions.assertEquals(0.5, bounceRateMetric.getValue());
	}

	@Test
	public void testGetSiteMetricEmptyState() {
		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			_getSearchQueryContext());

		Metric anonymousVisitorsMetric =
			siteMetric.getAnonymousVisitorsMetric();

		Assertions.assertNull(anonymousVisitorsMetric.getPreviousValue());
		Assertions.assertEquals(0, anonymousVisitorsMetric.getValue());

		Metric knownVisitorsMetric = siteMetric.getKnownVisitorsMetric();

		Assertions.assertNull(knownVisitorsMetric.getPreviousValue());
		Assertions.assertEquals(0, knownVisitorsMetric.getValue());

		Metric visitorsMetric = siteMetric.getVisitorsMetric();

		Assertions.assertNull(visitorsMetric.getPreviousValue());
		Assertions.assertEquals(0, visitorsMetric.getValue());
	}

	@SQLResource(resourcePath = "test_bq_events_2.sql")
	@Test
	public void testGetSiteMetricSessionDuration() {
		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			_getSearchQueryContext());

		Metric sessionDurationMetric = siteMetric.getSessionDurationMetric();

		Assertions.assertEquals(8.64E7, sessionDurationMetric.getValue());
	}

	@SQLResource(resourcePath = "test_bq_events_2.sql")
	@Test
	public void testGetSiteMetricSessionDurationLast24Hours() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		searchQueryContext.setTimeRange(TimeRange.LAST_24_HOURS);

		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			searchQueryContext);

		Metric sessionDurationMetric = siteMetric.getSessionDurationMetric();

		Assertions.assertEquals(3600000.0, sessionDurationMetric.getValue());
	}

	@SQLResource(resourcePath = "test_bq_events.sql")
	@Test
	public void testGetSiteMetricSessionPerVisitor() {
		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			_getSearchQueryContext());

		Metric sessionsPerVisitorMetric =
			siteMetric.getSessionsPerVisitorMetric();

		Assertions.assertEquals(1, sessionsPerVisitorMetric.getValue());
	}

	@SQLResource(resourcePath = "test_bq_events_3.sql")
	@Test
	public void testGetSiteMetricVisitorPreviousValueOnly() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		searchQueryContext.setTimeRange(TimeRange.LAST_7_DAYS);

		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			searchQueryContext);

		Metric visitorsMetric = siteMetric.getVisitorsMetric();

		Assertions.assertEquals(1, visitorsMetric.getPreviousValue());
		Assertions.assertEquals(0, visitorsMetric.getValue());
	}

	@SQLResource(resourcePath = "test_bq_events_4.sql")
	@Test
	public void testGetSiteMetricVisitors() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		searchQueryContext.setTimeRange(TimeRange.LAST_7_DAYS);

		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			searchQueryContext);

		Metric anonymousVisitorsMetrics =
			siteMetric.getAnonymousVisitorsMetric();

		Assertions.assertEquals(2, anonymousVisitorsMetrics.getPreviousValue());
		Assertions.assertEquals(2, anonymousVisitorsMetrics.getValue());

		Metric knownVisitorsMetrics = siteMetric.getKnownVisitorsMetric();

		Assertions.assertEquals(2, knownVisitorsMetrics.getPreviousValue());
		Assertions.assertEquals(2, knownVisitorsMetrics.getValue());

		Metric visitorsMetrics = siteMetric.getVisitorsMetric();

		Assertions.assertEquals(4, visitorsMetrics.getPreviousValue());
		Assertions.assertEquals(4, visitorsMetrics.getValue());
	}

	@SQLResource(resourcePath = "test_bq_sessions_visitors_by_day_and_time.sql")
	@Test
	public void testVisitorHeatMapMetrics30Days() {
		List<HeatMapMetric> heatMapMetrics = _siteMetricDog.getHeatMapMetrics(
			"1",
			TimeRange.of(
				LocalDate.parse("2022-10-06"), LocalDate.parse("2022-09-06")));

		HeatMapMetric heatMapMetric = heatMapMetrics.get(63);

		Assertions.assertEquals(1.0, heatMapMetric.getValue());

		heatMapMetric = heatMapMetrics.get(87);

		Assertions.assertEquals(1.0, heatMapMetric.getValue());
	}

	private SearchQueryContext _getSearchQueryContext() {
		SearchQueryContext searchQueryContext = new SearchQueryContext();

		searchQueryContext.setChannelId("1");
		searchQueryContext.setIncludePrevious(true);
		searchQueryContext.setTimeRange(TimeRange.LAST_30_DAYS);

		return searchQueryContext;
	}

	private SearchQueryContext _getSearchQueryContext(TimeRange timeRange) {
		SearchQueryContext searchQueryContext = new SearchQueryContext();

		searchQueryContext.setAssetType(AssetType.SITE);
		searchQueryContext.setChannelId("1");
		searchQueryContext.setIncludePrevious(true);
		searchQueryContext.setTimeRange(timeRange);

		return searchQueryContext;
	}

	@Autowired
	private SiteMetricDog _siteMetricDog;

}