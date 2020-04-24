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