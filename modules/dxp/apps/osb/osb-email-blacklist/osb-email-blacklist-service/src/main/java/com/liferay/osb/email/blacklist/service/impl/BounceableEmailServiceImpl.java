/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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