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

import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.util.comparator.TicketFeedbackCreateDateComparator;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Mate Thurzo
 */
public class TicketFeedbackSearch extends SearchContainer<TicketFeedback> {

	static List<String> headerNames = new ArrayList<>();
	static Map<String, String> orderableHeaders = new HashMap<>();

	static {
		headerNames.add("feedback");
		headerNames.add("create-date");
		headerNames.add("satisfied");
		headerNames.add("average-rating");
		headerNames.add("comments");
		headerNames.add("status");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-feedback-was-found";

	public TicketFeedbackSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new TicketFeedbackDisplayTerms(portletRequest),
			new TicketFeedbackSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		TicketFeedbackDisplayTerms displayTerms =
			(TicketFeedbackDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			TicketEntryDisplayTerms.ACCOUNT_ENTRY_NAME,
			displayTerms.getAccountEntryName());
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.ANSWERED,
			String.valueOf(displayTerms.getAnswered()));
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.ASSIGNED_NAME,
			displayTerms.getAssignedName());
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.ASSIGNED_TO,
			displayTerms.getAssignedTo());
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.COMMENTS, displayTerms.getComments());
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.CREATE_DATE_GT_DAY,
			String.valueOf(displayTerms.getCreateDateGTDay()));
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.CREATE_DATE_GT_MONTH,
			String.valueOf(displayTerms.getCreateDateGTMonth()));
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.CREATE_DATE_GT_YEAR,
			String.valueOf(displayTerms.getCreateDateGTYear()));
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.CREATE_DATE_LT_DAY,
			String.valueOf(displayTerms.getCreateDateLTDay()));
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.CREATE_DATE_LT_MONTH,
			String.valueOf(displayTerms.getCreateDateLTMonth()));
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.CREATE_DATE_LT_YEAR,
			String.valueOf(displayTerms.getCreateDateLTYear()));
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.RATINGS_1,
			ArrayUtil.toStringArray(displayTerms.getRatings1()));
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.RATINGS_2,
			ArrayUtil.toStringArray(displayTerms.getRatings2()));
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.RATINGS_3,
			ArrayUtil.toStringArray(displayTerms.getRatings3()));
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.RATINGS_4,
			ArrayUtil.toStringArray(displayTerms.getRatings4()));
		iteratorURL.setParameter(
			TicketFeedbackDisplayTerms.SATISFIED,
			String.valueOf(displayTerms.getSatisfied()));

		setOrderByComparator(new TicketFeedbackCreateDateComparator());
	}

}