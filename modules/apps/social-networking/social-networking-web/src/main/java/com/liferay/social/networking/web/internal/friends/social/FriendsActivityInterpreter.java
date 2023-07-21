/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.web.internal.friends.social;

import com.liferay.social.kernel.model.SocialActivityInterpreter;
import com.liferay.social.networking.constants.SocialNetworkingPortletKeys;
import com.liferay.social.networking.web.internal.social.BaseSocialNetworkingActivityInterpreter;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Zsolt Berentey
 */
@Component(
	property = "javax.portlet.name=" + SocialNetworkingPortletKeys.FRIENDS,
	service = SocialActivityInterpreter.class
)
public class FriendsActivityInterpreter
	extends BaseSocialNetworkingActivityInterpreter {
}