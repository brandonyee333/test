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
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.pulpo.connector.de.contacts.ContactsConnector;
import com.liferay.pulpo.connector.de.contacts.constants.IndividualChunksDestinationNames;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.Serializer;
import com.liferay.pulpo.connector.de.contacts.model.ContactsModelListener;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shinn Lok
 */
@Component(immediate = true, service = ModelListener.class)
public class UserModelListener extends ContactsModelListener<User> {

	@Override
	public void onAfterCreate(User user) throws ModelListenerException {
		sendModel(user);
	}

	@Override
	public void onAfterRemove(User user) throws ModelListenerException {
		String payload = _serializer.writeAsString(user);

		_contactsConnector.sendMessage(
			IndividualChunksDestinationNames.DELETE, payload);
	}

	@Override
	public void onAfterUpdate(User user) throws ModelListenerException {
		sendModel(user);
	}

	@Override
	public void sendModel(User user) {
		String payload = _serializer.writeAsString(user);

		_contactsConnector.sendMessage(
			IndividualChunksDestinationNames.ADD, payload);
	}

	@Reference
	private ContactsConnector _contactsConnector;

	@Reference(
		target = "(className=com.liferay.portal.kernel.model.User)",
		unbind = "-"
	)
	private Serializer _serializer;

}