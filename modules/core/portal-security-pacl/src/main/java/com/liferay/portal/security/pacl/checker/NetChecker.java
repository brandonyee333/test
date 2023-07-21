/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.security.pacl.Reflection;

import java.security.Permission;

/**
 * @author Brian Wing Shun Chan
 */
public class NetChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
	}

	@Override
	public boolean implies(Permission permission) {
		String name = permission.getName();

		if (name.equals(NET_PERMISSION_GET_PROXY_SELECTOR)) {
			if (!hasGetProxySelector(permission)) {
				logSecurityException(_log, "Attempted to get proxy selector");

				return false;
			}
		}
		else if (name.equals(NET_PERMISSION_SPECIFY_STREAM_HANDLER)) {
			if (!hasSpecifyStreamHandler(permission)) {
				logSecurityException(
					_log, "Attempted to specify stream handler");

				return false;
			}
		}
		else {
			logSecurityException(
				_log, "Attempted " + name + " network operation");

			return false;
		}

		return true;
	}

	protected boolean hasGetProxySelector(Permission permission) {
		int stackIndex = Reflection.getStackIndex(4, 3);

		Class<?> callerClass = Reflection.getCallerClass(stackIndex);

		if (isTrustedCaller(callerClass, permission)) {
			return true;
		}

		return false;
	}

	protected boolean hasSpecifyStreamHandler(Permission permission) {
		int stackIndex = Reflection.getStackIndex(4, 3);

		Class<?> callerClass = Reflection.getCallerClass(stackIndex);

		if (isTrustedCaller(callerClass, permission)) {
			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(NetChecker.class);

}