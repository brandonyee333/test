/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.shopping.model.ShoppingCategory;
import com.liferay.shopping.model.ShoppingItem;

/**
 * @author Eudaldo Alonso
 */
public class CategoryItemNameComparator extends OrderByComparator<Object> {

	public static final String ORDER_BY_ASC = "modelCategory DESC, name ASC";

	public static final String ORDER_BY_DESC = "modelCategory DESC, name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public CategoryItemNameComparator() {
		this(false);
	}

	public CategoryItemNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object object1, Object object2) {
		int value = 0;

		if ((object1 instanceof ShoppingCategory) &&
			(object2 instanceof ShoppingCategory)) {

			String name1 = getName(object1);
			String name2 = getName(object2);

			value = name1.compareTo(name2);
		}
		else if (object1 instanceof ShoppingCategory) {
			value = -1;
		}
		else if (object2 instanceof ShoppingCategory) {
			value = 1;
		}
		else {
			String name1 = getName(object1);
			String name2 = getName(object2);

			value = name1.compareTo(name2);
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

	protected String getName(Object object) {
		if (object instanceof ShoppingItem) {
			ShoppingItem item = (ShoppingItem)object;

			return item.getName();
		}

		ShoppingCategory category = (ShoppingCategory)object;

		return category.getName();
	}

	private final boolean _ascending;

}