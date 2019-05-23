/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.service.impl;

import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.admin.util.KeyGenerator;
import com.liferay.osb.exception.DuplicateIPAddressException;
import com.liferay.osb.exception.DuplicateMACAddressException;
import com.liferay.osb.exception.LicenseKeyActiveException;
import com.liferay.osb.exception.LicenseKeyDescriptionException;
import com.liferay.osb.exception.LicenseKeyHostNameException;
import com.liferay.osb.exception.LicenseKeyIPAddressException;
import com.liferay.osb.exception.LicenseKeyMACAddressException;
import com.liferay.osb.exception.LicenseKeyMaxServersException;
import com.liferay.osb.exception.LicenseKeyOwnerException;
import com.liferay.osb.exception.LicenseKeyProductEntryException;
import com.liferay.osb.exception.LicenseKeyProductVersionException;
import com.liferay.osb.exception.LicenseKeyRenewException;
import com.liferay.osb.exception.LicenseKeyServerIdException;
import com.liferay.osb.exception.LicenseKeyServerInfoException;
import com.liferay.osb.exception.MaximumLicenseKeyException;
import com.liferay.osb.exception.NoSuchLicenseKeyException;
import com.liferay.osb.exception.OfferingEntryStatusException;
import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.LicenseEntry;
import com.liferay.osb.model.LicenseEntryConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.LicenseKeyConstants;
import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.model.OfferingDefinitionConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.OfferingEntryGroup;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.model.SupportResponse;
import com.liferay.osb.service.base.LicenseKeyLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.comparator.LicenseKeyExpirationDateComparator;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.SubscriptionSender;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.io.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.PortletPreferences;

import org.apache.commons.lang.time.DateUtils;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class LicenseKeyLocalServiceImpl extends LicenseKeyLocalServiceBaseImpl {

	public LicenseKey addDeveloperLicenseKey(
			long userId, long accountEntryId, String productEntryRootName,
			int productMinorVersion)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		OfferingEntry primaryOfferingEntry = getPrimaryOfferingEntry(
			accountEntryId, productEntryRootName);

		if (primaryOfferingEntry == null) {
			throw new PrincipalException();
		}

		OfferingEntry offeringEntry = getDeveloperOfferingEntry(
			accountEntryId, primaryOfferingEntry.getProductEntryId(),
			primaryOfferingEntry.getVersion(),
			primaryOfferingEntry.getSizing());

		long licenseKeySetId = 0;

		LicenseKey licenseKey = licenseKeyPersistence.fetchByOEI_C_A_First(
			offeringEntry.getOfferingEntryId(), false, true, null);

		if (licenseKey != null) {
			licenseKeySetId = licenseKey.getLicenseKeySetId();
		}

		LicenseEntry licenseEntry = licenseEntryPersistence.findByPEI_T(
			offeringEntry.getProductEntryId(),
			LicenseEntryConstants.TYPE_DEVELOPER);

		return addLicenseKey(
			userId, licenseKeySetId, "Developer Activation Keys",
			offeringEntry.getOfferingEntryId(),
			licenseEntry.getLicenseEntryId(), offeringEntry.getProductEntryId(),
			getProductVersion(productMinorVersion), 0, user.getFullName(), 2, 5,
			accountEntry.getName() + " Developer Activation Keys",
			new String[0], new String[0], new String[0],
			new String[] {LicenseKeyConstants.SERVER_ID_DEVELOPER}, new Date(),
			false, true);
	}

	public LicenseKey addLicenseKey(
			long userId, LicenseKeySet licenseKeySet, String name,
			OfferingEntry offeringEntry, LicenseEntry licenseEntry,
			ProductEntry productEntry, int productVersion, long clusterId,
			String owner, int maxServers, int maxHttpSessions,
			String description, String[] hostNames, String[] ipAddresses,
			String[] macAddresses, String[] serverIds, Date startDate,
			Date expirationDate, String additionalInfo, boolean complimentary,
			boolean active)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		AccountEntry accountEntry = offeringEntry.getAccountEntry();
		OrderEntry orderEntry = offeringEntry.getOrderEntry();

		if (!complimentary) {
			productEntry = licenseEntry.getProductEntry();
		}

		String licenseEntryType = licenseEntry.getType();

		int licenseVersion = LicenseKeyConstants.getLicenseVersion(
			productVersion);

		Date now = new Date();

		if (startDate == null) {
			if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
				licenseEntryType.equals(
					LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {

				startDate = now;
			}
			else {
				startDate = orderEntry.getStartDate();
			}
		}

		if (expirationDate == null) {
			expirationDate = new Date(
				startDate.getTime() + offeringEntry.getLicenseLifetime());
		}

		validate(
			offeringEntry, licenseEntry, licenseVersion, productEntry,
			productVersion, owner, maxServers, description, hostNames,
			ipAddresses, macAddresses, serverIds, complimentary);

		if (licenseKeySet == null) {
			licenseKeySet = licenseKeySetLocalService.addLicenseKeySet(
				user.getUserId(), offeringEntry.getAccountEntryId(), name);
		}

		if (licenseVersion >= 3) {
			return doAddLicenseKeyVersion3_4(
				now, user, licenseKeySet, name, accountEntry, offeringEntry,
				licenseEntry, productEntry, licenseEntryType, licenseVersion,
				productVersion, clusterId, owner, maxServers, maxHttpSessions,
				description, hostNames, ipAddresses, macAddresses, serverIds,
				startDate, expirationDate, additionalInfo, complimentary,
				active);
		}
		else {
			return doAddLicenseKey(
				now, user, licenseKeySet, name, accountEntry, offeringEntry,
				licenseEntry, productEntry, licenseEntryType, licenseVersion,
				productVersion, clusterId, owner, maxServers, description,
				serverIds, startDate, expirationDate, additionalInfo,
				complimentary, active);
		}
	}

	public LicenseKey addLicenseKey(
			long userId, long licenseKeySetId, String name,
			long offeringEntryId, long licenseEntryId, long productEntryId,
			int productVersion, long clusterId, String owner, int maxServers,
			int maxHttpSessions, String description, String[] hostNames,
			String[] ipAddresses, String[] macAddresses, String[] serverIds,
			Date startDate, boolean complimentary, boolean active)
		throws PortalException {

		LicenseKeySet licenseKeySet = null;

		if (licenseKeySetId > 0) {
			licenseKeySet = licenseKeySetPersistence.findByPrimaryKey(
				licenseKeySetId);
		}

		OfferingEntry offeringEntry = offeringEntryPersistence.findByPrimaryKey(
			offeringEntryId);
		LicenseEntry licenseEntry = licenseEntryPersistence.findByPrimaryKey(
			licenseEntryId);

		ProductEntry productEntry = null;

		if (productEntryId > 0) {
			productEntry = productEntryPersistence.findByPrimaryKey(
				productEntryId);
		}

		return addLicenseKey(
			userId, licenseKeySet, name, offeringEntry, licenseEntry,
			productEntry, productVersion, clusterId, owner, maxServers,
			maxHttpSessions, description, hostNames, ipAddresses, macAddresses,
			serverIds, startDate, null, StringPool.BLANK, complimentary,
			active);
	}

	public LicenseKey addLicenseKey(
			long userId, String assetReceiptLicenseUuid,
			String licenseEntryType, String productEntryName, String productId,
			int productVersion, String owner, long maxUsers, String description,
			String hostName, String ipAddresses, String macAddresses,
			String serverId, Date startDate, Date expirationDate)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();
		int licenseVersion = 3;

		productEntryName = LicenseUtil.trimText(productEntryName);
		owner = LicenseUtil.trimText(owner);
		description = LicenseUtil.trimText(description);
		startDate = DateUtils.round(startDate, Calendar.SECOND);
		expirationDate = DateUtils.round(expirationDate, Calendar.SECOND);

		validate(owner, description, hostName, ipAddresses, macAddresses);

		String key = KeyGenerator.generate(
			StringPool.BLANK, StringPool.BLANK, licenseEntryType,
			licenseVersion, productEntryName, productId,
			String.valueOf(productVersion), owner, 0, 0, 0, maxUsers, 0,
			description, hostName, ipAddresses, macAddresses,
			new String[] {serverId}, startDate, expirationDate);

		long licenseKeyId = counterLocalService.increment();

		LicenseKey licenseKey = licenseKeyPersistence.create(licenseKeyId);

		licenseKey.setUserId(user.getUserId());
		licenseKey.setUserName(user.getFullName());
		licenseKey.setCreateDate(now);
		licenseKey.setModifiedUserId(user.getUserId());
		licenseKey.setModifiedUserName(user.getFullName());
		licenseKey.setModifiedDate(now);
		licenseKey.setAssetReceiptLicenseUuid(assetReceiptLicenseUuid);
		licenseKey.setLicenseEntryType(licenseEntryType);
		licenseKey.setLicenseVersion(licenseVersion);
		licenseKey.setProductEntryName(productEntryName);
		licenseKey.setProductId(productId);
		licenseKey.setProductVersion(productVersion);
		licenseKey.setProductVersionLabel(String.valueOf(productVersion));
		licenseKey.setOwner(owner);
		licenseKey.setMaxUsers(maxUsers);
		licenseKey.setDescription(description);
		licenseKey.setHostName(hostName);
		licenseKey.setIpAddresses(ipAddresses);
		licenseKey.setMacAddresses(macAddresses);
		licenseKey.setServerId(serverId);
		licenseKey.setKey(key);
		licenseKey.setStartDate(startDate);
		licenseKey.setExpirationDate(expirationDate);
		licenseKey.setComplimentary(false);
		licenseKey.setActive(true);

		return licenseKeyPersistence.update(licenseKey);
	}

	public void buyLicenseKey(long companyId, long userId)
		throws PortalException {

		boolean osbTrialpurchased = expandoValueLocalService.getData(
			companyId, User.class.getName(), "OSB", "osbTrialPurchased", userId,
			false);

		if (osbTrialpurchased) {
			return;
		}

		expandoValueLocalService.addValue(
			companyId, User.class.getName(), "OSB", "osbTrialPurchased", userId,
			true);
	}

	public List<LicenseKey> getAccountEntryLicenseKeys(long accountEntryId) {
		return licenseKeyPersistence.findByAccountEntryId(
			accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new LicenseKeyExpirationDateComparator());
	}

	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
		String assetReceiptLicenseUuid, boolean active) {

		return licenseKeyPersistence.findByARLU_A(
			assetReceiptLicenseUuid, active);
	}

	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return licenseKeyPersistence.findByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public int getAssetReceiptLicenseLicenseKeysCount(
		String assetReceiptLicenseUuid, boolean complimentary, boolean active) {

		return licenseKeyPersistence.countByARLU_C_A(
			assetReceiptLicenseUuid, complimentary, active);
	}

	public LicenseKey getFirstLicenseKey(
			long accountEntryId, OrderByComparator obc)
		throws PortalException {

		return licenseKeyPersistence.findByAccountEntryId_First(
			accountEntryId, obc);
	}

	public LicenseKey getLicenseKeyByUuid(String uuid) throws PortalException {
		List<LicenseKey> licenseKeys = licenseKeyPersistence.findByUuid(uuid);

		if (licenseKeys.isEmpty()) {
			throw new NoSuchLicenseKeyException("{uuid=" + uuid + "}");
		}
		else {
			return licenseKeys.get(0);
		}
	}

	public List<LicenseKey> getLicenseKeys(long userId, long accountEntryId) {
		return licenseKeyPersistence.findByU_AEI(userId, accountEntryId);
	}

	public List<LicenseKey> getLicenseKeys(long userId, String productId) {
		return licenseKeyPersistence.findByU_PI(userId, productId);
	}

	public List<LicenseKey> getLicenseKeys(String productId, String serverId) {
		return licenseKeyPersistence.findByPI_SI(productId, serverId);
	}

	public List<LicenseKey> getLicenseKeys(
		String assetReceiptLicenseUuid, String productId, String serverId,
		boolean active, int start, int end, OrderByComparator obc) {

		return licenseKeyPersistence.findByARLU_PI_SI_A(
			assetReceiptLicenseUuid, productId, serverId, active, start, end,
			obc);
	}

	public List<LicenseKey> getLicenseKeysByName(
		String productEntryName, String serverId, boolean active, int start,
		int end, OrderByComparator obc) {

		return licenseKeyPersistence.findByPEN_SI_A(
			productEntryName, serverId, active, start, end, obc);
	}

	public List<LicenseKey> getLicenseKeySetLicenseKeys(long licenseKeySetId) {
		return licenseKeyPersistence.findByLicenseKeySetId(licenseKeySetId);
	}

	public List<LicenseKey> getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, OrderByComparator obc) {

		return licenseKeyPersistence.findByOEI_C_A(
			offeringEntryIds, complimentary, active, start, end, obc);
	}

	public int getOfferingEntryGroupLicenseKeysCount(
		long[] offeringEntryIds, boolean complimentary, boolean active) {

		return licenseKeyPersistence.countByOEI_C_A(
			offeringEntryIds, complimentary, active);
	}

	public List<LicenseKey> getOfferingEntryLicenseKeys(long offeringEntryId) {
		return licenseKeyPersistence.findByOfferingEntryId(offeringEntryId);
	}

	public List<LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, boolean complimentary, boolean active) {

		return licenseKeyPersistence.findByOEI_C_A(
			offeringEntryId, complimentary, active);
	}

	public List<LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, long clusterId) {

		return licenseKeyPersistence.findByOEI_CI(offeringEntryId, clusterId);
	}

	public List<LicenseKey> getOfferingEntryLicenseKeys(
		long offeringEntryId, long clusterId, boolean active) {

		return licenseKeyPersistence.findByOEI_CI_A(
			offeringEntryId, clusterId, active);
	}

	public int getOfferingEntryLicenseKeysCount(long offeringEntryId) {
		return licenseKeyPersistence.countByOfferingEntryId(offeringEntryId);
	}

	public int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, boolean complimentary, boolean active) {

		int count = licenseKeyPersistence.countByOEI_NotLET_C_A(
			offeringEntryId, LicenseEntryConstants.TYPE_CLUSTER, complimentary,
			active);

		List<LicenseKey> licenseKeys = licenseKeyPersistence.findByOEI_LET_C_A(
			offeringEntryId, LicenseEntryConstants.TYPE_CLUSTER, complimentary,
			active);

		Set<Long> clusterIds = new HashSet<>();

		for (LicenseKey licenseKey : licenseKeys) {
			if (licenseKey.getLicenseVersion() >= 3) {
				if (clusterIds.contains(licenseKey.getClusterId())) {
					continue;
				}

				clusterIds.add(licenseKey.getClusterId());
			}

			count += licenseKey.getMaxServers();
		}

		return count;
	}

	public int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, long clusterId) {

		return licenseKeyPersistence.countByOEI_CI(offeringEntryId, clusterId);
	}

	public int getOfferingEntryLicenseKeysCount(
		long offeringEntryId, long clusterId, boolean active) {

		return licenseKeyPersistence.countByOEI_CI_A(
			offeringEntryId, clusterId, active);
	}

	public int getUserLicenseKeysCount(long userId, long accountEntryId) {
		return licenseKeyPersistence.countByU_AEI(userId, accountEntryId);
	}

	public LicenseKey renewLicenseKey(
			long userId, long licenseKeyId, Date startDate, Date expirationDate)
		throws PortalException {

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		if (!licenseKey.isActive()) {
			throw new LicenseKeyActiveException();
		}

		updateLicenseKey(userId, licenseKeyId, false);

		return addLicenseKey(
			userId, licenseKey.getAssetReceiptLicenseUuid(),
			licenseKey.getLicenseEntryType(), licenseKey.getProductEntryName(),
			licenseKey.getProductId(), licenseKey.getProductVersion(),
			licenseKey.getOwner(), licenseKey.getMaxUsers(),
			licenseKey.getDescription(), licenseKey.getHostName(),
			licenseKey.getIpAddresses(), licenseKey.getMacAddresses(),
			licenseKey.getServerId(), startDate, expirationDate);
	}

	public LicenseKey renewLicenseKey(
			long userId, long licenseKeyId, Date startDate, int renewTime)
		throws Exception {

		User user = userLocalService.getUser(userId);

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		LicenseKeySet licenseKeySet = licenseKeySetPersistence.findByPrimaryKey(
			licenseKey.getLicenseKeySetId());
		ProductEntry productEntry = productEntryPersistence.findByPrimaryKey(
			licenseKey.getProductEntryId());
		LicenseEntry licenseEntry = licenseKey.getLicenseEntry();

		if (!licenseKey.canRenew()) {
			throw new LicenseKeyRenewException();
		}

		licenseKey.setActive(false);

		licenseKeyPersistence.update(licenseKey);

		AccountEntry accountEntry = licenseKey.getAccountEntry();

		String description = String.valueOf(renewTime) + "-Day ";

		if (accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) {
			description += "Trial License";
		}
		else {
			description += "License";
		}

		Date expirationDate = new Date(
			startDate.getTime() + (renewTime * Time.DAY));

		LicenseKey renewedLicenseKey = doAddLicenseKeyVersion3_4(
			new Date(), user, licenseKeySet, licenseKeySet.getName(),
			accountEntry, licenseKey.getOfferingEntry(),
			licenseKey.getLicenseEntry(), productEntry, licenseEntry.getType(),
			licenseKey.getLicenseVersion(), licenseKey.getProductVersion(),
			licenseKey.getClusterId(), licenseKey.getOwner(),
			licenseKey.getMaxServers(), licenseKey.getMaxHttpSessions(),
			description, new String[] {licenseKey.getHostName()},
			new String[] {licenseKey.getIpAddresses()},
			new String[] {licenseKey.getMacAddresses()},
			new String[] {licenseKey.getServerId()}, startDate, expirationDate,
			licenseKey.getAdditionalInfo(), licenseKey.getComplimentary(),
			true);

		if (accountEntry.getType() == AccountEntryConstants.TYPE_TRIAL) {
			OfferingEntry offeringEntry = renewedLicenseKey.getOfferingEntry();

			offeringEntry.setSupportEndDate(
				renewedLicenseKey.getExpirationDate());

			offeringEntryPersistence.update(offeringEntry);

			User trialUser = userLocalService.getUser(accountEntry.getUserId());

			sendRegisteredEmail(
				trialUser.getEmailAddress(), trialUser.getFullName(),
				renewedLicenseKey);
		}

		return renewedLicenseKey;
	}

	public LicenseKey renewTrialLicenseKey(long userId) throws Exception {
		User user = userLocalService.getUser(userId);

		AccountEntry accountEntry =
			accountEntryLocalService.fetchUserTrialAccountEntry(userId);

		if (accountEntry == null) {
			return null;
		}

		LicenseKey lastLicenseKey =
			licenseKeyPersistence.findByAccountEntryId_First(
				accountEntry.getAccountEntryId(),
				new LicenseKeyExpirationDateComparator(false));

		LicenseKeySet licenseKeySet = licenseKeySetPersistence.findByPrimaryKey(
			lastLicenseKey.getLicenseKeySetId());

		OfferingEntry offeringEntry = offeringEntryPersistence.findByPrimaryKey(
			lastLicenseKey.getOfferingEntryId());

		lastLicenseKey.setActive(false);

		licenseKeyPersistence.update(lastLicenseKey);

		int productVersion = lastLicenseKey.getProductVersion();

		PortletPreferences portletPreferences =
			AdminUtil.getPortletPreferences();

		long productEntryId = GetterUtil.getLong(
			portletPreferences.getValue("trialProductEntryId", null));

		if (productEntryId == offeringEntry.getProductEntryId()) {
			productVersion = AdminUtil.getLatestProductVersion(
				portletPreferences, offeringEntry.getProductEntryId());
		}

		LicenseKey licenseKey = addLicenseKey(
			userId, licenseKeySet, "Trial Licenses", offeringEntry,
			lastLicenseKey.getLicenseEntry(), null, productVersion, 0,
			user.getFullName(), 1, 5, "30-Day Trial License", new String[0],
			new String[0], new String[0],
			new String[] {LicenseKeyConstants.SERVER_ID_DEVELOPER}, new Date(),
			null, StringPool.BLANK, false, true);

		offeringEntry.setSupportEndDate(licenseKey.getExpirationDate());

		offeringEntryPersistence.update(offeringEntry);

		sendRegisteredEmail(
			user.getEmailAddress(), user.getFullName(), licenseKey);

		return licenseKey;
	}

	public List<LicenseKey> search(
		Long createUserId, int createDateGTDay, int createDateGTMonth,
		int createDateGTYear, int createDateLTDay, int createDateLTMonth,
		int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		String accountEntryName, String licenseKeySetName, int startDateGTDay,
		int startDateGTMonth, int startDateGTYear, int startDateLTDay,
		int startDateLTMonth, int startDateLTYear, long[] licenseEntryIds,
		long[] productEntryIds, String productEntryName, String productId,
		int[] productVersions, String owner, String description,
		String hostName, String ipAddress, String macAddress, String serverId,
		String key, int expirationDateGTDay, int expirationDateGTMonth,
		int expirationDateGTYear, int expirationDateLTDay,
		int expirationDateLTMonth, int expirationDateLTYear,
		LinkedHashMap<String, Object> params, boolean andSearch, int start,
		int end, OrderByComparator obc) {

		Date createDateGT = PortalUtil.getDate(
			createDateGTMonth, createDateGTDay, createDateGTYear);
		Date createDateLT = PortalUtil.getDate(
			createDateLTMonth, createDateLTDay, createDateLTYear);
		Date modifiedDateGT = PortalUtil.getDate(
			modifiedDateGTMonth, modifiedDateGTDay, modifiedDateGTYear);
		Date modifiedDateLT = PortalUtil.getDate(
			modifiedDateLTMonth, modifiedDateLTDay, modifiedDateLTYear);
		Date startDateGT = PortalUtil.getDate(
			startDateGTMonth, startDateGTDay, startDateGTYear);
		Date startDateLT = PortalUtil.getDate(
			startDateLTMonth, startDateLTDay, startDateLTYear);
		Date expirationDateGT = PortalUtil.getDate(
			expirationDateGTMonth, expirationDateGTDay, expirationDateGTYear);
		Date expirationDateLT = PortalUtil.getDate(
			expirationDateLTMonth, expirationDateLTDay, expirationDateLTYear);

		return licenseKeyFinder.findByU_C_M_M_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
			createUserId, createDateGT, createDateLT, modifiedUserId,
			modifiedDateGT, modifiedDateLT, accountEntryName, licenseKeySetName,
			startDateGT, startDateLT, licenseEntryIds, productEntryIds,
			productEntryName, productId, productVersions, owner, description,
			hostName, ipAddress, macAddress, serverId, key, expirationDateGT,
			expirationDateLT, params, andSearch, start, end, obc);
	}

	public List<LicenseKey> search(
		String keywords, LinkedHashMap<String, Object> params, int start,
		int end, OrderByComparator obc) {

		return licenseKeyFinder.findByKeywords(
			keywords, params, start, end, obc);
	}

	public int searchCount(
		Long createUserId, int createDateGTDay, int createDateGTMonth,
		int createDateGTYear, int createDateLTDay, int createDateLTMonth,
		int createDateLTYear, Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		String accountEntryName, String licenseKeySetName, int startDateGTDay,
		int startDateGTMonth, int startDateGTYear, int startDateLTDay,
		int startDateLTMonth, int startDateLTYear, long[] licenseEntryIds,
		long[] productEntryIds, String productEntryName, String productId,
		int[] productVersions, String owner, String description,
		String hostName, String ipAddress, String macAddress, String serverId,
		String key, int expirationDateGTDay, int expirationDateGTMonth,
		int expirationDateGTYear, int expirationDateLTDay,
		int expirationDateLTMonth, int expirationDateLTYear,
		LinkedHashMap<String, Object> params, boolean andSearch) {

		Date createDateGT = PortalUtil.getDate(
			createDateGTMonth, createDateGTDay, createDateGTYear);
		Date createDateLT = PortalUtil.getDate(
			createDateLTMonth, createDateLTDay, createDateLTYear);
		Date modifiedDateGT = PortalUtil.getDate(
			modifiedDateGTMonth, modifiedDateGTDay, modifiedDateGTYear);
		Date modifiedDateLT = PortalUtil.getDate(
			modifiedDateLTMonth, modifiedDateLTDay, modifiedDateLTYear);
		Date startDateGT = PortalUtil.getDate(
			startDateGTMonth, startDateGTDay, startDateGTYear);
		Date startDateLT = PortalUtil.getDate(
			startDateLTMonth, startDateLTDay, startDateLTYear);
		Date expirationDateGT = PortalUtil.getDate(
			expirationDateGTMonth, expirationDateGTDay, expirationDateGTYear);
		Date expirationDateLT = PortalUtil.getDate(
			expirationDateLTMonth, expirationDateLTDay, expirationDateLTYear);

		return licenseKeyFinder.countByU_C_M_M_A_L_S_L_P_P_P_P_O_D_H_I_M_S_E_A(
			createUserId, createDateGT, createDateLT, modifiedUserId,
			modifiedDateGT, modifiedDateLT, accountEntryName, licenseKeySetName,
			startDateGT, startDateLT, licenseEntryIds, productEntryIds,
			productEntryName, productId, productVersions, owner, description,
			hostName, ipAddress, macAddress, serverId, key, expirationDateGT,
			expirationDateLT, params, andSearch);
	}

	public int searchCount(
		String keywords, LinkedHashMap<String, Object> params) {

		return licenseKeyFinder.countByKeywords(keywords, params);
	}

	public void sendRegisteredEmail(User user, LicenseKey licenseKey)
		throws Exception {

		sendRegisteredEmail(
			user.getEmailAddress(), user.getFullName(), licenseKey);
	}

	public void sendTrialRenewalNotificationEmail(
			String emailAddress, long accountEntryId)
		throws Exception {

		LicenseKey licenseKey =
			licenseKeyPersistence.findByAccountEntryId_First(
				accountEntryId, new LicenseKeyExpirationDateComparator(false));

		Map<Locale, String> subjectMap =
			LicenseUtil.getEmailNotificationTrialLicenseSubjectMap();
		Map<Locale, String> bodyMap =
			LicenseUtil.getEmailNotificationTrialLicenseBodyMap();

		String layoutURL = PortalUtil.getLayoutFullURL(
			OSBConstants.GROUP_LICENSE_ID, OSBPortletKeys.OSB_LICENSE);

		String licenseURL =
			layoutURL + Portal.FRIENDLY_URL_SEPARATOR + "license/offering/" +
				licenseKey.getOfferingEntryId();

		AccountEntry accountEntry = licenseKey.getAccountEntry();

		for (Map.Entry<Locale, String> subjectEntry : subjectMap.entrySet()) {
			String subject = StringUtil.replace(
				subjectEntry.getValue(),
				new String[] {"[$ACCOUNT_ENTRY_NAME$]"},
				new String[] {accountEntry.getName()});

			subjectEntry.setValue(subject);
		}

		User user = userLocalService.getUser(licenseKey.getUserId());

		Country country = countryService.getCountry(
			accountEntry.getCountryId());

		for (Map.Entry<Locale, String> bodyEntry : bodyMap.entrySet()) {
			String body = StringUtil.replace(
				bodyEntry.getValue(),
				new String[] {
					"[$ACCOUNT_ENTRY_NAME$]", "[$LICENSE_URL$]",
					"[$USER_COUNTRY$]", "[$USER_EMAIL_ADDRESS$]",
					"[$USER_FULL_NAME$]"
				},
				new String[] {
					accountEntry.getName(), licenseURL, country.getName(),
					user.getEmailAddress(), user.getFullName()
				});

			bodyEntry.setValue(body);
		}

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);
		subscriptionSender.setFrom("no-reply@liferay.com", "Licensing");
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"license_key_notification", licenseKey.getLicenseKeyId());
		subscriptionSender.setPortletId(OSBPortletKeys.OSB_LICENSE);
		subscriptionSender.setReplyToAddress("sales@liferay.com");

		subscriptionSender.addRuntimeSubscribers(emailAddress, "Liferay, Inc.");

		subscriptionSender.flushNotificationsAsync();
	}

	public void updateLicenseKey(long userId, long licenseKeyId, boolean active)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		licenseKey.setModifiedUserId(user.getUserId());
		licenseKey.setModifiedUserName(user.getFullName());
		licenseKey.setModifiedDate(new Date());
		licenseKey.setActive(active);

		licenseKeyPersistence.update(licenseKey);
	}

	public LicenseKey updateLicenseKey(
			long licenseKeyId, long accountEntryId, long offeringEntryId,
			long orderEntryId)
		throws PortalException {

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		licenseKey.setAccountEntryId(accountEntryId);
		licenseKey.setOfferingEntryId(offeringEntryId);
		licenseKey.setOrderEntryId(orderEntryId);

		return licenseKeyPersistence.update(licenseKey);
	}

	public LicenseKey updateLicenseKey(
			long userId, long licenseKeyId, long licenseKeySetId,
			long offeringEntryId, String name, boolean active)
		throws PortalException {

		LicenseKey licenseKey = licenseKeyPersistence.findByPrimaryKey(
			licenseKeyId);

		OfferingEntry offeringEntry = offeringEntryPersistence.findByPrimaryKey(
			offeringEntryId);

		String type = licenseKey.getLicenseEntryType();

		List<LicenseKey> clusterLicenseKeys = getClusterLicenseKeys(
			licenseKey, type);

		int activeClusterCount = 0;

		for (LicenseKey clusterLicenseKey : clusterLicenseKeys) {
			if (clusterLicenseKey.isActive()) {
				activeClusterCount++;
			}
		}

		boolean curActive = false;
		int serverCount = 0;

		if (licenseKey.getLicenseVersion() >= 3) {
			if (activeClusterCount > 0) {
				curActive = true;
			}

			serverCount = licenseKey.getMaxServers();
		}
		else {
			curActive = licenseKey.isActive();
			serverCount = clusterLicenseKeys.size();
		}

		if ((!curActive && active) ||
			((offeringEntryId != licenseKey.getOfferingEntryId()) &&
			 (curActive || active))) {

			validate(offeringEntry, serverCount);
		}

		if (licenseKey.getOfferingEntryId() != offeringEntryId) {
			OfferingEntry oldOfferingEntry = licenseKey.getOfferingEntry();

			ProductEntry oldProductEntry =
				productEntryLocalService.fetchProductEntry(
					oldOfferingEntry.getProductEntryId());

			ProductEntry newProductEntry = offeringEntry.getProductEntry();

			if ((oldProductEntry != null) &&
				(oldProductEntry.getEnvironment() !=
					newProductEntry.getEnvironment())) {

				throw new LicenseKeyProductEntryException();
			}
		}

		if (licenseKey.getLicenseVersion() >= 3) {
			if (!licenseKey.isActive() && active &&
				type.equals(LicenseEntryConstants.TYPE_CLUSTER)) {

				if (licenseKey.getMaxServers() <= activeClusterCount) {
					throw new MaximumLicenseKeyException();
				}
			}

			doUpdateLicenseKeyVersion3(
				new Date(), licenseKey, offeringEntry, clusterLicenseKeys,
				userId, licenseKeyId, licenseKeySetId, offeringEntryId, name,
				active);
		}
		else {
			doUpdateLicenseKey(
				new Date(), licenseKey, offeringEntry, clusterLicenseKeys,
				userId, licenseKeyId, licenseKeySetId, offeringEntryId, name,
				active);
		}

		return licenseKey;
	}

	protected LicenseKey doAddLicenseKey(
			Date now, User user, LicenseKeySet licenseKeySet, String name,
			AccountEntry accountEntry, OfferingEntry offeringEntry,
			LicenseEntry licenseEntry, ProductEntry productEntry,
			String licenseEntryType, int licenseVersion, int productVersion,
			long clusterId, String owner, int maxServers, String description,
			String[] serverIds, Date startDate, Date expirationDate,
			String additionalInfo, boolean complimentary, boolean active)
		throws PortalException {

		if (clusterId <= 0) {
			clusterId = counterLocalService.increment(
				getCounterName(offeringEntry.getOfferingEntryId()));
		}

		String accountEntryName = LicenseUtil.trimText(accountEntry.getName());
		String licenseEntryName = LicenseUtil.trimText(licenseEntry.getName());
		String productEntryName = LicenseUtil.trimText(productEntry.getName());
		String productVersionLabel = LicenseUtil.trimText(
			LicenseKeyConstants.getProductVersionLabel(productVersion));

		owner = LicenseUtil.trimText(owner);

		if ((licenseVersion != 2) ||
			(!licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) &&
			 !licenseEntryType.equals(
				 LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER))) {

			maxServers = 1;
		}

		description = LicenseUtil.trimText(description);

		int maxHttpSessions = 0;

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
			licenseEntryType.equals(
				LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER) ||
			licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL)) {

			maxHttpSessions = 10;
		}

		String[] macAddresses = null;

		if (((licenseVersion == 2) &&
			 licenseEntryType.equals(LicenseEntryConstants.TYPE_PRODUCTION)) ||
			((licenseVersion != 2) &&
			 (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
			  licenseEntryType.equals(
				  LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)))) {

			macAddresses = serverIds;
		}
		else {
			macAddresses = new String[serverIds.length];
		}

		startDate = DateUtils.round(startDate, Calendar.SECOND);
		expirationDate = DateUtils.round(expirationDate, Calendar.SECOND);

		String key = KeyGenerator.generate(
			accountEntryName, licenseEntryName, licenseEntryType,
			licenseVersion, productEntryName,
			ProductEntryConstants.PRODUCT_ID_PORTAL, productVersionLabel, owner,
			maxServers, maxHttpSessions, 0, 0, 0, description, null, null, null,
			serverIds, startDate, expirationDate);

		LicenseKey licenseKey = null;

		OfferingEntryGroup offeringEntryGroup =
			offeringEntry.getOfferingEntryGroup();

		List<OfferingEntry> availableOfferingEntries =
			offeringEntryGroup.getAvailableLicenseOfferingEntries();

		availableOfferingEntries.remove(offeringEntry);

		int availableServers = offeringEntry.getAvailableServers();

		for (int i = 0; i < serverIds.length; i++) {
			if (availableServers <= 0) {
				if (!availableOfferingEntries.isEmpty()) {
					offeringEntry = availableOfferingEntries.remove(0);

					availableServers = offeringEntry.getAvailableServers();
				}
			}

			licenseKey = doAddLicenseKey(
				user, now, licenseKeySet, offeringEntry, licenseEntry,
				accountEntryName, licenseEntryName, licenseEntryType,
				licenseVersion, productEntryName, productVersion,
				productVersionLabel, clusterId, owner, maxServers, 0, 0,
				maxHttpSessions, 0, description, StringPool.BLANK,
				StringPool.BLANK, macAddresses[i], serverIds[i], key, startDate,
				expirationDate, additionalInfo, complimentary, active);
		}

		return licenseKey;
	}

	protected LicenseKey doAddLicenseKey(
		User user, Date now, LicenseKeySet licenseKeySet,
		OfferingEntry offeringEntry, LicenseEntry licenseEntry,
		String accountEntryName, String licenseEntryName,
		String licenseEntryType, int licenseVersion, String productEntryName,
		int productVersion, String productVersionLabel, long clusterId,
		String owner, int maxServers, long maxConcurrentUsers, long maxUsers,
		int maxHttpSessions, int sizing, String description, String hostName,
		String ipAddresses, String macAddresses, String serverId, String key,
		Date startDate, Date expirationDate, String additionalInfo,
		boolean complimentary, boolean active) {

		long licenseKeyId = counterLocalService.increment();

		LicenseKey licenseKey = licenseKeyPersistence.create(licenseKeyId);

		licenseKey.setUserId(user.getUserId());
		licenseKey.setUserName(user.getFullName());
		licenseKey.setCreateDate(now);
		licenseKey.setModifiedUserId(user.getUserId());
		licenseKey.setModifiedUserName(user.getFullName());
		licenseKey.setModifiedDate(now);
		licenseKey.setLicenseKeySetId(licenseKeySet.getLicenseKeySetId());
		licenseKey.setAccountEntryId(offeringEntry.getAccountEntryId());
		licenseKey.setOrderEntryId(offeringEntry.getOrderEntryId());
		licenseKey.setOfferingEntryId(offeringEntry.getOfferingEntryId());
		licenseKey.setProductEntryId(offeringEntry.getProductEntryId());
		licenseKey.setSupportResponseId(offeringEntry.getSupportResponseId());
		licenseKey.setLicenseEntryId(licenseEntry.getLicenseEntryId());
		licenseKey.setProductEntryId(licenseEntry.getProductEntryId());
		licenseKey.setAccountEntryName(accountEntryName);
		licenseKey.setLicenseEntryName(licenseEntryName);
		licenseKey.setLicenseEntryType(licenseEntryType);
		licenseKey.setLicenseVersion(licenseVersion);
		licenseKey.setProductEntryName(productEntryName);
		licenseKey.setProductId(ProductEntryConstants.PRODUCT_ID_PORTAL);
		licenseKey.setProductVersion(productVersion);
		licenseKey.setProductVersionLabel(productVersionLabel);
		licenseKey.setClusterId(clusterId);
		licenseKey.setOwner(owner);
		licenseKey.setMaxServers(maxServers);
		licenseKey.setMaxConcurrentUsers(maxConcurrentUsers);
		licenseKey.setMaxUsers(maxUsers);
		licenseKey.setMaxHttpSessions(maxHttpSessions);
		licenseKey.setSizing(sizing);
		licenseKey.setDescription(description);
		licenseKey.setHostName(hostName);
		licenseKey.setIpAddresses(ipAddresses);
		licenseKey.setMacAddresses(macAddresses);
		licenseKey.setServerId(serverId);
		licenseKey.setKey(key);
		licenseKey.setStartDate(startDate);
		licenseKey.setExpirationDate(expirationDate);
		licenseKey.setAdditionalInfo(additionalInfo);
		licenseKey.setComplimentary(complimentary);
		licenseKey.setActive(active);

		return licenseKeyPersistence.update(licenseKey);
	}

	protected LicenseKey doAddLicenseKeyVersion3_4(
			Date now, User user, LicenseKeySet licenseKeySet, String name,
			AccountEntry accountEntry, OfferingEntry offeringEntry,
			LicenseEntry licenseEntry, ProductEntry productEntry,
			String licenseEntryType, int licenseVersion, int productVersion,
			long clusterId, String owner, int maxServers, int maxHttpSessions,
			String description, String[] hostNames, String[] ipAddresses,
			String[] macAddresses, String[] serverIds, Date startDate,
			Date expirationDate, String additionalInfo, boolean complimentary,
			boolean active)
		throws PortalException {

		String accountEntryName = LicenseUtil.trimText(accountEntry.getName());
		String licenseEntryName = LicenseUtil.trimText(licenseEntry.getName());
		String productEntryName = LicenseUtil.trimText(productEntry.getName());
		String productVersionLabel = LicenseUtil.trimText(
			LicenseKeyConstants.getProductVersionLabel(productVersion));

		owner = LicenseUtil.trimText(owner);

		if (!licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER)) {
			maxServers = 1;
		}

		description = LicenseUtil.trimText(description);

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_DEVELOPER) ||
			licenseEntryType.equals(
				LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {

			if (maxHttpSessions == 0) {
				maxHttpSessions = 10;
			}
		}
		else {
			maxHttpSessions = 0;
		}

		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER)) {
			if (clusterId <= 0) {
				clusterId = counterLocalService.increment(
					getCounterName(offeringEntry.getOfferingEntryId()));
			}
			else {
				List<LicenseKey> clusterLicenseKeys =
					licenseKeyPersistence.findByOEI_CI(
						offeringEntry.getOfferingEntryId(), clusterId);

				if (!clusterLicenseKeys.isEmpty()) {
					LicenseKey clusterLicenseKey = clusterLicenseKeys.get(0);

					maxServers = clusterLicenseKey.getMaxServers();
					startDate = clusterLicenseKey.getStartDate();
					expirationDate = clusterLicenseKey.getExpirationDate();
				}
			}
		}

		startDate = DateUtils.round(startDate, Calendar.SECOND);
		expirationDate = DateUtils.round(expirationDate, Calendar.SECOND);

		LicenseKey licenseKey = null;

		int keyCount = 0;

		if (ArrayUtil.isNotEmpty(serverIds)) {
			keyCount = serverIds.length;
		}
		else if (hostNames != null) {
			keyCount = hostNames.length;
		}

		OfferingEntryGroup offeringEntryGroup =
			offeringEntry.getOfferingEntryGroup();

		List<OfferingEntry> availableOfferingEntries =
			offeringEntryGroup.getAvailableLicenseOfferingEntries();

		availableOfferingEntries.remove(offeringEntry);

		int availableServers = offeringEntry.getAvailableServers();

		for (int i = 0; i < keyCount; i++) {
			if (availableServers <= 0) {
				if (!availableOfferingEntries.isEmpty()) {
					offeringEntry = availableOfferingEntries.remove(0);

					availableServers = offeringEntry.getAvailableServers();
				}
			}

			int sizing = 0;

			if ((licenseVersion >= 6) &&
				licenseEntryType.equals(
					LicenseEntryConstants.TYPE_PRODUCTION)) {

				sizing = offeringEntry.getSizing();
			}

			String hostName = StringPool.BLANK;
			String curIpAddresses = StringPool.BLANK;
			String curMacAddresses = StringPool.BLANK;
			String serverId = StringPool.BLANK;

			if ((hostNames != null) && (hostNames.length > i)) {
				hostName = hostNames[i];
				curIpAddresses = ipAddresses[i];
				curMacAddresses = macAddresses[i];
			}

			if ((serverIds != null) && (serverIds.length > i)) {
				serverId = serverIds[i];
			}

			if (!licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER)) {
				clusterId = counterLocalService.increment(
					getCounterName(offeringEntry.getOfferingEntryId()));
			}

			String key = KeyGenerator.generate(
				accountEntryName, licenseEntryName, licenseEntryType,
				licenseVersion, productEntryName,
				ProductEntryConstants.PRODUCT_ID_PORTAL, productVersionLabel,
				owner, maxServers, maxHttpSessions,
				offeringEntry.getMaxConcurrentUsers(),
				offeringEntry.getMaxUsers(), sizing, description, hostName,
				curIpAddresses, curMacAddresses, new String[] {serverId},
				startDate, expirationDate);

			licenseKey = doAddLicenseKey(
				user, now, licenseKeySet, offeringEntry, licenseEntry,
				accountEntryName, licenseEntryName, licenseEntryType,
				licenseVersion, productEntryName, productVersion,
				productVersionLabel, clusterId, owner, maxServers,
				offeringEntry.getMaxConcurrentUsers(),
				offeringEntry.getMaxUsers(), maxHttpSessions, sizing,
				description, hostName, curIpAddresses, curMacAddresses,
				serverId, key, startDate, expirationDate, additionalInfo,
				complimentary, active);

			availableServers--;
		}

		return licenseKey;
	}

	protected void doUpdateLicenseKey(
			Date now, LicenseKey licenseKey, OfferingEntry offeringEntry,
			List<LicenseKey> clusterLicenseKeys, long userId, long licenseKeyId,
			long licenseKeySetId, long offeringEntryId, String name,
			boolean active)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		if (licenseKeySetId <= 0) {
			LicenseKeySet licenseKeySet =
				licenseKeySetLocalService.addLicenseKeySet(
					userId, licenseKey.getAccountEntryId(), name);

			licenseKeySetId = licenseKeySet.getLicenseKeySetId();
		}
		else {
			licenseKeySetPersistence.findByPrimaryKey(licenseKeySetId);
		}

		long clusterId = licenseKey.getClusterId();

		if (offeringEntryId != licenseKey.getOfferingEntryId()) {
			clusterId = counterLocalService.increment(
				getCounterName(offeringEntryId));
		}

		for (LicenseKey clusterLicenseKey : clusterLicenseKeys) {
			clusterLicenseKey.setModifiedUserId(user.getUserId());
			clusterLicenseKey.setModifiedUserName(user.getFullName());
			clusterLicenseKey.setModifiedDate(now);
			clusterLicenseKey.setLicenseKeySetId(licenseKeySetId);
			clusterLicenseKey.setOrderEntryId(offeringEntry.getOrderEntryId());
			clusterLicenseKey.setOfferingEntryId(offeringEntryId);
			clusterLicenseKey.setProductEntryId(
				offeringEntry.getProductEntryId());
			clusterLicenseKey.setSupportResponseId(
				offeringEntry.getSupportResponseId());
			clusterLicenseKey.setClusterId(clusterId);
			clusterLicenseKey.setActive(active);

			licenseKeyPersistence.update(clusterLicenseKey);
		}
	}

	protected void doUpdateLicenseKeyVersion3(
			Date now, LicenseKey licenseKey, OfferingEntry offeringEntry,
			List<LicenseKey> clusterLicenseKeys, long userId, long licenseKeyId,
			long licenseKeySetId, long offeringEntryId, String name,
			boolean active)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		if (licenseKeySetId <= 0) {
			LicenseKeySet licenseKeySet =
				licenseKeySetLocalService.addLicenseKeySet(
					userId, licenseKey.getAccountEntryId(), name);

			licenseKeySetId = licenseKeySet.getLicenseKeySetId();
		}
		else {
			licenseKeySetPersistence.findByPrimaryKey(licenseKeySetId);
		}

		long clusterId = licenseKey.getClusterId();

		if (offeringEntryId != licenseKey.getOfferingEntryId()) {
			clusterId = counterLocalService.increment(
				getCounterName(offeringEntryId));
		}

		for (LicenseKey clusterLicenseKey : clusterLicenseKeys) {
			if (clusterLicenseKey.getLicenseKeyId() ==
					licenseKey.getLicenseKeyId()) {

				clusterLicenseKey.setActive(active);
			}

			clusterLicenseKey.setModifiedUserId(user.getUserId());
			clusterLicenseKey.setModifiedUserName(user.getFullName());
			clusterLicenseKey.setModifiedDate(now);
			clusterLicenseKey.setLicenseKeySetId(licenseKeySetId);
			clusterLicenseKey.setOrderEntryId(offeringEntry.getOrderEntryId());
			clusterLicenseKey.setOfferingEntryId(offeringEntryId);
			clusterLicenseKey.setProductEntryId(
				offeringEntry.getProductEntryId());
			clusterLicenseKey.setSupportResponseId(
				offeringEntry.getSupportResponseId());
			clusterLicenseKey.setClusterId(clusterId);

			licenseKeyPersistence.update(clusterLicenseKey);
		}
	}

	protected List<LicenseKey> getClusterLicenseKeys(
		LicenseKey licenseKey, String type) {

		if ((type.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
			 type.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) &&
			(licenseKey.getLicenseVersion() != 2)) {

			return licenseKeyPersistence.findByOEI_CI(
				licenseKey.getOfferingEntryId(), licenseKey.getClusterId());
		}
		else {
			return Arrays.asList(licenseKey);
		}
	}

	protected String getCounterName(long offeringEntryId) {
		return LicenseKey.class.getName(
		).concat(
			StringPool.POUND
		).concat(
			String.valueOf(offeringEntryId)
		);
	}

	protected OfferingEntry getDeveloperOfferingEntry(
			long accountEntryId, long primaryProductEntryId, int version,
			int sizing)
		throws PortalException {

		ProductEntry productEntry = getDeveloperProductEntry(
			primaryProductEntryId);

		OfferingEntry offeringEntry =
			offeringEntryPersistence.fetchByAEI_PEI_T_First(
				accountEntryId, productEntry.getProductEntryId(),
				OfferingEntryConstants.TYPE_DEVELOPER, null);

		if (offeringEntry != null) {
			return offeringEntry;
		}

		OrderEntry orderEntry = getDeveloperOrderEntry(accountEntryId);

		SupportResponse supportResponse =
			supportResponseLocalService.getSupportResponseByName("Limited");

		return offeringEntryLocalService.addOfferingEntry(
			OSBConstants.USER_DEFAULT_USER_ID, accountEntryId,
			orderEntry.getOrderEntryId(), productEntry.getProductEntryId(),
			supportResponse.getSupportResponseId(), StringPool.BLANK,
			OfferingEntryConstants.TYPE_DEVELOPER, version, true,
			OfferingDefinitionConstants.LIFETIME_INDEFINITE_VALUE, 0, 0, false,
			Time.MINUTE, sizing, OfferingEntryConstants.QUANTITY_UNLIMITED,
			OfferingEntryConstants.STATUS_ACTIVE);
	}

	protected OrderEntry getDeveloperOrderEntry(long accountEntryId)
		throws PortalException {

		OfferingEntry offeringEntry =
			offeringEntryPersistence.fetchByAEI_T_First(
				accountEntryId, OfferingEntryConstants.TYPE_DEVELOPER, null);

		if (offeringEntry != null) {
			return offeringEntry.getOrderEntry();
		}

		Calendar startCal = Calendar.getInstance();

		return orderEntryLocalService.addOrderEntry(
			OSBConstants.USER_DEFAULT_USER_ID, accountEntryId, StringPool.BLANK,
			startCal.get(Calendar.MONTH), startCal.get(Calendar.DATE),
			startCal.get(Calendar.YEAR), false, 0, 0, 0,
			WorkflowConstants.STATUS_APPROVED, StringPool.BLANK,
			new ArrayList<OfferingEntry>());
	}

	protected ProductEntry getDeveloperProductEntry(long primaryProductEntryId)
		throws PortalException {

		ProductEntry productEntry = productEntryPersistence.findByPrimaryKey(
			primaryProductEntryId);

		List<ProductEntry> productEntries =
			productEntryPersistence.findByEnvironment(
				ProductEntryConstants.ENVIRONMENT_DEVELOPMENT);

		for (ProductEntry curProductEntry : productEntries) {
			if ((curProductEntry.isDigitalEnterprise() &&
				 productEntry.isDigitalEnterprise()) ||
				(curProductEntry.isPortal() && productEntry.isPortal())) {

				return curProductEntry;
			}
		}

		return null;
	}

	protected OfferingEntry getPrimaryOfferingEntry(
			long accountEntryId, String productEntryRootName)
		throws PortalException {

		LinkedHashMap params = new LinkedHashMap();

		params.put("license", StringPool.BLANK);
		params.put("productEntry", ProductEntryConstants.TYPE_PRIMARY);

		List<OfferingEntry> offeringEntries = offeringEntryLocalService.search(
			0, accountEntryId, new int[] {OfferingEntryConstants.TYPE_REGULAR},
			new int[] {OfferingEntryConstants.STATUS_ACTIVE}, 0, 0, 0, 0, 0, 0,
			params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (OfferingEntry offeringEntry : offeringEntries) {
			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (productEntry.getEnvironment() ==
					ProductEntryConstants.ENVIRONMENT_DEVELOPMENT) {

				continue;
			}

			if ((productEntryRootName.equals(
					ProductEntryConstants.ROOT_NAME_DIGITAL_ENTERPRISE) &&
				 productEntry.isDigitalEnterprise()) ||
				(productEntryRootName.equals(
					ProductEntryConstants.ROOT_NAME_PORTAL) &&
				 productEntry.isPortal())) {

				return offeringEntry;
			}
		}

		return null;
	}

	protected int getProductVersion(int productMinorVersion) {
		if (productMinorVersion ==
				ProductEntryConstants.DIGITAL_ENTERPRISE_MINOR_VERSION_7_2) {

			return ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_2_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.
						DIGITAL_ENTERPRISE_MINOR_VERSION_7_1) {

			return ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_1_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.
						DIGITAL_ENTERPRISE_MINOR_VERSION_7_0) {

			return ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.PORTAL_MINOR_VERSION_5_2) {

			return ProductEntryConstants.PORTAL_VERSION_5_2_9;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.PORTAL_MINOR_VERSION_6_0) {

			return ProductEntryConstants.PORTAL_VERSION_6_0_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.PORTAL_MINOR_VERSION_6_1) {

			return ProductEntryConstants.PORTAL_VERSION_6_1_10;
		}
		else if (productMinorVersion ==
					ProductEntryConstants.PORTAL_MINOR_VERSION_6_2) {

			return ProductEntryConstants.PORTAL_VERSION_6_2_10;
		}

		return 0;
	}

	protected void sendRegisteredEmail(
			String emailAddress, String fullName, LicenseKey licenseKey)
		throws Exception {

		Map<Locale, String> subjectMap =
			LicenseUtil.getEmailRegisteredTrialLicenseSubjectMap();
		Map<Locale, String> bodyMap =
			LicenseUtil.getEmailRegisteredTrialLicenseBodyMap();

		File file = LicenseUtil.exportToFile(licenseKey);

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.addFileAttachment(file);
		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);
		subscriptionSender.setFrom("hello@liferay.com", "Liferay");
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"license_key", licenseKey.getLicenseKeyId());
		subscriptionSender.setPortletId(OSBPortletKeys.OSB_LICENSE);
		subscriptionSender.setReplyToAddress("hello@liferay.com");

		subscriptionSender.addRuntimeSubscribers(emailAddress, fullName);

		subscriptionSender.flushNotificationsAsync();
	}

	protected void validate(OfferingEntry offeringEntry, int numberOfKeys)
		throws PortalException {

		OfferingEntryGroup offeringEntryGroup =
			offeringEntry.getOfferingEntryGroup();

		if (offeringEntryGroup.getQuantity() <
				(offeringEntryGroup.getLicenseKeysCount() + numberOfKeys)) {

			throw new MaximumLicenseKeyException();
		}
	}

	protected void validate(
			OfferingEntry offeringEntry, LicenseEntry licenseEntry,
			int licenseVersion, ProductEntry productEntry, int productVersion,
			String owner, int maxServers, String description,
			String[] hostNames, String[] ipAddresses, String[] macAddresses,
			String[] serverIds, boolean complimentary)
		throws PortalException {

		if (offeringEntry.getStatus() != OfferingEntryConstants.STATUS_ACTIVE) {
			throw new OfferingEntryStatusException();
		}

		if (productVersion <= 0) {
			throw new LicenseKeyProductVersionException();
		}

		ListType listType = listTypeLocalService.fetchListType(productVersion);

		String listTypeType = ProductEntryConstants.getAllListType(
			ProductEntry.class.getName() + StringPool.PERIOD +
				productEntry.getVersionsListType());

		if ((listType == null) || !listTypeType.equals(listType.getType())) {
			throw new LicenseKeyProductVersionException();
		}

		if (Validator.isNull(owner)) {
			throw new LicenseKeyOwnerException();
		}

		String type = licenseEntry.getType();

		if ((licenseVersion >= 3) &&
			type.equals(LicenseEntryConstants.TYPE_CLUSTER)) {

			if ((maxServers <= 0) || !offeringEntry.isLicenses() ||
				(maxServers > offeringEntry.getQuantity())) {

				throw new LicenseKeyMaxServersException();
			}
		}

		if ((licenseVersion == 2) &&
			(type.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
			 type.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER))) {

			if (maxServers <= 0) {
				throw new LicenseKeyMaxServersException();
			}
		}

		if (Validator.isNull(description)) {
			throw new LicenseKeyDescriptionException();
		}

		if ((licenseVersion == 2) &&
			(type.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
			 type.equals(LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER))) {

			validate(offeringEntry, maxServers);

			return;
		}

		if (licenseVersion <= 2) {
			if (serverIds.length == 0) {
				throw new LicenseKeyServerIdException();
			}

			validate(offeringEntry, serverIds.length);

			for (String serverId : serverIds) {
				if (type.equals(LicenseEntryConstants.TYPE_ENTERPRISE) ||
					type.equals(LicenseEntryConstants.TYPE_OEM)) {

					continue;
				}

				if (Validator.isNull(serverId)) {
					throw new LicenseKeyServerIdException();
				}

				if (serverId.equals(LicenseKeyConstants.SERVER_ID_DEVELOPER) &&
					(complimentary ||
					 type.equals(LicenseEntryConstants.TYPE_DEVELOPER))) {

					continue;
				}

				if (serverId.length() == 44) {
					validateServerId(serverId);
				}
				else if (serverId.length() == 17) {
					validateMacAddress(serverId);
				}
				else {
					//throw new LicenseKeyServerIdException();
				}
			}
		}
		else if (ArrayUtil.isNotEmpty(serverIds)) {
			validate(offeringEntry, serverIds.length);
		}
		else {
			if (hostNames.length <= 0) {
				throw new LicenseKeyHostNameException();
			}

			validate(offeringEntry, hostNames.length);
		}
	}

	protected void validate(
			String owner, String description, String hostName,
			String ipAddresses, String macAddresses)
		throws PortalException {

		if (Validator.isNull(owner)) {
			throw new LicenseKeyOwnerException();
		}

		if (Validator.isNull(description)) {
			throw new LicenseKeyDescriptionException();
		}

		Set<String> distinctIpAddresses = new HashSet<>();

		String[] curIpAddresses = StringUtil.split(ipAddresses);

		for (String ipAddress : curIpAddresses) {
			validateIpAddress(ipAddress);

			if (distinctIpAddresses.contains(ipAddress)) {
				throw new DuplicateIPAddressException();
			}

			distinctIpAddresses.add(ipAddress);
		}

		Set<String> distinctMacAddresses = new HashSet<>();

		String[] curMacAddresses = StringUtil.split(macAddresses);

		for (String macAddress : curMacAddresses) {
			validateMacAddress(macAddress);

			if (distinctMacAddresses.contains(macAddress)) {
				throw new DuplicateMACAddressException();
			}

			distinctMacAddresses.add(macAddress);
		}

		if (Validator.isNull(hostName) && distinctIpAddresses.isEmpty() &&
			distinctMacAddresses.isEmpty()) {

			throw new LicenseKeyServerInfoException();
		}
	}

	protected void validateIpAddress(String ipAddress) throws PortalException {
		if (!Validator.isIPAddress(ipAddress)) {
			throw new LicenseKeyIPAddressException();
		}
	}

	protected void validateMacAddress(String macAddress)
		throws PortalException {

		String curMacAddress = StringUtil.replace(
			macAddress, CharPool.DASH, CharPool.COLON);

		String[] octets = StringUtil.split(curMacAddress, StringPool.COLON);

		if (octets.length != 6) {
			throw new LicenseKeyMACAddressException();
		}

		for (String octet : octets) {
			if (octet.length() > 2) {
				throw new LicenseKeyMACAddressException();
			}

			char[] charArray = octet.toCharArray();

			for (char c : charArray) {
				if (!Validator.isDigit(c) &&
					((c < 65) || ((c > 70) && (c < 97)) || (c > 102))) {

					throw new LicenseKeyMACAddressException();
				}
			}
		}
	}

	protected void validateServerId(String serverId) throws PortalException {
		char[] charArray = serverId.toCharArray();

		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];

			if (((i + 1) % 5) == 0) {
				if (c != CharPool.DASH) {
					throw new LicenseKeyServerIdException();
				}
			}
			else if (!Validator.isDigit(c) && !Validator.isChar(c)) {
				throw new LicenseKeyServerIdException();
			}
		}
	}

	@BeanReference(type = CountryService.class)
	protected CountryService countryService;

}