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
public class KeyStoreFactoryTest extends BaseTest {

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