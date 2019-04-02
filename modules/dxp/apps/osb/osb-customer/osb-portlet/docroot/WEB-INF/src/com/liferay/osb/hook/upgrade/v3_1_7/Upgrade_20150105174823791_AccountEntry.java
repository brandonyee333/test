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

/**
 * @author Amos Fong
 */
public class Upgrade_20150105174823791_AccountEntry extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasColumn("OSB_AccountEntry", "corpProjectId")) {
			return;
		}

		runSQL("alter table OSB_AccountEntry add column corpProjectId LONG");

		runSQL("create index IX_6AA361CE on OSB_AccountEntry (corpProjectId)");
	}

}