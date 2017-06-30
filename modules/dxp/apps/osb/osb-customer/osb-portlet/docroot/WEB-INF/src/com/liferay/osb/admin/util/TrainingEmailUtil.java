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

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.TrainingCertificateTemplate;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingCustomerConstants;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingExam;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.TrainingLinkedUser;
import com.liferay.osb.service.ContractAuditLocalServiceUtil;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.TrainingExamResultLocalServiceUtil;
import com.liferay.osb.service.TrainingLinkedUserLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.OSBSubscriptionSender;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.User;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.util.ContentUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Douglas Wong
 * @author Joan Kim
 * @author Haote Chou
 */
public class TrainingEmailUtil {

	public static Map<Locale, String> getLocalizationMap(String name)
		throws SystemException {

		PortletPreferences preferences = getPortletPreferences();

		Map<Locale, String> map = LocalizationUtil.getLocalizationMap(
			preferences, name);

		Locale defaultLocale = LocaleUtil.getDefault();

		String defaultValue = map.get(defaultLocale);

		if (Validator.isNotNull(defaultValue)) {
			return map;
		}

		map.put(defaultLocale, ContentUtil.get(getFileName(name)));

		return map;
	}

	public static PortletPreferences getPortletPreferences()
		throws SystemException {

		return PortletPreferencesLocalServiceUtil.getPreferences(
			OSBConstants.COMPANY_ID, OSBConstants.COMPANY_ID,
			PortletKeys.PREFS_OWNER_TYPE_COMPANY, PortletKeys.PREFS_PLID_SHARED,
			OSBPortletKeys.OSB_ADMIN, null);
	}

	public static void sendTrainingExamResultAgreementReminderMail(
			User user, TrainingExamResult trainingExamResult)
		throws Exception {

		Map<Locale, String> subjectMap = getLocalizationMap(
			"emailTrainingExamResultPassedReminderSubject");
		Map<Locale, String> bodyMap = getLocalizationMap(
			"emailTrainingExamResultPassedReminderBody");

		OSBSubscriptionSender subscriptionSender = new OSBSubscriptionSender();

		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);

		TrainingCertificateTemplate trainingCertificateTemplate =
			trainingExamResult.getTrainingCertificateTemplate();

		subscriptionSender.setContextAttributes(
			"[$CUSTOMER_FULL_NAME$]", user.getFullName(),
			"[$TRAINING_CERTIFICATE_TEMPLATE_NAME$]",
			trainingCertificateTemplate.getName());

		subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"training_exam_result",
			trainingExamResult.getTrainingExamResultId());
		subscriptionSender.setUserId(user.getUserId());

		subscriptionSender.addRuntimeSubscribers(
			user.getEmailAddress(), user.getFullName());

		subscriptionSender.flushNotificationsAsync();
	}

	public static void sendTrainingExamResultImportFailureMail(
			List<String> failedKyterionExamIds)
		throws Exception {

		Map<Locale, String> subjectMap = getLocalizationMap(
			"emailTrainingExamResultImportFailureSubject");
		Map<Locale, String> bodyMap = getLocalizationMap(
			"emailTrainingExamResultImportFailureBody");

		OSBSubscriptionSender subscriptionSender = new OSBSubscriptionSender();

		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);

		StringBundler sb = new StringBundler(failedKyterionExamIds.size() * 4);

		for (String failedKyterionExamId : failedKyterionExamIds) {
			sb.append("<li>Registration ID: ");
			sb.append(failedKyterionExamId);
			sb.append("</li>");
		}

		subscriptionSender.setContextAttribute(
			"[$FAILED_KYTERION_EXAM_IDS$]", sb.toString(), false);

		subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
		subscriptionSender.setGroupId(OSBConstants.GROUP_GLOBAL_ID);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyyMMddHHmmss");

		subscriptionSender.setMailId(
			"training_exam_result_import_failure",
			dateFormat.format(new Date()));

		subscriptionSender.setPortletId(OSBPortletKeys.OSB_ADMIN);
		subscriptionSender.setUserId(OSBConstants.USER_DEFAULT_USER_ID);

		String toAddress =
			PortletPropsValues.TRAINING_CERTIFICATION_EMAIL_ADDRESS;

		if (Validator.isNull(toAddress)) {
			return;
		}

		subscriptionSender.addRuntimeSubscribers(
			toAddress, "Certification Team");

		subscriptionSender.flushNotificationsAsync();
	}

	public static void sendTrainingLinkedUserPrimaryUserMail(long primaryUserId)
		throws PortalException, SystemException {

		List<TrainingLinkedUser> trainingLinkedUsers =
			TrainingLinkedUserLocalServiceUtil.getTrainingLinkedUsers(
				primaryUserId);

		if (trainingLinkedUsers.isEmpty()) {
			return;
		}

		TrainingLinkedUser trainingLinkedUser = trainingLinkedUsers.get(0);

		Map<Locale, String> subjectMap = getLocalizationMap(
			"emailTrainingLinkedUserPrimaryUserSubject");
		Map<Locale, String> bodyMap = getLocalizationMap(
			"emailTrainingLinkedUserPrimaryUserBody");

		OSBSubscriptionSender subscriptionSender = new OSBSubscriptionSender();

		User primaryUser = trainingLinkedUser.getPrimaryUser();

		subscriptionSender.setCompanyId(primaryUser.getCompanyId());
		subscriptionSender.setContextAttribute(
			"[$PRIMARY_USER_FULL_NAME$]", primaryUser.getFullName());

		StringBundler sb = new StringBundler(
			(trainingLinkedUsers.size() - 1) * 2);

		for (TrainingLinkedUser curTrainingLinkedUser : trainingLinkedUsers) {
			if (curTrainingLinkedUser.isPrimaryUser()) {
				continue;
			}

			User user = curTrainingLinkedUser.getUser();

			sb.append(user.getEmailAddress());
			sb.append("<br />");
		}

		subscriptionSender.setContextAttribute(
			"[$TRAINING_USER_EMAIL_ADDRESSES$]", sb.toString(), false);

		subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId("primary_user", primaryUserId);
		subscriptionSender.setUserId(primaryUser.getUserId());

		subscriptionSender.addRuntimeSubscribers(
			primaryUser.getEmailAddress(), primaryUser.getFullName());

		subscriptionSender.flushNotificationsAsync();
	}

	public static void sendTrainingLinkedUserTrainingUsersMail(
			long primaryUserId)
		throws PortalException, SystemException {

		List<TrainingLinkedUser> trainingLinkedUsers =
			TrainingLinkedUserLocalServiceUtil.getTrainingLinkedUsers(
				primaryUserId);

		if (trainingLinkedUsers.isEmpty()) {
			return;
		}

		TrainingLinkedUser trainingLinkedUser = trainingLinkedUsers.get(0);

		Map<Locale, String> subjectMap = getLocalizationMap(
			"emailTrainingLinkedUserTrainingUsersSubject");
		Map<Locale, String> bodyMap = getLocalizationMap(
			"emailTrainingLinkedUserTrainingUsersBody");

		OSBSubscriptionSender subscriptionSender = new OSBSubscriptionSender();

		User primaryUser = trainingLinkedUser.getPrimaryUser();

		subscriptionSender.setCompanyId(primaryUser.getCompanyId());
		subscriptionSender.setContextAttribute(
			"[$PRIMARY_USER_EMAIL_ADDRESS$]", primaryUser.getEmailAddress());

		subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId("primary_user", primaryUserId);
		subscriptionSender.setUserId(trainingLinkedUser.getUserId());

		for (TrainingLinkedUser curTrainingLinkedUser : trainingLinkedUsers) {
			if (curTrainingLinkedUser.isPrimaryUser()) {
				continue;
			}

			User user = curTrainingLinkedUser.getUser();

			subscriptionSender.setContextAttribute(
				"[$TRAINING_USER_FULL_NAME$]", user.getFullName());

			subscriptionSender.addRuntimeSubscribers(
				user.getEmailAddress(), user.getFullName());
		}

		subscriptionSender.flushNotificationsAsync();
	}

	public static void sendTrainingSurveyReminderMail(
			TrainingEvent trainingEvent)
		throws Exception {

		Map<Locale, String> subjectMap = getLocalizationMap(
			"emailTrainingEventParticipantFormReminderSubject");
		Map<Locale, String> bodyMap = getLocalizationMap(
			"emailTrainingEventParticipantFormReminderBody");

		List<TrainingCustomer> trainingCustomers =
			TrainingCustomerLocalServiceUtil.getTrainingCustomers(
				PortalUtil.getClassNameId(TrainingEvent.class.getName()),
				trainingEvent.getTrainingEventId(),
				new int[] {
					TrainingCustomerConstants.
						STATUS_PENDING_PARTICIPANT_FORM_COMPLETION
				});

		for (TrainingCustomer trainingCustomer : trainingCustomers) {
			User user = trainingCustomer.getUser();

			OSBSubscriptionSender subscriptionSender =
				new OSBSubscriptionSender();

			subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);
			subscriptionSender.setContextAttributes(
				trainingEvent, user.getLocale(), user.getTimeZone());
			subscriptionSender.setContextAttributes(
				"[$CUSTOMER_FULL_NAME$]", user.getFullName());
			subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
			subscriptionSender.setHtmlFormat(true);
			subscriptionSender.setLocalizedBodyMap(bodyMap);
			subscriptionSender.setLocalizedSubjectMap(subjectMap);
			subscriptionSender.setMailId(
				"training_survey_id", trainingCustomer.getTrainingCustomerId());
			subscriptionSender.setUserId(user.getUserId());

			subscriptionSender.addRuntimeSubscribers(
				user.getEmailAddress(), user.getFullName());

			subscriptionSender.flushNotificationsAsync();
		}
	}

	public static void sendWeeklyTrainingExamResultsMail() throws Exception {
		Map<Locale, String> subjectMap = getLocalizationMap(
			"emailTrainingExamResultRecentSubject");
		Map<Locale, String> bodyMap = getLocalizationMap(
			"emailTrainingExamResultRecentBody");

		Calendar calendar = CalendarFactoryUtil.getCalendar();

		int endDateDay = calendar.get(Calendar.DAY_OF_MONTH);
		int endDateMonth = calendar.get(Calendar.MONTH);
		int endDateYear = calendar.get(Calendar.YEAR);

		String endDate = _simpleDateFormat.format(calendar.getTime());

		calendar.add(Calendar.DAY_OF_MONTH, -7);

		int startDateDay = calendar.get(Calendar.DAY_OF_MONTH);
		int startDateMonth = calendar.get(Calendar.MONTH);
		int startDateYear = calendar.get(Calendar.YEAR);

		String startDate = _simpleDateFormat.format(calendar.getTime());

		String toAddress =
			PortletPropsValues.TRAINING_CERTIFICATION_EMAIL_ADDRESS;

		if (Validator.isNull(toAddress)) {
			return;
		}

		OSBSubscriptionSender subscriptionSender = new OSBSubscriptionSender();

		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);
		subscriptionSender.setContextAttribute("[$END_DATE$]", endDate);
		subscriptionSender.setContextAttribute("[$START_DATE$]", startDate);

		String trainingExamResultsRowHTML = getTrainingExamResultsRowHTML(
			startDateDay, startDateMonth, startDateYear);

		subscriptionSender.setContextAttribute(
			"[$TRAINING_EXAM_RESULTS_ROW_HTML$]", trainingExamResultsRowHTML,
			false);

		subscriptionSender.setFrom(FROM_ADDRESS, FROM_NAME);
		subscriptionSender.setGroupId(OSBConstants.GROUP_GLOBAL_ID);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyyMMddHHmmss");

		subscriptionSender.setMailId(
			"weekly_training_event_result", dateFormat.format(new Date()));

		subscriptionSender.setPortletId(OSBPortletKeys.OSB_ADMIN);
		subscriptionSender.setUserId(OSBConstants.USER_DEFAULT_USER_ID);

		subscriptionSender.addRuntimeSubscribers(
			toAddress, "Certification Team");

		subscriptionSender.flushNotificationsAsync();
	}

	protected static void appendTrainingExamResultCell(
		StringBundler sb, String value) {

		sb.append("<td style=\"background-color: #FFF;padding: 5px 10px;\">");
		sb.append(value);
		sb.append("</td>");
	}

	protected static String getFileName(String name) {
		String fileName = name.replaceAll("([A-Z][^A-Z])", "_$1");

		fileName = fileName.replaceAll("([^A-Z_])([A-Z])", "$1_$2");

		return
			"com/liferay/osb/admin/dependencies/" + fileName.toLowerCase() +
				".tmpl";
	}

	protected static String getTrainingExamResultsRowHTML(
			int createDateGTDay, int createDateGTMonth, int createDateGTYear)
		throws Exception {

		List<TrainingExamResult> trainingExamResults =
			TrainingExamResultLocalServiceUtil.getTrainingExamResults(
				createDateGTDay, createDateGTMonth, createDateGTYear);

		if (trainingExamResults.isEmpty()) {
			return "<tr><td colspan=\"9\">No Training Exam Results</td></tr>";
		}

		StringBundler sb = new StringBundler(26 * trainingExamResults.size());

		for (TrainingExamResult trainingExamResult : trainingExamResults) {
			sb.append("<tr>");

			appendTrainingExamResultCell(
				sb, trainingExamResult.getCandidateKey());

			User user = trainingExamResult.getUser();

			appendTrainingExamResultCell(sb, user.getFirstName());
			appendTrainingExamResultCell(sb, user.getLastName());
			appendTrainingExamResultCell(sb, user.getEmailAddress());

			TrainingExam trainingExam = trainingExamResult.getTrainingExam();

			appendTrainingExamResultCell(sb, trainingExam.getTestKey());

			appendTrainingExamResultCell(
				sb,
				_simpleDateFormat.format(trainingExamResult.getCreateDate()));

			appendTrainingExamResultCell(
				sb,
				_simpleDateFormat.format(trainingExamResult.getStartDate()));

			boolean acceptedTrainingExamEULA =
				ContractAuditLocalServiceUtil.hasContractAudit(
					ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
					ContractEntryConstants.DEFAULT_CLASS_PK,
					ContractEntryConstants.TYPE_TRAINING_EXAM_EULA,
					User.class.getName(), user.getUserId());

			appendTrainingExamResultCell(
				sb, String.valueOf(acceptedTrainingExamEULA));

			appendTrainingExamResultCell(sb, trainingExamResult.getResult());

			sb.append("</tr>");
		}

		return sb.toString();
	}

	protected static final String FROM_ADDRESS = "noreply@liferay.com";

	protected static final String FROM_NAME = "Liferay Training";

	private static SimpleDateFormat _simpleDateFormat = new SimpleDateFormat(
		"MM-dd-yyyy");

}