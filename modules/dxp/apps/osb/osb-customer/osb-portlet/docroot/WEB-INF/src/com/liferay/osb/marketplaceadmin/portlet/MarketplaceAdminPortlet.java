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

package com.liferay.osb.marketplaceadmin.portlet;

import com.liferay.compat.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.service.ECDocumentEntryServiceUtil;
import com.liferay.osb.AppEntryOrderURLException;
import com.liferay.osb.AppPackageLicenseException;
import com.liferay.osb.AssetReceiptLegalEntityNameException;
import com.liferay.osb.AssetReceiptLicenseEndDateException;
import com.liferay.osb.AssetReceiptLicenseStartDateException;
import com.liferay.osb.CorpEntryDescriptionException;
import com.liferay.osb.CorpProjectNameException;
import com.liferay.osb.DeveloperEntryDomainNameException;
import com.liferay.osb.DeveloperEntryDomainStatusException;
import com.liferay.osb.DeveloperEntryDossieraAccountKeyException;
import com.liferay.osb.DeveloperEntryEmailAddressException;
import com.liferay.osb.DeveloperEntryFatcaWithholdingPercentageException;
import com.liferay.osb.DeveloperEntryFaxNumberException;
import com.liferay.osb.DeveloperEntryNameException;
import com.liferay.osb.DeveloperEntryPaymentAccountException;
import com.liferay.osb.DeveloperEntryPaymentEmailAddressException;
import com.liferay.osb.DeveloperEntryPhoneNumberException;
import com.liferay.osb.DeveloperEntryProfileDescriptionException;
import com.liferay.osb.DeveloperEntryProfileEmailAddressException;
import com.liferay.osb.DeveloperEntryProfileLogoException;
import com.liferay.osb.DeveloperEntryProfileWebsiteException;
import com.liferay.osb.DeveloperEntrySubscriptionExpirationDateException;
import com.liferay.osb.DeveloperEntrySubscriptionStatusException;
import com.liferay.osb.DeveloperEntryTaxDocumentException;
import com.liferay.osb.DeveloperEntryTaxDocumentFileNameException;
import com.liferay.osb.DuplicateAssetReceiptException;
import com.liferay.osb.DuplicateDeveloperEntryDossieraAccountKeyException;
import com.liferay.osb.DuplicateDeveloperEntryException;
import com.liferay.osb.LicenseKeyServerInfoException;
import com.liferay.osb.MaximumLicenseKeyException;
import com.liferay.osb.NoSuchAssetReceiptLicenseException;
import com.liferay.osb.NoSuchContractEntryException;
import com.liferay.osb.PortalReleaseBuildNumberException;
import com.liferay.osb.PortalReleaseNameException;
import com.liferay.osb.RestrictedCountryException;
import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.marketplace.util.MarketplaceTwitterUtil;
import com.liferay.osb.marketplaceadmin.util.MarketplaceAdminDiscussionUtil;
import com.liferay.osb.marketplaceadmin.util.MarketplaceAdminRegistrationUtil;
import com.liferay.osb.marketplaceadmin.util.MarketplaceAdminReportUtil;
import com.liferay.osb.marketplaceadmin.util.MarketplaceQAUtil;
import com.liferay.osb.marketplacedeveloperapps.portlet.MarketplaceDeveloperAppsPortlet;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppFlag;
import com.liferay.osb.model.AppFlagConstants;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetList;
import com.liferay.osb.model.AssetListConstants;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.AssetReceiptConstants;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.model.DeveloperEntryConstants;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppEntryServiceUtil;
import com.liferay.osb.service.AppFlagLocalServiceUtil;
import com.liferay.osb.service.AppFlagServiceUtil;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.AppPackageServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.AssetLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetListAssetEntryLocalServiceUtil;
import com.liferay.osb.service.AssetListLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptServiceUtil;
import com.liferay.osb.service.AssetReceiptSupportLocalServiceUtil;
import com.liferay.osb.service.ContractEntryServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryServiceUtil;
import com.liferay.osb.service.LicenseKeyServiceUtil;
import com.liferay.osb.service.PortalReleaseLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBRequestUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.AssetReceiptCreateDateComparator;
import com.liferay.portal.AddressCityException;
import com.liferay.portal.AddressStreetException;
import com.liferay.portal.AddressZipException;
import com.liferay.portal.DuplicateUserEmailAddressException;
import com.liferay.portal.NoSuchCountryException;
import com.liferay.portal.NoSuchRegionException;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.PhoneNumberException;
import com.liferay.portal.UserEmailAddressException;
import com.liferay.portal.WebsiteURLException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.NoSuchEntryException;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryConstants;
import com.liferay.portlet.asset.model.AssetEntry;

import java.io.File;
import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 * @author Haote Chou
 */
public class MarketplaceAdminPortlet extends MarketplaceDeveloperAppsPortlet {

	public void addAssetLicense(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String className = ParamUtil.getString(actionRequest, "className");
		long classPK = ParamUtil.getLong(actionRequest, "classPK");
		String licenseId = ParamUtil.getString(actionRequest, "licenseId");
		String name = ParamUtil.getString(actionRequest, "name");
		int usageType = ParamUtil.getInteger(actionRequest, "usageType");
		int licenseType = ParamUtil.getInteger(actionRequest, "licenseType");
		long licenseTypeAllotment = ParamUtil.getLong(
			actionRequest, "licenseTypeAllotment");
		long lifetime = ParamUtil.getLong(actionRequest, "lifetime");

		AssetLicenseLocalServiceUtil.addAssetLicense(
			themeDisplay.getUserId(), className, classPK, licenseId, name,
			usageType, licenseType, licenseTypeAllotment, lifetime);
	}

	public void addAssetReceiptLicense(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long assetReceiptId = ParamUtil.getLong(
			actionRequest, "assetReceiptId");
		long assetLicenseId = ParamUtil.getLong(
			actionRequest, "assetLicenseId");
		long assetEntryId = ParamUtil.getLong(actionRequest, "assetEntryId");
		long ownerClassNameId = ParamUtil.getLong(
			actionRequest, "ownerClassNameId");
		long ownerClassPK = ParamUtil.getLong(actionRequest, "ownerClassPK");
		long productClassNameId = ParamUtil.getLong(
			actionRequest, "productClassNameId");
		long productClassPK = ParamUtil.getLong(
			actionRequest, "productClassPK");
		String productId = ParamUtil.getString(actionRequest, "productId");

		AssetReceiptLicense assetReceiptLicense =
			AssetReceiptLicenseLocalServiceUtil.addAssetReceiptLicense(
				themeDisplay.getUserId(), assetReceiptId, assetLicenseId,
				assetEntryId, ownerClassNameId, ownerClassPK,
				productClassNameId, productClassPK, productId, null, null);

		AssetLicense assetLicense = assetReceiptLicense.getAssetLicense();

		String className = assetLicense.getClassName();

		if (!className.equals(AppVersion.class.getName())) {
			return;
		}

		AppVersion appVersion = AppVersionLocalServiceUtil.getAppVersion(
			assetLicense.getClassPK());

		if (!appVersion.isSupported()) {
			return;
		}

		AssetEntry assetEntry = assetReceiptLicense.getAssetEntry();

		Date startDate = assetReceiptLicense.getStartDate();
		Date endDate = new Date(
			startDate.getTime() + AssetLicenseConstants.LIFETIME_SUBSCRIPTION);

		AssetReceiptSupportLocalServiceUtil.addAssetReceiptSupport(
			themeDisplay.getUserId(), assetReceiptId, assetLicenseId,
			assetEntryId, ownerClassNameId, ownerClassPK,
			PortalUtil.getClassNameId(AssetReceiptLicense.class),
			assetReceiptLicense.getAssetReceiptLicenseId(),
			assetEntry.getClassUuid(), startDate, endDate,
			assetReceiptLicense.getUsageType(),
			AssetLicenseConstants.LIFETIME_SUBSCRIPTION);
	}

	public void addContractEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		int type = ParamUtil.getInteger(actionRequest, "type");
		Map<Locale, String> contentMap = LocalizationUtil.getLocalizationMap(
			actionRequest, "content");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		ContractEntryServiceUtil.addContractEntry(
			ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
			ContractEntryConstants.DEFAULT_CLASS_PK, type, contentMap,
			serviceContext);
	}

	public void deactivateLicenseKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long licenseKeyId = ParamUtil.getLong(actionRequest, "licenseKeyId");

		long assetReceiptLicenseId = ParamUtil.getLong(
			actionRequest, "assetReceiptLicenseId");

		LicenseKeyServiceUtil.updateLicenseKey(
			themeDisplay.getUserId(), licenseKeyId, assetReceiptLicenseId,
			false);
	}

	public void deleteAssetLicense(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long assetLicenseId = ParamUtil.getLong(
			actionRequest, "assetLicenseId");

		AssetLicenseLocalServiceUtil.deleteAssetLicense(assetLicenseId);
	}

	public void deleteAssetReceipt(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long assetReceiptId = ParamUtil.getLong(
			actionRequest, "assetReceiptId");

		AssetReceiptLocalServiceUtil.deleteAssetReceipt(assetReceiptId);
	}

	public void deleteAssetReceiptLicense(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long assetReceiptLicenseId = ParamUtil.getLong(
			actionRequest, "assetReceiptLicenseId");

		AssetReceiptLicenseLocalServiceUtil.deleteAssetReceiptLicense(
			assetReceiptLicenseId);
	}

	@Override
	public void invokeTaglibDiscussion(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		String className = ParamUtil.getString(actionRequest, "className");
		long classPK = ParamUtil.getLong(actionRequest, "classPK");

		if (cmd.equals(Constants.ADD)) {
			String body = ParamUtil.getString(actionRequest, "body");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			MarketplaceAdminDiscussionUtil.addMBMessage(
				themeDisplay.getUserId(), className, classPK, body,
				serviceContext);
		}
		else if (cmd.equals(Constants.DELETE)) {
			long mbMessageId = ParamUtil.getLong(actionRequest, "mbMessageId");

			MarketplaceAdminDiscussionUtil.deleteMBMessage(
				themeDisplay.getUserId(), className, classPK, mbMessageId);
		}
		else if (cmd.equals(Constants.SUBSCRIBE_TO_COMMENTS)) {
			MarketplaceAdminDiscussionUtil.addSubscription(
				themeDisplay.getUserId(), className, classPK);
		}
		else if (cmd.equals(Constants.UNSUBSCRIBE_FROM_COMMENTS)) {
			MarketplaceAdminDiscussionUtil.deleteSubscription(
				themeDisplay.getUserId(), className, classPK);
		}
	}

	public void refundECDocumentEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ecDocumentEntryId = ParamUtil.getLong(
			actionRequest, "ecDocumentEntryId");

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryServiceUtil.refundECDocumentEntry(ecDocumentEntryId);
	}

	public void sendManualQAAppVersion(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appVersionId = ParamUtil.getLong(actionRequest, "appVersionId");

		AppVersion appVersion = AppVersionLocalServiceUtil.getAppVersion(
			appVersionId);

		MarketplaceQAUtil.sendManualQAAppVersion(appVersion);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("serveAppPackagePlugin")) {
				if (!RoleLocalServiceUtil.hasUserRole(
						themeDisplay.getUserId(),
						OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID)) {

					throw new PrincipalException();
				}

				OSBRequestUtil.serveAssetAttachment(
					resourceRequest, resourceResponse,
					AssetAttachmentConstants.TYPE_PACKAGE_PLUGIN);
			}
			else if (resourceID.equals("serveAssetReceiptsXLS")) {
				serveAssetReceiptsXLS(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("serveAssetStatsXLS")) {
				serveAssetStatsXLS(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("serveDocument")) {
				OSBRequestUtil.serveAssetAttachment(
					resourceRequest, resourceResponse,
					AssetAttachmentConstants.TYPE_DOCUMENT);
			}
			else if (resourceID.equals("serveLicensedApp")) {
				serveUnpackagedApp(resourceRequest, resourceResponse, true);
			}
			else if (resourceID.equals("serveLicenseKey")) {
				serveLicenseKey(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateAppEntryStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");

		int status = ParamUtil.getInteger(actionRequest, "status", -1);
		String statusMessage = ParamUtil.getString(
			actionRequest, "statusMessage");

		AppVersion appVersion = null;

		if (status == WorkflowConstants.STATUS_DENIED) {
			appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
				appEntryId, AppVersionConstants.STATUSES_PENDING, null);

			if (appVersion == null) {
				status = WorkflowConstants.STATUS_EXPIRED;
			}
			else {
				MarketplaceQAUtil.removeAppVersion(appVersion);
			}
		}
		else if (status == WorkflowConstants.STATUS_PENDING_QA) {
			appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
				appEntryId, AppVersionConstants.STATUSES_PENDING, null);

			MarketplaceQAUtil.sendAppVersion(appVersion);
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		AppEntry appEntry = AppEntryServiceUtil.updateStatus(
			appEntryId, status, statusMessage, serviceContext);

		if (status == WorkflowConstants.STATUS_APPROVED) {
			appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
				appEntryId, WorkflowConstants.STATUS_APPROVED, null);

			MarketplaceQAUtil.removeAppVersion(appVersion);

			updateMarketplaceMarketing(appEntry, serviceContext, actionRequest);
		}
	}

	public void updateAppPackageSrc(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");

		AppVersion appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
			appEntryId, AppVersionConstants.STATUSES_UNRELEASED, null);

		List<AppPackage> appPackages =
			AppPackageLocalServiceUtil.getAppPackages(
				appVersion.getAppVersionId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		try {
			for (AppPackage appPackage : appPackages) {
				String inputName = "srcFile" + appPackage.getCompatibility();

				File file = uploadPortletRequest.getFile(inputName);

				if ((file == null) || !file.exists()) {
					continue;
				}

				String fileName = uploadPortletRequest.getFileName(inputName);

				AppPackageServiceUtil.addAppPackageSrc(
					appPackage.getAppPackageId(), fileName, file);
			}
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void updateAssetListAssetEntries(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long assetCategoryId = ParamUtil.getLong(
			actionRequest, "assetCategoryId");

		for (int assetListType : AssetListConstants.TYPES_EDITABLE_LISTS) {
			boolean assetListModified = ParamUtil.getBoolean(
				actionRequest, "assetListModified" + assetListType);

			if (!assetListModified) {
				continue;
			}

			String assetListAssetEntryIds = ParamUtil.getString(
				actionRequest, "assetListAssetEntryIds" + assetListType);

			long[] assetEntryIds = StringUtil.split(assetListAssetEntryIds, 0L);

			AssetList assetList = AssetListLocalServiceUtil.fetchAssetList(
				assetCategoryId, assetListType);

			if (assetList == null) {
				assetList = AssetListLocalServiceUtil.addAssetList(
					assetCategoryId, assetListType);
			}

			AssetListAssetEntryLocalServiceUtil.setAssetListAssetEntries(
				assetList.getAssetListId(), assetEntryIds);
		}
	}

	public void updateAssetReceipt(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long purchasedByUserId = ParamUtil.getLong(
			actionRequest, "purchasedByUserId");

		String ownerClassName = StringPool.BLANK;
		long ownerClassPK = ParamUtil.getLong(actionRequest, "ownerClassPK");

		if (ownerClassPK > 0) {
			ownerClassName = CorpProject.class.getName();
		}
		else {
			ownerClassName = User.class.getName();
			ownerClassPK = purchasedByUserId;
		}

		String legalEntityName = ParamUtil.getString(
			actionRequest, "legalEntityName");
		String productClassName = ParamUtil.getString(
			actionRequest, "productClassName");
		long productClassPK = ParamUtil.getLong(
			actionRequest, "productClassPK");

		AssetReceipt assetReceipt = AssetReceiptLocalServiceUtil.purchaseAsset(
			purchasedByUserId, ownerClassName, ownerClassPK, legalEntityName,
			productClassName, productClassPK,
			AssetReceiptConstants.TYPE_PURCHASE_APP);

		List<AssetLicense> assetLicenses = assetReceipt.getAssetLicenses();

		if (!assetLicenses.isEmpty()) {
			String redirect = ParamUtil.getString(actionRequest, "redirect");

			LiferayPortletResponse liferayPortletResponse =
				(LiferayPortletResponse)actionResponse;

			PortletURL portletURL = liferayPortletResponse.createRenderURL();

			portletURL.setParameter(
				"mvcPath", "/marketplace_admin/edit_asset_receipt.jsp");
			portletURL.setParameter("redirect", redirect);
			portletURL.setParameter(
				"assetReceiptId",
				String.valueOf(assetReceipt.getAssetReceiptId()));

			actionRequest.setAttribute(WebKeys.REDIRECT, portletURL.toString());
		}
	}

	public void updateAssetReceiptLicense(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long assetReceiptId = ParamUtil.getLong(
			actionRequest, "assetReceiptId");

		int usageType = ParamUtil.getInteger(actionRequest, "usageType");

		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear,
			themeDisplay.getTimeZone(),
			AssetReceiptLicenseStartDateException.class);

		int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");

		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear, themeDisplay.getTimeZone(),
			AssetReceiptLicenseEndDateException.class);

		AssetReceiptLicenseLocalServiceUtil.updateAssetReceiptLicenses(
			assetReceiptId, usageType, startDate, endDate);
	}

	public void updateAssetReceiptOwner(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long assetReceiptId = ParamUtil.getLong(
			actionRequest, "assetReceiptId");

		String ownerClassName = ParamUtil.getString(
			actionRequest, "ownerClassName");
		long ownerClassPK = ParamUtil.getLong(actionRequest, "ownerClassPK");

		AssetReceiptServiceUtil.updateAssetReceipt(
			assetReceiptId, ownerClassName, ownerClassPK);
	}

	public void updateAwaitingResponseAppFlag(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		AppVersion appVersion = appEntry.getActionableAppVersion();

		AppFlag appFlag = AppFlagLocalServiceUtil.fetchAppFlag(
			appVersion.getAppVersionId(),
			AppFlagConstants.TYPE_AWAITING_RESPONSE);

		Locale locale = LocaleUtil.getDefault();

		String statusMessage = StringPool.BLANK;

		if (appFlag == null) {
			AppFlagServiceUtil.addAppFlag(
				appEntryId, appVersion.getAppVersionId(),
				AppFlagConstants.TYPE_AWAITING_RESPONSE);

			statusMessage = LanguageUtil.get(
				locale, "flag-app-as-awaiting-response");
		}
		else {
			AppFlagServiceUtil.deleteAppFlag(appFlag.getAppFlagId());

			statusMessage = LanguageUtil.get(
				locale, "unflag-app-as-awaiting-response");
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		User user = UserLocalServiceUtil.getUser(themeDisplay.getUserId());

		MarketplaceAdminDiscussionUtil.addSystemDiscussionMessage(
			user.getUserId(), user.getFullName(), appEntryId,
			appVersion.getStatus(), statusMessage, new ServiceContext());
	}

	public void updateCompanyDeveloperEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			long developerEntryId = ParamUtil.getLong(
				uploadPortletRequest, "developerEntryId");

			String emailAddress = ParamUtil.getString(
				uploadPortletRequest, "emailAddress");
			String legalEntityName = ParamUtil.getString(
				uploadPortletRequest, "legalEntityName");
			String phoneNumber = ParamUtil.getString(
				uploadPortletRequest, "phoneNumber");
			String faxNumber = ParamUtil.getString(
				uploadPortletRequest, "faxNumber");
			String street1 = ParamUtil.getString(
				uploadPortletRequest, "street1");
			String street2 = ParamUtil.getString(
				uploadPortletRequest, "street2");
			String street3 = ParamUtil.getString(
				uploadPortletRequest, "street3");
			String city = ParamUtil.getString(uploadPortletRequest, "city");
			String zip = ParamUtil.getString(uploadPortletRequest, "zip");
			long regionId = ParamUtil.getLong(uploadPortletRequest, "regionId");
			long countryId = ParamUtil.getLong(
				uploadPortletRequest, "countryId");
			Map<Locale, String> profileDescriptionMap =
				LocalizationUtil.getLocalizationMap(
					uploadPortletRequest, "profileDescription");
			String profileEmailAddress = ParamUtil.getString(
				uploadPortletRequest, "profileEmailAddress");
			File profileLogoFile = uploadPortletRequest.getFile("profileLogo");
			String profileWebsite = ParamUtil.getString(
				uploadPortletRequest, "profileWebsite");
			String taxDocumentType = ParamUtil.getString(
				uploadPortletRequest, "taxDocumentType");
			File taxDocumentFile = uploadPortletRequest.getFile("taxDocument");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				actionRequest);

			DeveloperEntryServiceUtil.updateCompanyDeveloperEntry(
				developerEntryId, emailAddress, legalEntityName, phoneNumber,
				faxNumber, street1, street2, street3, city, zip, regionId,
				countryId, profileLogoFile, profileDescriptionMap,
				profileEmailAddress, profileWebsite, taxDocumentType,
				taxDocumentFile, serviceContext);

			String redirect = ParamUtil.getString(
				uploadPortletRequest, "redirect");

			actionResponse.sendRedirect(redirect);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void updateDeveloperEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long developerEntryId = ParamUtil.getLong(
			uploadPortletRequest, "developerEntryId");

		String domainName = ParamUtil.getString(
			uploadPortletRequest, "domainName");
		int domainStatus = ParamUtil.getInteger(
			uploadPortletRequest, "domainStatus");
		String paymentFirstName = ParamUtil.getString(
			uploadPortletRequest, "paymentFirstName");
		String paymentLastName = ParamUtil.getString(
			uploadPortletRequest, "paymentLastName");
		String paymentEmailAddress = ParamUtil.getString(
			uploadPortletRequest, "paymentEmailAddress");

		int subscriptionExpirationDateYear = ParamUtil.getInteger(
			uploadPortletRequest, "subscriptionExpirationDateYear");
		int subscriptionExpirationDateMonth = ParamUtil.getInteger(
			uploadPortletRequest, "subscriptionExpirationDateMonth");
		int subscriptionExpirationDateDay = ParamUtil.getInteger(
			uploadPortletRequest, "subscriptionExpirationDateDay");

		Date subscriptionExpirationDate = PortalUtil.getDate(
			subscriptionExpirationDateMonth, subscriptionExpirationDateDay,
			subscriptionExpirationDateYear, themeDisplay.getTimeZone(),
			DeveloperEntrySubscriptionExpirationDateException.class);

		int subscriptionStatus = ParamUtil.getInteger(
			uploadPortletRequest, "subscriptionStatus");

		double fatcaWithholdingPercentage = ParamUtil.getDouble(
			uploadPortletRequest, "fatcaWithholdingPercentage",
			DeveloperEntryConstants.FATCA_WITHOLDING_PERCENTAGE_DEFAULT);

		boolean useDefaultFatca = ParamUtil.getBoolean(
			uploadPortletRequest, "useDefaultFatca");

		if (useDefaultFatca) {
			fatcaWithholdingPercentage =
				DeveloperEntryConstants.FATCA_WITHOLDING_PERCENTAGE_DEFAULT;
		}

		try {
			String taxDocumentType = ParamUtil.getString(
				uploadPortletRequest, "taxDocumentType");
			File taxDocumentFile = uploadPortletRequest.getFile("taxDocument");

			DeveloperEntryServiceUtil.updateDeveloperEntry(
				developerEntryId, domainName, domainStatus, paymentFirstName,
				paymentLastName, paymentEmailAddress,
				subscriptionExpirationDate, subscriptionStatus,
				fatcaWithholdingPercentage, taxDocumentType, taxDocumentFile);

			String redirect = ParamUtil.getString(
				uploadPortletRequest, "redirect");

			actionResponse.sendRedirect(redirect);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void updateDeveloperEntryDossieraAccountKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long developerEntryId = ParamUtil.getLong(
			actionRequest, "developerEntryId");

		DeveloperEntry developerEntry =
			DeveloperEntryLocalServiceUtil.getDeveloperEntry(developerEntryId);

		CorpEntry corpEntry = CorpEntryLocalServiceUtil.fetchCorpEntry(
			developerEntry.getDossieraAccountKey());

		if (corpEntry != null) {
			throw new PrincipalException();
		}

		if (cmd.equals(Constants.ADD)) {
			MarketplaceAdminRegistrationUtil.updateDeveloperEntryStatus(
				developerEntryId, developerEntry.getDossieraAccountKey(),
				WorkflowConstants.STATUS_APPROVED, StringPool.BLANK);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			String dossieraAccountKey = ParamUtil.getString(
				actionRequest, "dossieraAccountKey");

			DeveloperEntryServiceUtil.updateDeveloperEntry(
				developerEntryId, dossieraAccountKey);
		}
	}

	public void updateDeveloperEntryStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long developerEntryId = ParamUtil.getLong(
			actionRequest, "developerEntryId");

		String dossieraAccountKey = ParamUtil.getString(
			actionRequest, "dossieraAccountKey");
		int status = ParamUtil.getInteger(
			actionRequest, "status", WorkflowConstants.STATUS_ANY);
		String statusMessage = ParamUtil.getString(
			actionRequest, "statusMessage");

		MarketplaceAdminRegistrationUtil.updateDeveloperEntryStatus(
			developerEntryId, dossieraAccountKey, status, statusMessage);
	}

	public void updateDeveloperEntrySubscriptionStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long developerEntryId = ParamUtil.getLong(
			actionRequest, "developerEntryId");

		int subscriptionStatus = ParamUtil.getInteger(
			actionRequest, "subscriptionStatus");

		DeveloperEntry developerEntry =
			DeveloperEntryLocalServiceUtil.getDeveloperEntry(developerEntryId);

		DeveloperEntryServiceUtil.updateSubscription(
			developerEntryId, developerEntry.getSubscriptionExpirationDate(),
			subscriptionStatus);
	}

	public void updateECDocumentEntryStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ecDocumentEntryId = ParamUtil.getLong(
			actionRequest, "ecDocumentEntryId");

		int status = ParamUtil.getInteger(actionRequest, "status");

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryServiceUtil.updateStatus(ecDocumentEntryId, status);
	}

	public void updateECDocumentEntryType(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ecDocumentEntryId = ParamUtil.getLong(
			actionRequest, "ecDocumentEntryId");

		int type = ParamUtil.getInteger(actionRequest, "type");

		ECDocumentEntry ecDocumentEntry = ECDocumentEntryServiceUtil.updateType(
			ecDocumentEntryId, type);
	}

	public void updatePortalRelease(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long portalReleaseId = ParamUtil.getLong(
			actionRequest, "portalReleaseId");

		String versionName = ParamUtil.getString(actionRequest, "versionName");
		int buildNumber = ParamUtil.getInteger(actionRequest, "buildNumber");
		String fixPackName = ParamUtil.getString(actionRequest, "fixPackName");
		boolean ee = ParamUtil.getBoolean(actionRequest, "ee");
		boolean marketPlaceSupport = ParamUtil.getBoolean(
			actionRequest, "marketplaceSupport");
		boolean osgiSupport = ParamUtil.getBoolean(
			actionRequest, "osgiSupport");
		boolean paclSupport = ParamUtil.getBoolean(
			actionRequest, "paclSupport");
		boolean hidden = ParamUtil.getBoolean(actionRequest, "hidden");

		if (portalReleaseId > 0) {
			PortalReleaseLocalServiceUtil.updatePortalRelease(
				portalReleaseId, versionName, buildNumber, fixPackName, ee,
				marketPlaceSupport, osgiSupport, paclSupport, hidden);
		}
		else {
			PortalReleaseLocalServiceUtil.addPortalRelease(
				versionName, buildNumber, ee, marketPlaceSupport, osgiSupport,
				paclSupport, hidden);
		}
	}

	protected long[] getAssetCategoryIds(
			AssetEntry assetEntry, List<AssetCategory> assetCategories)
		throws SystemException {

		List<Long> assetCategoryIds = new ArrayList<Long>();

		for (AssetCategory assetCategory : assetEntry.getCategories()) {
			if (assetCategory.getVocabularyId() !=
					OSBConstants.ASSET_VOCABULARY_MARKETPLACE_ID) {

				assetCategoryIds.add(assetCategory.getCategoryId());
			}
		}

		for (AssetCategory assetCategory : assetCategories) {
			assetCategoryIds.add(assetCategory.getCategoryId());
		}

		return ArrayUtil.toArray(
			assetCategoryIds.toArray(new Long[assetCategoryIds.size()]));
	}

	@Override
	protected boolean isProcessPortletRequest(PortletRequest portletRequest) {
		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)portletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			if (RoleLocalServiceUtil.hasUserRole(
					themeDisplay.getUserId(),
					OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID) ||
				RoleLocalServiceUtil.hasUserRole(
					themeDisplay.getUserId(),
					OSBConstants.ROLE_OSB_MARKETPLACE_TESTER_ID)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (_log.isDebugEnabled()) {
			_log.debug(cause, cause);
		}

		if (cause instanceof AddressCityException ||
			cause instanceof AddressStreetException ||
			cause instanceof AddressZipException ||
			cause instanceof AppEntryOrderURLException ||
			cause instanceof AppPackageLicenseException ||
			cause instanceof AssetReceiptLegalEntityNameException ||
			cause instanceof AssetReceiptLicenseEndDateException ||
			cause instanceof AssetReceiptLicenseStartDateException ||
			cause instanceof CorpEntryDescriptionException ||
			cause instanceof CorpProjectNameException||
			cause instanceof DeveloperEntryDomainNameException ||
			cause instanceof DeveloperEntryDomainStatusException ||
			cause instanceof DeveloperEntryDossieraAccountKeyException ||
			cause instanceof DeveloperEntryEmailAddressException ||
			cause instanceof
				DeveloperEntryFatcaWithholdingPercentageException ||
			cause instanceof DeveloperEntryFaxNumberException ||
			cause instanceof DeveloperEntryNameException ||
			cause instanceof DeveloperEntryPaymentAccountException ||
			cause instanceof DeveloperEntryPaymentEmailAddressException ||
			cause instanceof DeveloperEntryPhoneNumberException ||
			cause instanceof DeveloperEntryProfileDescriptionException ||
			cause instanceof DeveloperEntryProfileEmailAddressException ||
			cause instanceof DeveloperEntryProfileLogoException ||
			cause instanceof DeveloperEntryProfileWebsiteException ||
			cause instanceof DeveloperEntrySubscriptionStatusException ||
			cause instanceof DeveloperEntryTaxDocumentException ||
			cause instanceof DeveloperEntryTaxDocumentFileNameException ||
			cause instanceof DuplicateAssetReceiptException ||
			cause instanceof
				DuplicateDeveloperEntryDossieraAccountKeyException ||
			cause instanceof DuplicateDeveloperEntryException ||
			cause instanceof DuplicateUserEmailAddressException ||
			cause instanceof LicenseKeyServerInfoException ||
			cause instanceof MaximumLicenseKeyException ||
			cause instanceof NoSuchAssetReceiptLicenseException ||
			cause instanceof NoSuchContractEntryException ||
			cause instanceof NoSuchCountryException ||
			cause instanceof NoSuchEntryException ||
			cause instanceof NoSuchRegionException ||
			cause instanceof NoSuchUserException ||
			cause instanceof PhoneNumberException ||
			cause instanceof PortalReleaseBuildNumberException ||
			cause instanceof PortalReleaseNameException ||
			cause instanceof RestrictedCountryException ||
			cause instanceof UserEmailAddressException ||
			cause instanceof WebsiteURLException) {

			return true;
		}
		else {
			return super.isSessionErrorException(cause);
		}
	}

	protected void serveAssetReceiptsXLS(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long productClassNameId = ParamUtil.getLong(
			resourceRequest, "productClassNameId");
		String assetEntryTitle = ParamUtil.getString(
			resourceRequest, "assetEntryTitle");
		long ownerClassNameId = ParamUtil.getLong(
			resourceRequest, "ownerClassNameId");
		long ownerClassPK = ParamUtil.getLong(resourceRequest, "ownerClassPK");

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (Validator.isNotNull(assetEntryTitle)) {
			params.put("assetEntryTitle", assetEntryTitle);
		}

		if (ownerClassNameId > 0) {
			params.put("ownerClassNameId", ownerClassNameId);
		}

		if (ownerClassPK > 0) {
			params.put("ownerClassPK", ownerClassPK);
		}

		List<AssetReceipt> assetReceipts =
			AssetReceiptLocalServiceUtil.getAssetReceipts(
				productClassNameId, params, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, new AssetReceiptCreateDateComparator(true));

		if (assetReceipts.isEmpty()) {
			return;
		}

		File file = MarketplaceAdminReportUtil.getAssetReceiptsXLS(
			assetReceipts);

		String contentType = MimeTypesUtil.getContentType(file.getName());

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, "customers.xls",
			new FileInputStream(file), contentType);
	}

	protected void serveAssetStatsXLS(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		int startDateMonth = ParamUtil.getInteger(
			resourceRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(
			resourceRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			resourceRequest, "startDateYear");

		Calendar startCalendar = CalendarFactoryUtil.getCalendar(
			TimeZoneUtil.getTimeZone(StringPool.UTC), themeDisplay.getLocale());

		startCalendar.set(Calendar.MONTH, startDateMonth);
		startCalendar.set(Calendar.DATE, startDateDay);
		startCalendar.set(Calendar.YEAR, startDateYear);

		int endDateMonth = ParamUtil.getInteger(
			resourceRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(resourceRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(resourceRequest, "endDateYear");

		Calendar endCalendar = CalendarFactoryUtil.getCalendar(
			TimeZoneUtil.getTimeZone(StringPool.UTC), themeDisplay.getLocale());

		endCalendar.set(Calendar.MONTH, endDateMonth);
		endCalendar.set(Calendar.DATE, endDateDay);
		endCalendar.set(Calendar.YEAR, endDateYear);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		boolean hideLiferayAppEntries = ParamUtil.getBoolean(
			resourceRequest, "hideLiferayAppEntries");

		if (hideLiferayAppEntries) {
			params.put(
				"notDeveloperEntryId",
				OSBConstants.DEVELOPER_ENTRY_LIFERAY_INC_ID);
		}

		String orderByCol = ParamUtil.getString(resourceRequest, "orderByCol");
		String orderByType = ParamUtil.getString(
			resourceRequest, "orderByType");

		File file = MarketplaceAdminReportUtil.getAssetStatsXLS(
			startCalendar, endCalendar, params, orderByCol, orderByType);

		String contentType = MimeTypesUtil.getContentType(file.getName());

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, "metrics.xls",
			new FileInputStream(file), contentType);
	}

	protected void serveLicenseKey(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long licenseKeyId = ParamUtil.getLong(resourceRequest, "licenseKeyId");

		LicenseKey licenseKey = LicenseKeyServiceUtil.getLicenseKey(
			licenseKeyId);

		String fileName = LicenseUtil.getLicenseKeyFileName(licenseKey);
		String licenseXML = LicenseUtil.exportToXML(licenseKey);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, fileName, licenseXML.getBytes(),
			ContentTypes.TEXT_XML);
	}

	protected void updateAssetListAssetEntry(
		AssetEntry assetEntry, int listType, int retries,
		PortletRequest portletRequest) {

		try {
			AssetList assetList = AssetListLocalServiceUtil.fetchAssetList(
				AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, listType);

			if (assetList == null) {
				assetList = AssetListLocalServiceUtil.addAssetList(
					AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID,
					listType);
			}

			List<AssetEntry> assetEntries = assetList.getAssetEntries(true);

			assetEntries = ListUtil.copy(assetEntries);

			assetEntries.remove(assetEntry);

			assetEntries.add(0, assetEntry);

			if (assetEntries.size() > 50) {
				assetEntries = ListUtil.subList(assetEntries, 0, 50);
			}

			AssetListAssetEntryLocalServiceUtil.setAssetListAssetEntries(
				assetList.getAssetListId(), assetEntries);
		}
		catch (Exception e) {
			if (retries > 0) {
				retries--;

				updateAssetListAssetEntry(
					assetEntry, listType, retries, portletRequest);

				return;
			}

			SessionMessages.add(portletRequest, "updateAssetListFailed");
		}
	}

	protected void updateMarketplaceMarketing(
			AppEntry appEntry, ServiceContext serviceContext,
			PortletRequest portletRequest)
		throws Exception {

		AppVersion appVersion = appEntry.getApprovedAppVersion();

		int count = AppVersionLocalServiceUtil.getAppVersionsCount(
			appEntry.getAppEntryId(), WorkflowConstants.STATUS_ANY);

		// Store front

		int retries = 3;

		AssetEntry assetEntry = appEntry.getAssetEntry();

		if (appVersion.isNewRelease() && (count == 1)) {
			updateAssetListAssetEntry(
				assetEntry, AssetListConstants.TYPE_NEW_AND_INTERESTING,
				retries, portletRequest);
			updateAssetListAssetEntry(
				assetEntry, AssetListConstants.TYPE_NEW_APPS, retries,
				portletRequest);
		}
		else {
			updateAssetListAssetEntry(
				assetEntry, AssetListConstants.TYPE_APP_UPDATES, retries,
				portletRequest);
		}

		// Twitter

		if (appVersion.isNewRelease() && (count == 1)) {
			MarketplaceTwitterUtil.sendNewAppEntryTweet(appEntry);
		}
		else {
			MarketplaceTwitterUtil.sendUpdatedAppEntryTweet(appEntry);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceAdminPortlet.class);

}