/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.subscription;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.service.SubscriptionLocalServiceUtil;

/**
 * @author Raymond Augé
 */
public class CleanUpSubscriptionMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		long groupId = (Long)message.get("groupId");
		long[] userIds = (long[])message.get("userIds");

		for (long userId : userIds) {
			SubscriptionLocalServiceUtil.deleteSubscriptions(userId, groupId);
		}
	}

}