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

package com.liferay.osb.loop.token.auth.service.http;

import com.liferay.osb.loop.token.auth.service.TokenAuthEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>TokenAuthEntryServiceUtil</code> service
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
 * @author Bruno Farache
 * @see TokenAuthEntryServiceSoap
 * @generated
 */
public class TokenAuthEntryServiceHttp {

	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			addTokenAuthEntry(HttpPrincipal httpPrincipal, String device)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TokenAuthEntryServiceUtil.class, "addTokenAuthEntry",
				_addTokenAuthEntryParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, device);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.loop.token.auth.model.TokenAuthEntry)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			deleteTokenAuthEntry(
				HttpPrincipal httpPrincipal, long tokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TokenAuthEntryServiceUtil.class, "deleteTokenAuthEntry",
				_deleteTokenAuthEntryParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, tokenAuthEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.loop.token.auth.model.TokenAuthEntry)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			deleteTokenAuthEntry(HttpPrincipal httpPrincipal, String token)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TokenAuthEntryServiceUtil.class, "deleteTokenAuthEntry",
				_deleteTokenAuthEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, token);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.loop.token.auth.model.TokenAuthEntry)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.loop.token.auth.model.TokenAuthEntry>
				getTokenAuthEntries(
					HttpPrincipal httpPrincipal, int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				TokenAuthEntryServiceUtil.class, "getTokenAuthEntries",
				_getTokenAuthEntriesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.loop.token.auth.model.TokenAuthEntry>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		TokenAuthEntryServiceHttp.class);

	private static final Class<?>[] _addTokenAuthEntryParameterTypes0 =
		new Class[] {String.class};
	private static final Class<?>[] _deleteTokenAuthEntryParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _deleteTokenAuthEntryParameterTypes2 =
		new Class[] {String.class};
	private static final Class<?>[] _getTokenAuthEntriesParameterTypes3 =
		new Class[] {int.class, int.class};

}