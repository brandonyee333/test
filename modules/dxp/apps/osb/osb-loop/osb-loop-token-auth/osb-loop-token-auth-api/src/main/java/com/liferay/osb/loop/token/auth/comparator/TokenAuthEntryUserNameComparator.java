/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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