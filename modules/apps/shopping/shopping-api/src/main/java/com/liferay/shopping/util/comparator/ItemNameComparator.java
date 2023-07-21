/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.shopping.model.ShoppingItem;

/**
 * @author Brian Wing Shun Chan
 */
public class ItemNameComparator extends OrderByComparator<ShoppingItem> {

	public static final String ORDER_BY_ASC =
		"ShoppingItem.categoryId ASC, ShoppingItem.name ASC";

	public static final String ORDER_BY_DESC =
		"ShoppingItem.categoryId DESC, ShoppingItem.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"categoryId", "name"};

	public ItemNameComparator() {
		this(false);
	}

	public ItemNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(ShoppingItem item1, ShoppingItem item2) {
		Long categoryId1 = item1.getCategoryId();
		Long categoryId2 = item2.getCategoryId();

		int value = categoryId1.compareTo(categoryId2);

		if (value == 0) {
			String name1 = StringUtil.toLowerCase(item1.getName());
			String name2 = StringUtil.toLowerCase(item2.getName());

			value = name1.compareTo(name2);

			if (value == 0) {
				Long itemId1 = item1.getItemId();
				Long itemId2 = item2.getItemId();

				value = itemId1.compareTo(itemId2);
			}
		}

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}

		return ORDER_BY_DESC;
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}