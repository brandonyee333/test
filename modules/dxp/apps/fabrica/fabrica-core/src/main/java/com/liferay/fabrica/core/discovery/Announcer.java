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