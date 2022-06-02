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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.model.OrderTotalValue;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQOrderRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Riccardo Ferrari
 */
@Component
public class CommerceDashboardDog {

	public Map<String, OrderTotalValue> getOrderTotalValue(
		Long channelId, boolean compareToPrevious, TimeRange timeRange) {

		Channel channel = _channelDog.fetchChannel(channelId);

		Set<ChannelDataSource> channelDataSources =
			channel.getChannelDataSources();

		Stream<ChannelDataSource> channelDataSourcesStream =
			channelDataSources.stream();

		List<Long> dataSourceIds = channelDataSourcesStream.map(
			ChannelDataSource::getDataSourceId
		).collect(
			Collectors.toList()
		);

		Map<String, BigDecimal> currentOrderTotalValue =
			_bqOrderRepository.getOrderTotalValue(
				dataSourceIds, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Map<String, BigDecimal> previousOrderTotalValue = null;

		if (compareToPrevious) {
			LocalDateTime previousEndLocalDateTime =
				timeRange.getStartLocalDateTime();

			previousEndLocalDateTime = previousEndLocalDateTime.minusDays(1);
			previousEndLocalDateTime = previousEndLocalDateTime.with(
				LocalTime.MAX);

			LocalDateTime previousStartLocalDateTime =
				previousEndLocalDateTime.with(LocalTime.MIN);

			previousStartLocalDateTime = previousStartLocalDateTime.minusDays(
				timeRange.getDeltaDays() - 1);

			previousOrderTotalValue = _bqOrderRepository.getOrderTotalValue(
				dataSourceIds, previousEndLocalDateTime,
				previousStartLocalDateTime, _timeZoneDog.getTimeZoneId());
		}

		Map<String, OrderTotalValue> orderTotalValues = new HashMap<>();

		for (Map.Entry<String, BigDecimal> totalOrderValue :
				currentOrderTotalValue.entrySet()) {

			String currencyCode = totalOrderValue.getKey();

			OrderTotalValue orderTotalValue = new OrderTotalValue(
				currencyCode, null, totalOrderValue.getValue());

			if (compareToPrevious) {
				BigDecimal previousValue = previousOrderTotalValue.getOrDefault(
					currencyCode, BigDecimal.ZERO);

				orderTotalValue.setPercentageVariation(
					_getPercentageVariation(
						totalOrderValue.getValue(), previousValue));
			}

			orderTotalValues.put(currencyCode, orderTotalValue);
		}

		return orderTotalValues;
	}

	private double _getPercentageVariation(
		BigDecimal currentValue, BigDecimal previousValue) {

		BigDecimal delta = currentValue.subtract(previousValue);

		delta = delta.divide(currentValue, RoundingMode.HALF_UP);

		return delta.doubleValue() * 100;
	}

	@Autowired
	private BQOrderRepository _bqOrderRepository;

	@Autowired
	private ChannelDog _channelDog;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}