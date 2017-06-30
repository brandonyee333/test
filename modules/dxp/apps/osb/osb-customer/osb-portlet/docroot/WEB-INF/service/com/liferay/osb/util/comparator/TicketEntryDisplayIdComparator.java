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
import com.liferay.osb.model.TicketEntry;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Jeremy Fu
 */
public class TicketEntryDisplayIdComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "code_ ASC, ticketId ASC";

	public static final String ORDER_BY_DESC = "code_ DESC, ticketId DESC";

	public static final String[] ORDER_BY_FIELDS = {"code_", "ticketId"};

	public TicketEntryDisplayIdComparator() {
		this(false);
	}

	public TicketEntryDisplayIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		TicketEntry ticketEntry1 = (TicketEntry)obj1;
		TicketEntry ticketEntry2 = (TicketEntry)obj2;

		int value = 0;

		try {
			AccountEntry accountEntry1 = ticketEntry1.getAccountEntry();
			AccountEntry accountEntry2 = ticketEntry2.getAccountEntry();

			String accountCode1 = accountEntry1.getCode();
			String accountCode2 = accountEntry2.getCode();

			if (accountCode1.compareTo(accountCode2) < 0) {
				value = -1;
			}
			else if (accountCode1.compareTo(accountCode2) > 0) {
				value = 1;
			}
		}
		catch (Exception e) {
		}

		if (value == 0) {
			if (ticketEntry1.getTicketId() < ticketEntry2.getTicketId()) {
				value = -1;
			}
			else if (ticketEntry1.getTicketId() > ticketEntry2.getTicketId()) {
				value = 1;
			}
		}

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
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
		return _ascending;
	}

	private boolean _ascending;

}