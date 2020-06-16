/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.email.blacklist.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BounceableEmailService}.
 *
 * @author Jamie Sammons
 * @see BounceableEmailService
 * @generated
 */
@ProviderType
public class BounceableEmailServiceWrapper implements BounceableEmailService,
	ServiceWrapper<BounceableEmailService> {
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
	public java.lang.String getOSGiServiceIdentifier() {
		return _bounceableEmailService.getOSGiServiceIdentifier();
	}

	@Override
	public void sendBounceableEmail(
		com.liferay.mail.kernel.model.MailMessage mailMessage) {
		_bounceableEmailService.sendBounceableEmail(mailMessage);
	}

	@Override
	public void sendBounceableEmail(
		com.liferay.portal.kernel.util.SubscriptionSender subscriptionSender)
		throws java.lang.Exception {
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
	public void setWrappedService(BounceableEmailService bounceableEmailService) {
		_bounceableEmailService = bounceableEmailService;
	}

	private BounceableEmailService _bounceableEmailService;
}