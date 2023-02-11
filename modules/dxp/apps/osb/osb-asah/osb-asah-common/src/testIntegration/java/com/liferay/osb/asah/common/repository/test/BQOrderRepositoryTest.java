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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.BQOrderRepository;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.math.BigDecimal;

import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Riccardo Ferrari
 */
@Import(JDBCTestConfiguration.class)
public class BQOrderRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderAccountAverageCurrencyValues() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, BigDecimal> orderAccountAverageCurrencyValues =
			_bqOrderRepository.getOrderAccountAverageCurrencyValues(
				123L, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertNotNull(orderAccountAverageCurrencyValues);
		Assertions.assertNotNull(orderAccountAverageCurrencyValues.get("USD"));

		BigDecimal actualValue = orderAccountAverageCurrencyValues.get("USD");

		BigDecimal expectedValue = new BigDecimal("14.0");

		Assertions.assertEquals(
			expectedValue.stripTrailingZeros(),
			actualValue.stripTrailingZeros());

		Assertions.assertNotNull(orderAccountAverageCurrencyValues.get("EUR"));

		actualValue = orderAccountAverageCurrencyValues.get("EUR");
		expectedValue = new BigDecimal("10.0");

		Assertions.assertEquals(
			expectedValue.stripTrailingZeros(),
			actualValue.stripTrailingZeros());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderAccountAverageCurrencyValuesWithEmptyDatasourceIds() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Assertions.assertEquals(
			Collections.emptyMap(),
			_bqOrderRepository.getOrderAccountAverageCurrencyValues(
				null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderAverageCurrencyValues() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, BigDecimal> orderAverageCurrencyValues =
			_bqOrderRepository.getOrderAverageCurrencyValues(
				123L, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertNotNull(orderAverageCurrencyValues);
		Assertions.assertNotNull(orderAverageCurrencyValues.get("USD"));

		BigDecimal actualValue = orderAverageCurrencyValues.get("USD");

		BigDecimal expectedValue = new BigDecimal("10.0");

		Assertions.assertEquals(
			expectedValue.stripTrailingZeros(),
			actualValue.stripTrailingZeros());

		Assertions.assertNotNull(orderAverageCurrencyValues.get("EUR"));

		actualValue = orderAverageCurrencyValues.get("EUR");
		expectedValue = new BigDecimal("10.0");

		Assertions.assertEquals(
			expectedValue.stripTrailingZeros(),
			actualValue.stripTrailingZeros());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderAverageCurrencyValuesWithEmptyDatasourceIds() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Assertions.assertEquals(
			Collections.emptyMap(),
			_bqOrderRepository.getOrderAverageCurrencyValues(
				null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderIncompleteCurrencyValues() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, BigDecimal> orderIncompleteCurrencyValues =
			_bqOrderRepository.getOrderIncompleteCurrencyValues(
				123L, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertNotNull(orderIncompleteCurrencyValues);
		Assertions.assertNotNull(orderIncompleteCurrencyValues.get("USD"));

		BigDecimal actualValue = orderIncompleteCurrencyValues.get("USD");

		BigDecimal expectedValue = new BigDecimal("20.0");

		Assertions.assertEquals(
			expectedValue.stripTrailingZeros(),
			actualValue.stripTrailingZeros());

		Assertions.assertNotNull(orderIncompleteCurrencyValues.get("EUR"));

		actualValue = orderIncompleteCurrencyValues.get("EUR");
		expectedValue = new BigDecimal("10.0");

		Assertions.assertEquals(
			expectedValue.stripTrailingZeros(),
			actualValue.stripTrailingZeros());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderIncompleteCurrencyValuesWithEmptyDataSourceIds() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Assertions.assertEquals(
			Collections.emptyMap(),
			_bqOrderRepository.getOrderIncompleteCurrencyValues(
				null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId()));
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderTotalCurrencyValues() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, BigDecimal> orderTotalCurrencyValues =
			_bqOrderRepository.getOrderTotalCurrencyValues(
				123L, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertNotNull(orderTotalCurrencyValues);
		Assertions.assertNotNull(orderTotalCurrencyValues.get("USD"));

		BigDecimal actualValue = orderTotalCurrencyValues.get("USD");

		BigDecimal expectedValue = new BigDecimal("70.0");

		Assertions.assertEquals(
			expectedValue.stripTrailingZeros(),
			actualValue.stripTrailingZeros());

		Assertions.assertNotNull(orderTotalCurrencyValues.get("EUR"));

		actualValue = orderTotalCurrencyValues.get("EUR");
		expectedValue = new BigDecimal("40.0");

		Assertions.assertEquals(
			expectedValue.stripTrailingZeros(),
			actualValue.stripTrailingZeros());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderTotalCurrencyValuesWithEmptyDataSourceIds() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Assertions.assertEquals(
			Collections.emptyMap(),
			_bqOrderRepository.getOrderTotalCurrencyValues(
				null, timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId()));
	}

	@Autowired
	private BQOrderRepository _bqOrderRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}