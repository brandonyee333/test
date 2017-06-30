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

import com.liferay.osb.model.TicketFeedback;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Alan Zhang
 */
public class TicketFeedbackCreateDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"OSB_TicketFeedback.createDate ASC";

	public static final String ORDER_BY_DESC =
		"OSB_TicketFeedback.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public TicketFeedbackCreateDateComparator() {
		this(false);
	}

	public TicketFeedbackCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		TicketFeedback ticketFeedback1 = (TicketFeedback)obj1;
		TicketFeedback ticketFeedback2 = (TicketFeedback)obj2;

		int value = DateUtil.compareTo(
			ticketFeedback1.getCreateDate(), ticketFeedback2.getCreateDate());

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