/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.upgrade.v3_0_8;

import com.liferay.sync.engine.upgrade.BaseUpgradeProcess;
import com.liferay.sync.engine.upgrade.util.UpgradeUtil;

/**
 * @author Dennis Ju
 * @author Shinn Lok
 */
public class UpgradeProcess_3_0_8 extends BaseUpgradeProcess {

	@Override
	public int getThreshold() {
		return 3008;
	}

	@Override
	public void upgrade() throws Exception {
		UpgradeUtil.copyLoggerConfiguration();
	}

	@Override
	public void upgradeSchema() throws Exception {
		runSQL("CREATE INDEX syncfile_checksum_idx ON SyncFile(checksum)");
	}

}