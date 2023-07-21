/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.token.auth.comparator;

import com.liferay.osb.loop.token.auth.model.TokenAuthEntry;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Bruno Farache
 */
public class TokenAuthEntryUserNameComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "userName ASC";

	public static final String ORDER_BY_DESC = "userName DESC";

	public static final String[] ORDER_BY_FIELDS = {"userName"};

	public TokenAuthEntryUserNameComparator() {
		this(true);
	}

	public TokenAuthEntryUserNameComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		TokenAuthEntry tokenAuthEntry1 = (TokenAuthEntry)obj1;
		TokenAuthEntry tokenAuthEntry2 = (TokenAuthEntry)obj2;

		String userName1 = tokenAuthEntry1.getUserName();
		String userName2 = tokenAuthEntry2.getUserName();

		int value = userName1.compareToIgnoreCase(userName2);

		if (_ascending) {
			return value;
		}

		return -value;
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}

		return ORDER_BY_DESC;
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}