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

	protected void doUpdateSupportResponse(
			int supportLevel, int deprecatedSupportLevel)
		throws Exception {

		runSQL(
			"update OSB_SupportResponse set supportLevel = " + supportLevel +
				" where supportLevel = " + deprecatedSupportLevel);
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

}