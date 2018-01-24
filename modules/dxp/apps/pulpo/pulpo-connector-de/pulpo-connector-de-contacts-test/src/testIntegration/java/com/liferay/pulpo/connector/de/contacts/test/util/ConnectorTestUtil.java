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

package com.liferay.pulpo.connector.de.contacts.test.util;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pulpo.connector.de.contacts.internal.model.listener.UserModelListener;

import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/**
 * @author Cristina González
 */
public class ConnectorTestUtil {

	public static BlockingQueue<Result> registerContactMessageListener(
		final String destinationName, final Consumer<String> validation) {

		final BlockingQueue<Result> result = new ArrayBlockingQueue(1, true);

		MessageBusUtil.registerMessageListener(
			destinationName,
			new MessageListener() {

				@Override
				public void receive(Message message) {
					String payload = (String)message.getPayload();
					Map<String, Object> metadata = message.getValues();

					try {
						validation.accept(payload);

						String connectorTransactionUuid = MapUtil.getString(
							metadata,
							UserModelListener.
								CONNECTOR_TRANSACTION_UUID_METADATA_KEY);

						if (Validator.isNull(connectorTransactionUuid)) {
							result.put(
								new Result(
									"Transaction was not registered", null));
						}
						else {
							result.put(
								new Result("OK", connectorTransactionUuid));
						}
					}
					catch (AssertionError ae) {
						try {
							result.put(new Result(ae.getMessage(), null));
						}
						catch (InterruptedException ie) {
							ie.printStackTrace();
						}
					}
					catch (InterruptedException ie) {
						ie.printStackTrace();
					}
					catch (Exception e) {
						try {
							result.put(new Result(e.getMessage(), null));
						}
						catch (InterruptedException ie) {
							ie.printStackTrace();
						}
					}
					finally {
						MessageBusUtil.unregisterMessageListener(
							destinationName, this);
					}
				}

			});

		return result;
	}

	public static class Result {

		public Result(String message, String connectorTransactionUuid) {
			_message = message;
			_connectorTransactionUuid = connectorTransactionUuid;
		}

		public String getConnectorTransactionUuid() {
			return _connectorTransactionUuid;
		}

		public String getMessage() {
			return _message;
		}

		private final String _connectorTransactionUuid;
		private final String _message;

	}

}