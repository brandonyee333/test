/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.hot.deploy.jmx.listener.mbean.manager;

import java.util.List;

/**
 * @author Cristina González
 */
public interface PluginMBeanManager {

	public List<String> listLegacyPlugins();

}