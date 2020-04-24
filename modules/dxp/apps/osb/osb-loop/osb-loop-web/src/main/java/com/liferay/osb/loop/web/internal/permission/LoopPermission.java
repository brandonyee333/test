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

package com.liferay.osb.loop.web.internal.permission;

import com.liferay.alloy.mvc.AlloyPermission;
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.model.LoopDivision;
import com.liferay.osb.loop.model.LoopPerson;
import com.liferay.osb.loop.service.LoopDivisionLocalServiceUtil;
import com.liferay.osb.loop.web.internal.constants.LoopAssetEntrySetConstants;
import com.liferay.osb.loop.web.internal.constants.LoopRoleConstants;
import com.liferay.osb.loop.web.internal.util.LoopDivisionUtil;
import com.liferay.osb.loop.web.internal.util.LoopParticipantAssignmentUtil;
import com.liferay.osb.loop.web.internal.util.LoopPersonRelUtil;
import com.liferay.osb.loop.web.internal.util.LoopPersonUtil;
import com.liferay.portal.kernel.exception.NoSuchResourceActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;

/**
 * @author Timothy Bell
 */
public class LoopPermission extends AlloyPermission {

	public static boolean contains(
		ThemeDisplay themeDisplay, BaseModel<?> baseModel, String action) {

		try {
			if (_isLoopPage(baseModel)) {
				return _containsLoopPagePermission(
					themeDisplay, action, (AssetEntrySet)baseModel);
			}

			String actionId = formatAction(action);

			try {
				ResourceActionsUtil.checkAction(
					baseModel.getModelClassName(), actionId);
			}
			catch (NoSuchResourceActionException nsrae) {
				if (_log.isDebugEnabled()) {
					_log.debug(nsrae, nsrae);
				}

				return true;
			}

			actionId = _getActionId(themeDisplay, baseModel, actionId);

			long[] groupIds = {themeDisplay.getScopeGroupId()};

			if (baseModel instanceof LoopDivision) {
				groupIds = LoopDivisionUtil.getOrganizationGroupIds(
					(LoopDivision)baseModel);
			}

			for (long groupId : groupIds) {
				if (contains(
						themeDisplay.getPermissionChecker(), groupId,
						baseModel.getModelClassName(),
						themeDisplay.getCompanyId(), actionId)) {

					return true;
				}
			}

			return false;
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	public static boolean contains(ThemeDisplay themeDisplay, String action) {
		return _contains(themeDisplay, formatAction(action));
	}

	public static boolean contains(
		ThemeDisplay themeDisplay, String controller, String action) {

		return _contains(themeDisplay, formatActionId(controller, action));
	}

	private static boolean _contains(
		ThemeDisplay themeDisplay, String actionId) {

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		try {
			ResourceActionsUtil.checkAction(
				portletDisplay.getRootPortletId(), actionId);
		}
		catch (NoSuchResourceActionException nsrae) {
			if (_log.isDebugEnabled()) {
				_log.debug(nsrae, nsrae);
			}

			return true;
		}

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		return permissionChecker.hasPermission(
			themeDisplay.getScopeGroupId(), portletDisplay.getRootPortletId(),
			portletDisplay.getRootPortletId(), actionId);
	}

	private static boolean _containsLoopPagePermission(
			ThemeDisplay themeDisplay, String action,
			AssetEntrySet assetEntrySet)
		throws Exception {

		if (PortalUtil.getClassNameId(LoopDivision.class) !=
				assetEntrySet.getClassNameId()) {

			return false;
		}

		BaseModel<?> baseModel = LoopDivisionLocalServiceUtil.getLoopDivision(
			assetEntrySet.getClassPK());

		return contains(themeDisplay, baseModel, action + "Pages");
	}

	private static String _getActionId(
			ThemeDisplay themeDisplay, BaseModel<?> baseModel, String actionId)
		throws Exception {

		if (_isOtherActionId(themeDisplay, baseModel)) {
			String otherActionId = actionId + "_OTHERS";

			try {
				ResourceActionsUtil.checkAction(
					baseModel.getModelClassName(), otherActionId);
			}
			catch (NoSuchResourceActionException nsrae) {
				return actionId;
			}

			return otherActionId;
		}

		return actionId;
	}

	private static boolean _isLoopPage(BaseModel<?> baseModel) {
		if (baseModel instanceof AssetEntrySet) {
			AssetEntrySet assetEntrySet = (AssetEntrySet)baseModel;

			if (assetEntrySet.getType() ==
					LoopAssetEntrySetConstants.TYPE_PAGE) {

				return true;
			}
		}

		return false;
	}

	private static boolean _isOtherActionId(
			ThemeDisplay themeDisplay, AssetEntrySet assetEntrySet)
		throws Exception {

		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		if (((assetEntrySet.getCreatorClassNameId() ==
				PortalUtil.getClassNameId(LoopDivision.class)) &&
			 LoopParticipantAssignmentUtil.isLoopDivisionLead(
				 assetEntrySet.getCreatorClassPK(),
				 curLoopPerson.getLoopPersonId())) ||
			((assetEntrySet.getCreatorClassNameId() ==
				PortalUtil.getClassNameId(LoopPerson.class)) &&
			 (assetEntrySet.getCreatorClassPK() ==
				 curLoopPerson.getLoopPersonId()))) {

			return false;
		}

		return true;
	}

	private static boolean _isOtherActionId(
			ThemeDisplay themeDisplay, BaseModel<?> baseModel)
		throws Exception {

		if (baseModel instanceof AssetEntrySet) {
			return _isOtherActionId(themeDisplay, (AssetEntrySet)baseModel);
		}
		else if (baseModel instanceof LoopDivision) {
			return _isOtherActionId(themeDisplay, (LoopDivision)baseModel);
		}
		else if (baseModel instanceof LoopPerson) {
			return _isOtherActionId(themeDisplay, (LoopPerson)baseModel);
		}

		return false;
	}

	private static boolean _isOtherActionId(
			ThemeDisplay themeDisplay, LoopDivision loopDivision)
		throws Exception {

		long organizationId = loopDivision.getOrganizationId();

		Organization organization =
			OrganizationLocalServiceUtil.getOrganization(organizationId);

		if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
				themeDisplay.getUserId(), organization.getGroupId(),
				LoopRoleConstants.LOOP_DIVISION_MEMBER, false)) {

			return false;
		}

		while (organizationId > 0) {
			organization = OrganizationLocalServiceUtil.getOrganization(
				organizationId);

			if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
					themeDisplay.getUserId(), organization.getGroupId(),
					LoopRoleConstants.LOOP_DIVISION_LEAD, false)) {

				return false;
			}

			organizationId = organization.getParentOrganizationId();
		}

		return true;
	}

	private static boolean _isOtherActionId(
			ThemeDisplay themeDisplay, LoopPerson loopPerson)
		throws Exception {

		if (themeDisplay.getUserId() == loopPerson.getPersonUserId()) {
			return false;
		}

		LoopPerson curLoopPerson = LoopPersonUtil.getLoopPerson(
			themeDisplay.getUserId());

		if (LoopPersonRelUtil.isLoopPersonManager(
				loopPerson.getLoopPersonId(),
				curLoopPerson.getLoopPersonId())) {

			return false;
		}

		return true;
	}

	private static final Log _log = LogFactoryUtil.getLog(LoopPermission.class);

}