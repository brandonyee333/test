/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class PropertyFactoryUtil {

	public static Property forName(String propertyName) {
		return getPropertyFactory().forName(propertyName);
	}

	public static PropertyFactory getPropertyFactory() {
		PortalRuntimePermission.checkGetBeanProperty(PropertyFactoryUtil.class);

		return _projectionFactory;
	}

	public void setPropertyFactory(PropertyFactory projectionFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_projectionFactory = projectionFactory;
	}

	private static PropertyFactory _projectionFactory;

}