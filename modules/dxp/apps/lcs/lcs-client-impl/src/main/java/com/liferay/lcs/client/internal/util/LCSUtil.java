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