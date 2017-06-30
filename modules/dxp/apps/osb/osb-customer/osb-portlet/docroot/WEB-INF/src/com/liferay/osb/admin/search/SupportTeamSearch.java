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

import com.liferay.osb.model.SupportTeam;
import com.liferay.osb.util.comparator.SupportTeamNameComparator;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brent Krone-Schmidt
 */
public class SupportTeamSearch extends SearchContainer<SupportTeam> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("name");
		headerNames.add("type");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-support-teams-were-found";

	public SupportTeamSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		this(portletRequest, DEFAULT_CUR_PARAM, iteratorURL);
	}

	public SupportTeamSearch(
		PortletRequest portletRequest, String curParam,
		PortletURL iteratorURL) {

		super(
			portletRequest, new SupportTeamDisplayTerms(portletRequest),
			new SupportTeamSearchTerms(portletRequest), curParam, DEFAULT_DELTA,
			iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		SupportTeamDisplayTerms displayTerms =
			(SupportTeamDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			SupportTeamDisplayTerms.NAME, displayTerms.getName());
		iteratorURL.setParameter(
			SupportTeamDisplayTerms.TYPE,
			String.valueOf(displayTerms.getType()));

		setOrderByComparator(new SupportTeamNameComparator());
	}

}