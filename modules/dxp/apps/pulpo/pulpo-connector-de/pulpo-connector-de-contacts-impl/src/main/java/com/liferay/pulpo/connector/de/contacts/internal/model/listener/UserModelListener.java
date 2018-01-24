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

package com.liferay.pulpo.connector.de.contacts.internal.model.listener;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.pulpo.connector.de.constants.ConnectorTransactionOperation;
import com.liferay.pulpo.connector.de.constants.ConnectorTransactionStatus;
import com.liferay.pulpo.connector.de.contacts.ContactsConnector;
import com.liferay.pulpo.connector.de.contacts.constants.IndividualChunksDestinationNames;
import com.liferay.pulpo.connector.de.contacts.model.ContactsModelListener;
import com.liferay.pulpo.connector.de.contacts.model.serializer.Serializer;
import com.liferay.pulpo.connector.de.model.ConnectorTransaction;
import com.liferay.pulpo.connector.de.service.ConnectorTransactionLocalService;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * Represents a contacts model listener for users.
 *
 * @author Shinn Lok
 */
@Component(immediate = true, service = ModelListener.class)
public class UserModelListener extends ContactsModelListener<User> {

	public static final String CONNECTOR_TRANSACTION_UUID_METADATA_KEY =
		"connectorTransactionUUID";

	@Override
	public void onAfterCreate(User user) throws ModelListenerException {
		sendModel(user);
	}

	@Override
	public void onAfterRemove(User user) throws ModelListenerException {
		_sendMessage(
			user, IndividualChunksDestinationNames.DELETE,
			IndividualChunksDestinationNames.DELETE_RETURN,
			ConnectorTransactionOperation.DELETE);
	}

	@Override
	public void onAfterUpdate(User user) throws ModelListenerException {
		sendModel(user);
	}

	@Override
	public void sendModel(User user) {
		_sendMessage(
			user, IndividualChunksDestinationNames.ADD,
			IndividualChunksDestinationNames.ADD_RETURN,
			ConnectorTransactionOperation.ADD);
	}

	private void _sendMessage(
		User user, String destinationName, String responseDestinationName,
		String operation) {

		String payload = _serializer.writeAsString(user);

		try {
			long defaultUserId = _userLocalService.getDefaultUserId(
				user.getCompanyId());

			ConnectorTransaction connectorTransaction =
				_connectorTransactionLocalService.addConnectorTransaction(
					defaultUserId,
					_portal.getClassNameId(user.getModelClassName()),
					user.getUserId(), ConnectorTransactionStatus.SENT,
					operation);

			Map<String, String> metadata = new HashMap();

			metadata.put(
				CONNECTOR_TRANSACTION_UUID_METADATA_KEY,
				connectorTransaction.getConnectorTransactionUuid());

			_contactsConnector.sendMessage(
				destinationName, payload, metadata, responseDestinationName);
		}
		catch (Exception e) {
			_log.error(
				String.format(
					"Error sending message for operation %s on user %d",
					operation, user.getUserId()),
				e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserModelListener.class);

	@Reference
	private ConnectorTransactionLocalService _connectorTransactionLocalService;

	@Reference
	private ContactsConnector _contactsConnector;

	@Reference
	private Portal _portal;

	@Reference(
		target = "(className=com.liferay.portal.kernel.model.User)",
		unbind = "-"
	)
	private Serializer _serializer;

	@Reference
	private UserLocalService _userLocalService;

}