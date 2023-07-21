/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.url.URLContainer;

import java.lang.reflect.Method;

import java.security.Permission;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public class InactivePACLPolicy extends BasePACLPolicy {

	public InactivePACLPolicy(
		String contextName, URLContainer urlContainer, ClassLoader classLoader,
		Properties properties) {

		super(contextName, urlContainer, classLoader, properties);
	}

	@Override
	public boolean hasJNDI(String name) {
		return true;
	}

	public boolean hasPortalService(
		Object object, Method method, Object[] arguments) {

		return true;
	}

	@Override
	public boolean hasSQL(String sql) {
		return true;
	}

	@Override
	public boolean implies(Permission permission) {
		return true;
	}

	@Override
	public boolean isActive() {
		return false;
	}

}