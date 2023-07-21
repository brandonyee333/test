/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

import java.nio.file.Path;

import java.util.concurrent.Callable;

/**
 * @author Shinn Lok
 */
public abstract class PathCallable implements Callable {

	public PathCallable(Path path) {
		_path = path;
	}

	public Path getPath() {
		return _path;
	}

	public long getStartTime() {
		return _START_TIME;
	}

	private static final long _START_TIME = System.currentTimeMillis();

	private final Path _path;

}