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