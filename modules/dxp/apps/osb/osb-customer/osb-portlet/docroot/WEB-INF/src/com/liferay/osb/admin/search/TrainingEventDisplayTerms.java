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

package com.liferay.osb.admin.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Calendar;
import java.util.Date;

import javax.portlet.PortletRequest;

/**
 * @author Val Nagy
 * @author Joan Kim
 */
public class TrainingEventDisplayTerms extends DisplayTerms {

	public static final String CITY = "city";

	public static final String CLIENT_NAME = "clientName";

	public static final String COUNTRY_ID = "countryId";

	public static final String COURSE = "course";

	public static final String LANGUAGE = "language";

	public static final String PARTNER = "partner";

	public static final String PORTAL_MINOR_VERSION = "portalMinorVersion";

	public static final String REGION_ID = "regionId";

	public static final String SCOPE = "scope";

	public static final String START_DATE_GT_DAY = "startDateGTDay";

	public static final String START_DATE_GT_MONTH = "startDateGTMonth";

	public static final String START_DATE_GT_YEAR = "startDateGTYear";

	public static final String START_DATE_LT_DAY = "startDateLTDay";

	public static final String START_DATE_LT_MONTH = "startDateLTMonth";

	public static final String START_DATE_LT_YEAR = "startDateLTYear";

	public static final String TRAINER_EMAIL_ADDRESS = "trainerEmailAddress";

	public static final String TRAINER_FIRST_NAME = "trainerFirstName";

	public static final String TRAINER_LAST_NAME = "trainerLastName";

	public static final String TYPE = "type";

	public TrainingEventDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		city = ParamUtil.getString(portletRequest, CITY);
		clientName = ParamUtil.getString(portletRequest, CLIENT_NAME);
		countryId = ParamUtil.getLong(portletRequest, COUNTRY_ID);
		course = ParamUtil.getString(portletRequest, COURSE);
		language = ParamUtil.getString(portletRequest, LANGUAGE);
		partner = ParamUtil.getString(portletRequest, PARTNER);
		portalMinorVersion = ParamUtil.getInteger(
			portletRequest, PORTAL_MINOR_VERSION);
		regionId = ParamUtil.getLong(portletRequest, REGION_ID);
		scope = ParamUtil.getInteger(portletRequest, SCOPE);
		startDateGTDay = ParamUtil.getInteger(
			portletRequest, START_DATE_GT_DAY);
		startDateGTMonth = ParamUtil.getInteger(
			portletRequest, START_DATE_GT_MONTH);
		startDateGTYear = ParamUtil.getInteger(
			portletRequest, START_DATE_GT_YEAR);
		startDateLTDay = ParamUtil.getInteger(
			portletRequest, START_DATE_LT_DAY);
		startDateLTMonth = ParamUtil.getInteger(
			portletRequest, START_DATE_LT_MONTH);
		startDateLTYear = ParamUtil.getInteger(
			portletRequest, START_DATE_LT_YEAR);
		trainerFirstName = ParamUtil.getString(
			portletRequest, TRAINER_FIRST_NAME);
		trainerLastName = ParamUtil.getString(
			portletRequest, TRAINER_LAST_NAME);
		trainerEmailAddress = ParamUtil.getString(
			portletRequest, TRAINER_EMAIL_ADDRESS);
		type = ParamUtil.getInteger(portletRequest, TYPE);
	}

	public String getCity() {
		return city;
	}

	public String getClientName() {
		return clientName;
	}

	public Long getCountryId() {
		return countryId;
	}

	public String getCourse() {
		return course;
	}

	public String getLanguage() {
		return language;
	}

	public String getPartner() {
		return partner;
	}

	public Integer getPortalMinorVersion() {
		return portalMinorVersion;
	}

	public Long getRegionId() {
		return regionId;
	}

	public int getScope() {
		return scope;
	}

	public int getStartDateGTDay() {
		return startDateGTDay;
	}

	public int getStartDateGTMonth() {
		return startDateGTMonth;
	}

	public int getStartDateGTYear() {
		return startDateGTYear;
	}

	public int getStartDateLTDay() {
		return startDateLTDay;
	}

	public int getStartDateLTMonth() {
		return startDateLTMonth;
	}

	public int getStartDateLTYear() {
		return startDateLTYear;
	}

	public String getTrainerEmailAddress() {
		return trainerEmailAddress;
	}

	public String getTrainerFirstName() {
		return trainerFirstName;
	}

	public String getTrainerLastName() {
		return trainerLastName;
	}

	public Integer getType() {
		return type;
	}

	public void setStartDateGT(Date startDateGT) {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.setTime(startDateGT);

		startDateGTDay = calendar.get(Calendar.DATE);
		startDateGTMonth = calendar.get(Calendar.MONTH);
		startDateGTYear = calendar.get(Calendar.YEAR);
	}

	public void setStartDateLT(Date startDateLT) {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.setTime(startDateLT);

		startDateLTDay = calendar.get(Calendar.DATE);
		startDateLTMonth = calendar.get(Calendar.MONTH);
		startDateLTYear = calendar.get(Calendar.YEAR);
	}

	protected String city;
	protected String clientName;
	protected Long countryId;
	protected String course;
	protected String language;
	protected String partner;
	protected Integer portalMinorVersion;
	protected Long regionId;
	protected int scope;
	protected int startDateGTDay;
	protected int startDateGTMonth;
	protected int startDateGTYear;
	protected int startDateLTDay;
	protected int startDateLTMonth;
	protected int startDateLTYear;
	protected String trainerEmailAddress;
	protected String trainerFirstName;
	protected String trainerLastName;
	protected Integer type;

}