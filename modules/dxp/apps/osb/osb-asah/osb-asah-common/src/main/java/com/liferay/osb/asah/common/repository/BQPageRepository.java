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

package com.liferay.osb.asah.common.repository;

import com.liferay.osb.asah.common.model.PageVisitorBehaviorMetric;
import com.liferay.osb.asah.common.model.SiteVisitorBehaviorMetric;
import com.liferay.osb.asah.common.model.TimeRange;

import java.time.ZoneId;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

/**
 * @author Leslie Wong
 */
public interface BQPageRepository {

	public long countPageVisitorBehaviorMetric(
		Long channelId, TimeRange timeRange, ZoneId zoneId);

	public Optional<PageVisitorBehaviorMetric> getPageVisitorBehaviorMetric(
		String canonicalUrl, Long channelId, TimeRange timeRange, String title,
		ZoneId zoneId);

	public List<SiteVisitorBehaviorMetric> getSiteVisitorBehaviorMetrics(
		Long channelId, boolean includePrevious, TimeRange timeRange,
		ZoneId zoneId);

	public List<PageVisitorBehaviorMetric> searchPageVisitorBehaviorMetrics(
		Long channelId, Pageable pageable, TimeRange timeRange, ZoneId zoneId);

}