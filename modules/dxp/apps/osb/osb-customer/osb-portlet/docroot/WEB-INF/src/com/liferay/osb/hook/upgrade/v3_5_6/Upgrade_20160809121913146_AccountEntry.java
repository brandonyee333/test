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

package com.liferay.osb.hook.upgrade.v3_5_6;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.util.OSBConstants;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20160809121913146_AccountEntry extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		removeLiferayTrialAccountCustomers();
		updateIndexes();
	}

	protected void removeLiferayTrialAccountCustomers() throws Exception {
		runSQL(
			"delete from OSB_AccountCustomer where accountEntryId = " +
				OSBConstants.ACCOUNT_ENTRY_TRIAL_ID);
	}

	protected void updateIndexes() throws Exception {
		if (!hasIndex("OSB_AccountEntry", "IX_31DB3E6F")) {
			runSQL(
				"create index IX_31DB3E6F on OSB_AccountEntry (userId, type_)");
		}

		if (!hasIndex("OSB_AccountEntry", "IX_77FBC39")) {
			runSQL(
				"create index IX_77FBC39 on OSB_AccountEntry (" +
					"redirectAccountEntryId, type_)");
		}
	}

}