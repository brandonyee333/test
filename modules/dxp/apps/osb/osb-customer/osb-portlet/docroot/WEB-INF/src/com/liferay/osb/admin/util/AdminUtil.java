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

package com.liferay.osb.admin.util;

import com.liferay.osb.tools.BaseUpgradeImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;

import java.net.URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Peter Shin
 * @author Alan Zhang
 */
public class AdminUtil {

	public static String formatAddress(Address address) {
		if (address == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(7);

		if (Validator.isNotNull(address.getStreet1())) {
			sb.append(address.getStreet1());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(address.getStreet2())) {
			sb.append(address.getStreet2());
			sb.append(StringPool.NEW_LINE);
		}

		if (Validator.isNotNull(address.getStreet3())) {
			sb.append(address.getStreet3());
			sb.append(StringPool.NEW_LINE);
		}

		StringBundler sb2 = new StringBundler(7);

		if (Validator.isNotNull(address.getCity())) {
			sb2.append(address.getCity());
		}

		Region region = address.getRegion();

		if (Validator.isNotNull(region.getName())) {
			if (sb2.index() != 0) {
				sb2.append(StringPool.SPACE);
			}

			sb2.append(region.getName());
		}

		if (Validator.isNotNull(address.getZip())) {
			if (sb2.index() != 0) {
				sb2.append(StringPool.SPACE);
			}

			sb2.append(address.getZip());
		}

		Country country = address.getCountry();

		if (Validator.isNotNull(country.getName())) {
			if (sb2.index() != 0) {
				sb2.append(StringPool.NEW_LINE);
			}

			sb2.append(country.getName());
		}

		sb.append(sb2);

		return sb.toString();
	}

	public static Map<Locale, String> getEmailProvisioningCreateAccountBodyMap(
		PortletPreferences preferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			preferences, "emailProvisioningCreateAccountBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				AdminUtil.class.getClassLoader(),
				"com/liferay/osb/admin/dependencies" +
					"/email_provisioning_create_account_body.tmpl"));

		return map;
	}

	public static Map<Locale, String>
		getEmailProvisioningCreateAccountSubjectMap(
			PortletPreferences preferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			preferences, "emailProvisioningCreateAccountSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				AdminUtil.class.getClassLoader(),
				"com/liferay/osb/admin/dependencies" +
					"/email_provisioning_create_account_subject.tmpl"));

		return map;
	}

	public static List<Class<?>> getManualUpgradeProcessClasses(int buildNumber)
		throws Exception {

		String manualUpgradeProcessPackage = _getManualUpgradeProcessPackage(
			buildNumber);

		String manualUpgradeProcessDirPath = StringUtil.replace(
			manualUpgradeProcessPackage, CharPool.PERIOD, CharPool.SLASH);

		ClassLoader classLoader = BaseUpgradeImpl.class.getClassLoader();

		URL manualUpgradeProcessURL = classLoader.getResource(
			manualUpgradeProcessDirPath);

		if (manualUpgradeProcessURL == null) {
			return Collections.emptyList();
		}

		List<Class<?>> manualUpgradeProcessClasses = new ArrayList<>();

		File manualUpgradeProcessDir = new File(
			manualUpgradeProcessURL.getFile());

		String[] fileNames = manualUpgradeProcessDir.list();

		Arrays.sort(fileNames);

		for (String fileName : fileNames) {
			if (!fileName.endsWith(".class")) {
				continue;
			}

			String className = fileName.substring(0, fileName.length() - 6);

			Class<?> manualUpgradeProcessClass = classLoader.loadClass(
				manualUpgradeProcessPackage + StringPool.PERIOD + className);

			manualUpgradeProcessClasses.add(manualUpgradeProcessClass);
		}

		return manualUpgradeProcessClasses;
	}

	public static PortletPreferences getPortletPreferences() {
		long ownerId = OSBConstants.COMPANY_ID;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = OSBPortletKeys.OSB_ADMIN;
		String defaultPreferences = null;

		return PortletPreferencesLocalServiceUtil.getPreferences(
			OSBConstants.COMPANY_ID, ownerId, ownerType, plid, portletId,
			defaultPreferences);
	}

	private static String _getManualUpgradeProcessPackage(int buildNumber) {
		String buildNumberString = String.valueOf(buildNumber);

		StringBundler sb = new StringBundler(buildNumberString.length() * 2);

		sb.append("com.liferay.osb.hook.upgrade.v");

		for (int i = 0; i < buildNumberString.length(); i++) {
			char c = buildNumberString.charAt(i);

			sb.append(c);

			if (i < (buildNumberString.length() - 1)) {
				sb.append(CharPool.UNDERLINE);
			}
		}

		sb.append(".manual");

		return sb.toString();
	}

}