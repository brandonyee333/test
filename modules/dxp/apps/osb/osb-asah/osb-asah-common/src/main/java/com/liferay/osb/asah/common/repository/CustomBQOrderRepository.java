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

import java.math.BigDecimal;

import java.time.LocalDateTime;

import java.util.Map;

/**
 * @author Riccardo Ferrari
 */
public interface CustomBQOrderRepository {

	public Map<String, BigDecimal> getOrderAccountAverageCurrencyValues(
		Long channelIds, LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId);

	public Map<String, BigDecimal> getOrderAverageCurrencyValues(
		Long channelId, LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId);

	public Map<String, BigDecimal> getOrderIncompleteCurrencyValues(
		Long channelId, LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId);

	public Map<String, BigDecimal> getOrderTotalCurrencyValues(
		Long channelId, LocalDateTime rangeEndLocalDateTime,
		LocalDateTime rangeStartLocalDateTime, String timeZoneId);

}