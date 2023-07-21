/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.checker;

import com.liferay.portal.security.pacl.PACLPolicy;

import java.security.Permission;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public interface Checker {

	public void afterPropertiesSet();

	public AuthorizationProperty generateAuthorizationProperty(
		Object... arguments);

	public ClassLoader getClassLoader();

	public String getContextName();

	public PACLPolicy getPACLPolicy();

	public boolean implies(Permission permission);

	public void setPACLPolicy(PACLPolicy paclPolicy);

}