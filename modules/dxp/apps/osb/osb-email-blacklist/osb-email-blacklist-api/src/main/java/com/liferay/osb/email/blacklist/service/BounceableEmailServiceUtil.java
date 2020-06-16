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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for BounceableEmail. This utility wraps
 * {@link com.liferay.osb.email.blacklist.service.impl.BounceableEmailServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Jamie Sammons
 * @see BounceableEmailService
 * @see com.liferay.osb.email.blacklist.service.base.BounceableEmailServiceBaseImpl
 * @see com.liferay.osb.email.blacklist.service.impl.BounceableEmailServiceImpl
 * @generated
 */
@ProviderType
public class BounceableEmailServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.email.blacklist.service.impl.BounceableEmailServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void sendBounceableEmail(
		com.liferay.mail.kernel.model.MailMessage mailMessage) {
		getService().sendBounceableEmail(mailMessage);
	}

	public static void sendBounceableEmail(
		com.liferay.portal.kernel.util.SubscriptionSender subscriptionSender)
		throws java.lang.Exception {
		getService().sendBounceableEmail(subscriptionSender);
	}

	public static void sendBounceableEmailAsync(
		com.liferay.mail.kernel.model.MailMessage mailMessage) {
		getService().sendBounceableEmailAsync(mailMessage);
	}

	public static void sendBounceableEmailAsync(
		com.liferay.portal.kernel.util.SubscriptionSender subscriptionSender) {
		getService().sendBounceableEmailAsync(subscriptionSender);
	}

	public static BounceableEmailService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<BounceableEmailService, BounceableEmailService> _serviceTracker =
		ServiceTrackerFactory.open(BounceableEmailService.class);
}