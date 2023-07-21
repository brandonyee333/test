/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.upgrade.v3_0_4;

import com.liferay.sync.engine.upgrade.BaseUpgradeProcess;

/**
 * @author Dennis Ju
 * @author Shinn Lok
 */
public class UpgradeProcess_3_0_4 extends BaseUpgradeProcess {

	@Override
	public int getThreshold() {
		return 3004;
	}

	@Override
	public void upgradeSchema() throws Exception {
		runSQL(
			"ALTER TABLE SyncAccount ADD COLUMN oAuthConsumerKey " +
				"VARCHAR(16777216) BEFORE password");
		runSQL(
			"ALTER TABLE SyncAccount ADD COLUMN oAuthConsumerSecret " +
				"VARCHAR(16777216) BEFORE password");
		runSQL(
			"ALTER TABLE SyncAccount ADD COLUMN oAuthEnabled " +
				"VARCHAR(16777216) BEFORE password");
	}

}