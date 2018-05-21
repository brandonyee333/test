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