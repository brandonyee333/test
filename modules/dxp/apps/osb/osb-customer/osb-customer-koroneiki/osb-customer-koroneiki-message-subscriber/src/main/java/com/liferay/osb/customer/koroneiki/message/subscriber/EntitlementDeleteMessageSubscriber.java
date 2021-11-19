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

package com.liferay.osb.customer.koroneiki.message.subscriber;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.customer.subscription.util.DXPCloudStatusPageSubscriptionUtil;
import com.liferay.osb.customer.zendesk.constants.ZendeskDestinationNames;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "topic.pattern=koroneiki.entitlement.delete",
	service = EntitlementDeleteMessageSubscriber.class
)
public class EntitlementDeleteMessageSubscriber
	extends BaseMessageSubscriber implements MessageSubscriber {

	@Override
	protected void doReceive(Message message) throws Exception {
		sendMessage(
			ZendeskDestinationNames.ACCOUNT_ENTITLEMENT_SYNC,
			message.getDestinationName(), (String)message.getPayload());

		JSONObject jsonObject = jsonFactory.createJSONObject(
			(String)message.getPayload());

		JSONObject contactJSONObject = jsonObject.getJSONObject("contact");

		if (contactJSONObject == null) {
			return;
		}

		JSONObject entitlementJSONObject = jsonObject.getJSONObject(
			"entitlement");

		String name = entitlementJSONObject.getString("name");

		Organization organization = organizationLocalService.fetchOrganization(
			OSBCustomerConstants.COMPANY_ID,
			EntitlementConstants.ORGANIZATION_NAME_PREFIX + name);

		if (organization == null) {
			return;
		}

		User user = userLocalService.fetchUserByEmailAddress(
			OSBCustomerConstants.COMPANY_ID,
			contactJSONObject.getString("emailAddress"));

		if (user == null) {
			return;
		}

		userLocalService.unsetOrganizationUsers(
			organization.getOrganizationId(), new long[] {user.getUserId()});

		ExpandoBridge organizationExpandoBridge =
			organization.getExpandoBridge();

		boolean remote = (Boolean)organizationExpandoBridge.getAttribute(
			"remote", false);

		if (remote) {
			userIdentityProvider.removeOrganizationMembership(
				organization.getOrganizationId(), user.getUserId());
		}

		if (name.equals(EntitlementConstants.NAME_CUSTOMER_DXP)) {
			_dxpCloudStatusPageSubscriptionUtil.unsubscribe(user);
		}
	}

	@Reference
	private DXPCloudStatusPageSubscriptionUtil
		_dxpCloudStatusPageSubscriptionUtil;

}