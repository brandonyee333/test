/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.web.internal.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.social.networking.constants.SocialNetworkingPortletKeys;
import com.liferay.social.networking.web.internal.configuration.SocialNetworkingServiceConfigurationValues;

/**
 * @author Zsolt Berentey
 */
public class WallUtil {

	public static String getWallLayoutFriendlyURL(User user)
		throws PortalException {

		String wallLayoutFriendlyURL =
			SocialNetworkingServiceConfigurationValues.WALL_LAYOUT_FRIENDLY_URL;

		if (Validator.isNull(wallLayoutFriendlyURL)) {
			Group group = user.getGroup();

			long plid = LayoutLocalServiceUtil.getDefaultPlid(
				group.getGroupId(), false, SocialNetworkingPortletKeys.WALL);

			if (plid != 0) {
				Layout layout = LayoutLocalServiceUtil.getLayout(plid);

				wallLayoutFriendlyURL = layout.getFriendlyURL();
			}
		}

		return wallLayoutFriendlyURL;
	}

	public static String getWallLink(
			User user, String wallLayoutFriendlyURL, String screenNameOrUserId,
			ThemeDisplay themeDisplay)
		throws Exception {

		if (Validator.isNull(wallLayoutFriendlyURL)) {
			return null;
		}

		StringBundler sb = new StringBundler(7);

		sb.append(themeDisplay.getPortalURL());
		sb.append(themeDisplay.getPathFriendlyURLPublic());
		sb.append(StringPool.SLASH);
		sb.append(HtmlUtil.escapeURL(user.getScreenName()));
		sb.append(wallLayoutFriendlyURL);
		sb.append("/-/wall/");
		sb.append(screenNameOrUserId);

		return sb.toString();
	}

}