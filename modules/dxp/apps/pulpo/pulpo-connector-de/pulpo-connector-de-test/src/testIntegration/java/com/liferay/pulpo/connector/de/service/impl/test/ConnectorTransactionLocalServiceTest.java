/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.pulpo.connector.de.service.impl.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.pulpo.connector.de.model.ConnectorTransaction;
import com.liferay.pulpo.connector.de.service.ConnectorTransactionLocalServiceUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eduardo Garcia
 */
@RunWith(Arquillian.class)
public class ConnectorTransactionLocalServiceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Before
	public void setUp() throws PortalException {
		_serviceContext = ServiceContextTestUtil.getServiceContext(
			TestPropsValues.getGroupId(), TestPropsValues.getUserId());
	}

	@Test
	public void testAddConnectorTransaction() throws Exception {
		int count =
			ConnectorTransactionLocalServiceUtil.
				getConnectorTransactionsCount();

		ConnectorTransactionLocalServiceUtil.addConnectorTransaction(
			TestPropsValues.getUserId(), RandomTestUtil.randomString(),
			RandomTestUtil.randomLong(), RandomTestUtil.randomString(),
			RandomTestUtil.randomString(), _serviceContext);

		Assert.assertEquals(
			"ConnectorTransaction hasn't been created properly", count + 1,
			ConnectorTransactionLocalServiceUtil.
				getConnectorTransactionsCount());
	}

	@Test
	public void testDeleteUnit() throws Exception {
		int count =
			ConnectorTransactionLocalServiceUtil.
				getConnectorTransactionsCount();

		ConnectorTransaction connectorTransaction =
			ConnectorTransactionLocalServiceUtil.addConnectorTransaction(
				TestPropsValues.getUserId(), RandomTestUtil.randomString(),
				RandomTestUtil.randomLong(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), _serviceContext);

		ConnectorTransactionLocalServiceUtil.deleteConnectorTransaction(
			connectorTransaction.getConnectorTransactionId());

		Assert.assertEquals(
			"ConnectorTransaction hasn't been deleted properly", count,
			ConnectorTransactionLocalServiceUtil.
				getConnectorTransactionsCount());
	}

	@Test
	public void testUpdateUnit() throws Exception {
		ConnectorTransaction initialConnectorTransaction =
			ConnectorTransactionLocalServiceUtil.addConnectorTransaction(
				TestPropsValues.getUserId(), RandomTestUtil.randomString(),
				RandomTestUtil.randomLong(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), _serviceContext);

		String updatedStatus = RandomTestUtil.randomString();

		ConnectorTransactionLocalServiceUtil.updateConnectorTransaction(
			initialConnectorTransaction.getConnectorTransactionId(),
			updatedStatus);

		ConnectorTransaction updatedConnectorTransaction =
			ConnectorTransactionLocalServiceUtil.getConnectorTransaction(
				initialConnectorTransaction.getConnectorTransactionId());

		Assert.assertEquals(
			"The ConnectorTransaction name has not been propertly updated",
			updatedStatus, updatedConnectorTransaction.getStatus());
	}

	private ServiceContext _serviceContext;

}