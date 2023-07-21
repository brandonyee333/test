/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.constants;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Timothy Bell
 */
public class LoopPersonRelConstants {

	public static final String LABEL_MANAGER = "manager";

	public static final String LABEL_PRIMARY_MANAGER = "primary-manager";

	public static final int TYPE_MANAGER = 1;

	public static final int TYPE_PRIMARY_MANAGER = 2;

	public static String getTypeLabel(int type) {
		if (type == 1) {
			return LABEL_MANAGER;
		}
		else if (type == 2) {
			return LABEL_PRIMARY_MANAGER;
		}

		return StringPool.BLANK;
	}

}