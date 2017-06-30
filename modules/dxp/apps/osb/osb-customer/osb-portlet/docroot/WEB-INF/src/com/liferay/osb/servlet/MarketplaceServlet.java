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

import com.liferay.compat.portal.kernel.util.HttpUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.osb.NoSuchAppEntryException;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.asset.model.AssetCategory;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Douglas Wong
 */
public class MarketplaceServlet extends HttpServlet {

	@Override
	public void service(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			String path = HttpUtil.fixPath(request.getPathInfo(), true, true);

			if (_log.isDebugEnabled()) {
				_log.debug("Path " + path);
			}

			String[] pathArray = StringUtil.split(path, StringPool.SLASH);

			if (pathArray.length == 0) {
				return;
			}

			String cmd = pathArray[0];

			if (cmd.equals("app")) {
				long appEntryId = ParamUtil.getLong(request, "appId");

				appEntryId = GetterUtil.getLong(pathArray[1], appEntryId);

				jsonObject = getAppEntry(appEntryId);
			}
			else {
				jsonObject.put("message", "Bad request.");
			}
		}
		catch (Exception e) {
			jsonObject.put("message", "Server encountered an error.");
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Response " + jsonObject);
		}

		ServletOutputStream servletOutputStream = response.getOutputStream();

		servletOutputStream.print(jsonObject.toString());
	}

	protected JSONObject getAppEntry(long appEntryId) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
				appEntryId);

			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			List<AssetCategory> assetCategories = appEntry.getAssetCategories();

			for (AssetCategory assetCategory : assetCategories) {
				jsonArray.put(assetCategory.getName());
			}

			jsonObject.put("categories", jsonArray);

			jsonObject.put("demoWebsite", appEntry.getDemoWebsite());
			jsonObject.put(
				"description", appEntry.getDescriptionCurrentValue());
			jsonObject.put("developer", appEntry.getDeveloperEntryName());
			jsonObject.put("developerURL", appEntry.getWebsite());
			jsonObject.put(
				"documentationURL", appEntry.getDocumentationWebsite());
			jsonObject.put("labs", String.valueOf(appEntry.isLabs()));

			AppVersion appVersion = appEntry.getApprovedAppVersion();

			jsonObject.put("latestVersion", appVersion.getVersion());

			jsonObject.put("sourceCodeURL", appEntry.getSourceCodeWebsite());
			jsonObject.put("supportURL", appEntry.getSupportWebsite());
			jsonObject.put("title", appEntry.getTitle());
		}
		catch (NoSuchAppEntryException nsaee) {
			jsonObject.put(
				"message", "No app exists with ID " + appEntryId + ".");
		}

		return jsonObject;
	}

	private static Log _log = LogFactoryUtil.getLog(MarketplaceServlet.class);

}