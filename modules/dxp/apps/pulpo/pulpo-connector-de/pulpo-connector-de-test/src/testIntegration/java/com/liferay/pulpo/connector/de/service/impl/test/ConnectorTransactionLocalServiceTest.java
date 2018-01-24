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
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.pulpo.connector.de.model.ConnectorTransaction;
import com.liferay.pulpo.connector.de.service.ConnectorTransactionLocalServiceUtil;

import org.junit.Assert;
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

	@Test
	public void testAddConnectorTransaction() throws Exception {
		int count =
			ConnectorTransactionLocalServiceUtil.
				getConnectorTransactionsCount();

		ConnectorTransaction connectorTransaction =
			ConnectorTransactionLocalServiceUtil.addConnectorTransaction(
				TestPropsValues.getUserId(), RandomTestUtil.randomLong(),
				RandomTestUtil.randomLong(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString());

		Assert.assertEquals(
			"ConnectorTransaction hasn't been created properly", count + 1,
			ConnectorTransactionLocalServiceUtil.
				getConnectorTransactionsCount());

		Assert.assertNotNull(
			"ConnectorTransaction does not contain a valid UUID",
			connectorTransaction.getConnectorTransactionUuid());
	}

	@Test
	public void testAddConnectorTransactionWithSameClassNameIdAndClassPK()
		throws Exception {

		long classNameId = RandomTestUtil.randomLong();
		long classPK = RandomTestUtil.randomLong();

		ConnectorTransaction connectorTransaction1 =
			ConnectorTransactionLocalServiceUtil.addConnectorTransaction(
				TestPropsValues.getUserId(), classNameId, classPK,
				RandomTestUtil.randomString(), RandomTestUtil.randomString());

		ConnectorTransaction connectorTransaction2 =
			ConnectorTransactionLocalServiceUtil.addConnectorTransaction(
				TestPropsValues.getUserId(), classNameId, classPK,
				RandomTestUtil.randomString(), RandomTestUtil.randomString());

		Assert.assertNotEquals(
			"A new operation on the same model entity should result in a new " +
				"ConnectorTransaction",
			connectorTransaction1.getConnectorTransactionUuid(),
			connectorTransaction2.getConnectorTransactionUuid());
	}

	@Test
	public void testDeleteUnit() throws Exception {
		int count =
			ConnectorTransactionLocalServiceUtil.
				getConnectorTransactionsCount();

		ConnectorTransaction connectorTransaction =
			ConnectorTransactionLocalServiceUtil.addConnectorTransaction(
				TestPropsValues.getUserId(), RandomTestUtil.randomLong(),
				RandomTestUtil.randomLong(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString());

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
				TestPropsValues.getUserId(), RandomTestUtil.randomLong(),
				RandomTestUtil.randomLong(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString());

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

}