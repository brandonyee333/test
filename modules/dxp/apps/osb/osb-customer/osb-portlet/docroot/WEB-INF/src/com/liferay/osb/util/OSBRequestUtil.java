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

package com.liferay.osb.util;

import com.liferay.osb.model.AccountEnvironmentConstants;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;
import java.util.Locale;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

/**
 * @author Douglas Wong
 * @author Ryan Park
 */
public class OSBRequestUtil {

	public static JSONObject getAccountEnvironment(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int envLFR = ParamUtil.getInteger(resourceRequest, "envLFR");

		List<ListType> envASListTypes = SupportUtil.getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_AS);

		jsonObject.put(
			"ENV_AS", getJSONArray(envASListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_AS#key", envASListTypes.hashCode());

		List<ListType> envBrowserListTypes = SupportUtil.getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_BROWSER);

		jsonObject.put(
			"ENV_Browser",
			getJSONArray(envBrowserListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_Browser#key", envBrowserListTypes.hashCode());

		if (ProductEntryConstants.isPortalVersion6_2(envLFR) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_0(envLFR)) {

			List<ListType> envCSListTypes = SupportUtil.getPortalEnvListTypes(
				envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_CS);

			jsonObject.put(
				"ENV_CS",
				getJSONArray(envCSListTypes, themeDisplay.getLocale()));
			jsonObject.put("ENV_CS#key", envCSListTypes.hashCode());
		}

		List<ListType> envDBListTypes = SupportUtil.getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_DB);

		jsonObject.put(
			"ENV_DB", getJSONArray(envDBListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_DB#key", envDBListTypes.hashCode());

		List<ListType> envJVMListTypes = SupportUtil.getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_JVM);

		jsonObject.put(
			"ENV_JVM", getJSONArray(envJVMListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_JVM#key", envJVMListTypes.hashCode());

		List<ListType> envOSListTypes = SupportUtil.getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_OS);

		jsonObject.put(
			"ENV_OS", getJSONArray(envOSListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_OS#key", envOSListTypes.hashCode());

		if (ProductEntryConstants.isDigitalEnterpriseVersion7_0(envLFR)) {
			List<ListType> envSearchListTypes =
				SupportUtil.getPortalEnvListTypes(
					envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_SEARCH);

			jsonObject.put(
				"ENV_Search",
				getJSONArray(envSearchListTypes, themeDisplay.getLocale()));
			jsonObject.put("ENV_Search#key", envSearchListTypes.hashCode());
		}

		return jsonObject;
	}

	protected static JSONArray getJSONArray(
		List<ListType> listTypes, Locale locale) {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (ListType listType : listTypes) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				"name", LanguageUtil.get(locale, listType.getName()));
			jsonObject.put("value", String.valueOf(listType.getListTypeId()));

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

}