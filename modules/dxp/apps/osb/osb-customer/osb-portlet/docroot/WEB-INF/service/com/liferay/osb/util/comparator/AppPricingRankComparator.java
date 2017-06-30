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

import com.liferay.osb.model.AppPricing;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Douglas Wong
 */
public class AppPricingRankComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "rank ASC";

	public static final String ORDER_BY_DESC = "rank DESC";

	public static final String[] ORDER_BY_FIELDS = {"rank"};

	public AppPricingRankComparator() {
		this(true);
	}

	public AppPricingRankComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AppPricing appPricing1 = (AppPricing)obj1;
		AppPricing appPricing2 = (AppPricing)obj2;

		int value = 0;

		int rank1 = appPricing1.getRank();
		int rank2 = appPricing2.getRank();

		if (rank1 < rank2) {
			value = -1;
		}
		else if (rank1 > rank2) {
			value = 1;
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