/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.util.regex.Pattern;

/**
 * @author Julio Camarero
 * @author Samuel Kong
 */
public class FriendlyURLNormalizerUtil {

	public static FriendlyURLNormalizer getFriendlyURLNormalizer() {
		return _friendlyURLNormalizer;
	}

	public static String normalize(String friendlyURL) {
		return getFriendlyURLNormalizer().normalize(friendlyURL);
	}

	/**
	 * @deprecated As of Wilberforce (7.0.x), with no direct replacement
	 */
	@Deprecated
	public static String normalize(
		String friendlyURL, Pattern friendlyURLPattern) {

		return getFriendlyURLNormalizer().normalize(
			friendlyURL, friendlyURLPattern);
	}

	public static String normalizeWithEncoding(String friendlyURL) {
		return getFriendlyURLNormalizer().normalizeWithEncoding(friendlyURL);
	}

	public static String normalizeWithPeriodsAndSlashes(String friendlyURL) {
		return getFriendlyURLNormalizer().normalizeWithPeriodsAndSlashes(
			friendlyURL);
	}

	public void setFriendlyURLNormalizer(
		FriendlyURLNormalizer friendlyURLNormalizer) {

		_friendlyURLNormalizer = friendlyURLNormalizer;
	}

	private static volatile FriendlyURLNormalizer _friendlyURLNormalizer =
		ServiceProxyFactory.newServiceTrackedInstance(
			FriendlyURLNormalizer.class, FriendlyURLNormalizerUtil.class,
			"_friendlyURLNormalizer", true);

}