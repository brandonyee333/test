/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.web.internal.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class CouponDisplayTerms extends DisplayTerms {

	public static final String ACTIVE = "active";

	public static final String CODE = "code";

	public static final String DISCOUNT_TYPE = "discountType";

	public CouponDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		active = ParamUtil.getString(portletRequest, ACTIVE, "yes");
		code = ParamUtil.getString(portletRequest, CODE);
		discountType = ParamUtil.getString(
			portletRequest, DISCOUNT_TYPE, "all");
	}

	public String getActive() {
		return active;
	}

	public String getCode() {
		return code;
	}

	public String getDiscountType() {
		if (discountType.equals("all")) {
			return null;
		}

		return discountType;
	}

	public boolean isActive() {
		if (active.equals("no")) {
			return false;
		}

		return true;
	}

	protected String active;
	protected String code;
	protected String discountType;

}