/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.lpkg;

import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Shuyang Zhou
 */
public class StaticLPKGResolver {

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #getStaticLPKGBundleSymbolicNames}
	 */
	@Deprecated
	public static String getStaticLPKGBundleSymbolicName() {
		return null;
	}

	public static String[] getStaticLPKGBundleSymbolicNames() {
		return _STATIC_LPKG_BUNDLE_SYMBOLIC_NAMES;
	}

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #getStaticLPKGFileNames}
	 */
	@Deprecated
	public static String getStaticLPKGFileName() {
		return null;
	}

	public static String[] getStaticLPKGFileNames() {
		return _STATIC_LPKG_FILE_NAMES;
	}

	private static final String[] _STATIC_LPKG_BUNDLE_SYMBOLIC_NAMES;

	private static final String[] _STATIC_LPKG_FILE_NAMES;

	static {
		String staticLPKGBundleSymbolicNames = System.getProperty(
			"static.lpkg.bundle.symbolic.names");

		String[] staticLPKGBundleSymbolicNameArray = StringUtil.split(
			staticLPKGBundleSymbolicNames);

		String name = ReleaseInfo.getName();

		String lpkgSymbolicNamePrefix = "Liferay ";

		if (name.contains("Community")) {
			lpkgSymbolicNamePrefix = "Liferay CE ";
		}

		for (int i = 0; i < staticLPKGBundleSymbolicNameArray.length; i++) {
			staticLPKGBundleSymbolicNameArray[i] =
				lpkgSymbolicNamePrefix.concat(
					staticLPKGBundleSymbolicNameArray[i]);
		}

		_STATIC_LPKG_BUNDLE_SYMBOLIC_NAMES = staticLPKGBundleSymbolicNameArray;

		String staticLPKGFileNames = System.getProperty(
			"static.lpkg.file.names");

		String[] staticLPKGFileNameArray =
			new String[staticLPKGBundleSymbolicNameArray.length];

		if (staticLPKGFileNames == null) {
			for (int i = 0; i < staticLPKGFileNameArray.length; i++) {
				staticLPKGFileNameArray[i] =
					staticLPKGBundleSymbolicNameArray[i].concat(".lpkg");
			}
		}
		else {
			staticLPKGFileNameArray = staticLPKGFileNames.split(",");
		}

		_STATIC_LPKG_FILE_NAMES = staticLPKGFileNameArray;
	}

}