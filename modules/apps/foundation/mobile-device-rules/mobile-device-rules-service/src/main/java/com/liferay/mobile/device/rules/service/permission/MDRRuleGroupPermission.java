/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.service.permission;

import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.mobile.device.rules.constants.MDRPortletKeys;
import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.service.MDRRuleGroupLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Michael C. Han
 */
@Component(
	property = "model.class.name=com.liferay.mobile.device.rules.model.MDRRuleGroup",
	service = BaseModelPermissionChecker.class
)
public class MDRRuleGroupPermission implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long ruleGroupId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, ruleGroupId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MDRRuleGroup.class.getName(), ruleGroupId,
				actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker, MDRRuleGroup ruleGroup,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, ruleGroup, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MDRRuleGroup.class.getName(),
				ruleGroup.getRuleGroupId(), actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long ruleGroupId,
			String actionId)
		throws PortalException {

		MDRRuleGroup ruleGroup = _mdrRuleGroupLocalService.getMDRRuleGroup(
			ruleGroupId);

		return contains(permissionChecker, ruleGroup, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, MDRRuleGroup ruleGroup,
		String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, ruleGroup.getGroupId(),
			MDRRuleGroup.class.getName(), ruleGroup.getRuleGroupId(),
			MDRPortletKeys.MOBILE_DEVICE_RULES, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		return permissionChecker.hasPermission(
			ruleGroup.getGroupId(), MDRRuleGroup.class.getName(),
			ruleGroup.getRuleGroupId(), actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setMDRRuleGroupLocalService(
		MDRRuleGroupLocalService mdrRuleGroupLocalService) {

		_mdrRuleGroupLocalService = mdrRuleGroupLocalService;
	}

	private static MDRRuleGroupLocalService _mdrRuleGroupLocalService;

}