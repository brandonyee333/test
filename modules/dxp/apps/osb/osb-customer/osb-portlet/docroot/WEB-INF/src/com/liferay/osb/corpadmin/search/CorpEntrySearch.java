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

package com.liferay.osb.corpadmin.search;

import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.util.comparator.CorpEntryCreateDateComparator;
import com.liferay.osb.util.comparator.CorpEntryNameComparator;
import com.liferay.osb.util.comparator.CorpEntryStatusComparator;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Douglas Wong
 */
public class CorpEntrySearch extends SearchContainer<CorpEntry> {

	static List<String> headerNames = new ArrayList<String>();
	static Map<String, String> orderableHeaders = new HashMap<String, String>();

	static {
		headerNames.add("name");
		headerNames.add("create-date");
		headerNames.add("status");

		orderableHeaders.put("name", "name");
		orderableHeaders.put("create-date", "create-date");
		orderableHeaders.put("status", "status");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-entries-were-found";

	public CorpEntrySearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new CorpEntryDisplayTerms(portletRequest),
			new CorpEntrySearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		CorpEntryDisplayTerms displayTerms =
			(CorpEntryDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter("name", displayTerms.getName());

		try {
			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol", "create-date");
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType", "desc");

			OrderByComparator orderByComparator = null;

			boolean asc = false;

			if (orderByType.equals("asc")) {
				asc = true;
			}

			if (orderByCol.equals("create-date")) {
				orderByComparator = new CorpEntryCreateDateComparator(asc);
			}
			else if (orderByCol.equals("name")) {
				orderByComparator = new CorpEntryNameComparator(asc);
			}
			else if (orderByCol.equals("status")) {
				orderByComparator = new CorpEntryStatusComparator(asc);
			}

			setOrderableHeaders(orderableHeaders);
			setOrderByCol(orderByCol);
			setOrderByType(orderByType);
			setOrderByComparator(orderByComparator);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CorpEntrySearch.class);

}