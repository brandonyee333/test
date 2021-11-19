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

package com.liferay.osb.customer.legacy.message.subscriber;

import com.liferay.osb.customer.koroneiki.constants.EntitlementConstants;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "topic.pattern=entity.organization.unassigned",
	service = OrganizationUnassignmentMessageSubscriber.class
)
public class OrganizationUnassignmentMessageSubscriber
	extends BaseMessageSubscriber {

	protected void doReceive(JSONObject jsonObject) throws Exception {
		JSONObject organizationJSONObject = jsonObject.getJSONObject(
			"organization");

		Organization organization = fetchOrganization(organizationJSONObject);

		if (organization == null) {
			return;
		}

		String name = organization.getName();

		if (name.startsWith(EntitlementConstants.ORGANIZATION_NAME_PREFIX)) {
			return;
		}

		JSONObject userJSONObject = jsonObject.getJSONObject("user");

		User user = fetchUser(userJSONObject.getString("uuid"));

		if (user == null) {
			return;
		}

		userLocalService.deleteOrganizationUser(
			organization.getOrganizationId(), user.getUserId());
	}

}