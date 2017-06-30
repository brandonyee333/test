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

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Douglas Wong
 */
public class TrainingLinkedUserSearch extends SearchContainer<User> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("first-name");
		headerNames.add("last-name");
		headerNames.add("email-address");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-users-were-found";

	public TrainingLinkedUserSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new TrainingLinkedUserDisplayTerms(portletRequest),
			new TrainingLinkedUserDisplayTerms(portletRequest),
			DEFAULT_CUR_PARAM, DEFAULT_DELTA, iteratorURL, headerNames,
			EMPTY_RESULTS_MESSAGE);

		TrainingLinkedUserDisplayTerms displayTerms =
			(TrainingLinkedUserDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			TrainingLinkedUserDisplayTerms.EMAIL_ADDRESS,
			displayTerms.getEmailAddress());
		iteratorURL.setParameter(
			TrainingLinkedUserDisplayTerms.FIRST_NAME,
			displayTerms.getFirstName());
		iteratorURL.setParameter(
			TrainingLinkedUserDisplayTerms.LAST_NAME,
			displayTerms.getLastName());
	}

}