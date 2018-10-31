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

package com.liferay.osb.customer.web.internal.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Alan Zhang
 */
public class ArticleSearch extends SearchContainer<Document> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-articles-were-found";

	public static List<String> headerNames = new ArrayList<>();

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