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

package com.liferay.osb.downloads.util;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.webcache.WebCachePoolUtil;
import com.liferay.taglib.util.GetUrlWebCacheItem;

/**
 * @author Amos Fong
 */
public class DownloadsUtil {

	public static String[] getPublicComponents(
		String url, String liferayVersion) {

		WebCacheItem webCacheItem = new GetUrlWebCacheItem(url, Time.HOUR);

		String publicComponents = (String)WebCachePoolUtil.get(
			DownloadsUtil.class.getName() + StringPool.PERIOD + liferayVersion,
			webCacheItem);

		if (Validator.isNull(publicComponents) ||
			publicComponents.equals(_ERROR_MESSAGE)) {

			return new String[0];
		}

		return StringUtil.split(publicComponents);
	}

	private static final String _ERROR_MESSAGE =
		"Session has expired. Please try again.";

}