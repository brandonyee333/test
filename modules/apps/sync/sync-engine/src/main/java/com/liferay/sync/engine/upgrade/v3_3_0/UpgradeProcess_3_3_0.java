/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.upgrade.v3_3_0;

import com.liferay.sync.engine.upgrade.BaseUpgradeProcess;

/**
 * @author Dennis Ju
 * @author Shinn Lok
 */
public class UpgradeProcess_3_3_0 extends BaseUpgradeProcess {

	@Override
	public int getThreshold() {
		return 3300;
	}

	@Override
	public void upgradeSchema() throws Exception {
		runSQL(
			"ALTER TABLE SyncAccount ADD COLUMN maxDownloadRate " +
				"VARCHAR(16777216) BEFORE oAuthConsumerKey");
		runSQL(
			"ALTER TABLE SyncAccount ADD COLUMN maxUploadRate " +
				"VARCHAR(16777216) BEFORE oAuthConsumerKey");
		runSQL(
			"ALTER TABLE SyncAccount ADD COLUMN syncContextModifiedTime LONG " +
				"BEFORE trustSelfSigned");
	}

}