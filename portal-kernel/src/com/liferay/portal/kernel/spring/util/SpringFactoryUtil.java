/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.spring.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class SpringFactoryUtil {

	public static SpringFactory getSpringFactory() {
		PortalRuntimePermission.checkGetBeanProperty(SpringFactoryUtil.class);

		return _springFactory;
	}

	public static Object newBean(String className)
		throws SpringFactoryException {

		return getSpringFactory().newBean(className);
	}

	public static Object newBean(
			String className, Map<String, Object> properties)
		throws SpringFactoryException {

		return getSpringFactory().newBean(className, properties);
	}

	public void setSpringFactory(SpringFactory springFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_springFactory = springFactory;
	}

	private static SpringFactory _springFactory;

}