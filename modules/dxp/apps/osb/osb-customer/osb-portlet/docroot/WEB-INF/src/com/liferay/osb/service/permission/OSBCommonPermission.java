/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.service.permission;

import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.permission.CommonPermission;

/**
 * @author Amos Fong
 */
public class OSBCommonPermission implements CommonPermission {

	public OSBCommonPermission(CommonPermission commonPermission) {
		_commonPermission = commonPermission;
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long classNameId, long classPK,
			String actionId)
		throws PortalException, SystemException {

		String className = PortalUtil.getClassName(classNameId);

		if (className.equals(AccountEntry.class.getName())) {
			return;
		}
		else if (className.equals(PartnerEntry.class.getName())) {
			return;
		}

		_commonPermission.check(
			permissionChecker, classNameId, classPK, actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, String className, long classPK,
			String actionId)
		throws PortalException, SystemException {

		if (className.equals(AccountEntry.class.getName())) {
			return;
		}
		else if (className.equals(PartnerEntry.class.getName())) {
			return;
		}

		_commonPermission.check(
			permissionChecker, className, classPK, actionId);
	}

	public CommonPermission getCommonPermission() {
		return _commonPermission;
	}

	private CommonPermission _commonPermission;

}