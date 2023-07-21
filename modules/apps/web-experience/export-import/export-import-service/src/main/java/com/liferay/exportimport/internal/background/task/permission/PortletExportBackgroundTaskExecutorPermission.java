/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.internal.background.task.permission;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.permission.GroupPermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(
	immediate = true,
	property = {
		"background.task.executor.class.name=com.liferay.exportimport.background.task.PortletExportBackgroundTaskExecutor",
		"model.class.name=com.liferay.portal.kernel.backgroundtask.BackgroundTask"
	},
	service = BaseModelPermissionChecker.class
)
public class PortletExportBackgroundTaskExecutorPermission
	implements BaseModelPermissionChecker {

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		if (!_groupPermission.contains(
				permissionChecker, groupId,
				ActionKeys.EXPORT_IMPORT_PORTLET_INFO)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker.getUserId(), ActionKeys.VIEW);
		}
	}

	@Reference
	private GroupPermission _groupPermission;

}