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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * @author Riccardo Ferrari
 */
public class MBeanServerServiceImpl implements MBeanServerService {

	@Override
	public MBeanServer getMBeanServer() {
		return _mBeanServer;
	}

	@Override
	public Object getObjectNameAttribute(
			ObjectName objectName, String attributeName)
		throws Exception {

		return _mBeanServer.getAttribute(objectName, attributeName);
	}

	@Override
	public Map<String, Object> getObjectNameAttributes(
			ObjectName objectName, String[] attributeNames)
		throws Exception {

		Map<String, Object> objectNameAttributes = new HashMap<>();

		if (ArrayUtil.isEmpty(attributeNames)) {
			MBeanInfo mBeanInfo = _mBeanServer.getMBeanInfo(objectName);

			for (MBeanAttributeInfo mBeanAttributeInfo :
					mBeanInfo.getAttributes()) {

				Object attribute = _mBeanServer.getAttribute(
					objectName, mBeanAttributeInfo.getName());

				objectNameAttributes.put(
					mBeanAttributeInfo.getName(), attribute);
			}
		}
		else {
			for (String attributeName : attributeNames) {
				Object attribute = _mBeanServer.getAttribute(
					objectName, attributeName);

				objectNameAttributes.put(attributeName, attribute);
			}
		}

		return objectNameAttributes;
	}

	@Override
	public Set<ObjectName> getObjectNames(
			ObjectName objectName, List<String> attributeNames)
		throws Exception {

		if (objectName.isPattern()) {
			Set<ObjectName> objectNames = _mBeanServer.queryNames(
				objectName, null);

			ObjectName[] objectNameArray = objectNames.toArray(
				new ObjectName[objectNames.size()]);

			return toObjectNames(objectNameArray, attributeNames);
		}

		return toObjectNames(objectName, attributeNames);
	}

	@Override
	public Map<String, Map<String, Object>> getObjectNamesAttributes(
			Set<ObjectName> objectNames, String[] attributeNames,
			MapKeyStrategy mapKeyStrategy)
		throws Exception {

		Map<String, Map<String, Object>> objectNamesAttributes =
			new HashMap<>();

		if (Validator.isNull(mapKeyStrategy)) {
			mapKeyStrategy = new CanonicalNameMapKeyStrategy();
		}

		for (ObjectName objectName : objectNames) {
			objectNamesAttributes.put(
				mapKeyStrategy.getMapKey(objectName),
				getObjectNameAttributes(objectName, attributeNames));
		}

		return objectNamesAttributes;
	}

	public void setMBeanServer(MBeanServer mBeanServer) {
		_mBeanServer = mBeanServer;
	}

	protected boolean exists(ObjectName objectName) {
		Set<ObjectName> objectNames = _mBeanServer.queryNames(objectName, null);

		return !objectNames.isEmpty();
	}

	protected Set<ObjectName> toObjectNames(
			Object object, List<String> attributeNames)
		throws Exception {

		Set<ObjectName> objectNames = new HashSet<>();

		if (Validator.isNull(attributeNames)) {
			attributeNames = new ArrayList<>();
		}

		if (object instanceof ObjectName) {
			if (attributeNames.isEmpty()) {
				ObjectName objectName = (ObjectName)object;

				if (exists(objectName)) {
					objectNames.add(objectName);
				}
			}
			else {
				String attributeName = attributeNames.remove(0);

				return toObjectNames(
					_mBeanServer.getAttribute(
						(ObjectName)object, attributeName),
					attributeNames);
			}
		}
		else if (object instanceof ObjectName[]) {
			for (ObjectName objectName : (ObjectName[])object) {
				objectNames.addAll(
					toObjectNames(
						objectName, new ArrayList<String>(attributeNames)));
			}

			return objectNames;
		}

		return objectNames;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		MBeanServerServiceImpl.class);

	private MBeanServer _mBeanServer;

}