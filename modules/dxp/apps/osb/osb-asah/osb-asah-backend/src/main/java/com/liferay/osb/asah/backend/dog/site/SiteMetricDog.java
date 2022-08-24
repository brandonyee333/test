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
import com.liferay.osb.asah.common.repository.BQPageRepository;

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

		List<SiteVisitorBehaviorMetric> siteVisitorBehaviorMetrics =
			_bqPageRepository.getSiteVisitorBehaviorMetrics(
				Long.parseLong(searchQueryContext.getChannelId()),
				searchQueryContext.isIncludePrevious(),
				searchQueryContext.getTimeRange(), _timeZoneDog.getZoneId());

		if (siteVisitorBehaviorMetrics.isEmpty()) {
			return siteMetric;
		}

		SiteVisitorBehaviorMetric siteVisitorBehaviorMetric =
			siteVisitorBehaviorMetrics.get(0);

		_setMetricValue(
			siteMetric.getVisitorsMetric(),
			Double.parseDouble(
				String.valueOf(siteVisitorBehaviorMetric.getVisitors())));

		_setMetricValue(
			siteMetric.getKnownVisitorsMetric(),
			Double.parseDouble(
				String.valueOf(siteVisitorBehaviorMetric.getKnownVisitors())));

		_setMetricValue(
			siteMetric.getAnonymousVisitorsMetric(),
			Double.parseDouble(
				String.valueOf(
					siteVisitorBehaviorMetric.getAnonymousVisitors())));

		if (searchQueryContext.isIncludePrevious() &&
			(siteVisitorBehaviorMetrics.size() > 1)) {

			siteVisitorBehaviorMetric = siteVisitorBehaviorMetrics.get(1);

			_setMetricPreviousValue(
				siteMetric.getVisitorsMetric(),
				Double.parseDouble(
					String.valueOf(siteVisitorBehaviorMetric.getVisitors())));

			_setMetricPreviousValue(
				siteMetric.getKnownVisitorsMetric(),
				Double.parseDouble(
					String.valueOf(
						siteVisitorBehaviorMetric.getKnownVisitors())));

			_setMetricPreviousValue(
				siteMetric.getAnonymousVisitorsMetric(),
				Double.parseDouble(
					String.valueOf(
						siteVisitorBehaviorMetric.getAnonymousVisitors())));
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
	private TimeZoneDog _timeZoneDog;

}