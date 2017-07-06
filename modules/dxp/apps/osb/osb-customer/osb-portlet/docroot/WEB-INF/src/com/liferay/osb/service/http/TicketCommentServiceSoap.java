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

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link TicketCommentServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.TicketCommentSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.TicketComment}, that is translated to a
 * {@link com.liferay.osb.model.TicketCommentSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
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
 * @author Brian Wing Shun Chan
 * @see TicketCommentServiceHttp
 * @see com.liferay.osb.model.TicketCommentSoap
 * @see TicketCommentServiceUtil
 * @generated
 */
@ProviderType
public class TicketCommentServiceSoap {
	public static com.liferay.osb.model.TicketCommentSoap addTicketComment(
		long userId, long ticketEntryId, java.lang.String body, int type,
		int visibility, int status, long ticketCannedResponseId,
		int[] pendingTypes,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.osb.model.TicketComment returnValue = TicketCommentServiceUtil.addTicketComment(userId,
					ticketEntryId, body, type, visibility, status,
					ticketCannedResponseId, pendingTypes, files, types,
					serviceContext);

			return com.liferay.osb.model.TicketCommentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketCommentSoap addTicketComment(
		long userId, long ticketEntryId, java.lang.String body, int type,
		int visibility, int status, long ticketCannedResponseId,
		int[] pendingTypes,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.osb.model.TicketComment returnValue = TicketCommentServiceUtil.addTicketComment(userId,
					ticketEntryId, body, type, visibility, status,
					ticketCannedResponseId, pendingTypes, serviceContext);

			return com.liferay.osb.model.TicketCommentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketCommentSoap deleteTicketComment(
		long ticketCommentId) throws RemoteException {
		try {
			com.liferay.osb.model.TicketComment returnValue = TicketCommentServiceUtil.deleteTicketComment(ticketCommentId);

			return com.liferay.osb.model.TicketCommentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketCommentSoap updateTicketComment(
		long userId, long ticketCommentId, long ticketEntryId,
		java.lang.String body, int visibility, int status,
		long ticketCannedResponseId, int[] pendingTypes,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types) throws RemoteException {
		try {
			com.liferay.osb.model.TicketComment returnValue = TicketCommentServiceUtil.updateTicketComment(userId,
					ticketCommentId, ticketEntryId, body, visibility, status,
					ticketCannedResponseId, pendingTypes, files, types);

			return com.liferay.osb.model.TicketCommentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketCommentSoap updateTicketCommentType(
		long ticketCommentId, int type) throws RemoteException {
		try {
			com.liferay.osb.model.TicketComment returnValue = TicketCommentServiceUtil.updateTicketCommentType(ticketCommentId,
					type);

			return com.liferay.osb.model.TicketCommentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TicketCommentServiceSoap.class);
}