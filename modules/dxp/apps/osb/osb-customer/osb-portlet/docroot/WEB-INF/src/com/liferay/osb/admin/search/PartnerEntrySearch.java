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

import com.liferay.osb.model.PartnerEntry;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class PartnerEntrySearch extends SearchContainer<PartnerEntry> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-partners-were-found";

	public static List<String> headerNames = new ArrayList<>();

	static {
		headerNames.add("code");
		headerNames.add("parent-partner");
	}

	public PartnerEntrySearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new PartnerEntryDisplayTerms(portletRequest),
			new PartnerEntrySearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		PartnerEntryDisplayTerms displayTerms =
			(PartnerEntryDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			PartnerEntryDisplayTerms.CHILD_PARTNER_ENTRIES,
			String.valueOf(displayTerms.getChildPartnerEntries()));
		iteratorURL.setParameter(
			PartnerEntryDisplayTerms.CODE, displayTerms.getCode());
		iteratorURL.setParameter(
			PartnerEntryDisplayTerms.MANAGING_SUPPORT,
			String.valueOf(displayTerms.getManagingSupport()));
		iteratorURL.setParameter(
			PartnerEntryDisplayTerms.STATUSES,
			ArrayUtil.toStringArray(displayTerms.getStatuses()));
		iteratorURL.setParameter(
			PartnerEntryDisplayTerms.SUPPORT_REGION_IDS,
			ArrayUtil.toStringArray(displayTerms.getSupportRegionIds()));
	}

}