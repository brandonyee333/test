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

package com.liferay.osb.marketplaceserver.portlet;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.NoSuchAppEntryException;
import com.liferay.osb.marketplace.portlet.MarketplacePortlet;
import com.liferay.osb.marketplace.util.MarketplaceEcommerceUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppPackagePlugin;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.PortalRelease;
import com.liferay.osb.model.impl.AssetAuditImpl;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.service.CorpProjectServiceUtil;
import com.liferay.osb.service.PortalReleaseLocalServiceUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CountryServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.IOException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Peter Shin
 * @author Ryan Park
 * @author Joan Kim
 */
public class MarketplaceServerPortlet extends MarketplacePortlet {

	public void acceptContractEntries(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] appEntryIds = ParamUtil.getLongValues(
			actionRequest, "appEntryIds");
		String ownerClassName = ParamUtil.getString(
			actionRequest, "ownerClassName");
		long ownerClassPK = ParamUtil.getLong(actionRequest, "ownerClassPK");
		int domain = AssetAuditImpl.getDomain(themeDisplay.getPortalURL());
		boolean acceptContract = ParamUtil.getBoolean(
			actionRequest, "acceptContract");
		String legalEntityName = ParamUtil.getString(
			actionRequest, "legalEntityName");

		if ((ownerClassPK > 0) &&
			ownerClassName.equals(CorpProject.class.getName())) {

			CorpProject corpProject = CorpProjectServiceUtil.getCorpProject(
				ownerClassPK);

			legalEntityName = corpProject.getName();
		}

		if (ownerClassName.equals(User.class.getName())) {
			ownerClassPK = themeDisplay.getUserId();
		}

		User user = themeDisplay.getUser();

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		long countryId = GetterUtil.getLong(
			expandoBridge.getAttribute("osbStoreCountryId"));

		Country country = CountryServiceUtil.fetchCountry(countryId);

		if (country == null) {
			country = CountryServiceUtil.fetchCountryByA2(
				PortletPropsValues.MARKETPLACE_STORE_COUNTRY_DEFAULT);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("cmd", "acceptContractEntries");

		try {
			for (long appEntryId : appEntryIds) {
				AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
					appEntryId);

				MarketplaceEcommerceUtil.purchaseAppEntryFree(
					themeDisplay.getUserId(), appEntry.getAppEntryId(),
					ownerClassName, ownerClassPK, country.getCountryId(),
					domain, acceptContract, legalEntityName);
			}

			jsonObject.put("message", "success");
		}
		catch (Exception e) {
			jsonObject.put("message", "fail");
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void getApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appEntryId = ParamUtil.getLong(actionRequest, "appEntryId");
		String version = ParamUtil.getString(actionRequest, "version");

		AppVersion appVersion = null;

		if (Validator.isNotNull(version)) {
			appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
				appEntryId, version);
		}
		else {
			appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
				appEntryId, AppVersionConstants.STATUSES_APPROVED, null);
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("appEntryId", appEntryId);
		jsonObject.put("paclEnabled", appVersion.isPaclEnabled());
		jsonObject.put("version", version);

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void getPrepackagedApps(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		int compatibility = ParamUtil.getInteger(
			actionRequest, "compatibility");

		String prepackagedApps = ParamUtil.getString(
			actionRequest, "prepackagedApps");

		JSONObject prepackagedAppsJSONObject = JSONFactoryUtil.createJSONObject(
			prepackagedApps);

		Set<AppPackage> appPackages = new HashSet<AppPackage>();

		Iterator<String> iterator = prepackagedAppsJSONObject.keys();

		while (iterator.hasNext()) {
			String identifyingName = iterator.next();

			String identifyingVersion = prepackagedAppsJSONObject.getString(
				identifyingName);

			List<AppPackage> prepackagedAppPackages =
				AppPackageLocalServiceUtil.getPrepackagedAppPackages(
					compatibility, identifyingName, identifyingVersion);

			appPackages.addAll(prepackagedAppPackages);
		}

		Set<AppVersion> appVersions = new HashSet<AppVersion>(
			appPackages.size());

		for (AppPackage appPackage : appPackages) {
			List<AppPackagePlugin> appPackagePlugins =
				appPackage.getAppPackagePlugins();

			boolean prepackagedAppVersion = true;

			for (AppPackagePlugin appPackagePlugin : appPackagePlugins) {
				String identifyingKey = prepackagedAppsJSONObject.getString(
					appPackagePlugin.getIdentifyingName());

				if (!identifyingKey.equals(
						appPackagePlugin.getIdentifyingKey())) {

					prepackagedAppVersion = false;

					break;
				}
			}

			if (prepackagedAppVersion) {
				AppVersion appVersion = appPackage.getAppVersion();

				if (appVersion.getStatus() ==
						WorkflowConstants.STATUS_APPROVED) {

					appVersions.add(appVersion);
				}
			}
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("cmd", "getPrepackagedApps");
		jsonObject.put("message", "success");

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (AppVersion appVersion : appVersions) {
			if (appVersion.getStatus() != WorkflowConstants.STATUS_APPROVED) {
				continue;
			}

			JSONObject appJSONObject = JSONFactoryUtil.createJSONObject();

			appJSONObject.put("appEntryId", appVersion.getAppEntryId());
			appJSONObject.put("update", !appVersion.isLatestApproved());
			appJSONObject.put("version", appVersion.getVersion());

			jsonArray.put(appJSONObject);
		}

		jsonObject.put("installedApps", jsonArray);

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	@Override
	public void init() throws PortletException {
		super.init();

		errorTemplate = templatePath.concat("error.jsp");
		fixPackUpdateTemplate = templatePath.concat("fix_pack_update.jsp");
		updateTemplate = templatePath.concat("update.jsp");
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		try {
			String actionName = ParamUtil.getString(
				actionRequest, ActionRequest.ACTION_NAME);

			if (actionName.equals("sendApp")) {
				sendApp(actionRequest, actionResponse);
			}
			else {
				super.processAction(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void sendApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appId = ParamUtil.getLong(actionRequest, "appId");
		String cmd = ParamUtil.getString(actionRequest, "cmd");
		String url = ParamUtil.getString(actionRequest, "url");
		String version = ParamUtil.getString(actionRequest, "version");
		String orderUuid = ParamUtil.getString(actionRequest, "orderUuid");
		boolean licensed = ParamUtil.getBoolean(actionRequest, "licensed");

		long companyId = PortalUtil.getCompanyId(actionRequest);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("appId", appId);
		jsonObject.put("cmd", cmd);
		jsonObject.put("url", url);
		jsonObject.put("version", version);

		if (Validator.isNotNull(orderUuid)) {
			jsonObject.put("orderUuid", orderUuid);
		}

		if (licensed) {
			AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appId);

			jsonObject.put("productEntryName", appEntry.getTitle());
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
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

	@Override
	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.FALSE);

		int clientBuild = ParamUtil.getInteger(renderRequest, "clientBuild");
		int compatibility = ParamUtil.getInteger(
			renderRequest, "compatibility");
		String[] installedPatches = ParamUtil.getParameterValues(
			renderRequest, "installedPatches");

		try {
			PortalRelease portalRelease =
				PortalReleaseLocalServiceUtil.getPortalRelease(compatibility);

			if (Validator.isNotNull(portalRelease.getFixPackName()) &&
				!ArrayUtil.contains(
					installedPatches, portalRelease.getFixPackName())) {

				include(fixPackUpdateTemplate, renderRequest, renderResponse);

				return;
			}

			AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
				PortletPropsValues.MARKETPLACE_APP_ENTRY_ID);

			AppPackage appPackage =
				AppPackageLocalServiceUtil.fetchCompatibleAppPackage(
					appEntry.getAppEntryId(), compatibility,
					WorkflowConstants.STATUS_APPROVED_HIDDEN);

			if ((appPackage != null) && appEntry.isApproved()) {
				int marketplaceBuild = GetterUtil.getInteger(
					appEntry.getVersion());

				if (marketplaceBuild > clientBuild) {
					include(updateTemplate, renderRequest, renderResponse);

					return;
				}
			}
		}
		catch (NoSuchAppEntryException nsaee) {
		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		try {
			String remoteMVCPath = ParamUtil.getString(
				renderRequest, "remoteMVCPath");

			if (remoteMVCPath.startsWith("/marketplace/")) {
				validateAppEntry(renderRequest);
			}
		}
		catch (Exception e) {
			include(errorTemplate, renderRequest, renderResponse);

			return;
		}

		super.doDispatch(renderRequest, renderResponse);
	}

	protected String errorTemplate;
	protected String fixPackUpdateTemplate;
	protected String updateTemplate;

	private static final String _CLASS_NAME =
		"com.liferay.portlet.login.action.LoginAction";

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceServerPortlet.class);

	private static MethodKey _getAuthenticatedUserIdMethodKey = new MethodKey(
		"com.liferay.portlet.login.util.LoginUtil", "getAuthenticatedUserId",
		HttpServletRequest.class, String.class, String.class, String.class);

}