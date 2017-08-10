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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Time;

import java.io.File;

import java.util.Date;

/**
 * @author Amos Fong
 * @author Calvin Keum
 */
public class OSBFileUtil {

	public static void cleanUpDir(String dirName, Date date) throws Exception {
		long timeInterval = System.currentTimeMillis() - Time.DAY;

		if (date != null) {
			timeInterval = date.getTime();
		}

		cleanUpDir(dirName, timeInterval);
	}

	public static void cleanUpDir(String dirName, long timeInterval)
		throws Exception {

		DLStoreUtil.validate(dirName, false);

		cleanUpDir(new File(_OSB_DIR + dirName), timeInterval);
	}

	public static File createFile(String fileName, String dirName)
		throws Exception {

		DLStoreUtil.validate(dirName + fileName, false);

		return createFile(new File(_OSB_DIR + dirName + fileName));
	}

	public static File createTempFile(String fileName) throws Exception {
		cleanUpDir(new File(_TEMP_DIR), System.currentTimeMillis() - Time.DAY);

		DLStoreUtil.validate(fileName, false);

		return createFile(new File(_TEMP_DIR + fileName));
	}

	public static Date getLastModifiedFileDate(String dirName) {
		long modifiedDate = 0;

		File dir = new File(_OSB_DIR + dirName);

		if (dir.exists()) {
			File[] files = dir.listFiles();

			for (File file : files) {
				if (file.lastModified() > modifiedDate) {
					modifiedDate = file.lastModified();
				}
			}
		}

		return DateUtil.newDate(modifiedDate);
	}

	public static String getPath(String dirName) {
		return _OSB_DIR + dirName;
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

	private static final String _OSB_DIR =
		PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/data/" + OSBConstants.OSB_DIR;

	private static final String _TEMP_DIR =
		PropsUtil.get(PropsKeys.LIFERAY_HOME) + "/data/" +
			OSBConstants.TEMP_DIR;

}