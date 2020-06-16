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

package com.liferay.osb.email.blacklist.service.http;

import com.liferay.osb.email.blacklist.service.BounceableEmailServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>BounceableEmailServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Jamie Sammons
 * @see BounceableEmailServiceHttp
 * @generated
 */
public class BounceableEmailServiceSoap {

	public static void sendBounceableEmail(
			com.liferay.mail.kernel.model.MailMessage mailMessage)
		throws RemoteException {

		try {
			BounceableEmailServiceUtil.sendBounceableEmail(mailMessage);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void sendBounceableEmail(
			com.liferay.portal.kernel.util.SubscriptionSender
				subscriptionSender)
		throws RemoteException {

		try {
			BounceableEmailServiceUtil.sendBounceableEmail(subscriptionSender);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void sendBounceableEmailAsync(
			com.liferay.mail.kernel.model.MailMessage mailMessage)
		throws RemoteException {

		try {
			BounceableEmailServiceUtil.sendBounceableEmailAsync(mailMessage);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static void sendBounceableEmailAsync(
			com.liferay.portal.kernel.util.SubscriptionSender
				subscriptionSender)
		throws RemoteException {

		try {
			BounceableEmailServiceUtil.sendBounceableEmailAsync(
				subscriptionSender);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		BounceableEmailServiceSoap.class);

}