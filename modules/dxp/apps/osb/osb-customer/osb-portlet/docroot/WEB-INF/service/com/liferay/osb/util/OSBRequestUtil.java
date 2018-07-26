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
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.portlet.ResourceRequest;

/**
 * @author Douglas Wong
 * @author Ryan Park
 */
public class OSBRequestUtil {

	public static JSONObject getAccountEnvironmentListTypes(
			ResourceRequest resourceRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		int envLFR = ParamUtil.getInteger(resourceRequest, "envLFR");

		List<ListType> envASListTypes = getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_AS);

		jsonObject.put(
			"ENV_AS", getJSONArray(envASListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_AS#key", envASListTypes.hashCode());

		List<ListType> envBrowserListTypes = getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_BROWSER);

		jsonObject.put(
			"ENV_Browser",
			getJSONArray(envBrowserListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_Browser#key", envBrowserListTypes.hashCode());

		if (ProductEntryConstants.isPortalVersion6_2(envLFR) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_0(envLFR) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_1(envLFR)) {

			List<ListType> envCSListTypes = getPortalEnvListTypes(
				envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_CS);

			jsonObject.put(
				"ENV_CS",
				getJSONArray(envCSListTypes, themeDisplay.getLocale()));
			jsonObject.put("ENV_CS#key", envCSListTypes.hashCode());
		}

		List<ListType> envDBListTypes = getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_DB);

		jsonObject.put(
			"ENV_DB", getJSONArray(envDBListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_DB#key", envDBListTypes.hashCode());

		List<ListType> envJVMListTypes = getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_JVM);

		jsonObject.put(
			"ENV_JVM", getJSONArray(envJVMListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_JVM#key", envJVMListTypes.hashCode());

		List<ListType> envOSListTypes = getPortalEnvListTypes(
			envLFR, AccountEnvironmentConstants.LIST_TYPE_ENV_OS);

		jsonObject.put(
			"ENV_OS", getJSONArray(envOSListTypes, themeDisplay.getLocale()));
		jsonObject.put("ENV_OS#key", envOSListTypes.hashCode());

		if (ProductEntryConstants.isDigitalEnterpriseVersion7_0(envLFR) ||
			ProductEntryConstants.isDigitalEnterpriseVersion7_1(envLFR)) {

			long accountEntryId = ParamUtil.getLong(
				resourceRequest, "accountEntryId");
			long productEntryId = ParamUtil.getLong(
				resourceRequest, "productEntryId");

			if (productEntryId != 0) {
				ProductEntry productEntry =
					ProductEntryLocalServiceUtil.getProductEntry(
						productEntryId);

				List<ListType> envSearchListTypes = new ArrayList<>();

				if (hasEnterpriseSearchOffering(
						accountEntryId, productEntry.getEnvironment())) {

					envSearchListTypes = getPortalEnvListTypes(
						envLFR,
						AccountEnvironmentConstants.LIST_TYPE_ENV_SEARCH,
						"enterprise");
				}
				else {
					envSearchListTypes = getPortalEnvListTypes(
						envLFR,
						AccountEnvironmentConstants.LIST_TYPE_ENV_SEARCH,
						"standard");
				}

				jsonObject.put(
					"ENV_Search",
					getJSONArray(envSearchListTypes, themeDisplay.getLocale()));
				jsonObject.put("ENV_Search#key", envSearchListTypes.hashCode());
			}
		}

		return jsonObject;
	}

	public static JSONObject getProductEntryVersionListTypes(
		ResourceRequest resourceRequest) {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		long productEntryId = ParamUtil.getLong(
			resourceRequest, "productEntryId");

		try {
			ProductEntry productEntry =
				ProductEntryLocalServiceUtil.getProductEntry(productEntryId);

			List<ListType> envLFRTypes = productEntry.getAllVersionsListTypes();

			jsonObject.put(
				"ENV_LFR", getJSONArray(envLFRTypes, themeDisplay.getLocale()));
			jsonObject.put("ENV_LFR#key", envLFRTypes.hashCode());

			jsonObject.put("productEntryId", productEntry.getProductEntryId());
		}
		catch (Exception e) {
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

	protected static List<ListType> getPortalEnvListTypes(
		long envLFR, String envListType) {

		return getPortalEnvListTypes(envLFR, envListType, StringPool.BLANK);
	}

	protected static List<ListType> getPortalEnvListTypes(
		long envLFR, String envListType, String sublistType) {

		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(
			envListType);

		listTypes = ListUtil.copy(listTypes);

		if (Validator.isNotNull(sublistType)) {
			sublistType = StringPool.PERIOD + sublistType;
		}

		long[] listTypeIds = AccountEnvironmentConstants.getEnvListTypeIds(
			envLFR, envListType + sublistType);

		Iterator<ListType> itr = listTypes.iterator();

		while (itr.hasNext()) {
			ListType listType = itr.next();

			if (!ArrayUtil.contains(listTypeIds, listType.getListTypeId())) {
				itr.remove();
			}
		}

		return listTypes;
	}

	protected static boolean hasEnterpriseSearchOffering(
		long accountEntryId, int productEntryEnvironment) {

		try {
			List<OfferingEntry> offeringEntries =
				OfferingEntryLocalServiceUtil.getAccountEntryOfferingEntries(
					accountEntryId);

			for (OfferingEntry offeringEntry : offeringEntries) {
				ProductEntry productEntry = offeringEntry.getProductEntry();

				if (productEntry.isEnterpriseSearch() &&
					(productEntry.getEnvironment() ==
						productEntryEnvironment)) {

					return true;
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(OSBRequestUtil.class);

}