/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.messageboards.service.permission;

import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;

/**
 * @author Brian Wing Shun Chan
 */
@OSGiBeanProperties(
	property = "model.class.name=com.liferay.message.boards.kernel.model.MBThread"
)
public class MBThreadPermission
	extends MBMessagePermission implements BaseModelPermissionChecker {
}