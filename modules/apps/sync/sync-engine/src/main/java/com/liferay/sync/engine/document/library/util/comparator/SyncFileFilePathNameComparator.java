/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.util.comparator;

import com.liferay.sync.engine.model.SyncFile;

import java.util.Comparator;

/**
 * @author Shinn Lok
 */
public class SyncFileFilePathNameComparator implements Comparator<SyncFile> {

	@Override
	public int compare(SyncFile syncFile1, SyncFile syncFile2) {
		String filePathName1 = syncFile1.getFilePathName();
		String filePathName2 = syncFile2.getFilePathName();

		return filePathName1.compareToIgnoreCase(filePathName2);
	}

}