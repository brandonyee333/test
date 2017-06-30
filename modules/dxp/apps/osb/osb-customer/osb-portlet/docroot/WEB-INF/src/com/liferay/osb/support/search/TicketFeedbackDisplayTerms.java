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

package com.liferay.osb.support.search;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Calendar;
import java.util.Date;

import javax.portlet.PortletRequest;

/**
 * @author Mate Thurzo
 */
public class TicketFeedbackDisplayTerms extends DisplayTerms {

	public static final String ACCOUNT_ENTRY_NAME = "accountEntryName";

	public static final String ANSWERED = "answered";

	public static final String ASSIGNED_NAME = "assignedName";

	public static final String ASSIGNED_PARTNER_ENTRY_IDS =
		"assignedPartnerEntryIds";

	public static final String ASSIGNED_SUPPORT_TEAM_IDS =
		"assignedSupportTeamIds";

	public static final String ASSIGNED_TO = "assignedTo";

	public static final String ASSIGNED_USER_IDS = "assignedUserIds";

	public static final String COMMENTS = "comments";

	public static final String CREATE_DATE_GT_DAY = "createDateGTDay";

	public static final String CREATE_DATE_GT_MONTH = "createDateGTMonth";

	public static final String CREATE_DATE_GT_YEAR = "createDateGTYear";

	public static final String CREATE_DATE_LT_DAY = "createDateLTDay";

	public static final String CREATE_DATE_LT_MONTH = "createDateLTMonth";

	public static final String CREATE_DATE_LT_YEAR = "createDateLTYear";

	public static final String RATINGS_1 = "ratings1";

	public static final String RATINGS_2 = "ratings2";

	public static final String RATINGS_3 = "ratings3";

	public static final String RATINGS_4 = "ratings4";

	public static final String SATISFIED = "satisfied";

	public TicketFeedbackDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		accountEntryName = ParamUtil.getString(
			portletRequest, ACCOUNT_ENTRY_NAME);

		String answeredString = ParamUtil.getString(portletRequest, ANSWERED);

		if (Validator.isNotNull(answeredString)) {
			answered = GetterUtil.getBoolean(answeredString);
		}

		assignedName = ParamUtil.getString(portletRequest, ASSIGNED_NAME);
		assignedPartnerEntryIds = ParamUtil.getLongValues(
			portletRequest, ASSIGNED_PARTNER_ENTRY_IDS);
		assignedSupportTeamIds = ParamUtil.getLongValues(
			portletRequest, ASSIGNED_SUPPORT_TEAM_IDS);
		assignedTo = ParamUtil.getString(portletRequest, ASSIGNED_TO);
		assignedUserIds = ParamUtil.getLongValues(
			portletRequest, ASSIGNED_USER_IDS);
		comments = ParamUtil.getString(portletRequest, COMMENTS);
		createDateGTDay = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_GT_DAY);
		createDateGTMonth = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_GT_MONTH);
		createDateGTYear = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_GT_YEAR);
		createDateLTDay = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_LT_DAY);
		createDateLTMonth = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_LT_MONTH);
		createDateLTYear = ParamUtil.getInteger(
			portletRequest, CREATE_DATE_LT_YEAR);
		ratings1 = ArrayUtil.toArray(
			ParamUtil.getIntegerValues(portletRequest, RATINGS_1));
		ratings2 = ArrayUtil.toArray(
			ParamUtil.getIntegerValues(portletRequest, RATINGS_2));
		ratings3 = ArrayUtil.toArray(
			ParamUtil.getIntegerValues(portletRequest, RATINGS_3));
		ratings4 = ArrayUtil.toArray(
			ParamUtil.getIntegerValues(portletRequest, RATINGS_4));

		String satisfiedString = ParamUtil.getString(portletRequest, SATISFIED);

		if (Validator.isNotNull(satisfiedString)) {
			satisfied = GetterUtil.getBoolean(satisfiedString);
		}
	}

	public String getAccountEntryName() {
		return accountEntryName;
	}

	public Boolean getAnswered() {
		return answered;
	}

	public String getAssignedName() {
		return assignedName;
	}

	public long[] getAssignedPartnerEntryIds() {
		return assignedPartnerEntryIds;
	}

	public long[] getAssignedSupportTeamIds() {
		return assignedSupportTeamIds;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public long[] getAssignedUserIds() {
		return assignedUserIds;
	}

	public String getComments() {
		return comments;
	}

	public Date getCreateDateGT() {
		if ((createDateGTDay > 0) && (createDateGTYear > 0)) {
			Calendar createDateGTCal = Calendar.getInstance();

			createDateGTCal.set(Calendar.DAY_OF_MONTH, createDateGTDay);
			createDateGTCal.set(Calendar.MONTH, createDateGTMonth);
			createDateGTCal.set(Calendar.YEAR, createDateGTYear);

			return createDateGTCal.getTime();
		}

		return null;
	}

	public int getCreateDateGTDay() {
		return createDateGTDay;
	}

	public int getCreateDateGTMonth() {
		return createDateGTMonth;
	}

	public int getCreateDateGTYear() {
		return createDateGTYear;
	}

	public Date getCreateDateLT() {
		if ((createDateLTDay > 0) && (createDateLTYear > 0)) {
			Calendar createDateLTCal = Calendar.getInstance();

			createDateLTCal.set(Calendar.DAY_OF_MONTH, createDateLTDay);
			createDateLTCal.set(Calendar.MONTH, createDateLTMonth);
			createDateLTCal.set(Calendar.YEAR, createDateLTYear);

			return createDateLTCal.getTime();
		}

		return null;
	}

	public int getCreateDateLTDay() {
		return createDateLTDay;
	}

	public int getCreateDateLTMonth() {
		return createDateLTMonth;
	}

	public int getCreateDateLTYear() {
		return createDateLTYear;
	}

	public Integer[] getRatings1() {
		return ratings1;
	}

	public Integer[] getRatings2() {
		return ratings2;
	}

	public Integer[] getRatings3() {
		return ratings3;
	}

	public Integer[] getRatings4() {
		return ratings4;
	}

	public Boolean getSatisfied() {
		return satisfied;
	}

	public boolean hasAnswered() {
		if (answered == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean hasSatisfied() {
		if (satisfied == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean isAnswered() {
		if (answered == null) {
			return false;
		}

		return answered.booleanValue();
	}

	public boolean isSatisfied() {
		if (satisfied == null) {
			return false;
		}

		return satisfied.booleanValue();
	}

	public JSONObject toJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (isAndOperator()) {
			jsonObject.put(AND_OPERATOR, "1");
		}
		else {
			jsonObject.put(AND_OPERATOR, "0");
		}

		jsonObject.put(ACCOUNT_ENTRY_NAME, accountEntryName);

		if (hasAnswered()) {
			if (isAnswered()) {
				jsonObject.put(ANSWERED, "1");
			}
			else {
				jsonObject.put(ANSWERED, "0");
			}
		}

		if (hasSatisfied()) {
			if (isSatisfied()) {
				jsonObject.put(SATISFIED, "1");
			}
			else {
				jsonObject.put(SATISFIED, "0");
			}
		}

		jsonObject.put(ASSIGNED_NAME, assignedName);
		jsonObject.put(ASSIGNED_TO, assignedTo);
		jsonObject.put(COMMENTS, comments);
		jsonObject.put(CREATE_DATE_GT_DAY, createDateGTDay);
		jsonObject.put(CREATE_DATE_GT_MONTH, createDateGTMonth);
		jsonObject.put(CREATE_DATE_GT_YEAR, createDateGTYear);
		jsonObject.put(CREATE_DATE_LT_DAY, createDateLTDay);
		jsonObject.put(CREATE_DATE_LT_MONTH, createDateLTMonth);
		jsonObject.put(CREATE_DATE_LT_YEAR, createDateLTYear);

		if (!ArrayUtil.contains(ratings1, 0)) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (int rating1 : ratings1) {
				jsonArray.put(String.valueOf(rating1));
			}

			jsonObject.put(RATINGS_1, jsonArray);
		}

		if (!ArrayUtil.contains(ratings2, 0)) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (int rating2 : ratings2) {
				jsonArray.put(String.valueOf(rating2));
			}

			jsonObject.put(RATINGS_2, jsonArray);
		}

		if (!ArrayUtil.contains(ratings3, 0)) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (int rating3 : ratings3) {
				jsonArray.put(String.valueOf(rating3));
			}

			jsonObject.put(RATINGS_3, jsonArray);
		}

		if (!ArrayUtil.contains(ratings4, 0)) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (int rating4 : ratings4) {
				jsonArray.put(String.valueOf(rating4));
			}

			jsonObject.put(RATINGS_4, jsonArray);
		}

		return jsonObject;
	}

	protected String accountEntryName;
	protected Boolean answered;
	protected String assignedName;
	protected long[] assignedPartnerEntryIds;
	protected long[] assignedSupportTeamIds;
	protected String assignedTo;
	protected long[] assignedUserIds;
	protected String comments;
	protected int createDateGTDay;
	protected int createDateGTMonth;
	protected int createDateGTYear;
	protected int createDateLTDay;
	protected int createDateLTMonth;
	protected int createDateLTYear;
	protected Integer[] ratings1;
	protected Integer[] ratings2;
	protected Integer[] ratings3;
	protected Integer[] ratings4;
	protected Boolean satisfied;

}