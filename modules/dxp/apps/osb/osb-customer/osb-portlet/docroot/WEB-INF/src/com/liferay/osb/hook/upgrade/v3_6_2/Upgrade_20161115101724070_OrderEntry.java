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

package com.liferay.osb.hook.upgrade.v3_6_2;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20161115101724070_OrderEntry extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("OSB_OrderEntry", "status")) {
			runSQL("alter table OSB_OrderEntry add column status INTEGER");
			runSQL(
				"alter table OSB_OrderEntry add column statusByUserId BIGINT");
			runSQL(
				"alter table OSB_OrderEntry add column statusByUserName " +
					"VARCHAR(75)");
			runSQL("alter table OSB_OrderEntry add column statusDate DATETIME");
			runSQL(
				"alter table OSB_OrderEntry add column statusMessage LONGTEXT");

			runSQL("update OSB_OrderEntry set status = 0");
		}
	}

}