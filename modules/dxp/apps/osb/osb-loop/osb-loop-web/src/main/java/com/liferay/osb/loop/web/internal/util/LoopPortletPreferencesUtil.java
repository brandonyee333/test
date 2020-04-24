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

package com.liferay.osb.loop.web.internal.util;

import com.liferay.osb.loop.constants.LoopPortletKeys;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationKeys;
import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationUtil;
import com.liferay.osb.loop.web.internal.constants.LoopUserNotificationConstants;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortletKeys;

import javax.portlet.PortletPreferences;

/**
 * @author Timothy Bell
 */
public class LoopPortletPreferencesUtil {

	public static String getPreference(
			long companyId, long ownerId, int ownerType, String key)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				companyId, ownerId, ownerType, PortletKeys.PREFS_PLID_SHARED,
				LoopPortletKeys.LOOP, getDefaultPreferences(ownerType));

		return portletPreferences.getValue(key, StringPool.BLANK);
	}

	public static JSONObject getPreferencesJSONObject(
			ThemeDisplay themeDisplay, int ownerType)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				themeDisplay.getCompanyId(), themeDisplay.getUserId(),
				ownerType, PortletKeys.PREFS_PLID_SHARED, LoopPortletKeys.LOOP,
				getDefaultPreferences(ownerType));

		for (String category :
				LoopUserNotificationConstants.SETTING_CATEGORIES) {

			jsonArray.put(
				getCategoryJSONObject(
					themeDisplay, category, portletPreferences));
		}

		jsonObject.put("categories", jsonArray);

		for (String key :
				LoopUserNotificationConstants.SETTING_KEYS_TYPE_BOOLEAN) {

			jsonObject.put(
				key,
				GetterUtil.getBoolean(
					portletPreferences.getValue(key, StringPool.BLANK)));
		}

		return jsonObject;
	}

	public static void setPreferences(
			long companyId, long ownerId, int ownerType, String key,
			String value)
		throws Exception {

		PortletPreferences portletPreferences =
			PortletPreferencesLocalServiceUtil.getPreferences(
				companyId, ownerId, ownerType, PortletKeys.PREFS_PLID_SHARED,
				LoopPortletKeys.LOOP, getDefaultPreferences(ownerType));

		portletPreferences.setValue(key, value);

		portletPreferences.store();
	}

	protected static JSONObject getCategoryJSONObject(
			ThemeDisplay themeDisplay, String category,
			PortletPreferences portletPreferences)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("label", themeDisplay.translate(category));

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (String key :
				LoopUserNotificationConstants.getCategorySettingKeys(
					category)) {

			jsonArray.put(
				getSettingJSONObject(themeDisplay, key, portletPreferences));
		}

		jsonObject.put("settings", jsonArray);

		return jsonObject;
	}

	protected static String getDefaultPreferences(int ownerType)
		throws Exception {

		if (ownerType != PortletKeys.PREFS_OWNER_TYPE_USER) {
			return null;
		}

		if (_defaultPreferences != null) {
			return _defaultPreferences;
		}

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.fromDefaultXML(StringPool.BLANK);

		for (String key :
				LoopUserNotificationConstants.SETTING_KEYS_TYPE_BOOLEAN) {

			portletPreferences.setValue(
				key,
				LoopWebConfigurationUtil.get(
					LoopWebConfigurationKeys.LOOP_NOTIFICATION_SETTING_DEFAULTS,
					new Filter(key)));
		}

		for (String key :
				LoopUserNotificationConstants.SETTING_KEYS_TYPE_JSON_OBJECT) {

			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			String[] defaultSettings = LoopWebConfigurationUtil.getArray(
				LoopWebConfigurationKeys.LOOP_NOTIFICATION_SETTING_DEFAULTS,
				new Filter(key));

			for (String typeLabel :
					LoopUserNotificationConstants.LABELS_DELIVERY_TYPE) {

				jsonObject.put(
					typeLabel, ArrayUtil.contains(defaultSettings, typeLabel));
			}

			portletPreferences.setValue(key, jsonObject.toString());
		}

		_defaultPreferences = PortletPreferencesFactoryUtil.toXML(
			portletPreferences);

		return _defaultPreferences;
	}

	protected static JSONObject getSettingJSONObject(
			ThemeDisplay themeDisplay, String key,
			PortletPreferences portletPreferences)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("key", key);
		jsonObject.put(
			"label",
			themeDisplay.translate(
				LoopUserNotificationConstants.getSettingLabel(key)));
		jsonObject.put(
			"values",
			JSONFactoryUtil.createJSONObject(
				portletPreferences.getValue(key, StringPool.BLANK)));

		return jsonObject;
	}

	private static String _defaultPreferences;

}