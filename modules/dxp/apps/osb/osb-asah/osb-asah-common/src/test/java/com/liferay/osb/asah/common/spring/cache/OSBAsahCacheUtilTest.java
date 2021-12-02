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

package com.liferay.osb.asah.common.spring.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author Alejo Ceballos
 */
public class OSBAsahCacheUtilTest {

	@Test
	public void testExtractTargetClassWithNoProxyAndNoInterfaces() {
		ProxyFactory proxyFactory = new ProxyFactory();

		proxyFactory.setTarget(new DummySimpleClass());

		Object target = OSBAsahCacheUtil.extractTargetClass(
			proxyFactory.getProxy());

		Class<?> dummySimpleClassClass = DummySimpleClass.class;

		Class<?> targetClass = (Class<?>)target;

		Assertions.assertEquals(
			dummySimpleClassClass.getName(), targetClass.getName());
	}

	@Test
	public void testExtractTargetClassWithProxyAndProxiedInterface() {
		ProxyFactory proxyFactory = new ProxyFactory();

		proxyFactory.addInterface(DummySimpleInterface.class);
		proxyFactory.setTarget(new DummySimpleClass());

		Object target = OSBAsahCacheUtil.extractTargetClass(
			proxyFactory.getProxy());

		Class<?> dummySimpleInterfaceClass = DummySimpleInterface.class;

		Class<?> targetClass = (Class<?>)target;

		Assertions.assertEquals(
			dummySimpleInterfaceClass.getName(), targetClass.getName());
	}

	@Test
	public void testExtractTargetClassWithProxyAndSimpleInterface() {
		ProxyFactory proxyFactory = new ProxyFactory();

		proxyFactory.setTarget(new DummyClassWithInterface());

		Object target = OSBAsahCacheUtil.extractTargetClass(
			proxyFactory.getProxy());

		Class<?> dummyClassWithInterfaceClass = DummyClassWithInterface.class;

		Class<?> targetClass = (Class<?>)target;

		Assertions.assertEquals(
			dummyClassWithInterfaceClass.getName(), targetClass.getName());
	}

}