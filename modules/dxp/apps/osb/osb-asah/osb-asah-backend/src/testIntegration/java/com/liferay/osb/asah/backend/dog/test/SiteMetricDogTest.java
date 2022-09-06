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
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.site.SiteMetricDog;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.SiteMetric;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;

/**
 * @author Marcos Martins
 */
@BootstrapWith(SpringBootTestContextBootstrapper.class)
public class SiteMetricDogTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testGetSiteMetric1() {
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

	@SQLResource(resourcePath = "test_bq_events.sql")
	@Test
	public void testGetSiteMetric2() {
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

	@SQLResource(resourcePath = "test_bq_events.sql")
	@Test
	public void testGetSiteMetric3() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		searchQueryContext.setIncludePrevious(true);

		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			searchQueryContext);

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
	public void testGetSiteMetric4() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		searchQueryContext.setTimeRange(TimeRange.LAST_7_DAYS);

		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			searchQueryContext);

		Metric bounceRateMetric = siteMetric.getBounceRateMetric();

		Assertions.assertEquals(0.5, bounceRateMetric.getValue());
	}

	@SQLResource(resourcePath = "test_bq_events_1.sql")
	@Test
	public void testGetSiteMetric5() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		searchQueryContext.setIncludePrevious(true);
		searchQueryContext.setTimeRange(TimeRange.LAST_7_DAYS);

		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			searchQueryContext);

		Metric bounceRateMetric = siteMetric.getBounceRateMetric();

		Assertions.assertEquals(1, bounceRateMetric.getPreviousValue());
		Assertions.assertEquals(0.5, bounceRateMetric.getValue());
	}

	@SQLResource(resourcePath = "test_bq_events_2.sql")
	@Test
	public void testGetSiteMetric6() {
		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			_getSearchQueryContext());

		Metric sessionDurationMetric = siteMetric.getSessionDurationMetric();

		Assertions.assertEquals(8.64E7, sessionDurationMetric.getValue());
	}

	@SQLResource(resourcePath = "test_bq_events_2.sql")
	@Test
	public void testGetSiteMetric7() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		searchQueryContext.setTimeRange(TimeRange.LAST_24_HOURS);

		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			searchQueryContext);

		Metric sessionDurationMetric = siteMetric.getSessionDurationMetric();

		Assertions.assertEquals(3600000.0, sessionDurationMetric.getValue());
	}

	@SQLResource(resourcePath = "test_bq_events.sql")
	@Test
	public void testGetSiteMetric8() {
		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			_getSearchQueryContext());

		Metric sessionsPerVisitorMetric =
			siteMetric.getSessionsPerVisitorMetric();

		Assertions.assertEquals(1, sessionsPerVisitorMetric.getValue());
	}

	@SQLResource(resourcePath = "test_bq_events_3.sql")
	@Test
	public void testGetSiteMetric9() {
		SearchQueryContext searchQueryContext = _getSearchQueryContext();

		searchQueryContext.setTimeRange(TimeRange.LAST_7_DAYS);

		SiteMetric siteMetric = _siteMetricDog.getSiteMetric(
			searchQueryContext);

		Metric visitorsMetric = siteMetric.getVisitorsMetric();

		Assertions.assertEquals(1, visitorsMetric.getPreviousValue());
		Assertions.assertEquals(0, visitorsMetric.getValue());
	}

	private SearchQueryContext _getSearchQueryContext() {
		SearchQueryContext searchQueryContext = new SearchQueryContext();

		searchQueryContext.setChannelId("1");
		searchQueryContext.setIncludePrevious(true);
		searchQueryContext.setTimeRange(TimeRange.LAST_30_DAYS);

		return searchQueryContext;
	}

	@Autowired
	private SiteMetricDog _siteMetricDog;

}