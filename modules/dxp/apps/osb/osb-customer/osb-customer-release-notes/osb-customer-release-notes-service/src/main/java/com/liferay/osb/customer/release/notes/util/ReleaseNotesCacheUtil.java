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

package com.liferay.osb.customer.release.notes.util;

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SystemProperties;

/**
 * @author Alan Zhang
 */
public class ReleaseNotesCacheUtil {

	public static final String CACHE_DIR_ISSUE = "issue";

	public static final String CACHE_DIR_LABEL = "label";

	public static final String CACHE_DIR_PROJECT_VERSION = "projectVersion";

	public static void clearCacheFile(String filePath) {
		FileUtil.delete(filePath);
	}

	public static String getCacheFilePath(String file, String directory) {
		StringBundler sb = new StringBundler(5);

		sb.append(_CACHE_DIR);
		sb.append(sterilizeFilePath(directory));
		sb.append(StringPool.FORWARD_SLASH);
		sb.append(sterilizeFilePath(file));
		sb.append(".html");

		return sb.toString();
	}

	protected static String sterilizeFilePath(String filePath) {
		return StringUtil.replace(
			filePath,
			new String[] {
				StringPool.BACK_SLASH, StringPool.COLON,
				StringPool.DOUBLE_QUOTE, StringPool.GREATER_THAN,
				StringPool.LESS_THAN, StringPool.PERCENT, StringPool.PERIOD,
				StringPool.PIPE, StringPool.QUESTION, StringPool.SLASH,
				StringPool.STAR
			},
			new String[] {
				StringPool.UNDERLINE, StringPool.UNDERLINE,
				StringPool.UNDERLINE, StringPool.UNDERLINE,
				StringPool.UNDERLINE, StringPool.UNDERLINE,
				StringPool.UNDERLINE, StringPool.UNDERLINE,
				StringPool.UNDERLINE, StringPool.UNDERLINE, StringPool.UNDERLINE
			});
	}

	private static final String _CACHE_DIR =
		SystemProperties.get(SystemProperties.TMP_DIR) +
			"/liferay/releasenotes/";

}