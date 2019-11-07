/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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