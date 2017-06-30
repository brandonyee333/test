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

import com.liferay.osb.model.AppPackage;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Joan Kim
 */
public class AppPackageCompatibilityComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"OSB_AppPackage.compatibility ASC";

	public static final String ORDER_BY_DESC =
		"OSB_AppPackage.compatibility DESC";

	public static final String[] ORDER_BY_FIELDS = {"compatibility"};

	public AppPackageCompatibilityComparator() {
		this(false);
	}

	public AppPackageCompatibilityComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AppPackage appPackage1 = (AppPackage)obj1;
		AppPackage appPackage2 = (AppPackage)obj2;

		int value = 0;

		long compatibility1 = appPackage1.getCompatibility();
		long compatibility2 = appPackage2.getCompatibility();

		if (compatibility1 < compatibility2) {
			value = -1;
		}
		else if (compatibility1 > compatibility2) {
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