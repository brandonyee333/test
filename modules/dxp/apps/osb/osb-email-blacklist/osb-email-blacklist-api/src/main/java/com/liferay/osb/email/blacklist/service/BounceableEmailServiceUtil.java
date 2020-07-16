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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

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
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static void sendBounceableEmail(
		com.liferay.mail.kernel.model.MailMessage mailMessage) {

		getService().sendBounceableEmail(mailMessage);
	}

	public static void sendBounceableEmail(
			com.liferay.portal.kernel.util.SubscriptionSender
				subscriptionSender)
		throws Exception {

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

	private static ServiceTracker
		<BounceableEmailService, BounceableEmailService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(BounceableEmailService.class);

		ServiceTracker<BounceableEmailService, BounceableEmailService>
			serviceTracker =
				new ServiceTracker
					<BounceableEmailService, BounceableEmailService>(
						bundle.getBundleContext(), BounceableEmailService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}