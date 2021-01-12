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

package com.liferay.portal.crypto.hash.provider.message.digest;

import com.liferay.portal.crypto.hash.provider.message.digest.internal.MessageDigestCryptoHashProvider;
import com.liferay.portal.crypto.hash.provider.message.digest.internal.factory.MessageDigestCryptoHashProviderFactory;
import com.liferay.portal.crypto.hash.spi.CryptoHashProvider;
import com.liferay.portal.crypto.hash.spi.salt.VariableSizeSaltProvider;
import com.liferay.portal.kernel.util.UnicodeFormatter;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.MockitoAnnotations;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Arthur Chan
 */
@PrepareForTest(MessageDigestCryptoHashProvider.class)
@RunWith(PowerMockRunner.class)
public class MessageDigestCryptoHashProviderTest extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		MessageDigestCryptoHashProviderFactory cryptoHashProviderFactory =
			new MessageDigestCryptoHashProviderFactory();

		_cryptoHashProvider = cryptoHashProviderFactory.create(
			_CRYPTO_HASH_PROVIDER_NAME, new HashMap<>());
	}

	@Test
	public void testGenerateDefaultSalt() throws Exception {
		byte[] salt = _cryptoHashProvider.generateSalt();

		Assert.assertEquals(salt.length, Long.BYTES * 2);
	}

	@Test
	public void testGenerateVariableSizeSalt() throws Exception {
		VariableSizeSaltProvider variableSizeSaltGenerator =
			(VariableSizeSaltProvider)_cryptoHashProvider;

		byte[] salt = variableSizeSaltGenerator.generateSalt(_VARIABLE_SIZE);

		Assert.assertEquals(salt.length, _VARIABLE_SIZE);
	}

	@Test
	public void testHash() throws Exception {
		byte[] hash = _cryptoHashProvider.generate(_EMPTY_BYTE_ARRAY, _INPUT);

		Assert.assertArrayEquals(_HASH, hash);
	}

	@Test
	public void testHashWithSalt() throws Exception {
		byte[] hash = _cryptoHashProvider.generate(_SALT, _INPUT);

		Assert.assertArrayEquals(_HASH_WITH_SALT, hash);
	}

	private static final String _CRYPTO_HASH_PROVIDER_NAME = "SHA-256";

	private static final byte[] _EMPTY_BYTE_ARRAY = new byte[0];

	private static final byte[] _HASH = UnicodeFormatter.hexToBytes(
		"5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8");

	private static final byte[] _HASH_WITH_SALT = UnicodeFormatter.hexToBytes(
		"13601bda4ea78e55a07b98866d2be6be0744e3866f13c00c811cab608a28f322");

	private static final byte[] _INPUT = "password".getBytes();

	private static final byte[] _SALT = "salt".getBytes();

	private static final int _VARIABLE_SIZE = 10;

	private CryptoHashProvider _cryptoHashProvider;

}