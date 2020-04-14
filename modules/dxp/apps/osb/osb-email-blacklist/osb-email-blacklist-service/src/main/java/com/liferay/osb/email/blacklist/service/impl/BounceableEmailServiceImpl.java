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

package com.liferay.osb.email.blacklist.service.impl;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.osb.email.blacklist.internal.util.EmailBlacklistEntryThreadLocal;
import com.liferay.osb.email.blacklist.service.base.BounceableEmailServiceBaseImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.async.Async;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.spring.extender.service.ServiceReference;

/**
 * @author Haote Chou
 * @author Jamie Sammons
 */
public class BounceableEmailServiceImpl extends BounceableEmailServiceBaseImpl {

	public void sendBounceableEmail(MailMessage mailMessage) {
		EmailBlacklistEntryThreadLocal.setVerify(false);

		_mailService.sendEmail(mailMessage);
	}

	public void sendBounceableEmail(SubscriptionSender subscriptionSender)
		throws Exception {

		EmailBlacklistEntryThreadLocal.setVerify(false);

		subscriptionSender.flushNotifications();
	}

	@Async
	public void sendBounceableEmailAsync(MailMessage mailMessage) {
		sendBounceableEmail(mailMessage);
	}

	@Async
	public void sendBounceableEmailAsync(
		SubscriptionSender subscriptionSender) {

		try {
			sendBounceableEmail(subscriptionSender);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BounceableEmailServiceImpl.class);

	@ServiceReference(type = MailService.class)
	private MailService _mailService;

}