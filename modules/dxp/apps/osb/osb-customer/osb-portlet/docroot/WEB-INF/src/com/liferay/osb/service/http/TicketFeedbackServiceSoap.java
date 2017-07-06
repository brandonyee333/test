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

import com.liferay.osb.service.TicketFeedbackServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link TicketFeedbackServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.TicketFeedbackSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.TicketFeedback}, that is translated to a
 * {@link com.liferay.osb.model.TicketFeedbackSoap}. Methods that SOAP cannot
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
 * @see TicketFeedbackServiceHttp
 * @see com.liferay.osb.model.TicketFeedbackSoap
 * @see TicketFeedbackServiceUtil
 * @generated
 */
@ProviderType
public class TicketFeedbackServiceSoap {
	public static com.liferay.osb.model.TicketFeedbackSoap addTicketFeedback(
		long ticketEntryId, int subject, int satisfied)
		throws RemoteException {
		try {
			com.liferay.osb.model.TicketFeedback returnValue = TicketFeedbackServiceUtil.addTicketFeedback(ticketEntryId,
					subject, satisfied);

			return com.liferay.osb.model.TicketFeedbackSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketFeedbackSoap fetchFirstOpenTicketFeedback(
		long userId, long ticketEntryId, int subject) throws RemoteException {
		try {
			com.liferay.osb.model.TicketFeedback returnValue = TicketFeedbackServiceUtil.fetchFirstOpenTicketFeedback(userId,
					ticketEntryId, subject);

			return com.liferay.osb.model.TicketFeedbackSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketFeedbackSoap fetchFirstTicketFeedback(
		long ticketEntryId, int subject) throws RemoteException {
		try {
			com.liferay.osb.model.TicketFeedback returnValue = TicketFeedbackServiceUtil.fetchFirstTicketFeedback(ticketEntryId,
					subject);

			return com.liferay.osb.model.TicketFeedbackSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketFeedbackSoap getTicketFeedback(
		long ticketFeedbackId) throws RemoteException {
		try {
			com.liferay.osb.model.TicketFeedback returnValue = TicketFeedbackServiceUtil.getTicketFeedback(ticketFeedbackId);

			return com.liferay.osb.model.TicketFeedbackSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketFeedbackSoap[] getTicketFeedbacks(
		long ticketEntryId, int subject) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.TicketFeedback> returnValue = TicketFeedbackServiceUtil.getTicketFeedbacks(ticketEntryId,
					subject);

			return com.liferay.osb.model.TicketFeedbackSoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.TicketFeedbackSoap updateTicketFeedback(
		long ticketFeedbackId, int satisfied, int answer1, int answer2,
		int answer3, int rating1, int rating2, int rating3, int rating4,
		java.lang.String comments) throws RemoteException {
		try {
			com.liferay.osb.model.TicketFeedback returnValue = TicketFeedbackServiceUtil.updateTicketFeedback(ticketFeedbackId,
					satisfied, answer1, answer2, answer3, rating1, rating2,
					rating3, rating4, comments);

			return com.liferay.osb.model.TicketFeedbackSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TicketFeedbackServiceSoap.class);
}