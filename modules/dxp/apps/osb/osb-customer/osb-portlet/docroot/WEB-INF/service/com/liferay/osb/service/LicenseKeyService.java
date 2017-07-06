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
	public boolean isActive(long corpProjectId, java.lang.String key)
		throws PortalException, SystemException;

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
	@JSONWebService
	public LicenseKey addLicenseKey(long licenseKeySetId, long offeringEntryId,
		long licenseEntryId, int productVersion, java.lang.String owner,
		int maxHttpSessions, java.lang.String description,
		java.lang.String hostName, java.lang.String ipAddresses,
		java.lang.String macAddresses, java.lang.String serverId,
		int startDateMonth, int startDateDay, int startDateYear)
		throws PortalException, SystemException;

	public LicenseKey addLicenseKey(long userId, long assetReceiptLicenseId,
		java.lang.String owner, java.lang.String description,
		java.lang.String[] hostNames, java.lang.String[] ipAddresses,
		java.lang.String[] macAddresses, java.lang.String[] serverIds,
		int startDateMonth, int startDateDay, int startDateYear)
		throws PortalException, SystemException;

	public LicenseKey addLicenseKey(long userId, long licenseKeySetId,
		java.lang.String name, long offeringEntryId, long licenseEntryId,
		long productEntryId, int productVersion, long clusterId,
		java.lang.String owner, int maxServers, int maxHttpSessions,
		java.lang.String description, java.lang.String[] hostNames,
		java.lang.String[] ipAddresses, java.lang.String[] macAddresses,
		java.lang.String[] serverIds, int startDateMonth, int startDateDay,
		int startDateYear, boolean complimentary, boolean active)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getLicenseKey(long licenseKeyId)
		throws PortalException, SystemException;

	public LicenseKey renewLicenseKey(long licenseKeyId)
		throws PortalException, SystemException;

	public LicenseKey renewLicenseKey(long licenseKeyId, Date startDate,
		int renewTime) throws java.lang.Exception;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryGroupLicenseKeysCount(long[] offeringEntryIds,
		boolean complimentary, boolean active)
		throws PortalException, SystemException;

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
		boolean andSearch) throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws PortalException, SystemException;

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	@JSONWebService
	public java.lang.String exportToXML(long licenseKeyId)
		throws java.lang.Exception;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeySetLicenseKeys(long licenseKeySetId)
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(long userId,
		java.lang.String productId) throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, OrderByComparator obc)
		throws PortalException, SystemException;

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
		throws PortalException, SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> search(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, OrderByComparator obc) throws PortalException, SystemException;

	@JSONWebService
	public void updateLicenseKey(long licenseKeyId, boolean active)
		throws PortalException, SystemException;

	public void updateLicenseKey(long userId, long licenseKeyId,
		long assetReceiptLicenseId, boolean active)
		throws PortalException, SystemException;

	public void updateLicenseKey(long userId, long licenseKeyId,
		long licenseKeySetId, long offeringEntryId, java.lang.String name,
		boolean active) throws PortalException, SystemException;
}