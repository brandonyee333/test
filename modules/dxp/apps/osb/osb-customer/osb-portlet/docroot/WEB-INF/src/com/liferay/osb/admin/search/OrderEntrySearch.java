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

import com.liferay.osb.model.OrderEntry;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Alan Zhang
 */
public class OrderEntrySearch extends SearchContainer<OrderEntry> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("uuid");
		headerNames.add("account");
		headerNames.add("start-date");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-orders-were-found";

	public OrderEntrySearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new OrderEntryDisplayTerms(portletRequest),
			new OrderEntrySearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		OrderEntryDisplayTerms displayTerms =
			(OrderEntryDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			OrderEntryDisplayTerms.ACCOUNT_ENTRY_ID,
			String.valueOf(displayTerms.getAccountEntryId()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.ACCOUNT_ENTRY_NAME,
			displayTerms.getAccountEntryName());
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.ACTUAL_START_DATE_GT_DAY,
			String.valueOf(displayTerms.getActualStartDateGTDay()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.ACTUAL_START_DATE_GT_MONTH,
			String.valueOf(displayTerms.getActualStartDateGTMonth()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.ACTUAL_START_DATE_GT_YEAR,
			String.valueOf(displayTerms.getActualStartDateGTYear()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.ACTUAL_START_DATE_LT_DAY,
			String.valueOf(displayTerms.getActualStartDateLTDay()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.ACTUAL_START_DATE_LT_MONTH,
			String.valueOf(displayTerms.getActualStartDateLTMonth()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.ACTUAL_START_DATE_LT_YEAR,
			String.valueOf(displayTerms.getActualStartDateLTYear()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.CREATE_DATE_GT_DAY,
			String.valueOf(displayTerms.getCreateDateGTDay()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.CREATE_DATE_GT_MONTH,
			String.valueOf(displayTerms.getCreateDateGTMonth()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.CREATE_DATE_GT_YEAR,
			String.valueOf(displayTerms.getCreateDateGTYear()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.CREATE_DATE_LT_DAY,
			String.valueOf(displayTerms.getCreateDateLTDay()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.CREATE_DATE_LT_MONTH,
			String.valueOf(displayTerms.getCreateDateLTMonth()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.CREATE_DATE_LT_YEAR,
			String.valueOf(displayTerms.getCreateDateLTYear()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.CREATE_USER_ID,
			String.valueOf(displayTerms.getCreateUserId()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.CREATE_USER_NAME,
			displayTerms.getCreateUserName());
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.MODIFIED_DATE_GT_DAY,
			String.valueOf(displayTerms.getModifiedDateGTDay()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.MODIFIED_DATE_GT_MONTH,
			String.valueOf(displayTerms.getModifiedDateGTMonth()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.MODIFIED_DATE_GT_YEAR,
			String.valueOf(displayTerms.getModifiedDateGTYear()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.MODIFIED_DATE_LT_DAY,
			String.valueOf(displayTerms.getModifiedDateLTDay()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.MODIFIED_DATE_LT_MONTH,
			String.valueOf(displayTerms.getModifiedDateLTMonth()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.MODIFIED_DATE_LT_YEAR,
			String.valueOf(displayTerms.getModifiedDateLTYear()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.MODIFIED_USER_ID,
			String.valueOf(displayTerms.getModifiedUserId()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.MODIFIED_USER_NAME,
			displayTerms.getModifiedUserName());
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.PRODUCT_ENTRY_ID,
			String.valueOf(displayTerms.getProductEntryId()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.PRORATED,
			String.valueOf(displayTerms.getProrated()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.PURCHASE_ORDER_KEY,
			displayTerms.getPurchaseOrderKey());
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.START_DATE_GT_DAY,
			String.valueOf(displayTerms.getStartDateGTDay()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.START_DATE_GT_MONTH,
			String.valueOf(displayTerms.getStartDateGTMonth()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.START_DATE_GT_YEAR,
			String.valueOf(displayTerms.getStartDateGTYear()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.START_DATE_LT_DAY,
			String.valueOf(displayTerms.getStartDateLTDay()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.START_DATE_LT_MONTH,
			String.valueOf(displayTerms.getStartDateLTMonth()));
		iteratorURL.setParameter(
			OrderEntryDisplayTerms.START_DATE_LT_YEAR,
			String.valueOf(displayTerms.getStartDateLTYear()));
	}

}