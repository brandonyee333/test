/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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

	public static List<String> headerNames = new ArrayList<String>() {
		{
			add("code");
			add("parent-partner");
		}
	};

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