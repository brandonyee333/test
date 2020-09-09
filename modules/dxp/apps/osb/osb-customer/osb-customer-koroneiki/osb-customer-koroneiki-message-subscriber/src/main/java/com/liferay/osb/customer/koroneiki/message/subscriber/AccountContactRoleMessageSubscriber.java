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

import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.customer.subscription.util.DXPCloudStatusPageSubscriptionUtil;
import com.liferay.osb.customer.zendesk.constants.ZendeskDestinationNames;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactRoleSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactSerDes;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = {
		"topic.pattern=koroneiki.account.contactrole.assigned",
		"topic.pattern=koroneiki.account.contactrole.unassigned"
	},
	service = AccountContactRoleMessageSubscriber.class
)
public class AccountContactRoleMessageSubscriber
	extends BaseMessageSubscriber implements MessageSubscriber {

	@Override
	public void doReceive(Message message) throws Exception {
		sendMessage(
			ZendeskDestinationNames.ACCOUNT_CONTACT_SYNC,
			message.getDestinationName(), (String)message.getPayload());

		try {
			updateDXPCloudSubscriptions(message);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void updateDXPCloudSubscriptions(Message message)
		throws Exception {

		JSONObject jsonObject = _jsonFactory.createJSONObject(
			(String)message.getPayload());

		Account account = AccountSerDes.toDTO(jsonObject.getString("account"));
		Contact contact = ContactSerDes.toDTO(jsonObject.getString("contact"));
		ContactRole contactRole = ContactRoleSerDes.toDTO(
			jsonObject.getString("contactRole"));

		if (ArrayUtil.contains(
				ContactRoleConstants.SUPPORT_CONTACT_ROLES,
				contactRole.getName())) {

			User user = _userIdentityProvider.fetchUserByEmailAddress(
				contact.getEmailAddress());

			if (user == null) {
				return;
			}

			String topic = message.getTopic();

			if (topic.equals(
					"topic.pattern=koroneiki.account.contactrole.assigned")) {

				if (_dxpCloudStatusPageSubscriptionUtil.hasActiveDXPCloud(
						account.getKey())) {

					_dxpCloudStatusPageSubscriptionUtil.subscribe(user);
				}
			}
			else {
				_dxpCloudStatusPageSubscriptionUtil.unsubscribe(user);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountContactRoleMessageSubscriber.class);

	@Reference
	private DXPCloudStatusPageSubscriptionUtil
		_dxpCloudStatusPageSubscriptionUtil;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference(target = "(provider=okta)")
	private UserIdentityProvider _userIdentityProvider;

}