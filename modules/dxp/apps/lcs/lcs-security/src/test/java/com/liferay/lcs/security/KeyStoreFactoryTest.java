/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.security;

import java.security.KeyStore;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Igor Beslic
 */
@RunWith(JUnit4.class)
public class KeyStoreFactoryTest extends BaseTestCase {

	@Test
	public void testGetInstance() throws Exception {
		KeyStore keyStore = KeyStoreFactory.getInstance(
			"keyStoreCustomPassword.jks", "JCEKS", "keystorepass");

		Assert.assertTrue(keyStore.containsAlias("testalias"));
	}

	@Test
	public void testGetInstanceDefaultPassword() throws Exception {
		KeyStore keyStore = KeyStoreFactory.getInstance(
			"keyStoreDefaultPassword.jks", "JCEKS");

		Assert.assertTrue(keyStore.containsAlias("testalias"));
	}

}