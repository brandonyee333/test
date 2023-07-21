/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.cache.ehcache;

/**
 * @author     Tina Tian
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.portal.cache.ehcache.internal.EhcacheConstants}
 */
@Deprecated
public interface EhcacheConstants {

	public static final String BOOTSTRAP_CACHE_LOADER_FACTORY_CLASS_NAME =
		"BOOTSTRAP_CACHE_LOADER_FACTORY_CLASS_NAME";

	public static final String CACHE_EVENT_LISTENER_FACTORY_CLASS_NAME =
		"CACHE_EVENT_LISTENER_FACTORY_CLASS_NAME";

	public static final String CACHE_MANAGER_LISTENER_FACTORY_CLASS_NAME =
		"CACHE_MANAGER_LISTENER_FACTORY_CLASS_NAME";

}