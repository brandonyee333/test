/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.shopping.model.ShoppingItem;
import com.liferay.shopping.model.ShoppingItemPrice;
import com.liferay.shopping.model.ShoppingItemPriceConstants;
import com.liferay.shopping.service.base.ShoppingItemPriceLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingItemPriceLocalServiceImpl
	extends ShoppingItemPriceLocalServiceBaseImpl {

	@Override
	public List<ShoppingItemPrice> getItemPrices(long itemId)
		throws PortalException {

		ShoppingItem item = shoppingItemPersistence.findByPrimaryKey(itemId);

		List<ShoppingItemPrice> itemPrices =
			shoppingItemPricePersistence.findByItemId(itemId);

		if (itemPrices.isEmpty()) {
			itemPrices = new ArrayList<>();

			ShoppingItemPrice itemPrice = shoppingItemPricePersistence.create(
				0);

			itemPrice.setItemId(itemId);
			itemPrice.setMinQuantity(item.getMinQuantity());
			itemPrice.setMaxQuantity(item.getMaxQuantity());
			itemPrice.setPrice(item.getPrice());
			itemPrice.setDiscount(item.getDiscount());
			itemPrice.setTaxable(item.isTaxable());
			itemPrice.setShipping(item.getShipping());
			itemPrice.setUseShippingFormula(item.isUseShippingFormula());
			itemPrice.setStatus(
				ShoppingItemPriceConstants.STATUS_ACTIVE_DEFAULT);

			itemPrices.add(itemPrice);
		}

		return itemPrices;
	}

}