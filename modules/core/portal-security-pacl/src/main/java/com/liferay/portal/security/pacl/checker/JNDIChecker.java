/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.security.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class JNDIChecker extends BaseChecker {

	@Override
	public void afterPropertiesSet() {
		initNames();
	}

	@Override
	public AuthorizationProperty generateAuthorizationProperty(
		Object... arguments) {

		if ((arguments == null) || (arguments.length != 1) ||
			!(arguments[0] instanceof String)) {

			return null;
		}

		AuthorizationProperty authorizationProperty =
			new AuthorizationProperty();

		authorizationProperty.setKey("security-manager-jndi-names");
		authorizationProperty.setValue((String)arguments[0]);

		return authorizationProperty;
	}

	public boolean hasJNDI(String name) {
		for (Pattern pattern : _patterns) {
			Matcher matcher = pattern.matcher(name);

			if (matcher.matches()) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean implies(Permission permission) {
		throw new UnsupportedOperationException();
	}

	protected void initNames() {
		Set<String> names = getPropertySet("security-manager-jndi-names");

		_patterns = new ArrayList<>(names.size());

		for (String name : names) {
			Pattern pattern = Pattern.compile(name);

			_patterns.add(pattern);

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Allowing access to JNDI names that match the regular " +
						"expression " + name);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(JNDIChecker.class);

	private List<Pattern> _patterns;

}