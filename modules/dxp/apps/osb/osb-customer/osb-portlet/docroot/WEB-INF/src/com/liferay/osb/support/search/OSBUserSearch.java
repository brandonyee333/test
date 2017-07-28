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

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.comparator.UserEmailAddressComparator;
import com.liferay.portal.kernel.util.comparator.UserFirstNameComparator;
import com.liferay.portal.kernel.util.comparator.UserLastNameComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Peter Shin
 * @author Joan Kim
 */
public class OSBUserSearch extends SearchContainer<User> {

	static List<String> headerNames = new ArrayList<>();
	static Map<String, String> orderableHeaders = new HashMap<>();

	static {
		headerNames.add("first-name");
		headerNames.add("last-name");
		headerNames.add("email-address");
		headerNames.add("type");

		orderableHeaders.put("email-address", "email-address");
		orderableHeaders.put("first-name", "first-name");
		orderableHeaders.put("last-name", "last-name");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-students-were-found";

	public OSBUserSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new OSBUserDisplayTerms(portletRequest),
			new OSBUserDisplayTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		String orderByCol = ParamUtil.getString(portletRequest, "orderByCol");
		String orderByType = ParamUtil.getString(portletRequest, "orderByType");

		OrderByComparator orderByComparator = getOrderByComparator(
			orderByCol, orderByType);

		setOrderableHeaders(orderableHeaders);
		setOrderByCol(orderByCol);
		setOrderByType(orderByType);
		setOrderByComparator(orderByComparator);

		OSBUserDisplayTerms displayTerms =
			(OSBUserDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			OSBUserDisplayTerms.EMAIL_ADDRESS, displayTerms.getEmailAddress());
		iteratorURL.setParameter(
			OSBUserDisplayTerms.FIRST_NAME, displayTerms.getFirstName());
		iteratorURL.setParameter(
			OSBUserDisplayTerms.LAST_NAME, displayTerms.getLastName());
	}

	protected OrderByComparator getOrderByComparator(
		String orderByCol, String orderByType) {

		boolean asc = true;

		if (orderByType.equals("desc")) {
			asc = false;
		}

		OrderByComparator orderByComparator = null;

		if (orderByCol.equals("email-address")) {
			orderByComparator = new UserEmailAddressComparator(asc);
		}
		else if (orderByCol.equals("first-name")) {
			orderByComparator = new UserFirstNameComparator(asc);
		}
		else if (orderByCol.equals("last-name")) {
			orderByComparator = new UserLastNameComparator(asc);
		}

		return orderByComparator;
	}

}