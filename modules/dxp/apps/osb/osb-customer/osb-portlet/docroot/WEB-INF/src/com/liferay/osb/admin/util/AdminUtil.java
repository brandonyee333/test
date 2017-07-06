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

package com.liferay.osb.admin.util;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.model.FileRepository;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.util.ContentUtil;

import java.net.URL;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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

	public static String getCommentPreferenceKey(
		String tab, String languageId) {

		String key = StringPool.BLANK;

		if (tab.equals("game-plan")) {
			key = "GamePlan";
		}
		else if (tab.equals("inactive-ticket")) {
			key = "TicketEntryInactive";
		}
		else {
			key = "TicketEntryClosed";
		}

		return getPreferenceKey("comment", key, languageId);
	}

	public static String getComponentPreferenceKey(
		String prefix, String productDisplayName, String key,
		String languageId) {

		StringBundler sb = new StringBundler(6);

		sb.append(prefix);
		sb.append(productDisplayName);
		sb.append(StringPool.UNDERLINE);
		sb.append(key);
		sb.append(StringPool.UNDERLINE);
		sb.append(languageId);

		return sb.toString();
	}

	public static Map<Locale, String> getEmailInactiveUserBodyMap(
		PortletPreferences preferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			preferences, "emailInactiveUserBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/admin/dependencies/" +
					"email_inactive_user_body.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailInactiveUserSubjectMap(
		PortletPreferences preferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			preferences, "emailInactiveUserSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/admin/dependencies/" +
					"email_inactive_user_subject.tmpl"));

		return map;
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
				"com/liferay/osb/admin/dependencies/" +
					"email_provisioning_create_account_body.tmpl"));

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
				"com/liferay/osb/admin/dependencies/" +
					"email_provisioning_create_account_subject.tmpl"));

		return map;
	}

	public static List<FileRepository> getFileRepositories()
		throws SystemException {

		List<FileRepository> fileRepositoryList =
			new ArrayList<FileRepository>();

		String fileRepositories = getPortletPreferences().getValue(
			"fileRepositories", null);

		UnicodeProperties fileRepositoriesProperties = new UnicodeProperties(
			true);

		fileRepositoriesProperties.fastLoad(fileRepositories);

		for (String fileRepositoryProperties :
				fileRepositoriesProperties.values()) {

			fileRepositoryList.add(
				new FileRepository(fileRepositoryProperties));
		}

		return fileRepositoryList;
	}

	public static FileRepository getFileRepository(String fileRepositoryId)
		throws SystemException {

		String fileRepositories = getPortletPreferences().getValue(
			"fileRepositories", null);

		UnicodeProperties fileRepositoriesProperties = new UnicodeProperties(
			true);

		fileRepositoriesProperties.fastLoad(fileRepositories);

		String fileRepositoryProperties =
			fileRepositoriesProperties.getProperty(fileRepositoryId);

		return new FileRepository(fileRepositoryProperties);
	}

	public static Map<Locale, String> getMarketingEventLocalizationMap(
		PortletRequest portletRequest, String param) {

		Map<Locale, String> localizationMap = new HashMap<Locale, String>();

		String[] translationLanguageIds = StringUtil.split(
			ParamUtil.getString(portletRequest, "translation_languageIds"));

		for (String translationLanguageId : translationLanguageIds) {
			String value = ParamUtil.getString(
				portletRequest,
				"translation_" + translationLanguageId + "_" + param);

			localizationMap.put(
				LocaleUtil.fromLanguageId(translationLanguageId), value);
		}

		return localizationMap;
	}

	public static PortletPreferences getPortletPreferences()
		throws SystemException {

		long ownerId = OSBConstants.COMPANY_ID;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = OSBPortletKeys.OSB_ADMIN;
		String defaultPreferences = null;

		return PortletPreferencesLocalServiceUtil.getPreferences(
			OSBConstants.COMPANY_ID, ownerId, ownerType, plid, portletId,
			defaultPreferences);
	}

	public static String getPreferenceKey(
		String prefix, String key, String param) {

		StringBundler sb = new StringBundler(4);

		sb.append(prefix);
		sb.append(key);
		sb.append(StringPool.UNDERLINE);
		sb.append(param);

		return sb.toString();
	}

	public static void reindex(String portletId, long modifiedDateOffset)
		throws PortalException, SystemException {

		if (portletId.equals(OSBPortletKeys.OSB_SUPPORT)) {
			Date date = new Date(
				System.currentTimeMillis() - modifiedDateOffset);

			int count = TicketEntryLocalServiceUtil.getTicketEntriesCount(date);
			int pages = count / 5000;

			for (int i = 0; i <= pages; i++) {
				int start = i * 5000;
				int end = start + 5000;

				List<TicketEntry> ticketEntries =
					TicketEntryLocalServiceUtil.getTicketEntries(
						date, start, end);

				for (TicketEntry ticketEntry : ticketEntries) {
					TicketEntryLocalServiceUtil.reindexTicketEntry(ticketEntry);
				}
			}
		}
	}

}