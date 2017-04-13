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

package com.liferay.osb.customer.web.internal.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Alan Zhang
 */
public class ArticleSearch extends SearchContainer<Document> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-articles-were-found";

	public static List<String> headerNames = new ArrayList<>();
	public static Map<String, String> orderableHeaders = new HashMap<>();

	public ArticleSearch(
		PortletRequest portletRequest, int cur, int delta,
		PortletURL iteratorURL) {

		super(
			portletRequest, new ArticleDisplayTerms(portletRequest),
			new ArticleSearchTerms(portletRequest), DEFAULT_CUR_PARAM, cur,
			delta, iteratorURL, headerNames, null);

		ArticleDisplayTerms displayTerms =
			(ArticleDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			ArticleDisplayTerms.ASSET_CATEGORY_IDS,
			ArrayUtil.toStringArray(displayTerms.getAssetCategoryIds()));
		iteratorURL.setParameter(
			ArticleDisplayTerms.LANGUAGE_IDS, displayTerms.getLanguageIds());
		iteratorURL.setParameter(
			ArticleDisplayTerms.PERMISSION_TYPES,
			ArrayUtil.toStringArray(displayTerms.getPermissionTypes()));
	}

	public ArticleSearch(
		PortletRequest portletRequest, PortletURL iteratorURL) {

		this(portletRequest, 0, DEFAULT_DELTA, iteratorURL);
	}

}