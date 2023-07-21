/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
public class KeyStoreAdvisorTest extends BaseTestCase {

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