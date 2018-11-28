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

package com.liferay.osb.hook.upgrade.v5_0_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20181127174056264_AccountEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20181127174056264L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!tableHasColumn("OSB_AccountEntry", "activeSupport")) {
			runSQL(
				"alter table OSB_AccountEntry add column activeSupport " +
					"boolean");
			runSQL(
				"alter table OSB_AccountEntry add column activeTicketSupport " +
					"boolean");
		}
	}

}