/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.model;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingOrderConstants {

	public static final String STATUS_CHECKOUT = "LIFERAY_STATUS_CHECKOUT";

	public static final String STATUS_COMPLETED = "Completed";

	public static final String STATUS_DENIED = "Denied";

	public static final String STATUS_LATEST = "LIFERAY_STATUS_LATEST";

	public static final String STATUS_PENDING = "Pending";

	public static final String STATUS_REFUNDED = "Refunded";

	public static final String[] STATUSES = {
		"checkout", "completed", "denied", "pending", "refunded"
	};

}