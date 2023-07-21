/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.file.system;

import com.liferay.sync.engine.SyncEngine;
import com.liferay.sync.engine.util.BidirectionalMap;
import com.liferay.sync.engine.util.FileUtil;
import com.liferay.sync.engine.util.OSDetector;

import java.io.IOException;

import java.nio.file.Path;

import name.pachler.nio.file.ClosedWatchServiceException;
import name.pachler.nio.file.FileSystem;
import name.pachler.nio.file.FileSystems;
import name.pachler.nio.file.Paths;
import name.pachler.nio.file.StandardWatchEventKind;
import name.pachler.nio.file.WatchEvent;
import name.pachler.nio.file.WatchKey;
import name.pachler.nio.file.WatchService;
import name.pachler.nio.file.ext.ExtendedWatchEventKind;
import name.pachler.nio.file.ext.ExtendedWatchEventModifier;
import name.pachler.nio.file.impl.PathImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael Young
 */
public class JPathWatcher extends Watcher {

	public JPathWatcher(long syncAccountId, Path filePath) {
		super(syncAccountId, filePath);
	}

	@Override
	public void close() {
		try {
			_watchService.close();
		}
		catch (Exception e) {
		}
		finally {
			_watchService = null;
		}

		super.close();
	}

	@Override
	public void registerFilePath(Path filePath) throws IOException {
		if (FileUtil.notExists(filePath)) {
			return;
		}

		if (OSDetector.isWindows() && !filePath.equals(getBaseFilePath())) {
			return;
		}

		WatchKey watchKey = null;

		name.pachler.nio.file.Path jpathwatchFilePath = Paths.get(
			filePath.toString());

		if (OSDetector.isWindows()) {
			watchKey = jpathwatchFilePath.register(
				_watchService,
				new WatchEvent.Kind[] {
					ExtendedWatchEventKind.ENTRY_RENAME_FROM,
					ExtendedWatchEventKind.ENTRY_RENAME_TO,
					ExtendedWatchEventKind.KEY_INVALID,
					StandardWatchEventKind.ENTRY_CREATE,
					StandardWatchEventKind.ENTRY_DELETE,
					StandardWatchEventKind.ENTRY_MODIFY
				},
				ExtendedWatchEventModifier.FILE_TREE);
		}
		else {
			watchKey = jpathwatchFilePath.register(
				_watchService, ExtendedWatchEventKind.ENTRY_RENAME_FROM,
				ExtendedWatchEventKind.ENTRY_RENAME_TO,
				ExtendedWatchEventKind.KEY_INVALID,
				StandardWatchEventKind.ENTRY_CREATE,
				StandardWatchEventKind.ENTRY_DELETE,
				StandardWatchEventKind.ENTRY_MODIFY);
		}

		_filePaths.put(watchKey, filePath);

		if (_logger.isTraceEnabled()) {
			_logger.trace("Registered file path {}", filePath);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (_watchService == null) {
					break;
				}

				WatchKey watchKey = null;

				try {
					watchKey = _watchService.take();
				}
				catch (ClosedWatchServiceException cwse) {
					if (!SyncEngine.isRunning()) {
						break;
					}

					_logger.error(cwse.getMessage(), cwse);

					continue;
				}
				catch (Exception e) {
					if (_logger.isTraceEnabled()) {
						_logger.trace(e.getMessage(), e);
					}

					continue;
				}

				Path parentFilePath = _filePaths.get(watchKey);

				if (parentFilePath == null) {
					continue;
				}

				for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {
					WatchEvent<Path> pathWatchEvent =
						(WatchEvent<Path>)watchEvent;

					PathImpl pathImpl = (PathImpl)pathWatchEvent.context();

					if (pathImpl == null) {
						continue;
					}

					WatchEvent.Kind<?> kind = pathWatchEvent.kind();

					Path childFilePath = parentFilePath.resolve(
						pathImpl.toString());

					processWatchEvent(kind.name(), childFilePath);
				}

				processFailedFilePaths();

				if (!watchKey.reset()) {
					Path filePath = _filePaths.remove(watchKey);

					if (_logger.isTraceEnabled()) {
						_logger.trace("Unregistered file path {}", filePath);
					}

					processMissingFilePath(filePath);

					if (_filePaths.isEmpty()) {
						break;
					}
				}
			}
			catch (Exception e) {
				_logger.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public void unregisterFilePath(Path filePath) {
		WatchKey watchKey = _filePaths.removeValue(filePath);

		if (watchKey == null) {
			return;
		}

		watchKey.cancel();

		if (_logger.isTraceEnabled()) {
			_logger.trace("Unregistered file path {}", filePath);
		}
	}

	@Override
	protected void init() {
		FileSystem fileSystem = FileSystems.getDefault();

		_watchService = fileSystem.newWatchService();

		_filePaths = new BidirectionalMap<>();
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		JPathWatcher.class);

	private BidirectionalMap<WatchKey, Path> _filePaths;
	private WatchService _watchService;

}