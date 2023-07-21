/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fabrica.core.distribution;

import com.liferay.fabrica.core.distribution.client.Client;
import com.liferay.fabrica.core.distribution.server.Server;

import java.util.Collection;
import java.util.Map;

/**
 * @author Miguel Pastor
 */
public interface DistributionProvider {

	public Client registerClient(
		Class<?>[] classNames, Map<String, ?> properties);

	public Collection<Server> registerServer(
		Object object, Class<?>[] classNames, Map<String, ?> properties);

	public void unregisterServer(Server server);

}