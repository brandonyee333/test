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

package com.liferay.osb.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.AccountCallServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link AccountCallServiceUtil} service utility. The
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
 * @author Brian Wing Shun Chan
 * @see AccountCallServiceSoap
 * @see HttpPrincipal
 * @see AccountCallServiceUtil
 * @generated
 */
@ProviderType
public class AccountCallServiceHttp {
	public static com.liferay.osb.model.AccountCall deleteAccountCall(
		HttpPrincipal httpPrincipal, long accountCallId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountCallServiceUtil.class,
					"deleteAccountCall", _deleteAccountCallParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountCallId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.AccountCall)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.AccountCall updateAccountCall(
		HttpPrincipal httpPrincipal, long accountCallId, long accountEntryId,
		int type, int callDateMonth, int callDateDay, int callDateYear,
		int callDateHour, int callDateMinute, long callLength,
		java.lang.String summary, java.lang.String clientsPresent,
		java.lang.String notes, java.lang.String actionItems)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountCallServiceUtil.class,
					"updateAccountCall", _updateAccountCallParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountCallId, accountEntryId, type, callDateMonth,
					callDateDay, callDateYear, callDateHour, callDateMinute,
					callLength, summary, clientsPresent, notes, actionItems);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.AccountCall)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AccountCallServiceHttp.class);
	private static final Class<?>[] _deleteAccountCallParameterTypes0 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateAccountCallParameterTypes1 = new Class[] {
			long.class, long.class, int.class, int.class, int.class, int.class,
			int.class, int.class, long.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class
		};
}