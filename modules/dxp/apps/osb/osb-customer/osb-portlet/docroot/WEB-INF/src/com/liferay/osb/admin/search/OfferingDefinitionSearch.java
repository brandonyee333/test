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

	public static List<String> headerNames = new ArrayList<>();

	static {
		headerNames.add("product");
		headerNames.add("sla");
		headerNames.add("licenses");
		headerNames.add("tickets");
	}

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