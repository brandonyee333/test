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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the license key local service. This utility wraps {@link com.liferay.osb.service.impl.LicenseKeyLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyLocalService
 * @see com.liferay.osb.service.base.LicenseKeyLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.LicenseKeyLocalServiceImpl
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey addLicenseKey(
		com.liferay.osb.model.LicenseKey licenseKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addLicenseKey(licenseKey);
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
	* Deletes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key that was removed
	* @throws PortalException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey deleteLicenseKey(
		long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteLicenseKey(licenseKeyId);
	}

	/**
	* Deletes the license key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKey the license key
	* @return the license key that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey deleteLicenseKey(
		com.liferay.osb.model.LicenseKey licenseKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteLicenseKey(licenseKey);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.LicenseKey fetchLicenseKey(
		long licenseKeyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchLicenseKey(licenseKeyId);
	}

	/**
	* Returns the license key with the primary key.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key
	* @throws PortalException if a license key with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey getLicenseKey(
		long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseKey(licenseKeyId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the license keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of license keies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeies(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseKeies(start, end);
	}

	/**
	* Returns the number of license keies.
	*
	* @return the number of license keies
	* @throws SystemException if a system exception occurred
	*/
	public static int getLicenseKeiesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseKeiesCount();
	}

	/**
	* Updates the license key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseKey the license key
	* @return the license key that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey updateLicenseKey(
		com.liferay.osb.model.LicenseKey licenseKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateLicenseKey(licenseKey);
	}

	/**
	* Updates the license key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseKey the license key
	* @param merge whether to merge the license key with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the license key that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseKey updateLicenseKey(
		com.liferay.osb.model.LicenseKey licenseKey, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateLicenseKey(licenseKey, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.LicenseKey addLicenseKey(long userId,
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense,
		java.lang.String owner, java.lang.String description,
		java.lang.String[] hostNames, java.lang.String[] ipAddresses,
		java.lang.String[] macAddresses, java.lang.String[] serverIds,
		int startDateMonth, int startDateDay, int startDateYear)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addLicenseKey(userId, assetReceiptLicense, owner,
			description, hostNames, ipAddresses, macAddresses, serverIds,
			startDateMonth, startDateDay, startDateYear);
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
		int startDateMonth, int startDateDay, int startDateYear,
		java.lang.String additionalInfo, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addLicenseKey(userId, licenseKeySet, name, offeringEntry,
			licenseEntry, productEntry, productVersion, clusterId, owner,
			maxServers, maxHttpSessions, description, hostNames, ipAddresses,
			macAddresses, serverIds, startDateMonth, startDateDay,
			startDateYear, additionalInfo, complimentary, active);
	}

	public static com.liferay.osb.model.LicenseKey addLicenseKey(long userId,
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
		return getService()
				   .addLicenseKey(userId, licenseKeySetId, name,
			offeringEntryId, licenseEntryId, productEntryId, productVersion,
			clusterId, owner, maxServers, maxHttpSessions, description,
			hostNames, ipAddresses, macAddresses, serverIds, startDateMonth,
			startDateDay, startDateYear, complimentary, active);
	}

	public static com.liferay.osb.model.LicenseKey addSingleUseLicenseKey(
		java.lang.String orderUuid, int productVersion,
		java.lang.String emailAddress, java.lang.String fullName,
		java.lang.String additionalInfo) throws java.lang.Exception {
		return getService()
				   .addSingleUseLicenseKey(orderUuid, productVersion,
			emailAddress, fullName, additionalInfo);
	}

	public static void buyLicenseKey(long companyId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().buyLicenseKey(companyId, userId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getAccountEntryLicenseKeys(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntryLicenseKeys(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getAssetReceiptLicenseLicenseKeys(
		long assetReceiptLicenseId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetReceiptLicenseLicenseKeys(assetReceiptLicenseId,
			active);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getAssetReceiptLicenseLicenseKeys(
		long assetReceiptLicenseId, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetReceiptLicenseLicenseKeys(assetReceiptLicenseId,
			complimentary, active);
	}

	public static int getAssetReceiptLicenseLicenseKeysCount(
		long assetReceiptLicenseId, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetReceiptLicenseLicenseKeysCount(assetReceiptLicenseId,
			complimentary, active);
	}

	public static com.liferay.osb.model.LicenseKey getFirstLicenseKey(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getFirstLicenseKey(accountEntryId, obc);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseKeys(userId, accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		long userId, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseKeys(userId, productId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getLicenseKeys(assetReceiptLicenseId, productId, serverId,
			active, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeys(
		java.lang.String productId, java.lang.String serverId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseKeys(productId, serverId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeysByName(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getLicenseKeysByName(productEntryName, serverId, active,
			start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getLicenseKeySetLicenseKeys(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseKeySetLicenseKeys(licenseKeySetId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingEntryGroupLicenseKeys(offeringEntryIds,
			complimentary, active, start, end, obc);
	}

	public static int getOfferingEntryGroupLicenseKeysCount(
		long[] offeringEntryIds, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingEntryGroupLicenseKeysCount(offeringEntryIds,
			complimentary, active);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOfferingEntryLicenseKeys(offeringEntryId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingEntryLicenseKeys(offeringEntryId, complimentary,
			active);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, long clusterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingEntryLicenseKeys(offeringEntryId, clusterId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, long clusterId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingEntryLicenseKeys(offeringEntryId, clusterId,
			active);
	}

	public static int getOfferingEntryLicenseKeysCount(long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOfferingEntryLicenseKeysCount(offeringEntryId);
	}

	public static int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		boolean complimentary, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingEntryLicenseKeysCount(offeringEntryId,
			complimentary, active);
	}

	public static int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		long clusterId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingEntryLicenseKeysCount(offeringEntryId, clusterId);
	}

	public static int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		long clusterId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingEntryLicenseKeysCount(offeringEntryId,
			clusterId, active);
	}

	public static int getUserLicenseKeysCount(long userId, long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserLicenseKeysCount(userId, accountEntryId);
	}

	public static com.liferay.osb.model.LicenseKey renewLicenseKey(
		long userId, long licenseKeyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().renewLicenseKey(userId, licenseKeyId);
	}

	public static com.liferay.osb.model.LicenseKey renewLicenseKey(
		long userId, long licenseKeyId, java.util.Date startDate, int renewTime)
		throws java.lang.Exception {
		return getService()
				   .renewLicenseKey(userId, licenseKeyId, startDate, renewTime);
	}

	public static com.liferay.osb.model.LicenseKey renewTrialLicenseKey(
		long userId) throws java.lang.Exception {
		return getService().renewTrialLicenseKey(userId);
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
		throws com.liferay.portal.kernel.exception.SystemException {
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
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(keywords, params, start, end, obc);
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
		throws com.liferay.portal.kernel.exception.SystemException {
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
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(keywords, params);
	}

	public static void sendRegisteredEmail(com.liferay.portal.model.User user,
		com.liferay.osb.model.LicenseKey licenseKey) throws java.lang.Exception {
		getService().sendRegisteredEmail(user, licenseKey);
	}

	public static void sendTrialRenewalNotificationEmail(
		java.lang.String emailAddress, long accountEntryId)
		throws java.lang.Exception {
		getService()
			.sendTrialRenewalNotificationEmail(emailAddress, accountEntryId);
	}

	public static void updateLicenseKey(long userId, long licenseKeyId,
		long assetReceiptLicenseId, boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateLicenseKey(userId, licenseKeyId, assetReceiptLicenseId,
			active);
	}

	public static void updateLicenseKey(long licenseKeyId, long accountEntryId,
		long offeringEntryId, long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateLicenseKey(licenseKeyId, accountEntryId, offeringEntryId,
			orderEntryId);
	}

	public static void updateLicenseKey(long userId, long licenseKeyId,
		long licenseKeySetId, long offeringEntryId, java.lang.String name,
		boolean active)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
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

	/**
	 * @deprecated
	 */
	public void setService(LicenseKeyLocalService service) {
	}

	private static LicenseKeyLocalService _service;
}