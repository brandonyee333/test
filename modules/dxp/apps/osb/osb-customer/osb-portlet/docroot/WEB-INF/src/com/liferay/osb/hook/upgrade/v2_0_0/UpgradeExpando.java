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

package com.liferay.osb.hook.upgrade.v2_0_0;

import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.List;

/**
 * @author Peter Shin
 */
public class UpgradeExpando extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		long companyId = OSBConstants.COMPANY_ID;

		updateOSBExpandoColumns(companyId);
		updateOSBMarketplaceServerExpandoColumns(companyId);
	}

	protected void updateOSBExpandoColumns(long companyId) throws Exception {
		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, User.class.getName(), "OSB");
		}
		catch (NoSuchTableException nste) {
			return;
		}

		ExpandoColumn oldExpandoColumn =
			ExpandoColumnLocalServiceUtil.getColumn(
				companyId, User.class.getName(), expandoTable.getName(),
				"trial-purchased");

		if (oldExpandoColumn == null) {
			return;
		}

		ExpandoColumn newExpandoColumn =
			ExpandoColumnLocalServiceUtil.getColumn(
				companyId, User.class.getName(), expandoTable.getName(),
				"trialPurchased");

		if (newExpandoColumn == null) {
			newExpandoColumn = ExpandoColumnLocalServiceUtil.updateColumn(
				oldExpandoColumn.getColumnId(), "trialPurchased",
				ExpandoColumnConstants.BOOLEAN);
		}

		List<ExpandoValue> expandoValues =
			ExpandoValueLocalServiceUtil.getColumnValues(
				oldExpandoColumn.getColumnId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (ExpandoValue expandoValue : expandoValues) {
			ExpandoValueLocalServiceUtil.addValue(
				expandoValue.getCompanyId(), User.class.getName(),
				expandoTable.getName(), newExpandoColumn.getName(),
				expandoValue.getClassPK(), expandoValue.getBoolean());
		}

		ExpandoColumnLocalServiceUtil.deleteColumn(
			oldExpandoColumn.getColumnId());
	}

	protected void updateOSBMarketplaceServerExpandoColumns(long companyId)
		throws Exception {

		ExpandoTable expandoTable = null;

		try {
			expandoTable = ExpandoTableLocalServiceUtil.getTable(
				companyId, Company.class.getName(), "OSB_MARKETPLACE_SERVER");
		}
		catch (NoSuchTableException nste) {
			return;
		}

		ExpandoColumn oldExpandoColumn =
			ExpandoColumnLocalServiceUtil.getColumn(
				companyId, Company.class.getName(), expandoTable.getName(),
				"client-id-key");

		if (oldExpandoColumn == null) {
			return;
		}

		ExpandoColumn newExpandoColumn =
			ExpandoColumnLocalServiceUtil.getColumn(
				companyId, Company.class.getName(), expandoTable.getName(),
				"clientIdKey");

		if (newExpandoColumn == null) {
			newExpandoColumn = ExpandoColumnLocalServiceUtil.updateColumn(
				oldExpandoColumn.getColumnId(), "clientIdKey",
				ExpandoColumnConstants.STRING);
		}

		List<ExpandoValue> expandoValues =
			ExpandoValueLocalServiceUtil.getColumnValues(
				oldExpandoColumn.getColumnId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (ExpandoValue expandoValue : expandoValues) {
			ExpandoValueLocalServiceUtil.addValue(
				expandoValue.getCompanyId(), Company.class.getName(),
				expandoTable.getName(), newExpandoColumn.getName(),
				expandoValue.getClassPK(), expandoValue.getString());
		}

		ExpandoColumnLocalServiceUtil.deleteColumn(
			oldExpandoColumn.getColumnId());
	}

}