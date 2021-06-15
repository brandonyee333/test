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

import com.liferay.osb.asah.common.model.CustomAssetMetricType;
import com.liferay.osb.asah.common.model.Interval;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.model.Tuple2;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.context.annotation.Primary;

/**
 * @author Marcellus Tavares
 */
@Primary
public interface CustomAssetMetricRepository {

	public List<Tuple2<LocalDateTime, BigDecimal>> getHistogramMetrics(
		Long channelId, CustomAssetMetricType customAssetMetricType,
		String customAssetPrimaryKey, Interval interval, TimeRange timeRange);

}