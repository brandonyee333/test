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