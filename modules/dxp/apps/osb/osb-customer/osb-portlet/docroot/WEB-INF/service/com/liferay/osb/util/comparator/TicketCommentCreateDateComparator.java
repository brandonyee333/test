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

import com.liferay.osb.model.TicketComment;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Amos Fong
 */
public class TicketCommentCreateDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"OSB_TicketComment.createDate ASC";

	public static final String ORDER_BY_DESC =
		"OSB_TicketComment.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public TicketCommentCreateDateComparator() {
		this(false);
	}

	public TicketCommentCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		TicketComment ticketComment1 = (TicketComment)obj1;
		TicketComment ticketComment2 = (TicketComment)obj2;

		int value = DateUtil.compareTo(
			ticketComment1.getCreateDate(), ticketComment2.getCreateDate());

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