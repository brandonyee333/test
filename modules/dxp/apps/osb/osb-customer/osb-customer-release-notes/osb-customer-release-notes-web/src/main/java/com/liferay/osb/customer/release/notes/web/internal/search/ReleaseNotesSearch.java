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

package com.liferay.osb.customer.release.notes.web.internal.search;

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