/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fabrica.core.discovery;

import java.net.SocketAddress;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Miguel Pastor
 */
public interface Announcer {

	public CompletableFuture<Announcement> announce(Map<String, ?> properties);

	public CompletableFuture<Announcement> announce(
		SocketAddress socketAddress, Map<String, ?> properties);

	public String schema();

}