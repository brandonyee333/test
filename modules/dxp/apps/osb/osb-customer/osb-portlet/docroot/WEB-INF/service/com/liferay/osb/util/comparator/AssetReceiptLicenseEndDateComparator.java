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

import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Douglas Wong
 */
public class AssetReceiptLicenseEndDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"OSB_AssetReceiptLicense.endDate ASC, usageType ASC, " +
			"licenseTypeAllotment ASC";

	public static final String ORDER_BY_DESC =
		"OSB_AssetReceiptLicense.endDate DESC, usageType ASC, " +
			"licenseTypeAllotment ASC";

	public static final String[] ORDER_BY_FIELDS = {
		"endDate", "usageType", "licenseTypeAllotment"
	};

	public AssetReceiptLicenseEndDateComparator() {
		this(false);
	}

	public AssetReceiptLicenseEndDateComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AssetReceiptLicense assetReceiptLicense1 = (AssetReceiptLicense)obj1;
		AssetReceiptLicense assetReceiptLicense2 = (AssetReceiptLicense)obj2;

		int value = DateUtil.compareTo(
			assetReceiptLicense1.getEndDate(),
			assetReceiptLicense2.getEndDate());

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