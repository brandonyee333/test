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

package com.liferay.osb.asah.backend.dog.page;

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.model.Metric;
import com.liferay.osb.asah.backend.model.PageMetric;
import com.liferay.osb.asah.backend.repository.AssetMetricRepository;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.PageMetricType;
import com.liferay.osb.asah.common.model.TimeRange;

import java.time.LocalDate;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author Inácio Nery
 */
@Component
public class PageDog {

	public long getIndirectAccessMetricValue(
		SearchQueryContext searchQueryContext) {

		PageMetric pageMetric = _pageMetricAssetMetricRepository.getAssetMetric(
			searchQueryContext.getAssetId(), searchQueryContext.getTitle(),
			Long.valueOf(searchQueryContext.getChannelId()),
			Collections.singleton(PageMetricType.INDIRECT_ACCESS.getName()),
			searchQueryContext.getTimeRange());

		return _getLongValue(pageMetric.getIndirectAccessMetric());
	}

	public long getReadsMetricValue(
		@Nullable String canonicalUrl, @Nullable LocalDate fromLocalDate,
		@Nullable LocalDate toLocalDate) {

		PageMetric pageMetric = _getPageMetric(
			canonicalUrl, fromLocalDate, PageMetricType.READS, toLocalDate);

		return _getLongValue(pageMetric.getReadsMetric());
	}

	public long getViewsMetricValue(
		@Nullable String canonicalUrl, @Nullable LocalDate fromLocalDate,
		@Nullable LocalDate toLocalDate) {

		PageMetric pageMetric = _getPageMetric(
			canonicalUrl, fromLocalDate, PageMetricType.VIEWS, toLocalDate);

		return _getLongValue(pageMetric.getViewsMetric());
	}

	private long _getLongValue(Metric metric) {
		Double value = metric.getValue();

		return value.longValue();
	}

	private PageMetric _getPageMetric(
		@Nullable String canonicalUrl, @Nullable LocalDate fromLocalDate,
		PageMetricType pageMetricType, @Nullable LocalDate toLocalDate) {

		if (toLocalDate == null) {
			toLocalDate = LocalDate.now(_timeZoneDog.getZoneId());
		}

		if (fromLocalDate == null) {
			fromLocalDate = toLocalDate.minusMonths(13);
		}

		return _pageMetricAssetMetricRepository.getAssetMetric(
			canonicalUrl, null, null,
			Collections.singleton(pageMetricType.getName()),
			TimeRange.of(toLocalDate, fromLocalDate));
	}

	@Autowired
	@Qualifier("PageAssetMetricRepository")
	private AssetMetricRepository<PageMetric> _pageMetricAssetMetricRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}