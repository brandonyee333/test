/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.util.comparator;

import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Amos Fong
 */
public class ProductEntryNameComparator extends OrderByComparator {

	public ProductEntryNameComparator() {
		this(false);
	}

	public ProductEntryNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		ProductEntry productEntry1 = (ProductEntry)obj1;
		ProductEntry productEntry2 = (ProductEntry)obj2;

		String name1 = StringUtil.toLowerCase(productEntry1.getName());
		String name2 = StringUtil.toLowerCase(productEntry2.getName());

		int value = name1.compareTo(name2);

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}