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

package com.liferay.osb.servlet;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.NoSuchAppPackageException;
import com.liferay.osb.NoSuchAppVersionException;
import com.liferay.osb.marketplace.util.MarketplaceLiferayPackageUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPackage;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.restful.http.UploadServletRequest;
import com.liferay.osb.restful.servlet.NoResourceException;
import com.liferay.osb.restful.servlet.SimpleRestfulServlet;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AppPackageLocalServiceUtil;
import com.liferay.osb.service.AppVersionLocalServiceUtil;
import com.liferay.osb.servlet.util.MarketplaceReleaseServletCommitUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Layout;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

import java.io.File;
import java.io.FileInputStream;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ryan Park
 * @author Supritha Sundaram
 * @author Haote Chou
 */
public class MarketplaceReleaseServlet extends SimpleRestfulServlet {

	public void deleteAppEntries(
			HttpServletRequest request, HttpServletResponse response,
			String appEntryIdString)
		throws Exception {

		long appEntryId = GetterUtil.getLong(appEntryIdString);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		if (!appEntry.isDeveloperEntryLiferayInc()) {
			throw new PrincipalException();
		}

		int count = AppVersionLocalServiceUtil.getAppVersionsCount(
			appEntryId, WorkflowConstants.STATUS_ANY);

		if (count <= 1) {
			throw new PrincipalException();
		}

		AppVersion appVersion = appEntry.getUnreleasedAppVersion();

		if (appVersion == null) {
			throw new PrincipalException();
		}

		AppVersionLocalServiceUtil.deleteAppVersion(
			appVersion.getAppVersionId());
	}

	public void getAppEntries(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		String keywords = ParamUtil.getString(request, "keywords");
		String buildNumber = request.getParameter("buildNumber");
		String portalRequired = request.getParameter("portalRequired");
		int start = ParamUtil.getInteger(request, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(request, "end", QueryUtil.ALL_POS);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (Validator.isNotNull(buildNumber)) {
			params.put(
				"appPackageCompatibility", GetterUtil.getInteger(buildNumber));
		}

		params.put(
			"developerEntryId", OSBConstants.DEVELOPER_ENTRY_LIFERAY_INC_ID);

		if (Validator.isNotNull(portalRequired)) {
			params.put("portalRequired", GetterUtil.getBoolean(portalRequired));
		}

		List<AppEntry> appEntries = AppEntryLocalServiceUtil.search(
			keywords, params, true, start, end, null);

		for (AppEntry appEntry : appEntries) {
			JSONObject jsonObject = getAppEntryJSONObject(appEntry);

			jsonArray.put(jsonObject);
		}

		ServletResponseUtil.write(response, jsonArray.toString());
	}

	public void getAppEntries(
			HttpServletRequest request, HttpServletResponse response,
			String appEntryIdString)
		throws Exception {

		long appEntryId = GetterUtil.getLong(appEntryIdString);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		if (!appEntry.isDeveloperEntryLiferayInc()) {
			throw new PrincipalException();
		}

		JSONObject jsonObject = getAppEntryJSONObject(appEntry);

		ServletResponseUtil.write(response, jsonObject.toString());
	}

	public void postAppEntries(
			HttpServletRequest request, HttpServletResponse response,
			String appEntryIdString)
		throws Exception {

		if (!(request instanceof UploadServletRequest)) {
			sendError(HttpServletResponse.SC_BAD_REQUEST, response);

			return;
		}

		UploadServletRequest uploadServletRequest =
			(UploadServletRequest)request;

		String version = ParamUtil.getString(uploadServletRequest, "version");
		String changeLog = ParamUtil.getString(
			uploadServletRequest, "changeLog");
		int buildNumber = ParamUtil.getInteger(
			uploadServletRequest, "buildNumber");
		File[] pluginFiles = uploadServletRequest.getFiles("plugin");

		File sourceCodeFile = null;

		File[] sourceCodeFiles = uploadServletRequest.getFiles("sourceCode");

		if (ArrayUtil.getLength(sourceCodeFiles) > 0) {
			sourceCodeFile = sourceCodeFiles[0];
		}

		long appEntryId = GetterUtil.getLong(appEntryIdString);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		if (!appEntry.isDeveloperEntryLiferayInc()) {
			throw new PrincipalException();
		}

		appEntry = MarketplaceReleaseServletCommitUtil.updateAppEntry(
			appEntryId, version, changeLog, buildNumber, pluginFiles,
			sourceCodeFile);

		JSONObject jsonObject = getAppEntryJSONObject(appEntry);

		ServletResponseUtil.write(response, jsonObject.toString());
	}

	public void postAppEntriesHotfix(
			HttpServletRequest request, HttpServletResponse response,
			String appEntryIdString)
		throws Exception {

		if (!(request instanceof UploadServletRequest)) {
			sendError(HttpServletResponse.SC_BAD_REQUEST, response);

			return;
		}

		UploadServletRequest uploadServletRequest =
			(UploadServletRequest)request;

		String version = ParamUtil.getString(uploadServletRequest, "version");
		int buildNumber = ParamUtil.getInteger(
			uploadServletRequest, "buildNumber");
		File[] plugins = uploadServletRequest.getFiles("plugin");

		long appEntryId = GetterUtil.getLong(appEntryIdString);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		if (!appEntry.isDeveloperEntryLiferayInc()) {
			throw new PrincipalException();
		}

		AppVersion appVersion = AppVersionLocalServiceUtil.fetchAppVersion(
			appEntry.getAppEntryId(), version);

		if (appVersion == null) {
			throw new NoSuchAppVersionException();
		}

		AppPackage appPackage = AppPackageLocalServiceUtil.fetchAppPackage(
			appVersion.getAppVersionId(), buildNumber);

		if (appPackage == null) {
			throw new NoSuchAppPackageException();
		}

		File file = MarketplaceLiferayPackageUtil.buildLiferayPackage(
			appPackage, plugins, true);

		String fileName = appEntry.getTitle() + ".lpkg";

		ServletResponseUtil.sendFile(
			request, response, fileName, new FileInputStream(file),
			ContentTypes.APPLICATION_ZIP);
	}

	public void postAppEntriesStatus(
			HttpServletRequest request, HttpServletResponse response,
			String appEntryIdString)
		throws Exception {

		long appEntryId = GetterUtil.getLong(appEntryIdString);

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		if (!appEntry.isDeveloperEntryLiferayInc()) {
			throw new PrincipalException();
		}

		String statusLabel = ParamUtil.getString(
			request, "status", WorkflowConstants.LABEL_ANY);

		int status = WorkflowConstants.toStatus(statusLabel);

		if (status != WorkflowConstants.STATUS_APPROVED) {
			throw new PrincipalException();
		}

		AppVersion appVersion = appEntry.getActionableAppVersion();

		appEntry = AppEntryLocalServiceUtil.updateStatus(
			appVersion.getUserId(), appEntryId, status, "Approved by Release",
			new ServiceContext());

		JSONObject jsonObject = getAppEntryJSONObject(appEntry);

		ServletResponseUtil.write(response, jsonObject.toString());
	}

	protected JSONObject getAppEntryJSONObject(AppEntry appEntry)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("appEntryId", appEntry.getAppEntryId());
		jsonObject.put("portalRequired", appEntry.isPortalRequired());
		jsonObject.put("status", appEntry.getStatusLabel());
		jsonObject.put("title", appEntry.getTitle());
		jsonObject.put("version", appEntry.getVersion());

		AppVersion appVersion = appEntry.getActionableAppVersion();

		jsonObject.put("versionStatus", appVersion.getStatusLabel());

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<AppVersion> appVersions =
			AppVersionLocalServiceUtil.getAppVersions(
				appEntry.getAppEntryId(), WorkflowConstants.STATUS_ANY,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (AppVersion curAppVersion : appVersions) {
			if (curAppVersion.getStatus() == WorkflowConstants.STATUS_EXPIRED) {
				continue;
			}

			JSONObject appVersionJSONObject =
				JSONFactoryUtil.createJSONObject();

			appVersionJSONObject.put("version", curAppVersion.getVersion());
			appVersionJSONObject.put(
				"releaseDate", curAppVersion.getReleaseDate());

			JSONArray appPackagesJSONArray = JSONFactoryUtil.createJSONArray();

			List<AppPackage> appPackages =
				AppPackageLocalServiceUtil.getAppPackages(
					curAppVersion.getAppVersionId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

			for (AppPackage appPackage : appPackages) {
				appPackagesJSONArray.put(getAppPackageJSONObject(appPackage));
			}

			appVersionJSONObject.put("packages", appPackagesJSONArray);

			appVersionJSONObject.put("status", curAppVersion.getStatusLabel());

			jsonArray.put(appVersionJSONObject);
		}

		jsonObject.put("versions", jsonArray);

		return jsonObject;
	}

	protected String getAppPackageDownloadURL(AppPackage appPackage)
		throws Exception {

		StringBundler sb = new StringBundler(5);

		Company company = CompanyLocalServiceUtil.getCompany(
			PortalUtil.getDefaultCompanyId());

		Group group = GroupLocalServiceUtil.getGroup(
			company.getCompanyId(), GroupConstants.GUEST);

		sb.append(company.getPortalURL(group.getGroupId()));

		sb.append("/web");
		sb.append(group.getFriendlyURL());

		long plid = PortalUtil.getPlidFromPortletId(
			group.getGroupId(), OSBPortletKeys.OSB_MARKETPLACE);

		Layout layout = LayoutLocalServiceUtil.getLayout(plid);

		sb.append(layout.getFriendlyURL());

		sb.append("/-/mp/application/");
		sb.append(appPackage.getAppEntryId());
		sb.append(StringPool.SLASH);
		sb.append(appPackage.getCompatibility());
		sb.append(StringPool.SLASH);

		AppVersion appVersion = appPackage.getAppVersion();

		sb.append(appVersion.getVersion());

		sb.append(StringPool.SLASH);
		sb.append(PortletPropsValues.MARKETPLACE_RELEASE_TEAM_TOKEN);

		return sb.toString();
	}

	protected JSONObject getAppPackageJSONObject(AppPackage appPackage)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("buildNumber", appPackage.getCompatibility());
		jsonObject.put("downloadURL", getAppPackageDownloadURL(appPackage));

		return jsonObject;
	}

	protected String getAuthorizationToken(HttpServletRequest request) {
		String token = ParamUtil.getString(request, "token");

		String authorization = request.getHeader("Authorization");

		if (authorization == null) {
			return token;
		}

		StringTokenizer stringTokenizer = new StringTokenizer(authorization);

		if (!stringTokenizer.hasMoreTokens()) {
			return token;
		}

		String authorizationType = stringTokenizer.nextToken();

		if (!authorizationType.equalsIgnoreCase("token")) {
			return token;
		}

		return stringTokenizer.nextToken();
	}

	@Override
	protected String getResourceKey(String path) {
		String[] pathArray = StringUtil.split(path, StringPool.SLASH);

		if (pathArray.length == 0) {
			return null;
		}

		String resourceKey = pathArray[pathArray.length - 1];

		if (Validator.isNumber(resourceKey)) {
			return resourceKey;
		}

		return null;
	}

	@Override
	protected String getResourceName(String path) throws NoResourceException {
		if (Validator.isNull(path)) {
			throw new NoResourceException();
		}

		if (getResourceKey(path) != null) {
			return path.substring(0, path.lastIndexOf(CharPool.SLASH));
		}

		return path;
	}

	@Override
	protected boolean isAuthorized(HttpServletRequest request) {
		if (!PortletPropsValues.DEVELOPER_MODE_ENABLED) {
			if (!request.isSecure()) {
				return false;
			}
		}

		String token = getAuthorizationToken(request);

		if (token.equals(PortletPropsValues.MARKETPLACE_RELEASE_TEAM_TOKEN)) {
			return true;
		}

		return false;
	}

}