/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.upgrade;

import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.persistence.SyncAccountPersistence;

/**
 * @author Shinn Lok
 */
public abstract class BaseUpgradeProcess extends UpgradeProcess {

	@Override
	public void upgrade() throws Exception {
	}

	@Override
	public void upgradeSchema() throws Exception {
	}

	protected void runSQL(String sql) throws Exception {
		SyncAccountPersistence syncAccountPersistence =
			SyncAccountService.getSyncAccountPersistence();

		syncAccountPersistence.executeRaw(sql);
	}

}