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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.users.admin.kernel.util.UsersAdminUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brent Krone-Schmidt
 */
public class SupportWorkerSearch extends SearchContainer<SupportWorker> {

	static List<String> headerNames = new ArrayList<String>();
	static Map<String, String> orderableHeaders = new HashMap<String, String>();

	static {
		headerNames.add("first-name");
		headerNames.add("last-name");
		headerNames.add("screen-name");
		headerNames.add("email-address");

		orderableHeaders.put("first-name", "first-name");
		orderableHeaders.put("last-name", "last-name");
		orderableHeaders.put("screen-name", "screen-name");
		orderableHeaders.put("email-address", "email-address");
	}

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-support-workers-were-found";

	public SupportWorkerSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		this(portletRequest, DEFAULT_CUR_PARAM, iteratorURL);
	}

	public SupportWorkerSearch(
		PortletRequest portletRequest, String curParam,
		PortletURL iteratorURL) {

		super(
			portletRequest, new SupportWorkerDisplayTerms(portletRequest),
			new SupportWorkerSearchTerms(portletRequest), curParam,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		SupportWorkerDisplayTerms displayTerms =
			(SupportWorkerDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			SupportWorkerDisplayTerms.EMAIL_ADDRESS,
			displayTerms.getEmailAddress());
		iteratorURL.setParameter(
			SupportWorkerDisplayTerms.FIRST_NAME, displayTerms.getFirstName());
		iteratorURL.setParameter(
			SupportWorkerDisplayTerms.LAST_NAME, displayTerms.getLastName());
		iteratorURL.setParameter(
			SupportWorkerDisplayTerms.MIDDLE_NAME,
			displayTerms.getMiddleName());
		iteratorURL.setParameter(
			SupportWorkerDisplayTerms.SCREEN_NAME,
			displayTerms.getScreenName());

		try {
			PortalPreferences preferences =
				PortletPreferencesFactoryUtil.getPortalPreferences(
					portletRequest);

			String orderByCol = ParamUtil.getString(
				portletRequest, "orderByCol");
			String orderByType = ParamUtil.getString(
				portletRequest, "orderByType");

			if (Validator.isNotNull(orderByCol) &&
				Validator.isNotNull(orderByType)) {

				preferences.setValue(
					OSBPortletKeys.OSB_ADMIN, "support-workers-order-by-col",
					orderByCol);
				preferences.setValue(
					OSBPortletKeys.OSB_ADMIN, "support-workers-order-by-type",
					orderByType);
			}
			else {
				orderByCol = preferences.getValue(
					OSBPortletKeys.OSB_ADMIN, "support-workers-order-by-col",
					"last-name");
				orderByType = preferences.getValue(
					OSBPortletKeys.OSB_ADMIN, "support-workers-order-by-type",
					"asc");
			}

			OrderByComparator orderByComparator =
				UsersAdminUtil.getUserOrderByComparator(
					orderByCol, orderByType);

			setOrderableHeaders(orderableHeaders);
			setOrderByCol(orderByCol);
			setOrderByType(orderByType);
			setOrderByComparator(orderByComparator);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SupportWorkerSearch.class);

}