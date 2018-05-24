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