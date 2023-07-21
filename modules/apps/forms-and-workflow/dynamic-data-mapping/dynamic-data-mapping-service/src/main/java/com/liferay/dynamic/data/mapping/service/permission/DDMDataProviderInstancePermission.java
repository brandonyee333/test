/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.service.permission;

import com.liferay.dynamic.data.mapping.model.DDMDataProviderInstance;
import com.liferay.dynamic.data.mapping.service.DDMDataProviderInstanceLocalServiceUtil;
import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Leonardo Barros
 */
public class DDMDataProviderInstancePermission {

	public static void check(
			PermissionChecker permissionChecker, long dataProviderInstanceId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, dataProviderInstanceId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, DDMDataProviderInstance.class.getName(),
				dataProviderInstanceId, actionId);
		}
	}

	public static boolean contains(
		PermissionChecker permissionChecker,
		DDMDataProviderInstance dataProviderInstance, String actionId) {

		String portletId = PortletProviderUtil.getPortletId(
			DDMDataProviderInstance.class.getName(),
			PortletProvider.Action.EDIT);

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, dataProviderInstance.getGroupId(),
			DDMDataProviderInstance.class.getName(),
			dataProviderInstance.getDataProviderInstanceId(), portletId,
			actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		if (permissionChecker.hasOwnerPermission(
				dataProviderInstance.getCompanyId(),
				DDMDataProviderInstance.class.getName(),
				dataProviderInstance.getDataProviderInstanceId(),
				dataProviderInstance.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			dataProviderInstance.getGroupId(),
			DDMDataProviderInstance.class.getName(),
			dataProviderInstance.getDataProviderInstanceId(), actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long dataProviderInstanceId,
			String actionId)
		throws PortalException {

		DDMDataProviderInstance dataProviderInstance =
			DDMDataProviderInstanceLocalServiceUtil.getDDMDataProviderInstance(
				dataProviderInstanceId);

		return contains(permissionChecker, dataProviderInstance, actionId);
	}

}