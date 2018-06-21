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
 * Provides a wrapper for {@link LicenseKeyLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyLocalService
 * @generated
 */
@ProviderType
public class LicenseKeyLocalServiceWrapper implements LicenseKeyLocalService,
	ServiceWrapper<LicenseKeyLocalService> {
	public LicenseKeyLocalServiceWrapper(
		LicenseKeyLocalService licenseKeyLocalService) {
		_licenseKeyLocalService = licenseKeyLocalService;
	}

	@Override
	public com.liferay.osb.model.LicenseKey addDeveloperLicenseKey(
		long userId, long accountEntryId,
		java.lang.String productEntryRootName, int productMinorVersion)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyLocalService.addDeveloperLicenseKey(userId,
			accountEntryId, productEntryRootName, productMinorVersion);
	}

	/**
	* Adds the license key to the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKey the license key
	* @return the license key that was added
	*/
	@Override
	public com.liferay.osb.model.LicenseKey addLicenseKey(
		com.liferay.osb.model.LicenseKey licenseKey) {
		return _licenseKeyLocalService.addLicenseKey(licenseKey);
	}

	@Override
	public com.liferay.osb.model.LicenseKey addLicenseKey(long userId,
		com.liferay.osb.model.LicenseKeySet licenseKeySet,
		java.lang.String name,
		com.liferay.osb.model.OfferingEntry offeringEntry,
		com.liferay.osb.model.LicenseEntry licenseEntry,
		com.liferay.osb.model.ProductEntry productEntry, int productVersion,
		long clusterId, java.lang.String owner, int maxServers,
		int maxHttpSessions, java.lang.String description,
		java.lang.String[] hostNames, java.lang.String[] ipAddresses,
		java.lang.String[] macAddresses, java.lang.String[] serverIds,
		java.util.Date startDate, java.util.Date expirationDate,
		java.lang.String additionalInfo, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyLocalService.addLicenseKey(userId, licenseKeySet,
			name, offeringEntry, licenseEntry, productEntry, productVersion,
			clusterId, owner, maxServers, maxHttpSessions, description,
			hostNames, ipAddresses, macAddresses, serverIds, startDate,
			expirationDate, additionalInfo, complimentary, active);
	}

	@Override
	public com.liferay.osb.model.LicenseKey addLicenseKey(long userId,
		java.lang.String assetReceiptLicenseUuid,
		java.lang.String licenseEntryType, java.lang.String productEntryName,
		java.lang.String productId, int productVersion, java.lang.String owner,
		long maxUsers, java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddresses, java.lang.String macAddresses,
		java.lang.String serverId, java.util.Date startDate,
		java.util.Date expirationDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyLocalService.addLicenseKey(userId,
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
		return _licenseKeyLocalService.addLicenseKey(userId, licenseKeySetId,
			name, offeringEntryId, licenseEntryId, productEntryId,
			productVersion, clusterId, owner, maxServers, maxHttpSessions,
			description, hostNames, ipAddresses, macAddresses, serverIds,
			startDate, complimentary, active);
	}

	/**
	* Creates a new license key with the primary key. Does not add the license key to the database.
	*
	* @param licenseKeyId the primary key for the new license key
	* @return the new license key
	*/
	@Override
	public com.liferay.osb.model.LicenseKey createLicenseKey(long licenseKeyId) {
		return _licenseKeyLocalService.createLicenseKey(licenseKeyId);
	}

	/**
	* Deletes the license key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKey the license key
	* @return the license key that was removed
	*/
	@Override
	public com.liferay.osb.model.LicenseKey deleteLicenseKey(
		com.liferay.osb.model.LicenseKey licenseKey) {
		return _licenseKeyLocalService.deleteLicenseKey(licenseKey);
	}

	/**
	* Deletes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key that was removed
	* @throws PortalException if a license key with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.LicenseKey deleteLicenseKey(long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyLocalService.deleteLicenseKey(licenseKeyId);
	}

	@Override
	public com.liferay.osb.model.LicenseKey fetchLicenseKey(long licenseKeyId) {
		return _licenseKeyLocalService.fetchLicenseKey(licenseKeyId);
	}

	@Override
	public com.liferay.osb.model.LicenseKey getFirstLicenseKey(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyLocalService.getFirstLicenseKey(accountEntryId, obc);
	}

	/**
	* Returns the license key with the primary key.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key
	* @throws PortalException if a license key with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.LicenseKey getLicenseKey(long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyLocalService.getLicenseKey(licenseKeyId);
	}

	@Override
	public com.liferay.osb.model.LicenseKey getLicenseKeyByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyLocalService.getLicenseKeyByUuid(uuid);
	}

	@Override
	public com.liferay.osb.model.LicenseKey renewLicenseKey(long userId,
		long licenseKeyId, java.util.Date startDate, int renewTime)
		throws java.lang.Exception {
		return _licenseKeyLocalService.renewLicenseKey(userId, licenseKeyId,
			startDate, renewTime);
	}

	@Override
	public com.liferay.osb.model.LicenseKey renewLicenseKey(long userId,
		long licenseKeyId, java.util.Date startDate,
		java.util.Date expirationDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyLocalService.renewLicenseKey(userId, licenseKeyId,
			startDate, expirationDate);
	}

	@Override
	public com.liferay.osb.model.LicenseKey renewTrialLicenseKey(long userId)
		throws java.lang.Exception {
		return _licenseKeyLocalService.renewTrialLicenseKey(userId);
	}

	/**
	* Updates the license key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseKey the license key
	* @return the license key that was updated
	*/
	@Override
	public com.liferay.osb.model.LicenseKey updateLicenseKey(
		com.liferay.osb.model.LicenseKey licenseKey) {
		return _licenseKeyLocalService.updateLicenseKey(licenseKey);
	}

	@Override
	public com.liferay.osb.model.LicenseKey updateLicenseKey(
		long licenseKeyId, long accountEntryId, long offeringEntryId,
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyLocalService.updateLicenseKey(licenseKeyId,
			accountEntryId, offeringEntryId, orderEntryId);
	}

	@Override
	public com.liferay.osb.model.LicenseKey updateLicenseKey(long userId,
		long licenseKeyId, long licenseKeySetId, long offeringEntryId,
		java.lang.String name, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyLocalService.updateLicenseKey(userId, licenseKeyId,
			licenseKeySetId, offeringEntryId, name, active);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _licenseKeyLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _licenseKeyLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _licenseKeyLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _licenseKeyLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getAssetReceiptLicenseLicenseKeysCount(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active) {
		return _licenseKeyLocalService.getAssetReceiptLicenseLicenseKeysCount(assetReceiptLicenseUuid,
			complimentary, active);
	}

	/**
	* Returns the number of license keies.
	*
	* @return the number of license keies
	*/
	@Override
	public int getLicenseKeiesCount() {
		return _licenseKeyLocalService.getLicenseKeiesCount();
	}

	@Override
	public int getOfferingEntryGroupLicenseKeysCount(long[] offeringEntryIds,
		boolean complimentary, boolean active) {
		return _licenseKeyLocalService.getOfferingEntryGroupLicenseKeysCount(offeringEntryIds,
			complimentary, active);
	}

	@Override
	public int getOfferingEntryLicenseKeysCount(long offeringEntryId) {
		return _licenseKeyLocalService.getOfferingEntryLicenseKeysCount(offeringEntryId);
	}

	@Override
	public int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		boolean complimentary, boolean active) {
		return _licenseKeyLocalService.getOfferingEntryLicenseKeysCount(offeringEntryId,
			complimentary, active);
	}

	@Override
	public int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		long clusterId) {
		return _licenseKeyLocalService.getOfferingEntryLicenseKeysCount(offeringEntryId,
			clusterId);
	}

	@Override
	public int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		long clusterId, boolean active) {
		return _licenseKeyLocalService.getOfferingEntryLicenseKeysCount(offeringEntryId,
			clusterId, active);
	}

	@Override
	public int getUserLicenseKeysCount(long userId, long accountEntryId) {
		return _licenseKeyLocalService.getUserLicenseKeysCount(userId,
			accountEntryId);
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
		boolean andSearch) {
		return _licenseKeyLocalService.searchCount(createUserId,
			createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear,
			modifiedUserId, modifiedDateGTDay, modifiedDateGTMonth,
			modifiedDateGTYear, modifiedDateLTDay, modifiedDateLTMonth,
			modifiedDateLTYear, accountEntryName, licenseKeySetName,
			startDateGTDay, startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, licenseEntryIds,
			productEntryIds, productEntryName, productId, productVersions,
			owner, description, hostName, ipAddress, macAddress, serverId, key,
			expirationDateGTDay, expirationDateGTMonth, expirationDateGTYear,
			expirationDateLTDay, expirationDateLTMonth, expirationDateLTYear,
			params, andSearch);
	}

	@Override
	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return _licenseKeyLocalService.searchCount(keywords, params);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _licenseKeyLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _licenseKeyLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _licenseKeyLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _licenseKeyLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _licenseKeyLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getAccountEntryLicenseKeys(
		long accountEntryId) {
		return _licenseKeyLocalService.getAccountEntryLicenseKeys(accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getAssetReceiptLicenseLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, boolean active) {
		return _licenseKeyLocalService.getAssetReceiptLicenseLicenseKeys(assetReceiptLicenseUuid,
			active);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getAssetReceiptLicenseLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active) {
		return _licenseKeyLocalService.getAssetReceiptLicenseLicenseKeys(assetReceiptLicenseUuid,
			complimentary, active);
	}

	/**
	* Returns a range of all the license keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of license keies
	*/
	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeies(
		int start, int end) {
		return _licenseKeyLocalService.getLicenseKeies(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeySetLicenseKeys(
		long licenseKeySetId) {
		return _licenseKeyLocalService.getLicenseKeySetLicenseKeys(licenseKeySetId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _licenseKeyLocalService.getLicenseKeys(assetReceiptLicenseUuid,
			productId, serverId, active, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		java.lang.String productId, java.lang.String serverId) {
		return _licenseKeyLocalService.getLicenseKeys(productId, serverId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		long userId, java.lang.String productId) {
		return _licenseKeyLocalService.getLicenseKeys(userId, productId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		long userId, long accountEntryId) {
		return _licenseKeyLocalService.getLicenseKeys(userId, accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeysByName(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _licenseKeyLocalService.getLicenseKeysByName(productEntryName,
			serverId, active, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _licenseKeyLocalService.getOfferingEntryGroupLicenseKeys(offeringEntryIds,
			complimentary, active, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId) {
		return _licenseKeyLocalService.getOfferingEntryLicenseKeys(offeringEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, boolean complimentary, boolean active) {
		return _licenseKeyLocalService.getOfferingEntryLicenseKeys(offeringEntryId,
			complimentary, active);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, long clusterId) {
		return _licenseKeyLocalService.getOfferingEntryLicenseKeys(offeringEntryId,
			clusterId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, long clusterId, boolean active) {
		return _licenseKeyLocalService.getOfferingEntryLicenseKeys(offeringEntryId,
			clusterId, active);
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
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _licenseKeyLocalService.search(createUserId, createDateGTDay,
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
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _licenseKeyLocalService.search(keywords, params, start, end, obc);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _licenseKeyLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _licenseKeyLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void buyLicenseKey(long companyId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_licenseKeyLocalService.buyLicenseKey(companyId, userId);
	}

	@Override
	public void sendRegisteredEmail(com.liferay.portal.kernel.model.User user,
		com.liferay.osb.model.LicenseKey licenseKey) throws java.lang.Exception {
		_licenseKeyLocalService.sendRegisteredEmail(user, licenseKey);
	}

	@Override
	public void sendTrialRenewalNotificationEmail(
		java.lang.String emailAddress, long accountEntryId)
		throws java.lang.Exception {
		_licenseKeyLocalService.sendTrialRenewalNotificationEmail(emailAddress,
			accountEntryId);
	}

	@Override
	public void updateLicenseKey(long userId, long licenseKeyId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		_licenseKeyLocalService.updateLicenseKey(userId, licenseKeyId, active);
	}

	@Override
	public LicenseKeyLocalService getWrappedService() {
		return _licenseKeyLocalService;
	}

	@Override
	public void setWrappedService(LicenseKeyLocalService licenseKeyLocalService) {
		_licenseKeyLocalService = licenseKeyLocalService;
	}

	private LicenseKeyLocalService _licenseKeyLocalService;
}