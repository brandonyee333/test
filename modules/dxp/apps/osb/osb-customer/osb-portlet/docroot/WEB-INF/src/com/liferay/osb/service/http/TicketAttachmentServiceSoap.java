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

import com.liferay.osb.service.TicketAttachmentServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.osb.service.TicketAttachmentServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.TicketAttachmentSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.TicketAttachment}, that is translated to a
 * {@link com.liferay.osb.model.TicketAttachmentSoap}. Methods that SOAP cannot
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
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TicketAttachmentServiceHttp
 * @see       com.liferay.osb.model.TicketAttachmentSoap
 * @see       com.liferay.osb.service.TicketAttachmentServiceUtil
 * @generated
 */
public class TicketAttachmentServiceSoap {
	public static com.liferay.osb.model.TicketAttachmentSoap addTicketAttachment(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.lang.String fileName, long fileSize, int type, int visibility,
		java.lang.String fileRepositoryId, int status)
		throws RemoteException {
		try {
			com.liferay.osb.model.TicketAttachment returnValue = TicketAttachmentServiceUtil.addTicketAttachment(userId,
					ticketEntryId, ticketSolutionId, fileName, fileSize, type,
					visibility, fileRepositoryId, status);

			return com.liferay.osb.model.TicketAttachmentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketAttachmentSoap[] addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		int[] pendingTypes,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.TicketAttachment> returnValue = TicketAttachmentServiceUtil.addTicketAttachments(userId,
					ticketEntryId, ticketSolutionId, files, types, visibility,
					status, pendingTypes, serviceContext);

			return com.liferay.osb.model.TicketAttachmentSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketAttachmentSoap[] addTicketAttachments(
		long userId, long ticketEntryId, long ticketSolutionId,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types, int visibility, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.TicketAttachment> returnValue = TicketAttachmentServiceUtil.addTicketAttachments(userId,
					ticketEntryId, ticketSolutionId, files, types, visibility,
					status, serviceContext);

			return com.liferay.osb.model.TicketAttachmentSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean checkAvailability(long ticketAttachmentId,
		java.lang.String fileRepositoryId) throws RemoteException {
		try {
			boolean returnValue = TicketAttachmentServiceUtil.checkAvailability(ticketAttachmentId,
					fileRepositoryId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketAttachmentSoap deleteTicketAttachment(
		long ticketAttachmentId) throws RemoteException {
		try {
			com.liferay.osb.model.TicketAttachment returnValue = TicketAttachmentServiceUtil.deleteTicketAttachment(ticketAttachmentId);

			return com.liferay.osb.model.TicketAttachmentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketAttachmentSoap getTicketAttachment(
		long ticketAttachmentId) throws RemoteException {
		try {
			com.liferay.osb.model.TicketAttachment returnValue = TicketAttachmentServiceUtil.getTicketAttachment(ticketAttachmentId);

			return com.liferay.osb.model.TicketAttachmentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String getUploadToken(long ticketEntryId,
		java.lang.String fileRepositoryId) throws RemoteException {
		try {
			java.lang.String returnValue = TicketAttachmentServiceUtil.getUploadToken(ticketEntryId,
					fileRepositoryId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketAttachmentSoap replicateTicketAttachment(
		long ticketAttachmentId) throws RemoteException {
		try {
			com.liferay.osb.model.TicketAttachment returnValue = TicketAttachmentServiceUtil.replicateTicketAttachment(ticketAttachmentId);

			return com.liferay.osb.model.TicketAttachmentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketAttachmentSoap updateDeleteDate(
		long ticketAttachmentId, java.util.Date deleteDate)
		throws RemoteException {
		try {
			com.liferay.osb.model.TicketAttachment returnValue = TicketAttachmentServiceUtil.updateDeleteDate(ticketAttachmentId,
					deleteDate);

			return com.liferay.osb.model.TicketAttachmentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketAttachmentSoap updateTicketAttachment(
		long ticketAttachmentId, long ticketEntryId, int type, int visibility,
		int[] pendingTypes) throws RemoteException {
		try {
			com.liferay.osb.model.TicketAttachment returnValue = TicketAttachmentServiceUtil.updateTicketAttachment(ticketAttachmentId,
					ticketEntryId, type, visibility, pendingTypes);

			return com.liferay.osb.model.TicketAttachmentSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketAttachmentSoap[] updateTicketAttachments(
		Long[] ticketAttachmentIds, long ticketEntryId,
		java.util.List<java.lang.Integer> types,
		java.util.List<java.lang.Integer> visibilities, int[] pendingTypes)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.TicketAttachment> returnValue = TicketAttachmentServiceUtil.updateTicketAttachments(ListUtil.toList(
						ticketAttachmentIds), ticketEntryId, types,
					visibilities, pendingTypes);

			return com.liferay.osb.model.TicketAttachmentSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TicketAttachmentServiceSoap.class);
}