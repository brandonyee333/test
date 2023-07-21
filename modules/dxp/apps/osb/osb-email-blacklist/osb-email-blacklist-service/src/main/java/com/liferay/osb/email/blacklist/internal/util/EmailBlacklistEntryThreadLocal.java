/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.internal.util;

import com.liferay.petra.lang.CentralizedThreadLocal;

/**
 * @author Ryan Park
 * @author Jamie Sammons
 */
public class EmailBlacklistEntryThreadLocal {

	public static boolean isVerify() {
		return _verify.get();
	}

	public static void setVerify(Boolean verify) {
		_verify.set(verify);
	}

	private static final ThreadLocal<Boolean> _verify =
		new CentralizedThreadLocal<>(
			EmailBlacklistEntryThreadLocal.class + "._verify",
			() -> Boolean.TRUE);

}