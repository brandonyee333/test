/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.upgrade.v3_4_2;

import com.liferay.sync.encryptor.SyncEncryptor;
import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.upgrade.BaseUpgradeProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dennis Ju
 * @author Shinn Lok
 */
public class UpgradeProcess_3_4_2 extends BaseUpgradeProcess {

	@Override
	public int getThreshold() {
		return 3402;
	}

	@Override
	public void upgrade() throws Exception {
		for (SyncAccount syncAccount : SyncAccountService.findAll()) {
			try {
				String oAuthTokenSecret = SyncEncryptor.decrypt(
					syncAccount.getOAuthTokenSecret(), 1);

				syncAccount.setOAuthTokenSecret(
					SyncEncryptor.encrypt(oAuthTokenSecret, 2));

				String password = SyncEncryptor.decrypt(
					syncAccount.getPassword(), 1);

				syncAccount.setPassword(SyncEncryptor.encrypt(password, 2));

				SyncAccountService.update(syncAccount);
			}
			catch (Exception e) {
				_logger.error(e.getMessage(), e);
			}
		}
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		UpgradeProcess_3_4_2.class);

}