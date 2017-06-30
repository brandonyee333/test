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

import com.liferay.osb.model.OfferingDefinition;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Alan Zhang
 */
public class OfferingEntrySearch extends SearchContainer<OfferingDefinition> {

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-offering-entries-were-found";

	public OfferingEntrySearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new OfferingEntryDisplayTerms(portletRequest),
			new OfferingEntryDisplayTerms(portletRequest), _CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, null, EMPTY_RESULTS_MESSAGE);

		OfferingEntryDisplayTerms displayTerms =
			(OfferingEntryDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			OfferingEntryDisplayTerms.EMAIL_ADDRESS,
			displayTerms.getEmailAddress());
		iteratorURL.setParameter(
			OfferingEntryDisplayTerms.FIRST_NAME, displayTerms.getFirstName());
		iteratorURL.setParameter(
			OfferingEntryDisplayTerms.LAST_NAME, displayTerms.getLastName());
		iteratorURL.setParameter(
			OfferingEntryDisplayTerms.MIDDLE_NAME,
			displayTerms.getMiddleName());
		iteratorURL.setParameter(
			OfferingEntryDisplayTerms.SCREEN_NAME,
			displayTerms.getScreenName());
	}

	private static final String _CUR_PARAM = "offeringEntryCur";

}