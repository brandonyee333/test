/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.security.pacl.PACLConstants;
import com.liferay.portal.security.pacl.PACLPolicy;
import com.liferay.portal.security.pacl.PACLUtil;

import java.security.Permission;

import java.util.Properties;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public abstract class BaseChecker implements Checker, PACLConstants {

	@Override
	public AuthorizationProperty generateAuthorizationProperty(
		Object... arguments) {

		throw new UnsupportedOperationException();
	}

	@Override
	public ClassLoader getClassLoader() {
		return _paclPolicy.getClassLoader();
	}

	@Override
	public String getContextName() {
		return _paclPolicy.getContextName();
	}

	@Override
	public PACLPolicy getPACLPolicy() {
		return _paclPolicy;
	}

	public boolean isTrustedCaller(
		Class<?> callerClass, Permission permission) {

		return PACLUtil.isTrustedCaller(
			callerClass, permission, getPACLPolicy());
	}

	@Override
	public void setPACLPolicy(PACLPolicy paclPolicy) {
		_paclPolicy = paclPolicy;
	}

	protected Properties getProperties() {
		return _paclPolicy.getProperties();
	}

	protected String getProperty(String key) {
		return _paclPolicy.getProperty(key);
	}

	protected String[] getPropertyArray(String key) {
		return _paclPolicy.getPropertyArray(key);
	}

	protected boolean getPropertyBoolean(String key) {
		return _paclPolicy.getPropertyBoolean(key);
	}

	protected Set<String> getPropertySet(String key) {
		return _paclPolicy.getPropertySet(key);
	}

	protected void logSecurityException(Log log, String message) {
		if (log.isWarnEnabled()) {
			log.warn(message);
		}
	}

	private PACLPolicy _paclPolicy;

}