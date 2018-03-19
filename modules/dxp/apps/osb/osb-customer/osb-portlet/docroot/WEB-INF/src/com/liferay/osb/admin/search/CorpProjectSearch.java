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

import com.liferay.osb.model.CorpProject;
import com.liferay.osb.util.comparator.CorpProjectNameComparator;
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
 * @author Joan Kim
 */
public class CorpProjectSearch extends SearchContainer<CorpProject> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-projects-were-found";

	public static List<String> headerNames = new ArrayList<>();
	public static Map<String, String> orderableHeaders = new HashMap<>();

	static {
		headerNames.add("name");

		orderableHeaders.put("name", "name");
	}

	public CorpProjectSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new CorpProjectDisplayTerms(portletRequest),
			new CorpProjectSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		CorpProjectDisplayTerms displayTerms =
			(CorpProjectDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter("name", displayTerms.getName());

		try {
			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol", "name");
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType", "asc");

			OrderByComparator orderByComparator = null;

			boolean asc = false;

			if (orderByType.equals("asc")) {
				asc = true;
			}

			if (orderByCol.equals("name")) {
				orderByComparator = new CorpProjectNameComparator(asc);
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

	private static Log _log = LogFactoryUtil.getLog(CorpProjectSearch.class);

}