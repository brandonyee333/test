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

import com.liferay.osb.service.TicketCallServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link TicketCallServiceUtil} service utility. The
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
 * @see TicketCallServiceSoap
 * @see HttpPrincipal
 * @see TicketCallServiceUtil
 * @generated
 */
@ProviderType
public class TicketCallServiceHttp {
	public static com.liferay.osb.model.TicketCall addTicketCall(
		HttpPrincipal httpPrincipal, long ticketEntryId, int type,
		int callDateMonth, int callDateDay, int callDateYear, int callDateHour,
		int callDateMinute, long callLength, java.lang.String customerName,
		java.lang.String customerContact, java.lang.String confirmation,
		java.lang.String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketCallServiceUtil.class,
					"addTicketCall", _addTicketCallParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					ticketEntryId, type, callDateMonth, callDateDay,
					callDateYear, callDateHour, callDateMinute, callLength,
					customerName, customerContact, confirmation, instructions);

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

			return (com.liferay.osb.model.TicketCall)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TicketCallServiceHttp.class);
	private static final Class<?>[] _addTicketCallParameterTypes0 = new Class[] {
			long.class, int.class, int.class, int.class, int.class, int.class,
			int.class, long.class, java.lang.String.class,
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class
		};
}