/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.upgrade.v3_0_11;

import com.liferay.sync.engine.model.SyncAccount;
import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncAccountService;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncSiteService;
import com.liferay.sync.engine.upgrade.BaseUpgradeProcess;
import com.liferay.sync.engine.util.FileKeyUtil;
import com.liferay.sync.engine.util.FileUtil;

import java.nio.file.Paths;

import java.util.List;

/**
 * @author Dennis Ju
 * @author Shinn Lok
 */
public class UpgradeProcess_3_0_11 extends BaseUpgradeProcess {

	@Override
	public int getThreshold() {
		return 3011;
	}

	@Override
	public void upgrade() throws Exception {
		List<SyncAccount> syncAccounts = SyncAccountService.findAll();

		for (SyncAccount syncAccount : syncAccounts) {
			List<SyncSite> syncSites = SyncSiteService.findSyncSites(
				syncAccount.getSyncAccountId());

			for (SyncSite syncSite : syncSites) {
				if (!syncSite.isActive() ||
					!FileUtil.exists(Paths.get(syncSite.getFilePathName()))) {

					continue;
				}

				SyncFile syncFile = SyncFileService.fetchSyncFile(
					syncSite.getFilePathName());

				if (syncFile == null) {
					syncFile = SyncFileService.fetchSyncFile(
						syncSite.getGroupId(), syncSite.getSyncAccountId(), 0);

					syncFile.setName(syncSite.getName());
					syncFile.setFilePathName(syncSite.getFilePathName());

					SyncFileService.update(syncFile);
				}

				FileKeyUtil.writeFileKey(
					Paths.get(syncSite.getFilePathName()),
					String.valueOf(syncFile.getSyncFileId()), true);
			}
		}
	}

}