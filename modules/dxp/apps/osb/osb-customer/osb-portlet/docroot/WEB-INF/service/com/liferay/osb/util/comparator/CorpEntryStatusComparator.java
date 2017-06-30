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

import com.liferay.osb.model.CorpEntry;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Rachael Koestartyo
 */
public class CorpEntryStatusComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "OSB_CorpEntry.status ASC";

	public static final String ORDER_BY_DESC = "OSB_CorpEntry.status DESC";

	public static final String[] ORDER_BY_FIELDS = {"status"};

	public CorpEntryStatusComparator() {
		this(true);
	}

	public CorpEntryStatusComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		CorpEntry corpEntry1 = (CorpEntry)obj1;
		CorpEntry corpEntry2 = (CorpEntry)obj2;

		int value = 0;

		if (corpEntry1.getStatus() < corpEntry2.getStatus()) {
			value = -1;
		}
		else if (corpEntry1.getStatus() > corpEntry2.getStatus()) {
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