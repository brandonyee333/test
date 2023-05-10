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

package com.liferay.osb.asah.backend.repository;

import com.liferay.osb.asah.common.model.TimeRange;

import java.time.ZoneId;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Marcellus Tavares
 */
public interface PageReferrerRepository {

	public Map<String, Double> getAcquisitionChannelAccesses(
		String canonicalUrl, @Nullable Long channelId, TimeRange timeRange,
		ZoneId zoneId);

	public Map<String, Double> getPageReferrerAccesses(
		String canonicalUrl, @Nullable Long channelId, TimeRange timeRange,
		ZoneId zoneId);

	public Map<String, Double>
		getSocialPageReferrerAccessesByReferrerCanonicalUrl(
			String canonicalUrl, @Nullable Long channelId, Pageable pageable,
			TimeRange timeRange, ZoneId zoneId);

	public Map<String, Double> getSocialPageReferrerAccessesByReferrerHost(
		String canonicalUrl, @Nullable Long channelId, Pageable pageable,
		TimeRange timeRange, ZoneId zoneId);

}