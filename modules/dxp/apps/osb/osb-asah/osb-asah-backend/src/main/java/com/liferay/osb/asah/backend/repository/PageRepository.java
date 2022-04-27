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

import com.liferay.osb.asah.backend.model.PageVisitorBehaviorMetric;
import com.liferay.osb.asah.common.model.TimeRange;

import java.time.ZoneId;

/**
 * @author Leslie Wong
 */
public interface PageRepository {

	public PageVisitorBehaviorMetric getPageVisitorBehaviorMetric(
		String canonicalUrl, Long channelId, TimeRange timeRange, String title,
		ZoneId zoneId);

}