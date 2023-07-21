/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.model;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingCouponConstants {

	public static final String DISCOUNT_TYPE_ACTUAL = "actual";

	public static final String DISCOUNT_TYPE_FREE_SHIPPING = "free-shipping";

	public static final String DISCOUNT_TYPE_PERCENTAGE = "percentage";

	public static final String DISCOUNT_TYPE_TAX_FREE = "tax-free";

	public static final String[] DISCOUNT_TYPES = {
		"percentage", "actual", "free-shipping", "tax-free"
	};

}