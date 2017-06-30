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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.TrainingExamResultStatusException;
import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.admin.util.TrainingEmailUtil;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.TrainingCertificateTemplate;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingExam;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.TrainingExamResultConstants;
import com.liferay.osb.model.UserProfile;
import com.liferay.osb.service.TrainingCertificateTemplateLocalServiceUtil;
import com.liferay.osb.service.base.TrainingExamResultLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;
import com.liferay.portal.util.SubscriptionSender;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletPreferences;

/**
 * @author Calvin Keum
 * @author Joan Kim
 * @author Haote Chou
 */
public class TrainingExamResultLocalServiceImpl
	extends TrainingExamResultLocalServiceBaseImpl {

	public TrainingExamResult addTrainingExamResult(
			long userId, long trainingExamId, int recordType,
			String registrationNumber, String formKey, Date startDate,
			String testScore, int correctCount, int incorrectCount,
			int skippedCount, int grade, String emailAddress, String firstName,
			String lastName, String legalEntityName)
		throws PortalException, SystemException {

		TrainingExamResult trainingExamResult =
			trainingExamResultPersistence.fetchByRegistrationNumber(
				registrationNumber);

		TrainingCustomer trainingCustomer = null;

		User user = null;

		if (trainingExamResult != null) {
			trainingCustomer = trainingExamResult.getTrainingCustomer();

			user = trainingCustomer.getUser();
		}
		else {
			long trainingExamResultId = counterLocalService.increment();

			if (userId > 0) {
				user = userLocalService.fetchUser(userId);
			}
			else {
				user = userLocalService.fetchUserByEmailAddress(
					OSBConstants.COMPANY_ID, emailAddress);
			}

			if (user != null) {
				UserProfile userProfile =
					userProfileLocalService.addUserProfile(
						user.getUserId(), emailAddress, firstName, lastName,
						legalEntityName);

				trainingCustomer =
					trainingCustomerLocalService.addTrainingCustomer(
						user.getUserId(),
						PortalUtil.getClassNameId(TrainingExamResult.class),
						trainingExamResultId, userProfile.getUserProfileId());
			}
			else {
				trainingCustomer =
					trainingCustomerLocalService.updateTrainingCustomer(
						OSBConstants.USER_DEFAULT_USER_ID, 0,
						PortalUtil.getClassNameId(TrainingExamResult.class),
						trainingExamResultId, emailAddress, firstName, lastName,
						legalEntityName);

				user = trainingCustomer.getUser();
			}

			trainingExamResult = trainingExamResultPersistence.create(
				trainingExamResultId);
		}

		trainingExamResult.setCreateDate(new Date());
		trainingExamResult.setTrainingExamId(trainingExamId);
		trainingExamResult.setRecordType(recordType);
		trainingExamResult.setRegistrationNumber(registrationNumber);
		trainingExamResult.setFormKey(formKey);
		trainingExamResult.setStartDate(startDate);
		trainingExamResult.setTestScore(testScore);
		trainingExamResult.setCorrectCount(correctCount);
		trainingExamResult.setIncorrectCount(incorrectCount);
		trainingExamResult.setSkippedCount(skippedCount);
		trainingExamResult.setGrade(grade);
		trainingExamResult.setStatus(
			WorkflowConstants.STATUS_PENDING_AGREEMENT);

		trainingExamResultPersistence.update(trainingExamResult, false);

		if (trainingCertificateLocalService.hasTrainingCertificate(
				trainingCustomer.getTrainingCustomerId())) {

			return trainingExamResult;
		}

		if (grade == TrainingExamResultConstants.GRADE_PASSED) {
			trainingCertificateLocalService.addTrainingCertificate(
				trainingCustomer.getUserId(),
				trainingCustomer.getTrainingCustomerId(), null, 0, 0);

			TrainingExam trainingExam = trainingExamResult.getTrainingExam();

			long trainingCertificateTemplateId =
				OSBConstants.
					TRAINING_CERTIFICATE_TEMPLATE_CERTIFIED_DEVELOPER_61_ID;

			if (trainingExam.getTrainingCertificateTemplateId() ==
					trainingCertificateTemplateId) {

				userLocalService.addRoleUsers(
					OSBConstants.ROLE_OSB_TRAINING_EXAM_PAGE_61_ID,
					new long[] {trainingCustomer.getUserId()});
			}

			trainingCertificateTemplateId =
				OSBConstants.
					TRAINING_CERTIFICATE_TEMPLATE_CERTIFIED_DEVELOPER_62_ID;

			if (trainingExam.getTrainingCertificateTemplateId() ==
					trainingCertificateTemplateId) {

				userLocalService.addRoleUsers(
					OSBConstants.ROLE_OSB_TRAINING_EXAM_PAGE_62_ID,
					new long[] {trainingCustomer.getUserId()});
			}
		}

		if ((grade == TrainingExamResultConstants.GRADE_PASSED) &&
			contractAuditLocalService.hasContractAudit(
				ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
				ContractEntryConstants.DEFAULT_CLASS_PK,
				ContractEntryConstants.TYPE_TRAINING_EXAM_EULA,
				User.class.getName(), user.getUserId())) {

			try {
				trainingCertificateLocalService.sendTrainingCertificate(
					trainingCustomer.getTrainingCustomerId());
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
		else {
			sendTrainingExamResultEmail(trainingExamResult, trainingCustomer);
		}

		return trainingExamResult;
	}

	public void checkIgnoredTrainingExamResults() throws Exception {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.add(Calendar.MONTH, -1);

		Date oneMonthReminderDate = calendar.getTime();

		calendar.add(Calendar.MONTH, -1);

		Date twoMonthReminderDate = calendar.getTime();

		List<TrainingExamResult> trainingExamResults =
			trainingExamResultPersistence.findByGtSD_G_NotS(
				calendar.getTime(), TrainingExamResultConstants.GRADE_PASSED,
				WorkflowConstants.STATUS_APPROVED);

		for (TrainingExamResult trainingExamResult : trainingExamResults) {
			User user = trainingExamResult.getUser();

			if (contractAuditLocalService.hasContractAudit(
					ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
					ContractEntryConstants.DEFAULT_CLASS_PK,
					ContractEntryConstants.TYPE_TRAINING_EXAM_EULA,
					User.class.getName(), user.getUserId())) {

				updateTrainingExamResult(
					trainingExamResult.getTrainingExamResultId(),
					WorkflowConstants.STATUS_APPROVED);

				continue;
			}

			int status = trainingExamResult.getStatus();
			Date startDate = trainingExamResult.getStartDate();

			if ((status == WorkflowConstants.STATUS_PENDING_FINAL_REMINDER) &&
				(DateUtil.compareTo(startDate, twoMonthReminderDate) < 0)) {

				TrainingEmailUtil.sendTrainingExamResultAgreementReminderMail(
					user, trainingExamResult);

				updateTrainingExamResult(
					trainingExamResult.getTrainingExamResultId(),
					WorkflowConstants.STATUS_PENDING);

				continue;
			}

			if ((status == WorkflowConstants.STATUS_PENDING_AGREEMENT) &&
				(DateUtil.compareTo(startDate, oneMonthReminderDate) < 0)) {

				TrainingEmailUtil.sendTrainingExamResultAgreementReminderMail(
					user, trainingExamResult);

				updateTrainingExamResult(
					trainingExamResult.getTrainingExamResultId(),
					WorkflowConstants.STATUS_PENDING_FINAL_REMINDER);
			}
		}
	}

	@Override
	public TrainingExamResult deleteTrainingExamResult(
			long trainingExamResultId)
		throws PortalException, SystemException {

		TrainingExamResult trainingExamResult =
			trainingExamResultPersistence.findByPrimaryKey(
				trainingExamResultId);

		return deleteTrainingExamResult(trainingExamResult);
	}

	@Override
	public TrainingExamResult deleteTrainingExamResult(
			TrainingExamResult trainingExamResult)
		throws PortalException, SystemException {

		// External ID mapper

		TrainingCustomer trainingCustomer =
			trainingExamResult.getTrainingCustomer();

		List<TrainingExamResult> trainingExamResults = getTrainingExamResults(
			trainingCustomer.getUserId());

		if (trainingExamResults.size() == 1) {
			externalIdMapperLocalService.deleteExternalIdMapper(
				PortalUtil.getClassNameId(User.class),
				trainingCustomer.getUserId(),
				ExternalIdMapperConstants.TYPE_KRYTERION);

			externalIdMapperLocalService.deleteExternalIdMapper(
				PortalUtil.getClassNameId(User.class),
				trainingCustomer.getUserId(),
				ExternalIdMapperConstants.TYPE_PROMETRIC);
		}

		// Training exam result

		trainingExamResultPersistence.remove(trainingExamResult);

		// Training exam result item

		trainingExamResultItemPersistence.removeByTrainingExamResultId(
			trainingExamResult.getTrainingExamResultId());

		// Training exam result section

		trainingExamResultSectionPersistence.removeByTrainingExamResultId(
			trainingExamResult.getTrainingExamResultId());

		return trainingExamResult;
	}

	public void deleteTrainingExamResults(long userId)
		throws PortalException, SystemException {

		List<TrainingCustomer> trainingCustomers =
			trainingCustomerPersistence.findByU_C(
				userId, PortalUtil.getClassNameId(TrainingExamResult.class));

		for (TrainingCustomer trainingCustomer : trainingCustomers) {
			deleteTrainingExamResult(trainingCustomer.getClassPK());
		}
	}

	public TrainingExamResult fetchTrainingExamResult(String registrationNumber)
		throws SystemException {

		return trainingExamResultPersistence.fetchByRegistrationNumber(
			registrationNumber);
	}

	public List<TrainingExamResult> getTrainingExamResults(
			int createDateGTDay, int createDateGTMonth, int createDateGTYear)
		throws SystemException {

		Date createDateGT = PortalUtil.getDate(
			createDateGTMonth, createDateGTDay, createDateGTYear);

		return trainingExamResultPersistence.findByGtCreateDate(createDateGT);
	}

	public List<TrainingExamResult> getTrainingExamResults(long userId)
		throws PortalException, SystemException {

		List<TrainingExamResult> trainingExamResults =
			new ArrayList<TrainingExamResult>();

		List<TrainingCustomer> trainingCustomers =
			trainingCustomerPersistence.findByU_C(
				userId, PortalUtil.getClassNameId(TrainingExamResult.class));

		for (TrainingCustomer trainingCustomer : trainingCustomers) {
			TrainingExamResult trainingExamResult =
				trainingExamResultPersistence.fetchByPrimaryKey(
					trainingCustomer.getClassPK());

			if (trainingExamResult == null) {
				continue;
			}

			trainingExamResults.add(trainingExamResult);
		}

		return trainingExamResults;
	}

	public List<TrainingExamResult> search(
			long userId, String testKey, int startDateGTDay,
			int startDateGTMonth, int startDateGTYear, int startDateLTDay,
			int startDateLTMonth, int startDateLTYear, boolean andOperator,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		Date startDateGT = PortalUtil.getDate(
			startDateGTMonth, startDateGTDay, startDateGTYear);
		Date startDateLT = PortalUtil.getDate(
			startDateLTMonth, startDateLTDay, startDateLTYear);

		return trainingExamResultFinder.findByU_EI_SD(
			userId, testKey, startDateGT, startDateLT, andOperator, start, end,
			obc);
	}

	public List<TrainingExamResult> search(
			long userId, String keywords, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return trainingExamResultFinder.findByKeywords(
			userId, keywords, start, end, obc);
	}

	public List<TrainingExamResult> search(
			String keywords, boolean groupByUserId, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return trainingExamResultFinder.findByKeywords(
			keywords, groupByUserId, start, end, obc);
	}

	public List<TrainingExamResult> search(
			String firstName, String lastName, String emailAddress,
			String candidateKey, boolean groupByUserId, boolean andOperator,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		return trainingExamResultFinder.findByFN_LN_EA_EI(
			firstName, lastName, emailAddress, candidateKey, groupByUserId,
			andOperator, start, end, obc);
	}

	public int searchCount(long userId, String keywords)
		throws SystemException {

		return trainingExamResultFinder.countByKeywords(userId, keywords);
	}

	public int searchCount(
			long userId, String testKey, int startDateGTDay,
			int startDateGTMonth, int startDateGTYear, int startDateLTDay,
			int startDateLTMonth, int startDateLTYear, boolean andOperator)
		throws SystemException {

		Date startDateGT = PortalUtil.getDate(
			startDateGTMonth, startDateGTDay, startDateGTYear);
		Date startDateLT = PortalUtil.getDate(
			startDateLTMonth, startDateLTDay, startDateLTYear);

		return trainingExamResultFinder.countByU_EI_SD(
			userId, testKey, startDateGT, startDateLT, andOperator);
	}

	public int searchCount(String keywords) throws SystemException {
		return trainingExamResultFinder.countByKeywords(keywords);
	}

	public int searchCount(
			String firstName, String lastName, String emailAddress,
			String candidateKey, boolean andOperator)
		throws SystemException {

		return trainingExamResultFinder.countByFN_LN_EA_EI(
			firstName, lastName, emailAddress, candidateKey, andOperator);
	}

	public TrainingExamResult updateTrainingExamResult(
			long trainingExamResultId, int status)
		throws PortalException, SystemException {

		validate(status);

		TrainingExamResult trainingExamResult =
			trainingExamResultPersistence.findByPrimaryKey(
				trainingExamResultId);

		trainingExamResult.setStatus(
			WorkflowConstants.STATUS_PENDING_AGREEMENT);

		return trainingExamResultPersistence.update(trainingExamResult, false);
	}

	protected void sendTrainingExamResultEmail(
			TrainingExamResult trainingExamResult,
			TrainingCustomer trainingCustomer)
		throws PortalException, SystemException {

		PortletPreferences preferences = AdminUtil.getPortletPreferences();

		Map<Locale, String> subjectMap = null;
		Map<Locale, String> bodyMap = null;

		if ((trainingExamResult.getGrade() ==
				TrainingExamResultConstants.GRADE_PASSED) &&
			trainingCustomer.isTrainingUser()) {

			subjectMap = AdminUtil.getEmailTrainingExamResultPassedSubjectMap(
				preferences);
			bodyMap = AdminUtil.getEmailTrainingExamResultPassedNewUserBodyMap(
				preferences);
		}
		else if (trainingExamResult.getGrade() ==
					TrainingExamResultConstants.GRADE_PASSED) {

			subjectMap = AdminUtil.getEmailTrainingExamResultPassedSubjectMap(
				preferences);
			bodyMap = AdminUtil.getEmailTrainingExamResultPassedBodyMap(
				preferences);
		}
		else if (trainingExamResult.getGrade() ==
					TrainingExamResultConstants.GRADE_FAILED) {

			subjectMap = AdminUtil.getEmailTrainingExamResultFailedSubjectMap(
				preferences);
			bodyMap = AdminUtil.getEmailTrainingExamResultFailedBodyMap(
				preferences);
		}

		if (subjectMap == null) {
			return;
		}

		User user = trainingCustomer.getUser();

		TrainingExam trainingExam = trainingExamPersistence.findByPrimaryKey(
			trainingExamResult.getTrainingExamId());

		TrainingCertificateTemplate trainingCertificateTemplate =
			TrainingCertificateTemplateLocalServiceUtil.
				getTrainingCertificateTemplate(
					trainingExam.getTrainingCertificateTemplateId());

		String examBluePrintURL =
			"https://web.liferay.com/documents/14/27998837/" +
				"Liferay_Certified_Professional_Developer_Exam_Blueprint.pdf";
		String liferayVersion = "6.1";

		if (trainingCertificateTemplate.getTrainingCertificateTemplateId() ==
				OSBConstants.
					TRAINING_CERTIFICATE_TEMPLATE_CERTIFIED_DEVELOPER_62_ID) {

			examBluePrintURL =
				"https://web.liferay.com/documents/14/39478043/" +
					"6.2+professional+developer-digital.pdf";
			liferayVersion = "6.2";
		}

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);
		subscriptionSender.setContextAttributes(
			"[$CUSTOMER_FULL_NAME$]", user.getFullName(),
			"[$EXAM_BLUEPRINT_URL$]", examBluePrintURL, "[$LIFERAY_VERSION$]",
			liferayVersion, "[$TRAINING_CERTIFICATE_AGREEMENT_URL$]",
			_TRAINING_CERTIFICATE_AGREEMENT_URL,
			"[$TRAINING_CERTIFICATE_TEMPLATE_NAME$]",
			trainingCertificateTemplate.getName(), "[$TRAINING_EXAM_TEST_KEY$]",
			trainingExam.getTestKey(), "[$TRAINING_EXAM_NAME$]",
			trainingExam.getName());
		subscriptionSender.setFrom("no-reply@liferay.com", "Liferay Training");
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setLocalizedBodyMap(bodyMap);
		subscriptionSender.setLocalizedSubjectMap(subjectMap);
		subscriptionSender.setMailId(
			"training_exam_result_id",
			trainingExamResult.getTrainingExamResultId());
		subscriptionSender.setPortletId(OSBPortletKeys.OSB_ADMIN);
		subscriptionSender.setUserId(user.getUserId());

		subscriptionSender.addRuntimeSubscribers(
			user.getEmailAddress(), user.getFullName());

		subscriptionSender.flushNotificationsAsync();
	}

	protected void validate(int status) throws PortalException {
		if ((status != WorkflowConstants.STATUS_APPROVED) &&
			(status != WorkflowConstants.STATUS_PENDING_AGREEMENT) &&
			(status != WorkflowConstants.STATUS_PENDING_FINAL_REMINDER)) {

			throw new TrainingExamResultStatusException();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		TrainingExamResultLocalServiceImpl.class);

	private static String _TRAINING_CERTIFICATE_AGREEMENT_URL =
		"https://www.liferay.com/services/certification/resources/";

}