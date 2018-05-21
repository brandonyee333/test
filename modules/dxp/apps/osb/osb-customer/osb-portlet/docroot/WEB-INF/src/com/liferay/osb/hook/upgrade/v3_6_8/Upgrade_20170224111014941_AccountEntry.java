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

package com.liferay.osb.hook.upgrade.v3_6_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170224111014941_AccountEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20170224111014941L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("OSB_AccountEntry", "renewalTemplate")) {
			return;
		}

		runSQL("alter table OSB_AccountEntry drop column renewalTemplate");
		runSQL("alter table OSB_AccountEntry drop column renewalContactUserId");
	}

}