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

import com.liferay.osb.model.LicenseKey;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the remote service interface for LicenseKey. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyServiceUtil
 * @see com.liferay.osb.service.base.LicenseKeyServiceBaseImpl
 * @see com.liferay.osb.service.impl.LicenseKeyServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LicenseKeyService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LicenseKeyServiceUtil} to access the license key remote service. Add custom service methods to {@link com.liferay.osb.service.impl.LicenseKeyServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean isActive(java.lang.String serverId,
		java.lang.String productId, java.lang.String key)
		throws PortalException;

	public LicenseKey addDeveloperLicenseKey(long accountEntryId,
		java.lang.String productEntryRootName, int productMinorVersion)
		throws PortalException;

	@JSONWebService
	public LicenseKey addLicenseKey(java.lang.String userUuid,
		java.lang.String assetReceiptLicenseUuid,
		java.lang.String licenseEntryType, java.lang.String productEntryName,
		java.lang.String productId, int productVersion, java.lang.String owner,
		long maxUsers, java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddresses, java.lang.String macAddresses,
		java.lang.String serverId, Date startDate, Date expirationDate)
		throws PortalException;

	public LicenseKey addLicenseKey(long userId, long licenseKeySetId,
		java.lang.String name, long offeringEntryId, long licenseEntryId,
		long productEntryId, int productVersion, long clusterId,
		java.lang.String owner, int maxServers, int maxHttpSessions,
		java.lang.String description, java.lang.String[] hostNames,
		java.lang.String[] ipAddresses, java.lang.String[] macAddresses,
		java.lang.String[] serverIds, Date startDate, boolean complimentary,
		boolean active) throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getLicenseKey(java.lang.String uuid)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getLicenseKey(long licenseKeyId)
		throws PortalException;

	@JSONWebService
	public LicenseKey registerLicenseKey(java.lang.String orderEntryUuid,
		java.lang.String productEntryName, int liferayVersion, int maxServers,
		java.lang.String hostName, java.lang.String ipAddresses,
		java.lang.String macAddresses, java.lang.String serverId)
		throws PortalException;

	@JSONWebService
	public LicenseKey renewLicenseKey(java.lang.String uuid, Date startDate,
		Date expirationDate) throws PortalException;

	public LicenseKey renewLicenseKey(long licenseKeyId, Date startDate,
		int renewTime) throws java.lang.Exception;

	public LicenseKey updateLicenseKey(long userId, long licenseKeyId,
		long licenseKeySetId, long offeringEntryId, java.lang.String name,
		boolean active) throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetReceiptLicenseLicenseKeysCount(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryGroupLicenseKeysCount(long[] offeringEntryIds,
		boolean complimentary, boolean active) throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		boolean complimentary, boolean active) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws PortalException;

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	@JSONWebService
	public java.lang.String generateWeDeployLicenseKey(java.lang.String owner,
		Date startDate, long licenseLifetime) throws java.lang.Exception;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeySetLicenseKeys(long licenseKeySetId)
		throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		OrderByComparator obc) throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(java.lang.String productId,
		java.lang.String serverId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(long userId,
		java.lang.String productId) throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeysByName(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end, OrderByComparator obc)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, OrderByComparator obc) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> search(java.lang.Long createUserId,
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
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, OrderByComparator obc)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> search(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, OrderByComparator obc) throws PortalException;

	@JSONWebService
	public void updateLicenseKey(java.lang.String userUuid,
		java.lang.String uuid, boolean active) throws PortalException;

	public void updateLicenseKey(long userId, long licenseKeyId, boolean active)
		throws PortalException;

	@JSONWebService
	public void updateLicenseKeys(java.lang.String assetReceiptLicenseUuid,
		boolean active) throws PortalException;
}