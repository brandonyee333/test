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