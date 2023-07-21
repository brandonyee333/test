/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.security.pacl.Reflection;

import java.security.Permission;

/**
 * @author Brian Wing Shun Chan
 */
public class SecurityChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
	}

	@Override
	public boolean implies(Permission permission) {
		String name = permission.getName();

		if (name.equals(SECURITY_PERMISSION_GET_POLICY)) {
			if (!hasGetPolicy(permission)) {
				logSecurityException(_log, "Attempted to get the policy");

				return false;
			}
		}
		else if (name.equals(SECURITY_PERMISSION_SET_POLICY)) {
			if (!hasSetPolicy(permission)) {
				logSecurityException(_log, "Attempted to set the policy");

				return false;
			}
		}
		else {
			if (_log.isDebugEnabled()) {
				Thread.dumpStack();
			}

			logSecurityException(
				_log,
				StringBundler.concat(
					"Attempted to ", permission.getName(), " on ",
					permission.getActions()));

			return false;
		}

		return true;
	}

	protected boolean hasGetPolicy(Permission permission) {
		int stackIndex = Reflection.getStackIndex(
			new int[] {4, 4}, new int[] {4, 3});

		Class<?> callerClass = Reflection.getCallerClass(stackIndex);

		if (isTrustedCaller(callerClass, permission)) {
			return true;
		}

		logSecurityException(_log, "Attempted to get the policy");

		return false;
	}

	protected boolean hasSetPolicy(Permission permission) {
		int stackIndex = Reflection.getStackIndex(
			new int[] {4, 4}, new int[] {4, 3});

		Class<?> callerClass = Reflection.getCallerClass(stackIndex);

		if (isTrustedCaller(callerClass, permission)) {
			return true;
		}

		logSecurityException(_log, "Attempted to set the policy");

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		SecurityChecker.class);

}