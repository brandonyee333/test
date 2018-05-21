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

package com.liferay.osb.util;

import com.liferay.portal.kernel.util.StringPool;

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
		else {
			return StringPool.BLANK;
		}
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
		else {
			return 0;
		}
	}

	private static final int[] _DISPLAY_VISIBILITIES =
		{PUBLIC, WORKERS, LIFERAY_INC};

}