/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.legacy.message.subscriber;

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.constants.EntitlementConstants;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "topic.pattern=entity.organization.assigned",
	service = OrganizationAssignmentMessageSubscriber.class
)
public class OrganizationAssignmentMessageSubscriber
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

		if ((user == null) &&
			(organization.getOrganizationId() ==
				OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			if (userJSONObject == null) {
				return;
			}

			user = addWebUser(userJSONObject);
		}
		else if (user == null) {
			return;
		}

		userLocalService.addOrganizationUser(
			organization.getOrganizationId(), user.getUserId());
	}

}