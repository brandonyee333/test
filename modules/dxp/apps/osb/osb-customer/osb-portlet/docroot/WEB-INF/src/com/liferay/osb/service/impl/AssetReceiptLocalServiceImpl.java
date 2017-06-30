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
import com.liferay.compat.portal.kernel.util.BigDecimalUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.model.ECDocumentItem;
import com.liferay.ecommerce.service.ECDocumentEntryLocalServiceUtil;
import com.liferay.osb.AssetReceiptCorpProjectException;
import com.liferay.osb.AssetReceiptLegalEntityNameException;
import com.liferay.osb.AssetReceiptOwnerClassNameException;
import com.liferay.osb.DuplicateAssetReceiptException;
import com.liferay.osb.marketplace.util.ECDocumentEntryExtraSettings;
import com.liferay.osb.marketplace.util.ECDocumentItemExtraSettings;
import com.liferay.osb.marketplace.util.MarketplaceEmailUtil;
import com.liferay.osb.marketplace.util.MarketplaceUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetAuditConstants;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.AssetReceiptConstants;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.AssetReceiptSupport;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.CurrencyEntry;
import com.liferay.osb.service.AssetReceiptRedeemTokenLocalServiceUtil;
import com.liferay.osb.service.base.AssetReceiptLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.util.Portal;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.asset.model.AssetEntry;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 * @author Joan Kim
 */
public class AssetReceiptLocalServiceImpl
	extends AssetReceiptLocalServiceBaseImpl {

	@Override
	public AssetReceipt deleteAssetReceipt(AssetReceipt assetReceipt)
		throws PortalException, SystemException {

		// Asset receipt

		assetReceiptPersistence.remove(assetReceipt);

		// Asset receipt license

		assetReceiptLicensePersistence.removeByAssetReceiptId(
			assetReceipt.getAssetReceiptId());

		// Asset receipt support

		assetReceiptSupportPersistence.removeByAssetReceiptId(
			assetReceipt.getAssetReceiptId());

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			AssetReceipt.class);

		indexer.delete(assetReceipt);

		return assetReceipt;
	}

	@Override
	public AssetReceipt deleteAssetReceipt(long assetReceiptId)
		throws PortalException, SystemException {

		AssetReceipt assetReceipt = assetReceiptPersistence.findByPrimaryKey(
			assetReceiptId);

		return deleteAssetReceipt(assetReceipt);
	}

	public List<AssetReceipt> deleteAssetReceipts(
			String ownerClassName, long ownerClassPK)
		throws PortalException, SystemException {

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("ownerClassNameId", ownerClassNameId);
		params.put("ownerClassPK", ownerClassPK);

		List<AssetReceipt> assetReceipts =
			assetReceiptFinder.findByProductClassNameId(
				null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (AssetReceipt assetReceipt : assetReceipts) {
			deleteAssetReceipt(assetReceipt);
		}

		return assetReceipts;
	}

	public AssetReceipt fetchAssetReceipt(
			String ownerClassName, long ownerClassPK, String productClassName,
			long productClassPK)
		throws SystemException {

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);
		long productClassNameId = PortalUtil.getClassNameId(productClassName);

		return assetReceiptPersistence.fetchByOCNI_OCPK_PCNI_PCPK(
			ownerClassNameId, ownerClassPK, productClassNameId, productClassPK);
	}

	public AssetReceipt getAssetReceipt(
			long userId, String productClassName, long productClassPK)
		throws PortalException, SystemException {

		long productClassNameId = PortalUtil.getClassNameId(productClassName);

		return assetReceiptPersistence.findByUI_PCNI_PCPK(
			userId, productClassNameId, productClassPK);
	}

	public AssetReceipt getAssetReceipt(
			String ownerClassName, long ownerClassPK, String productClassName,
			long productClassPK)
		throws PortalException, SystemException {

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);
		long productClassNameId = PortalUtil.getClassNameId(productClassName);

		return assetReceiptPersistence.findByOCNI_OCPK_PCNI_PCPK(
			ownerClassNameId, ownerClassPK, productClassNameId, productClassPK);
	}

	public List<AssetReceipt> getAssetReceipts(
			long productClassNameId, LinkedHashMap<String, Object> params,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		return assetReceiptFinder.findByProductClassNameId(
			productClassNameId, params, start, end, obc);
	}

	public int getAssetReceiptsCount(
			long productClassNameId, LinkedHashMap<String, Object> params)
		throws SystemException {

		return assetReceiptFinder.countByProductClassNameId(
			productClassNameId, params);
	}

	public boolean hasAssetReceipt(
			long userId, String productClassName, long productClassPK)
		throws SystemException {

		long productClassNameId = PortalUtil.getClassNameId(productClassName);

		int count = assetReceiptPersistence.countByUI_PCNI_PCPK(
			userId, productClassNameId, productClassPK);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasAssetReceipt(
			String ownerClassName, long ownerClassPK, String productClassName,
			long productClassPK)
		throws SystemException {

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);
		long productClassNameId = PortalUtil.getClassNameId(productClassName);

		int count = assetReceiptPersistence.countByOCNI_OCPK_PCNI_PCPK(
			ownerClassNameId, ownerClassPK, productClassNameId, productClassPK);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public AssetReceipt purchaseAsset(
			long userId, String ownerClassName, long ownerClassPK,
			String legalEntityName, String productClassName,
			long productClassPK, long type)
		throws PortalException, SystemException {

		validate(ownerClassName, legalEntityName, null);

		return updateAssetReceipt(
			userId, ownerClassName, ownerClassPK, legalEntityName,
			productClassName, productClassPK, type);
	}

	public AssetReceipt purchaseAssets(
			long userId, String ownerClassName, long ownerClassPK,
			String legalEntityName, String productClassName,
			long productClassPK, long type, long ecDocumentEntryId,
			long countryId, int domain)
		throws PortalException, SystemException {

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			productClassName, productClassPK);

		AssetReceipt assetReceipt = updateAssetReceipt(
			userId, ownerClassName, ownerClassPK, legalEntityName,
			productClassName, productClassPK, type);

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
				ecDocumentEntryId);

		validate(ownerClassName, legalEntityName, ecDocumentEntry);

		List<ECDocumentItem> ecDocumentItems =
			ecDocumentEntry.getECDocumentItems();

		Date now = new Date();

		int assetAuditType = AssetAuditConstants.TYPE_PURCHASE;
		double totalPrice = 0;

		for (ECDocumentItem ecDocumentItem : ecDocumentItems) {
			ECDocumentItemExtraSettings ecDocumentItemExtraSettings =
				new ECDocumentItemExtraSettings(ecDocumentItem);

			int usageType = ecDocumentItemExtraSettings.getUsageType();

			if (usageType == AssetLicenseConstants.USAGE_TYPE_TRIAL) {
				assetAuditType = AssetAuditConstants.TYPE_TRIAL;
			}

			Date startDate = ecDocumentItemExtraSettings.getStartDate();

			if (startDate == null) {
				startDate = now;
			}

			Date endDate = ecDocumentItemExtraSettings.getEndDate();

			if (endDate == null) {
				endDate = new Date(
					startDate.getTime() +
						AssetLicenseConstants.LIFETIME_SUBSCRIPTION);
			}

			String itemType = ecDocumentItemExtraSettings.getItemType();

			if (itemType.equals("license")) {
				String purchaseType =
					ecDocumentItemExtraSettings.getPurchaseType();

				if (purchaseType.equals("new") ||
					purchaseType.equals("renew")) {

					purchaseAssetLicense(
						assetReceipt, ecDocumentItem, assetEntry, startDate,
						endDate);
				}
				else if (purchaseType.equals("coterm")) {
					purchaseAssetLicenseCoterm(
						assetReceipt,
						ecDocumentItemExtraSettings.getUsageType(), startDate,
						endDate);
				}
			}
			else if (itemType.equals("support")) {
				purchaseAssetSupport(
					assetReceipt, ecDocumentItemExtraSettings.getUsageType(),
					assetEntry, startDate, endDate);
			}

			totalPrice = BigDecimalUtil.add(
				totalPrice, ecDocumentItem.getLinePrice());
		}

		if (type == AssetReceiptConstants.TYPE_PURCHASE_APP) {
			assetAuditLocalService.addAssetAudit(
				userId, legalEntityName, productClassName, productClassPK,
				assetAuditType, domain, ecDocumentEntry.getCurrencyCode(),
				totalPrice);
		}

		ecDocumentEntry = ECDocumentEntryLocalServiceUtil.updateECDocumentEntry(
			ecDocumentEntryId, now, ecDocumentEntry.getExtraSettings());

		MarketplaceEmailUtil.sendAssetReceiptMail(
			assetReceipt, ecDocumentEntry);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		if (ecDocumentEntryExtraSettings.isResale()) {
			sendResaleEndUserMail(assetReceipt, ecDocumentEntry);

			User endUser = ecDocumentEntryExtraSettings.getEndUser();

			if (endUser != null) {
				if (!corpProjectLocalService.hasUserCorpProject(
						endUser.getUserId(), ownerClassPK)) {

					corpProjectLocalService.addCorpProjectUsers(
						ownerClassPK, new long[] {endUser.getUserId()});
				}

				if (!corpProjectLocalService.hasUserCorpProjectRole(
						endUser.getUserId(), ownerClassPK,
						OSBConstants.ROLE_OSB_CORP_ADMIN_ID)) {

					corpProjectLocalService.addUserCorpProjectRoles(
						userId, ownerClassPK, new long[] {endUser.getUserId()},
						OSBConstants.ROLE_OSB_CORP_ADMIN_ID);
				}
			}
			else {
				AssetReceiptRedeemTokenLocalServiceUtil.
					addAssetReceiptRedeemToken(
						userId,
						PortalUtil.getClassNameId(ECDocumentEntry.class),
						ecDocumentEntryId,
						ecDocumentEntryExtraSettings.getEndUserEmailAddress());
			}
		}

		sendMessage(assetReceipt);

		return assetReceipt;
	}

	public AssetReceipt updateAssetReceipt(
			long assetReceiptId, String ownerClassName, long ownerClassPK)
		throws PortalException, SystemException {

		// Asset receipt

		Date now = new Date();

		AssetReceipt assetReceipt = assetReceiptPersistence.findByPrimaryKey(
			assetReceiptId);

		validate(assetReceipt.getAssetEntryId(), ownerClassName, ownerClassPK);

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);

		assetReceipt.setOwnerClassNameId(ownerClassNameId);
		assetReceipt.setOwnerClassPK(ownerClassPK);
		assetReceipt.setLegalEntityName(StringPool.BLANK);

		assetReceiptPersistence.update(assetReceipt, false);

		List<AssetReceiptLicense> assetReceiptLicenses =
			assetReceiptLicenseLocalService.getAssetReceiptLicenses(
				assetReceiptId);

		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			assetReceiptLicense.setModifiedDate(now);
			assetReceiptLicense.setOwnerClassNameId(ownerClassNameId);
			assetReceiptLicense.setOwnerClassPK(ownerClassPK);

			assetReceiptLicensePersistence.update(assetReceiptLicense, false);
		}

		List<AssetReceiptSupport> assetReceiptSupports =
			assetReceiptSupportPersistence.findByARI_PCNI_PCPK(
				assetReceipt.getAssetReceiptId(), ownerClassNameId,
				assetReceipt.getProductClassPK(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (AssetReceiptSupport assetReceiptSupport : assetReceiptSupports) {
			assetReceiptSupport.setModifiedDate(now);
			assetReceiptSupport.setOwnerClassNameId(ownerClassNameId);
			assetReceiptSupport.setOwnerClassPK(ownerClassPK);

			assetReceiptSupportPersistence.update(assetReceiptSupport, false);
		}

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			AssetReceipt.class);

		indexer.reindex(assetReceipt);

		return assetReceipt;
	}

	protected void purchaseAssetLicense(
			AssetReceipt assetReceipt, ECDocumentItem ecDocumentItem,
			AssetEntry assetEntry, Date startDate, Date endDate)
		throws PortalException, SystemException {

		for (int i = 0; i < ecDocumentItem.getQuantity(); i++) {
			AssetLicense assetLicense =
				assetLicensePersistence.findByPrimaryKey(
					ecDocumentItem.getClassPK());

			Date licenseEndDate = endDate;

			if (assetLicense.getLifetime() ==
					AssetLicenseConstants.LIFETIME_PERPETUAL) {

				licenseEndDate = new Date(
					startDate.getTime() + assetLicense.getLifetime());
			}

			AssetReceiptLicense assetReceiptLicense =
				assetReceiptLicenseLocalService.addAssetReceiptLicense(
					assetReceipt.getUserId(), assetReceipt.getAssetReceiptId(),
					assetLicense.getAssetLicenseId(), assetEntry.getEntryId(),
					assetReceipt.getOwnerClassNameId(),
					assetReceipt.getOwnerClassPK(),
					assetReceipt.getProductClassNameId(),
					assetReceipt.getProductClassPK(), assetEntry.getClassUuid(),
					startDate, licenseEndDate);

			String className = assetLicense.getClassName();

			if (!className.equals(AppVersion.class.getName()) ||
				(assetLicense.getUsageType() ==
					AssetLicenseConstants.USAGE_TYPE_TRIAL)) {

				continue;
			}

			AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
				assetLicense.getClassPK());

			if (!appVersion.isSupported()) {
				continue;
			}

			long classNameId = PortalUtil.getClassNameId(
				AssetReceiptLicense.class);

			assetReceiptSupportLocalService.addAssetReceiptSupport(
				assetReceipt.getUserId(), assetReceipt.getAssetReceiptId(),
				ecDocumentItem.getClassPK(), assetEntry.getEntryId(),
				assetReceipt.getOwnerClassNameId(),
				assetReceipt.getOwnerClassPK(), classNameId,
				assetReceiptLicense.getAssetReceiptLicenseId(),
				assetEntry.getClassUuid(), startDate, endDate,
				assetReceiptLicense.getUsageType(),
				AssetLicenseConstants.LIFETIME_SUBSCRIPTION);
		}
	}

	protected void purchaseAssetLicenseCoterm(
			AssetReceipt assetReceipt, int usageType, Date startDate,
			Date endDate)
		throws PortalException, SystemException {

		assetReceiptLicenseLocalService.updateAssetReceiptLicenses(
			assetReceipt.getAssetReceiptId(), usageType, startDate, endDate);
	}

	protected void purchaseAssetSupport(
			AssetReceipt assetReceipt, int usageType, AssetEntry assetEntry,
			Date startDate, Date endDate)
		throws PortalException, SystemException {

		List<AssetReceiptLicense> assetReceiptLicenses =
			assetReceiptLicenseLocalService.getActiveAssetReceiptLicenses(
				assetReceipt.getAssetReceiptId(), usageType, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		long classNameId = PortalUtil.getClassNameId(AssetReceiptLicense.class);

		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			AssetReceiptSupport assetReceiptSupport =
				assetReceiptSupportPersistence.
					fetchByARI_PCNI_PCPK_LtSD_GtED_First(
						assetReceipt.getAssetReceiptId(), classNameId,
						assetReceiptLicense.getAssetReceiptLicenseId(),
						startDate, startDate, null);

			if (assetReceiptSupport == null) {
				assetReceiptSupportLocalService.addAssetReceiptSupport(
					assetReceipt.getUserId(), assetReceipt.getAssetReceiptId(),
					assetReceiptLicense.getAssetReceiptLicenseId(),
					assetEntry.getEntryId(), assetReceipt.getOwnerClassNameId(),
					assetReceipt.getOwnerClassPK(), classNameId,
					assetReceiptLicense.getAssetReceiptLicenseId(),
					assetEntry.getClassUuid(), startDate, endDate,
					assetReceiptLicense.getUsageType(),
					AssetLicenseConstants.LIFETIME_SUBSCRIPTION);
			}
			else {
				assetReceiptSupportLocalService.updateAssetReceiptSupport(
					assetReceiptSupport.getAssetReceiptSupportId(), endDate);
			}
		}
	}

	protected void sendMessage(AssetReceipt assetReceipt)
		throws PortalException, SystemException {

		AppEntry appEntry = appEntryPersistence.fetchByPrimaryKey(
			assetReceipt.getProductClassPK());

		if (!appEntry.isDeveloperEntryLiferayInc()) {
			return;
		}

		Message message = new Message();

		message.put("appEntryId", appEntry.getAppEntryId());
		message.put("ownerClassNameId", assetReceipt.getOwnerClassNameId());
		message.put("ownerClassPK", assetReceipt.getOwnerClassPK());

		MessageBusUtil.sendMessage("liferay/osb_asset_receipt", message);
	}

	protected void sendResaleEndUserMail(
			AssetReceipt assetReceipt, ECDocumentEntry ecDocumentEntry)
		throws PortalException, SystemException {

		String fromName = "Liferay Marketplace";
		String fromAddress = "noreply@liferay.com";

		PortletPreferences preferences =
			MarketplaceUtil.getPortletPreferences();

		Map<Locale, String> subjectMap =
			MarketplaceEmailUtil.getLocalizationMap(
				"EmailResaleAssetReceiptSubject");
		Map<Locale, String> bodyMap =
			MarketplaceEmailUtil.getLocalizationMap(
				"EmailResaleAssetReceiptBody");

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		AppEntry appEntry = appEntryPersistence.findByPrimaryKey(
			ecDocumentEntryExtraSettings.getAppEntryId());

		String fullLayoutURL = PortalUtil.getLayoutFullURL(
			OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);

		String appEntryURL =
			fullLayoutURL + Portal.FRIENDLY_URL_SEPARATOR + "mp/application/" +
				appEntry.getAppEntryId();

		String appEntryIconURL =
			fullLayoutURL + Portal.FRIENDLY_URL_SEPARATOR + "mp/asset/icon/" +
				appEntry.getIconImageId();

		Company company = CompanyLocalServiceUtil.getCompany(
			OSBConstants.COMPANY_ID);

		String portalURL = PortalUtil.getPortalURL(
			company.getVirtualHostname(), PortalUtil.getPortalPort(true), true);

		String marketplaceLogoURL =
			portalURL + "/osb-portlet/marketplace_navigation/images/" +
				"marketplace_logo.png";

		User endUser = ecDocumentEntryExtraSettings.getEndUser();

		String fullName = StringPool.BLANK;
		String viewingInstructions = StringPool.BLANK;

		if (endUser != null) {
			fullName = endUser.getFullName();

			String appManagerURL = PortalUtil.getLayoutFullURL(
				endUser.getGroupId(), OSBPortletKeys.OSB_MARKETPLACE_APPS);

			appManagerURL +=
				Portal.FRIENDLY_URL_SEPARATOR + "marketplace_apps/" +
					assetReceipt.getAssetReceiptId();

			String pattern =
				"to-view-and-download-your-app-please-go-to-your-app-manager-" +
					"on-liferay-com";

			Object[] arguments = new Object[] {
				"<a href=\"" + appManagerURL + "\">", "</a>"};

			viewingInstructions = LanguageUtil.format(
				endUser.getLocale(), pattern, arguments, false);
		}
		else {
			fullName = PortalUtil.getFullName(
				ecDocumentEntryExtraSettings.getEndUserFirstName(),
				StringPool.BLANK,
				ecDocumentEntryExtraSettings.getEndUserLastName());

			User user = userPersistence.findByPrimaryKey(
				ecDocumentEntry.getUserId());

			String pattern =
				"to-view-and-download-your-app-you-must-first-create-a-" +
					"liferay-com-account";

			Object[] arguments = new Object[] {
				"<a href=\"" + portalURL + "\">", "</a>",
				ecDocumentEntryExtraSettings.getEndUserEmailAddress(),
				assetReceipt.getOwnerName()};

			viewingInstructions = LanguageUtil.format(
				user.getLocale(), pattern, arguments, false);
		}

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);
		subscriptionSender.setContextAttributes(
			"[$APP_ENTRY_ICON_URL$]", appEntryIconURL, "[$APP_ENTRY_TITLE$]",
			appEntry.getTitle(), "[$APP_ENTRY_URL$]", appEntryURL,
			"[$MARKETPLACE_LOGO$]", marketplaceLogoURL, "[$OWNER_NAME$]",
			assetReceipt.getFormalLegalEntityName(), "[$USER_FULL_NAME$]",
			fullName);
		subscriptionSender.setContextAttribute(
			"[$VIEWING_INSTRUCTIONS$]", viewingInstructions, false);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"asset_receipt", assetReceipt.getAssetReceiptId());

		if (endUser != null) {
			subscriptionSender.setUserId(endUser.getUserId());
		}

		subscriptionSender.addRuntimeSubscribers(
			ecDocumentEntryExtraSettings.getEndUserEmailAddress(), fullName);

		subscriptionSender.flushNotificationsAsync();
	}

	protected AssetReceipt updateAssetReceipt(
			long userId, String ownerClassName, long ownerClassPK,
			String legalEntityName, String productClassName,
			long productClassPK, long type)
		throws PortalException, SystemException {

		// Asset receipt

		User user = userPersistence.findByPrimaryKey(userId);
		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);
		long productClassNameId = PortalUtil.getClassNameId(productClassName);

		AssetEntry assetEntry = assetEntryLocalService.getEntry(
			productClassName, productClassPK);

		validate(0, ownerClassName, ownerClassPK);

		CurrencyEntry currencyEntry =
			currencyEntryLocalService.getCurrencyEntry("USD");

		long currencyEntryId = currencyEntry.getCurrencyEntryId();

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("assetEntryId", assetEntry.getEntryId());
		params.put("ownerClassNameId", ownerClassNameId);
		params.put("ownerClassPK", ownerClassPK);

		List<AssetReceipt> assetReceipts =
			assetReceiptFinder.findByProductClassNameId(
				productClassNameId, params, 0, 1, null);

		if (!assetReceipts.isEmpty()) {
			return assetReceipts.get(0);
		}

		long assetReceiptId = counterLocalService.increment();

		AssetReceipt assetReceipt = assetReceiptPersistence.create(
			assetReceiptId);

		assetReceipt.setUserId(userId);
		assetReceipt.setUserName(user.getFullName());
		assetReceipt.setCreateDate(new Date());
		assetReceipt.setAssetEntryId(assetEntry.getEntryId());
		assetReceipt.setOwnerClassNameId(ownerClassNameId);
		assetReceipt.setOwnerClassPK(ownerClassPK);
		assetReceipt.setLegalEntityName(legalEntityName);
		assetReceipt.setProductClassNameId(productClassNameId);
		assetReceipt.setProductClassPK(productClassPK);
		assetReceipt.setType(type);
		assetReceipt.setCurrencyEntryId(currencyEntryId);
		assetReceipt.setActualPrice(0);

		assetReceiptPersistence.update(assetReceipt, false);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			AssetReceipt.class);

		indexer.reindex(assetReceipt);

		return assetReceipt;
	}

	protected void validate(
			long assetEntryId, String ownerClassName, long ownerClassPK)
		throws PortalException, SystemException {

		if (ownerClassName.equals(CorpProject.class.getName())) {
			corpProjectPersistence.findByPrimaryKey(ownerClassPK);
		}
		else if (ownerClassName.equals(User.class.getName())) {
			userPersistence.findByPrimaryKey(ownerClassPK);
		}
		else {
			throw new AssetReceiptOwnerClassNameException();
		}

		if (assetEntryId == 0) {
			return;
		}

		AssetEntry assetEntry = assetEntryPersistence.findByPrimaryKey(
			assetEntryId);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("assetEntryId", assetEntryId);

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);

		params.put("ownerClassNameId", ownerClassNameId);

		params.put("ownerClassPK", ownerClassPK);

		int count = assetReceiptFinder.countByProductClassNameId(
			assetEntry.getClassNameId(), params);

		if (count > 0) {
			throw new DuplicateAssetReceiptException();
		}
	}

	protected void validate(
			String ownerClassName, String legalEntityName,
			ECDocumentEntry ecDocumentEntry)
		throws PortalException, SystemException {

		if ((ecDocumentEntry == null) || (ecDocumentEntry.getTotal() <= 0)) {
			return;
		}

		if (ownerClassName.equals(CorpProject.class.getName())) {
			if (Validator.isNull(legalEntityName)) {
				throw new AssetReceiptLegalEntityNameException();
			}
		}
		else {
			Country country = countryPersistence.findByPrimaryKey(
				ecDocumentEntry.getShippingAddressCountryId());

			if (ArrayUtil.contains(
					PortletPropsValues.
					MARKETPLACE_PROJECTS_ONLY_PURCHASE_COUNTRIES,
				country.getA2())) {

				throw new AssetReceiptCorpProjectException();
			}
		}
	}

}