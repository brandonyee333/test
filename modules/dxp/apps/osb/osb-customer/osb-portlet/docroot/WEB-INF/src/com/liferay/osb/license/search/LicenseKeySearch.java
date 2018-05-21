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

package com.liferay.osb.license.search;

import com.liferay.osb.license.util.LicenseUtil;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.OSBWebKeys;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortalPreferences;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;

/**
 * @author Amos Fong
 */
public class LicenseKeySearch extends SearchContainer<LicenseKey> {

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-license-keys-were-found";

	public static List<String> headerNames = new ArrayList<>();
	public static Map<String, String> orderableHeaders = new HashMap<>();

	static {
		headerNames.add("account");
		headerNames.add("name");
		headerNames.add("product");
		headerNames.add("version");
		headerNames.add("start-date");
		headerNames.add("expiration-date");

		orderableHeaders.put("expiration-date", "expiration-date");
		orderableHeaders.put("start-date", "start-date");
	}

	public LicenseKeySearch(
		PortletRequest portletRequest, DisplayTerms displayTerms,
		DisplayTerms searchTerms, PortletURL iteratorURL) {

		super(
			portletRequest, displayTerms, searchTerms, DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		iteratorURL.setParameter("sessionSearch", Boolean.TRUE.toString());

		PortletSession session = portletRequest.getPortletSession();

		session.setAttribute(
			OSBWebKeys.OSB_DISPLAY_TERMS, displayTerms,
			PortletSession.APPLICATION_SCOPE);
		session.setAttribute(
			OSBWebKeys.OSB_SEARCH_TERMS, searchTerms,
			PortletSession.APPLICATION_SCOPE);

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
					OSBPortletKeys.OSB_LICENSE, "license-key-order-by-col",
					orderByCol);
				preferences.setValue(
					OSBPortletKeys.OSB_LICENSE, "license-key-order-by-type",
					orderByType);
			}
			else {
				orderByCol = preferences.getValue(
					OSBPortletKeys.OSB_LICENSE, "license-key-order-by-col",
					"start-date");
				orderByType = preferences.getValue(
					OSBPortletKeys.OSB_LICENSE, "license-key-order-by-type",
					"desc");
			}

			OrderByComparator orderByComparator =
				LicenseUtil.getLicenseKeyOrderByComparator(
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

	public LicenseKeySearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		this(
			portletRequest, _getLicenseDisplayTerms(portletRequest),
			_getLicenseSearchTerms(portletRequest), iteratorURL);
	}

	private static LicenseKeyDisplayTerms _getLicenseDisplayTerms(
		PortletRequest portletRequest) {

		boolean sessionSearch = ParamUtil.getBoolean(
			portletRequest, "sessionSearch");

		if (sessionSearch) {
			PortletSession session = portletRequest.getPortletSession();

			LicenseKeyDisplayTerms displayTerms =
				(LicenseKeyDisplayTerms)session.getAttribute(
					OSBWebKeys.OSB_DISPLAY_TERMS,
					PortletSession.APPLICATION_SCOPE);

			if (displayTerms != null) {
				return displayTerms;
			}
		}

		return new LicenseKeyDisplayTerms(portletRequest);
	}

	private static LicenseKeySearchTerms _getLicenseSearchTerms(
		PortletRequest portletRequest) {

		boolean sessionSearch = ParamUtil.getBoolean(
			portletRequest, "sessionSearch");

		if (sessionSearch) {
			PortletSession session = portletRequest.getPortletSession();

			LicenseKeySearchTerms searchTerms =
				(LicenseKeySearchTerms)session.getAttribute(
					OSBWebKeys.OSB_SEARCH_TERMS,
					PortletSession.APPLICATION_SCOPE);

			if (searchTerms != null) {
				return searchTerms;
			}
		}

		return new LicenseKeySearchTerms(portletRequest);
	}

	private static Log _log = LogFactoryUtil.getLog(LicenseKeySearch.class);

}