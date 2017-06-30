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

import com.liferay.osb.service.DeveloperEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.osb.service.DeveloperEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.DeveloperEntrySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.DeveloperEntry}, that is translated to a
 * {@link com.liferay.osb.model.DeveloperEntrySoap}. Methods that SOAP cannot
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
 * @see       DeveloperEntryServiceHttp
 * @see       com.liferay.osb.model.DeveloperEntrySoap
 * @see       com.liferay.osb.service.DeveloperEntryServiceUtil
 * @generated
 */
public class DeveloperEntryServiceSoap {
	public static com.liferay.osb.model.DeveloperEntrySoap addUserDeveloperEntry(
		java.lang.String screenName, java.lang.String emailAddress,
		java.lang.String firstName, java.lang.String middleName,
		java.lang.String lastName, long contractEntryId, long countryId,
		java.lang.String phoneNumber,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			com.liferay.osb.model.DeveloperEntry returnValue = DeveloperEntryServiceUtil.addUserDeveloperEntry(screenName,
					emailAddress, firstName, middleName, lastName,
					contractEntryId, countryId, phoneNumber, serviceContext);

			return com.liferay.osb.model.DeveloperEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.DeveloperEntrySoap updateDeveloperEntry(
		long developerEntryId, java.lang.String dossieraAccountKey)
		throws RemoteException {
		try {
			com.liferay.osb.model.DeveloperEntry returnValue = DeveloperEntryServiceUtil.updateDeveloperEntry(developerEntryId,
					dossieraAccountKey);

			return com.liferay.osb.model.DeveloperEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.DeveloperEntrySoap updateDeveloperEntry(
		long developerEntryId, java.lang.String paymentFirstName,
		java.lang.String paymentLastName, java.lang.String paymentEmailAddress)
		throws RemoteException {
		try {
			com.liferay.osb.model.DeveloperEntry returnValue = DeveloperEntryServiceUtil.updateDeveloperEntry(developerEntryId,
					paymentFirstName, paymentLastName, paymentEmailAddress);

			return com.liferay.osb.model.DeveloperEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.DeveloperEntrySoap updateDeveloperEntryGoogleAnalyticsKey(
		long developerEntryId, java.lang.String googleAnalyticsKey)
		throws RemoteException {
		try {
			com.liferay.osb.model.DeveloperEntry returnValue = DeveloperEntryServiceUtil.updateDeveloperEntryGoogleAnalyticsKey(developerEntryId,
					googleAnalyticsKey);

			return com.liferay.osb.model.DeveloperEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.DeveloperEntrySoap updateStatus(
		long developerEntryId, int status, java.lang.String statusMessage)
		throws RemoteException {
		try {
			com.liferay.osb.model.DeveloperEntry returnValue = DeveloperEntryServiceUtil.updateStatus(developerEntryId,
					status, statusMessage);

			return com.liferay.osb.model.DeveloperEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.DeveloperEntrySoap updateSubscription(
		long developerEntryId, java.util.Date subscriptionExpirationDate,
		int subscriptionStatus) throws RemoteException {
		try {
			com.liferay.osb.model.DeveloperEntry returnValue = DeveloperEntryServiceUtil.updateSubscription(developerEntryId,
					subscriptionExpirationDate, subscriptionStatus);

			return com.liferay.osb.model.DeveloperEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DeveloperEntryServiceSoap.class);
}