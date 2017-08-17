/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.util;

import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Time;

import java.io.File;

/**
 * @author Amos Fong
 * @author Calvin Keum
 */
public class OSBFileUtil {

	public static File createTempFile(String fileName) throws Exception {
		cleanUpDir(new File(_TEMP_DIR), System.currentTimeMillis() - Time.DAY);

		DLStoreUtil.validate(fileName, false);

		return createFile(new File(_TEMP_DIR + fileName));
	}

	protected static void cleanUpDir(File dir, long timeInterval) {
		if (!dir.exists()) {
			dir.mkdirs();
		}

		File[] files = dir.listFiles();

		for (File file : files) {
			if (file.lastModified() < timeInterval) {
				FileUtil.delete(file);
			}
		}
	}

	protected static File createFile(File file) throws Exception {
		file.createNewFile();

		return file;
	}

	private static final String _TEMP_DIR =
		PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/data/" +
			OSBConstants.TEMP_DIR;

}