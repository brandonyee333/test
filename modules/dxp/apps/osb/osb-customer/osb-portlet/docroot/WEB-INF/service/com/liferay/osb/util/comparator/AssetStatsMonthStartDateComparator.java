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

package com.liferay.osb.util.comparator;

import com.liferay.osb.model.AssetStatsMonth;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Peter Shin
 */
public class AssetStatsMonthStartDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "year ASC, month ASC";

	public static final String ORDER_BY_DESC = "year DESC, month DESC";

	public static final String[] ORDER_BY_FIELDS = {"year", "month"};

	public AssetStatsMonthStartDateComparator() {
		this(true);
	}

	public AssetStatsMonthStartDateComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AssetStatsMonth assetStatsMonth1 = (AssetStatsMonth)obj1;
		AssetStatsMonth assetStatsMonth2 = (AssetStatsMonth)obj2;

		Integer year1 = assetStatsMonth1.getYear();
		Integer year2 = assetStatsMonth2.getYear();

		int value = year1.compareTo(year2);

		if (value == 0) {
			Integer month1 = assetStatsMonth1.getMonth();
			Integer month2 = assetStatsMonth2.getMonth();

			value = month1.compareTo(month2);
		}

		if (_asc) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_asc) {
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
		return _asc;
	}

	private boolean _asc;

}