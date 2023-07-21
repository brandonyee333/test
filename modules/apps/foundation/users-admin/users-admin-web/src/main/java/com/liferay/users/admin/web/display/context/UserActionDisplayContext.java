/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.web.display.context;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.users.admin.user.action.contributor.UserActionContributor;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Drew Brokke
 */
public class UserActionDisplayContext {

	public UserActionDisplayContext(
		HttpServletRequest request, LiferayPortletRequest liferayPortletRequest,
		User user, User selUser) {

		UserActionContributor[] userActionContributors =
			(UserActionContributor[])request.getAttribute(
				"USER_ACTION_CONTRIBUTORS");

		if (userActionContributors == null) {
			_filteredUserActionContributors = new UserActionContributor[0];
		}
		else {
			_filteredUserActionContributors = ArrayUtil.filter(
				userActionContributors,
				userActionContributor -> userActionContributor.isShow(
					liferayPortletRequest, user, selUser));
		}
	}

	public UserActionContributor[] getFilteredUserActionContributors() {
		return _filteredUserActionContributors;
	}

	private final UserActionContributor[] _filteredUserActionContributors;

}