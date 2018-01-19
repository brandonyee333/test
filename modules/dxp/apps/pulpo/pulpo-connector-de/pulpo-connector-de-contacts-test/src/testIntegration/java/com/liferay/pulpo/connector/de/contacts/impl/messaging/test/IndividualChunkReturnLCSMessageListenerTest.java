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

package com.liferay.pulpo.connector.de.contacts.impl.messaging.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.lcs.messaging.MessageBusMessage;
import com.liferay.osb.lcs.messaging.LCSMessageBusService;
import com.liferay.osb.lcs.messaging.LCSMessageListener;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.pulpo.connector.de.contacts.impl.messaging.IndividualChunkReturnLCSMessageListener;
import com.liferay.pulpo.connector.de.contacts.impl.model.ConnectorTransactionResponse;
import com.liferay.pulpo.connector.de.model.ConnectorTransaction;
import com.liferay.pulpo.connector.de.service.ConnectorTransactionLocalService;
import com.liferay.registry.Filter;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eduardo Garcia
 */
@RunWith(Arquillian.class)
public class IndividualChunkReturnLCSMessageListenerTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		Registry registry = RegistryUtil.getRegistry();

		Filter filter = registry.getFilter(
			"(&(objectClass=" +
				ConnectorTransactionLocalService.class.getName() + "))");

		_connectorTransactionLocalServiceServiceTracker =
			registry.trackServices(filter);

		_connectorTransactionLocalServiceServiceTracker.open();

		filter = registry.getFilter(
			"(&(objectClass=" + LCSMessageBusService.class.getName() + "))");

		_lcsMessageBusServiceServiceTracker = registry.trackServices(filter);

		_lcsMessageBusServiceServiceTracker.open();
	}

	@AfterClass
	public static void tearDownClass() {
		_connectorTransactionLocalServiceServiceTracker.close();
		_lcsMessageBusServiceServiceTracker.close();
	}

	@Test
	public void testReceive() throws Exception {
		ConnectorTransactionLocalService connectorTransactionLocalService =
			_connectorTransactionLocalServiceServiceTracker.getService();

		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(
				TestPropsValues.getGroupId(), TestPropsValues.getUserId());

		ConnectorTransaction connectorTransaction =
			connectorTransactionLocalService.addConnectorTransaction(
				TestPropsValues.getUserId(), RandomTestUtil.randomString(),
				RandomTestUtil.randomLong(), RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), serviceContext);

		ConnectorTransactionResponse connectorTransactionResponse =
			new ConnectorTransactionResponse();

		connectorTransactionResponse.setStatus(RandomTestUtil.randomString());
		connectorTransactionResponse.setConnectionTransactionId(
			connectorTransaction.getConnectorTransactionId());

		ObjectMapper objMapper = new ObjectMapper();

		String jsonConnectorTransactionResponse = objMapper.writeValueAsString(
			connectorTransactionResponse);

		MessageBusMessage messageBusMessage = new MessageBusMessage();

		messageBusMessage.setPayload(jsonConnectorTransactionResponse);

		LCSMessageBusService lcsMessageBusService =
			_lcsMessageBusServiceServiceTracker.getService();

		LCSMessageListener individualChunkAddReturnMessageListener =
			new IndividualChunkReturnLCSMessageListener(
				lcsMessageBusService, connectorTransactionLocalService);

		individualChunkAddReturnMessageListener.receive(messageBusMessage);

		ConnectorTransaction updatedConnectorTransaction =
			connectorTransactionLocalService.fetchConnectorTransaction(
				connectorTransaction.getConnectorTransactionId());

		Assert.assertEquals(
			connectorTransactionResponse.getStatus(),
			updatedConnectorTransaction.getStatus());
	}

	private static ServiceTracker
		<ConnectorTransactionLocalService, ConnectorTransactionLocalService>
			_connectorTransactionLocalServiceServiceTracker;
	private static ServiceTracker
		<LCSMessageBusService, LCSMessageBusService>
			_lcsMessageBusServiceServiceTracker;

}