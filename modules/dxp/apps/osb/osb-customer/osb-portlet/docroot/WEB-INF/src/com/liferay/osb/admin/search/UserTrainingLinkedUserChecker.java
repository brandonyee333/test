/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.admin.search;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.osb.model.TrainingLinkedUser;
import com.liferay.osb.service.TrainingLinkedUserLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;

import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Douglas Wong
 */
public class UserTrainingLinkedUserChecker extends RowChecker {

	public UserTrainingLinkedUserChecker(
		RenderResponse renderResponse, User user) {

		super(renderResponse);

		_user = user;
	}

	@Override
	public String getRowCheckBox(
		boolean checked, boolean disabled, String primaryKey) {

		return getRowCheckBox(null, checked, disabled, primaryKey);
	}

	public String getRowCheckBox(
		HttpServletRequest request, boolean checked, boolean disabled,
		String primaryKey) {

		if (disabled) {
			return StringPool.BLANK;
		}

		return getRowCheckBox(
			checked, disabled, getRowIds(), primaryKey,
			StringUtil.quote(getRowIds()), StringUtil.quote(getAllRowIds()),
			StringPool.BLANK);
	}

	@Override
	public boolean isChecked(Object obj) {
		User user = (User)obj;

		try {
			TrainingLinkedUser trainingLinkedUser1 =
				TrainingLinkedUserLocalServiceUtil.fetchUserTrainingLinkedUser(
					user.getUserId());
			TrainingLinkedUser trainingLinkedUser2 =
				TrainingLinkedUserLocalServiceUtil.fetchUserTrainingLinkedUser(
					_user.getUserId());

			if ((user.getUserId() == _user.getUserId()) ||
				((trainingLinkedUser1 != null) &&
				 (trainingLinkedUser2 != null) &&
				 (trainingLinkedUser1.getPrimaryUserId() ==
					trainingLinkedUser2.getPrimaryUserId()))) {

				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	@Override
	public boolean isDisabled(Object obj) {
		User user = (User)obj;

		try {
			long userId = user.getUserId();

			TrainingLinkedUser trainingLinkedUser1 =
				TrainingLinkedUserLocalServiceUtil.fetchUserTrainingLinkedUser(
					userId);

			if (trainingLinkedUser1.getPrimaryUserId() == userId) {
				return true;
			}

			TrainingLinkedUser trainingLinkedUser2 =
				TrainingLinkedUserLocalServiceUtil.fetchUserTrainingLinkedUser(
					_user.getUserId());

			if ((trainingLinkedUser1 != null) &&
				((trainingLinkedUser2 == null) ||
				 (trainingLinkedUser1.getPrimaryUserId() !=
					trainingLinkedUser2.getPrimaryUserId()))) {

				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		UserTrainingLinkedUserChecker.class);

	private User _user;

}