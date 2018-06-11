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

package com.liferay.osb.customer.rabbitmq.connector.connection;

import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * @author Vishal Reddy
 */
public interface ConnectionManager {

	public void connect();

	public Channel createChannel() throws IOException;

	public Channel createChannel(int prefetchCount) throws IOException;

	public void disconnect();

	public boolean isConnected();

	public void reconnect();

}