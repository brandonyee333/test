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

import com.liferay.osb.service.TicketSolutionServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link TicketSolutionServiceUtil} service utility. The
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
 * @see TicketSolutionServiceSoap
 * @see HttpPrincipal
 * @see TicketSolutionServiceUtil
 * @generated
 */
@ProviderType
public class TicketSolutionServiceHttp {
	public static com.liferay.osb.model.TicketSolution addTicketSolution(
		HttpPrincipal httpPrincipal, long userId, long ticketEntryId,
		java.lang.String summary, boolean useCustomerSummary, int issueType,
		java.lang.String solution, int type, boolean customerSpecific,
		boolean environmentSpecific, boolean versionSpecific,
		boolean reviewForKB, int status, int ticketEntrySubcomponent,
		java.lang.String ticketEntrySubcomponentCustom,
		java.util.List<java.lang.String> ticketLinkURLs,
		java.util.List<java.lang.Integer> ticketLinkTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws java.lang.Exception {
		try {
			MethodKey methodKey = new MethodKey(TicketSolutionServiceUtil.class,
					"addTicketSolution", _addTicketSolutionParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					ticketEntryId, summary, useCustomerSummary, issueType,
					solution, type, customerSpecific, environmentSpecific,
					versionSpecific, reviewForKB, status,
					ticketEntrySubcomponent, ticketEntrySubcomponentCustom,
					ticketLinkURLs, ticketLinkTypes, ticketAttachments);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof java.lang.Exception) {
					throw (java.lang.Exception)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.TicketSolution)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.TicketSolution updateTicketSolution(
		HttpPrincipal httpPrincipal, long ticketSolutionId, long ticketEntryId,
		int status, long statusByUserId, java.lang.String statusMessage,
		int statusReason) throws java.lang.Exception {
		try {
			MethodKey methodKey = new MethodKey(TicketSolutionServiceUtil.class,
					"updateTicketSolution", _updateTicketSolutionParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					ticketSolutionId, ticketEntryId, status, statusByUserId,
					statusMessage, statusReason);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof java.lang.Exception) {
					throw (java.lang.Exception)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.osb.model.TicketSolution)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TicketSolutionServiceHttp.class);
	private static final Class<?>[] _addTicketSolutionParameterTypes0 = new Class[] {
			long.class, long.class, java.lang.String.class, boolean.class,
			int.class, java.lang.String.class, int.class, boolean.class,
			boolean.class, boolean.class, boolean.class, int.class, int.class,
			java.lang.String.class, java.util.List.class, java.util.List.class,
			java.util.List.class
		};
	private static final Class<?>[] _updateTicketSolutionParameterTypes1 = new Class[] {
			long.class, long.class, int.class, long.class,
			java.lang.String.class, int.class
		};
}