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

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.AppEntryCategoryException;
import com.liferay.osb.AppEntryChangeLogException;
import com.liferay.osb.AppEntryDemoWebsiteException;
import com.liferay.osb.AppEntryDescriptionException;
import com.liferay.osb.AppEntryDocumentationWebsiteException;
import com.liferay.osb.AppEntryIconException;
import com.liferay.osb.AppEntryLicenseLifetimeException;
import com.liferay.osb.AppEntryLicenseSupportException;
import com.liferay.osb.AppEntryLicenseTypeException;
import com.liferay.osb.AppEntryOrderURLException;
import com.liferay.osb.AppEntryPACLException;
import com.liferay.osb.AppEntryPurchaseException;
import com.liferay.osb.AppEntryReferenceWebsiteException;
import com.liferay.osb.AppEntryReleaseTypeException;
import com.liferay.osb.AppEntrySourceCodeWebsiteException;
import com.liferay.osb.AppEntryStatusException;
import com.liferay.osb.AppEntrySupportWebsiteException;
import com.liferay.osb.AppEntryTitleException;
import com.liferay.osb.AppEntryVersionException;
import com.liferay.osb.AppEntryWebsiteException;
import com.liferay.osb.DeveloperEntrySubscriptionStatusException;
import com.liferay.osb.DuplicateAppEntryException;
import com.liferay.osb.MaximumAssetLicenseException;
import com.liferay.osb.MinimumAssetLicenseException;
import com.liferay.osb.RequiredAppPackageException;
import com.liferay.osb.RequiredAppPackagePluginException;
import com.liferay.osb.RequiredAssetLicenseException;
import com.liferay.osb.marketplace.social.MarketplaceActivityKeys;
import com.liferay.osb.marketplace.util.MarketplaceEmailUtil;
import com.liferay.osb.marketplace.util.MarketplaceLiferayPackageUtil;
import com.liferay.osb.marketplaceadmin.util.MarketplaceAdminDiscussionUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppFlag;
import com.liferay.osb.model.AppFlagConstants;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppPackagePlugin;
import com.liferay.osb.model.AppPackagePluginConstants;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.AssetAuditConstants;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.ContractEntry;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.base.AppEntryLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.AppVersionVersionOrderComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.asset.NoSuchEntryException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryConstants;
import com.liferay.portlet.asset.model.AssetEntry;

import java.awt.image.RenderedImage;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Joan Kim
 * @author Haote Chou
 */
public class AppEntryLocalServiceImpl extends AppEntryLocalServiceBaseImpl {

	public AppEntry addAppEntry(
			long userId, long developerEntryId, String title,
			Map<Locale, String> descriptionMap, String website,
			String demoWebsite, String documentationWebsite,
			String referenceWebsite, String sourceCodeWebsite,
			String supportWebsite, boolean labs, int productType,
			String version, Map<Locale, String> changeLogMap, File iconFile,
			boolean paclEnabled, Map<Locale, String> eulaContentMap,
			int licenseType, String orderURL, boolean hidden,
			boolean portalRequired, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		DeveloperEntry developerEntry =
			developerEntryLocalService.getDeveloperEntry(developerEntryId);
		Date now = new Date();

		validate(
			0, developerEntryId, title, descriptionMap, website, demoWebsite,
			documentationWebsite, referenceWebsite, sourceCodeWebsite,
			supportWebsite, version, iconFile, paclEnabled,
			AppVersionConstants.RELEASE_TYPE_NEW_VERSION, licenseType, orderURL,
			serviceContext.getAssetCategoryIds());

		long appEntryId = counterLocalService.increment();

		AppEntry appEntry = appEntryPersistence.create(appEntryId);

		if (title.equals(
				PortletPropsValues.MARKETPLACE_SO_CE_APP_ENTRY_TITLE)) {

			appEntry.setUuid(
				PortletPropsValues.MARKETPLACE_SO_CE_APP_ENTRY_TITLE);
		}

		if (title.equals(
				PortletPropsValues.MARKETPLACE_SO_EE_APP_ENTRY_TITLE)) {

			appEntry.setUuid(
				PortletPropsValues.MARKETPLACE_SO_EE_APP_ENTRY_TITLE);
		}

		appEntry.setUserId(user.getUserId());
		appEntry.setUserName(user.getFullName());
		appEntry.setCreateDate(now);
		appEntry.setModifiedDate(now);
		appEntry.setDeveloperEntryId(developerEntry.getDeveloperEntryId());
		appEntry.setDeveloperName(developerEntry.getName());
		appEntry.setTitle(title);
		appEntry.setDescriptionMap(descriptionMap, LocaleUtil.US);
		appEntry.setWebsite(website);
		appEntry.setDemoWebsite(demoWebsite);
		appEntry.setDocumentationWebsite(documentationWebsite);
		appEntry.setReferenceWebsite(referenceWebsite);
		appEntry.setSourceCodeWebsite(sourceCodeWebsite);
		appEntry.setSupportWebsite(supportWebsite);
		appEntry.setLabs(labs);
		appEntry.setProductType(productType);
		appEntry.setVersion(version);
		appEntry.setChangeLogMap(changeLogMap, LocaleUtil.US);
		appEntry.setPaclEnabled(paclEnabled);
		appEntry.setLicenseType(licenseType);
		appEntry.setOrderURL(orderURL);
		appEntry.setHidden(hidden);
		appEntry.setPortalRequired(portalRequired);
		appEntry.setStatus(WorkflowConstants.STATUS_DRAFT);
		appEntry.setStatusByUserId(user.getUserId());
		appEntry.setStatusByUserName(user.getFullName());
		appEntry.setStatusDate(now);
		appEntry.setStatusVersionDate(now);

		appEntryPersistence.update(appEntry, false);

		// App version

		AppVersion appVersion = addAppVersion(
			user, appEntry, now, title, descriptionMap, website, demoWebsite,
			documentationWebsite, referenceWebsite, sourceCodeWebsite,
			supportWebsite, labs, productType, version,
			AppVersionConstants.VERSION_ORDER_FIRST, changeLogMap, paclEnabled,
			null, AppVersionConstants.RELEASE_TYPE_NEW_VERSION, eulaContentMap,
			orderURL, hidden, portalRequired, WorkflowConstants.STATUS_DRAFT,
			serviceContext);

		// Asset attachment

		AssetAttachment assetAttachment =
			assetAttachmentLocalService.addAssetAttachment(
				userId, AppVersion.class.getName(),
				appVersion.getAppVersionId(), iconFile.getName(),
				AssetAttachmentConstants.TYPE_ICON, 0, iconFile);

		appEntry.setIconImageId(assetAttachment.getAssetAttachmentId());

		appEntryPersistence.update(appEntry, false);

		appVersion.setIconImageId(assetAttachment.getAssetAttachmentId());

		appVersionPersistence.update(appVersion, false);

		// Asset

		updateAsset(
			userId, appEntry, appVersion, serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		// Discussion

		MarketplaceAdminDiscussionUtil.addSystemDiscussionMessage(
			userId, user.getFullName(), appEntry.getAppEntryId(),
			WorkflowConstants.STATUS_DRAFT, StringPool.BLANK, serviceContext);

		// Resources

		if (serviceContext.isAddGroupPermissions() ||
			serviceContext.isAddGuestPermissions()) {

			addEntryResources(
				appEntry, serviceContext.isAddGroupPermissions(),
				serviceContext.isAddGuestPermissions());
		}
		else {
			addEntryResources(
				appEntry, serviceContext.getGroupPermissions(),
				serviceContext.getGuestPermissions());
		}

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			AppEntry.class);

		indexer.reindex(appEntry);

		return appEntry;
	}

	public void addEntryResources(
			AppEntry entry, boolean addCommunityPermissions,
			boolean addGuestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addResources(
			OSBConstants.COMPANY_ID, OSBConstants.GROUP_GUEST_ID,
			entry.getUserId(), AppEntry.class.getName(), entry.getAppEntryId(),
			false, addCommunityPermissions, addGuestPermissions);
	}

	public void addEntryResources(
			AppEntry entry, String[] communityPermissions,
			String[] guestPermissions)
		throws PortalException, SystemException {

		resourceLocalService.addModelResources(
			OSBConstants.COMPANY_ID, OSBConstants.GROUP_GUEST_ID,
			entry.getUserId(), AppEntry.class.getName(), entry.getAppEntryId(),
			communityPermissions, guestPermissions);
	}

	public File buildLiferayPackage(AppPackage appPackage, boolean licensed)
		throws PortalException, SystemException {

		return MarketplaceLiferayPackageUtil.buildLiferayPackage(
			appPackage, licensed);
	}

	public void buildLiferayPackages(AppVersion appVersion)
		throws PortalException, SystemException {

		List<AppPackage> appPackages =
			appPackagePersistence.findByAppVersionId(
				appVersion.getAppVersionId());

		for (AppPackage appPackage : appPackages) {
			File file = buildLiferayPackage(appPackage, true);

			StringBundler sb = new StringBundler(6);

			sb.append(appVersion.getAppEntryId());
			sb.append(StringPool.UNDERLINE);
			sb.append(appVersion.getAppVersionId());
			sb.append(StringPool.UNDERLINE);
			sb.append(appPackage.getAppPackageId());
			sb.append(".lpkg");

			String fileName = sb.toString();

			try {
				assetAttachmentLocalService.addAssetAttachment(
					appVersion.getUserId(), AppPackage.class.getName(),
					appPackage.getAppPackageId(), fileName,
					AssetAttachmentConstants.TYPE_PACKAGE, 0, file);
			}
			finally {
				file.delete();
			}
		}
	}

	@Override
	public AppEntry deleteAppEntry(AppEntry appEntry)
		throws PortalException, SystemException {

		// App entry

		appEntryPersistence.remove(appEntry);

		// App versions

		List<AppVersion> appVersions = appVersionPersistence.findByAppEntryId(
			appEntry.getAppEntryId());

		for (AppVersion appVersion : appVersions) {
			appVersionLocalService.deleteAppVersion(appVersion);

			assetEntryLocalService.deleteEntry(
				AppVersion.class.getName(), appVersion.getAppVersionId());
		}

		// Asset entry

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			AppEntry.class.getName(), appEntry.getAppEntryId());

		assetEntryLocalService.deleteEntry(assetEntry);

		// Asset list asset entries

		assetListAssetEntryPersistence.removeByAssetEntryId(
			assetEntry.getEntryId());

		// Asset receipts

		List<AssetReceipt> assetReceipts =
			assetReceiptPersistence.findByAssetEntryId(assetEntry.getEntryId());

		for (AssetReceipt assetReceipt : assetReceipts) {
			assetReceiptLocalService.deleteAssetReceipt(assetReceipt);
		}

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			AppEntry.class);

		indexer.delete(appEntry);

		return appEntry;
	}

	@Override
	public AppEntry deleteAppEntry(long appEntryId)
		throws PortalException, SystemException {

		AppEntry appEntry = appEntryPersistence.findByPrimaryKey(appEntryId);

		return deleteAppEntry(appEntry);
	}

	public void expireAppEntries(long developerEntryId)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			OSBConstants.USER_DEFAULT_USER_ID);
		Date now = new Date();

		List<AppEntry> appEntries = appEntryPersistence.findByDeveloperEntryId(
			developerEntryId);

		for (AppEntry appEntry : appEntries) {
			appEntry.setStatus(WorkflowConstants.STATUS_EXPIRED);
			appEntry.setStatusByUserId(user.getUserId());
			appEntry.setStatusByUserName(user.getFullName());
			appEntry.setStatusDate(now);

			appEntryPersistence.update(appEntry, false);

			assetEntryLocalService.updateVisible(
				AppEntry.class.getName(), appEntry.getAppEntryId(), false);

			MarketplaceAdminDiscussionUtil.addSystemDiscussionMessage(
				OSBConstants.USER_DEFAULT_USER_ID, user.getFullName(),
				appEntry.getAppEntryId(), WorkflowConstants.STATUS_EXPIRED,
				StringPool.BLANK, new ServiceContext());

			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				AppEntry.class);

			indexer.reindex(appEntry);
		}
	}

	public AppEntry fetchAppEntry(long appEntryId) throws SystemException {
		return appEntryPersistence.fetchByPrimaryKey(appEntryId);
	}

	public List<AppEntry> getAppEntries(int status, int start, int end)
		throws SystemException {

		return appEntryPersistence.findByStatus(status, start, end);
	}

	public List<AppEntry> getAppEntries(
			long developerEntryId, int status, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return appEntryPersistence.findByDeveloperEntryId(
				developerEntryId, start, end, obc);
		}
		else {
			return appEntryPersistence.findByDEI_S(
				developerEntryId, status, start, end, obc);
		}
	}

	public List<AppEntry> getAppEntries(
			long developerEntryId, String title, int start, int end)
		throws SystemException {

		if (Validator.isNull(title)) {
			return appEntryPersistence.findByDeveloperEntryId(
				developerEntryId, start, end);
		}
		else {
			return appEntryPersistence.findByDEI_LikeT(
				developerEntryId, title, start, end);
		}
	}

	public int getAppEntriesCount(int status) throws SystemException {
		return appEntryPersistence.countByStatus(status);
	}

	public int getAppEntriesCount(long developerEntryId, int status)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return appEntryPersistence.countByDeveloperEntryId(
				developerEntryId);
		}
		else {
			return appEntryPersistence.countByDEI_S(developerEntryId, status);
		}
	}

	public int getAppEntriesCount(long developerEntryId, String title)
		throws SystemException {

		if (Validator.isNull(title)) {
			return appEntryPersistence.countByDeveloperEntryId(
				developerEntryId);
		}
		else {
			return appEntryPersistence.countByDEI_LikeT(
				developerEntryId, title);
		}
	}

	public AppEntry getAppEntry(String title)
		throws PortalException, SystemException {

		return appEntryPersistence.findByTitle(title);
	}

	public List<AppEntry> getPrepackagedAppEntries(int compatibility)
		throws PortalException, SystemException {

		return appEntryFinder.findByC_P_S(
			compatibility, true, WorkflowConstants.STATUS_APPROVED);
	}

	public int getPrepackagedAppEntriesCount(int compatibility)
		throws PortalException, SystemException {

		return appEntryFinder.countByC_P_S(
			compatibility, true, WorkflowConstants.STATUS_APPROVED);
	}

	public AppEntry incrementDownloadCount(
			long userId, long appVersionId, int domain)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		appVersion.setDownloadCount(appVersion.getDownloadCount() + 1);

		appVersionPersistence.update(appVersion, false);

		AppEntry appEntry = appVersion.getAppEntry();

		appEntry.setDownloadCount(appEntry.getDownloadCount() + 1);

		appEntryPersistence.update(appEntry, false);

		assetAuditLocalService.addAssetAudit(
			userId, StringPool.BLANK, AppEntry.class.getName(),
			appEntry.getAppEntryId(), AssetAuditConstants.TYPE_DOWNLOAD, domain,
			StringPool.BLANK, 0);

		Indexer indexer = IndexerRegistryUtil.getIndexer(AppEntry.class);

		indexer.reindex(appEntry);

		return appEntry;
	}

	public List<AppEntry> search(
			String title, LinkedHashMap<String, Object> params,
			boolean andSearch, int start, int end, OrderByComparator obc)
		throws SystemException {

		return appEntryFinder.findByTitle(
			title, params, andSearch, start, end, obc);
	}

	public int searchCount(
			String title, LinkedHashMap<String, Object> params,
			boolean andSearch)
		throws SystemException {

		return appEntryFinder.countByTitle(title, params, andSearch);
	}

	public AppEntry updateAppEntry(
			long userId, long appEntryId, long licenseLifetime,
			boolean supported, long[] standardQuantities,
			boolean standardUnlimited, long[] developerQuantities,
			boolean developerUnlimited, boolean trial)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		// App entry

		AppEntry appEntry = appEntryPersistence.findByPrimaryKey(appEntryId);

		validate(
			appEntry, licenseLifetime, supported, standardQuantities,
			standardUnlimited, developerQuantities, developerUnlimited);

		appEntry.setLicenseLifetime(licenseLifetime);
		appEntry.setSupported(supported);

		appEntryPersistence.update(appEntry, false);

		// App version

		long approvedAppVersionId = 0;

		AppVersion appVersion = appEntry.getUnreleasedAppVersion();

		if (appVersion == null) {
			AppVersion approvedAppVersion = appEntry.getApprovedAppVersion();

			approvedAppVersionId = approvedAppVersion.getAppVersionId();

			Map<Locale, String> eulaContentMap = null;

			if (approvedAppVersion.getContractEntryId() > 0) {
				ContractEntry contractEntry =
					contractEntryLocalService.getContractEntry(
						approvedAppVersion.getContractEntryId());

				eulaContentMap = contractEntry.getContentMap();
			}

			appVersion = addAppVersion(
				user, appEntry, new Date(), appEntry.getTitle(),
				appEntry.getDescriptionMap(), appEntry.getWebsite(),
				appEntry.getDemoWebsite(), appEntry.getDocumentationWebsite(),
				appEntry.getReferenceWebsite(), appEntry.getSourceCodeWebsite(),
				appEntry.getSupportWebsite(), appEntry.isLabs(),
				appEntry.getProductType(), appEntry.getVersion(),
				AppVersionConstants.VERSION_ORDER_LAST,
				appEntry.getChangeLogMap(), appEntry.isPaclEnabled(), null,
				AppVersionConstants.RELEASE_TYPE_PRICING, eulaContentMap,
				appEntry.getOrderURL(), appEntry.isHidden(),
				appEntry.getPortalRequired(), WorkflowConstants.STATUS_DRAFT,
				new ServiceContext());

			List<AssetAttachment> assetAttachments =
				assetAttachmentLocalService.getAssetAttachments(
					AppVersion.class.getName(), approvedAppVersionId,
					AssetAttachmentConstants.TYPE_MEDIA, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

			for (AssetAttachment assetAttachment : assetAttachments) {
				assetAttachmentLocalService.copyAssetAttachment(
					assetAttachment.getAssetAttachmentId(),
					AppVersion.class.getName(), appVersion.getAppVersionId());
			}
		}

		appVersion.setSupported(supported);

		appVersionPersistence.update(appVersion, false);

		// Asset license

		List<AssetLicense> assetLicenses = new ArrayList<AssetLicense>(
			standardQuantities.length + developerQuantities.length + 3);

		for (long quantity : standardQuantities) {
			AssetLicense assetLicense = assetLicensePersistence.create(0);

			assetLicense.setClassName(AppVersion.class.getName());
			assetLicense.setClassPK(appVersion.getAppVersionId());
			assetLicense.setUsageType(
				AssetLicenseConstants.USAGE_TYPE_STANDARD);
			assetLicense.setLicenseType(appEntry.getLicenseType());
			assetLicense.setLicenseTypeAllotment(quantity);
			assetLicense.setLifetime(licenseLifetime);
			assetLicense.setName(getAppVersionAssetLicenseName(assetLicense));

			assetLicenses.add(assetLicense);
		}

		if (standardUnlimited) {
			AssetLicense assetLicense = assetLicensePersistence.create(0);

			assetLicense.setClassName(AppVersion.class.getName());
			assetLicense.setClassPK(appVersion.getAppVersionId());
			assetLicense.setUsageType(
				AssetLicenseConstants.USAGE_TYPE_STANDARD);
			assetLicense.setLicenseType(
				AssetLicenseConstants.LICENSE_TYPE_UNLIMITED);
			assetLicense.setLicenseTypeAllotment(
				AssetLicenseConstants.LICENSE_TYPE_ALLOTMENT_UNLIMITED);
			assetLicense.setLifetime(licenseLifetime);
			assetLicense.setName(getAppVersionAssetLicenseName(assetLicense));

			assetLicenses.add(assetLicense);
		}

		for (long quantity : developerQuantities) {
			AssetLicense assetLicense = assetLicensePersistence.create(0);

			assetLicense.setClassName(AppVersion.class.getName());
			assetLicense.setClassPK(appVersion.getAppVersionId());
			assetLicense.setUsageType(
				AssetLicenseConstants.USAGE_TYPE_DEVELOPER);
			assetLicense.setLicenseType(appEntry.getLicenseType());
			assetLicense.setLicenseTypeAllotment(quantity);
			assetLicense.setLifetime(licenseLifetime);
			assetLicense.setName(getAppVersionAssetLicenseName(assetLicense));

			assetLicenses.add(assetLicense);
		}

		if (developerUnlimited) {
			AssetLicense assetLicense = assetLicensePersistence.create(0);

			assetLicense.setClassName(AppVersion.class.getName());
			assetLicense.setClassPK(appVersion.getAppVersionId());
			assetLicense.setUsageType(
				AssetLicenseConstants.USAGE_TYPE_DEVELOPER);
			assetLicense.setLicenseType(
				AssetLicenseConstants.LICENSE_TYPE_UNLIMITED);
			assetLicense.setLicenseTypeAllotment(
				AssetLicenseConstants.LICENSE_TYPE_ALLOTMENT_UNLIMITED);
			assetLicense.setLifetime(licenseLifetime);
			assetLicense.setName(getAppVersionAssetLicenseName(assetLicense));

			assetLicenses.add(assetLicense);
		}

		if (trial) {
			long trialQuantity = 0;

			if (appEntry.getLicenseType() ==
					AssetLicenseConstants.LICENSE_TYPE_PER_USER) {

				trialQuantity =
					PortletPropsValues.ASSET_LICENSE_TRIAL_MAX_USERS;
			}
			else if (appEntry.getLicenseType() ==
						AssetLicenseConstants.LICENSE_TYPE_PRODUCTION) {

				trialQuantity =
					PortletPropsValues.ASSET_LICENSE_TRIAL_MAX_SERVERS;
			}

			AssetLicense assetLicense = assetLicensePersistence.create(0);

			assetLicense.setClassName(AppVersion.class.getName());
			assetLicense.setClassPK(appVersion.getAppVersionId());
			assetLicense.setUsageType(AssetLicenseConstants.USAGE_TYPE_TRIAL);
			assetLicense.setLicenseType(appEntry.getLicenseType());
			assetLicense.setLicenseTypeAllotment(trialQuantity);
			assetLicense.setLifetime(AssetLicenseConstants.LIFETIME_TRIAL);
			assetLicense.setName(getAppVersionAssetLicenseName(assetLicense));

			assetLicenses.add(assetLicense);
		}

		assetLicenseLocalService.updateAssetLicenses(
			userId, AppVersion.class.getName(), appVersion.getAppVersionId(),
			assetLicenses);

		if (approvedAppVersionId > 0) {

			// App packages

			appPackageLocalService.copyAppPackages(
				approvedAppVersionId, appVersion.getAppVersionId());

			// App pricing

			appPricingLocalService.copyAppPricings(
				approvedAppVersionId, appVersion.getAppVersionId());

			// Asset

			AssetEntry assetEntry = appEntry.getAssetEntry();

			updateAsset(
				appEntry.getUserId(), appEntry, appVersion,
				assetEntry.getCategoryIds(), assetEntry.getTagNames());

			// Indexer

			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				AppEntry.class);

			indexer.reindex(appEntry);
		}

		return appEntry;
	}

	public AppEntry updateAppEntry(
			long userId, long appEntryId, String title,
			Map<Locale, String> descriptionMap, String website,
			String demoWebsite, String documentationWebsite,
			String referenceWebsite, String sourceCodeWebsite,
			String supportWebsite, boolean labs, int productType,
			String version, int versionOrder, Map<Locale, String> changeLogMap,
			File iconFile, boolean paclEnabled, int releaseType,
			Map<Locale, String> eulaContentMap, int licenseType,
			String orderURL, boolean hidden, boolean portalRequired, int status,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// App entry

		User user = userPersistence.findByPrimaryKey(userId);

		AppEntry appEntry = appEntryPersistence.findByPrimaryKey(appEntryId);

		DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

		validate(
			appEntryId, developerEntry.getDeveloperEntryId(), title,
			descriptionMap, website, demoWebsite, documentationWebsite,
			referenceWebsite, sourceCodeWebsite, supportWebsite, version,
			iconFile, paclEnabled, releaseType, licenseType, orderURL,
			serviceContext.getAssetCategoryIds());

		AppVersion appVersion = appEntry.getUnreleasedAppVersion();

		if (appVersion == null) {

			// App version

			AppVersion approvedAppVersion = appEntry.getApprovedAppVersion();

			if (!isUpdateVersion(userId, releaseType, version, changeLogMap)) {
				changeLogMap = approvedAppVersion.getChangeLogMap();
				version = approvedAppVersion.getVersion();
			}

			Date releaseDate = null;

			if (status == WorkflowConstants.STATUS_APPROVED) {
				releaseDate = approvedAppVersion.getReleaseDate();
			}

			appVersion = addAppVersion(
				user, appEntry, new Date(), title, descriptionMap, website,
				demoWebsite, documentationWebsite, referenceWebsite,
				sourceCodeWebsite, supportWebsite, labs, productType, version,
				versionOrder, changeLogMap, paclEnabled, releaseDate,
				releaseType, eulaContentMap, orderURL, hidden, portalRequired,
				status, serviceContext);

			if (status != WorkflowConstants.STATUS_DRAFT) {
				appEntry.setTitle(title);
				appEntry.setHidden(hidden);
				appEntry.setPortalRequired(portalRequired);
				appEntry.setStatus(status);

				appEntryPersistence.update(appEntry, false);
			}

			// Asset licenses

			assetLicenseLocalService.copyAssetLicenses(
				AppVersion.class.getName(),
				approvedAppVersion.getAppVersionId(),
				AppVersion.class.getName(), appVersion.getAppVersionId());

			// App packages

			if (releaseType != AppVersionConstants.RELEASE_TYPE_NEW_VERSION) {
				appPackageLocalService.copyAppPackages(
					approvedAppVersion.getAppVersionId(),
					appVersion.getAppVersionId());
			}

			// App pricing

			appPricingLocalService.copyAppPricings(
				approvedAppVersion.getAppVersionId(),
				appVersion.getAppVersionId());
		}
		else {
			if (!appVersion.isNewRelease()) {
				version = appVersion.getVersion();
			}

			appVersion = updateAppVersion(
				user, appVersion, new Date(), title, descriptionMap, website,
				demoWebsite, documentationWebsite, referenceWebsite,
				sourceCodeWebsite, supportWebsite, labs, productType, version,
				versionOrder, changeLogMap, paclEnabled, eulaContentMap,
				orderURL, hidden, portalRequired, appVersion.getStatus(),
				appVersion.getStatusMessage(), serviceContext);

			if (appEntry.getStatus() == WorkflowConstants.STATUS_DRAFT) {
				appEntry.setTitle(title);
				appEntry.setLicenseType(licenseType);
				appEntry.setHidden(hidden);
				appEntry.setPortalRequired(portalRequired);

				appEntryPersistence.update(appEntry, false);
			}
		}

		// Asset attachment

		if ((iconFile != null) && iconFile.exists()) {
			assetAttachmentLocalService.updateAssetAttachment(
				appVersion.getIconImageId(), iconFile);
		}

		// Asset

		updateAsset(
			appEntry.getUserId(), appEntry, appVersion,
			serviceContext.getAssetCategoryIds(),
			serviceContext.getAssetTagNames());

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			AppEntry.class);

		indexer.reindex(appEntry);

		return appEntry;
	}

	public AppEntry updateAppEntry(
			long appEntryId, String version, Map<Locale, String> changeLogMap,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		AppEntry appEntry = appEntryPersistence.findByPrimaryKey(appEntryId);

		AppVersion appVersion = appEntry.getUnreleasedAppVersion();

		validate(appEntry, appVersion, version, changeLogMap);

		if (appVersion.isNewRelease()) {
			appVersion.setVersion(version);
		}

		appVersion.setChangeLogMap(changeLogMap, LocaleUtil.US);

		appVersionPersistence.update(appVersion, false);

		return appEntry;
	}

	public void updateAsset(
			long userId, AppEntry appEntry, AppVersion appVersion,
			long[] assetCategoryIds, String[] assetTagNames)
		throws PortalException, SystemException {

		boolean addDraftAssetEntry = false;

		if ((appVersion != null) && !appVersion.isApproved()) {
			int count = appVersionPersistence.countByAEI_S(
				appEntry.getAppEntryId(), WorkflowConstants.STATUS_APPROVED);

			if (count > 0) {
				addDraftAssetEntry = true;
			}
		}

		User user = userPersistence.fetchByPrimaryKey(userId);

		if (user == null) {
			userId = userLocalService.getDefaultUserId(OSBConstants.COMPANY_ID);
		}

		if (addDraftAssetEntry) {
			assetEntryLocalService.updateEntry(
				userId, OSBConstants.GROUP_GUEST_ID, AppVersion.class.getName(),
				appVersion.getAppVersionId(), appEntry.getUuid(), 0,
				assetCategoryIds, assetTagNames, false, null, null, null, null,
				null, appVersion.getTitle(), appVersion.getDescription(), null,
				null, null, 0, 0, null, false);
		}
		else {
			boolean visible = true;

			if ((appVersion != null) && !appVersion.isApproved()) {
				visible = false;
			}

			assetEntryLocalService.updateEntry(
				userId, OSBConstants.GROUP_GUEST_ID, AppEntry.class.getName(),
				appEntry.getAppEntryId(), appEntry.getUuid(), 0,
				assetCategoryIds, assetTagNames, visible, null, null, null,
				null, null, appEntry.getTitle(), appEntry.getDescription(),
				null, null, null, 0, 0, null, false);

			assetEntryLocalService.updateEntry(
				userId, OSBConstants.GROUP_GUEST_ID, AppVersion.class.getName(),
				appVersion.getAppVersionId(), appEntry.getUuid(), 0,
				assetCategoryIds, assetTagNames, false, null, null, null, null,
				null, appVersion.getTitle(), appVersion.getDescription(), null,
				null, null, 0, 0, null, false);
		}
	}

	public AppEntry updateStatus(
			long userId, long appEntryId, int status, String statusMessage,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		AppEntry appEntry = appEntryPersistence.findByPrimaryKey(appEntryId);

		AppVersion appVersion = appEntry.getActionableAppVersion();

		appAuditLocalService.addAppAudit(
			user.getUserId(), appEntryId, appVersion.getAppVersionId(), status);

		if ((appVersion != null) && appVersion.isHidden() &&
			(status == WorkflowConstants.STATUS_APPROVED)) {

			status = WorkflowConstants.STATUS_APPROVED_HIDDEN;
		}

		appEntry.setStatusVersionDate(now);

		MarketplaceAdminDiscussionUtil.addSystemDiscussionMessage(
			userId, user.getFullName(), appEntry.getAppEntryId(), status,
			statusMessage, serviceContext);

		if (status == WorkflowConstants.STATUS_EXPIRED) {
			appEntry.setStatus(status);
			appEntry.setStatusByUserId(user.getUserId());
			appEntry.setStatusByUserName(user.getFullName());
			appEntry.setStatusDate(now);

			appEntryPersistence.update(appEntry, false);

			assetEntryLocalService.updateVisible(
				AppEntry.class.getName(), appEntryId, false);

			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				AppEntry.class);

			indexer.reindex(appEntry);

			MarketplaceEmailUtil.sendAppEntryStatusMail(appEntry);

			return appEntry;
		}

		validateStatus(appEntry, appVersion, status);

		if ((status == WorkflowConstants.STATUS_APPROVED) ||
			(status == WorkflowConstants.STATUS_APPROVED_HIDDEN)) {

			if (appEntry.getStatus() == WorkflowConstants.STATUS_EXPIRED) {
				return restoreExpiredAppEntry(
					user, appEntry, true, statusMessage);
			}

			if (appVersion.isApproved()) {
				return appEntry;
			}

			if (!appVersion.isNewRelease()) {
				AppVersion oldAppVersion = appEntry.getApprovedAppVersion();

				if (oldAppVersion != null) {
					appVersion.setDownloadCount(
						oldAppVersion.getDownloadCount());
					appVersion.setReleaseDate(oldAppVersion.getReleaseDate());

					// Old app version

					oldAppVersion.setStatus(WorkflowConstants.STATUS_EXPIRED);
					oldAppVersion.setStatusByUserId(user.getUserId());
					oldAppVersion.setStatusByUserName(user.getFullName());
					oldAppVersion.setStatusDate(now);

					appVersionPersistence.update(oldAppVersion, false);
				}
				else {
					appVersion.setReleaseType(
						AppVersionConstants.RELEASE_TYPE_NEW_VERSION);
				}
			}

			if (appVersion.isNewRelease() &&
				(appVersion.getReleaseDate() == null)) {

				appVersion.setReleaseDate(new Date());
			}

			appVersion.setStatus(status);
			appVersion.setStatusByUserId(user.getUserId());
			appVersion.setStatusByUserName(user.getFullName());
			appVersion.setStatusDate(now);
			appVersion.setStatusMessage(statusMessage);

			appVersionPersistence.update(appVersion, false);

			// App entry

			appEntry.setTitle(appVersion.getTitle());
			appEntry.setDescription(appVersion.getDescription());
			appEntry.setWebsite(appVersion.getWebsite());
			appEntry.setSupportWebsite(appVersion.getSupportWebsite());
			appEntry.setDemoWebsite(appVersion.getDemoWebsite());
			appEntry.setDocumentationWebsite(
				appVersion.getDocumentationWebsite());
			appEntry.setReferenceWebsite(appVersion.getReferenceWebsite());
			appEntry.setProductType(appVersion.getProductType());
			appEntry.setVersion(appVersion.getVersion());
			appEntry.setChangeLog(appVersion.getChangeLog());
			appEntry.setIconImageId(appVersion.getIconImageId());
			appEntry.setSupported(appVersion.getSupported());
			appEntry.setOrderURL(appVersion.getOrderURL());
			appEntry.setHidden(appVersion.isHidden());
			appEntry.setPortalRequired(appVersion.getPortalRequired());
			appEntry.setStatusByUserId(user.getUserId());
			appEntry.setStatusByUserName(user.getFullName());
			appEntry.setStatusDate(now);
			appEntry.setStatus(appVersion.getStatus());

			appEntryPersistence.update(appEntry, false);

			// Asset

			AssetEntry assetEntry = null;

			try {
				AssetEntry draftAssetEntry = assetEntryLocalService.getEntry(
					AppVersion.class.getName(), appVersion.getAppVersionId());

				long[] assetCategoryIds = draftAssetEntry.getCategoryIds();
				String[] assetTagNames = draftAssetEntry.getTagNames();

				assetEntry = assetEntryLocalService.updateEntry(
					appVersion.getUserId(), OSBConstants.GROUP_GUEST_ID,
					AppEntry.class.getName(), appEntryId, null, 0,
					assetCategoryIds, assetTagNames, false, null, null, null,
					null, null, appEntry.getTitle(), appEntry.getDescription(),
					null, null, null, 0, 0, null, false);
			}
			catch (NoSuchEntryException nsee) {
			}

			if (status == WorkflowConstants.STATUS_APPROVED) {
				assetEntryLocalService.updateVisible(
					AppEntry.class.getName(), appEntryId, true);
				assetEntryLocalService.updateVisible(
					AppVersion.class.getName(), appVersion.getAppVersionId(),
					true);
			}

			// Social

			if (appEntry.isDeveloperEntryUser()) {
				int approvedAppVersionCount =
					appVersionLocalService.getAppVersionsCount(
						appEntryId, WorkflowConstants.STATUS_APPROVED);

				if (approvedAppVersionCount > 1) {
					socialActivityLocalService.addActivity(
						appVersion.getUserId(), OSBConstants.GROUP_GUEST_ID,
						AppEntry.class.getName(), appEntryId,
						MarketplaceActivityKeys.UDPATE_APP_ENTRY,
						StringPool.BLANK, 0);
				}
				else {
					socialActivityLocalService.addUniqueActivity(
						appVersion.getUserId(), OSBConstants.GROUP_GUEST_ID,
						AppEntry.class.getName(), appEntryId,
						MarketplaceActivityKeys.ADD_APP_ENTRY, StringPool.BLANK,
						0);
				}
			}

			// Liferay package

			buildLiferayPackages(appVersion);
		}
		else {
			if ((status == WorkflowConstants.STATUS_PENDING) ||
				(status == WorkflowConstants.STATUS_PENDING_QA)) {

				validate(appVersion);
			}

			appVersion.setStatus(status);
			appVersion.setStatusByUserId(user.getUserId());
			appVersion.setStatusByUserName(user.getFullName());
			appVersion.setStatusDate(now);
			appVersion.setStatusMessage(statusMessage);

			appVersionPersistence.update(appVersion, false);

			// App entry

			if (appEntry.getStatus() == WorkflowConstants.STATUS_EXPIRED) {
				appEntry = restoreExpiredAppEntry(user, appEntry, false, null);
			}
			else {
				AppVersion approvedAppVersion =
					appEntry.getApprovedAppVersion();

				if (approvedAppVersion != null) {
					appEntry.setVersion(approvedAppVersion.getVersion());

					appEntryPersistence.update(appEntry, false);
				}
				else {
					appEntry.setVersion(appVersion.getVersion());
					appEntry.setStatus(appVersion.getStatus());
					appEntry.setStatusByUserId(user.getUserId());
					appEntry.setStatusByUserName(user.getFullName());
					appEntry.setStatusDate(now);

					appEntryPersistence.update(appEntry, false);

					// Asset

					assetEntryLocalService.updateVisible(
						AppEntry.class.getName(), appEntryId, false);
				}
			}
		}

		// App flag

		AppFlag appFlag = appFlagPersistence.fetchByAVI_T(
			appVersion.getAppVersionId(),
			AppFlagConstants.TYPE_AWAITING_RESPONSE);

		if (appFlag != null) {
			appFlagLocalService.deleteAppFlag(appFlag.getAppFlagId());
		}

		// App package

		if (appEntry.isDeveloperEntryLiferayInc()) {
			List<AppPackage> appPackages =
				appPackagePersistence.findByAppVersionId(
					appVersion.getAppVersionId());

			for (AppPackage appPackage : appPackages) {
				appPackageLocalService.updateAppPackagePrepackaged(
					appPackage.getAppPackageId());
			}
		}

		// Resources importer

		if (appVersion.isAddResourcesImporter()) {
			if (appVersion.isPending()) {
				addResourcesImporterApp(appVersion);
			}
			else if (status == WorkflowConstants.STATUS_DRAFT) {
				removeResourcesImporterApp(appVersion);
			}
		}

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			AppEntry.class);

		indexer.reindex(appEntry);

		MarketplaceEmailUtil.sendAppEntryStatusMail(appEntry);

		return appEntry;
	}

	public void validatePurchase(long appEntryId)
		throws PortalException, SystemException {

		AppEntry appEntry = appEntryPersistence.findByPrimaryKey(appEntryId);

		if (!appEntry.isApproved()) {
			throw new AppEntryStatusException();
		}

		if (appEntry.getAppEntryId() ==
				PortletPropsValues.MARKETPLACE_APP_ENTRY_ID) {

			throw new AppEntryPurchaseException();
		}
	}

	protected AppVersion addAppVersion(
			User user, AppEntry appEntry, Date date, String title,
			Map<Locale, String> descriptionMap, String website,
			String demoWebsite, String documentationWebsite,
			String referenceWebsite, String sourceCodeWebsite,
			String supportWebsite, boolean labs, int productType,
			String version, int versionOrder, Map<Locale, String> changeLogMap,
			boolean paclEnabled, Date releaseDate, int releaseType,
			Map<Locale, String> eulaContentMap, String orderURL, boolean hidden,
			boolean portalRequired, int status, ServiceContext serviceContext)
		throws PortalException, SystemException {

		long appVersionId = counterLocalService.increment();

		AppVersion appVersion = appVersionPersistence.create(appVersionId);

		appVersion.setUserId(user.getUserId());
		appVersion.setUserName(user.getFullName());
		appVersion.setCreateDate(date);
		appVersion.setModifiedDate(date);
		appVersion.setAppEntryId(appEntry.getAppEntryId());
		appVersion.setDeveloperEntryId(appEntry.getDeveloperEntryId());
		appVersion.setDeveloperName(appEntry.getDeveloperName());
		appVersion.setTitle(title);
		appVersion.setDescriptionMap(descriptionMap, LocaleUtil.US);
		appVersion.setWebsite(website);
		appVersion.setDemoWebsite(demoWebsite);
		appVersion.setDocumentationWebsite(documentationWebsite);
		appVersion.setReferenceWebsite(referenceWebsite);
		appVersion.setSourceCodeWebsite(sourceCodeWebsite);
		appVersion.setSupportWebsite(supportWebsite);
		appVersion.setLabs(labs);
		appVersion.setProductType(productType);

		if ((releaseType == AppVersionConstants.RELEASE_TYPE_METADATA) ||
			(releaseType == AppVersionConstants.RELEASE_TYPE_NEW_VERSION)) {

			appVersion.setVersion(version);
		}
		else {
			appVersion.setVersion(appEntry.getVersion());
		}

		int versionId = 0;

		if (appEntry.isFirstSubmission()) {
			versionId = (int)counterLocalService.increment(
				getCounterName(appEntry.getAppEntryId()));
		}
		else {
			AppVersion latestAppVersion = appEntry.getLatestAppVersion();

			versionId = latestAppVersion.getVersionId();
		}

		appVersion.setVersionId(versionId);

		updateAppVersionVersionOrder(appVersion, versionOrder);

		appVersion.setChangeLogMap(changeLogMap, LocaleUtil.US);

		AppVersion approvedAppVersion = appEntry.getApprovedAppVersion();

		if ((approvedAppVersion != null) &&
			(approvedAppVersion.getIconImageId() > 0)) {

			AssetAttachment assetAttachment =
				assetAttachmentLocalService.copyAssetAttachment(
					approvedAppVersion.getIconImageId(),
					AppVersion.class.getName(), appVersion.getAppVersionId());

			appVersion.setIconImageId(assetAttachment.getAssetAttachmentId());
		}

		appVersion.setPaclEnabled(paclEnabled);
		appVersion.setReleaseDate(releaseDate);
		appVersion.setReleaseType(releaseType);

		if ((eulaContentMap != null) && !eulaContentMap.isEmpty()) {
			ServiceContext contractEntryServiceContext = new ServiceContext();

			contractEntryServiceContext.setAttribute(
				"defaultLanguageId", LocaleUtil.toLanguageId(LocaleUtil.US));

			ContractEntry contractEntry =
				contractEntryLocalService.addContractEntry(
					user.getUserId(), AppVersion.class.getName(),
					appVersion.getAppVersionId(),
					ContractEntryConstants.TYPE_APP_EULA, eulaContentMap,
					contractEntryServiceContext);

			appVersion.setContractEntryId(contractEntry.getContractEntryId());
		}

		appVersion.setSupported(appEntry.getSupported());
		appVersion.setOrderURL(orderURL);
		appVersion.setHidden(hidden);
		appVersion.setPortalRequired(portalRequired);
		appVersion.setStatus(status);
		appVersion.setStatusByUserId(user.getUserId());
		appVersion.setStatusByUserName(user.getFullName());
		appVersion.setStatusDate(date);

		appVersionPersistence.update(appVersion, false);

		return appVersion;
	}

	protected void addResourcesImporterApp(AppVersion appVersion)
		throws PortalException, SystemException {

		AppEntry resourcesImporterCEAppEntry =
			appEntryPersistence.findByPrimaryKey(
				PortletPropsValues.
					MARKETPLACE_RESOURCES_IMPORTER_CE_APP_ENTRY_ID);

		AppVersion resourcesImporterCEAppVersion =
			appVersionLocalService.fetchAppVersion(
				resourcesImporterCEAppEntry.getAppEntryId(),
				AppVersionConstants.STATUSES_APPROVED, null);

		AppEntry resourcesImporterEEAppEntry =
			appEntryPersistence.findByPrimaryKey(
				PortletPropsValues.
					MARKETPLACE_RESOURCES_IMPORTER_EE_APP_ENTRY_ID);

		AppVersion resourcesImporterEEAppVersion =
			appVersionLocalService.fetchAppVersion(
				resourcesImporterEEAppEntry.getAppEntryId(),
				AppVersionConstants.STATUSES_APPROVED, null);

		List<AppPackage> appPackages =
			appPackagePersistence.findByAppVersionId(
				appVersion.getAppVersionId());

		for (AppPackage appPackage : appPackages) {
			int count = appPackagePluginPersistence.countByAPI_CN(
				appPackage.getAppPackageId(),
				AppPackagePluginConstants.CONTEXT_NAME_RESOURCES_IMPORTER);

			if (count > 0) {
				continue;
			}

			AppPackage resourcesImporterPackage =
				appPackageLocalService.fetchCompatibleAppPackage(
					resourcesImporterEEAppVersion.getAppEntryId(),
					appPackage.getCompatibility(),
					WorkflowConstants.STATUS_APPROVED);

			if (resourcesImporterPackage == null) {
				resourcesImporterPackage =
					appPackageLocalService.fetchCompatibleAppPackage(
						resourcesImporterCEAppVersion.getAppEntryId(),
						appPackage.getCompatibility(),
						WorkflowConstants.STATUS_APPROVED);
			}

			if (resourcesImporterPackage == null) {
				continue;
			}

			appPackagePluginLocalService.copyAppPackagePlugins(
				resourcesImporterPackage.getAppPackageId(),
				appPackage.getAppPackageId());
		}
	}

	protected String getAppVersionAssetLicenseName(AssetLicense assetLicense) {
		if (Validator.isNotNull(assetLicense.getName())) {
			return assetLicense.getName();
		}

		StringBundler sb = new StringBundler(7);

		if (assetLicense.getLicenseType() !=
				AssetLicenseConstants.LICENSE_TYPE_UNLIMITED) {

			sb.append(assetLicense.getLicenseTypeAllotment());
			sb.append(StringPool.SPACE);
		}

		sb.append(assetLicense.getUsageTypeLabel());
		sb.append(StringPool.SPACE);
		sb.append(assetLicense.getLicenseTypeLabel());
		sb.append(StringPool.COMMA_AND_SPACE);

		if (assetLicense.getLifetime() ==
				AssetLicenseConstants.LIFETIME_PERPETUAL) {

			sb.append("perpetual");
		}
		else {
			sb.append("non-perpetual");
		}

		return sb.toString();
	}

	protected String getCounterName(long appEntryId) {
		return AppEntry.class.getName().concat(StringPool.POUND).concat(
			String.valueOf(appEntryId));
	}

	protected boolean isUpdateVersion(
			long userId, int releaseType, String version,
			Map<Locale, String> changeLogMap)
		throws SystemException {

		if (releaseType == AppVersionConstants.RELEASE_TYPE_NEW_VERSION) {
			return true;
		}
		else if (releaseType != AppVersionConstants.RELEASE_TYPE_METADATA) {
			return false;
		}
		else if (!roleLocalService.hasUserRole(
					userId, OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID)) {

			return false;
		}
		else if (Validator.isNull(version) || (changeLogMap == null)) {
			return false;
		}

		return true;
	}

	protected void removeResourcesImporterApp(AppVersion appVersion)
		throws PortalException, SystemException {

		List<AppPackage> appPackages =
			appPackagePersistence.findByAppVersionId(
				appVersion.getAppVersionId());

		for (AppPackage appPackage : appPackages) {
			List<AppPackagePlugin> appPackagePlugins =
				appPackagePluginPersistence.findByAPI_CN(
					appPackage.getAppPackageId(),
					AppPackagePluginConstants.CONTEXT_NAME_RESOURCES_IMPORTER);

			for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
				appPackagePluginLocalService.deleteAppPackagePlugin(
					appPackagePlugin);
			}
		}
	}

	protected AppEntry restoreExpiredAppEntry(
			User user, AppEntry appEntry, boolean reindex, String statusMessage)
		throws PortalException, SystemException {

		AppVersion appVersion = appEntry.getApprovedAppVersion();

		if (appVersion == null) {
			appVersion = appEntry.getLatestAppVersion();
		}

		appEntry.setStatus(appVersion.getStatus());
		appEntry.setStatusByUserId(user.getUserId());
		appEntry.setStatusByUserName(user.getFullName());
		appEntry.setStatusDate(new Date());

		appEntryPersistence.update(appEntry, false);

		boolean visible = false;

		if (appVersion.getStatus() == WorkflowConstants.STATUS_APPROVED) {
			visible = true;
		}

		assetEntryLocalService.updateVisible(
			AppEntry.class.getName(), appEntry.getAppEntryId(), visible);

		if (reindex) {
			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
				AppEntry.class);

			indexer.reindex(appEntry);

			MarketplaceEmailUtil.sendAppEntryStatusMail(appEntry);
		}

		return appEntry;
	}

	protected AppVersion updateAppVersion(
			User user, AppVersion appVersion, Date date, String title,
			Map<Locale, String> descriptionMap, String website,
			String demoWebsite, String documentationWebsite,
			String referenceWebsite, String sourceCodeWebsite,
			String supportWebsite, boolean labs, int productType,
			String version, int versionOrder, Map<Locale, String> changeLogMap,
			boolean paclEnabled, Map<Locale, String> eulaContentMap,
			String orderURL, boolean hidden, boolean portalRequired, int status,
			String statusMessage, ServiceContext serviceContext)
		throws PortalException, SystemException {

		appVersion.setModifiedDate(new Date());
		appVersion.setTitle(title);
		appVersion.setDescriptionMap(descriptionMap, LocaleUtil.US);
		appVersion.setWebsite(website);
		appVersion.setDemoWebsite(demoWebsite);
		appVersion.setDocumentationWebsite(documentationWebsite);
		appVersion.setReferenceWebsite(referenceWebsite);
		appVersion.setSourceCodeWebsite(sourceCodeWebsite);
		appVersion.setSupportWebsite(supportWebsite);
		appVersion.setLabs(labs);
		appVersion.setProductType(productType);

		if (version != null) {
			appVersion.setVersion(version);
		}

		updateAppVersionVersionOrder(appVersion, versionOrder);

		appVersion.setChangeLogMap(changeLogMap, LocaleUtil.US);
		appVersion.setPaclEnabled(paclEnabled);

		if ((eulaContentMap != null) && !eulaContentMap.isEmpty()) {
			ServiceContext contractEntryServiceContext = new ServiceContext();

			contractEntryServiceContext.setAttribute(
				"defaultLanguageId", LocaleUtil.toLanguageId(LocaleUtil.US));

			ContractEntry contractEntry = null;

			if (appVersion.getContractEntryId() > 0) {
				contractEntry = contractEntryLocalService.updateContractEntry(
					appVersion.getUserId(), appVersion.getContractEntryId(),
					eulaContentMap, contractEntryServiceContext);
			}
			else {
				contractEntry = contractEntryLocalService.addContractEntry(
					appVersion.getUserId(), AppVersion.class.getName(),
					appVersion.getAppVersionId(),
					ContractEntryConstants.TYPE_APP_EULA, eulaContentMap,
					contractEntryServiceContext);
			}

			appVersion.setContractEntryId(contractEntry.getContractEntryId());
		}
		else {
			if (appVersion.getContractEntryId() > 0) {
				contractEntryLocalService.deleteContractEntry(
					appVersion.getContractEntryId());
			}

			appVersion.setContractEntryId(0);
		}

		appVersion.setOrderURL(orderURL);
		appVersion.setHidden(hidden);
		appVersion.setPortalRequired(portalRequired);
		appVersion.setStatus(status);
		appVersion.setStatusByUserId(user.getUserId());
		appVersion.setStatusByUserName(user.getFullName());
		appVersion.setStatusDate(date);
		appVersion.setStatusMessage(statusMessage);

		appVersionPersistence.update(appVersion, false);

		return appVersion;
	}

	protected void updateAppVersionVersionOrder(
			AppVersion appVersion, int versionOrder)
		throws PortalException, SystemException {

		if ((appVersion.getVersionOrder() == versionOrder) &&
			!appVersion.isNew()) {

			return;
		}

		if (versionOrder == AppVersionConstants.VERSION_ORDER_LAST) {
			AppEntry appEntry = appVersion.getAppEntry();

			if (appEntry.isFirstSubmission()) {
				versionOrder = AppVersionConstants.VERSION_ORDER_FIRST;
			}
			else {
				AppVersion latestAppVersion =
					appVersionLocalService.fetchAppVersion(
						appVersion.getAppEntryId(),
						WorkflowConstants.STATUS_ANY, null);

				versionOrder = latestAppVersion.getVersionOrder() + 1;
			}
		}

		appVersion.setVersionOrder(versionOrder);

		List<AppVersion> appVersions = appVersionPersistence.findByAEI_GtVO(
			appVersion.getAppEntryId(), versionOrder, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, new AppVersionVersionOrderComparator(true));

		for (AppVersion curAppVersion : appVersions) {
			if (appVersion.equals(curAppVersion)) {
				continue;
			}

			versionOrder++;

			curAppVersion.setVersionOrder(versionOrder);

			appVersionPersistence.update(curAppVersion, false);
		}
	}

	protected void validate(
			AppEntry appEntry, AppVersion appVersion, String version,
			Map<Locale, String> changeLogMap)
		throws PortalException, SystemException {

		Matcher matcher = _appEntryVersionPattern.matcher(version);

		if (Validator.isNull(version) || !matcher.matches()) {
			throw new AppEntryVersionException();
		}

		List<AppPackage> appPackages = appPackagePersistence.findByAppVersionId(
			appVersion.getAppVersionId());

		for (AppPackage appPackage : appPackages) {
			List<AppPackagePlugin> appPackagePlugins =
				appPackagePluginPersistence.findByAppPackageId(
					appPackage.getAppPackageId());

			if (appPackagePlugins.isEmpty()) {
				throw new RequiredAppPackagePluginException();
			}
		}

		if (appEntry.isDeveloperEntryLiferayInc()) {
			return;
		}

		if (appEntry.hasMultipleNewVersions() &&
			((changeLogMap == null) ||
			 Validator.isNull(changeLogMap.get(LocaleUtil.US)))) {

			throw new AppEntryChangeLogException();
		}
	}

	protected void validate(
			AppEntry appEntry, long licenseLifetime, boolean supported,
			long[] standardQuantities, boolean standardUnlimited,
			long[] developerQuantities, boolean developerUnlimited)
		throws PortalException, SystemException {

		if (appEntry.isFree()) {
			throw new AppEntryLicenseTypeException();
		}

		if (!ArrayUtil.contains(
				AssetLicenseConstants.LIFETIMES, licenseLifetime)) {

			throw new AppEntryLicenseLifetimeException();
		}

		if (!appEntry.isFirstSubmission()) {
			if (appEntry.getLicenseLifetime() != licenseLifetime) {
				throw new AppEntryLicenseLifetimeException();
			}

			if (appEntry.isSupported() != supported) {
				throw new AppEntryLicenseSupportException();
			}
		}

		int standardLicenseCount = standardQuantities.length;

		if (standardUnlimited) {
			standardLicenseCount++;
		}

		int developerLicenseCount = developerQuantities.length;

		if (developerUnlimited) {
			developerLicenseCount++;
		}

		if ((standardLicenseCount <= 0) && (developerLicenseCount <= 0)) {
			throw new MinimumAssetLicenseException();
		}

		if ((standardLicenseCount > 10) || (developerLicenseCount > 10)) {
			throw new MaximumAssetLicenseException();
		}

		if (standardLicenseCount > 0) {
			boolean singleLicense = false;

			for (long quantity : standardQuantities) {
				if (quantity == 1) {
					singleLicense = true;

					break;
				}
			}

			if (!singleLicense) {
				throw new RequiredAssetLicenseException();
			}
		}

		if (developerLicenseCount > 0) {
			boolean singleLicense = false;

			for (long quantity : developerQuantities) {
				if (quantity == 1) {
					singleLicense = true;

					break;
				}
			}

			if (!singleLicense) {
				throw new RequiredAssetLicenseException();
			}
		}
	}

	protected void validate(AppVersion appVersion)
		throws PortalException, SystemException {

		if (Validator.isNull(appVersion.getVersion())) {
			throw new AppEntryVersionException();
		}

		List<AppPackage> appPackages = appPackagePersistence.findByAppVersionId(
			appVersion.getAppVersionId());

		if (appPackages.isEmpty() && appVersion.isNewRelease()) {
			throw new RequiredAppPackageException();
		}

		for (AppPackage appPackage : appPackages) {
			List<AppPackagePlugin> appPackagePlugins =
				appPackagePluginPersistence.findByAppPackageId(
					appPackage.getAppPackageId());

			if (appPackagePlugins.isEmpty()) {
				throw new RequiredAppPackagePluginException();
			}

			for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
				appPackagePluginLocalService.validateAppPackagePlugin(
					appVersion, appPackage, appPackagePlugin);
			}
		}

		if (!appVersion.hasCustomOrderWorkflow() && !appVersion.isFree()) {
			appPricingLocalService.validateAppPricings(
				appVersion.getAppVersionId());
		}
	}

	protected void validate(
			long appEntryId, long developerEntryId, String title,
			Map<Locale, String> descriptionMap, String website,
			String demoWebsite, String documentationWebsite,
			String referenceWebsite, String sourceCodeWebsite,
			String supportWebsite, String version, File iconFile,
			boolean paclEnabled, int releaseType, int licenseType,
			String orderURL, long[] assetCategoryIds)
		throws PortalException, SystemException {

		if (Validator.isNull(title)) {
			throw new AppEntryTitleException();
		}

		DeveloperEntry developerEntry =
			developerEntryPersistence.findByPrimaryKey(developerEntryId);

		if (!developerEntry.isLiferayInc() &&
			StringUtil.startsWith(title, "liferay")) {

			throw new AppEntryTitleException();
		}

		if ((descriptionMap == null) ||
			Validator.isNull(descriptionMap.get(LocaleUtil.US))) {

			throw new AppEntryDescriptionException();
		}

		AppEntry appEntry = appEntryPersistence.fetchByTitle(title);

		if ((appEntry != null) && (appEntry.getAppEntryId() != appEntryId)) {
			throw new DuplicateAppEntryException();
		}

		if (Validator.isNull(website) || !Validator.isUrl(website)) {
			throw new AppEntryWebsiteException();
		}

		if (Validator.isNotNull(demoWebsite) && !Validator.isUrl(demoWebsite)) {
			throw new AppEntryDemoWebsiteException();
		}

		if (Validator.isNotNull(documentationWebsite) &&
			!Validator.isUrl(documentationWebsite)) {

			throw new AppEntryDocumentationWebsiteException();
		}

		if (Validator.isNotNull(referenceWebsite) &&
			!Validator.isUrl(referenceWebsite)) {

			throw new AppEntryReferenceWebsiteException();
		}

		if (Validator.isNotNull(sourceCodeWebsite) &&
			!Validator.isUrl(sourceCodeWebsite)) {

			throw new AppEntrySourceCodeWebsiteException();
		}

		if (Validator.isNull(supportWebsite) ||
			!Validator.isUrl(supportWebsite)) {

			throw new AppEntrySupportWebsiteException();
		}

		if ((iconFile != null) && iconFile.exists()) {
			RenderedImage renderedImage = null;

			try {
				byte[] bytes = FileUtil.getBytes(iconFile);

				ImageBag imageBag = ImageToolUtil.read(bytes);

				renderedImage = imageBag.getRenderedImage();
			}
			catch (IOException ioe) {
				throw new AppEntryIconException(ioe);
			}

			if ((renderedImage.getHeight() !=
					PortletPropsValues.APP_ENTRY_ICON_MAX_HEIGHT) ||
				(renderedImage.getWidth() !=
					PortletPropsValues.APP_ENTRY_ICON_MAX_WIDTH)) {

				throw new AppEntryIconException();
			}
		}
		else if (appEntryId <= 0) {
			throw new AppEntryIconException();
		}

		if ((licenseType != AssetLicenseConstants.LICENSE_TYPE_FREE) &&
			(licenseType != AssetLicenseConstants.LICENSE_TYPE_PRODUCTION)) {

			throw new AppEntryLicenseTypeException();
		}

		if ((licenseType != AssetLicenseConstants.LICENSE_TYPE_FREE) &&
			!paclEnabled && !developerEntry.isLiferayInc()) {

			throw new AppEntryPACLException();
		}

		if ((releaseType != AppVersionConstants.RELEASE_TYPE_METADATA) &&
			(releaseType != AppVersionConstants.RELEASE_TYPE_NEW_VERSION)) {

			throw new AppEntryReleaseTypeException();
		}

		if ((appEntry != null) && !appEntry.isFirstSubmission() &&
			(appEntry.getLicenseType() != licenseType)) {

			throw new AppEntryLicenseTypeException();
		}

		if ((licenseType != AssetLicenseConstants.LICENSE_TYPE_FREE) &&
			developerEntry.isSubscriptionExpired()) {

			throw new AppEntryLicenseTypeException();
		}

		if (Validator.isNotNull(orderURL) && !Validator.isUrl(orderURL)) {
			throw new AppEntryOrderURLException();
		}

		if (assetCategoryIds == null) {
			throw new AppEntryCategoryException();
		}

		if ((assetCategoryIds.length == 1) &&
			(assetCategoryIds[0] ==
				OSBConstants.ASSET_CATEGORY_EE_PLUGINS_ID)) {

			throw new AppEntryCategoryException();
		}

		Set<Long> parentCategoryIds = new HashSet<Long>();

		for (long assetCategoryId : assetCategoryIds) {
			if (assetCategoryId <= 0) {
				continue;
			}

			AssetCategory assetCategory = assetCategoryLocalService.getCategory(
				assetCategoryId);

			long parentCategoryId = assetCategory.getParentCategoryId();

			if ((parentCategoryId !=
					AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) &&
				!ArrayUtil.contains(assetCategoryIds, parentCategoryId)) {

				throw new AppEntryCategoryException();
			}

			parentCategoryIds.add(parentCategoryId);
		}

		if (parentCategoryIds.isEmpty() ||
			!parentCategoryIds.contains(
				AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID)) {

			throw new AppEntryCategoryException();
		}
	}

	protected void validateStatus(
			AppEntry appEntry, AppVersion appVersion, int status)
		throws PortalException, SystemException {

		if ((status == WorkflowConstants.STATUS_DRAFT) &&
			!appVersion.isPending()) {

			throw new AppEntryStatusException();
		}

		if (appEntry.isFree()) {
			return;
		}

		if ((status == WorkflowConstants.STATUS_APPROVED) ||
			(status == WorkflowConstants.STATUS_PENDING_QA)) {

			DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

			if (developerEntry.isSubscriptionExpired() ||
				(developerEntry.getSubscriptionStatus() !=
					WorkflowConstants.STATUS_APPROVED)) {

				throw new DeveloperEntrySubscriptionStatusException();
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AppEntryLocalServiceImpl.class);

	private final Pattern _appEntryVersionPattern = Pattern.compile("[0-9.]+");
	private final String[] _contextListenerFiles = {
		"PluginContextListener.class", "SecurePluginContextListener.class",
		"filters/LiferayPackageFilter.class",
		"liferaypackage/LiferayPackageUtil.class"
	};

}