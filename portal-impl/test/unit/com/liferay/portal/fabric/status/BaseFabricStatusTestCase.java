/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.fabric.status;

import com.liferay.portal.kernel.util.Accessor;
import com.liferay.portal.kernel.util.ListUtil;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.PlatformLoggingMXBean;
import java.lang.management.PlatformManagedObject;

import java.util.List;

import javax.management.ObjectName;

import org.junit.Assert;

/**
 * @author Shuyang Zhou
 */
public abstract class BaseFabricStatusTestCase {

	protected void assertEquals(
		List<? extends PlatformManagedObject> platformManagedObjects1,
		List<? extends PlatformManagedObject> platformManagedObjects2) {

		Assert.assertArrayEquals(
			ListUtil.toArray(platformManagedObjects1, accessor),
			ListUtil.toArray(platformManagedObjects2, accessor));
	}

	protected void assertEquals(
		PlatformManagedObject platformManagedObject1,
		PlatformManagedObject platformManagedObject2) {

		Assert.assertEquals(
			platformManagedObject1.getObjectName(),
			platformManagedObject2.getObjectName());
	}

	protected void doTestObjectNames(FabricStatus fabricStatus) {
		assertEquals(
			ManagementFactory.getClassLoadingMXBean(),
			fabricStatus.getClassLoadingMXBean());
		assertEquals(
			ManagementFactory.getCompilationMXBean(),
			fabricStatus.getCompilationMXBean());
		assertEquals(
			ManagementFactory.getGarbageCollectorMXBeans(),
			fabricStatus.getGarbageCollectorMXBeans());
		assertEquals(
			ManagementFactory.getMemoryMXBean(),
			fabricStatus.getMemoryMXBean());
		assertEquals(
			ManagementFactory.getMemoryManagerMXBeans(),
			fabricStatus.getMemoryManagerMXBeans());
		assertEquals(
			ManagementFactory.getMemoryPoolMXBeans(),
			fabricStatus.getMemoryPoolMXBeans());
		assertEquals(
			ManagementFactory.getOperatingSystemMXBean(),
			fabricStatus.getAdvancedOperatingSystemMXBean());
		assertEquals(
			ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class),
			fabricStatus.getBufferPoolMXBeans());
		assertEquals(
			ManagementFactory.getPlatformMXBean(PlatformLoggingMXBean.class),
			fabricStatus.getPlatformLoggingMXBean());
		assertEquals(
			ManagementFactory.getRuntimeMXBean(),
			fabricStatus.getRuntimeMXBean());
		assertEquals(
			ManagementFactory.getThreadMXBean(),
			fabricStatus.getThreadMXBean());
	}

	protected static final Accessor<PlatformManagedObject, ObjectName>
		accessor = new Accessor<PlatformManagedObject, ObjectName>() {

			@Override
			public ObjectName get(PlatformManagedObject platformManagedObject) {
				return platformManagedObject.getObjectName();
			}

			@Override
			public Class<ObjectName> getAttributeClass() {
				return ObjectName.class;
			}

			@Override
			public Class<PlatformManagedObject> getTypeClass() {
				return PlatformManagedObject.class;
			}

		};

}