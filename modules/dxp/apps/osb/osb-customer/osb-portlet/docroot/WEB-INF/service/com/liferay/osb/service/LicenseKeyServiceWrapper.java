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
	public boolean isActive(long corpProjectId, java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeyService.isActive(corpProjectId, key);
	}

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
	@Override
	public com.liferay.osb.model.LicenseKey addLicenseKey(
		long licenseKeySetId, long offeringEntryId, long licenseEntryId,
		int productVersion, java.lang.String owner, int maxHttpSessions,
		java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddresses, java.lang.String macAddresses,
		java.lang.String serverId, int startDateMonth, int startDateDay,
		int startDateYear)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeyService.addLicenseKey(licenseKeySetId,
			offeringEntryId, licenseEntryId, productVersion, owner,
			maxHttpSessions, description, hostName, ipAddresses, macAddresses,
			serverId, startDateMonth, startDateDay, startDateYear);
	}

	@Override
	public com.liferay.osb.model.LicenseKey addLicenseKey(long userId,
		long assetReceiptLicenseId, java.lang.String owner,
		java.lang.String description, java.lang.String[] hostNames,
		java.lang.String[] ipAddresses, java.lang.String[] macAddresses,
		java.lang.String[] serverIds, int startDateMonth, int startDateDay,
		int startDateYear)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeyService.addLicenseKey(userId, assetReceiptLicenseId,
			owner, description, hostNames, ipAddresses, macAddresses,
			serverIds, startDateMonth, startDateDay, startDateYear);
	}

	@Override
	public com.liferay.osb.model.LicenseKey addLicenseKey(long userId,
		long licenseKeySetId, java.lang.String name, long offeringEntryId,
		long licenseEntryId, long productEntryId, int productVersion,
		long clusterId, java.lang.String owner, int maxServers,
		int maxHttpSessions, java.lang.String description,
		java.lang.String[] hostNames, java.lang.String[] ipAddresses,
		java.lang.String[] macAddresses, java.lang.String[] serverIds,
		int startDateMonth, int startDateDay, int startDateYear,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeyService.addLicenseKey(userId, licenseKeySetId, name,
			offeringEntryId, licenseEntryId, productEntryId, productVersion,
			clusterId, owner, maxServers, maxHttpSessions, description,
			hostNames, ipAddresses, macAddresses, serverIds, startDateMonth,
			startDateDay, startDateYear, complimentary, active);
	}

	@Override
	public com.liferay.osb.model.LicenseKey getLicenseKey(long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeyService.getLicenseKey(licenseKeyId);
	}

	@Override
	public com.liferay.osb.model.LicenseKey renewLicenseKey(long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeyService.renewLicenseKey(licenseKeyId);
	}

	@Override
	public com.liferay.osb.model.LicenseKey renewLicenseKey(long licenseKeyId,
		java.util.Date startDate, int renewTime) throws java.lang.Exception {
		return _licenseKeyService.renewLicenseKey(licenseKeyId, startDate,
			renewTime);
	}

	@Override
	public int getOfferingEntryGroupLicenseKeysCount(long[] offeringEntryIds,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeyService.getOfferingEntryGroupLicenseKeysCount(offeringEntryIds,
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
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
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
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeyService.searchCount(keywords, params);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _licenseKeyService.invokeMethod(name, parameterTypes, arguments);
	}

	@Override
	public java.lang.String exportToXML(long licenseKeyId)
		throws java.lang.Exception {
		return _licenseKeyService.exportToXML(licenseKeyId);
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
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeySetLicenseKeys(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeyService.getLicenseKeySetLicenseKeys(licenseKeySetId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		long userId, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeyService.getLicenseKeys(userId, productId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
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
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
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
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _licenseKeyService.search(keywords, params, start, end, obc);
	}

	@Override
	public void updateLicenseKey(long licenseKeyId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_licenseKeyService.updateLicenseKey(licenseKeyId, active);
	}

	@Override
	public void updateLicenseKey(long userId, long licenseKeyId,
		long assetReceiptLicenseId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_licenseKeyService.updateLicenseKey(userId, licenseKeyId,
			assetReceiptLicenseId, active);
	}

	@Override
	public void updateLicenseKey(long userId, long licenseKeyId,
		long licenseKeySetId, long offeringEntryId, java.lang.String name,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_licenseKeyService.updateLicenseKey(userId, licenseKeyId,
			licenseKeySetId, offeringEntryId, name, active);
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