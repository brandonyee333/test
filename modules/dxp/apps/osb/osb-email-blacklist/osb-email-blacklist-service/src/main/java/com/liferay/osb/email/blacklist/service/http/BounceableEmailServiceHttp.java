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

package com.liferay.osb.email.blacklist.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.email.blacklist.service.BounceableEmailServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link BounceableEmailServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
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
 * @see HttpPrincipal
 * @see BounceableEmailServiceUtil
 * @generated
 */
@ProviderType
public class BounceableEmailServiceHttp {
	public static void sendBounceableEmail(HttpPrincipal httpPrincipal,
		com.liferay.mail.kernel.model.MailMessage mailMessage) {
		try {
			MethodKey methodKey = new MethodKey(BounceableEmailServiceUtil.class,
					"sendBounceableEmail", _sendBounceableEmailParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					mailMessage);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void sendBounceableEmail(HttpPrincipal httpPrincipal,
		com.liferay.portal.kernel.util.SubscriptionSender subscriptionSender)
		throws java.lang.Exception {
		try {
			MethodKey methodKey = new MethodKey(BounceableEmailServiceUtil.class,
					"sendBounceableEmail", _sendBounceableEmailParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					subscriptionSender);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof java.lang.Exception) {
					throw (java.lang.Exception)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void sendBounceableEmailAsync(HttpPrincipal httpPrincipal,
		com.liferay.mail.kernel.model.MailMessage mailMessage) {
		try {
			MethodKey methodKey = new MethodKey(BounceableEmailServiceUtil.class,
					"sendBounceableEmailAsync",
					_sendBounceableEmailAsyncParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					mailMessage);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void sendBounceableEmailAsync(HttpPrincipal httpPrincipal,
		com.liferay.portal.kernel.util.SubscriptionSender subscriptionSender) {
		try {
			MethodKey methodKey = new MethodKey(BounceableEmailServiceUtil.class,
					"sendBounceableEmailAsync",
					_sendBounceableEmailAsyncParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					subscriptionSender);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(BounceableEmailServiceHttp.class);
	private static final Class<?>[] _sendBounceableEmailParameterTypes0 = new Class[] {
			com.liferay.mail.kernel.model.MailMessage.class
		};
	private static final Class<?>[] _sendBounceableEmailParameterTypes1 = new Class[] {
			com.liferay.portal.kernel.util.SubscriptionSender.class
		};
	private static final Class<?>[] _sendBounceableEmailAsyncParameterTypes2 = new Class[] {
			com.liferay.mail.kernel.model.MailMessage.class
		};
	private static final Class<?>[] _sendBounceableEmailAsyncParameterTypes3 = new Class[] {
			com.liferay.portal.kernel.util.SubscriptionSender.class
		};
}