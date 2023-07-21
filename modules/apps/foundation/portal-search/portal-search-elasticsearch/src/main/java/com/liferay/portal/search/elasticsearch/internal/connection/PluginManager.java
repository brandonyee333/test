/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch.internal.connection;

import java.io.IOException;

import java.nio.file.Path;

import org.elasticsearch.common.cli.Terminal;

/**
 * @author Artur Aquino
 * @author André de Oliveira
 */
public interface PluginManager {

	public void downloadAndExtract(
			String name, Terminal terminal, boolean batch)
		throws IOException;

	public Path[] getInstalledPluginsPaths() throws IOException;

	public boolean isCurrentVersion(Path path) throws IOException;

	public void removePlugin(String name, Terminal terminal) throws IOException;

}