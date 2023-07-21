/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.service.permission;

import com.liferay.exportimport.kernel.staging.permission.StagingPermissionUtil;
import com.liferay.mobile.device.rules.constants.MDRPortletKeys;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.mobile.device.rules.service.MDRRuleGroupInstanceLocalService;
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
	property = "model.class.name=com.liferay.mobile.device.rules.model.MDRRuleGroupInstance",
	service = BaseModelPermissionChecker.class
)
public class MDRRuleGroupInstancePermission
	implements BaseModelPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long ruleGroupInstanceId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, ruleGroupInstanceId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MDRRuleGroupInstance.class.getName(),
				ruleGroupInstanceId, actionId);
		}
	}

	public static void check(
			PermissionChecker permissionChecker,
			MDRRuleGroupInstance ruleGroupInstance, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, ruleGroupInstance, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, MDRRuleGroupInstance.class.getName(),
				ruleGroupInstance.getRuleGroupInstanceId(), actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long ruleGroupInstanceId,
			String actionId)
		throws PortalException {

		MDRRuleGroupInstance ruleGroupInstance =
			_mdrRuleGroupInstanceLocalService.getMDRRuleGroupInstance(
				ruleGroupInstanceId);

		return contains(permissionChecker, ruleGroupInstance, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker,
		MDRRuleGroupInstance ruleGroupInstance, String actionId) {

		Boolean hasPermission = StagingPermissionUtil.hasPermission(
			permissionChecker, ruleGroupInstance.getGroupId(),
			MDRRuleGroupInstance.class.getName(),
			ruleGroupInstance.getRuleGroupInstanceId(),
			MDRPortletKeys.MOBILE_DEVICE_RULES, actionId);

		if (hasPermission != null) {
			return hasPermission.booleanValue();
		}

		return permissionChecker.hasPermission(
			ruleGroupInstance.getGroupId(),
			MDRRuleGroupInstance.class.getName(),
			ruleGroupInstance.getRuleGroupInstanceId(), actionId);
	}

	@Override
	public void checkBaseModel(
			PermissionChecker permissionChecker, long groupId, long primaryKey,
			String actionId)
		throws PortalException {

		check(permissionChecker, primaryKey, actionId);
	}

	@Reference(unbind = "-")
	protected void setMDRRuleGroupInstanceLocalService(
		MDRRuleGroupInstanceLocalService mdrRuleGroupInstanceLocalService) {

		_mdrRuleGroupInstanceLocalService = mdrRuleGroupInstanceLocalService;
	}

	private static MDRRuleGroupInstanceLocalService
		_mdrRuleGroupInstanceLocalService;

}