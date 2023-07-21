/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.util;

/**
 * @author Shinn Lok
 */
public class Validator {

	public static boolean isBlank(String s) {
		if (s == null) {
			return true;
		}

		return s.isEmpty();
	}

}