/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util.test;

import com.liferay.sync.engine.model.SyncFile;
import com.liferay.sync.engine.service.SyncFileService;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Shinn Lok
 */
public class SyncFileTestUtil {

	public static SyncFile addFileSyncFile(
			String filePathName, long parentFolderId, long syncAccountId)
		throws Exception {

		return addFileSyncFile(
			filePathName, parentFolderId, 0, syncAccountId, 0);
	}

	public static SyncFile addFileSyncFile(
			String filePathName, long parentFolderId, long repositoryId,
			long syncAccountId, long typePK)
		throws Exception {

		Files.createFile(Paths.get(filePathName));

		SyncFile syncFile = SyncFileService.addSyncFile(
			null, null, true, null, filePathName, null, null, parentFolderId,
			repositoryId, 0, SyncFile.STATE_SYNCED, syncAccountId,
			SyncFile.TYPE_FILE);

		if (typePK == 0) {
			syncFile.setTypePK(syncFile.getSyncFileId());

			SyncFileService.update(syncFile);
		}

		return syncFile;
	}

	public static SyncFile addFolderSyncFile(
			String filePathName, int state, long syncAccountId)
		throws Exception {

		return addFolderSyncFile(filePathName, 0, state, syncAccountId);
	}

	public static SyncFile addFolderSyncFile(
			String filePathName, long syncAccountId)
		throws Exception {

		return addFolderSyncFile(
			filePathName, SyncFile.STATE_SYNCED, syncAccountId);
	}

	public static SyncFile addFolderSyncFile(
			String filePathName, long parentFolderId, int state,
			long syncAccountId)
		throws Exception {

		return addFolderSyncFile(
			filePathName, parentFolderId, 0, state, syncAccountId, 0);
	}

	public static SyncFile addFolderSyncFile(
			String filePathName, long parentFolderId, long syncAccountId)
		throws Exception {

		return addFolderSyncFile(
			filePathName, parentFolderId, SyncFile.STATE_SYNCED, syncAccountId);
	}

	public static SyncFile addFolderSyncFile(
			String filePathName, long parentFolderId, long repositoryId,
			int state, long syncAccountId, long typePK)
		throws Exception {

		Files.createDirectory(Paths.get(filePathName));

		SyncFile syncFile = SyncFileService.addSyncFile(
			null, null, false, null, filePathName, null, null, parentFolderId,
			repositoryId, 0, state, syncAccountId, SyncFile.TYPE_FOLDER);

		if (typePK == 0) {
			syncFile.setTypePK(syncFile.getSyncFileId());

			SyncFileService.update(syncFile);
		}

		return syncFile;
	}

}