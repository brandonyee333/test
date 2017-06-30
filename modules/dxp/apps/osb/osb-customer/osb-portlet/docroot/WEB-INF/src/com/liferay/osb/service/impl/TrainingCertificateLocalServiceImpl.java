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

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfNumber;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfString;

import com.liferay.compat.portal.kernel.servlet.HttpHeaders;
import com.liferay.compat.portal.kernel.util.HttpUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.NoSuchTrainingCertificateException;
import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.model.TrainingCertificate;
import com.liferay.osb.model.TrainingCertificateTemplate;
import com.liferay.osb.model.TrainingCourse;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingCustomerConstants;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingExam;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.UserProfile;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.base.TrainingCertificateLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBFileUtil;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.util.PwdGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.text.Format;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.PortletPreferences;
import javax.portlet.ResourceResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Calvin Keum
 * @author Eddie Olson
 */
public class TrainingCertificateLocalServiceImpl
	extends TrainingCertificateLocalServiceBaseImpl {

	public TrainingCertificate addTrainingCertificate(
			long userId, long trainingCustomerId, String key, int surveyStatus,
			long userProfileId)
		throws PortalException, SystemException {

		// Training certificate

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long trainingCertificateId = counterLocalService.increment();

		TrainingCertificate trainingCertificate =
			trainingCertificatePersistence.create(trainingCertificateId);

		trainingCertificate.setUserId(userId);
		trainingCertificate.setUserName(user.getFullName());
		trainingCertificate.setCreateDate(now);
		trainingCertificate.setModifiedDate(now);
		trainingCertificate.setTrainingCustomerId(trainingCustomerId);

		TrainingCustomer trainingCustomer =
			trainingCustomerPersistence.findByPrimaryKey(trainingCustomerId);

		UserProfile userProfile = userProfilePersistence.fetchByPrimaryKey(
			userProfileId);

		if (userProfile == null) {
			UserProfileHistory userProfileHistory =
				userProfileHistoryPersistence.findByPrimaryKey(
					trainingCustomer.getUserProfileHistoryId());

			userProfile = userProfileLocalService.addUserProfile(
				userProfileHistory.getUserId(),
				userProfileHistory.getEmailAddress(),
				userProfileHistory.getFirstName(),
				userProfileHistory.getLastName(),
				userProfileHistory.getLegalEntityName());
		}

		UserProfileHistory userProfileHistory =
			userProfileHistoryLocalService.addUserProfileHistory(
				userId, PortalUtil.getClassNameId(TrainingCertificate.class),
				trainingCertificateId, userProfile.getEmailAddress(),
				userProfile.getFirstName(), userProfile.getLastName(),
				userProfile.getLegalEntityName());

		trainingCertificate.setUserProfileHistoryId(
			userProfileHistory.getUserProfileHistoryId());

		if (Validator.isNotNull(key)) {
			trainingCertificate.setKey(key);
		}
		else {
			trainingCertificate.setKey(generateKey());
		}

		if (trainingCustomer.getClassNameId() ==
				PortalUtil.getClassNameId(TrainingExamResult.class)) {

			TrainingExamResult trainingExamResult =
				trainingExamResultPersistence.findByPrimaryKey(
					trainingCustomer.getClassPK());

			trainingCertificate.setCertifiedDate(
				trainingExamResult.getStartDate());
		}

		trainingCertificate.setSurveyStatus(surveyStatus);

		trainingCertificatePersistence.update(trainingCertificate, false);

		// Training customer

		if (trainingCustomer.getClassNameId() ==
				PortalUtil.getClassNameId(TrainingExamResult.class)) {

			trainingCustomerLocalService.updateStatus(
				trainingCustomerId, TrainingCustomerConstants.STATUS_CERTIFIED);
		}

		return trainingCertificate;
	}

	public void addTrainingCertificates(long userId, long[] trainingCustomerIds)
		throws PortalException, SystemException {

		for (long trainingCustomerId : trainingCustomerIds) {
			TrainingCertificate trainingCertificate =
				trainingCertificatePersistence.fetchByTrainingCustomerId(
					trainingCustomerId);

			if (trainingCertificate == null) {
				addTrainingCertificate(userId, trainingCustomerId, null, 0, 0);
			}
			else if (trainingCertificate.getCertifiedDate() == null) {
				updateCertifiedDate(
					trainingCertificate.getTrainingCertificateId(),
					trainingCustomerId);
			}
		}
	}

	public void deleteTrainingCustomerTrainingCertificate(
			long trainingCustomerId)
		throws PortalException, SystemException {

		TrainingCertificate trainingCertificate =
			trainingCertificatePersistence.fetchByTrainingCustomerId(
				trainingCustomerId);

		if (trainingCertificate == null) {
			return;
		}

		trainingCertificatePersistence.remove(trainingCertificate);
	}

	public TrainingCertificate fetchTrainingCertificateByTrainingCustomerId(
			long trainingCustomerId)
		throws SystemException {

		return trainingCertificatePersistence.fetchByTrainingCustomerId(
			trainingCustomerId);
	}

	public void getTrainingCertificate(
			ResourceResponse resourceResponse, long trainingCustomerId)
		throws Exception {

		resourceResponse.setContentType(ContentTypes.APPLICATION_PDF);

		TrainingCustomer trainingCustomer =
			trainingCustomerPersistence.findByPrimaryKey(trainingCustomerId);

		String contentDispositionFileName =
			"attachment; filename*=UTF-8''" +
				HttpUtil.encodeURL(getFileName(trainingCustomer), true);

		resourceResponse.setProperty(
			HttpHeaders.CONTENT_DISPOSITION, contentDispositionFileName);

		createTrainingCertificate(
			trainingCustomer, resourceResponse.getPortletOutputStream());
	}

	public TrainingCertificate getTrainingCertificate(String key)
		throws PortalException, SystemException {

		key = key.replaceAll("[^A-Za-z0-9]", StringPool.BLANK);

		return trainingCertificatePersistence.findByKey(key);
	}

	public TrainingCertificate getTrainingCertificate(
			String key, String lastName, long classNameId)
		throws PortalException, SystemException {

		TrainingCertificate trainingCertificate = getTrainingCertificate(key);

		if (trainingCertificate.getCertifiedDate() == null) {
			throw new NoSuchTrainingCertificateException();
		}

		TrainingCustomer trainingCustomer =
			trainingCustomerPersistence.fetchByPrimaryKey(
				trainingCertificate.getTrainingCustomerId());

		if ((trainingCustomer == null) ||
			(trainingCustomer.getClassNameId() != classNameId)) {

			throw new NoSuchTrainingCertificateException();
		}

		UserProfileHistory userProfileHistory =
			userProfileHistoryPersistence.fetchByPrimaryKey(
				trainingCustomer.getUserProfileHistoryId());

		if ((userProfileHistory == null) ||
			!lastName.equalsIgnoreCase(userProfileHistory.getLastName())) {

			throw new NoSuchTrainingCertificateException();
		}

		return trainingCertificate;
	}

	public TrainingCertificate getTrainingCertificateByTrainingCustomerId(
			long trainingCustomerId)
		throws PortalException, SystemException {

		return trainingCertificatePersistence.findByTrainingCustomerId(
			trainingCustomerId);
	}

	public boolean hasTrainingCertificate(long trainingCustomerId)
		throws SystemException {

		TrainingCertificate trainingCertificate =
			trainingCertificatePersistence.fetchByTrainingCustomerId(
				trainingCustomerId);

		if (trainingCertificate == null) {
			return false;
		}

		return true;
	}

	public boolean hasTrainingCertificateCertifiedDate(long trainingCustomerId)
		throws SystemException {

		TrainingCertificate trainingCertificate =
			trainingCertificatePersistence.fetchByTrainingCustomerId(
				trainingCustomerId);

		if ((trainingCertificate == null) ||
			(trainingCertificate.getCertifiedDate() == null)) {

			return false;
		}

		return true;
	}

	public void sendTrainingCertificate(long trainingCustomerId)
		throws Exception {

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);

		TrainingCustomer trainingCustomer =
			trainingCustomerPersistence.findByPrimaryKey(trainingCustomerId);

		TrainingCertificate trainingCertificate =
			trainingCertificatePersistence.findByTrainingCustomerId(
				trainingCustomer.getTrainingCustomerId());

		UserProfileHistory userProfileHistory =
			userProfileHistoryPersistence.findByPrimaryKey(
				trainingCustomer.getUserProfileHistoryId());

		if (trainingCustomer.getClassNameId() ==
				PortalUtil.getClassNameId(TrainingEvent.class)) {

			Format longDateFormatDate =
				FastDateFormatFactoryUtil.getSimpleDateFormat("MMMMM d, yyyy");

			TrainingEvent trainingEvent =
				trainingEventPersistence.findByPrimaryKey(
					trainingCustomer.getClassPK());

			TrainingCourse trainingCourse =
				trainingCoursePersistence.findByPrimaryKey(
					trainingEvent.getTrainingCourseId());

			ListType listType = ListTypeServiceUtil.getListType(
				trainingEvent.getPortalMinorVersion());

			subscriptionSender.setContextAttributes(
				"[$CERTIFICATE_KEY$]", trainingCertificate.getKey(),
				"[$CLASS_START_DATE$]",
				longDateFormatDate.format(trainingEvent.getStartDate()),
				"[$COURSE_NAME$]", trainingCourse.getName(),
				"[$CUSTOMER_FULL_NAME$]", userProfileHistory.getFullName(),
				"[$VERSION$]", listType.getName());

			subscriptionSender.addRuntimeSubscribers(
				userProfileHistory.getEmailAddress(),
				userProfileHistory.getFullName());
		}
		else if (trainingCustomer.getClassNameId() ==
					PortalUtil.getClassNameId(TrainingExamResult.class)) {

			TrainingExamResult trainingExamResult =
				trainingExamResultLocalService.getTrainingExamResult(
					trainingCustomer.getClassPK());

			TrainingCertificateTemplate trainingCertificateTemplate =
				trainingExamResult.getTrainingCertificateTemplate();

			subscriptionSender.setContextAttributes(
				"[$CERTIFICATE_KEY$]", trainingCertificate.getKey(),
				"[$CUSTOMER_FULL_NAME$]", userProfileHistory.getFullName(),
				"[$TRAINING_CERTIFICATE_TEMPLATE_NAME$]",
				trainingCertificateTemplate.getName());

			User user = trainingCustomer.getUser();

			subscriptionSender.addRuntimeSubscribers(
				user.getEmailAddress(), userProfileHistory.getFullName());
		}

		subscriptionSender.setFrom("no-reply@liferay.com", "Liferay Training");
		subscriptionSender.setHtmlFormat(true);

		PortletPreferences preferences = AdminUtil.getPortletPreferences();

		Map<Locale, String> bodyMap =
			AdminUtil.getEmailTrainingCertificateBodyMap(
				trainingCustomer.getClassNameId(),
				trainingCustomer.getClassPK(), preferences);

		subscriptionSender.setLocalizedBodyMap(bodyMap);

		Map<Locale, String> subjectMap =
			AdminUtil.getEmailTrainingCertificateSubjectMap(
				trainingCustomer.getClassNameId(),
				trainingCustomer.getClassPK(), preferences);

		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		subscriptionSender.setMailId(
			"training_certificate_id",
			trainingCertificate.getTrainingCertificateId());
		subscriptionSender.setPortletId(OSBPortletKeys.OSB_ADMIN);

		String fileName =
			"Training Certificate " + trainingCertificate.getKey() + ".pdf";

		File file = OSBFileUtil.createTempFile(fileName);

		createTrainingCertificate(trainingCustomer, new FileOutputStream(file));

		subscriptionSender.addFileAttachment(file, fileName);

		subscriptionSender.flushNotificationsAsync();
	}

	public TrainingCertificate updateCertifiedDate(
			long trainingCertificateId, Date date)
		throws PortalException, SystemException {

		TrainingCertificate trainingCertificate =
			trainingCertificatePersistence.findByPrimaryKey(
				trainingCertificateId);

		trainingCertificate.setCertifiedDate(date);

		return trainingCertificatePersistence.update(
			trainingCertificate, false);
	}

	public TrainingCertificate updateCertifiedDate(
			long trainingCertificateId, long trainingCustomerId)
		throws PortalException, SystemException {

		TrainingCertificate trainingCertificate =
			trainingCertificatePersistence.findByPrimaryKey(
				trainingCertificateId);

		trainingCertificate.setModifiedDate(new Date());

		trainingCustomerLocalService.updateStatus(
			trainingCustomerId, TrainingCustomerConstants.STATUS_CERTIFIED);

		trainingCertificate.setCertifiedDate(
			getCertifiedDate(trainingCustomerId));

		return trainingCertificatePersistence.update(
			trainingCertificate, false);
	}

	public TrainingCertificate updateTrainingCertificate(
			long trainingCertificateId, Date certifiedDate, int surveyStatus)
		throws PortalException, SystemException {

		TrainingCertificate trainingCertificate =
			trainingCertificatePersistence.findByPrimaryKey(
				trainingCertificateId);

		trainingCertificate.setModifiedDate(new Date());
		trainingCertificate.setCertifiedDate(certifiedDate);
		trainingCertificate.setSurveyStatus(surveyStatus);

		return trainingCertificatePersistence.update(
			trainingCertificate, false);
	}

	public TrainingCertificate updateTrainingCertificate(
			long trainingCertificateId, long userId, String emailAddress,
			String firstName, String lastName, String legalEntityName,
			int surveyStatus)
		throws PortalException, SystemException {

		TrainingCertificate trainingCertificate =
			trainingCertificatePersistence.findByPrimaryKey(
				trainingCertificateId);

		trainingCertificate.setModifiedDate(new Date());

		UserProfileHistory userProfileHistory = getUserProfileHistory(
			trainingCertificateId, userId, emailAddress, firstName, lastName,
			legalEntityName);

		trainingCertificate.setUserProfileHistoryId(
			userProfileHistory.getUserProfileHistoryId());

		TrainingCustomerLocalServiceUtil.updateStatus(
			trainingCertificate.getTrainingCustomerId(),
			TrainingCustomerConstants.STATUS_CERTIFIED);

		trainingCertificate.setCertifiedDate(
			getCertifiedDate(trainingCertificate.getTrainingCustomerId()));

		trainingCertificate.setSurveyStatus(surveyStatus);

		return trainingCertificatePersistence.update(
			trainingCertificate, false);
	}

	public TrainingCertificate updateUserProfileHistoryId(
			long trainingCertificateId, long userId, String emailAddress,
			String firstName, String lastName, String legalEntityName)
		throws PortalException, SystemException {

		TrainingCertificate trainingCertificate =
			trainingCertificatePersistence.findByPrimaryKey(
				trainingCertificateId);

		UserProfileHistory userProfileHistory = getUserProfileHistory(
			trainingCertificateId, userId, emailAddress, firstName, lastName,
			legalEntityName);

		trainingCertificate.setUserProfileHistoryId(
			userProfileHistory.getUserProfileHistoryId());

		return trainingCertificatePersistence.update(
			trainingCertificate, false);
	}

	protected void createTrainingCertificate(
			TrainingCustomer trainingCustomer, OutputStream outputStream)
		throws Exception {

		long trainingCertificateTemplateId = getTrainingCertificateTemplateId(
			trainingCustomer);

		if (trainingCertificateTemplateId == 0) {
			return;
		}

		InputStream inputStream =
			trainingCertificateTemplateLocalService.
				getTrainingCertificateTemplateAsStream(
					trainingCertificateTemplateId);

		PdfStamper pdfStamper = null;

		try {
			PdfReader pdfReader = new PdfReader(inputStream);

			pdfStamper = new PdfStamper(pdfReader, outputStream);

			pdfStamper.setFormFlattening(true);

			AcroFields acroFields = pdfStamper.getAcroFields();

			BaseFont chineseJapaneseSubstitutionFont = BaseFont.createFont(
				"/resources/fonts/fireflysung.ttf", BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED);

			acroFields.addSubstitutionFont(chineseJapaneseSubstitutionFont);

			BaseFont koreanSubstitutionFont = BaseFont.createFont(
				"/resources/fonts/UnDotum.ttf", BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED);

			acroFields.addSubstitutionFont(koreanSubstitutionFont);

			TrainingCertificate trainingCertificate =
				trainingCertificatePersistence.findByTrainingCustomerId(
					trainingCustomer.getTrainingCustomerId());

			UserProfileHistory userProfileHistory =
				userProfileHistoryPersistence.findByPrimaryKey(
					trainingCertificate.getUserProfileHistoryId());

			if (acroFields.getField("customerName") != null) {
				acroFields.setField(
					"customerName", userProfileHistory.getFullName());

				setFontSize(acroFields, "customerName");
			}

			if (acroFields.getField("key") != null) {
				acroFields.setField(
					"key", trainingCustomer.getTrainingCertificateKey());
			}

			if (trainingCustomer.getClassNameId() ==
					PortalUtil.getClassNameId(TrainingEvent.class)) {

				setTrainingEventCertificateFields(trainingCustomer, acroFields);
			}
			else if (trainingCustomer.getClassNameId() ==
						PortalUtil.getClassNameId(TrainingExamResult.class)) {

				setTrainingExamResultCertificateFields(
					trainingCustomer, acroFields);
			}
		}
		catch (Exception e) {
		}
		finally {
			if (pdfStamper != null) {
				pdfStamper.close();
			}

			StreamUtil.cleanUp(inputStream, outputStream);
		}
	}

	protected String generateKey() throws SystemException {
		String key = PwdGenerator.getPassword(
			PwdGenerator.KEY1 + PwdGenerator.KEY2, 15);

		try {
			trainingCertificatePersistence.findByKey(key);

			return generateKey();
		}
		catch (NoSuchTrainingCertificateException nstce) {
			return key;
		}
	}

	protected Date getCertifiedDate(long trainingCustomerId)
		throws PortalException, SystemException {

		TrainingCustomer trainingCustomer =
			trainingCustomerPersistence.findByPrimaryKey(trainingCustomerId);

		if (trainingCustomer.getClassNameId() ==
				PortalUtil.getClassNameId(TrainingEvent.class)) {

			TrainingEvent trainingEvent =
				trainingEventPersistence.findByPrimaryKey(
					trainingCustomer.getClassPK());

			return trainingEvent.getEndDate();
		}
		else if (trainingCustomer.getClassNameId() ==
					PortalUtil.getClassNameId(TrainingExamResult.class)) {

			TrainingExamResult trainingExamResult =
				trainingExamResultPersistence.findByPrimaryKey(
					trainingCustomer.getClassPK());

			return trainingExamResult.getStartDate();
		}

		return null;
	}

	protected Date getDate(Date date, TimeZone timeZone) {
		Calendar calendar = CalendarFactoryUtil.getCalendar(timeZone);

		calendar.setTime(date);

		return PortalUtil.getDate(
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
			calendar.get(Calendar.YEAR));
	}

	protected String getFileName(TrainingCustomer trainingCustomer)
		throws Exception {

		StringBundler sb = new StringBundler(4);

		TrainingCertificate trainingCertificate =
			trainingCertificatePersistence.findByTrainingCustomerId(
				trainingCustomer.getTrainingCustomerId());

		UserProfileHistory userProfileHistory =
			userProfileHistoryPersistence.findByPrimaryKey(
				trainingCertificate.getUserProfileHistoryId());

		sb.append(userProfileHistory.getFullName());
		sb.append(" - Liferay Inc - Training Certificate ");
		sb.append(trainingCertificate.getKey());
		sb.append(".pdf");

		return sb.toString();
	}

	protected long getTrainingCertificateTemplateId(
			TrainingCustomer trainingCustomer)
		throws Exception {

		if (trainingCustomer.getClassNameId() ==
				PortalUtil.getClassNameId(TrainingEvent.class)) {

			TrainingEvent trainingEvent =
				trainingEventPersistence.findByPrimaryKey(
					trainingCustomer.getClassPK());

			return trainingEvent.getTrainingCertificateTemplateId();
		}
		else if (trainingCustomer.getClassNameId() ==
					PortalUtil.getClassNameId(TrainingExamResult.class)) {

			TrainingExamResult trainingExamResult =
				trainingExamResultPersistence.findByPrimaryKey(
					trainingCustomer.getClassPK());

			TrainingExam trainingExam = trainingExamResult.getTrainingExam();

			return trainingExam.getTrainingCertificateTemplateId();
		}

		return 0;
	}

	protected UserProfileHistory getUserProfileHistory(
			long trainingCertificateId, long userId, String emailAddress,
			String firstName, String lastName, String legalEntityName)
		throws PortalException, SystemException {

		UserProfile userProfile = userProfileLocalService.addUserProfile(
			userId, emailAddress, firstName, lastName, legalEntityName);

		TrainingCertificate trainingCertificate =
			trainingCertificatePersistence.findByPrimaryKey(
				trainingCertificateId);

		UserProfileHistory userProfileHistory =
			userProfileHistoryLocalService.getUserProfileHistory(
				trainingCertificate.getUserProfileHistoryId());

		if (AdminUtil.equalsByUserProfile(userProfile, userProfileHistory)) {
			return userProfileHistory;
		}

		return userProfileHistoryLocalService.addUserProfileHistory(
			userId, PortalUtil.getClassNameId(TrainingCertificate.class),
			trainingCertificateId, userProfile.getEmailAddress(),
			userProfile.getFirstName(), userProfile.getLastName(),
			userProfile.getLegalEntityName());
	}

	protected void setFontSize(AcroFields acroFields, String fieldName)
		throws Exception {

		Chunk chunk = new Chunk(acroFields.getField(fieldName));

		AcroFields.Item item = acroFields.getFieldItem(fieldName);

		PdfDictionary pdfDictionary = item.getWidget(0);

		PdfString pdfString = (PdfString)pdfDictionary.get(PdfName.DA);

		String[] fontAttributes = StringUtil.split(
			pdfString.toString(), StringPool.SPACE);

		String fontName = fontAttributes[0].substring(1);

		float defaultFontSize = GetterUtil.getFloat(fontAttributes[1]);

		chunk.setFont(FontFactory.getFont(fontName, defaultFontSize));

		PdfArray pdfArray = (PdfArray)pdfDictionary.get(PdfName.RECT);

		PdfNumber lowerLeftX = pdfArray.getAsNumber(0);
		PdfNumber lowerLeftY = pdfArray.getAsNumber(1);
		PdfNumber upperRightX = pdfArray.getAsNumber(2);
		PdfNumber upperRightY = pdfArray.getAsNumber(3);

		Rectangle rectangle = new Rectangle(
			lowerLeftX.floatValue(), lowerLeftY.floatValue(),
			upperRightX.floatValue(), upperRightY.floatValue());

		for (float fontSize = defaultFontSize;
				chunk.getWidthPoint() > rectangle.getWidth(); fontSize--) {

			chunk.setFont(FontFactory.getFont(fontName, fontSize));
		}

		Font font = chunk.getFont();

		acroFields.setFieldProperty(
			fieldName, "textsize", font.getSize(), null);

		acroFields.regenerateField(fieldName);
	}

	protected void setTrainingEventCertificateFields(
			TrainingCustomer trainingCustomer, AcroFields acroFields)
		throws Exception {

		TrainingEvent trainingEvent = trainingEventPersistence.findByPrimaryKey(
			trainingCustomer.getClassPK());

		TimeZone timeZone = TimeZoneUtil.getTimeZone(
			trainingEvent.getTimeZoneId());

		Date startDate = getDate(trainingEvent.getStartDate(), timeZone);
		Date endDate = getDate(trainingEvent.getEndDate(), timeZone);

		String certifiedDate = acroFields.getField("certifiedDate");

		if (certifiedDate != null) {
			acroFields.setField("certifiedDate", _dateFormat.format(endDate));
		}

		String hours = acroFields.getField("hours");

		if (hours != null) {
			acroFields.setField(
				"hours",
				String.valueOf(
					(DateUtil.getDaysBetween(startDate, endDate) + 1) * 8));
		}

		ListType portalMinorVersion = listTypeService.getListType(
			trainingEvent.getPortalMinorVersion());

		StringBundler sb = new StringBundler(4);

		String trainingEventName = acroFields.getField("trainingEventName");

		if (trainingEventName != null) {
			sb.append(StringUtil.trim(trainingEventName));
			sb.append(StringPool.SPACE);
			sb.append(portalMinorVersion.getName());
			sb.append("EE");

			acroFields.setField("trainingEventName", sb.toString());

			setFontSize(acroFields, "trainingEventName");
		}

		String trainingWorkerDisplayHTML =
			trainingEvent.getTrainingWorkerDisplayHTML();

		if (Validator.isNotNull(trainingWorkerDisplayHTML) &&
			(acroFields.getField("workerName") != null)) {

			acroFields.setField("workerName", trainingWorkerDisplayHTML);

			setFontSize(acroFields, "workerName");
		}
	}

	protected void setTrainingExamResultCertificateFields(
			TrainingCustomer trainingCustomer, AcroFields acroFields)
		throws Exception {

		TrainingExamResult trainingExamResult =
			trainingExamResultPersistence.findByPrimaryKey(
				trainingCustomer.getClassPK());

		acroFields.setField(
			"certifiedDate",
			_dateFormat.format(trainingExamResult.getStartDate()));
	}

	private Format _dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
		"yyyy/MM/dd");

}