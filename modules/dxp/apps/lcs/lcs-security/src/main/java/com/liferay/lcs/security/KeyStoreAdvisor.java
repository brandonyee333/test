/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.security;

import com.liferay.portal.kernel.util.GetterUtil;

import java.security.KeyStore;

import java.util.Enumeration;

/**
 * @author Igor Beslic
 */
public class KeyStoreAdvisor {

	public String getKeyAlias(
			int buildNumber, String keyAlias, KeyStore keyStore)
		throws Exception {

		if (buildNumber == 0) {
			return keyAlias;
		}

		Enumeration<String> aliases = keyStore.aliases();

		int compatibleNumberSuffix = 0;

		while (aliases.hasMoreElements()) {
			String alias = aliases.nextElement();

			if (alias.equals(keyAlias)) {
				continue;
			}

			if (!alias.startsWith(keyAlias)) {
				continue;
			}

			int aliasNumberSuffix = GetterUtil.getInteger(
				alias.substring(keyAlias.length()));

			if (buildNumber == aliasNumberSuffix) {
				return alias;
			}

			if (buildNumber < aliasNumberSuffix) {
				continue;
			}

			if ((buildNumber > aliasNumberSuffix) &&
				(compatibleNumberSuffix < aliasNumberSuffix)) {

				compatibleNumberSuffix = aliasNumberSuffix;

				continue;
			}
		}

		if (compatibleNumberSuffix == 0) {
			return keyAlias;
		}

		return keyAlias + compatibleNumberSuffix;
	}

	public String getLatestKeyAlias(String keyAlias, KeyStore keyStore)
		throws Exception {

		Enumeration<String> aliases = keyStore.aliases();

		int latestNumberSuffix = 0;

		while (aliases.hasMoreElements()) {
			String alias = aliases.nextElement();

			if (alias.equals(keyAlias)) {
				continue;
			}

			if (!alias.startsWith(keyAlias)) {
				continue;
			}

			int aliasNumberSuffix = GetterUtil.getInteger(
				alias.substring(keyAlias.length()));

			if (latestNumberSuffix < aliasNumberSuffix) {
				latestNumberSuffix = aliasNumberSuffix;
			}
		}

		if (latestNumberSuffix == 0) {
			return keyAlias;
		}

		return keyAlias + latestNumberSuffix;
	}

}