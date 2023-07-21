/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.util.comparator;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Amos Fong
 */
public class AccountEntryLastZendeskAuditDateComparator
	extends OrderByComparator {

	public static final String ORDER_BY_ASC = "lastZendeskAuditDate ASC";

	public static final String ORDER_BY_DESC = "lastZendeskAuditDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"lastZendeskAuditDate"};

	public AccountEntryLastZendeskAuditDateComparator() {
		this(true);
	}

	public AccountEntryLastZendeskAuditDateComparator(boolean asc) {
		_asc = asc;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		AccountEntry accountEntry1 = (AccountEntry)obj1;
		AccountEntry accountEntry2 = (AccountEntry)obj2;

		int value = DateUtil.compareTo(
			accountEntry1.getLastZendeskAuditDate(),
			accountEntry2.getLastZendeskAuditDate());

		if (_asc) {
			return value;
		}

		return -value;
	}

	@Override
	public String getOrderBy() {
		if (_asc) {
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
		return _asc;
	}

	private boolean _asc;

}