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

import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.LicenseEntry;
import com.liferay.osb.model.LicenseEntryConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.LicenseKeyConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.service.base.LicenseKeyServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
import com.liferay.osb.service.permission.OSBLicenseKeyPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* TODO implement assetReceipt
import com.liferay.osb.model.AssetReceipt;

TODO implement assetReceipt
import com.liferay.osb.service.permission.OSBAssetReceiptPermission;
*/

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class LicenseKeyServiceImpl extends LicenseKeyServiceBaseImpl {

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

}