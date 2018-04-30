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

import com.liferay.osb.exception.MaximumLicenseKeyException;
import com.liferay.osb.exception.OfferingEntryStatusException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.LicenseEntry;
import com.liferay.osb.model.LicenseEntryConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.LicenseKeyConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryGroup;
import com.liferay.osb.model.OfferingEntryGroupFactoryUtil;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.base.LicenseKeyServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAccountEntryPermission;
import com.liferay.osb.service.permission.OSBAssetReceiptPermission;
import com.liferay.osb.service.permission.OSBLicenseKeyPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

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

	@JSONWebService
	public LicenseKey addLicenseKey(
			String userUuid, String assetReceiptLicenseUuid,
			String licenseEntryType, String productEntryName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, Date startDate, Date expirationDate)
		throws PortalException {

		validateJSONWebServicePermissions();

		User user = userLocalService.getUserByUuidAndCompanyId(
			userUuid, OSBConstants.COMPANY_ID);

		return licenseKeyLocalService.addLicenseKey(
			user.getUserId(), assetReceiptLicenseUuid, licenseEntryType,
			productEntryName, productId, productVersion, owner, maxUsers,
			description, hostName, ipAddresses, macAddresses, serverId,
			startDate, expirationDate);
	}

	@JSONWebService
	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getAssetReceiptLicenseLicenseKeys(
			assetReceiptLicenseUuid, complimentary, active);
	}

	@JSONWebService
	public int getAssetReceiptLicenseLicenseKeysCount(
			String assetReceiptLicenseUuid, boolean complimentary,
			boolean active)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getAssetReceiptLicenseLicenseKeysCount(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public LicenseKey getLicenseKey(long licenseKeyId) throws PortalException {
		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		if (Validator.isNotNull(licenseKey.getAssetReceiptLicenseUuid())) {
			OSBAssetReceiptPermission.check(
				getPermissionChecker(), OSBActionKeys.VIEW);
		}
		else {
			OSBLicenseKeyPermission.check(
				getPermissionChecker(), licenseKey, ActionKeys.VIEW);
		}

		return licenseKey;
	}

	@JSONWebService
	public LicenseKey getLicenseKey(String uuid) throws PortalException {
		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeyByUuid(uuid);
	}

	public List<LicenseKey> getLicenseKeys(long userId, String productId)
		throws PortalException {

		List<LicenseKey> licenseKeys = licenseKeyLocalService.getLicenseKeys(
			userId, productId);

		return filterLicenseKeys(licenseKeys);
	}

	@JSONWebService
	public List<LicenseKey> getLicenseKeys(String productId, String serverId)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeys(productId, serverId);
	}

	@JSONWebService
	public List<LicenseKey> getLicenseKeys(
			String assetReceiptLicenseUuid, String productId, String serverId,
			boolean active, int start, int end, OrderByComparator obc)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeys(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	@JSONWebService
	public List<LicenseKey> getLicenseKeysByName(
			String productEntryName, String serverId, boolean active, int start,
			int end, OrderByComparator obc)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getLicenseKeysByName(
			productEntryName, serverId, active, start, end, obc);
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
	public int getOfferingEntryLicenseKeysCount(
			long offeringEntryId, boolean complimentary, boolean active)
		throws PortalException {

		validateJSONWebServicePermissions();

		return licenseKeyLocalService.getOfferingEntryLicenseKeysCount(
			offeringEntryId, complimentary, active);
	}

	@JSONWebService
	public boolean isActive(String serverId, String productId, String key)
		throws PortalException {

		validateJSONWebServicePermissions();

		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		params.put("active", true);

		int activeLicensesCount = licenseKeyLocalService.searchCount(
			null, 0, 0, 0, 0, 0, 0, null, 0, 0, 0, 0, 0, 0, null, null, 0, 0, 0,
			0, 0, 0, new long[0], new long[0], null, productId, new int[0],
			null, null, null, null, null, serverId, key, 0, 0, 0, 0, 0, 0,
			params, true);

		if (activeLicensesCount > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@JSONWebService
	public LicenseKey registerLicenseKey(
			String orderEntryUuid, String productEntryName, int liferayVersion,
			int maxServers, String hostName, String ipAddresses,
			String macAddresses, String serverId)
		throws PortalException {

		validateJSONWebServicePermissions();

		OrderEntry orderEntry = orderEntryLocalService.getOrderEntry(
			orderEntryUuid);

		List<OfferingEntry> availableOfferingEntries = new ArrayList<>();

		List<OfferingEntry> offeringEntries = orderEntry.getOfferingEntries();

		for (OfferingEntry offeringEntry : offeringEntries) {
			ProductEntry productEntry = offeringEntry.getProductEntry();

			int environment = productEntry.getEnvironment();

			if ((environment ==
					ProductEntryConstants.ENVIRONMENT_DEVELOPMENT) ||
				(environment == ProductEntryConstants.ENVIRONMENT_NONE)) {

				continue;
			}

			String curProductEntryName = productEntry.getName();

			if (!curProductEntryName.equals(productEntryName)) {
				continue;
			}

			availableOfferingEntries.add(offeringEntry);
		}

		OfferingEntryGroup offeringEntryGroup =
			OfferingEntryGroupFactoryUtil.createOfferingEntryGroup(
				availableOfferingEntries);

		OfferingEntry offeringEntry =
			offeringEntryGroup.getAvailableLicenseOfferingEntry();

		ProductEntry productEntry = offeringEntry.getProductEntry();

		AccountEntry accountEntry = offeringEntry.getAccountEntry();

		long licenseEntryId = getLicenseEntryId(productEntry);

		int productVersion = getProductVersion(liferayVersion);

		Calendar cal = Calendar.getInstance();

		cal.setTime(orderEntry.getStartDate());

		try {
			return licenseKeyLocalService.addLicenseKey(
				offeringEntry.getUserId(), 0, accountEntry.getName(),
				offeringEntry.getOfferingEntryId(), licenseEntryId, 0,
				productVersion, 0, accountEntry.getName(), maxServers, 0,
				accountEntry.getName(), new String[] {hostName},
				new String[] {ipAddresses}, new String[] {macAddresses},
				new String[] {serverId}, cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE), cal.get(Calendar.YEAR), false, true);
		}
		catch (MaximumLicenseKeyException mlke) {
		}
		catch (OfferingEntryStatusException oese) {
		}

		return null;
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

	@JSONWebService
	public LicenseKey renewLicenseKey(
			String uuid, Date startDate, Date expirationDate)
		throws PortalException {

		validateJSONWebServicePermissions();

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKeyByUuid(
			uuid);

		return licenseKeyLocalService.renewLicenseKey(
			getUserId(), licenseKey.getLicenseKeyId(), startDate,
			expirationDate);
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

	public void updateLicenseKey(long userId, long licenseKeyId, boolean active)
		throws PortalException {

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKey(
			licenseKeyId);

		if (licenseKey.getActive() != active) {
			if (Validator.isNull(licenseKey.getServerId())) {
				OSBLicenseKeyPermission.check(
					getPermissionChecker(), licenseKey,
					OSBActionKeys.UPDATE_ADVANCED);
			}
			else {
				OSBAssetReceiptPermission.check(
					getPermissionChecker(), OSBActionKeys.MANAGE);
			}
		}
		else {
			OSBLicenseKeyPermission.check(
				getPermissionChecker(), licenseKey, OSBActionKeys.UPDATE_BASIC);
		}

		licenseKeyLocalService.updateLicenseKey(userId, licenseKeyId, active);
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

	@JSONWebService
	public void updateLicenseKey(String userUuid, String uuid, boolean active)
		throws PortalException {

		validateJSONWebServicePermissions();

		User user = userLocalService.getUserByUuidAndCompanyId(
			userUuid, OSBConstants.COMPANY_ID);

		LicenseKey licenseKey = licenseKeyLocalService.getLicenseKeyByUuid(
			uuid);

		licenseKeyLocalService.updateLicenseKey(
			user.getUserId(), licenseKey.getLicenseKeyId(), active);
	}

	@JSONWebService
	public void updateLicenseKeys(
			String assetReceiptLicenseUuid, boolean active)
		throws PortalException {

		List<LicenseKey> licenseKeys = licenseKeyPersistence.findByARLU_A(
			assetReceiptLicenseUuid, !active);

		for (LicenseKey licenseKey : licenseKeys) {
			licenseKeyLocalService.updateLicenseKey(
				getUserId(), licenseKey.getLicenseKeyId(), active);
		}
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

	protected long getLicenseEntryId(ProductEntry productEntry) {
		List<LicenseEntry> licenseEntries = productEntry.getLicenseEntries();

		for (LicenseEntry licenseEntry : licenseEntries) {
			String type = licenseEntry.getType();

			if (type.equals(LicenseEntryConstants.TYPE_LIMITED) ||
				type.equals(LicenseEntryConstants.TYPE_PER_USER) ||
				type.equals(LicenseEntryConstants.TYPE_PRODUCTION)) {

				return licenseEntry.getLicenseEntryId();
			}
		}

		return 0;
	}

	protected int getProductVersion(int liferayVersion) {
		if (liferayVersion == 6120) {
			return ProductEntryConstants.PORTAL_VERSION_6_1_20;
		}
		else if (liferayVersion == 6130) {
			return ProductEntryConstants.PORTAL_VERSION_6_1_30;
		}
		else if (liferayVersion == 6210) {
			return ProductEntryConstants.PORTAL_VERSION_6_2_10;
		}

		return ProductEntryConstants.PORTAL_VERSION_6_1_10;
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