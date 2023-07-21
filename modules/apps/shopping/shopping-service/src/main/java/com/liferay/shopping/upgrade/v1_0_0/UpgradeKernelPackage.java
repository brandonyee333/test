/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.upgrade.v1_0_0;

/**
 * @author Philip Jones
 */
public class UpgradeKernelPackage
	extends com.liferay.portal.upgrade.v7_0_0.UpgradeKernelPackage {

	@Override
	protected String[][] getClassNames() {
		return _CLASS_NAMES;
	}

	@Override
	protected String[][] getResourceNames() {
		return _RESOURCE_NAMES;
	}

	private static final String[][] _CLASS_NAMES = {
		{
			"com.liferay.portlet.shopping.model.ShoppingCart",
			"com.liferay.shopping.model.ShoppingCart"
		},
		{
			"com.liferay.portlet.shopping.model.ShoppingCartItem",
			"com.liferay.shopping.model.ShoppingCartItem"
		},
		{
			"com.liferay.portlet.shopping.model.ShoppingCategory",
			"com.liferay.shopping.model.ShoppingCategory"
		},
		{
			"com.liferay.portlet.shopping.model.ShoppingCoupon",
			"com.liferay.shopping.model.ShoppingCoupon"
		},
		{
			"com.liferay.portlet.shopping.model.ShoppingItem",
			"com.liferay.shopping.model.ShoppingItem"
		},
		{
			"com.liferay.portlet.shopping.model.ShoppingItemField",
			"com.liferay.shopping.model.ShoppingItemField"
		},
		{
			"com.liferay.portlet.shopping.model.ShoppingItemPrice",
			"com.liferay.shopping.model.ShoppingItemPrice"
		},
		{
			"com.liferay.portlet.shopping.model.ShoppingOrder",
			"com.liferay.shopping.model.ShoppingOrder"
		},
		{
			"com.liferay.portlet.shopping.model.ShoppingOrderItem",
			"com.liferay.shopping.model.ShoppingOrderItem"
		}
	};

	private static final String[][] _RESOURCE_NAMES = {
		{"com.liferay.portlet.shopping", "com.liferay.shopping"}
	};

}