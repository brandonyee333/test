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

package com.liferay.osb.servlet;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.TrainingCertificate;
import com.liferay.osb.model.TrainingCertificateTemplate;
import com.liferay.osb.model.TrainingCertificateTemplateConstants;
import com.liferay.osb.model.TrainingCourse;
import com.liferay.osb.model.TrainingCustomer;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.model.TrainingEventConstants;
import com.liferay.osb.model.TrainingExamResult;
import com.liferay.osb.model.TrainingLocation;
import com.liferay.osb.model.UserProfileHistory;
import com.liferay.osb.restful.servlet.NoResourceException;
import com.liferay.osb.restful.servlet.SimpleRestfulServlet;
import com.liferay.osb.service.TrainingCertificateLocalServiceUtil;
import com.liferay.osb.service.TrainingCustomerLocalServiceUtil;
import com.liferay.osb.service.TrainingEventLocalServiceUtil;
import com.liferay.osb.service.TrainingExamResultLocalServiceUtil;
import com.liferay.osb.service.UserProfileHistoryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.User;
import com.liferay.portal.service.CountryServiceUtil;
import com.liferay.portal.service.ListTypeServiceUtil;

import java.io.IOException;

import java.text.DateFormat;
import java.text.Format;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Haote Chou
 * @author Yury Butrymovich
 */
public class TrainingServlet extends SimpleRestfulServlet {

	public void getTrainingCertificates(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException, PortalException, SystemException {

		String certificationKey = ParamUtil.getString(request, "key");
		String lastName = ParamUtil.getString(request, "lastName");
		int trainingCertificationType = ParamUtil.getInteger(
			request, "type", -1);

		long classNameId = -1;

		if (trainingCertificationType ==
				TrainingCertificateTemplateConstants.TYPE_TRAINING_EXAM) {

			classNameId = PortalUtil.getClassNameId(TrainingExamResult.class);
		}
		else if (trainingCertificationType ==
					TrainingCertificateTemplateConstants.TYPE_TRAINING_EVENT) {

			classNameId = PortalUtil.getClassNameId(TrainingEvent.class);
		}

		TrainingCertificate trainingCertificate =
			TrainingCertificateLocalServiceUtil.getTrainingCertificate(
				certificationKey, lastName, classNameId);

		JSONObject jsonObject = getTrainingCertificateJSONObject(
			trainingCertificate);

		ServletResponseUtil.write(response, jsonObject.toString());
	}

	public void getTrainingEvents(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		long[] trainingCourseIds = ParamUtil.getLongValues(
			request, "trainingCourseIds");

		long startDateTime = ParamUtil.getLong(
			request, "startDate", System.currentTimeMillis());

		Date startDate = new Date(startDateTime);

		String[] countryA2Codes = ParamUtil.getParameterValues(
			request, "countryA2Codes");
		boolean online = ParamUtil.getBoolean(request, "online");
		String portalMinorVersion = ParamUtil.getString(
			request, "portalMinorVersion");
		int start = ParamUtil.getInteger(request, "start", QueryUtil.ALL_POS);
		int end = ParamUtil.getInteger(request, "end", QueryUtil.ALL_POS);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (request.getParameter("trainingCourseIds") != null) {
			params.put("trainingCourseIds", trainingCourseIds);
		}

		params.put("startDate", startDate);

		if (request.getParameter("countryA2Codes") != null) {
			params.put("countryIds", getCountryIds(countryA2Codes));
		}

		if (request.getParameter("online") != null) {
			params.put("online", online);
		}

		if (request.getParameter("portalMinorVersion") != null) {
			int portalMinorVersionListTypeId = getPortalMinorVersionListTypeId(
				portalMinorVersion);

			if (portalMinorVersionListTypeId > 0) {
				params.put("portalMinorVersion", portalMinorVersionListTypeId);
			}
		}

		params.put("type", TrainingEventConstants.TYPE_PUBLIC);

		OrderByComparator obc = OrderByComparatorFactoryUtil.create(
			"OSB_TrainingEvent", "startDate", true);

		List<TrainingEvent> trainingEvents =
			TrainingEventLocalServiceUtil.getTrainingEvents(
				params, start, end, obc);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (TrainingEvent trainingEvent : trainingEvents) {
			jsonArray.put(getTrainingEventJSONObject(trainingEvent));
		}

		jsonObject.put("trainingEvents", jsonArray);

		if (_log.isDebugEnabled()) {
			_log.debug("Response " + jsonObject.toString());
		}

		ServletResponseUtil.write(response, jsonObject.toString());
	}

	protected String getCountryIds(String[] countryA2Codes)
		throws SystemException {

		StringBundler sb = new StringBundler(countryA2Codes.length * 2);

		for (String countryA2Code : countryA2Codes) {
			Country country = CountryServiceUtil.fetchCountryByA2(
				StringUtil.toUpperCase(countryA2Code));

			if (country != null) {
				sb.append(country.getCountryId());
				sb.append(StringPool.COMMA);
			}
		}

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	protected int getPortalMinorVersionListTypeId(String portalMinorVersion)
		throws SystemException {

		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(
			"com.liferay.osb.model.ProductEntry.portalMinorVersions");

		String name = null;

		for (ListType listType : listTypes) {
			name = listType.getName();

			if (name.equals(portalMinorVersion)) {
				return listType.getListTypeId();
			}
		}

		return 0;
	}

	@Override
	protected String getResourceKey(String path) {
		return null;
	}

	@Override
	protected String getResourceName(String path) throws NoResourceException {
		if (Validator.isNull(path)) {
			throw new NoResourceException();
		}

		return path;
	}

	protected JSONObject getTrainingCertificateJSONObject(
			TrainingCertificate trainingCertificate)
		throws PortalException, SystemException {

		TrainingCustomer trainingCustomer =
			TrainingCustomerLocalServiceUtil.getTrainingCustomer(
				trainingCertificate.getTrainingCustomerId());

		UserProfileHistory trainingCustomerUserProfileHistory =
			UserProfileHistoryLocalServiceUtil.fetchUserProfileHistory(
				trainingCustomer.getUserProfileHistoryId());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			"customerName", trainingCustomerUserProfileHistory.getFullName());

		if (trainingCustomer.getClassNameId() ==
				PortalUtil.getClassNameId(TrainingExamResult.class)) {

			TrainingExamResult trainingExamResult =
				TrainingExamResultLocalServiceUtil.getTrainingExamResult(
					trainingCustomer.getClassPK());

			Format longDateFormatDate =
				FastDateFormatFactoryUtil.getDate(
					DateFormat.LONG, LocaleUtil.getDefault(),
					TimeZoneUtil.getDefault());

			jsonObject.put(
				"certificationDate",
				longDateFormatDate.format(trainingExamResult.getStartDate()));

			TrainingCertificateTemplate trainingCertificateTemplate =
				trainingExamResult.getTrainingCertificateTemplate();

			jsonObject.put(
				"certificationType", trainingCertificateTemplate.getName());

			jsonObject.put(
				"certificateNumber",
				trainingExamResult.getTrainingCertificateKey());
		}
		else if (trainingCustomer.getClassNameId() ==
					PortalUtil.getClassNameId(TrainingEvent.class)) {

			TrainingEvent trainingEvent =
				TrainingEventLocalServiceUtil.getTrainingEvent(
					trainingCustomer.getClassPK());

			TrainingCourse trainingCourse = trainingEvent.getTrainingCourse();

			jsonObject.put("courseName", trainingCourse.getName());

			ListType portalMinorVersionType =
				ListTypeServiceUtil.getListType(
					trainingEvent.getPortalMinorVersion());

			jsonObject.put("courseVersion", portalMinorVersionType.getName());

			TimeZone trainingEventTimeZone = TimeZoneUtil.getTimeZone(
				trainingEvent.getTimeZoneId());

			User trainingCustomerUser = trainingCustomer.getUser();

			DateFormat trainingCustomerDateFormat =
				DateFormatFactoryUtil.getDate(
					trainingCustomerUser.getLocale(), trainingEventTimeZone);

			jsonObject.put(
				"courseDate",
				trainingCustomerDateFormat.format(
					trainingCertificate.getCertifiedDate()));

			jsonObject.put(
				"courseLocation", trainingEvent.getAddressDisplayHTML());
			jsonObject.put(
				"courseInstructorName",
				trainingEvent.getTrainingWorkerDisplayHTML());
			jsonObject.put(
				"certificateNumber",
				trainingCustomer.getTrainingCertificateKey());
		}

		return jsonObject;
	}

	protected JSONObject getTrainingEventJSONObject(TrainingEvent trainingEvent)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("endDate", trainingEvent.getEndDate());
		jsonObject.put("enrollmentURL", trainingEvent.getEnrollmentURL());

		Locale locale = LocaleUtil.fromLanguageId(
			trainingEvent.getLanguageId(), false);

		jsonObject.put("language", locale.getDisplayLanguage());

		Address address = trainingEvent.getAddress();

		Country country = address.getCountry();

		jsonObject.put("country", country.getA2());

		ListType portalMinorVersionListType =
			ListTypeServiceUtil.getListType(
				trainingEvent.getPortalMinorVersion());

		jsonObject.put(
			"portalMinorVersion", portalMinorVersionListType.getName());

		jsonObject.put(
			"startDate", _dateFormat.format(trainingEvent.getStartDate()));

		TimeZone timeZone = TimeZoneUtil.getTimeZone(
			trainingEvent.getTimeZoneId());

		Date startDate = trainingEvent.getStartDate();

		boolean daylightTime = timeZone.inDaylightTime(startDate);

		String timeZoneDisplayName = timeZone.getDisplayName(
			daylightTime, TimeZone.SHORT, locale);

		jsonObject.put("timeZone", timeZoneDisplayName);

		TrainingCourse trainingCourse = trainingEvent.getTrainingCourse();

		jsonObject.put("trainingCourse", trainingCourse.getName());

		jsonObject.put("trainingCourseId", trainingEvent.getTrainingCourseId());

		if (trainingEvent.getTrainingLocationId() == 0) {
			jsonObject.put("trainingLocation", StringPool.BLANK);
		}
		else {
			TrainingLocation trainingLocation =
				trainingEvent.getTrainingLocation();

			jsonObject.put("trainingLocation", trainingLocation.getName());
		}

		return jsonObject;
	}

	@Override
	protected boolean isAuthorized(HttpServletRequest request) {
		return true;
	}

	private static Log _log = LogFactoryUtil.getLog(TrainingServlet.class);

	private Format _dateFormat = FastDateFormatFactoryUtil.getSimpleDateFormat(
		"MMM dd, yyyy hh:mm a");

}