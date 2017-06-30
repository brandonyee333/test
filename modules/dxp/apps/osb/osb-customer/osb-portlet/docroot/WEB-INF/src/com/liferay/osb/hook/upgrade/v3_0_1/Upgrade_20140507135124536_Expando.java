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

package com.liferay.osb.hook.upgrade.v3_0_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rachael Koestartyo
 */
public class Upgrade_20140507135124536_Expando extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20140507135124536L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateExpandoColumn(OSBConstants.COMPANY_ID);
	}

	protected void setPermissions(long companyId, ExpandoColumn expandoColumn)
		throws PortalException, SystemException {

		Map<Long, String[]> roleIdsToActionIds = new HashMap<Long, String[]>();

		String[] actionIds = {ActionKeys.UPDATE, ActionKeys.VIEW};
		Role guestRole = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.GUEST);

		roleIdsToActionIds.put(guestRole.getRoleId(), actionIds);

		Role userRole = RoleLocalServiceUtil.getRole(
			companyId, RoleConstants.USER);

		roleIdsToActionIds.put(userRole.getRoleId(), actionIds);

		ResourcePermissionLocalServiceUtil.setResourcePermissions(
			companyId, ExpandoColumn.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			String.valueOf(expandoColumn.getColumnId()), roleIdsToActionIds);
	}

	protected void updateExpandoColumn(long companyId) throws Exception {
		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, User.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (NoSuchTableException nste) {
			expandoTable = ExpandoTableLocalServiceUtil.addTable(
				companyId, User.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(
			companyId, expandoTable.getClassName(), expandoTable.getName(),
			_EXPANDO_COLUMN_NAME);

		if (expandoColumn == null) {
			expandoColumn = ExpandoColumnLocalServiceUtil.addColumn(
				expandoTable.getTableId(), _EXPANDO_COLUMN_NAME,
				ExpandoColumnConstants.STRING_ARRAY, _OSB_DEPARTMENT_DATA);
		}

		setPermissions(companyId, expandoColumn);

		ExpandoColumnLocalServiceUtil.updateTypeSettings(
			expandoColumn.getColumnId(), "display-type=selection-list");
	}

	private static final String _EXPANDO_COLUMN_NAME = "osbDepartment";

	private static final String[] _OSB_DEPARTMENT_DATA = {
		"development-engineering", "finance", "hr", "it-support",
		"legal-procurement", "marketing", "operations", "sales", "other"
	};

}