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

package com.liferay.osb.customer.release.notes.web.search;

import com.liferay.osb.customer.release.notes.model.ReleaseNotes;
import com.liferay.portal.kernel.dao.search.SearchContainer;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Alan Zhang
 */
public class ReleaseNotesSearch extends SearchContainer<ReleaseNotes> {

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-release-notes-were-found";

	public ReleaseNotesSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new ReleaseNotesDisplayTerms(portletRequest),
			new ReleaseNotesDisplayTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, null, EMPTY_RESULTS_MESSAGE);

		ReleaseNotesDisplayTerms displayTerms =
			(ReleaseNotesDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			ReleaseNotesDisplayTerms.NAME, displayTerms.getName());
	}

}