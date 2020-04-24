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

package com.liferay.osb.testray.util;

import com.liferay.alloy.mvc.AlloyPermission;
import com.liferay.petra.string.CharPool;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Ethan Bustad
 */
public class TestrayPermission extends AlloyPermission {

	public static boolean contains(
		ThemeDisplay themeDisplay, BaseModel<?> baseModel, String action) {

		if (baseModel == null) {
			return false;
		}

		try {
			return contains(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), baseModel.getModelClassName(),
				(Long)baseModel.getPrimaryKeyObj(), formatAction(action));
		}
		catch (Exception e) {
			return contains(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), baseModel.getModelClassName(),
				themeDisplay.getCompanyId(), formatAction(action));
		}
	}

	public static boolean contains(
		ThemeDisplay themeDisplay, Class<?> modelClass, String action) {

		if (modelClass == null) {
			return false;
		}

		return contains(
			themeDisplay.getPermissionChecker(), themeDisplay.getScopeGroupId(),
			modelClass.getName(), themeDisplay.getCompanyId(),
			formatAction(action));
	}

	public static boolean contains(
		ThemeDisplay themeDisplay, String controller, String action) {

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		String actionId = formatActionId(controller, action);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		return permissionChecker.hasPermission(
			themeDisplay.getScopeGroupId(), portletDisplay.getRootPortletId(),
			portletDisplay.getRootPortletId(), actionId);
	}

	protected static String formatAction(String action) {
		StringBuilder sb = new StringBuilder(StringUtil.toUpperCase(action));

		for (int i = 0; i < action.length(); i++) {
			char c = action.charAt(i);

			if (Character.isUpperCase(c) && (i > 0)) {
				int delta = sb.length() - action.length();

				sb.insert(i + delta, CharPool.UNDERLINE);
			}
		}

		return sb.toString();
	}

}