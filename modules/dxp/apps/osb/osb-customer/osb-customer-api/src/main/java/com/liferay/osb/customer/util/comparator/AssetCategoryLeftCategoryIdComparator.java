/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.util.comparator;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Alan Zhang
 */
public class AssetCategoryLeftCategoryIdComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"AssetCategory.leftCategoryId ASC";

	public static final String ORDER_BY_DESC =
		"AssetCategory.leftCategoryId DESC";

	public static final String[] ORDER_BY_FIELDS = {"leftCategoryId"};

	public AssetCategoryLeftCategoryIdComparator() {
		this(false);
	}

	public AssetCategoryLeftCategoryIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AssetCategory assetCategory1 = (AssetCategory)obj1;
		AssetCategory assetCategory2 = (AssetCategory)obj2;

		long leftCategoryId1 = assetCategory1.getLeftCategoryId();
		long leftCategoryId2 = assetCategory2.getLeftCategoryId();

		int value = 0;

		if (leftCategoryId1 < leftCategoryId2) {
			value = -1;
		}
		else if (leftCategoryId1 > leftCategoryId2) {
			value = 1;
		}

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