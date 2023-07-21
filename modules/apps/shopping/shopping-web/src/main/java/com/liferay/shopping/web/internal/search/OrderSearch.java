/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.web.internal.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.shopping.model.ShoppingOrder;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class OrderSearch extends SearchContainer<ShoppingOrder> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-orders-were-found";

	public static List<String> headerNames = new ArrayList<>();

	static {
		headerNames.add("number");
		headerNames.add("date");
		headerNames.add("status");
		headerNames.add("customer");
	}

	public OrderSearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		super(
			portletRequest, new OrderDisplayTerms(portletRequest),
			new OrderSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		OrderDisplayTerms displayTerms = (OrderDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			OrderDisplayTerms.EMAIL_ADDRESS, displayTerms.getEmailAddress());
		iteratorURL.setParameter(
			OrderDisplayTerms.FIRST_NAME, displayTerms.getFirstName());
		iteratorURL.setParameter(
			OrderDisplayTerms.LAST_NAME, displayTerms.getLastName());
		iteratorURL.setParameter(
			OrderDisplayTerms.NUMBER, displayTerms.getNumber());
		iteratorURL.setParameter(
			OrderDisplayTerms.STATUS, displayTerms.getStatus());
	}

}