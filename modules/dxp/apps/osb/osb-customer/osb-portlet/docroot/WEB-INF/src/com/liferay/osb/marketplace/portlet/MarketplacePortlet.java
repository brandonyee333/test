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

package com.liferay.osb.marketplace.portlet;

import com.liferay.compat.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.model.ECDocumentEntryConstants;
import com.liferay.ecommerce.service.ECDocumentEntryLocalServiceUtil;
import com.liferay.ecommerce.service.ECDocumentEntryServiceUtil;
import com.liferay.ecommerce.service.ECDocumentItemLocalServiceUtil;
import com.liferay.ipgeocoder.model.IPInfo;
import com.liferay.ipgeocoder.util.IPGeocoderUtil;
import com.liferay.osb.AddressCompanyNameException;
import com.liferay.osb.AppEntryCategoryException;
import com.liferay.osb.AppEntryTitleException;
import com.liferay.osb.AppEntryVersionException;
import com.liferay.osb.AssetEntryReviewLengthException;
import com.liferay.osb.AssetLicenseLicenseTypeAllotmentException;
import com.liferay.osb.AssetReceiptEmailAddressException;
import com.liferay.osb.AssetReceiptLegalEntityNameException;
import com.liferay.osb.AssetReceiptOwnerClassNameException;
import com.liferay.osb.ContractAuditAcceptanceException;
import com.liferay.osb.ExpiredAppPricingItemException;
import com.liferay.osb.NoSuchAppEntryException;
import com.liferay.osb.NoSuchContractEntryException;
import com.liferay.osb.NoSuchCorpEntryException;
import com.liferay.osb.NoSuchCorpProjectException;
import com.liferay.osb.RequiredEndUserEmailAddressException;
import com.liferay.osb.RequiredEndUserFirstNameException;
import com.liferay.osb.RequiredEndUserLastNameException;
import com.liferay.osb.RequiredEndUserPhoneNumberException;
import com.liferay.osb.RequiredQuantityException;
import com.liferay.osb.RequiredRenewalException;
import com.liferay.osb.RestrictedReviewException;
import com.liferay.osb.SalesforceAddressException;
import com.liferay.osb.TrialLicenseException;
import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.marketplace.util.ECDocumentEntryExtraSettings;
import com.liferay.osb.marketplace.util.MarketplaceCatalogUtil;
import com.liferay.osb.marketplace.util.MarketplaceEcommerceUtil;
import com.liferay.osb.marketplace.util.MarketplaceSOEEUtil;
import com.liferay.osb.marketplace.util.MarketplaceUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.impl.AssetAuditImpl;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppEntryServiceUtil;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.AppPackageServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.AssetAttachmentLocalServiceUtil;
import com.liferay.osb.service.AssetLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLicenseLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptServiceUtil;
import com.liferay.osb.service.ContractAuditServiceUtil;
import com.liferay.osb.service.CorpProjectServiceUtil;
import com.liferay.osb.service.LicenseKeyServiceUtil;
import com.liferay.osb.service.permission.OSBAppEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.OSBRequestUtil;
import com.liferay.osb.util.OSBWebKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.AppVersionVersionOrderComparator;
import com.liferay.osb.util.comparator.AssetLicenseLicenseTypeAllotmentComparator;
import com.liferay.osb.util.mvc.OSBPortlet;
import com.liferay.portal.AddressCityException;
import com.liferay.portal.AddressStreetException;
import com.liferay.portal.AddressZipException;
import com.liferay.portal.NoSuchAddressException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.zip.ZipWriter;
import com.liferay.portal.kernel.zip.ZipWriterFactoryUtil;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.Ticket;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.CountryServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.service.TicketLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.NoSuchCategoryException;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.flags.service.FlagsEntryServiceUtil;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.messageboards.MessageBodyException;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;
import com.liferay.portlet.ratings.service.RatingsEntryServiceUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Douglas Wong
 * @author Joan Kim
 */
public class MarketplacePortlet extends OSBPortlet {

	public void acceptContractEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		long contractEntryId = ParamUtil.getLong(
			actionRequest, "contractEntryId");
		String signatoryClassName = ParamUtil.getString(
			actionRequest, "signatoryClassName");
		long signatoryClassPK = ParamUtil.getLong(
			actionRequest, "signatoryClassPK");
		String productClassName = ParamUtil.getString(
			actionRequest, "productClassName");
		long productClassPK = ParamUtil.getLong(
			actionRequest, "productClassPK");

		ContractAuditServiceUtil.addContractAudit(
			contractEntryId, signatoryClassName, signatoryClassPK,
			productClassName, productClassPK);
	}

	public void addCorpProject(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String name = ParamUtil.getString(actionRequest, "name");

		CorpProjectServiceUtil.addCorpProject(name);
	}

	public void cancelPurchaseAppEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ecDocumentEntryId = ParamUtil.getLong(
			actionRequest, "ecDocumentEntryId");

		ECDocumentEntryServiceUtil.deleteECDocumentEntry(ecDocumentEntryId);
	}

	public void deleteDiscussionMessage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String className = ParamUtil.getString(actionRequest, "className");
		long classPK = ParamUtil.getLong(actionRequest, "classPK");
		long messageId = ParamUtil.getLong(actionRequest, "messageId");

		MBMessageServiceUtil.deleteDiscussionMessage(
			OSBConstants.GROUP_GUEST_ID, className, classPK, className, classPK,
			themeDisplay.getUserId(), messageId);
	}

	public void deleteECDocumentItem(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ecDocumentItemId = ParamUtil.getLong(
			actionRequest, "ecDocumentItemId");

		MarketplaceEcommerceUtil.deleteECDocumentItem(ecDocumentItemId);
	}

	public void followOwner(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		String ownerClassName = ParamUtil.getString(
			actionRequest, "ownerClassName");
		long ownerClassPK = ParamUtil.getLong(actionRequest, "ownerClassPK");

		SubscriptionLocalServiceUtil.addSubscription(
			themeDisplay.getUserId(), 0, ownerClassName, ownerClassPK);
	}

	public void purchaseAppEntryCoterm(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ecDocumentEntryId = ParamUtil.getLong(
			actionRequest, "ecDocumentEntryId");

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		ECDocumentEntry ecDocumentEntry = null;

		if (appEntry.getLicenseLifetime() ==
				AssetLicenseConstants.LIFETIME_PERPETUAL) {

			boolean cotermStandardSupport = ParamUtil.getBoolean(
				actionRequest, "cotermStandardSupport");
			boolean cotermDeveloperSupport = ParamUtil.getBoolean(
				actionRequest, "cotermDeveloperSupport");

			ecDocumentEntry =
				MarketplaceEcommerceUtil.updateECDocumentEntrySupportCoterm(
					ecDocumentEntryId, cotermStandardSupport,
					cotermDeveloperSupport);
		}
		else if (appEntry.getLicenseLifetime() ==
					AssetLicenseConstants.LIFETIME_SUBSCRIPTION) {

			boolean cotermStandardLicenses = ParamUtil.getBoolean(
				actionRequest, "cotermStandardLicenses");
			boolean cotermDeveloperLicenses = ParamUtil.getBoolean(
				actionRequest, "cotermDeveloperLicenses");

			ecDocumentEntry =
				MarketplaceEcommerceUtil.
					updateECDocumentEntryAssetLicenseCoterm(
						ecDocumentEntryId, cotermStandardLicenses,
						cotermDeveloperLicenses);
		}

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)actionResponse;

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcPath", "/marketplace/purchase_app_entry.jsp");
		portletURL.setParameter("purchaseStep", "destination");

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		if (ecDocumentEntryExtraSettings.getAddressId() > 0) {
			portletURL.setParameter("purchaseStep", "summary");
		}

		portletURL.setParameter(
			"ecDocumentEntryId", String.valueOf(ecDocumentEntryId));
		portletURL.setParameter("appEntryId", String.valueOf(appEntryId));

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void purchaseAppEntryDestination(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		long ecDocumentEntryId = ParamUtil.getLong(
			actionRequest, "ecDocumentEntryId");
		long addressId = ParamUtil.getLong(actionRequest, "addressId");

		if (cmd.equals(Constants.UPDATE)) {
			String street1 = ParamUtil.getString(actionRequest, "street1");
			String street2 = ParamUtil.getString(actionRequest, "street2");
			String street3 = ParamUtil.getString(actionRequest, "street3");
			String city = ParamUtil.getString(actionRequest, "city");
			String zip = ParamUtil.getString(actionRequest, "zip");
			long regionId = ParamUtil.getLong(actionRequest, "regionId");
			long countryId = ParamUtil.getLong(actionRequest, "countryId");
			String companyName = ParamUtil.getString(
				actionRequest, "companyName");
			String vatNumber = ParamUtil.getString(actionRequest, "vatNumber");

			Contact contact = themeDisplay.getContact();

			MarketplaceEcommerceUtil.updateECDocumentEntryDestination(
				themeDisplay.getUserId(), ecDocumentEntryId, addressId,
				contact.getContactId(), street1, street2, street3, city, zip,
				regionId, countryId, companyName, vatNumber);
		}
		else {
			MarketplaceEcommerceUtil.updateECDocumentEntryDestination(
				themeDisplay.getUserId(), ecDocumentEntryId, addressId);
		}
	}

	public void purchaseAppEntryEndUser(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ecDocumentEntryId = ParamUtil.getLong(
			actionRequest, "ecDocumentEntryId");

		String emailAddress = ParamUtil.getString(
			actionRequest, "emailAddress");
		String firstName = ParamUtil.getString(actionRequest, "firstName");
		String lastName = ParamUtil.getString(actionRequest, "lastName");
		String phoneNumber = ParamUtil.getString(actionRequest, "phoneNumber");
		boolean emailReceipt = ParamUtil.getBoolean(
			actionRequest, "emailReceipt");

		MarketplaceEcommerceUtil.updateECDocumentEntryEndUser(
			ecDocumentEntryId, emailAddress, firstName, lastName, phoneNumber,
			emailReceipt);
	}

	public void purchaseAppEntryLicense(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ecDocumentEntryId = ParamUtil.getLong(
			actionRequest, "ecDocumentEntryId");

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");
		String purchaseType = ParamUtil.getString(
			actionRequest, "purchaseType");
		long countryId = ParamUtil.getLong(actionRequest, "countryId");

		Map<AssetLicense, Integer> assetLicensesMap = getAssetLicensesMap(
			appEntryId, actionRequest);

		ECDocumentEntry ecDocumentEntry =
			MarketplaceEcommerceUtil.updateECDocumentItemAssetLicenses(
				ecDocumentEntryId, assetLicensesMap, purchaseType, countryId);

		String purchaseStep = ParamUtil.getString(
			actionRequest, "purchaseStep");

		if (!purchaseStep.equals("license")) {
			return;
		}

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)actionResponse;

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcPath", "/marketplace/purchase_app_entry.jsp");

		if (isCotermRequired(
				appEntryId, ecDocumentEntryExtraSettings.getOwnerClassName(),
				ecDocumentEntryExtraSettings.getOwnerClassPK())) {

			portletURL.setParameter("purchaseStep", "coterm");
		}
		else if (ecDocumentEntryExtraSettings.getAddressId() > 0) {
			portletURL.setParameter("purchaseStep", "summary");
		}
		else {
			portletURL.setParameter("purchaseStep", "destination");
		}

		portletURL.setParameter(
			"ecDocumentEntryId", String.valueOf(ecDocumentEntryId));
		portletURL.setParameter("appEntryId", String.valueOf(appEntryId));

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void purchaseAppEntryProject(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ecDocumentEntryId = ParamUtil.getLong(
			actionRequest, "ecDocumentEntryId");

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");
		String ownerClassName = ParamUtil.getString(
			actionRequest, "ownerClassName");
		long ownerClassPK = ParamUtil.getLong(actionRequest, "ownerClassPK");
		int domain = AssetAuditImpl.getDomain(themeDisplay.getPortalURL());
		boolean trial = ParamUtil.getBoolean(actionRequest, "trial");
		boolean acceptContract = ParamUtil.getBoolean(
			actionRequest, "acceptContract");
		String legalEntityName = ParamUtil.getString(
			actionRequest, "legalEntityName");
		long countryId = ParamUtil.getLong(actionRequest, "countryId");

		String portletId = PortalUtil.getPortletId(actionRequest);

		PortletURL portletURL = PortletURLFactoryUtil.create(
			actionRequest, portletId, themeDisplay.getPlid(),
			PortletRequest.RENDER_PHASE);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		if (appEntry.isSOEEPlugin() && !trial) {
			AppVersion appVersion = appEntry.getApprovedAppVersion();

			long contractEntryId = 0;

			if (acceptContract) {
				contractEntryId = appVersion.getContractEntryId();
			}

			if (ownerClassName.equals(User.class.getName())) {
				ownerClassPK = themeDisplay.getUserId();
			}

			MarketplaceSOEEUtil.updateSalesforce(
				themeDisplay.getUserId(), ownerClassName, ownerClassPK,
				AppEntry.class.getName(), appEntry.getAppEntryId(),
				contractEntryId, actionRequest);

			portletURL.setParameter(
				MarketplaceUtil.getMVCPathParam(actionRequest),
				"/marketplace/so_ee_thank_you.jsp");

			actionResponse.sendRedirect(portletURL.toString());
		}
		else if (appEntry.isFree() || trial) {
			ECDocumentEntry ecDocumentEntry =
				MarketplaceEcommerceUtil.purchaseAppEntryFree(
					themeDisplay.getUserId(), appEntryId, ownerClassName,
					ownerClassPK, countryId, domain, acceptContract,
					legalEntityName);

			portletURL.setParameter(
				MarketplaceUtil.getMVCPathParam(actionRequest),
				"/marketplace/view_app_entry_receipt.jsp");
			portletURL.setParameter(
				"ecDocumentEntryId",
				String.valueOf(ecDocumentEntry.getEcDocumentEntryId()));
			portletURL.setParameter("appEntryId", String.valueOf(appEntryId));
		}
		else {
			boolean resale = ParamUtil.getBoolean(actionRequest, "resale");

			ECDocumentEntry ecDocumentEntry =
				MarketplaceEcommerceUtil.updateECDocumentEntryProject(
					themeDisplay.getUserId(), ecDocumentEntryId, ownerClassName,
					ownerClassPK, appEntryId, countryId, resale);

			ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
				new ECDocumentEntryExtraSettings(ecDocumentEntry);

			portletURL.setParameter(
				"mvcPath", "/marketplace/purchase_app_entry.jsp");

			int count = ECDocumentItemLocalServiceUtil.getECDocumentItemsCount(
				ecDocumentEntry.getEcDocumentEntryId());

			if (resale) {
				portletURL.setParameter("purchaseStep", "end-user");
			}
			else if (count == 0) {
				portletURL.setParameter("purchaseStep", "license");
			}
			else if (ecDocumentEntryExtraSettings.getAddressId() <= 0) {
				portletURL.setParameter("purchaseStep", "destination");
			}
			else {
				portletURL.setParameter("purchaseStep", "summary");
			}

			portletURL.setParameter(
				"ecDocumentEntryId",
				String.valueOf(ecDocumentEntry.getEcDocumentEntryId()));
			portletURL.setParameter("appEntryId", String.valueOf(appEntryId));
		}

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void purchaseAppEntryRenew(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long ecDocumentEntryId = ParamUtil.getLong(
			actionRequest, "ecDocumentEntryId");
		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");
		String purchaseType = ParamUtil.getString(
			actionRequest, "purchaseType");
		long countryId = ParamUtil.getLong(actionRequest, "countryId");

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
				ecDocumentEntryId);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		AppVersion appVersion = appEntry.getApprovedAppVersion();

		if (appEntry.getLicenseLifetime() ==
				AssetLicenseConstants.LIFETIME_SUBSCRIPTION) {

			AssetReceipt assetReceipt =
				AssetReceiptLocalServiceUtil.getAssetReceipt(
					ecDocumentEntryExtraSettings.getOwnerClassName(),
					ecDocumentEntryExtraSettings.getOwnerClassPK(),
					AppEntry.class.getName(), appEntry.getAppEntryId());

			Map<AssetLicense, Integer> assetLicensesMap = getAssetLicensesMap(
				appEntryId, actionRequest);

			boolean renewAssetLicenseDeveloper = ParamUtil.getBoolean(
				actionRequest, "renewAssetLicenseDeveloper");

			if (renewAssetLicenseDeveloper) {
				getAssetLicenseMapRenewal(
					assetLicensesMap, assetReceipt.getAssetReceiptId(),
					appVersion.getAppVersionId(),
					AssetLicenseConstants.USAGE_TYPE_DEVELOPER);
			}

			boolean renewAssetLicenseStandard = ParamUtil.getBoolean(
				actionRequest, "renewAssetLicenseStandard");

			if (renewAssetLicenseStandard) {
				getAssetLicenseMapRenewal(
					assetLicensesMap, assetReceipt.getAssetReceiptId(),
					appVersion.getAppVersionId(),
					AssetLicenseConstants.USAGE_TYPE_STANDARD);
			}

			MarketplaceEcommerceUtil.updateECDocumentItemAssetLicenses(
				ecDocumentEntryId, assetLicensesMap, purchaseType, countryId);
		}
		else {
			boolean renewAssetReceiptSupportDeveloper = ParamUtil.getBoolean(
				actionRequest, "renewAssetReceiptSupportDeveloper");
			boolean renewAssetReceiptSupportStandard = ParamUtil.getBoolean(
				actionRequest, "renewAssetReceiptSupportStandard");

			MarketplaceEcommerceUtil.updateECDocumentItemSupport(
				ecDocumentEntryId, renewAssetReceiptSupportStandard,
				renewAssetReceiptSupportDeveloper, purchaseType, countryId);
		}

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)actionResponse;

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcPath", "/marketplace/purchase_app_entry.jsp");
		portletURL.setParameter("purchaseStep", "destination");

		if (ecDocumentEntryExtraSettings.getAddressId() > 0) {
			portletURL.setParameter("purchaseStep", "summary");
		}

		portletURL.setParameter(
			"ecDocumentEntryId", String.valueOf(ecDocumentEntryId));
		portletURL.setParameter("appEntryId", String.valueOf(appEntryId));

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void purchaseAppEntrySummary(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long ecDocumentEntryId = ParamUtil.getLong(
			actionRequest, "ecDocumentEntryId");

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");
		String paymentMethod = ParamUtil.getString(
			actionRequest, "paymentMethod");
		boolean acceptContract = ParamUtil.getBoolean(
			actionRequest, "acceptContract");
		int domain = AssetAuditImpl.getDomain(themeDisplay.getPortalURL());

		try {
			MarketplaceEcommerceUtil.updateECDocumentEntryPayment(
				ecDocumentEntryId, paymentMethod, acceptContract, domain);
		}
		catch (ExpiredAppPricingItemException eapie) {
			ECDocumentEntryServiceUtil.deleteECDocumentEntry(ecDocumentEntryId);

			SessionErrors.add(
				actionRequest, ExpiredAppPricingItemException.class);

			LiferayPortletResponse liferayPortletResponse =
				(LiferayPortletResponse)actionResponse;

			PortletURL portletURL = liferayPortletResponse.createRenderURL();

			portletURL.setParameter(
				"mvcPath", "/marketplace/purchase_app_entry.jsp");
			portletURL.setParameter("purchaseStep", "project");
			portletURL.setParameter("appEntryId", String.valueOf(appEntryId));

			actionResponse.sendRedirect(portletURL.toString());

			return;
		}

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)actionResponse;

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		if (paymentMethod.equals("paypal")) {
			portletURL.setParameter(
				"mvcPath", "/marketplace/view_app_entry_receipt.jsp");
			portletURL.setParameter("appEntryId", String.valueOf(appEntryId));
			portletURL.setParameter(
				"ecDocumentEntryId", String.valueOf(ecDocumentEntryId));

			String confirmURL = portletURL.toString();

			portletURL.setParameter(
				"mvcPath", "/marketplace/purchase_app_entry.jsp");
			portletURL.setParameter("purchaseStep", "summary");

			String cancelURL = portletURL.toString();

			String paymentURL =
				ECDocumentEntryLocalServiceUtil.getECDocumentEntryPaymentURL(
					ecDocumentEntryId, confirmURL, cancelURL);

			actionResponse.sendRedirect(paymentURL);
		}
		else {
			String emailAddress = ParamUtil.getString(
				actionRequest, "emailAddress");

			ECDocumentEntry ecDocumentEntry =
				MarketplaceEcommerceUtil.updateECDocumentEntryRequestInvoice(
					ecDocumentEntryId, emailAddress);

			portletURL.setParameter(
				"mvcPath", "/marketplace/view_app_entry_receipt.jsp");
			portletURL.setParameter(
				"ecDocumentEntryId", String.valueOf(ecDocumentEntryId));
			portletURL.setParameter("appEntryId", String.valueOf(appEntryId));
			portletURL.setParameter("paymentMethod", "request-invoice");

			actionResponse.sendRedirect(portletURL.toString());
		}
	}

	public void reportAbuse(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String className = ParamUtil.getString(actionRequest, "className");
		long classPK = ParamUtil.getLong(actionRequest, "classPK");
		long reportedUserId = ParamUtil.getLong(
			actionRequest, "reportedUserId");
		String contentTitle = ParamUtil.getString(
			actionRequest, "contentTitle");
		String contentURL = ParamUtil.getString(actionRequest, "contentURL");
		String reason = ParamUtil.getString(actionRequest, "reason");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			"com.liferay.portlet.flags.model.FlagsEntry", actionRequest);

		FlagsEntryServiceUtil.addEntry(
			className, classPK, StringPool.BLANK, reportedUserId, contentTitle,
			contentURL, reason, serviceContext);
	}

	public void sendEmailAddressVerification(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		User user = themeDisplay.getUser();

		UserLocalServiceUtil.sendEmailAddressVerification(
			user, user.getEmailAddress(), serviceContext);
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		String resourceID = resourceRequest.getResourceID();

		try {
			if (resourceID.equals("serveApp")) {
				serveApp(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("serveAutoQAApp")) {
				serveAutoQAApp(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("serveAppPackageSrc")) {
				serveAppPackageSrc(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("serveCatalogXML")) {
				serveCatalogXML(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("serveIcon")) {
				OSBRequestUtil.serveAssetAttachment(
					resourceRequest, resourceResponse,
					AssetAttachmentConstants.TYPE_ICON);
			}
			else if (resourceID.equals("serveMedia")) {
				OSBRequestUtil.serveAssetAttachment(
					resourceRequest, resourceResponse,
					AssetAttachmentConstants.TYPE_MEDIA);
			}
			else if (resourceID.equals("serveReleaseApp")) {
				serveReleaseApp(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("viewJournalArticleContent")) {
				viewJournalArticleContent(resourceRequest, resourceResponse);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}

			sendErrorNotFound(e, resourceRequest, resourceResponse);
		}
	}

	public void unfollowOwner(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		String ownerClassName = ParamUtil.getString(
			actionRequest, "ownerClassName");
		long ownerClassPK = ParamUtil.getLong(actionRequest, "ownerClassPK");

		SubscriptionLocalServiceUtil.deleteSubscription(
			themeDisplay.getUserId(), ownerClassName, ownerClassPK);
	}

	public void updateAppEntryReview(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");
		long threadId = ParamUtil.getLong(actionRequest, "threadId");
		long parentMessageId = ParamUtil.getLong(
			actionRequest, "parentMessageId");
		String review = ParamUtil.getString(actionRequest, "review");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (!OSBAppEntryPermission.contains(
				themeDisplay.getPermissionChecker(), appEntryId,
				OSBActionKeys.REVIEW_APP)) {

			jsonObject.put("exception", PrincipalException.class.getName());
			jsonObject.put("message", "fail");

			writeJSON(actionRequest, actionResponse, jsonObject);

			return;
		}

		if (review.length() >
				MarketplaceUtil.ASSET_ENTRY_REVIEW_MAX_LENGTH) {

			jsonObject.put(
				"exception", AssetEntryReviewLengthException.class.getName());
			jsonObject.put("message", "fail");

			writeJSON(actionRequest, actionResponse, jsonObject);

			return;
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			MBMessage.class.getName(), actionRequest);

		MBMessage message = MarketplaceUtil.getUserDiscussionMessage(
			themeDisplay.getUserId(), AppEntry.class.getName(), appEntryId);

		if (message != null) {
			if (message.getStatus() == WorkflowConstants.STATUS_DENIED) {
				jsonObject.put(
					"exception", RestrictedReviewException.class.getName());
				jsonObject.put("message", "fail");
			}
			else {
				MBMessageServiceUtil.updateDiscussionMessage(
					AppEntry.class.getName(), appEntryId,
					AppEntry.class.getName(), appEntryId,
					themeDisplay.getUserId(), message.getMessageId(),
					StringPool.BLANK, review, serviceContext);
			}
		}
		else {
			MBMessageServiceUtil.addDiscussionMessage(
				OSBConstants.GROUP_GUEST_ID, AppEntry.class.getName(),
				appEntryId, AppEntry.class.getName(), appEntryId,
				themeDisplay.getUserId(), threadId, parentMessageId,
				StringPool.BLANK, review, serviceContext);
		}

		double rating = ParamUtil.getDouble(actionRequest, "rating");

		if (rating > 0) {
			RatingsEntryServiceUtil.updateEntry(
				AppEntry.class.getName(), appEntryId, rating);
		}

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateDiscussionMessageStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isGroupAdmin(themeDisplay.getScopeGroupId())) {
			return;
		}

		long mbMessageId = ParamUtil.getLong(actionRequest, "mbMessageId");

		int status = ParamUtil.getInteger(actionRequest, "status");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			MBMessage.class.getName(), actionRequest);

		serviceContext.setAttribute("className", AppEntry.class.getName());

		MBMessageLocalServiceUtil.updateStatus(
			themeDisplay.getUserId(), mbMessageId, status, serviceContext);
	}

	public void updateStoreCountry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		long storeCountryId = ParamUtil.getLong(
			actionRequest, "storeCountryId");

		User user = themeDisplay.getUser();

		ExpandoBridge userExpandoBridge = user.getExpandoBridge();

		userExpandoBridge.setAttribute("osbStoreCountryId", storeCountryId);
	}

	@Override
	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (actionName.equals("cancelPurchaseAppEntry") ||
			actionName.equals("purchaseAppEntryCoterm") ||
			actionName.equals("purchaseAppEntryDestination") ||
			actionName.equals("purchaseAppEntryLicense") ||
			actionName.equals("purchaseAppEntryProject") ||
			actionName.equals("purchaseAppEntryRenew") ||
			actionName.equals("purchaseAppEntryRequestInvoice") ||
			actionName.equals("purchaseAppEntrySummary")) {

			return;
		}

		super.addSuccessMessage(actionRequest, actionResponse);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		String portletId = PortalUtil.getPortletId(renderRequest);

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			Country country = null;

			if (themeDisplay.isSignedIn()) {
				User user = themeDisplay.getUser();

				ExpandoBridge expandoBridge = user.getExpandoBridge();

				long countryId = GetterUtil.getLong(
					expandoBridge.getAttribute("osbStoreCountryId"));

				country = CountryServiceUtil.fetchCountry(countryId);
			}

			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				renderRequest);

			IPInfo ipInfo = IPGeocoderUtil.getIPInfo(request.getRemoteAddr());

			if ((country == null) && (ipInfo != null)) {
				country = CountryServiceUtil.fetchCountryByA2(
					ipInfo.getCountryCode());
			}

			if (country == null) {
				country = CountryServiceUtil.fetchCountryByA2(
					PortletPropsValues.MARKETPLACE_STORE_COUNTRY_DEFAULT);
			}

			renderRequest.setAttribute(OSBWebKeys.OSB_STORE_COUNTRY, country);

			if (!portletId.equals(OSBPortletKeys.OSB_MARKETPLACE)) {
				super.doDispatch(renderRequest, renderResponse);

				return;
			}

			if ((ipInfo != null) &&
				ArrayUtil.contains(
					PortletPropsValues.MARKETPLACE_RESTRICTED_COUNTRIES,
					ipInfo.getCountryCode())) {

				include(
					"/marketplace/embargo.jsp", renderRequest, renderResponse);

				return;
			}

			String assetCategoryIdString = ParamUtil.getString(
				renderRequest, "assetCategoryId");

			if (Validator.isNotNull(assetCategoryIdString)) {
				if (!Validator.isNumber(assetCategoryIdString)) {
					throw new NoSuchCategoryException();
				}

				long assetCategoryId = GetterUtil.getLong(
					assetCategoryIdString);

				if (assetCategoryId > 0) {
					AssetCategoryLocalServiceUtil.getCategory(assetCategoryId);
				}
			}

			validateAppEntry(renderRequest);

			String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");

			if (PortletPropsValues.DEVELOPER_MODE_ENABLED &&
				mvcPath.equals("/marketplace/view_app_entry_receipt.jsp")) {

				processDeveloperModeECDocumentEntryPayment(
					renderRequest, renderResponse);
			}
		}
		catch (Exception e) {
			if (portletId.equals(OSBPortletKeys.OSB_MARKETPLACE)) {
				include(
					"/marketplace/error.jsp", renderRequest, renderResponse);

				return;
			}
		}

		super.doDispatch(renderRequest, renderResponse);
	}

	protected AssetAttachment getAppPackageSrcAssetAttachment(
			ResourceRequest resourceRequest)
		throws Exception {

		long assetAttachmentId = ParamUtil.getLong(
			resourceRequest, "assetAttachmentId");;

		if (assetAttachmentId > 0) {
			return AssetAttachmentLocalServiceUtil.getAssetAttachment(
				assetAttachmentId);
		}

		long appEntryId = ParamUtil.getLong(resourceRequest, "appEntryId");
		String version = ParamUtil.getString(resourceRequest, "version");

		AppVersion appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
			appEntryId, version);

		if (appVersion == null) {
			return null;
		}

		int portalBuildNumber = ParamUtil.getInteger(
			resourceRequest, "portalBuildNumber");

		AppPackage appPackage = AppPackageServiceUtil.fetchAppPackage(
			appVersion.getAppVersionId(), portalBuildNumber);

		if (appPackage == null) {
			return null;
		}

		List<AssetAttachment> assetAttachments =
			AssetAttachmentLocalServiceUtil.getAssetAttachments(
				AppPackage.class.getName(), appPackage.getAppPackageId(),
				AssetAttachmentConstants.TYPE_PACKAGE_SRC, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		return assetAttachments.get(0);
	}

	protected void getAssetLicenseMapRenewal(
			Map<AssetLicense, Integer> assetLicensesMap, long assetReceiptId,
			long appVersionId, int usageType)
		throws Exception {

		long totalLicenseTypeAllotment =
			AssetReceiptLicenseLocalServiceUtil.
				getActiveAssetReceiptLicenseTypeAllotment(
					assetReceiptId, usageType);

		long totalRenewedLicenseTypeAllotment =
			AssetReceiptLicenseLocalServiceUtil.
				getRenewedAssetReceiptLicenseTypeAllotment(
					assetReceiptId, usageType);

		totalLicenseTypeAllotment -= totalRenewedLicenseTypeAllotment;

		if (totalLicenseTypeAllotment <= 0) {
			return;
		}

		List<AssetLicense> assetLicenses =
			AssetLicenseLocalServiceUtil.getAssetLicenses(
				AppVersion.class.getName(), appVersionId, usageType,
				AssetLicenseConstants.LICENSE_TYPE_PRODUCTION,
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS,
				new AssetLicenseLicenseTypeAllotmentComparator(false));

		for (AssetLicense assetLicense : assetLicenses) {
			if (totalLicenseTypeAllotment == 0) {
				break;
			}

			long quantity =
				totalLicenseTypeAllotment /
					assetLicense.getLicenseTypeAllotment();

			if (quantity == 0) {
				continue;
			}

			assetLicensesMap.put(assetLicense, (int)quantity);

			totalLicenseTypeAllotment -=
				(quantity * assetLicense.getLicenseTypeAllotment());
		}
	}

	protected Map<AssetLicense, Integer> getAssetLicensesMap(
			long appEntryId, ActionRequest actionRequest)
		throws Exception {

		AppVersion appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
			appEntryId, AppVersionConstants.STATUSES_APPROVED, null);

		List<AssetLicense> assetLicenses =
			AssetLicenseLocalServiceUtil.getAssetLicenses(
				AppVersion.class.getName(), appVersion.getAppVersionId(),
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		Map<AssetLicense, Integer> assetLicensesMap =
			new HashMap<AssetLicense, Integer>(assetLicenses.size());

		for (AssetLicense assetLicense : assetLicenses) {
			int quantity = ParamUtil.getInteger(
				actionRequest,
				"assetLicenseQuantity" + assetLicense.getAssetLicenseId());

			assetLicensesMap.put(assetLicense, quantity);
		}

		return assetLicensesMap;
	}

	protected String getLiferayPackageName(
		AppEntry appEntry, AppVersion appVersion, int portalBuildNumber,
		boolean licensed, LicenseKey licenseKey) {

		StringBundler sb = new StringBundler(6);

		sb.append(appEntry.getTitle());

		/*if (appVersion != null) {
			sb.append(StringPool.DASH);
			sb.append(appVersion.getVersion());
		}

		if (portalBuildNumber > 0) {
			sb.append(StringPool.DASH);
			sb.append(portalBuildNumber);
		}*/

		if (licenseKey != null) {
			if (Validator.isNotNull(licenseKey.getHostName())) {
				sb.append(StringPool.SPACE);

				sb.append(licenseKey.getHostName());
			}
			else if (Validator.isNotNull(licenseKey.getIpAddresses())) {
				sb.append(StringPool.SPACE);

				String[] ipAddresses = StringUtil.split(
					licenseKey.getIpAddresses());

				sb.append(ipAddresses[0]);
			}
			else if (Validator.isNotNull(licenseKey.getMacAddresses())) {
				sb.append(StringPool.SPACE);

				String[] macAddresses = StringUtil.split(
					licenseKey.getMacAddresses());

				sb.append(macAddresses[0]);
			}
		}

		if (!licensed) {
			sb.append(StringPool.SPACE);
			sb.append("unlicensed");
		}

		sb.append(".lpkg");

		return sb.toString();
	}

	protected boolean isCotermRequired(
			long appEntryId, String ownerClassName, long ownerClassPK)
		throws Exception {

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		if (!appEntry.isCotermRequired()) {
			return false;
		}

		AssetReceipt assetReceipt = AssetReceiptServiceUtil.fetchAssetReceipt(
			ownerClassName, ownerClassPK, AppEntry.class.getName(), appEntryId);

		if (assetReceipt == null) {
			return false;
		}

		if (assetReceipt.hasActiveAssetReceiptLicenses(
				AssetLicenseConstants.USAGE_TYPE_DEVELOPER) ||
			assetReceipt.hasActiveAssetReceiptLicenses(
				AssetLicenseConstants.USAGE_TYPE_STANDARD)) {

			return true;
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
			cause instanceof AppEntryCategoryException ||
			cause instanceof AppEntryTitleException ||
			cause instanceof AppEntryVersionException ||
			cause instanceof AssetEntryReviewLengthException ||
			cause instanceof AssetLicenseLicenseTypeAllotmentException ||
			cause instanceof AssetReceiptEmailAddressException ||
			cause instanceof AssetReceiptLegalEntityNameException ||
			cause instanceof AssetReceiptOwnerClassNameException ||
			cause instanceof ContractAuditAcceptanceException ||
			cause instanceof MessageBodyException ||
			cause instanceof NoSuchAddressException ||
			cause instanceof NoSuchContractEntryException ||
			cause instanceof NoSuchCorpEntryException ||
			cause instanceof NoSuchCorpProjectException ||
			cause instanceof AddressCompanyNameException ||
			cause instanceof RequiredEndUserEmailAddressException ||
			cause instanceof RequiredEndUserFirstNameException ||
			cause instanceof RequiredEndUserLastNameException ||
			cause instanceof RequiredEndUserPhoneNumberException ||
			cause instanceof RequiredQuantityException ||
			cause instanceof RequiredRenewalException ||
			cause instanceof AssetReceiptOwnerClassNameException ||
			cause instanceof RestrictedReviewException ||
			cause instanceof SalesforceAddressException ||
			cause instanceof TrialLicenseException) {

			return true;
		}
		else {
			return false;
		}
	}

	protected void processDeveloperModeECDocumentEntryPayment(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		long ecDocumentEntryId = ParamUtil.getLong(
			renderRequest, "ecDocumentEntryId");

		if (ecDocumentEntryId <= 0) {
			return;
		}

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
				ecDocumentEntryId);

		if (((ecDocumentEntry.getType() !=
				ECDocumentEntryConstants.TYPE_PURCHASE_ORDER) &&
			 (ecDocumentEntry.getType() !=
				ECDocumentEntryConstants.TYPE_SALES_ORDER)) ||
			ecDocumentEntry.isPaid()) {

			return;
		}

		String paymentProcessor = ecDocumentEntry.getPaymentProcessor();

		if (paymentProcessor.equals(
				MarketplaceEcommerceUtil.
					PAY_PAL_PAYMENT_PROCESSOR_CLASS_NAME)) {

			ECDocumentEntryLocalServiceUtil.updateECDocumentEntry(
				ecDocumentEntry.getEcDocumentEntryId(),
				ECDocumentEntryConstants.TYPE_INVOICE,
				ECDocumentEntryConstants. STATUS_PAID_PENDING_PAYOUT);
		}
	}

	protected void sendErrorNotFound(
		Exception exception, ResourceRequest resourceRequest,
		ResourceResponse resourceResponse) {

		try {
			if (exception == null) {
				exception = new PortletException();
			}

			OSBRequestUtil.sendError(
				HttpServletResponse.SC_NOT_FOUND, exception, resourceRequest,
				resourceResponse);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	protected void serveApp(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long appPackageId = ParamUtil.getLong(resourceRequest, "appPackageId");
		String version = ParamUtil.getString(resourceRequest, "version");
		int portalBuildNumber = ParamUtil.getInteger(
			resourceRequest, "portalBuildNumber");

		AppPackage appPackage = null;

		String portletId = PortalUtil.getPortletId(resourceRequest);

		if (appPackageId > 0) {
			appPackage = AppPackageLocalServiceUtil.getAppPackage(appPackageId);

			AppEntry appEntry = appPackage.getAppEntry();

			if (!appEntry.isLiferayEEPlugin() ||
				!portletId.equals(OSBPortletKeys.OSB_MARKETPLACE_SERVER)) {

				appPackage = AppPackageServiceUtil.getAppPackage(appPackageId);
			}
		}
		else {
			long appEntryId = ParamUtil.getLong(resourceRequest, "appEntryId");

			AppVersion appVersion = null;

			if (Validator.isNotNull(version)) {
				appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
					appEntryId, version);
			}
			else {
				int[] statuses = {
					WorkflowConstants.STATUS_APPROVED,
					WorkflowConstants.STATUS_APPROVED_HIDDEN
				};

				List<AppVersion> appVersions =
					AppVersionLocalServiceUtil.getAppVersions(
						appEntryId, statuses, 0, 1,
						new AppVersionVersionOrderComparator());

				if (!appVersions.isEmpty()) {
					appVersion = appVersions.get(0);
				}
			}

			if (appVersion == null) {
				sendErrorNotFound(null, resourceRequest, resourceResponse);

				return;
			}

			appPackage = AppPackageServiceUtil.fetchAppPackage(
				appVersion.getAppVersionId(), portalBuildNumber);
		}

		if (appPackage == null) {
			sendErrorNotFound(null, resourceRequest, resourceResponse);

			return;
		}

		AppEntry appEntry = appPackage.getAppEntry();

		List<AssetAttachment> assetAttachments =
			AssetAttachmentLocalServiceUtil.getAssetAttachments(
				AppPackage.class.getName(), appPackage.getAppPackageId(),
				AssetAttachmentConstants.TYPE_PACKAGE, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		AssetAttachment assetAttachment = assetAttachments.get(
			assetAttachments.size() - 1);

		String fileName = StringPool.BLANK;
		InputStream inputStream = null;

		AppVersion appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
			appEntry.getAppEntryId(), version);

		long licenseKeyId = ParamUtil.getLong(resourceRequest, "licenseKeyId");

		if (licenseKeyId > 0) {
			File file = null;

			try {
				file = FileUtil.createTempFile("zip");

				FileUtil.write(file, assetAttachment.getFileAsStream());

				ZipWriter zipWriter = ZipWriterFactoryUtil.getZipWriter(file);

				LicenseKey licenseKey = LicenseKeyServiceUtil.getLicenseKey(
					licenseKeyId);

				String licenseXML = LicenseUtil.exportToXML(licenseKey);

				zipWriter.addEntry("license.xml", licenseXML);

				fileName = getLiferayPackageName(
					appEntry, appVersion, portalBuildNumber, true, licenseKey);
				inputStream = new FileInputStream(zipWriter.getFile());
			}
			finally {
				FileUtil.delete(file);
			}
		}
		else {
			fileName = getLiferayPackageName(
				appEntry, appVersion, portalBuildNumber, true, null);
			inputStream = assetAttachment.getFileAsStream();
		}

		String contentType = MimeTypesUtil.getContentType(fileName);

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, fileName, inputStream,
			contentType);

		int domain = AssetAuditImpl.getDomain(themeDisplay.getPortalURL());

		AppEntryLocalServiceUtil.incrementDownloadCount(
			themeDisplay.getUserId(), appPackage.getAppVersionId(), domain);
	}

	protected void serveAppPackageSrc(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		AssetAttachment assetAttachment = getAppPackageSrcAssetAttachment(
			resourceRequest);

		if (assetAttachment == null) {
			sendErrorNotFound(null, resourceRequest, resourceResponse);

			return;
		}

		AppPackage appPackage = AppPackageServiceUtil.getAppPackage(
			assetAttachment.getClassPK());

		AppEntry appEntry = appPackage.getAppEntry();

		if (_MARKETPLACE_SO_EE_APP_ENTRY_TITLE.equals(appEntry.getTitle())) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)resourceRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

			if (!MarketplaceSOEEUtil.hasSourceCodePermission(
					themeDisplay.getUserId())) {

				throw new PrincipalException();
			}
		}

		String contentType = MimeTypesUtil.getContentType(
			assetAttachment.getFileName());

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, assetAttachment.getFileName(),
			assetAttachment.getFileAsStream(), contentType);
	}

	protected void serveAutoQAApp(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String token = ParamUtil.getString(resourceRequest, "token");

		String decodedToken = new String(Base64.decode(token));

		Ticket ticket = TicketLocalServiceUtil.fetchTicket(decodedToken);

		if (ticket != null) {
			TicketLocalServiceUtil.deleteTicket(ticket);

			Date expirationDate = ticket.getExpirationDate();

			if ((expirationDate == null) ||
				DateUtil.compareTo(expirationDate, new Date()) <= 0) {

				sendErrorNotFound(null, resourceRequest, resourceResponse);

				return;
			}
		}
		else {
			sendErrorNotFound(null, resourceRequest, resourceResponse);

			return;
		}

		long appPackageId = ParamUtil.getLong(resourceRequest, "appPackageId");

		AppPackage appPackage = AppPackageLocalServiceUtil.getAppPackage(
			appPackageId);

		Boolean licensed = ParamUtil.getBoolean(resourceRequest, "licensed");

		File file = null;

		try {
			file = AppEntryLocalServiceUtil.buildLiferayPackage(
				appPackage, licensed);

			String fileName = getLiferayPackageName(
				appPackage.getAppEntry(), null, 0, licensed, null);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, fileName,
				new FileInputStream(file), ContentTypes.APPLICATION_ZIP);
		}
		finally {
			FileUtil.delete(file);
		}
	}

	protected void serveCatalogXML(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String token = ParamUtil.getString(resourceRequest, "token");

		if (!token.equals(PortletPropsValues.MARKETPLACE_RELEASE_TEAM_TOKEN)) {
			sendErrorNotFound(null, resourceRequest, resourceResponse);

			return;
		}

		Document document = MarketplaceCatalogUtil.getAppEntriesXML(
			OSBConstants.DEVELOPER_ENTRY_LIFERAY_INC_ID,
			WorkflowConstants.STATUS_APPROVED);

		String documentXML = document.formattedString();

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, "marketplace_catalog.xml",
			documentXML.getBytes(), ContentTypes.TEXT_XML);
	}

	protected void serveReleaseApp(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		String token = ParamUtil.getString(resourceRequest, "token");

		if (!token.equals(PortletPropsValues.MARKETPLACE_RELEASE_TEAM_TOKEN)) {
			sendErrorNotFound(null, resourceRequest, resourceResponse);

			return;
		}

		long appEntryId = ParamUtil.getLong(resourceRequest, "appEntryId");

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		if (!appEntry.isDeveloperEntryLiferayInc()) {
			sendErrorNotFound(null, resourceRequest, resourceResponse);

			return;
		}

		AppVersion appVersion = null;
		AppPackage appPackage = null;

		int buildNumber = ParamUtil.getInteger(resourceRequest, "buildNumber");
		String version = ParamUtil.getString(resourceRequest, "version");

		if (Validator.isNull(version)) {
			appVersion = appEntry.getApprovedAppVersion();

			appPackage = AppPackageLocalServiceUtil.fetchCompatibleAppPackage(
				appEntryId, buildNumber, WorkflowConstants.STATUS_APPROVED);

			if (appPackage == null) {
				appPackage =
					AppPackageLocalServiceUtil.fetchCompatibleAppPackage(
						appEntryId, buildNumber,
						WorkflowConstants.STATUS_APPROVED_HIDDEN);
			}
		}
		else {
			appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
				appEntryId, version);

			if (appVersion == null) {
				sendErrorNotFound(null, resourceRequest, resourceResponse);
			}

			appPackage = AppPackageLocalServiceUtil.fetchAppPackage(
				appVersion.getAppVersionId(), buildNumber);
		}

		if ((appVersion == null) || (appPackage == null)) {
			sendErrorNotFound(null, resourceRequest, resourceResponse);

			return;
		}

		boolean licensed = ParamUtil.getBoolean(
			resourceRequest, "licensed", true);

		if (licensed && appVersion.isApproved()) {
			List<AssetAttachment> assetAttachments =
				AssetAttachmentLocalServiceUtil.getAssetAttachments(
					AppPackage.class.getName(), appPackage.getAppPackageId(),
					AssetAttachmentConstants.TYPE_PACKAGE, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

			if (assetAttachments.isEmpty()) {
				sendErrorNotFound(null, resourceRequest, resourceResponse);

				return;
			}

			AssetAttachment assetAttachment = assetAttachments.get(
				assetAttachments.size() - 1);

			String fileName = getLiferayPackageName(
				appEntry, appVersion, buildNumber, true, null);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, fileName,
				assetAttachment.getFileAsStream(),
				ContentTypes.APPLICATION_ZIP);
		}
		else {
			File file = null;

			try {
				String fileName = getLiferayPackageName(
					appEntry, null, buildNumber, true, null);

				file = AppEntryLocalServiceUtil.buildLiferayPackage(
					appPackage, licensed);

				PortletResponseUtil.sendFile(
					resourceRequest, resourceResponse, fileName,
					new FileInputStream(file), ContentTypes.APPLICATION_ZIP);
			}
			finally {
				FileUtil.delete(file);
			}
		}
	}

	protected void serveUnpackagedApp(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			boolean licensed)
		throws Exception {

		long appPackageId = ParamUtil.getLong(resourceRequest, "appPackageId");

		AppPackage appPackage = AppPackageServiceUtil.getAppPackage(
			appPackageId);

		File file = null;

		try {
			file = AppEntryServiceUtil.buildLiferayPackage(
				appPackage, licensed);

			String fileName = getLiferayPackageName(
				appPackage.getAppEntry(), null, 0, licensed, null);

			PortletResponseUtil.sendFile(
				resourceRequest, resourceResponse, fileName,
				new FileInputStream(file), ContentTypes.APPLICATION_ZIP);
		}
		finally {
			FileUtil.delete(file);
		}
	}

	protected void validateAppEntry(RenderRequest renderRequest)
		throws PortalException, SystemException {

		String appEntryIdString = ParamUtil.getString(
			renderRequest, "appEntryId");

		if (Validator.isNull(appEntryIdString)) {
			return;
		}

		if (!Validator.isNumber(appEntryIdString)) {
			throw new NoSuchAppEntryException();
		}

		long appEntryId = GetterUtil.getLong(appEntryIdString);

		if (appEntryId <= 0) {
			return;
		}

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		appEntry.getApprovedAppVersion();

		if (!appEntry.isApproved() || appEntry.isHidden()) {
			throw new PrincipalException();
		}
	}

	protected void viewJournalArticleContent(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long groupId = ParamUtil.getLong(resourceRequest, "groupId");
		String articleId = ParamUtil.getString(resourceRequest, "articleId");

		JournalArticle article = JournalArticleLocalServiceUtil.getArticle(
			groupId, articleId);

		String content = JournalArticleLocalServiceUtil.getArticleContent(
			article, article.getTemplateId(), null,
			themeDisplay.getLanguageId(), themeDisplay);

		PortletResponseUtil.write(resourceResponse, content);
	}

	private static final String _MARKETPLACE_SO_EE_APP_ENTRY_TITLE =
		PortletPropsValues.MARKETPLACE_SO_EE_APP_ENTRY_TITLE;

	private static Log _log = LogFactoryUtil.getLog(MarketplacePortlet.class);

}