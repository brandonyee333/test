/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.impl.UserImpl;
import com.liferay.portal.security.lang.DoPrivilegedBean;
import com.liferay.portal.security.lang.DoPrivilegedFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class DoPrivilegedFactoryTest {

	@Before
	public void setUp() {
		PortalClassLoaderUtil.setClassLoader(UserImpl.class.getClassLoader());
	}

	@Test
	public void testBoolean() {
		Boolean wrappedBoolean = DoPrivilegedFactory.wrap(Boolean.TRUE);

		Assert.assertTrue(wrappedBoolean);
		Assert.assertEquals(wrappedBoolean.getClass(), Boolean.class);
	}

	@Test
	public void testClassWithNoInterfaces() {
		ClassWithNoInterfaces classWithNoInterfaces =
			new ClassWithNoInterfaces();

		ClassWithNoInterfaces wrappedClassWithNoInterfaces =
			DoPrivilegedFactory.wrap(classWithNoInterfaces);

		Assert.assertEquals(
			wrappedClassWithNoInterfaces, classWithNoInterfaces);

		Assert.assertFalse(
			wrappedClassWithNoInterfaces instanceof DoPrivilegedBean);
	}

	@Test
	public void testString() {
		String string = DoPrivilegedFactory.wrap("Test");

		Assert.assertEquals("Test", string);
		Assert.assertEquals(string.getClass(), String.class);
	}

	@Test
	public void testUser() {
		User user = DoPrivilegedFactory.wrap(new UserImpl());

		Assert.assertTrue(user instanceof DoPrivilegedBean);
	}

	private static class ClassWithNoInterfaces {
	}

}