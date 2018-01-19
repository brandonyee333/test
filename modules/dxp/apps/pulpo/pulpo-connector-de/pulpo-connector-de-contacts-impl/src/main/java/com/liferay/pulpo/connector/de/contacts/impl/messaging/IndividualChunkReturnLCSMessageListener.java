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

package com.liferay.pulpo.connector.de.contacts.impl.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.lcs.messaging.MessageBusMessage;
import com.liferay.osb.lcs.messaging.LCSMessageBusService;
import com.liferay.osb.lcs.messaging.LCSMessageListener;
import com.liferay.osb.lcs.messaging.LCSMessageListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.pulpo.connector.de.contacts.impl.model.ConnectorTransactionResponse;
import com.liferay.pulpo.connector.de.exception.NoSuchConnectorTransactionException;
import com.liferay.pulpo.connector.de.service.ConnectorTransactionLocalService;

/**
 * Represents a LCS Message Listener for Individual Chunk return messages.
 *
 * @author Eduardo Garcia
 */
public class IndividualChunkReturnLCSMessageListener
	implements LCSMessageListener {

	/**
	 * Builds an IndividualChunkReturnLCSMessageListener.
	 *
	 * @param lcsMessageBusService The LCSMessageBusService
	 * @param connectorTransactionLocalService The ConnectorTransactionService
	 */
	public IndividualChunkReturnLCSMessageListener(
		LCSMessageBusService lcsMessageBusService,
		ConnectorTransactionLocalService connectorTransactionLocalService) {

		_lcsMessageBusService = lcsMessageBusService;
		_connectorTransactionLocalService = connectorTransactionLocalService;
	}

	@Override
	public void receive(MessageBusMessage messageBusMessage)
		throws LCSMessageListenerException {

		if (_log.isInfoEnabled()) {
			_log.info("Received message:" + messageBusMessage);
		}

		String payload = messageBusMessage.getPayload();

		if (_log.isInfoEnabled()) {
			_log.info("Message payload: " + payload);
		}

		ObjectMapper objectMapper = new ObjectMapper();

		try {
			ConnectorTransactionResponse connectorTransactionResponse =
				objectMapper.readValue(
					payload, ConnectorTransactionResponse.class);

			_connectorTransactionLocalService.updateConnectorTransaction(
				connectorTransactionResponse.getConnectionTransactionId(),
				connectorTransactionResponse.getStatus());
		}
		catch (NoSuchConnectorTransactionException nscte) {
			_log.error(
				"Invalid transactionId in return message " + payload, nscte);
		}
		catch (Exception e) {
			_log.error("Cannot process return message " + payload, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		IndividualChunkReturnLCSMessageListener.class);

	private final ConnectorTransactionLocalService
		_connectorTransactionLocalService;
	private final LCSMessageBusService _lcsMessageBusService;

}