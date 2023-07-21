/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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