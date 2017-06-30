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

import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.TrainingEventEndDateException;
import com.liferay.osb.TrainingEventStartDateException;
import com.liferay.osb.admin.util.AdminUtil;
import com.liferay.osb.admin.util.TrainingEmailUtil;
import com.liferay.osb.model.TrainingCourse;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingCustomerConstants;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingEventConstants;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.service.base.TrainingEventLocalServiceBaseImpl;
import com.liferay.osb.util.DDLRecordSetConstants;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.OSBUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CountryServiceUtil;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;

import java.text.Format;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 * @author Joan Kim
 * @author Haote Chou
 * @author Yury Butrymovich
 */
public class TrainingEventLocalServiceImpl
	extends TrainingEventLocalServiceBaseImpl {

	public TrainingEventLocalServiceImpl() {
		_liferayTrainingEmailAddresses.put("AU", "training-au@liferay.com");
		_liferayTrainingEmailAddresses.put("BR", "training-br@liferay.com");
		_liferayTrainingEmailAddresses.put("CN", "training-cn@liferay.com");
		_liferayTrainingEmailAddresses.put("ES", "training-es@liferay.com");
		_liferayTrainingEmailAddresses.put("FR", "training-fr@liferay.com");
		_liferayTrainingEmailAddresses.put("IN", "training-in@liferay.com");
		_liferayTrainingEmailAddresses.put("US", "training-us@liferay.com");

		String[] euCountryCodes = OSBUtil.EU_COUNTRY_CODES;

		for (String euCountryCode : euCountryCodes) {
			if (!_liferayTrainingEmailAddresses.containsKey(euCountryCode)) {
				_liferayTrainingEmailAddresses.put(
					euCountryCode, "training-eu@liferay.com");
			}
		}
	}

	public TrainingEvent addTrainingEvent(
			long userId, long ddmStructureId, long partnerEntryId,
			long trainingCertificateTemplateId, long trainingCourseId,
			long trainingLocationId, String name, String emailAddress,
			int portalMinorVersion, int type, String languageId,
			boolean localizedSlides, String timeZoneId, int startDateMonth,
			int startDateDay, int startDateYear, int startDateHour,
			int startDateMinute, int endDateMonth, int endDateDay,
			int endDateYear, int endDateHour, int endDateMinute,
			String addressStreet1, String addressStreet2, String addressStreet3,
			String addressCity, String addressZip, long addressRegionId,
			long addressCountryId, int maxCustomers, String enrollmentURL,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String liferayTrainingEmailAddress = getLiferayTrainingEmailAddress(
			addressCountryId);

		if (!emailAddress.matches(liferayTrainingEmailAddress)) {
			StringBundler sb = new StringBundler(3);

			sb.append(liferayTrainingEmailAddress);
			sb.append(StringPool.NEW_LINE);
			sb.append(emailAddress);

			emailAddress = sb.toString();
		}

		validate(trainingCourseId, portalMinorVersion);

		long trainingEventId = counterLocalService.increment();

		TrainingEvent trainingEvent = trainingEventPersistence.create(
			trainingEventId);

		User user = userPersistence.findByPrimaryKey(userId);

		trainingEvent.setUserId(user.getUserId());
		trainingEvent.setUserName(user.getFullName());

		Date now = new Date();

		trainingEvent.setCreateDate(now);
		trainingEvent.setModifiedDate(now);

		if (ddmStructureId > 0) {
			DDLRecordSet ddlRecordSet = addDDLRecordSet(
				user.getUserId(), ddmStructureId, serviceContext);

			trainingEvent.setDDLRecordSetId(ddlRecordSet.getRecordSetId());
		}

		trainingEvent.setPartnerEntryId(partnerEntryId);
		trainingEvent.setTrainingCertificateTemplateId(
			trainingCertificateTemplateId);
		trainingEvent.setTrainingCourseId(trainingCourseId);
		trainingEvent.setTrainingLocationId(trainingLocationId);
		trainingEvent.setName(name);
		trainingEvent.setEmailAddress(emailAddress);
		trainingEvent.setPortalMinorVersion(portalMinorVersion);
		trainingEvent.setType(type);
		trainingEvent.setLanguageId(languageId);
		trainingEvent.setLocalizedSlides(localizedSlides);

		TimeZone timeZone = TimeZoneUtil.getTimeZone(timeZoneId);

		trainingEvent.setTimeZoneId(timeZoneId);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, timeZone, TrainingEventStartDateException.class);

		trainingEvent.setStartDate(startDate);

		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			timeZone, TrainingEventEndDateException.class);

		trainingEvent.setEndDate(endDate);

		Address address = addressLocalService.addAddress(
			userId, TrainingEvent.class.getName(), trainingEventId,
			addressStreet1, addressStreet2, addressStreet3, addressCity,
			addressZip, addressRegionId, addressCountryId, 0, true, true);

		trainingEvent.setAddressId(address.getAddressId());

		trainingEvent.setMaxCustomers(maxCustomers);
		trainingEvent.setEnrollmentURL(enrollmentURL);

		trainingEventPersistence.update(trainingEvent, false);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			TrainingEvent.class);

		indexer.reindex(trainingEvent);

		return trainingEvent;
	}

	public void checkTrainingEventSurveys(int weeksPast) throws Exception {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.add(Calendar.WEEK_OF_YEAR, (weeksPast * -1));

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date endDateGT = calendar.getTime();

		calendar.add(Calendar.DATE, 1);

		Date endDateLT = calendar.getTime();

		List<TrainingEvent> trainingEvents = trainingEventFinder.findByEndDate(
			endDateGT, endDateLT, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (TrainingEvent trainingEvent : trainingEvents) {
			TrainingEmailUtil.sendTrainingSurveyReminderMail(trainingEvent);
		}
	}

	@Override
	public TrainingEvent deleteTrainingEvent(long trainingEventId)
		throws PortalException, SystemException {

		TrainingEvent trainingEvent = trainingEventPersistence.findByPrimaryKey(
			trainingEventId);

		return deleteTrainingEvent(trainingEvent);
	}

	@Override
	public TrainingEvent deleteTrainingEvent(TrainingEvent trainingEvent)
		throws PortalException, SystemException {

		// Training customer

		trainingCustomerLocalService.deleteTrainingCustomers(
			PortalUtil.getClassNameId(TrainingEvent.class),
			trainingEvent.getTrainingEventId());

		// Training event

		trainingEventPersistence.remove(trainingEvent);

		// Training worker

		trainingWorkerPersistence.removeByC_C(
			PortalUtil.getClassNameId(TrainingEvent.class),
			trainingEvent.getTrainingEventId());

		// Address

		addressLocalService.deleteAddress(trainingEvent.getAddressId());

		// DDL record set

		if (trainingEvent.getDDLRecordSetId() > 0) {
			ddlRecordSetLocalService.deleteRecordSet(
				trainingEvent.getDDLRecordSetId());
		}

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			TrainingEvent.class);

		indexer.delete(trainingEvent);

		return trainingEvent;
	}

	public TrainingEvent fetchTrainingEventByDDLRecordSetId(long ddlRecordSetId)
		throws SystemException {

		return trainingEventPersistence.fetchByDDLRecordSetId(ddlRecordSetId);
	}

	public TrainingEvent getTrainingEventByDDLRecordSetId(long ddlRecordSetId)
		throws PortalException, SystemException {

		return trainingEventPersistence.findByDDLRecordSetId(ddlRecordSetId);
	}

	public List<TrainingEvent> getTrainingEvents(
			int type, Date startDate, int start, int end, OrderByComparator obc)
		throws PortalException, SystemException {

		return trainingEventPersistence.findByT_SD(
			type, startDate, start, end, obc);
	}

	public List<TrainingEvent> getTrainingEvents(
			LinkedHashMap<String, Object> params, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		if ((params == null) || params.isEmpty()) {
			return trainingEventPersistence.findAll(start, end, obc);
		}

		return trainingEventFinder.findByParams(params, start, end, obc);
	}

	public List<TrainingEvent> getTrainingEvents(
			long trainingCourseId, int type, Date startDate, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		return trainingEventPersistence.findByTC_T_SD(
			trainingCourseId, type, startDate, start, end, obc);
	}

	public List<TrainingEvent> getTrainingEvents(
			long userId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return trainingEventFinder.findByUserId(userId, start, end, obc);
	}

	public List<TrainingEvent> getTrainingEvents(
			long[] trainingCourseIds, Date endDateGT, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return trainingEventFinder.findByTCI_GtED(
			trainingCourseIds, endDateGT, start, end, obc);
	}

	public int getTrainingEventsCount(long userId) throws SystemException {
		return trainingEventFinder.countByUserId(userId);
	}

	public int getTrainingEventsCount(long[] trainingCourseIds, Date endDateGT)
		throws SystemException {

		return trainingEventFinder.countByTCI_GtED(
			trainingCourseIds, endDateGT);
	}

	public List<TrainingEvent> search(
			Integer type, String name, String course,
			Integer portalMinorVersion, String city, Long regionId,
			Long countryId, String language, String partner, int startDateGTDay,
			int startDateGTMonth, int startDateGTYear, int startDateLTDay,
			int startDateLTMonth, int startDateLTYear, String trainerFirstName,
			String trainerLastName, String trainerEmailAddress,
			LinkedHashMap<String, Object> params, boolean andOperator,
			int start, int end, OrderByComparator obc)
		throws SystemException {

		Date startDateGT = PortalUtil.getDate(
			startDateGTMonth, startDateGTDay, startDateGTYear);
		Date startDateLT = PortalUtil.getDate(
			startDateLTMonth, startDateLTDay, startDateLTYear);

		return trainingEventFinder.findByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(
			type, name, course, portalMinorVersion, city, regionId, countryId,
			language, partner, startDateGT, startDateLT, trainerFirstName,
			trainerLastName, trainerEmailAddress, params, andOperator, start,
			end, obc);
	}

	public List<TrainingEvent> search(
			String keywords, Long regionId, Long countryId, int startDateGTDay,
			int startDateGTMonth, int startDateGTYear, int startDateLTDay,
			int startDateLTMonth, int startDateLTYear,
			LinkedHashMap<String, Object> params, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		Date startDateGT = PortalUtil.getDate(
			startDateGTMonth, startDateGTDay, startDateGTYear);
		Date startDateLT = PortalUtil.getDate(
			startDateLTMonth, startDateLTDay, startDateLTYear);

		return trainingEventFinder.findByKeywords(
			keywords, regionId, countryId, startDateGT, startDateLT, params,
			start, end, obc);
	}

	public int searchCount(
			Integer type, String name, String course,
			Integer portalMinorVersion, String city, Long regionId,
			Long countryId, String language, String partner, int startDateGTDay,
			int startDateGTMonth, int startDateGTYear, int startDateLTDay,
			int startDateLTMonth, int startDateLTYear, String trainerFirstName,
			String trainerLastName, String trainerEmailAddress,
			LinkedHashMap<String, Object> params, boolean andOperator)
		throws SystemException {

		Date startDateGT = PortalUtil.getDate(
			startDateGTMonth, startDateGTDay, startDateGTYear);
		Date startDateLT = PortalUtil.getDate(
			startDateLTMonth, startDateLTDay, startDateLTYear);

		return trainingEventFinder.countByT_N_C_PMV_C_R_C_L_P_SD_TFN_TLN_TEA(
			type, name, course, portalMinorVersion, city, regionId, countryId,
			language, partner, startDateGT, startDateLT, trainerFirstName,
			trainerLastName, trainerEmailAddress, params, andOperator);
	}

	public int searchCount(
			String keywords, Long regionId, Long countryId, int startDateGTDay,
			int startDateGTMonth, int startDateGTYear, int startDateLTDay,
			int startDateLTMonth, int startDateLTYear,
			LinkedHashMap<String, Object> params)
		throws SystemException {

		Date startDateGT = PortalUtil.getDate(
			startDateGTMonth, startDateGTDay, startDateGTYear);
		Date startDateLT = PortalUtil.getDate(
			startDateLTMonth, startDateLTDay, startDateLTYear);

		return trainingEventFinder.countByKeywords(
			keywords, regionId, countryId, startDateGT, startDateLT, params);
	}

	public void sendTrainingSurvey(
			long trainingCustomerId, String trainingSurveyURL)
		throws Exception {

		TrainingCustomer trainingCustomer =
			trainingCustomerPersistence.findByPrimaryKey(trainingCustomerId);

		if ((trainingCustomer.getClassNameId() !=
				PortalUtil.getClassNameId(TrainingEvent.class)) ||
			(trainingCustomer.getStatus() !=
				TrainingCustomerConstants.
					STATUS_PENDING_PARTICIPANT_FORM_COMPLETION)) {

			return;
		}

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setCompanyId(OSBConstants.COMPANY_ID);

		TrainingEvent trainingEvent = trainingEventPersistence.findByPrimaryKey(
			trainingCustomer.getClassPK());

		String timeZoneId = trainingEvent.getTimeZoneId();

		TimeZone timeZone = TimeZoneUtil.getTimeZone(timeZoneId);

		Format longDateFormatDate =
			FastDateFormatFactoryUtil.getSimpleDateFormat("MMMMM d", timeZone);

		TrainingCourse trainingCourse =
			trainingCoursePersistence.findByPrimaryKey(
				trainingEvent.getTrainingCourseId());

		UserProfileHistory userProfileHistory =
			userProfileHistoryPersistence.findByPrimaryKey(
				trainingCustomer.getUserProfileHistoryId());

		StringBundler sb = new StringBundler(3);

		sb.append(longDateFormatDate.format(trainingEvent.getStartDate()));

		if (!trainingEvent.isDurationSingleDay()) {
			sb.append(" - ");
			sb.append(longDateFormatDate.format(trainingEvent.getEndDate()));
		}

		subscriptionSender.setContextAttributes(
			"[$CLASS_DATES$]", sb.toString(), "[$COURSE_NAME$]",
			trainingCourse.getName(), "[$CUSTOMER_FULL_NAME$]",
			userProfileHistory.getFullName(), "[$TRAINING_SURVEY_URL$]",
			trainingSurveyURL);

		subscriptionSender.setFrom("no-reply@liferay.com", "Liferay Training");
		subscriptionSender.setHtmlFormat(true);

		PortletPreferences preferences = AdminUtil.getPortletPreferences();

		Map<Locale, String> bodyMap =
			AdminUtil.getEmailTrainingSurveyBodyMap(
				trainingCustomer.getClassNameId(),
				trainingCustomer.getClassPK(), preferences);

		subscriptionSender.setLocalizedBodyMap(bodyMap);

		Map<Locale, String> subjectMap =
			AdminUtil.getEmailTrainingSurveySubjectMap(
				trainingCustomer.getClassNameId(),
				trainingCustomer.getClassPK(), preferences);

		subscriptionSender.setLocalizedSubjectMap(subjectMap);

		subscriptionSender.setMailId(
			"training_survey_id", trainingCustomer.getTrainingCustomerId());
		subscriptionSender.setPortletId(OSBPortletKeys.OSB_ADMIN);

		subscriptionSender.addRuntimeSubscribers(
			userProfileHistory.getEmailAddress(),
			userProfileHistory.getFullName());

		subscriptionSender.flushNotificationsAsync();
	}

	public TrainingEvent updateTrainingEvent(
			long trainingEventId, long ddmStructureId, long ddlRecordSetId,
			long partnerEntryId, long trainingCertificateTemplateId,
			long trainingCourseId, long trainingLocationId, String name,
			String emailAddress, int portalMinorVersion, int type,
			String languageId, boolean localizedSlides, String timeZoneId,
			int startDateMonth, int startDateDay, int startDateYear,
			int startDateHour, int startDateMinute, int endDateMonth,
			int endDateDay, int endDateYear, int endDateHour, int endDateMinute,
			String addressStreet1, String addressStreet2, String addressStreet3,
			String addressCity, String addressZip, long addressRegionId,
			long addressCountryId, int maxCustomers, String enrollmentURL,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		// Training event

		validate(trainingCourseId, portalMinorVersion);

		TrainingEvent trainingEvent = trainingEventPersistence.findByPrimaryKey(
			trainingEventId);

		trainingEvent.setModifiedDate(new Date());

		DDLRecordSet ddlRecordSet = null;

		if ((ddlRecordSetId <= 0) && (ddmStructureId > 0)) {
			ddlRecordSet = addDDLRecordSet(
				trainingEvent.getUserId(), ddmStructureId, serviceContext);
		}
		else if (ddlRecordSetId > 0) {
			ddlRecordSet = updateDDLRecordSet(
				ddlRecordSetId, ddmStructureId, serviceContext);
		}

		if (ddlRecordSet != null) {
			ddlRecordSetId = ddlRecordSet.getRecordSetId();
		}

		trainingEvent.setDDLRecordSetId(ddlRecordSetId);

		trainingEvent.setPartnerEntryId(partnerEntryId);
		trainingEvent.setTrainingCertificateTemplateId(
			trainingCertificateTemplateId);
		trainingEvent.setTrainingCourseId(trainingCourseId);

		if (trainingLocationId > 0) {
			trainingEvent.setTrainingLocationId(trainingLocationId);
		}

		trainingEvent.setName(name);
		trainingEvent.setEmailAddress(emailAddress);
		trainingEvent.setPortalMinorVersion(portalMinorVersion);
		trainingEvent.setType(type);
		trainingEvent.setLanguageId(languageId);
		trainingEvent.setLocalizedSlides(localizedSlides);

		TimeZone timeZone = TimeZoneUtil.getTimeZone(timeZoneId);

		trainingEvent.setTimeZoneId(timeZoneId);

		Date startDate = PortalUtil.getDate(
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, timeZone, TrainingEventStartDateException.class);

		trainingEvent.setStartDate(startDate);

		Date endDate = PortalUtil.getDate(
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			timeZone, TrainingEventEndDateException.class);

		trainingEvent.setEndDate(endDate);
		trainingEvent.setMaxCustomers(maxCustomers);
		trainingEvent.setEnrollmentURL(enrollmentURL);

		trainingEventPersistence.update(trainingEvent, false);

		addressLocalService.updateAddress(
			trainingEvent.getAddressId(), addressStreet1, addressStreet2,
			addressStreet3, addressCity, addressZip, addressRegionId,
			addressCountryId, 0, true, true);

		// Indexer

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			TrainingEvent.class);

		indexer.reindex(trainingEvent);

		return trainingEvent;
	}

	protected DDLRecordSet addDDLRecordSet(
			long userId, long ddmStructureId, ServiceContext serviceContext)
		throws PortalException, SystemException {

		return ddlRecordSetLocalService.addRecordSet(
			userId, OSBConstants.GROUP_GUEST_ID, ddmStructureId, null, null,
			null, DDLRecordSetConstants.MIN_DISPLAY_ROWS_DEFAULT,
			DDLRecordSetConstants.SCOPE_TRAINING_EVENTS, serviceContext);
	}

	protected String getLiferayTrainingEmailAddress(long countryId)
		throws PortalException, SystemException {

		Country country = CountryServiceUtil.getCountry(countryId);

		return getLiferayTrainingEmailAddress(country.getA2());
	}

	protected String getLiferayTrainingEmailAddress(String countryCode) {
		return GetterUtil.getString(
			_liferayTrainingEmailAddresses.get(countryCode));
	}

	protected DDLRecordSet updateDDLRecordSet(
			long ddlRecordSetId, long ddmStructureId,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDLRecordSet ddlRecordSet = ddlRecordSetPersistence.findByPrimaryKey(
			ddlRecordSetId);

		return ddlRecordSetLocalService.updateRecordSet(
			ddlRecordSetId, ddmStructureId,
			LocalizationUtil.getLocalizationMap(ddlRecordSet.getName()),
			LocalizationUtil.getLocalizationMap(ddlRecordSet.getDescription()),
			DDLRecordSetConstants.MIN_DISPLAY_ROWS_DEFAULT, serviceContext);
	}

	protected void validate(long trainingCourseId, int portalMinorVersion)
		throws PortalException, SystemException {

		trainingCoursePersistence.findByPrimaryKey(trainingCourseId);

		ListTypeServiceUtil.validate(
			portalMinorVersion,
			TrainingEventConstants.LIST_TYPE_PORTAL_MINOR_VERSIONS);
	}

	private Map<String, String> _liferayTrainingEmailAddresses =
		new HashMap<String, String>();

}