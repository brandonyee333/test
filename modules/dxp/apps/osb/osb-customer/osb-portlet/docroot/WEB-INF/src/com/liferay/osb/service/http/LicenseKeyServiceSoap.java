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

import com.liferay.osb.service.LicenseKeyServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * {@link LicenseKeyServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.osb.model.LicenseKeySoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.osb.model.LicenseKey}, that is translated to a
 * {@link com.liferay.osb.model.LicenseKeySoap}. Methods that SOAP cannot
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
 * @see LicenseKeyServiceHttp
 * @see com.liferay.osb.model.LicenseKeySoap
 * @see LicenseKeyServiceUtil
 * @generated
 */
@ProviderType
public class LicenseKeyServiceSoap {
	/**
	* Adds a license key.
	*
	* <p>
	* Only use for adding license keys for 6.1.10 and greater.
	* </p>
	*
	* @param licenseKeySetId the primary key of the license key set for
	grouping keys together. Use 0 if you are not adding to an
	existing set.
	* @param offeringEntryId the primary key of the offering entry
	* @param licenseEntryId the primary key of the license entry
	* @param productVersion the listTypeId of product entry's portalAllVersion
	list type. Values can also be found in ProductEntryConstants.
	* @param owner the license key's owner, usually the account entry's name
	* @param maxHttpSessions the max number of IP's that can access the
	portal. This is only used for developer type license keys.
	* @param description the license key's description
	* @param hostName the server's hostname that this license key is valid
	for. Leave blank for developer, enterprise, and OEM type license
	keys.
	* @param ipAddresses a comma delimited list of the server's IP addresses
	that this license key is valid for. Leave blank for developer,
	enterprise, and OEM type license keys.
	* @param macAddresses a comma delimited list of the server's MAC addresses
	that this license key is valid for. Leave blank for developer,
	enterprise, and OEM type license keys.
	* @param serverId the server ID's received from the server that this
	license key is valid for. The server ID is a encrypted string
	which includes a random string, and the server's MAC addresses,
	IP addresses, and hostname. Leave this blank if generating an
	offline license key.
	* @param startDateMonth the license key's starting date's month
	* @param startDateDay the license key's starting date's day
	* @param startDateYear the license key's starting date's year
	* @throws PortalException if the offering entry is closed, or if there are
	no more available license keys left in the offering entry
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKeySoap addLicenseKey(
		long licenseKeySetId, long offeringEntryId, long licenseEntryId,
		int productVersion, java.lang.String owner, int maxHttpSessions,
		java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddresses, java.lang.String macAddresses,
		java.lang.String serverId, int startDateMonth, int startDateDay,
		int startDateYear) throws RemoteException {
		try {
			com.liferay.osb.model.LicenseKey returnValue = LicenseKeyServiceUtil.addLicenseKey(licenseKeySetId,
					offeringEntryId, licenseEntryId, productVersion, owner,
					maxHttpSessions, description, hostName, ipAddresses,
					macAddresses, serverId, startDateMonth, startDateDay,
					startDateYear);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.LicenseKeySoap addLicenseKey(
		long userId, long licenseKeySetId, java.lang.String name,
		long offeringEntryId, long licenseEntryId, long productEntryId,
		int productVersion, long clusterId, java.lang.String owner,
		int maxServers, int maxHttpSessions, java.lang.String description,
		java.lang.String[] hostNames, java.lang.String[] ipAddresses,
		java.lang.String[] macAddresses, java.lang.String[] serverIds,
		int startDateMonth, int startDateDay, int startDateYear,
		boolean complimentary, boolean active) throws RemoteException {
		try {
			com.liferay.osb.model.LicenseKey returnValue = LicenseKeyServiceUtil.addLicenseKey(userId,
					licenseKeySetId, name, offeringEntryId, licenseEntryId,
					productEntryId, productVersion, clusterId, owner,
					maxServers, maxHttpSessions, description, hostNames,
					ipAddresses, macAddresses, serverIds, startDateMonth,
					startDateDay, startDateYear, complimentary, active);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.LicenseKeySoap addLicenseKey(
		long userId, long assetReceiptLicenseId, java.lang.String owner,
		java.lang.String description, java.lang.String[] hostNames,
		java.lang.String[] ipAddresses, java.lang.String[] macAddresses,
		java.lang.String[] serverIds, int startDateMonth, int startDateDay,
		int startDateYear) throws RemoteException {
		try {
			com.liferay.osb.model.LicenseKey returnValue = LicenseKeyServiceUtil.addLicenseKey(userId,
					assetReceiptLicenseId, owner, description, hostNames,
					ipAddresses, macAddresses, serverIds, startDateMonth,
					startDateDay, startDateYear);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static java.lang.String exportToXML(long licenseKeyId)
		throws RemoteException {
		try {
			java.lang.String returnValue = LicenseKeyServiceUtil.exportToXML(licenseKeyId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.LicenseKeySoap getLicenseKey(
		long licenseKeyId) throws RemoteException {
		try {
			com.liferay.osb.model.LicenseKey returnValue = LicenseKeyServiceUtil.getLicenseKey(licenseKeyId);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.LicenseKeySoap[] getLicenseKeys(
		long userId, java.lang.String productId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.LicenseKey> returnValue = LicenseKeyServiceUtil.getLicenseKeys(userId,
					productId);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.LicenseKeySoap[] getLicenseKeySetLicenseKeys(
		long licenseKeySetId) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.LicenseKey> returnValue = LicenseKeyServiceUtil.getLicenseKeySetLicenseKeys(licenseKeySetId);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.LicenseKeySoap[] getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.LicenseKey> returnValue = LicenseKeyServiceUtil.getOfferingEntryGroupLicenseKeys(offeringEntryIds,
					complimentary, active, start, end, obc);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getOfferingEntryGroupLicenseKeysCount(
		long[] offeringEntryIds, boolean complimentary, boolean active)
		throws RemoteException {
		try {
			int returnValue = LicenseKeyServiceUtil.getOfferingEntryGroupLicenseKeysCount(offeringEntryIds,
					complimentary, active);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean isActive(long corpProjectId, java.lang.String key)
		throws RemoteException {
		try {
			boolean returnValue = LicenseKeyServiceUtil.isActive(corpProjectId,
					key);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.LicenseKeySoap renewLicenseKey(
		long licenseKeyId) throws RemoteException {
		try {
			com.liferay.osb.model.LicenseKey returnValue = LicenseKeyServiceUtil.renewLicenseKey(licenseKeyId);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.LicenseKeySoap renewLicenseKey(
		long licenseKeyId, java.util.Date startDate, int renewTime)
		throws RemoteException {
		try {
			com.liferay.osb.model.LicenseKey returnValue = LicenseKeyServiceUtil.renewLicenseKey(licenseKeyId,
					startDate, renewTime);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateLicenseKey(long licenseKeyId, boolean active)
		throws RemoteException {
		try {
			LicenseKeyServiceUtil.updateLicenseKey(licenseKeyId, active);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateLicenseKey(long userId, long licenseKeyId,
		long assetReceiptLicenseId, boolean active) throws RemoteException {
		try {
			LicenseKeyServiceUtil.updateLicenseKey(userId, licenseKeyId,
				assetReceiptLicenseId, active);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateLicenseKey(long userId, long licenseKeyId,
		long licenseKeySetId, long offeringEntryId, java.lang.String name,
		boolean active) throws RemoteException {
		try {
			LicenseKeyServiceUtil.updateLicenseKey(userId, licenseKeyId,
				licenseKeySetId, offeringEntryId, name, active);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LicenseKeyServiceSoap.class);
}