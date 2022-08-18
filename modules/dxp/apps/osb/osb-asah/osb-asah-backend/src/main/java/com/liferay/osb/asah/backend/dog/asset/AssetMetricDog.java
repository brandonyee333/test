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

package com.liferay.osb.asah.backend.dog.asset;

import com.liferay.osb.asah.common.model.MetricType;

import java.time.LocalDate;

import java.util.Optional;

import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class AssetMetricDog {

	public long getMetricValue(
		String assetId, Optional<String> channelIdOptional,
		String collectionName, Optional<String> dataSourceIdOptional,
		Optional<LocalDate> endLocalDateOptional, MetricType metricType,
		Optional<LocalDate> startLocalDateOptional) {

		return 0;
	}

}