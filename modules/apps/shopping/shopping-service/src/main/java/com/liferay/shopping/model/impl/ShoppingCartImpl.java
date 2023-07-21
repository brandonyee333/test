/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.shopping.exception.NoSuchCouponException;
import com.liferay.shopping.model.ShoppingCartItem;
import com.liferay.shopping.model.ShoppingCoupon;
import com.liferay.shopping.service.ShoppingCartLocalServiceUtil;
import com.liferay.shopping.service.ShoppingCouponLocalServiceUtil;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingCartImpl extends ShoppingCartBaseImpl {

	@Override
	public void addItemId(long itemId, String fields) {
		setItemIds(
			StringUtil.add(
				getItemIds(), itemId + fields, StringPool.COMMA, true));
	}

	@Override
	public ShoppingCoupon getCoupon() throws PortalException {
		ShoppingCoupon coupon = null;

		if (Validator.isNotNull(getCouponCodes())) {
			String code = StringUtil.split(getCouponCodes())[0];

			try {
				coupon = ShoppingCouponLocalServiceUtil.getCoupon(code);
			}
			catch (NoSuchCouponException nsce) {
			}
		}

		return coupon;
	}

	@Override
	public Map<ShoppingCartItem, Integer> getItems() {
		return ShoppingCartLocalServiceUtil.getItems(
			getGroupId(), getItemIds());
	}

	@Override
	public int getItemsSize() {
		String[] itemIds = StringUtil.split(getItemIds());

		return itemIds.length;
	}

}