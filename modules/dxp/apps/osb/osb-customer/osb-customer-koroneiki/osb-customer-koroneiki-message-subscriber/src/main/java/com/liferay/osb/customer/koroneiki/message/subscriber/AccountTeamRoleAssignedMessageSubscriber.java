/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.message.subscriber;

import com.liferay.osb.customer.zendesk.constants.ZendeskDestinationNames;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "topic.pattern=koroneiki.account.teamrole.assigned",
	service = AccountTeamRoleAssignedMessageSubscriber.class
)
public class AccountTeamRoleAssignedMessageSubscriber
	extends BaseMessageSubscriber implements MessageSubscriber {

	@Override
	public void doReceive(Message message) throws Exception {
		sendMessage(
			ZendeskDestinationNames.ACCOUNT_TEAMROLE_SYNC,
			message.getDestinationName(), (String)message.getPayload());
	}

}