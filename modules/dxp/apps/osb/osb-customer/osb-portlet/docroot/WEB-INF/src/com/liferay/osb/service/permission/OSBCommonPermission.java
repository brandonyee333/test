/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.service.permission;

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.permission.CommonPermission;
import com.liferay.portal.kernel.util.PortalUtil;

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
		throws PortalException {

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
		throws PortalException {

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

	private final CommonPermission _commonPermission;

}