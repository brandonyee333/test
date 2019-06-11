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

package com.liferay.lcs.client.internal.management;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.lang.management.ManagementFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Ferrari
 */
@Component(service = MBeanServerService.class)
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
				try {
					Object attribute = _mBeanServer.getAttribute(
						objectName, attributeName);

					objectNameAttributes.put(attributeName, attribute);
				}
				catch (AttributeNotFoundException anfe) {
					if (_log.isDebugEnabled()) {
						_log.debug(anfe.getMessage());
					}
				}
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
				new ObjectName[0]);

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

		if (mapKeyStrategy == null) {
			mapKeyStrategy = new CanonicalNameMapKeyStrategy();
		}

		for (ObjectName objectName : objectNames) {
			objectNamesAttributes.put(
				mapKeyStrategy.getMapKey(objectName),
				getObjectNameAttributes(objectName, attributeNames));
		}

		return objectNamesAttributes;
	}

	@Activate
	protected void activate() {
		MBeanServer mBeanServer = null;

		List<MBeanServer> servers = MBeanServerFactory.findMBeanServer(null);

		if (!servers.isEmpty()) {
			mBeanServer = servers.get(0);
		}

		if (mBeanServer == null) {
			try {
				mBeanServer = ManagementFactory.getPlatformMBeanServer();
			}
			catch (SecurityException se) {
				throw new IllegalStateException(
					"No specific MBeanServer found, and not allowed to " +
						"obtain the Java platform MBeanServer",
					se);
			}
		}

		if (mBeanServer == null) {
			throw new IllegalStateException(
				"Unable to locate an MBeanServer instance");
		}

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

		if (attributeNames == null) {
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