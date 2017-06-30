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

package com.liferay.osb.marketplaceserver.search;

import com.liferay.osb.model.AssetReceipt;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Joan Kim
 * @author Douglas Wong
 */
public class AssetReceiptSearch extends SearchContainer<AssetReceipt> {

	static List<String> headerNames = new ArrayList<String>();

	static {
		headerNames.add("product-information");
		headerNames.add("purchased-by");
	}

	public static final String EMPTY_RESULTS_MESSAGE = "no-entries-were-found";

	public AssetReceiptSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		super(
			portletRequest, new AssetReceiptDisplayTerms(portletRequest),
			new AssetReceiptSearchTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		AssetReceiptDisplayTerms displayTerms =
			(AssetReceiptDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			"assetCategoryId",
			String.valueOf(displayTerms.getAssetCategoryId()));
		iteratorURL.setParameter(
			"assetEntryTitle", displayTerms.getAssetEntryTitle());

		if (displayTerms.getFilterByCompatibility()) {
			iteratorURL.setParameter(
				"compatibility",
				String.valueOf(displayTerms.getCompatibility()));
		}

		String orderByCol = ParamUtil.getString(
			portletRequest, "orderByCol", "title");
		String orderByType = ParamUtil.getString(
			portletRequest, "orderByType", "asc");

		setOrderByCol(orderByCol);
		setOrderByType(orderByType);
	}

}