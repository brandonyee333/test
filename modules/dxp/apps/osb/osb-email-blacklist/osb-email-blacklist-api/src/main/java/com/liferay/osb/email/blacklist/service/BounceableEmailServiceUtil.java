/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.service;

/**
 * Provides the remote service utility for BounceableEmail. This utility wraps
 * <code>com.liferay.osb.email.blacklist.service.impl.BounceableEmailServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Jamie Sammons
 * @see BounceableEmailService
 * @generated
 */
public class BounceableEmailServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.email.blacklist.service.impl.BounceableEmailServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
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
			com.liferay.portal.kernel.util.SubscriptionSender
				subscriptionSender)
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
		return _service;
	}

	public static void setService(BounceableEmailService service) {
		_service = service;
	}

	private static volatile BounceableEmailService _service;

}