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

import com.liferay.osb.model.TrainingCertificateTemplate;
import com.liferay.osb.util.comparator.TrainingCertificateTemplateNameComparator;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Ryan Park
 */
public class TrainingCertificateTemplateSearch
	extends SearchContainer<TrainingCertificateTemplate> {

	static List<String> headerNames = new ArrayList<String>();
	static Map<String, String> orderableHeaders = new HashMap<String, String>();

	static {
		headerNames.add("name");
		headerNames.add("description");
		headerNames.add("badge");

		orderableHeaders.put("name", "name");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-certificate-templates-were-found";

	public TrainingCertificateTemplateSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, null, null, DEFAULT_CUR_PARAM, DEFAULT_DELTA,
			iteratorURL, null, EMPTY_RESULTS_MESSAGE);

		String orderByCol = ParamUtil.getString(portletRequest, "orderByCol");
		String orderByType = ParamUtil.getString(portletRequest, "orderByType");

		OrderByComparator orderByComparator = getOrderByComparator(
			orderByCol, orderByType);

		setOrderableHeaders(orderableHeaders);
		setOrderByCol(orderByCol);
		setOrderByComparator(orderByComparator);
		setOrderByType(orderByType);
	}

	protected OrderByComparator getOrderByComparator(
		String orderByCol, String orderByType) {

		boolean asc = true;

		if (orderByType.equals("desc")) {
			asc = false;
		}

		return new TrainingCertificateTemplateNameComparator(asc);
	}

}