/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.service.impl;

import com.liferay.shopping.model.ShoppingItemField;
import com.liferay.shopping.service.base.ShoppingItemFieldLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingItemFieldLocalServiceImpl
	extends ShoppingItemFieldLocalServiceBaseImpl {

	@Override
	public List<ShoppingItemField> getItemFields(long itemId) {
		return shoppingItemFieldPersistence.findByItemId(itemId);
	}

}