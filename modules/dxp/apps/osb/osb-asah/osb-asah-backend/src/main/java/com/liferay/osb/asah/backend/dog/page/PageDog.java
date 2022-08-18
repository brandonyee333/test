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

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;

import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.PageMetricType;
import com.liferay.osb.asah.common.model.PageVisitorBehaviorMetric;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQPageRepository;

/**
 * @author Inácio Nery
 */
@Component
public class PageDog {

	public long getIndirectAccessMetricValue(
		SearchQueryContext searchQueryContext) {

		return 0;
	}

	public long getMetricValue(
		Optional<String> canonicalUrlOptional,
		Optional<String> fromDateStringOptional, PageMetricType pageMetricType,
		Optional<String> toDateStringOptional, Optional<String> urlOptional) {

		return 0;
	}

	public PageVisitorBehaviorMetric getPageVisitorBehaviorMetric(
		String canonicalUrl, Long channelId, TimeRange timeRange,
		String title) {

		return null;
	}

	public Page<PageVisitorBehaviorMetric> getPageVisitorBehaviorMetricPage(
		Long channelId, int page, int size, Sort sort, TimeRange timeRange) {

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_bqPageRepository.searchPageVisitorBehaviorMetrics(
				channelId, pageRequest, timeRange, _timeZoneDog.getZoneId()),
			pageRequest,
			() -> _bqPageRepository.countPageVisitorBehaviorMetric(
				channelId, timeRange, _timeZoneDog.getZoneId()));
	}

	public long getViewsMetricValue(
		Optional<String> fromDateStringOptional,
		Optional<String> toDateStringOptional, Optional<String> urlOptional) {

		return getMetricValue(
			Optional.empty(), fromDateStringOptional, PageMetricType.VIEWS,
			toDateStringOptional, urlOptional);
	}

	@Autowired
	private BQPageRepository _bqPageRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}