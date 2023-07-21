/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl;

import java.security.Permission;
import java.security.PermissionCollection;

import java.util.Collections;
import java.util.Enumeration;

/**
 * @author Raymond Augé
 */
public class LenientPermissionCollection extends PermissionCollection {

	@Override
	public void add(Permission permission) {
	}

	@Override
	public Enumeration<Permission> elements() {
		return Collections.enumeration(Collections.<Permission>emptyList());
	}

	@Override
	public boolean implies(Permission permission) {
		return true;
	}

}