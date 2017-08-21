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

import com.liferay.osb.service.TicketEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link TicketEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.TicketEntrySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.TicketEntry}, that is translated to a
 * {@link com.liferay.osb.model.TicketEntrySoap}. Methods that SOAP cannot
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
 * @see TicketEntryServiceHttp
 * @see com.liferay.osb.model.TicketEntrySoap
 * @see TicketEntryServiceUtil
 * @generated
 */
@ProviderType
public class TicketEntryServiceSoap {
	public static void closeTicketEntry(long ticketEntryId, int resolution,
		java.lang.String body) throws RemoteException {
		try {
			TicketEntryServiceUtil.closeTicketEntry(ticketEntryId, resolution,
				body);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void escalateTicketEntry(long ticketEntryId)
		throws RemoteException {
		try {
			TicketEntryServiceUtil.escalateTicketEntry(ticketEntryId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketEntrySoap forwardTicketEntry(
		long ticketEntryId, java.lang.String commentBody)
		throws RemoteException {
		try {
			com.liferay.osb.model.TicketEntry returnValue = TicketEntryServiceUtil.forwardTicketEntry(ticketEntryId,
					commentBody);

			return com.liferay.osb.model.TicketEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketEntrySoap getTicketEntry(
		long ticketEntryId) throws RemoteException {
		try {
			com.liferay.osb.model.TicketEntry returnValue = TicketEntryServiceUtil.getTicketEntry(ticketEntryId);

			return com.liferay.osb.model.TicketEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketEntrySoap getTicketEntry(
		long accountEntryId, long ticketId) throws RemoteException {
		try {
			com.liferay.osb.model.TicketEntry returnValue = TicketEntryServiceUtil.getTicketEntry(accountEntryId,
					ticketId);

			return com.liferay.osb.model.TicketEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketEntrySoap[] search(
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.TicketEntry> returnValue = TicketEntryServiceUtil.search(keywords,
					start, end, obc);

			return com.liferay.osb.model.TicketEntrySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int searchCount(java.lang.String keywords)
		throws RemoteException {
		try {
			int returnValue = TicketEntryServiceUtil.searchCount(keywords);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketEntrySoap updatePendingTypes(
		long ticketEntryId, int[] pendingTypes) throws RemoteException {
		try {
			com.liferay.osb.model.TicketEntry returnValue = TicketEntryServiceUtil.updatePendingTypes(ticketEntryId,
					pendingTypes);

			return com.liferay.osb.model.TicketEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketEntrySoap updateTicketEntry(
		long userId, long ticketEntryId, long assigneeUserId,
		long supportRegionId, int dueDateMonth, int dueDateDay,
		int dueDateYear, int dueDateHour, int dueDateMinute)
		throws RemoteException {
		try {
			com.liferay.osb.model.TicketEntry returnValue = TicketEntryServiceUtil.updateTicketEntry(userId,
					ticketEntryId, assigneeUserId, supportRegionId,
					dueDateMonth, dueDateDay, dueDateYear, dueDateHour,
					dueDateMinute);

			return com.liferay.osb.model.TicketEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TicketEntryServiceSoap.class);
}