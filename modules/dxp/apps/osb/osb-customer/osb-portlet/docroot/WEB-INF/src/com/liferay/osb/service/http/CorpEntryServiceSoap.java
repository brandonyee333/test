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

import com.liferay.osb.service.CorpEntryServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;

import java.rmi.RemoteException;

import java.util.Locale;
import java.util.Map;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.osb.service.CorpEntryServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.CorpEntrySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.CorpEntry}, that is translated to a
 * {@link com.liferay.osb.model.CorpEntrySoap}. Methods that SOAP cannot
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
 * @see       CorpEntryServiceHttp
 * @see       com.liferay.osb.model.CorpEntrySoap
 * @see       com.liferay.osb.service.CorpEntryServiceUtil
 * @generated
 */
public class CorpEntryServiceSoap {
	public static void addCorpEntryUsers(long corpEntryId, long[] userIds)
		throws RemoteException {
		try {
			CorpEntryServiceUtil.addCorpEntryUsers(corpEntryId, userIds);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addUserCorpEntryRoles(long corpEntryId, long[] userIds,
		long roleId) throws RemoteException {
		try {
			CorpEntryServiceUtil.addUserCorpEntryRoles(corpEntryId, userIds,
				roleId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void addUserCorpEntryRoles(long corpEntryId,
		java.lang.String[] userUuids, java.lang.String roleName)
		throws RemoteException {
		try {
			CorpEntryServiceUtil.addUserCorpEntryRoles(corpEntryId, userUuids,
				roleName);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.CorpEntrySoap deleteCorpEntry(
		long corpEntryId) throws RemoteException {
		try {
			com.liferay.osb.model.CorpEntry returnValue = CorpEntryServiceUtil.deleteCorpEntry(corpEntryId);

			return com.liferay.osb.model.CorpEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteUserCorpEntryRoles(long corpEntryId,
		long[] userIds, long roleId) throws RemoteException {
		try {
			CorpEntryServiceUtil.deleteUserCorpEntryRoles(corpEntryId, userIds,
				roleId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.CorpEntrySoap getCorpEntry(
		long corpEntryId) throws RemoteException {
		try {
			com.liferay.osb.model.CorpEntry returnValue = CorpEntryServiceUtil.getCorpEntry(corpEntryId);

			return com.liferay.osb.model.CorpEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean hasUserCorpEntry(long userId, long corpEntryId)
		throws RemoteException {
		try {
			boolean returnValue = CorpEntryServiceUtil.hasUserCorpEntry(userId,
					corpEntryId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.CorpEntrySoap mergeCorpEntry(
		long corpEntryId, long mergeCorpEntryId, java.lang.String name,
		java.lang.String[] descriptionMapLanguageIds,
		java.lang.String[] descriptionMapValues, long logoId,
		java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId, java.lang.String contactEmailAddress,
		java.lang.String profileEmailAddress, java.lang.String phoneNumber,
		java.lang.String faxNumber, java.lang.String website,
		java.lang.String dossieraAccountKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws RemoteException {
		try {
			Map<Locale, String> descriptionMap = LocalizationUtil.getLocalizationMap(descriptionMapLanguageIds,
					descriptionMapValues);

			com.liferay.osb.model.CorpEntry returnValue = CorpEntryServiceUtil.mergeCorpEntry(corpEntryId,
					mergeCorpEntryId, name, descriptionMap, logoId, street1,
					street2, street3, city, zip, regionId, countryId,
					contactEmailAddress, profileEmailAddress, phoneNumber,
					faxNumber, website, dossieraAccountKey, serviceContext);

			return com.liferay.osb.model.CorpEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void unsetCorpEntryUsers(long corpEntryId, long[] userIds)
		throws RemoteException {
		try {
			CorpEntryServiceUtil.unsetCorpEntryUsers(corpEntryId, userIds);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.CorpEntrySoap updateStatus(
		long corpEntryId, int status, java.lang.String statusMessage)
		throws RemoteException {
		try {
			com.liferay.osb.model.CorpEntry returnValue = CorpEntryServiceUtil.updateStatus(corpEntryId,
					status, statusMessage);

			return com.liferay.osb.model.CorpEntrySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CorpEntryServiceSoap.class);
}