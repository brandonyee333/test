/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.security.Permission;

/**
 * @author Brian Wing Shun Chan
 */
public class DefaultRejectChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
	}

	@Override
	public boolean implies(Permission permission) {
		if (_log.isDebugEnabled()) {
			Thread.dumpStack();
		}

		Class<?> clazz = permission.getClass();
		String name = permission.getName();
		String actions = permission.getActions();

		if (Validator.isNotNull(actions)) {
			logSecurityException(
				_log,
				StringBundler.concat(
					"Permission ", clazz.getName(), " attempted to ", name,
					" on ", actions));
		}
		else {
			logSecurityException(
				_log,
				StringBundler.concat(
					"Permission ", clazz.getName(), " attempted to ", name));
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultRejectChecker.class);

}