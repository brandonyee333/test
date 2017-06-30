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

import com.liferay.osb.service.AccountCallServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.osb.service.AccountCallServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.AccountCallSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.AccountCall}, that is translated to a
 * {@link com.liferay.osb.model.AccountCallSoap}. Methods that SOAP cannot
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
 * @see       AccountCallServiceHttp
 * @see       com.liferay.osb.model.AccountCallSoap
 * @see       com.liferay.osb.service.AccountCallServiceUtil
 * @generated
 */
public class AccountCallServiceSoap {
	public static com.liferay.osb.model.AccountCallSoap deleteAccountCall(
		long accountCallId) throws RemoteException {
		try {
			com.liferay.osb.model.AccountCall returnValue = AccountCallServiceUtil.deleteAccountCall(accountCallId);

			return com.liferay.osb.model.AccountCallSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.AccountCallSoap updateAccountCall(
		long accountCallId, long accountEntryId, int type, int callDateMonth,
		int callDateDay, int callDateYear, int callDateHour,
		int callDateMinute, long callLength, java.lang.String summary,
		java.lang.String clientsPresent, java.lang.String notes,
		java.lang.String actionItems) throws RemoteException {
		try {
			com.liferay.osb.model.AccountCall returnValue = AccountCallServiceUtil.updateAccountCall(accountCallId,
					accountEntryId, type, callDateMonth, callDateDay,
					callDateYear, callDateHour, callDateMinute, callLength,
					summary, clientsPresent, notes, actionItems);

			return com.liferay.osb.model.AccountCallSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AccountCallServiceSoap.class);
}