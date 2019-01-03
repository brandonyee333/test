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

package com.liferay.lcs.task;

import com.liferay.lcs.messaging.PortalModelMessage;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Eduardo García
 */
public class UserGroupsTask extends BasePortalModelTask {

	@Override
	protected List<Map<String, Object>> getModels(int start, int end) {
		List<Map<String, Object>> userGroupMaps = new ArrayList<>();

		List<UserGroup> userGroups = UserGroupLocalServiceUtil.getUserGroups(
			start, end);

		for (UserGroup userGroup : userGroups) {
			userGroupMaps.add(getUserGroupMap(userGroup));
		}

		return userGroupMaps;
	}

	@Override
	protected long getModelsCount() {
		return UserGroupLocalServiceUtil.getUserGroupsCount();
	}

	@Override
	protected PortalModelMessage.Type getPortalModelType() {
		return PortalModelMessage.Type.USER_GROUP;
	}

	protected Map<String, Object> getUserGroupMap(UserGroup userGroup) {
		Map<String, Object> userGroupMap = new HashMap<>();

		int userGroupUsersCount = UserLocalServiceUtil.getUserGroupUsersCount(
			userGroup.getUserGroupId());

		userGroupMap.put("companyId", userGroup.getCompanyId());
		userGroupMap.put("name", userGroup.getName());
		userGroupMap.put("userCount", userGroupUsersCount);
		userGroupMap.put("userGroupId", userGroup.getUserGroupId());
		userGroupMap.put("uuid", userGroup.getUuid());

		return userGroupMap;
	}

}