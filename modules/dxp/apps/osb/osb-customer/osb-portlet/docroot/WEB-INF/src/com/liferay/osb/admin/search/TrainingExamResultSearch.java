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

import com.liferay.osb.model.TrainingExamResult;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Calvin Keum
 */
public class TrainingExamResultSearch
	extends SearchContainer<TrainingExamResult> {

	static List<String> headerNames = new ArrayList<String>();
	static Map<String, String> orderableHeaders = new HashMap<String, String>();

	static {
		headerNames.add("first-name");
		headerNames.add("last-name");
		headerNames.add("email-address");
		headerNames.add("profiles");
		headerNames.add("candidate-id");
		headerNames.add("registration-number");
		headerNames.add("test-date");
		headerNames.add("test-start-time");
		headerNames.add("test-id");
		headerNames.add("form-id");
		headerNames.add("correct-items");
		headerNames.add("incorrect-items");
		headerNames.add("skipped-items");
		headerNames.add("result");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"there-are-no-training-exam-results";

	public TrainingExamResultSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new TrainingExamResultDisplayTerms(portletRequest),
			new TrainingExamResultSearchTerms(portletRequest),
			DEFAULT_CUR_PARAM, DEFAULT_DELTA, iteratorURL, headerNames,
			EMPTY_RESULTS_MESSAGE);

		TrainingExamResultDisplayTerms displayTerms =
			(TrainingExamResultDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			TrainingExamResultDisplayTerms.FIRST_NAME,
			displayTerms.getFirstName());
		iteratorURL.setParameter(
			TrainingExamResultDisplayTerms.LAST_NAME,
			displayTerms.getLastName());
		iteratorURL.setParameter(
			TrainingExamResultDisplayTerms.EMAIL_ADDRESS,
			displayTerms.getEmailAddress());
		iteratorURL.setParameter(
			TrainingExamResultDisplayTerms.CANDIDATE_KEY,
			displayTerms.getCandidateKey());
		iteratorURL.setParameter(
			TrainingExamResultDisplayTerms.START_DATE_GT_DAY,
			String.valueOf(displayTerms.getStartDateGTDay()));
		iteratorURL.setParameter(
			TrainingExamResultDisplayTerms.START_DATE_GT_MONTH,
			String.valueOf(displayTerms.getStartDateGTMonth()));
		iteratorURL.setParameter(
			TrainingExamResultDisplayTerms.START_DATE_GT_YEAR,
			String.valueOf(displayTerms.getStartDateGTYear()));
		iteratorURL.setParameter(
			TrainingExamResultDisplayTerms.START_DATE_LT_DAY,
			String.valueOf(displayTerms.getStartDateLTDay()));
		iteratorURL.setParameter(
			TrainingExamResultDisplayTerms.START_DATE_LT_MONTH,
			String.valueOf(displayTerms.getStartDateLTMonth()));
		iteratorURL.setParameter(
			TrainingExamResultDisplayTerms.START_DATE_LT_YEAR,
			String.valueOf(displayTerms.getStartDateLTYear()));
		iteratorURL.setParameter(
			TrainingExamResultDisplayTerms.TEST_KEY, displayTerms.getTestKey());
	}

}