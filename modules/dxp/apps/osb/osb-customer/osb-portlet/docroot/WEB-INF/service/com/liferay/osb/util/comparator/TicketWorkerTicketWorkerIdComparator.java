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

import com.liferay.osb.model.TicketWorker;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Brent Krone-Schmidt
 */
public class TicketWorkerTicketWorkerIdComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "ticketWorkerId ASC";

	public static final String ORDER_BY_DESC = "ticketWorkerId DESC";

	public static final String[] ORDER_BY_FIELDS = {"ticketWorkerId"};

	public TicketWorkerTicketWorkerIdComparator() {
		this(false);
	}

	public TicketWorkerTicketWorkerIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		TicketWorker ticketWorker1 = (TicketWorker)obj1;
		TicketWorker ticketWorker2 = (TicketWorker)obj2;

		int value =
			(int)(ticketWorker1.getTicketWorkerId() -
				ticketWorker2.getTicketWorkerId());

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