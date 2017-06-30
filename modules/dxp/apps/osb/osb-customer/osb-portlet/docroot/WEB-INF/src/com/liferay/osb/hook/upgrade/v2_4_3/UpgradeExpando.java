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

package com.liferay.osb.hook.upgrade.v2_4_3;

import com.liferay.compat.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portlet.expando.NoSuchTableException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * @author Rachael Koestartyo
 */
public class UpgradeExpando extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		long companyId = OSBConstants.COMPANY_ID;

		updateExpandoColumn(companyId);
	}

	protected void updateExpandoColumn(long companyId) throws Exception {
		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, User.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (NoSuchTableException nste) {
			return;
		}

		ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.getColumn(
			companyId, expandoTable.getClassName(), expandoTable.getName(),
			_EXPANDO_COLUMN_NAME);

		if (expandoColumn == null) {
			ExpandoColumnLocalServiceUtil.addColumn(
				expandoTable.getTableId(), _EXPANDO_COLUMN_NAME,
				ExpandoColumnConstants.STRING, StringPool.BLANK);
		}
	}

	private static final String _EXPANDO_COLUMN_NAME = "osbNickname";

}