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

package com.liferay.pulpo.connector.de.contacts.internal.activator;

import com.liferay.osb.lcs.messaging.LCSMessageBusService;
import com.liferay.osb.lcs.messaging.LCSMessageListener;
import com.liferay.pulpo.connector.de.contacts.constants.IndividualChunksDestinationNames;
import com.liferay.pulpo.connector.de.contacts.impl.messaging.IndividualChunkReturnLCSMessageListener;
import com.liferay.pulpo.connector.de.service.ConnectorTransactionLocalService;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Registers the individual chunk listeners for return messages on bundle
 * activation and unregisters them on bundle deactivation.
 *
 * @author Eduardo Garcia
 */
@Component(immediate = true)
public class IndividualChunkReturnLCSMessageListenerActivator {

	/**
	 * Registers the individual chunk listeners for return messages on bundle
	 * activation
	 */
	@Activate
	public void start() {
		_individualChunkAddReturnMessageListener =
			new IndividualChunkReturnLCSMessageListener(
				_lcsMessageBusService, _connectorTransactionLocalService);

		_lcsMessageBusService.registerLCSMessageListener(
			IndividualChunksDestinationNames.ADD_RETURN,
			_individualChunkAddReturnMessageListener);

		_individualChunkDeleteReturnMessageListener =
			new IndividualChunkReturnLCSMessageListener(
				_lcsMessageBusService, _connectorTransactionLocalService);

		_lcsMessageBusService.registerLCSMessageListener(
			IndividualChunksDestinationNames.DELETE_RETURN,
			_individualChunkDeleteReturnMessageListener);
	}

	/**
	 * Unregisters the individual chunk listeners for return messages on bundle
	 * dectivation
	 */
	@Deactivate
	public void stop() {
		_lcsMessageBusService.unregisterLCSMessageListener(
			IndividualChunksDestinationNames.ADD_RETURN,
			_individualChunkAddReturnMessageListener);

		_lcsMessageBusService.unregisterLCSMessageListener(
			IndividualChunksDestinationNames.DELETE_RETURN,
			_individualChunkDeleteReturnMessageListener);
	}

	@Reference
	private ConnectorTransactionLocalService _connectorTransactionLocalService;

	private LCSMessageListener _individualChunkAddReturnMessageListener;
	private LCSMessageListener _individualChunkDeleteReturnMessageListener;

	@Reference
	private LCSMessageBusService _lcsMessageBusService;

}