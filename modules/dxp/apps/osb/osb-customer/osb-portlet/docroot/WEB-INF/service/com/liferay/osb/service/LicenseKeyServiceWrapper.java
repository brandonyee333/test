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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LicenseKeyService}.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyService
 * @generated
 */
@ProviderType
public class LicenseKeyServiceWrapper implements LicenseKeyService,
	ServiceWrapper<LicenseKeyService> {
	public LicenseKeyServiceWrapper(LicenseKeyService licenseKeyService) {
		_licenseKeyService = licenseKeyService;
	}

	@Override
	public boolean isActive(java.lang.String serverId,
		java.lang.String productId, java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.isActive(serverId, productId, key);
	}

	@Override
	public com.liferay.osb.model.LicenseKey addDeveloperLicenseKey(
		long accountEntryId, java.lang.String productEntryRootName,
		int productMinorVersion)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.addDeveloperLicenseKey(accountEntryId,
			productEntryRootName, productMinorVersion);
	}

	@Override
	public com.liferay.osb.model.LicenseKey addLicenseKey(
		java.lang.String userUuid, java.lang.String assetReceiptLicenseUuid,
		java.lang.String licenseEntryType, java.lang.String productEntryName,
		java.lang.String productId, int productVersion, java.lang.String owner,
		long maxUsers, java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddresses, java.lang.String macAddresses,
		java.lang.String serverId, java.util.Date startDate,
		java.util.Date expirationDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.addLicenseKey(userUuid,
			assetReceiptLicenseUuid, licenseEntryType, productEntryName,
			productId, productVersion, owner, maxUsers, description, hostName,
			ipAddresses, macAddresses, serverId, startDate, expirationDate);
	}

	@Override
	public com.liferay.osb.model.LicenseKey addLicenseKey(long userId,
		long licenseKeySetId, java.lang.String name, long offeringEntryId,
		long licenseEntryId, long productEntryId, int productVersion,
		long clusterId, java.lang.String owner, int maxServers,
		int maxHttpSessions, java.lang.String description,
		java.lang.String[] hostNames, java.lang.String[] ipAddresses,
		java.lang.String[] macAddresses, java.lang.String[] serverIds,
		java.util.Date startDate, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.addLicenseKey(userId, licenseKeySetId, name,
			offeringEntryId, licenseEntryId, productEntryId, productVersion,
			clusterId, owner, maxServers, maxHttpSessions, description,
			hostNames, ipAddresses, macAddresses, serverIds, startDate,
			complimentary, active);
	}

	@Override
	public com.liferay.osb.model.LicenseKey getLicenseKey(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.getLicenseKey(uuid);
	}

	@Override
	public com.liferay.osb.model.LicenseKey getLicenseKey(long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.getLicenseKey(licenseKeyId);
	}

	@Override
	public com.liferay.osb.model.LicenseKey registerLicenseKey(
		java.lang.String orderEntryUuid, java.lang.String productEntryName,
		int liferayVersion, int maxServers, java.lang.String hostName,
		java.lang.String ipAddresses, java.lang.String macAddresses,
		java.lang.String serverId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.registerLicenseKey(orderEntryUuid,
			productEntryName, liferayVersion, maxServers, hostName,
			ipAddresses, macAddresses, serverId);
	}

	@Override
	public com.liferay.osb.model.LicenseKey renewLicenseKey(
		java.lang.String uuid, java.util.Date startDate,
		java.util.Date expirationDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.renewLicenseKey(uuid, startDate,
			expirationDate);
	}

	@Override
	public com.liferay.osb.model.LicenseKey renewLicenseKey(long licenseKeyId,
		java.util.Date startDate, int renewTime) throws java.lang.Exception {
		return _licenseKeyService.renewLicenseKey(licenseKeyId, startDate,
			renewTime);
	}

	@Override
	public com.liferay.osb.model.LicenseKey updateLicenseKey(long userId,
		long licenseKeyId, long licenseKeySetId, long offeringEntryId,
		java.lang.String name, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.updateLicenseKey(userId, licenseKeyId,
			licenseKeySetId, offeringEntryId, name, active);
	}

	@Override
	public int getAssetReceiptLicenseLicenseKeysCount(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.getAssetReceiptLicenseLicenseKeysCount(assetReceiptLicenseUuid,
			complimentary, active);
	}

	@Override
	public int getOfferingEntryGroupLicenseKeysCount(long[] offeringEntryIds,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.getOfferingEntryGroupLicenseKeysCount(offeringEntryIds,
			complimentary, active);
	}

	@Override
	public int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.getOfferingEntryLicenseKeysCount(offeringEntryId,
			complimentary, active);
	}

	@Override
	public int searchCount(java.lang.Long createUserId, int createDateGTDay,
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
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.searchCount(createUserId, createDateGTDay,
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

	@Override
	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.searchCount(keywords, params);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _licenseKeyService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public java.lang.String generateWeDeployLicenseKey(java.lang.String owner,
		java.util.Date startDate, long licenseLifetime)
		throws java.lang.Exception {
		return _licenseKeyService.generateWeDeployLicenseKey(owner, startDate,
			licenseLifetime);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _licenseKeyService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getAssetReceiptLicenseLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.getAssetReceiptLicenseLicenseKeys(assetReceiptLicenseUuid,
			complimentary, active);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeySetLicenseKeys(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.getLicenseKeySetLicenseKeys(licenseKeySetId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.getLicenseKeys(assetReceiptLicenseUuid,
			productId, serverId, active, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		java.lang.String productId, java.lang.String serverId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.getLicenseKeys(productId, serverId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		long userId, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.getLicenseKeys(userId, productId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeysByName(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.getLicenseKeysByName(productEntryName,
			serverId, active, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.getOfferingEntryGroupLicenseKeys(offeringEntryIds,
			complimentary, active, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> search(
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
		return _licenseKeyService.search(createUserId, createDateGTDay,
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
			params, andSearch, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyService.search(keywords, params, start, end, obc);
	}

	@Override
	public void updateLicenseKey(java.lang.String userUuid,
		java.lang.String uuid, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		_licenseKeyService.updateLicenseKey(userUuid, uuid, active);
	}

	@Override
	public void updateLicenseKey(long userId, long licenseKeyId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		_licenseKeyService.updateLicenseKey(userId, licenseKeyId, active);
	}

	@Override
	public void updateLicenseKeys(java.lang.String assetReceiptLicenseUuid,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		_licenseKeyService.updateLicenseKeys(assetReceiptLicenseUuid, active);
	}

	@Override
	public LicenseKeyService getWrappedService() {
		return _licenseKeyService;
	}

	@Override
	public void setWrappedService(LicenseKeyService licenseKeyService) {
		_licenseKeyService = licenseKeyService;
	}

	private LicenseKeyService _licenseKeyService;
}