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
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Calvin Keum
 */
public class TrainingExamResultDisplayTerms extends DisplayTerms {

	public static final String CANDIDATE_KEY = "candidateKey";

	public static final String EMAIL_ADDRESS = "emailAddress";

	public static final String FIRST_NAME = "firstName";

	public static final String LAST_NAME = "lastName";

	public static final String START_DATE_GT_DAY = "startDateGTDay";

	public static final String START_DATE_GT_MONTH = "startDateGTMonth";

	public static final String START_DATE_GT_YEAR = "startDateGTYear";

	public static final String START_DATE_LT_DAY = "startDateLTDay";

	public static final String START_DATE_LT_MONTH = "startDateLTMonth";

	public static final String START_DATE_LT_YEAR = "startDateLTYear";

	public static final String TEST_KEY = "testKey";

	public TrainingExamResultDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		candidateKey = ParamUtil.getString(portletRequest, CANDIDATE_KEY);
		emailAddress = ParamUtil.getString(portletRequest, EMAIL_ADDRESS);
		firstName = ParamUtil.getString(portletRequest, FIRST_NAME);
		lastName = ParamUtil.getString(portletRequest, LAST_NAME);
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
		testKey = ParamUtil.getString(portletRequest, TEST_KEY);
	}

	public String getCandidateKey() {
		return candidateKey;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
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

	public String getTestKey() {
		return testKey;
	}

	protected String candidateKey;
	protected String emailAddress;
	protected String firstName;
	protected String lastName;
	protected int startDateGTDay;
	protected int startDateGTMonth;
	protected int startDateGTYear;
	protected int startDateLTDay;
	protected int startDateLTMonth;
	protected int startDateLTYear;
	protected String testKey;

}