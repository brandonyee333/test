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

package com.liferay.osb.marketplacedeveloperapps.portlet;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.AppEntryCategoryException;
import com.liferay.osb.AppEntryChangeLogException;
import com.liferay.osb.AppEntryDemoWebsiteException;
import com.liferay.osb.AppEntryDescriptionException;
import com.liferay.osb.AppEntryDocumentationWebsiteException;
import com.liferay.osb.AppEntryIconException;
import com.liferay.osb.AppEntryLicenseLifetimeException;
import com.liferay.osb.AppEntryLicenseTypeException;
import com.liferay.osb.AppEntryOrderURLException;
import com.liferay.osb.AppEntryPACLException;
import com.liferay.osb.AppEntryReleaseTypeException;
import com.liferay.osb.AppEntrySourceCodeWebsiteException;
import com.liferay.osb.AppEntryStatusException;
import com.liferay.osb.AppEntrySupportWebsiteException;
import com.liferay.osb.AppEntryTitleException;
import com.liferay.osb.AppEntryVersionException;
import com.liferay.osb.AppEntryWebsiteException;
import com.liferay.osb.AppPackageMarketplaceSupportException;
import com.liferay.osb.AppPackagePluginBundleSymbolicNameException;
import com.liferay.osb.AppPackagePluginFileException;
import com.liferay.osb.AppPackagePluginNameException;
import com.liferay.osb.AppPackagePluginPACLException;
import com.liferay.osb.AppPackagePluginPluginPackageException;
import com.liferay.osb.AppPricingCountryException;
import com.liferay.osb.AppPricingItemCurrencyEntryException;
import com.liferay.osb.AppPricingItemPriceException;
import com.liferay.osb.AppPricingPriceException;
import com.liferay.osb.AssetLicenseLicenseTypeAllotmentException;
import com.liferay.osb.AssetLicenseLicenseTypeException;
import com.liferay.osb.AssetLicenseLifetimeException;
import com.liferay.osb.AssetLicenseUsageTypeException;
import com.liferay.osb.ContractEntryContentException;
import com.liferay.osb.DuplicateAppEntryException;
import com.liferay.osb.MaximumAssetLicenseException;
import com.liferay.osb.MinimumAssetLicenseException;
import com.liferay.osb.NoSuchCurrencyEntryException;
import com.liferay.osb.RequiredAppPackageException;
import com.liferay.osb.RequiredAppPackagePluginException;
import com.liferay.osb.RequiredAppPricingItemException;
import com.liferay.osb.RequiredAssetLicenseException;
import com.liferay.osb.RequiredLiferayDeploymentContextException;
import com.liferay.osb.RequiredResourcesImporterException;
import com.liferay.osb.RestrictedLiferayDeploymentContextException;
import com.liferay.osb.marketplace.portlet.MarketplacePortlet;
import com.liferay.osb.marketplaceadmin.util.MarketplaceQAUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppEntryRel;
import com.liferay.osb.model.AppEntryRelConstants;
import com.liferay.osb.model.AppFlag;
import com.liferay.osb.model.AppFlagConstants;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppPackagePlugin;
import com.liferay.osb.model.AppPricing;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.model.PortalRelease;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppEntryRelLocalServiceUtil;
import com.liferay.osb.service.AppEntryRelServiceUtil;
import com.liferay.osb.service.AppEntryServiceUtil;
import com.liferay.osb.service.AppFlagLocalServiceUtil;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.AppPackagePluginLocalServiceUtil;
import com.liferay.osb.service.AppPackagePluginServiceUtil;
import com.liferay.osb.service.AppPackageServiceUtil;
import com.liferay.osb.service.AppPricingItemServiceUtil;
import com.liferay.osb.service.AppPricingLocalServiceUtil;
import com.liferay.osb.service.AppPricingServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.AppVersionServiceUtil;
import com.liferay.osb.service.AssetAttachmentLocalServiceUtil;
import com.liferay.osb.service.ContractAuditLocalServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.CountryAppPricingServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.service.PortalReleaseLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.PortalReleaseUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.AssetTagException;
import com.liferay.portlet.documentlibrary.FileSizeException;

import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Douglas Wong
 * @author Joan Kim
 * @author Haote Chou
 */
public class MarketplaceDeveloperAppsPortlet extends MarketplacePortlet {

	public void addAppEntryMedia(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			long appEntryId = ParamUtil.getLong(
				uploadPortletRequest, "appEntryId");
			String fileName = uploadPortletRequest.getParameter(
				"resumableFilename");
			File file = uploadPortletRequest.getFile("file");

			AssetAttachment assetAttachment =
				AppEntryServiceUtil.addAppEntryMedia(
					appEntryId, fileName, file);

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"assetAttachmentId", assetAttachment.getAssetAttachmentId());
			jsonObject.put("message", "success");
			jsonObject.put("name", assetAttachment.getFileName());

			writeJSON(actionRequest, actionResponse, jsonObject);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void addAppPackage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");
		long appVersionId = ParamUtil.getLong(actionRequest, "appVersionId");
		int compatibility = ParamUtil.getInteger(
			actionRequest, "compatibility");

		AppPackage appPackage = AppPackageServiceUtil.addAppPackage(
			appEntryId, appVersionId, compatibility, true);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("appPackageId", appPackage.getAppPackageId());
		jsonObject.put("compatibility", compatibility);

		PortalRelease portalRelease =
			PortalReleaseLocalServiceUtil.getPortalRelease(compatibility);

		jsonObject.put("compatibilityLabel", portalRelease.getVersionName());

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void addAppPackagePlugin(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			long appVersionId = ParamUtil.getLong(
				uploadPortletRequest, "appVersionId");
			String fileName = uploadPortletRequest.getParameter(
				"resumableFilename");
			File file = uploadPortletRequest.getFile("file");
			int compatibility = ParamUtil.getInteger(
				uploadPortletRequest, "compatibility");
			boolean compatibilityPlus = ParamUtil.getBoolean(
				uploadPortletRequest, "compatibilityPlus");

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			AppPackagePlugin appPackagePlugin = null;

			try {
				appPackagePlugin =
					AppPackagePluginServiceUtil.addAppPackagePlugin(
						appVersionId, fileName, file, compatibility,
						compatibilityPlus);
			}
			catch (Exception e) {
				jsonObject.put("exception", e.getClass().getName());
			}

			if (appPackagePlugin != null) {
				JSONObject assetAttachmentJSONObject =
					JSONFactoryUtil.createJSONObject();

				AppPackage appPackage = appPackagePlugin.getAppPackage();

				assetAttachmentJSONObject.put(
					"assetAttachmentId",
					appPackagePlugin.getAssetAttachmentId());
				assetAttachmentJSONObject.put(
					"compatibility", appPackage.getCompatibility());
				assetAttachmentJSONObject.put(
					"compatibilityPlus", appPackage.getCompatibilityPlus());
				assetAttachmentJSONObject.put(
					"conflictingContextName",
					appPackagePlugin.hasConflictingContextName());
				assetAttachmentJSONObject.put(
					"liferayVersionName",
					PortalReleaseUtil.getVersionName(appPackage));

				jsonObject.put("assetAttachment", assetAttachmentJSONObject);
				jsonObject.put("message", "success");
				jsonObject.put("name", fileName);
			}
			else {
				jsonObject.put("message", "fail");

				if (!jsonObject.has("exception")) {
					jsonObject.put(
						"exception",
						AppPackagePluginPluginPackageException.class.getName());
				}
			}

			writeJSON(actionRequest, actionResponse, jsonObject);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void addAppPricing(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");

		String name = LanguageUtil.get(themeDisplay.getLocale(), "untitled");

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		AppVersion appVersion = appEntry.getUnreleasedAppVersion();

		AppPricing appPricing = AppPricingServiceUtil.addAppPricing(
			appVersion.getAppVersionId(), name);

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)actionResponse;

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		portletURL.setParameter(
			"mvcPath", "/marketplace_developer_apps/app_pricing.jsp");
		portletURL.setParameter(
			"appEntryId", String.valueOf(appPricing.getAppEntryId()));
		portletURL.setParameter(
			"appPricingId", String.valueOf(appPricing.getAppPricingId()));
		portletURL.setWindowState(LiferayWindowState.EXCLUSIVE);

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void deactivateAppEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		AppEntryServiceUtil.updateStatus(
			appEntryId, WorkflowConstants.STATUS_EXPIRED, StringPool.BLANK,
			serviceContext);
	}

	public void deleteAppEntryMedia(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");
		long assetAttachmentId = ParamUtil.getLong(
			actionRequest, "assetAttachmentId");

		AppEntryServiceUtil.deleteAppEntryMedia(appEntryId, assetAttachmentId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void deleteAppPackage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appVersionId = ParamUtil.getLong(actionRequest, "appVersionId");
		int compatibility = ParamUtil.getInteger(
			actionRequest, "compatibility");

		AppPackage appPackage = AppPackageLocalServiceUtil.fetchAppPackage(
			appVersionId, compatibility);

		AppPackageLocalServiceUtil.deleteAppPackage(
			appPackage.getAppPackageId());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("compatibility", compatibility);

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void deleteAppPackagePlugin(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long assetAttachmentId = ParamUtil.getLong(
			actionRequest, "assetAttachmentId");

		AssetAttachment assetAttachment =
			AssetAttachmentLocalServiceUtil.getAssetAttachment(
				assetAttachmentId);

		AppPackagePlugin appPackagePlugin =
			AppPackagePluginLocalServiceUtil.getAppPackagePlugin(
				assetAttachment.getClassPK());
		AppPackagePluginServiceUtil.deleteAppPackagePlugin(
			appPackagePlugin.getAppPackagePluginId());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void deleteAppPackageSrc(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long assetAttachmentId = ParamUtil.getLong(
			actionRequest, "assetAttachmentId");

		AssetAttachment assetAttachment =
			AssetAttachmentLocalServiceUtil.getAssetAttachment(
				assetAttachmentId);

		AppPackageServiceUtil.deleteAppPackageSrc(assetAttachment.getClassPK());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void deleteAppPricing(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appPricingId = ParamUtil.getLong(actionRequest, "appPricingId");

		AppPricingServiceUtil.deleteAppPricing(appPricingId);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void deleteInvalidAppPackagePlugins(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		AppVersion appVersion = appEntry.getUnreleasedAppVersion();

		AppPackagePluginServiceUtil.deleteInvalidAppPackagePlugins(
			appVersion.getAppVersionId());
	}

	public void discardAppVersion(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");

		AppEntry appEntry = AppEntryServiceUtil.getAppEntry(appEntryId);

		AppVersion appVersion = appEntry.getUnreleasedAppVersion();

		if (appVersion == null) {
			return;
		}

		int count = AppVersionLocalServiceUtil.getAppVersionsCount(
			appEntryId, WorkflowConstants.STATUS_ANY);

		if (count > 1) {
			AppVersionServiceUtil.deleteAppVersion(
				appVersion.getAppVersionId());
		}
		else {
			AppEntryServiceUtil.deleteAppEntry(appEntryId);

			LiferayPortletResponse liferayPortletResponse =
				(LiferayPortletResponse)actionResponse;

			PortletURL portletURL = liferayPortletResponse.createRenderURL();

			portletURL.setParameter(
				"mvcPath", "/marketplace_developer_apps/view.jsp");
			portletURL.setParameter("tabs1", "apps");

			actionResponse.sendRedirect(portletURL.toString());
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("serveUnlicensedApp")) {
				serveUnpackagedApp(resourceRequest, resourceResponse, false);
			}
			else {
				super.serveResource(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void updateAppEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		try {
			AppEntry appEntry = doUpdateAppEntry(
				uploadPortletRequest, actionResponse);

			String redirect = ParamUtil.getString(
				uploadPortletRequest, "redirect");

			if (Validator.isNull(redirect) && (appEntry != null)) {
				LiferayPortletResponse liferayPortletResponse =
					(LiferayPortletResponse)actionResponse;

				PortletURL portletURL =
					liferayPortletResponse.createRenderURL();

				portletURL.setParameter(
					"mvcPath",
					"/marketplace_developer_apps/edit_app_version.jsp");
				portletURL.setParameter(
					"appEntryId", String.valueOf(appEntry.getAppEntryId()));

				redirect = portletURL.toString();
			}

			if (Validator.isNotNull(redirect)) {
				actionResponse.sendRedirect(redirect);
			}
		}
		finally {
			uploadPortletRequest.cleanUp();
		}
	}

	public void updateAppEntryPricing(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");

		AppVersion appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
			appEntryId, AppVersionConstants.STATUSES_UNRELEASED, null);

		AppPricingLocalServiceUtil.validateAppPricings(
			appVersion.getAppVersionId());
	}

	public void updateAppEntryStatus(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");

		int status = ParamUtil.getInteger(actionRequest, "status");
		String statusMessage = ParamUtil.getString(
			actionRequest, "statusMessage");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			actionRequest);

		if (status == WorkflowConstants.STATUS_DRAFT) {
			long appVersionId = ParamUtil.getLong(
				actionRequest, "appVersionId");

			AppVersion appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
				appVersionId);

			MarketplaceQAUtil.removeAppVersion(appVersion);
		}

		AppEntryServiceUtil.updateStatus(
			appEntryId, status, statusMessage, serviceContext);
	}

	public void updateAppEntryVersion(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long appEntryId = ParamUtil.getLong(uploadPortletRequest, "appEntryId");

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		try {
			doUpdateAppEntryVersion(
				uploadPortletRequest, actionResponse, appEntry);
		}
		finally {
			uploadPortletRequest.cleanUp();
		}

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)actionResponse;

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		AppVersion appVersion = appEntry.getActionableAppVersion();

		if (!appVersion.hasCustomOrderWorkflow() && !appVersion.isFree()) {
			portletURL.setParameter(
				"mvcPath", "/marketplace_developer_apps/edit_app_license.jsp");
		}
		else {
			portletURL.setParameter(
				"mvcPath", "/marketplace_developer_apps/view_app_entry.jsp");
		}

		portletURL.setParameter("appEntryId", String.valueOf(appEntryId));

		actionResponse.sendRedirect(portletURL.toString());
	}

	public void updateAppPackage(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appPackageId = ParamUtil.getLong(actionRequest, "appPackageId");

		boolean compatibilityPlus = ParamUtil.getBoolean(
			actionRequest, "compatibilityPlus");

		AppPackageLocalServiceUtil.updateAppPackage(
			appPackageId, compatibilityPlus);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateAppPricing(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appPricingId = ParamUtil.getLong(actionRequest, "appPricingId");

		String name = ParamUtil.getString(actionRequest, "name");
		long currencyEntryId = ParamUtil.getLong(
			actionRequest, "currencyEntryId");
		double standardSupportPrice = ParamUtil.getDouble(
			actionRequest, "standardSupportPrice");
		double developerSupportPrice = ParamUtil.getDouble(
			actionRequest, "developerSupportPrice");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			AppPricingServiceUtil.updateAppPricing(
				appPricingId, name, currencyEntryId, standardSupportPrice,
				developerSupportPrice);

			jsonObject.put("message", "success");
		}
		catch (Exception e) {
			jsonObject.put("message", e.getClass().getName());
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateAppPricingItem(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appPricingId = ParamUtil.getLong(actionRequest, "appPricingId");
		long assetLicenseId = ParamUtil.getLong(
			actionRequest, "assetLicenseId");
		double price = ParamUtil.getDouble(actionRequest, "price");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			AppPricingItemServiceUtil.updateAppPricingItem(
				appPricingId, assetLicenseId, price);

			jsonObject.put("message", "success");
		}
		catch (Exception e) {
			jsonObject.put("message", e.getClass().getName());
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateAssetLicenses(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");

		long licenseLifetime = ParamUtil.getLong(
			actionRequest, "licenseLifetime");
		boolean supported = ParamUtil.getBoolean(actionRequest, "supported");
		long[] standardQuantities = ParamUtil.getLongValues(
			actionRequest, "standardQuantities");
		long[] developerQuantities = ParamUtil.getLongValues(
			actionRequest, "developerQuantities");
		boolean trial = ParamUtil.getBoolean(actionRequest, "trial");

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		if (!appEntry.isFirstSubmission()) {
			licenseLifetime = appEntry.getLicenseLifetime();
		}

		AppEntryServiceUtil.updateAppEntry(
			appEntryId, licenseLifetime, supported, standardQuantities, false,
			developerQuantities, false, trial);
	}

	public void updateCountryAppPricings(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");

		long appPricingId = ParamUtil.getLong(actionRequest, "appPricingId");
		long[] countryIds = ParamUtil.getLongValues(
			actionRequest, "countryIds");

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		AppVersion appVersion = appEntry.getUnreleasedAppVersion();

		if (appPricingId > 0) {
			CountryAppPricingServiceUtil.updateCountryAppPricings(
				appVersion.getAppVersionId(), appPricingId, countryIds);
		}
		else {
			CountryAppPricingServiceUtil.deleteCountryAppPricings(
				appVersion.getAppVersionId(), countryIds);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	@Override
	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		String actionName = ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME);

		if (actionName.equals("addAppPricing") ||
			actionName.equals("deleteAppPricing") ||
			actionName.equals("updateAppPricing") ||
			actionName.equals("updateAppPricingItem") ||
			actionName.equals("updateCountryAppPricings")) {

			return;
		}

		super.addSuccessMessage(actionRequest, actionResponse);
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			String portletId = (String)renderRequest.getAttribute(
				WebKeys.PORTLET_ID);

			if (portletId.equals(OSBPortletKeys.OSB_MARKETPLACE_ADMIN)) {
				super.doDispatch(renderRequest, renderResponse);

				return;
			}

			renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.FALSE);

			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String className = StringPool.BLANK;
			long classPK = 0;
			boolean administrator = false;

			Group group = themeDisplay.getScopeGroup();

			if (group.isOrganization()) {
				CorpEntry corpEntry =
					CorpEntryLocalServiceUtil.getOrganizationCorpEntry(
						group.getOrganizationId());

				className = CorpEntry.class.getName();
				classPK = corpEntry.getCorpEntryId();

				if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
						themeDisplay.getUserId(),
						themeDisplay.getScopeGroupId(),
						OSBConstants.ROLE_OSB_CORP_ADMIN_ID)) {

					administrator = true;
				}
			}
			else {
				className = User.class.getName();
				classPK = themeDisplay.getUserId();
				administrator = true;
			}

			if (!ContractAuditLocalServiceUtil.hasLatestContractAudit(
					ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
					ContractEntryConstants.DEFAULT_CLASS_PK,
					ContractEntryConstants.TYPE_TERMS_OF_SERVICE, className,
					classPK)) {

				include(
					"/marketplace_developer_apps/view_contract_entry.jsp",
					renderRequest, renderResponse);

				return;
			}

			DeveloperEntry developerEntry =
				DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(
					group.getGroupId());

			if (administrator &&
				!ContractAuditLocalServiceUtil.hasLatestContractAudit(
					ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
					ContractEntryConstants.DEFAULT_CLASS_PK,
					ContractEntryConstants.TYPE_DEVELOPER_AGREEMENT, className,
					classPK) &&
				developerEntry.isApproved()) {

				include(
					"/marketplace_developer_apps/view_contract_entry.jsp",
					renderRequest, renderResponse);

				return;
			}

			String path = getPath(renderRequest);

			if (Validator.isNull(path)) {
				path = getDefaultPath(renderRequest);
			}

			if (hasDeveloperPath(path) && developerEntry.isLiferayInc()) {
				include(getDeveloperPath(path), renderRequest, renderResponse);

				return;
			}

			String mvcPath = ParamUtil.getString(renderRequest, "mvcPath");

			if (PortletPropsValues.DEVELOPER_MODE_ENABLED &&
				(mvcPath.equals(
					"/marketplace_developer_apps/view_app_entry.jsp") ||
				 mvcPath.equals(
					"/marketplace_developer_apps/edit_app_entry.jsp"))) {

				processDeveloperModeECDocumentEntryPayment(
					renderRequest, renderResponse);
			}
		}
		catch (Exception e) {
			include(
				"/marketplace_developer_apps/error.jsp", renderRequest,
				renderResponse);

			return;
		}

		super.doDispatch(renderRequest, renderResponse);
	}

	protected AppEntry doUpdateAppEntry(
			UploadPortletRequest uploadPortletRequest,
			ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)uploadPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long appEntryId = ParamUtil.getLong(uploadPortletRequest, "appEntryId");

		String title = ParamUtil.getString(uploadPortletRequest, "title");
		Map<Locale, String> descriptionMap =
			LocalizationUtil.getLocalizationMap(
				uploadPortletRequest, "description");
		String website = ParamUtil.getString(uploadPortletRequest, "website");
		String demoWebsite = ParamUtil.getString(
			uploadPortletRequest, "demoWebsite");
		String documentationWebsite = ParamUtil.getString(
			uploadPortletRequest, "documentationWebsite");
		String referenceWebsite = ParamUtil.getString(
			uploadPortletRequest, "referenceWebsite");
		String sourceCodeWebsite = ParamUtil.getString(
			uploadPortletRequest, "sourceCodeWebsite");
		String supportWebsite = ParamUtil.getString(
			uploadPortletRequest, "supportWebsite");
		boolean labs = ParamUtil.getBoolean(uploadPortletRequest, "labs");
		int productType = ParamUtil.getInteger(
			uploadPortletRequest, "productType");
		int versionOrder = ParamUtil.getInteger(
			uploadPortletRequest, "versionOrder",
			AppVersionConstants.VERSION_ORDER_LAST);
		File iconFile = uploadPortletRequest.getFile("icon");
		boolean paclDisabled = ParamUtil.getBoolean(
			uploadPortletRequest, "paclDisabled");
		int licenseType = ParamUtil.getInteger(
			uploadPortletRequest, "licenseType");
		int releaseType = ParamUtil.getInteger(
			uploadPortletRequest, "releaseType",
			AppVersionConstants.RELEASE_TYPE_NEW_VERSION);

		Map<Locale, String> eulaContentMap =
			LocalizationUtil.getLocalizationMap(
				uploadPortletRequest, "eulaContent");

		boolean customEula = ParamUtil.getBoolean(
			uploadPortletRequest, "customEula");

		if (!customEula) {
			eulaContentMap = null;
		}
		else if (!PortletPropsValues.CONTRACT_ENTRY_LOCALIZED_ENABLED) {
			String eulaContent = ParamUtil.getString(
				uploadPortletRequest, "eulaContent");

			String xml = LocalizationUtil.updateLocalization(
				StringPool.BLANK, "Content", eulaContent, "en_US", "en_US",
				true, false);

			eulaContentMap = LocalizationUtil.getLocalizationMap(xml);
		}

		String orderURL = ParamUtil.getString(uploadPortletRequest, "orderURL");
		boolean hidden = ParamUtil.getBoolean(uploadPortletRequest, "hidden");
		boolean portalRequired = ParamUtil.getBoolean(
			uploadPortletRequest, "portalRequired");

		boolean liferayEEPlugin = ParamUtil.getBoolean(
			uploadPortletRequest, "liferayEEPlugin");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			uploadPortletRequest);

		if (liferayEEPlugin) {
			long[] assetCategoryIds = serviceContext.getAssetCategoryIds();

			assetCategoryIds = ArrayUtil.append(
				assetCategoryIds, OSBConstants.ASSET_CATEGORY_EE_PLUGINS_ID);

			serviceContext.setAssetCategoryIds(assetCategoryIds);
		}

		AppEntry appEntry = null;

		if (appEntryId > 0) {
			appEntry = AppEntryServiceUtil.updateAppEntry(
				appEntryId, title, descriptionMap, website, demoWebsite,
				documentationWebsite, referenceWebsite, sourceCodeWebsite,
				supportWebsite, labs, productType, null, versionOrder, null,
				iconFile, !paclDisabled, releaseType, eulaContentMap,
				licenseType, orderURL, hidden, portalRequired,
				WorkflowConstants.STATUS_DRAFT, serviceContext);
		}
		else {
			DeveloperEntry developerEntry =
				DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(
					themeDisplay.getScopeGroupId());

			appEntry = AppEntryServiceUtil.addAppEntry(
				developerEntry.getDeveloperEntryId(), title, descriptionMap,
				website, demoWebsite, documentationWebsite, referenceWebsite,
				sourceCodeWebsite, supportWebsite, labs, productType, null,
				null, iconFile, !paclDisabled, eulaContentMap, licenseType,
				orderURL, hidden, portalRequired, serviceContext);
		}

		// App entry rel

		List<AppEntryRel> appEntryRels =
			AppEntryRelLocalServiceUtil.getAppEntryRels(
				appEntry.getAppEntryId(), AppEntryRelConstants.TYPE_SUPERSEDES);

		for (AppEntryRel appEntryRel : appEntryRels) {
			AppEntryRelServiceUtil.deleteAppEntryRel(
				appEntryRel.getAppEntryRelId());
		}

		long[] supersedesAppEntryIds =
			StringUtil.split(
				ParamUtil.getString(
					uploadPortletRequest, "supersedesAppEntryIds"), 0L);

		for (long supersedesAppEntryId : supersedesAppEntryIds) {
			AppEntryRelServiceUtil.addAppEntryRel(
				appEntry.getAppEntryId(), supersedesAppEntryId,
				AppEntryRelConstants.TYPE_SUPERSEDES);
		}

		// App flag

		boolean deprecated = ParamUtil.getBoolean(
			uploadPortletRequest, "deprecated");

		AppVersion appVersion = appEntry.getUnreleasedAppVersion();

		AppFlag appFlag = AppFlagLocalServiceUtil.fetchAppFlag(
			appVersion.getAppVersionId(), AppFlagConstants.TYPE_DEPRECATED);

		if (deprecated) {
			if (appFlag == null) {
				AppFlagLocalServiceUtil.addAppFlag(
					appEntry.getAppEntryId(), appVersion.getAppVersionId(),
					AppFlagConstants.TYPE_DEPRECATED);
			}
		}
		else {
			if (appFlag != null) {
				AppFlagLocalServiceUtil.deleteAppFlag(appFlag);
			}
		}

		// Asset attachment

		long[] assetAttachmentIds = StringUtil.split(
			ParamUtil.getString(
				uploadPortletRequest, "assetAttachmentIds"), 0L);

		int rank = 1;

		for (long assetAttachmentId : assetAttachmentIds) {
			AssetAttachmentLocalServiceUtil.updateAssetAttachment(
				assetAttachmentId, AppVersion.class.getName(),
				appVersion.getAppVersionId(), rank);

			rank++;
		}

		return appEntry;
	}

	protected void doUpdateAppEntryVersion(
			UploadPortletRequest uploadPortletRequest,
			ActionResponse actionResponse, AppEntry appEntry)
		throws Exception {

		String version = ParamUtil.getString(uploadPortletRequest, "version");
		Map<Locale, String> changeLogMap = LocalizationUtil.getLocalizationMap(
			uploadPortletRequest, "changeLog");

		AppVersion appVersion = appEntry.getUnreleasedAppVersion();

		if (!appVersion.isNewRelease()) {
			version = appVersion.getVersion();
		}

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			uploadPortletRequest);

		AppEntryServiceUtil.updateAppEntry(
			appEntry.getAppEntryId(), version, changeLogMap, serviceContext);

		List<AppPackage> appPackages =
			AppPackageLocalServiceUtil.getAppPackages(
				appVersion.getAppVersionId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

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

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (_log.isDebugEnabled()) {
			_log.debug(cause, cause);
		}

		if (cause instanceof AppEntryCategoryException ||
			cause instanceof AppEntryChangeLogException ||
			cause instanceof AppEntryDemoWebsiteException ||
			cause instanceof AppEntryDescriptionException ||
			cause instanceof AppEntryDocumentationWebsiteException ||
			cause instanceof AppEntryIconException ||
			cause instanceof AppEntryLicenseLifetimeException ||
			cause instanceof AppEntryLicenseTypeException ||
			cause instanceof AppEntryOrderURLException ||
			cause instanceof AppEntryPACLException ||
			cause instanceof AppEntryReleaseTypeException ||
			cause instanceof AppEntrySourceCodeWebsiteException ||
			cause instanceof AppEntryStatusException ||
			cause instanceof AppEntrySupportWebsiteException ||
			cause instanceof AppEntryTitleException ||
			cause instanceof AppEntryVersionException ||
			cause instanceof AppEntryWebsiteException ||
			cause instanceof AppPackageMarketplaceSupportException ||
			cause instanceof AppPackagePluginBundleSymbolicNameException ||
			cause instanceof AppPackagePluginFileException ||
			cause instanceof AppPackagePluginNameException ||
			cause instanceof AppPackagePluginPACLException ||
			cause instanceof AppPricingCountryException ||
			cause instanceof AppPricingItemCurrencyEntryException ||
			cause instanceof AppPricingItemPriceException ||
			cause instanceof AppPricingPriceException ||
			cause instanceof AssetLicenseLicenseTypeAllotmentException ||
			cause instanceof AssetLicenseLicenseTypeException ||
			cause instanceof AssetLicenseLifetimeException ||
			cause instanceof AssetLicenseUsageTypeException ||
			cause instanceof AssetTagException ||
			cause instanceof ContractEntryContentException ||
			cause instanceof DuplicateAppEntryException ||
			cause instanceof FileSizeException ||
			cause instanceof RestrictedLiferayDeploymentContextException ||
			cause instanceof MaximumAssetLicenseException ||
			cause instanceof MinimumAssetLicenseException ||
			cause instanceof RequiredAppPricingItemException ||
			cause instanceof RequiredLiferayDeploymentContextException ||
			cause instanceof NoSuchCurrencyEntryException ||
			cause instanceof AppEntryLicenseTypeException ||
			cause instanceof RequiredAppPackageException ||
			cause instanceof RequiredAppPackagePluginException ||
			cause instanceof RequiredAssetLicenseException ||
			cause instanceof RequiredResourcesImporterException) {

			return true;
		}
		else {
			return false;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceDeveloperAppsPortlet.class);

}