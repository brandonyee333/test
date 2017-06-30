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

import com.liferay.osb.model.AssetLicense;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Douglas Wong
 */
public class AssetLicenseLicenseTypeAllotmentComparator
	extends OrderByComparator {

	public static final String ORDER_BY_ASC = "licenseTypeAllotment ASC";

	public static final String ORDER_BY_DESC = "licenseTypeAllotment DESC";

	public static final String[] ORDER_BY_FIELDS = {"licenseTypeAllotment"};

	public AssetLicenseLicenseTypeAllotmentComparator() {
		this(true);
	}

	public AssetLicenseLicenseTypeAllotmentComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AssetLicense assetLicense1 = (AssetLicense)obj1;
		AssetLicense assetLicense2 = (AssetLicense)obj2;

		int value = 0;

		long licenseTypeAllotment1 = assetLicense1.getLicenseTypeAllotment();
		long licenseTypeAllotment2 = assetLicense2.getLicenseTypeAllotment();

		if (licenseTypeAllotment1 < licenseTypeAllotment2) {
			value = -1;
		}
		else if (licenseTypeAllotment1 > licenseTypeAllotment2) {
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