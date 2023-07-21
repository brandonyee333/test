/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author David Truong
 */
public class RobotsUtil {

	public static String getDefaultRobots() {
		return getDefaultRobots(null);
	}

	public static String getDefaultRobots(String virtualHost) {
		if (Validator.isNotNull(virtualHost)) {
			String content = ContentUtil.get(
				RobotsUtil.class.getClassLoader(),
				PropsValues.ROBOTS_TXT_WITH_SITEMAP);

			return StringUtil.replace(content, "[$HOST$]", virtualHost);
		}

		return ContentUtil.get(
			RobotsUtil.class.getClassLoader(),
			PropsValues.ROBOTS_TXT_WITHOUT_SITEMAP);
	}

	public static String getRobots(LayoutSet layoutSet) throws PortalException {
		if (layoutSet == null) {
			return getDefaultRobots(null);
		}

		return GetterUtil.get(
			layoutSet.getSettingsProperty(
				layoutSet.isPrivateLayout() + "-robots.txt"),
			getDefaultRobots(PortalUtil.getVirtualHostname(layoutSet)));
	}

}