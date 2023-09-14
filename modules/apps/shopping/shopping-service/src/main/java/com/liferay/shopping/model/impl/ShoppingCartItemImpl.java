/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.model.impl;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.shopping.model.ShoppingCartItem;
import com.liferay.shopping.model.ShoppingItem;

import java.util.Objects;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingCartItemImpl implements ShoppingCartItem {

	public static String[] getFieldsArray(String fields) {
		return StringUtil.split(fields, '&');
	}

	public ShoppingCartItemImpl(ShoppingItem item, String fields) {
		_item = item;
		_fields = fields;
	}

	@Override
	public int compareTo(ShoppingCartItem cartItem) {
		if (cartItem == null) {
			return -1;
		}

		int value = getItem().compareTo(cartItem.getItem());

		if (value == 0) {
			value = getFields().compareTo(cartItem.getFields());
		}

		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ShoppingCartItem)) {
			return false;
		}

		ShoppingCartItem cartItem = (ShoppingCartItem)obj;

		if (Objects.equals(getItem(), cartItem.getItem()) &&
			Objects.equals(getFields(), cartItem.getFields())) {

			return true;
		}

		return false;
	}

	@Override
	public String getCartItemId() {
		long itemId = getItem().getItemId();

		if (Validator.isNull(_fields)) {
			return String.valueOf(itemId);
		}

		return itemId + "|" + _fields;
	}

	@Override
	public String getFields() {
		return _fields;
	}

	@Override
	public String[] getFieldsArray() {
		return getFieldsArray(_fields);
	}

	@Override
	public ShoppingItem getItem() {
		return _item;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, _item.getItemId());

		return HashUtil.hash(hashCode, _fields);
	}

	private final String _fields;
	private final ShoppingItem _item;

}