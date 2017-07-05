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

import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
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
import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.TicketCommentConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketEntryDiscussion;
import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.model.TicketLink;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AccountEntryServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.osb.service.TicketAttachmentLocalServiceUtil;
import com.liferay.osb.service.TicketCommentLocalServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.service.TicketEntryServiceUtil;
import com.liferay.osb.service.TicketFeedbackLocalServiceUtil;
import com.liferay.osb.service.TicketLinkLocalServiceUtil;
import com.liferay.osb.service.TicketWorkerLocalServiceUtil;
import com.liferay.osb.support.util.parser.PlainTextParser;
import com.liferay.osb.support.util.parser.SimpleBBCodeParser;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.PortletPropsKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.OfferingEntryPKComparator;
import com.liferay.osb.util.comparator.TicketEntryCreateDateComparator;
import com.liferay.osb.util.comparator.TicketEntryDiscussionCreateDateComparator;
import com.liferay.osb.util.comparator.TicketEntryDisplayIdComparator;
import com.liferay.osb.util.comparator.TicketEntryDueDateComparator;
import com.liferay.osb.util.comparator.TicketEntryModifiedDateComparator;
import com.liferay.osb.util.comparator.TicketEntryStatusComparator;
import com.liferay.osb.util.comparator.TicketEntrySubjectComparator;
import com.liferay.osb.util.comparator.TicketEntryTicketWorkerComparator;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.util.ContentUtil;
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

	public static String createJIRAIssueURL(
			HttpServletRequest request, TicketEntry ticketEntry)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		StringBundler sb = new StringBundler(35);

		sb.append("https://issues.liferay.com/secure/");
		sb.append("CreateIssueDetails!init.jspa?pid=11172&assignee=-1");

		sb.append("&customfield_14827=");

		AccountEntry accountEntry = ticketEntry.getAccountEntry();

		sb.append(accountEntry.getCode());

		sb.append("&customfield_14834=");

		String encodedAccountEntryName = HttpUtil.encodeURL(
			accountEntry.getName(), true);

		encodedAccountEntryName = encodedAccountEntryName.replaceAll(
			StringPool.QUOTE, "%22");

		sb.append(encodedAccountEntryName);

		sb.append("&priority=");
		sb.append(5 - ticketEntry.getSeverity());

		sb.append("&description=");

		String encodedReproductionSteps = HttpUtil.encodeURL(
			ticketEntry.getReproductionSteps(), true);

		encodedReproductionSteps = encodedReproductionSteps.replaceAll(
			"\\n", "%0A");
		encodedReproductionSteps = encodedReproductionSteps.replaceAll(
			StringPool.QUOTE, "%22");

		sb.append(encodedReproductionSteps);

		sb.append("&duedate=");

		Date dueDate = ticketEntry.getDueDate();

		Format dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
			"dd/MMM/yy", themeDisplay.getTimeZone());

		sb.append(dateFormat.format(dueDate.getTime()));

		sb.append("&customfield_11126=");
		sb.append(dateFormat.format(ticketEntry.getCreateDate()));

		sb.append("&customfield_10731=");

		String encodedTicketEntryURL =
			HttpUtil.encodeURL(
				getFriendlyTicketEntryURL(
					request, ticketEntry.getTicketEntryId()), true);

		sb.append(encodedTicketEntryURL);

		SupportRegion supportRegion = ticketEntry.getSupportRegion();

		sb.append("&customfield_11523=");
		sb.append(getJIRARegion(supportRegion.getName()));

		if (getJIRAComponent(ticketEntry.getComponent()) > 0) {
			sb.append("&components=");
			sb.append(getJIRAComponent(ticketEntry.getComponent()));
		}

		if (getJIRAEnvLFR(ticketEntry.getEnvLFR()) > 0) {
			sb.append("&versions=");
			sb.append(getJIRAEnvLFR(ticketEntry.getEnvLFR()));
		}

		if (getJIRAEnvOS(ticketEntry.getEnvOS()) > 0) {
			sb.append("&customfield_13629=");
			sb.append(getJIRAEnvOS(ticketEntry.getEnvOS()));
		}

		if (getJIRAEnvJVM(ticketEntry.getEnvJVM()) > 0) {
			sb.append("&customfield_13630=");
			sb.append(getJIRAEnvJVM(ticketEntry.getEnvJVM()));
		}

		if (getJIRAEnvAS(ticketEntry.getEnvAS()) > 0) {
			sb.append("&customfield_13631=");
			sb.append(getJIRAEnvAS(ticketEntry.getEnvAS()));
		}

		if (getJIRAEnvDB(ticketEntry.getEnvDB()) > 0) {
			sb.append("&customfield_13632=");
			sb.append(getJIRAEnvDB(ticketEntry.getEnvDB()));
		}

		if (getJIRAEnvBrowser(ticketEntry.getEnvBrowser()) > 0) {
			sb.append("&customfield_13633=");
			sb.append(getJIRAEnvBrowser(ticketEntry.getEnvBrowser()));
		}

		if (getJIRAEnvCS(ticketEntry.getEnvCS()) > 0) {
			sb.append("&customfield_18128=");
			sb.append(getJIRAEnvCS(ticketEntry.getEnvCS()));
		}

		if (Validator.isNotNull(ticketEntry.getEnvSearch())) {
			int[] envSearches = StringUtil.split(
				ticketEntry.getEnvSearch(), StringPool.NEW_LINE, 0);

			for (int envSearch : envSearches) {
				sb.append("&customfield_18129=");
				sb.append(getJIRAEnvSearch(envSearch));
			}
		}

		return sb.toString();
	}

	public static String[] getAccountWorkerKeys(long accountEntryId)
		throws SystemException {

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
		throws PortalException, SystemException {

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
				"com/liferay/osb/support/dependencies/" +
					"action_information_body.tmpl"));

		return map;
	}

	public static String[] getAttachmentKeywords() throws SystemException {
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
		throws PortalException, SystemException {

		List<OfferingEntryGroup> availableOfferingEntryGroups =
			new ArrayList<OfferingEntryGroup> ();

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
				"com/liferay/osb/support/dependencies/" +
					"comment_game_plan_body.tmpl"));

		return map;
	}

	public static Map<Locale, String> getCommentTicketEntryInactiveMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "commentTicketEntryInactive");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/support/dependencies/" +
					"comment_ticket_entry_inactive_body.tmpl"));

		return map;
	}

	public static Map<Locale, String> getCommentTicketEntryNoticeMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "commentTicketEntryClosed");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/support/dependencies/" +
					"comment_ticket_entry_notice_body.tmpl"));

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
				"com/liferay/osb/support/dependencies/" +
					"email_account_entry_tier_body.tmpl"));

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
				"com/liferay/osb/support/dependencies/" +
					"email_account_entry_tier_subject.tmpl"));

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
				"com/liferay/osb/support/dependencies/" +
					"email_partner_feedback_reminder_body.tmpl"));

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
				"com/liferay/osb/support/dependencies/" +
					"email_partner_feedback_reminder_subject.tmpl"));

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
					"com/liferay/osb/support/dependencies/" +
						"email_team_feedback_body.tmpl"));

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
				"com/liferay/osb/support/dependencies/" +
					"email_team_feedback_subject.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailTicketEntryBodyMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailTicketEntryBody");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/support/dependencies/" +
					"email_ticket_entry_body.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailTicketEntryCommentMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailTicketEntryCommentTemplate");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/support/dependencies/" +
					"email_ticket_entry_comment.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailTicketEntryDueDateMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailTicketEntryDueDateTemplate");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/support/dependencies/" +
					"email_ticket_entry_due_date.tmpl"));

		return map;
	}

	public static Map<Locale, String> getEmailTicketEntrySubjectMap(
		PortletPreferences portletPreferences) {

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "emailTicketEntrySubject");

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(
			defaultLocale,
			ContentUtil.get(
				"com/liferay/osb/support/dependencies/" +
					"email_ticket_entry_subject.tmpl"));

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
				"com/liferay/osb/support/dependencies/" +
					"email_worker_feedback_body.tmpl"));

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
					"com/liferay/osb/support/dependencies/" +
						"email_worker_feedback_subject.tmpl"));

		return map;
	}

	public static String getEscalationDetails() throws SystemException {
		PortletPreferences portletPreferences = getPortletPreferences();

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			portletPreferences, "escalationDetails");

		Locale defaultLocale = LocaleUtil.getDefault();

		String escalationDetails = map.get(defaultLocale);

		if (Validator.isNull(escalationDetails)) {
			escalationDetails = ContentUtil.get(
				"com/liferay/osb/support/dependencies/" +
					"message_escalation_details_body.tmpl");
		}

		return escalationDetails;
	}

	public static List<FileRepository> getFileRepositories()
		throws SystemException {

		List<FileRepository> fileRepositoryList =
			new ArrayList<FileRepository> ();

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

	public static FileRepository getFileRepository(String fileRepositoryId)
		throws SystemException {

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

	public static FileRepository getFileRepository(TicketEntry ticketEntry)
		throws SystemException {

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
	}

	public static FileRepository getFirstActiveFileRepository(
			Set<String> fileRepositoryIdsSet)
		throws SystemException {

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

	public static String getFriendlySearchFilterURL(
			HttpServletRequest request, long searchFilterId)
		throws PortalException, SystemException {

		long customerPlid = PortalUtil.getPlidFromPortletId(
			OSBConstants.GROUP_CUSTOMER_ID, OSBPortletKeys.OSB_SUPPORT);

		PortletURL searchFilterURL = PortletURLFactoryUtil.create(
			request, OSBPortletKeys.OSB_SUPPORT, customerPlid,
			PortletRequest.RENDER_PHASE);

		searchFilterURL.setParameter("mvcPath", "/support/view.jsp");
		searchFilterURL.setParameter(
			"searchFilterId", String.valueOf(searchFilterId));
		searchFilterURL.setParameter("friendly", String.valueOf(Boolean.TRUE));

		return searchFilterURL.toString();
	}

	public static String getFriendlyTicketEntryURL(
			HttpServletRequest request, long ticketEntryId)
		throws PortalException, SystemException {

		long customerPlid = PortalUtil.getPlidFromPortletId(
			OSBConstants.GROUP_CUSTOMER_ID, OSBPortletKeys.OSB_SUPPORT);

		PortletURL ticketEntryURL = PortletURLFactoryUtil.create(
			request, OSBPortletKeys.OSB_SUPPORT, customerPlid,
			PortletRequest.RENDER_PHASE);

		ticketEntryURL.setParameter(
			"mvcPath", "/support/edit_ticket_entry.jsp");
		ticketEntryURL.setParameter(
			"ticketEntryId", String.valueOf(ticketEntryId));
		ticketEntryURL.setParameter("friendly", String.valueOf(Boolean.TRUE));

		return ticketEntryURL.toString();
	}

	public static String getHTML(String text) {
		return getHTML(text, TicketCommentConstants.FORMAT_PLAIN);
	}

	public static String getHTML(String text, String format) {
		text = HtmlUtil.escape(text);

		if (format.equals(TicketCommentConstants.FORMAT_BBCODE)) {
			text = _bbCodeParser.parse(text);
		}

		return _plainTextParser.parse(text);
	}

	public static String getHTML(TicketComment ticketComment) {
		return getHTML(ticketComment.getBody(), ticketComment.getFormat());
	}

	public static int getListTypeIdFromName(
			String type, String name, boolean translate)
		throws SystemException {

		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(type);

		for (ListType listType : listTypes) {
			String listTypeName = listType.getName();

			if (translate) {
				listTypeName = LanguageUtil.get(Locale.US, listTypeName);
			}

			if (listTypeName.equals(name)) {
				return listType.getListTypeId();
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
		throws PortalException, SystemException {

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
		throws PortalException, SystemException {

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

		return new ArrayList<OfferingEntryGroup>(
			offeringEntryGroupMap.values());
	}

	public static String getOtherAssigneesUserNames(long ticketEntryId)
		throws PortalException, SystemException {

		TicketWorker primaryTicketWorker =
			TicketWorkerLocalServiceUtil.fetchPrimaryTicketWorker(
				ticketEntryId);

		long[] ticketWorkerUserIds = getTicketWorkerUserIds(ticketEntryId);

		StringBundler sb = new StringBundler(
			(ticketWorkerUserIds.length * 2) - 1);

		for (int i = 0; i < ticketWorkerUserIds.length; i++) {
			if ((primaryTicketWorker != null) &&
				(primaryTicketWorker.getUserId() == ticketWorkerUserIds[i])) {

				continue;
			}

			User user = null;

			try {
				user = UserLocalServiceUtil.getUser(ticketWorkerUserIds[i]);
			}
			catch (NoSuchUserException nsue) {
				continue;
			}

			if (sb.index() > 0) {
				sb.append(StringPool.COMMA_AND_SPACE);
			}

			sb.append(HtmlUtil.escape(user.getFullName()));
		}

		return sb.toString();
	}

	public static long[] getPartnerEntryIds(long partnerEntryId)
		throws SystemException {

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
			int envLFR, String envListType)
		throws SystemException {

		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(
			envListType);

		listTypes = ListUtil.copy(listTypes);

		int[] listTypeIds = TicketEntryConstants.getEnvListTypeIds(
			envLFR, envListType);

		Iterator<ListType> itr = listTypes.iterator();

		while (itr.hasNext()) {
			ListType listType = itr.next();

			if (!ArrayUtil.contains(listTypeIds, listType.getListTypeId())) {
				itr.remove();
			}
		}

		return listTypes;
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

	public static String getPreferenceValue(Locale locale, String key)
		throws SystemException {

		return getPreferenceValue(getPortletPreferences(), locale, key);
	}

	public static String getPreferenceValue(
			PortletPreferences portletPreferences, Locale locale, String key)
		throws SystemException {

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

	public static String getPreferenceValue(String key) throws SystemException {
		PortletPreferences portletPreferences = getPortletPreferences();

		return portletPreferences.getValue(key, StringPool.BLANK);
	}

	public static String getPrimaryAssigneeUserName(long ticketEntryId)
		throws PortalException, SystemException {

		TicketWorker ticketWorker =
			TicketWorkerLocalServiceUtil.fetchPrimaryTicketWorker(
				ticketEntryId);

		if (ticketWorker == null) {
			return StringPool.BLANK;
		}

		try {
			User user = UserLocalServiceUtil.getUser(ticketWorker.getUserId());

			return HtmlUtil.escape(user.getFullName());
		}
		catch (Exception e) {
		}

		return StringPool.BLANK;
	}

	public static Map<Integer, List<SupportWorker>> getSupportWorkerMap(
			long supportTeamId)
		throws SystemException {

		Map<Integer, List<SupportWorker>> supportWorkerMap =
			new HashMap<Integer, List<SupportWorker>>();

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
				supportWorkerList = new ArrayList<SupportWorker>();

				supportWorkerMap.put(role, supportWorkerList);
			}

			supportWorkerList.add(supportWorker);
		}

		return supportWorkerMap;
	}

	public static TicketEntry getTicketEntry(String ticketDisplayId)
		throws PortalException, SystemException {

		Matcher matcher = _ticketDisplayIdPattern.matcher(ticketDisplayId);

		if (matcher.find()) {
			String code = matcher.group(1);

			AccountEntry accountEntry =
				AccountEntryServiceUtil.getAccountEntryByCode(code);

			long ticketId = GetterUtil.getLong(matcher.group(2));

			return TicketEntryServiceUtil.getTicketEntry(
				accountEntry.getAccountEntryId(), ticketId);
		}

		return null;
	}

	public static String[] getTicketEntryComments(
			long ticketEntryId, int visibility)
		throws SystemException {

		List<TicketComment> ticketComments =
			TicketCommentLocalServiceUtil.getTicketComments(
				ticketEntryId, new int[] {visibility},
				new int[] {WorkflowConstants.STATUS_APPROVED});

		String[] ticketEntryComments = new String[ticketComments.size()];

		for (int i = 0; i < ticketComments.size(); i++) {
			TicketComment ticketComment = ticketComments.get(i);

			ticketEntryComments[i] = ticketComment.getBody();
		}

		return ticketEntryComments;
	}

	public static List<TicketEntryDiscussion> getTicketEntryDiscussions(
			long userId, long ticketEntryId, int[] visibilities,
			boolean orderByAsc)
		throws SystemException {

		Map<String, TicketEntryDiscussion> ticketEntryDiscussionMap =
			new HashMap<String, TicketEntryDiscussion>();

		// Ticket attachments

		List<TicketAttachment> ticketAttachments =
			TicketAttachmentLocalServiceUtil.getTicketAttachments(
				ticketEntryId, visibilities, WorkflowConstants.STATUS_APPROVED);

		for (TicketAttachment ticketAttachment : ticketAttachments) {
			String key = ticketAttachment.getKey();

			TicketEntryDiscussion ticketEntryDiscussion =
				ticketEntryDiscussionMap.get(key);

			if (ticketEntryDiscussion != null) {
				ticketEntryDiscussion.addTicketAttachment(ticketAttachment);
			}
			else {
				ticketEntryDiscussion = new TicketEntryDiscussion();

				ticketEntryDiscussion.addTicketAttachment(ticketAttachment);

				ticketEntryDiscussionMap.put(key, ticketEntryDiscussion);
			}
		}

		// Ticket comments

		List<TicketComment> ticketComments =
			TicketCommentLocalServiceUtil.getTicketComments(
				ticketEntryId, visibilities,
				new int[] {WorkflowConstants.STATUS_APPROVED});

		for (TicketComment ticketComment : ticketComments) {
			String key = ticketComment.getKey();

			TicketEntryDiscussion ticketEntryDiscussion =
				ticketEntryDiscussionMap.get(key);

			if (ticketEntryDiscussion != null) {
				ticketEntryDiscussion.setTicketComment(ticketComment);
			}
			else {
				ticketEntryDiscussion = new TicketEntryDiscussion();

				ticketEntryDiscussion.setTicketComment(ticketComment);

				ticketEntryDiscussionMap.put(key, ticketEntryDiscussion);
			}
		}

		// Ticket links

		List<TicketLink> ticketLinks =
			TicketLinkLocalServiceUtil.getTicketLinks(
				ticketEntryId, visibilities);

		for (TicketLink ticketLink : ticketLinks) {
			String key = ticketLink.getKey();

			TicketEntryDiscussion ticketEntryDiscussion =
				ticketEntryDiscussionMap.get(key);

			if (ticketEntryDiscussion != null) {
				ticketEntryDiscussion.addTicketLink(ticketLink);
			}
			else {
				ticketEntryDiscussion = new TicketEntryDiscussion();

				ticketEntryDiscussion.addTicketLink(ticketLink);

				ticketEntryDiscussionMap.put(key, ticketEntryDiscussion);
			}
		}

		List<TicketEntryDiscussion> ticketEntryDiscussions =
			new ArrayList<TicketEntryDiscussion>(
				ticketEntryDiscussionMap.values());

		Collections.sort(
			ticketEntryDiscussions,
			new TicketEntryDiscussionCreateDateComparator(orderByAsc));

		if (visibilities.length != 1) {
			return ticketEntryDiscussions;
		}

		TicketComment draftTicketComment =
			TicketCommentLocalServiceUtil.fetchLastTicketComment(
				userId, ticketEntryId, visibilities[0],
				WorkflowConstants.STATUS_DRAFT, null);

		if (draftTicketComment != null) {
			TicketEntryDiscussion ticketEntryDiscussion =
				new TicketEntryDiscussion();

			ticketEntryDiscussion.setTicketComment(draftTicketComment);

			List<TicketAttachment> draftTicketAttachments =
				TicketAttachmentLocalServiceUtil.getTicketAttachments(
					userId, ticketEntryId, visibilities[0],
					WorkflowConstants.STATUS_DRAFT);

			for (TicketAttachment ticketAttachment : draftTicketAttachments) {
				ticketEntryDiscussion.addTicketAttachment(ticketAttachment);
			}

			if (orderByAsc) {
				ticketEntryDiscussions.add(ticketEntryDiscussion);
			}
			else {
				ticketEntryDiscussions.add(0, ticketEntryDiscussion);
			}
		}

		return ticketEntryDiscussions;
	}

	public static OrderByComparator getTicketEntryOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = null;

		if (orderByCol.equals("assignee")) {
			orderByComparator = new TicketEntryTicketWorkerComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("create-date")) {
			orderByComparator = new TicketEntryCreateDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("display-id")) {
			orderByComparator = new TicketEntryDisplayIdComparator(orderByAsc);
		}
		else if (orderByCol.equals("due-date")) {
			orderByComparator = new TicketEntryDueDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("modified-date")) {
			orderByComparator = new TicketEntryModifiedDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("status")) {
			orderByComparator = new TicketEntryStatusComparator(orderByAsc);
		}
		else if (orderByCol.equals("subject")) {
			orderByComparator = new TicketEntrySubjectComparator(orderByAsc);
		}
		else {
			orderByComparator = new TicketEntryCreateDateComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static long[] getTicketFeedbackUserIds(
			long ticketEntryId, int subject)
		throws PortalException, SystemException {

		TicketEntry ticketEntry = TicketEntryLocalServiceUtil.getTicketEntry(
			ticketEntryId);

		List<Long> ticketFeedbackUserIds = new ArrayList<Long>();

		if (subject == TicketFeedbackConstants.SUBJECT_LIFERAY) {
			List<AccountCustomer> accountCustomers =
				AccountCustomerLocalServiceUtil.getAccountCustomers(
					ticketEntry.getAccountEntryId());

			for (AccountCustomer accountCustomer : accountCustomers) {
				if (accountCustomer.getRole() !=
						AccountCustomerConstants.ROLE_DEVELOPER) {

					continue;
				}

				TicketFeedback ticketFeedback =
					TicketFeedbackLocalServiceUtil.fetchFirstOpenTicketFeedback(
						accountCustomer.getUserId(), ticketEntryId,
						TicketFeedbackConstants.SUBJECT_LIFERAY);

				if (ticketFeedback != null) {
					ticketFeedbackUserIds.add(accountCustomer.getUserId());
				}
			}
		}

		return ArrayUtil.toLongArray(ticketFeedbackUserIds);
	}

	public static long[] getTicketWorkerUserIds(long ticketEntryId)
		throws SystemException {

		List<TicketWorker> ticketWorkers =
			TicketWorkerLocalServiceUtil.getTicketWorkers(ticketEntryId);

		List<Long> ticketWorkerUserIds = new ArrayList<Long>();

		for (int i = 0; i < ticketWorkers.size(); i++) {
			TicketWorker ticketWorker = ticketWorkers.get(i);

			ticketWorkerUserIds.add(ticketWorker.getUserId());
		}

		return ArrayUtil.toArray(ticketWorkerUserIds.toArray(new Long[0]));
	}

	public static PortletPreferences getUserPreferences(long userId)
		throws SystemException {

		int ownerType = PortletKeys.PREFS_OWNER_TYPE_USER;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = OSBPortletKeys.OSB_SUPPORT;
		String defaultPreferences = null;

		return PortletPreferencesLocalServiceUtil.getPreferences(
			OSBConstants.COMPANY_ID, userId, ownerType, plid, portletId,
			defaultPreferences);
	}

	public static boolean getUserPreferenceValue(long userId, String key)
		throws SystemException {

		PortletPreferences portletPreferences = getUserPreferences(userId);

		return GetterUtil.getBoolean(portletPreferences.getValue(key, null));
	}

	public static List<User> getUsers(long accountEntryId, int role)
		throws PortalException, SystemException {

		List<AccountWorker> accountWorkers =
			AccountWorkerLocalServiceUtil.getAccountWorkers(
				accountEntryId, role);

		List<User> users = new ArrayList<User>();

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

	public static boolean isElasticsearchEnabled() {
		try {
			PortletPreferences preferences = getPortletPreferences();

			if (GetterUtil.getBoolean(
					preferences.getValue("elasticsearchEnabled", null))) {

				return true;
			}
		}
		catch (SystemException se) {
		}

		return false;
	}

	public static boolean isMobile(HttpServletRequest request) {
		String userAgent = getUserAgent(request);

		if (userAgent.contains("mobile") ||
			(userAgent.contains("android") && userAgent.contains("nexus"))) {

			return true;
		}

		return false;
	}

	public static boolean isVersion2CustomerAnnouncementEnabled() {
		try {
			PortletPreferences preferences = getPortletPreferences();

			if (GetterUtil.getBoolean(
					preferences.getValue(
						"support2CustomerAnnouncement", null))) {

				return true;
			}
		}
		catch (SystemException se) {
		}

		return false;
	}

	public static boolean isVersion2Enabled() {
		try {
			PortletPreferences preferences = getPortletPreferences();

			if (GetterUtil.getBoolean(
					preferences.getValue("support2Enabled", null))) {

				return true;
			}
		}
		catch (SystemException se) {
		}

		return false;
	}

	public static boolean isVersion2Enabled(long userId) {
		try {
			PortletPreferences preferences = getPortletPreferences();

			if (!GetterUtil.getBoolean(
					preferences.getValue("support2Enabled", null))) {

				return false;
			}

			if (OrganizationLocalServiceUtil.hasUserOrganization(
					userId, OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

				return true;
			}

			if (GetterUtil.getBoolean(
					preferences.getValue("support2Partners", null))) {

				List<PartnerWorker> partnerWorkers =
					PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(userId);

				if (!partnerWorkers.isEmpty()) {
					return true;
				}
			}

			String enabledPartners = GetterUtil.getString(
				preferences.getValue("support2EnabledPartners", null));

			String[] enabledPartnersArray = StringUtil.split(
				enabledPartners.toLowerCase());

			List<PartnerEntry> partnerEntries =
				PartnerEntryLocalServiceUtil.getUserPartnerEntries(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (PartnerEntry partnerEntry : partnerEntries) {
				String code = partnerEntry.getCode();

				if (ArrayUtil.contains(
						enabledPartnersArray, code.toLowerCase())) {

					return true;
				}
			}

			String enabledAccounts = GetterUtil.getString(
				preferences.getValue("support2EnabledAccounts", null));

			String[] enabledAccountsArray = StringUtil.split(
				enabledAccounts.toLowerCase());

			List<AccountEntry> accountEntries =
				AccountEntryLocalServiceUtil.getUserAccountEntries(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (AccountEntry accountEntry : accountEntries) {
				int tier = accountEntry.getTier();

				if (((tier == AccountEntryConstants.TIER_OEM) &&
					 GetterUtil.getBoolean(
						preferences.getValue(
							"support2OEMAccountTier", null))) ||
					((tier == AccountEntryConstants.TIER_PREMIER) &&
					 GetterUtil.getBoolean(
						preferences.getValue(
							"support2PremierAccountTier", null))) ||
					((tier == AccountEntryConstants.TIER_REGULAR) &&
					 GetterUtil.getBoolean(
						preferences.getValue(
							"support2RegularAccountTier", null))) ||
					((tier == AccountEntryConstants.TIER_STRATEGIC) &&
					 GetterUtil.getBoolean(
						preferences.getValue(
							"support2StrategicAccountTier", null)))) {

					return true;
				}

				String code = accountEntry.getCode();

				if (ArrayUtil.contains(
						enabledAccountsArray, code.toLowerCase())) {

					return true;
				}
			}
		}
		catch (SystemException se) {
		}

		return false;
	}

	public static boolean isVersion2LiferayAnnouncementEnabled() {
		try {
			PortletPreferences preferences = getPortletPreferences();

			if (GetterUtil.getBoolean(
					preferences.getValue(
						"support2LiferayAnnouncement", null))) {

				return true;
			}
		}
		catch (SystemException se) {
		}

		return false;
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

	public static String serialize(List<OrderEntry> orderEntries)
		throws SystemException {

		List<Map<String, Object>> orderEntriesList =
			new ArrayList<Map<String, Object>>();

		for (OrderEntry orderEntry : orderEntries) {
			Map<String, Object> orderEntryAttributes = getOrderEntryAttributes(
				orderEntry);

			orderEntriesList.add(orderEntryAttributes);
		}

		return JSONFactoryUtil.looseSerializeDeep(orderEntriesList);
	}

	public static String serialize(OrderEntry orderEntry)
		throws SystemException {

		Map<String, Object> orderEntryAttributes = getOrderEntryAttributes(
			orderEntry);

		return JSONFactoryUtil.looseSerializeDeep(orderEntryAttributes);
	}

	protected static List<FileRepository> getDefaultFileRepositories() {
		List<FileRepository> fileRepositoryList =
			new ArrayList<FileRepository>();

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

	protected static int getJIRAComponent(int component) {
		return GetterUtil.getInteger(_jiraComponentMapping.get(component));
	}

	protected static int getJIRAEnvAS(int envAS) {
		return GetterUtil.getInteger(_jiraEnvASMapping.get(envAS));
	}

	protected static int getJIRAEnvBrowser(int envBrowser) {
		return GetterUtil.getInteger(_jiraEnvBrowserMapping.get(envBrowser));
	}

	protected static int getJIRAEnvCS(int envCS) {
		return GetterUtil.getInteger(_jiraEnvCSMapping.get(envCS));
	}

	protected static int getJIRAEnvDB(int envDB) {
		return GetterUtil.getInteger(_jiraEnvDBMapping.get(envDB));
	}

	protected static int getJIRAEnvJVM(int envJVM) {
		return GetterUtil.getInteger(_jiraEnvJVMMapping.get(envJVM));
	}

	protected static int getJIRAEnvLFR(int envLFR) {
		return GetterUtil.getInteger(_jiraEnvLFRMapping.get(envLFR));
	}

	protected static int getJIRAEnvOS(int envOS) {
		return GetterUtil.getInteger(_jiraEnvOSMapping.get(envOS));
	}

	protected static int getJIRAEnvSearch(int envSearch) {
		return GetterUtil.getInteger(_jiraEnvSearchMapping.get(envSearch));
	}

	protected static String getJIRARegion(String supportRegionName) {
		return GetterUtil.getString(_jiraRegionMapping.get(supportRegionName));
	}

	protected static Map<String, Object> getOrderEntryAttributes(
			OrderEntry orderEntry)
		throws SystemException {

		Map<String, Object> orderEntryAttributes =
			orderEntry.getModelAttributes();

		List<Map<String, Object>> offeringEntriesList =
			new ArrayList<Map<String, Object>>();

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
			userAgent = userAgent.toLowerCase();
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
			return ContentUtil.get(templateDirName + templateName);
		}
		else {
			return ContentUtil.get(templateDirName + defaultTemplateName);
		}
	}

	private static final SimpleBBCodeParser _bbCodeParser =
		new SimpleBBCodeParser();
	private static final PlainTextParser _plainTextParser =
		new PlainTextParser();

	private static Map<Integer, Integer> _jiraComponentMapping =
		new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> _jiraEnvASMapping =
		new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> _jiraEnvBrowserMapping =
		new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> _jiraEnvCSMapping =
		new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> _jiraEnvDBMapping =
		new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> _jiraEnvJVMMapping =
		new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> _jiraEnvLFRMapping =
		new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> _jiraEnvOSMapping =
		new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> _jiraEnvSearchMapping =
		new HashMap<Integer, Integer>();
	private static Map<String, String> _jiraRegionMapping =
		new HashMap<String, String>();
	private static Pattern _ticketDisplayIdPattern = Pattern.compile(
		"^([a-zA-Z0-9]{1,15})-([0-9]+)$");

	static {
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_AUTHENTICATION, 11585);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_CALENDAR, 15150);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_CLUSTERING, 15012);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_COLLABORATION_SUITE, 15151);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_CUSTOM_DEVELOPMENT, 15095);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_LIFERAY_DEVELOPER_STUDIO, 15093);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_DOCUMENT_LIBRARY, 15166);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_LAR_STAGING, 15047);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_LIFERAY_FACES, 15090);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_LIFERAY_MOBILE_SDK, 18883);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_LIFERAY_SYNC, 18884);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_PATCH_MANAGEMENT, 18880);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_SEARCH_INDEXING, 11644);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_SECURITY, 11649);
		_jiraComponentMapping.put(TicketEntryConstants.COMPONENT_UI, 15019);
		_jiraComponentMapping.put(
			TicketEntryConstants.COMPONENT_WEB_CONTENT_MANAGEMENT, 15040);

		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_GLASSFISH_3, 13389);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_GLASSFISH_3_1, 13390);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_GLASSFISH_4_0, 13391);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_JBOSS_AS_7_1, 13396);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_JBOSS_EAP_5_1, 13397);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_JBOSS_EAP_6_0, 13398);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_JBOSS_EAP_6_1, 17717);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_JBOSS_EAP_6_2, 17720);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_JBOSS_EAP_6_3, 17721);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_JBOSS_EAP_6_4, 17718);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_JBOSS_EAP_7_0, 19111);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_JONAS_5_2, 13402);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_RESIN_4, 13410);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_TCAT_6_4, 13403);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_TCAT_7_0, 13404);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_TCSERVER_2_6, 13411);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_TCSERVER_2_9, 14413);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_TCSERVER_3_1, 18432);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_TCSERVER_3_2, 20510);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_TOMCAT_6_0, 13387);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_TOMCAT_7_0, 13388);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_TOMCAT_7_0_X, 14310);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_TOMCAT_8_0, 18413);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_WEBLOGIC_10_0, 13405);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_WEBLOGIC_10_3, 13406);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_WEBLOGIC_11_G, 13407);
		_jiraEnvASMapping.put(
			TicketEntryConstants.ENV_AS_WEBLOGIC_12C_R1, 13409);
		_jiraEnvASMapping.put(
			TicketEntryConstants.ENV_AS_WEBLOGIC_12C_R2, 18814);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_WEBSPHERE_6_1, 13392);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_WEBSPHERE_7_0, 13393);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_WEBSPHERE_8, 13394);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_WEBSPHERE_8_5, 13395);
		_jiraEnvASMapping.put(TicketEntryConstants.ENV_AS_WILDFILY_10_0, 18415);

		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_CHROME, 15642);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_EDGE, 18811);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_FIREFOX, 15643);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_FIREFOX_ESR_45, 18812);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_IE_6, 13321);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_IE_7, 13322);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_IE_8, 13323);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_IE_9, 13324);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_IE_10, 13325);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_IE_11, 14411);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_IOS_SAFARI, 18938);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_MOBILE_CHROME, 18937);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_SAFARI_5, 13326);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_SAFARI_6, 13327);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_SAFARI_8, 18428);
		_jiraEnvBrowserMapping.put(
			TicketEntryConstants.ENV_BROWSER_SAFARI_9, 18429);
		_jiraEnvBrowserMapping.put(TicketEntryConstants.ENV_BROWSER_OTHER, -1);

		_jiraEnvCSMapping.put(
			TicketEntryConstants.ENV_CS_AWS_ELASTIC_COMPUTE_CLOUD, 18940);
		_jiraEnvCSMapping.put(
			TicketEntryConstants.ENV_CS_AWS_RELATIONAL_DATABASE_SERVICE, 18941);
		_jiraEnvCSMapping.put(TicketEntryConstants.ENV_CS_AWS_S3, 18942);
		_jiraEnvCSMapping.put(TicketEntryConstants.ENV_CS_AZURE_FILES, 18943);
		_jiraEnvCSMapping.put(
			TicketEntryConstants.ENV_CS_AZURE_SQL_DATABASES, 18944);
		_jiraEnvCSMapping.put(
			TicketEntryConstants.ENV_CS_AZURE_VIRTUAL_MACHINES, 18945);

		_jiraEnvDBMapping.put(TicketEntryConstants.ENV_DB_DB2_9_7, 13358);
		_jiraEnvDBMapping.put(TicketEntryConstants.ENV_DB_DB2_10_1, 13359);
		_jiraEnvDBMapping.put(TicketEntryConstants.ENV_DB_MARIADB_10, 17711);
		_jiraEnvDBMapping.put(TicketEntryConstants.ENV_DB_MYSQL_5_0, 13366);
		_jiraEnvDBMapping.put(TicketEntryConstants.ENV_DB_MYSQL_5_1, 13367);
		_jiraEnvDBMapping.put(TicketEntryConstants.ENV_DB_MYSQL_5_5, 13368);
		_jiraEnvDBMapping.put(TicketEntryConstants.ENV_DB_MYSQL_5_6, 13369);
		_jiraEnvDBMapping.put(TicketEntryConstants.ENV_DB_MYSQL_5_7, 18424);
		_jiraEnvDBMapping.put(TicketEntryConstants.ENV_DB_ORACLE_10G, 13370);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_ORACLE_10G_RELEASE_2, 13371);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_ORACLE_11G_RELEASE_1, 13372);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_ORACLE_11G_RELEASE_2, 13373);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_ORACLE_12C_RELEASE_1, 17712);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_POSTGRESQL_8_4, 13374);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_POSTGRESQL_9_0, 13375);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_POSTGRESQL_9_1, 13376);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_POSTGRESQL_9_2, 13377);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_POSTGRESQL_9_3, 17713);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_POSTGRESQL_9_4, 18813);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_SQL_SERVER_2005, 13379);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_SQL_SERVER_2008, 13380);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_SQL_SERVER_2008_R2, 13381);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_SQL_SERVER_2012, 13382);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_SQL_SERVER_2014, 17714);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_SYBASE_ASE_15_5, 13383);
		_jiraEnvDBMapping.put(
			TicketEntryConstants.ENV_DB_SYBASE_ASE_15_7, 13384);
		_jiraEnvDBMapping.put(TicketEntryConstants.ENV_DB_SYBASE_ASE_16, 18412);

		_jiraEnvJVMMapping.put(TicketEntryConstants.ENV_JVM_IBM_JDK_6, 13441);
		_jiraEnvJVMMapping.put(TicketEntryConstants.ENV_JVM_IBM_JDK_7, 13442);
		_jiraEnvJVMMapping.put(TicketEntryConstants.ENV_JVM_IBM_JDK_8, 18939);
		_jiraEnvJVMMapping.put(TicketEntryConstants.ENV_JVM_JAVA_5, 14921);
		_jiraEnvJVMMapping.put(TicketEntryConstants.ENV_JVM_JAVA_6, 13439);
		_jiraEnvJVMMapping.put(TicketEntryConstants.ENV_JVM_JAVA_7, 13440);
		_jiraEnvJVMMapping.put(TicketEntryConstants.ENV_JVM_JAVA_8, 17322);
		_jiraEnvJVMMapping.put(
			TicketEntryConstants.ENV_JVM_JROCKET_JDK_6, 13443);

		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_5_1_3, 10432);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_5_1_4, 10431);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_5_1_5, 10430);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_5_1_6, 10452);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_5_1_7, 10531);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_5_1_8, 10614);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_5_2_4, 10429);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_5_2_5, 10428);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_5_2_6, 10451);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_5_2_7, 10468);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_5_2_8, 10490);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_5_2_9, 10615);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_6_0_10, 10641);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_6_0_11, 10713);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_6_0_12, 10792);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_6_1_10, 11610);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_6_1_20, 12522);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_6_1_30, 14611);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.PORTAL_VERSION_6_2_10, 15816);
		_jiraEnvLFRMapping.put(
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10, 23334);

		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_AIX_6_1, 13417);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_AIX_7_1, 13418);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_CENTOS_5, 13414);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_CENTOS_6, 13415);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_CENTOS_7, 18417);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_DEBIAN_6_0, 17716);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_DEBIAN_7, 18418);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_DEBIAN_8, 18419);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_HP_UX, 13416);
		_jiraEnvOSMapping.put(
			TicketEntryConstants.ENV_OS_MAC_OS_X_10_5_PLUS, 13419);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_OPENSUSE_13_1, 13423);
		_jiraEnvOSMapping.put(
			TicketEntryConstants.ENV_OS_ORACLE_LINUX_6, 13424);
		_jiraEnvOSMapping.put(
			TicketEntryConstants.ENV_OS_ORACLE_LINUX_7, 18420);
		_jiraEnvOSMapping.put(
			TicketEntryConstants.ENV_OS_RED_HAT_ENTERPRISE_5, 13427);
		_jiraEnvOSMapping.put(
			TicketEntryConstants.ENV_OS_RED_HAT_ENTERPRISE_6, 13428);
		_jiraEnvOSMapping.put(
			TicketEntryConstants.ENV_OS_RED_HAT_ENTERPRISE_7, 17710);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_SOLARIS_10, 13425);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_SOLARIS_11, 17715);
		_jiraEnvOSMapping.put(
			TicketEntryConstants.ENV_OS_SUSE_ENTERPRISE_LINUX_11, 13429);
		_jiraEnvOSMapping.put(
			TicketEntryConstants.ENV_OS_SUSE_ENTERPRISE_LINUX_12, 18421);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_UBUNTU_10, 13430);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_UBUNTU_11, 13431);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_UBUNTU_12, 13432);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_UBUNTU_13, 13433);
		_jiraEnvOSMapping.put(
			TicketEntryConstants.ENV_OS_UBUNTU_LTS_14_04, 18422);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_WINDOWS_7, 13435);
		_jiraEnvOSMapping.put(
			TicketEntryConstants.ENV_OS_WINDOWS_SERVER_2008, 13437);
		_jiraEnvOSMapping.put(
			TicketEntryConstants.ENV_OS_WINDOWS_SERVER_2012, 13438);
		_jiraEnvOSMapping.put(TicketEntryConstants.ENV_OS_WINDOWS_XP, 13434);

		_jiraEnvSearchMapping.put(
			TicketEntryConstants.ENV_SEARCH_ELASTICSEARCH, 18946);
		_jiraEnvSearchMapping.put(
			TicketEntryConstants.ENV_SEARCH_KIBANA_4_4, 18947);
		_jiraEnvSearchMapping.put(
			TicketEntryConstants.ENV_SEARCH_MARVEL_2_2, 18948);
		_jiraEnvSearchMapping.put(
			TicketEntryConstants.ENV_SEARCH_SHIELD_2_2, 18949);
		_jiraEnvSearchMapping.put(TicketEntryConstants.ENV_SEARCH_SOLR, 18950);
		_jiraEnvSearchMapping.put(
			TicketEntryConstants.ENV_SEARCH_SOLRCLOUD, 18951);

		_jiraRegionMapping.put("Australia", "11415");
		_jiraRegionMapping.put("Brazil", "11418");
		_jiraRegionMapping.put("China", "11415");
		_jiraRegionMapping.put("Hungary", "11416");
		_jiraRegionMapping.put("India", "11440");
		_jiraRegionMapping.put("Japan", "16511");
		_jiraRegionMapping.put("Spain", "11417");
		_jiraRegionMapping.put("US", "11414");
	}

}