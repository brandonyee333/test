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

package com.liferay.osb.service.impl;

import com.liferay.osb.exception.LicenseKeyExportException;
import com.liferay.osb.exception.LicenseKeyVersionException;
import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
/* TODO implement assetReceipt
import com.liferay.osb.model.AssetReceipt;
*/
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.LicenseEntry;
import com.liferay.osb.model.LicenseEntryConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.LicenseKeyConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.base.LicenseKeyServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
/* TODO implement assetReceipt
import com.liferay.osb.service.permission.OSBAssetReceiptPermission;
*/
import com.liferay.osb.service.permission.OSBLicenseKeyPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class LicenseKeyServiceImpl extends LicenseKeyServiceBaseImpl {

	/**
	 * Adds a license key.
	 *
	 * <p>
	 * Only use for adding license keys for 6.1.10 and greater.
	 * </p>
	 *
	 * @param  licenseKeySetId the primary key of the license key set for
	 *         grouping keys together. Use 0 if you are not adding to an
	 *         existing set.
	 * @param  offeringEntryId the primary key of the offering entry
	 * @param  licenseEntryId the primary key of the license entry
	 * @param  productVersion the listTypeId of product entry's portalAllVersion
	 *         list type. Values can also be found in ProductEntryConstants.
	 * @param  owner the license key's owner, usually the account entry's name
	 * @param  maxHttpSessions the max number of IP's that can access the
	 *         portal. This is only used for developer type license keys.
	 * @param  description the license key's description
	 * @param  hostName the server's hostname that this license key is valid
	 *         for. Leave blank for developer, enterprise, and OEM type license
	 *         keys.
	 * @param  ipAddresses a comma delimited list of the server's IP addresses
	 *         that this license key is valid for. Leave blank for developer,
	 *         enterprise, and OEM type license keys.
	 * @param  macAddresses a comma delimited list of the server's MAC addresses
	 *         that this license key is valid for. Leave blank for developer,
	 *         enterprise, and OEM type license keys.
	 * @param  serverId the server ID's received from the server that this
	 *         license key is valid for. The server ID is a encrypted string
	 *         which includes a random string, and the server's MAC addresses,
	 *         IP addresses, and hostname. Leave this blank if generating an
	 *         offline license key.
	 * @param  startDateMonth the license key's starting date's month
	 * @param  startDateDay the license key's starting date's day
	 * @param  startDateYear the license key's starting date's year
	 * @throws PortalException if the offering entry is closed, or if there are
	 *         no more available license keys left in the offering entry
	 * @throws SystemException if a system exception occurred
	 */
	@JSONWebService
	public LicenseKey addLicenseKey(
			long licenseKeySetId, long offeringEntryId, long licenseEntryId,
			int productVersion, String owner, int maxHttpSessions,
			String description, String hostName, String ipAddresses,
			String macAddresses, String serverId, int startDateMonth,
			int startDateDay, int startDateYear)
		throws PortalException {

		validateJSONWebServicePermissions();

		if (productVersion < ProductEntryConstants.PORTAL_VERSION_6_1_10) {
			throw new LicenseKeyVersionException(
				"Portal version must be greater than 6.1.10");
		}

		if (Validator.isNull(serverId)) {
			LicenseEntry licenseEntry =
				licenseEntryPersistence.findByPrimaryKey(licenseEntryId);

			serverId = LicenseKeyConstants.getServerId(licenseEntry.getType());
		}

		return licenseKeyLocalService.addLicenseKey(
			getUserId(), licenseKeySetId, owner, offeringEntryId,
			licenseEntryId, 0, productVersion, 0, owner, 0, maxHttpSessions,
			description, new String[] {hostName}, new String[] {ipAddresses},
			new String[] {macAddresses}, new String[] {serverId},
			startDateMonth, startDateDay, startDateYear, false, true);
	}

	public LicenseKey addLicenseKey(
			long userId, long licenseKeySetId, String name,
			long offeringEntryId, long licenseEntryId, long productEntryId,
			int productVersion, long clusterId, String owner, int maxServers,
			int maxHttpSessions, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses, String[] serverIds,
			int startDateMonth, int startDateDay, int startDateYear,
			boolean complimentary, boolean active)
		throws PortalException {

		OfferingEntry offeringEntry =
			offeringEntryLocalService.getOfferingEntry(offeringEntryId);

		OSBAccountEntryPermission.check(
			getPermissionChecker(), offeringEntry.getAccountEntryId(),
			ActionKeys.ADD_LICENSE);

		LicenseEntry licenseEntry = licenseEntryPersistence.findByPrimaryKey(
			licenseEntryId);

		String licenseEntryType = licenseEntry.getType();

		if ((LicenseKeyConstants.getLicenseVersion(productVersion) >= 3) &&
			(licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
			 licenseEntryType.equals(
				 LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) &&
			(maxHttpSessions != 5)) {

			if (!roleLocalService.hasUserRole(
					getUserId(), OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID) &&
				!roleLocalService.hasUserRole(
					getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

				throw new PrincipalException();
			}
		}

		if (Validator.isGregorianDate(
				startDateMonth, startDateDay, startDateYear)) {

			if (!roleLocalService.hasUserRole(
					getUserId(), OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID) &&
				!roleLocalService.hasUserRole(
					getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

				throw new PrincipalException();
			}
		}

		return licenseKeyLocalService.addLicenseKey(
			userId, licenseKeySetId, name, offeringEntryId, licenseEntryId,
			productEntryId, productVersion, clusterId, owner, maxServers,
			maxHttpSessions, description, hostNames, ipAddresses, macAddresses,
			serverIds, startDateMonth, startDateDay, startDateYear, false,
			true);
	}

	public LicenseKey addLicenseKey(
			long userId, long assetReceiptLicenseId, String owner,
			String description, String[] hostNames, String[] ipAddresses,
			String[] macAddresses, String[] serverIds, int startDateMonth,
			int startDateDay, int startDateYear)
		throws PortalException {

		AssetReceiptLicense assetReceiptLicense =
			assetReceiptLicensePersistence.findByPrimaryKey(
				assetReceiptLicenseId);

		/* TODO implement assetReceipt
		AssetReceipt assetReceipt = assetReceiptPersistence.findByPrimaryKey(
			assetReceiptLicense.getAssetReceiptId());

		OSBAssetReceiptPermission.check(
			getPermissionChecker(), assetReceipt, OSBActionKeys.ADD_LICENSE);
		*/

		return licenseKeyLocalService.addLicenseKey(
			userId, assetReceiptLicense, owner, description, hostNames,
			ipAddresses, macAddresses, serverIds, startDateMonth, startDateDay,
			startDateYear);
	}

	@JSONWebService
	public String exportToXML(long licenseKeyId) throws Exception {
		validateJSONWebServicePermissions();

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		if (licenseKey.getLicenseVersion() < 2) {
			throw new LicenseKeyExportException(
				"License key version must be greater than 2");
		}

		return LicenseUtil.exportToXML(licenseKey);
	}

	public LicenseKey getLicenseKey(long licenseKeyId) throws PortalException {
		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		if (licenseKey.getAssetReceiptLicenseId() > 0) {
			AssetReceiptLicense assetReceiptLicense =
				assetReceiptLicensePersistence.findByPrimaryKey(
					licenseKey.getAssetReceiptLicenseId());

			/* TODO implement assetReceipt
			AssetReceipt assetReceipt =
				assetReceiptPersistence.findByPrimaryKey(
					assetReceiptLicense.getAssetReceiptId());

			OSBAssetReceiptPermission.check(
				getPermissionChecker(), assetReceipt, OSBActionKeys.VIEW);
			*/
		}
		else {
			OSBLicenseKeyPermission.check(
				getPermissionChecker(), licenseKey, ActionKeys.VIEW);
		}

		return licenseKey;
	}

	public List<LicenseKey> getLicenseKeys(long userId, String productId)
		throws PortalException {

		List<LicenseKey> licenseKeys = licenseKeyLocalService.getLicenseKeys(
			userId, productId);

		return filterLicenseKeys(licenseKeys);
	}

	public List<LicenseKey> getLicenseKeySetLicenseKeys(long licenseKeySetId)
		throws PortalException {

		List<LicenseKey> licenseKeys =
			licenseKeyPersistence.findByLicenseKeySetId(licenseKeySetId);

		return filterLicenseKeys(licenseKeys);
	}

	public List<LicenseKey> getOfferingEntryGroupLicenseKeys(
			long[] offeringEntryIds, boolean complimentary, boolean active,
			int start, int end, OrderByComparator obc)
		throws PortalException {

		if (!isAccountAdmin(getUserId()) &&
			!roleLocalService.hasUserRole(
				getUserId(), OSBConstants.ROLE_OSB_TRIAL_LICENSE_ADMIN_ID)) {

			throw new PrincipalException();
		}

		return licenseKeyLocalService.getOfferingEntryGroupLicenseKeys(
			offeringEntryIds, complimentary, active, start, end, obc);
	}

	public int getOfferingEntryGroupLicenseKeysCount(
			long[] offeringEntryIds, boolean complimentary, boolean active)
		throws PortalException {

		if (!isAccountAdmin(getUserId()) &&
			!roleLocalService.hasUserRole(
				getUserId(), OSBConstants.ROLE_OSB_TRIAL_LICENSE_ADMIN_ID)) {

			throw new PrincipalException();
		}

		return licenseKeyLocalService.getOfferingEntryGroupLicenseKeysCount(
			offeringEntryIds, complimentary, active);
	}

	@JSONWebService
	public boolean isActive(long corpProjectId, String key)
		throws PortalException {

		validateJSONWebServicePermissions();

		if (licenseKeyFinder.countByCPI_K_A(corpProjectId, key, true) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public LicenseKey renewLicenseKey(long licenseKeyId)
		throws PortalException {

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		AssetReceiptLicense assetReceiptLicense =
			assetReceiptLicensePersistence.findByPrimaryKey(
				licenseKey.getAssetReceiptLicenseId());

		/* TODO implement assetReceipt
		AssetReceipt assetReceipt = assetReceiptLicense.getAssetReceipt();

		OSBAssetReceiptPermission.check(
			getPermissionChecker(), assetReceipt, OSBActionKeys.RENEW_LICENSE);
		*/

		return licenseKeyLocalService.renewLicenseKey(
			getUserId(), licenseKeyId);
	}

	public LicenseKey renewLicenseKey(
			long licenseKeyId, Date startDate, int renewTime)
		throws Exception {

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		OSBAccountEntryPermission.check(
			getPermissionChecker(), licenseKey.getAccountEntryId(),
			OSBActionKeys.ADD_LICENSE);

		return licenseKeyLocalService.renewLicenseKey(
			getUserId(), licenseKeyId, startDate, renewTime);
	}

	public List<LicenseKey> search(
			Long createUserId, int createDateGTDay, int createDateGTMonth,
			int createDateGTYear, int createDateLTDay, int createDateLTMonth,
			int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
			int modifiedDateGTMonth, int modifiedDateGTYear,
			int modifiedDateLTDay, int modifiedDateLTMonth,
			int modifiedDateLTYear, String accountEntryName,
			String licenseKeySetName, int startDateGTDay, int startDateGTMonth,
			int startDateGTYear, int startDateLTDay, int startDateLTMonth,
			int startDateLTYear, long[] licenseEntryIds, long[] productEntryIds,
			String productEntryName, String productId, int[] productVersions,
			String owner, String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			int expirationDateGTDay, int expirationDateGTMonth,
			int expirationDateGTYear, int expirationDateLTDay,
			int expirationDateLTMonth, int expirationDateLTYear,
			LinkedHashMap<String, Object> params, boolean andSearch, int start,
			int end, OrderByComparator obc)
		throws PortalException {

		addPermissionParams(params);

		return licenseKeyLocalService.search(
			createUserId, createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear,
			modifiedUserId, modifiedDateGTDay, modifiedDateGTMonth,
			modifiedDateGTYear, modifiedDateLTDay, modifiedDateLTMonth,
			modifiedDateLTYear, accountEntryName, licenseKeySetName,
			startDateGTDay, startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, licenseEntryIds, productEntryIds,
			productEntryName, productId, productVersions, owner, description,
			hostName, ipAddress, macAddress, serverId, key, expirationDateGTDay,
			expirationDateGTMonth, expirationDateGTYear, expirationDateLTDay,
			expirationDateLTMonth, expirationDateLTYear, params, andSearch,
			start, end, obc);
	}

	public List<LicenseKey> search(
			String keywords, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws PortalException {

		addPermissionParams(params);

		return licenseKeyLocalService.search(keywords, params, start, end, obc);
	}

	public int searchCount(
			Long createUserId, int createDateGTDay, int createDateGTMonth,
			int createDateGTYear, int createDateLTDay, int createDateLTMonth,
			int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
			int modifiedDateGTMonth, int modifiedDateGTYear,
			int modifiedDateLTDay, int modifiedDateLTMonth,
			int modifiedDateLTYear, String accountEntryName,
			String licenseKeySetName, int startDateGTDay, int startDateGTMonth,
			int startDateGTYear, int startDateLTDay, int startDateLTMonth,
			int startDateLTYear, long[] licenseEntryIds, long[] productEntryIds,
			String productEntryName, String productId, int[] productVersions,
			String owner, String description, String hostName, String ipAddress,
			String macAddress, String serverId, String key,
			int expirationDateGTDay, int expirationDateGTMonth,
			int expirationDateGTYear, int expirationDateLTDay,
			int expirationDateLTMonth, int expirationDateLTYear,
			LinkedHashMap<String, Object> params, boolean andSearch)
		throws PortalException {

		addPermissionParams(params);

		return licenseKeyLocalService.searchCount(
			createUserId, createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear,
			modifiedUserId, modifiedDateGTDay, modifiedDateGTMonth,
			modifiedDateGTYear, modifiedDateLTDay, modifiedDateLTMonth,
			modifiedDateLTYear, accountEntryName, licenseKeySetName,
			startDateGTDay, startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, licenseEntryIds, productEntryIds,
			productEntryName, productId, productVersions, owner, description,
			hostName, ipAddress, macAddress, serverId, key, expirationDateGTDay,
			expirationDateGTMonth, expirationDateGTYear, expirationDateLTDay,
			expirationDateLTMonth, expirationDateLTYear, params, andSearch);
	}

	public int searchCount(
			String keywords, LinkedHashMap<String, Object> params)
		throws PortalException {

		addPermissionParams(params);

		return licenseKeyLocalService.searchCount(keywords, params);
	}

	@JSONWebService
	public void updateLicenseKey(long licenseKeyId, boolean active)
		throws PortalException {

		validateJSONWebServicePermissions();

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		licenseKeyLocalService.updateLicenseKey(
			getUserId(), licenseKeyId, licenseKey.getLicenseKeySetId(),
			licenseKey.getOfferingEntryId(), StringPool.BLANK, active);
	}

	public void updateLicenseKey(
			long userId, long licenseKeyId, long assetReceiptLicenseId,
			boolean active)
		throws PortalException {

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		if (licenseKey.getAssetReceiptLicenseId() != assetReceiptLicenseId) {
			OSBLicenseKeyPermission.check(
				getPermissionChecker(), licenseKey, OSBActionKeys.UPDATE_ADMIN);
		}
		else if (licenseKey.getActive() != active) {
			if (Validator.isNull(licenseKey.getServerId())) {
				OSBLicenseKeyPermission.check(
					getPermissionChecker(), licenseKey,
					OSBActionKeys.UPDATE_ADVANCED);
			}
			else {
				AssetReceiptLicense assetReceiptLicense =
					assetReceiptLicensePersistence.findByPrimaryKey(
						licenseKey.getAssetReceiptLicenseId());

				/* TODO implement assetReceipt
				AssetReceipt assetReceipt =
					assetReceiptPersistence.findByPrimaryKey(
						assetReceiptLicense.getAssetReceiptId());

				OSBAssetReceiptPermission.check(
					getPermissionChecker(), assetReceipt, OSBActionKeys.MANAGE);
				*/
			}
		}
		else {
			OSBLicenseKeyPermission.check(
				getPermissionChecker(), licenseKey, OSBActionKeys.UPDATE_BASIC);
		}

		licenseKeyLocalService.updateLicenseKey(
			userId, licenseKeyId, assetReceiptLicenseId, active);
	}

	public void updateLicenseKey(
			long userId, long licenseKeyId, long licenseKeySetId,
			long offeringEntryId, String name, boolean active)
		throws PortalException {

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		if (licenseKey.getOfferingEntryId() != offeringEntryId) {
			OSBLicenseKeyPermission.check(
				getPermissionChecker(), licenseKey, OSBActionKeys.UPDATE_ADMIN);
		}
		else if (licenseKey.getActive() != active) {
			OSBLicenseKeyPermission.check(
				getPermissionChecker(), licenseKey,
				OSBActionKeys.UPDATE_ADVANCED);
		}
		else {
			OSBLicenseKeyPermission.check(
				getPermissionChecker(), licenseKey, OSBActionKeys.UPDATE_BASIC);
		}

		licenseKeyLocalService.updateLicenseKey(
			userId, licenseKeyId, licenseKeySetId, offeringEntryId, name,
			active);
	}

	protected void addPermissionParams(LinkedHashMap<String, Object> params)
		throws PortalException {

		if (isAccountAdmin(getUserId())) {
			return;
		}

		if (roleLocalService.hasUserRole(
				getUserId(), OSBConstants.ROLE_OSB_TRIAL_LICENSE_ADMIN_ID)) {

			params.put("accountEntryType", AccountEntryConstants.TYPE_TRIAL);

			return;
		}

		params.put("accountEntryMembership", Long.valueOf(getUserId()));

		if (accountWorkerLocalService.hasAccountWorkerRole(
				getUserId(), AccountWorkerConstants.ROLE_SALES)) {

			return;
		}

		params.put("active", Boolean.TRUE);
	}

	protected List<LicenseKey> filterLicenseKeys(List<LicenseKey> licenseKeys)
		throws PortalException {

		List<LicenseKey> filteredLicenseKeys = ListUtil.copy(licenseKeys);

		Iterator<LicenseKey> itr = filteredLicenseKeys.iterator();

		while (itr.hasNext()) {
			LicenseKey licenseKey = itr.next();

			if (!OSBLicenseKeyPermission.contains(
					getPermissionChecker(), licenseKey, ActionKeys.VIEW)) {

				itr.remove();
			}
		}

		return filteredLicenseKeys;
	}

	protected boolean isAccountAdmin(long userId) {
		if (roleLocalService.hasUserRole(
				userId, OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)) {

			return true;
		}

		if (roleLocalService.hasUserRole(
				userId, OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return true;
		}

		return false;
	}

	protected void validateJSONWebServicePermissions() throws PortalException {
		if (!roleLocalService.hasUserRole(
				getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}
	}

}