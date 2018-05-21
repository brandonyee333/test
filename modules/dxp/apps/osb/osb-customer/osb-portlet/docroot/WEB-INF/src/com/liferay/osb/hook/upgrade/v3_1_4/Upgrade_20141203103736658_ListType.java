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

package com.liferay.osb.hook.upgrade.v3_1_4;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20141203103736658_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20141203103736658L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			34031, "pending-customer",
			"com.liferay.osb.model.AuditEntry.field");
		insertListType(
			34032, "pending-liferay", "com.liferay.osb.model.AuditEntry.field");
		insertListType(
			34033, "pending-partner", "com.liferay.osb.model.AuditEntry.field");
	}

}