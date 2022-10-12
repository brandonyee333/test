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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.dog.CommerceDashboardDog;
import com.liferay.osb.asah.common.entity.Channel;
import com.liferay.osb.asah.common.entity.ChannelDataSource;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.model.CurrencyValue;
import com.liferay.osb.asah.common.model.TimeRange;
import com.liferay.osb.asah.common.repository.ChannelRepository;
import com.liferay.osb.asah.common.repository.DataSourceRepository;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Riccardo Ferrari
 */
public class CommerceDashboardDogTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() {
		_dataSource = new DataSource("Liferay Italy");

		_dataSource.setCredentialType("Token Authentication");
		_dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		_dataSource.setId(123L);
		_dataSource.setIsNew(Boolean.TRUE);
		_dataSource.setProviderType("LIFERAY");
		_dataSource.setState("READY");
		_dataSource.setStatus("STARTED");
		_dataSource.setURL("");

		_dataSource = _dataSourceRepository.save(_dataSource);

		Channel channel = new Channel("channel");

		channel.setId(11L);
		channel.setIsNew(Boolean.TRUE);

		channel.addChannelDataSource(
			new ChannelDataSource(_dataSource.getId(), null));

		_channelRepository.save(channel);
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderAccountAverageCurrencyValues() {
		Map<String, CurrencyValue> orderAccountAverageCurrencyValues =
			_commerceDashboardDog.getOrderAccountAverageCurrencyValues(
				11L, false, TimeRange.LAST_7_DAYS);

		Assertions.assertNotNull(orderAccountAverageCurrencyValues);

		CurrencyValue currencyValue = orderAccountAverageCurrencyValues.get(
			"USD");

		Assertions.assertNotNull(currencyValue);
		Assertions.assertNotNull(currencyValue.getValue());
		Assertions.assertNull(currencyValue.getPercentageVariation());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderAccountAverageCurrencyValuesWithPreviousPeriodComparison() {
		Map<String, CurrencyValue> orderAccountAverageCurrencyValues =
			_commerceDashboardDog.getOrderAccountAverageCurrencyValues(
				11L, true, TimeRange.LAST_7_DAYS);

		Assertions.assertNotNull(orderAccountAverageCurrencyValues);

		CurrencyValue currencyValue = orderAccountAverageCurrencyValues.get(
			"USD");

		Assertions.assertNotNull(currencyValue);
		Assertions.assertNotNull(currencyValue.getValue());
		Assertions.assertNotNull(currencyValue.getPercentageVariation());
		Assertions.assertEquals(5.0, currencyValue.getPercentageVariation());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderAverageCurrencyValues() {
		Map<String, CurrencyValue> orderAverageCurrencyValues =
			_commerceDashboardDog.getOrderAverageCurrencyValues(
				11L, false, TimeRange.LAST_7_DAYS);

		Assertions.assertNotNull(orderAverageCurrencyValues);

		CurrencyValue currencyValue = orderAverageCurrencyValues.get("USD");

		Assertions.assertNotNull(currencyValue);
		Assertions.assertNotNull(currencyValue.getValue());
		Assertions.assertNull(currencyValue.getPercentageVariation());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderAverageCurrencyValuesWithPreviousPeriodComparison() {
		Map<String, CurrencyValue> orderAverageCurrencyValues =
			_commerceDashboardDog.getOrderAverageCurrencyValues(
				11L, true, TimeRange.LAST_7_DAYS);

		Assertions.assertNotNull(orderAverageCurrencyValues);

		CurrencyValue currencyValue = orderAverageCurrencyValues.get("USD");

		Assertions.assertNotNull(currencyValue);
		Assertions.assertNotNull(currencyValue.getValue());
		Assertions.assertNotNull(currencyValue.getPercentageVariation());
		Assertions.assertEquals(0.0, currencyValue.getPercentageVariation());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderIncompleteCurrencyValues() {
		Map<String, CurrencyValue> orderIncompleteCurrencyValues =
			_commerceDashboardDog.getOrderIncompleteCurrencyValues(
				11L, false, TimeRange.LAST_7_DAYS);

		Assertions.assertNotNull(orderIncompleteCurrencyValues);

		CurrencyValue currencyValue = orderIncompleteCurrencyValues.get("USD");

		Assertions.assertNotNull(currencyValue);
		Assertions.assertNotNull(currencyValue.getValue());
		Assertions.assertNull(currencyValue.getPercentageVariation());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderIncompleteCurrencyValuesWithPreviousPeriodComparison() {
		Map<String, CurrencyValue> orderIncompleteCurrencyValues =
			_commerceDashboardDog.getOrderIncompleteCurrencyValues(
				11L, true, TimeRange.LAST_7_DAYS);

		Assertions.assertNotNull(orderIncompleteCurrencyValues);

		CurrencyValue currencyValue = orderIncompleteCurrencyValues.get("USD");

		Assertions.assertNotNull(currencyValue);
		Assertions.assertNotNull(currencyValue.getValue());
		Assertions.assertNotNull(currencyValue.getPercentageVariation());
		Assertions.assertEquals(0.0, currencyValue.getPercentageVariation());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderTotalCurrencyValues() {
		Map<String, CurrencyValue> orderTotalCurrencyValues =
			_commerceDashboardDog.getOrderTotalCurrencyValues(
				11L, false, TimeRange.LAST_7_DAYS);

		Assertions.assertNotNull(orderTotalCurrencyValues);

		CurrencyValue currencyValue = orderTotalCurrencyValues.get("USD");

		Assertions.assertNotNull(currencyValue);
		Assertions.assertNotNull(currencyValue.getValue());
		Assertions.assertNull(currencyValue.getPercentageVariation());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderTotalCurrencyValuesWithPreviousPeriodComparison() {
		Map<String, CurrencyValue> orderTotalCurrencyValues =
			_commerceDashboardDog.getOrderTotalCurrencyValues(
				11L, true, TimeRange.LAST_7_DAYS);

		Assertions.assertNotNull(orderTotalCurrencyValues);

		CurrencyValue currencyValue = orderTotalCurrencyValues.get("USD");

		Assertions.assertNotNull(currencyValue);
		Assertions.assertNotNull(currencyValue.getValue());
		Assertions.assertNotNull(currencyValue.getPercentageVariation());
		Assertions.assertEquals(75.0, currencyValue.getPercentageVariation());
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private CommerceDashboardDog _commerceDashboardDog;

	private DataSource _dataSource;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

}