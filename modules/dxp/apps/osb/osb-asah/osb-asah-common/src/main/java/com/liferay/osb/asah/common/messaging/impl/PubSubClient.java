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

package com.liferay.osb.asah.common.messaging.impl;

import io.grpc.ManagedChannel;

/**
 * @author Marcellus Tavares
 */
public class PubSubClient<T extends AutoCloseable> implements AutoCloseable {

	public PubSubClient(T client, ManagedChannel managedChannel) {
		_client = client;
		_managedChannel = managedChannel;
	}

	@Override
	public void close() throws Exception {
		_client.close();

		if (_managedChannel != null) {
			_managedChannel.shutdown();
		}
	}

	public T get() {
		return _client;
	}

	private final T _client;
	private final ManagedChannel _managedChannel;

}