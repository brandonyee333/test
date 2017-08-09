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

package com.liferay.osb.model;

/**
 * @author Amos Fong
 */
public class TicketFlagConstants {

	public static final int FLAG_TRUE = 1;

	public static final int TYPE_LIFERAY_COMMENT_UNREAD = 6;

	public static final int TYPE_PENDING_CUSTOMER = 2;

	public static final int TYPE_PENDING_LIFERAY = 3;

	public static final int TYPE_PENDING_PARTNER = 4;

	public static final int TYPE_READ_INSTRUCTIONS = 1;

	public static final int TYPE_READ_SOLUTION = 5;

	public static final int[] TYPES_PENDING =
		{TYPE_PENDING_CUSTOMER, TYPE_PENDING_LIFERAY, TYPE_PENDING_PARTNER};

	public static String getTypeLabel(int type) {
		if (type == TYPE_LIFERAY_COMMENT_UNREAD) {
			return "liferay-comment-unread";
		}
		else if (type == TYPE_PENDING_CUSTOMER) {
			return "customer";
		}
		else if (type == TYPE_PENDING_LIFERAY) {
			return "liferay";
		}
		else if (type == TYPE_PENDING_PARTNER) {
			return "partner";
		}
		else if (type == TYPE_READ_INSTRUCTIONS) {
			return "read-instructions";
		}
		else if (type == TYPE_READ_SOLUTION) {
			return "read-solution";
		}
		else {
			return null;
		}
	}

}