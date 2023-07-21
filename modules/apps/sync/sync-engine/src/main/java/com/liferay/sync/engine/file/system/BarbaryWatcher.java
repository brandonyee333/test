/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.file.system;

import com.barbarysoftware.watchservice.ClosedWatchServiceException;
import com.barbarysoftware.watchservice.ExtendedWatchEventKind;
import com.barbarysoftware.watchservice.StandardWatchEventKind;
import com.barbarysoftware.watchservice.WatchEvent;
import com.barbarysoftware.watchservice.WatchKey;
import com.barbarysoftware.watchservice.WatchService;
import com.barbarysoftware.watchservice.WatchableFile;

import java.io.File;
import java.io.IOException;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Shinn Lok
 */
public class BarbaryWatcher extends Watcher {

	public BarbaryWatcher(long syncAccountId, Path filePath) {
		super(syncAccountId, filePath);
	}

	@Override
	public void close() {
		try {
			_watchService.close();
		}
		catch (Exception e) {
		}

		super.close();
	}

	@Override
	public void registerFilePath(Path filePath) throws IOException {
		if (!filePath.equals(getBaseFilePath())) {
			return;
		}

		WatchableFile watchableFile = new WatchableFile(filePath.toFile());

		watchableFile.register(
			_watchService, ExtendedWatchEventKind.ENTRY_RENAME_FROM,
			ExtendedWatchEventKind.ENTRY_RENAME_TO,
			StandardWatchEventKind.ENTRY_CREATE,
			StandardWatchEventKind.ENTRY_DELETE,
			StandardWatchEventKind.ENTRY_MODIFY);

		if (_logger.isTraceEnabled()) {
			_logger.trace("Registered file path {}", filePath);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				WatchKey watchKey = null;

				try {
					watchKey = _watchService.take();
				}
				catch (ClosedWatchServiceException cwse) {
					break;
				}
				catch (Exception e) {
					if (_logger.isTraceEnabled()) {
						_logger.trace(e.getMessage(), e);
					}

					continue;
				}

				for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {
					WatchEvent<File> fileWatchEvent =
						(WatchEvent<File>)watchEvent;

					File file = fileWatchEvent.context();

					if (file == null) {
						continue;
					}

					WatchEvent.Kind<?> kind = fileWatchEvent.kind();

					processWatchEvent(kind.name(), file.toPath());
				}

				processFailedFilePaths();

				if (!watchKey.reset()) {
					if (_logger.isTraceEnabled()) {
						_logger.trace(
							"Unregistered file path {}", getBaseFilePath());
					}

					processMissingFilePath(getBaseFilePath());
				}
			}
			catch (Exception e) {
				_logger.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public void unregisterFilePath(Path filePath) {
	}

	@Override
	protected void init() {
		_watchService = WatchService.newWatchService();
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		BarbaryWatcher.class);

	private WatchService _watchService;

}