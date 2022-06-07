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
import com.liferay.osb.asah.common.model.OrderTotalValue;
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
		Channel channel = new Channel("channel");

		channel.setId(11L);
		channel.setIsNew(true);

		_dataSource = new DataSource("Liferay Italy");

		_dataSource.setCredentialType("Token Authentication");
		_dataSource.setFaroBackendSecuritySignature(
			"faroBackendSecuritySignature");
		_dataSource.setId(123L);
		_dataSource.setIsNew(true);
		_dataSource.setProviderType("LIFERAY");
		_dataSource.setState("READY");
		_dataSource.setStatus("STARTED");
		_dataSource.setURL("");

		_dataSource = _dataSourceRepository.save(_dataSource);

		channel.addChannelDataSource(
			new ChannelDataSource(_dataSource.getId(), null));

		_channelRepository.save(channel);
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderTotalValues() {
		Map<String, OrderTotalValue> orderTotalValues =
			_commerceDashboardDog.getOrderTotalValues(
				11L, false, TimeRange.LAST_7_DAYS);

		Assertions.assertNotNull(orderTotalValues);

		OrderTotalValue orderTotalValue = orderTotalValues.get("USD");

		Assertions.assertNotNull(orderTotalValue);

		Assertions.assertNotNull(orderTotalValue.getValue());

		Assertions.assertNull(orderTotalValue.getPercentageVariation());
	}

	@SQLResource(resourcePath = "test_bq_order.sql")
	@Test
	public void testGetOrderTotalValuesWithPreviousPeriodComparison() {
		Map<String, OrderTotalValue> orderTotalValues =
			_commerceDashboardDog.getOrderTotalValues(
				11L, true, TimeRange.LAST_7_DAYS);

		Assertions.assertNotNull(orderTotalValues);

		OrderTotalValue orderTotalValue = orderTotalValues.get("USD");

		Assertions.assertNotNull(orderTotalValue);

		Assertions.assertNotNull(orderTotalValue.getValue());

		Assertions.assertNotNull(orderTotalValue.getPercentageVariation());
	}

	@Autowired
	private ChannelRepository _channelRepository;

	@Autowired
	private CommerceDashboardDog _commerceDashboardDog;

	private DataSource _dataSource;

	@Autowired
	private DataSourceRepository _dataSourceRepository;

}