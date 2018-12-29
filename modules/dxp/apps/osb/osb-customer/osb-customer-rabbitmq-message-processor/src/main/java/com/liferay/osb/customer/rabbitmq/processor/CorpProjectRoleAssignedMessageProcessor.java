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

import com.liferay.osb.model.CorpProject;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "routing.key=entity.corpproject.role.assigned",
	service = CorpProjectRoleAssignedMessageProcessor.class
)
public class CorpProjectRoleAssignedMessageProcessor
	extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		User user = fetchUser(userJSONObject);

		if (user == null) {
			user = addUser(userJSONObject);
		}

		JSONObject corpProjectJSONObject = jsonObject.getJSONObject(
			"corpProject");

		CorpProject corpProject =
			CorpProjectLocalServiceUtil.fetchCorpProjectByUuid(
				corpProjectJSONObject.getString("uuid"));

		if (corpProject == null) {
			return;
		}

		Group group = corpProject.getGroup();

		JSONObject roleJSONObject = jsonObject.getJSONObject("role");

		Role role = fetchRole(roleJSONObject);

		if (role == null) {
			return;
		}

		userGroupRoleLocalService.addUserGroupRoles(
			user.getUserId(), group.getGroupId(),
			new long[] {role.getRoleId()});

		if (role.getRoleId() ==
				OSBConstants.ROLE_OSB_CORP_ANALYTICS_CLOUD_OWNER_ID) {

			AccountEntryLocalServiceUtil.assignOwnership(
				corpProject.getUuid(), user.getUserId());
		}
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

}