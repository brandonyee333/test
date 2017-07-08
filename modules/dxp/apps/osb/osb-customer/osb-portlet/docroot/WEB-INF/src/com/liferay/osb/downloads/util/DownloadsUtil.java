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

package com.liferay.osb.downloads.util;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.StringPool;
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