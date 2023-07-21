/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BounceableEmailService}.
 *
 * @author Jamie Sammons
 * @see BounceableEmailService
 * @generated
 */
public class BounceableEmailServiceWrapper
	implements BounceableEmailService, ServiceWrapper<BounceableEmailService> {

	public BounceableEmailServiceWrapper(
		BounceableEmailService bounceableEmailService) {

		_bounceableEmailService = bounceableEmailService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _bounceableEmailService.getOSGiServiceIdentifier();
	}

	@Override
	public void sendBounceableEmail(
		com.liferay.mail.kernel.model.MailMessage mailMessage) {

		_bounceableEmailService.sendBounceableEmail(mailMessage);
	}

	@Override
	public void sendBounceableEmail(
			com.liferay.portal.kernel.util.SubscriptionSender
				subscriptionSender)
		throws Exception {

		_bounceableEmailService.sendBounceableEmail(subscriptionSender);
	}

	@Override
	public void sendBounceableEmailAsync(
		com.liferay.mail.kernel.model.MailMessage mailMessage) {

		_bounceableEmailService.sendBounceableEmailAsync(mailMessage);
	}

	@Override
	public void sendBounceableEmailAsync(
		com.liferay.portal.kernel.util.SubscriptionSender subscriptionSender) {

		_bounceableEmailService.sendBounceableEmailAsync(subscriptionSender);
	}

	@Override
	public BounceableEmailService getWrappedService() {
		return _bounceableEmailService;
	}

	@Override
	public void setWrappedService(
		BounceableEmailService bounceableEmailService) {

		_bounceableEmailService = bounceableEmailService;
	}

	private BounceableEmailService _bounceableEmailService;

}