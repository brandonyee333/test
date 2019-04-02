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

package com.liferay.osb.hook.upgrade.v3_6_3;

import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jeremy Fu
 */
public class Upgrade_20161121170428345_Expando extends BaseUpgradeProcess {

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