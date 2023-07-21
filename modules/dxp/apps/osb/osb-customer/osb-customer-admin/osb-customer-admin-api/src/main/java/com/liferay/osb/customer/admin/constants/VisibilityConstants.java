/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.constants;

import com.liferay.petra.string.StringPool;

/**
 * @author Alan Zhang
 */
public class VisibilityConstants {

	public static final int ADMIN = 4;

	public static final int LIFERAY_INC = 3;

	public static final int PUBLIC = 1;

	public static final int WORKERS = 2;

	public static String toLabel(int visibility) {
		if (visibility == ADMIN) {
			return "admin";
		}
		else if (visibility == LIFERAY_INC) {
			return "liferay";
		}
		else if (visibility == PUBLIC) {
			return "public";
		}
		else if (visibility == WORKERS) {
			return "workers";
		}

		return StringPool.BLANK;
	}

	public static int toVisibility(String label) {
		if (label.equals("admin")) {
			return ADMIN;
		}
		else if (label.equals("liferay")) {
			return LIFERAY_INC;
		}
		else if (label.equals("public")) {
			return PUBLIC;
		}
		else if (label.equals("workers")) {
			return WORKERS;
		}

		return 0;
	}

}