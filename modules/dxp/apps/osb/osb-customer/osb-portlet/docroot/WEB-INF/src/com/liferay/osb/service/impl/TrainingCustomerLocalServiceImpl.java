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

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.model.ContractEntryConstants;
import com.liferay.osb.model.TrainingCertificate;
import com.liferay.osb.model.TrainingCertificateTemplate;
import com.liferay.osb.model.TrainingCourse;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingEventConstants;
import com.liferay.osb.model.TrainingExam;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.TrainingLinkedUser;
import com.liferay.osb.model.UserProfile;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.service.base.TrainingCustomerLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserGroupRole;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.documentlibrary.NoSuchDirectoryException;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 * @author Peter Shin
 */
public class TrainingCustomerLocalServiceImpl
	extends TrainingCustomerLocalServiceBaseImpl {

	public TrainingCustomer addTrainingCustomer(
			long userId, long classNameId, long classPK, long userProfileId)
		throws PortalException, SystemException {

		UserProfile userProfile = userProfilePersistence.fetchByPrimaryKey(
			userProfileId);

		if (userProfile == null) {
			User user = userPersistence.findByPrimaryKey(userId);

			userProfile = userProfileLocalService.addUserProfile(
				userId, user.getEmailAddress(), user.getFirstName(),
				user.getLastName(), StringPool.BLANK);
		}

		UserProfileHistory userProfileHistory =
			userProfileHistoryLocalService.addUserProfileHistory(
				userId, classNameId, classPK, userProfile.getEmailAddress(),
				userProfile.getFirstName(), userProfile.getLastName(),
				userProfile.getLegalEntityName());

		long trainingCustomerId = counterLocalService.increment();

		TrainingCustomer trainingCustomer = trainingCustomerPersistence.create(
			trainingCustomerId);

		trainingCustomer.setUserId(userId);
		trainingCustomer.setClassNameId(classNameId);
		trainingCustomer.setClassPK(classPK);
		trainingCustomer.setUserProfileHistoryId(
			userProfileHistory.getUserProfileHistoryId());

		trainingCustomerPersistence.update(trainingCustomer, false);

		return trainingCustomer;
	}

	public void addTrainingCustomers(
			long[] userIds, long classNameId, long classPK,
			Map<Long, Long> userProfileIds)
		throws PortalException, SystemException {

		for (long userId : userIds) {
			Long userProfileId = userProfileIds.get(userId);

			if (userProfileId == null) {
				userProfileId = 0L;
			}

			TrainingCustomer trainingCustomer =
				trainingCustomerPersistence.fetchByU_C_C(
					userId, classNameId, classPK);

			if (trainingCustomer == null) {
				addTrainingCustomer(
					userId, classNameId, classPK, userProfileId);
			}
			else if (userProfileId > 0) {
				updateTrainingCustomer(
					trainingCustomer.getTrainingCustomerId(), userId,
					classNameId, classPK, userProfileId);
			}
		}
	}

	public TrainingCustomer deleteTrainingCustomer(long trainingCustomerId)
		throws PortalException, SystemException {

		TrainingCustomer trainingCustomer =
			trainingCustomerPersistence.findByPrimaryKey(trainingCustomerId);

		return deleteTrainingCustomer(trainingCustomer);
	}

	public TrainingCustomer deleteTrainingCustomer(
			TrainingCustomer trainingCustomer)
		throws PortalException, SystemException {

		// Training certificate

		trainingCertificateLocalService.
			deleteTrainingCustomerTrainingCertificate(
				trainingCustomer.getTrainingCustomerId());

		// Training customer

		trainingCustomerPersistence.remove(trainingCustomer);

		// User

		if (trainingCustomer.getClassPK() ==
				TrainingEventConstants.DEFAULT_TRAINING_EVENT_ID) {

			userLocalService.deleteUser(trainingCustomer.getUserId());
		}

		return trainingCustomer;
	}

	public void deleteTrainingCustomers(long userId)
		throws PortalException, SystemException {

		List<TrainingCustomer> trainingCustomers =
			trainingCustomerPersistence.findByUserId(userId);

		for (TrainingCustomer trainingCustomer : trainingCustomers) {
			deleteTrainingCustomer(trainingCustomer);
		}
	}

	public void deleteTrainingCustomers(long classNameId, long classPK)
		throws PortalException, SystemException {

		if (PortalUtil.getClassNameId(TrainingEvent.class) == classNameId) {
			List<TrainingCustomer> trainingCustomers =
				getClassTrainingCustomers(classNameId, classPK);

			for (TrainingCustomer trainingCustomer : trainingCustomers) {
				deleteTrainingCustomer(trainingCustomer);
			}
		}
	}

	public void deleteTrainingCustomers(
			long[] userIds, long classNameId, long classPK)
		throws PortalException, SystemException {

		for (long userId : userIds) {
			TrainingCustomer trainingCustomer =
				trainingCustomerPersistence.fetchByU_C_C(
					userId, classNameId, classPK);

			if (trainingCustomer == null) {
				continue;
			}

			deleteTrainingCustomer(trainingCustomer);
		}
	}

	public TrainingCustomer fetchTrainingCustomer(
			long userId, long classNameId, long classPK)
		throws SystemException {

		return trainingCustomerPersistence.fetchByU_C_C(
			userId, classNameId, classPK);
	}

	public TrainingCustomer fetchTrainingCustomer(
			long userId, OrderByComparator obc)
		throws SystemException {

		return trainingCustomerPersistence.fetchByUserId_First(userId, obc);
	}

	public List<TrainingCustomer> getClassTrainingCustomers(
			long classNameId, long classPK)
		throws SystemException {

		return trainingCustomerPersistence.findByC_C(classNameId, classPK);
	}

	public TrainingCustomer getTrainingCustomer(
			long userId, long classNameId, long classPK)
		throws PortalException, SystemException {

		return trainingCustomerPersistence.findByU_C_C(
			userId, classNameId, classPK);
	}

	public Map<Long, String> getTrainingCustomerBadgeNames(
			long userId, long classNameId)
		throws PortalException, SystemException {

		Map<Long, String> trainingCustomerBadgeNames =
			new LinkedHashMap<Long, String>();

		List<TrainingCustomer> trainingCustomers =
			getTrainingLinkedUserTrainingCustomers(userId, classNameId);

		for (TrainingCustomer trainingCustomer : trainingCustomers) {
			if (!trainingCertificateLocalService.
					hasTrainingCertificateCertifiedDate(
						trainingCustomer.getTrainingCustomerId())) {

				continue;
			}

			long trainingCertificateTemplateId = 0;

			String name = StringPool.BLANK;

			if (trainingCustomer.getClassNameId() ==
					PortalUtil.getClassNameId(TrainingEvent.class)) {

				TrainingEvent trainingEvent =
					trainingEventPersistence.findByPrimaryKey(
						trainingCustomer.getClassPK());

				trainingCertificateTemplateId =
					trainingEvent.getTrainingCertificateTemplateId();

				TrainingCourse trainingCourse =
					trainingEvent.getTrainingCourse();

				name = trainingCourse.getName();
			}
			else if (trainingCustomer.getClassNameId() ==
						PortalUtil.getClassNameId(TrainingExamResult.class)) {

				if (!contractAuditLocalService.hasContractAudit(
						ContractEntryConstants.DEFAULT_CLASS_NAME_ID,
						ContractEntryConstants.DEFAULT_CLASS_PK,
						ContractEntryConstants.TYPE_TRAINING_EXAM_EULA,
						User.class.getName(), trainingCustomer.getUserId())) {

					continue;
				}

				TrainingExamResult trainingExamResult =
					trainingExamResultPersistence.findByPrimaryKey(
						trainingCustomer.getClassPK());

				if (!trainingExamResult.hasPassedGrade()) {
					continue;
				}

				TrainingExam trainingExam =
					trainingExamPersistence.findByPrimaryKey(
						trainingExamResult.getTrainingExamId());

				trainingCertificateTemplateId =
					trainingExam.getTrainingCertificateTemplateId();
			}

			if (trainingCustomerBadgeNames.containsKey(
					trainingCertificateTemplateId)) {

				continue;
			}

			TrainingCertificateTemplate trainingCertificateTemplate =
				trainingCertificateTemplatePersistence.fetchByPrimaryKey(
					trainingCertificateTemplateId);

			if (trainingCertificateTemplate == null) {
				continue;
			}
			else if (Validator.isNull(name)) {
				name = trainingCertificateTemplate.getName();
			}

			try {
				DLStoreUtil.getFileNames(
					CompanyConstants.SYSTEM, CompanyConstants.SYSTEM,
					trainingCertificateTemplate.getBadgesDir());
			}
			catch (NoSuchDirectoryException nsde) {
				if (_log.isDebugEnabled()) {
					_log.debug(nsde, nsde);
				}

				if (!PortletPropsValues.DEVELOPER_MODE_ENABLED) {
					continue;
				}
			}

			trainingCustomerBadgeNames.put(trainingCertificateTemplateId, name);
		}

		return trainingCustomerBadgeNames;
	}

	public List<TrainingCustomer> getTrainingCustomers(
			long classNameId, long classPK, int[] statuses)
		throws SystemException {

		return trainingCustomerPersistence.findByC_C_S(
			classNameId, classPK, statuses);
	}

	public long getTrainingCustomersCount(
			long classNameId, long classPK, int[] statuses)
		throws SystemException {

		return trainingCustomerPersistence.countByC_C_S(
			classNameId, classPK, statuses);
	}

	public List<TrainingCustomer> getUserTrainingCustomers(
			long userId, long classNameId)
		throws SystemException {

		return trainingCustomerPersistence.findByU_C(userId, classNameId);
	}

	public int getUserTrainingCustomersCount(long userId)
		throws SystemException {

		return trainingCustomerPersistence.countByUserId(userId);
	}

	public boolean hasTrainingCustomer(
			long userId, long classNameId, long classPK)
		throws SystemException {

		TrainingCustomer trainingCustomer =
			trainingCustomerPersistence.fetchByU_C_C(
				userId, classNameId, classPK);

		if (trainingCustomer == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean hasTrainingCustomerBadgeNames(long userId)
		throws PortalException, SystemException {

		Map<Long, String> trainingCustomerBadgeNames =
			getTrainingCustomerBadgeNames(
				userId, PortalUtil.getClassNameId(TrainingEvent.class));

		if (!trainingCustomerBadgeNames.isEmpty()) {
			return true;
		}

		trainingCustomerBadgeNames = getTrainingCustomerBadgeNames(
			userId, PortalUtil.getClassNameId(TrainingExamResult.class));

		if (!trainingCustomerBadgeNames.isEmpty()) {
			return true;
		}

		return false;
	}

	public List<TrainingCustomer> search(String keywords, int start, int end)
		throws SystemException {

		return trainingCustomerFinder.findByKeywords(keywords, start, end);
	}

	public List<TrainingCustomer> search(
			String firstName, String lastName, String emailAddress,
			boolean andSearch, int start, int end)
		throws SystemException {

		return trainingCustomerFinder.findByFN_LN_EA(
			firstName, lastName, emailAddress, andSearch, start, end);
	}

	public int searchCount(String keywords) throws SystemException {
		return trainingCustomerFinder.countByKeywords(keywords);
	}

	public int searchCount(
			String firstName, String lastName, String emailAddress,
			boolean andSearch)
		throws SystemException {

		return trainingCustomerFinder.countByFN_LN_EA(
			firstName, lastName, emailAddress, andSearch);
	}

	public void updateComments(long trainingCustomerId, String comments)
		throws PortalException, SystemException {

		TrainingCustomer trainingCustomer =
			trainingCustomerPersistence.findByPrimaryKey(trainingCustomerId);

		trainingCustomer.setComments(comments);

		trainingCustomerPersistence.update(trainingCustomer, false);
	}

	public TrainingCustomer updateStatus(long trainingCustomerId, int status)
		throws PortalException, SystemException {

		// Training customer

		TrainingCustomer trainingCustomer =
			trainingCustomerPersistence.findByPrimaryKey(trainingCustomerId);

		trainingCustomer.setStatus(status);

		trainingCustomerPersistence.update(trainingCustomer, false);

		// Training certificate

		if (status > 0) {
			TrainingCertificate trainingCertificate =
				trainingCertificatePersistence.fetchByTrainingCustomerId(
					trainingCustomer.getTrainingCustomerId());

			if (trainingCertificate == null) {
				trainingCertificateLocalService.addTrainingCertificate(
					trainingCustomer.getUserId(),
					trainingCustomer.getTrainingCustomerId(), null, 0, 0);
			}
		}

		return trainingCustomer;
	}

	public List<TrainingCustomer> updateStatuses(
			long[] trainingCustomerIds, long trainingEventId, int status)
		throws PortalException, SystemException {

		List<TrainingCustomer> trainingCustomers =
			new ArrayList<TrainingCustomer>(trainingCustomerIds.length);

		long classNameId = PortalUtil.getClassNameId(TrainingEvent.class);

		for (long trainingCustomerId : trainingCustomerIds) {
			TrainingCustomer trainingCustomer =
				trainingCustomerPersistence.findByPrimaryKey(
					trainingCustomerId);

			if ((trainingCustomer.getClassNameId() != classNameId) ||
				(trainingCustomer.getClassPK() != trainingEventId)) {

				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to update status for training customer " +
							trainingCustomerId);
				}

				continue;
			}

			updateStatus(trainingCustomerId, status);

			trainingCustomers.add(trainingCustomer);
		}

		return trainingCustomers;
	}

	public TrainingCustomer updateTrainingCustomer(
			long trainingCustomerId, long userId, long classNameId,
			long classPK, long userProfileId)
		throws PortalException, SystemException {

		UserProfile userProfile = userProfilePersistence.findByPrimaryKey(
			userProfileId);

		TrainingCustomer trainingCustomer =
			trainingCustomerPersistence.findByPrimaryKey(trainingCustomerId);

		UserProfileHistory userProfileHistory =
			userProfileHistoryLocalService.getUserProfileHistory(
				trainingCustomer.getUserProfileHistoryId());

		if (AdminUtil.equalsByUserProfile(userProfile, userProfileHistory)) {
			return trainingCustomer;
		}

		userProfileHistory =
			userProfileHistoryLocalService.addUserProfileHistory(
				userId, classNameId, classPK, userProfile.getEmailAddress(),
				userProfile.getFirstName(), userProfile.getLastName(),
				userProfile.getLegalEntityName());

		trainingCustomer.setUserId(userId);
		trainingCustomer.setClassNameId(classNameId);
		trainingCustomer.setClassPK(classPK);
		trainingCustomer.setUserProfileHistoryId(
			userProfileHistory.getUserProfileHistoryId());

		trainingCustomerPersistence.update(trainingCustomer, false);

		return trainingCustomer;
	}

	public TrainingCustomer updateTrainingCustomer(
			long creatorUserId, long trainingCustomerId, long classNameId,
			long classPK, String emailAddress, String firstName,
			String lastName, String legalEntityName)
		throws PortalException, SystemException {

		TrainingCustomer trainingCustomer =
			trainingCustomerPersistence.fetchByPrimaryKey(trainingCustomerId);

		ServiceContext serviceContext = new ServiceContext();

		Map<String, Serializable> expandoBridgeAttributes =
			serviceContext.getExpandoBridgeAttributes();

		expandoBridgeAttributes.put("osbCompany", legalEntityName);

		if (trainingCustomer == null) {

			// User

			trainingCustomerId = counterLocalService.increment();

			long companyId = OSBConstants.COMPANY_ID;
			boolean autoPassword = true;
			String password1 = StringPool.BLANK;
			String password2 = StringPool.BLANK;
			boolean autoScreenName = false;
			String screenName = AdminUtil.getGeneratedScreenName(
				trainingCustomerId);

			if (Validator.isNull(emailAddress)) {
				emailAddress = AdminUtil.getGeneratedEmailAddress(
					trainingCustomerId);
			}
			else {
				emailAddress = emailAddress.trim().toLowerCase();
			}

			long facebookId = 0;
			String openId = StringPool.BLANK;
			Locale locale = LocaleUtil.getDefault();
			String middleName = StringPool.BLANK;
			int prefixId = 0;
			int suffixId = 0;
			boolean male = true;
			int birthdayDay = 1;
			int birthdayMonth = 0;
			int birthdayYear = 1970;
			String jobTitle = StringPool.BLANK;
			long[] groupIds = null;
			long[] organizationIds = null;
			long[] roleIds = null;
			long[] userGroupIds = null;
			boolean sendEmail = false;

			serviceContext.setAttribute("sendVerificationEmail", false);

			User user = userLocalService.addUser(
				creatorUserId, companyId, autoPassword, password1, password2,
				autoScreenName, screenName, emailAddress, facebookId, openId,
				locale, firstName, middleName, lastName, prefixId, suffixId,
				male, birthdayMonth, birthdayDay, birthdayYear, jobTitle,
				groupIds, organizationIds, roleIds, userGroupIds, sendEmail,
				serviceContext);

			userLocalService.updateStatus(
				user.getUserId(),
				WorkflowConstants.STATUS_INCOMPLETE_TRAINING_REGISTRATION);

			// Training customer

			trainingCustomer = trainingCustomerPersistence.create(
				trainingCustomerId);

			trainingCustomer.setUserId(user.getUserId());
			trainingCustomer.setClassNameId(classNameId);
			trainingCustomer.setClassPK(
				TrainingEventConstants.DEFAULT_TRAINING_EVENT_ID);

			trainingCustomer = trainingCustomerPersistence.update(
				trainingCustomer, false);

			// User profile

			UserProfile userProfile = userProfileLocalService.addUserProfile(
				user.getUserId(),
				getEmailAddress(trainingCustomerId, emailAddress), firstName,
				lastName, legalEntityName);

			if (classPK > 0) {
				trainingCustomer = addTrainingCustomer(
					user.getUserId(), classNameId, classPK,
					userProfile.getUserProfileId());
			}

			return trainingCustomer;
		}

		// User

		User user = userPersistence.fetchByPrimaryKey(
			trainingCustomer.getUserId());

		if (user.getStatus() !=
				WorkflowConstants.STATUS_INCOMPLETE_TRAINING_REGISTRATION) {

			userProfileLocalService.addUserProfile(
				user.getUserId(),
				getEmailAddress(trainingCustomerId, emailAddress), firstName,
				lastName, legalEntityName);

			return trainingCustomer;
		}

		Contact contact = user.getContact();

		String oldPassword = StringPool.BLANK;
		String newPassword1 = StringPool.BLANK;
		String newPassword2 = StringPool.BLANK;
		boolean passwordReset = false;
		String reminderQueryQuestion = StringPool.BLANK;
		String reminderQueryAnswer = StringPool.BLANK;
		String screenName = user.getScreenName();

		if (Validator.isNull(emailAddress)) {
			emailAddress = AdminUtil.getGeneratedEmailAddress(
				trainingCustomerId);
		}
		else {
			emailAddress = emailAddress.trim().toLowerCase();
		}

		long facebookId = user.getFacebookId();
		String openId = user.getOpenId();
		String languageId = user.getLanguageId();
		String timeZoneId = user.getTimeZoneId();
		String greeting = user.getGreeting();
		String comments = StringPool.BLANK;
		String middleName = user.getMiddleName();
		int prefixId = contact.getPrefixId();
		int suffixId = contact.getSuffixId();
		boolean male = user.getMale();
		int birthdayDay = 1;
		int birthdayMonth = 0;
		int birthdayYear = 1970;
		String smsSn = contact.getSmsSn();
		String aimSn = contact.getAimSn();
		String facebookSn = contact.getFacebookSn();
		String icqSn = contact.getIcqSn();
		String jabberSn = contact.getJabberSn();
		String msnSn = contact.getMsnSn();
		String mySpaceSn = contact.getMySpaceSn();
		String skypeSn = contact.getSkypeSn();
		String twitterSn = contact.getTwitterSn();
		String ymSn = contact.getYmSn();
		String jobTitle = user.getJobTitle();
		long[] groupIds = user.getGroupIds();
		long[] organizationIds = user.getOrganizationIds();
		long[] roleIds = user.getRoleIds();
		List<UserGroupRole> userGroupRoles = Collections.emptyList();
		long[] userGroupIds = user.getUserGroupIds();

		user = userLocalService.updateUser(
			user.getUserId(), oldPassword, newPassword1, newPassword2,
			passwordReset, reminderQueryQuestion, reminderQueryAnswer,
			screenName, emailAddress, facebookId, openId, languageId,
			timeZoneId, greeting, comments, firstName, middleName, lastName,
			prefixId, suffixId, male, birthdayMonth, birthdayDay, birthdayYear,
			smsSn, aimSn, facebookSn, icqSn, jabberSn, msnSn, mySpaceSn,
			skypeSn, twitterSn, ymSn, jobTitle, groupIds, organizationIds,
			roleIds, userGroupRoles, userGroupIds, serviceContext);

		// User profile

		userProfileLocalService.addUserProfile(
			user.getUserId(), getEmailAddress(trainingCustomerId, emailAddress),
			firstName, lastName, legalEntityName);

		return trainingCustomer;
	}

	protected String getEmailAddress(
		long trainingCustomerId, String emailAddress) {

		if (emailAddress.equals(
				AdminUtil.getGeneratedEmailAddress(trainingCustomerId))) {

			return StringPool.BLANK;
		}

		return emailAddress;
	}

	protected List<TrainingCustomer> getTrainingLinkedUserTrainingCustomers(
			long userId, long classNameId)
		throws PortalException, SystemException {

		TrainingLinkedUser trainingLinkedUser =
			trainingLinkedUserLocalService.fetchUserTrainingLinkedUser(userId);

		if (trainingLinkedUser == null) {
			return trainingCustomerPersistence.findByU_C(userId, classNameId);
		}

		if (!trainingLinkedUser.isPrimaryUser()) {
			return Collections.emptyList();
		}

		List<TrainingLinkedUser> trainingLinkedUsers =
			trainingLinkedUserLocalService.getTrainingLinkedUsers(
				trainingLinkedUser.getUserId());

		List<TrainingCustomer> trainingCustomers =
			new ArrayList<TrainingCustomer>();

		for (TrainingLinkedUser curTrainingLinkedUser : trainingLinkedUsers) {
			trainingCustomers.addAll(
				trainingCustomerPersistence.findByU_C(
					curTrainingLinkedUser.getUserId(), classNameId));
		}

		return trainingCustomers;
	}

	private static Log _log = LogFactoryUtil.getLog(
		TrainingCustomerLocalServiceImpl.class);

}