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
public class OfferingEntryConstants {

	public static final int QUANTITY_UNLIMITED = 10000;

	public static final int SIZING_1 = 1;

	public static final int SIZING_2 = 2;

	public static final int SIZING_3 = 3;

	public static final int SIZING_4 = 4;

	public static final int STATUS_ACTIVE = 1;

	public static final int STATUS_CLOSED = 2;

	public static final int STATUS_ON_HOLD = 3;

	public static final int STATUS_PENDING = 4;

	public static final int TYPE_REGULAR = 1;

	public static final int TYPE_SUBSCRIPTION = 2;

	public static final int TYPE_TRIAL = 3;

	public static String getSizingLabel(int sizing) {
		if (sizing == SIZING_1) {
			return "sizing-1";
		}
		else if (sizing == SIZING_2) {
			return "sizing-2";
		}
		else if (sizing == SIZING_3) {
			return "sizing-3";
		}
		else if (sizing == SIZING_4) {
			return "sizing-4";
		}

		return StringPool.BLANK;
	}

	public static String getStatusLabel(int status) {
		if (status == STATUS_ACTIVE) {
			return "active";
		}
		else if (status == STATUS_CLOSED) {
			return "closed";
		}
		else if (status == STATUS_ON_HOLD) {
			return "on-hold";
		}
		else if (status == STATUS_PENDING) {
			return "pending";
		}
		else {
			return null;
		}
	}

	public static String getTypeLabel(int type) {
		if (type == TYPE_REGULAR) {
			return "regular";
		}
		else if (type == TYPE_SUBSCRIPTION) {
			return "subscription";
		}
		else if (type == TYPE_TRIAL) {
			return "trial";
		}
		else {
			return null;
		}
	}

}