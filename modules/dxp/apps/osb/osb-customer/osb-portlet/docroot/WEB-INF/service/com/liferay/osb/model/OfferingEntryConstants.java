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

	public static final int TYPE_DEVELOPER = 2;

	public static final int TYPE_REGULAR = 1;

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
		if (type == TYPE_DEVELOPER) {
			return "developer";
		}
		else if (type == TYPE_REGULAR) {
			return "regular";
		}
		else if (type == TYPE_TRIAL) {
			return "trial";
		}
		else {
			return null;
		}
	}

}