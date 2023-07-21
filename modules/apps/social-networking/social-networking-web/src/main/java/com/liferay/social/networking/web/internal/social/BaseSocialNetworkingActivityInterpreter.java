/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.networking.web.internal.social;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.social.kernel.model.BaseSocialActivityInterpreter;
import com.liferay.social.kernel.model.SocialActivity;
import com.liferay.social.networking.web.internal.friends.social.FriendsActivityKeys;

/**
 * @author Adolfo Pérez
 */
public abstract class BaseSocialNetworkingActivityInterpreter
	extends BaseSocialActivityInterpreter {

	@Override
	public String[] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String getLink(
			SocialActivity activity, ServiceContext serviceContext)
		throws Exception {

		return getUserName(activity.getReceiverUserId(), serviceContext);
	}

	@Override
	protected ResourceBundleLoader getResourceBundleLoader() {
		return ResourceBundleLoaderUtil.
			getResourceBundleLoaderByBundleSymbolicName(
				"com.liferay.social.networking.web");
	}

	@Override
	protected Object[] getTitleArguments(
			String groupName, SocialActivity activity, String link,
			String title, ServiceContext serviceContext)
		throws Exception {

		int activityType = activity.getType();

		if (activityType != FriendsActivityKeys.ADD_FRIEND) {
			return new Object[0];
		}

		String creatorUserName = getUserName(
			activity.getUserId(), serviceContext);
		String receiverUserName = getUserName(
			activity.getReceiverUserId(), serviceContext);

		return new Object[] {creatorUserName, receiverUserName};
	}

	@Override
	protected String getTitlePattern(
		String groupName, SocialActivity activity) {

		int activityType = activity.getType();

		if (activityType == FriendsActivityKeys.ADD_FRIEND) {
			return "activity-social-networking-summary-add-friend";
		}

		return StringPool.BLANK;
	}

	@Override
	protected boolean hasPermissions(
		PermissionChecker permissionChecker, SocialActivity activity,
		String actionId, ServiceContext serviceContext) {

		return true;
	}

	private static final String[] _CLASS_NAMES = {User.class.getName()};

}