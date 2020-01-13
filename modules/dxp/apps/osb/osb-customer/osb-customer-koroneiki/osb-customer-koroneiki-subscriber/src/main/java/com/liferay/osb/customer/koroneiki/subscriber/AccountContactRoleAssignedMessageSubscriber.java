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

package com.liferay.osb.customer.koroneiki.subscriber;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "topic.pattern=koroneiki.account.contactrole.assigned",
	service = AccountContactRoleAssignedMessageSubscriber.class
)
public class AccountContactRoleAssignedMessageSubscriber
	implements MessageSubscriber {

	public void receive(Message message) {
		try {
			com.liferay.portal.kernel.messaging.Message zendeskMessage =
				new com.liferay.portal.kernel.messaging.Message();

			zendeskMessage.put(
				"topic", "koroneiki.account.contactrole.assigned");
			zendeskMessage.setPayload(message.getPayload());

			MessageBusUtil.sendMessage(
				"liferay/zendesk_account_contact_sync", zendeskMessage);
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountContactRoleAssignedMessageSubscriber.class);

}