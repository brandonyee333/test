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

import com.liferay.osb.model.TicketEntry;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Kyle Bischof
 */
public class TicketEntrySubjectComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "subject ASC";

	public static final String ORDER_BY_DESC = "subject DESC";

	public static final String[] ORDER_BY_FIELDS = {"subject"};

	public TicketEntrySubjectComparator() {
		this(false);
	}

	public TicketEntrySubjectComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		TicketEntry ticketEntry1 = (TicketEntry)obj1;
		TicketEntry ticketEntry2 = (TicketEntry)obj2;

		int value = 0;

		try {
			String subject1 = ticketEntry1.getSubject();
			String subject2 = ticketEntry2.getSubject();

			if (subject1.compareTo(subject2) < 0) {
				value = -1;
			}
			else if (subject1.compareTo(subject2) > 0) {
				value = 1;
			}
		}
		catch (Exception e) {
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