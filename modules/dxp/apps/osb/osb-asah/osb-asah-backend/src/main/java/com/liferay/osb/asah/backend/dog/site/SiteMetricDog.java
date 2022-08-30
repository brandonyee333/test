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

package com.liferay.osb.asah.backend.dog.site;

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.SiteMetric;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.SiteVisitorBehaviorMetric;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQPageRepository;
import com.liferay.osb.asah.common.repository.BQSessionRepository;

import java.time.ZoneId;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcos Martins
 */
@Component
public class SiteMetricDog {

	public SiteMetric getSiteMetric(SearchQueryContext searchQueryContext) {
		SiteMetric siteMetric = new SiteMetric();

		Long channelId = Long.parseLong(searchQueryContext.getChannelId());
		boolean includePrevious = searchQueryContext.isIncludePrevious();
		TimeRange timeRange = searchQueryContext.getTimeRange();
		ZoneId zoneId = _timeZoneDog.getZoneId();

		List<SiteVisitorBehaviorMetric> pageSiteVisitorBehaviorMetrics =
			_bqPageRepository.getSiteVisitorBehaviorMetrics(
				channelId, includePrevious, timeRange, zoneId);

		if (!pageSiteVisitorBehaviorMetrics.isEmpty()) {
			SiteVisitorBehaviorMetric pageSiteVisitorBehaviorMetric =
				pageSiteVisitorBehaviorMetrics.get(0);

			_setMetricValue(
				siteMetric.getVisitorsMetric(),
				Double.parseDouble(
					String.valueOf(
						pageSiteVisitorBehaviorMetric.getVisitors())));

			_setMetricValue(
				siteMetric.getKnownVisitorsMetric(),
				Double.parseDouble(
					String.valueOf(
						pageSiteVisitorBehaviorMetric.getKnownVisitors())));

			_setMetricValue(
				siteMetric.getAnonymousVisitorsMetric(),
				Double.parseDouble(
					String.valueOf(
						pageSiteVisitorBehaviorMetric.getAnonymousVisitors())));

			if (searchQueryContext.isIncludePrevious() &&
				(pageSiteVisitorBehaviorMetrics.size() > 1)) {

				pageSiteVisitorBehaviorMetric =
					pageSiteVisitorBehaviorMetrics.get(1);

				_setMetricPreviousValue(
					siteMetric.getVisitorsMetric(),
					Double.parseDouble(
						String.valueOf(
							pageSiteVisitorBehaviorMetric.getVisitors())));

				_setMetricPreviousValue(
					siteMetric.getKnownVisitorsMetric(),
					Double.parseDouble(
						String.valueOf(
							pageSiteVisitorBehaviorMetric.getKnownVisitors())));

				_setMetricPreviousValue(
					siteMetric.getAnonymousVisitorsMetric(),
					Double.parseDouble(
						String.valueOf(
							pageSiteVisitorBehaviorMetric.
								getAnonymousVisitors())));
			}
		}

		List<SiteVisitorBehaviorMetric> sessionSiteVisitorBehaviorMetrics =
			_bqSessionRepository.getSiteVisitorBehaviorMetrics(
				channelId, includePrevious, timeRange, zoneId);

		if (!sessionSiteVisitorBehaviorMetrics.isEmpty()) {
			SiteVisitorBehaviorMetric sessionSiteVisitorBehaviorMetric =
				sessionSiteVisitorBehaviorMetrics.get(0);

			_setMetricValue(
				siteMetric.getSessionsMetric(),
				Double.parseDouble(
					String.valueOf(
						sessionSiteVisitorBehaviorMetric.getSessions())));

			_setMetricValue(
				siteMetric.getBounceRateMetric(),
				Double.parseDouble(
					String.valueOf(
						sessionSiteVisitorBehaviorMetric.getBounceRate())));

			_setMetricValue(
				siteMetric.getSessionsMetric(),
				Double.parseDouble(
					String.valueOf(
						sessionSiteVisitorBehaviorMetric.getSessions())));

			if (searchQueryContext.isIncludePrevious() &&
				(sessionSiteVisitorBehaviorMetrics.size() > 1)) {

				sessionSiteVisitorBehaviorMetric =
					sessionSiteVisitorBehaviorMetrics.get(1);

				_setMetricPreviousValue(
					siteMetric.getSessionsMetric(),
					Double.parseDouble(
						String.valueOf(
							sessionSiteVisitorBehaviorMetric.getSessions())));

				_setMetricValue(
					siteMetric.getSessionDurationMetric(),
					Double.parseDouble(
						String.valueOf(
							sessionSiteVisitorBehaviorMetric.
								getSessionDuration())));

				_setMetricPreviousValue(
					siteMetric.getBounceRateMetric(),
					Double.parseDouble(
						String.valueOf(
							sessionSiteVisitorBehaviorMetric.getBounceRate())));
			}
		}

		return siteMetric;
	}

	private void _setMetricPreviousValue(Metric metric, Double value) {
		metric.setPreviousValue(value);
	}

	private void _setMetricValue(Metric metric, Double value) {
		metric.setValue(value);
	}

	@Autowired
	private BQPageRepository _bqPageRepository;

	@Autowired
	private BQSessionRepository _bqSessionRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}