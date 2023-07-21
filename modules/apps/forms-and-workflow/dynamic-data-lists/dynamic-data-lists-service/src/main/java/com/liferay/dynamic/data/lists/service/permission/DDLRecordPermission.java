/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.lists.service.permission;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

/**
 * @author Marcellus Tavares
 */
public class DDLRecordPermission {

	public static void check(
			PermissionChecker permissionChecker, DDLRecord record,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, record, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, DDLRecord.class.getName(),
				record.getRecordId(), actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, long recordId, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, recordId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, DDLRecord.class.getName(), recordId,
				actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, DDLRecord record,
			String actionId)
		throws PortalException {

		DDLRecordSet recordSet = record.getRecordSet();

		return DDLRecordSetPermission.contains(
			permissionChecker, recordSet, actionId);
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long recordId, String actionId)
		throws PortalException {

		DDLRecord record = DDLRecordLocalServiceUtil.getRecord(recordId);

		return contains(permissionChecker, record, actionId);
	}

}