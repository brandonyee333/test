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

package com.liferay.osb.asah.backend.dog;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.liferay.osb.asah.backend.model.HeatMapMetric;
import com.liferay.osb.asah.common.model.TimeRange;

/**
 * @author Leslie Wong
 */
@Component
public class SiteVisitorHeatMapDog {

	public List<HeatMapMetric> getHeatMapMetrics(
		String assetId, String channelId, TimeRange timeRange) {

			return Collections.emptyList();
	}
}