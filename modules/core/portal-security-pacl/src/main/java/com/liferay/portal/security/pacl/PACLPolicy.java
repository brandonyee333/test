/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.url.URLContainer;

import java.net.URL;

import java.security.Permission;
import java.security.Policy;

import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public interface PACLPolicy {

	public ClassLoader getClassLoader();

	public String getContextName();

	public Policy getPolicy();

	public Properties getProperties();

	public String getProperty(String key);

	public String[] getPropertyArray(String key);

	public boolean getPropertyBoolean(String key);

	public Set<String> getPropertySet(String key);

	public URLContainer getURLContainer();

	public List<URL> getURLs();

	public boolean hasJNDI(String name);

	public boolean hasSQL(String sql);

	public boolean implies(Permission permission);

	public boolean isActive();

	public boolean isCheckablePermission(Permission permission);

}