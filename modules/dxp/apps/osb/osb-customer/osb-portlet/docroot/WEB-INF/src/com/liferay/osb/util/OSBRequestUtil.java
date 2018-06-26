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
import com.liferay.portal.kernel.util.StringPool;
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
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_AS, StringPool.BLANK);

		jsonObject.put(
			"ENV_AS", getJSONArray(envASListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_AS#key", envASListTypes.hashCode());

		List<ListType> envBrowserListTypes = SupportUtil.getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_BROWSER,
			StringPool.BLANK);

		jsonObject.put(
			"ENV_Browser",
			getJSONArray(envBrowserListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_Browser#key", envBrowserListTypes.hashCode());

		if (ProductEntryConstants.isPortalVersion6_2(envLFR) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_0(envLFR)) {

			List<ListType> envCSListTypes = SupportUtil.getPortalEnvListTypes(
				envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_CS,
				StringPool.BLANK);

			jsonObject.put(
				"ENV_CS",
				getJSONArray(envCSListTypes, themeDisplay.getLocale()));
			jsonObject.put("ENV_CS#key", envCSListTypes.hashCode());
		}

		List<ListType> envDBListTypes = SupportUtil.getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_DB, StringPool.BLANK);

		jsonObject.put(
			"ENV_DB", getJSONArray(envDBListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_DB#key", envDBListTypes.hashCode());

		List<ListType> envJVMListTypes = SupportUtil.getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_JVM, StringPool.BLANK);

		jsonObject.put(
			"ENV_JVM", getJSONArray(envJVMListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_JVM#key", envJVMListTypes.hashCode());

		List<ListType> envOSListTypes = SupportUtil.getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_OS, StringPool.BLANK);

		jsonObject.put(
			"ENV_OS", getJSONArray(envOSListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_OS#key", envOSListTypes.hashCode());

		if (ProductEntryConstants.isDigitalEnterpriseVersion7_0(envLFR)) {
			long offeringEntryId = ParamUtil.getInteger(
				resourceRequest, "offeringEntryId");

			List<ListType> envSearchListTypes = new ArrayList<ListType>();

			if (SupportUtil.hasEnterpriseSearchEnvOffering(offeringEntryId)) {
				envSearchListTypes = SupportUtil.getPortalEnvListTypes(
					envLFR, TicketEntryConstants.LIST_TYPE_ENV_SEARCH,
					".enterprise");
			}
			else {
				envSearchListTypes = SupportUtil.getPortalEnvListTypes(
					envLFR, TicketEntryConstants.LIST_TYPE_ENV_SEARCH,
					".standard");
			}

			jsonObject.put(
				"ENV_Search",
				getJsonArray(envSearchListTypes, themeDisplay.getLocale()));
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