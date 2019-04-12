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
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Alan Zhang
 */
public class OfferingDefinitionSearch
	extends SearchContainer<OfferingDefinition> {

	public static final String EMPTY_RESULTS_MESSAGE =
		"no-offering-definitions-were-found";

	public static List<String> headerNames = new ArrayList<String>() {
		{
			headerNames.add("product");
			headerNames.add("sla");
			headerNames.add("licenses");
			headerNames.add("tickets");
		}
	};

	public OfferingDefinitionSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new OfferingDefinitionDisplayTerms(portletRequest),
			new OfferingDefinitionSearchTerms(portletRequest),
			DEFAULT_CUR_PARAM, DEFAULT_DELTA, iteratorURL, headerNames,
			EMPTY_RESULTS_MESSAGE);

		OfferingDefinitionDisplayTerms displayTerms =
			(OfferingDefinitionDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			OfferingDefinitionDisplayTerms.PRODUCT_ENTRY_IDS,
			ArrayUtil.toStringArray(displayTerms.getProductEntryIds()));
		iteratorURL.setParameter(
			OfferingDefinitionDisplayTerms.SUPPORT_RESPONSE_IDS,
			ArrayUtil.toStringArray(displayTerms.getSupportResponseIds()));
	}

}