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

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Kyle Bischof
 */
public class TicketCallConstants {

	public static final String NOT_AVAILABLE = "N/A";

	public static int TYPE_INCOMING = 1;

	public static int TYPE_OUTGOING = 2;

	public static String getTypeLabel(int type) {
		if (type == TYPE_INCOMING) {
			return "incoming";
		}
		else if (type == TYPE_OUTGOING) {
			return "outgoing";
		}
		else {
			return StringPool.BLANK;
		}
	}

}