/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Shinn Lok
 */
public class FileLockRetryUtil {

	public static void init() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				Iterator<PathCallable> iterator = _pathCallables.iterator();

				while (iterator.hasNext()) {
					if (processPathCallable(iterator.next())) {
						iterator.remove();
					}
				}
			}

		};

		_scheduledExecutorService.scheduleWithFixedDelay(
			runnable, 0, 5, TimeUnit.SECONDS);
	}

	public static boolean processPathCallable(PathCallable pathCallable) {
		FileChannel fileChannel = null;

		FileLock fileLock = null;

		try {
			Path filePath = pathCallable.getPath();

			if ((filePath == null) || FileUtil.notExists(filePath)) {
				return true;
			}

			if (Files.isDirectory(filePath)) {
				pathCallable.call();

				return true;
			}

			fileChannel = FileChannel.open(
				filePath, StandardOpenOption.READ, StandardOpenOption.WRITE);

			fileLock = FileUtil.getFileLock(fileChannel);

			if (fileLock != null) {
				pathCallable.call();

				return true;
			}
		}
		catch (Exception e) {
		}
		finally {
			FileUtil.releaseFileLock(fileLock);

			StreamUtil.cleanUp(fileChannel);
		}

		return false;
	}

	public static void registerPathCallable(PathCallable pathCallable) {
		if (!processPathCallable(pathCallable)) {
			_pathCallables.add(pathCallable);
		}
	}

	public static void shutdown() {
		_scheduledExecutorService.shutdownNow();
	}

	private static final List<PathCallable> _pathCallables = new ArrayList<>();
	private static final ScheduledExecutorService _scheduledExecutorService =
		Executors.newSingleThreadScheduledExecutor();

}