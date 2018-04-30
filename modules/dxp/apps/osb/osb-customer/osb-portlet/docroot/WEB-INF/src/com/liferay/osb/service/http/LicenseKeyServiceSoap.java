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
		java.lang.String userUuid, java.lang.String assetReceiptLicenseUuid,
		java.lang.String licenseEntryType, java.lang.String productEntryName,
		java.lang.String productId, int productVersion, java.lang.String owner,
		long maxUsers, java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddresses, java.lang.String macAddresses,
		java.lang.String serverId, java.util.Date startDate,
		java.util.Date expirationDate) throws RemoteException {
		try {
			com.liferay.osb.model.LicenseKey returnValue = LicenseKeyServiceUtil.addLicenseKey(userUuid,
					assetReceiptLicenseUuid, licenseEntryType,
					productEntryName, productId, productVersion, owner,
					maxUsers, description, hostName, ipAddresses, macAddresses,
					serverId, startDate, expirationDate);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.LicenseKeySoap[] getAssetReceiptLicenseLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active) throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.LicenseKey> returnValue = LicenseKeyServiceUtil.getAssetReceiptLicenseLicenseKeys(assetReceiptLicenseUuid,
					complimentary, active);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active) throws RemoteException {
		try {
			int returnValue = LicenseKeyServiceUtil.getAssetReceiptLicenseLicenseKeysCount(assetReceiptLicenseUuid,
					complimentary, active);

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

	public static com.liferay.osb.model.LicenseKeySoap getLicenseKey(
		java.lang.String uuid) throws RemoteException {
		try {
			com.liferay.osb.model.LicenseKey returnValue = LicenseKeyServiceUtil.getLicenseKey(uuid);

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

	public static com.liferay.osb.model.LicenseKeySoap[] getLicenseKeys(
		java.lang.String productId, java.lang.String serverId)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.LicenseKey> returnValue = LicenseKeyServiceUtil.getLicenseKeys(productId,
					serverId);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.LicenseKeySoap[] getLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.LicenseKey> returnValue = LicenseKeyServiceUtil.getLicenseKeys(assetReceiptLicenseUuid,
					productId, serverId, active, start, end, obc);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.LicenseKeySoap[] getLicenseKeysByName(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws RemoteException {
		try {
			java.util.List<com.liferay.osb.model.LicenseKey> returnValue = LicenseKeyServiceUtil.getLicenseKeysByName(productEntryName,
					serverId, active, start, end, obc);

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

	public static int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		boolean complimentary, boolean active) throws RemoteException {
		try {
			int returnValue = LicenseKeyServiceUtil.getOfferingEntryLicenseKeysCount(offeringEntryId,
					complimentary, active);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean isActive(java.lang.String serverId,
		java.lang.String productId, java.lang.String key)
		throws RemoteException {
		try {
			boolean returnValue = LicenseKeyServiceUtil.isActive(serverId,
					productId, key);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.osb.model.LicenseKeySoap registerLicenseKey(
		java.lang.String orderEntryUuid, java.lang.String productEntryName,
		int liferayVersion, int maxServers, java.lang.String hostName,
		java.lang.String ipAddresses, java.lang.String macAddresses,
		java.lang.String serverId) throws RemoteException {
		try {
			com.liferay.osb.model.LicenseKey returnValue = LicenseKeyServiceUtil.registerLicenseKey(orderEntryUuid,
					productEntryName, liferayVersion, maxServers, hostName,
					ipAddresses, macAddresses, serverId);

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

	public static com.liferay.osb.model.LicenseKeySoap renewLicenseKey(
		java.lang.String uuid, java.util.Date startDate,
		java.util.Date expirationDate) throws RemoteException {
		try {
			com.liferay.osb.model.LicenseKey returnValue = LicenseKeyServiceUtil.renewLicenseKey(uuid,
					startDate, expirationDate);

			return com.liferay.osb.model.LicenseKeySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateLicenseKey(long userId, long licenseKeyId,
		boolean active) throws RemoteException {
		try {
			LicenseKeyServiceUtil.updateLicenseKey(userId, licenseKeyId, active);
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

	public static void updateLicenseKey(java.lang.String userUuid,
		java.lang.String uuid, boolean active) throws RemoteException {
		try {
			LicenseKeyServiceUtil.updateLicenseKey(userUuid, uuid, active);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, boolean active)
		throws RemoteException {
		try {
			LicenseKeyServiceUtil.updateLicenseKeys(assetReceiptLicenseUuid,
				active);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(LicenseKeyServiceSoap.class);
}