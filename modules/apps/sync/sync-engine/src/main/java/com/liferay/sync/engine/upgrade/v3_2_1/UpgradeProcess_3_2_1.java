/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.upgrade.v3_2_1;

import com.liferay.sync.engine.upgrade.BaseUpgradeProcess;

/**
 * @author Dennis Ju
 * @author Shinn Lok
 */
public class UpgradeProcess_3_2_1 extends BaseUpgradeProcess {

	@Override
	public int getThreshold() {
		return 3201;
	}

	@Override
	public void upgradeSchema() throws Exception {
		runSQL(
			"CREATE INDEX syncwatchevent_filepathname_idx ON " +
				"SyncWatchEvent(filePathName)");
	}

}