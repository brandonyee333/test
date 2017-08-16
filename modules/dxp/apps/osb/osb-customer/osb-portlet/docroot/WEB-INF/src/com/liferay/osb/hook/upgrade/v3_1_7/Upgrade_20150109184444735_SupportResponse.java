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

package com.liferay.osb.hook.upgrade.v3_1_7;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

/**
 * @author Alan Zhang
 */
public class Upgrade_20150109184444735_SupportResponse
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150109184444735L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateSupportResponse();

		recalculateHighestSupportResponse();
	}

	protected void recalculateHighestSupportResponse() throws Exception {
		List<AccountEntry> accountEntries =
			AccountEntryLocalServiceUtil.getAccountEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountEntry accountEntry : accountEntries) {
			AccountEntryLocalServiceUtil.recalculateHighestSupportResponse(
				accountEntry.getAccountEntryId());
		}
	}

	protected void updateSupportResponse() throws Exception {
		int[][] supportLevelMaps = {{20, 2}, {40, 4}, {10, 1}, {30, 3}};

		for (int[] supportLevelMap : supportLevelMaps) {
			doUpdateSupportResponse(supportLevelMap[0], supportLevelMap[1]);
		}
	}

	private void doUpdateSupportResponse(
			int supportLevel, int deprecatedSupportLevel)
		throws Exception {

		runSQL(
			"update OSB_SupportResponse set supportLevel = " + supportLevel +
				" where supportLevel = " + deprecatedSupportLevel);
	}

}