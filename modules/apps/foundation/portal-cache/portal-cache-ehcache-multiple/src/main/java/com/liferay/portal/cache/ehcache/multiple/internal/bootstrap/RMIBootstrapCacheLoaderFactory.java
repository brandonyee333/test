/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.ehcache.multiple.internal.bootstrap;

import com.liferay.portal.cache.PortalCacheBootstrapLoader;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Properties;

/**
 * @author Tina Tian
 */
public class RMIBootstrapCacheLoaderFactory
	extends net.sf.ehcache.distribution.RMIBootstrapCacheLoaderFactory {

	protected boolean extractBootstrapAsynchronously(Properties properties) {
		return GetterUtil.getBoolean(
			properties.getProperty(
				PortalCacheBootstrapLoader.BOOTSTRAP_ASYNCHRONOUSLY),
			PortalCacheBootstrapLoader.DEFAULT_BOOTSTRAP_ASYNCHRONOUSLY);
	}

}