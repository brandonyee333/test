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