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

import com.liferay.osb.model.TrainingEvent;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Val Nagy
 */
public class TrainingSurveySearch extends SearchContainer<TrainingEvent> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("type");
		headerNames.add("client-name");
		headerNames.add("location");
		headerNames.add("language");
		headerNames.add("partner");
		headerNames.add("start-date");
		headerNames.add("trainer");
		headerNames.add("template");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-surveys-were-found";

	public TrainingSurveySearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new TrainingEventDisplayTerms(portletRequest),
			new TrainingEventSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		TrainingEventDisplayTerms displayTerms =
			(TrainingEventDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			TrainingEventDisplayTerms.CITY, displayTerms.getCity());
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.CLIENT_NAME,
			displayTerms.getClientName());
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.COUNTRY_ID,
			String.valueOf(displayTerms.getCountryId()));
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.COURSE, displayTerms.getCourse());
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.LANGUAGE, displayTerms.getLanguage());
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.PARTNER, displayTerms.getPartner());
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.PORTAL_MINOR_VERSION,
			String.valueOf(displayTerms.getPortalMinorVersion()));
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.REGION_ID,
			String.valueOf(displayTerms.getRegionId()));
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.START_DATE_GT_DAY,
			String.valueOf(displayTerms.getStartDateGTDay()));
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.START_DATE_GT_MONTH,
			String.valueOf(displayTerms.getStartDateGTMonth()));
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.START_DATE_GT_YEAR,
			String.valueOf(displayTerms.getStartDateGTYear()));
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.START_DATE_LT_DAY,
			String.valueOf(displayTerms.getStartDateLTDay()));
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.START_DATE_LT_MONTH,
			String.valueOf(displayTerms.getStartDateLTMonth()));
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.START_DATE_LT_YEAR,
			String.valueOf(displayTerms.getStartDateLTYear()));
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.TRAINER_FIRST_NAME,
			displayTerms.getTrainerFirstName());
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.TRAINER_LAST_NAME,
			displayTerms.getTrainerLastName());
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.TRAINER_EMAIL_ADDRESS,
			displayTerms.getTrainerEmailAddress());
		iteratorURL.setParameter(
			TrainingEventDisplayTerms.TYPE,
			String.valueOf(displayTerms.getType()));
	}

}