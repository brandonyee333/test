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
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for LicenseKey. This utility wraps
 * {@link com.liferay.osb.service.impl.LicenseKeyLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyLocalService
 * @see com.liferay.osb.service.base.LicenseKeyLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.LicenseKeyLocalServiceImpl
 * @generated
 */
@ProviderType
public class LicenseKeyLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.LicenseKeyLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the license key to the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKey the license key
	* @return the license key that was added
	*/
	public static com.liferay.osb.model.LicenseKey addLicenseKey(
		com.liferay.osb.model.LicenseKey licenseKey) {
		return getService().addLicenseKey(licenseKey);
	}

	public static com.liferay.osb.model.LicenseKey addLicenseKey(long userId,
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
		return getService()
				   .addLicenseKey(userId, licenseKeySet, name, offeringEntry,
			licenseEntry, productEntry, productVersion, clusterId, owner,
			maxServers, maxHttpSessions, description, hostNames, ipAddresses,
			macAddresses, serverIds, startDate, expirationDate, additionalInfo,
			complimentary, active);
	}

	public static com.liferay.osb.model.LicenseKey addLicenseKey(long userId,
		java.lang.String assetReceiptLicenseUuid,
		java.lang.String licenseEntryType, java.lang.String productEntryName,
		java.lang.String productId, int productVersion, java.lang.String owner,
		long maxUsers, java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddresses, java.lang.String macAddresses,
		java.lang.String serverId, java.util.Date startDate,
		java.util.Date expirationDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addLicenseKey(userId, assetReceiptLicenseUuid,
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

	/**
	* Creates a new license key with the primary key. Does not add the license key to the database.
	*
	* @param licenseKeyId the primary key for the new license key
	* @return the new license key
	*/
	public static com.liferay.osb.model.LicenseKey createLicenseKey(
		long licenseKeyId) {
		return getService().createLicenseKey(licenseKeyId);
	}

	/**
	* Deletes the license key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKey the license key
	* @return the license key that was removed
	*/
	public static com.liferay.osb.model.LicenseKey deleteLicenseKey(
		com.liferay.osb.model.LicenseKey licenseKey) {
		return getService().deleteLicenseKey(licenseKey);
	}

	/**
	* Deletes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key that was removed
	* @throws PortalException if a license key with the primary key could not be found
	*/
	public static com.liferay.osb.model.LicenseKey deleteLicenseKey(
		long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLicenseKey(licenseKeyId);
	}

	public static com.liferay.osb.model.LicenseKey fetchLicenseKey(
		long licenseKeyId) {
		return getService().fetchLicenseKey(licenseKeyId);
	}

	public static com.liferay.osb.model.LicenseKey getFirstLicenseKey(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFirstLicenseKey(accountEntryId, obc);
	}

	/**
	* Returns the license key with the primary key.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key
	* @throws PortalException if a license key with the primary key could not be found
	*/
	public static com.liferay.osb.model.LicenseKey getLicenseKey(
		long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLicenseKey(licenseKeyId);
	}

	public static com.liferay.osb.model.LicenseKey getLicenseKeyByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLicenseKeyByUuid(uuid);
	}

	public static com.liferay.osb.model.LicenseKey renewLicenseKey(
		long userId, long licenseKeyId, java.util.Date startDate, int renewTime)
		throws java.lang.Exception {
		return getService()
				   .renewLicenseKey(userId, licenseKeyId, startDate, renewTime);
	}

	public static com.liferay.osb.model.LicenseKey renewLicenseKey(
		long userId, long licenseKeyId, java.util.Date startDate,
		java.util.Date expirationDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .renewLicenseKey(userId, licenseKeyId, startDate,
			expirationDate);
	}

	public static com.liferay.osb.model.LicenseKey renewTrialLicenseKey(
		long userId) throws java.lang.Exception {
		return getService().renewTrialLicenseKey(userId);
	}

	/**
	* Updates the license key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseKey the license key
	* @return the license key that was updated
	*/
	public static com.liferay.osb.model.LicenseKey updateLicenseKey(
		com.liferay.osb.model.LicenseKey licenseKey) {
		return getService().updateLicenseKey(licenseKey);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active) {
		return getService()
				   .getAssetReceiptLicenseLicenseKeysCount(assetReceiptLicenseUuid,
			complimentary, active);
	}

	/**
	* Returns the number of license keies.
	*
	* @return the number of license keies
	*/
	public static int getLicenseKeiesCount() {
		return getService().getLicenseKeiesCount();
	}

	public static int getOfferingEntryGroupLicenseKeysCount(
		long[] offeringEntryIds, boolean complimentary, boolean active) {
		return getService()
				   .getOfferingEntryGroupLicenseKeysCount(offeringEntryIds,
			complimentary, active);
	}

	public static int getOfferingEntryLicenseKeysCount(long offeringEntryId) {
		return getService().getOfferingEntryLicenseKeysCount(offeringEntryId);
	}

	public static int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		boolean complimentary, boolean active) {
		return getService()
				   .getOfferingEntryLicenseKeysCount(offeringEntryId,
			complimentary, active);
	}

	public static int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		long clusterId) {
		return getService()
				   .getOfferingEntryLicenseKeysCount(offeringEntryId, clusterId);
	}

	public static int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		long clusterId, boolean active) {
		return getService()
				   .getOfferingEntryLicenseKeysCount(offeringEntryId,
			clusterId, active);
	}

	public static int getUserLicenseKeysCount(long userId, long accountEntryId) {
		return getService().getUserLicenseKeysCount(userId, accountEntryId);
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
		boolean andSearch) {
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
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getService().searchCount(keywords, params);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getAccountEntryLicenseKeys(
		long accountEntryId) {
		return getService().getAccountEntryLicenseKeys(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getAssetReceiptLicenseLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, boolean active) {
		return getService()
				   .getAssetReceiptLicenseLicenseKeys(assetReceiptLicenseUuid,
			active);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getAssetReceiptLicenseLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active) {
		return getService()
				   .getAssetReceiptLicenseLicenseKeys(assetReceiptLicenseUuid,
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
	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeies(
		int start, int end) {
		return getService().getLicenseKeies(start, end);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeySetLicenseKeys(
		long licenseKeySetId) {
		return getService().getLicenseKeySetLicenseKeys(licenseKeySetId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .getLicenseKeys(assetReceiptLicenseUuid, productId,
			serverId, active, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		java.lang.String productId, java.lang.String serverId) {
		return getService().getLicenseKeys(productId, serverId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		long userId, java.lang.String productId) {
		return getService().getLicenseKeys(userId, productId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		long userId, long accountEntryId) {
		return getService().getLicenseKeys(userId, accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeysByName(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .getLicenseKeysByName(productEntryName, serverId, active,
			start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .getOfferingEntryGroupLicenseKeys(offeringEntryIds,
			complimentary, active, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId) {
		return getService().getOfferingEntryLicenseKeys(offeringEntryId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, boolean complimentary, boolean active) {
		return getService()
				   .getOfferingEntryLicenseKeys(offeringEntryId, complimentary,
			active);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, long clusterId) {
		return getService()
				   .getOfferingEntryLicenseKeys(offeringEntryId, clusterId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, long clusterId, boolean active) {
		return getService()
				   .getOfferingEntryLicenseKeys(offeringEntryId, clusterId,
			active);
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
		com.liferay.portal.kernel.util.OrderByComparator obc) {
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
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().search(keywords, params, start, end, obc);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void buyLicenseKey(long companyId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().buyLicenseKey(companyId, userId);
	}

	public static void sendRegisteredEmail(
		com.liferay.portal.kernel.model.User user,
		com.liferay.osb.model.LicenseKey licenseKey) throws java.lang.Exception {
		getService().sendRegisteredEmail(user, licenseKey);
	}

	public static void sendTrialRenewalNotificationEmail(
		java.lang.String emailAddress, long accountEntryId)
		throws java.lang.Exception {
		getService()
			.sendTrialRenewalNotificationEmail(emailAddress, accountEntryId);
	}

	public static void updateLicenseKey(long licenseKeyId, long accountEntryId,
		long offeringEntryId, long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateLicenseKey(licenseKeyId, accountEntryId, offeringEntryId,
			orderEntryId);
	}

	public static void updateLicenseKey(long userId, long licenseKeyId,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().updateLicenseKey(userId, licenseKeyId, active);
	}

	public static void updateLicenseKey(long userId, long licenseKeyId,
		long licenseKeySetId, long offeringEntryId, java.lang.String name,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.updateLicenseKey(userId, licenseKeyId, licenseKeySetId,
			offeringEntryId, name, active);
	}

	public static void clearService() {
		_service = null;
	}

	public static LicenseKeyLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					LicenseKeyLocalService.class.getName());

			if (invokableLocalService instanceof LicenseKeyLocalService) {
				_service = (LicenseKeyLocalService)invokableLocalService;
			}
			else {
				_service = new LicenseKeyLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(LicenseKeyLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static LicenseKeyLocalService _service;
}