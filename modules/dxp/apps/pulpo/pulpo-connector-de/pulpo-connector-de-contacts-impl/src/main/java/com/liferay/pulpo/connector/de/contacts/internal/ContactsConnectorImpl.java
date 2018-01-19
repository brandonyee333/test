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

package com.liferay.pulpo.connector.de.contacts.internal;

import com.liferay.lcs.messaging.MessageBusMessage;
import com.liferay.osb.lcs.messaging.LCSMessageBusService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pulpo.connector.de.contacts.ContactsConnector;
import com.liferay.pulpo.connector.de.contacts.model.ContactsModelListener;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shinn Lok
 */
@Component(
	property = {
		"json.web.service.context.name=pulpo",
		"json.web.service.context.path=pulpo"
	},
	service = ContactsConnector.class
)
@JSONWebService
public class ContactsConnectorImpl implements ContactsConnector {

	public static final String PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME =
		"PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME";

	@Override
	public void sendMessage(String destinationName, String payload) {
		sendMessage(destinationName, payload, null);
	}

	@Override
	public void sendMessage(
		String destinationName, String payload, Map<String, String> metadata) {

		MessageBusMessage message = new MessageBusMessage();

		message.setPayload(payload);

		if (metadata != null) {
			Map<String, Object> values = new HashMap<>();

			values.putAll(metadata);

			message.setValues(values);
		}

		destinationName += "_" + _getEnvironmentUniqueName();

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Sending message %s to destination %s", payload,
					destinationName));
		}

		_lcsMessageBusService.sendMessage(destinationName, message);
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private String _getEnvironmentUniqueName() {
		String environmentUniqueName = System.getenv(
			PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME);

		if (Validator.isNull(environmentUniqueName)) {
			throw new RuntimeException(
				String.format(
					"Environment variable %s must be set",
					PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME));
		}

		return environmentUniqueName;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ContactsConnectorImpl.class);

	@Reference
	private LCSMessageBusService _lcsMessageBusService;

	private final Map<String, ContactsModelListener> _modelListeners =
		new HashMap<>();

	@Reference
	private UserLocalService _userLocalService;

}