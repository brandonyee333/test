/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.messaging.subscription;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Time;

import org.apache.commons.lang.time.StopWatch;

/**
 * @author Brian Wing Shun Chan
 */
public class SubscriptionSenderMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		SubscriptionSender subscriptionSender =
			(SubscriptionSender)message.getPayload();

		StopWatch stopWatch = new StopWatch();

		stopWatch.start();

		if (_log.isInfoEnabled()) {
			_log.info(
				"Sending notifications for {mailId=" +
					subscriptionSender.getMailId() + "}");
		}

		subscriptionSender.flushNotifications();

		if (_log.isInfoEnabled()) {
			_log.info(
				StringBundler.concat(
					"Sending notifications for {mailId=",
					subscriptionSender.getMailId(), "} completed in ",
					String.valueOf(stopWatch.getTime() / Time.SECOND),
					" seconds"));
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SubscriptionSenderMessageListener.class);

}