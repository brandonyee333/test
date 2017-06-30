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

package com.liferay.osb.marketplaceregistration.util;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.util.ContentUtil;

import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 */
public class MarketplaceRegistrationEmailUtil {

	public static Map<Locale, String> getLocalizationMap(String name)
		throws SystemException {

		PortletPreferences preferences = getPortletPreferences();

		Map<Locale, String> localizationMap =
			LocalizationUtil.getLocalizationMap(preferences, name);

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = localizationMap.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return localizationMap;
		}

		localizationMap.put(defaultLocale, ContentUtil.get(getFileName(name)));

		return localizationMap;
	}

	public static PortletPreferences getPortletPreferences()
		throws SystemException {

		return PortletPreferencesLocalServiceUtil.getPreferences(
			OSBConstants.COMPANY_ID, OSBConstants.COMPANY_ID,
			PortletKeys.PREFS_OWNER_TYPE_COMPANY, PortletKeys.PREFS_PLID_SHARED,
			OSBPortletKeys.OSB_MARKETPLACE_REGISTRATION, null);
	}

	protected static String getFileName(String name) {
		String fileName = name.replaceAll("([A-Z][^A-Z])", "_$1");

		fileName = fileName.replaceAll("([^A-Z_])([A-Z])", "$1_$2");

		return "com/liferay/osb/marketplaceregistration/dependencies/" +
			fileName.toLowerCase() + ".tmpl";
	}

}