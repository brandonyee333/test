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

import java.util.Arrays;
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
	public void testGetOrderTotalValues() {
		TimeRange timeRange = TimeRange.LAST_7_DAYS;

		Map<String, BigDecimal> orderTotalValues =
			_bqOrderRepository.getOrderTotalValues(
				Arrays.asList(123L), timeRange.getEndLocalDateTime(),
				timeRange.getStartLocalDateTime(),
				_timeZoneDog.getTimeZoneId());

		Assertions.assertNotNull(orderTotalValues);
		Assertions.assertNotNull(orderTotalValues.get("USD"));

		BigDecimal actualValue = orderTotalValues.get("USD");

		BigDecimal expectedValue = new BigDecimal("50.0");

		Assertions.assertEquals(
			expectedValue.stripTrailingZeros(),
			actualValue.stripTrailingZeros());

		Assertions.assertNotNull(orderTotalValues.get("EUR"));

		actualValue = orderTotalValues.get("EUR");
		expectedValue = new BigDecimal("20.0");

		Assertions.assertEquals(
			expectedValue.stripTrailingZeros(),
			actualValue.stripTrailingZeros());
	}

	@Autowired
	private BQOrderRepository _bqOrderRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}