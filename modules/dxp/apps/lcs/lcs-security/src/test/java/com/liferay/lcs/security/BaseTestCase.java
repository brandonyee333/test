/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.security;

import java.io.File;
import java.io.FileOutputStream;

import java.security.KeyStore;

import javax.crypto.spec.SecretKeySpec;

import org.junit.After;
import org.junit.Before;

/**
 * @author Igor Beslic
 */
public abstract class BaseTestCase {

	@Before
	public void setUp() throws Exception {
		createKeyStore(KEY_STORE_PATH_CUSTOM_PASSWORD, "JCEKS", "keystorepass");

		createKeyStore(KEY_STORE_PATH_DEFAULT_PASSWORD, "JCEKS", null);
	}

	@After
	public void tearDown() {
		File file = new File(KEY_STORE_PATH_CUSTOM_PASSWORD);

		file.delete();

		file = new File(KEY_STORE_PATH_DEFAULT_PASSWORD);

		file.delete();
	}

	protected KeyStore createKeyStore(
			String keyStorePath, String keyStoreType, String keyStorePassword)
		throws Exception {

		String password = "_k3y#5t0r3-p45S";

		if (keyStorePassword != null) {
			password = keyStorePassword;
		}

		FileOutputStream fileOutputStream = new FileOutputStream(keyStorePath);

		KeyStore keyStore = KeyStore.getInstance(keyStoreType);

		keyStore.load(null, null);

		KeyStore.ProtectionParameter protectionParameter =
			new KeyStore.PasswordProtection(password.toCharArray());

		keyStore.setEntry(
			DEFAULT_ALIAS, getSecretKeyEntry(), protectionParameter);

		keyStore.store(fileOutputStream, password.toCharArray());

		fileOutputStream.close();

		return keyStore;
	}

	protected KeyStore.SecretKeyEntry getSecretKeyEntry() {
		byte[] seed = "eru9tyighw34ilty348934i34uiq34q34ri".getBytes();

		return new KeyStore.SecretKeyEntry(
			new SecretKeySpec(seed, 0, seed.length, "AES"));
	}

	protected static final String DEFAULT_ALIAS = "testalias";

	protected static final String KEY_STORE_PATH_CUSTOM_PASSWORD =
		"keyStoreCustomPassword.jks";

	protected static final String KEY_STORE_PATH_DEFAULT_PASSWORD =
		"keyStoreDefaultPassword.jks";

}