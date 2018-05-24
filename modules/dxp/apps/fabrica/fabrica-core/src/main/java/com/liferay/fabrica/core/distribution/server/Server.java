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

package com.liferay.fabrica.core.distribution.server;

import java.io.IOException;

import java.net.InetSocketAddress;

import java.util.Map;

/**
 * @author Miguel Pastor
 */
public interface Server {

	public InetSocketAddress getInetSocketAddress();

	public Map<String, ?> getInfo();

	public void start() throws IOException;

	public void stop() throws InterruptedException;

}