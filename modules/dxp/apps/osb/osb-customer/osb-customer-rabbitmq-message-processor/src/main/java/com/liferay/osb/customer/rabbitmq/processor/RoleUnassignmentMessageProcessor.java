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

package com.liferay.osb.customer.rabbitmq.processor;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "routing.key=entity.role.unassigned",
	service = RoleUnassignmentMessageProcessor.class
)
public class RoleUnassignmentMessageProcessor extends BaseMessageProcessor {

	@Override
	protected void doProcess(JSONObject jsonObject) throws Exception {
		JSONObject roleJSONObject = jsonObject.getJSONObject("role");

		Role role = fetchRole(roleJSONObject);

		if (role == null) {
			return;
		}

		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		User user = fetchUser(userJSONObject);

		if (user == null) {
			return;
		}

		userLocalService.deleteRoleUser(role.getRoleId(), user.getUserId());
	}

}