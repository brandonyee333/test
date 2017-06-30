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

import com.liferay.osb.model.AccountEntry;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Amos Fong
 */
public class AccountEntryNameComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "accountEntryName ASC";

	public static final String ORDER_BY_DESC = "accountEntryName DESC";

	public static final String[] ORDER_BY_FIELDS = {"accountEntryName"};

	public AccountEntryNameComparator() {
		this(false);
	}

	public AccountEntryNameComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AccountEntry accountEntry1 = (AccountEntry)obj1;
		AccountEntry accountEntry2 = (AccountEntry)obj2;

		int value = accountEntry1.getName().toLowerCase().compareTo(
			accountEntry2.getName().toLowerCase());

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