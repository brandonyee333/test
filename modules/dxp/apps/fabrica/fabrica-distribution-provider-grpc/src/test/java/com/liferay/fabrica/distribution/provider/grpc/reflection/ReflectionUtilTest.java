/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fabrica.distribution.provider.grpc.reflection;

import java.lang.reflect.Method;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Miguel Pastor
 */
public class ReflectionUtilTest {

	@Test
	public void testGetInterfaceMethodsWithEmptyInterface() {
		Collection<Method> methods = ReflectionUtil.getInterfaceMethods(
			Empty.class);

		Assert.assertEquals(methods.toString(), 0, methods.size());
	}

	@Test
	public void testGetInterfaceMethodsWithNoInterface() {
		Collection<Method> methods = ReflectionUtil.getInterfaceMethods(
			String.class);

		Assert.assertEquals(methods.toString(), 0, methods.size());
	}

	@Test
	public void testGetInterfaceMethodsWithParentInterface() {
		Collection<Method> methods = ReflectionUtil.getInterfaceMethods(
			B.class);

		Assert.assertEquals(methods.toString(), 1, methods.size());
	}

	@Test
	public void testGetInterfaceMethodsWithSingleInterface() {
		Collection<Method> methods = ReflectionUtil.getInterfaceMethods(
			A.class);

		Assert.assertEquals(methods.toString(), 1, methods.size());
	}

	@Test
	public void testGetInterfaceMethodsWithStaticMethods() {
		Collection<Method> methods = ReflectionUtil.getInterfaceMethods(
			Static.class);

		Assert.assertEquals(methods.toString(), 0, methods.size());
	}

	private interface A {

		public String a();

	}

	private interface B extends A {

		public String b();

	}

	private interface Empty {
	}

	private interface Static {

		public static String m() {
			return "";
		}

	}

}