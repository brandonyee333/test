/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.lcs.management;

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