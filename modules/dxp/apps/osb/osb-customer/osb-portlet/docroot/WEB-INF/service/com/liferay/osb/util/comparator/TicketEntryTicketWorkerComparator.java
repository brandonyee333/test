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
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.service.TicketWorkerLocalServiceUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

/**
 * @author Kyle Bischof
 */
public class TicketEntryTicketWorkerComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"User_.firstName ASC, User_.middleName ASC, User_.lastName ASC";

	public static final String ORDER_BY_DESC =
		"User_.firstName DESC, User_.middleName DESC, User_.lastName DESC";

	public static final String[] ORDER_BY_FIELDS = {
		"User_.firstName", "User_.middleName", "User_.lastName"};

	public TicketEntryTicketWorkerComparator() {
		this(false);
	}

	public TicketEntryTicketWorkerComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		TicketEntry ticketEntry1 = (TicketEntry)obj1;
		TicketEntry ticketEntry2 = (TicketEntry)obj2;

		int value = 0;

		try {
			TicketWorker ticketWorker1 =
				TicketWorkerLocalServiceUtil.fetchPrimaryTicketWorker(
					ticketEntry1.getTicketEntryId());
			TicketWorker ticketWorker2 =
				TicketWorkerLocalServiceUtil.fetchPrimaryTicketWorker(
					ticketEntry2.getTicketEntryId());

			String ticketWorkerUserFullName1 = StringPool.BLANK;
			String ticketWorkerUserFullName2 = StringPool.BLANK;

			if ((ticketWorker1 != null) && (ticketWorker2 != null)) {
				User ticketWorkerUser1 = UserLocalServiceUtil.getUser(
					ticketWorker1.getUserId());
				User ticketWorkerUser2 = UserLocalServiceUtil.getUser(
						ticketWorker2.getUserId());

				ticketWorkerUserFullName1 = ticketWorkerUser1.getFullName();
				ticketWorkerUserFullName2 = ticketWorkerUser2.getFullName();
			}

			if (ticketWorkerUserFullName1.compareTo(
					ticketWorkerUserFullName2) < 0) {

				value = -1;
			}
			else if (ticketWorkerUserFullName1.compareTo(
						ticketWorkerUserFullName2) > 0) {

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