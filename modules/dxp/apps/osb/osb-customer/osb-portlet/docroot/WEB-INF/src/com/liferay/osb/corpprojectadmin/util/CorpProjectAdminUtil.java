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

package com.liferay.osb.corpprojectadmin.util;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Kyle Bischof
 */
public class CorpProjectAdminUtil {

	public static PortletPreferences getPortletPreferences()
		throws SystemException {

		long ownerId = OSBConstants.COMPANY_ID;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = OSBPortletKeys.OSB_CORP_PROJECT_ADMIN;

		return PortletPreferencesLocalServiceUtil.getPreferences(
			OSBConstants.COMPANY_ID, ownerId, ownerType, plid, portletId, null);
	}

	public static String getPreferenceKey(
		String prefix, String key, String languageId) {

		StringBundler sb = new StringBundler(4);

		sb.append(prefix);
		sb.append(key);
		sb.append(StringPool.UNDERLINE);
		sb.append(languageId);

		return sb.toString();
	}

	public static String getPreferenceValue(Locale locale, String key)
		throws SystemException {

		PortletPreferences portletPreferences = getPortletPreferences();

		Map<Locale, String> valueMap = LocalizationUtil.getLocalizationMap(
			portletPreferences, key);

		String value = valueMap.get(locale);

		if (Validator.isNull(value)) {
			Locale defaultLocale = LocaleUtil.getDefault();

			value = valueMap.get(defaultLocale);
		}

		return value;
	}

}