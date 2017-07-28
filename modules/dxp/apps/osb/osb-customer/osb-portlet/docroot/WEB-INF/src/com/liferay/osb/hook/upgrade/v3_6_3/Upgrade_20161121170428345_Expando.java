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

package com.liferay.osb.hook.upgrade.v3_6_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;

import java.util.HashMap;
import java.util.Map;

*/

/**
 * @author Jeremy Fu
 */
public class Upgrade_20161121170428345_Expando extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20161121170428345L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateExpandoColumns(OSBConstants.COMPANY_ID);
	}

	protected void setPermissions(long companyId, ExpandoColumn expandoColumn)
		throws PortalException {

		Map<Long, String[]> roleIdsToActionIds = new HashMap<>();

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

	protected void updateExpandoColumn(long companyId, String expandoColumnName)
		throws Exception {

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
			expandoColumnName);

		if (expandoColumn == null) {
			expandoColumn = ExpandoColumnLocalServiceUtil.addColumn(
				expandoTable.getTableId(), expandoColumnName,
				ExpandoColumnConstants.STRING, StringPool.BLANK);
		}

		setPermissions(companyId, expandoColumn);
	}

	protected void updateExpandoColumns(long companyId) throws Exception {
		for (String expandoColumnName : _EXPANDO_COLUMN_NAMES) {
			updateExpandoColumn(companyId, expandoColumnName);
		}
	}

	private static final String[] _EXPANDO_COLUMN_NAMES = {
		"hsCampaignContent", "hsCampaignKeyword", "hsCampaignMedium",
		"hsCampaignName", "hsCampaignSource", "hsPreviousPagePath", "osbRegion"
	};

}

*/

}