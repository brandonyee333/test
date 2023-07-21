/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.util;

import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Timothy Bell
 */
public class LoopFeaturedContentUtil {

	public static boolean isFeatured(
		ThemeDisplay themeDisplay, String featuredPreferencesKey,
		long classPK) {

		try {
			String featuredClassPKsString =
				LoopPortletPreferencesUtil.getPreference(
					themeDisplay.getCompanyId(), themeDisplay.getCompanyId(),
					PortletKeys.PREFS_OWNER_TYPE_COMPANY,
					featuredPreferencesKey);

			long[] featuredClassPKs = StringUtil.split(
				featuredClassPKsString, 0L);

			return ArrayUtil.contains(featuredClassPKs, classPK);
		}
		catch (Exception e) {
		}

		return false;
	}

}