/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.downloads.proxy.web.internal.util;

import com.liferay.petra.string.StringPool;
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