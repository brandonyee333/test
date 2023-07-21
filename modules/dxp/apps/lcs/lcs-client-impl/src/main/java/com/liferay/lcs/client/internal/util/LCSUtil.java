/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.util;

import com.liferay.portal.kernel.util.ReleaseInfo;

import java.lang.reflect.Field;

import java.util.StringTokenizer;

/**
 * @author Igor Beslic
 * @author Ivica Cardic
 */
public class LCSUtil {

	public static String getPortalEdition() {
		try {
			Field field = ReleaseInfo.class.getDeclaredField(
				"_VERSION_DISPLAY_NAME");

			field.setAccessible(true);

			StringTokenizer stringTokenizer = new StringTokenizer(
				(String)field.get(null));

			stringTokenizer.nextToken();

			return stringTokenizer.nextToken();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}