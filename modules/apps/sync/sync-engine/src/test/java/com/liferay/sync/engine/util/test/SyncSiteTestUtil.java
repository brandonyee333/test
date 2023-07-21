/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util.test;

import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.model.SyncSite;
import com.liferay.sync.engine.service.SyncFileService;
import com.liferay.sync.engine.service.SyncSiteService;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Shinn Lok
 */
public class SyncSiteTestUtil {

	public static SyncSite addSyncSite(
			long companyId, String filePathName, long groupId,
			long syncAccountId)
		throws Exception {

		// Sync site

		SyncSite syncSite = new SyncSite();

		syncSite.setCompanyId(companyId);
		syncSite.setFilePathName(filePathName);
		syncSite.setFriendlyURL(filePathName);
		syncSite.setGroupId(groupId);
		syncSite.setName(filePathName);
		syncSite.setSyncAccountId(syncAccountId);

		SyncSiteService.update(syncSite);

		// Sync file

		Files.createDirectories(Paths.get(filePathName));

		SyncFileService.addSyncFile(
			null, null, false, null, filePathName, null, filePathName, 0,
			groupId, 0, SyncFile.STATE_SYNCED, syncSite.getSyncAccountId(),
			SyncFile.TYPE_SYSTEM);

		return syncSite;
	}

}