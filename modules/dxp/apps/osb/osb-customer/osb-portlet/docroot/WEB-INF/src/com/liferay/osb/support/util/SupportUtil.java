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

package com.liferay.osb.support.util;

import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEnvironmentConstants;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.FileRepository;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryGroup;
import com.liferay.osb.model.OfferingEntryGroupFactoryUtil;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.model.SupportRegion;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AccountEntryServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.PortletPropsKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.OfferingEntryPKComparator;
import com.liferay.petra.content.ContentUtil;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.portlet.PortletProps;

import java.net.URL;

import java.text.Format;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 * @author Mate Thurzo
 */
public class SupportUtil {

	public static String[] getAccountWorkerKeys(long accountEntryId) {
		List<AccountWorker> accountWorkers =
			AccountWorkerLocalServiceUtil.getAccountWorkers(accountEntryId);

		String[] accountWorkerKeys = new String[accountWorkers.size()];

		for (int i = 0; i < accountWorkers.size(); i++) {
			AccountWorker accountWorker = accountWorkers.get(i);

			accountWorkerKeys[i] = accountWorker.getKey();
		}

		return accountWorkerKeys;
	}

	public static String getAccountWorkers(long accountEntryId, int role)
		throws PortalException {

		List<User> users = getUsers(accountEntryId, role);

		StringBuilder sb = new StringBuilder();

		for (User user : users) {
			if (sb.length() > 0) {
				sb.append(StringPool.COMMA_AND_SPACE);
			}

			sb.append(HtmlUtil.escape(user.getFullName()));
		}

		return sb.toString();
	}

	public static Map<Locale, String> getActionInformationMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "actionInformation");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/action_information_body.tmpl"));

		return map;
	}

	public static String[] getAttachmentKeywords() {
		PortletPreferences portletPreferences = getPortletPreferences();

		String[] attachmentKeywords = StringUtil.split(
			portletPreferences.getValue(
				"attachmentKeywords", "find the attached"),
			StringPool.NEW_LINE);

		return attachmentKeywords;
	}

	public static List<OfferingEntryGroup> getAvailableOfferingEntryGroups(
			long userId, long accountEntryId, int[] types, int[] statuses,
			int supportEndDateGTDay, int supportEndDateGTMonth,
			int supportEndDateGTYear, int supportEndDateLTDay,
			int supportEndDateLTMonth, int supportEndDateLTYear,
			LinkedHashMap<String, Object> params, boolean andSearch)
		throws PortalException {

		List<OfferingEntryGroup> availableOfferingEntryGroups =
			new ArrayList<>();

		List<OfferingEntryGroup> offeringEntryGroups = getOfferingEntryGroups(
			userId, accountEntryId, types, statuses, supportEndDateGTDay,
			supportEndDateGTMonth, supportEndDateGTYear, supportEndDateLTDay,
			supportEndDateLTMonth, supportEndDateLTYear, params, andSearch);

		for (OfferingEntryGroup offeringEntryGroup : offeringEntryGroups) {
			if (offeringEntryGroup.hasAvailableSupportTickets()) {
				availableOfferingEntryGroups.add(offeringEntryGroup);
			}
		}

		return availableOfferingEntryGroups;
	}

	public static Map<Locale, String> getCommentGamePlanMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "commentGamePlan");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/comment_game_plan_body.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailAccountEntryTierBodyMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailAccountEntryTierBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/email_account_entry_tier_body.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailAccountEntryTierSubjectMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailAccountEntryTierSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/email_account_entry_tier_subject.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailCustomerFeedbackBodyMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailCustomerFeedbackBody");

		for (Locale locale : LanguageUtil.getAvailableLocales()) {
			String value = map.get(locale);

			if (Validator.isNotNull(value)) {
				continue;
			}

			String templateName =
				"email_customer_feedback_body_" + locale + ".tmpl";

			map.put(
				locale,
				_getEmailTemplate(
					templateName, "email_customer_feedback_body.tmpl"));
		}

		return map;
	}

	public static Map<Locale, String> getEmailCustomerFeedbackSubjectMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailCustomerFeedbackSubject");

		for (Locale locale : LanguageUtil.getAvailableLocales()) {
			String value = map.get(locale);

			if (Validator.isNotNull(value)) {
				continue;
			}

			String templateName =
				"email_customer_feedback_subject_" + locale + ".tmpl";

			map.put(
				locale,
				_getEmailTemplate(
					templateName, "email_customer_feedback_subject.tmpl"));
		}

		return map;
	}

	public static Map<Locale, String> getEmailPartnerFeedbackReminderBodyMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailLiferayFeedbackBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/email_partner_feedback_reminder_body.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailPartnerFeedbackReminderSubjectMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailLiferayFeedbackSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/email_partner_feedback_reminder_subject.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailTeamFeedbackBodyMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailTeamFeedbackBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/email_team_feedback_body.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailTeamFeedbackSubjectMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailTeamFeedbackSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/email_team_feedback_subject.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailWorkerFeedbackBodyMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailWorkerFeedbackBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/email_worker_feedback_body.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailWorkerFeedbackSubjectMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailWorkerFeedbackSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/email_worker_feedback_subject.tmpl"));

		return map;
	}

	public static String getEscalationDetails() {
		PortletPreferences portletPreferences = getPortletPreferences();

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "escalationDetails");

		Locale defaultLocale = LocaleUtil.getDefault();

		String escalationDetails = map.get(defaultLocale);

		if (Validator.isNull(escalationDetails)) {
			escalationDetails = ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				"com/liferay/osb/support/dependencies" +
					"/message_escalation_details_body.tmpl");
		}

		return escalationDetails;
	}

	public static List<FileRepository> getFileRepositories() {
		List<FileRepository> fileRepositoryList = new ArrayList<>();

		PortletPreferences portletPreferences = getPortletPreferences();

		String fileRepositories = portletPreferences.getValue(
			"fileRepositories", null);

		UnicodeProperties fileRepositoriesProperties = new UnicodeProperties(
			true);

		fileRepositoriesProperties.fastLoad(fileRepositories);

		if (fileRepositoriesProperties.isEmpty()) {
			return getDefaultFileRepositories();
		}

		for (String fileRepositoryProperties :
				fileRepositoriesProperties.values()) {

			FileRepository fileRepository = new FileRepository(
				fileRepositoryProperties);

			if (fileRepository.getStatus() ==
					WorkflowConstants.STATUS_INACTIVE) {

				continue;
			}

			fileRepositoryList.add(fileRepository);
		}

		return fileRepositoryList;
	}

	public static FileRepository getFileRepository(String fileRepositoryId) {
		if (Validator.isNull(fileRepositoryId)) {
			return null;
		}

		PortletPreferences portletPreferences = getPortletPreferences();

		String fileRepositories = portletPreferences.getValue(
			"fileRepositories", null);

		if (fileRepositories != null) {
			UnicodeProperties fileRepositoriesProperties =
				new UnicodeProperties(true);

			fileRepositoriesProperties.fastLoad(fileRepositories);

			String fileRepositoryProperties =
				fileRepositoriesProperties.getProperty(fileRepositoryId);

			return new FileRepository(fileRepositoryProperties);
		}
		else {
			return getDefaultFileRepository(fileRepositoryId);
		}
	}

	/* Refactor for zendesk
	 * 
	 * public static FileRepository getFileRepository(TicketEntry ticketEntry) {
		FileRepository defaultFileRepository = null;

		for (FileRepository fileRepository : getFileRepositories()) {
			long[] supportRegionIds = fileRepository.getSupportRegionIds();

			if (ArrayUtil.contains(
					supportRegionIds, ticketEntry.getSupportRegionId())) {

				return fileRepository;
			}

			if (defaultFileRepository == null) {
				defaultFileRepository = fileRepository;
			}
		}

		return defaultFileRepository;
	}*/

	public static FileRepository getFirstActiveFileRepository(
		Set<String> fileRepositoryIdsSet) {

		if (fileRepositoryIdsSet.isEmpty()) {
			return null;
		}

		for (FileRepository fileRepository : getFileRepositories()) {
			String fileRepositoryId = fileRepository.getFileRepositoryId();

			if (fileRepositoryIdsSet.contains(fileRepositoryId)) {
				return fileRepository;
			}
		}

		return null;
	}

	public static int getListTypeIdFromName(
		String type, String name, boolean translate) {

		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(type);

		for (ListType listType : listTypes) {
			String listTypeName = listType.getName();

			if (translate) {
				listTypeName = LanguageUtil.get(Locale.US, listTypeName);
			}

			if (listTypeName.equals(name)) {
				return (int)listType.getListTypeId();
			}
		}

		return 0;
	}

	public static List<OfferingEntryGroup> getOfferingEntryGroups(
			long userId, long accountEntryId, int[] types, int[] statuses,
			int supportEndDateGTDay, int supportEndDateGTMonth,
			int supportEndDateGTYear, int supportEndDateLTDay,
			int supportEndDateLTMonth, int supportEndDateLTYear,
			LinkedHashMap<String, Object> params, boolean andSearch)
		throws PortalException {

		return getOfferingEntryGroups(
			userId, accountEntryId, types, statuses, supportEndDateGTDay,
			supportEndDateGTMonth, supportEndDateGTYear, supportEndDateLTDay,
			supportEndDateLTMonth, supportEndDateLTYear, params, andSearch,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public static List<OfferingEntryGroup> getOfferingEntryGroups(
			long userId, long accountEntryId, int[] types, int[] statuses,
			int supportEndDateGTDay, int supportEndDateGTMonth,
			int supportEndDateGTYear, int supportEndDateLTDay,
			int supportEndDateLTMonth, int supportEndDateLTYear,
			LinkedHashMap<String, Object> params, boolean andSearch, int start,
			int end)
		throws PortalException {

		List<OfferingEntry> offeringEntries =
			OfferingEntryLocalServiceUtil.search(
				userId, accountEntryId, types, statuses, supportEndDateGTDay,
				supportEndDateGTMonth, supportEndDateGTYear,
				supportEndDateLTDay, supportEndDateLTMonth,
				supportEndDateLTYear, params, andSearch, start, end,
				new OfferingEntryPKComparator(true));

		Map<String, OfferingEntryGroup> offeringEntryGroupMap =
			OfferingEntryGroupFactoryUtil.createOfferingEntryGroupMap(
				offeringEntries);

		return new ArrayList<>(offeringEntryGroupMap.values());
	}

	public static long[] getPartnerEntryIds(long partnerEntryId) {
		List<PartnerEntry> childPartnerEntries =
			PartnerEntryLocalServiceUtil.getChildPartnerEntries(
				partnerEntryId, true);

		long[] partnerEntryIds = new long[childPartnerEntries.size() + 1];

		partnerEntryIds[0] = partnerEntryId;

		for (int i = 0; i < childPartnerEntries.size(); i++) {
			PartnerEntry childPartnerEntry = childPartnerEntries.get(i);

			partnerEntryIds[i + 1] = childPartnerEntry.getPartnerEntryId();
		}

		return partnerEntryIds;
	}

	public static List<ListType> getPortalEnvListTypes(
		int envLFR, String envListType) {

		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(
			envListType);

		listTypes = ListUtil.copy(listTypes);

		int[] envListTypeIds = AccountEnvironmentConstants.getEnvListTypeIds(
			envLFR, envListType);

		Long[] listTypeIds = ArrayUtil.toLongArray(envListTypeIds);

		Iterator<ListType> itr = listTypes.iterator();

		while (itr.hasNext()) {
			ListType listType = itr.next();

			if (!ArrayUtil.contains(listTypeIds, listType.getListTypeId())) {
				itr.remove();
			}
		}

		return listTypes;
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

	public static String getPreferenceValue(Locale locale, String key) {
		return getPreferenceValue(getPortletPreferences(), locale, key);
	}

	public static String getPreferenceValue(
		PortletPreferences portletPreferences, Locale locale, String key) {

		if (portletPreferences == null) {
			portletPreferences = getPortletPreferences();
		}

		Map<Locale, String> valueMap = LocalizationUtil.getLocalizationMap(
			portletPreferences, key);

		String value = valueMap.get(locale);

		if (Validator.isNull(value)) {
			Locale defaultLocale = LocaleUtil.getDefault();

			value = valueMap.get(defaultLocale);
		}

		return value;
	}

	public static String getPreferenceValue(String key) {
		PortletPreferences portletPreferences = getPortletPreferences();

		return portletPreferences.getValue(key, StringPool.BLANK);
	}

	public static Map<Integer, List<SupportWorker>> getSupportWorkerMap(
		long supportTeamId) {

		Map<Integer, List<SupportWorker>> supportWorkerMap = new HashMap<>();

		List<SupportWorker> supportWorkers =
			SupportWorkerLocalServiceUtil.getTeamSupportWorkers(supportTeamId);

		for (SupportWorker supportWorker : supportWorkers) {
			User user = UserLocalServiceUtil.fetchUserById(
				supportWorker.getUserId());

			if (user == null) {
				continue;
			}

			int role = supportWorker.getRole();

			List<SupportWorker> supportWorkerList = supportWorkerMap.get(role);

			if (supportWorkerList == null) {
				supportWorkerList = new ArrayList<>();

				supportWorkerMap.put(role, supportWorkerList);
			}

			supportWorkerList.add(supportWorker);
		}

		return supportWorkerMap;
	}

	public static PortletPreferences getUserPreferences(long userId) {
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_USER;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = OSBPortletKeys.OSB_SUPPORT;
		String defaultPreferences = null;

		return PortletPreferencesLocalServiceUtil.getPreferences(
			OSBConstants.COMPANY_ID, userId, ownerType, plid, portletId,
			defaultPreferences);
	}

	public static boolean getUserPreferenceValue(long userId, String key) {
		PortletPreferences portletPreferences = getUserPreferences(userId);

		return GetterUtil.getBoolean(portletPreferences.getValue(key, null));
	}

	public static List<User> getUsers(long accountEntryId, int role)
		throws PortalException {

		List<AccountWorker> accountWorkers =
			AccountWorkerLocalServiceUtil.getAccountWorkers(
				accountEntryId, role);

		List<User> users = new ArrayList<>();

		for (AccountWorker accountWorker : accountWorkers) {
			try {
				users.add(
					UserLocalServiceUtil.getUser(accountWorker.getUserId()));
			}
			catch (NoSuchUserException nsue) {
			}
		}

		return users;
	}

	public static boolean hasAttachmentPreview(String fileName) {
		if (ArrayUtil.contains(
				PortletPropsValues.TICKET_ATTACHMENT_PREVIEW_EXTENSIONS,
				FileUtil.getExtension(fileName))) {

			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isWatcher(long accountEntryId, long userId) {
		try {
			AccountEntry accountEntry =
				AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

			if (accountEntry.getPartnerEntryId() > 0) {
				PartnerWorker partnerWorker =
					PartnerWorkerLocalServiceUtil.fetchPartnerWorker(
						userId, accountEntry.getPartnerEntryId());

				if ((partnerWorker != null) &&
					(partnerWorker.getRole() ==
						PartnerWorkerConstants.ROLE_WATCHER)) {

					return true;
				}
			}

			AccountCustomer accountCustomer =
				AccountCustomerLocalServiceUtil.fetchAccountCustomer(
					userId, accountEntryId);

			if ((accountCustomer != null) &&
				(accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_WATCHER)) {

				return true;
			}
		}
		catch (Exception e) {
		}

		return false;
	}

	public static String serialize(List<OrderEntry> orderEntries) {
		List<Map<String, Object>> orderEntriesList = new ArrayList<>();

		for (OrderEntry orderEntry : orderEntries) {
			Map<String, Object> orderEntryAttributes = getOrderEntryAttributes(
				orderEntry);

			orderEntriesList.add(orderEntryAttributes);
		}

		return JSONFactoryUtil.looseSerializeDeep(orderEntriesList);
	}

	public static String serialize(OrderEntry orderEntry) {
		Map<String, Object> orderEntryAttributes = getOrderEntryAttributes(
			orderEntry);

		return JSONFactoryUtil.looseSerializeDeep(orderEntryAttributes);
	}

	protected static List<FileRepository> getDefaultFileRepositories() {
		List<FileRepository> fileRepositoryList = new ArrayList<>();

		for (String fileRepositoryId : PortletPropsValues.FILE_REPOSITORY_IDS) {
			fileRepositoryList.add(getDefaultFileRepository(fileRepositoryId));
		}

		return fileRepositoryList;
	}

	protected static FileRepository getDefaultFileRepository(
		String fileRepositoryId) {

		if (Validator.isNull(fileRepositoryId)) {
			return null;
		}

		String host = PortletProps.get(
			PortletPropsKeys.FILE_REPOSITORY_HOST,
			new Filter(fileRepositoryId));
		String name = PortletProps.get(
			PortletPropsKeys.FILE_REPOSITORY_NAME,
			new Filter(fileRepositoryId));
		long[] supportRegionIds = GetterUtil.getLongValues(
			PortletProps.getArray(
				PortletPropsKeys.FILE_REPOSITORY_SUPPORT_REGION_IDS,
				new Filter(fileRepositoryId)));

		return new FileRepository(
			fileRepositoryId, name, host, supportRegionIds);
	}

	protected static Map<String, Object> getOrderEntryAttributes(
		OrderEntry orderEntry) {

		Map<String, Object> orderEntryAttributes =
			orderEntry.getModelAttributes();

		List<Map<String, Object>> offeringEntriesList = new ArrayList<>();

		List<OfferingEntry> offeringEntries = orderEntry.getOfferingEntries();

		for (OfferingEntry offeringEntry : offeringEntries) {
			offeringEntriesList.add(offeringEntry.getModelAttributes());
		}

		orderEntryAttributes.put("offeringEntries", offeringEntriesList);

		return orderEntryAttributes;
	}

	protected static String getUserAgent(HttpServletRequest request) {
		String userAgent = StringPool.BLANK;

		if (request == null) {
			return userAgent;
		}

		userAgent = String.valueOf(
			request.getAttribute(HttpHeaders.USER_AGENT));

		if (Validator.isNotNull(userAgent)) {
			return userAgent;
		}

		userAgent = request.getHeader(HttpHeaders.USER_AGENT);

		if (userAgent != null) {
			userAgent = StringUtil.toLowerCase(userAgent);
		}
		else {
			userAgent = StringPool.BLANK;
		}

		request.setAttribute(HttpHeaders.USER_AGENT, userAgent);

		return userAgent;
	}

	private static String _getEmailTemplate(
		String templateName, String defaultTemplateName) {

		ClassLoader portletClassLoader = SupportUtil.class.getClassLoader();

		String templateDirName = "com/liferay/osb/support/dependencies/";

		URL url = portletClassLoader.getResource(
			templateDirName + templateName);

		if (url != null) {
			return ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				templateDirName + templateName);
		}
		else {
			return ContentUtil.get(
				SupportUtil.class.getClassLoader(),
				templateDirName + defaultTemplateName);
		}
	}

}