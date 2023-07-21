/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.test.rule.PACLTestRule;

import java.net.Authenticator;
import java.net.ProxySelector;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class NetPermissionTest {

	@ClassRule
	@Rule
	public static final PACLTestRule paclTestRule = new PACLTestRule();

	@Test
	public void test1() throws Exception {
		try {
			Authenticator.setDefault(
				new Authenticator() {
				});

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test2() throws Exception {
		new URL(
			Http.HTTP, "localhost", 80, ".",
			new URLStreamHandler() {

				@Override
				protected URLConnection openConnection(URL url) {
					return null;
				}

			});
	}

	@Test
	public void test3() throws Exception {
		try {
			ProxySelector.getDefault();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test4() throws Exception {
		try {
			ProxySelector.setDefault(null);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

}