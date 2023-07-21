/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.privatemessaging.internal.model.listener;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.social.privatemessaging.service.UserThreadLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Scott Lee
 * @author Peter Fellwock
 */
@Component(immediate = true, service = ModelListener.class)
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterUpdate(User user) {
		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Updating private messages user name for user " +
						user.getUserId());
			}

			_userThreadLocalService.updateUserName(user);
		}
		catch (Exception e) {
			_log.error(
				"Unable to update private messages user name for user " +
					user.getUserId());
		}
	}

	@Override
	public void onBeforeRemove(User user) {
		try {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Removing private messages for user " + user.getUserId());
			}

			_userThreadLocalService.deleteUser(user.getUserId());
		}
		catch (Exception e) {
			_log.error(
				"Unable to remove private messages for user " +
					user.getUserId());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserModelListener.class);

	@Reference
	private UserThreadLocalService _userThreadLocalService;

}