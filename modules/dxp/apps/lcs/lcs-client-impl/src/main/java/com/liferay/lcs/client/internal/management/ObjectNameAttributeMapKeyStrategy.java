/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.management;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author Riccardo Ferrari
 */
public class ObjectNameAttributeMapKeyStrategy implements MapKeyStrategy {

	public ObjectNameAttributeMapKeyStrategy(
		MBeanServer mBeanServer, String attributeName) {

		_mBeanServer = mBeanServer;
		_attributeName = attributeName;
	}

	@Override
	public String getMapKey(ObjectName objectName) {
		String mapKey = null;

		try {
			mapKey = String.valueOf(
				_mBeanServer.getAttribute(objectName, _attributeName));
		}
		catch (Exception e) {
			mapKey = objectName.getCanonicalName();
		}

		return mapKey;
	}

	private final String _attributeName;
	private final MBeanServer _mBeanServer;

}