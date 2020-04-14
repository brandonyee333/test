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