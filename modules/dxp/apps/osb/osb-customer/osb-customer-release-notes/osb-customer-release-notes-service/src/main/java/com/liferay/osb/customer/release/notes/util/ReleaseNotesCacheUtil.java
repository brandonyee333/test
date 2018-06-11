/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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