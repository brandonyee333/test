/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.util;

import oasis.names.tc.wsrp.v2.types.LocalizedString;

/**
 * @author Brian Wing Shun Chan
 */
public class LocalizedStringUtil {

	public static String getLocalizedStringValue(
		LocalizedString localizedString) {

		return getLocalizedStringValue(localizedString, null);
	}

	public static String getLocalizedStringValue(
		LocalizedString localizedString, String defaultValue) {

		if (localizedString == null) {
			return defaultValue;
		}

		return localizedString.getValue();
	}

	public static String[] getLocalizedStringValues(
		LocalizedString[] localizedStrings) {

		return getLocalizedStringValues(localizedStrings, null);
	}

	public static String[] getLocalizedStringValues(
		LocalizedString[] localizedStrings, String[] defaultValue) {

		if (localizedStrings == null) {
			return defaultValue;
		}

		String[] values = new String[localizedStrings.length];

		for (int i = 0; i < localizedStrings.length; i++) {
			values[i] = getLocalizedStringValue(localizedStrings[i]);
		}

		return values;
	}

}