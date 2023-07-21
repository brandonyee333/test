/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.Subscription;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.ResourcePermissionCheckerUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;

/**
 * @author Roberto Díaz
 */
public class GroupSubscriptionCheckSubscriptionSender
	extends SubscriptionSender {

	public GroupSubscriptionCheckSubscriptionSender(String resourceName) {
		_resourceName = resourceName;
	}

	@Override
	protected Boolean hasSubscribePermission(
			PermissionChecker permissionChecker, Subscription subscription)
		throws PortalException {

		Group group = GroupLocalServiceUtil.fetchGroup(
			subscription.getClassPK());

		if (group != null) {
			ResourceAction resourceAction =
				ResourceActionLocalServiceUtil.fetchResourceAction(
					subscription.getClassName(), ActionKeys.SUBSCRIBE);

			if (resourceAction == null) {
				return true;
			}

			if (ResourcePermissionCheckerUtil.containsResourcePermission(
					permissionChecker, _resourceName, subscription.getClassPK(),
					ActionKeys.SUBSCRIBE)) {

				return true;
			}

			String className = subscription.getClassName();

			if (className.equals("com.liferay.journal.model.JournalFolder") &&
				group.isStaged() && !group.isStagingGroup()) {

				group = group.getStagingGroup();

				return ResourcePermissionCheckerUtil.containsResourcePermission(
					permissionChecker, _resourceName, group.getGroupId(),
					ActionKeys.SUBSCRIBE);
			}

			return false;
		}

		return super.hasSubscribePermission(permissionChecker, subscription);
	}

	private final String _resourceName;

}