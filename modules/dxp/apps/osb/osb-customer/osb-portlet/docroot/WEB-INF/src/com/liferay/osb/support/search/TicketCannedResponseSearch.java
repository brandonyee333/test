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

import com.liferay.osb.model.TicketCannedResponse;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class TicketCannedResponseSearch
	extends SearchContainer<TicketCannedResponse> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("name");
		headerNames.add("modified-date");
		headerNames.add("use-count");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-canned-responses-were-found";

	public TicketCannedResponseSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest,
			new TicketCannedResponseDisplayTerms(portletRequest),
			new TicketCannedResponseDisplayTerms(portletRequest),
			DEFAULT_CUR_PARAM, DEFAULT_DELTA, iteratorURL, headerNames,
			EMPTY_RESULTS_MESSAGE);

		TicketCannedResponseDisplayTerms displayTerms =
			(TicketCannedResponseDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			TicketCannedResponseDisplayTerms.CONTENT,
			displayTerms.getContent());
		iteratorURL.setParameter(
			TicketCannedResponseDisplayTerms.NAME, displayTerms.getName());
	}

}