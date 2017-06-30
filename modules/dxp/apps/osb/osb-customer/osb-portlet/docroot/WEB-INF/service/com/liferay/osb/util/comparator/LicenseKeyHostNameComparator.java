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

import com.liferay.osb.model.LicenseKey;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Joan Kim
 */
public class LicenseKeyHostNameComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"OSB_LicenseKey.hostName ASC, OSB_LicenseKey.expirationDate ASC";

	public static final String ORDER_BY_DESC =
		"OSB_LicenseKey.hostName DESC, OSB_LicenseKey.expirationDate DESC";

	public static final String[] ORDER_BY_FIELDS =
		{"hostName", "expirationDate"};

	public LicenseKeyHostNameComparator() {
		this(true);
	}

	public LicenseKeyHostNameComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		LicenseKey licenseKey1 = (LicenseKey)obj1;
		LicenseKey licenseKey2 = (LicenseKey)obj2;

		int value = licenseKey1.getHostName().toLowerCase().compareTo(
			licenseKey2.getHostName().toLowerCase());

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