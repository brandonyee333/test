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

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "routing.key=koroneiki.account.contactrole.unassigned",
	service = AccountContactRoleUnassignedMessageProcessor.class
)
public class AccountContactRoleUnassignedMessageProcessor
	extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		/*
		TODO
		JSONObject accountJSONObject = jsonObject.getJSONObject("account");

		CorpProject corpProject =
			CorpProjectLocalServiceUtil.fetchCorpProjectByUuid(
				accountJSONObject.getString("key"));

		if (corpProject == null) {
			return;
		}

		JSONObject contactJSONObject = jsonObject.getJSONObject("contact");

		User user = fetchUser(contactJSONObject.getString("uuid"));

		if (user == null) {
			user = addKoroneikiUser(contactJSONObject);
		}

		Group group = corpProject.getGroup();

		JSONObject contactRoleJSONObject = jsonObject.getJSONObject(
			"contactRole");

		Role role = fetchRole(contactRoleJSONObject.getString("key"));

		if (role == null) {
			return;
		}

		userGroupRoleLocalService.deleteUserGroupRoles(
			user.getUserId(), group.getGroupId(),
			new long[] {role.getRoleId()});
		*/
	}

}