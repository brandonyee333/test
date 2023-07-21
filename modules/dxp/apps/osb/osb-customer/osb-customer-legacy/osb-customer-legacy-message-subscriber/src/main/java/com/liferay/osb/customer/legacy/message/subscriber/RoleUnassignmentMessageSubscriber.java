/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.legacy.message.subscriber;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "topic.pattern=entity.role.unassigned",
	service = RoleUnassignmentMessageSubscriber.class
)
public class RoleUnassignmentMessageSubscriber extends BaseMessageSubscriber {

	@Override
	protected void doReceive(JSONObject jsonObject) throws Exception {
		JSONObject roleJSONObject = jsonObject.getJSONObject("role");

		Role role = fetchRole(roleJSONObject.getString("uuid"));

		if (role == null) {
			return;
		}

		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		User user = fetchUser(userJSONObject.getString("uuid"));

		if (user == null) {
			return;
		}

		userLocalService.deleteRoleUser(role.getRoleId(), user.getUserId());
	}

}