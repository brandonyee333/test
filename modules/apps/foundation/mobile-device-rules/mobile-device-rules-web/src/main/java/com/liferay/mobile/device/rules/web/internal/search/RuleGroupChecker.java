/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.web.internal.search;

import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.service.permission.MDRRuleGroupPermission;
import com.liferay.portal.kernel.dao.search.EmptyOnClickRowChecker;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;

import javax.portlet.PortletResponse;

/**
 * @author Jorge Ferrer
 */
public class RuleGroupChecker extends EmptyOnClickRowChecker {

	public RuleGroupChecker(PortletResponse portletResponse) {
		super(portletResponse);
	}

	@Override
	public boolean isDisabled(Object obj) {
		MDRRuleGroup ruleGroup = (MDRRuleGroup)obj;

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (!MDRRuleGroupPermission.contains(
				permissionChecker, ruleGroup, ActionKeys.DELETE)) {

			return true;
		}

		return super.isDisabled(obj);
	}

}