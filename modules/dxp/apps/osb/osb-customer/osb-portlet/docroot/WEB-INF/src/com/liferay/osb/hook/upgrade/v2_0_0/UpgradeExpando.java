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