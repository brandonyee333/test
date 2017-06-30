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

import com.liferay.osb.model.AssetStatsDay;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Peter Shin
 */
public class AssetStatsDayStartDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "year ASC, day ASC";

	public static final String ORDER_BY_DESC = "year DESC, day DESC";

	public static final String[] ORDER_BY_FIELDS = {"year", "day"};

	public AssetStatsDayStartDateComparator() {
		this(true);
	}

	public AssetStatsDayStartDateComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AssetStatsDay assetStatsDay1 = (AssetStatsDay)obj1;
		AssetStatsDay assetStatsDay2 = (AssetStatsDay)obj2;

		Integer year1 = assetStatsDay1.getYear();
		Integer year2 = assetStatsDay2.getYear();

		int value = year1.compareTo(year2);

		if (value == 0) {
			Integer day1 = assetStatsDay1.getDay();
			Integer day2 = assetStatsDay2.getDay();

			value = day1.compareTo(day2);
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