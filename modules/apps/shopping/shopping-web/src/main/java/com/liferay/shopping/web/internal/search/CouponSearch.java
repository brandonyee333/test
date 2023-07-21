/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.web.internal.search;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.shopping.model.ShoppingCoupon;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class CouponSearch extends SearchContainer<ShoppingCoupon> {

	public static final String EMPTY_RESULTS_MESSAGE = "no-coupons-were-found";

	public static List<String> headerNames = new ArrayList<>();

	static {
		headerNames.add("code");
		headerNames.add("description");
		headerNames.add("start-date");
		headerNames.add("expiration-date");
		headerNames.add("discount-type");
	}

	public CouponSearch(PortletRequest portletRequest, PortletURL iteratorURL) {
		super(
			portletRequest, new CouponDisplayTerms(portletRequest),
			new CouponDisplayTerms(portletRequest), DEFAULT_CUR_PARAM,
			DEFAULT_DELTA, iteratorURL, headerNames, EMPTY_RESULTS_MESSAGE);

		CouponDisplayTerms displayTerms = (CouponDisplayTerms)getDisplayTerms();

		iteratorURL.setParameter(
			CouponDisplayTerms.ACTIVE, displayTerms.getActive());
		iteratorURL.setParameter(
			CouponDisplayTerms.CODE, displayTerms.getCode());
		iteratorURL.setParameter(
			CouponDisplayTerms.DISCOUNT_TYPE, displayTerms.getDiscountType());
	}

}