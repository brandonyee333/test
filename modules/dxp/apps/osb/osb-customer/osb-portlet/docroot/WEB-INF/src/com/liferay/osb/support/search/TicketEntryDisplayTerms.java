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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Amos Fong
 */
public class TicketEntryDisplayTerms extends DisplayTerms {

	public static final String ACCOUNT_ENTRY_IDS = "accountEntryIds";

	public static final String ACCOUNT_ENTRY_NAME = "accountEntryName";

	public static final String ACCOUNT_ENTRY_TIERS = "accountEntryTiers";

	public static final String ACCOUNT_WORKER_NAMES = "accountWorkerNames";

	public static final String ACCOUNT_WORKER_ROLES = "accountWorkerRoles";

	public static final String ACCOUNT_WORKER_USER_IDS = "accountWorkerUserIds";

	public static final String ASSIGNED_NAME = "assignedName";

	public static final String ASSIGNED_PARTNER_ENTRY_IDS =
		"assignedPartnerEntryIds";

	public static final String ASSIGNED_SUPPORT_TEAM_IDS =
		"assignedSupportTeamIds";

	public static final String ASSIGNED_TO = "assignedTo";

	public static final String ASSIGNED_USER_IDS = "assignedUserIds";

	public static final String CLOSED_DATE_GT_DAY = "closedDateGTDay";

	public static final String CLOSED_DATE_GT_MONTH = "closedDateGTMonth";

	public static final String CLOSED_DATE_GT_YEAR = "closedDateGTYear";

	public static final String CLOSED_DATE_LT_DAY = "closedDateLTDay";

	public static final String CLOSED_DATE_LT_MONTH = "closedDateLTMonth";

	public static final String CLOSED_DATE_LT_YEAR = "closedDateLTYear";

	public static final String COMPONENTS = "components";

	public static final String CONTENT = "content";

	public static final String CREATE_DATE_GT_DAY = "createDateGTDay";

	public static final String CREATE_DATE_GT_MONTH = "createDateGTMonth";

	public static final String CREATE_DATE_GT_YEAR = "createDateGTYear";

	public static final String CREATE_DATE_LT_DAY = "createDateLTDay";

	public static final String CREATE_DATE_LT_MONTH = "createDateLTMonth";

	public static final String CREATE_DATE_LT_YEAR = "createDateLTYear";

	public static final String DATABASE_SEARCH = "databaseSearch";

	public static final String DUE_DATE_GT_DAY = "dueDateGTDay";

	public static final String DUE_DATE_GT_MONTH = "dueDateGTMonth";

	public static final String DUE_DATE_GT_YEAR = "dueDateGTYear";

	public static final String DUE_DATE_LT_DAY = "dueDateLTDay";

	public static final String DUE_DATE_LT_MONTH = "dueDateLTMonth";

	public static final String DUE_DATE_LT_YEAR = "dueDateLTYear";

	public static final String ENV_AS_IDS = "envAS";

	public static final String ENV_DB_IDS = "envDB";

	public static final String ENV_JVM_IDS = "envJVM";

	public static final String ENV_LFR_IDS = "envLFR";

	public static final String ENV_OS_IDS = "envOS";

	public static final String ESCALATION_LEVELS = "escalationLevels";

	public static final String FEEDBACK_AVAILABLE = "feedbackAvailable";

	public static final String ORDER_BY_COL = "orderByCol";

	public static final String ORDER_BY_TYPE = "orderByType";

	public static final String PENDING_TYPES = "pendingTypes";

	public static final String PRODUCT_ENTRY_IDS = "productEntryIds";

	public static final String RESOLUTIONS = "resolutions";

	public static final String SATISFIED_DUE_DATE = "satisfiedDueDate";

	public static final String SEARCH_ATTACHMENTS = "searchAttachments";

	public static final String SEARCH_FILTER_BY = "searchFilterBy";

	public static final String SEVERITIES = "severities";

	public static final String STATUSES = "statuses";

	public static final String SUPPORT_REGION_IDS = "supportRegionIds";

	public static final String TICKET_WORKER = "ticketWorker";

	public TicketEntryDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		accountEntryIds = ParamUtil.getLongValues(
			portletRequest, ACCOUNT_ENTRY_IDS);
		accountEntryName = ParamUtil.getString(
			portletRequest, ACCOUNT_ENTRY_NAME);

		if (Validator.isNull(accountEntryName)) {
			accountEntryName = ParamUtil.getString(portletRequest, KEYWORDS);
		}

		accountEntryTiers = ParamUtil.getIntegerValues(
			portletRequest, ACCOUNT_ENTRY_TIERS);
		accountWorkerNames = ParamUtil.getParameterValues(
			portletRequest, ACCOUNT_WORKER_NAMES);
		accountWorkerRoles = ParamUtil.getIntegerValues(
			portletRequest, ACCOUNT_WORKER_ROLES);
		accountWorkerUserIds = ParamUtil.getLongValues(
			portletRequest, ACCOUNT_WORKER_USER_IDS);
		assignedName = ParamUtil.getString(portletRequest, ASSIGNED_NAME);
		assignedPartnerEntryIds = ParamUtil.getLongValues(
			portletRequest, ASSIGNED_PARTNER_ENTRY_IDS);
		assignedSupportTeamIds = ParamUtil.getLongValues(
			portletRequest, ASSIGNED_SUPPORT_TEAM_IDS);
		assignedTo = ParamUtil.getString(portletRequest, ASSIGNED_TO);
		assignedUserIds = ParamUtil.getLongValues(
			portletRequest, ASSIGNED_USER_IDS);
		closedDateGTDay = ParamUtil.getInteger(
			portletRequest, CLOSED_DATE_GT_DAY);
		closedDateGTMonth = ParamUtil.getInteger(
			portletRequest, CLOSED_DATE_GT_MONTH);
		closedDateGTYear = ParamUtil.getInteger(
			portletRequest, CLOSED_DATE_GT_YEAR);
		closedDateLTDay = ParamUtil.getInteger(
			portletRequest, CLOSED_DATE_LT_DAY);
		closedDateLTMonth = ParamUtil.getInteger(
			portletRequest, CLOSED_DATE_LT_MONTH);
		closedDateLTYear = ParamUtil.getInteger(
			portletRequest, CLOSED_DATE_LT_YEAR);
		components = ParamUtil.getIntegerValues(portletRequest, COMPONENTS);
		content = ParamUtil.getString(portletRequest, CONTENT);

		if (Validator.isNull(content)) {
			content = ParamUtil.getString(portletRequest, KEYWORDS);
		}

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

		String databaseSearchString = ParamUtil.getString(
			portletRequest, DATABASE_SEARCH);

		if (Validator.isNotNull(databaseSearchString)) {
			databaseSearch = GetterUtil.getBoolean(databaseSearchString);
		}

		dueDateGTDay = ParamUtil.getInteger(portletRequest, DUE_DATE_GT_DAY);
		dueDateGTMonth = ParamUtil.getInteger(
			portletRequest, DUE_DATE_GT_MONTH);
		dueDateGTYear = ParamUtil.getInteger(portletRequest, DUE_DATE_GT_YEAR);
		dueDateLTDay = ParamUtil.getInteger(portletRequest, DUE_DATE_LT_DAY);
		dueDateLTMonth = ParamUtil.getInteger(
			portletRequest, DUE_DATE_LT_MONTH);
		dueDateLTYear = ParamUtil.getInteger(portletRequest, DUE_DATE_LT_YEAR);
		envASIds = ParamUtil.getLongValues(portletRequest, ENV_AS_IDS);
		envDBIds = ParamUtil.getLongValues(portletRequest, ENV_DB_IDS);
		envJVMIds = ParamUtil.getLongValues(portletRequest, ENV_JVM_IDS);
		envLFRIds = ParamUtil.getLongValues(portletRequest, ENV_LFR_IDS);
		envOSIds = ParamUtil.getLongValues(portletRequest, ENV_OS_IDS);
		escalationLevels = ParamUtil.getIntegerValues(
			portletRequest, ESCALATION_LEVELS);

		String feedbackAvailableString = ParamUtil.getString(
			portletRequest, FEEDBACK_AVAILABLE);

		if (Validator.isNotNull(feedbackAvailableString)) {
			feedbackAvailable = GetterUtil.getBoolean(feedbackAvailableString);
		}

		orderByCol = ParamUtil.getString(portletRequest, ORDER_BY_COL);
		orderByType = ParamUtil.getString(portletRequest, ORDER_BY_TYPE);
		pendingTypes = ParamUtil.getIntegerValues(
			portletRequest, PENDING_TYPES);
		productEntryIds = ParamUtil.getLongValues(
			portletRequest, PRODUCT_ENTRY_IDS);
		resolutions = ParamUtil.getIntegerValues(portletRequest, RESOLUTIONS);

		String satsifiedDueDateString = ParamUtil.getString(
			portletRequest, SATISFIED_DUE_DATE);

		if (Validator.isNotNull(satsifiedDueDateString)) {
			satisfiedDueDate = GetterUtil.getBoolean(satsifiedDueDateString);
		}

		searchAttachments = ParamUtil.getBoolean(
			portletRequest, SEARCH_ATTACHMENTS);

		searchFilterBy = ParamUtil.getString(portletRequest, SEARCH_FILTER_BY);
		severities = ParamUtil.getIntegerValues(portletRequest, SEVERITIES);
		statuses = ParamUtil.getIntegerValues(portletRequest, STATUSES);
		supportRegionIds = ParamUtil.getLongValues(
			portletRequest, SUPPORT_REGION_IDS);
		ticketWorker = ParamUtil.getBoolean(portletRequest, TICKET_WORKER);
	}

	public TicketEntryDisplayTerms(
		PortletRequest portletRequest, String searchFilter) {

		super(portletRequest);

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				searchFilter);

			jsonObject = _convertToLESA2(jsonObject);

			accountEntryIds = _getLongValues(jsonObject, ACCOUNT_ENTRY_IDS);
			accountEntryName = GetterUtil.getString(
				jsonObject.getString(ACCOUNT_ENTRY_NAME));
			accountEntryTiers = _getIntegerValues(
				jsonObject, ACCOUNT_ENTRY_TIERS);
			accountWorkerNames = _getStringValues(
				jsonObject, ACCOUNT_WORKER_NAMES);
			accountWorkerRoles = _getIntegerValues(
				jsonObject, ACCOUNT_WORKER_ROLES);
			accountWorkerUserIds = _getLongValues(
				jsonObject, ACCOUNT_WORKER_USER_IDS);
			assignedName = GetterUtil.getString(
				jsonObject.getString(ASSIGNED_NAME));
			assignedPartnerEntryIds = _getLongValues(
				jsonObject, ASSIGNED_PARTNER_ENTRY_IDS);
			assignedSupportTeamIds = _getLongValues(
				jsonObject, ASSIGNED_SUPPORT_TEAM_IDS);
			assignedTo = GetterUtil.getString(
				jsonObject.getString(ASSIGNED_TO));
			assignedUserIds = _getLongValues(jsonObject, ASSIGNED_USER_IDS);
			closedDateGTDay = GetterUtil.getInteger(
				jsonObject.getInt(CLOSED_DATE_GT_DAY));
			closedDateGTMonth = GetterUtil.getInteger(
				jsonObject.getInt(CLOSED_DATE_GT_MONTH));
			closedDateGTYear = GetterUtil.getInteger(
				jsonObject.getInt(CLOSED_DATE_GT_YEAR));
			closedDateLTDay = GetterUtil.getInteger(
				jsonObject.getInt(CLOSED_DATE_LT_DAY));
			closedDateLTMonth = GetterUtil.getInteger(
				jsonObject.getInt(CLOSED_DATE_LT_MONTH));
			closedDateLTYear = GetterUtil.getInteger(
				jsonObject.getInt(CLOSED_DATE_LT_YEAR));
			components = _getIntegerValues(jsonObject, COMPONENTS);
			content = GetterUtil.getString(jsonObject.getString(CONTENT));
			createDateGTDay = GetterUtil.getInteger(
				jsonObject.getInt(CREATE_DATE_GT_DAY));
			createDateGTMonth = GetterUtil.getInteger(
				jsonObject.getInt(CREATE_DATE_GT_MONTH));
			createDateGTYear = GetterUtil.getInteger(
				jsonObject.getInt(CREATE_DATE_GT_YEAR));
			createDateLTDay = GetterUtil.getInteger(
				jsonObject.getInt(CREATE_DATE_LT_DAY));
			createDateLTMonth = GetterUtil.getInteger(
				jsonObject.getInt(CREATE_DATE_LT_MONTH));
			createDateLTYear = GetterUtil.getInteger(
				jsonObject.getInt(CREATE_DATE_LT_YEAR));

			if (jsonObject.has(DATABASE_SEARCH)) {
				databaseSearch = jsonObject.getBoolean(DATABASE_SEARCH);
			}

			dueDateGTDay = GetterUtil.getInteger(
				jsonObject.getInt(DUE_DATE_GT_DAY));
			dueDateGTMonth = GetterUtil.getInteger(
				jsonObject.getInt(DUE_DATE_GT_MONTH));
			dueDateGTYear = GetterUtil.getInteger(
				jsonObject.getInt(DUE_DATE_GT_YEAR));
			dueDateLTDay = GetterUtil.getInteger(
				jsonObject.getInt(DUE_DATE_LT_DAY));
			dueDateLTMonth = GetterUtil.getInteger(
				jsonObject.getInt(DUE_DATE_LT_MONTH));
			dueDateLTYear = GetterUtil.getInteger(
				jsonObject.getInt(DUE_DATE_LT_YEAR));
			envASIds = _getLongValues(jsonObject, ENV_AS_IDS);
			envDBIds = _getLongValues(jsonObject, ENV_DB_IDS);
			envJVMIds = _getLongValues(jsonObject, ENV_JVM_IDS);
			envLFRIds = _getLongValues(jsonObject, ENV_LFR_IDS);
			envOSIds = _getLongValues(jsonObject, ENV_OS_IDS);
			escalationLevels = _getIntegerValues(jsonObject, ESCALATION_LEVELS);

			if (jsonObject.has(FEEDBACK_AVAILABLE)) {
				feedbackAvailable = jsonObject.getBoolean(FEEDBACK_AVAILABLE);
			}

			keywords = GetterUtil.getString(jsonObject.getString(KEYWORDS));
			orderByCol = GetterUtil.getString(
				jsonObject.getString(ORDER_BY_COL));
			orderByType = GetterUtil.getString(
				jsonObject.getString(ORDER_BY_TYPE));
			pendingTypes = _getIntegerValues(jsonObject, PENDING_TYPES);
			productEntryIds = _getLongValues(jsonObject, PRODUCT_ENTRY_IDS);
			resolutions = _getIntegerValues(jsonObject, RESOLUTIONS);

			if (jsonObject.has(SATISFIED_DUE_DATE)) {
				satisfiedDueDate = jsonObject.getBoolean(SATISFIED_DUE_DATE);
			}

			if (jsonObject.has(SEARCH_ATTACHMENTS)) {
				searchAttachments = jsonObject.getBoolean(SEARCH_ATTACHMENTS);
			}

			searchFilterBy = GetterUtil.getString(
				jsonObject.getString(SEARCH_FILTER_BY));
			severities = _getIntegerValues(jsonObject, SEVERITIES);

			JSONArray jsonArray = jsonObject.getJSONArray(STATUSES);

			if (jsonArray != null) {
				statuses = new int[jsonArray.length()];

				for (int i = 0; i < jsonArray.length(); i++) {
					statuses[i] = GetterUtil.getInteger(jsonArray.getInt(i));
				}
			}

			supportRegionIds = _getLongValues(jsonObject, SUPPORT_REGION_IDS);

			if (jsonObject.has(TICKET_WORKER)) {
				ticketWorker = jsonObject.getBoolean(TICKET_WORKER);
			}
		}
		catch (JSONException jsone) {
		}
	}

	public long[] getAccountEntryIds() {
		return accountEntryIds;
	}

	public String getAccountEntryName() {
		return accountEntryName;
	}

	public int[] getAccountEntryTiers() {
		return accountEntryTiers;
	}

	public String[] getAccountWorkerNames() {
		return accountWorkerNames;
	}

	public int[] getAccountWorkerRoles() {
		return accountWorkerRoles;
	}

	public long[] getAccountWorkerUserIds() {
		return accountWorkerUserIds;
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

	public Date getClosedDateGT() {
		if ((closedDateGTDay > 0) && (closedDateGTYear > 0)) {
			Calendar closedDateGTCal = Calendar.getInstance();

			closedDateGTCal.set(Calendar.DAY_OF_MONTH, closedDateGTDay);
			closedDateGTCal.set(Calendar.MONTH, closedDateGTMonth);
			closedDateGTCal.set(Calendar.YEAR, closedDateGTYear);

			return closedDateGTCal.getTime();
		}

		return null;
	}

	public int getClosedDateGTDay() {
		return closedDateGTDay;
	}

	public int getClosedDateGTMonth() {
		return closedDateGTMonth;
	}

	public int getClosedDateGTYear() {
		return closedDateGTYear;
	}

	public Date getClosedDateLT() {
		if ((closedDateLTDay > 0) && (closedDateLTYear > 0)) {
			Calendar closedDateLTCal = Calendar.getInstance();

			closedDateLTCal.set(Calendar.DAY_OF_MONTH, closedDateLTDay);
			closedDateLTCal.set(Calendar.MONTH, closedDateLTMonth);
			closedDateLTCal.set(Calendar.YEAR, closedDateLTYear);

			return closedDateLTCal.getTime();
		}

		return null;
	}

	public int getClosedDateLTDay() {
		return closedDateLTDay;
	}

	public int getClosedDateLTMonth() {
		return closedDateLTMonth;
	}

	public int getClosedDateLTYear() {
		return closedDateLTYear;
	}

	public int[] getComponents() {
		return components;
	}

	public String getContent() {
		return content;
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

	public Date getDueDateGT() {
		if ((dueDateGTDay > 0) && (dueDateGTYear > 0)) {
			Calendar dueDateGTCal = Calendar.getInstance();

			dueDateGTCal.set(Calendar.DAY_OF_MONTH, dueDateGTDay);
			dueDateGTCal.set(Calendar.MONTH, dueDateGTMonth);
			dueDateGTCal.set(Calendar.YEAR, dueDateGTYear);

			return dueDateGTCal.getTime();
		}

		return null;
	}

	public int getDueDateGTDay() {
		return dueDateGTDay;
	}

	public int getDueDateGTMonth() {
		return dueDateGTMonth;
	}

	public int getDueDateGTYear() {
		return dueDateGTYear;
	}

	public Date getDueDateLT() {
		if ((dueDateLTDay > 0) && (dueDateLTYear > 0)) {
			Calendar dueDateLTCal = Calendar.getInstance();

			dueDateLTCal.set(Calendar.DAY_OF_MONTH, dueDateLTDay);
			dueDateLTCal.set(Calendar.MONTH, dueDateLTMonth);
			dueDateLTCal.set(Calendar.YEAR, dueDateLTYear);

			return dueDateLTCal.getTime();
		}

		return null;
	}

	public int getDueDateLTDay() {
		return dueDateLTDay;
	}

	public int getDueDateLTMonth() {
		return dueDateLTMonth;
	}

	public int getDueDateLTYear() {
		return dueDateLTYear;
	}

	public long[] getEnvASIds() {
		return envASIds;
	}

	public long[] getEnvDBIds() {
		return envDBIds;
	}

	public long[] getEnvJVMIds() {
		return envJVMIds;
	}

	public long[] getEnvLFRIds() {
		return envLFRIds;
	}

	public long[] getEnvOSIds() {
		return envOSIds;
	}

	public int[] getEscalationLevels() {
		return escalationLevels;
	}

	public Boolean getFeedbackAvailable() {
		return feedbackAvailable;
	}

	public int[] getPendingTypes() {
		return pendingTypes;
	}

	public long[] getProductEntryIds() {
		return productEntryIds;
	}

	public int[] getResolutions() {
		return resolutions;
	}

	public Boolean getSatisfiedDueDate() {
		return satisfiedDueDate;
	}

	public Boolean getSearchAttachments() {
		return searchAttachments;
	}

	public String getSearchFilterBy() {
		return searchFilterBy;
	}

	public int[] getSeverities() {
		return severities;
	}

	public int[] getStatuses() {
		return statuses;
	}

	public long[] getSupportRegionIds() {
		return supportRegionIds;
	}

	public boolean hasFeedbackAvailable() {
		if (feedbackAvailable == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean hasSatisfiedDueDate() {
		if (satisfiedDueDate == null) {
			return false;
		}
		else {
			return true;
		}
	}

	public boolean isDatabaseSearch() {
		if (databaseSearch == null) {
			return false;
		}

		return databaseSearch.booleanValue();
	}

	public boolean isFeedbackAvailable() {
		if (feedbackAvailable == null) {
			return false;
		}

		return feedbackAvailable.booleanValue();
	}

	public boolean isSatisfiedDueDate() {
		if (satisfiedDueDate == null) {
			return false;
		}

		return satisfiedDueDate.booleanValue();
	}

	public boolean isSearchAttachments() {
		if (searchAttachments == null) {
			return true;
		}

		return searchAttachments.booleanValue();
	}

	public boolean isTicketWorker() {
		if (ticketWorker == null) {
			return false;
		}

		return ticketWorker.booleanValue();
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
		jsonObject.put(ASSIGNED_NAME, assignedName);
		jsonObject.put(ASSIGNED_TO, assignedTo);
		jsonObject.put(CLOSED_DATE_GT_DAY, closedDateGTDay);
		jsonObject.put(CLOSED_DATE_GT_MONTH, closedDateGTMonth);
		jsonObject.put(CLOSED_DATE_GT_YEAR, closedDateGTYear);
		jsonObject.put(CLOSED_DATE_LT_DAY, closedDateLTDay);
		jsonObject.put(CLOSED_DATE_LT_MONTH, closedDateLTMonth);
		jsonObject.put(CLOSED_DATE_LT_YEAR, closedDateLTYear);
		jsonObject.put(CONTENT, content);
		jsonObject.put(CREATE_DATE_GT_DAY, createDateGTDay);
		jsonObject.put(CREATE_DATE_GT_MONTH, createDateGTMonth);
		jsonObject.put(CREATE_DATE_GT_YEAR, createDateGTYear);
		jsonObject.put(CREATE_DATE_LT_DAY, createDateLTDay);
		jsonObject.put(CREATE_DATE_LT_MONTH, createDateLTMonth);
		jsonObject.put(CREATE_DATE_LT_YEAR, createDateLTYear);
		jsonObject.put(DUE_DATE_GT_DAY, dueDateGTDay);
		jsonObject.put(DUE_DATE_GT_MONTH, dueDateGTMonth);
		jsonObject.put(DUE_DATE_GT_YEAR, dueDateGTYear);
		jsonObject.put(DUE_DATE_LT_DAY, dueDateLTDay);
		jsonObject.put(DUE_DATE_LT_MONTH, dueDateLTMonth);
		jsonObject.put(DUE_DATE_LT_YEAR, dueDateLTYear);

		if (hasFeedbackAvailable()) {
			if (isFeedbackAvailable()) {
				jsonObject.put(FEEDBACK_AVAILABLE, "1");
			}
			else {
				jsonObject.put(FEEDBACK_AVAILABLE, "0");
			}
		}

		jsonObject.put(KEYWORDS, keywords);
		jsonObject.put(ORDER_BY_COL, orderByCol);
		jsonObject.put(ORDER_BY_TYPE, orderByType);
		jsonObject.put(SEARCH_ATTACHMENTS, searchAttachments);

		addJSONArray(jsonObject, ACCOUNT_ENTRY_IDS, accountEntryIds);
		addJSONArray(jsonObject, ACCOUNT_ENTRY_TIERS, accountEntryTiers);

		if (accountWorkerNames.length > 0) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (String accountWorkerName : accountWorkerNames) {
				if (Validator.isNotNull(accountWorkerName)) {
					jsonArray.put(accountWorkerName);
				}
			}

			if (jsonArray.length() > 0) {
				jsonObject.put(ACCOUNT_WORKER_NAMES, jsonArray);
			}
		}

		addJSONArray(jsonObject, ACCOUNT_WORKER_ROLES, accountWorkerRoles);
		addJSONArray(jsonObject, ACCOUNT_WORKER_USER_IDS, accountWorkerUserIds);
		addJSONArray(
			jsonObject, ASSIGNED_PARTNER_ENTRY_IDS, assignedPartnerEntryIds);
		addJSONArray(
			jsonObject, ASSIGNED_SUPPORT_TEAM_IDS, assignedSupportTeamIds);
		addJSONArray(jsonObject, ASSIGNED_USER_IDS, assignedUserIds);
		addJSONArray(jsonObject, COMPONENTS, components);
		addJSONArray(jsonObject, ENV_AS_IDS, envASIds);
		addJSONArray(jsonObject, ENV_DB_IDS, envDBIds);
		addJSONArray(jsonObject, ENV_JVM_IDS, envJVMIds);
		addJSONArray(jsonObject, ENV_LFR_IDS, envLFRIds);
		addJSONArray(jsonObject, ENV_OS_IDS, envOSIds);
		addJSONArray(jsonObject, ESCALATION_LEVELS, escalationLevels);
		addJSONArray(jsonObject, PENDING_TYPES, pendingTypes);
		addJSONArray(jsonObject, PRODUCT_ENTRY_IDS, productEntryIds);
		addJSONArray(jsonObject, RESOLUTIONS, resolutions);

		if (hasSatisfiedDueDate()) {
			if (isSatisfiedDueDate()) {
				jsonObject.put(SATISFIED_DUE_DATE, "1");
			}
			else {
				jsonObject.put(SATISFIED_DUE_DATE, "0");
			}
		}

		addJSONArray(jsonObject, SEVERITIES, severities);

		if (statuses.length > 0) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			for (int status : statuses) {
				jsonArray.put(String.valueOf(status));
			}

			jsonObject.put(STATUSES, jsonArray);
		}

		addJSONArray(jsonObject, SUPPORT_REGION_IDS, supportRegionIds);

		return jsonObject;
	}

	protected void addJSONArray(
		JSONObject jsonObject, String key, int[] intValues) {

		if (ArrayUtil.isEmpty(intValues)) {
			return;
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (int intValue : intValues) {
			if (intValue > 0) {
				jsonArray.put(String.valueOf(intValue));
			}
		}

		if (jsonArray.length() > 0) {
			jsonObject.put(key, jsonArray);
		}
	}

	protected void addJSONArray(
		JSONObject jsonObject, String key, long[] longValues) {

		if (ArrayUtil.isEmpty(longValues)) {
			return;
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		for (long longValue : longValues) {
			if (longValue > 0) {
				jsonArray.put(String.valueOf(longValue));
			}
		}

		if (jsonArray.length() > 0) {
			jsonObject.put(key, jsonArray);
		}
	}

	protected long[] accountEntryIds;
	protected String accountEntryName;
	protected int[] accountEntryTiers;
	protected String[] accountWorkerNames;
	protected int[] accountWorkerRoles;
	protected long[] accountWorkerUserIds;
	protected String assignedName;
	protected long[] assignedPartnerEntryIds;
	protected long[] assignedSupportTeamIds;
	protected String assignedTo;
	protected long[] assignedUserIds;
	protected int closedDateGTDay;
	protected int closedDateGTMonth;
	protected int closedDateGTYear;
	protected int closedDateLTDay;
	protected int closedDateLTMonth;
	protected int closedDateLTYear;
	protected int[] components;
	protected String content;
	protected int createDateGTDay;
	protected int createDateGTMonth;
	protected int createDateGTYear;
	protected int createDateLTDay;
	protected int createDateLTMonth;
	protected int createDateLTYear;
	protected Boolean databaseSearch;
	protected int dueDateGTDay;
	protected int dueDateGTMonth;
	protected int dueDateGTYear;
	protected int dueDateLTDay;
	protected int dueDateLTMonth;
	protected int dueDateLTYear;
	protected long[] envASIds;
	protected long[] envDBIds;
	protected long[] envJVMIds;
	protected long[] envLFRIds;
	protected long[] envOSIds;
	protected int[] escalationLevels;
	protected Boolean feedbackAvailable;
	protected String orderByCol;
	protected String orderByType;
	protected int[] pendingTypes;
	protected long[] productEntryIds;
	protected int[] resolutions;
	protected Boolean satisfiedDueDate;
	protected Boolean searchAttachments;
	protected String searchFilterBy;
	protected int[] severities;
	protected int[] statuses;
	protected long[] supportRegionIds;
	protected Boolean ticketWorker;

	private JSONObject _convertToLESA2(JSONObject jsonObject) {
		String accountEntryName = StringUtil.unquote(
			jsonObject.getString(ACCOUNT_ENTRY_NAME));

		if (Validator.isNotNull(accountEntryName) &&
			!jsonObject.has(ACCOUNT_ENTRY_IDS)) {

			try {
				AccountEntry accountEntry =
					AccountEntryLocalServiceUtil.getAccountEntryByName(
						accountEntryName);

				JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

				jsonArray.put(String.valueOf(accountEntry.getAccountEntryId()));

				jsonObject.put(ACCOUNT_ENTRY_IDS, jsonArray);
			}
			catch (Exception e) {
			}
		}

		String accountWorkerName = jsonObject.getString("accountWorkerName");

		if (Validator.isNotNull(accountWorkerName)) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			jsonArray.put(accountWorkerName);

			jsonObject.put(ACCOUNT_WORKER_NAMES, jsonArray);
		}

		int accountWorkerRole = GetterUtil.getInteger(
			jsonObject.getInt("accountWorkerRole"));

		if (accountWorkerRole > 0) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			jsonArray.put(String.valueOf(accountWorkerRole));

			jsonObject.put(ACCOUNT_WORKER_ROLES, jsonArray);
		}

		long accountWorkerUserId = GetterUtil.getLong(
			jsonObject.getLong("accountWorkerUserId"));

		if (accountWorkerUserId > 0) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			jsonArray.put(String.valueOf(accountWorkerUserId));

			jsonObject.put(ACCOUNT_WORKER_USER_IDS, jsonArray);
		}

		String assignedTo = jsonObject.getString(ASSIGNED_TO);

		if (Validator.isNotNull(assignedTo)) {
			String assignedToIds = assignedTo;

			if (assignedTo.contains(StringPool.DASH)) {
				assignedToIds = assignedTo.substring(
					assignedTo.indexOf(StringPool.DASH));
			}

			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			jsonArray.put(assignedToIds);

			if (assignedTo.startsWith("partnerEntryId-") &&
				!jsonObject.has(ASSIGNED_PARTNER_ENTRY_IDS)) {

				jsonObject.put(ASSIGNED_PARTNER_ENTRY_IDS, jsonArray);
			}
			else if (assignedTo.startsWith("supportTeamId-") &&
					 !jsonObject.has(ASSIGNED_SUPPORT_TEAM_IDS)) {

				jsonObject.put(ASSIGNED_SUPPORT_TEAM_IDS, jsonArray);
			}
			else if (!jsonObject.has(ASSIGNED_USER_IDS)) {
				jsonObject.put(ASSIGNED_USER_IDS, jsonArray);
			}
		}

		String content = jsonObject.getString(CONTENT);
		String keywords = jsonObject.getString(KEYWORDS);

		if (Validator.isNotNull(content) && Validator.isNull(keywords)) {
			jsonObject.put(KEYWORDS, content);
		}

		int pendingType = GetterUtil.getInteger(
			jsonObject.getInt("pendingType"));

		if (pendingType > 0) {
			JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

			jsonArray.put(String.valueOf(pendingType));

			jsonObject.put(PENDING_TYPES, jsonArray);
		}

		return jsonObject;
	}

	private int[] _getIntegerValues(JSONObject jsonObject, String key) {
		JSONArray jsonArray = jsonObject.getJSONArray(key);

		if (jsonArray != null) {
			List<Integer> integerValues = new ArrayList<>(jsonArray.length());

			for (int i = 0; i < jsonArray.length(); i++) {
				int intValue = GetterUtil.getInteger(jsonArray.getInt(i));

				if (intValue > 0) {
					integerValues.add(intValue);
				}
			}

			return ArrayUtil.toArray(integerValues.toArray(new Integer[0]));
		}

		return new int[0];
	}

	private long[] _getLongValues(JSONObject jsonObject, String key) {
		JSONArray jsonArray = jsonObject.getJSONArray(key);

		if (jsonArray != null) {
			List<Long> longValues = new ArrayList<>(jsonArray.length());

			for (int i = 0; i < jsonArray.length(); i++) {
				long longValue = GetterUtil.getLong(jsonArray.getLong(i));

				if (longValue > 0) {
					longValues.add(longValue);
				}
			}

			return ArrayUtil.toArray(longValues.toArray(new Long[0]));
		}

		long longValue = GetterUtil.getLong(jsonObject.getLong(key));

		if (longValue > 0) {
			return new long[] {longValue};
		}

		return new long[0];
	}

	private String[] _getStringValues(JSONObject jsonObject, String key) {
		JSONArray jsonArray = jsonObject.getJSONArray(key);

		if (jsonArray != null) {
			List<String> stringValues = new ArrayList<>(jsonArray.length());

			for (int i = 0; i < jsonArray.length(); i++) {
				String value = GetterUtil.getString(jsonArray.getString(i));

				if (Validator.isNotNull(value)) {
					stringValues.add(value);
				}
			}

			return stringValues.toArray(new String[0]);
		}

		String value = GetterUtil.getString(jsonObject.getString(key));

		if (Validator.isNotNull(value)) {
			return new String[] {value};
		}

		return new String[0];
	}

}