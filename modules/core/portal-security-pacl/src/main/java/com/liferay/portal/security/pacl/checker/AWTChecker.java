/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.awt.AWTPermission;

import java.security.Permission;
import java.security.Permissions;

import java.util.Set;

/**
 * @author Raymond Augé
 */
public class AWTChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
		initOperations();
	}

	@Override
	public AuthorizationProperty generateAuthorizationProperty(
		Object... arguments) {

		if ((arguments != null) && (arguments.length == 1) &&
			(arguments[0] instanceof Permission)) {

			return null;
		}

		Permission permission = (Permission)arguments[0];

		AuthorizationProperty authorizationProperty =
			new AuthorizationProperty();

		authorizationProperty.setKey("security-manager-awt-operations");
		authorizationProperty.setValue(permission.getName());

		return authorizationProperty;
	}

	@Override
	public boolean implies(Permission permission) {
		if (_permissions.implies(permission)) {
			return true;
		}

		String name = permission.getName();

		logSecurityException(_log, "Attempted operation " + name + " on AWT");

		return false;
	}

	protected void initOperations() {
		Set<String> names = getPropertySet("security-manager-awt-operations");

		for (String name : names) {
			Permission permission = new AWTPermission(name);

			_permissions.add(permission);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(AWTChecker.class);

	private final Permissions _permissions = new Permissions();

}