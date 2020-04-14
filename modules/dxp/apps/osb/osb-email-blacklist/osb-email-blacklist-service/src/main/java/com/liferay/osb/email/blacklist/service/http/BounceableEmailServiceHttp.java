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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>BounceableEmailServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Jamie Sammons
 * @see BounceableEmailServiceSoap
 * @generated
 */
public class BounceableEmailServiceHttp {

	public static void sendBounceableEmail(
		HttpPrincipal httpPrincipal,
		com.liferay.mail.kernel.model.MailMessage mailMessage) {

		try {
			MethodKey methodKey = new MethodKey(
				BounceableEmailServiceUtil.class, "sendBounceableEmail",
				_sendBounceableEmailParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, mailMessage);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void sendBounceableEmail(
			HttpPrincipal httpPrincipal,
			com.liferay.portal.kernel.util.SubscriptionSender
				subscriptionSender)
		throws Exception {

		try {
			MethodKey methodKey = new MethodKey(
				BounceableEmailServiceUtil.class, "sendBounceableEmail",
				_sendBounceableEmailParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, subscriptionSender);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof Exception) {
					throw (Exception)exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void sendBounceableEmailAsync(
		HttpPrincipal httpPrincipal,
		com.liferay.mail.kernel.model.MailMessage mailMessage) {

		try {
			MethodKey methodKey = new MethodKey(
				BounceableEmailServiceUtil.class, "sendBounceableEmailAsync",
				_sendBounceableEmailAsyncParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, mailMessage);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void sendBounceableEmailAsync(
		HttpPrincipal httpPrincipal,
		com.liferay.portal.kernel.util.SubscriptionSender subscriptionSender) {

		try {
			MethodKey methodKey = new MethodKey(
				BounceableEmailServiceUtil.class, "sendBounceableEmailAsync",
				_sendBounceableEmailAsyncParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, subscriptionSender);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		BounceableEmailServiceHttp.class);

	private static final Class<?>[] _sendBounceableEmailParameterTypes0 =
		new Class[] {com.liferay.mail.kernel.model.MailMessage.class};
	private static final Class<?>[] _sendBounceableEmailParameterTypes1 =
		new Class[] {com.liferay.portal.kernel.util.SubscriptionSender.class};
	private static final Class<?>[] _sendBounceableEmailAsyncParameterTypes2 =
		new Class[] {com.liferay.mail.kernel.model.MailMessage.class};
	private static final Class<?>[] _sendBounceableEmailAsyncParameterTypes3 =
		new Class[] {com.liferay.portal.kernel.util.SubscriptionSender.class};

}