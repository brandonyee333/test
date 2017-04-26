/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.api.util.comparator;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Alan Zhang
 */
public class AssetCategoryNameComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "AssetCategory.name ASC";

	public static final String ORDER_BY_DESC = "AssetCategory.name DESC";

	public static final String[] ORDER_BY_FIELDS = {"name"};

	public AssetCategoryNameComparator() {
		this(false);
	}

	public AssetCategoryNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AssetCategory assetCategory1 = (AssetCategory)obj1;
		AssetCategory assetCategory2 = (AssetCategory)obj2;

		String name1 = assetCategory1.getName();
		String name2 = assetCategory2.getName();

		int value = name1.compareTo(name2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
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