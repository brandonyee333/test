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

import com.liferay.osb.service.TicketWorkerServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.osb.service.TicketWorkerServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.TicketWorkerSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.TicketWorker}, that is translated to a
 * {@link com.liferay.osb.model.TicketWorkerSoap}. Methods that SOAP cannot
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
 * @see       TicketWorkerServiceHttp
 * @see       com.liferay.osb.model.TicketWorkerSoap
 * @see       com.liferay.osb.service.TicketWorkerServiceUtil
 * @generated
 */
public class TicketWorkerServiceSoap {
	public static com.liferay.osb.model.TicketWorkerSoap[] addTicketWorkers(
		long[] userIds, long ticketEntryId, long[] sourceClassNameIds,
		long[] sourceClassPKs, int[] roles, long primaryUserId)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.TicketWorker> returnValue = TicketWorkerServiceUtil.addTicketWorkers(userIds,
					ticketEntryId, sourceClassNameIds, sourceClassPKs, roles,
					primaryUserId);

			return com.liferay.osb.model.TicketWorkerSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteTicketWorkers(long[] userIds, long ticketEntryId,
		long primaryUserId) throws RemoteException {
		try {
			TicketWorkerServiceUtil.deleteTicketWorkers(userIds, ticketEntryId,
				primaryUserId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketWorkerSoap[] updateTicketWorkers(
		long[] addUserIds, int[] addRoles, long[] removeUserIds,
		long ticketEntryId, long[] sourceClassNameIds, long[] sourceClassPKs,
		long primaryUserId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.TicketWorker> returnValue = TicketWorkerServiceUtil.updateTicketWorkers(addUserIds,
					addRoles, removeUserIds, ticketEntryId, sourceClassNameIds,
					sourceClassPKs, primaryUserId);

			return com.liferay.osb.model.TicketWorkerSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TicketWorkerServiceSoap.class);
}