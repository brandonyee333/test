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

import com.liferay.osb.customer.admin.constants.EntitlementConstants;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "routing.key=koroneiki.entitlement.delete",
	service = EntitlementDeleteMessageProcessor.class
)
public class EntitlementDeleteMessageProcessor extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		JSONObject contactJSONObject = jsonObject.getJSONObject("contact");

		if (contactJSONObject == null) {
			return;
		}

		JSONObject entitlementJSONObject = jsonObject.getJSONObject(
			"entitlement");

		Organization organization = fetchOrganization(
			EntitlementConstants.ORGANIZATION_NAME_PREFIX +
				entitlementJSONObject.getString("name"));

		if (organization == null) {
			return;
		}

		User user = fetchUserByEmailAddress(
			contactJSONObject.getString("emailAddress"));

		if (user == null) {
			return;
		}

		userLocalService.unsetOrganizationUsers(
			organization.getOrganizationId(), new long[] {user.getUserId()});
	}

}