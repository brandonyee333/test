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

package com.liferay.osb.util.comparator;

import com.liferay.osb.model.AccountEntry;
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