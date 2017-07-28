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

import com.liferay.osb.service.TicketCommentServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link TicketCommentServiceUtil} service utility. The
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
 * @see TicketCommentServiceSoap
 * @see HttpPrincipal
 * @see TicketCommentServiceUtil
 * @generated
 */
@ProviderType
public class TicketCommentServiceHttp {
	public static com.liferay.osb.model.TicketComment addTicketComment(
		HttpPrincipal httpPrincipal, long userId, long ticketEntryId,
		java.lang.String body, int type, int visibility, int status,
		long ticketCannedResponseId, int[] pendingTypes,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketCommentServiceUtil.class,
					"addTicketComment", _addTicketCommentParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					ticketEntryId, body, type, visibility, status,
					ticketCannedResponseId, pendingTypes, files, types,
					serviceContext);

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

			return (com.liferay.osb.model.TicketComment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.TicketComment addTicketComment(
		HttpPrincipal httpPrincipal, long userId, long ticketEntryId,
		java.lang.String body, int type, int visibility, int status,
		long ticketCannedResponseId, int[] pendingTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketCommentServiceUtil.class,
					"addTicketComment", _addTicketCommentParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					ticketEntryId, body, type, visibility, status,
					ticketCannedResponseId, pendingTypes, serviceContext);

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

			return (com.liferay.osb.model.TicketComment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.TicketComment deleteTicketComment(
		HttpPrincipal httpPrincipal, long ticketCommentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketCommentServiceUtil.class,
					"deleteTicketComment", _deleteTicketCommentParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					ticketCommentId);

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

			return (com.liferay.osb.model.TicketComment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.TicketComment updateTicketComment(
		HttpPrincipal httpPrincipal, long userId, long ticketCommentId,
		long ticketEntryId, java.lang.String body, int visibility, int status,
		long ticketCannedResponseId, int[] pendingTypes,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketCommentServiceUtil.class,
					"updateTicketComment", _updateTicketCommentParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, userId,
					ticketCommentId, ticketEntryId, body, visibility, status,
					ticketCannedResponseId, pendingTypes, files, types);

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

			return (com.liferay.osb.model.TicketComment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.TicketComment updateTicketCommentType(
		HttpPrincipal httpPrincipal, long ticketCommentId, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(TicketCommentServiceUtil.class,
					"updateTicketCommentType",
					_updateTicketCommentTypeParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					ticketCommentId, type);

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

			return (com.liferay.osb.model.TicketComment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TicketCommentServiceHttp.class);
	private static final Class<?>[] _addTicketCommentParameterTypes0 = new Class[] {
			long.class, long.class, java.lang.String.class, int.class, int.class,
			int.class, long.class, int[].class, java.util.List.class,
			java.util.List.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _addTicketCommentParameterTypes1 = new Class[] {
			long.class, long.class, java.lang.String.class, int.class, int.class,
			int.class, long.class, int[].class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteTicketCommentParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateTicketCommentParameterTypes3 = new Class[] {
			long.class, long.class, long.class, java.lang.String.class,
			int.class, int.class, long.class, int[].class, java.util.List.class,
			java.util.List.class
		};
	private static final Class<?>[] _updateTicketCommentTypeParameterTypes4 = new Class[] {
			long.class, int.class
		};
}