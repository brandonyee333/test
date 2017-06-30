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
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.support.util.SupportUtil;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortalPreferences;
import com.liferay.portlet.PortletPreferencesFactoryUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class TicketEntrySearch extends SearchContainer<TicketEntry> {

	public static final int DEFAULT_DELTA = 30;

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-support-tickets-were-found";

	public TicketEntrySearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new TicketEntryDisplayTerms(portletRequest),
			new TicketEntrySearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, null, EMPTY_RESULTS_MESSAGE);

		TicketEntryDisplayTerms displayTerms =
			(TicketEntryDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ACCOUNT_ENTRY_IDS,
			ArrayUtil.toStringArray(displayTerms.getAccountEntryIds()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ACCOUNT_ENTRY_NAME,
			displayTerms.getAccountEntryName());
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ACCOUNT_ENTRY_TIERS,
			ArrayUtil.toStringArray(displayTerms.getAccountEntryTiers()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ACCOUNT_WORKER_NAMES,
			displayTerms.getAccountWorkerNames());
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ACCOUNT_WORKER_ROLES,
			ArrayUtil.toStringArray(displayTerms.getAccountWorkerRoles()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ACCOUNT_WORKER_USER_IDS,
			ArrayUtil.toStringArray(displayTerms.getAccountWorkerUserIds()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ASSIGNED_NAME,
			displayTerms.getAssignedName());
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ASSIGNED_PARTNER_ENTRY_IDS,
			ArrayUtil.toStringArray(displayTerms.getAssignedPartnerEntryIds()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ASSIGNED_SUPPORT_TEAM_IDS,
			ArrayUtil.toStringArray(displayTerms.getAssignedSupportTeamIds()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ASSIGNED_TO, displayTerms.getAssignedTo());
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ASSIGNED_USER_IDS,
			ArrayUtil.toStringArray(displayTerms.getAssignedUserIds()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CLOSED_DATE_GT_DAY,
			String.valueOf(displayTerms.getClosedDateGTDay()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CLOSED_DATE_GT_MONTH,
			String.valueOf(displayTerms.getClosedDateGTMonth()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CLOSED_DATE_GT_YEAR,
			String.valueOf(displayTerms.getClosedDateGTYear()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CLOSED_DATE_LT_DAY,
			String.valueOf(displayTerms.getClosedDateLTDay()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CLOSED_DATE_LT_MONTH,
			String.valueOf(displayTerms.getClosedDateLTMonth()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CLOSED_DATE_LT_YEAR,
			String.valueOf(displayTerms.getClosedDateLTYear()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.COMPONENTS,
			ArrayUtil.toStringArray(displayTerms.getComponents()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CONTENT, displayTerms.getContent());
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CREATE_DATE_GT_DAY,
			String.valueOf(displayTerms.getCreateDateGTDay()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CREATE_DATE_GT_MONTH,
			String.valueOf(displayTerms.getCreateDateGTMonth()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CREATE_DATE_GT_YEAR,
			String.valueOf(displayTerms.getCreateDateGTYear()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CREATE_DATE_LT_DAY,
			String.valueOf(displayTerms.getCreateDateLTDay()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CREATE_DATE_LT_MONTH,
			String.valueOf(displayTerms.getCreateDateLTMonth()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.CREATE_DATE_LT_YEAR,
			String.valueOf(displayTerms.getCreateDateLTYear()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.DATABASE_SEARCH,
			String.valueOf(displayTerms.isDatabaseSearch()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.DUE_DATE_GT_DAY,
			String.valueOf(displayTerms.getDueDateGTDay()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.DUE_DATE_GT_MONTH,
			String.valueOf(displayTerms.getDueDateGTMonth()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.DUE_DATE_GT_YEAR,
			String.valueOf(displayTerms.getDueDateGTYear()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.DUE_DATE_LT_DAY,
			String.valueOf(displayTerms.getDueDateLTDay()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.DUE_DATE_LT_MONTH,
			String.valueOf(displayTerms.getDueDateLTMonth()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.DUE_DATE_LT_YEAR,
			String.valueOf(displayTerms.getDueDateLTYear()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ENV_AS_IDS,
			ArrayUtil.toStringArray(displayTerms.getEnvASIds()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ENV_DB_IDS,
			ArrayUtil.toStringArray(displayTerms.getEnvDBIds()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ENV_JVM_IDS,
			ArrayUtil.toStringArray(displayTerms.getEnvJVMIds()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ENV_LFR_IDS,
			ArrayUtil.toStringArray(displayTerms.getEnvLFRIds()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ENV_OS_IDS,
			ArrayUtil.toStringArray(displayTerms.getEnvOSIds()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ESCALATION_LEVELS,
			ArrayUtil.toStringArray(displayTerms.getEscalationLevels()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.PENDING_TYPES,
			ArrayUtil.toStringArray(displayTerms.getPendingTypes()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.PRODUCT_ENTRY_IDS,
			ArrayUtil.toStringArray(displayTerms.getProductEntryIds()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.RESOLUTIONS,
			ArrayUtil.toStringArray(displayTerms.getResolutions()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.SATISFIED_DUE_DATE,
			String.valueOf(displayTerms.getSatisfiedDueDate()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.SEVERITIES,
			ArrayUtil.toStringArray(displayTerms.getSeverities()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.STATUSES,
			ArrayUtil.toStringArray(displayTerms.getStatuses()));
		iteratorURL.setParameter(
			TicketEntryDisplayTerms.SUPPORT_REGION_IDS,
			ArrayUtil.toStringArray(displayTerms.getSupportRegionIds()));

		try {
			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					portletRequest);

			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol");
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType");

			if (Validator.isNotNull(orderByCol) &&
				Validator.isNotNull(orderByType)) {

				preferences.setValue(
					OSBPortletKeys.OSB_SUPPORT, "ticket-entry-order-by-col",
					orderByCol);
				preferences.setValue(
					OSBPortletKeys.OSB_SUPPORT, "ticket-entry-order-by-type",
					orderByType);
			}
			else {
				orderByCol = preferences.getValue(
					OSBPortletKeys.OSB_SUPPORT, "ticket-entry-order-by-col",
					"ticket");
				orderByType = preferences.getValue(
					OSBPortletKeys.OSB_SUPPORT, "ticket-entry-order-by-type",
					"desc");
			}

			OrderByComparator orderByComparator =
				SupportUtil.getTicketEntryOrderByComparator(
					orderByCol, orderByType);

			setOrderByCol(orderByCol);
			setOrderByType(orderByType);
			setOrderByComparator(orderByComparator);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TicketEntrySearch.class);

}