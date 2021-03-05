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

package com.liferay.portal.crypto.hash.internal;

import com.liferay.portal.crypto.hash.spi.CryptoHashProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Carlos Sierra Andrés
 * @author Arthur Chan
 */
public class CryptoHashProviderRegistry {

	public static CryptoHashProvider getCryptoHashProvider(
		String cryptoHashProviderName) {

		return _cryptoHashProviders.get(cryptoHashProviderName);
	}

	public static void register(CryptoHashProvider cryptoHashProvider) {
		_cryptoHashProviders.put(
			cryptoHashProvider.getCryptoHashProviderName(), cryptoHashProvider);
	}

	public static void unregister(String cryptoHashProviderName) {
		_cryptoHashProviders.remove(cryptoHashProviderName);
	}

	private CryptoHashProviderRegistry() {
	}

	private static final Map<String, CryptoHashProvider> _cryptoHashProviders =
		new HashMap<>();

}