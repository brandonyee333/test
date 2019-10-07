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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for LicenseKey. This utility wraps
 * {@link com.liferay.osb.service.impl.LicenseKeyServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyService
 * @see com.liferay.osb.service.base.LicenseKeyServiceBaseImpl
 * @see com.liferay.osb.service.impl.LicenseKeyServiceImpl
 * @generated
 */
@ProviderType
public class LicenseKeyServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.LicenseKeyServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean isActive(java.lang.String serverId,
		java.lang.String productId, java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().isActive(serverId, productId, key);
	}

	public static com.liferay.osb.model.LicenseKey addDeveloperLicenseKey(
		long accountEntryId, java.lang.String productEntryRootName,
		int productMinorVersion)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addDeveloperLicenseKey(accountEntryId,
			productEntryRootName, productMinorVersion);
	}

	public static com.liferay.osb.model.LicenseKey addLicenseKey(
		java.lang.String userUuid, java.lang.String assetReceiptLicenseUuid,
		java.lang.String licenseEntryType, java.lang.String productEntryName,
		java.lang.String productId, int productVersion, java.lang.String owner,
		long maxUsers, java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddresses, java.lang.String macAddresses,
		java.lang.String serverId, java.util.Date startDate,
		java.util.Date expirationDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLicenseKey(userUuid, assetReceiptLicenseUuid,
			licenseEntryType, productEntryName, productId, productVersion,
			owner, maxUsers, description, hostName, ipAddresses, macAddresses,
			serverId, startDate, expirationDate);
	}

	public static com.liferay.osb.model.LicenseKey addLicenseKey(long userId,
		long licenseKeySetId, java.lang.String name, long offeringEntryId,
		long licenseEntryId, long productEntryId, int productVersion,
		long clusterId, java.lang.String owner, int maxServers,
		int maxHttpSessions, java.lang.String description,
		java.lang.String[] hostNames, java.lang.String[] ipAddresses,
		java.lang.String[] macAddresses, java.lang.String[] serverIds,
		java.util.Date startDate, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLicenseKey(userId, licenseKeySetId, name,
			offeringEntryId, licenseEntryId, productEntryId, productVersion,
			clusterId, owner, maxServers, maxHttpSessions, description,
			hostNames, ipAddresses, macAddresses, serverIds, startDate,
			complimentary, active);
	}

	public static com.liferay.osb.model.LicenseKey getLicenseKey(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLicenseKey(uuid);
	}

	public static com.liferay.osb.model.LicenseKey getLicenseKey(
		long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLicenseKey(licenseKeyId);
	}

	public static com.liferay.osb.model.LicenseKey registerLicenseKey(
		java.lang.String orderEntryUuid, java.lang.String productEntryName,
		int liferayVersion, int maxServers, java.lang.String hostName,
		java.lang.String ipAddresses, java.lang.String macAddresses,
		java.lang.String serverId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .registerLicenseKey(orderEntryUuid, productEntryName,
			liferayVersion, maxServers, hostName, ipAddresses, macAddresses,
			serverId);
	}

	public static com.liferay.osb.model.LicenseKey renewLicenseKey(
		java.lang.String uuid, java.util.Date startDate,
		java.util.Date expirationDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().renewLicenseKey(uuid, startDate, expirationDate);
	}

	public static com.liferay.osb.model.LicenseKey renewLicenseKey(
		long licenseKeyId, java.util.Date startDate, int renewTime)
		throws java.lang.Exception {
		return getService().renewLicenseKey(licenseKeyId, startDate, renewTime);
	}

	public static com.liferay.osb.model.LicenseKey updateLicenseKey(
		long userId, long licenseKeyId, long licenseKeySetId,
		long offeringEntryId, java.lang.String name, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateLicenseKey(userId, licenseKeyId, licenseKeySetId,
			offeringEntryId, name, active);
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getAssetReceiptLicenseLicenseKeysCount(assetReceiptLicenseUuid,
			complimentary, active);
	}

	public static int getOfferingEntryGroupLicenseKeysCount(
		long[] offeringEntryIds, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getOfferingEntryGroupLicenseKeysCount(offeringEntryIds,
			complimentary, active);
	}

	public static int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getOfferingEntryLicenseKeysCount(offeringEntryId,
			complimentary, active);
	}

	public static int searchCount(java.lang.Long createUserId,
		int createDateGTDay, int createDateGTMonth, int createDateGTYear,
		int createDateLTDay, int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.String accountEntryName, java.lang.String licenseKeySetName,
		int startDateGTDay, int startDateGTMonth, int startDateGTYear,
		int startDateLTDay, int startDateLTMonth, int startDateLTYear,
		long[] licenseEntryIds, long[] productEntryIds,
		java.lang.String productEntryName, java.lang.String productId,
		int[] productVersions, java.lang.String owner,
		java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddress, java.lang.String macAddress,
		java.lang.String serverId, java.lang.String key,
		int expirationDateGTDay, int expirationDateGTMonth,
		int expirationDateGTYear, int expirationDateLTDay,
		int expirationDateLTMonth, int expirationDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCount(createUserId, createDateGTDay,
			createDateGTMonth, createDateGTYear, createDateLTDay,
			createDateLTMonth, createDateLTYear, modifiedUserId,
			modifiedDateGTDay, modifiedDateGTMonth, modifiedDateGTYear,
			modifiedDateLTDay, modifiedDateLTMonth, modifiedDateLTYear,
			accountEntryName, licenseKeySetName, startDateGTDay,
			startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, licenseEntryIds,
			productEntryIds, productEntryName, productId, productVersions,
			owner, description, hostName, ipAddress, macAddress, serverId, key,
			expirationDateGTDay, expirationDateGTMonth, expirationDateGTYear,
			expirationDateLTDay, expirationDateLTMonth, expirationDateLTYear,
			params, andSearch);
	}

	public static int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().searchCount(keywords, params);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static java.lang.String generateCommerceLicenseKey(
		java.lang.String owner, java.util.Date startDate, long licenseLifetime)
		throws java.lang.Exception {
		return getService()
				   .generateCommerceLicenseKey(owner, startDate, licenseLifetime);
	}

	public static java.lang.String generateWeDeployLicenseKey(
		java.lang.String owner, java.util.Date startDate, long licenseLifetime)
		throws java.lang.Exception {
		return getService()
				   .generateWeDeployLicenseKey(owner, startDate, licenseLifetime);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getAssetReceiptLicenseLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getAssetReceiptLicenseLicenseKeys(assetReceiptLicenseUuid,
			complimentary, active);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeySetLicenseKeys(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLicenseKeySetLicenseKeys(licenseKeySetId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLicenseKeys(assetReceiptLicenseUuid, productId,
			serverId, active, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		java.lang.String productId, java.lang.String serverId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLicenseKeys(productId, serverId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		long userId, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLicenseKeys(userId, productId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeysByName(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getLicenseKeysByName(productEntryName, serverId, active,
			start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getOfferingEntryGroupLicenseKeys(offeringEntryIds,
			complimentary, active, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> search(
		java.lang.Long createUserId, int createDateGTDay,
		int createDateGTMonth, int createDateGTYear, int createDateLTDay,
		int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.String accountEntryName, java.lang.String licenseKeySetName,
		int startDateGTDay, int startDateGTMonth, int startDateGTYear,
		int startDateLTDay, int startDateLTMonth, int startDateLTYear,
		long[] licenseEntryIds, long[] productEntryIds,
		java.lang.String productEntryName, java.lang.String productId,
		int[] productVersions, java.lang.String owner,
		java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddress, java.lang.String macAddress,
		java.lang.String serverId, java.lang.String key,
		int expirationDateGTDay, int expirationDateGTMonth,
		int expirationDateGTYear, int expirationDateLTDay,
		int expirationDateLTMonth, int expirationDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .search(createUserId, createDateGTDay, createDateGTMonth,
			createDateGTYear, createDateLTDay, createDateLTMonth,
			createDateLTYear, modifiedUserId, modifiedDateGTDay,
			modifiedDateGTMonth, modifiedDateGTYear, modifiedDateLTDay,
			modifiedDateLTMonth, modifiedDateLTYear, accountEntryName,
			licenseKeySetName, startDateGTDay, startDateGTMonth,
			startDateGTYear, startDateLTDay, startDateLTMonth, startDateLTYear,
			licenseEntryIds, productEntryIds, productEntryName, productId,
			productVersions, owner, description, hostName, ipAddress,
			macAddress, serverId, key, expirationDateGTDay,
			expirationDateGTMonth, expirationDateGTYear, expirationDateLTDay,
			expirationDateLTMonth, expirationDateLTYear, params, andSearch,
			start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().search(keywords, params, start, end, obc);
	}

	public static void updateLicenseKey(java.lang.String userUuid,
		java.lang.String uuid, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateLicenseKey(userUuid, uuid, active);
	}

	public static void updateLicenseKey(long userId, long licenseKeyId,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateLicenseKey(userId, licenseKeyId, active);
	}

	public static void updateLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateLicenseKeys(assetReceiptLicenseUuid, active);
	}

	public static void clearService() {
		_service = null;
	}

	public static LicenseKeyService getService() {
		if (_service == null) {
			InvokableService invokableService = (InvokableService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					LicenseKeyService.class.getName());

			if (invokableService instanceof LicenseKeyService) {
				_service = (LicenseKeyService)invokableService;
			}
			else {
				_service = new LicenseKeyServiceClp(invokableService);
			}

			ReferenceRegistry.registerReference(LicenseKeyServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static LicenseKeyService _service;
}