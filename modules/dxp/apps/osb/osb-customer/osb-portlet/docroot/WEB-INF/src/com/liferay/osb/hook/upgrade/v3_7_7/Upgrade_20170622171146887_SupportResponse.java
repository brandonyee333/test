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

package com.liferay.osb.hook.upgrade.v3_7_7;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.SupportResponseConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.SupportResponseLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class Upgrade_20170622171146887_SupportResponse
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20170622171146887L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		SupportResponseLocalServiceUtil.addSupportResponse(
			OSBConstants.USER_AMOS_FONG_USER_ID, "Floating",
			SupportResponseConstants.SUPPORT_LEVEL_FLOATING, 0, 0, 0, 0, 0, 0);

		List<AccountEntry> accountEntries =
			AccountEntryLocalServiceUtil.getAccountEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountEntry accountEntry : accountEntries) {
			AccountEntryLocalServiceUtil.recalculateHighestSupportResponse(
				accountEntry.getAccountEntryId());
		}
	}

}