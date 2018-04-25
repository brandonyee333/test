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