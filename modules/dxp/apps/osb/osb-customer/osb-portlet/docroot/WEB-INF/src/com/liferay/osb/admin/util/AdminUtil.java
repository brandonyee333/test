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
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingCustomerConstants;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.TrainingLinkedUser;
import com.liferay.osb.model.UserProfile;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.TrainingEventLocalServiceUtil;
import com.liferay.osb.service.TrainingLinkedUserLocalServiceUtil;
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

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

/**
 * @author Peter Shin
 * @author Alan Zhang
 */
public class AdminUtil {

	public static boolean equalsByUserProfile(Object object1, Object object2) {
		JSONObject jsonObject1 = toUserProfileJSONObject(object1);
		JSONObject jsonObject2 = toUserProfileJSONObject(object2);

		if (jsonObject1.length() != jsonObject2.length()) {
			return false;
		}

		Iterator<String> iterator = jsonObject1.keys();

		while (iterator.hasNext()) {
			String key = iterator.next();

			String value = jsonObject1.getString(key);

			if (!value.equals(jsonObject2.getString(key))) {
				return false;
			}
		}

		return true;
	}

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

	public static Map<Locale, String> getEmailTrainingCertificateBodyMap(
			long classNameId, long classPK, PortletPreferences preferences)
		throws PortalException, SystemException {

		Map<Locale, String> map = new HashMap<Locale, String>();

		String templateName = StringPool.BLANK;

		if (classNameId == PortalUtil.getClassNameId(TrainingEvent.class)) {
			map = LocalizationUtil.getLocalizationMap(
				preferences, "emailTrainingEventCertificateBody");

			TrainingEvent trainingEvent =
				TrainingEventLocalServiceUtil.getTrainingEvent(classPK);

			templateName =
				"email_training_event_certificate_body_" +
					trainingEvent.getLanguageId() + ".tmpl";
		}
		else if (classNameId ==
					PortalUtil.getClassNameId(TrainingExamResult.class)) {

			map = LocalizationUtil.getLocalizationMap(
				preferences, "emailTrainingExamCertificateBody");

			templateName = "email_training_exam_certificate_body.tmpl";
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			_getEmailTemplate(
				templateName,
				"email_training_event_certificate_body_en_US.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailTrainingCertificateSubjectMap(
			long classNameId, long classPK, PortletPreferences preferences)
		throws PortalException, SystemException {

		Map<Locale, String> map = new HashMap<Locale, String>();

		String templateName = StringPool.BLANK;

		if (classNameId == PortalUtil.getClassNameId(TrainingEvent.class)) {
			map = LocalizationUtil.getLocalizationMap(
				preferences, "emailTrainingEventCertificateSubject");

			TrainingEvent trainingEvent =
				TrainingEventLocalServiceUtil.getTrainingEvent(classPK);

			templateName =
				"email_training_event_certificate_subject_" +
					trainingEvent.getLanguageId() + ".tmpl";
		}
		else if (classNameId ==
					PortalUtil.getClassNameId(TrainingExamResult.class)) {

			map = LocalizationUtil.getLocalizationMap(
				preferences, "emailTrainingExamCertificateSubject");

			templateName = "email_training_exam_certificate_subject.tmpl";
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			_getEmailTemplate(
				templateName,
				"email_training_event_certificate_subject_en_US.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailTrainingExamResultFailedBodyMap(
			PortletPreferences preferences)
		throws PortalException, SystemException {

		Map<Locale, String> map = new HashMap<Locale, String>();

		map = LocalizationUtil.getLocalizationMap(
			preferences, "emailTrainingExamResultFailedBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/admin/dependencies/" +
					"email_training_exam_result_failed_body.tmpl"));

		return map;
	}

	public static Map<Locale, String>
		getEmailTrainingExamResultFailedSubjectMap(
			PortletPreferences preferences)
		throws PortalException, SystemException {

		Map<Locale, String> map = new HashMap<Locale, String>();

		map = LocalizationUtil.getLocalizationMap(
			preferences, "emailTrainingExamResultFailedSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/admin/dependencies/" +
					"email_training_exam_result_failed_subject.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailTrainingExamResultPassedBodyMap(
			PortletPreferences preferences)
		throws PortalException, SystemException {

		Map<Locale, String> map = new HashMap<Locale, String>();

		map = LocalizationUtil.getLocalizationMap(
			preferences, "emailTrainingExamResultPassedBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/admin/dependencies/" +
					"email_training_exam_result_passed_body.tmpl"));

		return map;
	}

	public static Map<Locale, String>
		getEmailTrainingExamResultPassedNewUserBodyMap(
			PortletPreferences preferences)
		throws PortalException, SystemException {

		Map<Locale, String> map = new HashMap<Locale, String>();

		map = LocalizationUtil.getLocalizationMap(
			preferences, "emailTrainingExamResultPassedNewUserBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/admin/dependencies/" +
					"email_training_exam_result_passed_new_user_body.tmpl"));

		return map;
	}

	public static Map<Locale, String>
		getEmailTrainingExamResultPassedSubjectMap(
			PortletPreferences preferences)
		throws PortalException, SystemException {

		Map<Locale, String> map = new HashMap<Locale, String>();

		map = LocalizationUtil.getLocalizationMap(
			preferences, "emailTrainingExamResultPassedSubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/admin/dependencies/" +
					"email_training_exam_result_passed_subject.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailTrainingSurveyBodyMap(
			long classNameId, long classPK, PortletPreferences preferences)
		throws PortalException, SystemException {

		Map<Locale, String> map = new HashMap<Locale, String>();

		String templateName = StringPool.BLANK;

		if (classNameId == PortalUtil.getClassNameId(TrainingEvent.class)) {
			map = LocalizationUtil.getLocalizationMap(
				preferences, "emailTrainingEventSurveyBody");

			TrainingEvent trainingEvent =
				TrainingEventLocalServiceUtil.getTrainingEvent(classPK);

			if (LocaleUtil.equals(
					Locale.US,
					LocaleUtil.fromLanguageId(
						trainingEvent.getLanguageId(), false))) {

				templateName =
					"email_training_event_participant_form_body_" +
						trainingEvent.getLanguageId() + ".tmpl";
			}
			else {
				templateName =
					"email_training_event_survey_body_" +
						trainingEvent.getLanguageId() + ".tmpl";
			}
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			_getEmailTemplate(
				templateName, "email_training_event_survey_body_en_US.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailTrainingSurveySubjectMap(
			long classNameId, long classPK, PortletPreferences preferences)
		throws PortalException, SystemException {

		Map<Locale, String> map = new HashMap<Locale, String>();

		String templateName = StringPool.BLANK;

		if (classNameId == PortalUtil.getClassNameId(TrainingEvent.class)) {
			map = LocalizationUtil.getLocalizationMap(
				preferences, "emailTrainingEventSurveySubject");

			TrainingEvent trainingEvent =
				TrainingEventLocalServiceUtil.getTrainingEvent(classPK);

			if (LocaleUtil.equals(
					Locale.US,
					LocaleUtil.fromLanguageId(
						trainingEvent.getLanguageId(), false))) {

				templateName =
					"email_training_event_participant_form_subject_" +
						trainingEvent.getLanguageId() + ".tmpl";
			}
			else {
				templateName =
					"email_training_event_survey_subject_" +
						trainingEvent.getLanguageId() + ".tmpl";
			}
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			_getEmailTemplate(
				templateName,
				"email_training_event_survey_subject_en_US.tmpl"));

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

	public static String getGeneratedEmailAddress(long trainingCustomerId) {
		return StringUtil.valueOf(trainingCustomerId).concat(
			TrainingCustomerConstants.EMAIL_ADDRESS_SUFFIX);
	}

	public static String getGeneratedScreenName(long trainingCustomerId) {
		return TrainingCustomerConstants.SCREEN_NAME_PREFIX.concat(
			StringUtil.valueOf(trainingCustomerId));
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

	public static String getTrainingCustomerType(User user) {
		if (user != null) {
			String screenName = user.getScreenName();

			if (screenName.startsWith(
					TrainingCustomerConstants.SCREEN_NAME_PREFIX)) {

				return TrainingCustomerConstants.TYPE_TRAINING_USER;
			}
			else {
				return TrainingCustomerConstants.TYPE_REGULAR_USER;
			}
		}
		else {
			return StringPool.BLANK;
		}
	}

	public static List<User> getTrainingLinkedUserUsers(long userId)
		throws PortalException, SystemException {

		TrainingLinkedUser trainingLinkedUser =
			TrainingLinkedUserLocalServiceUtil.fetchUserTrainingLinkedUser(
				userId);

		if (trainingLinkedUser == null) {
			User user = UserLocalServiceUtil.getUser(userId);

			List<User> users = new ArrayList<User>(1);

			users.add(user);

			return users;
		}

		List<TrainingLinkedUser> trainingLinkedUsers =
			TrainingLinkedUserLocalServiceUtil.getTrainingLinkedUsers(
				trainingLinkedUser.getPrimaryUserId());

		List<User> users = new ArrayList<User>(trainingLinkedUsers.size());

		for (TrainingLinkedUser curTrainingLinkedUser : trainingLinkedUsers) {
			users.add(curTrainingLinkedUser.getUser());
		}

		return users;
	}

	public static String getUserProfileDisplayHTML(Object object) {
		return getUserProfileDisplayHTML(object, "<br />");
	}

	public static String getUserProfileDisplayHTML(
		Object object, String delimiter) {

		JSONObject jsonObject = toUserProfileJSONObject(object);

		StringBundler sb = new StringBundler(5);

		String fullName = jsonObject.getString("fullName");

		sb.append(HtmlUtil.escape(fullName));

		String emailAddress = jsonObject.getString("emailAddress");

		if (Validator.isNotNull(emailAddress)) {
			sb.append(delimiter);
			sb.append(HtmlUtil.escape(emailAddress));
		}

		String legalEntityName = jsonObject.getString("legalEntityName");

		if (Validator.isNotNull(legalEntityName)) {
			sb.append(delimiter);
			sb.append(HtmlUtil.escape(legalEntityName));
		}

		return sb.toString();
	}

	public static List<String> getUserProfilesDisplayHTML(
		List<Object> objects, String delimiter) {

		Set<String> userProfilesDisplay = new HashSet<String>();

		for (Object object : objects) {
			userProfilesDisplay.add(
				getUserProfileDisplayHTML(object, delimiter));
		}

		return new ArrayList<String>(userProfilesDisplay);
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
		else if (portletId.equals(OSBPortletKeys.OSB_ADMIN)) {
			List<TrainingCustomer> trainingCustomers =
				TrainingCustomerLocalServiceUtil.getTrainingCustomers(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			Indexer indexer = IndexerRegistryUtil.getIndexer(User.class);

			for (TrainingCustomer trainingCustomer : trainingCustomers) {
				indexer.reindex(trainingCustomer.getUser());
			}
		}
	}

	public static JSONObject toUserProfileJSONObject(Object object) {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (object instanceof UserProfile) {
			UserProfile userProfile = (UserProfile)object;

			jsonObject.put("emailAddress", userProfile.getEmailAddress());
			jsonObject.put("fullName", userProfile.getFullName());
			jsonObject.put("legalEntityName", userProfile.getLegalEntityName());
		}
		else if (object instanceof UserProfileHistory) {
			UserProfileHistory userProfileHistory = (UserProfileHistory)object;

			jsonObject.put(
				"emailAddress", userProfileHistory.getEmailAddress());
			jsonObject.put("fullName", userProfileHistory.getFullName());
			jsonObject.put(
				"legalEntityName", userProfileHistory.getLegalEntityName());
		}

		return jsonObject;
	}

	private static String _getEmailTemplate(
		String templateName, String defaultTemplateName) {

		ClassLoader portletClassLoader = AdminUtil.class.getClassLoader();

		String templateDirName = "com/liferay/osb/admin/dependencies/";

		URL url = portletClassLoader.getResource(
			templateDirName + templateName);

		if (url != null) {
			return ContentUtil.get(templateDirName + templateName);
		}
		else {
			return ContentUtil.get(templateDirName + defaultTemplateName);
		}
	}

}