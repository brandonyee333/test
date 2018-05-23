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

import java.io.FileOutputStream;

import java.security.KeyStore;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Igor Beslic
 */
@RunWith(JUnit4.class)
public class KeyStoreAdvisorTest extends BaseTest {

	@Test
	public void testGetKeyAlias() throws Exception {
		KeyStore keyStore = KeyStoreFactory.getInstance(
			KEY_STORE_PATH_DEFAULT_PASSWORD, "JCEKS");

		int buildNumber = 130;

		KeyStore.ProtectionParameter protectionParameter =
			new KeyStore.PasswordProtection("testpassword".toCharArray());

		while (buildNumber < 200) {
			keyStore.setEntry(
				DEFAULT_ALIAS + buildNumber, getSecretKeyEntry(),
				protectionParameter);

			buildNumber = buildNumber + 15;
		}

		keyStore.store(
			new FileOutputStream(KEY_STORE_PATH_DEFAULT_PASSWORD),
			"keystorepass".toCharArray());

		KeyStoreAdvisor keyStoreAdvisor = new KeyStoreAdvisor();

		Assert.assertEquals(
			DEFAULT_ALIAS,
			keyStoreAdvisor.getKeyAlias(0, DEFAULT_ALIAS, keyStore));
		Assert.assertEquals(
			DEFAULT_ALIAS + 130,
			keyStoreAdvisor.getKeyAlias(130, DEFAULT_ALIAS, keyStore));
		Assert.assertEquals(
			DEFAULT_ALIAS + 130,
			keyStoreAdvisor.getKeyAlias(140, DEFAULT_ALIAS, keyStore));
		Assert.assertEquals(
			DEFAULT_ALIAS + 145,
			keyStoreAdvisor.getKeyAlias(150, DEFAULT_ALIAS, keyStore));
		Assert.assertEquals(
			DEFAULT_ALIAS + 190,
			keyStoreAdvisor.getKeyAlias(205, DEFAULT_ALIAS, keyStore));
	}

}