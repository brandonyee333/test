/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.orm;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 */
public class PortalCustomSQLUtil {

	public static String get(String id) {
		return getPortalCustomSQL().get(id);
	}

	public static PortalCustomSQL getPortalCustomSQL() {
		PortalRuntimePermission.checkGetBeanProperty(PortalCustomSQLUtil.class);

		return _portalCustomSQL;
	}

	public void setPortalCustomSQL(PortalCustomSQL portalCustomSQL) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_portalCustomSQL = portalCustomSQL;
	}

	private static PortalCustomSQL _portalCustomSQL;

}