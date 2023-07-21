/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.file.system;

import java.nio.file.Path;

/**
 * @author Shinn Lok
 */
public class DummyWatcher extends Watcher {

	public DummyWatcher() {
		super(0, null);
	}

	@Override
	public void addDeletedFilePathName(String filePathName) {
	}

	@Override
	public void addDownloadedFilePathName(String filePathName) {
	}

	@Override
	public void addMovedFilePathName(String filePathName) {
	}

	@Override
	public void close() {
	}

	@Override
	public void registerFilePath(Path filePath) {
	}

	@Override
	public void removeDeletedFilePathName(String filePathName) {
	}

	@Override
	public void removeDownloadedFilePathName(String filePathName) {
	}

	@Override
	public void removeMovedFilePathName(String filePathName) {
	}

	@Override
	public void run() {
	}

	@Override
	public void unregisterFilePath(Path filePath) {
	}

	@Override
	public void walkFileTree(Path filePath, boolean skipRoot) {
	}

	@Override
	protected void init() {
	}

}