/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl;

/**
 * @author Raymond Augé
 */
public class PACLPolicyThreadLocal {

	public static PACLPolicy get() {
		return _paclPolicy.get();
	}

	public static void set(PACLPolicy paclPolicy) {
		_paclPolicy.set(paclPolicy);
	}

	private static final ThreadLocal<PACLPolicy> _paclPolicy =
		new ThreadLocal<>();

}