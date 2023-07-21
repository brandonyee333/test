/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.internal.upgrade.v1_0_1;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;
import com.liferay.social.networking.constants.SocialNetworkingPortletKeys;

/**
 * @author Adolfo Pérez
 */
public class UpgradePortletId extends BaseUpgradePortletId {

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{
				"1_WAR_socialnetworkingportlet",
				SocialNetworkingPortletKeys.SUMMARY
			},
			{
				"2_WAR_socialnetworkingportlet",
				SocialNetworkingPortletKeys.FRIENDS
			},
			{"3_WAR_socialnetworkingportlet", SocialNetworkingPortletKeys.WALL},
			{
				"4_WAR_socialnetworkingportlet",
				SocialNetworkingPortletKeys.FRIENDS_ACTIVITIES
			},
			{
				"5_WAR_socialnetworkingportlet",
				SocialNetworkingPortletKeys.MEMBERS
			},
			{"6_WAR_socialnetworkingportlet", SocialNetworkingPortletKeys.MAP},
			{
				"7_WAR_socialnetworkingportlet",
				SocialNetworkingPortletKeys.MEETUPS
			},
			{
				"8_WAR_socialnetworkingportlet",
				SocialNetworkingPortletKeys.MEMBERS_ACTIVITIES
			}
		};
	}

}