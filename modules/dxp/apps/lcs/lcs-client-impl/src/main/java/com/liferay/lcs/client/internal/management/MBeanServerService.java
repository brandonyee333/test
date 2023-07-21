/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.management;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author Riccardo Ferrari
 */
public interface MBeanServerService {

	public MBeanServer getMBeanServer();

	public Object getObjectNameAttribute(
			ObjectName objectName, String attributeName)
		throws Exception;

	public Map<String, Object> getObjectNameAttributes(
			ObjectName objectName, String[] attributeNames)
		throws Exception;

	public Set<ObjectName> getObjectNames(
			ObjectName objectName, List<String> attributeNames)
		throws Exception;

	public Map<String, Map<String, Object>> getObjectNamesAttributes(
			Set<ObjectName> objectNames, String[] attributeNames,
			MapKeyStrategy mapKeyStrategy)
		throws Exception;

}