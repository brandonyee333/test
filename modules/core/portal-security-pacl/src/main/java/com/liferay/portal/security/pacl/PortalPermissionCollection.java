/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.util.StringPool;

import java.security.Permission;
import java.security.Policy;

/**
 * @author Raymond Augé
 */
public class PortalPermissionCollection extends LenientPermissionCollection {

	public PortalPermissionCollection(PACLPolicy paclPolicy) {
		_paclPolicy = paclPolicy;
	}

	public ClassLoader getClassLoader() {
		return _paclPolicy.getClassLoader();
	}

	public PACLPolicy getPACLPolicy() {
		return _paclPolicy;
	}

	public Policy getPolicy() {
		return _paclPolicy.getPolicy();
	}

	@Override
	public boolean implies(Permission permission) {
		if (!_paclPolicy.isActive()) {
			return true;
		}

		if (permission instanceof PACLUtil.Permission) {
			PACLPolicyThreadLocal.set(_paclPolicy);

			return true;
		}

		if (_paclPolicy.implies(permission)) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		Class<?> clazz = getClass();

		String className = clazz.getSimpleName();

		return className.concat(
			StringPool.POUND
		).concat(
			_paclPolicy.toString()
		);
	}

	private final PACLPolicy _paclPolicy;

}