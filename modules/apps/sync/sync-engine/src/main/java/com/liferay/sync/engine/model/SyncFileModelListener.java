/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.model;

import java.util.Map;

/**
 * @author Shinn Lok
 */
public class SyncFileModelListener implements ModelListener<SyncFile> {

	@Override
	public void onCreate(SyncFile syncFile) {
	}

	@Override
	public void onRemove(SyncFile syncFile) {
	}

	@Override
	public void onUpdate(
		SyncFile syncFile, Map<String, Object> originalValues) {
	}

}