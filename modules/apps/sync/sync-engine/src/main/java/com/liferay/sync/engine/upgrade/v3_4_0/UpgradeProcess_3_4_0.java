/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.upgrade.v3_4_0;

import com.liferay.sync.engine.service.SyncLanClientService;
import com.liferay.sync.engine.service.SyncLanEndpointService;
import com.liferay.sync.engine.service.persistence.SyncLanClientPersistence;
import com.liferay.sync.engine.service.persistence.SyncLanEndpointPersistence;
import com.liferay.sync.engine.upgrade.BaseUpgradeProcess;
import com.liferay.sync.engine.upgrade.util.UpgradeUtil;

/**
 * @author Dennis Ju
 * @author Shinn Lok
 */
public class UpgradeProcess_3_4_0 extends BaseUpgradeProcess {

	@Override
	public int getThreshold() {
		return 3400;
	}

	@Override
	public void upgrade() throws Exception {
		UpgradeUtil.copyLoggerConfiguration();
	}

	@Override
	public void upgradeSchema() throws Exception {
		runSQL(
			"ALTER TABLE SyncAccount ADD COLUMN lanCertificate " +
				"VARCHAR(16777216) BEFORE login");
		runSQL(
			"ALTER TABLE SyncAccount ADD COLUMN lanEnabled TINYINT BEFORE " +
				"login");
		runSQL(
			"ALTER TABLE SyncAccount ADD COLUMN lanKey VARCHAR(16777216) " +
				"BEFORE login");
		runSQL(
			"ALTER TABLE SyncAccount ADD COLUMN lanServerUuid VARCHAR(255) " +
				"BEFORE login");

		runSQL(
			"ALTER TABLE SyncFile ADD COLUMN lanTokenKey VARCHAR(255) BEFORE " +
				"localExtraSettings");

		SyncLanClientPersistence syncLanClientPersistence =
			SyncLanClientService.getSyncLanClientPersistence();

		if (!syncLanClientPersistence.isTableExists()) {
			syncLanClientPersistence.createTable();
		}

		SyncLanEndpointPersistence syncLanEndpointPersistence =
			SyncLanEndpointService.getSyncLanEndpointPersistence();

		if (!syncLanEndpointPersistence.isTableExists()) {
			syncLanEndpointPersistence.createTable();
		}
	}

}